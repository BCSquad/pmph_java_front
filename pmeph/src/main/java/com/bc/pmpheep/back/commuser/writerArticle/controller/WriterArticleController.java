package com.bc.pmpheep.back.commuser.writerArticle.controller;

import java.math.BigInteger;
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
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.FileService;
import com.bc.pmpheep.general.service.MessageService;
@RequestMapping("/writerArticle")
@Controller
public class WriterArticleController extends BaseController{
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
	public  ModelAndView initWriteArticle(HttpServletRequest request){
		//初始化展示 保存 提交 查看  控制按钮的显示
		Map<String,Object> paraMap = new HashMap<String,Object>();
		Map<String, Object> user = this.getUserInfo();
		BigInteger uid = (BigInteger) user.get("id");//用户的id
		paraMap.put("userId", uid);
	    String id = request.getParameter("id");//写文章的id
	    if(id!=null&&"".equals(id)){
	    	String userid = request.getParameter("userid");
	    	paraMap.put("userId", Long.parseLong(userid));
	    	paraMap.put("id", Long.parseLong(id));//写文章的id
	    }
		Map<String, Object> map2 = writerArticleServiceImpl.queryWriteArticleInfo(paraMap);
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
	public Map<String,Object> writeArticle(HttpServletRequest request){
		String titleValue = request.getParameter("titleValue");
		String UEContent = request.getParameter("UEContent");
		String btnType = request.getParameter("btnType");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String flag = "0";
		Map<String, Object> user = this.getUserInfo();
		BigInteger uid = (BigInteger) user.get("id");//用户的id
		
		//添加校验
		if(titleValue.length()==0||UEContent.length()==0){
			resultMap.put("isValidate","必填项不为空");
			resultMap.put("flag","2");
			resultMap.put("titleValue",titleValue);
			resultMap.put("UEContent",UEContent);
			return resultMap;
		}else if(titleValue.length()>30){
			resultMap.put("isValidate","标题不能超过30个字");
			resultMap.put("flag","3");
			resultMap.put("titleValue",titleValue);
			resultMap.put("UEContent",UEContent);
			return resultMap;
		}
		//List list= new ArrayList();
		try {
			    int is_staging = "0".equals(btnType)?0:1;  //是否暂存  0 暂存  1不暂存 提交
				Map map = new HashMap();
				map.put("parent_id", 0); //上级id
				map.put("category_id",1); //内容类型 >0 非评论
				map.put("title",titleValue); //内容标题  
				map.put("author_type",2); //作者类型
				map.put("author_id",uid); //作者id
				map.put("is_staging",is_staging); //是否暂存
				map.put("path",0); //根路径
				flag=writerArticleServiceImpl.insertWriteArticle(map,UEContent);
				//flag = "0".equals(btnType)?"0":"1";
			//	flag = msg_id;
			
		} catch (Exception e) {
			// TODO: handle exception
			flag ="1";
			e.printStackTrace();
			resultMap.put("flag", flag);
			return resultMap;
		}
		resultMap.put("flag", flag);
		return resultMap;
		
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/updateIsStaging",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateIsStaging(HttpServletRequest request){
		String titleValue = request.getParameter("titleValue");
		String UEContent = request.getParameter("UEContent");
		String msg_id = request.getParameter("msg_id");
		String btnType = request.getParameter("btnType");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String  flag="0";
		Map<String, Object> user = this.getUserInfo();
		BigInteger uid = (BigInteger) user.get("id");//用户的id
		try {
			int is_staging = "0".equals(btnType)?0:1;  //是否暂存  0 暂存  1不暂存 提交
		
			Map map = new HashMap();
			map.put("msg_id", msg_id); //内容id 唯一标识
			map.put("parent_id", 0); //上级id
			map.put("category_id",1); //内容类型 >0 非评论
			map.put("title",titleValue); //内容标题  
			map.put("author_type",2); //作者类型
			map.put("author_id",uid); //作者id
			map.put("path",0); //根路径
			map.put("is_staging",is_staging); //是否暂存
			//修改上一次的全部内容
			 flag =writerArticleServiceImpl.updateIsStaging(map,UEContent);
		} catch (Exception e) {
			// TODO: handle exception
			flag ="1";
			e.printStackTrace();
			resultMap.put("flag", flag);
			return resultMap;
			
		}
		resultMap.put("flag", flag);
		return resultMap;
	}

}
