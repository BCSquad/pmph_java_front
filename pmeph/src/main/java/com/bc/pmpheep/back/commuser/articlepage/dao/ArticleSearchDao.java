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
	void changeLikes(Map<String, Object> map);
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
	 * 删除数据
	 * @param map
	 */
	void del(Map<String, Object> map);
	/**
	 * 模糊查询
	 * @param title
	 * @return
	 */
	List<Map<String, Object>> queryall(@Param("title") String title);

}
