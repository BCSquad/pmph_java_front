package com.bc.pmpheep.back.commuser.writerpoint.service;
import java.util.List;

import com.bc.pmpheep.back.commuser.writerpoint.bean.WriterPointLog;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 积分日志记录业务层
 * @author mr
 *
 */

public interface WriterPointLogService {
	
	/**
	 * 添加积分记录
	 * @param writerPointLog
	 * @return
	 * @throws CheckedServiceException
	 */
	Integer add(WriterPointLog writerPointLog)throws CheckedServiceException;
	
	
	/**
	 *  通过用户id查询积分记录
	 * @param writerId
	 * @return
	 * @throws CheckedServiceException
	 */
	List<WriterPointLog> getWriterPointLogByUserId(Long userId) throws CheckedServiceException;
}
