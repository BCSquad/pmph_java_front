package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.applydocaudit.bean.Material;
import com.bc.pmpheep.back.authadmin.applydocaudit.dao.ApplyDocAuditDao;
import com.bc.pmpheep.back.plugin.PageParameter;

/**
 * 资料申报审核（机构用户）service层实现类
 * @author liudi
 *
 */
@Service("com.bc.pmpheep.back.authadmin.applydocaudit.service.ApplyDocAuditServiceImpl")
public class ApplyDocAuditServiceImpl implements ApplyDocAuditService {

	@Autowired
	ApplyDocAuditDao applyDocAuditDao;
	
	@Override
	public List<Map<String,Object>> materialDeclareAuditListQuery(PageParameter<Map<String, Object>> pageParameter) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("startNum", pageParameter.getStart());
		paraMap.put("pageSize", pageParameter.getPageSize());
		paraMap.put("queryCon", pageParameter.getParameter().get("queryCon"));
		paraMap.put("queryStatus", pageParameter.getParameter().get("queryStatus"));
		List<Map<String,Object>> resultList = applyDocAuditDao.materialDeclareAuditListQuery(paraMap);
		return resultList;
	}

	@Override
	public int materialDeclareAuditListQueryCount(PageParameter<Map<String, Object>> pageParameter) {
		Map<String, Object> paraMap = pageParameter.getParameter();
		paraMap.put("queryCon", pageParameter.getParameter().get("queryCon"));
		paraMap.put("queryStatus", pageParameter.getParameter().get("queryStatus"));
		Integer count =applyDocAuditDao.materialDeclareAuditListQueryCount(paraMap);
		Integer maxPageNum = (int) Math.ceil(1.0*count/pageParameter.getPageSize());
		return maxPageNum;
	}

}
