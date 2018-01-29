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

    // 调查列表
    @Override
    public List<Map<String, Object>> surveyList(Map<String, Object> map) {
        List<Map<String, Object>> list = surveyDao.surveyList(map);
        return list;
    }

    // 查询调查对应的题目
    @Override
    public List<Map<String, Object>> getSurvey(long surveyId) {
        List<Map<String, Object>> list = surveyDao.getSurvey(surveyId);
        return list;
    }

    // 查询单选和多选对应的选项
    @Override
    public List<Map<String, Object>> getOptions(Long questionId) {
        List<Map<String, Object>> list = surveyDao.getOptions(questionId);
        return list;
    }

    // 保存单选答案
    @Override
    public void saveRadioAnswer(Map<String, Object> map) {
        surveyDao.saveRadioAnswer(map);
    }

    // 保存多选答案
    @Override
    public void saveCheckboxAnswer(Map<String, Object> map1) {
        surveyDao.saveCheckboxAnswer(map1);

    }

    // 保存输入框答案
    @Override
    public void saveInputAnswer(Map<String, Object> map2) {
        surveyDao.saveInputAnswer(map2);

    }

    @Override
    public Integer deleteUserAnswer(Long userId) {
        if (null == userId) {
            return 0;
        }
        return surveyDao.deleteUserAnswer(userId);
    }
}
