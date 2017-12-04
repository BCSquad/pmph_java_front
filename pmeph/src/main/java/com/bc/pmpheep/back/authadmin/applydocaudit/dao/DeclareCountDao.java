package com.bc.pmpheep.back.authadmin.applydocaudit.dao;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;

public interface DeclareCountDao {

	  /**
     * 
     * <pre>
     * 功能描述：申报统计（机构用户）
     * 使用示范：
	 * @param paraMap 
     *
     * @param sessionId 
     * @return 
     * </pre>
     */
    
	//查询
	List<Map<String,Object>> findDeclareCount(Map<String, Object> paraMap);
	
	 /**
     * 最终结果名单列表
     * <pre>
     * 功能描述：申报统计（机构用户）
     * 使用示范： 
	 * @param paraMap 
     *
     * @param sessionId 
     * @return 
     * </pre>
     */
    
	List<Map<String,Object>> findNameList(Map<String, Object> paraMap);
	
  	//查看更多
  	List<Map<String, Object>> selectNoticeMessage(Map<String, Object> paraMap);
  	
  	//查看全部
  	List<Map<String, Object>> selectAll();
  	
  	

}
