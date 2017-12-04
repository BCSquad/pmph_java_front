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

/**
 * @author (作者) 
 * 申报资料审核（机构用户）
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 
 * @modify (最后修改时间) 
 * @审核人 ：
 * </pre>
 */
@Controller
@RequestMapping(value = "/dataaudit")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DataAuditController {
    @Autowired
    @Qualifier("com.bc.pmpheep.back.authadmin.applydocaudit.service.DataAuditServiceImpl")
    DataAuditService dataAuditService;
    
	@Autowired
    @Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
    private TemplateService templateService;

	//跳转到主页
	@RequestMapping("toPage")
	public ModelAndView toPage(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();
		String material_id = request.getParameter("material_id");
		String material_name = request.getParameter("material_name");
		material_name = new String(material_name .getBytes("iso8859-1"),"utf-8"); 
		mv.addObject("material_id",material_id);
		mv.addObject("material_name",material_name);
		mv.setViewName("authadmin/applydocaudit/dataaudit");
		return mv;
	}
	
	/**
	 * 申报资料审核（机构用户）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findDataAudit",method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean<Map<String,Object>> findDataAudit(HttpServletRequest request){
		
		ResponseBean<Map<String,Object>> rb = new ResponseBean<Map<String,Object>>();
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String queryName = request.getParameter("queryName");
		String material_id = request.getParameter("material_id");
		String contextpath = request.getParameter("contextpath");
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("queryName", queryName);
		paraMap.put("material_id", material_id);
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<Map<String,Object>>(pageNum,pageSize);
		pageParameter.setParameter(paraMap);
		List<Map<String, Object>> List_map = dataAuditService.findDataAudit(pageParameter);
		int totoal_count = dataAuditService.findDataAuditCount(pageParameter);
		
		Map<String, Object> vm_map = new HashMap<String, Object>();
		vm_map.put("List_map", List_map);
		vm_map.put("startNum", pageParameter.getStart()+1);
		vm_map.put("contextpath", contextpath);
		String html ="";
		String vm = "authadmin/applydocaudit/dataaudit.vm";
		html = templateService.mergeTemplateIntoString(vm, vm_map);
		
		Map<String,Object> data_map = new HashMap<String,Object>();
		data_map.put("html", html);
		data_map.put("totoal_count", totoal_count);
		rb.setData(data_map);
		return rb;
	}
    
    
}
