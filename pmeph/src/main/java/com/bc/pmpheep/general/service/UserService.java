package com.bc.pmpheep.general.service;

import com.bc.pmpheep.general.dao.UserDao;
import org.apache.commons.collections.MapUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by lihuan on 2017/12/14.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public Map<String, Object> getUserInfo(String username, String usertype) {
        return userDao.getUserInfo(username, usertype);
    }

    @Transactional
    public int insertUserLoginLog(Map<String, Object> info) {
        return userDao.insertUserLoginLog(info);
    }

    @Transactional
    public void insertUserScores(Map<String, Object> info) {

        List<Map<String, Object>> list = userDao.getUserNeedAddScores(info);

        int pp = MapUtils.getIntValue(list.get(0), "pp");

        int score = 0;

        if (pp > 0) {
            score = 0;
        } else {
            if (list.size() >= 2) {
                for (int i = 1; i < list.size(); i++) {
                    if (MapUtils.getIntValue(list.get(i), "pp") == i) {
                        score += 1;
                    } else {
                        break;
                    }
                }
            }
        }

        if (score != 0) {
            info.put("scores", score);
            userDao.insertUserScore(info);
        }
    }

}
