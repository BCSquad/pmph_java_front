package com.bc.pmpheep.back.commuser.materialdec.service;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

import java.util.List;
import java.util.Map;

public interface ExpertationService {


	/**
	 * 新增 专家申报信息
	 */
	public Map<String,Object> insertJcsbxx(Map<String, Object> perMap, List<Map<String, Object>> stuList, List<Map<String, Object>> workList,
                                           List<Map<String, Object>> steaList, List<Map<String, Object>> zjxsList, List<Map<String, Object>> jcbjList, List<Map<String, Object>> gjkcjsList, List<Map<String, Object>> gjghjcList,
                                           List<Map<String, Object>> jcbxList, List<Map<String, Object>> zjkyList, List<Map<String, Object>> zjkzqkList, Map<String, Object> achievementMap, List<Map<String, Object>> monographList,
                                           List<Map<String, Object>> publishList, List<Map<String, Object>> sciList, List<Map<String, Object>> clinicalList, List<Map<String, Object>> acadeList,
                                           List<Map<String, Object>> pmphList, Map<String, Object> digitalMap, Map<String, Object> intentionlMap);
	/**
	 * 修改 专家申报信息
	 */
	public Map<String,Object> updateJcsbxx(Map<String, Object> perMap, List<Map<String, Object>> stuList, List<Map<String, Object>> workList, String declaration_id,
                                           List<Map<String, Object>> steaList, List<Map<String, Object>> zjxsList, List<Map<String, Object>> jcbjList, List<Map<String, Object>> gjkcjsList, List<Map<String, Object>> gjghjcList,
                                           List<Map<String, Object>> jcbxList, List<Map<String, Object>> zjkyList, List<Map<String, Object>> zjkzqkList, Map<String, Object> achievementMap, List<Map<String, Object>> monographList,
                                           List<Map<String, Object>> publishList, List<Map<String, Object>> sciList, List<Map<String, Object>> clinicalList, List<Map<String, Object>> acadeList,
                                           List<Map<String, Object>> pmphList, Map<String, Object> digitalMap, Map<String, Object> intentionlMap);

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
}
