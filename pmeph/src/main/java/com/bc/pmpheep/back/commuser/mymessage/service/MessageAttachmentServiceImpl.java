package com.bc.pmpheep.back.commuser.mymessage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.mymessage.bean.MessageAttachment;
import com.bc.pmpheep.back.commuser.mymessage.dao.MessageAttachmentDao;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.back.util.StringUtil;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

@Service("com.bc.pmpheep.back.commuser.mymessage.service.MessageAttachmentServiceImpl")
public class MessageAttachmentServiceImpl implements MessageAttachmentService {

	@Autowired
	MessageAttachmentDao messageAttachmentDao;

	@Override
	public List<MessageAttachment> getMessageAttachmentByMsgId(String msgId) throws CheckedServiceException {
		if (StringUtil.isEmpty(msgId)) {
			throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
					"用户消息ID为空 ");
		}
		List<MessageAttachment> list = messageAttachmentDao.getMessageAttachmentByMsgId(msgId);
		for (MessageAttachment messageAttachment : list) {
			messageAttachment.setAttachment(RouteUtil.MONGODB_FILE + messageAttachment.getAttachment());
		}
		return list;
	}
}
