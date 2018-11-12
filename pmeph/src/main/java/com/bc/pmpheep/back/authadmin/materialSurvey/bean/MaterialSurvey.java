package com.bc.pmpheep.back.authadmin.materialSurvey.bean;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public class MaterialSurvey implements Serializable {
    private Long id;
    private String title;
    private String subhead;
    private String intro;
    private String realName;
    private Integer sort;
    private Integer isDelected;
    private Timestamp gmtCreate;
    private Timestamp gmtUpdate;
    private Integer status;
    private Long materialId;
    private  Boolean requiredForMaterial;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsDelected() {
        return isDelected;
    }

    public void setIsDelected(Integer isDelected) {
        this.isDelected = isDelected;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }
}
