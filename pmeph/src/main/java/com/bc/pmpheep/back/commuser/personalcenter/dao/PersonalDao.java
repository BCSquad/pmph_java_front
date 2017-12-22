package com.bc.pmpheep.back.commuser.personalcenter.dao;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;
import com.bc.pmpheep.back.plugin.PageParameter;

public interface PersonalDao {

	public List<PersonalNewMessage> ListMyConlection(Map<String, Object> permap);
	public List<PersonalNewMessage> ListMyFriend(Map<String, Object> permap);
	public List<PersonalNewMessage> ListMyGroup(Map<String, Object> permap);
	public List<PersonalNewMessage> ListMyOfeerTwo(Map<String, Object> permap);
	public List<PersonalNewMessage> ListMyWritingsTwo(Map<String, Object> permap);
	public List<PersonalNewMessage> ListMyBookNewsTwo(Map<String, Object> permap);
	public List<Map<String, Object>> ListAllBookJoin(PageParameter<Map<String, Object>> pageParameter);
	public Integer queryMyBooksJoinCount(PageParameter<Map<String, Object>> pageParameter);
	/**
	 * 分页查询选题申报topic表 供个人中心-我要出书页面展示
	 * @param pageParameter
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> queryMyTopicChoose(PageParameter<Map<String, Object>> pageParameter);
	/**
	 * 选题申报topic查询总数 
	 * @param pageParameter
	 * @return
	 */
	public Integer queryMyTopicChooseCount(PageParameter<Map<String, Object>> pageParameter);
	
	
}
