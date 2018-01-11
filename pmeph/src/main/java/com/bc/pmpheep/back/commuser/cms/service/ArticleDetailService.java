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
	 * 查询读书详情页的信息
	 * @param id
	 * @return map
	 */
	Map<String, Object> queryRead(String id);
	
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
	
	/**
	 * 查询书籍相关评论
	 * @param id
	 * @return
	 */
	PageResult<Map<String, Object>> queryComment(PageParameter<Map<String, Object>> pageParameter);
	
	/**
	 * @Description: 查询猜你喜欢
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryArticleSix();
	
	/**
	 * 根据书籍ID增加点赞数
	 * @param id
	 */
	Map<String, Object> addlikes(Map<String, Object> map);
	/**
	 * 根据book_id和writer_id查询是否点赞
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryLikes(Map<String, Object> map);
	
	/**
	 * 查询是否有收藏夹
	 * @param id
	 * @return
	 */
	Map<String, Object> queryDedaultFavorite(String id);
	
	/**
	 * 查询默认收藏夹是否收藏本书
	 * @param bookId
	 * @param favoriteId
	 * @param writerId
	 * @return
	 */
	int queryMark(String wid,String favorite_id,String writer_id);
	
	/**添加收藏
	 * @param bookId   书籍id
	 * @param writerId  作家用户id
	 * @return
	 */
	Map<String, Object> inserMark(long wid,long writer_id);
	
}
