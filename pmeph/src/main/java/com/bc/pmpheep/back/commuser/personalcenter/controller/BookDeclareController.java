package com.bc.pmpheep.back.commuser.personalcenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.general.controller.BaseController;
/**
 * 我要出书-申报
 * @author hqf
 * @timer 2017/12/26
 */
@Controller
@RequestMapping("/bookdeclare/")
public class BookDeclareController extends BaseController{
	
	@RequestMapping("toBookdeclareAdd")
	public ModelAndView toBookdeclareAdd(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView mav = new ModelAndView("commuser/personalcenter/toBookdeclareAdd");
		return mav;
		
	}
}
