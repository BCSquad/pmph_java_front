package com.bc.pmpheep.back.authadmin.materialSurvey.service;

import com.bc.pmpheep.back.authadmin.applydocaudit.bean.Material;
import com.bc.pmpheep.back.authadmin.materialSurvey.bean.*;
import com.bc.pmpheep.back.authadmin.materialSurvey.dao.MaterialSurveyDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("com.bc.pmpheep.back.authadmin.materialSurvey.service.MaterialSurveyServiceImpl")
public class MaterialSurveyServiceImpl implements MaterialSurveyService {
    @Autowired
    MaterialSurveyDao materialSurveyDao;

    @Override
    public List<MaterialSurvey> getSurveyByMaterialId(Long materialId) {
        return materialSurveyDao.getSurveyByMaterialId(materialId);
    }

    @Override
    public List<MaterialSurveyQuestion> getSurveyQuestionBySurveyId(Long surveyId) {
        return materialSurveyDao.getSurveyQuestionBySurveyId(surveyId);
    }



    @Override
    public List<MaterialSurveyQuestionOption> getSurveyQuestionOptionByQuestionId(Long questionId) {

        return materialSurveyDao.getSurveyQuestionOptionByQuestionId(questionId);
    }

    @Override
    public List<MaterialSurveyQuestionAnswer> getSurveyQuestionAnswerByQuestionId(Long questionId) {
        return materialSurveyDao.getSurveyQuestionAnswerByQuestionId(questionId);
    }

    @Override
    public Integer checkFile(Map<String, Object> parameter) {
        return materialSurveyDao.checkFile(parameter);
    }

    @Override
    public Integer fillSurveyQuestion(List<MaterialSurveyQuestionAnswer> materialSurveyQuestionAnswer) {
        return materialSurveyDao.insertQuestionAnswer(materialSurveyQuestionAnswer);
    }

    @Override
    public PageResult<Map<String, Object>> querySearchList(PageParameter<Map<String, Object>> pageParameter) {
        PageResult<Map<String, Object>> pageResult= new PageResult<>();
        pageResult.setPageNumber(pageParameter.getPageNumber());
        pageResult.setPageSize(pageParameter.getPageSize());
        List<Map<String,Object>> list = materialSurveyDao.querySearchList(pageParameter);
        int count = materialSurveyDao.queryCount(pageParameter);
        pageResult.setRows(list);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public MaterialSurvey getSurveyById(Long id) {
        return materialSurveyDao.getSurveyById(id);
    }

    @Override
    public Material getMaterialByid(Long id) {
        return materialSurveyDao.getMaterialByid(id);
    }



}
