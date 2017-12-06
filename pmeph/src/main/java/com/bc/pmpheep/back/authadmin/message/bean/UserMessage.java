package com.bc.pmpheep.back.authadmin.message.bean;

/**
 * 机构用户发送新消息 （批量）
 * @author xcy
 *
 */
public class UserMessage {

	/**
	 * 消息id
	 */
	private String msg_id; 
	/**
	 * 消息类型
	 */
	private int msg_type; 
	/**
	 * 消息标题
	 */
	private String title; 
	/**
	 * 发送者id
	 */
	private long sender_id;  
	/**
	 * 发送者类型
	 */
	private int sender_type;  
	/**
	 * 接收者id
	 */
	private long receiver_id; 
	/**
	 * 接收者类型
	 */
	private int receiver_type; 
	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}
	public int getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(int msg_type) {
		this.msg_type = msg_type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getSender_id() {
		return sender_id;
	}
	public void setSender_id(long sender_id) {
		this.sender_id = sender_id;
	}
	public int getSender_type() {
		return sender_type;
	}
	public void setSender_type(int sender_type) {
		this.sender_type = sender_type;
	}
	public long getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(long receiver_id) {
		this.receiver_id = receiver_id;
	}
	public int getReceiver_type() {
		return receiver_type;
	}
	public void setReceiver_type(int receiver_type) {
		this.receiver_type = receiver_type;
	}
	
}
