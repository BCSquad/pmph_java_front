package com.bc.pmpheep.general.filter;

import com.bc.pmpheep.general.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lihuan on 2017/11/23.
 */
public class UserInfoFilter implements Filter {

    private static String CONST_USER = "_CONST_USER_";

    private String userid;

    private String usertype;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.userid = filterConfig.getInitParameter("userid");
        this.usertype = filterConfig.getInitParameter("usertype");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletRequest.getServletContext());
        UserDao userDao = applicationContext.getBean(UserDao.class);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        session.setAttribute(CONST_USER, userDao.getUserInfo(Long.valueOf(userid), usertype));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
