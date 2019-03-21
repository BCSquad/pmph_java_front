package com.bc.pmpheep.back.commuser.reportprogress.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bc.pmpheep.back.commuser.book.bean.BookVO;
import com.bc.pmpheep.back.commuser.reportprogress.bean.Textbook;
import com.bc.pmpheep.back.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.reportprogress.bean.TextBookCheckVO;
import com.bc.pmpheep.back.commuser.reportprogress.bean.UserMessageVO;
import com.bc.pmpheep.back.commuser.reportprogress.service.ReportProgressService;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * 
 * <pre>
 * 功能描述：申报进度 控制器
 * 使用示范：
 * 
 * 
 * @author (作者) nyz
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 2017-11-30
 * @modify (最后修改时间) 
 * @修改人 ：nyz 
 * @审核人 ：
 * </pre>
 */
@Controller
@RequestMapping(value = "/progress")
public class ReportProgressController extends BaseController {

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.reportprogress.service.impl.ReportProgressServiceImpl")
    ReportProgressService reportProgressService;

    /**
     * 
     * <pre>
     * 功能描述：申报教材-申报进度
     * 使用示范：
     *
     * @ materialId 教材ID
     * @return 
     * @throws Exception
     * </pre>
     */
    @RequestMapping(value = "/listReportProgress", method = RequestMethod.GET)
    public ModelAndView listReportProgress(HttpServletRequest request/*@RequestParam("materialId") Long materialId*/)
    throws Exception {
        ModelAndView model = new ModelAndView();
        String pageUrl = "commuser/report_progress/progress";
        Long userId = Long.parseLong(this.getUserInfo().get("id").toString());
        String materialStr = request.getParameter("materialId");
        Long materialId = null;	
        //Long userId = 32781L; 
        //Long materialId = 123L;
        if(null!=materialStr&&!materialStr.equals("")){
        	 materialId = new Long(materialStr);
        	 // 申报进度
             TextBookCheckVO progress = reportProgressService.getMaterialProgress(userId, materialId);
             // 书籍审核结果
             List<TextBookCheckVO> textBookChecks =reportProgressService.getTextBookCheckResult(userId, materialId);
             // 申报消息
             List<UserMessageVO> userMessageList = reportProgressService.getUserMessageByMaterialId(userId, materialId);
             model.addObject("progress", progress);
             model.addObject("textBookCheck", textBookChecks);
             model.addObject("userMessageList", userMessageList);
             model.addObject("materialId", materialId);
        	 
        }else{
        	model.addObject("flag", "no");
        }
        
        
        model.setViewName(pageUrl);
        return model;
    }


    @RequestMapping(value = "/listEditProgress", method = RequestMethod.GET)
    public ModelAndView listEditProgress(HttpServletRequest request,
                                         @RequestParam("materialId") Long materialId,
                                         String materialName)
            throws Exception {
        ModelAndView model = new ModelAndView();
        String pageUrl = "commuser/personalcenter/editProgress";
        Long userId = Long.parseLong(this.getUserInfo().get("id").toString());
        String userType = this.getUserType();
        if("2".equals(userType)){
            userId = null;
        }

        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("materialId",materialId);
        paraMap.put("userId",userId);

        //userId 当前登录人用户id 若为机构用户传入null 表示不受此参数限制
        List<Textbook> bookList = reportProgressService.getTextBookListByMaterialId(paraMap);
        String erpParamJson ="{" +
                "\"topicNumbers\":[";

        for (Textbook b:bookList) {
            if(StringUtil.notEmpty(b.getTopicNumber())){
                erpParamJson += "{\"topicNumber\":\""+b.getTopicNumber()+"\"},";
            }
        }
        erpParamJson.replaceAll(",$","");
        erpParamJson += "]}";


        model.addObject("userType", userType);
        model.addObject("bookList", bookList);
        model.addObject("materialId", materialId);
        model.addObject("materialName", materialName);
        model.addObject("erpParamJson", erpParamJson);
        model.addObject("materialId", materialId);
        model.setViewName(pageUrl);
        return model;
    }

    @RequestMapping(value = "refreshEditProgress",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> refreshEditProgress(HttpServletRequest request,
                                                  String textbookName,
                                                  Long materialId
                                                  ){
        Long userId = Long.parseLong(this.getUserInfo().get("id").toString());

        String userType = this.getUserType();
        if("2".equals(userType)){
            userId = null;
        }

        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("materialId",materialId);
        paraMap.put("userId",userId);
        paraMap.put("textbookName",textbookName);

        List<Textbook> bookList = reportProgressService.getTextBookListByMaterialId(paraMap);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("bookList",bookList);


        return resultMap;
    }


}
