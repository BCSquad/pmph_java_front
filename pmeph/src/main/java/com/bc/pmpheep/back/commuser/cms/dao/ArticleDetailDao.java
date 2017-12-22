package com.bc.pmpheep.back.commuser.cms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.plugin.PageParameter;


public interface  ArticleDetailDao{
	
	/**
	 * 根据ID查询医学详情页信息
	 * @param id
	 * @return map
	 */
	Map<String, Object> queryTitle( Map<String, Object> map);
	/**
	 * @Description: 插入写评论信息
	 * @return void
	 */
	void insertWriteArticle(Map map);
	
	/**
	 * @Description: 查询作者
	 * @return List<Map<String, Object>
	 */
	Map<String, Object> queryAuthor(Map<String, Object> map);
	
	/**
	 * 查询最近文章条数
	 * @return int
	 */
	int queryArticleCount(String author_id);
	
	/**
	 * @Description: 查询最近三条医学随笔
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryArticle(String author_id);
	
	/**
	 * 查询相关文章
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryRecommendByE(@Param("x") int num);
	
	
	
}
