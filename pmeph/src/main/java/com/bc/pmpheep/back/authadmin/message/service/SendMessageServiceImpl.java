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
	

	/**
	 * 获取到接受者id
	 * @param userId
	 */
	@Override
	public List<Map<String,Object>> findOrgUserAndWriterUser(String userId) {
		// TODO Auto-generated method stub
		return orgMessageDao.findOrgUserAndWriterUser(userId);
	}
	
	/**
	 * 批量插入消息
	 * @param userMessageList
	 */
	public void batchInsertMessage(List<UserMessage> userMessageList) {
		// TODO Auto-generated method stub
		orgMessageDao.batchInsertMessage(userMessageList);
	}

	/**
	 * 插入附件信息
	 * @param map
	 */
	public void insertAttachmentInfo(Map map) {
		// TODO Auto-generated method stub
		 orgMessageDao.insertAttachmentInfo(map);
	}

	

}
