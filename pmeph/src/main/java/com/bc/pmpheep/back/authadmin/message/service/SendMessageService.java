package com.bc.pmpheep.back.authadmin.message.service;

import java.util.*;

import com.bc.pmpheep.back.authadmin.message.bean.UserMessage;

public interface SendMessageService {
	
	/**
	 * 获取到接受者id
	 * @param userId
	 */
	public List<Map<String,Object>> findOrgUserAndWriterUser(String userId);
	/**
	 * 存放消息
	 * @param list
	 */
	public void insertMessage(List<Map<String,Object>> list);
	

	/**
	 * 存放附件
	 * @param list
	 */
	public void insertAttachment(List<Map<String,Object>> list);
	
	
	/**
	 * 批量插入消息
	 * @param userMessageList
	 */
	public void batchInsertMessage(List<UserMessage> userMessageList);
	
	/**
	 * 插入附件信息
	 * @param map
	 */
	public void insertAttachmentInfo(Map map);
	
	

}
