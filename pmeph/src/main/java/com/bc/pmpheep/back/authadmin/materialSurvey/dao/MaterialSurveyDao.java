package com.bc.pmpheep.back.authadmin.materialSurvey.dao;

import com.bc.pmpheep.back.authadmin.applydocaudit.bean.Material;
import com.bc.pmpheep.back.authadmin.materialSurvey.bean.MaterialSurvey;
import com.bc.pmpheep.back.authadmin.materialSurvey.bean.MaterialSurveyQuestion;
import com.bc.pmpheep.back.authadmin.materialSurvey.bean.MaterialSurveyQuestionAnswer;
import com.bc.pmpheep.back.authadmin.materialSurvey.bean.MaterialSurveyQuestionOption;
import com.bc.pmpheep.back.plugin.PageParameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MaterialSurveyDao {

    List<MaterialSurvey> getSurveyByMaterialId(Long materialId);
    List<MaterialSurveyQuestion> getSurveyQuestionBySurveyId(Long surveyId);
    List<MaterialSurveyQuestionOption> getSurveyQuestionOptionByQuestionId(Long questionId);
    List<MaterialSurveyQuestionAnswer> getSurveyQuestionAnswerByQuestionId(Map<String,Object> Parameter);
    Integer insertQuestionAnswer(List<MaterialSurveyQuestionAnswer> materialSurveyQuestionAnswer);
    int queryCount();
    int queryCount(PageParameter<Map<String, Object>> pageParameter);
    List<Map<String,Object>> querySearchList(PageParameter<Map<String, Object>> pageParameter);
    MaterialSurvey getSurveyById(Long id);
    Material getMaterialByid(Long id);
    Integer checkFile(Map<String, Object> parameter);
    List<Map<String,Object>> querySearchByTextbookId(@Param("textbook_id") String textbook_id);
    List<Map<String,Object>> queryAnswer(@Param("material_id") String material_id,@Param("user_id") String user_id);
}
