package com.bc.pmpheep.back.commuser.personalcenter.bean;

import org.apache.ibatis.type.Alias;

/**
 * 积分规则表实体类VO
 * @author tyc
 * @date 2017年12月28日 下午14:36:23
 */
@SuppressWarnings("serial")
@Alias("WriterPointRuleVO")
public class WriterPointRuleVO implements java.io.Serializable{

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
	//条件分页总条数分页查询
    private Integer count;
    //页面查询条件（状态）
    private Integer status;
	
	//构造器
	public WriterPointRuleVO(){
	}
	
	public WriterPointRuleVO(Long id){
		super();
		this.id = id;
	}
	
	public WriterPointRuleVO(Long id, String ruleName, String ruleCode, Integer point,
                             Boolean isExchange, String thirdName, Integer exchangePoint, String description,
                             Boolean isDisabled){
		super();
		this.id = id;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "WriterPointRuleVO [id=" + id + ", ruleName=" + ruleName
				+ ", ruleCode=" + ruleCode + ", point=" + point
				+ ", isExchange=" + isExchange + ", thirdName=" + thirdName
				+ ", exchangePoint=" + exchangePoint + ", description="
				+ description + ", isDisabled=" + isDisabled + ", count="
				+ count + ", status=" + status + "]";
	}
}
