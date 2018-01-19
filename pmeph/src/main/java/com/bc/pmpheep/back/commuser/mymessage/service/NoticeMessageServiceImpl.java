package com.bc.pmpheep.back.commuser.mymessage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.mymessage.dao.NoticeMessageDao;


@Service("com.bc.pmpheep.back.commuser.mymessage.service.NoticeMessageServiceImpl")
public class NoticeMessageServiceImpl implements NoticeMessageService {
	
	@Autowired
	NoticeMessageDao noticeMessageDao;

	//申请信息列表
	@Override
	public List<Map<String, Object>> selectApplyMessage(Map<String,Object> paraMap) {
		List<Map<String, Object>> list = noticeMessageDao.selectApplyMessage(paraMap);
		return list;
	}
	
	//更新申请消息表
	@Override
	public void updateApplyMessage(Map<String, Object> paraMap) {
		noticeMessageDao.updateApplyMessage(paraMap);
	}
	
	//消息通知列表
	@Override
	public List<Map<String, Object>> selectNoticeMessage(Map<String, Object> paraMap) {
		List<Map<String, Object>> list = noticeMessageDao.selectNoticeMessage(paraMap);
		return list;
	}
	
	//删除通知
	@Override
	public void deleteNoticeMessage(Map<String, Object> paraMap) {
		noticeMessageDao.deleteNoticeMessage(paraMap);
		
	}
	
	//教材申报通知详情
	@Override
	public Map<String, Object> queryNoticeMessageDetail(Map<String, Object> paraMap) {
	    Map<String, Object>  map = noticeMessageDao.noticeMessageDetail(paraMap);
		return map;
	}

	//查询教材备注相关的附件
	@Override
	public List<Map<String, Object>> queryNoticeMessageDetailAttachment(Map<String, Object> paraMap) {
		List<Map<String, Object>> list = noticeMessageDao.queryNoticeMessageDetailAttachment(paraMap);
		return list;
	}
	
	//查询教材相关联系人
	@Override
	public List<Map<String, Object>> queryNoticeMessageDetailContact(Map<String, Object> paraMap){
			List<Map<String, Object>> list = noticeMessageDao.queryNoticeMessageDetailContact(paraMap);
			return list;
	}
     
	//查询遴选公告的内容id的查询公告详情
	@Override
	public Map<String, Object> queryCMSNotice(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return noticeMessageDao.queryCMSNotice(paraMap);
	}

	//更新通知点击量
	@Override
	public void updateNoticeClicks(String cmsId) {
		noticeMessageDao.updateNoticeClicks(cmsId);
		
	}

	
	
	
}
