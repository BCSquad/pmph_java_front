package com.bc.pmpheep.back.authadmin.applydocaudit.dao;

import java.util.List;
import java.util.Map;


public interface DataAuditDao {
	
	
	/**
	 * 查询列表
	 * @param paraMap
	 * @return
	 */
	List<Map<String, Object>> findDataAudit(Map<String, Object> paraMap);
	
	/**
	 * 查询总数
	 * @param paraMap
	 * @return
	 */
	Integer findDataAuditCount(Map<String, Object> paraMap);
	
	
}
