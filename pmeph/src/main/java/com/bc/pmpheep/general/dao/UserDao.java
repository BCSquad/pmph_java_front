package com.bc.pmpheep.general.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by lihuan on 2017/11/23.
 */
public interface UserDao {
    public Map<String, Object> getUserInfo(@Param("userid") long userid,@Param("usertype") String usertype);
}
