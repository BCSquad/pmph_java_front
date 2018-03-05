package com.bc.pmpheep.back.commuser.writerArticle.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
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
import com.bc.pmpheep.back.uncertainfieldcom.bean.CmsCategoryConfig;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.ContentService;
import com.bc.pmpheep.general.service.FileService;
import com.bc.pmpheep.general.service.MessageService;
import com.bc.pmpheep.general.service.SensitiveService;
import com.mongodb.gridfs.GridFSDBFile;
@RequestMapping("/writerArticle")
@Controller
public class WriterArticleController extends BaseController{
	@Autowired
	ContentService contentService;
	@Autowired
    @Qualifier("com.bc.pmpheep.general.service.FileService")
    FileService fileService;
	@Autowired
	@Qualifier("com.bc.pmpheep.general.service.SensitiveService")
	SensitiveService sensitiveService;
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.writerArticle.service.WriterArticleServiceImpl")
	private WriterArticleServiceImpl writerArticleServiceImpl;
	Logger logger = LoggerFactory.getLogger(WriterArticleController.class);
	
	@Autowired
	CmsCategoryConfig cmsCategoryConfig;
	
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
	    paraMap.put("category_id", cmsCategoryConfig.getId("医学随笔"));
	     
	    
	    if(id!=null&&!"".equals(id)){
	    	String userid = request.getParameter("userid");
	    	paraMap.put("userId", Long.parseLong(userid));
	    	paraMap.put("id", Long.parseLong(id));//写文章的id
	    }else {
	    	paraMap.put("id", -1);//写文章的id 如果不是请求中传入的，就应该无法查到已有的，用不存在的id
		}
	    Map<String, Object> map2 = new HashMap<String, Object>();
	    
	    Map<String, Object> map1 = writerArticleServiceImpl.queryWriteArticleInfo(paraMap);
		if (map1!=null) {
			map2.putAll(map1);
		}
		
		if(id!=null&&!"".equals(id)){
			map2.put("submitTypeCode", 1);
		}else{
			map2.put("submitTypeCode", 0);
		}
		
		try {
			String UEContent = contentService.get(map2.get("mid").toString()).getContent();
			map2.put("UEContent", map2==null?"":UEContent);
			GridFSDBFile cover = fileService.get(map2.get("cover").toString());
			map2.put("coverName", map2==null?"":cover.getFilename());
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
		String cover = request.getParameter("image");
		String btnType = request.getParameter("btnType");
		String coverName = request.getParameter("imageName");
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
		if (sensitiveService.confirmSensitive(titleValue) || sensitiveService.confirmSensitive(UEContent)){
			List<String> sensitives = sensitiveService.getSensitives(titleValue, UEContent);
			resultMap.put("flag", "4");
			resultMap.put("value", sensitives);
			resultMap.put("UEContent", sensitiveService.delHTMLTag(UEContent));
			return resultMap;
		}
		//List list= new ArrayList();
		try {
			    int is_staging = "0".equals(btnType)?0:1;  //是否暂存  1 暂存  0不暂存 提交
				Map map = new HashMap();
				map.put("parent_id", 0); //上级id
				map.put("category_id",cmsCategoryConfig.getId("医学随笔")); //内容类型 >0 非评论
				map.put("title",titleValue); //内容标题  
				map.put("cover",cover); //封面mdb 的id
				map.put("coverName",coverName); //封面mdb 的名字
				map.put("author_type",2); //作者类型
				map.put("author_id",uid); //作者id
				map.put("is_staging",is_staging); //是否暂存
				map.put("path",0); //根路径
				Map<String, Object> insertMap = writerArticleServiceImpl.insertWriteArticle(map,UEContent);
				flag=insertMap.get("msg_id").toString();
				resultMap.put("atrticle_id", insertMap.get("atrticle_id").toString());
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
		String cover = request.getParameter("image");
		String coverName = request.getParameter("imageName");
		UEContent = UEContent.replaceAll("\r\n", "");
		
		
		String msg_id = request.getParameter("msg_id");
		String btnType = request.getParameter("btnType");
		String atrticle_id = request.getParameter("atrticle_id");
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String  flag="0";
		if (sensitiveService.confirmSensitive(titleValue) || sensitiveService.confirmSensitive(UEContent)){
			flag = "4";
			List<String> sensitives = sensitiveService.getSensitives(titleValue, UEContent);
			resultMap.put("flag", flag);
			resultMap.put("value", sensitives);
			resultMap.put("UEContent", sensitiveService.delHTMLTag(UEContent));
			return resultMap;
		}
		Map<String, Object> user = this.getUserInfo();
		BigInteger uid = (BigInteger) user.get("id");//用户的id
		try {
			int is_staging = "0".equals(btnType)?0:1;  //是否暂存  1 暂存  0不暂存 提交
		
			Map map = new HashMap();
			map.put("msg_id", msg_id); //内容id 唯一标识
			map.put("parent_id", 0); //上级id
			map.put("category_id",cmsCategoryConfig.getId("医学随笔")); //内容类型 >0 非评论
			map.put("title",titleValue); //内容标题  
			map.put("cover",cover); //封面mdb 的id
			map.put("coverName",coverName); //封面mdb 的名字
			map.put("author_type",2); //作者类型
			map.put("author_id",uid); //作者id
			map.put("path",0); //根路径
			map.put("is_staging",is_staging); //是否暂存
			map.put("atrticle_id",atrticle_id); //文章主键
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
	
	/**
	 * 删除随笔文章
	 * @return
	 */
	@RequestMapping(value="/updateDelWriter",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateDelWriter(HttpServletRequest request){
		String writerid = request.getParameter("id");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String  flag="0";
		Map<String, Object> user = this.getUserInfo();
		BigInteger uid = (BigInteger) user.get("id");//用户的id
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("author_id",uid); //作者id
			map.put("writerid",writerid); //文章id
			//修改上一次的全部内容
			 writerArticleServiceImpl.updateDelWriter(map);
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
