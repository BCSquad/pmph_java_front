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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.survey.service.SurveyService;
import com.bc.pmpheep.back.commuser.writerpoint.bean.WriterPoint;
import com.bc.pmpheep.back.commuser.writerpoint.bean.WriterPointLog;
import com.bc.pmpheep.back.commuser.writerpoint.bean.WriterPointRule;
import com.bc.pmpheep.back.commuser.writerpoint.service.WriterPointLogService;
import com.bc.pmpheep.back.commuser.writerpoint.service.WriterPointRuleService;
import com.bc.pmpheep.back.commuser.writerpoint.service.WriterPointService;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.general.controller.BaseController;

@Controller
@RequestMapping(value = "/survey")
public class SurveyController extends BaseController {

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.survey.service.SurveyServiceImpl")
    SurveyService surveyService;
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.writerpoint.service.WriterPointRuleServiceImpl")
	WriterPointRuleService writerPointRuleService;
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.writerpoint.service.WriterPointLogServiceImpl")
	WriterPointLogService writerPointLogService;
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.writerpoint.service.WriterPointServiceImpl")
	WriterPointService writerPointService;
    
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
                        question.put("listOptions", listOptions);
                        listResult.add(question);
                    }
                } else {
                    listResult.add(question);
                }

            }
        }

        mv.addObject("listSesult", listResult);
        mv.addObject("surveyId", surveyId);
        mv.addObject("listSize", listResult.size());
        mv.setViewName("commuser/survey/writeSurvey");
        return mv;
    }

    // 填写问卷答案
    @RequestMapping(value = "/addSurveyAnswers")
    @ResponseBody
    public String addSurveyAnswers(HttpServletRequest request) {
        Map<String, Object> writerUser = this.getUserInfo();
        Long userId = new Long(String.valueOf(writerUser.get("id")));
        // 先删除用户回答
        surveyService.deleteUserAnswer(userId);
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
        for (int i = 0; i < radios.length; i++) {
            // 取出每一道单选题的答案
            String radioAnswer = request.getParameter(radios[i]);
            map.put("optionId", radioAnswer);

            for (int j = 0; j < questionIds.length; j++) {
                map.put("questionId", questionIds[i]);
            }

            // 保存单选答案的方法
            surveyService.saveRadioAnswer(map);
        }

        // 先获取所有多选的name集合
        String checkboxs[] = request.getParameterValues("checkboxValues");
        // 多选问题id集合
        String checkboxQuestionIds[] = request.getParameterValues("checkboxQuestionIds");
        // 遍历多选name
        for (int m = 0; m < checkboxs.length; m++) {
            // 取出每一道多选题的答案
            String checkbox[] = request.getParameterValues(checkboxs[m]);
            // String checkboxAnswer = "";
            // // 拼接多选答案
            // for (int a = 0; a < checkbox.length; a++) {
            // if (a < checkbox.length - 1) {
            // checkboxAnswer = checkboxAnswer + checkbox[a] + ",";
            // } else {
            // checkboxAnswer = checkboxAnswer + checkbox[a];
            // }
            // }
            map1.put("optionIds", checkbox);
            // map1.put("checkboxAnswer", checkboxAnswer);
            for (int n = 0; n < checkboxQuestionIds.length; n++) {
                map1.put("questionId", checkboxQuestionIds[m]);
            }
            // 拼接的多选答案是String类型，只能放在答案内容字段
            // map1.put("checkboxAnswer", checkboxAnswer);
            // 保存答案的方法,
            surveyService.saveCheckboxAnswer(map1);
        }

        // 先获取所有输入框的name集合
        String input[] = request.getParameterValues("inputValues");
        // 输入框问题id集合
        String inputQuestionIds[] = request.getParameterValues("inputQuestionIds");
        for (int x = 0; x < inputQuestionIds.length; x++) {
            map2.put("questionId", inputQuestionIds[x]);
            for (int y = 0; y < input.length; y++) {
                String inputValue = request.getParameter(input[y]);
                map2.put("inputValue", inputValue);
            }
            surveyService.saveInputAnswer(map2);
        }

        // 获取所有文本框的name集合
        String textValues[] = request.getParameterValues("textValues");
        // 输入框问题id集合
        String textQuestionIds[] = request.getParameterValues("textQuestionIds");
        for (int x = 0; x < inputQuestionIds.length; x++) {
            map3.put("questionId", textQuestionIds[x]);
            for (int y = 0; y < textValues.length; y++) {
                String textValue = request.getParameter(textValues[y]);
                map3.put("inputValue", textValue);
            }
            surveyService.saveInputAnswer(map3);
        }
        //当用户回答问卷后，增加积分
        String ruleName="问卷调查";
		//获取积分规则
		WriterPointRule writerPointRuleVOs=writerPointRuleService.getWriterPointRuleByName(ruleName);
		if(null!=writerPointRuleVOs){ 
			if(writerPointRuleVOs.getIsDisabled()==false){
				//查询用户评论之前的积分值
				List<WriterPointLog> writerPointLog2=writerPointLogService.getWriterPointLogByUserId(userId);
				WriterPointLog writerPointLog=new WriterPointLog();
				//现在的规则的积分值+以前的积分
                Integer temp = 0; // 获取的总积分
				if (!writerPointLog2.isEmpty()) {
		            Integer newTemp = 0; // 以前的总积分
		            // 遍历以前获取的积分
		            for (WriterPointLog writerPointLogNew : writerPointLog2) {
	                    newTemp += writerPointLogNew.getPoint();
	                }
	                temp = writerPointRuleVOs.getPoint() + newTemp;
	            } else {
	                temp = writerPointRuleVOs.getPoint();
		        }
				//积分规则id
				writerPointLog.setRuleId(writerPointRuleVOs.getId());
				writerPointLog.setUserId(userId);
				//增加积分记录
				writerPointLogService.add(writerPointLog);
				WriterPoint point = writerPointService.getWriterPointByUserId(userId);
				if (ObjectUtil.notNull(point)) {
                    writerPointService.updateWriterPoint(new WriterPoint(point.getId(), userId,
	                                                                         temp + point.getLoss(),
	                                                                         temp,
	                                                                         point.getLoss()));
                } else {
                    writerPointService.addWriterPoint(new WriterPoint(userId, temp, temp, 0));
                }

			}
			
		}
        String code = "OK";
        return code;
    }
}
