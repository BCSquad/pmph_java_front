package com.bc.pmpheep.back.commuser.group.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.group.bean.GroupFileVO;
import com.bc.pmpheep.back.commuser.group.bean.GroupList;
import com.bc.pmpheep.back.commuser.group.bean.GroupMessageVO;
import com.bc.pmpheep.back.commuser.group.bean.PmphGroupMemberVO;
import com.bc.pmpheep.back.commuser.group.service.GroupService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.general.controller.FileDownLoadController;
import com.bc.pmpheep.general.service.FileService;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;
import com.mongodb.gridfs.GridFSDBFile;


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
	Logger logger = LoggerFactory.getLogger(FileDownLoadController.class);
    @Autowired
    private FileService fileService;
    
	
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
    @RequestMapping(value = "/list2", method = RequestMethod.GET)
    public ModelAndView listMyGroup(Integer pageNumber,Integer pageSize )  {
    	if(null == pageNumber || pageNumber < 1){
    		pageNumber = 1 ;
    	}
    	if(null == pageSize   || pageSize < 1){
    		pageSize = 100000 ;  //默认100000,差不多就是查询全部
    	}
        ModelAndView model = new ModelAndView();
        // 获取用户
        Map<String, Object> writerUserMap = this.getUserInfo();
        Long userId =  Long.parseLong(writerUserMap.get("id").toString()) ;
        List<GroupList> lst=groupService.groupList((pageNumber-1)*pageSize, pageSize, userId,"group_name") ;
        model.setViewName("commuser/mygroup/groupList");
        model.addObject("listgroup", lst);
        return model;
    }
    
    
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
        List<GroupList> lst=groupService.groupList(0, pageSize, userId,"group_name") ;
        String isover="no";
        if(lst!=null&& lst.size()<pageSize){
        	isover="yes";
        }
        model.setViewName("commuser/mygroup/groupList");
        model.addObject("listgroup", lst);
        model.addObject("listSize", lst.size());
        model.addObject("isover", isover);
        model.addObject("pageNumber", pageNumber);
        return model;
    }
    
    
    /**
     * 小组换一批
     * @introduction 
     * @author Mryang
     * @createDate 2018年1月17日 下午4:55:58
     * @param groupId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/anotherBatch", method = RequestMethod.GET)
    public List<GroupList> anotherBatch(@RequestParam(value="groupId")Long  groupId)  {
    	if(null == groupId){
    		groupId = -1L;
    	}
    	//我的小组
    	Map<String, Object> map = this.getUserInfo();
    	Long userId = new Long (String.valueOf(map.get("id")));
    	//随机排序
    	List<GroupList> myGroupList     = groupService.groupList(0,1000000,userId,null);
    	if(null == myGroupList || myGroupList.size() == 0){
    		return new ArrayList<GroupList>();
    	}
    	List<GroupList> otherGroupList  = new ArrayList<GroupList>(myGroupList.size()-1 );
    	for(GroupList group: myGroupList){
    		if(null !=group && null!= group.getId() && groupId != group.getId() ){
    			otherGroupList.add(group);
    		}
    	}
    	return otherGroupList;
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
    public ModelAndView toMyGroup(@RequestParam(value="groupId")Long groupId){
    	ModelAndView modelAndView = new ModelAndView();
    	if(null == groupId){
    		modelAndView.setViewName("/comm/error");
            return modelAndView;
    	}
    	Map<String, Object> map = this.getUserInfo();
    	Long userId = new Long (String.valueOf(map.get("id")));
    	modelAndView.addObject("groupId", groupId);
    	//我的小组
    	List<GroupList> myGroupList     = groupService.groupList(0,1000000,userId,"group_name");
    	List<GroupList> otherGroupList  = new ArrayList<GroupList>(myGroupList.size()-1 );
    	GroupList thisGroup = null;
    	for(GroupList group: myGroupList){
    		if(groupId.equals(group.getId())){
    			thisGroup = group ;
    		}else{
    			otherGroupList.add(group);
    		}
    	}
    	//查询当前用户在指定小组中是否是管理员
		Map<String,Object> pmap=groupService.queryAdmin(String.valueOf(map.get("id")),groupId);
    	//没有当前小组的权限
    	if(null == thisGroup){
    		modelAndView.setViewName("/comm/error");
            return modelAndView;
    	}
    	//管理员判定
		modelAndView.addObject("admin",pmap.get("admin"));
    	//当前小组
    	modelAndView.addObject("thisGroup",thisGroup);
    	//用户id
    	modelAndView.addObject("userId",userId);
    	//用户名
    	modelAndView.addObject("userName",map.get("realname"));
    	//其他小组
        modelAndView.addObject("otherGroup",otherGroupList);
        //角色
        modelAndView.addObject("role", groupService.isFounderOrisAdmin(groupId.toString(),userId.toString()));
        //小组用户
        List<PmphGroupMemberVO> gropuMemebers= groupService.listPmphGroupMember(groupId,userId);
        modelAndView.addObject("gropuMemebers",gropuMemebers);
        modelAndView.addObject("gropuMemebersNum",gropuMemebers.size());
        //文件总数
        modelAndView.addObject("fileTotal",groupService.getFilesTotal(groupId, null,userId));
        //页面路径
        modelAndView.setViewName("commuser/mygroup/communication");
        return modelAndView;
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
    public List<GroupFileVO> getFiles(
    		Integer pageNumber,
    		Integer pageSize,
    		@RequestParam(value="groupId")Long  groupId,
    		String  fileName,
    		String order,String rank
    		)  {
    	Map<String, Object> map = this.getUserInfo();
    	Long thisId = new Long (String.valueOf(map.get("id")));
        return groupService.groupFiles(pageNumber,pageSize,groupId,fileName,thisId,order,rank);
    }
    
    /**
     * 获取文件
     * @introduction 
     * @author Mryang
     * @createDate 2017年12月13日 上午10:28:14
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
     * @param groupId
     * @return
     * @throws IOException 
     * @throws CheckedServiceException 
     */
    @ResponseBody
    @RequestMapping(value = "/fileup", method = RequestMethod.POST)
    public String fileup(@RequestParam(value="groupId") Long    groupId,
			    		  @RequestParam(value="fileSize")Long   fileSize,
			    		  @RequestParam(value="fileName")String fileName,
			    		  @RequestParam(value="fileId")  String fileId,
    		HttpServletResponse response,HttpServletRequest request) throws CheckedServiceException, IOException  {
    	Map<String, Object> map = this.getUserInfo();
    	Long thisId = new Long (String.valueOf(map.get("id")));
    	groupService.addFile(groupId,fileId, fileName, fileSize, thisId);
    	return "OK";
    }
    
    @ResponseBody
    @RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
    public String deleteFile(@RequestParam(value="groupId")Long groupId,@RequestParam(value="id")Long id,@RequestParam(value="fileId")String fileId)  {
    	Map<String, Object> map = this.getUserInfo();
    	Long thisId = new Long (String.valueOf(map.get("id")));
        if(!groupService.deleteFileAuthority(groupId.toString(),thisId.toString(),id)){
            return "2";
        }
    	Integer res= groupService.deleteFile(id,groupId,fileId,thisId);
    	if(null != res && 1 == res.intValue()){
    		return "0";
		}else{
			return "1";
		}
    }
    
   
    
    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public void download(@PathVariable("id") String id,Long groupId,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException  {
    	 GridFSDBFile file = fileService.get(id);
         if (null == file) {
            logger.warn("未找到id为'{}'的文件", id);
            return;
         }
         Map<String,Object> filemap=groupService.queryGroupFileByFileId(id);
    	 String userAgent = request.getHeader("User-Agent");  
         String oraFileName = file.getFilename();  
         String formFileName=filemap.get("file_name").toString();  
             
         
         if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {  
        	// 针对IE或者以IE为内核的浏览器：  
             formFileName = java.net.URLEncoder.encode(formFileName, "UTF-8");  
         } else {  
             // 非IE浏览器的处理：  
             formFileName = new String(formFileName.getBytes("UTF-8"), "ISO-8859-1");  
         }  
         response.setHeader("Content-disposition",   String.format("attachment; filename=\"%s\"", formFileName));  
         response.setCharacterEncoding("UTF-8");  
       
         response.setContentType("application/force-download");
        
        try (OutputStream out = response.getOutputStream()) {
        	//更新文件次数
        	groupService.updateDownload(groupId,id);
            file.writeTo(out);
            out.flush();
            out.close();
        } catch (IOException ex) {
            logger.error("文件下载时出现IO异常：{}", ex.getMessage());
        }
    }
    
    @RequestMapping(value="/webSocketSentForIE" ,method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> webSocketSentForIE(HttpServletRequest request){
    	//("{senderId:"+userId+",senderType:"+0+",content:'\""+$("#userName").val()+"\"退出了小组"+"',groupId:"+$("#groupId").val()+",sendType:0}");
    	String senderId = request.getParameter("senderId");
    	String senderType = request.getParameter("senderType");
    	String content = request.getParameter("content");
    	String groupId = request.getParameter("groupId");
    	String sendType = request.getParameter("sendType");
    	
    	Map<String, Object> user = getUserInfo();
    	
    	Map<String,Object> paraMap = new HashMap<String,Object>();
    	paraMap.put("senderId", senderId);
    	paraMap.put("senderType", senderType);
    	paraMap.put("content", content);
    	paraMap.put("groupId", groupId);
    	paraMap.put("sendType", sendType);
    	paraMap.put("logUserId", user.get("id"));
    	
    	Map<String,Object> resultMap = groupService.webSocketSentForIE(paraMap);
    	
		return resultMap;
    }
    
    
    /**
     * 查询成员管理列表
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/getMembers",method=RequestMethod.POST)
    @ResponseBody
    public Map<Object,Object> getMembers(HttpServletRequest request
    		,@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    		,@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
    	Map<Object,Object> resultMap = new HashMap<Object, Object>();
    	Map<String,Object> paraMap = new HashMap<String, Object>();
    	PageParameter<Map<String, Object>> pageParameter = new PageParameter<Map<String, Object>>(pageNum, pageSize);
    	String memberSearchName = request.getParameter("memberSearchName");
    	String groupId = request.getParameter("groupId");
    	Map<String, Object> user = getUserInfo();
    	
    	paraMap.put("groupId", groupId);
    	paraMap.put("memberSearchName", memberSearchName);
    	paraMap.put("logUserId", user.get("id"));
    	pageParameter.setParameter(paraMap);
    	
    	List<Map<String,Object>> mlist = groupService.queryMemberListForManage(pageParameter);
    	int count = groupService.queryMemberListForManageCount(pageParameter);
    	
    	Integer maxPageNum = (int) Math.ceil(1.0 * count / pageSize);
    	resultMap.put("pageNum", pageNum);
    	resultMap.put("maxPageNum", maxPageNum);
    	resultMap.put("mlist", mlist);
    	
    	return resultMap;
    }
    
    /**
     * 修改成员的组内名称 
     * @param request
     * @return
     */
    @RequestMapping(value="/updateDisplayName",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateDisplayName(HttpServletRequest request){
    	Map<String,Object> resultMap = new HashMap<String, Object>();
    	Map<String,Object> paraMap = new HashMap<String, Object>();
    	String groupId = request.getParameter("groupId");
    	String member_id = request.getParameter("member_id");
    	String display_name = request.getParameter("display_name");
    	
    	
    	paraMap.put("member_id", member_id);
    	paraMap.put("display_name", display_name);
    	
    	int count = groupService.updateDisplayName(paraMap);
    	
    	
    	return resultMap;
    }

	/**
	 * 根据ID删除小组成员
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deletePmphGroupMemberById",method=RequestMethod.POST)
	@ResponseBody
	public String deletePmphGroupMemberById(HttpServletRequest request){
		String str=request.getParameter("removememberIds");
		String returncode="";
		List<String> list = JSONArray.fromObject(str);
		for(int i=0;i<list.size();i++){
			returncode=groupService.deletePmphGroupMemberById(list.get(i));
		}
		return returncode;
	 };
    
}






