package com.bc.pmpheep.back.commuser.survey.dao;

import java.util.List;
import java.util.Map;

public interface SurveyDao {
	
	//调查列表
	List<Map<String, Object>> surveyList();
	
	//查询的单个调查对应的问题
	List<Map<String, Object>> getSurvey(long surveyId);
	
	//查询问题对应的选项
	List<Map<String, Object>> getOptions(Long questionId);

}
