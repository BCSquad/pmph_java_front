package com.bc.pmpheep.general.controller;

import com.bc.pmpheep.back.authadmin.usermanage.service.OrgUserService;
import com.bc.pmpheep.back.authadmin.usermanage.service.WriterUserService;
import com.bc.pmpheep.back.interceptor.LoginInterceptor;
import com.bc.pmpheep.back.sessioncontext.SessionListener;
import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.back.util.MD5;
import com.bc.pmpheep.general.service.UserService;
import com.bc.pmpheep.utils.HttpRequestUtil;
import com.zving.zas.client.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.struts.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lihuan on 2017/12/14.
 */
@Controller
public class SSOLoginoutController extends BaseController {

    Logger logger = LoggerFactory.getLogger(SSOLoginoutController.class);

    @Autowired
    UserService userService;


    @Autowired
    LoginInterceptor interceptor;

    @Autowired
    EhCacheCacheManager cacheCacheManager;

    private static final ThreadLocal<PathMatchingResourcePatternResolver> resolvers;

    private static final ThreadLocal<CloseableHttpClient> httpclients;

    static {
        resolvers = new ThreadLocal<PathMatchingResourcePatternResolver>();
        httpclients = new ThreadLocal<CloseableHttpClient>();
    }

    private PathMatchingResourcePatternResolver getPathMatchingResourcePatternResolver() {
        if (resolvers.get() == null) {
            resolvers.set(new PathMatchingResourcePatternResolver());
        }
        return resolvers.get();
    }

    private CloseableHttpClient getCloseableHttpClient() {
        if (httpclients.get() == null) {
            httpclients.set(HttpClients.createDefault());
        }
        return httpclients.get();
    }

   /* private static final CloseableHttpClient httpclient = HttpClients.createDefault();*/

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
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String ticket = request.getParameter("UserData");
        String username = null;

        StringBuilder builder = new StringBuilder("");
        Map<String, String[]> map = (Map<String, String[]>) request.getParameterMap();
        for (String name : map.keySet()) {
            String[] values = map.get(name);
            builder.append(name + "=" + (values.length > 0 ? values[0] : "") + "&");
        }




        if (ticket != null && !ticket.equals("")) {
            ServiceTicketValidator stv = new ServiceTicketValidator();
            String data = request.getParameter("UserData");
            System.out.println(data);
            data = data.replaceAll("   ", "+");
            System.out.println(data);
            data = new String(ZASUtil.base64Decode(data), "GBK");
            data = data.replaceAll("<\\?xml", "mark#1");
            data = data.replaceAll("\"UTF-8\"\\?>", "mark#2");
            data = data.replaceAll("\\?", ">");
            data = data.replaceAll("mark#1", "<?xml");
            data = data.replaceAll("mark#2", "\"UTF-8\"?>");
            System.out.println(data);
            stv.validateTrustMode(data);

            ZASUserData userData = stv.getUser();
            username = userData.getUserName();
        } else {
            logger.warn("错误的回调参数：" + builder);
            response.sendRedirect(request.getContextPath() + "/");
        }


/*

        Cache cache = cacheCacheManager.getCache("shiro-pmph-authenticationCache");

        try {
            HttpGet httpget = new HttpGet(url + "ServiceValidate.jsp?ServiceID=" + serviceID + "&ST=" + ticket);
            CloseableHttpResponse closeableHttpResponse = getCloseableHttpClient().execute(httpget);
            HttpEntity entity = closeableHttpResponse.getEntity();
            String xmlString = EntityUtils.toString(entity);
         */
/*   System.out.println(xmlString);*//*

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();
            //通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
            Document document = db.parse(new ByteArrayInputStream(xmlString.getBytes("utf-8")));
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
                if (cache.get(ticket) == null) {
                    logger.error("==========================STERROR:" + ticket);
                    logger.error("===========================params:" + builder);
                    logger.error(HttpRequestUtil.getClientIP(request) + ":" + xmlString);
                    response.sendRedirect(request.getContextPath() + "/");
                    return;
                } else {
                    username = cache.get(ticket, String.class);
                }

            } else {
                logger.info("==========================STOK:" + ticket);
                cache.put(ticket, username);
            }
*/


        List<Map<String, Object>> types = userService.getUserType(username);
        String usertype = "1";
        if (types.size() == 0) {
            usertype = "1";
        } else {
            if(types.size()>1){
                usertype = MapUtils.getString(types.get(1), "type", "1");
            }else{
                usertype = MapUtils.getString(types.get(0), "type", "1");
            }
        }

        Map<String, Object> user = userService.getUserInfo(username, usertype);

        if (user == null) {

            //新建用户信息

            userService.addNewUser(username, usertype);
            user = userService.getUserInfo(username, usertype);
            // throw new RuntimeException("获取用户帐号不存在");
        }

        //接入微信登录：此处需要考虑微信SSO登录的情况，如果是微信来的请求把请求转发给微信服务器
        if (StringUtils.isNotEmpty(request.getParameter("Referer"))) {
            String referUrl = request.getParameter("Referer");
            //解析参数，判断是否是微信过来的
            if (referUrl.split("\\?").length >= 2) {
                String stringParams = referUrl.split("\\?")[1];
                for (String stringParam : stringParams.split("&")) {
                    String[] param = stringParam.split("=");
                    if (param.length >= 2) {
                        if (param[0].equals("weixinref")) {//此处可以判断为微信过来的请求,跳转到微信的内部登录
                            response.sendRedirect(referUrl.split("\\?")[0] +
                                    "?username=" + username +
                                    "&usertype=" + usertype +
                                    "&refer=" + param[1] +
                                    "&md5=" + MD5.md5(username + "1005387596c57c2278f4f61058c78d1b" + new SimpleDateFormat("yyyyMMdd").format(new Date())).toLowerCase());
                            return;
                        }
                    }
                }
            }
        }


