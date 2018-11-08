package com.bc.pmpheep.back.authadmin.materialSurvey.controller;


import com.bc.pmpheep.back.authadmin.backlog.service.ScheduleService;
import com.bc.pmpheep.back.authadmin.materialSurvey.bean.*;
import com.bc.pmpheep.back.authadmin.materialSurvey.service.MaterialSurveyService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.back.util.DateUtil;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.back.util.StringUtil;
import com.bc.pmpheep.general.controller.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * @author Administrator
 * 待办事项控制类
 */
@Controller
@RequestMapping("/orgSurvey")
public class MaterialSurveyController extends BaseController {
    @Autowired
    @Qualifier("com.bc.pmpheep.back.authadmin.backlog.service.ScheduleServiceImpl")
    private
    ScheduleService scheduleService;

    @Autowired
    @Qualifier("com.bc.pmpheep.back.authadmin.materialSurvey.service.MaterialSurveyServiceImpl")
    MaterialSurveyService materialSurveyService;


    /**
     * 根据教材id获取问卷
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/fillSurveyById")
    public ModelAndView toEventRecord(HttpServletRequest request) throws ParseException {
        Map<String, Object> writerUser = this.getUserInfo();
        if (writerUser.get("latest_login_time") == null || writerUser.get("latest_login_time").toString().length() < 1) {
            writerUser.put("latest_login_time", new Date());
        }
        Long userId = new Long(String.valueOf(writerUser.get("id")));
        MaterialSurveyVO materialSurveyVO = new MaterialSurveyVO();
        List<MaterialSurveyQuestionVO> materialSurveyQuestionVOS = new ArrayList<>();
        //获取教材id


        MaterialSurvey surveyByMaterialId=null;
        if(StringUtil.notEmpty(request.getParameter("materialId"))){
            /*根据教材id获取问卷
             */
            String materialId = request.getParameter("materialId");
           surveyByMaterialId = materialSurveyService.getSurveyByMaterialId(Long.parseLong(materialId));

        }

        if(StringUtil.notEmpty(request.getParameter("surveyId"))){
            /*根据问卷id获取问卷对象
             */
            Long surveyId =  Long.parseLong(request.getParameter("surveyId"));
            surveyByMaterialId = materialSurveyService.getSurveyById(surveyId);
        }

        /*根据问卷id获取问题
         */
        List<MaterialSurveyQuestion> surveyQuestionBySurveyId = materialSurveyService.getSurveyQuestionBySurveyId(surveyByMaterialId.getId());
        BeanUtils.copyProperties(surveyByMaterialId, materialSurveyVO);
        for (MaterialSurveyQuestion materialSurveyQuestion : surveyQuestionBySurveyId) {
            MaterialSurveyQuestionVO materialSurveyQuestionVO = new MaterialSurveyQuestionVO();
            BeanUtils.copyProperties(materialSurveyQuestion, materialSurveyQuestionVO);

            /*根据问题id获取选项
             */
            List<MaterialSurveyQuestionOption> surveyQuestionOptionByQuestionId = materialSurveyService.getSurveyQuestionOptionByQuestionId(materialSurveyQuestion.getId());

            materialSurveyQuestionVO.setMaterialSurveyQuestionOptionList(surveyQuestionOptionByQuestionId);

            materialSurveyQuestionVOS.add(materialSurveyQuestionVO);
        }
        materialSurveyVO.setMaterialSurveyQuestionList(materialSurveyQuestionVOS);
        ModelAndView mv = new ModelAndView();
        //机构用户基本信息
        Map<String, Object> map = new HashMap<>();
        map.put("survey", materialSurveyVO);
        mv.addObject("res", map);
        mv.setViewName("authadmin/materialSurvey/fillMaterialSurvey");
        return mv;
    }

    /**
     * 填写问卷内容
     * @param json
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/fillSurveyQuestion", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> fillSurveyQuestion(@RequestBody String json, HttpServletRequest request) throws ParseException {


        Map<String, Object> writerUser = this.getUserInfo();
        if (writerUser.get("latest_login_time") == null || writerUser.get("latest_login_time").toString().length() < 1) {
            writerUser.put("latest_login_time", new Date());
        }
        /**
         * 获取当前用户
         */
        Integer userId = new Integer(String.valueOf(writerUser.get("id")));

        /**
         * 解析JSON 字符串
         */
        JSONObject jsonObject = JSONObject.fromObject(json);
        //问卷id
        Long surveyId = jsonObject.getLong("id");
        //获取问题id及回答内容
        JSONArray quesions = jsonObject.getJSONArray("quesions");

        List<MaterialSurveyQuestionAnswer> fillMateriallSurveyQuestionVOS = new ArrayList<>();
        for (int i = 0; i < quesions.size(); i++) {
            MaterialSurveyQuestionAnswer fillMateriallSurveyQuestionVO = new MaterialSurveyQuestionAnswer();
            fillMateriallSurveyQuestionVO.setUserId(userId);
            fillMateriallSurveyQuestionVO.setUserType(2);   //机构用户2
            fillMateriallSurveyQuestionVO.setSurveyId(surveyId);
            fillMateriallSurveyQuestionVO.setGmtCreate(DateUtil.getCurrentTime());

            if (ObjectUtil.notNull(quesions.getJSONObject(i).get("id"))) {
                fillMateriallSurveyQuestionVO.setQuestionId(quesions.getJSONObject(i).getLong("id"));
            }
            if (ObjectUtil.notNull(quesions.getJSONObject(i).get("answerId"))) {
                String answerId = quesions.getJSONObject(i).getString("answerId");

                /**
                 * 多选处理
                 */
                String[] split = answerId.split(",");
                if (split.length <= 1) {
                    fillMateriallSurveyQuestionVO.setOptionId(quesions.getJSONObject(i).getLong("answerId"));
                } else {

                    for (String opid : split) {
                        MaterialSurveyQuestionAnswer materialSurveyQuestionAnswer = new MaterialSurveyQuestionAnswer();
                        BeanUtils.copyProperties(fillMateriallSurveyQuestionVO, materialSurveyQuestionAnswer);
                        materialSurveyQuestionAnswer.setOptionId(Long.parseLong(opid));
                        fillMateriallSurveyQuestionVOS.add(materialSurveyQuestionAnswer);
                    }
                }

            }
            if (ObjectUtil.notNull(quesions.getJSONObject(i).get("answerInput"))) {
                fillMateriallSurveyQuestionVO.setOptionContent(quesions.getJSONObject(i).getString("answerInput"));
            }
            if (ObjectUtil.notNull(quesions.getJSONObject(i).get("answerTextArea"))) {
                fillMateriallSurveyQuestionVO.setOptionContent(quesions.getJSONObject(i).getString("answerTextArea"));
            }

            fillMateriallSurveyQuestionVOS.add(fillMateriallSurveyQuestionVO);
        }
        Integer integer = 0;
        Map<String, Object> map = new HashMap<>();
        try {
            integer = materialSurveyService.fillSurveyQuestion(fillMateriallSurveyQuestionVOS);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", integer);
            return map;

        }


        map.put("code", integer);
        return map;

    }


    @RequestMapping("tolist")
    public ModelAndView tolist(HttpServletRequest request,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
                               ){
        ModelAndView modelAndView=new ModelAndView();
        String state = request.getParameter("state");
        String materialId = request.getParameter("materialId");
        Map<String,Object> map = new HashMap<>();
        Map<String, Object> user=getUserInfo();
        map.put("id",user.get("id"));
        map.put("state",state);

        if(materialId!=null){
            map.put("materialId",materialId);
            modelAndView.addObject("materialId",materialId);

        }

        PageParameter<Map<String, Object>> pageParameter = new PageParameter<Map<String, Object>>(pageNum, pageSize);
        pageParameter.setParameter(map);
        PageResult<Map<String, Object>> pageResult = materialSurveyService.querySearchList(pageParameter);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("pageSize",pageSize);
        modelAndView.addObject("pageResult",pageResult);
        modelAndView.addObject("state",state);

        modelAndView.setViewName("authadmin/materialSurvey/SurveyList");
        return modelAndView;
    }


}
