package com.bc.pmpheep.back.commuser.materialdec.service;

import java.util.List;
import java.util.Map;

public interface MaterialDetailService {
	
	/**
	 * 通过教材ID查出教材
	 * @param material_id
	 * @return
	 */
	public Map<String,Object> queryMaterialbyId(String material_id);
	/**
	 * 通过教材ID查出所有教材下的书籍
	 * @param material_id
	 * @return
	 */
	public List<Map<String,Object>> queryBookById(String material_id);
	/**
	 * 通过教材ID查出相关的机构信息
	 * @param material_id
	 * @return
	 */
	public List<Map<String,Object>> queryOrgById(String material_id);
	/**
	 * 通过教材ID查出扩展信息
	 * @param material_id
	 * @return
	 */
	public List<Map<String,Object>> queryZjkzxxById(String material_id);
	/**
	 * 查询专家信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerson(Map<String,Object> map);
	/**
	 * 插入专家个人信息
	 * @param map
	 * @return
	 */
	public int insertPerson(Map<String,Object> map);
	/**
	 * 作家申报职位-图书选择信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryTsxz(Map<String,Object> map);
	/**
	 * 作家申报职位-添加
	 * @param map
	 * @return
	 */
	public int insertTsxz(Map<String,Object> map);
	
	/**
	 * 图书申报职位暂存
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryTssbZc(Map<String,Object> map);
	/**
	 * 图书申报职位暂存插入
	 * @param map
	 * @return
	 */
	public int insertTssbZc(Map<String,Object> map);
	
	/**
	 * 查询学习经历
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryStu(Map<String,Object> map);
	public int insertStu(Map<String,Object> map);
	/**
	 * 查询工作经历
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryWork(Map<String,Object> map);
	public int insertWork(Map<String,Object> map);
	/**
	 * 作家学术
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryZjxs(Map<String,Object> map);
	public int insertZjxs(Map<String,Object> map);
	/**
	 * 查询教学经历
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryStea(Map<String,Object> map);
	public int insertStea(Map<String,Object> map);
	/**
	 * 查询上版教材编辑
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryJcbj(Map<String,Object> map);
	public int insertJcbj(Map<String,Object> map);
	/**
	 * 查询国家精品课程建设
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryGjkcjs(Map<String,Object> map);
	public int insertGjkcjs(Map<String,Object> map);
	/**
	 * 作家主编国家级规划教材情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryGjghjc(Map<String,Object> map);
	public int insertGjghjc(Map<String,Object> map);
	/**
	 * 查询教材编写情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryJcbx(Map<String,Object> map);
	public int insertJcbx(Map<String,Object> map);
	/**
	 * 作家科研情况表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryZjkyqk(Map<String,Object> map);
	public int insertZjkyqk(Map<String,Object> map);
	/**
	 * 作家扩展项填报表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryZjkzbb(Map<String,Object> map);
	public int insertZjZjkzbb(Map<String,Object> map);
	
	/**
	 * 清除所有关联表
	 */
	public int delGlxx(Map<String,Object> map);
}
