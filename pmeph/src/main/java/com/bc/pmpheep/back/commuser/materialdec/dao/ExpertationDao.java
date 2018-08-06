package com.bc.pmpheep.back.commuser.materialdec.dao;

import com.bc.pmpheep.back.plugin.PageParameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExpertationDao {

	//通过教材ID查出教材
	public Map<String,Object> queryProductbyId(String product_id);

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

	/**
	 * 根据作家艾迪查询申报（首页三大新产品申报）
	 * @param user_id
	 * @return
	 */
	List<Map<String,Object>> queryExpertation(@Param("user_id") String user_id);


	/**
	 * 学科分类关联表
	 */
	public int insertSubject(Map<String,Object> map);
	public int delSubject(String experation_id);

	/**
	 * 内容分类关联表
	 */
	public int insertContent(Map<String,Object> map);
	public int delContent(String experation_id);
}
