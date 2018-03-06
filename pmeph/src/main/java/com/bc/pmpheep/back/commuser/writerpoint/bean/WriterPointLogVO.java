package com.bc.pmpheep.back.commuser.writerpoint.bean;

import org.apache.ibatis.type.Alias;

/**
 * 积分记录表实体类VO
 * @author tyc
 * @date 2017年12月28日 下午15:19:54
 */
@SuppressWarnings("serial")
@Alias("WriterPointLogVO")
public class WriterPointLogVO implements java.io.Serializable{

	//主键
	private Long id;
	//用户id
	private Long userId;
	//规则id
	private Long ruleId;
	//积分变化(可为负数)
	private Integer point;
	//条件分页总条数分页查询
    private Integer count;
    //页面查询条件（状态）
    private Integer status;
	
	//构造器
	public WriterPointLogVO(){
	}
	
	public WriterPointLogVO(Long id){
		super();
		this.id = id;
	}
	
	public WriterPointLogVO(Long id, Long userId, Long ruleId, Integer point){
		super();
		this.id = id;
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
		return "WriterPointLogVO [id=" + id + ", userId=" + userId
				+ ", ruleId=" + ruleId + ", point=" + point + ", count="
				+ count + ", status=" + status + "]";
	}
}
