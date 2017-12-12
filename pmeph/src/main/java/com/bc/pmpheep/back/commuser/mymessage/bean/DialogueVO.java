package com.bc.pmpheep.back.commuser.mymessage.bean;

import java.io.Serializable;
import java.sql.Timestamp;


import org.apache.ibatis.type.Alias;

@SuppressWarnings("serial")
@Alias("DialogueVO")
public class DialogueVO implements Serializable {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 发送者头像
	 */
	private String avatar;
	/**
	 * 发送者姓名
	 */
	private String senderName;
	/**
	 * 消息id
	 */
	private String msgId;
	/**
	 * 消息内容
	 */
	private String content;
	/**
	 * 发送时间
	 */
	private Timestamp sendTime;
	/**
	 * 是否是自己发送的
	 */
	private Boolean isMy;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
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
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	public Boolean getIsMy() {
		return isMy;
	}
	public void setIsMy(Boolean isMy) {
		this.isMy = isMy;
	}
	
	@Override
	public String toString() {
		return "{id:" + id + ", Avatar:" + avatar + ", senderName:"
				+ senderName + ", msgId:" + msgId + ", content:" + content
				+ ", sendTime:" + sendTime + ", isMy:" + isMy + "}";
	}
	
	
}
