package com.bc.pmpheep.back.commuser.mymessage.dao;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.authadmin.message.bean.UserMessage;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 消息列表Dao层（机构用户）
 * @param 
 * @return 
 * @throws
 */
public interface NoticeMessageDao {
    /**获取消息列表
     * @return List<OrgMessage>
     */
    List<Map<String,Object>> getMessageList();
    
  	//更新申请消息表
  	void updateApplyMessage(Map<String, Object> paraMap);
  	
  	//申请列表
  	List<Map<String, Object>> selectApplyMessage(Map<String, Object> paraMap);
  	
  	//消息通知列表
  	List<Map<String, Object>> selectNoticeMessage(Map<String, Object> paraMap);
  	
  	//删除通知
  	void deleteNoticeMessage(Map<String, Object> paraMap);

  	//获取到机构用户和作家用户的id
	List<Map<String, Object>> findOrgUserAndWriterUser(String userId);

	void batchInsertMessage(List<UserMessage> userMessageList);

	void insertAttachmentInfo(Map map);

	//查询通知详情
	Map<String, Object> noticeMessageDetail(Map<String, Object> paraMap);
	
	//查询通知相关的附件
	List<Map<String, Object>> queryNoticeMessageDetailAttachment(
			Map<String, Object> paraMap);
	//查询通知相关的联系人
	List<Map<String, Object>> queryNoticeMessageDetailContact(
			Map<String, Object> paraMap);

	/**查询遴选公告的内容id的查询公告详情
	 * @param paraMap
	 * @return
	 */
	Map<String, Object> queryCMSNotice(Map<String, Object> paraMap);
	
	//更新通知点击量
	void updateNoticeClicks(String cmsId);
	
	//查询系统消息数据总量
	int selectNoticeMessageSysCount(Map<String, Object> paraMap);
	//公告数量
	int selectNoticeMessageCount(Map<String, Object> paraMap);
	
	//查询系统消息总量
	int selectSysMessageTotalCount(Map<String, Object> paraMap);
	/**查询公告附件
	 * @param paraMap
	 * @return
	 */
	List<Map<String, Object>>queryCMSAttach(Map<String, Object> paraMap);
	
	/**系统消息标题弹框回显
	 * @param paraMap
	 * @return
	 */
	Map<String, Object> queryTitleMessage(Map<String, Object> paraMap);
  }
