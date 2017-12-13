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
	public ModelAndView toPage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String textBookId = request.getParameter("textBookId");
		textBookId = "21524";
		
		Map<String, Object> user = getUserInfo();
		BigInteger logUserId=(BigInteger) user.get("id");
		String logUserName=(String) user.get("realname");
		
		Map<String, Object> textBook = chooseEditorService.queryTextBookById(textBookId);
		List<Map<String, Object>> OrgList = chooseEditorService.getOrgList();
		String textBookName = (String) textBook.get("textbook_name");
		Boolean is_list_selected = (Boolean) textBook.get("is_list_selected");
		String selectedIds = getTempSelectedIds(textBookId, logUserId);
		
		mv.addObject("selectedIds", selectedIds);
		mv.addObject("logUserName",logUserName);
		mv.addObject("textBookName",textBookName);
		mv.addObject("OrgList",OrgList);
		mv.addObject("textBookId",textBookId);
		mv.addObject("is_list_selected",is_list_selected);
		mv.setViewName("authadmin/chooseeditor/chooseeditor");
		return mv;
	}

	/**
	 * 通过输入 查询当前职务申报暂存表 中已选为编委的申报 所对应的正式申报表的id数组字符串
	 * @param textBookId
	 * @param logUserId
	 * @return
	 */
	private String getTempSelectedIds(String textBookId, BigInteger logUserId) {
		//查询条件封装入pageParameter的parameter
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("textBookId", textBookId);
		paraMap.put("logUserId", logUserId);
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<Map<String,Object>>(0,10);
		pageParameter.setParameter(paraMap);
		
		//查询所有页
		List<Map<String, Object>> total_List_map = chooseEditorService.queryEditorToBeCount(pageParameter);
		
		//将暂存表中已选中的初始化入selectedIds
		String selectedIds = "";
		for (Map<String, Object> m : total_List_map) {
			if ("3".equals(""+m.get("chosen_position"))) {
				selectedIds += ("'"+ m.get("dec_position_id") + "',");
			}
		}
		if (selectedIds.length()>0) {
			selectedIds = selectedIds.substring(0,selectedIds.length()-1);
		}
		selectedIds = "["+selectedIds+"]";
		return selectedIds;
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
		
		//查询条件封装入pageParameter的parameter
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("textBookId", textBookId);
		paraMap.put("queryName", queryName);
		paraMap.put("queryOrg", queryOrg);
		paraMap.put("logUserId", logUserId);
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
		//vm_map.put("contextpath", contextpath);
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
		String textBookId = request.getParameter("textBookId");
		Map<String, Object> user = getUserInfo();
		BigInteger logUserId=(BigInteger) user.get("id");
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("logUserId", logUserId);
		paraMap.put("textBookId", textBookId);
		paraMap.put("selectedIds", selectedIds);
		if (true) {//作家用户 判断条件暂不明 待修改
			paraMap.put("is_background", 0);
		}else{
			paraMap.put("is_background", 1);
		}
		Map<String,Object> resultMap= new HashMap<String,Object>();
		
		resultMap = chooseEditorService.tempSave(paraMap);
		
		String result_selectedIds = getTempSelectedIds(textBookId, logUserId);
		resultMap.put("selectedIds", result_selectedIds);
		
		return resultMap;
	}
	
	/**
	 * 提交
	 */
	@RequestMapping(value="selectSubmit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> selectSubmit(HttpServletRequest request){
		String selectedIds = request.getParameter("selectedIds");
		String textBookId = request.getParameter("textBookId");
		Map<String, Object> user = getUserInfo();
		BigInteger logUserId=(BigInteger) user.get("id");
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("logUserId", logUserId);
		paraMap.put("textBookId", textBookId);
		paraMap.put("selectedIds", selectedIds);
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