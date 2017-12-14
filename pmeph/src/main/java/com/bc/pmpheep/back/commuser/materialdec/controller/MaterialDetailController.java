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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.authadmin.message.service.SendMessageServiceImpl;
import com.bc.pmpheep.back.commuser.materialdec.service.MaterialDetailService;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.service.FileService;

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
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.message.service.SendMessageServiceImpl")
	private SendMessageServiceImpl sendMessageServiceImpl;
	@Autowired
    @Qualifier("com.bc.pmpheep.general.service.FileService")
    FileService fileService;
	
	/**
	 * 跳转到申报新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping("toMaterialAdd")
	public ModelAndView toMaterialAdd(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("commuser/materialdec/toMaterialAdd");
		Map<String,Object> userMap =  this.getUserInfo();
		String material_id = request.getParameter("material_id"); //教材ID
		if(material_id == null){			
			material_id = "120";
		}
		//教材信息
		Map<String,Object> materialMap = new HashMap<String,Object>();
		materialMap = this.mdService.queryMaterialbyId(material_id);
		//作家扩展信息
		List<Map<String,Object>> zjkzxxList = this.mdService.queryZjkzxxById(material_id);
		//书籍信息
		List<Map<String,Object>> bookList = this.mdService.queryBookById(material_id);
		StringBuffer bookSelects = new StringBuffer();
		for (Map<String, Object> map : bookList) {
			bookSelects.append("<option value='"+map.get("id")+"'>"+map.get("textbook_name")+"</option>");
		}
		//机构信息
		List<Map<String,Object>> orgList = this.mdService.queryOrgById(material_id);
		StringBuffer orgSelects = new StringBuffer();
		if(orgList.size()>0){
		for (Map<String, Object> map : orgList) {
			orgSelects.append("<option value='"+map.get("org_id")+"'>"+map.get("org_name")+"</option>");
		}}
		mav.addObject("bookSelects", bookSelects.toString());
		mav.addObject("orgSelects", orgSelects.toString());
		mav.addObject("materialMap", materialMap);
		mav.addObject("userMap", userMap);
		mav.addObject("zjkzxxList", zjkzxxList);
		mav.addObject("material_id", materialMap.get("id"));
		return mav;
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
		materialMap = this.mdService.queryMaterialbyId(material_id);
		return materialMap;
	}
	
	/**
	 * 添加申报信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doMaterialAdd")
	@ResponseBody
	public String doMaterialAdd(HttpServletRequest request,
			HttpServletResponse response){
		//公共参数
		String material_id = request.getParameter("material_id");
		String user_id = request.getParameter("user_id"); //系统用户(暂存人)
		String type = request.getParameter("type"); //类型
		String is_background = "0";
		String msg = "";
		//创建时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());
		//专家信息
		Map<String,Object> perMap = new HashMap<String,Object>();
		if(type.equals("1")){ //提交
			perMap.put("is_staging", "0");
		}else{ //暂存
			perMap.put("is_staging", "1");
		}
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
			String textbook_ids[] = request.getParameterValues("textbook_id");
			String preset_positions[] = request.getParameterValues("preset_position");
			String syllabus_ids[] = request.getParameterValues("syllabus_id");
			String syllabus_names[] = request.getParameterValues("syllabus_name");
			for(int i=0;i<preset_positions.length;i++) { //遍历数组
				Map<String,Object> tsxzMap = new HashMap<String,Object>();
				String preset_position = request.getParameter(preset_positions[i].toString());
				tsxzMap.put("textbook_id", textbook_ids[i]);
				tsxzMap.put("declaration_id", declaration_id);
				tsxzMap.put("preset_position", preset_position);
				tsxzMap.put("is_on_list", "1"); //默认值
				tsxzMap.put("author_id", user_id); //暂存人Id
				tsxzMap.put("is_background", is_background); //是否为社内用户
				tsxzMap.put("syllabus_id", syllabus_ids[i]);
				tsxzMap.put("syllabus_name", syllabus_names[i]);
				tsxzMap.put("gmt_create", date);
				if(type.equals("1")){ //提交					
					this.mdService.insertTsxz(tsxzMap);
				}else{ //暂存
					this.mdService.insertTssbZc(tsxzMap);
				}
			}
			//主要学习经历
			String xx_kssj[] = request.getParameterValues("xx_kssj");
			String xx_jssj[] = request.getParameterValues("xx_jssj");
			String xx_major[] = request.getParameterValues("xx_major");
			String xx_degree[] = request.getParameterValues("xx_degree");
			String xx_note[] = request.getParameterValues("xx_note");
			String xx_school_name[] = request.getParameterValues("xx_school_name");
			
			for(int i=0;i<xx_kssj.length;i++) { //遍历数组
				if(!xx_kssj[i].equals("")){
					Map<String,Object> xxjlMap = new HashMap<String,Object>();
					xxjlMap.put("declaration_id", declaration_id);
					xxjlMap.put("major", xx_major[i]);
					xxjlMap.put("xx_school_name", xx_school_name[i]);
					xxjlMap.put("degree", xx_degree[i]);
					xxjlMap.put("note", xx_note[i]);
					xxjlMap.put("date_begin", xx_kssj[i]);
					xxjlMap.put("date_end", xx_jssj[i]);
					this.mdService.insertStu(xxjlMap);
				}
			}
			//作家工作经历
			String gz_kssj[] = request.getParameterValues("gz_kssj");
			String gz_jssj[] = request.getParameterValues("gz_jssj");
			String gz_org_name[] = request.getParameterValues("gz_org_name");
			String gz_position[] = request.getParameterValues("gz_position");
			String gz_note[] = request.getParameterValues("gz_note");
			for(int i=0;i<gz_kssj.length;i++) { //遍历数组
				if(!gz_kssj[i].equals("")){ //判断是否存在
					Map<String,Object> workjlMap = new HashMap<String,Object>();
					workjlMap.put("declaration_id", declaration_id);
					workjlMap.put("org_name", gz_org_name[i]);
					workjlMap.put("position", gz_position[i]);
					workjlMap.put("note", gz_note[i]);
					workjlMap.put("date_begin", gz_kssj[i]);
					workjlMap.put("date_end", gz_jssj[i]);
					this.mdService.insertWork(workjlMap);
				}
			}
			//教学经历表
			String jx_kssj[] = request.getParameterValues("jx_kssj");
			String jx_jssj[] = request.getParameterValues("jx_jssj");
			String jx_school_name[] = request.getParameterValues("jx_school_name");
			String jx_subject[] = request.getParameterValues("jx_subject");
			String jx_note[] = request.getParameterValues("jx_note");
			for(int i=0;i<jx_kssj.length;i++) { //遍历数组
				if(!jx_jssj[i].equals("")){ //判断是否存在
					Map<String,Object> jxjlMap = new HashMap<String,Object>();
					jxjlMap.put("declaration_id", declaration_id);
					jxjlMap.put("school_name", jx_school_name[i]);
					jxjlMap.put("subject", jx_subject[i]);
					jxjlMap.put("note", jx_note[i]);
					jxjlMap.put("date_begin", jx_kssj[i]);
					jxjlMap.put("date_end", jx_jssj[i]);
					this.mdService.insertStea(jxjlMap);
				}
			}
			//作家兼职学术
			String org_name[] = request.getParameterValues("org_name");
			String xs_rank[] = request.getParameterValues("xs_rank");
			String xs_position[] = request.getParameterValues("xs_position");
			String xs_note[] = request.getParameterValues("xs_note");
			for(int i=0;i<org_name.length;i++) { //遍历数组
				if(!org_name[i].equals("")){ //判断是否存在
					Map<String,Object> xsjzMap = new HashMap<String,Object>();
					xsjzMap.put("declaration_id", declaration_id);
					xsjzMap.put("org_name", org_name[i]);
					xsjzMap.put("rank", request.getParameter(xs_rank[i]));
					xsjzMap.put("note", xs_note[i]);
					xsjzMap.put("position", xs_position[i]);
					this.mdService.insertZjxs(xsjzMap);
				}	
			}
			//上套教材参编情况
			String jc_material_name[] = request.getParameterValues("jc_material_name");
			String jc_position[] = request.getParameterValues("jc_position");
			String jc_note[] = request.getParameterValues("jc_note");
			for(int i=0;i<jc_material_name.length;i++) { //遍历数组
				if(!jc_material_name[i].equals("")){ //判断是否存在
					Map<String,Object> JcbjMap = new HashMap<String,Object>();
					JcbjMap.put("declaration_id", declaration_id);
					JcbjMap.put("material_name", jc_material_name[i]);
					JcbjMap.put("position", request.getParameter(jc_position[i]));
					JcbjMap.put("note", jc_note[i]);
					this.mdService.insertJcbj(JcbjMap);
				}	
			}
			//精品课程建设情况
			String gj_course_name[] = request.getParameterValues("gj_course_name");
			String gj_class_hour[] = request.getParameterValues("gj_class_hour");
			String gj_note[] = request.getParameterValues("gj_note");
			String gj_type[] = request.getParameterValues("gj_type");
			for(int i=0;i<gj_type.length;i++) { //遍历数组
				if(!gj_course_name[i].equals("")){ //判断是否存在
					Map<String,Object> GjkcjsMap = new HashMap<String,Object>();
					GjkcjsMap.put("declaration_id", declaration_id);
					GjkcjsMap.put("course_name", gj_course_name[i]);
					GjkcjsMap.put("class_hour", gj_class_hour[i]);
					GjkcjsMap.put("type", gj_type[i]);
					GjkcjsMap.put("note", gj_note[i]);
					this.mdService.insertGjkcjs(GjkcjsMap);
				}	
			}	
			//主编国家级规划教材情况
			String hj_material_name[] = request.getParameterValues("hj_material_name");
			String hj_isbn[] = request.getParameterValues("hj_isbn");
			String hj_rank[] = request.getParameterValues("hj_rank");
			String hj_note[] = request.getParameterValues("hj_note");
			for(int i=0;i<hj_material_name.length;i++) { //遍历数组
				if(!hj_material_name[i].equals("")){ //判断是否存在
					Map<String,Object> GjghjcMap = new HashMap<String,Object>();
					GjghjcMap.put("declaration_id", declaration_id);
					GjghjcMap.put("hj_material_name",hj_material_name[i]);
					GjghjcMap.put("isbn",hj_isbn[i]);
					GjghjcMap.put("rank", request.getParameter(hj_rank[i]));
					GjghjcMap.put("note", hj_note[i]);
					this.mdService.insertGjghjc(GjghjcMap);
				}	
			}
			//作家教材编写情况
			String jcb_material_name[] = request.getParameterValues("jcb_material_name");
			String jcb_rank[] = request.getParameterValues("jcb_rank");
			String jcb_position[] = request.getParameterValues("jcb_position");
			String jcb_publisher[] = request.getParameterValues("jcb_publisher");
			String jcb_publish_date[] = request.getParameterValues("jcb_publish_date");
			String jcb_isbn[] = request.getParameterValues("jcb_isbn");
			String jcb_note[] = request.getParameterValues("jcb_note");
			for(int i=0;i<jcb_material_name.length;i++) { //遍历数组
				if(!jcb_material_name[i].equals("")){ //判断是否存在
					Map<String,Object> JcbxMap = new HashMap<String,Object>();
					JcbxMap.put("declaration_id", declaration_id);
					JcbxMap.put("material_name", jcb_material_name[i]);
					JcbxMap.put("rank", jcb_rank[i]);
					JcbxMap.put("position", jcb_position[i]);
					JcbxMap.put("publisher", jcb_publisher[i]);
					JcbxMap.put("publish_date", jcb_publish_date[i]);
					JcbxMap.put("isbn", jcb_isbn[i]);
					JcbxMap.put("note", jcb_note[i]);
					this.mdService.insertJcbx(JcbxMap);
				}	
			}	
			//作家科研情况
			String zjk_research_name[] = request.getParameterValues("zjk_research_name");
			String zjk_approval_unit[] = request.getParameterValues("zjk_approval_unit");
			String zjk_award[] = request.getParameterValues("zjk_award");
			String zjk_note[] = request.getParameterValues("zjk_note");
			for(int i=0;i<zjk_research_name.length;i++) { //遍历数组
				if(!zjk_research_name[i].equals("")){ //判断是否存在
					Map<String,Object> ZjkyqkMap = new HashMap<String,Object>();
					ZjkyqkMap.put("declaration_id", declaration_id);
					ZjkyqkMap.put("research_name", zjk_research_name[i]);
					ZjkyqkMap.put("approval_unit", zjk_approval_unit[i]);
					ZjkyqkMap.put("award", zjk_award[i]);
					ZjkyqkMap.put("note", zjk_note[i]);
					this.mdService.insertZjkyqk(ZjkyqkMap);
				}
			}
			msg = "OK";
		}
		return msg;
	}
	
	/**
	 * 查看申报详情
	 * @param request
	 * @return
	 */
	@RequestMapping("showMaterial")
	public ModelAndView showMaterial(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("commuser/materialdec/showMaterial");
		//传参  user_id  material_id
		Map<String,Object> userMap =  this.getUserInfo();
		//	String user_id = request.getParameter("user_id");
		String material_id = request.getParameter("material_id");
		String declaration_id = request.getParameter("declaration_id");
		String user_id = userMap.get("id").toString();
		if(material_id == null){			
			material_id = "120";
		}
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("user_id", user_id);
		queryMap.put("material_id", material_id); 
		
		//1.作家申报表
		List<Map<String,Object>> gezlList = new ArrayList<Map<String,Object>>();
		gezlList = this.mdService.queryPerson(queryMap);
		if(declaration_id == null){
		queryMap.put("declaration_id", gezlList.get(0).get("id"));
		}else{
			queryMap.put("declaration_id", declaration_id);
		}
		//2.作家申报职位
		List<Map<String,Object>> tsxzList = new ArrayList<Map<String,Object>>();
		tsxzList=this.mdService.queryTsxz(queryMap);
		//3.作家学习经历表
		List<Map<String,Object>> stuList = new ArrayList<Map<String,Object>>();
		stuList=this.mdService.queryStu(queryMap);
		//4.作家工作经历表
		List<Map<String,Object>> workList = new ArrayList<Map<String,Object>>();
		workList=this.mdService.queryWork(queryMap);
		//5.作家教学经历表
		List<Map<String,Object>> steaList = new ArrayList<Map<String,Object>>();
		steaList=this.mdService.queryStea(queryMap);
		//6.作家兼职学术表
		List<Map<String,Object>> zjxsList = new ArrayList<Map<String,Object>>();
		zjxsList=this.mdService.queryZjxs(queryMap);
		//7.作家上套教材参编情况表
		List<Map<String,Object>> jcbjList = new ArrayList<Map<String,Object>>();
		jcbjList=this.mdService.queryJcbj(queryMap);
		//8.作家精品课程建设情况表
		List<Map<String,Object>> gjkcjsList = new ArrayList<Map<String,Object>>();
		gjkcjsList=this.mdService.queryGjkcjs(queryMap);
		//9.作家主编国家级规划教材情况表
		List<Map<String,Object>> gjghjcList = new ArrayList<Map<String,Object>>();
		gjghjcList = this.mdService.queryGjghjc(queryMap);
		//10.作家教材编写情况表
		List<Map<String,Object>> jcbxList = new ArrayList<Map<String,Object>>();
		jcbxList=this.mdService.queryJcbx(queryMap);
		//11.作家科研情况表
		List<Map<String,Object>> zjkyList = new ArrayList<Map<String,Object>>();
		zjkyList = this.mdService.queryZjkyqk(queryMap);
		//12.作家扩展项填报表
		List<Map<String,Object>> zjkzqkList = new ArrayList<Map<String,Object>>();
		zjkzqkList = this.mdService.queryZjkzbb(queryMap);

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
		return mav;
	}
	
	/**
	 * 跳转到暂存页面
	 * @param request
	 * @return
	 */
	@RequestMapping("toMaterialZc")
	@ResponseBody
	public ModelAndView toMaterialZc(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView mav = new ModelAndView("commuser/materialdec/toMaterialZc");
		//传参   material_id declaration_id
		Map<String,Object> userMap =  this.getUserInfo();
		String material_id = request.getParameter("material_id");
		String declaration_id = request.getParameter("declaration_id");
		String user_id = userMap.get("id").toString();
		if(material_id == null){			
			material_id = "120";
		}
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("user_id", user_id);
		queryMap.put("material_id", material_id); 
		
		//1.作家申报信息表
		List<Map<String,Object>> gezlList = new ArrayList<Map<String,Object>>();
		gezlList = this.mdService.queryPerson(queryMap);
		if(declaration_id == null){
		queryMap.put("declaration_id", gezlList.get(0).get("id"));
		}else{
			queryMap.put("declaration_id", declaration_id);
		}
		//2.作家申报职位暂存
		List<Map<String,Object>> tssbList = new ArrayList<Map<String,Object>>();
		tssbList=this.mdService.queryTssbZc(queryMap);
		//3.作家学习经历表
		List<Map<String,Object>> stuList = new ArrayList<Map<String,Object>>();
		stuList=this.mdService.queryStu(queryMap);
		//4.作家工作经历表
		List<Map<String,Object>> workList = new ArrayList<Map<String,Object>>();
		workList=this.mdService.queryWork(queryMap);
		//5.作家教学经历表
		List<Map<String,Object>> steaList = new ArrayList<Map<String,Object>>();
		steaList=this.mdService.queryStea(queryMap);
		//6.作家兼职学术表
		List<Map<String,Object>> zjxsList = new ArrayList<Map<String,Object>>();
		zjxsList=this.mdService.queryZjxs(queryMap);
		//7.作家上套教材参编情况表
		List<Map<String,Object>> jcbjList = new ArrayList<Map<String,Object>>();
		jcbjList=this.mdService.queryJcbj(queryMap);
		//8.作家精品课程建设情况表
		List<Map<String,Object>> gjkcjsList = new ArrayList<Map<String,Object>>();
		gjkcjsList=this.mdService.queryGjkcjs(queryMap);
		//9.作家主编国家级规划教材情况表
		List<Map<String,Object>> gjghjcList = new ArrayList<Map<String,Object>>();
		gjghjcList = this.mdService.queryGjghjc(queryMap);
		//10.作家教材编写情况表
		List<Map<String,Object>> jcbxList = new ArrayList<Map<String,Object>>();
		jcbxList=this.mdService.queryJcbx(queryMap);
		//11.作家科研情况表
		List<Map<String,Object>> zjkyList = new ArrayList<Map<String,Object>>();
		zjkyList = this.mdService.queryZjkyqk(queryMap);
		//12.作家扩展项填报表
		List<Map<String,Object>> zjkzqkList = new ArrayList<Map<String,Object>>();
		zjkzqkList = this.mdService.queryZjkzbb(queryMap);
		
		//书籍信息
		List<Map<String,Object>> bookList = this.mdService.queryBookById(material_id);
		StringBuffer bookSelects = new StringBuffer();
		for (Map<String, Object> map : bookList) {
			bookSelects.append("<option value='"+map.get("id")+"'>"+map.get("textbook_name")+"</option>");
		}
		//机构信息
		List<Map<String,Object>> orgList = this.mdService.queryOrgById(material_id);
		StringBuffer orgSelects = new StringBuffer();
		if(orgList.size()>0){
		for (Map<String, Object> map : orgList) {
			orgSelects.append("<option value='"+map.get("org_id")+"'>"+map.get("org_name")+"</option>");
		}}
		//下拉框选择
		//职位选择
		for (Map<String, Object> map : tssbList) {
			StringBuffer bookSelect = new StringBuffer();
			for (Map<String, Object> map2 : bookList) {
				if(map.get("textbook_id").equals(map2.get("id"))){
					bookSelect.append("<option value='"+map2.get("id")+"' selected='selected'>"+map2.get("textbook_name")+"</option>");
				}else{
					bookSelect.append("<option value='"+map2.get("id")+"'>"+map2.get("textbook_name")+"</option>");
				}
			}
			map.put("bookSelect", bookSelect.toString());
		}
		//机构选择
		for (Map<String, Object> map : gezlList) {
			StringBuffer orgSelect = new StringBuffer();
			for (Map<String, Object> map2 : orgList) {
				if(map.get("org_id").equals(map2.get("org_id"))){
					orgSelect.append("<option value='"+map2.get("org_id")+"' selected='selected'>"+map2.get("org_name")+"</option>");
				}else{
					orgSelect.append("<option value='"+map2.get("org_id")+"'>"+map2.get("org_name")+"</option>");
				}
			}
			map.put("orgSelect", orgSelect.toString());
		}
		
		//填充
		mav.addObject("bookSelects", bookSelects.toString());
		mav.addObject("orgSelects", orgSelects.toString());
		mav.addObject("gezlList", gezlList.get(0));
		mav.addObject("tssbList", tssbList);
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
		mav.addObject("materialMap", queryMap);
		mav.addObject("userMap", userMap);
		return mav;
	}
	
	/**
	 * 暂存页面保存
	 * @param request
	 * @param file
	 * @param response
	 * @return
	 */
	@RequestMapping("doMaterialUpdate")
	@ResponseBody
	public String doMaterialUpdate(HttpServletRequest request,
			HttpServletResponse response){
		//公共参数
		String material_id = request.getParameter("material_id");
		String declaration_id1 = request.getParameter("declaration_id");
		String user_id = request.getParameter("user_id"); //系统用户(暂存人)
		String type = request.getParameter("type"); //类型
		String is_background = "0";
		String msg = "";
		//删除暂存内容
		Map<String,Object> glMap = new HashMap<String,Object>();
		glMap.put("declaration_id", declaration_id1);
		this.mdService.delGlxx(glMap);
		//创建时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());
		//专家信息
		Map<String,Object> perMap = new HashMap<String,Object>();
		if(type.equals("1")){ //提交
			perMap.put("is_staging", "0");
		}else{ //暂存
			perMap.put("is_staging", "1");
		}
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
			String textbook_ids[] = request.getParameterValues("textbook_id");
			String preset_positions[] = request.getParameterValues("preset_position");
			String syllabus_ids[] = request.getParameterValues("syllabus_id");
			String syllabus_names[] = request.getParameterValues("syllabus_name");
			for(int i=0;i<preset_positions.length;i++) { //遍历数组
				Map<String,Object> tsxzMap = new HashMap<String,Object>();
				String preset_position = request.getParameter(preset_positions[i].toString());
				tsxzMap.put("textbook_id", textbook_ids[i]);
				tsxzMap.put("declaration_id", declaration_id);
				tsxzMap.put("preset_position", preset_position);
				tsxzMap.put("is_on_list", "1"); //默认值
				tsxzMap.put("author_id", user_id); //暂存人Id
				tsxzMap.put("is_background", is_background); //是否为社内用户
				tsxzMap.put("syllabus_id", syllabus_ids[i]);
				tsxzMap.put("syllabus_name", syllabus_names[i]);
				tsxzMap.put("gmt_create", date);
				if(type.equals("1")){ //提交					
					this.mdService.insertTsxz(tsxzMap);
				}else{ //暂存
					this.mdService.insertTssbZc(tsxzMap);
				}
			}
			//主要学习经历
			String xx_kssj[] = request.getParameterValues("xx_kssj");
			String xx_jssj[] = request.getParameterValues("xx_jssj");
			String xx_major[] = request.getParameterValues("xx_major");
			String xx_degree[] = request.getParameterValues("xx_degree");
			String xx_note[] = request.getParameterValues("xx_note");
			String xx_school_name[] = request.getParameterValues("xx_school_name");
			
			for(int i=0;i<xx_kssj.length;i++) { //遍历数组
				if(!xx_kssj[i].equals("")){
					Map<String,Object> xxjlMap = new HashMap<String,Object>();
					xxjlMap.put("declaration_id", declaration_id);
					xxjlMap.put("major", xx_major[i]);
					xxjlMap.put("xx_school_name", xx_school_name[i]);
					xxjlMap.put("degree", xx_degree[i]);
					xxjlMap.put("note", xx_note[i]);
					xxjlMap.put("date_begin", xx_kssj[i]);
					xxjlMap.put("date_end", xx_jssj[i]);
					this.mdService.insertStu(xxjlMap);
				}
			}
			//作家工作经历
			String gz_kssj[] = request.getParameterValues("gz_kssj");
			String gz_jssj[] = request.getParameterValues("gz_jssj");
			String gz_org_name[] = request.getParameterValues("gz_org_name");
			String gz_position[] = request.getParameterValues("gz_position");
			String gz_note[] = request.getParameterValues("gz_note");
			for(int i=0;i<gz_kssj.length;i++) { //遍历数组
				if(!gz_kssj[i].equals("")){ //判断是否存在
					Map<String,Object> workjlMap = new HashMap<String,Object>();
					workjlMap.put("declaration_id", declaration_id);
					workjlMap.put("org_name", gz_org_name[i]);
					workjlMap.put("position", gz_position[i]);
					workjlMap.put("note", gz_note[i]);
					workjlMap.put("date_begin", gz_kssj[i]);
					workjlMap.put("date_end", gz_jssj[i]);
					this.mdService.insertWork(workjlMap);
				}
			}
			//教学经历表
			String jx_kssj[] = request.getParameterValues("jx_kssj");
			String jx_jssj[] = request.getParameterValues("jx_jssj");
			String jx_school_name[] = request.getParameterValues("jx_school_name");
			String jx_subject[] = request.getParameterValues("jx_subject");
			String jx_note[] = request.getParameterValues("jx_note");
			for(int i=0;i<jx_kssj.length;i++) { //遍历数组
				if(!jx_jssj[i].equals("")){ //判断是否存在
					Map<String,Object> jxjlMap = new HashMap<String,Object>();
					jxjlMap.put("declaration_id", declaration_id);
					jxjlMap.put("school_name", jx_school_name[i]);
					jxjlMap.put("subject", jx_subject[i]);
					jxjlMap.put("note", jx_note[i]);
					jxjlMap.put("date_begin", jx_kssj[i]);
					jxjlMap.put("date_end", jx_jssj[i]);
					this.mdService.insertStea(jxjlMap);
				}
			}
			//作家兼职学术
			String org_name[] = request.getParameterValues("org_name");
			String xs_rank[] = request.getParameterValues("xs_rank");
			String xs_position[] = request.getParameterValues("xs_position");
			String xs_note[] = request.getParameterValues("xs_note");
			for(int i=0;i<org_name.length;i++) { //遍历数组
				if(!org_name[i].equals("")){ //判断是否存在
					Map<String,Object> xsjzMap = new HashMap<String,Object>();
					xsjzMap.put("declaration_id", declaration_id);
					xsjzMap.put("org_name", org_name[i]);
					xsjzMap.put("rank", request.getParameter(xs_rank[i]));
					xsjzMap.put("note", xs_note[i]);
					xsjzMap.put("position", xs_position[i]);
					this.mdService.insertZjxs(xsjzMap);
				}	
			}
			//上套教材参编情况
			String jc_material_name[] = request.getParameterValues("jc_material_name");
			String jc_position[] = request.getParameterValues("jc_position");
			String jc_note[] = request.getParameterValues("jc_note");
			for(int i=0;i<jc_material_name.length;i++) { //遍历数组
				if(!jc_material_name[i].equals("")){ //判断是否存在
					Map<String,Object> JcbjMap = new HashMap<String,Object>();
					JcbjMap.put("declaration_id", declaration_id);
					JcbjMap.put("material_name", jc_material_name[i]);
					JcbjMap.put("position", request.getParameter(jc_position[i]));
					JcbjMap.put("note", jc_note[i]);
					this.mdService.insertJcbj(JcbjMap);
				}	
			}
			//精品课程建设情况
			String gj_course_name[] = request.getParameterValues("gj_course_name");
			String gj_class_hour[] = request.getParameterValues("gj_class_hour");
			String gj_note[] = request.getParameterValues("gj_note");
			String gj_type[] = request.getParameterValues("gj_type");
			for(int i=0;i<gj_type.length;i++) { //遍历数组
				if(!gj_course_name[i].equals("")){ //判断是否存在
					Map<String,Object> GjkcjsMap = new HashMap<String,Object>();
					GjkcjsMap.put("declaration_id", declaration_id);
					GjkcjsMap.put("course_name", gj_course_name[i]);
					GjkcjsMap.put("class_hour", gj_class_hour[i]);
					GjkcjsMap.put("type", gj_type[i]);
					GjkcjsMap.put("note", gj_note[i]);
					this.mdService.insertGjkcjs(GjkcjsMap);
				}	
			}	
			//主编国家级规划教材情况
			String hj_material_name[] = request.getParameterValues("hj_material_name");
			String hj_isbn[] = request.getParameterValues("hj_isbn");
			String hj_rank[] = request.getParameterValues("hj_rank");
			String hj_note[] = request.getParameterValues("hj_note");
			for(int i=0;i<hj_material_name.length;i++) { //遍历数组
				if(!hj_material_name[i].equals("")){ //判断是否存在
					Map<String,Object> GjghjcMap = new HashMap<String,Object>();
					GjghjcMap.put("declaration_id", declaration_id);
					GjghjcMap.put("hj_material_name",hj_material_name[i]);
					GjghjcMap.put("isbn",hj_isbn[i]);
					GjghjcMap.put("rank", request.getParameter(hj_rank[i]));
					GjghjcMap.put("note", hj_note[i]);
					this.mdService.insertGjghjc(GjghjcMap);
				}	
			}
			//作家教材编写情况
			String jcb_material_name[] = request.getParameterValues("jcb_material_name");
			String jcb_rank[] = request.getParameterValues("jcb_rank");
			String jcb_position[] = request.getParameterValues("jcb_position");
			String jcb_publisher[] = request.getParameterValues("jcb_publisher");
			String jcb_publish_date[] = request.getParameterValues("jcb_publish_date");
			String jcb_isbn[] = request.getParameterValues("jcb_isbn");
			String jcb_note[] = request.getParameterValues("jcb_note");
			for(int i=0;i<jcb_material_name.length;i++) { //遍历数组
				if(!jcb_material_name[i].equals("")){ //判断是否存在
					Map<String,Object> JcbxMap = new HashMap<String,Object>();
					JcbxMap.put("declaration_id", declaration_id);
					JcbxMap.put("material_name", jcb_material_name[i]);
					JcbxMap.put("rank", jcb_rank[i]);
					JcbxMap.put("position", jcb_position[i]);
					JcbxMap.put("publisher", jcb_publisher[i]);
					JcbxMap.put("publish_date", jcb_publish_date[i]);
					JcbxMap.put("isbn", jcb_isbn[i]);
					JcbxMap.put("note", jcb_note[i]);
					this.mdService.insertJcbx(JcbxMap);
				}	
			}	
			//作家科研情况
			String zjk_research_name[] = request.getParameterValues("zjk_research_name");
			String zjk_approval_unit[] = request.getParameterValues("zjk_approval_unit");
			String zjk_award[] = request.getParameterValues("zjk_award");
			String zjk_note[] = request.getParameterValues("zjk_note");
			for(int i=0;i<zjk_research_name.length;i++) { //遍历数组
				if(!zjk_research_name[i].equals("")){ //判断是否存在
					Map<String,Object> ZjkyqkMap = new HashMap<String,Object>();
					ZjkyqkMap.put("declaration_id", declaration_id);
					ZjkyqkMap.put("research_name", zjk_research_name[i]);
					ZjkyqkMap.put("approval_unit", zjk_approval_unit[i]);
					ZjkyqkMap.put("award", zjk_award[i]);
					ZjkyqkMap.put("note", zjk_note[i]);
					this.mdService.insertZjkyqk(ZjkyqkMap);
				}
			}
			msg = "OK";
		}
		return msg;
	}
	
	
	//传值测试
	@RequestMapping("doMaterialTest")
	@ResponseBody
	public String doMaterialTest(HttpServletRequest request,@RequestParam(value="file",required=false)MultipartFile file,
			HttpServletResponse response){
		//主要学习经历
		String xx_kssj[] = request.getParameterValues("xx_kssj");
		String xx_jssj[] = request.getParameterValues("xx_jssj");
		String xx_major[] = request.getParameterValues("xx_major");
		String xx_degree[] = request.getParameterValues("xx_degree");
		String xx_note[] = request.getParameterValues("xx_note");
		for(int i=0;i<xx_kssj.length;i++) { //遍历数组
			if(!xx_kssj[i].equals("")){
				Map<String,Object> xxjlMap = new HashMap<String,Object>();
				xxjlMap.put("declaration_id", "120");
				xxjlMap.put("major", xx_major[i]);
				xxjlMap.put("degree", xx_degree[i]);
				xxjlMap.put("note", xx_note[i]);
				xxjlMap.put("date_begin", xx_kssj[i]);
				xxjlMap.put("date_end", xx_jssj[i]);
			}
		}
		String msg = "OK";
		return msg;
	}
	
}
