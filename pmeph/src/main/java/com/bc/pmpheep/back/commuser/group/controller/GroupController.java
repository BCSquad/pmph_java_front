package com.bc.pmpheep.back.commuser.group.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.group.bean.GroupFileVO;
import com.bc.pmpheep.back.commuser.group.bean.GroupList;
import com.bc.pmpheep.back.commuser.group.bean.GroupMessageVO;
import com.bc.pmpheep.back.commuser.group.service.GroupService;
import com.bc.pmpheep.service.exception.CheckedServiceException;


/**
 * 
 * @introduction
 *
 * @author Mryang
 *
 * @createDate 2017年12月8日 下午2:03:30
 *
 */


@Controller
@RequestMapping(value = "/group")
public class GroupController extends com.bc.pmpheep.general.controller.BaseController {
	
    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.group.service.GroupServiceImpl")
    private GroupService groupService;

    /**
     * 加载全部小组
     * @introduction 
     * @author Mryang
     * @createDate 2017年12月8日 下午2:09:27
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listMyGroup(HttpServletRequest request,
    		HttpServletResponse response )  {
    	Integer pageNumber = 1;
    	Integer pageSize = 6;
    	String count = request.getParameter("pageNumber");
    	Map<String, Object> map = this.getUserInfo();
    	Long userId =  Long.parseLong(map.get("id").toString());
        //Long userId = 10226L;
		 if(count!=null){
			 pageNumber = Integer.parseInt(count)+1;
			 pageSize = pageSize+(pageNumber-1)*3;
		 }
        ModelAndView model = new ModelAndView();
        //加载更多
        List<GroupList> lst=groupService.groupList(0, pageSize, userId) ;
        model.setViewName("commuser/mygroup/groupList");
        model.addObject("listgroup", lst);
        model.addObject("listSize", lst.size());
        model.addObject("pageNumber", pageNumber);
        return model;
    }
    
    /**
     * 进入某个具体小组
     * @introduction 
     * @author Mryang
     * @createDate 2017年12月12日 下午5:42:27
     * @param groupId
     * @return
     */
    @RequestMapping("/toMyGroup")
    public ModelAndView toMyGroup(HttpServletRequest request,
    		HttpServletResponse response){
    	ModelAndView mav = new ModelAndView();
    	Map<String, Object> userMap = this.getUserInfo();
    	String type = request.getParameter("type");
    	String count = request.getParameter("pageSize");
    	String groupId = request.getParameter("groupId");
    	String file_name1 = request.getParameter("file_name");
    	String file_name = "";
    	if(file_name1!=null){
    		try {
    			file_name = "%"+URLDecoder.decode(file_name1,"UTF-8")+"%";
    		} catch (UnsupportedEncodingException e) {
    			e.printStackTrace();
    		}
    	}
    	Map<String,Object> queryMap = new HashMap<String,Object>();
    	queryMap.put("group_id", groupId);
    	queryMap.put("user_id", userMap.get("id"));
    	queryMap.put("file_name", file_name1);
    	
    	//其余成员信息
    	List<Map<String,Object>> memberList = this.groupService.memberList(queryMap);
    	mav.addObject("memberList", memberList);
    	
    	mav.addObject("queryMap", queryMap);
    	//小组名称
    	mav.addObject("groupMap", this.groupService.queryGroup(queryMap));
    	 //角色
    	mav.addObject("role", groupService.isFounderOrisAdmin(groupId,userMap.get("id").toString())?"你是这个小组的管理员":"你是这个小组的普通用户");
    	//人数
    	mav.addObject("memberCount", groupService.countMember(queryMap));
    	//文件数
    	mav.addObject("fileCount", groupService.countFile(queryMap));
    	if(type!=null){
	    	 if(type.equals("wjgx")){ //文件共享
	    		//文件共享
	    		 int pageSize = 1;
	    		 if(count!=null){
	    			 pageSize = Integer.parseInt(count)+1;
	    		 }
	    		queryMap.put("pageSize", pageSize*10);
	        	List<Map<String,Object>> fileList = this.groupService.fileList(queryMap);
	        	mav.addObject("fileList", fileList);
	        	mav.addObject("pageSize", pageSize);
	        	mav.addObject("type", "wjgx");
	    		mav.setViewName("commuser/mygroup/toShareFile");
	    	}else{
	    		//消息列表
	        	List<Map<String,Object>> messgaeList = this.groupService.messageList(queryMap);
	        	mav.addObject("messgaeList", messgaeList);
	        	mav.addObject("type", "hdjl");
	    		mav.setViewName("commuser/mygroup/communication");
	    	}
    	}else{
    		//消息列表
        	List<Map<String,Object>> messgaeList = this.groupService.messageList(queryMap);
        	mav.addObject("messgaeList", messgaeList);
        	mav.addObject("type", "hdjl");
    		mav.setViewName("commuser/mygroup/communication");
    	}
        return mav;
    }
    
