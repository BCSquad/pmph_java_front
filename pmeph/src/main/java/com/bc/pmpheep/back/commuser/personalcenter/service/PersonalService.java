package com.bc.pmpheep.back.commuser.personalcenter.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;
import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterUserTrendst;
import com.bc.pmpheep.back.plugin.PageParameter;

public interface PersonalService {

	public List<PersonalNewMessage> queryMyCol(Map<String, Object> permap);// 查询个人中心我的收藏。

	public List<PersonalNewMessage> queryMyFriend(Map<String, Object> permap);// 我的好友

	public List<Map<String, Object>> queryMyGroup(Map<String, Object> permap);// 我的小组

	public List<PersonalNewMessage> queryMyOfeerNew(Map<String, Object> permap);// 我的申请动态最新消息

	public List<Map<String, Object>> queryMyBooksNew(PageParameter<Map<String, Object>> pageParameter);// 我的书评消息动态最新消息

	public List<Map<String, Object>> queryMyBooksJoin(PageParameter<Map<String, Object>> pageParameter);// 教材申报最新消息

	public int queryMyBooksJoinCount(PageParameter<Map<String, Object>> pageParameter);// 教材申报最新消息总数

	public List<Map<String, Object>> queryLcjc(PageParameter<Map<String, Object>> pageParameter);//个人中心临床决策
	
	public List<Map<String, Object>> queryLcjcVer2(PageParameter<Map<String, Object>> pageParameter);//个人中心临床决策

	public int queryCountLcjc(PageParameter<Map<String, Object>> pageParameter);//个人中心临床决策
	
	public int queryCountLcjcVer2(PageParameter<Map<String, Object>> pageParameter);//个人中心临床决策
	/**
	 * 分页查询选题申报topic表 供个人中心-我要出书页面展示
	 * 
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> queryMyTopicChoose(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 选题申报topic查询总数 供个人中心-我要出书页面分页
	 * 
	 * @param pageParameter
	 * @return
	 */
	public int queryMyTopicChooseCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 我的随笔文章
	 * 
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> queryMyWritingsNew(PageParameter<Map<String, Object>> pageParameter);// 我的随笔文章动态最新消息

