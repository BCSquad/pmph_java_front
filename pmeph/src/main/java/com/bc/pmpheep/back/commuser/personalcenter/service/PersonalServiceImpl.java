package com.bc.pmpheep.back.commuser.personalcenter.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;
import com.bc.pmpheep.back.commuser.personalcenter.dao.PersonalDao;
import com.bc.pmpheep.back.plugin.PageParameter;

@Service("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
public class PersonalServiceImpl implements PersonalService {

	@Autowired
	private PersonalDao personaldao;
	
	@Override
	public List<PersonalNewMessage> queryMyCol(Map<String, Object> permap) {
		// TODO 自动生成的方法存根 查询我的收藏列表
		
		List<PersonalNewMessage> list1=personaldao.ListMyConlection(permap);
		return list1;
	}

	@Override
	public List<PersonalNewMessage> queryMyFriend(Map<String, Object> permap) {
		// TODO 自动生成的方法存根 查询我的好友
		List<PersonalNewMessage> list2=personaldao.ListMyFriend(permap);
		return list2;
	}

	@Override
	public List<PersonalNewMessage> queryMyGroup(Map<String, Object> permap) {
		// TODO 自动生成的方法存根  查询我的小组信息
		List<PersonalNewMessage> list3=personaldao.ListMyGroup(permap);
		return list3;
	}

	@Override
	public List<PersonalNewMessage> queryMyOfeerNew(Map<String, Object> permap) {
		// TODO 自动生成的方法存根 查询最新申请动态信息
		List<PersonalNewMessage> list4=personaldao.ListMyOfeerTwo(permap);
		return list4;
	}

	@Override
	public List<Map<String,Object>> queryMyWritingsNew(PageParameter<Map<String, Object>> pageParameter) {
		// 查询我的随笔文章最新信息
		List<Map<String,Object>> list5=personaldao.ListMyWritingsTwo(pageParameter);
		return list5;
	}
	
	@Override
	public int queryMyWritingsNewCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count =personaldao.queryMyWritingsNewCount(pageParameter);
		return count;
	}

	@Override
	public List<Map<String,Object>> queryMyBooksNew(PageParameter<Map<String, Object>> pageParameter) {
		// TODO 自动生成的方法存根 查询我的书评最新信息
		List<Map<String,Object>> list6=personaldao.ListMyBookNewsTwo(pageParameter);
		return list6;
	}

	@Override
	public List<Map<String, Object>>  queryMyBooksJoin(PageParameter<Map<String, Object>> pageParameter) {
		// 查询我的教材申报最新信息
		List<Map<String, Object>> list7=personaldao.ListAllBookJoin(pageParameter);
		return list7;
	}
	@Override
	public int queryMyBooksJoinCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count =personaldao.queryMyBooksJoinCount(pageParameter);
		return count;
	}

	@Override
	public List<Map<String, Object>> queryMyTopicChoose(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String, Object>> result_list = personaldao.queryMyTopicChoose(pageParameter);
		return result_list;
	}

	@Override
	public int queryMyTopicChooseCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count =personaldao.queryMyTopicChooseCount(pageParameter);
		return count;
	}

	@Override
	public List<Map<String, Object>> queryBookCorrectd(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String, Object>> result_list = personaldao.queryBookCorrectd(pageParameter);
		return result_list;
	}

	@Override
	public int queryBookCorrectdCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count =personaldao.queryBookCorrectdCount(pageParameter);
		return count;
	}

	

	

}
