package com.bc.pmpheep.back.commuser.homepage.service;

import java.util.List;
import java.util.Map;


public interface HomeService {

	/**
	 * @Description: 查询前三条公告 
	 * @return List<HomepageDocument>
	 */
	List<Map<String, Object>> queryDocument(String id);
	/**
	 * @Description: 查询前五条公告信息快报
	 * @return List<HomepageDocument>
	 */
	List<Map<String, Object>> queryNotice();
	/**
	 * @Description: 查询最新四条医学随笔
	 * @return List<HomepageDocument>
	 */
	List<Map<String, Object>> queryArticle(int endrow);
	/**
	 * @Description: 查询点击人数最多的随笔的作者
	 * @return List<HomepageDocument>
	 */
	List<Map<String, Object>> queryAuthor();
	/**
	 * @Description: 查询点击人数最多的书评
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryComment();
	/**
	 * @Description: 查询书籍
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryBook(Map<String, Object> map);
	/**
	 * @Description: 查询销量最高的书籍
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> querySale(int type);
	/**
	 * @Description: 查询书籍分类
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryBookType(int parent_id);
	/**
	 * @Description: 查询热门标签
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryLabel(long typeid);
}
