package com.bc.pmpheep.back.commuser.group.bean;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 
 * 
 * 功能描述： 小组成员VO
 * 
 * 
 * @author (作者) 曾庆峰
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @date (开发日期) 2017年9月29日
 * @修改人 ：曾庆峰
 *
 */
@SuppressWarnings("serial")
@Alias("PmphGroupMemberVO")
public class PmphGroupMemberVO implements Serializable {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 小组id
	 */
	private Long groupId;
	/**
	 * 成员id
	 */
	private Long userId;
	/**
	 * 成员类型 1 社内 2 作家 3 机构
	 */
	private Short userType;
	/***
	 * 小组成员头像
	 */
	private String avatar;
	/**
	 * 是否是作家用户
	 */
	private Boolean isWriter;
	/**
	 * 是否创建者
	 */
	private Boolean isFounder;
	/**
	 * 是否管理员
	 */
	private Boolean isAdmin;
	/**
	 * 小组显示名称
	 */
	private String displayName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Boolean getIsWriter() {
		return isWriter;
	}

	public void setIsWriter(Boolean isWriter) {
		this.isWriter = isWriter;
	}

	public Boolean getIsFounder() {
		return isFounder;
	}

	public void setIsFounder(Boolean isFounder) {
		this.isFounder = isFounder;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public Short getUserType() {
		return userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
