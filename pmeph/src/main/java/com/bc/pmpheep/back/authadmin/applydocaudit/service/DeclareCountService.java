package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import java.util.List;
import java.util.Map;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 
 * @ClassName: DeclareCountService
 * @Description: 申报统计（机构用户）接口
 * @author SunZhuoQun
 * @date 2017-12-5 上午9:37:36
 * 
 */

public interface DeclareCountService {
	/**
	 * 
	 * @Title: findDeclareCount
	 * @Description: 我校申报统计情况
	 * @param @param paraMap
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> findDeclareCount(Map<String, Object> paraMap);

	/**
	 * 
	 * @Title: findNameList
	 * @Description: 最终结果名单列表
	 * @param @param paraMap
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> findNameList(Map<String, Object> paraMap);

	/**
	 * 
	 * @Title: selectNoticeMessage
	 * @Description: 加载更多
	 * @param @param paraMap
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> selectNoticeMessage(Map<String, Object> paraMap);

	/**
	 * 
	 * @Title: selectAll
	 * @Description: 查看全部
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> selectAll(Map<String, Object> paraMap);
	
	/**
	 * 最终结果名单
	 * @Title: selectResults
	 * @Description: 查看全部
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> selectResults(Map<String, Object> paraMap);
}
