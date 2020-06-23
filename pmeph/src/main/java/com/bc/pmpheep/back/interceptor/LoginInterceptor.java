package com.bc.pmpheep.back.interceptor;

import com.alibaba.fastjson.JSON;
import com.bc.pmpheep.back.sessioncontext.SessionListener;
import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.controller.bean.ResponseBean;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * Created by lihuan on 2017/12/14.
 */

public class LoginInterceptor implements HandlerInterceptor, ApplicationContextAware {

    private String remoteUrl;

    private String remoteVideoUrl;

    private String _timestamp = "";

    private String aipUrl;
    private String cartUrl;

    public void setCartUrl(String cartUrl) {
        this.cartUrl = cartUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }


    public void setAipUrl(String aipUrl) {
        this.aipUrl = aipUrl;
    }

    public void setRemoteVideoUrl(String remoteVideoUrl) {
        this.remoteVideoUrl = remoteVideoUrl;
    }


    public static class PathWithUsertypeMap {
        private String path;
        private String userType;

        public PathWithUsertypeMap(String path, String userType) {
            this.path = path;
            this.userType = userType;
        }

        public String getPath() {
            return path;
        }

        public String getUserType() {
            return userType;
        }

        @Override
        public String toString() {
            return "PathWithUsertypeMap{" +
                    "path='" + path + '\'' +
                    ", userType='" + userType + '\'' +
                    '}';
        }
    }

    public static class PathWithUsertypeMapPropertyEditor extends PropertyEditorSupport {
        public void setAsText(String text) {
            Assert.notNull(text);
            String[] paths = text.split("->");
            Assert.state(paths.length == 2, "属性必须使用 -> 分割，例如：/login -> 1");
            this.setValue(new PathWithUsertypeMap(paths[0].trim(), paths[1].trim()));
        }
    }

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


    private String redirectUrl;

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    private String serviceID;

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    private Set<PathWithUsertypeMap> pathWithUsertypeMaps;

    public void setPathWithUsertypeMaps(Set<PathWithUsertypeMap> pathWithUsertypeMaps) {
        this.pathWithUsertypeMaps = pathWithUsertypeMaps;
    }

    public Set<PathWithUsertypeMap> getPathWithUsertypeMaps() {
        return pathWithUsertypeMaps;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {


        HttpSession session1 = httpServletRequest.getSession();


            PathMatchingResourcePatternResolver resolver = getPathMatchingResourcePatternResolver();
        boolean isAjax = "XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"));
        Map<String, Object>  userInfo1 = (Map<String, Object>) session1.getAttribute(Const.SESSION_USER_CONST_WRITER);
        if (userInfo1 != null) {
            String sessionId = httpServletRequest.getSession().getId();
            if (SessionListener.LOGOUT_USER_MAP.containsValue(sessionId)) {//判断当前用户sessionID是否存在逼下线列表里
                SessionListener.LOGOUT_USER_MAP.remove(new Long(userInfo1.get("id").toString()));
                String home1 = URLEncoder.encode(httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + httpServletRequest.getContextPath() + "/homepage/tohomepage.action", "UTF-8");
                String home2 = URLEncoder.encode(httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + httpServletRequest.getContextPath() + "/schedule/scheduleList.action", "UTF-8");

                if ("1".equals(session1.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
                    session1.removeAttribute(Const.SESSION_USER_CONST_WRITER);
                    String headReferer = httpServletRequest.getHeader("Referer");
                    session1.removeAttribute(Const.SESSION_USER_CONST_TYPE);


                    httpServletResponse.sendRedirect("http://sso.ipmph.com/ssoLogout" + "?ServiceID=" + serviceID + "&Referer=" + home1);
                } else if ("2".equals(session1.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
                    session1.removeAttribute(Const.SESSION_USER_CONST_ORGUSER);
                    session1.removeAttribute(Const.SESSION_USER_CONST_ORGUSER);
                    String headReferer = httpServletRequest.getHeader("Referer");

                    httpServletResponse.sendRedirect("http://sso.ipmph.com/ssoLogout" + "?ServiceID=" + serviceID + "&Referer=" + home1);
                } else {
                    httpServletResponse.sendRedirect("http://sso.ipmph.com/ssoLogout" + "?ServiceID=" + serviceID + "&Referer=" + home1);
                }
            }
        }
                for (PathWithUsertypeMap pathMap : pathWithUsertypeMaps) {
                //需要拦截的URL
                if (resolver.getPathMatcher().match(pathMap.getPath(), httpServletRequest.getServletPath())) {
                    HttpSession session = httpServletRequest.getSession();
                    Map<String, Object> userInfo = null;
                    if (pathMap.getUserType().split(",").length >= 1) {
                        for (String s : pathMap.getUserType().split(",")) {
                            if (s.trim().equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE)) && s.trim().equals("1")) {
                                if (session.getAttribute(Const.SESSION_USER_CONST_WRITER) != null) {
                                    userInfo = (Map<String, Object>) session.getAttribute(Const.SESSION_USER_CONST_WRITER);
                                }
                            }
                            if (s.trim().equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE)) && s.trim().equals("2")) {
                                if (session.getAttribute(Const.SESSION_USER_CONST_ORGUSER) != null) {
                                    userInfo = (Map<String, Object>) session.getAttribute(Const.SESSION_USER_CONST_ORGUSER);
                                }

                            }
                        }
                    }




