/**
 * create by xcy on 2017-12-7
 */
package com.bc.pmpheep.back.authadmin.message.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.authadmin.message.service.AllMessageServiceImpl;
import com.bc.pmpheep.general.controller.BaseController;
import com.mongodb.util.Hash;


/** 
 * @ClassName: AllMessageController 
 * @Description: 机构用户（全部消息）
 * @author xcy
 * @date 2017-12-7 上午11:23:06  
 */
@RequestMapping("/AllMessage")
@Controller
public class AllMessageController extends BaseController {

	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.message.service.AllMessageServiceImpl")
	AllMessageServiceImpl allMessageServiceImpl;

	@Autowired
	private MessageService messageService;

	/**
	 * 
	* @Title: init 
	* @Description: 页面——机构用户（全部消息）
	* @return ModelAndView    返回类型 
	* @throws
	 */
	
	@RequestMapping("/init")
	public ModelAndView init(@RequestParam(value="tag",defaultValue="receive")String tag){
		Map<String,Object> param = this.getUserInfo();
		param.put("startPara",0);
		List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
		if ("receive".equals(tag)) {
			list1 = allMessageServiceImpl.getAllMessageInit(param);
		}else if ("send".equals(tag)) {
			list1 = allMessageServiceImpl.getSendMessage(param);
		}else{
			list1 = allMessageServiceImpl.getAllMessageInit(param);
		}
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("list", list1);
		resultMap.put("listSize", list1.size());
		
		if ("receive".equals(tag)) {
			return new ModelAndView("/authadmin/message/organizationAllMessage",resultMap);
		}else if ("send".equals(tag)) {
			return new ModelAndView("/authadmin/message/organizationSentMessage",resultMap);
		}else{
			return new ModelAndView("/authadmin/message/organizationAllMessage",resultMap);
		}

	}
	
	/**
	 * 
	* @Title: loadMore 
	* @Description: 加载更多数据
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	@RequestMapping(value="/loadMore",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> loadMore(HttpServletRequest request,@RequestParam(value="tag",defaultValue="receive")String tag){
		Map<String,Object> param = this.getUserInfo();
		String para=request.getParameter("startPara");
		int startPara=0;
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(null!=para&&!para.equals("")){
			startPara = Integer.parseInt(para);
			param.put("startPara",startPara);
		}else{
			startPara=8;
			param.put("startPara",startPara);
		}
		List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
		if ("receive".equals(tag)) {
			list1 =	allMessageServiceImpl.getAllMessageInit(param);
		}else if ("send".equals(tag)) {
			list1 =	allMessageServiceImpl.getSendMessage(param);
		}else{
			list1 =	allMessageServiceImpl.getAllMessageInit(param);
		}

		resultMap.put("list", list1);
		resultMap.put("listSize", list1.size());
		return resultMap;
	}

	//根据Mid查询系统消息详情
	/*@RequestMapping("msg")
	@ResponseBody
	public Map<String,Object> msg(HttpServletRequest request){
		Map<String,Object> map=new HashMap<>();
		Map<String, Object> user = getUserInfo();
		Message massage=messageService.get(request.getParameter("mid"));
		int count=allMessageServiceImpl.updateIsRead(request.getParameter("mid"),user.get("id").toString());
		String isread="no";
		if(count>0){
			isread="yes";
		}
		String regEx_html="<[^>]+>";
		Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
		Matcher m_html=p_html.matcher(massage.getContent());
		String msg=m_html.replaceAll("");
		map.put("msg",msg);
		map.put("isread", isread);
		return map;
	};*/
	/**
	 * 删除消息
	 * @param req
	 * @return
	 */
	@RequestMapping("delmsg")
	@ResponseBody
	public Map<String,Object> delmsg(HttpServletRequest req){
		 Map<String, Object> user = getUserInfo();
		 int count=allMessageServiceImpl.deletemsg(req.getParameter("mid"));
		 String isdel="no";
		 if(count==1){
			 isdel="yes";
		 }
		 Map<String,Object> map=new HashMap<String, Object>();
		 map.put("isdel", isdel);
		return map;
	}
	
}
