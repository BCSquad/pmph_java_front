package com.bc.pmpheep.back.authadmin.message.service;

import java.util.*;

import com.bc.pmpheep.back.authadmin.message.bean.UserMessage;

/**
 * 机构用户发送 新消息
 * @author xcy
 *
 */
public interface SendMessageService {
	
	/**
	 * 获取到接受者id
	 * @param userId
	 */
	public List<Map<String,Object>> findOrgUserAndWriterUser(Map<String, Object> map);
	
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


	/**
	 * 获取接受者id
	 * @param s
	 * @return
	 */
	List<Map<String,Object>> findApplyId(String s);
}
