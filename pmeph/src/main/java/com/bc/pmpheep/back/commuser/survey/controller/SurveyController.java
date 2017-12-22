package com.bc.pmpheep.back.commuser.survey.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.survey.service.SurveyService;
import com.bc.pmpheep.general.controller.BaseController;


@Controller
@RequestMapping(value="/survey")
public class SurveyController extends BaseController{
	
	@Autowired
	SurveyService surveyService; 
	
	
	//问卷列表
	@RequestMapping(value="/surveyList")
	public ModelAndView surveyList(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("commuser/survey/surveyList");
		return mv;
	}
	//填写问卷
	@RequestMapping(value="/writeSurvey")
	public ModelAndView writeSurvey(){
		ModelAndView mv = new ModelAndView();
		
		
		List<Map<String,Object>> list = surveyService.surveyList();
		mv.addObject("list",list);
		mv.addObject("listSize",list.size());
		mv.setViewName("commuser/survey/writeSurvey");
		return mv;
	}
}
