package com.bc.pmpheep.back.commuser.personalcenter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterPointRuleVO;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.back.util.CollectionUtil;
import com.bc.pmpheep.back.util.PageParameterUitl;
import com.bc.pmpheep.service.exception.CheckedServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.personalcenter.dao.IntegralDao;



@Service("com.bc.pmpheep.back.commuser.personalcenter.service.IntegralServiceImpl")
public class IntegralServiceImpl implements IntegralService {

	@Autowired
	private IntegralDao integralDao;
	
	
	/**
	 * 积分记录  加载更多
	 */
	@Override
	public List<Map<String, Object>> findPointList(Map<String, Object> paraMap) {
		List<Map<String, Object>> list = integralDao.findPointList(paraMap);
		
		return list;
	}
	
	/**
	 * 筛选一周、三个月内、一年积分记录
	 */
	@Override
	public List<Map<String, Object>> findPointByMonth(Map<String, Object> paraMap) {
		List<Map<String, Object>> list = integralDao.findPointByMonth(paraMap);
		
		return list;
	}

	/**
	 * 总积分
	 */
	@Override
	public Map<String, Object> findTotalPoint(Map<String, Object> paraMap) {
		Map<String, Object> map = integralDao.findTotalPoint(paraMap);
		return map;
	}

	/**
	 * 总积分
	 */
	@Override
	public Map<String, Object> findPointExchange(Map<String, Object> paraMap) {
		Map<String, Object> map = integralDao.findPointExchange(paraMap);
		return map;
	}

	@Override
	public List<WriterPointRuleVO> getlistWriterPointRulePoint()
			throws CheckedServiceException {
		return integralDao.listWriterPointRulePoint();
	}

	@Override
	public int PointChange(Map<String,Object> params) throws CheckedServiceException {

		return integralDao.updateWriterPoint(params);
	}

	@Override
	public WriterPointRuleVO findWrterPointRulePointByRuleCode(String rule_code) {
		return integralDao.findWrterPointRulePointByRuleCode(rule_code);


	}

	@Override
	public Map<String, Object> findWriterPointByid(Long id) {
		return integralDao.findWriterPointByid(id);
	}

	@Override
	public int addPointlog(Map<String, Object> params) {
		return integralDao.addPointlog(params);
	}

}