	/**
	 * 我的随笔文章总数
	 * 
	 * @param pageParameter
	 * @return
	 */
	public int queryMyWritingsNewCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 作为第一主编被提出的图书纠错查询 （图书纠错 页面）
	 * 
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> queryBookCorrectd(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 作为第一主编被提出的图书纠错总数 （图书纠错 页面）
	 * 
	 * @param pageParameter
	 * @return
	 */
	public int queryBookCorrectdCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 对主键为id的纠错进行主编回复
	 * 
	 * @param id
	 * @param author_reply
	 * @return
	 */
	public Map<String, Object> authorReply(String id, String author_reply);

	/**
	 * 我的纠错查询 （作为纠错人）
	 * 
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> queryMyCorrection(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 我的纠错查询总数 （作为纠错人）
	 * 
	 * @param pageParameter
	 * @return
	 */
	public int queryMyCorrectionCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 我的评论
	 * 
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> myComment(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 我的评论总数
	 * 
	 * @param pageParameter
	 * @return
	 */
	public int myCommentCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 我的评论修改
	 * 
	 * @param map
	 */
	Map<String, Object> updateComment(Map<String, Object> map);

	/**
	 * 删除我的评论
	 * 
	 * @param id
	 * @param logUserId
	 * @return
	 */
	public void deleteComment(Map<String, Object> map);

	/**
	 * 我的问卷
	 * 
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> mySurvey(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 我的问卷总数
	 * 
	 * @param pageParameter
	 * @return
	 */
	public int mySurveyCount(PageParameter<Map<String, Object>> pageParameter);

	// 查询一个调查
	List<Map<String, Object>> getSurvey(long surveyId);

	// 查询选项
	List<Map<String, Object>> getOptions(Long questionId);

	// 查询单选选项答案
	public String getAnswers(Map<String, Object> map);

	// 查询多选选项答案
	List<Map<String, Object>> getCheckAnswers(Map<String, Object> map);

	// 查询填空选项答案
	public String getInpAnswers(Map<String, Object> map);

	// 回显答案保存按钮判断是否消失
	public Map<String, Object> btnSaveOrHidden(Map<String, Object> map);

	// 弹框回显短评内容
	public Map<String, Object> shortComment(Map<String, Object> map);

	/**
	 * 个人中心动态 汇总后面所有页签对应信息的新增、审批的动态
	 * 
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> queryWriterUserTrendst(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 个人中心动态总数
	 * 
	 * @param pageParameter
	 * @return
	 */
	public int queryWriterUserTrendstCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 生成个人动态的方法
	 * 
	 * @param writerUserTrendst
	 */
	public void saveUserTrendst(WriterUserTrendst writerUserTrendst);

	/**
	 * 伪删除我的纠错
	 * 
	 * @param id
	 * @param logUserId
	 * @return
	 */
	public Map<String, Object> deleteMyCorrection(String id, String logUserId);

	/**
	 * 伪删除我的书评
	 * 
	 * @param id
	 * @param logUserId
	 * @return
	 */
	public Map<String, Object> deleteMyBookComment(String id, String logUserId);

	/**
	 * 通过id查user
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, Object> queryUserById(String userId);

	/**
	 * 查询两人除拒绝（1）外最亲密的好友关系（0申请，2接收 取max）
	 * 
	 * @param userId
	 * @param logUserId
	 * @return
	 */
	public Map<String, Object> queryOurFriendShip(String userId, String logUserId);

	/**
	 * 逻辑删除动态
	 * 
	 * @param writerUserTrendst
	 */
	void deleteUserTrendst(WriterUserTrendst writerUserTrendst);

	/**
	 * 根据id查询教材申报
	 * 
	 * @param declaration_id
	 * @return
	 */
	public Map<String, Object> queryDeclarationById(String declaration_id);

	/**
	 * 
	 * 
	 * 功能描述：删除评论时 图书评论-1
	 *
	 * @param id
	 *
	 */
	void updateDownComments(Long id);
	
	//查询评论并删除 
	public Long getCommentBycid(Long id);

	//查询当前用户是否参加过调查问卷
	List<Map<String,Object>> whetherSurvey(PageParameter pageParameter);

	/**
	 * 我的收藏
	 *
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> listMyFavorites(PageParameter<Map<String, Object>> pageParameter,String contextpath);

	/**
	 * 我的收藏总数
	 *
	 * @param pageParameter
	 * @return
	 */
	public int listMyFavoritesCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 读者反馈列表查询
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> queryMyBookFeedBack(PageParameter<Map<String, Object>> pageParameter);
	/**
	 * 读者反馈总数查询
	 * @param pageParameter
	 * @return
	 */
	public int queryMyBookFeedBackCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 保存或更新个人申报信息
	 * @param perMap
	 * @param tssbList
	 * @param stuList
	 * @param workList
	 * @param steaList
	 * @param zjxsList
	 * @param jcbjList
	 * @param gjkcjsList
	 * @param gjghjcList
	 * @param jcbxList
	 * @param zjkyList
	 * @param zjkzqkList
	 * @param achievementMap
	 * @param monographList
	 * @param publishList
	 * @param sciList
	 * @param clinicalList
	 * @param acadeList
	 * @param pmphList
	 * @param digitalMap
	 * @param intentionlMap
	 * @return
	 */
    Map<String,Object> savePerInfo(Map<String, Object> perMap, List<Map<String, Object>> tssbList, List<Map<String, Object>> stuList, List<Map<String, Object>> workList, List<Map<String, Object>> steaList, List<Map<String, Object>> zjxsList, List<Map<String, Object>> jcbjList, List<Map<String, Object>> gjkcjsList, List<Map<String, Object>> gjghjcList, List<Map<String, Object>> jcbxList, List<Map<String, Object>> zjkyList, List<Map<String, Object>> zjkzqkList, Map<String, Object> achievementMap, List<Map<String, Object>> monographList, List<Map<String, Object>> publishList, List<Map<String, Object>> sciList, List<Map<String, Object>> clinicalList, List<Map<String, Object>> acadeList, List<Map<String, Object>> pmphList, Map<String, Object> digitalMap, Map<String, Object> intentionlMap);
}
