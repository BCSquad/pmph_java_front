package com.bc.pmpheep.back.commuser.readpage.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

public interface ReadDetailService {

	/**
	 * 查询读书详情页的信息
	 * @param id
	 * @return map
	 */
	Map<String, Object> queryReadBook(String id);
	/**
	 * 查询书籍相关评论
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> queryComment(String id,int start);
	/**
	 * 根据书籍ID查询配套图书
	 * @param id
	 * @return
	 */
	Map<String, Object> querySupport (String id);
	/**
	 * 新增图书纠错
	 * @param map
	 * @return
	 */
	Map<String, Object> correction(Map<String, Object> map);
	/**
	 * 查询人卫推荐书籍
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryRecommendByE(int num);
	/**
	 * 新增图书评论
	 * @param map
	 */
	Map<String, Object> insertComment(Map<String, Object> map);
	/**
	 * 根据作者查询该作者写的书
	 * @param author
	 * @return
	 */
	List<Map<String, Object>> queryAuthorType(String author);
	/**
	 * 根据书籍类型查询书籍
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryBookByType(Map<String, Object> map);
	/**
	 * 根据书籍类型查询相同类型的书
	 * @param x
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> fresh(Map<String, Object> map);
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
	/**添加收藏
	 * @param bookId   书籍id
	 * @param writerId  作家用户id
	 * @return
	 */
	Map<String, Object> inserMark(long bookId,long writerId);
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
	int queryMark(String bookId,String favoriteId,String writerId);
	/**
	 * 删除对应的书评
	 * @param 书评Id
	 * @param favoriteId
	 * @param writerId
	 * @return
	 */
	String delbookwriter(Map<String, Object> map);
	/**
	 * 写长评
	 * @param map
	 * @return OK,OK代表数据添加成功
	 */
	String insertlong(Map<String, Object> map);
	/**
	 * 查询是否写过长评
	 * @return
	 */
	List<Map<String, Object>> queryLoginLong(String writer_id,String book_id);
	/**
	 * 查询长评
	 * @param book_id
	 * @return
	 */
	List<Map<String, Object>> queryLong(String book_id,int start);
	/**
	 * 根据ID编辑点击数
	 * @param book_id 书籍ID
	 * @param clicks 点击数
	 */
	void changeClicks(String book_id,int clicks);
	/**
	 * 查询视频
	 * @param book_id 书籍ID
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryVideo(String book_id);
	
	/**
	 * 查询我的长评
	 *
	 */
	Map<String, Object> queryMyLong(String book_id,String id);
	
	/**
	 * 我的长评论修改
	 * 
	 * @param map
	 */
	Map<String, Object> updateCommentLong(Map<String, Object> map);
	
	/**
	 * 读者反馈
	 * @param map
	 * @return
	 */
	String bookfeedback(Map<String, Object> map);

	/**查询书籍微视频
	 * @param map  startnum:分页的开始的序号      size:分页的数据容量  
	 * @return
	 */
	List<Map<String,Object>> queryMoreBookVidos(Map<String,Object> map);
	/**查询微视频的总数
	 * @param map   materialId:教材id
	 * @return int
	 */
	int queryMoreBookVidosCount(Map<String,Object> map);


}
