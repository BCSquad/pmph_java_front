package com.bc.pmpheep.back.commuser.articlepage.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ArticleSearchService {

	/**
	 * 初始化文章搜索
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> searchArticle(Map<String, Object> map);
	/**
	 * 查询一共有多少条数据
	 * @return
	 */
	List<Map<String, Object>> queryList();
	/**
	 * 改变点赞数
	 * @param map
	 */
	Map<String, Object> changeLikes(Map<String, Object> map);
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	Map<String, Object> queryById(String id);
	/**
	 * 查询当前文章是否被点过赞
	 * @param map
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryPraise(Map<String, Object> map);
	/**
	 * 模糊查询
	 * @param title
	 * @return
	 */
	List<Map<String, Object>> queryall(String title);
	/**
	 * 根据mid获取到图片地址
	 * @param html
	 * @return List
	 */
	public List<String> getImgSrc(String html);
}