    /**
     * 获取小组讨论
     * @introduction 
     * @author Mryang
     * @createDate 2017年12月12日 下午5:50:14
     * @param pageNumber
     * @param pageSize
     * @param groupId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTalks", method = RequestMethod.GET)
    public List<GroupMessageVO> getTalks(Integer pageNumber,Integer pageSize,@RequestParam(value="groupId")Long  groupId)  {
    	Map<String, Object> map = this.getUserInfo();
    	Long thisId = new Long (String.valueOf(map.get("id")));
        return groupService.getTalks(thisId, groupId, pageNumber,pageSize);
    }
    
    /**
     * 获取文件
     * @introduction 
     * @author Mryang
     * @createDate 2017年12月13日 上午10:28:14
     * @param pageNumber
     * @param pageSize
     * @param groupId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFiles", method = RequestMethod.GET)
    public List<GroupFileVO> getFiles(Integer pageNumber,Integer pageSize,@RequestParam(value="groupId")Long  groupId,String  fileName)  {
    	Map<String, Object> map = this.getUserInfo();
    	Long thisId = new Long (String.valueOf(map.get("id")));
        return groupService.groupFiles(pageNumber,pageSize,groupId,fileName,thisId);
    }
    
    /**
     * 退出小组
     * @introduction 
     * @author Mryang
     * @createDate 2017年12月13日 上午10:28:14
     * @param pageNumber
     * @param pageSize
     * @param groupId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/quitGroup", method = RequestMethod.GET)
    public Boolean quitGroup(@RequestParam(value="groupId")Long  groupId)  {
    	Map<String, Object> map = this.getUserInfo();
    	Long thisId = new Long (String.valueOf(map.get("id")));
        return groupService.quitGroup(groupId,thisId);
    }
    
    /**
     * 获取文件
     * @introduction 
     * @author Mryang
     * @createDate 2017年12月13日 上午10:28:14
     * @param pageNumber
     * @param pageSize
     * @param groupId
     * @return
     * @throws IOException 
     * @throws CheckedServiceException 
     */
    @ResponseBody
    @RequestMapping(value = "/fileup", method = RequestMethod.POST)
    public Boolean fileup(@RequestParam("file")MultipartFile file,@RequestParam(value="groupId")Long groupId,
    		HttpServletResponse response,HttpServletRequest request) throws CheckedServiceException, IOException  {
    	Map<String, Object> map = this.getUserInfo();
    	Long thisId = new Long (String.valueOf(map.get("id")));
    	groupService.addFile(file,  groupId, thisId);
    	response.sendRedirect(request.getServletContext().getContextPath()+"/group/toMyGroup.action?groupId="+groupId);//重定向到apage.jsp
    	return true;
    }
    
    @ResponseBody
    @RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
    public Boolean deleteFile(@RequestParam(value="groupId")Long groupId,@RequestParam(value="id")Long id,@RequestParam(value="fileId")String fileId)  {
    	Map<String, Object> map = this.getUserInfo();
    	Long thisId = new Long (String.valueOf(map.get("id")));
    	groupService.deleteFile(id,groupId,fileId,thisId);
    	return true;
    }
    
    /**
     * 发送消息
     */
    @RequestMapping("/sendMessage")
    @ResponseBody
    public String sendMessage(HttpServletRequest request,
    		HttpServletResponse response){
    	String Msg = "";
    	Map<String, Object> userMap = this.getUserInfo();
    	String group_id = request.getParameter("group_id");
    	String msg_content = request.getParameter("msg_content");
    	Map<String, Object> queryMap = new HashMap<String,Object>();
    	queryMap.put("group_id", group_id);
    	queryMap.put("member_id", userMap.get("id"));
    	queryMap.put("msg_content", msg_content);
    	int count = this.groupService.addMessage(queryMap);
    	if(count>0){
    		Msg = "OK";
    	}
    	return Msg;
    }
    
    /**
     * 添加附件
     */
    @RequestMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(HttpServletRequest request,
    		HttpServletResponse response){
    	String Msg = "";
    	Map<String, Object> userMap = this.getUserInfo();
    	String syllabus_id = request.getParameter("syllabus_id");
    	String syllabus_name = request.getParameter("syllabus_name");
    	String group_id = request.getParameter("group_id");
    	Map<String, Object> queryMap = new HashMap<String,Object>();
    	queryMap.put("group_id", group_id);
    	queryMap.put("member_id", userMap.get("id"));
    	queryMap.put("msg_content", userMap.get("nickname")+"上传了"+syllabus_name);
    	queryMap.put("file_id", syllabus_id);
    	queryMap.put("file_name", syllabus_name);
    	int count = this.groupService.addMessage(queryMap);
    	count += this.groupService.addFile(queryMap);
    	if(count>0){
    		Msg = "OK";
    	}
    	return Msg;
    }
    
    
}






