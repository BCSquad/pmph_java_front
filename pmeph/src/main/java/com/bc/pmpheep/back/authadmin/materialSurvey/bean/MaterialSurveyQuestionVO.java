package com.bc.pmpheep.back.authadmin.materialSurvey.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 问卷问题扩展实体类
 */
public class MaterialSurveyQuestionVO implements Serializable {
    //问题id
    private Long id;
    //问卷id
    private Long surveyId;
    //分类id
    private Long categoryId;
    //问题标题
    private String title;
    //问题类型  1当选 2多选 3下拉框 4 输入框,5文本域 6 文件
    private Integer type;
    //问卷排序
    private Integer sort;
    //问卷说明
    private String direction;
    //是否必填
    private Integer isAnswer;
    //是否删除
    private Integer isDeleted;
    //创建时间
    private Timestamp gmtCreate;
    //更新时间
    private Timestamp gmtUpdate;

    //问题选项
    private List<MaterialSurveyQuestionOption> materialSurveyQuestionOptionList;
    private List<Long> optionAnswers;
    //选中选项
    private Long optionAnswer;
    //填写文本
    private String optionContent;

    public List<Long> getOptionAnswers() {
        return optionAnswers;
    }

    public void setOptionAnswers(List<Long> optionAnswers) {
        this.optionAnswers = optionAnswers;
    }

    public Long getOptionAnswer() {
        return optionAnswer;
    }

    public void setOptionAnswer(Long optionAnswer) {
        this.optionAnswer = optionAnswer;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public List<MaterialSurveyQuestionOption> getMaterialSurveyQuestionOptionList() {
        return materialSurveyQuestionOptionList;
    }

    public void setMaterialSurveyQuestionOptionList(List<MaterialSurveyQuestionOption> materialSurveyQuestionOptionList) {
        this.materialSurveyQuestionOptionList = materialSurveyQuestionOptionList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(Integer isAnswer) {
        this.isAnswer = isAnswer;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Timestamp gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }
}
