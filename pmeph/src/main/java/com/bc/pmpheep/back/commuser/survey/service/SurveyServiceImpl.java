package com.bc.pmpheep.back.commuser.survey.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.survey.dao.SurveyDao;



@Service("com.bc.pmpheep.back.commuser.survey.service.SurveyServiceImpl")
public class SurveyServiceImpl implements SurveyService {
	
	@Autowired
	SurveyDao surveyDao;
	
	//调查列表
	@Override
	public List<Map<String, Object>> surveyList() {
		List<Map<String, Object>> list= surveyDao.surveyList();
		return list;
	}

}
