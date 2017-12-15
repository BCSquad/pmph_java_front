package com.bc.pmpheep.back.commuser.personalcenter.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;
import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
import com.bc.pmpheep.general.controller.BaseController;

//首页controller
@Controller
@RequestMapping("/personalhomepage")
public class PersonalCenterController extends BaseController {
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
	private PersonalService personalService;
	
	@RequestMapping("/tohomepage")//个人中心动态
	public ModelAndView move() {

		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> permap=this.getUserInfo();//个人信息
		List<PersonalNewMessage> listmycol=personalService.queryMyCol(permap);//我的收藏
		List<PersonalNewMessage> listmyfriend=personalService.queryMyFriend(permap);//我的好友
		List<PersonalNewMessage> listmygroup=personalService.queryMyGroup(permap);//我的小组
		List<PersonalNewMessage> listmyofeernew=personalService.queryMyOfeerNew(permap);//我的申请动态最新消息
		List<PersonalNewMessage> listmywritingsnew=personalService.queryMyWritingsNew(permap);//我的随笔文章动态最新消息
		List<PersonalNewMessage> listmybooknews=personalService.queryMyBooksNew(permap);//我的书评消息动态最新消息
		List<PersonalNewMessage> listbookjoins=personalService.queryMyBooksJoin(permap);//教材申报最新消息
		modelAndView.addObject("permap",permap );
		modelAndView.addObject("listmycol",listmycol );
		modelAndView.addObject("listmyfriend",listmyfriend );
		modelAndView.addObject("listmygroup",listmygroup );
		modelAndView.addObject("listmyofeernew",listmyofeernew);
		modelAndView.addObject("listmywritingsnew",listmywritingsnew);
		modelAndView.addObject("listmybooknews",listmybooknews);
		modelAndView.addObject("listbookjoins",listbookjoins);
		modelAndView.setViewName("commuser/personalcenter/PersonalHome");
		return modelAndView;
	}
	
	
	@RequestMapping("/tohomepageout")//个人中心展示页面
	public ModelAndView moveout(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String uid= request.getParameter("uid");
		Map<String, Object> permap=this.getUserInfo();;//个人信息
		permap.put("id", uid);
		List<PersonalNewMessage> listmycol=personalService.queryMyCol(permap);//我的收藏
		List<PersonalNewMessage> listmyfriend=personalService.queryMyFriend(permap);//我的好友
		List<PersonalNewMessage> listmywritingsnew=personalService.queryMyWritingsNew(permap);//我的随笔文章动态最新消息
		List<PersonalNewMessage> listmybooknews=personalService.queryMyBooksNew(permap);//我的书评消息动态最新消息
		modelAndView.addObject("permap",permap );
		modelAndView.addObject("listmycol",listmycol );
		modelAndView.addObject("listmyfriend",listmyfriend );
		modelAndView.addObject("listmywritingsnew",listmywritingsnew);
		modelAndView.addObject("listmybooknews",listmybooknews);
		modelAndView.setViewName("commuser/personalcenter/PersonalHomeOut");
		return modelAndView;
	}
	
