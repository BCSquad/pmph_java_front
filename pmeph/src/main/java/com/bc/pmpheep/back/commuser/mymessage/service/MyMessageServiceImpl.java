package com.bc.pmpheep.back.commuser.mymessage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.back.commuser.mymessage.dao.MyMessageDao;
import com.bc.pmpheep.back.commuser.user.bean.PmphUser;
import com.bc.pmpheep.back.commuser.user.bean.WriterUser;
import com.bc.pmpheep.back.commuser.user.service.PmphUserService;
import com.bc.pmpheep.back.commuser.user.service.WriterUserService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.back.util.ArrayUtil;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.back.util.PageParameterUitl;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

@Service
public class MyMessageServiceImpl implements MyMessageService {

	@Autowired
	MyMessageDao myMessageDao;
	@Autowired
	MessageService messageService;
	@Autowired
	MessageAttachmentService messageAttachmentService;
	@Autowired
	PmphUserService pmphUserService;
	@Autowired
	WriterUserService writerUserService;

	@Override
	public PageResult<MyMessageVO> listMyMessage(PageParameter<MyMessageVO> pageParameter)
			throws CheckedServiceException {
		if (ObjectUtil.isNull(pageParameter.getParameter().getUserId())) {
			throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
					"用户id为空！");
		}
		if (ObjectUtil.isNull(pageParameter.getParameter().getUserType())) {
			throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
					"用户类型为空！");
		}
		PageResult<MyMessageVO> pageResult = new PageResult<MyMessageVO>();
		Integer total = myMessageDao.listMyMessageTotal(pageParameter);
		PageParameterUitl.CopyPageParameter(pageParameter, pageResult);
		if (total > 0) {
			List<MyMessageVO> list = myMessageDao.listMyMessage(pageParameter);
			for (MyMessageVO myMessageVO : list) {
				myMessageVO.setUserId(pageParameter.getParameter().getUserId());
				myMessageVO.setUserType(pageParameter.getParameter().getUserType());
				myMessageVO = setAvatar(myMessageVO);
				Message message = messageService.get(myMessageVO.getMsgId());
				if (ObjectUtil.isNull(message)) {
					throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE,
							CheckedExceptionResult.NULL_PARAM, "没有获取到内容！");
				}
				myMessageVO.setContent(message.getContent());
			}
			pageResult.setRows(list);
		}
		pageResult.setTotal(total);
		return pageResult;
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
		if (myMessageVO.getUserId().equals(myMessageVO.getReceiverId())
				&& myMessageVO.getUserType().equals(myMessageVO.getReceiverType())) {// 当接收者是当前用户时，给发送者赋值
			switch (myMessageVO.getSenderType()) {
			case 0:
				myMessageVO.setSenderName("系统");
				break;
			case 1:
				PmphUser pmphUser = pmphUserService.get(myMessageVO.getSenderId());
				myMessageVO.setSenderAvatar(pmphUser.getAvatar());
				myMessageVO.setSenderName(pmphUser.getRealname());
				break;

			case 2:
				WriterUser writerUser = writerUserService.get(myMessageVO.getSenderId());
				myMessageVO.setSenderAvatar(writerUser.getAvatar());
				myMessageVO.setSenderName(writerUser.getRealname());
				break;

			case 3:
				// 现在没有机构用户
				break;

			default:
				throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
						"发送者类型不正确！");
			}
		}
		if (myMessageVO.getUserId().equals(myMessageVO.getSenderId())
				&& myMessageVO.getUserType().equals(myMessageVO.getSenderType())) {// 当发送者是当前用户时，给接收者赋值
			switch (myMessageVO.getSenderType()) {
			case 0:
				myMessageVO.setSenderName("系统");
				break;
			case 1:
				PmphUser pmphUser = pmphUserService.get(myMessageVO.getSenderId());
				myMessageVO.setReceiverAvatar(pmphUser.getAvatar());
				myMessageVO.setReceiverName(pmphUser.getRealname());
				break;

			case 2:
				WriterUser writerUser = writerUserService.get(myMessageVO.getSenderId());
				myMessageVO.setReceiverAvatar(writerUser.getAvatar());
				myMessageVO.setReceiverName(writerUser.getRealname());
				break;

			case 3:
				// 现在没有机构用户
				break;

			default:
				throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
						"发送者类型不正确！");
			}
		}

		return myMessageVO;
	}

	@Override
	public List<MyMessageVO> updateMyMessage(Long senderId, Integer senderType, Long userId, Long userType)
			throws CheckedServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
