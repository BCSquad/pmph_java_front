package com.bc.pmpheep.back.authadmin.applydocaudit.dao;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;
/**
 * 资料申报审核（机构用户）dao层
 * @author Administrator
 *
 */
public interface ApplyDocAuditDao {
	/**
	 * 查询教材列表
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String,Object>> materialDeclareAuditListQuery(PageParameter<Map<String, Object>> pageParameter);
	/**
	 * 查询教材总数
	 * @param map
	 * @return
	 */
	public Integer materialDeclareAuditListQueryCount(PageParameter<Map<String, Object>> pageParameter);
	
	
	
}
