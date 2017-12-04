package com.bc.pmpheep.back.authadmin.message.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.message.dao.OrgMessageDao;

@Service("com.bc.pmpheep.back.authadmin.message.service.MessageServiceImpl")
public class OrgMessageServiceImpl implements OrgMessageService {
	
	@Autowired
	OrgMessageDao orgMessageDao;

	//申请信息列表
	@Override
	public List<Map<String, Object>> selectApplyMessage(Map<String,Object> paraMap) {
		List<Map<String, Object>> list = orgMessageDao.selectApplyMessage(paraMap);
		return list;
	}
	
	//更新申请消息表
	@Override
	public void updateApplyMessage(Map<String, Object> paraMap) {
		orgMessageDao.updateApplyMessage(paraMap);
	}
	
	//消息通知列表
	@Override
	public List<Map<String, Object>> selectNoticeMessage(Map<String, Object> paraMap) {
		List<Map<String, Object>> list = orgMessageDao.selectNoticeMessage(paraMap);
		return list;
	}
	
	//删除通知
	@Override
	public void deleteNoticeMessage(Map<String, Object> paraMap) {
		orgMessageDao.deleteNoticeMessage(paraMap);
		
	}
	
	//教材申报通知详情
	@Override
	public Map<String, Object> queryNoticeMessageDetail(Map<String, Object> paraMap) {
	    Map<String, Object>  map = orgMessageDao.noticeMessageDetail(paraMap);
		return map;
	}

	//查询教材备注相关的附件
	@Override
	public List<Map<String, Object>> queryNoticeMessageDetailAttachment(Map<String, Object> paraMap) {
		List<Map<String, Object>> list = orgMessageDao.queryNoticeMessageDetailAttachment(paraMap);
		return list;
	}
	
	//查询教材相关联系人
	@Override
	public List<Map<String, Object>> queryNoticeMessageDetailContact(Map<String, Object> paraMap){
			List<Map<String, Object>> list = orgMessageDao.queryNoticeMessageDetailContact(paraMap);
			return list;
	}
	
	
	
}
