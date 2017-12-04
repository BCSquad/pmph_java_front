package com.bc.pmpheep.back.commuser.readpage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.readpage.dao.ReadDetailDao;
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
	public List<Map<String, Object>> queryComment(String id) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> map=readDetailDao.queryComment(id);
		for (Map<String, Object> pmap : map) {
			String time=pmap.get("gmt_create").toString().substring(0, 16);
			pmap.put("gmt_create", time);
		}
		return map;
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
		readDetailDao.addlikes(map);
		readDetailDao.insertlikes(map);
		Map<String, Object> pmap=new HashMap<String, Object>();
		pmap.put("returncode", "no");
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
	public Map<String, Object> inserMark(long bookId,long favoriteId, long writerId) {
		Map<String,Object> map=new HashMap<>();
		int count=readDetailDao.queryMark(bookId,favoriteId,writerId);//查询用户是否收藏某一本书，如收藏count大于0，否则，等于0
		long marks=readDetailDao.queryBookMarks(bookId);//查询书籍的收藏数
		if(count>0){
			map.put("returncode","remain");
		}else{
			readDetailDao.insertMark(bookId,favoriteId,writerId);//向用户书籍收藏表中加入收藏记录
			readDetailDao.updateMarks(bookId,marks+1);//更新书籍表中的收藏数量啊
			map.put("returncode", "OK");
		}
		return map;
	}
	
}
