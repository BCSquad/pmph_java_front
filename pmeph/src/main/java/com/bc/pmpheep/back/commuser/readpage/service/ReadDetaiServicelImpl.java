package com.bc.pmpheep.back.commuser.readpage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.readpage.dao.ReadDetailDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.mongodb.util.Hash;

@Service("com.bc.pmpheep.back.commuser.readpage.service.ReadDetaiServicelImpl")
public class ReadDetaiServicelImpl implements ReadDetailService {
	
	@Autowired
	private ReadDetailDao readDetailDao;

	/**
	 * 查询读书详情页信息
	 */
	@Override
	public Map<String, Object> queryReadBook(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> map=readDetailDao.queryReadBook(id);
		return map;
	}

	/**
	 * 查询相关评论
	 */
	@Override
	public PageResult<Map<String, Object>> queryComment(PageParameter<Map<String, Object>> pageParameter) {
		// TODO Auto-generated method stub
		PageResult<Map<String, Object>> pageResult=new PageResult<Map<String, Object>>();
		pageResult.setPageSize(pageParameter.getPageSize());
		List<Map<String, Object>> map=readDetailDao.queryComment(pageParameter);
		for (Map<String, Object> pmap : map) {
			String time=pmap.get("gmt_create").toString().substring(0, 16);
			pmap.put("gmt_create", time);
		}
		int count=readDetailDao.querySize(pageParameter.getParameter().get("id").toString());
		pageResult.setTotal(count);
		pageResult.setRows(map);
		pageResult.setPageNumber(pageParameter.getPageNumber());
		return pageResult;
	}

	/**
	 * 查询配套图书
	 */
	@Override
	public Map<String, Object> querySupport(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> map=readDetailDao.querySupport(id);
		return map;
	}

	/**
	 * 查询人卫E教推荐书籍
	 */
	@Override
	public List<Map<String, Object>> queryRecommendByE(int num) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> map=readDetailDao.queryRecommendByE(num);
		return map;
	}

	/**
	 * 新增书籍评论
	 */
	@Override
	public Map<String, Object> insertComment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> rmap=new HashMap<String, Object>();
	    readDetailDao.insertComment(map);
	    rmap.put("returncode", "OK");
	    return rmap;
	}
	
    /**
     * 根据作者查询该作者写的书
     */
	@Override
	public List<Map<String, Object>> queryAuthorType(String author) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=readDetailDao.queryAuthorType(author);
		return list;
	}

	/**
	 * 根据书籍类型查询相关书籍
	 */
	@Override
	public List<Map<String, Object>> queryBookByType(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=readDetailDao.queryBookByType(map);
		return list;
	}

	/**
	 * 根据书籍类型查询相同类型的书
	 */
	@Override
	public List<Map<String, Object>> fresh(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=readDetailDao.fresh(map);
		return list;
	}

	/**
	 * 根据书籍ID增加点赞数
	 */
	@Override
	public Map<String, Object> addlikes(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> pmap=new HashMap<String, Object>();
		if(("del").equals(map.get("flag"))){
			readDetailDao.addlikes(map);
			int count=readDetailDao.dellikes(map);
			if(count>0){
				pmap.put("returncode", "yes");
			}
		}else{
			readDetailDao.addlikes(map);
			readDetailDao.insertlikes(map);
			pmap.put("returncode", "no");
		}
		return pmap;
	}

	@Override
	public List<Map<String, Object>> queryLikes(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=readDetailDao.queryLikes(map);
		return list;
	}

	/* 
	 * 添加收藏
	 */
	@Override
	public Map<String, Object> inserMark(long bookId, long writerId) {
		Map<String,Object> map=new HashMap<>();
		Map<String,Object> dmap=readDetailDao.queryDedaultFavorite(writerId);
		if(dmap==null){
			readDetailDao.insertFavorite(writerId);
			dmap=readDetailDao.queryDedaultFavorite(writerId);
		}
		long favoriteId=Long.valueOf(dmap.get("id").toString());
		int count=readDetailDao.queryMark(bookId,favoriteId,writerId);//查询用户是否收藏某一本书，如果收藏       count大于0，否则，等于0
		long marks=readDetailDao.queryBookMarks(bookId);//查询书籍的收藏数
		if(count>0){
			readDetailDao.deleteMark(bookId,favoriteId,writerId);
			readDetailDao.updateMarks(bookId,marks-1);//更新书籍表中的收藏数量啊
			map.put("returncode","remain");
		}else{
			readDetailDao.insertMark(bookId,favoriteId,writerId);//向用户书籍收藏表中加入收藏记录
			readDetailDao.updateMarks(bookId,marks+1);//更新书籍表中的收藏数量啊
			map.put("returncode", "OK");
		}
		return map;
	}

	/**
	 * 查询是否有收藏夹
	 */
	@Override
	public Map<String, Object> queryDedaultFavorite(String id) {
		// TODO Auto-generated method stub
		long userId=Long.valueOf(id);
		return readDetailDao.queryDedaultFavorite(userId);
	}

	/**
	 * 查询默认收藏夹是否收藏本书
	 */
	@Override
	public int queryMark(String bookId, String favoriteId, String writerId) {
		// TODO Auto-generated method stub
		long book_id=Long.valueOf(bookId);
		long favorite_id=Long.valueOf(favoriteId);
		long writer_id=Long.valueOf(writerId);
		return readDetailDao.queryMark(book_id,favorite_id,writer_id);
	}

	@Override
	public String delbookwriter(Map<String, Object> map) {
		// TODO 自动生成的方法存根  删除书评
		return readDetailDao.updateDelBookWriter(map);
	}
	
}
