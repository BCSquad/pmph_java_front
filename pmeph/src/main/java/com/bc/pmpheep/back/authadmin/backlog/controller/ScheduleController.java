package com.bc.pmpheep.back.authadmin.backlog.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.authadmin.backlog.service.ScheduleService;
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;
import com.sun.org.apache.bcel.internal.generic.SIPUSH;

/**
 * 
 * @author Administrator
 *待办事项控制类
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController extends BaseController{
	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.backlog.service.ScheduleServiceImpl")
	private
	ScheduleService scheduleService;
	
	@Autowired
	MessageService mssageService;
	
	//查询待办事项列表
	@RequestMapping(value="/scheduleList")
	public ModelAndView toScheduleList (HttpServletRequest  request){
		Map<String, Object> writerUser = this.getUserInfo();
		Long userId = new Long(String.valueOf(writerUser.get("id")));
		//Long userId = (long) 1383;
		String  currentPageStr = (String) request.getParameter("currentPage");
		String  pageSizeStr = request.getParameter("pageSize");
		String  time = request.getParameter("time");
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		
		//查询条件
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00.0");
        Calendar c = Calendar.getInstance();
        paraMap.put("week", null);
    	paraMap.put("month", null);
    	paraMap.put("year", null);
        if(null!=time&&!time.equals("")){
        	
        	
        	 if(time.equals("week")){
             	//过去七天
                 c.setTime(new Date());
                 c.add(Calendar.DATE, - 7);
                 Date d = c.getTime();
                 String week = format.format(d);
                 paraMap.put("week", week);
             }else if(time.equals("month")){
            	 //过去一月
             	  c.setTime(new Date());
                  c.add(Calendar.MONTH, - 1);
                  Date d = c.getTime();
                  String month = format.format(d);
                  paraMap.put("month", month);
             }else if(time.equals("year")){
            	 //过去一年
             	 c.setTime(new Date());
                  c.add(Calendar.YEAR, - 1);
                  Date d = c.getTime();
                  String year = format.format(d);
                  paraMap.put("year", year);
             }
        }
        
		int currentPage = 0;
		int pageSize = 5;
		
		if(null!=currentPageStr&&!currentPageStr.equals("")){
			 currentPage = Integer.parseInt(currentPageStr);
		}
		if(null!=pageSizeStr&&!pageSizeStr.equals("")){
			 pageSize = Integer.parseInt(pageSizeStr);
		}
		
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<>(currentPage,pageSize);
		paraMap.put("userId", userId);
		paraMap.put("endPage", pageSize);
		
		pageParameter.setParameter(paraMap);
		//代办事项列表
		PageResult<Map<String,Object>> pageResult = scheduleService.selectScheduleList(pageParameter);
		for(Map<String, Object> map:pageResult.getRows()){
         	if("DEFAULT".equals(map.get("avatar").toString())){
 				map.put("avatar", "statics/pictures/head.png");
 			}else{
 				map.put("avatar", "file/download/"+map.get("avatar")+".action");
 			}
         }
		ModelAndView mv = new ModelAndView();
		//机构用户基本信息
		Map<String,Object> map = scheduleService.selectOrgUser(userId);
		if(null!=map&&map.size()>0){
			/*boolean license =  (boolean) map.get("is_proxy_upload");
			String progress = (String) map.get("progress");
			if(license==true&&progress==true){
				map.put("license","true");
			}else{
				map.put("license","false");
			}*/
			if("DEFAULT".equals(map.get("avatar").toString())||"/static/default_image.png".equals(map.get("avatar").toString())){
				map.put("avatar", "statics/pictures/head.png");
			}else{
				map.put("avatar", "file/download/"+map.get("avatar").toString().replace("/image/","/")+".action");
			}
			map.put("time", time);
			map.put("userId", userId);
			map.put("pageResult", pageResult);
			mv.addObject("map",map);
		}else{
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("pageResult", pageResult);
			map1.put("license", "no");
			mv.addObject("map",map1);

		}
		
		mv.setViewName("authadmin/backlog/schedule");
		return mv;
		
	}
	
	//查询办事记录列表
	@RequestMapping(value="/eventRecord")
	public ModelAndView toEventRecord(HttpServletRequest  request) throws ParseException{
		Map<String, Object> writerUser = this.getUserInfo();
		Long userId = new Long(String.valueOf(writerUser.get("id")));
		//Long userId = (long) 1459;
		String  currentPageStr = (String) request.getParameter("currentPage");
		//String  time =  request.getParameter("time");
		String  pageSizeStr = request.getParameter("pageSize");
		/*if(null!=time&&!time.equals("")){
			String year = time.substring(0,4);
			String month = time.substring(5,7);
			String day = time.substring(8);
			
			time = year+"-"+month+"-"+day;
			//查询条件时间
			if(null!=time&&!time.equals("")){
			time="%"+time+"%";
			paraMap.put("time", time);
		}
		}*/
		
		int currentPage = 0;
		int pageSize = 5;
		if(null!=currentPageStr&&!currentPageStr.equals("")){
			 currentPage = Integer.parseInt(currentPageStr);
		}
		if(null!=pageSizeStr&&!pageSizeStr.equals("")){
			 pageSize = Integer.parseInt(pageSizeStr);
		}
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(currentPage==0){
			paraMap.put("startPage", currentPage);
		}else{
			paraMap.put("startPage", currentPage*pageSize-pageSize);
		}
		
		paraMap.put("userId", userId);
		
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<>(currentPage,pageSize);
		pageParameter.setParameter(paraMap);
		//List<String> list = scheduleService.selectUserMessageList(paraMap);
		//int pageCount = scheduleService.selectUserMessageCount(paraMap);
		//机构用户基本信息
		Map<String,Object> map = scheduleService.selectOrgUser(userId);
		//已办事项的名称和时间
		PageResult<Map<String,Object>> pageResult = scheduleService.selectDoneSchedule(pageParameter);

		ModelAndView mv = new ModelAndView();
		if(null!=map&&map.size()>0){
			map.put("pageResult", pageResult);
			map.put("userId", userId);
			
			if("DEFAULT".equals(map.get("avatar").toString())||"/static/default_image.png".equals(map.get("avatar").toString())){
				map.put("avatar", "statics/pictures/head.png");
			}else{
				map.put("avatar", "file/download/"+map.get("avatar").toString().replace("/image/","/")+".action");
			}
			map.put("userId", userId);
			map.put("pageResult", pageResult);
			
			mv.addObject("map",map);

		}else{
			Map<String,Object> map1 =  new HashMap<String,Object>();
			map1.put("pageResult", pageResult);
			map1.put("license", "no");
			mv.addObject("map",map1);
		}
		

		mv.setViewName("authadmin/backlog/eventRecord");
		return mv;
		
	}
	
}
