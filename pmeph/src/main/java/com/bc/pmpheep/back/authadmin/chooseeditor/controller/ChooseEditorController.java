package com.bc.pmpheep.back.authadmin.chooseeditor.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.authadmin.chooseeditor.service.ChooseEditorService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.template.service.TemplateService;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * 第一主编选择编委 控制层
 * @author liudi
 *
 */
@Controller
@RequestMapping("chooseEditor")
public class ChooseEditorController extends BaseController {
	
	@Autowired
    @Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
    private TemplateService templateService;
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.chooseeditor.service.ChooseEditorServiceImpl")
	ChooseEditorService chooseEditorService;
	
	
	/**
	 * 跳转到第一主编选择编委界面
	 */
	@RequestMapping("toPage")
	public ModelAndView toPage(@RequestParam(value="textBookId",required=true)String textBookId, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		/*String textBookId = request.getParameter("textBookId");*/
		//textBookId = "3141";
		
		Map<String, Object> user = getUserInfo();
		BigInteger logUserId=(BigInteger) user.get("id");
		String logUserName=(String) user.get("realname");
		
		Map<String, Object> textBook = chooseEditorService.queryTextBookById(textBookId);
		List<Map<String, Object>> OrgList = chooseEditorService.getOrgList();
		String textBookName = (String) textBook.get("textbook_name");
		Boolean is_locked = (Boolean) textBook.get("is_locked");
		Boolean is_force_end = (Boolean) textBook.get("is_force_end");
		Boolean is_digital_editor_optional = (Boolean) textBook.get("is_digital_editor_optional");
		/*String tag ="editor";
		String selectedIds = getTempSelectedIds(textBookId, logUserId,tag);
		tag ="numEditor";
		String selectedNumIds = getTempSelectedIds(textBookId, logUserId,tag);*/
		
		Map<String, Object> selectMap = getTempSelectedIds(textBookId, logUserId);
		
		Boolean isFirstEditorLogIn = chooseEditorService.isFirstEditorLogIn(logUserId,textBookId);
		
		mv.addObject("isFirstEditorLogIn", isFirstEditorLogIn);
		mv.addObject("selectedIds", selectMap.get("selectedIds"));
		mv.addObject("selectedNumIds", selectMap.get("selectedNumIds"));
		mv.addObject("logUserName",logUserName);
		mv.addObject("planning_editor",textBook.get("realname")!=null?textBook.get("realname").toString():"待分配");
		mv.addObject("textBookName",textBookName);
		mv.addObject("OrgList",OrgList);
		mv.addObject("textBookId",textBookId);
		mv.addObject("is_locked",is_locked);
		mv.addObject("is_force_end",is_force_end);
		mv.addObject("is_digital_editor_optional",is_digital_editor_optional);
		mv.setViewName("authadmin/chooseeditor/chooseeditor");
		return mv;
	}

