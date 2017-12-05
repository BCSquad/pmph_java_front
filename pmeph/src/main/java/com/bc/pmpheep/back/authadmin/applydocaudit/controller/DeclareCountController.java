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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
public class DeclareCountController extends BaseController{
	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.applydocaudit.service.DeclareCountServiceImpl")
	DeclareCountService declareCountService;

	@Autowired
	@Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
	private TemplateService templateService;

	/**
	 * 
	 * @Title: findDataAudit
	 * @Description: 我校统计情况 最终结果名单列表
	 * @param @param request
	 * @param @return
	 * @param @throws UnsupportedEncodingException
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/findDeclareCount")
	public ModelAndView findDataAudit(HttpServletRequest request)
			throws UnsupportedEncodingException {
		String material_id = request.getParameter("material_id");
		String material_name = request.getParameter("material_name");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("material_id", material_id);
		// 我校统计情况
		List<Map<String, Object>> list = declareCountService
				.findDeclareCount(paraMap);
		// 最终结果名单列表
		List<Map<String, Object>> listName = declareCountService
				.findNameList(paraMap);
		ModelAndView mv = new ModelAndView();

		material_name = new String(material_name.getBytes("iso8859-1"), "utf-8");
		mv.addObject("material_id", material_id);
		mv.addObject("material_name", material_name);
		mv.addObject("listMap", list);
		mv.addObject("listName", listName);
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
	@RequestMapping(value = "/loadMore")
	@ResponseBody
	public List<Map<String, Object>> loadMore(HttpServletRequest request) {
		String para = request.getParameter("startPara");
		int startPara = 0;
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (null != para && !para.equals("")) {
			startPara = Integer.parseInt(para);
			paraMap.put("startPara", startPara);
		} else {
			startPara = 15;
			paraMap.put("startPara", startPara);
		}
		List<Map<String, Object>> list = declareCountService
				.selectNoticeMessage(paraMap);
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
	@RequestMapping(value = "/selectAll")
	@ResponseBody
	public List<Map<String, Object>> selectAll(HttpServletRequest request) {
		List<Map<String, Object>> list = declareCountService.selectAll();
		return list;
	}

}
