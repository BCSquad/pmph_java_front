package com.bc.pmpheep.back.commuser.mygroup.service;

import java.util.List;
import java.util.Map;

/**
 * Created by cyx  on 2017/11/21
 */
public interface MyGroupService {

    /**
     * 获取我的小组列表信息
     * @param userId
     * @return
     */
    public List<Map<String,Object>> getMyGroups(String userId);



}
