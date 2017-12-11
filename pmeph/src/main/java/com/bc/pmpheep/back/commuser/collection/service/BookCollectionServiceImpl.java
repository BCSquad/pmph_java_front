package com.bc.pmpheep.back.commuser.collection.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.collection.dao.BookCollectionDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 书记收藏接口实现
 * @param 
 * @return 
 * @throws
 */
@Service("com.bc.pmpheep.back.commuser.collection.service.BookCollectionServiceImpl")
public class BookCollectionServiceImpl implements BookCollectionService {
	@Resource
	private BookCollectionDao   bookCollectionDao;

	@Override
	public List<Map<String, Object>> queryBookCollectionList(BigInteger writerId) {
		return bookCollectionDao.queryBookCollectionList(writerId);
	}

	@Override
	public PageResult<Map<String,Object>> queryBookList(PageParameter<Map<String,Object>> param) {
		PageResult<Map<String,Object>> result=new PageResult<>();
		result.setPageSize(param.getPageSize());
		result.setPageNumber(param.getPageNumber());
		int bookcount = bookCollectionDao.queryBookCont(param.getParameter());
		List<Map<String, Object>> list = bookCollectionDao.queryBookList(param);
		if(list.size()>0){
		for (Map<String, Object> map : list) {
			if("DEFAULT".equals(map.get("image_url"))){
				map.put("image_url", "statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
			}
			int like=bookCollectionDao.queryLikes((BigInteger) map.get("id"),(BigInteger) param.getParameter().get("writerId"));
		    map.put("like", like);
		}
		}
		result.setTotal(bookcount);
		result.setRows(list);
        return result;
	}

	@Override
	public int queryBookCont(BigInteger favoriteId,BigInteger writerId) {
		// TODO Auto-generated method stub
//		return bookCollectionDao.queryBookCont(favoriteId,writerId);
	    return 0;
	}

	@Override
	public int queryLikes(BigInteger bookId, BigInteger writerId) {
		// TODO Auto-generated method stub
		return bookCollectionDao.queryLikes(bookId, writerId);
	}

	@Override
	public Map<String, Object> updateLike(BigInteger bookId, BigInteger writerId) {
		Map<String,Object> map=new HashMap<>();
		int count=bookCollectionDao.queryLikes(bookId, writerId);
		BigInteger step=new BigInteger("1");
		Map<String, Object> numMap = bookCollectionDao.queryNum(bookId);
		BigInteger likes=(BigInteger) numMap.get("likes");
		if(count>0){
			bookCollectionDao.deleteBookLike(bookId, writerId);
			BigInteger mlike=likes.subtract(step);
			bookCollectionDao.updateBookLikes(bookId,mlike);
			map.put("likes", mlike);
		}else{
			Map<String,Object> lmap=new HashMap<String, Object>();
			lmap.put("bookId", bookId);
			lmap.put("writerId", writerId);
			bookCollectionDao.insertBookLike(lmap);
			BigInteger mlike=likes.add(step);
			bookCollectionDao.updateBookLikes(bookId,mlike);
			map.put("likes", mlike);
		}
		map.put("returncode", "OK");
		return map;
	}

	@Override
	public Map<String, Object> deleteMark(BigInteger markId,
			BigInteger writerId, BigInteger favoriteId,BigInteger bookId) {
		Map<String,Object> map=new HashMap<>();
		BigInteger step=new BigInteger("1");
		Map<String, Object> numMap = bookCollectionDao.queryNum(bookId);
		BigInteger markes=(BigInteger) numMap.get("bookmarks");
		bookCollectionDao.updateMarkes(bookId,markes.subtract(step));
		bookCollectionDao.deleteMark(markId, writerId, favoriteId);
		map.put("returncode", "OK");
		return map;
	}

	@Override
	public Map<String, Object> deleteFavorite(BigInteger writerId,
			BigInteger favoriteId) {
		Map<String,Object> map=new HashMap<>();
		BigInteger step=new BigInteger("1");
		List<Map<String,Object>> list=bookCollectionDao.queryOther(writerId, favoriteId);
		if(list.size()>0){
		for (Map<String, Object> book : list) {
			bookCollectionDao.updateMarkes((BigInteger)book.get("id"), ((BigInteger) book.get("bookmarks")).subtract(step));
		}
		}
		bookCollectionDao.deleteMark(null, writerId, favoriteId);
		bookCollectionDao.deleteFavorite(writerId, favoriteId);
		return map;
	}

	@Override
	public Map<String, Object> queryFavoriteById(BigInteger favoriteId) {
		// TODO Auto-generated method stub
		return bookCollectionDao.queryFavoriteById(favoriteId);
	}
}
