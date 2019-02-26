package com.bc.pmpheep.back.commuser.course.bean;

import java.sql.Timestamp;

public class CourseBookStudentVO {
    /**
     * 主键
     */
    private Long id ;
    /**
     * 课程图书id
     */
    private Long courseBookId ;
    /**
     * 学生的用户id
     */
    private Long writerUserId ;
    /**
     * 备注
     */
    private String note ;
    /**
     * 是否被逻辑删除
     */
    private Boolean deleted ;
    /**
     * 是否被教师取消
     */
    private Boolean teacherCanceled ;
    /**
     * 创建时间
     */
    private Timestamp gmtCreate ;
    /**
     * 修改时间
     */
    private Timestamp gmtUpdate ;

    public CourseBookStudentVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseBookId() {
        return courseBookId;
    }

    public void setCourseBookId(Long courseBookId) {
        this.courseBookId = courseBookId;
    }

    public Long getWriterUserId() {
        return writerUserId;
    }

    public void setWriterUserId(Long writerUserId) {
        this.writerUserId = writerUserId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getTeacherCanceled() {
        return teacherCanceled;
    }

    public void setTeacherCanceled(Boolean teacherCanceled) {
        this.teacherCanceled = teacherCanceled;
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
