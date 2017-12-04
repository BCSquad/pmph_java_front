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
public class ScheduleController {
	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.backlog.service.ScheduleServiceImpl")
	private
	ScheduleService scheduleService;
	
	@Autowired
	MessageService mssageService;
	
	//查询待办事项列表
	@RequestMapping(value="/scheduleList")
	public ModelAndView toScheduleList(HttpServletRequest  request){
		Long userId = (long) 1383;
		String  currentPageStr = (String) request.getParameter("currentPage");
		String  pageSizeStr = request.getParameter("pageSize");
		String  time = request.getParameter("time");
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		
		//查询条件
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00.0");
        Calendar c = Calendar.getInstance();
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
             }else if(time.equals("year")){
            	  paraMap.put("all", "all");
             }
        } 
       
        
		int currentPage = 0;
		int pageSize = 2;
		
		if(null!=currentPageStr&&!currentPageStr.equals("")){
			 currentPage = Integer.parseInt(currentPageStr);
		}
		if(null!=pageSizeStr&&!pageSizeStr.equals("")){
			 pageSize = Integer.parseInt(pageSizeStr);
		}
		
		if(currentPage==0){
			paraMap.put("startPage", currentPage);
		}else{
			paraMap.put("startPage", currentPage*pageSize-pageSize);
		}
		
		paraMap.put("userId", userId);
		paraMap.put("endPage", pageSize);
		
		//代办事项列表
		List<Map<String,Object>> list = scheduleService.selectScheduleList(paraMap);
		int pageCount = scheduleService.selectScheduleCount(paraMap);
		//机构用户基本信息
		Map<String,Object> map = scheduleService.selectOrgUser(userId);
		boolean license =  (boolean) map.get("is_proxy_upload");
		boolean progress = map.get("progress")=="1"?true:false;
		if(license==true&&progress==true){
			map.put("license",true);
		}else{
			map.put("license",false);
		}
		
		//总页数
		int totalPage  = pageCount/pageSize;
		if(pageCount%pageSize>0){
			totalPage+=1;
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("listMap",list);
		mv.addObject("map",map);
		mv.addObject("listSize",list.size());
		mv.addObject("totalPage",totalPage);
		mv.addObject("currentPage",currentPage);
		mv.addObject("pageSize",pageSize);
		mv.addObject("time",time);
		mv.setViewName("authadmin/backlog/schedule");
		return mv;
		
	}
	
	
	//查询办事记录列表
	@RequestMapping(value="/eventRecord")
	public ModelAndView toEventRecord(HttpServletRequest  request) throws ParseException{
		Long userId = (long) 1270;
		String  currentPageStr = (String) request.getParameter("currentPage");
		String  time =  request.getParameter("time");
		String  pageSizeStr = request.getParameter("pageSize");
		if(null!=time&&!time.equals("")){
			String year = time.substring(0,4);
			String month = time.substring(5,7);
			String day = time.substring(8);
			
			time = year+"-"+month+"-"+day;
		}
		
		int currentPage = 0;
		int pageSize = 2;
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
		
		//查询条件时间
		if(null!=time&&!time.equals("")){
			time="%"+time+"%";
			paraMap.put("time", time);
		}
		paraMap.put("userId", userId);
		
		paraMap.put("endPage", pageSize);
		
		
		//List<String> list = scheduleService.selectUserMessageList(paraMap);
		int pageCount = scheduleService.selectUserMessageCount(paraMap);
		//机构用户基本信息
		Map<String,Object> map = scheduleService.selectOrgUser(userId);
		//已办事项的名称和时间
		List<Map<String,Object>> listNameAndTime = scheduleService.selectUserMessageNameAndTime(paraMap);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> NT:listNameAndTime){
			//已办事项内容
			Message message = mssageService.get((String)NT.get("msg_id"));
			if(null!=message&&!message.equals("")){
				NT.put("content",message.getContent());
			}
			list.add(NT);
		}
		
		//总页数
		int totalPage  = pageCount/pageSize;
		if(pageCount%pageSize>0){
			totalPage+=1;
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("map",map);
		mv.addObject("listSize",listNameAndTime.size());
		mv.addObject("list",list);
		mv.addObject("totalPage",totalPage);
		mv.addObject("currentPage",currentPage);
		mv.addObject("pageSize",pageSize);
		
		mv.setViewName("authadmin/backlog/eventRecord");
		return mv;
		
	}
	
}
