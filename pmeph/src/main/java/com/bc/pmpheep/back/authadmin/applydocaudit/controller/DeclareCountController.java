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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.authadmin.applydocaudit.service.DataAuditService;
import com.bc.pmpheep.back.authadmin.applydocaudit.service.DeclareCountService;
import com.bc.pmpheep.back.template.service.TemplateService;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * 
 * @ClassName: DeclareCountController
 * @Description: 申报统计（机构用户）
 * @author SunZhuoQun
 * @date 2017-12-5 上午9:33:35
 * 
 */
@Controller
@RequestMapping(value = "/declareCount")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DeclareCountController extends BaseController {
	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.applydocaudit.service.DeclareCountServiceImpl")
	DeclareCountService declareCountService;

	@Autowired
	@Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
	private TemplateService templateService;

	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.applydocaudit.service.DataAuditServiceImpl")
	DataAuditService dataAuditService;

	/**
	 * 
	 * @Title: findDataAudit
	 * @Description: 查询我校统计情况 及最终结果名单列表
	 * @param @param request
	 * @param @return
	 * @param @throws UnsupportedEncodingException
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/findDeclareCount", method = RequestMethod.GET)
	public ModelAndView findDataAudit(HttpServletRequest request)
			throws UnsupportedEncodingException {
		String material_id = request.getParameter("material_id");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("material_id", material_id);
		// 获取标题
		String material_name = dataAuditService.findTitleName(paraMap);

		// 我校统计情况
		List<Map<String, Object>> list = declareCountService
				.findDeclareCount(paraMap);
		// 最终结果名单列表
		// List<Map<String, Object>> listName = declareCountService
		// .findNameList(paraMap);
		ModelAndView mv = new ModelAndView();
		mv.addObject("material_id", material_id);
		mv.addObject("material_name", material_name);
		mv.addObject("listMap", list);
		mv.setViewName("authadmin/applydocaudit/declarecount");
		return mv;
	}

	/**
	 * 
	 * @Title: loadMore
	 * @Description: 加载更多
	 * @param @param request
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/loadMore", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> loadMore(HttpServletRequest request) {
		String material_id = request.getParameter("material_id");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("material_id", material_id);
		List<Map<String, Object>> list = declareCountService
				.findNameList(paraMap);
		return list;
	}

	/**
	 * 查看全部
	 * 
	 * @Title: selectAll
	 * @Description: 我校统计情况
	 * @param @param request
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/selectAll", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> selectAll(HttpServletRequest request) {
		String material_id = request.getParameter("material_id");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("material_id", material_id);
		List<Map<String, Object>> list = declareCountService.selectAll(paraMap);
		return list;
	}

	/**
	 * 查看全部
	 * 
	 * @Title: selectResults
	 * @Description: 最终结果名单
	 * @param @param request
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/selectResults", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> selectResults(HttpServletRequest request) {
		String material_id = request.getParameter("material_id");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("material_id", material_id);
		List<Map<String, Object>> list = declareCountService
				.selectResults(paraMap);
		return list;
	}

}
