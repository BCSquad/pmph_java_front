package com.bc.pmpheep.back.commuser.mymessage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.mymessage.bean.DialogueVO;
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.service.exception.CheckedServiceException;

public interface MyMessageDao {
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
	List<MyMessageVO> listMyMessage(PageParameter<MyMessageVO> pageParameter);

	/**
	 * 
	 * 
	 * 功能描述：获取总条数
	 * 
	 * @param pageParameter
	 * @return
	 * 
	 */
	Integer listMyMessageTotal(PageParameter<MyMessageVO> pageParameter);
	
	/**
	 * 
	 * 
	 * 功能描述：查询私信列表
	 *
	 * @param senderId
	 * @param senderType
	 * @param userId
	 * @param userType
	 * @return
	 *
	 */
	List<MyMessageVO> listMyMessageDetail(@Param("senderId") Long senderId, @Param("senderType") Integer senderType,
			@Param("userId") Long userId, @Param("userType") Integer userType);
	
	/**
	 * 获取我和朋友的对话记录
	 * @author Mryang
	 * @createDate 2017年12月7日 下午2:20:33
	 * @param thisId
	 * @param friendId
	 * @return
	 */
	List<DialogueVO> findMyDialogue  (@Param("thisId")Long thisId,@Param("friendId")Long friendId);

}
