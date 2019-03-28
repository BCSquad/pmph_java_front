package com.bc.pmpheep.back.commuser.personalcenter.service;

import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterPointActivityVO;
import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterPointRuleVO;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

import java.util.List;
import java.util.Map;

/**
 *积分  接口
 */


public interface IntegralService {

	
	/**
	 * 
	 * @Title: findPointList
	 * @Description: 积分记录  加载更多
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

	/**
	 *
	 * @Title: totalPoint
	 * @Description: 查询总积分
	 * @param @param paraMap
	 * @param @return
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	Map<String, Object> findPointExchange(Map<String, Object> paraMap);

	/**
	 * 分页查询积分兑换规则列表
	 * @param pageParameter
	 * @return
	 * @throws CheckedServiceException
	 */
	List<WriterPointRuleVO> getlistWriterPointRulePoint()
			throws CheckedServiceException;

	/**
	 * 分页查询积分兑换规则列表
	 * @param pageParameter
	 * @return
	 * @throws CheckedServiceException
	 */
	 int PointChange(Map<String,Object> params) throws CheckedServiceException ;

	WriterPointRuleVO findWrterPointRulePointByRuleCode(String rule_code);
	Map<String,Object> findWriterPointByid(Long id);

	int addPointlog(Map<String,Object> params);

	Map<String, Object> queryMallExchangeRule(Long id);
}
