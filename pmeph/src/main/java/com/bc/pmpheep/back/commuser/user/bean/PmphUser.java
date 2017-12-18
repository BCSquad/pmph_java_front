package com.bc.pmpheep.back.commuser.user.bean;

// Generated 2017-9-11 1:19:21 by Hibernate Tools 4.3.1

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * PmphUser 实体类
 */
@SuppressWarnings("serial")
@Alias("PmphUser")
public class PmphUser implements java.io.Serializable {
    /**
     * 主键
     */
    private Long    id;
    /**
     * 用户名
     */
    private String  username;
    /**
     * 密码
     */
    private String  password;
    /**
     * 是否禁用
     */
    private Boolean isDisabled;
    /**
     * 真实姓名
     */
    private String  realname;
    /**
     * 部门id
     */
    private Long    departmentId;
    /**
     * 手机
     */
    private String  handphone;
    /**
     * 邮箱
     */
    private String  email;
    /**
     * 头像
     */
    private String  avatar;
    /**
     * 备注
     */
    private String  note;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 是否逻辑删除
     */
    private Boolean isDeleted;
    /**
     * 创建时间
     */
    private Date    gmtCreate;
    /**
     * 修改时间
     */
    private Date    gmtUpdate;
    /**
     * 登陆类型
     */
    private Short   loginType;
    /**
     * 是否为管理员
     */
    private Boolean isAdmin = false;

    public PmphUser() {
    }

    public PmphUser(Long id) {
        this.id = id;
    }

    public PmphUser(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public PmphUser(String username, String password, Boolean isDisabled, String realname,
    Long departmentId, String handphone, String email, String avatar, String note, Integer sort,
    Boolean isDeleted, Date gmtCreate, Date gmtUpdate, Short loginType) {
        this.username = username;
        this.password = password;
        this.isDisabled = isDisabled;
        this.realname = realname;
        this.departmentId = departmentId;
        this.handphone = handphone;
        this.email = email;
        this.avatar = avatar;
        this.note = note;
        this.sort = sort;
        this.isDeleted = isDeleted;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.loginType = loginType;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return this.realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Long getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getHandphone() {
        return this.handphone;
    }

    public void setHandphone(String handphone) {
        this.handphone = handphone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getGmtCreate() {
        return this.gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return this.gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Boolean getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * @return the loginType
     */
    public Short getLoginType() {
        return loginType;
    }

    /**
     * @param loginType the loginType to set
     */
    public void setLoginType(Short loginType) {
        this.loginType = loginType;
    }

    /**
     * @return the isAdmin
     */
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "PmphUser [id=" + id + ", username=" + username + ", password=" + password
               + ", isDisabled=" + isDisabled + ", realname=" + realname + ", departmentId="
               + departmentId + ", handphone=" + handphone + ", email=" + email + ", avatar="
               + avatar + ", note=" + note + ", sort=" + sort + ", isDeleted=" + isDeleted
               + ", gmtCreate=" + gmtCreate + ", gmtUpdate=" + gmtUpdate + ", loginType="
               + loginType + "]";
    }

}
