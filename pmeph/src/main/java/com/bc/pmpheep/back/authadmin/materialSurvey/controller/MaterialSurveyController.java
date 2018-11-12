package com.bc.pmpheep.back.authadmin.materialSurvey.controller;


import com.bc.pmpheep.back.authadmin.applydocaudit.bean.Material;
import com.bc.pmpheep.back.authadmin.backlog.service.ScheduleService;
import com.bc.pmpheep.back.authadmin.materialSurvey.bean.*;
import com.bc.pmpheep.back.authadmin.materialSurvey.service.MaterialSurveyService;
import com.bc.pmpheep.back.commuser.materialdec.service.MaterialDetailService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.back.util.DateUtil;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.back.util.StringUtil;
import com.bc.pmpheep.general.controller.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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

    MaterialDetailService materialDetailService;


    @Autowired
    @Qualifier("com.bc.pmpheep.back.authadmin.materialSurvey.service.MaterialSurveyServiceImpl")
    MaterialSurveyService materialSurveyService;


    /**
     * 根据教材id获取问卷
     *
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/fillSurveyById")
    public ModelAndView toEventRecord(HttpServletRequest request) throws ParseException {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> writerUser = this.getUserInfo();
        if (writerUser.get("latest_login_time") == null || writerUser.get("latest_login_time").toString().length() < 1) {
            writerUser.put("latest_login_time", new Date());
        }

        Long userId = new Long(String.valueOf(writerUser.get("id")));
        MaterialSurveyVO materialSurveyVO = new MaterialSurveyVO();
        List<MaterialSurveyQuestionVO> materialSurveyQuestionVOS = new ArrayList<>();
        //获取教材id


        MaterialSurvey surveyByMaterialId = null;
        if (StringUtil.notEmpty(request.getParameter("materialId"))) {
            /*根据教材id获取最新的关联的问卷
             */
            String materialId = request.getParameter("materialId");
            List<MaterialSurvey> surveyByMaterialIds = materialSurveyService.getSurveyByMaterialId(Long.parseLong(materialId));

            if (surveyByMaterialIds.size() == 1) {
                surveyByMaterialId = surveyByMaterialIds.get(0);
            } else {

                int pageNum = 1;
                int pageSize = 10;
                Map<String, Object> map = new HashMap<>();
                Map<String, Object> user = getUserInfo();
                map.put("id", user.get("id"));
                if (materialId != null) {
                    if (surveyByMaterialIds.size() != 0) {
                        map.put("materialId", materialId);
                        Material materialByid = materialSurveyService.getMaterialByid(Long.parseLong(materialId));
                        mv.addObject("material", materialByid);
                    }

                }
                PageParameter<Map<String, Object>> pageParameter = new PageParameter<Map<String, Object>>(pageNum, pageSize);
                pageParameter.setParameter(map);
                PageResult<Map<String, Object>> pageResult = materialSurveyService.querySearchList(pageParameter);
                mv.addObject("pageNum", pageNum);
                mv.addObject("pageSize", pageSize);
                mv.addObject("pageResult", pageResult);
                mv.addObject("state", "");

                mv.setViewName("authadmin/materialSurvey/SurveyList");
                return mv;
            }

        }

        if (StringUtil.notEmpty(request.getParameter("surveyId"))) {
            /*根据问卷id获取问卷对象
             */
            Long surveyId = Long.parseLong(request.getParameter("surveyId"));
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

        String state=request.getParameter("state");
        Map<String, Object> map = new HashMap<>();
        if(StringUtil.notEmpty(state)){
            mv.addObject("state",1);
        }else{
            mv.addObject("state",2);
        }
        //机构用户基本信息
        map.put("survey", materialSurveyVO);
        mv.addObject("res", map);
        mv.setViewName("authadmin/materialSurvey/fillMaterialSurvey");
        return mv;
    }

    /**
     * 填写问卷内容
     *
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
    ) {
        ModelAndView modelAndView = new ModelAndView();
        String state = request.getParameter("state");
        String materialId = request.getParameter("materialId");
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> user = getUserInfo();
        map.put("id", user.get("id"));
        map.put("state", state);

        if (materialId != null) {
            map.put("materialId", materialId);
            Material materialByid = materialSurveyService.getMaterialByid(Long.parseLong(materialId));

            modelAndView.addObject("material", materialByid);


        }

        PageParameter<Map<String, Object>> pageParameter = new PageParameter<Map<String, Object>>(pageNum, pageSize);
        pageParameter.setParameter(map);
        PageResult<Map<String, Object>> pageResult = materialSurveyService.querySearchList(pageParameter);
        modelAndView.addObject("pageNum", pageNum);
        modelAndView.addObject("pageSize", pageSize);
        modelAndView.addObject("pageResult", pageResult);
        modelAndView.addObject("state", state);

        modelAndView.setViewName("authadmin/materialSurvey/SurveyList");
        return modelAndView;
    }

    /**
     * 根据教材id获取问卷
     *
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/surveyDetailsById")
    public ModelAndView getDetails(HttpServletRequest request) throws ParseException {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> writerUser = this.getUserInfo();
        if (writerUser.get("latest_login_time") == null || writerUser.get("latest_login_time").toString().length() < 1) {
            writerUser.put("latest_login_time", new Date());
        }

        Long userId = new Long(String.valueOf(writerUser.get("id")));
        MaterialSurveyVO materialSurveyVO = new MaterialSurveyVO();
        List<MaterialSurveyQuestionVO> materialSurveyQuestionVOS = new ArrayList<>();
        //获取教材id


        MaterialSurvey surveyByMaterialId = null;

        if (StringUtil.notEmpty(request.getParameter("surveyId"))) {
            /*根据问卷id获取问卷对象
             */
            Long surveyId = Long.parseLong(request.getParameter("surveyId"));
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

            /*根据问题id获取选项 回答
             */
            materialSurveyQuestionVO.setMaterialSurveyQuestionOptionList(surveyQuestionOptionByQuestionId);
            List<MaterialSurveyQuestionAnswer> surveyQuestionAnswerByQuestionId = materialSurveyService.getSurveyQuestionAnswerByQuestionId(materialSurveyQuestion.getId());

            switch (materialSurveyQuestion.getType()) {
                case 1:
                    materialSurveyQuestionVO.setOptionAnswer(surveyQuestionAnswerByQuestionId.get(0).getOptionId());
                    break;
                case 2:
                    if (surveyQuestionAnswerByQuestionId.size() > 1) {
                        List<Long> qustionAnswers = new ArrayList<>();
                        for (MaterialSurveyQuestionAnswer materialSurveyQuestionAnswer : surveyQuestionAnswerByQuestionId) {
                            qustionAnswers.add(materialSurveyQuestionAnswer.getOptionId());
                        }
                        materialSurveyQuestionVO.setOptionAnswers(qustionAnswers);
                    } else {
                        materialSurveyQuestionVO.setOptionAnswer(surveyQuestionAnswerByQuestionId.get(0).getOptionId());
                    }
                    break;
                case 3:
                    materialSurveyQuestionVO.setOptionAnswer(surveyQuestionAnswerByQuestionId.get(0).getOptionId());
                    break;
                case 4:
                    materialSurveyQuestionVO.setOptionContent(surveyQuestionAnswerByQuestionId.get(0).getOptionContent());
                    break;
                case 5:
                    materialSurveyQuestionVO.setOptionContent(surveyQuestionAnswerByQuestionId.get(0).getOptionContent());
                    break;
            }
            materialSurveyQuestionVOS.add(materialSurveyQuestionVO);
        }
        materialSurveyVO.setMaterialSurveyQuestionList(materialSurveyQuestionVOS);

        //机构用户基本信息
        Map<String, Object> map = new HashMap<>();
        map.put("survey", materialSurveyVO);
        map.put("type", "view");
        mv.addObject("res", map);
        mv.setViewName("authadmin/materialSurvey/fillMaterialSurvey");
        return mv;
    }
    /**
     * 填写问卷内容
     *
     * @param json
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/checkFill", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkFill(HttpServletRequest request) throws ParseException {
        String materialId = request.getParameter("materialId");
        if(StringUtil.notEmpty(materialId)){

        }



        return null;

    }

    /**
     * 填写问卷内容
     *
     * @param json
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/getFillCount", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getFillCount( HttpServletRequest request) throws ParseException {




        return null;

    }

}
