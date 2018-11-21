package com.bc.pmpheep.back.authadmin.materialSurvey.service;

import com.bc.pmpheep.back.authadmin.applydocaudit.bean.Material;
import com.bc.pmpheep.back.authadmin.materialSurvey.bean.MaterialSurvey;
import com.bc.pmpheep.back.authadmin.materialSurvey.bean.MaterialSurveyQuestion;
import com.bc.pmpheep.back.authadmin.materialSurvey.bean.MaterialSurveyQuestionAnswer;
import com.bc.pmpheep.back.authadmin.materialSurvey.bean.MaterialSurveyQuestionOption;
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
    List<MaterialSurvey> getSurveyByMaterialId(Long materialId);

    /**
     * 根据问卷id获取问题
     * @param surveyId
     * @return
     */
    List<MaterialSurveyQuestion> getSurveyQuestionBySurveyId(Long surveyId);

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

    /**
     * 根据问卷id获取问卷对象
     * @param id
     * @return
     */
    MaterialSurvey getSurveyById(Long id);
    /**
     * 根据教材id获取教材对象
     * @param id
     * @return
     */
    Material getMaterialByid(Long id);
    /**
     * 根据问题id获取问题回答列表
     * @param id
     * @return
     */
    List<MaterialSurveyQuestionAnswer> getSurveyQuestionAnswerByQuestionId(Map<String,Object> Parameter);

    /**
     * 检查是否填写必填项
     * @param id
     * @return
     */
    Integer checkFile(Map<String, Object> parameter);

    /**
     * 根据图书id查询调研表
     * @param textbook_id
     * @return
     */
   List<Map<String, Object>> querySearchByTextbookId(String textbook_id);

    /**
     *   查询申请用户已经填写过的调研表
     * @param material_id
     * @param user_id
     * @return
     */
    List<Map<String,Object>> queryAnswer(String material_id,String user_id);
}
