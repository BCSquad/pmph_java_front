package com.bc.pmpheep.back.commuser.personalcenter.service;

import java.util.List;

import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;

public interface PersonalService {

	
	public List<PersonalNewMessage> queryMyCol();//查询个人中心我的收藏。

	public List<PersonalNewMessage> queryMyFriend();//我的好友

	public List<PersonalNewMessage> queryMyGroup();//我的小组
	
	public List<PersonalNewMessage> queryMyOfeerNew();//我的申请动态最新消息

	public List<PersonalNewMessage> queryMyWritingsNew();//我的随笔文章动态最新消息

	public List<PersonalNewMessage> queryMyBooksNew();//我的书评消息动态最新消息

	public List<PersonalNewMessage> queryMyBooksJoin();//教材申报最新消息
}
