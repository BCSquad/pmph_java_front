package com.bc.pmpheep.back.commuser.personalcenter.bean;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;


/**
 * 个人中心 用户动态表  writer_user_trendst 对应实体类 写入用
 * @author liudi
 *
 */

public class WriterUserTrendst {
	private String id = null;
	private String user_id = null;
	private Integer is_public = 1;
	private Integer type = null;
	private String detail = null;
	private String cms_content_id = null;
	private String book_id = null;
	private String book_comment_id = null;
	
	/**
	 * 产生一条1到7类型的动态写入pojo
	 * @param user_id 动态人
	 * @param type 动态类型 见@type
	 * @type 0=其他(非后面任何一项)/1=发表文章/2=文章评论/3=文章收藏/4=文章点赞/5=发表书评/6=图书收藏/7=图书点赞/8=教材申报/9=我要出书/10=图书纠错/11=问卷调查
	 * @param content_id 相应内容id 
	 */
	public WriterUserTrendst(String user_id, Integer type, String content_id) {
		super();
		this.user_id = user_id;
		this.type = type;
		
		if (type>=1&&type<=4) { //content_id 为文章id
			this.cms_content_id = content_id;
		}else if(type==5){ //content_id 为图书评论id
			this.book_comment_id = content_id;
		}else if(type==6|| type==7){ //content_id 为图书id
			this.book_id = content_id;
		}else if(type==8|| type==9 || type==11 ){ // content_id material topic survey 等的id
			this.book_id = content_id;
		}else if(type==10){ //content_id 为图书id
			this.book_id = content_id;
			
		}else if(type==0){ //content_id 不保存
			
		}
		
		if (type > 7) {
			this.is_public = 0;
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Integer getIs_public() {
		return is_public;
	}
	public void setIs_public(Integer is_public) {
		this.is_public = is_public;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * json格式的set方法
	 * @param title 动态标题
	 * @param content 动态内容
	 * @param img 1 笑脸 2 哭脸
	 */
	public void setDetail(String title,String content,int img) {
		this.detail = "{\"title\":\""+title+"\",\"content\":\""+content+"\",\"img\":"+img+"}";
	}
	/**
	 * json格式的set方法
	 * @param title 动态标题
	 * @param content 动态内容
	 * @param img 1 笑脸 2 哭脸
	 * @param page 页 line 行
	 */
	public void setDetail(String title,String content,int img,int page,int line) {
		this.detail = "{\"title\":\""+title+"\",\"content\":{\"page\":"+page+",\"line\":"+line+",\"content\":\""+((content.length()>100)?content.substring(0, 99):content)+"\"},\"img\":"+img+"}";
	}
	
	/**
	 * 教材申报审核 的动态详情生成方法
	 * @param dmap 申报
	 * @param online_progress 0=未提交/1=已提交但是待审核/2=被申报单位退回/3=申报单位通过/4=出版社退回申报单位/5=出版社退回个人
	 */
	public void declarationAuditDetail(Map<String,Object> dmap,String online_progress) {
		String title = "";
		String content = "";
		String img = "";
		
		if ("2".equals(online_progress)) { //被申报单位退回
			title = "教材申报退回";
			img = "2";
			content = "抱歉！您于"+dmap.get("create_time").toString()+"提交的《"+dmap.get("material_name").toString()+"》申报表被 \\\""+dmap.get("org_name").toString()+"\\\" 退回,退回原因："+dmap.get("return_cause").toString()+",请您核对后重新提交!";
		}else if("3".equals(online_progress)){ //申报单位通过
			title = "教材申报通过单位审核";
			img = "1";
			content = "恭喜！您于"+dmap.get("create_time").toString()+"提交的《"+dmap.get("material_name").toString()+"》申报表通过 \\\""+dmap.get("org_name").toString()+"\\\" 审核,请耐心等待出版社审核。";
		}
		
		this.is_public = 0;
		this.detail = "{\"title\":\""+title+"\",\"content\":\""+content+"\",\"img\":"+img+"}";
		
	}
	
	public String getCms_content_id() {
		return cms_content_id;
	}
	public void setCms_content_id(String cms_content_id) {
		this.cms_content_id = cms_content_id;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getBook_comment_id() {
		return book_comment_id;
	}
	public void setBook_comment_id(String book_comment_id) {
		this.book_comment_id = book_comment_id;
	}

	
	
	
}
