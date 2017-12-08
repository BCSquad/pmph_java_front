/**
 * create by xcy on 2017-12-7
 */
package com.bc.pmpheep.back.authadmin.message.controller;

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

import com.bc.pmpheep.back.authadmin.message.service.AllMessageServiceImpl;
import com.bc.pmpheep.general.controller.BaseController;
import com.sun.tools.internal.ws.processor.model.Request;

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

	/**
	 * 
	* @Title: init 
	* @Description: 页面——机构用户（全部消息）
	* @return ModelAndView    返回类型 
	* @throws
	 */
	
	@RequestMapping("/init")
	public ModelAndView init(){
		Map<String,Object> param = this.getUserInfo();
		param.put("startPara",0);
		List<Map<String,Object>> list1 = allMessageServiceImpl.getAllMessageInit(param);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("list", list1);
		resultMap.put("listSize", list1.size());
		return new ModelAndView("/authadmin/message/organizationAllMessage",resultMap);
		
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
	public Map<String,Object> loadMore(HttpServletRequest request){
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
		List<Map<String,Object>> list1 = allMessageServiceImpl.getAllMessageInit(param);
		resultMap.put("list", list1);
		resultMap.put("listSize", list1.size());
		return resultMap;
	}
	
	
}