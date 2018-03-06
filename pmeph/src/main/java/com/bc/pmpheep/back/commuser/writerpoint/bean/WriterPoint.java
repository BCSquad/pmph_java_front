package com.bc.pmpheep.back.commuser.writerpoint.bean;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

/**
 * 用户积分表实体类
 * @author mr
 * @date 2018年3月26日 上午08:44:17
 */
@SuppressWarnings("serial")
@Alias("WriterPoint")
public class WriterPoint implements java.io.Serializable{

	//主键
	private Long id;
	//用户id
	private Long userId;
	//当前总积分
	private Integer total;
	//获取积分合计
	private Integer gain;
	//消费积分合计
	private Integer loss;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtUpdate;
	
	//构造器
	public WriterPoint(){
	}
	
	public WriterPoint(Long id){
		super();
		this.id = id;
	}
	
	public WriterPoint(Long userId, Integer total, Integer gain, 
			Integer loss){
		super();
		this.userId = userId;
		this.total = total;
		this.gain = gain;
		this.loss = loss;
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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getGain() {
		return gain;
	}

	public void setGain(Integer gain) {
		this.gain = gain;
	}

	public Integer getLoss() {
		return loss;
	}

	public void setLoss(Integer loss) {
		this.loss = loss;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtUpdate() {
		return gmtUpdate;
	}

	public void setGmtUpdate(Date gmtUpdate) {
		this.gmtUpdate = gmtUpdate;
	}

	@Override
	public String toString() {
		return "WriterPoint [id=" + id + ", userId=" + userId + ", total="
				+ total + ", gain=" + gain + ", loss=" + loss + ", gmtCreate="
				+ gmtCreate + ", gmtUpdate=" + gmtUpdate + "]";
	}
}
