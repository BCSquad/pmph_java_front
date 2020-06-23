package com.bc.pmpheep.back.sessioncontext;

import com.bc.pmpheep.back.util.Const;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

public class SessionListener implements HttpSessionListener {
    public static Map<Long, String> LOGIN_USER_MAP = new HashMap<Long, String>();//登入用户
    public static Map<Long, String> LOGOUT_USER_MAP = new HashMap<Long, String>();//逼下线用户
    //session创建
    @Override
    public void sessionCreated(HttpSessionEvent event) {
    }
    //session销毁
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        Map<String, Object> user =  (Map<String, Object>) session.getAttribute(Const.SESSION_USER_CONST_WRITER);
        if(user != null){
            LOGIN_USER_MAP.remove(user.get("id"));
        }
    }

}
