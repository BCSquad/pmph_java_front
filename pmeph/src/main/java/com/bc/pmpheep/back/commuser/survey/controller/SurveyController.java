package com.bc.pmpheep.back.commuser.survey.controller;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterUserTrendst;
import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
import com.bc.pmpheep.back.commuser.survey.service.SurveyService;
import com.bc.pmpheep.general.controller.BaseController;

@Controller
@RequestMapping(value = "/survey")
public class SurveyController extends BaseController {

	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.survey.service.SurveyServiceImpl")
	SurveyService           surveyService;

	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
	private PersonalService personalService;

	// 问卷列表
	@RequestMapping(value = "/surveyList")
	public ModelAndView surveyList() {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> writerUser = this.getUserInfo();
		Long userId = new Long(String.valueOf(writerUser.get("id")));
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = sdf.format(date);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("dateStr", dateStr);
		List<Map<String, Object>> list = surveyService.surveyList(map);
		mv.addObject("list", list);
		mv.addObject("listSize", list.size());
		mv.setViewName("commuser/survey/surveyList");
		return mv;
	}

	// 跳转到某一个填写问卷页面
	@RequestMapping(value = "/writeSurvey")
	public ModelAndView writeSurvey(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String surveyIdStr = request.getParameter("surveyId");
		long surveyId = new Long(surveyIdStr);
		// 获取调查基本信息
		Map<String, Object> mapSurvey = surveyService.getSurveyBaseInfo(surveyId);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String logUserId = getUserInfo().get("id").toString();
		paraMap.put("logUserId", logUserId);
		paraMap.put("surveyId", surveyId);
		// 查询该调查包含的所有题目
		List<Map<String, Object>> list = surveyService.getSurvey(surveyId);
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		if (list.size() > 0) {
			// 遍历题目
			for (Map<String, Object> question : list) {
				// 查询每一个单选和多选题对应的选项
				if (question.get("type").equals(1) || question.get("type").equals(2)) {
					String questionIdStr = question.get("id").toString();
					if (null != questionIdStr && !questionIdStr.equals("")) {
						long questionId = Long.valueOf(questionIdStr).longValue();
						List<Map<String, Object>> listOptions =
								surveyService.getOptions(questionId);
						// 查询单选答案
						paraMap.put("questionId", questionId);
						String answer = personalService.getAnswers(paraMap);
						// 查询多选答案
						List<Map<String, Object>> che  = personalService.getCheckAnswers(paraMap);
						boolean flag = false;
						if (null != che) {
							for (Map<String, Object> ch : che) {
								for (Map<String, Object> opt : listOptions) {

									if ((ch.get("option_id").toString()).equals(opt.get("id").toString())) {
										flag = true;
										opt.put("flag", flag);

									} else {
										flag = false;
									}
								}
							}
						}
						question.put("listOptions", listOptions);
						question.put("answer", answer);
						listResult.add(question);
					}
				} else if (question.get("type").equals(4) || question.get("type").equals(5)) {
					// 查询填空答案
					String questionIdStr = question.get("id").toString();
					if (null != questionIdStr && !questionIdStr.equals("")) {
						long questionId = Long.valueOf(questionIdStr).longValue();
						paraMap.put("questionId", questionId);
						String inp = personalService.getInpAnswers(paraMap);
						question.put("inp", inp);
						listResult.add(question);
					}
				} else {
					listResult.add(question);
				}

			}
		}
		Map<String, Object> btn = personalService.btnSaveOrHidden(paraMap);
		mv.addObject("btn", btn);
		mv.addObject("logUserId", logUserId);
		mv.addObject("mapSurvey", mapSurvey);
		mv.addObject("listSesult", listResult);
		mv.addObject("surveyId", surveyId);
		mv.addObject("listSize", listResult.size());
		mv.setViewName("commuser/survey/writeSurvey");
		return mv;
	}

