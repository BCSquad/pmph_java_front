package com.bc.pmpheep.back.commuser.materialdec.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.materialdec.service.MaterialDetailService;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * 注释:教材申报
 * @Author:黄琼飞
 * @date 2017-11-27上午10:10:34
 */
@Controller
@RequestMapping("/material/")
public class MaterialDetailController extends BaseController{
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.materialdec.service.MaterialDetailServiceImpl")
	private MaterialDetailService mdService;
	
	//跳转到新增页面
	@RequestMapping("toMaterialAdd")
	public ModelAndView toMaterialAdd(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("commuser/materialdec/toMaterialAdd");
		Map<String,Object> userMap =  this.getUserInfo();
		//String material_id = request.getParameter("material_id"); //教材ID
		String material_id = "119";
		//教材信息
		Map<String,Object> materialMap = new HashMap<String,Object>();
		materialMap = this.mdService.queryMaterialbyId(material_id);
		//书籍信息
		List<Map<String,Object>> bookList = this.mdService.queryBookById(material_id);
		StringBuffer bookSelects = new StringBuffer();
		for (Map<String, Object> map : bookList) {
			bookSelects.append("<option value='"+map.get("id")+"'>"+map.get("textbook_name")+"</option>");
		}
		mav.addObject("bookSelects", bookSelects.toString());
		mav.addObject("materialMap", materialMap);
		mav.addObject("userMap", userMap);
		return mav;
	}
	
	//执行添加
	@RequestMapping("doMaterialAdd")
	public String doMaterialAdd(HttpServletRequest request,
			HttpServletResponse response){
		//公共参数
		String material_id = request.getParameter("material_id");
		String user_id = request.getParameter("user_id");
		String msg = "";
		//创建时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());
		//专家信息
		Map<String,Object> perMap = new HashMap<String,Object>();
		perMap.put("realname", request.getParameter("realname"));
		perMap.put("user_id", user_id);
		perMap.put("material_id", material_id);
		perMap.put("sex", request.getParameter("sex"));
		perMap.put("birthday", request.getParameter("birthday"));
		perMap.put("experience", request.getParameter("experience"));
		perMap.put("org_name", request.getParameter("org_name"));
		perMap.put("position", request.getParameter("position"));
		perMap.put("title", request.getParameter("title"));
		perMap.put("address", request.getParameter("address"));
		perMap.put("postcode", request.getParameter("postcode"));
		perMap.put("telephone", request.getParameter("telephone"));
		perMap.put("handphone", request.getParameter("handphone"));
		perMap.put("email", request.getParameter("email"));
		perMap.put("idtype", request.getParameter("idtype"));
		perMap.put("idcard", request.getParameter("idcard"));
		perMap.put("org_id", request.getParameter("edu"));
		perMap.put("gmt_create", date);	
		int count = this.mdService.insertPerson(perMap);
		if(count>0){ //表示主表已添加
			List<Map<String,Object>> perList = this.mdService.queryPerson(perMap);
			Object declaration_id = perList.get(0).get("id");
			//获取图书选择参数
			Map<String,Object> tsxzMap = new HashMap<String,Object>();
			String textbook_id = request.getParameter("textbook_id");
			String preset_position = request.getParameter("preset_position");
			String syllabus_id = request.getParameter("syllabus_id");
			String syllabus_name = request.getParameter("syllabus_name");
			tsxzMap.put("textbook_id", textbook_id);
			tsxzMap.put("declaration_id", declaration_id);
			tsxzMap.put("preset_position", preset_position);
			tsxzMap.put("syllabus_id", syllabus_id);
			tsxzMap.put("syllabus_name", syllabus_name);
			tsxzMap.put("gmt_create", date);
			this.mdService.insertTsxz(tsxzMap);
			//主要学习经历
			
			msg = "OK";
		}
		
		return msg;
	}
	//查看详情
	@RequestMapping("showMaterial")
	public ModelAndView showMaterial(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("commuser/materialdec/showMaterial");
		List<Map<String,Object>> gezlList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> tsxzList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> stuList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> workList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> steaList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> jcbjList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> gjkcjsList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> jcbxList = new ArrayList<Map<String,Object>>();
		//传参  user_id  material_id
	//	String user_id = request.getParameter("user_id");
	//	String material_id = request.getParameter("material_id");
		String user_id = "32781";
		String material_id = "123";
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("user_id", user_id);
		queryMap.put("material_id", material_id);
		
		gezlList = this.mdService.queryPerson(queryMap);
		queryMap.put("declaration_id", gezlList.get(0).get("id"));
		
		tsxzList=this.mdService.queryTsxz(queryMap);
		stuList=this.mdService.queryStu(queryMap);
		workList=this.mdService.queryWork(queryMap);
		steaList=this.mdService.queryStea(queryMap);
		jcbjList=this.mdService.queryJcbj(queryMap);
		gjkcjsList=this.mdService.queryGjkcjs(queryMap);
		jcbxList=this.mdService.queryJcbx(queryMap);
		
		mav.addObject("gezlList", gezlList.get(0));
		mav.addObject("tsxzList", tsxzList);
		mav.addObject("stuList", stuList);
		mav.addObject("workList", workList);
		mav.addObject("steaList", steaList);
		mav.addObject("jcbjList", jcbjList);
		mav.addObject("gjkcjsList", gjkcjsList);
		mav.addObject("jcbxList", jcbxList);
		return mav;
	}
}
