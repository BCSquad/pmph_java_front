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
                                           List<Map<String, Object>> steaList, List<Map<String, Object>> zjxsList, List<Map<String, Object>> jcbjList, List<Map<String, Object>> gjkcjsList, List<Map<String, Object>> gjghjcList,
                                           List<Map<String, Object>> jcbxList, List<Map<String, Object>> zjkyList, List<Map<String, Object>> zjkzqkList, Map<String, Object> achievementMap, List<Map<String, Object>> monographList,
                                           List<Map<String, Object>> publishList, List<Map<String, Object>> sciList, List<Map<String, Object>> clinicalList, List<Map<String, Object>> acadeList,
                                           List<Map<String, Object>> pmphList, Map<String, Object> digitalMap, Map<String, Object> intentionlMap,
										   List<Map<String,Object>> subjectList,List<Map<String,Object>> contentList);
	/**
	 * 修改 专家申报信息
	 */
	public Map<String,Object> updateJcsbxx(Map<String, Object> perMap, List<Map<String, Object>> stuList, List<Map<String, Object>> workList, String declaration_id,
                                           List<Map<String, Object>> steaList, List<Map<String, Object>> zjxsList, List<Map<String, Object>> jcbjList, List<Map<String, Object>> gjkcjsList, List<Map<String, Object>> gjghjcList,
                                           List<Map<String, Object>> jcbxList, List<Map<String, Object>> zjkyList, List<Map<String, Object>> zjkzqkList, Map<String, Object> achievementMap, List<Map<String, Object>> monographList,
                                           List<Map<String, Object>> publishList, List<Map<String, Object>> sciList, List<Map<String, Object>> clinicalList, List<Map<String, Object>> acadeList,
                                           List<Map<String, Object>> pmphList, Map<String, Object> digitalMap, Map<String, Object> intentionlMap,
										   List<Map<String,Object>> subjectList,List<Map<String,Object>> contentList);

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
	 * @param material_id
	 * @return
	 */
	public Map<String,Object> queryMaterialbyId(String material_id);

    public List<Map<String,Object>> selectSubject(List<Map<String, Object>> map);
    public List<Map<String,Object>> selectContent(List<Map<String, Object>> map);
	/**
	 * 根据用户ID和类型查询申请信息
	 * @param user_id 用户ID
	 * @param expert_type 申请类型
	 * @return
	 */
	Map<String,Object> queryExpertationDetail(String user_id,String expert_type);
}
