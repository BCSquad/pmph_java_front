package com.bc.pmpheep.back.commuser.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.bc.pmpheep.back.commuser.book.bean.BookVO;

import com.bc.pmpheep.back.commuser.cms.bean.CmsContentVO;
import com.bc.pmpheep.back.commuser.cms.service.CmsContentService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.back.util.StringUtil;
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.service.exception.CheckedServiceException;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * CMS
 * 
 * @author Mr 2017-11-27
 */
@Controller
@RequestMapping(value = "/cms")
@SuppressWarnings("all")
public class CmsContentController extends com.bc.pmpheep.general.controller.BaseController{
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

    /**
     * 文章（和医学随笔列表相同） 增加标题查询
     * @param pageSize
     * @param pageNumber
     * @param cmsContentVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(Integer pageSize, Integer pageNumber, CmsContentVO cmsContentVO) throws Exception {
        ModelAndView model =new ModelAndView();
        String pageUrl = "";
        PageParameter<CmsContentVO> pageParameter = new PageParameter<>(pageNumber, pageSize);
        if (StringUtil.notEmpty(cmsContentVO.getTitle())) {
        	cmsContentVO.setTitle(cmsContentVO.getTitle().replaceAll(" ", ""));// 去除空格
        }
        pageParameter.setParameter(cmsContentVO);
        try {
            PageResult<CmsContentVO> page = cmsContentService.listCms(pageParameter);
            List<CmsContentVO> cmsContentVOs = page.getRows();
            for (CmsContentVO cmsContentVO2 : cmsContentVOs) {
            	cmsContentVO2.setAuthorImg(RouteUtil.DEFAULT_USER_AVATAR);
            	cmsContentVO2.setCmsImg(RouteUtil.MONGODB_IMAGE);
            }
            model.addObject("page", page);
            model.setViewName(pageUrl);
        } catch (CheckedServiceException e) {
            throw new CheckedServiceException(e.getBusiness(), e.getResult(), e.getMessage(),
                                              pageUrl);
        }
        return model;
    }
}
