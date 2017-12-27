package com.bc.pmpheep.back.authadmin.accountset.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * @Author: SuiXinYang
 * @Description:
 * @Date: Created in 11:51 2017/11/30
 * @Modified: SuiXinYang
 **/
public class OrgAdminUser implements Serializable{
    //主键ID
    private Long id;
    //机构ID
    private Long orgId;
    //密码
    private String password;
    //管理员姓名
    private String realName;
    //性别
    private int sex;
    //职位
    private String position;
    //职称
    private String title;
    //传真
    private String fax;
    //手机
    private String handphone;
    //电话
    private String telephone;
    //身份证
    private String idCard;
    //邮箱
    private String email;
    //地址
    private String address;
    //邮编
    private String postCode;
    //是否上传委托书
    private int isProxyUpload;
    //委托书（mongoDB）
    private String proxy;
    //审核进度
    private int progress;
    //审核通过时间
    private Timestamp reviewDate;
    //备注
    private String note;
    //排序
    private int sort;
    //是否被逻辑删除
    private int isDeleted;
    
    /**
     * 生日
     */
    private Date    birthday;
    /**
     * 教龄
     */
    private int experience;


	/**
     * 教龄
     */
    private String workplace;
    
    public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}





	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHandphone() {
        return handphone;
    }

    public void setHandphone(String handPhone) {
        this.handphone = handPhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public int getIsProxyUpload() {
        return isProxyUpload;
    }

    public void setIsProxyUpload(int isProxyUpload) {
        this.isProxyUpload = isProxyUpload;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Timestamp getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Timestamp reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
