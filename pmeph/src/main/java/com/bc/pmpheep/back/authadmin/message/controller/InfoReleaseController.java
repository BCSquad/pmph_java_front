package com.bc.pmpheep.back.authadmin.message.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.authadmin.message.service.InfoReleaseService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * 
 * @ClassName: InfoReleaseController
 * @Description: 选择消息发布对象(机构用户)
 * @author SunZhuoQun
 * @date 2017-12-5 上午9:45:55
 * 
 */
@Controller
@RequestMapping("/info")
public class InfoReleaseController extends BaseController{

	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.message.service.InfoReleaseServiceImpl")
	InfoReleaseService infoReleaseService;

	/**
	 * 选择消息发布对象(机构用户)
	 * 
	 * @Title: toPage
	 * @Description: 跳转到主页
	 * @param @param request
	 * @param @return
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping("toPage")
	public ModelAndView toPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		List<Map<String, Object>> List_map = infoReleaseService.selectMenu();
		mv.addObject("listMenu", List_map);
		mv.setViewName("authadmin/message/inforelease");
		return mv;
	}

	/**
	 * 
	 * @Title: infoRelease
	 * @Description: 模糊分页
	 * @param @param request
	 * @param @return
	 * @return ResponseBean<Map<String,Object>> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/infoRelease", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> infoRelease(
			HttpServletRequest request) {

		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String queryName = request.getParameter("queryName");

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("queryName", queryName);
		Map<String, Object>   user=getUserInfo();
		BigInteger uid = (BigInteger) user.get("id");
		paraMap.put("userId", uid);
		PageParameter<Map<String, Object>> pageParameter = new PageParameter<Map<String, Object>>(
				pageNum, pageSize);
		pageParameter.setParameter(paraMap);
		List<Map<String, Object>> List_map = infoReleaseService
				.selectInfoRelease(pageParameter);
		int totoal_count = infoReleaseService
				.selectInfoReleaseCount(pageParameter);
		Map<String, Object> vm_map = new HashMap<String, Object>();
		vm_map.put("listMap", List_map);
		vm_map.put("startNum", pageParameter.getStart() + 1);
		vm_map.put("pageSize", pageParameter.getPageSize());
		vm_map.put("totoal_count", totoal_count);
		return vm_map;
	}

}
