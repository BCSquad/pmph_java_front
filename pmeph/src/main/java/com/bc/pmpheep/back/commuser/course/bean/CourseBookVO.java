package com.bc.pmpheep.back.commuser.course.bean;

public class CourseBookVO {
    /**
     * 主键
     */
    private Long id ;
    /**
     * 课程id
     */
    private Long courseId ;
    /**
     * 图书id
     */
    private Long bookId ;
    /**
     * 备注说明
     */
    private String note ;
    /**
     * 是否被逻辑删除
     */
    private Boolean deleted ;

    private String bookname;

    private String isbn;

    public CourseBookVO() {
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

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
