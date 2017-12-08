package com.bc.pmpheep.back.commuser.personalcenter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public ModelAndView moveout() {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> permap=this.getUserInfo();//个人信息
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
	public ModelAndView moveoutone() {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> permap=this.getUserInfo();//个人信息
		List<PersonalNewMessage> listmycol=personalService.queryMyCol(permap);//我的收藏
		List<PersonalNewMessage> listmyfriend=personalService.queryMyFriend(permap);//我的好友
		List<PersonalNewMessage> listbookjoins=personalService.queryMyBooksJoin(permap);//教材申报最新消息
		modelAndView.addObject("permap",permap );
		modelAndView.addObject("listmycol",listmycol );
		modelAndView.addObject("listmyfriend",listmyfriend );
		modelAndView.addObject("listbookjoins",listbookjoins);
		modelAndView.setViewName("commuser/personalcenter/PersonalHomeOne");
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
