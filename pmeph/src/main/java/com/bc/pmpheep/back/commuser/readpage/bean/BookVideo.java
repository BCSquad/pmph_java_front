package com.bc.pmpheep.back.commuser.readpage.bean;

import java.io.Serializable;

/**
 * BookVideo 实体 
 * @author MrYang
 * 2018年1月31日  上午8:53:35
 */
@SuppressWarnings("serial")
@org.apache.ibatis.type.Alias("BookVideo")
public class BookVideo implements Serializable{
	//主键
	private Long id;
	//图书id
	private Long bookId;
	//标题
	private String title;
	//原始视频存放路径
	private String origPath;
	//原始文件名称
	private String origFileName;
	//原始文件大小
	private Long   origFileSize;
	//转码后文件放置路径
	private String path;
	//转码后文件名称
	private String fileName;
	//转码后文件大小
	private Long   fileSize;
	//在图书页面显示顺序
	private Integer sort;
	//点击数
	private Long    clicks;
	//上传者id
	private Long userId ;
	//是否通过审核
	private Boolean isAuth;
	//审核者id
	private Long authUserId;
	//审核时间
	private java.util.Date authDate;
	//是否逻辑删除
	private Boolean isDeleted;
	//创建时间
	private java.sql.Timestamp gmtCreate;
	//修改时间
	private java.sql.Timestamp gmtUpdate;
	//封面图片id
	private String  cover; 
	
	public BookVideo() {
		super();
	}
	
	public BookVideo(Long bookId, String title, String origPath,
                     String origFileName, Long origFileSize, Long userId) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.origPath = origPath;
		this.origFileName = origFileName;
		this.origFileSize = origFileSize;
		this.userId = userId;
	}

	public String getCover() {
		return cover;
	}

	public BookVideo setCover(String cover) {
		this.cover = cover;
		return this;
	}

	public Long getId() {
		return id;
	}

	public BookVideo setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getBookId() {
		return bookId;
	}

	public BookVideo setBookId(Long bookId) {
		this.bookId = bookId;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public BookVideo setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getOrigPath() {
		return origPath;
	}

	public BookVideo setOrigPath(String origPath) {
		this.origPath = origPath;
		return this;
	}

	public String getOrigFileName() {
		return origFileName;
	}

	public BookVideo setOrigFileName(String origFileName) {
		this.origFileName = origFileName;
		return this;
	}

	public Long getOrigFileSize() {
		return origFileSize;
	}

	public BookVideo setOrigFileSize(Long origFileSize) {
		this.origFileSize = origFileSize;
		return this;
	}

	public String getPath() {
		return path;
	}

	public BookVideo setPath(String path) {
		this.path = path;
		return this;
	}

	public String getFileName() {
		return fileName;
	}

	public BookVideo setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public BookVideo setFileSize(Long fileSize) {
		this.fileSize = fileSize;
		return this;
	}

	public Integer getSort() {
		return sort;
	}

	public BookVideo setSort(Integer sort) {
		this.sort = sort;
		return this;
	}

	public Long getClicks() {
		return clicks;
	}

	public BookVideo setClicks(Long clicks) {
		this.clicks = clicks;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public BookVideo setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public Boolean getIsAuth() {
		return isAuth;
	}

	public BookVideo setIsAuth(Boolean isAuth) {
		this.isAuth = isAuth;
		return this;
	}

	public Long getAuthUserId() {
		return authUserId;
	}

	public BookVideo setAuthUserId(Long authUserId) {
		this.authUserId = authUserId;
		return this;
	}

	public java.util.Date getAuthDate() {
		return authDate;
	}

	public BookVideo setAuthDate(java.util.Date authDate) {
		this.authDate = authDate;
		return this;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public BookVideo setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
		return this;
	}

	public java.sql.Timestamp getGmtCreate() {
		return gmtCreate;
	}

	public BookVideo setGmtCreate(java.sql.Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

	public java.sql.Timestamp getGmtUpdate() {
		return gmtUpdate;
	}

	public BookVideo setGmtUpdate(java.sql.Timestamp gmtUpdate) {
		this.gmtUpdate = gmtUpdate;
		return this;
	}

	@Override
	public String toString() {
		return "{id:" + id + ",bookId:" + bookId + ",title:" + title
				+ ",origPath:" + origPath + ",origFileName:" + origFileName
				+ ",origFileSize:" + origFileSize + ",path:" + path
				+ ",fileName:" + fileName + ",fileSize:" + fileSize + ",sort:"
				+ sort + ",clicks:" + clicks + ",userId:" + userId + ",isAuth:"
				+ isAuth + ",authUserId:" + authUserId + ",authDate:"
				+ authDate + ",isDeleted:" + isDeleted + ",gmtCreate:"
				+ gmtCreate + ",gmtUpdate:" + gmtUpdate + ",cover:" + cover
				+ "}";
	}
	
	
	
}
