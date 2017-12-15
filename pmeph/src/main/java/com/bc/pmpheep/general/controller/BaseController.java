package com.bc.pmpheep.general.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bc.pmpheep.back.util.Const;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Created by lihuan on 2017/11/23.
 */
public class BaseController {


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

    protected String getUserType() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        return session.getAttribute(Const.SESSION_USER_CONST_TYPE) == null ? "-1" : session.getAttribute(Const.SESSION_USER_CONST_TYPE).toString();
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView otherException(Exception ex, HttpServletRequest request,
                                       HttpServletResponse response) {
        ex.printStackTrace();
        response.setStatus(500);
        if (StringUtils.isEmpty(request.getHeader("x-requested-with"))) {
            return new ModelAndView("comm/error");
        } else {

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("status", "500");
            model.put("errMsg", ex.getCause());

            return new ModelAndView(new MappingJackson2JsonView(), model);
        }

    }

}
