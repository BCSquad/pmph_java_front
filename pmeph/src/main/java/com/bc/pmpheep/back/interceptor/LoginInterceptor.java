package com.bc.pmpheep.back.interceptor;

import com.bc.pmpheep.back.util.Const;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * Created by lihuan on 2017/12/14.
 */

public class LoginInterceptor implements HandlerInterceptor {

/*    private static final ThreadLocal<PathMatchingResourcePatternResolver> resolvers;

    static {
        resolvers = new ThreadLocal<PathMatchingResourcePatternResolver>();
    }

    private PathMatchingResourcePatternResolver getPathMatchingResourcePatternResolver() {
        if (resolvers.get() == null) {
            resolvers.set(new PathMatchingResourcePatternResolver());
        }
        return resolvers.get();
    }

    private Set<String> urls;

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }*/


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
      /*  PathMatchingResourcePatternResolver resolver = getPathMatchingResourcePatternResolver();
        for (String url : urls) {
            //需要拦截的URL
            if (resolver.getPathMatcher().match(url, httpServletRequest.getServletPath())) {

                HttpSession session = httpServletRequest.getSession();
                Object userInfo = session.getAttribute(Const.SESSION_USER_CONST);
                if (userInfo == null) {
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "pages/comm/login.jsp?refer=" + httpServletRequest.getContextPath() + httpServletRequest.getServletPath());
                    return false;
                } else {
                    return true;
                }
            }
        }*/

        HttpSession session = httpServletRequest.getSession();
        Object userInfo = session.getAttribute(Const.SESSION_USER_CONST);
        if (userInfo == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/pages/comm/login.jsp?refer=" + httpServletRequest.getContextPath() + httpServletRequest.getServletPath());
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
