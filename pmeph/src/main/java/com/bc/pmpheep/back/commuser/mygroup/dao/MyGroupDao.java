package com.bc.pmpheep.back.commuser.mygroup.dao;


import java.util.List;
import java.util.Map;

/**
 * Created by cyx  on 2017/11/21
 */
public interface MyGroupDao {

    /**
     * 获取我的小组列表信息
     * @param userId
     * @return
     */
    public List<Map<String,Object>> getMyGroups(String userId);

    /**
     * 获取到小组前三条信息
     * @return
     */
    public List<Map<String,Object>> getThreeInfo();
}
