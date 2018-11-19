package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import com.bc.pmpheep.back.authadmin.applydocaudit.dao.ExpertationListAuditDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.back.util.MD5;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.back.util.PageParameterUitl;
import com.bc.pmpheep.back.util.StringUtil;
import com.bc.pmpheep.general.bean.FileType;
import com.bc.pmpheep.general.service.FileService;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 功能描述：
 * @author (作者) sunzhuoqun
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期)
 * @modify (最后修改时间)
 * @修改人 ：
 * @审核人 ：
 *
 */
@Service("com.bc.pmpheep.back.authadmin.applydocaudit.service.ExpertationListAuditServiceImpl")
public class ExpertationListAuditServiceImpl implements ExpertationListAuditService {


	@Autowired
	private ExpertationListAuditDao expertationListAuditDao;

	@Override
	public PageResult<Map<String, Object>> getOrg(PageParameter<Map<String, Object>> pageParameter) {
		if(ObjectUtil.isNull(pageParameter.getParameter().get("orgId"))){
			throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
					CheckedExceptionResult.NULL_PARAM, "机构id不能为空");
		}


        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("start", pageParameter.getParameter().get("starta"));
        paraMap.put("pageSize", pageParameter.getPageSize());
        paraMap.put("queryName", pageParameter.getParameter().get("queryName"));
		paraMap.put("expertType", pageParameter.getParameter().get("expertType"));
		paraMap.put("xxsh", pageParameter.getParameter().get("xxsh"));
        paraMap.put("orgId", pageParameter.getParameter().get("orgId"));
		int total = expertationListAuditDao.getOrgTotal(paraMap);
		PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
		if (total > 0) {
			PageParameterUitl.CopyPageParameter(pageParameter, pageResult);
			List<Map<String, Object>> rows = expertationListAuditDao.getOrg(paraMap);
			pageResult.setRows(rows);
		}
		pageResult.setTotal(total);
		return pageResult;
	}

	@Override
	public List<Map<String, Object>> productIdList() {
		return this.expertationListAuditDao.productIdList();
	}

	@Override
	public void updPrintStatus(String id) {
		this.expertationListAuditDao.updPrintStatus(id);

	}
}
