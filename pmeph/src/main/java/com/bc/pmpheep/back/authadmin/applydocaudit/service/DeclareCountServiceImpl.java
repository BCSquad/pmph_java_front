package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.applydocaudit.dao.DeclareCountDao;

/**
 * 
 * @ClassName: DeclareCountServiceImpl
 * @Description: TODO
 * @author SunZhuoQun
 * @date 2017-12-5 上午9:40:33
 * 
 */

@Service("com.bc.pmpheep.back.authadmin.applydocaudit.service.DeclareCountServiceImpl")
public class DeclareCountServiceImpl implements DeclareCountService {
	@Autowired
	DeclareCountDao declareCountDao;

	/**
	 * 我校申报统计情况
	 */
	@Override
	public List<Map<String, Object>> findDeclareCount(
			Map<String, Object> paraMap) {
		List<Map<String, Object>> list = declareCountDao
				.findDeclareCount(paraMap);
		return list;
	}

	/**
	 * 最终结果名单列表
	 */
	@Override
	public List<Map<String, Object>> findNameList(Map<String, Object> paraMap) {
		List<Map<String, Object>> list = declareCountDao.findNameList(paraMap);
		return list;
	}

	/**
	 * 加载更多
	 */
	@Override
	public List<Map<String, Object>> selectNoticeMessage(
			Map<String, Object> paraMap) {
		List<Map<String, Object>> list = declareCountDao
				.selectNoticeMessage(paraMap);
		return list;
	}

	/**
	 * 查看全部
	 */
	@Override
	public List<Map<String, Object>> selectAll() {
		List<Map<String, Object>> list = declareCountDao.selectAll();
		return list;
	}
	
	/**
	 * 最终结果名单
	 * 查看全部
	 */
	@Override
	public List<Map<String, Object>> selectResults() {
		List<Map<String, Object>> list = declareCountDao.selectResults();
		return list;
	}
	

}
