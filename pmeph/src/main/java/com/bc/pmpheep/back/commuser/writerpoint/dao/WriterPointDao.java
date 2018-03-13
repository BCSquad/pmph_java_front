package com.bc.pmpheep.back.commuser.writerpoint.dao;

import com.bc.pmpheep.back.commuser.writerpoint.bean.WriterPoint;

/**
 * WriterPoint实体类的数据访问层接口
 * 
 * @author tyc
 * 
 */
public interface WriterPointDao {
	
	/**
	 * 修改
     * @author:tyc
     * @date:2017年12月28日上午09:12:53
	 * @param writerPoint
	 * @return
	 */
	Integer updateWriterPoint(WriterPoint writerPoint);

	
	/**
	 * 通过用户id查询积分
	 * @param userId
	 * @return
	 */
	WriterPoint getWriterPointByUserId(Long userId);

	
	/**
	 * 添加
	 * @param writerPoint
	 * @return
	 */
	Integer addWriterPoint(WriterPoint writerPoint);
	
}