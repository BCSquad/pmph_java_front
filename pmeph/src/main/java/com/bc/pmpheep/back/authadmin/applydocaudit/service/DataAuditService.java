package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import java.util.List;
import java.util.Map;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 
 * @ClassName: DataAuditService
 * @Description: 申报资料审核（机构用户）
 * @author SunZhuoQun
 * @date 2017-12-5 上午9:28:01
 * 
 */
public interface DataAuditService {

	/**
	 * 
	 * @Title: findDataAudit
	 * @Description: 查询列表
	 * @param @param pageParameter
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> findDataAudit(
			PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 
	 * @Title: findDataAuditCount
	 * @Description: 查询总数
	 * @param @param pageParameter
	 * @param @return
	 * @return int 返回类型
	 * @throws
	 */
	int findDataAuditCount(PageParameter<Map<String, Object>> pageParameter);

}
