package com.bc.pmpheep.back.commuser.teacherPlatform.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeacherPlatformDao {
    /**
     * 查询信息快报
     * @param id
     * @return
     */
    Map<String,Object> queryXikb(String id);

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
    int updateclicks(@Param("clicks") int clicks,@Param("id") String id);

    /**
     * 查询相关资源列表
     * @return
     */
    List<Map<String,Object>> QuerySourceList(@Param("startrow") String startrow);
    /**
     * 查询相关资源总数
     * @return
     */
    int sourceCount(@Param("id") String id);
    /**
     * 查询相关视频总数
     * @return
     */
    int VideoCount(@Param("id") String id);
    /**
     * 查询活动信息
     * @return
     */
    List<Map<String,Object>> QueryActivitiById(Map<String,Object> map);

    /**
     * 增加活动阅读次数
     */
    void addtimes(@Param("times") int times,@Param("id") String id);

    /**
     * 查询视频播放次数的和
     * @param id
     * @return
     */
    int queryClicks(@Param("id") String id);
}
