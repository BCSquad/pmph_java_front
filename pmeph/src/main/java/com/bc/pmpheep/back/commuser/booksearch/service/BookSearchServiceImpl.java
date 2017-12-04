package com.bc.pmpheep.back.commuser.booksearch.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.booksearch.dao.BookSearchDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.controller.bean.ResponseBean;
/**
 * 书籍搜索服务层实现类
 * @author liudi
 *
 */
@Service("com.bc.pmpheep.back.commuser.booksearch.service.BookSearchServiceImpl")
public class BookSearchServiceImpl implements BookSearchService {

	@Autowired
	BookSearchDao bookDao;
	
	@Override
	public List<Map<String, Object>> queryBookList(PageParameter<Map<String, Object>> pageParameter) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("startNum", pageParameter.getStart());
		paraMap.put("pageSize", pageParameter.getPageSize());
		paraMap.put("queryName", pageParameter.getParameter().get("queryName"));
		paraMap.put("uid", pageParameter.getParameter().get("uid"));
		List<Map<String,Object>> resultList = bookDao.queryBookList(paraMap);
		return resultList;
	}

	@Override
	public int queryBookCount(PageParameter<Map<String, Object>> pageParameter) {
		Map<String, Object> paraMap = pageParameter.getParameter();
		paraMap.put("queryName", pageParameter.getParameter().get("queryName"));
		paraMap.put("queryStatus", pageParameter.getParameter().get("queryStatus"));
		Integer count =bookDao.queryBookCount(paraMap);
		Integer maxPageNum = (int) Math.ceil(1.0*count/pageParameter.getPageSize());
		return maxPageNum;
	}

	@Override
	public Map<String, Object> likeSwitch(String uid, String bookId) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		paraMap.put("uid", uid);
		paraMap.put("bookId", bookId);
		if (uid != null && bookId != null) { //必须不为空 避免误删所有点赞数据
			//删除该用户对该数的所有点赞
			Integer del_like_count = bookDao.UserDontLikeTheBook(paraMap);
			if (del_like_count==0) { //删除数为0 说明是无赞状态 则增加一条点赞数据 按钮应切换为激活状态
				Integer like_count =bookDao.UserLikeTheBook(paraMap);
				bookDao.increaseOneLike(bookId);
				resultMap.put("switchResult", "active");
			}else{ //有删除 说明原为赞状态 现取消赞 按钮应切换为非激活状态
				resultMap.put("switchResult", "non-active");
				bookDao.decreaseOneLike(bookId);
			}
		}
		int like_count = bookDao.queryBookLikeById(bookId);
		resultMap.put("like_count", like_count);
		return resultMap;
	}

	
	
}