	// 填写问卷答案
	@RequestMapping(value = "/addSurveyAnswers", method = RequestMethod.POST)
	@ResponseBody
	public String addSurveyAnswers(HttpServletRequest request) {
		Map<String, Object> writerUser = this.getUserInfo();
		Long userId = new Long(String.valueOf(writerUser.get("id")));
		String surveyId = request.getParameter("surveyId");
		// 先获取所有单选的name集合
		String radios[] = request.getParameterValues("radioValues");
		// 单选问题id集合
		String questionIds[] = request.getParameterValues("questionIds");
		Map<String, Object> map = new HashMap<String, Object>(); // 存放单选
		Map<String, Object> map1 = new HashMap<String, Object>();// 存放多选
		Map<String, Object> map2 = new HashMap<String, Object>();// 存放输入框
		Map<String, Object> map3 = new HashMap<String, Object>();// 存放文本框
		map.put("userId", userId);
		map.put("surveyId", surveyId);
		map1.put("userId", userId);
		map1.put("surveyId", surveyId);
		map2.put("userId", userId);
		map2.put("surveyId", surveyId);
		map3.put("userId", userId);
		map3.put("surveyId", surveyId);
		// 遍历单选name
		if (null != radios) {

			for (int i = 0; i < radios.length; i++) {
				// 取出每一道单选题的答案
				String radioAnswer = request.getParameter(radios[i]);
				map.put("optionId", radioAnswer);
				if (null != questionIds) {
					for (int j = 0; j < questionIds.length; j++) {
						map.put("questionId", questionIds[i]);
					}
				}
				// 保存单选答案的方法
				surveyService.saveRadioAnswer(map);
			}

		}
		// 先获取所有多选的name集合
		String checkboxs[] = request.getParameterValues("checkboxValues");
		// 多选问题id集合
		String checkboxQuestionIds[] = request.getParameterValues("checkboxQuestionIds");
		// 遍历多选name
		if (null != checkboxs) {
			for (int m = 0; m < checkboxs.length; m++) {
				// 取出每一道多选题的答案
				String checkbox[] = request.getParameterValues(checkboxs[m]);
				// String checkboxAnswer = "";
				// //拼接多选答案
				// for(int a=0;a<checkbox.length;a++){
				// if(a<checkbox.length-1){
				// checkboxAnswer=checkboxAnswer+checkbox[a]+",";
				// }else{
				// checkboxAnswer=checkboxAnswer+checkbox[a];
				// }
				// }
				// map1.put("checkboxAnswer", checkboxAnswer);
				if (null != checkboxQuestionIds) {
					// for (int n = 0; n < checkboxQuestionIds.length; n++) {
					map1.put("questionId", checkboxQuestionIds[m]);
					// }
				}
				// //拼接的多选答案是String类型，只能放在答案内容字段
				// map1.put("checkboxAnswer", checkboxAnswer);
				map1.put("optionIds", checkbox);
				// 保存答案的方法,
				surveyService.saveCheckboxAnswer(map1);
			}
		}
		// 先获取所有输入框的name集合

		String input[] = request.getParameterValues("inputValues");
		// 输入框问题id集合
		String[] inputQuestionIds = request.getParameterValues("inputQuestionIds");
		if (null != inputQuestionIds) {
			for (int x = 0; x < inputQuestionIds.length; x++) {
				map2.put("questionId", inputQuestionIds[x]);
				for (int y = 0; y < input.length; y++) {
					String inputValue = request.getParameter(input[y]);
					map2.put("inputValue", inputValue);
				}
				surveyService.saveInputAnswer(map2);
			}
		}

		// 获取所有文本框的name集合
		String textValues[] = request.getParameterValues("textValues");
		// 输入框问题id集合
		String[] textQuestionIds = request.getParameterValues("textQuestionIds");
		if (null != textQuestionIds) {
			for (int x = 0; x < textQuestionIds.length; x++) {
				map3.put("questionId", textQuestionIds[x]);
				if (null != textValues) {
					for (int y = 0; y < textValues.length; y++) {
						String textValue = request.getParameter(textValues[y]);
						map3.put("inputValue", textValue);
					}
				}
				surveyService.saveInputAnswer(map3);
			}
		}
		// TODO 填写问卷时 生成动态
		Map<String, Object> mapSurvey =
				surveyService.getSurveyBaseInfo(Long.parseLong(surveyId, 10));
		Map<String, Object> userMap = this.getUserInfo();
		WriterUserTrendst wut = new WriterUserTrendst(userMap.get("id").toString(), 11, null);
		wut.setDetail("参加调查",
				userMap.get("realname").toString() + " 参加了问卷《"
						+ mapSurvey.get("title").toString() + "》。",
				0);
		personalService.saveUserTrendst(wut);// 填写问卷时 生成动态
		// 填写问卷增加积分
		map.put("id", userId);
		surveyService.insertUserScores(map);
		String code = "OK";
		return code;
	}
}
