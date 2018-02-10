package com.bc.pmpheep.back.authadmin.applydocaudit.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.authadmin.applydocaudit.service.DataAuditService;
import com.bc.pmpheep.back.commuser.materialdec.service.MaterialDetailService;
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
		String view_audit = request.getParameter("view_audit");
		Map<String,Object> userMap =  this.getUserInfo();
		String user_id = userMap.get("id").toString();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("material_id", material_id);
		//获取标题
		String	material_name = dataAuditService.findTitleName(map);
		mv.addObject("material_id", material_id);
		mv.addObject("view_audit", view_audit);
		mv.addObject("material_name", material_name);
		mv.addObject("userId", user_id);
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
		paraMap.put("userId", this.getUserInfo().get("id").toString());
		
		PageParameter<Map<String, Object>> pageParameter = new PageParameter<Map<String, Object>>(
				pageNum, pageSize);
		pageParameter.setParameter(paraMap);
		List<Map<String, Object>> List_map = dataAuditService
				.findDataAudit(pageParameter);
		int totoal_count = dataAuditService.findDataAuditCount(pageParameter);

		
		Map<String, Object> vm_map = new HashMap<String, Object>();
		vm_map.put("List_map", List_map);
		vm_map.put("material_id", material_id);
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
	
		//申报审核页面
		@RequestMapping("toMaterialAudit")
		public ModelAndView toMaterialAudit(HttpServletRequest request,
				HttpServletResponse response){
			ModelAndView mav = new ModelAndView("authadmin/applydocaudit/toMaterialAudit");
			//传参  user_id  material_id
			String material_id = request.getParameter("material_id");
			String declaration_id = request.getParameter("declaration_id");
			String view_audit = request.getParameter("view_audit");
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("material_id", material_id); 
			queryMap.put("declaration_id", declaration_id); 
			
			//1.作家申报表
			List<Map<String,Object>> gezlList = new ArrayList<Map<String,Object>>();
			gezlList = this.dataAuditService.queryPerson(queryMap);
			
			if(declaration_id == null){
			queryMap.put("declaration_id", gezlList.get(0).get("id"));
			}else{
				queryMap.put("declaration_id", declaration_id);
			}
			//2.作家申报职位
			List<Map<String,Object>> tsxzList = new ArrayList<Map<String,Object>>();
			tsxzList=this.dataAuditService.queryTsxz(queryMap);
			//3.作家学习经历表
			List<Map<String,Object>> stuList = new ArrayList<Map<String,Object>>();
			stuList=this.dataAuditService.queryStu(queryMap);
			//4.作家工作经历表
			List<Map<String,Object>> workList = new ArrayList<Map<String,Object>>();
			workList=this.dataAuditService.queryWork(queryMap);
			//5.作家教学经历表
			List<Map<String,Object>> steaList = new ArrayList<Map<String,Object>>();
			steaList=this.dataAuditService.queryStea(queryMap);
			//6.作家兼职学术表
			List<Map<String,Object>> zjxsList = new ArrayList<Map<String,Object>>();
			zjxsList=this.dataAuditService.queryZjxs(queryMap);
			//7.作家上套教材参编情况表
			List<Map<String,Object>> jcbjList = new ArrayList<Map<String,Object>>();
			jcbjList=this.dataAuditService.queryJcbj(queryMap);
			//8.作家精品课程建设情况表
			List<Map<String,Object>> gjkcjsList = new ArrayList<Map<String,Object>>();
			gjkcjsList=this.dataAuditService.queryGjkcjs(queryMap);
			//9.作家主编国家级规划教材情况表
			List<Map<String,Object>> gjghjcList = new ArrayList<Map<String,Object>>();
			gjghjcList = this.dataAuditService.queryGjghjc(queryMap);
			//10.作家教材编写情况表
			List<Map<String,Object>> jcbxList = new ArrayList<Map<String,Object>>();
			jcbxList=this.dataAuditService.queryJcbx(queryMap);
			//11.作家科研情况表
			List<Map<String,Object>> zjkyList = new ArrayList<Map<String,Object>>();
			zjkyList = this.dataAuditService.queryZjkyqk(queryMap);
			//12.作家扩展项填报表
			List<Map<String,Object>> zjkzqkList = new ArrayList<Map<String,Object>>();
			zjkzqkList = this.dataAuditService.queryZjkzbb(queryMap);

			//填充
			mav.addObject("gezlList", gezlList.get(0));
			mav.addObject("tsxzList", tsxzList);
			mav.addObject("stuList", stuList);
			mav.addObject("workList", workList);
			mav.addObject("steaList", steaList);
			mav.addObject("jcbjList", jcbjList);
			mav.addObject("gjkcjsList", gjkcjsList);
			mav.addObject("jcbxList", jcbxList);
			mav.addObject("gjghjcList", gjghjcList);
			mav.addObject("zjkyList", zjkyList);
			mav.addObject("zjxsList", zjxsList);
			mav.addObject("zjkzqkList", zjkzqkList);
			mav.addObject("view_audit", view_audit);
			mav.addObject("material_id", material_id);
			mav.addObject("online_progress", gezlList.get(0).get("online_progress").toString());//判断审核通过、退回按钮是否隐藏
			return mav;
		}
		
		
		//申报审核
		@RequestMapping("doMaterialAudit")
		@ResponseBody
		public Map<String,Object> doMaterialAudit(HttpServletRequest request,
				HttpServletResponse response){
			Map<String,Object> paramMap = new HashMap<String,Object>();
			Map<String,Object> resultMap = new HashMap<String,Object>();
			String declaration_id = request.getParameter("declaration_id");
			String type = request.getParameter("type");  //类型
			Map<String,Object> userMap =  this.getUserInfo();
			String user_id = userMap.get("id").toString();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String date = df.format(new Date());
			String msg = "";
			
			paramMap.put("declaration_id", declaration_id);
			paramMap.put("online_progress", type);
			paramMap.put("auth_user_id", user_id);
			paramMap.put("auth_date", date);
			int count = this.dataAuditService.updateDeclaration(paramMap);
			if(count>0){
				msg = "OK";
			}
			resultMap.put("msg", msg);
			return resultMap;
		}
		

		
		/**
		 * 页面组合方法，主要js中通过ajax传值对新增页面模块进行初始化操作
		 * @param request
		 * @return
		 */
		@RequestMapping("queryMaterialMap")
		@ResponseBody
		public Map<String,Object> queryMaterialMap(HttpServletRequest request){
			//教材信息
			String material_id = request.getParameter("material_id");
			Map<String,Object> materialMap = new HashMap<String,Object>();
			materialMap = this.dataAuditService.queryMaterialbyId(material_id);
			if(materialMap==null){
				materialMap=new HashMap<String,Object>();
			}
			return materialMap;
		}

}
