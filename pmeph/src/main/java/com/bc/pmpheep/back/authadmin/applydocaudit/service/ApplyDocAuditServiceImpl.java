package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.applydocaudit.bean.Material;
import com.bc.pmpheep.back.authadmin.applydocaudit.dao.ApplyDocAuditDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

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
	public List<Map<String,Object>> materialDeclareAuditListQuery(PageParameter<Map<String, Object>> pageParameter) throws CheckedServiceException{
		if (ObjectUtil.isNull(pageParameter.getParameter().get("org_id"))) {
			throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
					"机构id为空！");
		}
		List<Map<String,Object>> resultList = applyDocAuditDao.materialDeclareAuditListQuery(pageParameter);
		return resultList;
	}

	@Override
	public int materialDeclareAuditListQueryCount(PageParameter<Map<String, Object>> pageParameter) throws CheckedServiceException{
		if (ObjectUtil.isNull(pageParameter.getParameter().get("org_id"))) {
			throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
					"机构id为空！");
		}
		Integer count =applyDocAuditDao.materialDeclareAuditListQueryCount(pageParameter);
		Integer maxPageNum = (int) Math.ceil(1.0*count/pageParameter.getPageSize());
		return maxPageNum;
	}

}
