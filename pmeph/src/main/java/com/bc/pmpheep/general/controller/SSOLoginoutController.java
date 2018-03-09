package com.bc.pmpheep.general.controller;

import com.bc.pmpheep.back.interceptor.LoginInterceptor;
import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.back.util.MD5;
import com.bc.pmpheep.general.service.UserService;
import com.bc.pmpheep.utils.HttpRequestUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.struts.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by lihuan on 2017/12/14.
 */
@Controller
public class SSOLoginoutController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    LoginInterceptor interceptor;

    private static final ThreadLocal<PathMatchingResourcePatternResolver> resolvers;

    static {
        resolvers = new ThreadLocal<PathMatchingResourcePatternResolver>();
    }

    private PathMatchingResourcePatternResolver getPathMatchingResourcePatternResolver() {
        if (resolvers.get() == null) {
            resolvers.set(new PathMatchingResourcePatternResolver());
        }
        return resolvers.get();
    }


    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    private String serviceID;

    private String url;

    private String logouturl;

    @Value("${sso.serviceID}")
    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    @Value("${sso.url}")
    public void setUrl(String url) {
        this.url = url;
    }

    @Value("${sso.logouturl}")
    public void setLogouturl(String logouturl) {
        this.logouturl = logouturl;
    }

    @RequestMapping("weblogin")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String username = null;
        String usertype;
        if ("1".equals(request.getParameter("isOrganUserName"))) {
            usertype = "2";
        } else {
            usertype = "1";
        }

        StringBuilder builder = new StringBuilder("");
        Map<String, String[]> map = (Map<String, String[]>) request.getParameterMap();
        for (String name : map.keySet()) {
            String[] values = map.get(name);
            builder.append(name + "=" + (values.length > 0 ? values[0] : "") + "&");
        }
        System.out.println(builder);


        String ticket = request.getParameter("ST");
        try {
            HttpGet httpget = new HttpGet(url + "ServiceValidate.jsp?ServiceID=" + serviceID + "&ST=" + ticket);
            CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpget);
            HttpEntity entity = closeableHttpResponse.getEntity();
            String xmlString = EntityUtils.toString(entity);
            System.out.println(xmlString);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();
            //通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
            Document document = db.parse(new ByteArrayInputStream(xmlString.getBytes()));
            NodeList bookList = document.getElementsByTagName("var");
            String status = null;
            String message = null;
            for (int i = 0; i < bookList.getLength(); i++) {
                Node node = bookList.item(i);
                NamedNodeMap attrs = node.getAttributes();
                if ("Status".equals(attrs.getNamedItem("name").getNodeValue())) {
                    status = node.getTextContent();
                }
                if ("Message".equals(attrs.getNamedItem("name").getNodeValue())) {
                    message = node.getTextContent();
                }
                if ("UserName".equals(attrs.getNamedItem("name").getNodeValue())) {
                    username = node.getTextContent();
                }
            }
            if (!"OK".equals(status)) {
                throw new RuntimeException("获取用户帐号失败:" + message);
            }
            System.out.println("username:" + username);
            Map<String, Object> user = userService.getUserInfo(username, usertype);
            if (user == null) {

                //新建用户信息

                userService.addNewUser(username, usertype);
                user = userService.getUserInfo(username, usertype);
                // throw new RuntimeException("获取用户帐号不存在");
            }


            HttpSession session = request.getSession();
            if ("1".equals(usertype)) {
                session.setAttribute(Const.SESSION_USER_CONST_WRITER, user);
                session.setAttribute(Const.SESSION_USER_CONST_TYPE, "1");
            } else if ("2".equals(usertype)) {
                session.setAttribute(Const.SESSION_USER_CONST_ORGUSER, user);
                session.setAttribute(Const.SESSION_USER_CONST_TYPE, "2");
            }

            if (StringUtils.isEmpty(request.getParameter("refer")) || request.getParameter("refer").endsWith("/") || request.getParameter("refer").endsWith("tohomepage.action")) {
                if ("1".equals(usertype)) {
                    response.sendRedirect(request.getContextPath() + "/");
                } else if ("2".equals(usertype)) {
                    response.sendRedirect(request.getContextPath() + "/schedule/scheduleList.action");
                }
                Map<String, Object> params = new HashMap<String, Object>(user);

                params.put("clientip", HttpRequestUtil.getClientIP(request));

                userService.insertUserScores(params);
                userService.insertUserLoginLog(params);


                Set<LoginInterceptor.PathWithUsertypeMap> pathWithUsertypeMaps = interceptor.getPathWithUsertypeMaps();

                PathMatchingResourcePatternResolver resolver = getPathMatchingResourcePatternResolver();
                for (LoginInterceptor.PathWithUsertypeMap pathMap : pathWithUsertypeMaps) {
                    //需要拦截的URL
                    if (resolver.getPathMatcher().match(request.getContextPath() + pathMap.getPath(), request.getParameter("refer"))
                            && pathMap.getUserType().equals(usertype)) {//路径匹配，并且用户身份一致可以跳转

                        response.sendRedirect(request.getParameter("refer"));
                        return;

                    }
                }

                if ("1".equals(usertype)) {
                    response.sendRedirect(request.getContextPath() + "/homepage/tohomepage.action");
                } else if ("2".equals(usertype)) {
                    response.sendRedirect(request.getContextPath() + "/schedule/scheduleList.action");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        Set<LoginInterceptor.PathWithUsertypeMap> pathWithUsertypeMaps = interceptor.getPathWithUsertypeMaps();
        PathMatchingResourcePatternResolver resolver = getPathMatchingResourcePatternResolver();

        if ("1".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
            session.removeAttribute(Const.SESSION_USER_CONST_WRITER);
            String headReferer = request.getHeader("Referer");
            String refer = StringUtils.isEmpty(headReferer) ? request.getHeader("referer") : headReferer;


            response.sendRedirect(logouturl + "?ServiceID=" + serviceID + "Referer=" + request.getContextPath() + "/homepage/tohomepage.action");
               /* if (refer != null) {
                    refer = refer.substring(refer.indexOf(request.getContextPath()), refer.length());
                }

                for (LoginInterceptor.PathWithUsertypeMap pathMap : pathWithUsertypeMaps) {
                    //需要拦截的URL
                    if (resolver.getPathMatcher().match(request.getContextPath() + pathMap.getPath(), refer)) {//路径匹配

                        return;
                    }
                }*/

        } else if ("2".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
            session.removeAttribute(Const.SESSION_USER_CONST_ORGUSER);
            String headReferer = request.getHeader("Referer");
            String refer = StringUtils.isEmpty(headReferer) ? request.getHeader("referer") : headReferer;
            for (LoginInterceptor.PathWithUsertypeMap pathMap : pathWithUsertypeMaps) {
                //需要拦截的URL
                if (resolver.getPathMatcher().match(request.getContextPath() + pathMap.getPath(), refer)) {//路径匹配

                    response.sendRedirect(logouturl + "?ServiceID=" + serviceID + "Referer=" + request.getContextPath() + "/schedule/scheduleList.action");
                    return;

                }
            }
            response.sendRedirect(logouturl + "?ServiceID=" + serviceID + "Referer=" + refer);
        } else {
            response.sendRedirect(logouturl + "?ServiceID=" + serviceID + "Referer=" + request.getContextPath() + "/homepage/tohomepage.action");
        }


    }


    @RequestMapping(value = "innerlogin", method = RequestMethod.GET)
    public void innerLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String value = MD5.md5(username + "1005387596c57c2278f4f61058c78d1b" + new SimpleDateFormat("yyyyMMdd").format(new Date())).toLowerCase();
        String refer = request.getParameter("refer");
        if ((StringUtils.isEmpty(request.getParameter("md5")) ? "" : request.getParameter("md5")).toLowerCase().equals(value)) {
            String usertype = request.getParameter("usertype");
            Map<String, Object> user = userService.getUserInfo(username, usertype);
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/pages/comm/login.jsp?refer=" + request.getParameter("refer") + "&msg=username is not exist");
                return;
            }
            HttpSession session = request.getSession();
            if ("1".equals(usertype)) {
                session.setAttribute(Const.SESSION_USER_CONST_WRITER, user);
                session.setAttribute(Const.SESSION_USER_CONST_TYPE, "1");
            } else if ("2".equals(usertype)) {
                session.setAttribute(Const.SESSION_USER_CONST_ORGUSER, user);
                session.setAttribute(Const.SESSION_USER_CONST_TYPE, "2");
            }


            if(!StringUtils.isEmpty(refer)){
                response.sendRedirect(refer);
            }else if ("1".equals(usertype)) {
                response.sendRedirect(request.getContextPath() + "/homepage/tohomepage.action");
            } else if ("2".equals(usertype)) {
                response.sendRedirect(request.getContextPath() + "/schedule/scheduleList.action");
            }
        } else {
            response.setStatus(500);
        }
    }


}
