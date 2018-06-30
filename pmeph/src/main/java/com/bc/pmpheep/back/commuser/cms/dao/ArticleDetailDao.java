package com.bc.pmpheep.back.commuser.cms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.plugin.PageParameter;

public interface ArticleDetailDao {
	/**
	 * 根据书籍ID查询读书详情页信息
	 * 
	 * @param id
	 * @return map
	 */
	Map<String, Object> queryRead(String id);

	/**
	 * 根据ID查询医学详情页信息
	 * 
	 * @param id
	 * @return map
	 */
	Map<String, Object> queryTitle(Map<String, Object> map);

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
	 * 
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
	 * 
	 * @param wid
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryRecommendByE(@Param("x") int num, @Param("wid") String wid);

	/**
	 * 根据书籍ID查询评论
	 * 
	 * @param id
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryComment(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 根据书籍ID查询一共有多少条数据
	 * 
	 * @return int
	 */
	int querySize(String id);

	/**
	 * @Description: 查询最新6条医学随笔
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryArticleSix();

	/**
	 * 根据书籍ID增加点赞数
	 * 
	 * @param id
	 */
	void addlikes(Map<String, Object> map);

	/**
	 * 点赞后往书籍点在表增加数据
	 * 
	 * @param map
	 */
	void insertlikes(Map<String, Object> map);

	/**
	 * 根据writer_id和book_id查询是否点赞
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryLikes(Map<String, Object> map);

	/**
	 * 删除点赞表里面的记录
	 * 
	 * @param map
	 * @return int
	 */
	int dellikes(Map<String, Object> map);

	/**
	 * 查询默认收书籍藏夹
	 * 
	 * @param writerId
	 *            用户id
	 * @return
	 */
	Map<String, Object> queryDedaultFavorite(@Param("writer_id") long writer_id);

	/**
	 * 添加默认书籍收藏夹
	 * 
	 * @param writerId
	 *            用户id
	 */
	void insertFavorite(@Param("writer_id") long writer_id);

	/**
	 * 查询用户收藏夹下是否已收藏此书籍
	 * 
	 * @param bookId
	 *            书籍id
	 * @param favoriteId
	 *            收藏夹id
	 * @param writerId
	 *            用户id
	 * @return
	 */
	int queryMark(@Param("wid") long wid, @Param("favorite_id") long favorite_id, @Param("writer_id") long writer_id);

	/**
	 * 添加书籍收藏
	 * 
	 * @param bookId
	 *            书籍id
	 * @param favoriteId
	 *            收藏夹id
	 * @param writerId
	 *            用户id
	 */
	void insertMark(@Param("wid") long wid, @Param("favorite_id") long favorite_id, @Param("writer_id") long writer_id);

	/**
	 * 更新书籍的收藏数量
	 * 
	 * @param bookId
	 *            书籍id
	 * @param marks
	 *            书籍的收藏数
	 */
	void updateMarks(@Param("wid") long wid, @Param("bookmarks") long bookmarks);

	/**
	 * 查询书籍的收藏数
	 * 
	 * @param bookId
	 *            书籍id
	 * @return long
	 */
	long queryBookMarks(@Param("wid") long wid);

	/**
	 * 删除某一本书的收藏记录
	 * 
	 * @param bookId
	 *            书籍id
	 * @param favoriteId
	 *            收藏夹id
	 * @param writerId
	 *            用户id
	 */
	void deleteMark(@Param("wid") long wid, @Param("favorite_id") long favorite_id, @Param("writer_id") long writer_id);

	/**
	 * 根据ID修改点击数
	 * 
	 * @param id
	 * @param clicks
	 * @return
	 */
	void changeClicks(@Param("id") String id, @Param("clicks") int clicks);

	/**
	 * 查询文章附件
	 * 
	 * @param paraMap
	 * @return
	 */
	List<Map<String, Object>> queryCMSAttach(Map<String, Object> paraMap);

	/**
	 * 
	 * 
	 * 功能描述：添加评论数
	 *
	 * @param id
	 *
	 */
	//void updateComments(@Param("id") Long id);

	/**
	 * 根据ID查询相关文章
	 * @param id 文章主键
	 * @return
	 */
	List<Map<String, Object>> QueryShipByID(@Param("id") String id,@Param("startrow") int startrow);
    /**
     * 根据ID查询相关文章总条数
     * @param id 文章主键
     * @return
     */
    int QueryAllShip(@Param("id") String id);

    /**
     * 根据ID查询相关文章 总数
     * @param id
     * @return
     */
	int QueryShipByIDCount(String id);
}
