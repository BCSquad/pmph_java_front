package com.bc.pmpheep.back.authadmin.message.dao;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.authadmin.message.bean.UserMessage;
import org.apache.ibatis.annotations.Param;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 消息列表Dao层（机构用户）
 * @param 
 * @return 
 * @throws
 */
public interface OrgMessageDao {
	
    /**获取消息列表
     * @return List<OrgMessage>
     */
    List<Map<String,Object>> getMessageList();
    
    /**
     * 更新申请消息表
     * @param paraMap
     */
  	void updateApplyMessage(Map<String, Object> paraMap);
  	
  	/**
  	 * 申请列表
  	 * @param paraMap
  	 * @return
  	 */
  	List<Map<String, Object>> selectApplyMessage(Map<String, Object> paraMap);
  	
  	/**
  	 * 消息通知列表
  	 * @param paraMap
  	 * @return
  	 */
  	List<Map<String, Object>> selectNoticeMessage(Map<String, Object> paraMap);
  	
  	/**
  	 * 删除通知
  	 * @param paraMap
  	 */
  	void deleteNoticeMessage(Map<String, Object> paraMap);

  	/**
  	 * 获取到机构用户和作家用户的id
  	 * @param userId
  	 * @return
  	 */
	List<Map<String, Object>> findOrgUserAndWriterUser(Map<String, Object> map);

	/**
	 * 批量插入消息
	 * @param userMessageList
	 */
	void batchInsertMessage(List<UserMessage> userMessageList);

	/**
	 * 插入附件信息
	 * @param map
	 */
	void insertAttachmentInfo(Map map);

	/**
	 * 查询通知详情
	 * @param paraMap
	 * @return
	 */
	Map<String, Object> noticeMessageDetail(Map<String, Object> paraMap);
	
	/**
	 * 查询通知相关的附件
	 * @param paraMap
	 * @return
	 */
	List<Map<String, Object>> queryNoticeMessageDetailAttachment(
			Map<String, Object> paraMap);

	/**
	 * 查询通知相关的联系人
	 * @param paraMap
	 * @return
	 */
	List<Map<String, Object>> queryNoticeMessageDetailContact(
			Map<String, Object> paraMap);

	/**
	 * 查找申请人的id
	 * @param bookId
	 * @return
	 */
    List<Map<String,Object>> findApplyId(@Param("bookId") String bookId);
}
