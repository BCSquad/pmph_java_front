package com.bc.pmpheep.back.commuser.booksearch.dao;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;
/**
 * 书籍搜索dao接口
 * @author Administrator
 *
 */
public interface BookSearchDao {
	/**
	 * 查询书籍列表
	 * @param pageParameter
	 * @return
	 */
	List<Map<String, Object>> queryBookList(PageParameter<Map<String, Object>> pageParameter);
	
	/**
	 * 查询书籍总数
	 * @param pageParameter
	 * @return
	 */
	Integer queryBookCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 修改状态
	 * @param paraMap
	 * @return
	 */
	Integer statusModify(Map<String, Object> paraMap);

	/**
	 * 删除某作家用户（writer_user）对某出版书籍(book)的点赞
	 * @param paraMap
	 * @return
	 */
	Integer UserDontLikeTheBook(Map<String, Object> paraMap);
	
	/**
	 * 插入某作家用户（writer_user）对某出版书籍(book)的点赞
	 * @param paraMap
	 * @return
	 */
	Integer UserLikeTheBook(Map<String, Object> paraMap);

	/**
	 * 点赞数+1
	 * @param bookId
	 * @return
	 */
	Integer increaseOneLike(String bookId);

	/**
	 * 点赞数-1
	 * @param bookId
	 * @return
	 */
	Integer decreaseOneLike(String bookId);
	
	/**
	 * 查询当前点赞数
	 * @param bookId
	 * @return
	 */
	int queryBookLikeById(String bookId);
	
}
