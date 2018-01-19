package com.bc.pmpheep.back.commuser.mymessage.controller;

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

import com.bc.pmpheep.back.commuser.mymessage.service.NoticeMessageService;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;


@Controller
@RequestMapping("/message")
public class MessageController extends BaseController{
	
	@Autowired
	MessageService mssageService;
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.mymessage.service.NoticeMessageServiceImpl")
	NoticeMessageService noticeMessageService;
	
	//查询申请列表
	@RequestMapping(value="/applyMessageList")
	public ModelAndView toScheduleList(HttpServletRequest  request){
		
		Map<String, Object> map = getUserInfo();
		Long userId = new Long(String.valueOf(map.get("id")));
		//Long userId = (long) 24967;
		
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
		paraMap.put("condition",condition);
		paraMap.put("userId",userId);
		paraMap.put("addPara",addPara);
		
		List<Map<String,Object>> list = noticeMessageService.selectApplyMessage(paraMap);
		mv.addObject("list",list);
		mv.addObject("listSize",list.size());
		mv.addObject("condition",condition);
		mv.addObject("addPara",addPara);
		
		mv.setViewName("commuser/message/applyMessage");
		return mv;
	}
	
	//查询更多申请列表
	@RequestMapping(value="/loadMoreApply")
	@ResponseBody
	public List<Map<String,Object>> loadMoreApply(HttpServletRequest request){
		String condition=request.getParameter("condition");
		String para=request.getParameter("startPara");
		Map<String, Object> map = getUserInfo();
		Long userId = new Long(String.valueOf(map.get("id")));
		//Long userId = (long) 1609;
		int startPara=0;
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(null!=para&&!para.equals("")){
			startPara = Integer.parseInt(para);
			
			paraMap.put("startPara",startPara);
			
		}else{
			startPara=8;
			paraMap.put("startPara",startPara);
		}
		
		
		paraMap.put("condition",condition);
		paraMap.put("userId",userId);
		
		List<Map<String,Object>> list = noticeMessageService.selectApplyMessage(paraMap);
		return list;
	}
	
	
	
	//更新申请信息状态
	@RequestMapping(value="/updateApplyMessage")
	public ModelAndView updateApplyMessage(HttpServletRequest  request){
		String id=request.getParameter("id");
		Map<String, Object> map = getUserInfo();
		Long userId = new Long(String.valueOf(map.get("id")));
		//Long userId = (long) 24967;
		//1：忽略    2：接受
		String flag=request.getParameter("flag");
		ModelAndView mv = new ModelAndView();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(null!=id&&!id.equals("")){
			long applyId = Long.valueOf(id).longValue();
			paraMap.put("flag", flag);
			paraMap.put("applyId", applyId);
			noticeMessageService.updateApplyMessage(paraMap);
		}
		
		paraMap.put("userId", userId);
		List<Map<String,Object>> list = noticeMessageService.selectApplyMessage(paraMap);
		mv.addObject("list",list);
		mv.addObject("listSize",list.size());
		mv.setViewName("commuser/message/applyMessage");
		return mv;
	}
	
	//查询通知列表
	@RequestMapping(value="/noticeMessageList")
	public ModelAndView toNoticeMessageList(HttpServletRequest request){
		
		Map<String, Object> map = getUserInfo();
		Long userId = new Long(String.valueOf(map.get("id")));
		//Long userId = (long) 1609;
		String condition=request.getParameter("condition");
		Map<String,Object> paraMap = new HashMap<String,Object>();
		ModelAndView mv = new ModelAndView();
	
		paraMap.put("condition",condition);
		paraMap.put("userId",userId);
		paraMap.put("startPara",0);
		List<Map<String,Object>> list = noticeMessageService.selectNoticeMessage(paraMap);
		
		mv.addObject("listSize",list.size());
		mv.addObject("list",list);
		mv.addObject("condition",condition);
		
		mv.setViewName("commuser/message/noticeMessage");
		return mv;
	}
	//查询更多通知列表
	@RequestMapping(value="/loadMore")
	@ResponseBody
	public List<Map<String,Object>> loadMore(HttpServletRequest request){
		Map<String, Object> map = getUserInfo();
		Long userId = new Long(String.valueOf(map.get("id")));
		//Long userId = (long) 1609;
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
		
		paraMap.put("condition",condition);
		paraMap.put("userId",userId);
		
		List<Map<String,Object>> list = noticeMessageService.selectNoticeMessage(paraMap);
		
		return list;
	}
	
