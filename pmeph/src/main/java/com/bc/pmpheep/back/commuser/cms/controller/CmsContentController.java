package com.bc.pmpheep.back.commuser.cms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.cms.bean.CmsContentVO;
import com.bc.pmpheep.back.commuser.cms.service.CmsContentService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * CMS
 * 
 * @author Mr 2017-11-27
 */
@Controller
@RequestMapping(value = "/cms")
@SuppressWarnings("all")
public class CmsContentController extends BaseController {

    private final String      BUSSINESS_TYPE = "文章";

    @Resource
    private CmsContentService cmsContentService;

    /**
     * 功能描述：查询医学随笔列表
     * 
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @RequestMapping(value = "/cmsList", method = RequestMethod.POST)
    public ModelAndView cmsList(Integer pageSize, Integer pageNumber) {
        PageParameter<CmsContentVO> pageParameter = new PageParameter<>(pageNumber, pageSize);
        CmsContentVO cmsContentVO = new CmsContentVO();
        pageParameter.setParameter(cmsContentVO);
        Map<String, ResponseBean<CmsContentVO>> map = new HashMap<>();
        map.put("CmsContentVO", new ResponseBean(cmsContentService.list(pageParameter)));
        return new ModelAndView("commuser/cmscontent/listcms", map);
    }
}
