package com.bc.pmpheep.back.commuser.cms.bean;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

/**
 * 
 * 
 * 功能描述：公告的VO
 * 
 * 
 * 
 * @author (作者) 曾庆峰
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 2017年11月28日
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
@Alias("CmsNoticeList")
public class CmsNoticeList {
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
	private Timestamp gmtCreate;
	/**
	 * 截止时间
	 */
	private Timestamp deadline;
    /**
     * 内容id
     */
    private String mid;
    
    /**
     * 内容
     */
    private String contentxt;
    
	public String getContentxt() {
		return contentxt;
	}

	public void setContentxt(String contentxt) {
		this.contentxt = contentxt;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
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

	public Timestamp getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Timestamp getDeadline() {
		return deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

}
