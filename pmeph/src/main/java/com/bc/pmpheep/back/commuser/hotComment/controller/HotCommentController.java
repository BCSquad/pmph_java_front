package com.bc.pmpheep.back.commuser.hotComment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.hotComment.service.HotCommentService;
import com.bc.pmpheep.general.controller.BaseController;



@Controller
@RequestMapping("hotComment")
public class HotCommentController extends BaseController{
	
	@Autowired
	HotCommentService  hotCommentService;
	
	//去热门书评列表页面
	@RequestMapping(value="hotCommentList")
	public ModelAndView toHotCommentList(){
		ModelAndView mv = new ModelAndView();
		List<Map<String,Object>> listComment = hotCommentService.selectHotCommentList();
		
		
		
		
		
		mv.setViewName("commuser/hotComment/hotCommentList");
		return mv;
	}
}
