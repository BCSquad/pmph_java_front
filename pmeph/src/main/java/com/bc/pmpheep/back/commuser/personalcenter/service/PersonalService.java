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

	/**
	 * 对主键为id的纠错进行主编回复
	 * @param id
	 * @param author_reply
	 * @return
	 */
	public Map<String, Object> authorReply(String id, String author_reply);

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
	public int queryMyCorrectionCount(PageParameter<Map<String, Object>> pageParameter);
	
	
	/**
	 * 我的评论
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> myComment(PageParameter<Map<String, Object>> pageParameter);
	/**
	 * 我的评论总数
	 * @param pageParameter
	 * @return
	 */
	public int myCommentCount(PageParameter<Map<String, Object>> pageParameter);
	
	/**
	 * 我的问卷
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> mySurvey(PageParameter<Map<String, Object>> pageParameter);
	/**
	 * 我的问卷总数
	 * @param pageParameter
	 * @return
	 */
	public int mySurveyCount(PageParameter<Map<String, Object>> pageParameter);
	
	//查询一个调查
	List<Map<String, Object>> getSurvey(long surveyId);
	
	//查询选项
	List<Map<String, Object>> getOptions(Long questionId);
	
	//查询单选选项答案
	public String getAnswers(Long questionId);
	
	//查询多选选项答案
	public String getCheckAnswers(Long questionId);
	//查询填空选项答案
	public String getInpAnswers(Long questionId);

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
	public int queryWriterUserTrendstCount(PageParameter<Map<String, Object>> pageParameter);
	
	/**
	 * 生成个人动态的方法
	 * @param TrendstName 动态名称
	 * @param tableId	  关联表主键
	 * @param trendstType 动态类型：0生成，1通过，2未通过
	 */
	public void saveUserTrendst(String TrendstName,String tableId,int trendstType,String writer_user_id);

	/**
	 * 伪删除我的纠错
	 * @param id
	 * @param logUserId
	 * @return
	 */
	public Map<String, Object> deleteMyCorrection(String id, String logUserId);

	/**
	 * 伪删除我的书评
	 * @param id
	 * @param logUserId
	 * @return
	 */
	public Map<String, Object> deleteMyBookComment(String id, String logUserId);

	
	
	
}
