package com.bc.pmpheep.back.commuser.booksearch.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.booksearch.dao.BookSearchDao;
import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterUserTrendst;
import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
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
	
	@Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
    private PersonalService personalService;
	
	@Override
	public List<Map<String, Object>> queryBookList(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String,Object>> resultList = bookDao.queryBookList(pageParameter);
		return resultList;
	}

	@Override
	public int queryBookCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count =bookDao.queryBookCount(pageParameter);
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
				
				WriterUserTrendst wut = new WriterUserTrendst(uid, 7, bookId);
				personalService.saveUserTrendst(wut);
				
			}else{ //有删除 说明原为赞状态 现取消赞 按钮应切换为非激活状态
				resultMap.put("switchResult", "non-active");
				bookDao.decreaseOneLike(bookId);
			}
		}
		int like_count = bookDao.queryBookLikeById(bookId);
		resultMap.put("like_count", like_count);
		return resultMap;
	}

	@Override
	public List<Map<String, Object>> queryChildSort(Long parentId) {
		// TODO Auto-generated method stub
		return bookDao.queryChildSort(parentId);
	}

	@Override
	public Map<String, Object> querySortById(Long id) {
		// TODO Auto-generated method stub
		return bookDao.querySortById(id);
	}

	@Override
	public List<Map<String, Object>> listBook(
			PageParameter<Map<String, Object>> pageParameter) {
		// TODO Auto-generated method stub
		return bookDao.listBook(pageParameter);
	}

	@Override
	public Integer getBookTotal(PageParameter<Map<String, Object>> pageParameter) {
		// TODO Auto-generated method stub
		return bookDao.getBookTotal(pageParameter);
	}

	@Override
	public List<Map<String, Object>> querySearchSort(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bookDao.querySearchSort(map);
	}

	
	
}