	//更新申请信息状态(逻辑删除)
	@RequestMapping(value="/deleteNoticeMessage")
	@ResponseBody
	public String deleteNoticeMessage(HttpServletRequest  request){
		String id=request.getParameter("nid");
		Map<String, Object> map = getUserInfo();
		Long userId = new Long(String.valueOf(map.get("id")));
		//Long userId = (long) 1609;
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(null!=id&&!id.equals("")){
			long noticeId = Long.valueOf(id).longValue();
			paraMap.put("noticeId", noticeId);
			noticeMessageService.deleteNoticeMessage(paraMap);
		}
		
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
		String messageId=request.getParameter("msgId");
		String cmsId=request.getParameter("cmsId");
		ModelAndView mv = new ModelAndView();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("messageId", messageId);
		paraMap.put("cmsId", cmsId);
		//标题、时间、邮寄地址、备注
		Map<String,Object> mapTitle =new HashMap<>();
			mapTitle = noticeMessageService.queryNoticeMessageDetail(paraMap);
			mv.addObject("firsttag", "个人中心");
			mv.addObject("secondtag", "消息通知");
			mv.addObject("firstpath", "personalhomepage/tohomepage.action");
			mv.addObject("secondpath", "message/noticeMessageList.action");
		
		
		if(mapTitle!=null && mapTitle.size()>0){
			paraMap.put("attachmentId", mapTitle.get("attachmentId"));
			paraMap.put("materialId", mapTitle.get("materialId"));
			//备注附件
			List<Map<String,Object>> listAttachment = noticeMessageService.queryNoticeMessageDetailAttachment(paraMap);
			//联系人
			List<Map<String,Object>> listContact = noticeMessageService.queryNoticeMessageDetailContact(paraMap);
			
			mv.addObject("map",mapTitle);
			mv.addObject("listAttachment",listAttachment);
			mv.addObject("listContact",listContact);
			
		}
		
		
	/*	Message message = new Message();
		message.setContent(" 人民卫生出版社建社50年来，累计出版图书2万余种，总印数约67000万册，每年出书1000余种，年发行量1000多万册， 年产值超过5亿元。出书品种主要包括： 医学教材、参考书和医学科普读物等，涉及现代医药学和中国传统医药学的所有领域， 体系完整，品种齐全。人卫社不断加强管理，优化选题，提高质量，多出精品，加强服务，已成为国内唯一涵盖医学各领域,各层次的出版机构,能满足不同读者的需求。使读者享受到一流的作者、一流的质量、一流的服务。人卫社的品牌已成为优质图书的代名词。人民卫生出版社出版医学教材有着优良的传统。 从建社伊始的20世纪50年代， 翻译前苏联的医学教材以满足国内教学需要， 到组织国内一流作者自编教材至今已有50年的历史。一代代的医学生都是伴随着人卫社出版的教材成长起来的。");
		message.setId("5a15c32dc5482247f0b8dca2");
		mssageService.add(message);*/
		
		//mongoDB查询通知内容
		Message message = mssageService.get(messageId);
		//更新通知点击量
		noticeMessageService.updateNoticeClicks(cmsId);
		
		
		mv.addObject("message",message);
		mv.addObject("messageId",messageId);
		
		mv.setViewName("commuser/message/noticeMessageDetail");
		return mv;
	}
}
