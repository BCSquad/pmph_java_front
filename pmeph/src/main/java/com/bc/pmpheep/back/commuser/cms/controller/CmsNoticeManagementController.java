package com.bc.pmpheep.back.commuser.cms.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.bc.pmpheep.back.commuser.homepage.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.cms.bean.CmsNoticeList;
import com.bc.pmpheep.back.commuser.cms.service.CmsNoticeManagementService;
import com.bc.pmpheep.back.commuser.mymessage.service.NoticeMessageService;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.ContentService;
import com.bc.pmpheep.general.service.MessageService;

/**
 * 
 * 
 * 功能描述：公告列表
 * 
 * 
 * 
 * @author (作者) 曾庆峰
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 2017年11月27日
 * @modify (最后修改时间)
 * @修改人 ：曾庆峰
 * @审核人 ：
 *
 */
@Controller
@RequestMapping(value = "/cmsnotice")
public class CmsNoticeManagementController extends BaseController {
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.cms.service.CmsNoticeManagementServiceImpl")
	CmsNoticeManagementService cmsNoticeManagementService;
	@Autowired
	MessageService mssageService;
	@Autowired
	ContentService contentServioce;
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.mymessage.service.NoticeMessageServiceImpl")
	NoticeMessageService noticeMessageService;

	@Autowired
	@Qualifier("com.bc.pmpheep.back.homepage.service.HomeServiceImpl")
	private HomeService homeService;
	@Autowired
	ContentService contentService;

	/**
	 * 跳转到教材列表页面
	 * @author Mryang
	 * @createDate 2017年12月5日 下午4:29:26
	 * @param pageSize
	 * @param pageNumber
	 * @param isHot
	 * @return
	 */
	@RequestMapping(value = "/tolist", method = RequestMethod.GET)
	public ModelAndView tolistPage(HttpServletRequest req,Integer pageSize, Integer pageNumber, Boolean isHot) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> adInfo = homeService.getPageAdInfo("pc信息快报和遴选公告列表 ");
		String materialId=req.getParameter("materialId");
		modelAndView.addObject("adInfo", adInfo);
		modelAndView.addObject("materialId", materialId);
		modelAndView.setViewName("commuser/focusAndSelect/materialNotice");
		return  modelAndView;
	}
	
	/**
	 * 
	 * 
	 * 功能描述：获取公告列表
	 *
	 * @param pageSize
	 *            一页的数据数量
	 * @param pageNumber
	 *            当前第几页
	 * @param isHot
	 *            是否热门
	 * @return
	 *
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<CmsNoticeList> list(HttpServletRequest req,Integer pageSize, Integer pageNumber, Integer order,String noticeName) {
		Long userid=0L;
		Map<String,Object> usermap=getUserInfo();
		String materialId=req.getParameter("materialId");
		if(usermap !=null){
			userid=Long.valueOf(usermap.get("id").toString());
		}
		List<CmsNoticeList> cmsNoticeList =  cmsNoticeManagementService.list(pageSize, pageNumber, order,userid,materialId,noticeName);
		if(cmsNoticeList!=null && cmsNoticeList.size()>0){
			for (CmsNoticeList cmsNotice : cmsNoticeList) {
				Content content = contentService.get(cmsNotice.getMid());
				if(content!=null){
				 cmsNotice.setContentxt(removeHtml(content.getContent()));
				}else{
					 cmsNotice.setContentxt("(内容为空)");
				}
			}
		}
		return cmsNoticeList ;
	}
    
	//查询公告详情
		@RequestMapping(value="/noticeMessageDetail")
		public ModelAndView toNoticeMessageDetail(HttpServletRequest request){
			String messageId=request.getParameter("id");
			String tag=request.getParameter("tag");
//			ModelAndView mv = new ModelAndView();
//			Map<String,Object> paraMap = new HashMap<String,Object>();
//			paraMap.put("messageId", messageId);
			Map<String, Object> user = getUserInfo();
			String materialId=request.getParameter("materialId");
			String cmsId=request.getParameter("csmId");
			ModelAndView mv = new ModelAndView();
			Map<String,Object> paraMap = new HashMap<String,Object>();
			paraMap.put("materialId", materialId);
			paraMap.put("cmsId", cmsId);
			paraMap.put("userid", (user!=null?user.get("id"):""));
			//标题、时间、邮寄地址、备注
			Map<String,Object> mapTitle =new HashMap<>();
			mapTitle=noticeMessageService.queryNoticeMessageDetail(paraMap);
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
			mv.addObject("is_material_entry", mapTitle.get("is_material_entry"));
			mv.addObject("firsttag", "首页>");
			mv.addObject("firstpath", "homepage/tohomepage.action");
			mv.addObject("materialId",mapTitle.get("material_id"));
			if(tag!=null && tag.equals("FromCommunityList")){
				//来自教材社区列表的request
				
				mv.addObject("secondtag", "教材社区>");
				mv.addObject("secondpath", "community/tolist.action");
			}else{
				//来遴选公告列表中通知的request
				
				mv.addObject("secondtag", "遴选公告>");
				mv.addObject("secondpath", "cmsnotice/tolist.action");
			}
			List<Map<String, Object>> cmsAttach = noticeMessageService.queryCMSAttach(paraMap);
			mv.addObject("notEnd", 0);
			if(mapTitle!=null && mapTitle.size()>0 && mapTitle.get("is_material_entry").toString()=="true"){
				if("no".endsWith(mapTitle.get("ended").toString())&&
						"false".equals(mapTitle.get("is_all_textbook_published").toString())
						&&"false".equals(mapTitle.get("is_force_end").toString())){
		        	   mv.addObject("notEnd", 1);
		        }else{
		        	   mv.addObject("notEnd", 0);
		        }
				
				
				paraMap.put("materialId", mapTitle.get("material_id"));
				//备注附件
				List<Map<String,Object>> listAttachment = noticeMessageService.queryNoticeMessageDetailAttachment(paraMap);
				//联系人
				List<Map<String,Object>> listContact = noticeMessageService.queryNoticeMessageDetailContact(paraMap);
				
				for(Map<String,Object> map :listAttachment){
					map.put("attachmentId", "file/download/"+map.get("attachment")+".action");
				}
				mv.addObject("listAttachment",listAttachment);
				mv.addObject("listContact",listContact);
			}	
			mv.addObject("map",mapTitle);
			//mongoDB查询通知内容
			Content message= contentServioce.get(messageId);
			mv.addObject("content",message.getContent());
			mv.addObject("cmsAttach",cmsAttach);
			mv.setViewName("commuser/message/noticeMessageDetail");
			return mv;
		}
		//去掉字符串中的html标签
		public String removeHtml(String str){
			String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
			Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
			Matcher m_html=p_html.matcher(str); 
			str=m_html.replaceAll(""); //过滤html标签 
			return str;
		}
}
