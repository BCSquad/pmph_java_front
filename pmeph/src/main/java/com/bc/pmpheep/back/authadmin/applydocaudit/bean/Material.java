package com.bc.pmpheep.back.authadmin.applydocaudit.bean;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 教材实体类 对应material表
 * @author liudi
 *
 */
public class Material {
	private Long id;
	private String materialName;
	private Integer materialRound;
	private Long materialType;
	private Date deadline;
	private Date actualDeadline;
	private Date ageDeadline;
	private String mailAddress;
	private Long departmentId;
	private Long director;
	private Integer isMultiBooks;
	private Integer isMultiPosition;
	private Integer isEduExpUsed;
	private Integer isEduExpRequired;
	private Integer isWorkExpUsed;
	private Integer isWorkExpRequired;
	private Integer isTeachExpUsed;
	private Integer isTeachExpRequired;
	private Integer isAcadeUsed;
	private Integer isAcadeRequired;
	private Integer isLastPositionUsed;
	private Integer isLastPositionRequired;
	private Integer isNationalCourseUsed;
	private Integer isNationalCourseRequired;
	private Integer isProvincialCourseUsed;
	private Integer isProvincialCourseRequired;
	private Integer isSchoolCourseUsed;
	private Integer isSchoolCourseRequired;
	private Integer isNationalPlanUsed;
	private Integer isNationalPlanRequired;
	private Integer isTextbookUsed;
	private Integer isTextbookRequired;
	private Integer isOtherTextbookUsed;
	private Integer isOtherTextbookRequired;
	private Integer isResearchUsed;
	private Integer isResearchRequired;
	private Integer isPublished;
	private Integer isDeleted;
	private Timestamp gmtCreate;
	private Integer founderId;
	private Timestamp gmtUpdate;
	private Integer menderId;
	private Integer isPublic;
	private Integer isAllTextbookPublished;
	private Integer isForceEnd;
	
