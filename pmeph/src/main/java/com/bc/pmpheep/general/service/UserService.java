package com.bc.pmpheep.general.service;

import com.bc.pmpheep.general.dao.UserDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
