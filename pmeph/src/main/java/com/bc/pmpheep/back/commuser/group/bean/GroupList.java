package com.bc.pmpheep.back.commuser.group.bean;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("GroupList")
public class GroupList {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 小组名称
	 */
	private String groupName;
	/**
	 * 小组头像
	 */
	private String groupImage;
	/**
	 * 书籍名称
	 */
	private String bookName;
	/**
	 * 创建时间
	 */
	private String gmtCreate;
	/**
	 * 小组人数
	 */
	private Integer members;
	/**
	 * 小组文件数
	 */
	private Integer files;
	/**
	 * 小组成员头像
	 */
	private List<String> avatars;
	/**
	 * 小组最新动态
	 */
	private List<GroupMessageVO> groupMassages;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupImage() {
		return groupImage;
	}

	public void setGroupImage(String groupImage) {
		this.groupImage = groupImage;
	}

	public Integer getMembers() {
		return members;
	}

	public void setMembers(Integer members) {
		this.members = members;
	}

	public Integer getFiles() {
		return files;
	}

	public void setFiles(Integer files) {
		this.files = files;
	}

	public List<String> getAvatars() {
		return avatars;
	}

	public void setAvatars(List<String> avatars) {
		this.avatars = avatars;
	}

	public List<GroupMessageVO> getGroupMassages() {
		return groupMassages;
	}

	public void setGroupMassages(List<GroupMessageVO> groupMassages) {
		this.groupMassages = groupMassages;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", groupName:" + groupName + ", groupImage:"
				+ groupImage + ", bookName:" + bookName + ", gmtCreate:"
				+ gmtCreate + ", members:" + members + ", files:" + files
				+ ", avatars:" + avatars + ", groupMassages:" + groupMassages
				+ "}";
	}

	
}
