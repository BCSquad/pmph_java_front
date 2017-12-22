package com.bc.pmpheep.back.commuser.survey.service;

import java.util.List;
import java.util.Map;

public interface SurveyService {
	
	//调查列表
	List<Map<String, Object>> surveyList();
	
	//查询一个调查
	List<Map<String, Object>> getSurvey(long surveyId);
	
	//查询选项
	List<Map<String, Object>> getOptions(Long questionId);

}
