package com.bc.pmpheep.back.commuser.cms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.cms.bean.CmsNoticeList;
import com.bc.pmpheep.back.commuser.cms.service.CmsNoticeManagementService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.controller.bean.ResponseBean;

/**
 * 
 * 
 * 功能描述：公告列表
 * 
 * 
 * 
 * @author (作者) 曾庆峰
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 2017年11月27日
 * @modify (最后修改时间)
 * @修改人 ：曾庆峰
 * @审核人 ：
 *
 */
@Controller
@RequestMapping(value = "/cmsnotice")
public class CmsNoticeManagementController {
	@Autowired
	CmsNoticeManagementService cmsNoticeManagementService;

	/**
	 * 跳转到教材列表页面
	 * @author Mryang
	 * @createDate 2017年12月5日 下午4:29:26
	 * @param pageSize
	 * @param pageNumber
	 * @param isHot
	 * @return
	 */
	@RequestMapping(value = "/tolist", method = RequestMethod.GET)
	public ModelAndView tolistPage(Integer pageSize, Integer pageNumber, Boolean isHot) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("commuser/focusAndSelect/newsReport");
		return  modelAndView;
	}
	
	/**
	 * 
	 * 
	 * 功能描述：获取公告列表
	 *
	 * @param pageSize
	 *            一页的数据数量
	 * @param pageNumber
	 *            当前第几页
	 * @param isHot
	 *            是否热门
	 * @return
	 *
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Integer pageSize, Integer pageNumber, Boolean isHot) {
		PageParameter<CmsNoticeList> pageParameter = new PageParameter<>(pageNumber, pageSize);
		CmsNoticeList cmsNoticeList = new CmsNoticeList();
		cmsNoticeList.setIsHot(isHot);
		pageParameter.setParameter(cmsNoticeList);
		Map<String, ResponseBean<CmsNoticeList>> map = new HashMap<>();
		map.put("CmsInfoLettersList", new ResponseBean(cmsNoticeManagementService.list(pageParameter)));
		return new ModelAndView("commuser/cmsnotice/list", map);
	}

}
