package com.bc.pmpheep.back.commuser.cms.bean;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

/**
 * 
 * 
 * 功能描述：信息快报
 * 
 * 
 * 
 * @author (作者) 曾庆峰
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 2017年11月27日
 * @modify (最后修改时间)
 * @修改人 ：曾庆峰
 * @审核人 ：
 *
 */
/**
 * @author 
 *@Title: 
 * @Description: 
 * @param 
 * @return 
 * @throws
 */
@Alias("CmsInfoLettersList")
public class CmsInfoLettersList {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 摘要
	 */
	private String summary;
	/**
	 * 点击数
	 */
	private Long clicks;
	/**
	 * 点赞数
	 */
	private Long likes;
	/**
	 * 是否置顶
	 */
	private Boolean isStick;
	/**
	 * 是否热门
	 */
	private Boolean isHot;
	/**
	 * 是否推荐
	 */
	private Boolean isPromote;
	/**
	 * 审核时间
	 */
	private Timestamp authDate;
    /**
     * 创建时间
     */
    private Timestamp gmtCreate;
    /**
     * 内容id
     */
    private String mid;
    /**
     * cms类型id
     */
    private int categoryId;
    
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Timestamp getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Long getClicks() {
		return clicks;
	}

	public void setClicks(Long clicks) {
		this.clicks = clicks;
	}

	public Long getLikes() {
		return likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public Boolean getIsStick() {
		return isStick;
	}

	public void setIsStick(Boolean isStick) {
		this.isStick = isStick;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public Boolean getIsPromote() {
		return isPromote;
	}

	public void setIsPromote(Boolean isPromote) {
		this.isPromote = isPromote;
	}

	public Timestamp getAuthDate() {
		return authDate;
	}

	public void setAuthDate(Timestamp authDate) {
		this.authDate = authDate;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
    
}
