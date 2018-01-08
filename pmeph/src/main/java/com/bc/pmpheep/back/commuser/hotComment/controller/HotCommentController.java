package com.bc.pmpheep.back.commuser.hotComment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.hotComment.service.HotCommentService;
import com.bc.pmpheep.back.commuser.readpage.service.ReadDetailService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.general.controller.BaseController;



@Controller
@RequestMapping("hotComment")
public class HotCommentController extends BaseController{
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.hotComment.service.HotCommentServiceImpl")
	HotCommentService  hotCommentService;
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.readpage.service.ReadDetaiServicelImpl")
	private ReadDetailService readDetailService;
	
	//去热门书评列表页面
	@RequestMapping(value="hotCommentList")
	public ModelAndView toHotCommentList(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String  currentPageStr = (String) request.getParameter("currentPage");
		String  pageSizeStr = request.getParameter("pageSize");
		int currentPage = 1;
		int pageSize = 2;
		
		if(null!=currentPageStr&&!currentPageStr.equals("")){
			 currentPage = Integer.parseInt(currentPageStr);
		}
		if(null!=pageSizeStr&&!pageSizeStr.equals("")){
			 pageSize = Integer.parseInt(pageSizeStr);
		}
		Map<String,Object> paraMap = new HashMap<String,Object>();
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<>(currentPage,pageSize);
		paraMap.put("endPage", pageSize);
		pageParameter.setParameter(paraMap);
		//热门书评列表
		PageResult<Map<String,Object>> pageResult = hotCommentService.selectHotCommentList(pageParameter);
		for(Map<String, Object> map:pageResult.getRows()){
			if(("DEFAULT").equals(map.get("image_url"))){
				map.put("image_url", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
			}
		}
		
		//查询人卫推荐
		List<Map<String, Object>> eMap=readDetailService.queryRecommendByE(0);
		
		//配套图书
		String id = "168";
		Map<String, Object> supMap=readDetailService.querySupport(id);
		
		mv.addObject("listHotComment",pageResult);
		mv.addObject("eMap",eMap);
		mv.addObject("supMap",supMap);
		mv.setViewName("commuser/hotComment/hotCommentList");
		return mv;
	}
	
	//展开详情
	@RequestMapping("hotCommentDetail")
	public ModelAndView hotCommentDetail(HttpServletRequest request){
		String id = request.getParameter("id");
		ModelAndView mv = new ModelAndView();
		Map<String,Object> map = hotCommentService.getHotCommentDetail(id);
		//查询人卫推荐
		List<Map<String, Object>> eMap=readDetailService.queryRecommendByE(0);
		
		mv.addObject("map",map);
		mv.addObject("eMap",eMap);
		
		mv.setViewName("commuser/hotComment/hotCommentDetail");
		return mv;
		
	}
}
