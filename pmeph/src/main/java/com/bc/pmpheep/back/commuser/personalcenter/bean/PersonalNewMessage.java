package com.bc.pmpheep.back.commuser.personalcenter.bean;

public class PersonalNewMessage {

	private String realname;//真实姓名
	private String sex;//性别
	private String rank;//用户级别，普通，专家
	private String avatar;//头像
	private String signature;//个性签名
	private String id;//主键
	private String org_id;//对应学校id
	private String email;//邮箱
	private String book_id;//书本ID
	private String book_name;//书名
	private String buy_url;//购买地址
	private String category_id;//内容类型
	private String image_url;//书评书本图片
	private String group_name;	//小组名称		
	private String group_image;//小组头像
	private String grouppeo;//小组人数
	private String gmt_create;//创建日期
	private String online_progress;//审核进度		0=未提交/1=已提交/2=被退回/3=通过	
	private String material_name;//教材名称
	private String gmt_update;//更新日期
	private String title;	//标题
	private String summary;//内容
	private String auth_status;//审核状态
	private String score;//评分
	private String content;//评价内容
	private String age_deadline;//deadline,报名截止日期
	private String exmember;//报名人数
	private String iamin;//用户是否报名，1已报名，0未报名
	private String is_staging;//是否暂存
	private String material_id;//教材ID
	
	
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBuy_url() {
		return buy_url;
	}
	public void setBuy_url(String buy_url) {
		this.buy_url = buy_url;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getGroup_image() {
		return group_image;
	}
	public void setGroup_image(String group_image) {
		this.group_image = group_image;
	}
	public String getGrouppeo() {
		return grouppeo;
	}
	public void setGrouppeo(String grouppeo) {
		this.grouppeo = grouppeo;
	}
	public String getGmt_create() {
		return gmt_create;
	}
	public void setGmt_create(String gmt_create) {
		this.gmt_create = gmt_create;
	}
	public String getOnline_progress() {
		return online_progress;
	}
	public void setOnline_progress(String online_progress) {
		this.online_progress = online_progress;
	}
	public String getMaterial_name() {
		return material_name;
	}
	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}
	public String getGmt_update() {
		return gmt_update;
	}
	public void setGmt_update(String gmt_update) {
		this.gmt_update = gmt_update;
	}
	public String getTitle() {
		return title;
	}
	public String getSummary() {
		return summary;
	}
	public String getAuth_status() {
		return auth_status;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public void setAuth_status(String auth_status) {
		this.auth_status = auth_status;
	}
	public String getScore() {
		return score;
	}
	public String getContent() {
		return content;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAge_deadline() {
		return age_deadline;
	}
	public String getExmember() {
		return exmember;
	}
	public void setAge_deadline(String age_deadline) {
		this.age_deadline = age_deadline;
	}
	public void setExmember(String exmember) {
		this.exmember = exmember;
	}
	public String getIamin() {
		return iamin;
	}
	public void setIamin(String iamin) {
		this.iamin = iamin;
	}
	public String getIs_staging() {
		return is_staging;
	}
	public void setIs_staging(String is_staging) {
		this.is_staging = is_staging;
	}
	public String getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}

}
