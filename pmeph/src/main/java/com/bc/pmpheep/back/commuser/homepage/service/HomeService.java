package com.bc.pmpheep.back.commuser.homepage.service;

import java.io.UnsupportedEncodingException;
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
     * @param contextpath 
     * @return List<HomepageDocument>
     * @Description: 查询前五条公告信息快报
     */
    List<Map<String, Object>> queryNotice(String contextpath);

    /**
     * @return List<HomepageDocument>
     * @Description: 查询最新四条医学随笔
     */
    List<Map<String, Object>> queryArticle(int endrow) throws Exception;

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
    List<Map<String, Object>> queryComment() throws UnsupportedEncodingException;

    /**
     * @return List<Map<String, Object>>
     * @Description: 查询书籍
     */
    List<Map<String, Object>> queryBook(Map<String, Object> map);

    /**
     * @return List<Map<String, Object>>
     * @Description: 查询销量最高的书籍
     */
    List<Map<String, Object>> querySale(int type) throws UnsupportedEncodingException;

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
     * @param logUserId 
     */
    int countSurvey(String logUserId);

    /**
     * 未读消息查询
     * @param id
     * @return
     */
    List<Map<String, Object>> queryNotReadMessages(String id);

    /**
     * 超出部分省略号显示
     * @param content
     * @return
     */
    String omit(String content,int length) throws UnsupportedEncodingException;

    /**查询更多热门书评列表
     * @param
     * @param startnum  分也开始序号
     * @param size 每页的数据数量
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> queryHotCommentList(int startnum ,int size,String contextpath) throws UnsupportedEncodingException;

    /**查询更多热门书评列表数量
     *
     */
    int queryHotCommentListCount();
    /**
     * 根据书籍类型查询总条数
     * @return
     */
    int querySize(String type);
    /**
     * 根据产品类型查询详情
     * @return
     */
    List<Map<String,Object>> quertProductByType(String product_type);
    /**
     * 查询首页师资平台
     * @return
     */
    List<Map<String,Object>> Queryszpt();

}
