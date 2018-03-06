package com.bc.pmpheep.back.commuser.writerpoint.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.writerpoint.bean.WriterPointLog;
import com.bc.pmpheep.back.commuser.writerpoint.dao.WriterPointLogDao;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 积分日志记录接口实现
 * @author mr
 *
 */
@Service("com.bc.pmpheep.back.commuser.writerpoint.service.WriterPointLogServiceImpl")
public class WriterPointLogServiceImpl implements WriterPointLogService{
	
	@Autowired
	WriterPointLogDao writerPointLogDao;

	@Override
	public Integer add(WriterPointLog writerPointLog) throws CheckedServiceException {
		return writerPointLogDao.addWriterPointLog(writerPointLog);
	}

	@Override
	public List<WriterPointLog> getWriterPointLogByUserId(Long userId) throws CheckedServiceException {
		return writerPointLogDao.getWriterPointLogByUserId(userId);
	}

}
