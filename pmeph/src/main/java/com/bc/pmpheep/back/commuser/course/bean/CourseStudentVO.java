package com.bc.pmpheep.back.commuser.course.bean;

import java.sql.Timestamp;

public class CourseStudentVO {
    /**
     * 主键
     */
    private Long id ;
    /**
     * 课程id
     */
    private Long courseId ;
    /**
     * 学生的用户id
     */
    private Long writerUserId ;
    /**
     * 学生姓名
     */
    private String name ;
/**
 * 班级
 */
    private String className ;
    /**
     * 学号
     */
    private String studentId ;
    /**
     * 备注
     */
    private String note ;
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

    public CourseStudentVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getWriterUserId() {
        return writerUserId;
    }

    public void setWriterUserId(Long writerUserId) {
        this.writerUserId = writerUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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
