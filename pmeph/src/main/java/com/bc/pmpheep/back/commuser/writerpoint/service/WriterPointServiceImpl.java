package com.bc.pmpheep.back.commuser.writerpoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.writerpoint.bean.WriterPoint;
import com.bc.pmpheep.back.commuser.writerpoint.dao.WriterPointDao;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 用户积分接口实现
 * @author mr
 *	2017-12-28
 */
@Service("com.bc.pmpheep.back.commuser.writerpoint.service.WriterPointServiceImpl")
public class WriterPointServiceImpl implements WriterPointService{
	
	@Autowired
	WriterPointDao writerPointDao;
	

	@Override
	public Integer updateWriterPoint(WriterPoint writerPoint) throws CheckedServiceException {
		return writerPointDao.updateWriterPoint(writerPoint);
	}


	@Override
	public WriterPoint getWriterPointByUserId(Long userId) throws CheckedServiceException {
		return writerPointDao.getWriterPointByUserId(userId);
	}


	@Override
	public WriterPoint addWriterPoint(WriterPoint writerPoint) throws CheckedServiceException {
		if(ObjectUtil.isNull(writerPoint)){
			throw new CheckedServiceException(CheckedExceptionBusiness.WRITER_POINT_MANAGEMENT,
					CheckedExceptionResult.NULL_PARAM, "参数为空");
		}
		if(ObjectUtil.isNull(writerPoint.getUserId())){
			throw new CheckedServiceException(CheckedExceptionBusiness.WRITER_POINT_MANAGEMENT,
					CheckedExceptionResult.NULL_PARAM, "用户id为空");
		}
		writerPointDao.addWriterPoint(writerPoint);
		return writerPoint;
	}

}
