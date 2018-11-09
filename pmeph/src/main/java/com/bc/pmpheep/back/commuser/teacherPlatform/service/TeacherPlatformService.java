package com.bc.pmpheep.back.commuser.teacherPlatform.service;


import org.apache.ibatis.annotations.Param;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface TeacherPlatformService {
    /**
     * 查询信息快报
     * @param id
     * @return
     */
    Map<String,Object> queryXikb (String id) throws UnsupportedEncodingException;
    /**
     * 查询视频列表
     * @param map
     * @return
     */
    List<Map<String,Object>> Queryvideo(Map<String,Object> map);
    /**
     * 查询相关资源
     * @param map
     * @return
     */
    List<Map<String,Object>> Querysource(Map<String,Object> map);
    /**
     * 查询相关资源
     * @param clicks:播放次数 id：数据的主键
     * @return
     */
    int updateclicks(int clicks,String id);
    /**
     * 查询相关资源列表
     * @return
     */
    List<Map<String,Object>> QuerySourceList(String startrow) throws UnsupportedEncodingException ;
    /**
     * 查询相关资源总数
     * @return
     */
    int sourceCount(String id);
    /**
     * 查询相关视频总数
     * @return
     */
    int VideoCount(String id);
    /**
     * 查询活动信息
     * @param map
     * @return
     */
    List<Map<String,Object>> QueryActivitiById(Map<String,Object> map);

    /**
     * 查询视频播放次数的和
     * @param id
     * @return
     */
    int queryClicks(String id);
}
