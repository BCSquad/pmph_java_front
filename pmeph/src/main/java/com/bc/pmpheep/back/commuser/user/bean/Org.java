package com.bc.pmpheep.back.commuser.user.bean;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Org (机构信息表)实体类 -- org
 * 
 * @author tyc
 *
 */
@SuppressWarnings("serial")
@Alias("Org")
public class Org implements Serializable {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 上级机构id
	 */
	private Long parentId;
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 机构类型ID
	 */
	private Long orgTypeId;
	/**
	 * 所在区域ID
	 */
	private Long areaId;
	/**
	 * 联系人
	 */
	private String contactPerson;
	/**
	 * 联系电话
	 */
	private String contactPhone;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 显示顺序
	 */
	private Integer sort;
	/**
	 * 是否被逻辑删除
	 */
	private Boolean isDeleted;
	/**
	 * 创建时间
	 */
	private Timestamp gmtCreate;
	/**
	 * 修改时间
	 */
	private Timestamp gmtUpdate;

	public Org() {

	}

	public Org(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @param parentId
	 *            上级机构id
	 * @param orgName
	 *            机构名称
	 * @param orgTypeId
	 *            机构类型id
	 * @param areaId
	 *            所在区域id
	 * @param countactPerson
	 *            联系人
	 * @param countactPhone
	 *            联系电话
	 * @param note
	 *            备注
	 * @param sort
	 *            显示顺序
	 * @param isDeleted
	 *            是否被逻辑删除
	 * @param gmtCreate
	 * @param gmtUpdate
	 */
	public Org(Long parentId, String orgName, Long orgTypeId, Long areaId, String contactPerson, String contactPhone,
			String note, Integer sort, boolean isDeleted, Timestamp gmtCreate, Timestamp gmtUpdate) {
		this.parentId = parentId;
		this.orgName = orgName;
		this.orgTypeId = orgTypeId;
		this.areaId = areaId;
		this.contactPerson = contactPerson;
		this.contactPhone = contactPhone;
		this.note = note;
		this.sort = sort;
		this.isDeleted = isDeleted;
		this.gmtCreate = gmtCreate;
		this.gmtUpdate = gmtUpdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Timestamp getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Timestamp getGmtUpdate() {
		return gmtUpdate;
	}

	public void setGmtUpdate(Timestamp gmtUpdate) {
		this.gmtUpdate = gmtUpdate;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", parentId:" + parentId + ", orgName:" + orgName + ", orgTypeId:" + orgTypeId
				+ ", areaId:" + areaId + ", countactPerson:" + contactPerson + ", countactPhone:" + contactPhone
				+ ", note:" + note + ", sort:" + sort + ", isDeleted:" + isDeleted + ", gmtCreate:" + gmtCreate
				+ ", gmtUpdate:" + gmtUpdate + "}";
	}

}
