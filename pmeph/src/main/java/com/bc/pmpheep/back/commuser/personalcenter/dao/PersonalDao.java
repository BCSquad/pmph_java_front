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
	
	public List<Map<String,Object>> ListMyBookNewsTwo(PageParameter<Map<String, Object>> pageParameter);
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
	/**
	 * 文章随笔列表
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String,Object>> ListMyWritingsTwo(PageParameter<Map<String, Object>> pageParameter);
	/**
	 * 文章随笔总数
	 * @param pageParameter
	 * @return
	 */
	public Integer queryMyWritingsNewCount(PageParameter<Map<String, Object>> pageParameter);
	
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
	public Integer queryBookCorrectdCount(PageParameter<Map<String, Object>> pageParameter);
	/**
	 * 第一主编回复纠错
	 * @param paraMap
	 * @return
	 */
	public int authorReply(Map<String, String> paraMap);
	
	/**
	 * 我的纠错查询 （作为纠错人）
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> queryMyCorrection(PageParameter<Map<String, Object>> pageParameter);
	
	/**
	 * 我的纠错查询总数 （作为纠错人）
	 * @param pageParameter
	 * @return
	 */
	public Integer queryMyCorrectionCount(PageParameter<Map<String, Object>> pageParameter);
	
	/**
	 * 个人中心动态 汇总后面所有页签对应信息的新增、审批的动态
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> queryWriterUserTrendst(PageParameter<Map<String, Object>> pageParameter);
	
	/**
	 * 个人中心动态总数
	 * @param pageParameter
	 * @return
	 */
	public Integer queryWriterUserTrendstCount(PageParameter<Map<String, Object>> pageParameter);
	
	
	
	
}
