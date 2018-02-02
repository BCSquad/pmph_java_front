package com.bc.pmpheep.back.commuser.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.cms.bean.CmsContentVO;
import com.bc.pmpheep.back.commuser.cms.service.CmsContentService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.back.util.StringUtil;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * CMS 医学随笔（文章）
 * 
 * @author Mr 2017-11-27
 */
@Controller
@RequestMapping(value = "/cms")
public class CmsContentController extends com.bc.pmpheep.general.controller.BaseController{
    private final String      BUSSINESS_TYPE = "文章";

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.cms.service.CmsContentServiceImpl")
    private CmsContentService cmsContentService;

    /**
     * 文章（和医学随笔列表相同） 增加标题查询
     * @param pageSize
     * @param pageNumber
     * @param cmsContentVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView toList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("commuser/cms/cmscontents");
		return mv;
	}
    
  
    /**
     * 文章（和医学随笔列表相同） 分页
     * @param pageSize
     * @param pageNumber
     * @param cmsContentVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/toPage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> toPage( HttpServletRequest request) throws Exception {
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        PageParameter<Map<String,Object>> pageParameter = new PageParameter<>(pageNum, pageSize);
       
        List<Map<String, Object>> page = cmsContentService.listCms(pageParameter);
        int total = cmsContentService.getCmsContentCount(pageParameter);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("page", page);
            map.put("total", total);
            map.put("startNum", pageParameter.getStart() + 1);
        return map;
    }
    
    
    
    
    
    
    
}
