package com.bc.pmpheep.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lihuan on 2017/7/8.
 */
public class HttpRequestUtil {

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equalsIgnoreCase(requestType);
        return isAjax;
    }

    public static boolean acceptJsonOrText(HttpServletRequest request) {
        if (request.getHeader("Accept") == null) {
            return false;
        }
        String accept = request.getHeader("Accept").toLowerCase();
        boolean isJsonOrText = false;

        if (accept.contains("json") || accept.contains("text")) {
            isJsonOrText = true;
        }
        return isJsonOrText;
    }

    /***
     * 获取客户端IP地址;这里通过了Nginx获取;X-Real-IP,
     * @param request
     * @return
     */
    public static String getClientIP(HttpServletRequest request) {
        String fromSource = "X-Real-IP";
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            fromSource = "X-Forwarded-For";
            ip = request.getHeader(fromSource);
            if (!StringUtils.isEmpty(ip)) {
                String[] tmps = ip.split(",");
                if (tmps.length > 0) {
                    ip = tmps[0];
                }
            }

        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            fromSource = "Proxy-Client-IP";
            ip = request.getHeader(fromSource);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            fromSource = "WL-Proxy-Client-IP";
            ip = request.getHeader(fromSource);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            fromSource = "request.getRemoteAddr";
        }
        return ip;
    }


}