        HttpSession session = request.getSession();
        SessionListener sessionListener = new SessionListener();



        if ("1".equals(usertype)) {
            session.setAttribute(Const.SESSION_USER_CONST_WRITER, user);
            session.setAttribute(Const.SESSION_USER_CONST_TYPE, "1");
            HttpSessionBindingEvent httpSessionBindingEvent = new HttpSessionBindingEvent(session,"user");

            String sessionId = session.getId();
            if(SessionListener.LOGIN_USER_MAP.containsKey(new Long(user.get("id").toString()))){//存在用户
                if(!sessionId.equalsIgnoreCase(SessionListener.LOGIN_USER_MAP.get(new Long(user.get("id").toString())))){//判断是否相同的sessionID
                    SessionListener.LOGOUT_USER_MAP.put(new Long(user.get("id").toString()), SessionListener.LOGIN_USER_MAP.get(new Long(user.get("id").toString())));
                    SessionListener.LOGIN_USER_MAP.put(new Long(user.get("id").toString()), sessionId);
                }
            }else {
                SessionListener.LOGIN_USER_MAP.put(new Long(user.get("id").toString()), sessionId);

            }


        } else if ("2".equals(usertype)) {
            session.setAttribute(Const.SESSION_USER_CONST_ORGUSER, user);
            session.setAttribute(Const.SESSION_USER_CONST_TYPE, "2");

        }


        Map<String, Object> params = new HashMap<String, Object>(user);

        params.put("clientip", HttpRequestUtil.getClientIP(request));

        userService.insertUserScores(params);
        userService.insertUserLoginLog(params);


        if (StringUtils.isEmpty(request.getParameter("Referer"))) {
            if ("1".equals(usertype)) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            } else if ("2".equals(usertype)) {
                response.sendRedirect(request.getContextPath() + "/schedule/scheduleList.action");
                return;
            }

        } else {

            if ("1".equals(usertype)) {
                response.sendRedirect(request.getParameter("Referer"));
                return;
            }


            Set<LoginInterceptor.PathWithUsertypeMap> pathWithUsertypeMaps = interceptor.getPathWithUsertypeMaps();

            PathMatchingResourcePatternResolver resolver = getPathMatchingResourcePatternResolver();
            for (LoginInterceptor.PathWithUsertypeMap pathMap : pathWithUsertypeMaps) {
                //需要拦截的URL
                String comparePath = "";
                if (request.getParameter("Referer").startsWith(request.getScheme())) {
                    comparePath = request.getScheme() + "://" + request.getServerName() +
                                /*":" + request.getServerPort() +*/ request.getContextPath() + pathMap.getPath();
                } else {
                    comparePath = request.getContextPath() + pathMap.getPath();
                }

                if (resolver.getPathMatcher().match(comparePath, request.getParameter("Referer"))
                        && pathMap.getUserType().equals(usertype) && "2".equals(usertype)) {//路径匹配，并且用户身份一致可以跳转

                    response.sendRedirect(request.getParameter("Referer"));
                    return;

                }
            }


            if ("1".equals(usertype)) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            } else if ("2".equals(usertype)) {
                response.sendRedirect(request.getContextPath() + "/schedule/scheduleList.action");
                return;
            }
        }

    }

    @RequestMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        Set<LoginInterceptor.PathWithUsertypeMap> pathWithUsertypeMaps = interceptor.getPathWithUsertypeMaps();
        PathMatchingResourcePatternResolver resolver = getPathMatchingResourcePatternResolver();

        String home1 = URLEncoder.encode(request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/homepage/tohomepage.action", "UTF-8");
        String home2 = URLEncoder.encode(request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/schedule/scheduleList.action", "UTF-8");

        if ("1".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
            session.removeAttribute(Const.SESSION_USER_CONST_WRITER);
            String headReferer = request.getHeader("Referer");
            session.removeAttribute(Const.SESSION_USER_CONST_TYPE);


            response.sendRedirect(logouturl + "?ServiceID=" + serviceID + "&Referer=" + home1);
        } else if ("2".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
            session.removeAttribute(Const.SESSION_USER_CONST_ORGUSER);
            session.removeAttribute(Const.SESSION_USER_CONST_ORGUSER);
            String headReferer = request.getHeader("Referer");

            response.sendRedirect(logouturl + "?ServiceID=" + serviceID + "&Referer=" + home1);
        } else {
            response.sendRedirect(logouturl + "?ServiceID=" + serviceID + "&Referer=" + home1);
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
                if ("1".equals(usertype)) {
                    response.sendRedirect(url + "?ServiceID=" + serviceID + "&Referer=" + request.getContextPath() + "/homepage/tohomepage.action");
                } else if ("2".equals(usertype)) {
                    response.sendRedirect(url + "?ServiceID=" + serviceID + "&Referer=" + request.getContextPath() + "/schedule/scheduleList.action");
                }
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


            if (!StringUtils.isEmpty(refer)) {
                response.sendRedirect(refer);
            } else if ("1".equals(usertype)) {
                response.sendRedirect(request.getContextPath() + "/homepage/tohomepage.action");
            } else if ("2".equals(usertype)) {
                response.sendRedirect(request.getContextPath() + "/schedule/scheduleList.action");
            }
        } else {
            response.setStatus(500);
        }
    }
    @RequestMapping(value = "loginToChange", method = RequestMethod.GET)
    public void login2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
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
    }

}
