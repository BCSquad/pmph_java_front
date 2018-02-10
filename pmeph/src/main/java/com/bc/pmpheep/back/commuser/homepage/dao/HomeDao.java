package com.bc.pmpheep.back.commuser.homepage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * @author xieming
 * @Description: 首页的DAO层
 */
public interface HomeDao {

    /**
     * @return List<HomepageDocument>
     * @Description: 查询三条最新公告
     */
    List<Map<String, Object>> queryDocument(@Param("id") String id);

    /**
     * @return List<HomepageDocument>
     * @Description: 查询前五条信息快报
     */
    List<Map<String, Object>> queryNotice();

    /**
     * @return List<HomepageDocument>
     * @Description: 查询最新四条医学随笔
     */
    List<Map<String, Object>> queryArticle(@Param("endrow") int endrow);

    /**
     * 查询作者
     * @param logUserId 
     *
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> queryAuthor(@Param("logUserId")String logUserId);

    /**
     * @return List<HomepageDocument>
     * @Description: 查询四个点击人数最多的随笔的作者
     */
    List<Map<String, Object>> queryComment();

    /**
     * @return List<HomepageDocument>
     * @Description: 查询某分类下的书
     */
    List<Map<String, Object>> queryBook(Map<String, Object> map);

    /**
     * @return List<HomepageDocument>
     * @Description: 查询销量最高的6本书
     */
    List<Map<String, Object>> querySale(@Param("type") int type);

    /**
     * @return List<HomepageDocument>
     * @Description: 查询第一级下的子类
     */
    List<Map<String, Object>> queryBookType(@Param("parent_id") int parent_id);

    /**
     * @return List<HomepageDocument>
     * @Description: 查询热门标签
     */
    List<Map<String, Object>> queryLabel(String typepath);

    /**
     * 获取页面广告，用名称匹配
     *
     * @param adName
     * @return
     */
    public Map<String, Object> getPageAdInfo(@Param("adName") String adName);

    /**
     * 获取页面广告，用名称匹配
     *
     * @param adName
     * @return
     */
    public List<Map<String, Object>> getPageAdDetail(@Param("adName") String adName);
    /**
     * 添加好友
     * @param request_id 申请人ID
     * @param target_id 申请对象ID
     * @return int
     */
    int addfriend(@Param("request_id") String request_id,@Param("target_id") String target_id);
    /**
     * 查询教材社区公告
     * @param id 登陆人ID
     * @return list
     */
    List<Map<String, Object>> queryMaterial(@Param("id") String id);
    
    /**
     * 根据分类查询书籍总数
     */
    int countBookByType(String type);
}
