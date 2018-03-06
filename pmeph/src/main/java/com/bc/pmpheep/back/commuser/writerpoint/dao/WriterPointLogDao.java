package com.bc.pmpheep.back.commuser.writerpoint.dao;

import java.util.List;

import com.bc.pmpheep.back.commuser.writerpoint.bean.WriterPointLog;

/**
 * WriterPointLog实体类的数据访问层接口
 * 
 * @author tyc
 * 
 */
public interface WriterPointLogDao {

	/**
	 * 添加
     * @author:tyc
     * @date:2017年12月28日上午09:19:01
	 * @param writerPointLog
	 * @return
	 */
	Integer addWriterPointLog(WriterPointLog writerPointLog);

	/**
	 * 通过用户id查询积分记录
	 * @param writerId
	 * @return
	 */
	List<WriterPointLog> getWriterPointLogByUserId(Long userId);
}
