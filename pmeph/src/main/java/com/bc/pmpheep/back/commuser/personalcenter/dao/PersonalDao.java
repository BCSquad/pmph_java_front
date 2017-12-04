package com.bc.pmpheep.back.commuser.personalcenter.dao;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;

public interface PersonalDao {

	public List<PersonalNewMessage> ListMyConlection();
	public List<PersonalNewMessage> ListMyFriend();
	public List<PersonalNewMessage> ListMyGroup();
	public List<PersonalNewMessage> ListMyOfeerTwo();
	public List<PersonalNewMessage> ListMyWritingsTwo();
	public List<PersonalNewMessage> ListMyBookNewsTwo();
	public List<PersonalNewMessage> ListAllBookJoin();
}
