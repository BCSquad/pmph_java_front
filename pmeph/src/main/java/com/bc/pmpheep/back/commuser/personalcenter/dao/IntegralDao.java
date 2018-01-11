package com.bc.pmpheep.back.commuser.personalcenter.dao;

import java.util.List;
import java.util.Map;

/**
 * 积分
 * @ClassName IntegralDao
 * @Description TODO
 * @author sunzhuoqun
 * @date 2017-12-28
 */

public interface IntegralDao {

	/**
	 * 
	 * @Title: findPointList
	 * @Description: 积分记录
	 * @param @param paraMap
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> findPointList(Map<String, Object> paraMap);
	
	/**
	 * 
	 * @Title: findPointByMonth
	 * @Description: 三个月内积分记录
	 * @param @param paraMap
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> findPointByMonth(Map<String, Object> paraMap);
	
	/**
	 * 
	 * @Title: totalPoint
	 * @Description: 查询总积分
	 * @param @param paraMap
	 * @param @return
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	Map<String, Object> findTotalPoint(Map<String, Object> paraMap);
	
}
