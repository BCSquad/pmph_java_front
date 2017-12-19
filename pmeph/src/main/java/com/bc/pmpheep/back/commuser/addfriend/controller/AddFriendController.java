package com.bc.pmpheep.back.commuser.addfriend.controller;

import java.math.BigInteger;
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



import com.bc.pmpheep.back.commuser.addfriend.service.AddFriendService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.template.service.TemplateService;
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.general.controller.BaseController;
/**
 * 
 * @author liudi
 *	添加好友
 */
@Controller
@RequestMapping(value = "/addFriend")
public class AddFriendController extends BaseController {
	@Autowired
    @Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
    private TemplateService templateService;
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.addfriend.service.AddFriendService")
	private AddFriendService addFriendService;
	
	/**
	 * 跳转至页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toPage",method = RequestMethod.GET)
	public ModelAndView approvelList(HttpServletRequest request){
		Map<String, Object> user = getUserInfo();
		ModelAndView mv = new  ModelAndView();
		
		mv.setViewName("commuser/addfriend/addfriend");
		
		return mv;
	}
	
	/**
	 * 翻页，模糊查询，状态查询，刷新列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addFriendList",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> approvelninelist(HttpServletRequest request){
		
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String queryName = request.getParameter("queryName");
		String contextpath = request.getParameter("contextpath");
		Map<String, Object> user = getUserInfo();
		BigInteger my_uid = (BigInteger) user.get("id");
		Map<String,Object> queryConMap = new HashMap<String,Object>();
		queryConMap.put("queryName", queryName);
		queryConMap.put("uid", my_uid);
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<Map<String,Object>>(pageNum,15);
		pageParameter.setParameter(queryConMap);
		//List_map中 map可能有relations键 对应如"0-24966-24981,1-24966-24981"的值，代表与此人所有好友申请，状态-申请人-被申请人
		List<Map<String, Object>> List_map = addFriendService.addFriendListQuery(pageParameter);
		int totoal_count = addFriendService.addFriendListQueryCount(pageParameter);
		Map<String, Object> vmMap = new HashMap<String, Object>();
		
		
		
		
		vmMap.put("my_uid", my_uid);
		vmMap.put("List_map", List_map);
		vmMap.put("contextpath", contextpath);
		String html ="";
		String vm = "/commuser/addfriend/addfriend.vm";
		html = templateService.mergeTemplateIntoString(vm, vmMap);
		Map<String,Object> data_map = new HashMap<String,Object>();
		data_map.put("html", html);
		data_map.put("totoal_count", totoal_count);
		
		return data_map;
	}
	
	/**
	 * 添加一条好友申请 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addFriendfun",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addFriendfun(HttpServletRequest request){
		Map<String,Object> result_map =new HashMap<String,Object>();
		String target_id = request.getParameter("uid");
		String status = request.getParameter("status");
		Map<String, Object> user = getUserInfo();
		String request_id = user.get("id").toString();
		
		result_map = addFriendService.addFriendRequest(target_id,request_id,status);
		
		
		return result_map;
	}
	
}
