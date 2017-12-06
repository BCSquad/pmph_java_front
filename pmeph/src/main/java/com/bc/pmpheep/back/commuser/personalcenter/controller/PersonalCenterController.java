package com.bc.pmpheep.back.commuser.personalcenter.controller;

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
	
	@RequestMapping("/tohomepage")
	public ModelAndView move() {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> permap=this.getUserInfo();//个人信息
		List<PersonalNewMessage> listmycol=personalService.queryMyCol();//我的收藏
		List<PersonalNewMessage> listmyfriend=personalService.queryMyFriend();//我的好友
		List<PersonalNewMessage> listmygroup=personalService.queryMyGroup();//我的小组
		List<PersonalNewMessage> listmyofeernew=personalService.queryMyOfeerNew();//我的申请动态最新消息
		List<PersonalNewMessage> listmywritingsnew=personalService.queryMyWritingsNew();//我的随笔文章动态最新消息
		List<PersonalNewMessage> listmybooknews=personalService.queryMyBooksNew();//我的书评消息动态最新消息
		List<PersonalNewMessage> listbookjoins=personalService.queryMyBooksJoin();//教材申报最新消息
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
	
	
	@RequestMapping("/tohomepageout")
	public ModelAndView moveout() {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> permap=this.getUserInfo();//个人信息
		List<PersonalNewMessage> listmycol=personalService.queryMyCol();//我的收藏
		List<PersonalNewMessage> listmyfriend=personalService.queryMyFriend();//我的好友
		List<PersonalNewMessage> listmywritingsnew=personalService.queryMyWritingsNew();//我的随笔文章动态最新消息
		List<PersonalNewMessage> listmybooknews=personalService.queryMyBooksNew();//我的书评消息动态最新消息
		modelAndView.addObject("permap",permap );
		modelAndView.addObject("listmycol",listmycol );
		modelAndView.addObject("listmyfriend",listmyfriend );
		modelAndView.addObject("listmywritingsnew",listmywritingsnew);
		modelAndView.addObject("listmybooknews",listmybooknews);
		modelAndView.setViewName("commuser/personalcenter/PersonalHomeOut");
		return modelAndView;
	}
	
	@RequestMapping("/tohomepageone")
	public ModelAndView moveoutone() {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> permap=this.getUserInfo();//个人信息
		List<PersonalNewMessage> listmycol=personalService.queryMyCol();//我的收藏
		List<PersonalNewMessage> listmyfriend=personalService.queryMyFriend();//我的好友
		List<PersonalNewMessage> listbookjoins=personalService.queryMyBooksJoin();//教材申报最新消息
		modelAndView.addObject("permap",permap );
		modelAndView.addObject("listmycol",listmycol );
		modelAndView.addObject("listmyfriend",listmyfriend );
		modelAndView.addObject("listbookjoins",listbookjoins);
		modelAndView.setViewName("commuser/personalcenter/PersonalHomeOne");
		return modelAndView;
	}
	
	@RequestMapping("/tohomepagetwo")
	public ModelAndView moveouttwo() {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> permap=this.getUserInfo();//个人信息
		List<PersonalNewMessage> listmycol=personalService.queryMyCol();//我的收藏
		List<PersonalNewMessage> listmyfriend=personalService.queryMyFriend();//我的好友
		List<PersonalNewMessage> listmywritingsnew=personalService.queryMyWritingsNew();//我的随笔文章动态最新消息
		modelAndView.addObject("permap",permap );
		modelAndView.addObject("listmycol",listmycol );
		modelAndView.addObject("listmyfriend",listmyfriend );
		modelAndView.addObject("listmywritingsnew",listmywritingsnew);
		modelAndView.setViewName("commuser/personalcenter/PersonalHomeTwo");
		return modelAndView;
	}
	
	@RequestMapping("/tohomepagethe")
	public ModelAndView moveoutthe() {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> permap=this.getUserInfo();//个人信息
		List<PersonalNewMessage> listmycol=personalService.queryMyCol();//我的收藏
		List<PersonalNewMessage> listmyfriend=personalService.queryMyFriend();//我的好友
		List<PersonalNewMessage> listmybooknews=personalService.queryMyBooksNew();//我的书评消息动态最新消息
		modelAndView.addObject("permap",permap );
		modelAndView.addObject("listmycol",listmycol );
		modelAndView.addObject("listmyfriend",listmyfriend );
		modelAndView.addObject("listmybooknews",listmybooknews);
		modelAndView.setViewName("commuser/personalcenter/PersonalHomeThere");
		return modelAndView;
	}
	
}
