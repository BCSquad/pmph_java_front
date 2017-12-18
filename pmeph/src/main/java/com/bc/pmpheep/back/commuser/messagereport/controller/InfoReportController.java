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
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;

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
   @Autowired
   private MessageService messageservice;
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
			   if(num>=total){
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



	  //mongodb   添加数据测试
//	  @RequestMapping("/addcontent")
//	  @ResponseBody
//	  public Map<String,Object> addcontent(HttpServletRequest request){
//	    Message message=new Message();
//	    message.setContent("　新华社北京12月11日电  中共中央总书记、国家主席、中央军委主席习近平近日作出重要指示强调，纠正“四风”不能止步，作风建设永远在路上。习近平是就新华社一篇《形式主义、官僚主义新表现值得警惕》的文章作出指示的。他指出，文章反映的情况，看似新表现，实则老问题，再次表明“四风”问题具有顽固性反复性。纠正“四风”不能止步，作风建设永远在路上。各地区各部门都要摆摆表现，找找差距，抓住主要矛盾，特别要针对表态多调门高、行动少落实差等突出问题，拿出过硬措施，扎扎实实地改。各级领导干部要带头转变作风，身体力行，以上率下，形成“头雁效应”。在即将开展的“不忘初心、牢记使命”主题教育中，要力戒形式主义，以好的作风确保好的效果。新华社的文章反映，党的十八大以来，从制定和执行中央八项规定开始，全党上下纠正“四风”取得重大成效，但形式主义、官僚主义在一定程度上仍然存在，如：一些领导干部调研走过场、搞形式主义，调研现场成了“秀场”；一些单位“门好进、脸好看”，就是“事难办”；一些地方注重打造领导“可视范围”内的项目工程，“不怕群众不满意，就怕领导不注意”；有的地方层层重复开会，用会议落实会议；部分地区写材料、制文件机械照抄，出台制度决策“依葫芦画瓢”；一些干部办事拖沓敷衍、懒政庸政怠政，把责任往上推；一些地方不重实效重包装，把精力放在“材料美化”上，搞“材料出政绩”；有的领导干部热衷于将责任下移，“履责”变“推责”；有的干部知情不报、听之任之，态度漠然；有的干部说一套做一套、台上台下两个样。12月9日，中共中央办公厅印发通知指出，习近平总书记的这一重要指示，一针见血、切中时弊，内涵丰富、要求明确，充分表明了以习近平同志为核心的党中央坚定不移全面从严治党、持之以恒正风肃纪的鲜明态度和坚定决心，对于全党深入学习贯彻党的十九大精神、加强党的作风建设具有重要指导意义。通知要求，各地区各部门要迅速传达学习并切实抓好贯彻落实。要认真组织党员、干部学习讨论习近平总书记的重要指示，结合深入学习贯彻党的十九大精神和习近平新时代中国特色社会主义思想，深刻领会指示的内容和精神实质，牢固树立“四个意识”，不断提高政治站位和政治自觉，以永远在路上的坚韧锲而不舍抓好作风建设；各地区各部门年底召开民主生活会和组织生活会，要把贯彻落实中央八项规定精神、转作风改作风情况作为对照检查的重要内容，切实按照习近平总书记的重要指示要求，认真查找“四风”突出问题特别是形式主义、官僚主义的新表现，采取过硬措施，坚决加以整改，务求取得实效；要坚持从各级领导干部做起，以上率下、层层带动，继续紧盯元旦、春节等时间节点，从一件件小事抓起，坚决防止不良风气反弹回潮，不断巩固和拓展落实中央八项规定精神的成果。");
//	    message.setId("5a11bbe72379fac0e14f4275");
//	    messageservice.add(message);
//		return new HashMap<>();
//	  }
}
