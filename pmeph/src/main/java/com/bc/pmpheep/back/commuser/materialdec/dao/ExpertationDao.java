package com.bc.pmpheep.back.commuser.materialdec.dao;

import com.bc.pmpheep.back.plugin.PageParameter;

import java.util.List;
import java.util.Map;

public interface ExpertationDao {

	/**
	 * 查询专家信息
	 */
	public List<Map<String,Object>> queryPerson(Map<String, Object> map);
	public int insertPerson(Map<String, Object> map);
	public int DelPerson(Map<String, Object> map);
	public int updatePerson(Map<String, Object> map);

	/**
	 * 学科分类
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String,Object>> querySubjectList(PageParameter<Map<String, Object>> pageParameter);
	public int querySubjectCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 内容分类
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String,Object>> queryContentList(PageParameter<Map<String, Object>> pageParameter);
	public int queryContentCount(PageParameter<Map<String, Object>> pageParameter);
}