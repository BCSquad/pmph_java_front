package com.bc.pmpheep.back.commuser.writerpoint.service;

import com.bc.pmpheep.back.commuser.writerpoint.bean.WriterPoint;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 用户积分业务层
 * @author mr
 *	2017-12-28
 */
public interface WriterPointService {
	
	/**
	 * 修改用户积分
	 * @param writerPoint
	 * @return
	 * @throws CheckedServiceException
	 */
    Integer updateWriterPoint(WriterPoint writerPoint)throws CheckedServiceException;
    
    /**
     * 通过用户id查询积分
     * @param userId
     * @return
     * @throws CheckedServiceException
     */
	WriterPoint getWriterPointByUserId(Long userId)throws CheckedServiceException;
}
