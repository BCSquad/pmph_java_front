package com.bc.pmpheep.back.authadmin.materialSurvey.service;

import com.bc.pmpheep.back.authadmin.materialSurvey.bean.*;
import com.bc.pmpheep.back.authadmin.materialSurvey.dao.MaterialSurveyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("com.bc.pmpheep.back.authadmin.materialSurvey.service.MaterialSurveyServiceImpl")
public class MaterialSurveyServiceImpl implements MaterialSurveyService {
    @Autowired
    MaterialSurveyDao materialSurveyDao;

    @Override
    public MaterialSurvey getSurveyByMaterialId(Long materialId) {
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
    public Integer fillSurveyQuestion(List<MaterialSurveyQuestionAnswer> materialSurveyQuestionAnswer) {
        return materialSurveyDao.insertQuestionAnswer(materialSurveyQuestionAnswer);
    }


}
