package com.bc.pmpheep.back.interceptor;

import com.alibaba.fastjson.JSON;
import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.controller.bean.ResponseBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Set;

/**
 * Created by lihuan on 2017/12/14.
 */

public class LoginInterceptor implements HandlerInterceptor {

    private String redirectUrl;

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpSession session = httpServletRequest.getSession();
        Object userInfo = session.getAttribute(Const.SESSION_USER_CONST);

        boolean isAjax = "XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"));

        if (userInfo == null) {
            String refer;
            if (isAjax) {
                String headReferer = httpServletRequest.getHeader("Referer");
                refer = StringUtils.isEmpty(headReferer) ? httpServletRequest.getHeader("referer") : headReferer;
                ResponseBean<String> responseBean = new ResponseBean<String>();
                responseBean.setCode(ResponseBean.NO_PERMISSION);
                responseBean.setMsg("user is not login");
                responseBean.setData(httpServletRequest.getContextPath() + redirectUrl + "?refer=" + refer);
                httpServletResponse.getWriter().write(JSON.toJSONString(responseBean));
            } else {
                refer = URLEncoder.encode(httpServletRequest.getContextPath() + httpServletRequest.getServletPath(), "UTF-8");
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + redirectUrl + "?refer=" + refer);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object
            o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, Object o, Exception e) throws Exception {

    }
}
