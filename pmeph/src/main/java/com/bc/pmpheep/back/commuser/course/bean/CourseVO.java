package com.bc.pmpheep.back.commuser.course.bean;

import com.bc.pmpheep.back.util.DateUtil;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.back.util.StringUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 课程实体类
 */
public class CourseVO implements Serializable{

    /**
     * 主键
     */
    private Long id ;
    /**
     * 名称
     */
    private String name ;
    /**
     * 备注说明
     */
    private String note ;
    /**
     * 开始时间为空时永久有效
     */
    private Timestamp beginDate ;
    /**
     * 结束时间为空时永久有效
     */
    private Timestamp endDate ;
    /**
     * 学生代表用户名关联用户名username
     */
    private String stuRepreUsername ;
    /**
     * 是否已发布
     */
    private Boolean published ;
    /**
     * 是否已下单
     */
    private Boolean orderPlaced ;
    /**
     * 是否已付款
     */
    private Boolean paid ;
    /**
     * 是否被逻辑删除
     */
    private Boolean deleted ;
    /**
     * 创建时间
     */
    private Timestamp gmtCreate ;
    /**
     * 修改时间
     */
    private Timestamp gmtUpdate ;
    /**
     * 老师id关联主键id
     */
    private Long teacherId ;


    private String status ;

    public CourseVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getStuRepreUsername() {
        return stuRepreUsername;
    }

    public void setStuRepreUsername(String stuRepreUsername) {
        this.stuRepreUsername = stuRepreUsername;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getOrderPlaced() {
        return orderPlaced;
    }

    public void setOrderPlaced(Boolean orderPlaced) {
        this.orderPlaced = orderPlaced;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getStatus() {
        if(StringUtil.notEmpty(this.status)){

        }else if(this.paid!=null && this.paid){
            this.status = "已付款";
        }else if(this.orderPlaced!=null &&this.orderPlaced){
            this.status = "已下单";
        }else if(ObjectUtil.notNull(this.endDate)
                && !DateUtil.compareDate(DateUtil.formatTimeStamp("yyyy-MM-dd",this.endDate),DateUtil.getDay())){
            this.status = "已截止";
        }else if(this.published!=null &&this.published){
            this.status = "已发布";
        }else if(this.published!=null){
            this.status = "未发布";
        }else{
            this.status = "全部";
        }

        return status;
    }

    public void setStatus(String status) {
        status = status != null ?status.trim():"全部";
        this.published = null;
        this.orderPlaced = null;
        this.paid = null ;
        this.endDate = null;
        if("未发布".equals(status)){
            this.published = false;
        }else if("已发布".equals(status)){
            this.published = true;
        }else if("已截止".equals(status)){
            this.endDate = DateUtil.getCurrentTime();
        }else if("已下单".equals(status)){
            this.orderPlaced = true;
        }else if("已付款".equals(status)){
            this.paid =true;
        }
        this.status = status;
    }
}
