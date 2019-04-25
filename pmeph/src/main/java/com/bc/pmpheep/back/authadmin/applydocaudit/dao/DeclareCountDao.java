package com.bc.pmpheep.back.authadmin.applydocaudit.dao;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;

/**
 * 
 * @ClassName: DeclareCountDao
 * @Description: 申报统计（机构用户） 接口
 * @author SunZhuoQun
 * @date 2017-12-5 上午9:42:25
 * 
 */
public interface DeclareCountDao {
	/**
	 * 
	 * @Title: findDeclareCount
	 * @Description: 申报统计（机构用户） 查询
	 * @param @param paraMap
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> findDeclareCount(Map<String, Object> paraMap);
	List<Map<String, Object>> findDeclareCount2(Map<String, Object> paraMap);


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
	 * @Title: selectAll
	 * @Description: 查看全部
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> selectAll(Map<String, Object> paraMap);
	List<Map<String, Object>> selectAll2(Map<String, Object> paraMap);

	/**
	 * 最终结果名单  导出excel
	 * @Title: selectResults
	 * @Description: 查看全部  
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> selectResults(Map<String, Object> paraMap);
	


}
