package com.bc.pmpheep.back.authadmin.applydocaudit.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
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


import com.bc.pmpheep.back.authadmin.applydocaudit.service.ApplyDocAuditService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.template.service.TemplateService;
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.general.controller.BaseController;
/**
 * 
 * @author liudi
 *	申报资料审核（机构用户）
 */
@Controller
@RequestMapping(value = "/applyDocAudit")
public class ApplyDocAuditController extends BaseController {
	@Autowired
    @Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
    private TemplateService templateService;
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.applydocaudit.service.ApplyDocAuditServiceImpl")
	private ApplyDocAuditService applyDocAuditService;
	
	/**
	 * 跳转至页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toPage",method = RequestMethod.GET)
	public ModelAndView approvelList(HttpServletRequest request){
		Map<String, Object> user = getUserInfo();
		BigInteger org_id=(BigInteger) user.get("org_id");
		ModelAndView mv = new  ModelAndView();
		String queryCon = request.getParameter("queryCon");
		String queryStatus = request.getParameter("queryStatus");
		Map<String,Object> queryConMap = new HashMap<String,Object>();
		queryConMap.put("queryCon", queryCon);
		queryConMap.put("queryStatus", queryStatus);
		queryConMap.put("org_id", org_id);
		String contextpath = request.getParameter("contextpath");
		
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<Map<String,Object>>(1,9);
		pageParameter.setParameter(queryConMap);
		List<Map<String, Object>> List_map = applyDocAuditService.materialDeclareAuditListQuery(pageParameter);
		int totoal_count = applyDocAuditService.materialDeclareAuditListQueryCount(pageParameter);
		String html ="";
		String vm = "authadmin/applydocaudit/applydocaudit.vm";
		SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
		for (Map<String, Object> m : List_map) {
			Date now = new Date();
			if(m.get("actualDeadline") != null){
				
				m.put("isEnd",/*Integer.parseInt(dt.format(now))>Integer.parseInt(dt.format((Date)m.get("actualDeadline")))||*/((Boolean)m.get("is_all_textbook_published") || (Boolean)m.get("is_force_end"))?1:0);
				m.put("contextpath", contextpath);
			}else{
				m.put("isEnd", 0);
				m.put("contextpath", contextpath);
			}
			html += templateService.mergeTemplateIntoString(vm, m);
		}
		
		mv.addObject("materialNineTable",html);
		mv.setViewName("/authadmin/applydocaudit/applydocaudit");
		mv.addObject("totoal_count",totoal_count);
		return mv;
	}
	
	/**
	 * 翻页，模糊查询，状态查询，刷新列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approvelninelist",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> approvelninelist(HttpServletRequest request){
		Map<String, Object> user = getUserInfo();
		BigInteger org_id=(BigInteger) user.get("org_id");
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String queryCon = request.getParameter("queryCon");
		String queryStatus = request.getParameter("queryStatus");
		Map<String,Object> queryConMap = new HashMap<String,Object>();
		queryConMap.put("queryCon", queryCon);
		queryConMap.put("queryStatus", queryStatus);
		queryConMap.put("org_id", org_id);
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<Map<String,Object>>(pageNum,9);
		pageParameter.setParameter(queryConMap);
		List<Map<String, Object>> List_map = applyDocAuditService.materialDeclareAuditListQuery(pageParameter);
		int totoal_count = applyDocAuditService.materialDeclareAuditListQueryCount(pageParameter);
		String html ="";
		String vm = "authadmin/applydocaudit/applydocaudit.vm";
		SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
		for (Map<String, Object> m : List_map) {
			Date now = new Date();
			if(m.get("actualDeadline") != null){
				
				m.put("isEnd",/*Integer.parseInt(dt.format(now))>Integer.parseInt(dt.format((Date)m.get("actualDeadline")))||*/((Boolean)m.get("is_all_textbook_published") || (Boolean)m.get("is_force_end"))?1:0);
				
			}else{
				m.put("isEnd", 0);
				
			}
			html += templateService.mergeTemplateIntoString(vm, m);
		}
		Map<String,Object> data_map = new HashMap<String,Object>();
		data_map.put("html", html);
		data_map.put("totoal_count", totoal_count);
		
		return data_map;
	}
}
