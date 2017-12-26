package com.bc.pmpheep.back.commuser.personalcenter.service;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;
import com.bc.pmpheep.back.plugin.PageParameter;

public interface PersonalService {

	
	public List<PersonalNewMessage> queryMyCol(Map<String, Object> permap);//查询个人中心我的收藏。

	public List<PersonalNewMessage> queryMyFriend(Map<String, Object> permap);//我的好友

	public List<PersonalNewMessage> queryMyGroup(Map<String, Object> permap);//我的小组
	
	public List<PersonalNewMessage> queryMyOfeerNew(Map<String, Object> permap);//我的申请动态最新消息

	

	public List<Map<String,Object>> queryMyBooksNew(PageParameter<Map<String, Object>> pageParameter);//我的书评消息动态最新消息

	public List<Map<String, Object>>  queryMyBooksJoin(PageParameter<Map<String, Object>> pageParameter);//教材申报最新消息
	
	public int queryMyBooksJoinCount(PageParameter<Map<String, Object>> pageParameter);//教材申报最新消息总数

	/**
	 * 分页查询选题申报topic表 供个人中心-我要出书页面展示
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> queryMyTopicChoose(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 选题申报topic查询总数 供个人中心-我要出书页面分页
	 * @param pageParameter
	 * @return
	 */
	public int queryMyTopicChooseCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 我的随笔文章
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String,Object>> queryMyWritingsNew(PageParameter<Map<String, Object>> pageParameter);//我的随笔文章动态最新消息
	
	/**
	 * 我的随笔文章总数
	 * @param pageParameter
	 * @return
	 */
	public int queryMyWritingsNewCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 作为第一主编被提出的图书纠错查询 （图书纠错 页面）
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> queryBookCorrectd(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 作为第一主编被提出的图书纠错总数 （图书纠错 页面）
	 * @param pageParameter
	 * @return
	 */
	public int queryBookCorrectdCount(PageParameter<Map<String, Object>> pageParameter);

	
	
	
	
}
