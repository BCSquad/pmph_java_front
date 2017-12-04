package com.bc.pmpheep.back.commuser.personalcenter.service;

import java.util.List;

import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;

public interface PersonalService {

	
	public List<PersonalNewMessage> queryMyCol();//查询个人中心我的收藏。

	public List<PersonalNewMessage> queryMyFriend();

	public List<PersonalNewMessage> queryMyGroup();
	
	public List<PersonalNewMessage> queryMyOfeerNew();

	public List<PersonalNewMessage> queryMyWritingsNew();

	public List<PersonalNewMessage> queryMyBooksNew();

	public List<PersonalNewMessage> queryMyBooksJoin();
}
