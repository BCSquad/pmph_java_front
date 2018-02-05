package com.bc.pmpheep.back.commuser.personalcenter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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
	public Integer myCommentCount(PageParameter<Map<String, Object>> pageParameter);
	
	/**
	 * 我的评论修改
	 * @param map
	 */
	void updateComment(Map<String, Object> map);
	
	/**
	 * 我的评论删除
	 * @param para_map
	 * @return
	 */
	void deleteComment(Map<String, Object> map);
	
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
	public Integer mySurveyCount(PageParameter<Map<String, Object>> pageParameter);
	

	// 查询的单个调查对应的问题
	List<Map<String, Object>> getSurvey(long surveyId);

	// 查询问题对应的选项
	List<Map<String, Object>> getOptions(Long questionId);

	// 查询单选选项答案
	public String getAnswers(Map<String, Object> map);

	// 查询多选选项答案
	public String getCheckAnswers(Map<String, Object> map);

	// 查询填空选项答案
	public String getInpAnswers(Map<String, Object> map);

	/**
	 * 生成动态
	 * @param paraMap
	 * @return
	 */
	public int saveUserTrendst(Map<String, Object> paraMap);
	
	/**
	 * 通过图书纠错表主键查询被纠错图书的第一主编id
	 * @param tableId
	 * @return
	 */
	public String queryFirstEditorByBookCorrectionId(String tableId);
	/**
	 * 伪删除我的纠错 同时根据纠错表主键和纠错人id 同时满足才删除
	 * @param para_map
	 * @return
	 */
	public int deleteMyCorrection(Map<String, Object> para_map);
	/**
	 * 伪删除我的书评 同时根据纠错表主键和纠错人id 同时满足才删除
	 * @param para_map
	 * @return
	 */
	public int deleteMyBookComment(Map<String, Object> para_map);
	/**
	 * 查用户
	 * @param userId
	 * @return
	 */
	public Map<String, Object> queryUserById(String userId);
	
	
	/**
	 * 查询两人除拒绝（1）外最亲密的好友关系（0申请，2接收 取max）
	 * @param userId
	 * @param logUserId
	 * @return
	 */
	public Map<String, Object> queryOurFriendShip(@Param("userId")String userId,@Param("logUserId")String logUserId);
	
	
}
