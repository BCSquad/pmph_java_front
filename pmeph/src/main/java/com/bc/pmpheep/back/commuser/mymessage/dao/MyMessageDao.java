package com.bc.pmpheep.back.commuser.mymessage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.mymessage.bean.DialogueVO;
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessage;
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
	List<MyMessageVO> listMyMessage(@Param("start")Integer start ,@Param("pageSize")Integer pageSize ,@Param("state")String state,@Param("thisUserId")Long thisUserId);

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
	 * 更新已经看了的数据 
	 * @author Mryang
	 * @createDate 2017年12月11日 下午4:52:16
	 * @param pageParameter
	 * @return
	 */
	Integer updateMyTalk(@Param("senderId") Long senderId, @Param("senderType") Short senderType,
			             @Param("receiverId") Long receiverId, @Param("receiverType") Short receiverType);
	
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
	List<DialogueVO> findMyDialogue  (@Param("thisId")Long thisId,@Param("friendId")Long friendId,@Param("friendType")Integer friendType);
	
	/**
     * 单条数据插入 UserMessage
     * 
     * @author Mryang
     * @createDate 2017年9月28日 下午3:35:46
     * @param userMessage
     * @return 影响行数
     */
    Integer addUserMessage(MyMessage userMessage);
    
    /**
     * 通过id 动态更新UserMessage
     */
    Integer updateUserMessage(MyMessage userMessage);


	List<Map<String,Object>> getMaterialManagers(@Param("materialId")Long materialId);

	List<Map<String,Object>> getOrgUser(@Param("orgId")Long orgId);

}
