package com.bc.pmpheep.general.controller;

import com.bc.pmpheep.back.interceptor.LoginInterceptor;
import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.back.util.MD5;
import com.bc.pmpheep.general.service.UserService;
import com.bc.pmpheep.utils.HttpRequestUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
public class SSOLoginoutController {

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

        Map<String, Object> params = new HashMap<String, Object>(user);

        params.put("clientip", HttpRequestUtil.getClientIP(request));

        userService.insertUserScores(params);
        userService.insertUserLoginLog(params);




 /*       if ("1".equals(usertype)) {
            response.sendRedirect(request.getContextPath() + "/homepage/tohomepage.action");
        } else if ("2".equals(usertype)) {
            response.sendRedirect(request.getContextPath() + "/schedule/scheduleList.action");
        }*/


        if (StringUtils.isEmpty(request.getParameter("refer"))) {
            if ("1".equals(usertype)) {
                response.sendRedirect(request.getContextPath() + "/");
            } else if ("2".equals(usertype)) {
                response.sendRedirect(request.getContextPath() + "/schedule/scheduleList.action");
            }

        } else {

            Set<LoginInterceptor.PathWithUsertypeMap> pathWithUsertypeMaps = interceptor.getPathWithUsertypeMaps();

            PathMatchingResourcePatternResolver resolver = getPathMatchingResourcePatternResolver();
            for (LoginInterceptor.PathWithUsertypeMap pathMap : pathWithUsertypeMaps) {
                //需要拦截的URL
                if (resolver.getPathMatcher().match(request.getContextPath() + pathMap.getPath(), request.getParameter("refer"))
                        && pathMap.getUserType().equals(usertype)) {//路径匹配，并且用户身份一致可以跳转

                    response.sendRedirect(request.getParameter("refer"));
                    return;

                } else if (resolver.getPathMatcher().match(request.getContextPath() + pathMap.getPath(), request.getParameter("refer"))) {
                    if ("1".equals(usertype)) {
                        response.sendRedirect(request.getContextPath() + "/");
                        return;
                    } else if ("2".equals(usertype)) {
                        response.sendRedirect(request.getContextPath() + "/schedule/scheduleList.action");
                        return;
                    }
                }


            }

            if ("1".equals(usertype)) {
                response.sendRedirect(request.getParameter("refer"));
            } else if ("2".equals(usertype)) {
                response.sendRedirect(request.getContextPath() + "/schedule/scheduleList.action");
            }

        }
    }

    @RequestMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        Set<LoginInterceptor.PathWithUsertypeMap> pathWithUsertypeMaps = interceptor.getPathWithUsertypeMaps();
        PathMatchingResourcePatternResolver resolver = getPathMatchingResourcePatternResolver();

        if ("1".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
            session.removeAttribute(Const.SESSION_USER_CONST_WRITER);
            session.removeAttribute(Const.SESSION_USER_CONST_TYPE);
           /* String headReferer = request.getHeader("Referer");
            String refer = StringUtils.isEmpty(headReferer) ? request.getHeader("referer") : headReferer;

            if (refer != null) {
                refer = refer.substring(refer.indexOf(request.getContextPath()), refer.length());
            }

            for (LoginInterceptor.PathWithUsertypeMap pathMap : pathWithUsertypeMaps) {
                //需要拦截的URL
                if (resolver.getPathMatcher().match(request.getContextPath() + pathMap.getPath(), refer)) {//路径匹配
                    response.sendRedirect(request.getContextPath() + "/homepage/tohomepage.action");
                    return;
                }
            }*/

            response.sendRedirect(request.getContextPath() + "/homepage/tohomepage.action");
        } else if ("2".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
            session.removeAttribute(Const.SESSION_USER_CONST_ORGUSER);
            session.removeAttribute(Const.SESSION_USER_CONST_TYPE);
           /* String headReferer = request.getHeader("Referer");
            String refer = StringUtils.isEmpty(headReferer) ? request.getHeader("referer") : headReferer;
            for (LoginInterceptor.PathWithUsertypeMap pathMap : pathWithUsertypeMaps) {
                //需要拦截的URL
                if (resolver.getPathMatcher().match(request.getContextPath() + pathMap.getPath(), refer)) {//路径匹配
                    response.sendRedirect(request.getContextPath() + "/schedule/scheduleList.action");
                    return;

                }
            }*/
            response.sendRedirect(request.getContextPath() + "/schedule/scheduleList.action");
        } else {
            response.sendRedirect(request.getContextPath() + "/homepage/tohomepage.action");
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


}
