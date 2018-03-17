package com.bc.pmpheep.back.authadmin.message.service;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;
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
	 */
	@Override
	public List<Map<String,Object>> findOrgUserAndWriterUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return orgMessageDao.findOrgUserAndWriterUser(map);
	}
	
	/**
	 * 批量插入消息
	 * @param userMessageList
	 */
	public void batchInsertMessage(List<UserMessage> userMessageList) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(userMessageList)) {
			throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
					CheckedExceptionResult.NULL_PARAM, "未查到有人申报改教材或改机构下未查到对应的作家");
		}
		orgMessageDao.batchInsertMessage(userMessageList);
	}

	/**
	 * 插入附件信息
	 * @param map
	 */
	public void insertAttachmentInfo(Map map) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(map)) {
			throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
					CheckedExceptionResult.NULL_PARAM, "文件未空");
		}
		 orgMessageDao.insertAttachmentInfo(map);
	}

	@Override
	public List<Map<String, Object>> findApplyId(String bookId) {
		return orgMessageDao.findApplyId(bookId);
	}


}
