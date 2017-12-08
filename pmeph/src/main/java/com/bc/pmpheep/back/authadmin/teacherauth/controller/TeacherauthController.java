package com.bc.pmpheep.back.authadmin.teacherauth.controller;

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

import com.bc.pmpheep.back.authadmin.teacherauth.service.TeacherAuthService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.template.service.TemplateService;
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * 教师认证 控制层
 * @author liudi
 *
 */
@Controller
@RequestMapping("teacherauth")
public class TeacherauthController extends BaseController {
	
	@Autowired
    @Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
    private TemplateService templateService;
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.teacherauth.service.TeacherAuthServiceImpl")
	TeacherAuthService teacherAuthService;
	
	/**
	 * 跳转到教师认证界面
	 */
	@RequestMapping("toPage")
	public ModelAndView toPage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("authadmin/teacherauth/teacherauth");
		
		return mv;
	}
	
	/**
	 * 教师认证列表查询更新
	 */
	@RequestMapping(value = "/queryTeacherAuth",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryTeacherAuth(HttpServletRequest request){
		Map<String, Object> user = getUserInfo();
		BigInteger orgUserId=(BigInteger) user.get("id");
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String queryName = request.getParameter("queryName");
		String queryStatus = request.getParameter("queryStatus");
		String contextpath = request.getParameter("contextpath");
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("queryName", queryName);
		paraMap.put("queryStatus", queryStatus);
		paraMap.put("orgUserId", orgUserId);
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<Map<String,Object>>(pageNum,pageSize);
		pageParameter.setParameter(paraMap);
		List<Map<String, Object>> List_map = teacherAuthService.queryTeacherAuthList(pageParameter);
		int totoal_count = teacherAuthService.queryTeacherAuthCount(pageParameter);
		Map<String, Object> vm_map = new HashMap<String, Object>();
		vm_map.put("List_map", List_map);
		vm_map.put("startNum", pageParameter.getStart()+1);
		vm_map.put("contextpath", contextpath);
		String html ="";
		String vm = "authadmin/teacherauth/teacherauth.vm";
		html = templateService.mergeTemplateIntoString(vm, vm_map);
		
		Map<String,Object> data_map = new HashMap<String,Object>();
		data_map.put("html", html);
		data_map.put("totoal_count", totoal_count);
		
		return data_map;
	}
	
	/**
	 * 改变教师认证状态 通过或退回 （若有专门的认证界面，此方法废弃，换做跳转到认证界面方法）
	 */
	@RequestMapping(value="statusModify" ,method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> statusModify(HttpServletRequest request){
		String status = request.getParameter("status");
		String id = request.getParameter("id");
		Map<String,Object> rb = teacherAuthService.statusModify(id,status);
		
		return rb;
	}
	
	
}
