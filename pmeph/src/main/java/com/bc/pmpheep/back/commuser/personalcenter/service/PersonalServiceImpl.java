package com.bc.pmpheep.back.commuser.personalcenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;
import com.bc.pmpheep.back.commuser.personalcenter.dao.PersonalDao;

@Service("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
public class PersonalServiceImpl implements PersonalService {

	@Autowired
	private PersonalDao personaldao;
	
	@Override
	public List<PersonalNewMessage> queryMyCol() {
		// TODO 自动生成的方法存根 查询我的收藏列表
		
		List<PersonalNewMessage> list1=personaldao.ListMyConlection();
		return list1;
	}

	@Override
	public List<PersonalNewMessage> queryMyFriend() {
		// TODO 自动生成的方法存根 查询我的好友
		List<PersonalNewMessage> list2=personaldao.ListMyFriend();
		return list2;
	}

	@Override
	public List<PersonalNewMessage> queryMyGroup() {
		// TODO 自动生成的方法存根  查询我的小组信息
		List<PersonalNewMessage> list3=personaldao.ListMyGroup();
		return list3;
	}

	@Override
	public List<PersonalNewMessage> queryMyOfeerNew() {
		// TODO 自动生成的方法存根 查询最新申请动态信息
		List<PersonalNewMessage> list4=personaldao.ListMyOfeerTwo();
		return list4;
	}

	@Override
	public List<PersonalNewMessage> queryMyWritingsNew() {
		// TODO 自动生成的方法存根  查询我的随笔文章最新信息
		List<PersonalNewMessage> list5=personaldao.ListMyWritingsTwo();
		return list5;
	}

	@Override
	public List<PersonalNewMessage> queryMyBooksNew() {
		// TODO 自动生成的方法存根 查询我的书评最新信息
		List<PersonalNewMessage> list6=personaldao.ListMyBookNewsTwo();
		return list6;
	}

	@Override
	public List<PersonalNewMessage> queryMyBooksJoin() {
		// TODO 自动生成的方法存根  查询我的教材申报最新信息
		List<PersonalNewMessage> list7=personaldao.ListAllBookJoin();
		return list7;
	}

}
