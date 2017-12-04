package com.bc.pmpheep.back.commuser.booksearch.service;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.controller.bean.ResponseBean;

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

	
	
}
