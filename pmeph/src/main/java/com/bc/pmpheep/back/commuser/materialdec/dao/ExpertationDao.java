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
	public List<Map<String,Object>> selectSubject(Map<String, Object> map);
	public int insertSubject(Map<String,Object> map);
	public int delSubject(String experation_id);

	/**
	 * 内容分类关联表
	 */
	public List<Map<String,Object>> selectContent(Map<String, Object> map);
	public int insertContent(Map<String,Object> map);
	public int delContent(String experation_id);

	/**
	 * 根据用户ID和类型查询申请信息
	 * @param user_id 用户ID
	 * @param expert_type 申请类型
	 * @return
	 */
	Map<String,Object> queryExpertationDetail(@Param("user_id") String user_id,@Param("expert_type") String expert_type);
    //文章发表情况 insertWzfbqk
	public int insertWzfbqk(Map<String, Object> map);
	//查询学习经历
	public List<Map<String,Object>> queryStu(Map<String, Object> map);
	public int insertStu(Map<String, Object> map);
	public int DelStu(Map<String, Object> map);
	//查询工作经历
	public List<Map<String,Object>> queryWork(Map<String, Object> map);
	public int insertWork(Map<String, Object> map);
	public int DelWork(Map<String, Object> map);
	//作家学术
	public List<Map<String,Object>> queryZjxs(Map<String, Object> map);
	public int insertZjxs(Map<String, Object> map);
	public int DelZjxs(Map<String, Object> map);
	//作家主编国家级规划教材情况
	public List<Map<String,Object>> queryGjghjc(Map<String, Object> map);
	public int insertGjghjc(Map<String, Object> map);
	public int DelGjghjc(Map<String, Object> map);
	//查询人卫社教材编写情况
	public List<Map<String,Object>> queryRwsjc(Map<String, Object> map);
	public int insertRwsjc(Map<String, Object> map);
	public int DelRwsjc(Map<String, Object> map);
	//作家扩展项填报表
	public List<Map<String,Object>> queryZjkzbb(Map<String, Object> map);
	public int insertZjkzbb(Map<String, Object> map);
	public int delZjkzbb(Map<String, Object> map);

	//主编或参编图书情况
	public List<Map<String,Object>> queryEditor(Map<String, Object> map);
	public int insertEditor(Map<String, Object> map);
	public int delEditor(Map<String, Object> map);

	//文章发表情况（须第一作者，与本专业相关）
	public List<Map<String,Object>> queryWzfbqk(Map<String, Object> map);


	//本专业获奖情况
	public List<Map<String,Object>> queryBzyhjqk(Map<String, Object> map);


	//主编学术专著情况
	public List<Map<String,Object>> queryMonograph(Map<String, Object> map);
	public int insertMonograph(Map<String, Object> map);
	public int DelMonograph(Map<String, Object> map);

	//查看临床信息
	public Map<String,Object> queryProduct(String product_type);

	public List<Map<String,Object>> queryZjkzxxById(String material_id);

	/**
	 * 更新临床申报（仅审核）
	 * @param paramMap
	 * @return
	 */
	public int updateExpertationAudit(Map<String, Object> paramMap);

	public Map<String, Object> queryOrgById(String id);

	public List<Map<String, Object>> queryOrgList(
			PageParameter<Map<String, Object>> pageParameter);

	public int queryOrgCount(PageParameter<Map<String, Object>> pageParameter);
}
