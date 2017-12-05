package com.bc.pmpheep.back.authadmin.applydocaudit.dao;

import java.util.List;
import java.util.Map;

public interface DataAuditDao {

	/**
	 * 
	 * @Title: findDataAudit
	 * @Description: 查询列表
	 * @param @param paraMap
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> findDataAudit(Map<String, Object> paraMap);

	/**
	 * 
	 * @Title: findDataAuditCount
	 * @Description: 查询总数
	 * @param @param paraMap
	 * @param @return
	 * @return Integer 返回类型
	 * @throws
	 */
	Integer findDataAuditCount(Map<String, Object> paraMap);

}
