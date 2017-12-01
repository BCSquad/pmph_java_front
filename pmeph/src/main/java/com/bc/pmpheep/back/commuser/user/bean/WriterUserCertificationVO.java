package com.bc.pmpheep.back.commuser.user.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @introduction WriterUserCertificationVO 作家用户教师资格认证表
 * 
 * @author tyc
 * 
 * @createDate 2017年11月30日 上午9:47:00
 * 
 */
@SuppressWarnings("serial")
@Alias("WriterUserCertificationVO")
public class WriterUserCertificationVO implements Serializable {
    // 主键
    private Long id;
    // 作家id
    private Long userId;
    // 真实姓名
    private String realName;
    // 对应学校id
    private Long orgId;
    // 机构名称
    private String orgName;
    // 手机
    private String handphone;
    // 身份证
    private String idcard;
    // 认证进度 --- 0=未提交/1=已提交/2=被退回/3=通过
    private Integer progress;
    // 教师资格证--- 资格证图片的资源地址
    private MultipartFile cert;
    // 创建时间
    private Timestamp gmtCreate;
    // 修改时间
    private Timestamp gmtUpdate;

    public WriterUserCertificationVO() {
    }

    public WriterUserCertificationVO(Long userId, Integer progress) {
        this.userId = userId;
        this.progress = progress;
    }

    public WriterUserCertificationVO(Long id) {
        super();
        this.id = id;
    }

    public WriterUserCertificationVO(Long userId, Long orgId, String handphone, String idcard,
    		Integer progress, MultipartFile cert, Timestamp gmtCreate, Timestamp gmtUpdate) {
        this.userId = userId;
        this.orgId = orgId;
        this.handphone = handphone;
        this.idcard = idcard;
        this.progress = progress;
        this.cert = cert;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getHandphone() {
		return handphone;
	}

	public void setHandphone(String handphone) {
		this.handphone = handphone;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public MultipartFile getCert() {
		return cert;
	}

	public void setCert(MultipartFile cert) {
		this.cert = cert;
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
		return "WriterUserCertificationVO [id=" + id + ", userId=" + userId
				+ ", realName=" + realName + ", orgId=" + orgId + ", orgName="
				+ orgName + ", handphone=" + handphone + ", idcard=" + idcard
				+ ", progress=" + progress + ", cert=" + cert + ", gmtCreate="
				+ gmtCreate + ", gmtUpdate=" + gmtUpdate + "]";
	}

    
}