package com.bc.pmpheep.back.authadmin.applydocaudit.dao;

import java.util.List;
import java.util.Map;
/**
 * 资料申报审核（机构用户）dao层
 * @author Administrator
 *
 */
public interface ApplyDocAuditDao {
	/**
	 * 查询教材列表
	 * @param paraMap
	 * @return
	 */
	public List<Map<String,Object>> materialDeclareAuditListQuery(Map<String,Object> paraMap);
	/**
	 * 查询教材总数
	 * @param map
	 * @return
	 */
	public Integer materialDeclareAuditListQueryCount(Map<String, Object> map);
	
	
	
}
