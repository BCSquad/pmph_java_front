package com.bc.pmpheep.back.commuser.mymessage.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.type.Alias;

@SuppressWarnings("serial")
@Alias("MyMessageVO")
public class MyMessageVO implements Serializable {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 消息id
	 */
	private String msgId;
	/**
	 * 消息内容
	 */
	private String content;
	/**
	 * 发送者id
	 */
	private Long senderId;
	/**
	 * 发送者类别
	 */
	private Integer senderType;
	/**
	 * 对话着头像
	 */
	private String avatar;
	/**
	 * 对话这姓名
	 */
	private String name;
	/**
	 * 接收者id
	 */
	private Long receiverId;
	/**
	 * 接收者类型
	 */
	private Integer receiverType;
	/**
	 * 当前用户姓名
	 */
	private String userName;
	/**
	 * 当前用户头像
	 */
	private String userAvatar;
	/**
	 * 发送时间
	 */
	private Date sendTime;
	/**
	 * 是否已读
	 */
	private Boolean isRead;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 用户类型
	 */
	private Integer userType;
	/**
	 * 是否是当前用户发送的消息
	 */
	private Boolean isMy;

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

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Integer getSenderType() {
		return senderType;
	}

	public void setSenderType(Integer senderType) {
		this.senderType = senderType;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public Integer getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(Integer receiverType) {
		this.receiverType = receiverType;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public Boolean getIsMy() {
		return isMy;
	}

	public void setIsMy(Boolean isMy) {
		this.isMy = isMy;
	}

}
