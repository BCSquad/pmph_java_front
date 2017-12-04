package com.bc.pmpheep.back.commuser.writerArticle.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.authadmin.message.controller.SendMessage;
import com.bc.pmpheep.back.authadmin.message.service.SendMessageServiceImpl;
import com.bc.pmpheep.back.commuser.writerArticle.service.WriterArticleServiceImpl;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.FileService;
import com.bc.pmpheep.general.service.MessageService;
@RequestMapping("/writerArticle")
@Controller
public class WriterArticleController {
	@Autowired
	MessageService mssageService;
	@Autowired
    @Qualifier("com.bc.pmpheep.general.service.FileService")
    FileService fileService;
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.writerArticle.service.WriterArticleServiceImpl")
	private WriterArticleServiceImpl writerArticleServiceImpl;
	Logger logger = LoggerFactory.getLogger(WriterArticleController.class);
	
	
	
	/**
	 * 写文章页面
	 * @return
	 */
	@RequestMapping("/initWriteArticle")
	public  ModelAndView initWriteArticle(){
		//初始化展示 保存 提交 查看  控制按钮的显示
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", "527");
		Map<String, Object> map2 = writerArticleServiceImpl.queryWriteArticleInfo(map);
		try {
			String UEContent = mssageService.get(map2.get("mid").toString()).getContent();
			map2.put("UEContent", map2==null?"":UEContent);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return new ModelAndView("/commuser/message/writeArticle",map2);
	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/writeArticle",method=RequestMethod.POST)
	@ResponseBody
	public String writeArticle(HttpServletRequest request){
		String titleValue = request.getParameter("titleValue");
		String UEContent = request.getParameter("UEContent");
		String btnType = request.getParameter("btnType");
		String flag = "0";
		//List list= new ArrayList();
		try {
			    int is_staging = "0".equals(btnType)?0:1;  //是否暂存  0 暂存  1不暂存 提交
				//发送消息 到MongoDB 
				Message message = new Message();
				message.setContent(UEContent);
				Message messageResult = mssageService.add(message);
				String msg_id = messageResult.getId();
				Map map = new HashMap();
				map.put("mid", msg_id); //内容id
				map.put("parent_id", 0); //上级id
				map.put("category_id",1); //内容类型 >0 非评论
				map.put("title",titleValue); //内容标题  
				map.put("author_type",2); //作者类型
				map.put("author_id",Long.parseLong("527")); //作者id
				map.put("is_staging",is_staging); //是否暂存
				map.put("path",0); //根路径
				writerArticleServiceImpl.insertWriteArticle(map);
				//flag = "0".equals(btnType)?"0":"1";
				flag = msg_id;
			
		} catch (Exception e) {
			// TODO: handle exception
			flag ="1";
			e.printStackTrace();
			return flag;
		}
		
		return flag;
		
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/updateIsStaging",method=RequestMethod.POST)
	@ResponseBody
	public String updateIsStaging(HttpServletRequest request){
		String titleValue = request.getParameter("titleValue");
		String UEContent = request.getParameter("UEContent");
		String msg_id = request.getParameter("msg_id");
		String btnType = request.getParameter("btnType");
		String flag = "0";
		try {
			int is_staging = "0".equals(btnType)?0:1;  //是否暂存  0 暂存  1不暂存 提交
			//发送消息 到MongoDB  根据传过去的msg_id 去找到写的这个文章
			Message message = new Message();
			message.setContent(UEContent);
			Message messageResult = mssageService.add(message);
			String mid = messageResult.getId();
			Map map = new HashMap();
			map.put("mid", mid); //内容id
			map.put("msg_id", msg_id); //内容id
			map.put("parent_id", 0); //上级id
			map.put("category_id",1); //内容类型 >0 非评论
			map.put("title",titleValue); //内容标题  
			map.put("author_type",2); //作者类型
			map.put("author_id",Long.parseLong("527")); //作者id
			map.put("path",0); //根路径
			map.put("is_staging",is_staging); //是否暂存
			//修改上一次的全部内容
			writerArticleServiceImpl.updateIsStaging(map);
			flag = mid;
		} catch (Exception e) {
			// TODO: handle exception
			flag ="1";
			e.printStackTrace();
			return flag;
			
		}
		return flag;
	}

}
