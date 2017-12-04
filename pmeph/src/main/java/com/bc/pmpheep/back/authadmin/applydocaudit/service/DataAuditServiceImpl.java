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
 * <pre>
 * 功能描述：资料审核 接口实现
 * 使用示范：
 * 
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @modify (最后修改时间) 
 * @审核人 ：
 * </pre>
 */
@Service("com.bc.pmpheep.back.authadmin.applydocaudit.service.DataAuditServiceImpl")
public class DataAuditServiceImpl implements DataAuditService {
    @Autowired
    DataAuditDao       dataAuditDao;
   
	
	@Override
	public List<Map<String, Object>> findDataAudit(PageParameter<Map<String, Object>> pageParameter) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("startNum", pageParameter.getStart());
		paraMap.put("pageSize", pageParameter.getPageSize());
		paraMap.put("material_id", pageParameter.getParameter().get("material_id"));
		paraMap.put("queryName", pageParameter.getParameter().get("queryName"));
		List<Map<String,Object>> resultList = dataAuditDao.findDataAudit(paraMap);
		return resultList;
	}
  	//查询条数
	@Override
	public int findDataAuditCount(PageParameter<Map<String, Object>> pageParameter) {
		Map<String, Object> paraMap = pageParameter.getParameter();
		paraMap.put("queryName", pageParameter.getParameter().get("queryName"));
		paraMap.put("material_id", pageParameter.getParameter().get("material_id"));
		Integer count =dataAuditDao.findDataAuditCount(paraMap);
		Integer maxPageNum = (int) Math.ceil(1.0*count/pageParameter.getPageSize());
		return maxPageNum;
	}

  
}
