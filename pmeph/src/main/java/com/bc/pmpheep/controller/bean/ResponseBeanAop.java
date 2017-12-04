/*
 * Copyright 2017 BangChen Information Technology Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.bc.pmpheep.controller.bean;

import com.alibaba.fastjson.JSON;
import com.bc.pmpheep.back.util.JsonUtil;
import com.bc.pmpheep.utils.HttpRequestUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bc.pmpheep.service.exception.CheckedServiceException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常AOP拦截器
 *
 * @author L.X <gugia@qq.com>
 */
public class ResponseBeanAop {

    Logger logger = LoggerFactory.getLogger(ResponseBeanAop.class);

    public Object controllerMethodHandler(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();


        boolean isAjax = HttpRequestUtil.isAjaxRequest(request);

        //判断是否请求的为json或者text

        boolean isJsonOrText = HttpRequestUtil.acceptJsonOrText(request);

        Object result = null;

        ResponseBean<Object> responseBean;
        try {
            result = pjp.proceed();
            responseBean = new ResponseBean();
            responseBean.setData(result);
            responseBean.setCode(ResponseBean.SUCCESS);
            logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
            responseBean.setMsg("use time: " + (System.currentTimeMillis() - startTime));
        } catch (Throwable ex) {
            responseBean = exceptionHandler(pjp, ex);

            if (!isAjax || !isJsonOrText) {
                throw ex;
            }
        }

        if (isAjax && !(result instanceof ModelAndView)) {
            Signature s = pjp.getSignature();
            MethodSignature ms = (MethodSignature) s;
            Method method = ms.getMethod();
            if (method.getReturnType().equals(Object.class)) {
                return responseBean;
            } else if (result instanceof ResponseBean) {
                return responseBean;
            } else if (result instanceof Map) {
                Map m = new HashMap();
                m.put("data", responseBean.getData());
                m.put("code", responseBean.getCode());
                m.put("msg", responseBean.getMsg());
                return m;
            } else if (result instanceof String) {
                return JSON.toJSONString(responseBean);
            }


        }

        return result;
    }

    @SuppressWarnings("rawtypes")
    private ResponseBean<Object> exceptionHandler(ProceedingJoinPoint pjp, Throwable ex) {
        ResponseBean<Object> responseBean = new ResponseBean();
        StringBuilder sb = new StringBuilder();
        sb.append(pjp.getSignature().toString());
        sb.append(" 发生错误:{}");
        if (ex instanceof CheckedServiceException) {
            responseBean.setCode(((CheckedServiceException) ex).getResult().getValue());
            // 如果是已检查的异常，不打印异常堆栈
            responseBean.setMsg(((CheckedServiceException) ex).getBusiness() + "===>"
                    + ex.getMessage());
            logger.error(sb.toString(), ex.toString());
        } else if (ex instanceof IndexOutOfBoundsException) {
            responseBean.setMsg("下标越界异常");
            responseBean.setCode(ResponseBean.INDEX_OUT_OF_BOUNDS);
            logger.error(sb.toString(), ex.toString());
        } else if (ex instanceof ClassCastException) {
            responseBean.setMsg("类型转换异常");
            responseBean.setCode(ResponseBean.CLASS_CAST);
            logger.error(sb.toString(), ex.toString());
        } else {
            responseBean.setMsg(ex.toString());
            responseBean.setCode(ResponseBean.UNKNOWN_ERROR);
            // 未知异常应打印堆栈
            logger.error(pjp.getSignature() + " 发生未知错误", ex);
        }

        return responseBean;
    }
}
