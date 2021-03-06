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

        String material_id=request.getParameter("material_id");
        String from=request.getParameter("state");
        Map<String, Object> map = new HashMap<>();
        map.put("material_id",material_id);
        map.put("survey", materialSurveyVO);
        if("fromwrtlist".equals(from)||"material".equals(from)){
            map.put("from",from);
            mv.setViewName("commuser/research/researchadd");
        }else{
            mv.setViewName("authadmin/materialSurvey/fillMaterialSurvey");
        }
        mv.addObject("res", map);
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
        Integer integer = 0;
        Map<String, Object> map = new HashMap<>();
        try {
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
            fillMateriallSurveyQuestionVO.setUserType(Integer.parseInt(this.getUserType()));   //机构用户2
            fillMateriallSurveyQuestionVO.setSurveyId(surveyId);
            fillMateriallSurveyQuestionVO.setGmtCreate(DateUtil.getCurrentTime());

            if (ObjectUtil.notNull(quesions.getJSONObject(i).get("id"))) {
                fillMateriallSurveyQuestionVO.setQuestionId(quesions.getJSONObject(i).getLong("id"));
            }
            if (ObjectUtil.notNull(quesions.getJSONObject(i).get("answerInput"))) {
                fillMateriallSurveyQuestionVO.setOptionContent(quesions.getJSONObject(i).getString("answerInput"));
            }
            if (ObjectUtil.notNull(quesions.getJSONObject(i).get("answerTextArea"))) {
                fillMateriallSurveyQuestionVO.setOptionContent(quesions.getJSONObject(i).getString("answerTextArea"));
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
                    continue;
                }

            }


            fillMateriallSurveyQuestionVOS.add(fillMateriallSurveyQuestionVO);
        }



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
        String required = request.getParameter("required");
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> user = getUserInfo();
        map.put("id", user.get("id"));
        map.put("state", state);
        map.put("required",required);

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
        modelAndView.addObject("required", required);

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
        Long userType = new Long(String.valueOf(this.getUserType()));

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
            Map<String, Object> parameter =new HashMap<>();
            parameter.put("questionId",materialSurveyQuestion.getId());
            if(StringUtil.notEmpty(request.getParameter("user_id"))){
                userId=Long.parseLong(request.getParameter("user_id"));
            }
            if(StringUtil.notEmpty(request.getParameter("user_type"))){
                userType=Long.parseLong(request.getParameter("user_type"));
            }
            parameter.put("user_id",userId);
            parameter.put("user_type",userType);
            List<MaterialSurveyQuestionAnswer> surveyQuestionAnswerByQuestionId = materialSurveyService.getSurveyQuestionAnswerByQuestionId(parameter);
            if(surveyQuestionAnswerByQuestionId.size()>0){
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
                    default:
                        break;
                }
            }

            materialSurveyQuestionVOS.add(materialSurveyQuestionVO);
        }
        materialSurveyVO.setMaterialSurveyQuestionList(materialSurveyQuestionVOS);
        String from=request.getParameter("state");
        //机构用户基本信息
        Map<String, Object> map = new HashMap<>();
        map.put("survey", materialSurveyVO);
        map.put("type", "view");
        if("fromwrtlist".equals(from)||"material".equals(from)){
            map.put("from",from);
            mv.setViewName("commuser/research/researchadd");
        }else{
            mv.setViewName("authadmin/materialSurvey/fillMaterialSurvey");
        }
        map.put("material_id",request.getParameter("material_id"));
        mv.addObject("res", map);
        mv.addObject("res", map);
        return mv;
    }
    /**
     * 填写问卷内容
     *
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/checkFill", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkFill(HttpServletRequest request) throws ParseException {
        String materialId = request.getParameter("materialId");
        Map<String, Object> user = this.getUserInfo();
        String state ="2";
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        map.put("state",state);
        map.put("required","1");
        map.put("id",this.getUserInfo().get("id"));
        map.put("userType",this.getUserType());
        if(StringUtil.notEmpty(materialId)){
            map.put("materialId", materialId);
        }
        Integer integer = materialSurveyService.checkFile(map);
        Boolean success=true;
        if(integer>0){
            success=false;
            res.put("success",success);
        }else{
            res.put("success",success);
        }
        return res;
    }

    //根据书籍ID查询调研表
    @RequestMapping("querySearchByTextbookId")
    @ResponseBody
    public List<Map<String,Object>> querySearchByTextbookId(HttpServletRequest request){
        String textbook_id=request.getParameter("textbook_id");
        textbook_id = textbook_id.replaceAll("[\\[|\\]|\\{|\\}|\"]","");
        textbook_id = "0" +(StringUtil.notEmpty(textbook_id)?",":"")+textbook_id;
        textbook_id = "(" + textbook_id + ")";
        Map<String, Object> user=getUserInfo();

        List<Map<String,Object>> list = materialSurveyService.querySearchByTextbookId(textbook_id);
        return list;
    }

    //查询申请用户已经填写过的调研表
    @RequestMapping("queryAnswer")
    @ResponseBody
    public List<Map<String,Object>> queryAnswer(HttpServletRequest request){
        String material_id=request.getParameter("material_id");
        String user_id = request.getParameter("user_id");
        List<Map<String,Object>> list = materialSurveyService.queryAnswer(material_id,user_id);
        return list;
    }
}
