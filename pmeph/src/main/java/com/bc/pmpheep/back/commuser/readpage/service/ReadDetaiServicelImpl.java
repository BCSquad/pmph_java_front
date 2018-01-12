package com.bc.pmpheep.back.commuser.readpage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
import com.bc.pmpheep.back.commuser.readpage.dao.ReadDetailDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.mongodb.util.Hash;

@Service("com.bc.pmpheep.back.commuser.readpage.service.ReadDetaiServicelImpl")
public class ReadDetaiServicelImpl implements ReadDetailService {
	
	@Autowired
	private ReadDetailDao readDetailDao;
	
	@Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
    private PersonalService personalService;

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
	public List<Map<String, Object>> queryComment(String id,int start) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> map=readDetailDao.queryComment(id,start);
		for (Map<String, Object> pmap : map) {
			String time=pmap.get("gmt_create").toString().substring(0, 16);
			pmap.put("gmt_create", time);
			pmap.put("start", start+2);
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
	    personalService.saveUserTrendst("wdsp", map.get("table_trendst_id").toString(), 0, map.get("writer_id").toString());
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

	/**
	 * 查询是否点赞
	 */
	@Override
	public List<Map<String, Object>> queryLikes(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=readDetailDao.queryLikes(map);
		return list;
	}

	/** 
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

	/**
	 * 删除书评 
	 */
	@Override
	public String delbookwriter(Map<String, Object> map) {
		// TODO 自动生成的方法存根  删除书评
		return readDetailDao.updateDelBookWriter(map);
	}

	/**
	 * 新增图书纠错
	 */
	@Override
	public String correction(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String returncode="";
		int count=readDetailDao.correction(map);
		if(count>0){
			returncode="OK";
			personalService.saveUserTrendst("wdjc", map.get("table_trendst_id").toString(), 0, map.get("user_id").toString());
		}
		return returncode;
	}

	/**
	 * 写长评
	 */
	@Override
	public String insertlong(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String returncode="";
		int count=readDetailDao.insertlong(map);
		if(count>0){
			returncode="OK";
		}
		return returncode;
	}

	/**
	 * 查询登陆人是否写过长评
	 */
	@Override
	public List<Map<String, Object>> queryLoginLong(String writer_id,String book_id) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=readDetailDao.queryLoginLong(writer_id,book_id);
		return list;
	}

	/**
	 * 查询长评
	 */
	@Override
	public List<Map<String, Object>> queryLong(String book_id,int start) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=readDetailDao.queryLong(book_id,start);
		for (Map<String, Object> pmap : list) {
			String time=pmap.get("gmt_create").toString().substring(0, 16);
			pmap.put("gmt_create", time);
			pmap.put("longstart", start+2);
		}
		return list;
	}

	/**
	 * 根据ID改变点击数
	 */
	@Override
	public void changeClicks(String book_id, int clicks) {
		// TODO Auto-generated method stub
		readDetailDao.changeClicks(book_id, clicks);
	}
}
