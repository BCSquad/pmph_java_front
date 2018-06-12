package com.bc.pmpheep.back.interceptor;


import com.alibaba.fastjson.JSON;
import com.bc.pmpheep.back.authadmin.accountset.service.AdminInfoService;
import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.controller.bean.ResponseBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;


public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier("com.bc.pmpheep.back.authadmin.accountset.service.AdminInfoServiceImpl")
    private AdminInfoService adminInfoService;

    protected Map<String, Object> getUserInfo() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if ("1".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
            return (Map<String, Object>) session.getAttribute(Const.SESSION_USER_CONST_WRITER);
        } else if ("2".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
            return (Map<String, Object>) session.getAttribute(Const.SESSION_USER_CONST_ORGUSER);
        }
        return null;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Map<String, Object> user=getUserInfo();
        if(user!=null){
            Long userId = new Long(String.valueOf(user.get("id")));
            Map<String, Object> admininfo = adminInfoService.getOrgUserById(userId);
            int progress=Integer.parseInt(admininfo.get("progress").toString());
            if(1!=progress){
                //String headReferer = httpServletRequest.getHeader("Referer");
                //refer = StringUtils.isEmpty(headReferer) ? httpServletRequest.getHeader("referer") : headReferer;
                ResponseBean<String> responseBean = new ResponseBean<String>();
                responseBean.setCode(ResponseBean.NO_AUTH);
                responseBean.setMsg(admininfo.get("progress").toString());
                responseBean.setProxy(admininfo.get("proxy_upload").toString());
                if(progress == 2) {
                    responseBean.setBackReason(admininfo.get("backReason").toString());
                }
                responseBean.setUserid(user.get("id").toString());
                //responseBean.setData(httpServletRequest.getContextPath() + redirectUrl + "?refer=" + refer);
                httpServletResponse.getWriter().write(JSON.toJSONString(responseBean));
                return false;
            }else {
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
