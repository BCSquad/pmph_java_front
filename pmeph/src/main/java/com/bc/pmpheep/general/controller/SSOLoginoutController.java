package com.bc.pmpheep.general.controller;

import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.general.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by lihuan on 2017/12/14.
 */
@Controller
public class SSOLoginoutController {

    @Autowired
    UserService userService;

    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    @RequestMapping("login")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String username = null;
        String usertype;
        if ("1".equals(request.getParameter("isOrganUserName"))) {
            usertype = "2";
        } else {
            usertype = "1";
        }

        String ticket = request.getParameter("ST");
        try {
            HttpGet httpget = new HttpGet("http://sso.ipmph.com/ServiceValidate.jsp?ServiceID=yixuejiaoyujiaohu&ST=" + ticket);
            CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpget);
            HttpEntity entity = closeableHttpResponse.getEntity();
            String xmlString = EntityUtils.toString(entity);

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
            }
            if (!"ok".equals(status)) {
                throw new RuntimeException("获取用户帐号失败");
            }
            username = message;
            Map<String, Object> user = userService.getUserInfo(username, usertype);
            if (user == null) {
                throw new RuntimeException("获取用户帐号不存在");
            }

            HttpSession session = request.getSession();
            if ("1".equals(usertype)) {
                session.setAttribute(Const.SESSION_USER_CONST_WRITER, user);
                session.setAttribute(Const.SESSION_USER_CONST_TYPE, "1");
            } else if ("2".equals(usertype)) {
                session.setAttribute(Const.SESSION_USER_CONST_ORGUSER, user);
                session.setAttribute(Const.SESSION_USER_CONST_TYPE, "2");
            }

            if (StringUtils.isEmpty(request.getParameter("Referer"))) {
                response.sendRedirect(request.getContextPath() + "/");
            } else {
                response.sendRedirect(request.getParameter("Referer"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @RequestMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if ("1".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
            session.removeAttribute(Const.SESSION_USER_CONST_WRITER);
            String headReferer = request.getHeader("Referer");
            String refer = StringUtils.isEmpty(headReferer) ? request.getHeader("referer") : headReferer;
            response.sendRedirect(refer);
        } else if ("2".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
            session.removeAttribute(Const.SESSION_USER_CONST_ORGUSER);
            String headReferer = request.getHeader("Referer");
            String refer = StringUtils.isEmpty(headReferer) ? request.getHeader("referer") : headReferer;
            response.sendRedirect(refer);
        }

    }

}
