package com.bc.pmpheep.back.commuser.messagereport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.bc.pmpheep.back.commuser.messagereport.service.InfoReportService;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 信息快报控制层
 * @param 
 * @return 
 * @throws
 */
@Controller
@RequestMapping("/inforeport")
public class InfoReportController extends BaseController {
   @Autowired
   @Qualifier("com.bc.pmpheep.back.commuser.messagereport.service.InfoReportServiceImpl")
   private InfoReportService infoReportService;
   
    /** 
	 * 到信息快报详情界面
	 */
	@RequestMapping("/toinforeport")
   public ModelAndView toInfoReport(HttpServletRequest request){
	   Map<String,Object> map=new HashMap<>();
	   Map<String, Object> usermap = getUserInfo();
	   Pattern pattern = Pattern.compile("[0-9]*"); 
	   long cmsid=Long.valueOf(request.getParameter("id"));
	   String count=request.getParameter("count");
	   int num=0;
	   int size=5;
	   if(count!=null && !count.equals("")&&pattern.matcher(count).matches()){
		   num=Integer.parseInt(count)*size;
		   map.put("count", count);
	   }else{
		   map.put("count", "0");
	   }
	   List <Map<String,Object>> list=infoReportService.queryReportList(num,size);
	   Map<String,Object> rmap=infoReportService.queryInfoReportById(cmsid,usermap);
	   long clicks=Long.valueOf(rmap.get("clicks").toString());
	   infoReportService.updateClicks(cmsid,clicks+1);
	   rmap.put("clicks", clicks+1);
	   map.put("rmap", rmap);
	   map.put("list", list);
	   return new ModelAndView("commuser/messagereport/inforeport",map);
   }
	
	 /**
	 * 信息快报界面，换一换，刷新列表
	 */
	@RequestMapping("/trychange")
	   @ResponseBody
	   public Map<String,Object> tryChange(HttpServletRequest request){
		   Map<String,Object> map=new HashMap<>();
		   Pattern pattern = Pattern.compile("[0-9]*");
		   String count=request.getParameter("count");
		   int num=0;
		   int size=5;
		   int total=infoReportService.getInfoReportCount();
		   if(count!=null && !count.equals("")&&pattern.matcher(count).matches()){
			   num=Integer.parseInt(count)*size;
			   if(num>total){
				   num=0;
				   map.put("count", "0");
			   }else{
				   map.put("count",count); 
			   }
		   }else{
			   map.put("count", "0");
		   }
		   List <Map<String,Object>> list=infoReportService.queryReportList(num,size);
		   map.put("list", list);
		   return map;
	   }
	
	 /**
	 *点赞或取消点赞
	 */
	   @RequestMapping("/addlike")
	   @ResponseBody
	   public Map<String,Object> addLike(HttpServletRequest request){
		   Map<String,Object> map=new HashMap<>();
		   Map<String, Object> usermap = getUserInfo();
		   long userId=Long.valueOf(usermap.get("id").toString());
		   long id =Long.valueOf(request.getParameter("id"));
		   map=infoReportService.insertLike(id,userId);
		   return map;
	   }
	   
	  /**
	  *收藏或取消收藏
	  */
	  @RequestMapping("/addmark")
	  @ResponseBody
	  public Map<String,Object> addMark(HttpServletRequest request){
	  Map<String,Object> map=new HashMap<>();
	  Map<String, Object> usermap = getUserInfo();
	   long userId=Long.valueOf(usermap.get("id").toString());
	   long id =Long.valueOf(request.getParameter("id"));
	   map=infoReportService.insertMark(id, userId);
		return map;
	  }
}
