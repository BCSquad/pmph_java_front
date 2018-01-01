package com.bc.pmpheep.back.commuser.booksearch.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 
 * @author liudi
 * 书籍搜索 服务层接口
 */
public interface BookSearchService {

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
	int queryBookCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 点赞或取消赞 切换
	 * @param uid
	 * @param bookId
	 * @return
	 */
	Map<String, Object> likeSwitch(String uid, String bookId);

	/**查询某一类型下的子类型
	 * @param parentId
	 * @return
	 */
	List<Map<String,Object>> queryChildSort(Long parentId);

	/**根据id查询分类
	 * @param valueOf
	 * @return
	 */
	Map<String, Object> querySortById(Long id);
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
	
}
