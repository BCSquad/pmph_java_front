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
			Map<String,Object> xxjlMap = new HashMap<String,Object>();
			xxjlMap.put("declaration_id", declaration_id);
			xxjlMap.put("major", request.getParameter("xx_major"));
			xxjlMap.put("degree", request.getParameter("xx_degree"));
			xxjlMap.put("note", request.getParameter("xx_note"));
			xxjlMap.put("date_begin", request.getParameter("xx_kssj"));
			xxjlMap.put("date_end", request.getParameter("xx_jssj"));
			this.mdService.insertStu(xxjlMap);
			//作家工作经历
			Map<String,Object> workjlMap = new HashMap<String,Object>();
			workjlMap.put("declaration_id", declaration_id);
			workjlMap.put("org_name", request.getParameter("gz_org_name"));
			workjlMap.put("position", request.getParameter("gz_position"));
			workjlMap.put("note", request.getParameter("gz_note"));
			workjlMap.put("date_begin", request.getParameter("gz_kssj"));
			workjlMap.put("date_end", request.getParameter("gz_jssj"));
			this.mdService.insertWork(workjlMap);
			//教学经历表
			Map<String,Object> jxjlMap = new HashMap<String,Object>();
			jxjlMap.put("declaration_id", declaration_id);
			jxjlMap.put("school_name", request.getParameter("jx_school_name"));
			jxjlMap.put("subject", request.getParameter("jx_subject"));
			jxjlMap.put("note", request.getParameter("jx_note"));
			jxjlMap.put("date_begin", request.getParameter("jx_kssj"));
			jxjlMap.put("date_end", request.getParameter("jx_jssj"));
			this.mdService.insertStea(jxjlMap);
			//作家兼职学术
			Map<String,Object> xsjzMap = new HashMap<String,Object>();
			xsjzMap.put("declaration_id", declaration_id);
			xsjzMap.put("org_name", request.getParameter("xs_org_name"));
			xsjzMap.put("rank", request.getParameter("xs_rank"));
			xsjzMap.put("note", request.getParameter("xs_note"));
			xsjzMap.put("position", request.getParameter("xs_position"));
			this.mdService.insertZjxs(xsjzMap);
			//上套教材参编情况
			Map<String,Object> JcbjMap = new HashMap<String,Object>();
			JcbjMap.put("declaration_id", declaration_id);
			JcbjMap.put("material_name", request.getParameter("jc_material_name"));
			JcbjMap.put("position", request.getParameter("jc_position"));
			JcbjMap.put("note", request.getParameter("jc_note"));
			this.mdService.insertJcbj(JcbjMap);
			//精品课程建设情况
			Map<String,Object> GjkcjsMap = new HashMap<String,Object>();
			GjkcjsMap.put("declaration_id", declaration_id);
			GjkcjsMap.put("course_name", request.getParameter("gj_course_name"));
			GjkcjsMap.put("class_hour", request.getParameter("gj_class_hour"));
			GjkcjsMap.put("type", request.getParameter("gj_type"));
			GjkcjsMap.put("note", request.getParameter("gj_note"));
			this.mdService.insertGjkcjs(GjkcjsMap);
			//主编国家级规划教材情况
			Map<String,Object> GjghjcMap = new HashMap<String,Object>();
			GjghjcMap.put("declaration_id", declaration_id);
			GjghjcMap.put("material_name", request.getParameter("hj_material_name"));
			GjghjcMap.put("isbn", request.getParameter("hj_isbn"));
			GjghjcMap.put("rank", request.getParameter("hj_rank"));
			GjghjcMap.put("note", request.getParameter("hj_note"));
			this.mdService.insertGjghjc(GjghjcMap);
			//作家教材编写情况
			Map<String,Object> JcbxMap = new HashMap<String,Object>();
			JcbxMap.put("declaration_id", declaration_id);
			JcbxMap.put("material_name", request.getParameter("jcb_material_name"));
			JcbxMap.put("rank", request.getParameter("jcb_rank"));
			JcbxMap.put("position", request.getParameter("jcb_position"));
			JcbxMap.put("publisher", request.getParameter("jcb_publisher"));
			JcbxMap.put("publish_date", request.getParameter("jcb_publish_date"));
			JcbxMap.put("isbn", request.getParameter("jcb_isbn"));
			JcbxMap.put("note", request.getParameter("jcb_note"));
			this.mdService.insertJcbx(JcbxMap);
			//作家科研情况
			Map<String,Object> ZjkyqkMap = new HashMap<String,Object>();
			ZjkyqkMap.put("declaration_id", declaration_id);
			ZjkyqkMap.put("research_name", request.getParameter("zjk_research_name"));
			ZjkyqkMap.put("approval_unit", request.getParameter("zjk_approval_unit"));
			ZjkyqkMap.put("award", request.getParameter("zjk_award"));
			ZjkyqkMap.put("note", request.getParameter("zjk_note"));
			this.mdService.insertZjkyqk(ZjkyqkMap);
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
