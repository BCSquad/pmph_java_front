package com.bc.pmpheep.back.commuser.cms.bean;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;


@SuppressWarnings("serial")
@Alias("CmsContentVO")
public class CmsContentVO implements java.io.Serializable {

    // 主键
    private Long      id;
    // 内容id
    private String    mid;
    // 内容类型
    private Long      categoryId;
    // 内容标题
    private String    title;
    // 摘要
    private String    summary;
    // 作者类型
    private Short     authorType;
    // 作者id
    private Long      authorId;
    // 作者真实姓名
    private String	  realname;
    // 点击数
    private Long      clicks;
	// 评论数
    private Long      comments;
    // 点赞数
    private Long      likes;
    // 收藏数
    private Long      bookmarks;
    // 是否分类置顶
    private Boolean   isStick;
    // 分类显示顺序
    private Integer   sort;
    // 置顶到期时间
    private String    deadlineStick;
    // 是否热门
    private Boolean   isHot;
    // 热门显示顺序
    private Integer   sortHot;
    // 热门到期时间
    private String    deadlineHot;
    // 是否推荐
    private Boolean   isPromote;
    // 推荐显示顺序
    private Integer   sortPromote;
    // 推荐到期时间
    private String    deadlinePromote;
    // 是否隐藏
    private Boolean   isHide;
    // 是否已发布
    private Boolean   isPublished;
    // 是否通过审核
    private Boolean   isAuth;
    // 是否被逻辑删除
    private Boolean   isDeleted;
    // 创建时间
    private Timestamp gmtCreate;
    // 教材id
    private Long      materialId;
    // 条件分页总条数分页查询
    private Integer   count;
    // 作者头像
    private String    authorImg;
    // 医学随笔图片
    private String    cmsImg;
    
    // 审核通过时间
    private String authdate;
    //头像路径 
    private String avatar;
    

	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getAuthdate() {
		return authdate;
	}
	public void setAuthdate(String authdate) {
		this.authdate = authdate;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Short getAuthorType() {
		return authorType;
	}
	public void setAuthorType(Short authorType) {
		this.authorType = authorType;
	}
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	public Long getClicks() {
		return clicks;
	}
	public void setClicks(Long clicks) {
		this.clicks = clicks;
	}
	public Long getComments() {
		return comments;
	}
	public void setComments(Long comments) {
		this.comments = comments;
	}
	public Long getLikes() {
		return likes;
	}
	public void setLikes(Long likes) {
		this.likes = likes;
	}
	public Long getBookmarks() {
		return bookmarks;
	}
	public void setBookmarks(Long bookmarks) {
		this.bookmarks = bookmarks;
	}
	public Boolean getIsStick() {
		return isStick;
	}
	public void setIsStick(Boolean isStick) {
		this.isStick = isStick;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getDeadlineStick() {
		return deadlineStick;
	}
	public void setDeadlineStick(String deadlineStick) {
		this.deadlineStick = deadlineStick;
	}
	public Boolean getIsHot() {
		return isHot;
	}
	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}
	public Integer getSortHot() {
		return sortHot;
	}
	public void setSortHot(Integer sortHot) {
		this.sortHot = sortHot;
	}
	public String getDeadlineHot() {
		return deadlineHot;
	}
	public void setDeadlineHot(String deadlineHot) {
		this.deadlineHot = deadlineHot;
	}
	public Boolean getIsPromote() {
		return isPromote;
	}
	public void setIsPromote(Boolean isPromote) {
		this.isPromote = isPromote;
	}
	public Integer getSortPromote() {
		return sortPromote;
	}
	public void setSortPromote(Integer sortPromote) {
		this.sortPromote = sortPromote;
	}
	public String getDeadlinePromote() {
		return deadlinePromote;
	}
	public void setDeadlinePromote(String deadlinePromote) {
		this.deadlinePromote = deadlinePromote;
	}
	public Boolean getIsHide() {
		return isHide;
	}
	public void setIsHide(Boolean isHide) {
		this.isHide = isHide;
	}
	public Boolean getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}
	public Boolean getIsAuth() {
		return isAuth;
	}
	public void setIsAuth(Boolean isAuth) {
		this.isAuth = isAuth;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Timestamp getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getAuthorImg() {
		return authorImg;
	}
	public void setAuthorImg(String authorImg) {
		this.authorImg = authorImg;
	}
	public String getCmsImg() {
		return cmsImg;
	}
	public void setCmsImg(String cmsImg) {
		this.cmsImg = cmsImg;
	}
    
}