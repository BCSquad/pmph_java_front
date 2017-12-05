package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.applydocaudit.dao.DataAuditDao;
import com.bc.pmpheep.back.plugin.PageParameter;

/**
 * 
 * @ClassName: DataAuditServiceImpl
 * @Description: TODO
 * @author SunZhuoQun
 * @date 2017-12-5 上午9:29:34
 * 
 */
@Service("com.bc.pmpheep.back.authadmin.applydocaudit.service.DataAuditServiceImpl")
public class DataAuditServiceImpl implements DataAuditService {
	@Autowired
	DataAuditDao dataAuditDao;

	/**
	 * 查询列表
	 */
	@Override
	public List<Map<String, Object>> findDataAudit(
			PageParameter<Map<String, Object>> pageParameter) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("startNum", pageParameter.getStart());
		paraMap.put("pageSize", pageParameter.getPageSize());
		paraMap.put("material_id",
				pageParameter.getParameter().get("material_id"));
		paraMap.put("queryName", pageParameter.getParameter().get("queryName"));
		List<Map<String, Object>> resultList = dataAuditDao
				.findDataAudit(paraMap);
		return resultList;
	}

	/**
	 * 查询条数
	 */
	@Override
	public int findDataAuditCount(
			PageParameter<Map<String, Object>> pageParameter) {
		Map<String, Object> paraMap = pageParameter.getParameter();
		paraMap.put("queryName", pageParameter.getParameter().get("queryName"));
		paraMap.put("material_id",
				pageParameter.getParameter().get("material_id"));
		Integer count = dataAuditDao.findDataAuditCount(paraMap);
		Integer maxPageNum = (int) Math.ceil(1.0 * count
				/ pageParameter.getPageSize());
		return maxPageNum;
	}

}
