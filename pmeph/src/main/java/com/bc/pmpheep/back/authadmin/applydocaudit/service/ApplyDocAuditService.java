package com.bc.pmpheep.back.authadmin.applydocaudit.service;

/**
 * 资料申报审核（机构用户）service层
 */
import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;
public interface ApplyDocAuditService {

	/**
	 * 查询教材列表
	 * @param pageParameter 
	 * @return
	 */
	public List<Map<String,Object>> materialDeclareAuditListQuery(PageParameter<Map<String, Object>> pageParameter);
	
	/**
	 * 查询教材总数量
	 * @param pageParameter
	 * @return
	 */
	public int materialDeclareAuditListQueryCount(PageParameter<Map<String, Object>> pageParameter);
}
