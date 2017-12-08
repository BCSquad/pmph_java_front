package com.bc.pmpheep.back.commuser.personalcenter.service;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;

public interface PersonalService {

	
	public List<PersonalNewMessage> queryMyCol(Map<String, Object> permap);//查询个人中心我的收藏。

	public List<PersonalNewMessage> queryMyFriend(Map<String, Object> permap);//我的好友

	public List<PersonalNewMessage> queryMyGroup(Map<String, Object> permap);//我的小组
	
	public List<PersonalNewMessage> queryMyOfeerNew(Map<String, Object> permap);//我的申请动态最新消息

	public List<PersonalNewMessage> queryMyWritingsNew(Map<String, Object> permap);//我的随笔文章动态最新消息

	public List<PersonalNewMessage> queryMyBooksNew(Map<String, Object> permap);//我的书评消息动态最新消息

	public List<PersonalNewMessage> queryMyBooksJoin(Map<String, Object> permap);//教材申报最新消息
}
