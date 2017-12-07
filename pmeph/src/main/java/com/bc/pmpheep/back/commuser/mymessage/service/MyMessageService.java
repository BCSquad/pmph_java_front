package com.bc.pmpheep.back.commuser.mymessage.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.mymessage.bean.DialogueVO;
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
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
	PageResult<MyMessageVO> listMyMessage(PageParameter<MyMessageVO> pageParameter) throws CheckedServiceException;
	
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
	List<DialogueVO> findMyDialogue  (Long thisId,Long friendId);
	
}
