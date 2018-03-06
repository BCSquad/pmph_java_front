package com.bc.pmpheep.back.commuser.writerpoint.bean;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

/**
 * 积分记录表实体类
 * @author mr
 * @date 2018年3月6日 上午08:53:47
 */
@SuppressWarnings("serial")
@Alias("WriterPointLog")
public class WriterPointLog implements java.io.Serializable{

	//主键
	private Long id;
	//用户id
	private Long userId;
	//规则id
	private Long ruleId;
	//积分变化(可为负数)
	private Integer point;
	//创建时间
	private Date gmtCreate;
	
	//构造器
	public WriterPointLog(){
	}
	
	public WriterPointLog(Long id){
		super();
		this.id = id;
	}
	
	public WriterPointLog(Long userId, Long ruleId, Integer point){
		super();
		this.userId = userId;
		this.ruleId = ruleId;
		this.point = point;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	@Override
	public String toString() {
		return "WriterPointLog [id=" + id + ", userId=" + userId + ", ruleId="
				+ ruleId + ", point=" + point + ", gmtCreate=" + gmtCreate
				+ "]";
	}
}
