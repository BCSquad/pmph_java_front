package com.bc.pmpheep.back.commuser.group.bean;

import java.io.Serializable;
import java.sql.Timestamp;



import org.apache.ibatis.type.Alias;

/**
 * PmphGroupMessage(后台小组消息表) 实体类   ,对应数据库 pmph_group_message 
 * 
 * @author 曾庆峰
 *
 */
@SuppressWarnings("serial")
@Alias("GroupMessageVO")
public class GroupMessageVO implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 小组id
     */
    private Long groupId;
    /**
     * 发送者id
     */
    private Long memberId;
    /**
     * 发送者头像
     */
    private String avatar;
    /**
     * 发送者姓名
     */
    private String displayName;
    /**
     * 消息类型  0 系统消息 1 我的消息  2 他人的消息
     */
    private Integer msgType ;

    /**
     * 消息内容
     */
    private String msgContent;
    /**
     * 创建时间
     */
    private Timestamp gmtCreate;

    
    public GroupMessageVO(Long id) {
		super();
		this.id = id;
	}

	public GroupMessageVO() {
		super();
	}

	public GroupMessageVO(Long groupId, Long memberId, String msgContent) {
	this.groupId = groupId;
	this.memberId = memberId;
	this.msgContent = msgContent;
    }

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

    public Long getMemberId() {
	return memberId;
    }

    public void setMemberId(Long memberId) {
	this.memberId = memberId;
    }

    public String getMsgContent() {
	return msgContent;
    }

    public void setMsgContent(String msgContent) {
	this.msgContent = msgContent;
    }

    public Timestamp getGmtCreate() {
	return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
	this.gmtCreate = gmtCreate;
    }
    
    public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", groupId:" + groupId + ", memberId:" + memberId
				+ ", avatar:" + avatar + ", displayName:" + displayName
				+ ", msgType:" + msgType + ", msgContent:" + msgContent
				+ ", gmtCreate:" + gmtCreate + "}";
	}

}
