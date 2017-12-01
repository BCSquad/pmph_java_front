package com.bc.pmpheep.back.commuser.reportprogress.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DecPosition
 * 
 * @author mryang
 * 
 */
@SuppressWarnings("serial")
@Alias("DecPosition")
public class DecPosition implements Serializable {
    // 主键
    private Long      id;
    // 申报表
    private Long      declarationId;
    // 书籍id
    private Long      textbookId;
    // 申报职务
    private Integer   presetPosition;
    // 是否为数字编辑
    @JsonProperty("isDigitalEditor")
    private Boolean   isDigitalEditor;
    // 是否进入预选名单
    private Integer   isOnList;
    // 遴选职务
    private Integer   chosenPosition;
    // 排位
    private Integer   rank;
    // 教学大纲id
    private String    syllabusId;
    // 教学大纲名称
    private String    syllabusName;
    // 创建时间
    private Timestamp gmtCreate;
    // 修改时间
    private Timestamp gmtUpdate;

    public DecPosition() {
        super();
    }

    public DecPosition(Long id) {
        super();
        this.id = id;
    }

    public DecPosition(Long declarationId, Long textbookId, Integer presetPosition) {
        super();
        this.declarationId = declarationId;
        this.textbookId = textbookId;
        this.presetPosition = presetPosition;
    }

    public DecPosition(Long declarationId, Long textbookId, Integer presetPosition,
    Boolean isDigitalEditor, Integer isOnList, Integer chosenPosition, Integer rank,
    String syllabusId, String syllabusName, Timestamp gmtCreate, Timestamp gmtUpdate) {
        super();
        this.declarationId = declarationId;
        this.textbookId = textbookId;
        this.presetPosition = presetPosition;
        this.isDigitalEditor = isDigitalEditor;
        this.isOnList = isOnList;
        this.chosenPosition = chosenPosition;
        this.rank = rank;
        this.syllabusId = syllabusId;
        this.syllabusName = syllabusName;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    public Boolean getIsDigitalEditor() {
        return isDigitalEditor;
    }

    public void setIsDigitalEditor(Boolean isDigitalEditor) {
        this.isDigitalEditor = isDigitalEditor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeclarationId() {
        return declarationId;
    }

    public void setDeclarationId(Long declarationId) {
        this.declarationId = declarationId;
    }

    public Long getTextbookId() {
        return textbookId;
    }

    public void setTextbookId(Long textbookId) {
        this.textbookId = textbookId;
    }

    public Integer getPresetPosition() {
        return presetPosition;
    }

    public void setPresetPosition(Integer presetPosition) {
        this.presetPosition = presetPosition;
    }

    public Integer getIsOnList() {
        return isOnList;
    }

    public void setIsOnList(Integer isOnList) {
        this.isOnList = isOnList;
    }

    public Integer getChosenPosition() {
        return chosenPosition;
    }

    public void setChosenPosition(Integer chosenPosition) {
        this.chosenPosition = chosenPosition;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getSyllabusId() {
        return syllabusId;
    }

    public void setSyllabusId(String syllabusId) {
        this.syllabusId = syllabusId;
    }

    public String getSyllabusName() {
        return syllabusName;
    }

    public void setSyllabusName(String syllabusName) {
        this.syllabusName = syllabusName;
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

    @Override
    public String toString() {
        return "DecPosition [id=" + id + ", declarationId=" + declarationId + ", textbookId="
               + textbookId + ", presetPosition=" + presetPosition + ", isDigitalEditor="
               + isDigitalEditor + ", isOnList=" + isOnList + ", chosenPosition=" + chosenPosition
               + ", rank=" + rank + ", syllabusId=" + syllabusId + ", syllabusName=" + syllabusName
               + ", gmtCreate=" + gmtCreate + ", gmtUpdate=" + gmtUpdate + "]";
    }

}