                    if (userInfo == null) {
                        String refer;
                        if (isAjax) {
                            String headReferer = httpServletRequest.getHeader("Referer");
                            refer = StringUtils.isEmpty(headReferer) ? httpServletRequest.getHeader("referer") : headReferer;
                            refer = URLEncoder.encode(refer, "UTF-8");


                            ResponseBean<String> responseBean = new ResponseBean<String>();
                            responseBean.setCode(ResponseBean.NO_PERMISSION);
                            responseBean.setMsg("user is not login");
                            responseBean.setData(redirectUrl + "?ServiceID=" + serviceID + "&Referer=" + refer);
                            httpServletResponse.getWriter().write(JSON.toJSONString(responseBean));
                        } else {

                            String sessionId = httpServletRequest.getSession().getId();
                            if(SessionListener.LOGOUT_USER_MAP.containsValue(sessionId)){//判断当前用户sessionID是否存在逼下线列表里
                                SessionListener.LOGOUT_USER_MAP.remove(new Long(userInfo.get("id").toString()));
                                String home1 = URLEncoder.encode(httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + httpServletRequest.getContextPath() + "/homepage/tohomepage.action", "UTF-8");
                                String home2 = URLEncoder.encode(httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + httpServletRequest.getContextPath() + "/schedule/scheduleList.action", "UTF-8");

                                if ("1".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
                                    session.removeAttribute(Const.SESSION_USER_CONST_WRITER);
                                    String headReferer = httpServletRequest.getHeader("Referer");
                                    session.removeAttribute(Const.SESSION_USER_CONST_TYPE);


                                    httpServletResponse.sendRedirect("http://sso.ipmph.com/ssoLogout" + "?ServiceID=" + serviceID + "&Referer=" + home1);
                                } else if ("2".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
                                    session.removeAttribute(Const.SESSION_USER_CONST_ORGUSER);
                                    session.removeAttribute(Const.SESSION_USER_CONST_ORGUSER);
                                    String headReferer = httpServletRequest.getHeader("Referer");

                                    httpServletResponse.sendRedirect("http://sso.ipmph.com/ssoLogout" + "?ServiceID=" + serviceID + "&Referer=" + home1);
                                } else {
                                    httpServletResponse.sendRedirect("http://sso.ipmph.com/ssoLogout" + "?ServiceID=" + serviceID + "&Referer=" + home1);
                                }


                            }

                            StringBuilder builder = new StringBuilder("");
                            Map<String, String[]> map = (Map<String, String[]>) httpServletRequest.getParameterMap();
                            for (String name : map.keySet()) {
                                String[] values = map.get(name);
                                builder.append(name + "=" + (values.length > 0 ? values[0] : "") + "&");
                            }
                            refer = URLEncoder.encode(httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() +
                                    /*":" + httpServletRequest.getServerPort() +*/ httpServletRequest.getContextPath() +
                                    httpServletRequest.getServletPath() + "?" + builder.toString(), "UTF-8");

                            httpServletResponse.sendRedirect(redirectUrl + "?ServiceID=" + serviceID + "&Referer=" + refer);
                        }
                        return false;
                    } else {
                        return true;
                    }


                }
            }

            return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object
            o, ModelAndView modelAndView) throws Exception {
        httpServletRequest.getServletContext().setAttribute("_remoteUrl", remoteUrl);
        httpServletRequest.getServletContext().setAttribute("_timestamp", _timestamp);
        httpServletRequest.getServletContext().setAttribute("_remoteVideoUrl", remoteVideoUrl);
        httpServletRequest.getServletContext().setAttribute("_cartUrl", cartUrl);
        httpServletRequest.getServletContext().setAttribute("_aipUrl", aipUrl);

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, Object o, Exception e) throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this._timestamp = System.currentTimeMillis() + "";
    }


}
