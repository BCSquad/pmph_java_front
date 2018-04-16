package com.bc.pmpheep.general.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by lihuan on 2017/11/23.
 */
public interface UserDao {

    public List<Map<String, Object>> getUserType(@Param("username")String username);

    public Map<String, Object> getUserInfo(@Param("username") String username, @Param("usertype") String usertype);

    //添加新的用户
    public int addNewUser(@Param("username") String username, @Param("usertype") String usertype);

    public int insertUserLoginLog(Map<String, Object> info);



    public List<Map<String, Object>> getPointRules();

    public List<Map<String, Object>> getUserNeedAddScores(Map<String, Object> info);

    public int insertUserScore(Map<String, Object> info);

    /**
     * 获取到好友的信息
     * @param id
     * @return
     */
    Map<String,Object> getFriendInfo(@Param("FriendId") String id,@Param("userId") String userId);
}
