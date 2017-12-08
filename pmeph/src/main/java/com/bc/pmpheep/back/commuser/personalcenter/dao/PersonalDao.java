package com.bc.pmpheep.back.commuser.personalcenter.dao;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;

public interface PersonalDao {

	public List<PersonalNewMessage> ListMyConlection(Map<String, Object> permap);
	public List<PersonalNewMessage> ListMyFriend(Map<String, Object> permap);
	public List<PersonalNewMessage> ListMyGroup(Map<String, Object> permap);
	public List<PersonalNewMessage> ListMyOfeerTwo(Map<String, Object> permap);
	public List<PersonalNewMessage> ListMyWritingsTwo(Map<String, Object> permap);
	public List<PersonalNewMessage> ListMyBookNewsTwo(Map<String, Object> permap);
	public List<PersonalNewMessage> ListAllBookJoin(Map<String, Object> permap);
}
