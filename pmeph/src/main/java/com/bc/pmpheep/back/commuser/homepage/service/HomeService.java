package com.bc.pmpheep.back.commuser.homepage.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface HomeService {

    /**
     * @return List<HomepageDocument>
     * @Description: 查询前三条公告
     */
    List<Map<String, Object>> queryDocument(String id);

    /**
     * @return List<HomepageDocument>
     * @Description: 查询前五条公告信息快报
     */
    List<Map<String, Object>> queryNotice();

    /**
     * @return List<HomepageDocument>
     * @Description: 查询最新四条医学随笔
     */
    List<Map<String, Object>> queryArticle(int endrow);

    /**
     * @param logUserId 
     * @return List<HomepageDocument>
     * @Description: 查询点击人数最多的随笔的作者
     */
    List<Map<String, Object>> queryAuthor(String logUserId);

    /**
     * @return List<Map<String, Object>>
     * @Description: 查询点击人数最多的书评
     */
    List<Map<String, Object>> queryComment();

    /**
     * @return List<Map<String, Object>>
     * @Description: 查询书籍
     */
    List<Map<String, Object>> queryBook(Map<String, Object> map);

    /**
     * @return List<Map<String, Object>>
     * @Description: 查询销量最高的书籍
     */
    List<Map<String, Object>> querySale(int type);

    /**
     * @return List<Map<String, Object>>
     * @Description: 查询书籍分类
     */
    List<Map<String, Object>> queryBookType(int parent_id);

    /**
     * @return List<Map<String, Object>>
     * @Description: 查询热门标签
     */
    List<Map<String, Object>> queryLabel(long typeid);

    /**
     * 获取页面广告，用名称匹配
     * @param adName
     * @return
     */
    Map<String, Object> getPageAdInfo(String adName);
    /**
     * 添加好友
     * @param request_id 申请人ID
     * @param target_id 申请对象ID
     * @return code=OK
     */
    String addfriend(String request_id,String target_id);
    /**
     * 查询教材社区公告
     * @param id 登陆人ID
     * @return list
     */
    List<Map<String, Object>> queryMaterial(String id);
    /**
     * 根据分类查询书籍总数
     */
    int countBookByType(String type);

    /**
     * 查询问卷调查总数
     */
    int countSurvey();

}
