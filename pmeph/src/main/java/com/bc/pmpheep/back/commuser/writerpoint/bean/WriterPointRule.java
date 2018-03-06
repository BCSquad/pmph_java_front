package com.bc.pmpheep.back.commuser.writerpoint.bean;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

/**
 * 积分规则表实体类
 * @author mr
 * @date 2018年3月6日 上午08:59:55
 */
@SuppressWarnings("serial")
@Alias("WriterPointRule")
public class WriterPointRule implements java.io.Serializable{

	//主键
	private Long id;
	//积分规则名称
	private String ruleName;
	//积分规则标识
	private String ruleCode;
	//积分值(可为负数)
	private Integer point;
	//是否用于三方兑换
	private Boolean isExchange;
	//目标平台名称
	private String thirdName;
	//兑换三方积分
	private Integer exchangePoint;
	//规则描述
	private String description;
	//是否禁用
	private Boolean isDisabled;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtUpdate;
	
	//构造器
	public WriterPointRule(){
	}
	
	public WriterPointRule(Long id){
		super();
		this.id = id;
	}
	
	public WriterPointRule(String ruleName, String ruleCode, Integer point, 
			Boolean isExchange, String thirdName, Integer exchangePoint, String description, 
			Boolean isDisabled){
		super();
		this.ruleName = ruleName;
		this.ruleCode = ruleCode;
		this.point = point;
		this.isExchange = isExchange;
		this.thirdName = thirdName;
		this.exchangePoint = exchangePoint;
		this.description = description;
		this.isDisabled = isDisabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Boolean getIsExchange() {
		return isExchange;
	}

	public void setIsExchange(Boolean isExchange) {
		this.isExchange = isExchange;
	}

	public String getThirdName() {
		return thirdName;
	}

	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}

	public Integer getExchangePoint() {
		return exchangePoint;
	}

	public void setExchangePoint(Integer exchangePoint) {
		this.exchangePoint = exchangePoint;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
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
		return "WriterPointRule [id=" + id + ", ruleName=" + ruleName
				+ ", ruleCode=" + ruleCode + ", point=" + point
				+ ", isExchange=" + isExchange + ", thirdName=" + thirdName
				+ ", exchangePoint=" + exchangePoint + ", description="
				+ description + ", isDisabled=" + isDisabled + ", gmtCreate="
				+ gmtCreate + ", gmtUpdate=" + gmtUpdate + "]";
	}
}
