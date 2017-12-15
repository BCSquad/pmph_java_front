package com.bc.pmpheep.back.commuser.mymessage.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.user.bean.PmphUser;
import com.bc.pmpheep.back.authadmin.user.bean.WriterUser;
import com.bc.pmpheep.back.authadmin.user.service.PmphUserService;
import com.bc.pmpheep.back.authadmin.user.service.WriterUserService;
import com.bc.pmpheep.back.commuser.mymessage.bean.DialogueVO;
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessage;
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.back.commuser.mymessage.dao.MyMessageDao;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

@Service("com.bc.pmpheep.back.commuser.mymessage.service.MyMessageServiceImpl")
public class MyMessageServiceImpl implements MyMessageService {

	@Autowired
	MyMessageDao myMessageDao;
	@Autowired
	@Qualifier("com.bc.pmpheep.general.service.MessageService")
	MessageService messageService;
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.mymessage.service.MessageAttachmentServiceImpl")
	MessageAttachmentService messageAttachmentService;
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.user.service.PmphUserServiceImpl")
	PmphUserService pmphUserService;
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.user.service.WriterUserServiceImpl")
	WriterUserService writerUserService;

	@Override
	public List<MyMessageVO> listMyMessage(Integer pageNumber,Integer pageSize,String state,Long userId) throws CheckedServiceException {
		if (null == userId ) {
			throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
					"用户id为空！");
		}
		if(null == pageNumber || pageNumber < 1){
			pageNumber =1; 
		}
		if(null == pageSize || pageSize < 1){
			pageSize = 999999; 
		}
		List<MyMessageVO> list = myMessageDao.listMyMessage((pageNumber-1)*pageSize,pageSize,state,userId);
		if(null==list){
			list = new ArrayList<MyMessageVO> (1);
		}
		for (MyMessageVO myMessageVO : list) {
			String msgId = myMessageVO.getMsgId() ;
			Message message = messageService.get(msgId);
			myMessageVO.setContent(null == message ?null :message.getContent());
			myMessageVO.setAvatar(RouteUtil.userAvatar(myMessageVO.getAvatar()));
		}
		return list;
	}

	/**
	 * 
	 * 
	 * 功能描述：给消息赋予头像
	 *
	 * @param myMessageVO
	 * @return
	 *
	 */
	public MyMessageVO setAvatar(MyMessageVO myMessageVO) {
		WriterUser user = writerUserService.get(myMessageVO.getUserId());
		myMessageVO.setUserAvatar(RouteUtil.userAvatar(user.getAvatar()));
		myMessageVO.setUserName(user.getRealname());
		if (myMessageVO.getSenderId().equals(user.getId()) && myMessageVO.getSenderType().equals(2)) {
			switch (myMessageVO.getReceiverType()) {
			case 0:
				myMessageVO.setName("系统");
				break;
			case 1:
				PmphUser pmphUser = pmphUserService.get(myMessageVO.getReceiverId());
				myMessageVO.setAvatar(RouteUtil.userAvatar(pmphUser.getAvatar()));
				myMessageVO.setName(pmphUser.getRealname());
				break;

			case 2:
				WriterUser writerUser = writerUserService.get(myMessageVO.getReceiverId());
				myMessageVO.setAvatar(RouteUtil.userAvatar(writerUser.getAvatar()));
				myMessageVO.setName(writerUser.getRealname());
				break;

			case 3:
				// 现在没有机构用户
				break;

			default:
				throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
						"发送者类型不正确！");
			}
		} else {
			switch (myMessageVO.getSenderType()) {
			case 0:
				myMessageVO.setName("系统");
				break;
			case 1:
				PmphUser pmphUser = pmphUserService.get(myMessageVO.getSenderId());
				myMessageVO.setAvatar(RouteUtil.userAvatar(pmphUser.getAvatar()));
				myMessageVO.setName(pmphUser.getRealname());
				break;

			case 2:
				WriterUser writerUser = writerUserService.get(myMessageVO.getSenderId());
				myMessageVO.setAvatar(RouteUtil.userAvatar(writerUser.getAvatar()));
				myMessageVO.setName(writerUser.getRealname());
				break;

			case 3:
				// 现在没有机构用户
				break;

			default:
				throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
						"发送者类型不正确！");
			}
		}

		if (myMessageVO.getUserId().equals(myMessageVO.getReceiverId())
				&& myMessageVO.getUserType().equals(myMessageVO.getReceiverType())) {// 当接收者是当前用户时，表示不是我发送的
			myMessageVO.setIsMy(false);

		}
		if (myMessageVO.getUserId().equals(myMessageVO.getSenderId())
				&& myMessageVO.getUserType().equals(myMessageVO.getSenderType())) {// 当发送者是当前用户时，表示是我发送的
			myMessageVO.setIsMy(true);
		}

		return myMessageVO;
	}
	
	@Override
	public List<MyMessageVO> updateMyMessage(Long senderId, Integer senderType, Long userId, Integer userType)
			throws CheckedServiceException {
		if (ObjectUtil.isNull(senderId)) {
			throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
					"对话者id为空");
		}
		if (ObjectUtil.isNull(senderType)) {
			throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
					"对话者类型为空");
		}
		if (ObjectUtil.isNull(userId)) {
			throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
					"用户id为空");
		}
		if (ObjectUtil.isNull(userType)) {
			throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
					"用户类型为空");
		}
		List<MyMessageVO> list = myMessageDao.listMyMessageDetail(senderId, senderType, userId, userType);
		for (MyMessageVO myMessageVO : list) {
			myMessageVO.setUserId(userId);
			myMessageVO.setUserType(userType);
			myMessageVO = setAvatar(myMessageVO);
			// Message message = messageService.get(myMessageVO.getMsgId());
			// if (ObjectUtil.isNull(message)) {
			// throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE,
			// CheckedExceptionResult.NULL_PARAM,
			// "没有获取到内容！");
			// }
			// myMessageVO.setContent(message.getContent());

		}
		return list;
	}

	@Override
	public List<DialogueVO> findMyDialogue(Long thisId, Long friendId,Integer friendType) {
		if(null == friendType){
			friendType =2 ; //默认作家用户
		}
		List<DialogueVO> lst = myMessageDao.findMyDialogue(thisId, friendId,friendType);
		// 装入详情
		for (DialogueVO dialogueVO : lst) {
			Message message = messageService.get(dialogueVO.getMsgId());
			if (null != message) {
				dialogueVO.setContent(message.getContent());
			}
			String avatar =  dialogueVO.getAvatar();
			dialogueVO.setAvatar(RouteUtil.userAvatar(avatar));
		}
		return lst;
	}
	
	@Override
	public Integer updateMyTalk(Long senderId, Short senderType, Long receiverId, Short receiverType){
		return myMessageDao.updateMyTalk(senderId, senderType, receiverId, receiverType);
	}

	@Override
	public void senNewMsg(Long thisId, Long frendId, Short friendIdType, String title, String content) {
		Message message = new Message();
		message.setContent(content);
		messageService.add(message);
		MyMessage userMessage = new MyMessage();
		userMessage.setMsgId(message.getId());
		userMessage.setMsgType(new Short("2"));
		userMessage.setTitle(title);
		userMessage.setSenderId(thisId);
		if (null == friendIdType) {
			userMessage.setSenderType(new Short("2"));
		} else {
			userMessage.setSenderType(friendIdType);
		}
		userMessage.setReceiverId(frendId);
		userMessage.setReceiverType(new Short("2"));
		myMessageDao.addUserMessage(userMessage);
	}
}
