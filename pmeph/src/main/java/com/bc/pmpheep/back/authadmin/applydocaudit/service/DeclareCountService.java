package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import java.util.List;
import java.util.Map;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 
 * <pre>
 * 功能描述：DeclareCountService 接口
 * 使用示范：
 */

public interface DeclareCountService {
   
    /**
     * 
     * <pre>
     * 功能描述：申报统计（机构用户）查询显示
     * 使用示范：
     * @param paraMap 
     *
     * @param pageParameter 带有分页参数和查询条件参数
     * @throws CheckedServiceException
     * </pre>
     */
	List<Map<String,Object>> findDeclareCount(Map<String, Object> paraMap);

	
	 /**
     * 最终结果名单列表
     * <pre>
     * 功能描述：申报统计（机构用户）查询显示
     * 使用示范：
     *
     * @param pageParameter 带有分页参数和查询条件参数
     * @throws CheckedServiceException
     * </pre>
     */
	List<Map<String,Object>> findNameList(Map<String, Object> paraMap);
	
	//加载更多
	List<Map<String, Object>> selectNoticeMessage(Map<String, Object> paraMap);
   //查看全部
	List<Map<String, Object>> selectAll();
}
