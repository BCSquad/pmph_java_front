package com.bc.pmpheep.back.authadmin.message.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.message.bean.UserMessage;
import com.bc.pmpheep.back.authadmin.message.dao.OrgMessageDao;

@Service("com.bc.pmpheep.back.authadmin.message.service.SendMessageServiceImpl")
public class SendMessageServiceImpl implements SendMessageService {

	@Autowired
	OrgMessageDao orgMessageDao;
	
	@Override
	public void insertMessage(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertAttachment(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String,Object>> findOrgUserAndWriterUser(String userId) {
		
		// TODO Auto-generated method stub
		
		return orgMessageDao.findOrgUserAndWriterUser(userId);
		
		
	}

	public void batchInsertMessage(List<UserMessage> userMessageList) {
		// TODO Auto-generated method stub
		orgMessageDao.batchInsertMessage(userMessageList);
	}

	public void insertAttachmentInfo(Map map) {
		// TODO Auto-generated method stub
		 orgMessageDao.insertAttachmentInfo(map);
	}

	

}
