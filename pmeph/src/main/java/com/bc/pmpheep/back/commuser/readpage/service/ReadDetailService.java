package com.bc.pmpheep.back.commuser.readpage.service;

import java.util.List;
import java.util.Map;


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
	String delbookwriter(Map<String, Object> map);
	/**
	 * 删除对应的书评
	 * @param 书评Id
	 * @param favoriteId
	 * @param writerId
	 * @return
	 */
}
