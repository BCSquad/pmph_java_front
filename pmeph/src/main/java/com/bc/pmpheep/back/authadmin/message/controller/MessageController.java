package com.bc.pmpheep.back.authadmin.message.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.bc.pmpheep.general.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.authadmin.message.service.OrgMessageService;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;


@Controller
@RequestMapping("/message")
public class MessageController extends BaseController{
	
	@Autowired
	MessageService mssageService;
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.message.service.MessageServiceImpl")
	OrgMessageService OrgMessageService;
	
	//查询申请列表
	@RequestMapping(value="/applyMessageList")
	public ModelAndView toScheduleList(HttpServletRequest  request){
		String condition=request.getParameter("condition");
		String para=request.getParameter("addPara");
		int addPara=0;
		if(null!=para&&!para.equals("")){
			addPara = Integer.parseInt(para);
		}else{
			addPara=3;
		}
		Map<String,Object> paraMap = new HashMap<String,Object>();
		ModelAndView mv = new ModelAndView();
		Long userId = (long) 24967;
		paraMap.put("condition",condition);
		paraMap.put("userId",userId);
		paraMap.put("addPara",addPara);
		
		List<Map<String,Object>> list = OrgMessageService.selectApplyMessage(paraMap);
		mv.addObject("list",list);
		mv.addObject("listSize",list.size());
		mv.addObject("condition",condition);
		mv.addObject("addPara",addPara);
		
		mv.setViewName("authadmin/message/applyMessage");
		return mv;
	}
	
	//查询更多申请列表
	@RequestMapping(value="/loadMoreApply")
	@ResponseBody
	public List<Map<String,Object>> loadMoreApply(HttpServletRequest request){
		String condition=request.getParameter("condition");
		String para=request.getParameter("startPara");
		int startPara=0;
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(null!=para&&!para.equals("")){
			startPara = Integer.parseInt(para);
			
			paraMap.put("startPara",startPara);
			
		}else{
			startPara=8;
			paraMap.put("startPara",startPara);
		}
		
		Long userId = (long) 1609;
		paraMap.put("condition",condition);
		paraMap.put("userId",userId);
		
		List<Map<String,Object>> list = OrgMessageService.selectApplyMessage(paraMap);
		
		return list;
	}
	
	
	
	//更新申请信息状态
	@RequestMapping(value="/updateApplyMessage")
	public ModelAndView updateApplyMessage(HttpServletRequest  request){
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		ModelAndView mv = new ModelAndView();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(null!=id&&!id.equals("")){
			long applyId = Long.valueOf(id).longValue();
			paraMap.put("flag", flag);
			paraMap.put("applyId", applyId);
			OrgMessageService.updateApplyMessage(paraMap);
		}
		Long userId = (long) 24967;
		paraMap.put("userId", userId);
		List<Map<String,Object>> list = OrgMessageService.selectApplyMessage(paraMap);
		mv.addObject("list",list);
		mv.setViewName("authadmin/message/applyMessage");
		return mv;
	}
	
	//查询通知列表
	@RequestMapping(value="/noticeMessageList")
	public ModelAndView toNoticeMessageList(HttpServletRequest request){
		String condition=request.getParameter("condition");
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		ModelAndView mv = new ModelAndView();
		Long userId = (long) 1609;
		paraMap.put("condition",condition);
		paraMap.put("userId",userId);
		paraMap.put("startPara",0);
		List<Map<String,Object>> list = OrgMessageService.selectNoticeMessage(paraMap);
		
		mv.addObject("listSize",list.size());
		mv.addObject("list",list);
		mv.addObject("condition",condition);
		
		mv.setViewName("authadmin/message/noticeMessage");
		return mv;
	}
	//查询更多通知列表
	@RequestMapping(value="/loadMore")
	@ResponseBody
	public List<Map<String,Object>> loadMore(HttpServletRequest request){
		String condition=request.getParameter("condition");
		String para=request.getParameter("startPara");
		int startPara=0;
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(null!=para&&!para.equals("")){
			startPara = Integer.parseInt(para);
			
			paraMap.put("startPara",startPara);
			
		}else{
			startPara=8;
			paraMap.put("startPara",startPara);
		}
		
		Long userId = (long) 1609;
		paraMap.put("condition",condition);
		paraMap.put("userId",userId);
		
		List<Map<String,Object>> list = OrgMessageService.selectNoticeMessage(paraMap);
		
		return list;
	}
	
	//更新申请信息状态
	@RequestMapping(value="/deleteNoticeMessage")
	@ResponseBody
	public String deleteNoticeMessage(HttpServletRequest  request){
		String id=request.getParameter("nid");
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(null!=id&&!id.equals("")){
			long noticeId = Long.valueOf(id).longValue();
			paraMap.put("noticeId", noticeId);
			OrgMessageService.deleteNoticeMessage(paraMap);
		}
		Long userId = (long) 1609;
		paraMap.put("userId", userId);
		//List<Map<String,Object>> list = OrgMessageService.selectNoticeMessage(paraMap);
		//mv.addObject("list",list);
		//mv.setViewName("authadmin/message/noticeMessage");
		String code = "OK";
		return code;
	}
	
	//查询公告详情
	@RequestMapping(value="/noticeMessageDetail")
	public ModelAndView toNoticeMessageDetail(HttpServletRequest request){
		String messageId=request.getParameter("id");
		ModelAndView mv = new ModelAndView();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("messageId", messageId);
		Map<String,Object> mapTitle = OrgMessageService.queryNoticeMessageDetail(paraMap);
		if(mapTitle.size()>0){
			paraMap.put("attachmentId", mapTitle.get("attachmentId"));
			paraMap.put("materialId", mapTitle.get("materialId"));
			List<Map<String,Object>> listAttachment = OrgMessageService.queryNoticeMessageDetailAttachment(paraMap);
			List<Map<String,Object>> listContact = OrgMessageService.queryNoticeMessageDetailContact(paraMap);
			
			mv.addObject("map",mapTitle);
			mv.addObject("listAttachment",listAttachment);
			mv.addObject("listContact",listContact);
			
		}
		
		
		Message message = mssageService.get(messageId);
		
		mv.addObject("message",message);
		
		
		mv.setViewName("authadmin/message/noticeMessageDetail");
		return mv;
	}
}
