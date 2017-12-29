package com.bc.pmpheep.back.commuser.personalcenter.service;

import java.util.List;
import java.util.Map;

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
	 * 三个月内积分记录
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
	

}
