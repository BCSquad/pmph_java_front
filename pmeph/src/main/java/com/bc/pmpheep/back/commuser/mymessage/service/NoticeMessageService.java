package com.bc.pmpheep.back.commuser.mymessage.service;

import java.util.List;
import java.util.Map;

public interface NoticeMessageService {
	
	
	//申请信息列表
	List<Map<String, Object>> selectApplyMessage(Map<String, Object> paraMap);
	//更新申请消息表
	void updateApplyMessage(Map<String, Object> paraMap);
	//小子通知列表
	List<Map<String, Object>> selectNoticeMessage(Map<String, Object> paraMap);
	//删除通知
	void deleteNoticeMessage(Map<String, Object> paraMap);
	//教材申报通知详情
	Map<String, Object> queryNoticeMessageDetail(Map<String, Object> paraMap);
	//查询教材备注相关的附件
	List<Map<String, Object>> queryNoticeMessageDetailAttachment(Map<String, Object> paraMap);
	//查询教材相关联系人
	List<Map<String, Object>> queryNoticeMessageDetailContact(Map<String, Object> paraMap);
	/**查询遴选公告的内容id的查询公告详情
	 * @param paraMap
	 * @return
	 */
	Map<String, Object> queryCMSNotice(Map<String, Object> paraMap);
	
	//更新通知点击量
	void updateNoticeClicks(String cmsId);
	//查询通知数据总量
	int selectNoticeMessageTotalCount(Map<String, Object> paraMap);
	
	//查询系统消息总量
	int selectSysMessageTotalCount(Map<String, Object> paraMap);


}
