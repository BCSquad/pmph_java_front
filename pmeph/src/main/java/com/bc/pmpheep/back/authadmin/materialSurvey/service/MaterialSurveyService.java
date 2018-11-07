package com.bc.pmpheep.back.authadmin.materialSurvey.service;

import com.bc.pmpheep.back.authadmin.materialSurvey.bean.*;

import java.util.List;

public interface MaterialSurveyService {

    /**
     * 根据教材id获取调查问卷
     * @param materialId
     * @return
     */
    MaterialSurvey getSurveyByMaterialId (Long materialId);

    /**
     * 根据问卷id获取问题
     * @param surveyId
     * @return
     */
    List<MaterialSurveyQuestion> getSurveyQuestionBySurveyId (Long surveyId);

    /**
     * 根据问题id获取选项
     * @param questionId
     * @return List<MaterialSurveyQuestionOption> 问题集合对象
     */
    List<MaterialSurveyQuestionOption> getSurveyQuestionOptionByQuestionId(Long questionId);

    /**
     * 填写问卷
     * @param materialSurveyQuestionAnswer
     * @return
     */
    Integer fillSurveyQuestion(List<MaterialSurveyQuestionAnswer> materialSurveyQuestionAnswer);

}
