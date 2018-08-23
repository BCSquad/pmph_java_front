package com.bc.pmpheep.back.commuser.materialdec.service;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

import java.util.List;
import java.util.Map;

public interface ExpertationService {


	/**
	 * 查询专家信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerson(Map<String,Object> map);

	/**
	 * 新增 专家申报信息
	 */
	public Map<String,Object> insertJcsbxx(Map<String, Object> perMap, List<Map<String, Object>> stuList, List<Map<String, Object>> workList,
				   List<Map<String, Object>> zjxsList, List<Map<String, Object>> zjkzqkList,List<Map<String, Object>> monographList,
				   List<Map<String, Object>> pmphList,List<Map<String,Object>> subjectList,List<Map<String,Object>> contentList,List<Map<String,Object>> editorList,
                   List<Map<String,Object>> wzfbqkList,List<Map<String,Object>> bzyhjqkList);
	/**
	 * 修改 专家申报信息
	 */
	public Map<String,Object> updateJcsbxx(Map<String, Object> perMap, List<Map<String, Object>> stuList, List<Map<String, Object>> workList,
				   List<Map<String, Object>> zjxsList, List<Map<String, Object>> zjkzqkList,List<Map<String, Object>> monographList,
				   List<Map<String, Object>> pmphList,List<Map<String,Object>> subjectList,List<Map<String,Object>> contentList,List<Map<String,Object>> editorList,String declaration_id
	);

    /**
     * 学科分类
     */
    PageResult<Map<String, Object>> selectSubjectList(PageParameter<Map<String, Object>> pageParameter);
    /**
     * 内容分类
     */
    PageResult<Map<String, Object>> selectContentList(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 根据作家艾迪查询申报（首页三大新产品申报）
	 * @param user_id
	 * @return
	 */
	List<Map<String,Object>> queryExpertation(String user_id);

	/**
	 * 通过教材ID查出教材
	 * @param expert_type
	 * @return
	 */
	public Map<String,Object> queryMaterialbyId(String expert_type);

    public List<Map<String,Object>> selectSubject(Map<String, Object> map);
    public List<Map<String,Object>> selectContent(Map<String, Object> map);
	/**
	 * 根据用户ID和类型查询申请信息
	 * @param user_id 用户ID
	 * @param expert_type 申请类型
	 * @return
	 */
	Map<String,Object> queryExpertationDetail(String user_id,String expert_type);
	/**
	 * 查询学习经历
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryStu(Map<String,Object> map);
	/**
	 * 查询工作经历
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryWork(Map<String,Object> map);
	/**
	 * 作家学术
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryZjxs(Map<String,Object> map);
	/**
	 * 作家主编国家级规划教材情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryGjghjc(Map<String,Object> map);
	/**
	 * 作家扩展项填报表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryZjkzbb(Map<String,Object> map);
	/**
	 * 主编学术专著情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryMonograph(Map<String,Object> map);
	/**
	 * 主编学术专著情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryEditor(Map<String,Object> map);
	/**
	 * 查询人卫社教材编写情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> rwsjcList(Map<String,Object> map);

	/**
	 * 通过产品ID查出扩展信息
	 * @param material_id
	 * @return
	 */
	public List<Map<String,Object>> queryZjkzxxById(String material_id);

	/**查看临床消息
	 * @param expert_type 临床类型
	 * @param expert_type 申请类型
	 * @return
	 */
	Map<String,Object> queryProduct(String expert_type);

	/**
	 * 用于在机构管理员审核时修改审核状态，保存退回原因等
	 * @param paramMap
	 * @return
	 */
	public int updateExpertationPass(Map<String, Object> paramMap);

	/**
	 * 根据id查询机构
	 * @param string
	 * @return
	 */
	public Map<String, Object> queryOrgById(String id);

	
	/**
	 * 机构选择
	 */
	PageResult<Map<String, Object>> selectOrgList(PageParameter<Map<String, Object>> pageParameter);
}
