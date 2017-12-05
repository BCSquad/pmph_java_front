package com.bc.pmpheep.back.commuser.cms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.cms.bean.CmsNoticeList;
import com.bc.pmpheep.back.commuser.cms.service.CmsNoticeManagementService;

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
	@Qualifier("com.bc.pmpheep.back.commuser.cms.service.CmsNoticeManagementServiceImpl")
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
		modelAndView.setViewName("commuser/focusAndSelect/materialNotice");
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
	@ResponseBody
	public List<CmsNoticeList> list(Integer pageSize, Integer pageNumber, Integer order) {
		List<CmsNoticeList> cmsNoticeList =  cmsNoticeManagementService.list(pageSize, pageNumber, order);
		return cmsNoticeList ;
	}

}
