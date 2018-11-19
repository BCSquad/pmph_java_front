package com.bc.pmpheep.back.authadmin.applydocaudit.controller;
import com.bc.pmpheep.back.authadmin.applydocaudit.service.ExpertationListAuditService;
import com.bc.pmpheep.back.commuser.mymessage.bean.DialogueVO;
import com.bc.pmpheep.back.commuser.mymessage.service.MyMessageService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.service.exception.CheckedServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 临床决策专家申报审核（机构用户）
 *
 * @author sunzhuoqun
 * @Date 2018年08月17日 下午16:00:00
 **/
@Controller
@RequestMapping(value = "/expertationList")
public class ExpertationListAuditController extends com.bc.pmpheep.general.controller.BaseController {

    @Autowired
    @Qualifier("com.bc.pmpheep.back.authadmin.applydocaudit.service.ExpertationListAuditServiceImpl")
    private ExpertationListAuditService expertationListAuditService;

    /**
     *临床决策专家申报审核初始化页面
     * @param
     * @return
     */

    @RequestMapping(value = "/toPageList", method = RequestMethod.GET)
    public ModelAndView toPageList(Integer pageSize, Integer pageNumber, HttpServletRequest request)
            throws Exception {
        ModelAndView model = new ModelAndView();
        if (null == pageSize) {
            pageSize = 10;
        }
        if (null == pageNumber) {
            pageNumber = 1;
        }

        PageParameter<Map<String, Object>> pageParameter = new PageParameter<Map<String, Object>>(pageNumber, pageSize);
        String username = request.getParameter("username");
        String expertType = request.getParameter("expertType");
        String xxsh = request.getParameter("xxsh");
        String name="";
        if (username != null) {
            name = java.net.URLDecoder.decode(username, "UTF-8");
        }
        Map<String, Object> paraMap = new HashMap<String, Object>();
        int starta = (pageNumber - 1) * pageSize;
        paraMap.put("starta", starta);
        paraMap.put("queryName", name);
        paraMap.put("expertType", expertType);
        paraMap.put("xxsh", xxsh);


        //获取当前用户
        Map<String, Object> user = getUserInfo();
        BigInteger uid = (BigInteger) user.get("org_id");
        paraMap.put("orgId", uid);
        pageParameter.setParameter(paraMap);
        String pageUrl = "authadmin/applydocaudit/expertationListAudit";
        try {
            PageResult<Map<String, Object>> page = expertationListAuditService.getOrg(pageParameter);
            model.setViewName(pageUrl);
            model.addObject("page", page);

            model.addObject("pageNumber", pageNumber);
            model.addObject("pageSize", pageSize);
            model.addObject("username", name);
            model.addObject("expertType", expertType);
            model.addObject("xxsh", xxsh);
           /* model.addObject("productIdList", expertationListAuditService.productIdList());*/
        } catch (CheckedServiceException e) {
            throw new CheckedServiceException(e.getBusiness(), e.getResult(), e.getMessage(),
                    pageUrl);
        }
        return model;
    }


    //点击打印按钮改变打印状态
    @RequestMapping(value = "/updPrintStatus")
    @ResponseBody
    public String updPrintStatus(HttpServletRequest request) {
        String id = request.getParameter("did");
        this.expertationListAuditService.updPrintStatus(id);
        return "OK";
    }



}
