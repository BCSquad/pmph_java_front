package com.bc.pmpheep.back.commuser.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.cms.bean.CmsInfoLettersList;
import com.bc.pmpheep.back.commuser.cms.service.CmsInfoLettersManagementService;


/**
 * 
 * 
 * 功能描述：焦点信息列表
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
@RequestMapping(value = "/cmsinfoletters")
public class CmsInfoLettersManagementController {
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.cms.service.CmsInfoLettersManagementServiceImpl")
	CmsInfoLettersManagementService cmsInfoLettersManagementService;

	/**
	 * 
	 * 
	 * 功能描述：前台获取信息快报的列表
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
	public List<CmsInfoLettersList> topage(Integer pageSize, Integer pageNumber, Integer order) {
//		ModelAndView model = new ModelAndView();
//		model.setViewName("commuser/focusAndSelect/annoceSelect");
//		Integer total =  cmsInfoLettersManagementService.getCmsInfoLettersListTotal(pageSize,pageNumber);
//		if(null != total && total>0 ){
//			List<CmsInfoLettersList> cmsInfoLettersList = cmsInfoLettersManagementService.list(pageSize,pageNumber);
//			model.addObject("cmsInfoLettersList", cmsInfoLettersList);
//		}
//		model.addObject("total",total);
		return cmsInfoLettersManagementService.list(pageSize,pageNumber);
	}
	
	/**
	 * 页面跳转
	 * @author Mryang
	 * @createDate 2017年12月5日 下午2:30:24
	 * @param pageSize
	 * @param pageNumber
	 * @param isHot
	 * @return
	 */
	@RequestMapping(value = "/tolist", method = RequestMethod.GET)
	public ModelAndView list(Integer pageSize, Integer pageNumber, Integer order) {
		ModelAndView model = new ModelAndView();
		model.setViewName("commuser/focusAndSelect/newsReport");
		return model;
	}
}
