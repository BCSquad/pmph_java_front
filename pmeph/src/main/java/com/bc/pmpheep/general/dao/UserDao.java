package com.bc.pmpheep.general.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by lihuan on 2017/11/23.
 */
public interface UserDao {

    public Map<String, Object> getUserInfo(@Param("username") String username, @Param("usertype") String usertype);

    //添加新的用户
    public int addNewUser(@Param("username") String username, @Param("usertype") String usertype);
}
