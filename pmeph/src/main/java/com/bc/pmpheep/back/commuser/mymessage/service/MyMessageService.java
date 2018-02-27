package com.bc.pmpheep.back.commuser.mymessage.service;

import java.util.List;

import com.bc.pmpheep.back.commuser.mymessage.bean.DialogueVO;
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.service.exception.CheckedServiceException;

public interface MyMessageService {
	
	/**
	 * 
	 * 
	 * 功能描述：获取我的消息列表
	 * 
	 * @param pageParameter
	 *            分页参数,title 消息标题,isRead 是否已读,userId 用户id,userType 用户类型
	 * @return
	 * @throws CheckedServiceException
	 * 
	 */
	List<MyMessageVO> listMyMessage(Integer pageNumber,Integer pageSize,String state,Long userId) throws CheckedServiceException;
	
	/**
	 * 
	 * 
	 * 功能描述：
	 *
	 * @param senderId 发送者id
	 * @param senderType 
	 * @param userId
	 * @param userType
	 * @return
	 * @throws CheckedServiceException
	 *
	 */
	List<MyMessageVO> updateMyMessage(Long senderId, Integer senderType, Long userId, Integer userType)
			throws CheckedServiceException;
	
	/**
	 * 获取我和朋友的对话记录
	 * @author Mryang
	 * @createDate 2017年12月7日 下午2:20:33
	 * @param thisId
	 * @param friendId
	 * @return
	 */
	List<DialogueVO> findMyDialogue  (Long thisId,Long friendId,Integer friendType);
	
    /**
     * 
     * 更新我和对话者的对话状态
     * @author Mryang
     * @createDate 2017年12月11日 下午4:57:50
     * @param senderId
     * @param senderType
     * @param receiverId
     * @param receiverType
     * @return
     */
	Integer updateMyTalk(Long senderId, Short senderType, Long receiverId, Short receiverType);
	
	/**
	 * 发送私信
	 * @introduction 
	 * @author Mryang
	 * @createDate 2017年12月7日 下午3:51:11
	 * @param frendId
	 * @param content
	 */
	void senNewMsg(Long thisId,Short thisType,Long frendId,Short friendIdType,String title,String content);
	
}