	/**
	 * 通过输入 查询当前职务申报暂存表 中已选为编委的申报 所对应的正式申报表的id数组字符串
	 * @param textBookId
	 * @param logUserId
	 * @return
	 */
	private Map<String,Object> getTempSelectedIds(String textBookId, BigInteger logUserId) {
		//查询条件封装入pageParameter的parameter
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("textBookId", textBookId);
		paraMap.put("logUserId", logUserId);
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<Map<String,Object>>(0,10);
		pageParameter.setParameter(paraMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String selectedIds = "";
		String selectedNumIds = "";

		//查询所有页的编委
		List<Map<String, Object>> total_List_map = chooseEditorService.queryEditorToBeCount(pageParameter);
		
		//将暂存表中已选中的初始化入selectedIds
		
		for (Map<String, Object> m : total_List_map) {
			if ("1".equals(""+m.get("chosen_position"))||"9".equals(""+m.get("chosen_position"))) {
				selectedIds += ("'"+ m.get("dec_position_id") + "',");
			}
			if (Integer.parseInt(m.get("chosen_position")==null?"0":m.get("chosen_position").toString())>=8) {
				selectedNumIds += ("'"+ m.get("dec_position_id") + "',");
			}
			
		}
		if (selectedIds.length()>0) {
			selectedIds = selectedIds.substring(0,selectedIds.length()-1);
		}
		if (selectedNumIds.length()>0) {
			selectedNumIds = selectedNumIds.substring(0,selectedNumIds.length()-1);
		}
		selectedIds = "["+selectedIds+"]";
		selectedNumIds = "["+selectedNumIds+"]";
	
		resultMap.put("selectedIds", selectedIds);
		resultMap.put("selectedNumIds", selectedNumIds);
		
		/*if(tag.equals("editor")){
			//查询所有页的编委
			List<Map<String, Object>> total_List_map = chooseEditorService.queryEditorToBeCount(pageParameter);
			
			//将暂存表中已选中的初始化入selectedIds
			
			for (Map<String, Object> m : total_List_map) {
				if ("1".equals(""+m.get("chosen_position"))||"9".equals(""+m.get("chosen_position"))) {
					selectedIds += ("'"+ m.get("dec_position_id") + "',");
				}
				
			}
			if (selectedIds.length()>0) {
				selectedIds = selectedIds.substring(0,selectedIds.length()-1);
			}
			selectedIds = "["+selectedIds+"]";
		}else if(tag.equals("numEditor")){
			//查询所有页的数字编委
			List<Map<String, Object>> total_List_map = chooseEditorService.queryNumEditorToBeCount(pageParameter);
			
			//将暂存表中已选中的初始化入selectedIds
			for (Map<String, Object> m : total_List_map) {
				if ("8".equals(""+m.get("chosen_position"))||"9".equals(""+m.get("chosen_position"))) {
				if(Integer.parseInt("0"+m.get("chosen_position"))>=8){
					selectedIds += ("'"+ m.get("dec_position_id") + "',");
				}
			}
			if (selectedIds.length()>0) {
				selectedIds = selectedIds.substring(0,selectedIds.length()-1);
			}
			selectedIds = "["+selectedIds+"]";
		}*/
		
		return resultMap;
	}
	
	/**
	 * 第一主编选择编委列表查询更新
	 */
	@RequestMapping(value = "/queryUserList",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryUserList(HttpServletRequest request){
		Map<String, Object> user = getUserInfo();
		BigInteger logUserId=(BigInteger) user.get("id");
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String queryName = request.getParameter("queryName");
		String queryOrg = request.getParameter("queryOrg");
		String textBookId = request.getParameter("textBookId");
		String isFirstEditorLogIn = request.getParameter("isFirstEditorLogIn");
		String is_digital_editor_optional = request.getParameter("is_digital_editor_optional");
		
		
		//查询条件封装入pageParameter的parameter
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("textBookId", textBookId);
		paraMap.put("queryName", queryName);
		paraMap.put("queryOrg", queryOrg);
		paraMap.put("logUserId", logUserId);
		paraMap.put("isFirstEditorLogIn", isFirstEditorLogIn);
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<Map<String,Object>>(pageNum,pageSize);
		pageParameter.setParameter(paraMap);
		
		//查询本页
		List<Map<String, Object>> List_map = chooseEditorService.queryEditorToBeList(pageParameter);
		//查询所有页
		List<Map<String, Object>> total_List_map = chooseEditorService.queryEditorToBeCount(pageParameter);
		//所有页总数据条数
		Integer total_count = total_List_map.size();
		//总页数
		int page_count = (int) Math.ceil(1.0*total_count/pageParameter.getPageSize());

		Map<String, Object> vm_map = new HashMap<String, Object>();
		vm_map.put("List_map", List_map);
		vm_map.put("startNum", pageParameter.getStart()+1);
		vm_map.put("isFirstEditorLogIn", isFirstEditorLogIn);
		vm_map.put("is_digital_editor_optional", is_digital_editor_optional);
		
		String contextpath = request.getContextPath()+"/";
		vm_map.put("contextpath", contextpath);
		String html ="";
		String vm = "/authadmin/chooseeditor/chooseeditor.vm";
		html = templateService.mergeTemplateIntoString(vm, vm_map);
		
		Map<String,Object> data_map = new HashMap<String,Object>();
		data_map.put("html", html);
		data_map.put("totoal_count", page_count);
		
		
		return data_map;
	}
	

	/**
	 * 暂存
	 */
	@RequestMapping(value="tempSave",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> tempSave(HttpServletRequest request){
 		String selectedIds = request.getParameter("selectedIds");
		String selectedNumIds = request.getParameter("selectedNumIds");
		String textBookId = request.getParameter("textBookId");
		Map<String, Object> user = getUserInfo();
		BigInteger logUserId=(BigInteger) user.get("id");
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("logUserId", logUserId);
		paraMap.put("textBookId", textBookId);
		paraMap.put("selectedIds", selectedIds);
		paraMap.put("selectedNumIds", selectedNumIds);
		
		if (true) {//作家用户 判断条件暂不明 待修改
			paraMap.put("is_background", 0);
		}else{
			paraMap.put("is_background", 1);
		}
		Map<String,Object> resultMap= new HashMap<String,Object>();
		
		resultMap = chooseEditorService.tempSave(paraMap);
		/*String tag = "editor";
		String result_selectedIds = getTempSelectedIds(textBookId, logUserId,tag);
		tag="numEditor";
		String result_selectedIdsNum = getTempSelectedIds(textBookId, logUserId,tag);*/
		Map<String, Object> selectMap = getTempSelectedIds(textBookId, logUserId);
		/*resultMap.put("selectedIds", result_selectedIds);
		resultMap.put("selectedNumIds", result_selectedIdsNum);*/
		resultMap.putAll(selectMap);
		
		return resultMap;
	}
	
	/**
	 * 提交
	 */
	@RequestMapping(value="selectSubmit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> selectSubmit(HttpServletRequest request){
		String selectedIds = request.getParameter("selectedIds");
		String selectedNumIds = request.getParameter("selectedNumIds");
		String textBookId = request.getParameter("textBookId");
		Map<String, Object> user = getUserInfo();
		BigInteger logUserId=(BigInteger) user.get("id");
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("logUserId", logUserId);
		paraMap.put("textBookId", textBookId);
		paraMap.put("selectedIds", selectedIds);
		paraMap.put("selectedNumIds", selectedNumIds);
		if (true) {//作家用户 判断条件暂不明 待修改
			paraMap.put("is_background", 0);
		}else{
			paraMap.put("is_background", 1);
		}
		Map<String,Object> resultMap= new HashMap<String,Object>();
		
		resultMap = chooseEditorService.editorSelectRubmit(paraMap);

		return resultMap;
	}
	
}
