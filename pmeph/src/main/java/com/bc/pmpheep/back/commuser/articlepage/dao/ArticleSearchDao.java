package com.bc.pmpheep.back.commuser.articlepage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ArticleSearchDao {
	
	/**
	 * 初始化文章搜索
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> searchArticle(Map<String, Object> map);
	/**
	 * 查询一共有多少条文章
	 * @return
	 */
	List<Map<String, Object>> queryList();
	/**
	 * 根据ID查询
	 * @param id
	 * @return Map<String, Object>
	 */
	Map<String, Object> queryById(@Param("id") String id);
	/**
	 * 改变点赞数
	 * @param map
	 */
	int changeLikes(@Param("likes") int likes,@Param("id") String id);
	/**
	 * 查询当前文章是否被点过赞
	 * @param map
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryPraise(Map<String, Object> map);
	/**
	 * 往点赞表里面新增数据
	 * @param map
	 */
	void insertPraise(Map<String, Object> map);
	/**
	 * 删除点赞表里面的数据
	 * @param map
	 */
	void del(@Param("id") String id);
	/**
	 * 模糊查询
	 * @param title
	 * @return
	 */
	List<Map<String, Object>> queryall(@Param("title") String title);
	/**
	 * 查询登陆人是否对指定文章点过赞
	 * @param id 文章ID
	 * @param writer_id 登陆人ID
	 * @return
	 */
	List<Map<String, Object>> querydExit(@Param("id") String id,@Param("writer_id") String writer_id);
	/**
	 * 根据用户ID和文章ID往点赞表里面添加数据
	 * @param content_id 文章ID
	 * @param writer_id 用户ID
	 * @return 1，1>0说明数据添加成功
	 */
	int insertPraise(@Param("content_id") String content_id,@Param("writer_id") String writer_id);
}
