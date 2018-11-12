package com.bc.pmpheep.back.authadmin.materialSurvey.service;

import com.bc.pmpheep.back.authadmin.applydocaudit.bean.Material;
import com.bc.pmpheep.back.authadmin.materialSurvey.bean.*;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

import java.util.List;
import java.util.Map;

public interface MaterialSurveyService {

    /**
     * 根据教材id获取调查问卷
     * @param materialId
     * @return
     */
    List<MaterialSurvey> getSurveyByMaterialId (Long materialId);

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


    PageResult<Map<String, Object>> querySearchList(PageParameter<Map<String, Object>> pageParameter);

    MaterialSurvey getSurveyById(Long id);

    Material getMaterialByid(Long id);

    List<MaterialSurveyQuestionAnswer> getSurveyQuestionAnswerByQuestionId (Long surveyId);
    Integer checkFile(Map<String,Object> parameter);

}
