package com.bc.pmpheep.general.controller;

import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.general.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by lihuan on 2017/12/14.
 */
@Controller
public class SSOLoginoutController {

    @Autowired
    UserService userService;

    @RequestMapping("login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        if (StringUtils.isEmpty(request.getParameter("refer"))) {
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            response.sendRedirect(request.getParameter("refer"));
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
