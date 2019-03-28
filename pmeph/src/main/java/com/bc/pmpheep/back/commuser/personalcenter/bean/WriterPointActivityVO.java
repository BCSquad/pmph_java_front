package com.bc.pmpheep.back.commuser.personalcenter.bean;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * writer_point_activity
 * @author 
 */
@Alias("WriterPointActivityVO")
public class WriterPointActivityVO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    private String name;
    /**
     * 规则id
     */
    private Long ruleId;

    /**
     *
     * 规则名称
     *
     */
    private String ruleName;

    private Integer point;

    /**
     * 原始兑换积分
     */

    private Integer originalExchangePoint;

    /**
     * 活动开始时间
     */
    private Date startGmt;

    /**
     * 活动结束时间
     */
    private Date endGmt;

    /**
     * 活动期间兑换积分
     */
    private Integer exchangePoint;

    /**
     * 活动简介
     */
    private String note;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtUpdate;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 是否启用
     */
    private Boolean status;


    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(final String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(final Integer point) {
        this.point = point;
    }

    public Integer getOriginalExchangePoint() {
        return originalExchangePoint;
    }

    public void setOriginalExchangePoint(final Integer originalExchangePoint) {
        this.originalExchangePoint = originalExchangePoint;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Date getStartGmt() {
        return startGmt;
    }

    public void setStartGmt(Date startGmt) {
        this.startGmt = startGmt;
    }

    public Date getEndGmt() {
        return endGmt;
    }

    public void setEndGmt(Date endGmt) {
        this.endGmt = endGmt;
    }

    public Integer getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(Integer exchangePoint) {
        this.exchangePoint = exchangePoint;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}