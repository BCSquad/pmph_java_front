package com.bc.pmpheep.back.commuser.booksearch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.book.bean.BookVO;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.service.exception.CheckedServiceException;
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
	/**查询某一类型下的子类型
	 * @param parentId
	 * @return
	 */
	List<Map<String,Object>> queryChildSort(@Param("parentId") Long parentId);

	/**根据id差分类
	 * @param id
	 * @return
	 */
	Map<String, Object> querySortById(@Param("id") Long id);
	 /**
     * 
     * 
     * 功能描述：分页初始化/查询图书详情
     * 
     * @param pageParameter 分页参数 ，isOnSale 是否上架，isNew 是否新书 ，type 书籍类别 ， isPromote 是否推荐，name
     *            isbn/图书名称
     * @return 分好页的结果集
     * @throws CheckedServiceException
     * 
     */
    List<Map<String,Object>> listBook(PageParameter<Map<String,Object>> pageParameter);

    /**
     * 
     * 
     * 功能描述：获取总条数
     * 
     * @param pageParameter 分页参数 ，isOnSale 是否上架，isNew 是否新书 ，type 书籍类别 ， isPromote 是否推荐，name
     *            isbn/图书名称
     * @return 分好页的结果集
     * @throws CheckedServiceException
     * 
     */
    Integer getBookTotal(PageParameter<Map<String,Object>> pageParameter);
    /**
     * @param map
     * @return
     */
    List<Map<String,Object>> querySearchSort(Map<String,Object> map);
}
