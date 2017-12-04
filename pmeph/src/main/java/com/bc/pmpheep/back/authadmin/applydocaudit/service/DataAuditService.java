package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import java.util.List;
import java.util.Map;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 
 * <pre>
 * 功能描述：CmsContentService 接口
 * 使用示范：
 * 
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @modify (最后修改时间) 
 * @修改人 ：
 * </pre>
 */
public interface DataAuditService {
   
	/**
	 * 查询列表
	 * @param pageParameter
	 * @return
	 */
	List<Map<String, Object>> findDataAudit(PageParameter<Map<String, Object>> pageParameter);
	
	/**
	 * 查询总数
	 * @param pageParameter
	 * @return
	 */
	int findDataAuditCount(PageParameter<Map<String, Object>> pageParameter);

   
}
