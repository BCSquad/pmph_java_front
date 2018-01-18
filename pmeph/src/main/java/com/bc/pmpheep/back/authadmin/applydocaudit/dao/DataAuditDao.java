package com.bc.pmpheep.back.authadmin.applydocaudit.dao;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.back.plugin.PageParameter;

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

	/**
	 * 
	 * @Title: findTitleName
	 * @Description: 查询列表
	 * @param @param paraMap
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	String findTitleName(Map<String, Object> paraMap);

	/**
	 * 导出excle表
	 * @Title: findDataAuditExcel
	 * @Description: TODO
	 * @param @param paraMap
	 * @param @return 设定文件
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> findDataAuditExcel(Map<String, Object> paraMap);
	
	//查询专家信息
	public List<Map<String,Object>> queryPerson(Map<String,Object> map);
	
	//图书申报职位
	public List<Map<String,Object>> queryTsxz(Map<String,Object> map);
	//查询学习经历
	public List<Map<String,Object>> queryStu(Map<String,Object> map);
	//查询工作经历
	public List<Map<String,Object>> queryWork(Map<String,Object> map);
	//查询教学经历
	public List<Map<String,Object>> queryStea(Map<String,Object> map);
	//作家学术
	public List<Map<String,Object>> queryZjxs(Map<String,Object> map);
	//查询上版教材编辑
	public List<Map<String,Object>> queryJcbj(Map<String,Object> map);
	//查询精品课程建设
	public List<Map<String,Object>> queryGjkcjs(Map<String,Object> map);
	//作家主编国家级规划教材情况
	public List<Map<String,Object>> queryGjghjc(Map<String,Object> map);
	//查询教材编写情况
	public List<Map<String,Object>> queryJcbx(Map<String,Object> map);
	//作家科研情况表
	public List<Map<String,Object>> queryZjkyqk(Map<String,Object> map);
	//作家扩展项填报表
	public List<Map<String,Object>> queryZjkzbb(Map<String,Object> map);
	//申报审核
	public int updateDeclaration(Map<String,Object> map);
	//通过教材ID查出教材
	public Map<String,Object> queryMaterialbyId(String material_id);

}
