package com.bc.pmpheep.back.commuser.personalcenter.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;
import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterUserTrendst;
import com.bc.pmpheep.back.plugin.PageParameter;

import javax.swing.*;

public interface PersonalDao {

	public List<PersonalNewMessage> ListMyConlection(Map<String, Object> permap);

	public List<PersonalNewMessage> ListMyFriend(Map<String, Object> permap);

	public List<Map<String, Object>> ListMyGroup(Map<String, Object> permap);

	public List<PersonalNewMessage> ListMyOfeerTwo(Map<String, Object> permap);

	public List<Map<String, Object>> ListMyBookNewsTwo(PageParameter<Map<String, Object>> pageParameter);

	public List<Map<String, Object>> ListAllBookJoin(PageParameter<Map<String, Object>> pageParameter);

	public Integer queryMyBooksJoinCount(PageParameter<Map<String, Object>> pageParameter);

	public List<Map<String, Object>> queryLcjc(PageParameter<Map<String, Object>> pageParameter);

	int queryCountLcjc(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 分页查询选题申报topic表 供个人中心-我要出书页面展示
	 * 
	 * @param pageParameter
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> queryMyTopicChoose(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 选题申报topic查询总数
	 * 
	 * @param pageParameter
	 * @return
	 */
	public Integer queryMyTopicChooseCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 文章随笔列表
	 * 
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> ListMyWritingsTwo(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 文章随笔总数
	 * 
	 * @param pageParameter
	 * @return
	 */
	public Integer queryMyWritingsNewCount(PageParameter<Map<String, Object>> pageParameter);

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
	public Integer queryBookCorrectdCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 第一主编回复纠错
	 * 
	 * @param paraMap
	 * @return
	 */
	public int authorReply(Map<String, String> paraMap);

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
	public Integer queryMyCorrectionCount(PageParameter<Map<String, Object>> pageParameter);

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
	public Integer queryWriterUserTrendstCount(PageParameter<Map<String, Object>> pageParameter);

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
	public Integer myCommentCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 我的评论修改
	 * 
	 * @param map
	 */
	void updateComment(Map<String, Object> map);

	/**
	 * 我的评论删除
	 * 
	 * @param para_map
	 * @return
	 */
	void deleteComment(Map<String, Object> map);

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
	public Integer mySurveyCount(PageParameter<Map<String, Object>> pageParameter);

	// 查询的单个调查对应的问题
	List<Map<String, Object>> getSurvey(long surveyId);

	// 查询问题对应的选项
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
	 * 生成动态
	 * 
	 * @param writerUserTrendst
	 * @return
	 */
	public int saveUserTrendst(WriterUserTrendst writerUserTrendst);

	/**
	 * 通过图书纠错表主键查询被纠错图书的第一主编id
	 * 
	 * @param tableId
	 * @return
	 */
	public String queryFirstEditorByBookCorrectionId(String tableId);

	/**
	 * 伪删除我的纠错 同时根据纠错表主键和纠错人id 同时满足才删除
	 * 
	 * @param para_map
	 * @return
	 */
	public int deleteMyCorrection(Map<String, Object> para_map);

	/**
	 * 伪删除我的书评 同时根据纠错表主键和纠错人id 同时满足才删除
	 * 
	 * @param para_map
	 * @return
	 */
	public int deleteMyBookComment(Map<String, Object> para_map);

	/**
	 * 查用户
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
	public List<Map<String, Object>> queryOurFriendShip(@Param("userId") String userId,
			@Param("logUserId") String logUserId);

	/**
	 * 删除旧动态 逻辑删除
	 * 
	 * @param writerUserTrendst
	 * @return
	 */
	public int deleteUserTrendst(WriterUserTrendst writerUserTrendst);

	/**
	 * 根据id查询申报情况
	 * 
	 * @param declaration_id
	 * @return
	 */
	public Map<String, Object> queryDeclarationById(@Param("declaration_id") String declaration_id);

	Long getCommentById(@Param("id") Long id);
	//查询评论并删除 
	Long getCommentBycid(@Param("id") Long id);
	
	void updateDownComments(@Param("id") Long id);

	//查询用户参加过的问卷
	Map<String,Object> joinSurvey(@Param("id") String id);

	//查询当前用户是否参加过调查问卷
	List<Map<String,Object>> whetherSurvey(PageParameter pageParameter );

	/**
	 * 我的收藏
	 *
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> listMyFavorites(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 我的收藏总数
	 *
	 * @param pageParameter
	 * @return
	 */
	public Integer listMyFavoritesCount(PageParameter<Map<String, Object>> pageParameter);

	/**查询用户是否对某一本书点赞
	 * @param bookId  BigInteger 书籍id
	 * @param writerId BigInteger 用户id
	 * @return
	 */
	int queryLikesb(@Param("id") String id,@Param("logUserId") String logUserId);

	/**查询用户为某一文章是否点赞
	 * @param contentId  文章id
	 * @param writerId   用户id
	 * @return
	 */
	int queryLikesc(@Param("id") String id,@Param("logUserId") String logUserId );

	/**
	 * 查询读者反馈列表
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String, Object>> queryMyBookFeedBack(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 查询读者反馈总数
	 * @param pageParameter
	 * @return
	 */
	public Integer queryMyBookFeedBackCount(PageParameter<Map<String, Object>> pageParameter);
	
	

}