	@RequestMapping("/tohomepageone")//个人中心教材申报列表
	public ModelAndView moveoutone(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String bookname = null;
		if(request.getParameter("bookname")!=null){
		try {
			bookname = new String(request.getParameter("bookname").getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 		}
		String s=request.getParameter("s");
		String pageinfo= request.getParameter("pageinfo");
		String dateinfo= request.getParameter("dateinfo");
		String online_progress= request.getParameter("online_progress");
		String is_staging= request.getParameter("is_staging");
		Map<String, Object> permap=this.getUserInfo();//个人信息
		List<PersonalNewMessage> listmycol=personalService.queryMyCol(permap);//我的收藏
		List<PersonalNewMessage> listmyfriend=personalService.queryMyFriend(permap);//我的好友
		permap.put("s", s);
		permap.put("pageinfo", pageinfo);
		permap.put("dateinfo", dateinfo);
		permap.put("online_progress", online_progress);
		permap.put("is_staging", is_staging);
		permap.put("bookname", bookname);
		List<PersonalNewMessage> listbookjoins=personalService.queryMyBooksJoin(permap);//教材申报最新消息
		modelAndView.addObject("permap",permap );
		modelAndView.addObject("listmycol",listmycol );
		modelAndView.addObject("listmyfriend",listmyfriend );
		modelAndView.addObject("listbookjoins",listbookjoins);
		modelAndView.setViewName("commuser/personalcenter/PersonalHomeOne");
		modelAndView.addObject("serchbox", bookname);
		return modelAndView;
	}
	
	@RequestMapping("/tohomepageonelist")//我的申请个人中心教材申报列表
	public ModelAndView moveoutone1(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String bookname = null;
		if(request.getParameter("bookname")!=null){
		try {
			bookname = new String(request.getParameter("bookname").getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 		}
		String s="1";
		String pageinfo= request.getParameter("pageinfo");
		String dateinfo= request.getParameter("dateinfo");
		String online_progress= request.getParameter("online_progress");
		String is_staging= request.getParameter("is_staging");
		Map<String, Object> permap=this.getUserInfo();//个人信息
		List<PersonalNewMessage> listmycol=personalService.queryMyCol(permap);//我的收藏
		List<PersonalNewMessage> listmyfriend=personalService.queryMyFriend(permap);//我的好友
		permap.put("s", s);
		permap.put("pageinfo", pageinfo);
		permap.put("dateinfo", dateinfo);
		permap.put("online_progress", online_progress);
		permap.put("is_staging", is_staging);
		permap.put("bookname", bookname);
		List<PersonalNewMessage> listbookjoins=personalService.queryMyBooksJoin(permap);//教材申报最新消息
		modelAndView.addObject("permap",permap );
		modelAndView.addObject("listmycol",listmycol );
		modelAndView.addObject("listmyfriend",listmyfriend );
		modelAndView.addObject("listbookjoins",listbookjoins);
		modelAndView.setViewName("commuser/personalcenter/PersonalHomeOneMy");
		modelAndView.addObject("serchbox", bookname);
		return modelAndView;
	}
	
	
	
	@RequestMapping("/tohomepagetwo")//个人中心随笔文章列表
	public ModelAndView moveouttwo() {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> permap=this.getUserInfo();//个人信息
		List<PersonalNewMessage> listmycol=personalService.queryMyCol(permap);//我的收藏
		List<PersonalNewMessage> listmyfriend=personalService.queryMyFriend(permap);//我的好友
		List<PersonalNewMessage> listmywritingsnew=personalService.queryMyWritingsNew(permap);//我的随笔文章动态最新消息
		modelAndView.addObject("permap",permap );
		modelAndView.addObject("listmycol",listmycol );
		modelAndView.addObject("listmyfriend",listmyfriend );
		modelAndView.addObject("listmywritingsnew",listmywritingsnew);
		modelAndView.setViewName("commuser/personalcenter/PersonalHomeTwo");
		return modelAndView;
	}
	
	@RequestMapping("/tohomepagethe")//个人中心我的书评列表
	public ModelAndView moveoutthe() {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> permap=this.getUserInfo();//个人信息
		List<PersonalNewMessage> listmycol=personalService.queryMyCol(permap);//我的收藏
		List<PersonalNewMessage> listmyfriend=personalService.queryMyFriend(permap);//我的好友
		List<PersonalNewMessage> listmybooknews=personalService.queryMyBooksNew(permap);//我的书评消息动态最新消息
		modelAndView.addObject("permap",permap );
		modelAndView.addObject("listmycol",listmycol );
		modelAndView.addObject("listmyfriend",listmyfriend );
		modelAndView.addObject("listmybooknews",listmybooknews);
		modelAndView.setViewName("commuser/personalcenter/PersonalHomeThere");
		return modelAndView;
	}
	
}
