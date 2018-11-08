package com.bc.pmpheep.back.authadmin.materialSurvey.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 填写问卷实体类
 */
public class MaterialSurveyQuestionAnswer implements Serializable {
    //id
    private Long id;
    //用户id
    private Integer userId;
    //用户类型
    private Integer userType;
    //问题id
    private Long questionId;
    //选项id
    private Long optionId;
    //问题输入内容
    private String optionContent;
    //创建时间
    private Timestamp gmtCreate;
    //问卷id
    private Long surveyId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }
}