	//非数据库单表对应字段属性 联合查询得
	private Long user_num;
	
	
	/**
	 * 教材主键
	 * @return
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public Integer getMaterialRound() {
		return materialRound;
	}
	public void setMaterialRound(Integer materialRound) {
		this.materialRound = materialRound;
	}
	public Long getMaterialType() {
		return materialType;
	}
	public void setMaterialType(Long materialType) {
		this.materialType = materialType;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public Date getActualDeadline() {
		return actualDeadline;
	}
	public void setActualDeadline(Date actualDeadline) {
		this.actualDeadline = actualDeadline;
	}
	public Date getAgeDeadline() {
		return ageDeadline;
	}
	public void setAgeDeadline(Date ageDeadline) {
		this.ageDeadline = ageDeadline;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getDirector() {
		return director;
	}
	public void setDirector(Long director) {
		this.director = director;
	}
	public Integer getIsMultiBooks() {
		return isMultiBooks;
	}
	public void setIsMultiBooks(Integer isMultiBooks) {
		this.isMultiBooks = isMultiBooks;
	}
	public Integer getIsMultiPosition() {
		return isMultiPosition;
	}
	public void setIsMultiPosition(Integer isMultiPosition) {
		this.isMultiPosition = isMultiPosition;
	}
	public Integer getIsEduExpUsed() {
		return isEduExpUsed;
	}
	public void setIsEduExpUsed(Integer isEduExpUsed) {
		this.isEduExpUsed = isEduExpUsed;
	}
	public Integer getIsEduExpRequired() {
		return isEduExpRequired;
	}
	public void setIsEduExpRequired(Integer isEduExpRequired) {
		this.isEduExpRequired = isEduExpRequired;
	}
	public Integer getIsWorkExpUsed() {
		return isWorkExpUsed;
	}
	public void setIsWorkExpUsed(Integer isWorkExpUsed) {
		this.isWorkExpUsed = isWorkExpUsed;
	}
	public Integer getIsWorkExpRequired() {
		return isWorkExpRequired;
	}
	public void setIsWorkExpRequired(Integer isWorkExpRequired) {
		this.isWorkExpRequired = isWorkExpRequired;
	}
	public Integer getIsTeachExpUsed() {
		return isTeachExpUsed;
	}
	public void setIsTeachExpUsed(Integer isTeachExpUsed) {
		this.isTeachExpUsed = isTeachExpUsed;
	}
	public Integer getIsTeachExpRequired() {
		return isTeachExpRequired;
	}
	public void setIsTeachExpRequired(Integer isTeachExpRequired) {
		this.isTeachExpRequired = isTeachExpRequired;
	}
	public Integer getIsAcadeUsed() {
		return isAcadeUsed;
	}
	public void setIsAcadeUsed(Integer isAcadeUsed) {
		this.isAcadeUsed = isAcadeUsed;
	}
	public Integer getIsAcadeRequired() {
		return isAcadeRequired;
	}
	public void setIsAcadeRequired(Integer isAcadeRequired) {
		this.isAcadeRequired = isAcadeRequired;
	}
	public Integer getIsLastPositionUsed() {
		return isLastPositionUsed;
	}
	public void setIsLastPositionUsed(Integer isLastPositionUsed) {
		this.isLastPositionUsed = isLastPositionUsed;
	}
	public Integer getIsLastPositionRequired() {
		return isLastPositionRequired;
	}
	public void setIsLastPositionRequired(Integer isLastPositionRequired) {
		this.isLastPositionRequired = isLastPositionRequired;
	}
	public Integer getIsNationalCourseUsed() {
		return isNationalCourseUsed;
	}
	public void setIsNationalCourseUsed(Integer isNationalCourseUsed) {
		this.isNationalCourseUsed = isNationalCourseUsed;
	}
	public Integer getIsNationalCourseRequired() {
		return isNationalCourseRequired;
	}
	public void setIsNationalCourseRequired(Integer isNationalCourseRequired) {
		this.isNationalCourseRequired = isNationalCourseRequired;
	}
	public Integer getIsProvincialCourseUsed() {
		return isProvincialCourseUsed;
	}
	public void setIsProvincialCourseUsed(Integer isProvincialCourseUsed) {
		this.isProvincialCourseUsed = isProvincialCourseUsed;
	}
	public Integer getIsProvincialCourseRequired() {
		return isProvincialCourseRequired;
	}
	public void setIsProvincialCourseRequired(Integer isProvincialCourseRequired) {
		this.isProvincialCourseRequired = isProvincialCourseRequired;
	}
	public Integer getIsSchoolCourseUsed() {
		return isSchoolCourseUsed;
	}
	public void setIsSchoolCourseUsed(Integer isSchoolCourseUsed) {
		this.isSchoolCourseUsed = isSchoolCourseUsed;
	}
	public Integer getIsSchoolCourseRequired() {
		return isSchoolCourseRequired;
	}
	public void setIsSchoolCourseRequired(Integer isSchoolCourseRequired) {
		this.isSchoolCourseRequired = isSchoolCourseRequired;
	}
	public Integer getIsNationalPlanUsed() {
		return isNationalPlanUsed;
	}
	public void setIsNationalPlanUsed(Integer isNationalPlanUsed) {
		this.isNationalPlanUsed = isNationalPlanUsed;
	}
	public Integer getIsNationalPlanRequired() {
		return isNationalPlanRequired;
	}
	public void setIsNationalPlanRequired(Integer isNationalPlanRequired) {
		this.isNationalPlanRequired = isNationalPlanRequired;
	}
	public Integer getIsTextbookUsed() {
		return isTextbookUsed;
	}
	public void setIsTextbookUsed(Integer isTextbookUsed) {
		this.isTextbookUsed = isTextbookUsed;
	}
	public Integer getIsTextbookRequired() {
		return isTextbookRequired;
	}
	public void setIsTextbookRequired(Integer isTextbookRequired) {
		this.isTextbookRequired = isTextbookRequired;
	}
	public Integer getIsOtherTextbookUsed() {
		return isOtherTextbookUsed;
	}
	public void setIsOtherTextbookUsed(Integer isOtherTextbookUsed) {
		this.isOtherTextbookUsed = isOtherTextbookUsed;
	}
	public Integer getIsOtherTextbookRequired() {
		return isOtherTextbookRequired;
	}
	public void setIsOtherTextbookRequired(Integer isOtherTextbookRequired) {
		this.isOtherTextbookRequired = isOtherTextbookRequired;
	}
	public Integer getIsResearchUsed() {
		return isResearchUsed;
	}
	public void setIsResearchUsed(Integer isResearchUsed) {
		this.isResearchUsed = isResearchUsed;
	}
	public Integer getIsResearchRequired() {
		return isResearchRequired;
	}
	public void setIsResearchRequired(Integer isResearchRequired) {
		this.isResearchRequired = isResearchRequired;
	}
	public Integer getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(Integer isPublished) {
		this.isPublished = isPublished;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Timestamp getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Integer getFounderId() {
		return founderId;
	}
	public void setFounderId(Integer founderId) {
		this.founderId = founderId;
	}
	public Timestamp getGmtUpdate() {
		return gmtUpdate;
	}
	public void setGmtUpdate(Timestamp gmtUpdate) {
		this.gmtUpdate = gmtUpdate;
	}
	public Integer getMenderId() {
		return menderId;
	}
	public void setMenderId(Integer menderId) {
		this.menderId = menderId;
	}
	public Integer getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	public Integer getIsAllTextbookPublished() {
		return isAllTextbookPublished;
	}
	public void setIsAllTextbookPublished(Integer isAllTextbookPublished) {
		this.isAllTextbookPublished = isAllTextbookPublished;
	}
	public Integer getIsForceEnd() {
		return isForceEnd;
	}
	public void setIsForceEnd(Integer isForceEnd) {
		this.isForceEnd = isForceEnd;
	}
	public Long getUser_num() {
		return user_num;
	}
	public void setUser_num(Long user_num) {
		this.user_num = user_num;
	}
	
	
	
	
	
	
}
