package com.bc.pmpheep.back.commuser.cms.service;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;


/**
 * ArticleDetail 接口
 * @author sunzhuoqun
 *
 */
public interface ArticleDetailService {
	
	/**
	 * 查询医学随笔详情页的信息(文章)
	 * @param id
	 * @return map
	 */
	Map<String, Object> queryTitle(Map<String, Object> map);
	
	
	/**
	 * 插入文章评论
	 * @param map
	 */
	public Map<String, Object> insertWriteArticle(Map<String, Object> map,String content);
	
	/**
	 * @Description: 查询作者
	 * @return List<Map<String, Object>>
	 */
	Map<String, Object> queryAuthor(Map<String, Object> map);
	
	/**
	 * 查询最近文章条数
	 * @return int
	 */
	int queryArticleCount(String author_id);
	
	/**
	 * @Description: 查询最近3条医学随笔
	 * @return List<Map<String, Object>> 
	 */
	List<Map<String, Object>> queryArticle(String author_id);
	
	/**
	 * 查询相关文章
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryRecommendByE(int num);
	
}
