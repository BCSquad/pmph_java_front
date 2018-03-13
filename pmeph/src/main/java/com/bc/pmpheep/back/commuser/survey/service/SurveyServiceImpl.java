package com.bc.pmpheep.back.commuser.survey.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.survey.dao.SurveyDao;
import com.bc.pmpheep.general.dao.UserDao;



@Service("com.bc.pmpheep.back.commuser.survey.service.SurveyServiceImpl")
public class SurveyServiceImpl implements SurveyService {
	
	@Autowired
	SurveyDao surveyDao;
	
	@Autowired
    UserDao userDao;
	
	//调查列表
	@Override
	public List<Map<String, Object>> surveyList(Map<String, Object> map) {
		List<Map<String, Object>> list= surveyDao.surveyList(map);
		return list;
	}
	
	//查询调查对应的题目
	@Override
	public List<Map<String, Object>> getSurvey(long surveyId) {
		List<Map<String, Object>> list = surveyDao.getSurvey(surveyId);
		return list;
	}

	//查询单选和多选对应的选项
	@Override
	public List<Map<String, Object>> getOptions(Long questionId) {
		List<Map<String, Object>> list = surveyDao.getOptions(questionId);
		return list;
	}
	
	//保存单选答案
	@Override
	public void saveRadioAnswer(Map<String, Object> map) {
		surveyDao.saveRadioAnswer(map);
	}
	
	//保存多选答案
	@Override
	public void saveCheckboxAnswer(Map<String, Object> map1) {
		surveyDao.saveCheckboxAnswer(map1);
		
	}
	
	//保存输入框答案
	@Override
	public void saveInputAnswer(Map<String, Object> map2) {
		surveyDao.saveInputAnswer(map2);
		
	}

	//获取调查基本信息
	@Override
	public Map<String, Object> getSurveyBaseInfo(long surveyId) {
		 Map<String,Object> paraMap =  new HashMap<String,Object>();
		 paraMap.put("surveyId", surveyId);
		 Map<String, Object> map  = surveyDao.getSurveyBaseInfo(paraMap);
		return map;
	}

	//填写问卷增加积分
	@Override
	public void insertUserScores(Map<String, Object> info) {

        List<Map<String, Object>> rulesList = userDao.getPointRules();
        Map<String, Map<String, Object>> rules = new HashMap<String, Map<String, Object>>();
        for (Map<String, Object> rule : rulesList) {
            rules.put(MapUtils.getString(rule, "rule_code"), rule);
        }
            
            int ruleid = MapUtils.getIntValue(rules.get("survey"), "id");
            if (ruleid>0) { //关闭的rule不应增加积分
            	info.put("scores", 1);
                info.put("ruleid", ruleid);
                userDao.insertUserScore(info);
			}
            
	}
	
	
}
