package com.bc.pmpheep.back.commuser.homepage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;



/**
 * 
 * @author xieming
 * @Description: 首页的DAO层
 */
public interface HomeDao {
	
	/**
	 * @Description: 查询前三条公文 
	 * @return List<HomepageDocument>
	 */
	List<Map<String, Object>> queryDocument();
	/**
	 * @Description: 查询前五条公告 
	 * @return List<HomepageDocument>
	 */
	List<Map<String, Object>> queryNotice();
	/**
	 * @Description: 查询最新四条医学随笔
	 * @return List<HomepageDocument>
	 */
	List<Map<String, Object>> queryArticle();
	/**
	 * @Description: 查询四个点击人数最多的随笔的作者
	 * @return List<HomepageDocument>
	 */
	List<Map<String, Object>> queryComment();
	/**
	 * @Description: 查询某分类下的书
	 * @return List<HomepageDocument>
	 */
	List<Map<String, Object>> queryBook(Map<String, Object> map);
	/**
	 * @Description: 查询销量最高的6本书
	 * @return List<HomepageDocument>
	 */
	List<Map<String, Object>> querySale(@Param("type")int type);
	/**
	 * @Description: 查询第一级下的子类
	 * @return List<HomepageDocument>
	 */
	List<Map<String, Object>> queryBookType(@Param("parent_id")int parent_id);
	/**
	 * @Description: 查询热门标签
	 * @return List<HomepageDocument>
	 */
	List<Map<String, Object>> queryLabel(String typepath);
}
