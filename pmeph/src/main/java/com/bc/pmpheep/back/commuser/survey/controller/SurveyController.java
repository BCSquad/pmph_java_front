package com.bc.pmpheep.back.commuser.survey.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.survey.service.SurveyService;
import com.bc.pmpheep.general.controller.BaseController;


@Controller
@RequestMapping(value="/survey")
public class SurveyController extends BaseController{
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.survey.service.SurveyServiceImpl")
	SurveyService surveyService; 
	
	
	//问卷列表
	@RequestMapping(value="/surveyList")
	public ModelAndView surveyList(){
		ModelAndView mv = new ModelAndView();
		List<Map<String,Object>> list = surveyService.surveyList();
		mv.addObject("list",list);
		mv.addObject("listSize",list.size());
		mv.setViewName("commuser/survey/surveyList");
		return mv;
	}
	//跳转到某一个填写问卷页面
	@RequestMapping(value="/writeSurvey")
	public ModelAndView writeSurvey(){
		ModelAndView mv = new ModelAndView();
		long surveyId = 1L;
		//查询该调查包含的所有题目
		List<Map<String,Object>> list = surveyService.getSurvey(surveyId);
		List<Map<String,Object>> listSesult = new ArrayList<Map<String,Object>>();
		if(list.size()>0){
			//遍历题目
			for(Map<String,Object> question : list){
				//查询每一个单选和多选题对应的选项
				if(question.get("type").equals(1)||question.get("type").equals(2)){
					String questionIdStr = question.get("id").toString();
					if(null!=questionIdStr&&!questionIdStr.equals("")){
						long questionId = Long.valueOf(questionIdStr).longValue();
						List<Map<String,Object>> listOptions = surveyService.getOptions(questionId);
						question.put("listOptions", listOptions);
						listSesult.add(question);
					}
				}else{
					listSesult.add(question);
				}
				
			}
		}
		
		mv.addObject("listSesult",listSesult);
		mv.addObject("listSize",listSesult.size());
		mv.setViewName("commuser/survey/writeSurvey");
		return mv;
	}
	
	//填写问卷答案
	@RequestMapping(value="/addSurveyAnswers")
	public String addSurveyAnswers(HttpServletRequest request){
		Map<String, Object> writerUser = this.getUserInfo();
		//Long userId = new Long(String.valueOf(writerUser.get("id")));
		long userId = 1L;
		//先获取所有单选的name集合
		String radios[] = request.getParameterValues("radioValues");
		//单选问题id集合
		String questionIds[] = request.getParameterValues("questionIds");
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		map.put("userId", userId);
		map1.put("userId", userId);
		map2.put("userId", userId);
		Date date = new Date();
		map.put("date", date);
		map1.put("date", date);
		map2.put("date", date);
		//遍历单选name
		for(int i=0;i<radios.length;i++){
				//取出每一道单选题的答案
				String radioAnswer = request.getParameter(radios[i]);
				map.put("optionId", radioAnswer);
				
			for(int j =0;j<questionIds.length;j++){
				map.put("questionId", questionIds[i]);
			}
			
			//保存答案的方法
			//surveyService.save(map);
		}
		
		//先获取所有多选的name集合
		String checkboxs[] = request.getParameterValues("checkboxValues");
		//多选问题id集合
		String checkboxQuestionIds[] = request.getParameterValues("checkboxQuestionIds");
		//遍历多选name
		for(int m = 0;m<checkboxs.length;m++){
				//取出每一道多选题的答案
				String checkbox[] = request.getParameterValues(checkboxs[m]);
				String checkboxAnswer = "";
				//拼接多选答案
				for(int a=0;a<checkbox.length;a++){
					if(a<=checkbox.length-1){
						checkboxAnswer=checkboxAnswer+checkbox[a]+",";
					}else{
						checkboxAnswer=checkboxAnswer+checkbox[a];
					}
				}
				map1.put("checkboxAnswer", checkboxAnswer);
				for(int n=0;n<checkboxQuestionIds.length;n++){
					map1.put("questionId", checkboxQuestionIds[m]);
				}
				//surveyService.save(map);
				//保存答案的方法
		}
		
		//先获取所有输入框的name集合
		String input[] = request.getParameterValues("inputValues");
		//输入框问题id集合
		String inputQuestionIds[] = request.getParameterValues("inputQuestionIds");
		for(int x=0;x<inputQuestionIds.length;x++){
			map2.put("questionId", inputQuestionIds[x]);
			for(int y=0;y<input.length;y++){
				String inputValue = request.getParameter(input[y]);
				map2.put("inputValue", inputValue);
			}
			
			
		}
		String code = "OK";
		return code;
	}
}
