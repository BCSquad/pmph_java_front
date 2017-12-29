package com.bc.pmpheep.back.commuser.homepage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.homepage.dao.HomeDao;



@Service("com.bc.pmpheep.back.homepage.service.HomeServiceImpl")
public class HomeServiceImpl implements HomeService {
	
	@Autowired
	private HomeDao homeDao;
	
	/**
	 * 查询公文
	 */
	@Override
	public List<Map<String, Object>> queryDocument(){
		List<Map<String, Object>> list=homeDao.queryDocument();
		return list;
	}
	/**
	 * 查询公告
	 */
	@Override
	public List<Map<String, Object>> queryNotice(){
		List<Map<String, Object>> list=homeDao.queryNotice();
		return list;
	}
	/**
	 * 查询医学随笔
	 */
	@Override
	public List<Map<String, Object>> queryArticle(int endrow){
		List<Map<String, Object>> list=homeDao.queryArticle(endrow);
		for (Map<String, Object> map : list) {
			String time=map.get("gmt_create").toString().substring(0, 16);
			map.put("gmt_create", time);
		}
		return list;
	}
	/**
	 * 查询推荐作者
	 */
	@Override
	public List<Map<String, Object>> queryAuthor(){
		List<Map<String, Object>> list=homeDao.queryAuthor();
		return list;
	}
	/**
	 * 查询书评
	 */
	@Override
	public List<Map<String, Object>> queryComment(){
		List<Map<String, Object>> list=homeDao.queryComment();
		return list;
	}
	/**
	 * 查询书评
	 */
	@Override
	public List<Map<String, Object>> queryBook(Map<String, Object> map){
		List<Map<String, Object>> list=homeDao.queryBook(map);
		return list;
	}
	/**
	 * 查询销量最高的书
	 */
	@Override
	public List<Map<String, Object>> querySale(int type){
		List<Map<String, Object>> list=homeDao.querySale(type);
		return list;
	}
	/**
	 * 查询书籍分类
	 */
	@Override
	public List<Map<String, Object>> queryBookType(int parent_id){
		List<Map<String, Object>> list=homeDao.queryBookType(parent_id);
		return list;
	}
	/**
	 * 查询热门标签
	 */
	@Override
	public List<Map<String, Object>> queryLabel(long typeid){
		String typepath="0-"+typeid+"-%";
		List<Map<String, Object>> list=homeDao.queryLabel(typepath);
		return list;
	}
}
