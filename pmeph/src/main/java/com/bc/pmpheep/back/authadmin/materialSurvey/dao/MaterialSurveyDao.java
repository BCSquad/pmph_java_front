package com.bc.pmpheep.back.authadmin.materialSurvey.dao;

import com.bc.pmpheep.back.authadmin.materialSurvey.bean.MaterialSurvey;
import com.bc.pmpheep.back.authadmin.materialSurvey.bean.MaterialSurveyQuestion;
import com.bc.pmpheep.back.authadmin.materialSurvey.bean.MaterialSurveyQuestionAnswer;
import com.bc.pmpheep.back.authadmin.materialSurvey.bean.MaterialSurveyQuestionOption;
import com.bc.pmpheep.back.plugin.PageParameter;

import java.util.List;
import java.util.Map;

public interface MaterialSurveyDao {

    MaterialSurvey getSurveyByMaterialId (Long materialId);
    List<MaterialSurveyQuestion> getSurveyQuestionBySurveyId (Long surveyId);
    List<MaterialSurveyQuestionOption> getSurveyQuestionOptionByQuestionId(Long questionId);
    Integer insertQuestionAnswer(List<MaterialSurveyQuestionAnswer> materialSurveyQuestionAnswer);
}
