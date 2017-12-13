package com.bc.pmpheep.back.authadmin.applydocaudit.controller;

import java.io.UnsupportedEncodingException;
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

import com.bc.pmpheep.back.authadmin.applydocaudit.service.DataAuditService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.template.service.TemplateService;
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * 
 * @ClassName: DataAuditController
 * @Description: 申报资料审核（机构用户）
 * @author SunZhuoQun
 * @date 2017-12-5 上午9:25:53
 * 
 */
@Controller
@RequestMapping(value = "/dataaudit")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DataAuditController extends BaseController{
	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.applydocaudit.service.DataAuditServiceImpl")
	DataAuditService dataAuditService;

	@Autowired
	@Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
	private TemplateService templateService;
	
	

	/**
	 * 
	 * @Title: toPage
	 * @Description: 跳转到主页
	 * @param @param request
	 * @param @return
	 * @param @throws UnsupportedEncodingException
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping("toPage")
	public ModelAndView toPage(HttpServletRequest request)
			throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView();
		String material_id = request.getParameter("material_id");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("material_id", material_id);
		//获取标题
		String	material_name = dataAuditService.findTitleName(map);
		mv.addObject("material_id", material_id);
		mv.addObject("material_name", material_name);
		mv.setViewName("authadmin/applydocaudit/dataaudit");
		return mv;
	}

	/**
	 * 
	 * @Title: findDataAudit
	 * @Description: 申报资料审核（机构用户）
	 * @param @param request
	 * @param @return
	 * @return ResponseBean<Map<String,Object>> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/findDataAudit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findDataAudit(
			HttpServletRequest request) {

		ResponseBean<Map<String, Object>> rb = new ResponseBean<Map<String, Object>>();
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String queryName = request.getParameter("queryName");
		String material_id = request.getParameter("material_id");
		String contextpath = request.getParameter("contextpath");

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("queryName", queryName);
		paraMap.put("material_id", material_id);
		PageParameter<Map<String, Object>> pageParameter = new PageParameter<Map<String, Object>>(
				pageNum, pageSize);
		pageParameter.setParameter(paraMap);
		List<Map<String, Object>> List_map = dataAuditService
				.findDataAudit(pageParameter);
		int totoal_count = dataAuditService.findDataAuditCount(pageParameter);

		Map<String, Object> vm_map = new HashMap<String, Object>();
		vm_map.put("List_map", List_map);
		vm_map.put("startNum", pageParameter.getStart() + 1);
		vm_map.put("contextpath", contextpath);
		String html = "";
		String vm = "authadmin/applydocaudit/dataaudit.vm";
		html = templateService.mergeTemplateIntoString(vm, vm_map);

		Map<String, Object> data_map = new HashMap<String, Object>();
		data_map.put("html", html);
		data_map.put("totoal_count", totoal_count);
		
		return data_map;
	}

}
