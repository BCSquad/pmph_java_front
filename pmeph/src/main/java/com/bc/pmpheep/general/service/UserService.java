package com.bc.pmpheep.general.service;

import com.alibaba.fastjson.JSON;
import com.bc.pmpheep.general.dao.UserDao;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihuan on 2017/12/14.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    //api接入用户名
    private String apiUsername;
    //api接入密码
    private String apiPassword;

    private String url;

    @Value("${sso.url}")
    public void setUrl(String url) {
        this.url = url;
    }

    @Value("${sso.api.username}")
    public void setApiUsername(String apiUsername) {
        this.apiUsername = apiUsername;
    }

    @Value("${sso.api.password}")
    public void setApiPassword(String apiPassword) {
        this.apiPassword = apiPassword;
    }

    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    public Map<String, Object> getUserInfo(String username, String usertype) {
        return userDao.getUserInfo(username, usertype);
    }


    public Map<String, Object> getUserType(String username) {
        return userDao.getUserType(username);
    }

    @Transactional
    public int insertUserLoginLog(Map<String, Object> info) {
        return userDao.insertUserLoginLog(info);
    }

    @Transactional
    public void insertUserScores(Map<String, Object> info) {

        List<Map<String, Object>> list = userDao.getUserNeedAddScores(info);

        List<Map<String, Object>> rulesList = userDao.getPointRules();
        Map<String, Map<String, Object>> rules = new HashMap<String, Map<String, Object>>();
        for (Map<String, Object> rule : rulesList) {
            rules.put(MapUtils.getString(rule, "rule_code"), rule);
        }

        int pp = MapUtils.getIntValue(list.get(0), "pp");

        int score = MapUtils.getIntValue(rules.get("logins"), "point", 0);

        if (pp > 0) {
            score = 0;
        } else {
            if (list.size() >= 2) {
                for (int i = 1; i < list.size(); i++) {
                    if (MapUtils.getIntValue(list.get(i), "pp") == i) {
                        score += MapUtils.getIntValue(rules.get("login"), "point", 0);
                    } else {
                        break;
                    }
                }
            }
        }

        if (score != 0) {
            info.put("scores", score);
            info.put("ruleid", MapUtils.getIntValue(rules.get("login"), "id"));
            userDao.insertUserScore(info);
        }
    }


    public int addNewUser(String username, String usertype) {
        return userDao.addNewUser(username, usertype);
    }

    /**
     * {
     * username: 'admin',
     * password: 'admin',
     * language: 'zh - cn',
     * method: 'ZAS.ModifyUser',
     * params: {
     * "LoginID": "13419658687",
     * "Password": "123456"
     * }
     * }
     *
     * @param newPassword
     * @return
     */
    public int modifyUser(String username, String newPassword) throws IOException {
        HttpPost post = new HttpPost(url + "api/json");
        post.setHeader("Content-Type", "application/json");
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("username", apiUsername);
        json.put("password", apiPassword);
        json.put("language", "zh - cn");
        json.put("method", "ZAS.ModifyUser");
        Map<String, String> params = new HashMap<String, String>();
        params.put("LoginID", username);
        params.put("Password", newPassword);
        json.put("params", params);
        StringEntity entity = new StringEntity(JSON.toJSONString(json), "utf-8");
        post.setEntity(entity);

        HttpResponse httpResponse = httpclient.execute(post);

        String response = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        Map<String, Object> result = JSON.parseObject(response, Map.class);

        if (MapUtils.getBooleanValue(result, "Success", false)) {//修改成功

            return 1;

        } else {//修改失败

            throw new RuntimeException("修改密码失败：" + MapUtils.getString(result, "Message", ""));

        }
    }

}
