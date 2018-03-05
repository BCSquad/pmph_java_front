package com.bc.pmpheep.back.commuser.reportprogress.bean;

import java.sql.Timestamp;
import java.util.Map;

import org.apache.ibatis.type.Alias;

/**
 * 
 * <pre>
 * 功能描述：用户申报教材消息VO
 * 使用示范：
 * 
 * 
 * @author (作者) nyz
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 2017-12-6
 * @modify (最后修改时间) 
 * @修改人 ：nyz 
 * @审核人 ：
 * </pre>
 */
@Alias("UserMessageVO")
public class UserMessageVO {
    // 主键
    private Long      id;
    // 消息id 创建消息时先创建MongoDB条目拿到id
    private String    msgId;
    // 消息标题
    private String    title;
    // 消息类型 0=系统消息/1=站内群发/2=站内私信(作家和机构用户不能群发)
    private Short     msgType;
    // 发送者id 0=系统/其他=用户id
    private Long      senderId;
    // 发送者类型 0=系统/1=社内用户/2=作家用户/3=机构用户
    private Short     senderType;
    // 接收者id
    private Long      receiverId;
    // 接收者类型 1=社内用户/2=作家/3=机构用户
    private Short     receiverType;
    // 是否被逻辑删除 只有接收者可以删除
    private Boolean   isDeleted;

	// 创建时间
    private Timestamp gmtCreate;
    // 教材ID
    private Long      materialId;
    // MongoDB消息内容
    private String    msgContent;
    // 动态类的消息
    private Map<String,Object> msgMap;
    
    // 动态表的id
    private Long trendstId;
    
    

    public Long getTrendstId() {
		return trendstId;
	}

	public void setTrendstId(Long trendstId) {
		this.trendstId = trendstId;
	}

	public Map<String, Object> getMsgMap() {
		return msgMap;
	}

	public void setMsgMap(Map<String, Object> msgMap) {
		this.msgMap = msgMap;
	}

    public UserMessageVO() {
    }

    public UserMessageVO(Long materialId, String msgContent, Timestamp gmtCreate) {
        this.materialId = materialId;
        this.msgContent = msgContent;
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the msgId
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * @param msgId the msgId to set
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the msgType
     */
    public Short getMsgType() {
        return msgType;
    }

    /**
     * @param msgType the msgType to set
     */
    public void setMsgType(Short msgType) {
        this.msgType = msgType;
    }

    /**
     * @return the senderId
     */
    public Long getSenderId() {
        return senderId;
    }

    /**
     * @param senderId the senderId to set
     */
    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    /**
     * @return the senderType
     */
    public Short getSenderType() {
        return senderType;
    }

    /**
     * @param senderType the senderType to set
     */
    public void setSenderType(Short senderType) {
        this.senderType = senderType;
    }

    /**
     * @return the receiverId
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * @param receiverId the receiverId to set
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * @return the receiverType
     */
    public Short getReceiverType() {
        return receiverType;
    }

    /**
     * @param receiverType the receiverType to set
     */
    public void setReceiverType(Short receiverType) {
        this.receiverType = receiverType;
    }

    /**
     * @return the isDeleted
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted the isDeleted to set
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * @return the msgContent
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * @param msgContent the msgContent to set
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    /**
     * @return the materialId
     */
    public Long getMaterialId() {
        return materialId;
    }

    /**
     * @param materialId the materialId to set
     */
    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    /**
     * @return the gmtCreate
     */
    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate the gmtCreate to set
     */
    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

}
