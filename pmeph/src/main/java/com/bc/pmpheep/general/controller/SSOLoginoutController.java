package com.bc.pmpheep.general.controller;

import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.general.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
            response.sendRedirect(request.getContextPath() + "/pages/comm/login.jsp?refer=" + request.getParameter("refer")+"&msg=username is not exist");
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute(Const.SESSION_USER_CONST, user);
        response.sendRedirect(request.getContextPath() + request.getParameter("refer"));
    }

}
