package com.bc.pmpheep.back.commuser.readpage.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ReadDetailDao {

	/**
	 * 根据书籍ID查询读书详情页信息
	 * @param id
	 * @return map
	 */
	Map<String, Object> queryReadBook(@Param("id") String id);
	/**
	 * 根据书籍ID查询评论
	 * @param id
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryComment(@Param("id") String id,@Param("start") int start);
	/**
	 * 根据书籍ID查询纠错
	 * @param id
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryCorrectByBookId(@Param("id") String id,@Param("start") int start);
	/**
	 * 根据书籍ID查询读者反馈
	 * @param id
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryFeedBackByBookId(@Param("id") String id,@Param("start") int start);
	/**
	 * 根据书籍ID查询配套图书
	 * @param id
	 * @return Map<String, Object>
	 */
	Map<String, Object> querySupport(@Param("id") String id);
	/**
	 * 根据书籍ID查询一共有多少条数据
	 * @return int
	 */
//	int querySize(@Param("book_id") String book_id);
	/**
	 * 查询人卫推荐
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryRecommendByE(@Param("x") int num);
	/**
	 * 新增图书评论
	 * @param map
	 */
	void insertComment(Map<String, Object> map);
	/**
	 * 新增图书纠错
	 * @param map
	 */
	int correction(Map<String, Object> map);
	/**
	 * 根据作者查询该作者写的书
	 * @param author
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryAuthorType(@Param("author") String author);
	/**
	 * 根据书籍类型查询书籍
	 * @param num
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryBookByType(Map<String, Object> map);
	/**
	 * 更具书籍类型查询相同类型的书
	 * @param x
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> fresh(Map<String, Object> map);
	/**
	 * 根据书籍ID增加点赞数
	 * @param id
	 */
	void addlikes(Map<String, Object> map);
	/**
	 * 点赞后往书籍点在表增加数据
	 * @param map
	 */
	void insertlikes(Map<String, Object> map);
	/**
	 * 根据writer_id和book_id查询是否点赞
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryLikes(Map<String, Object> map);
	
	/**查询用户收藏夹下是否已收藏此书籍
	 * @param bookId   书籍id
	 * @param favoriteId  收藏夹id
	 * @param writerId   用户id
	 * @return
	 */
	int queryMark(@Param("bookId") long bookId,@Param("favoriteId") long favoriteId,@Param("writerId") long writerId);
	
	/**添加书籍收藏
	 * @param bookId      书籍id
	 * @param favoriteId  收藏夹id
	 * @param writerId    用户id
	 */
	void insertMark(@Param("bookId") long bookId,@Param("favoriteId") long favoriteId,@Param("writerId") long writerId);
	/**更新书籍的收藏数量
	 * @param bookId 书籍id
	 * @param marks  书籍的收藏数
	 */
	void updateMarks(@Param("bookId") long bookId,@Param("marks") long marks);
	/**查询书籍的收藏数
	 * @param bookId  书籍id
	 * @return long
	 */
	long queryBookMarks(@Param("bookId") long bookId);
	/**
	 * 删除点赞表里面的记录
	 * @param map
	 * @return int
	 */
	int dellikes(Map<String, Object> map);
	
	/**查询默认收书籍藏夹
	 * @param writerId  用户id
	 * @return
	 */
	Map<String, Object> queryDedaultFavorite(@Param("writerId") long writerId);
    /**添加默认书籍收藏夹
     * @param writerId 用户id
     */
    void insertFavorite(@Param("writerId") long writerId);
	/**删除某一本书的收藏记录
	 * @param bookId      书籍id
	 * @param favoriteId  收藏夹id
	 * @param writerId    用户id
	 */
	void deleteMark(@Param("bookId")long bookId,@Param("favoriteId") long favoriteId,@Param("writerId") long writerId);
	/**
	 * 写长评
	 * @param map
	 * @return 1，1代表数据添加成功
	 */
	int insertlong(Map<String, Object> map);
	/**
	 * 查询登录人是否写过长评
	 * @return list,list.size大于1说明写过
	 */
	List<Map<String, Object>> queryLoginLong(@Param("writer_id") String writer_id,@Param("book_id") String book_id);
	/**
	 * 删除评论
	 * @param map
	 * @return
	 */
	String updateDelBookWriter(Map<String, Object> map);
	/**
	 * 查询长评
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryLong(@Param("book_id") String book_id,@Param("start") int start);
	/**
	 * 根据ID修改点击数
	 * @param book_id
	 * @param clicks
	 * @return
	 */
	void changeClicks(@Param("book_id") String book_id,@Param("clicks") int clicks);
	
	/**
	 * 查询主编
	 * @param map
	 * @return
	 */
	Map<String, Object> queryEditor(Map<String, Object> map);
	/**
	 * 查询视频
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryVideo(@Param("book_id") String book_id);
	List<Map<String, Object>> querySource(@Param("book_id") String book_id);
	
	/**
	 * 查询我的长评
	 *
	 */
	Map<String, Object> queryMyLong(@Param("book_id") String book_id,@Param("id") String id);
	
	/**
	 * 我的长评论修改
	 * 
	 * @param map
	 */
	void updateCommentLong(Map<String, Object> map);
	
	/**
	 * 提交读者反馈
	 * @param map
	 * @return
	 */
	int bookfeedback(Map<String, Object> map);

	/**查询书籍微视频
	 * @param map  startnum:分页的开始的序号      size:分页的数据容量
	 * @return
	 */
	List<Map<String,Object>> queryMoreBookVidos(Map<String,Object> map);
	/**查询书籍微视频的总数
	 * @param map
	 * @return int
	 */
	int queryMoreBookVidosCount(Map<String,Object> map);
	
	/**
	 * 查询关联图书总数
	 * @param id
	 * @param type
	 * @return
	 */
	int queryRelatedBookListCount(Map<String,Object> map);
	
	/**
	 * 查询关联图书列表
	 * @param id
	 * @param type
	 * @param start
	 * @param pageSize
	 * @param nextPage
	 * @return
	 */
	List<Map<String, Object>> queryRelatedBookList(Map<String,Object> map);

	int addSource(Map<String, Object> map);
}
