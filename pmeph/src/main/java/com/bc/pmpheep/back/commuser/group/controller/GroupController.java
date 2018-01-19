package com.bc.pmpheep.back.commuser.group.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        model.setViewName("commuser/mygroup/groupList");
        model.addObject("listgroup", lst);
        model.addObject("listSize", lst.size());
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
    	//没有当前小组的权限
    	if(null == thisGroup){
    		modelAndView.setViewName("/comm/error");
            return modelAndView;
    	}
    	//当前小组
    	modelAndView.addObject("thisGroup",thisGroup);
    	//用户id
    	modelAndView.addObject("userId",userId);
    	//其他小组
        modelAndView.addObject("otherGroup",otherGroupList);
        //角色
        modelAndView.addObject("role", groupService.isFounderOrisAdmin(groupId.toString(),userId.toString())?"你是这个小组的管理员":"你是这个小组的普通用户");
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
    public Boolean deleteFile(@RequestParam(value="groupId")Long groupId,@RequestParam(value="id")Long id,@RequestParam(value="fileId")String fileId)  {
    	Map<String, Object> map = this.getUserInfo();
    	Long thisId = new Long (String.valueOf(map.get("id")));
    	Integer res= groupService.deleteFile(id,groupId,fileId,thisId);
    	if(null != res && 1 == res.intValue()){
    		return true;
		}else{
			return false;
		}
    }
    
   
    
    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public void download(@PathVariable("id") String id,Long groupId,HttpServletResponse response) {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/force-download");
        GridFSDBFile file = fileService.get(id);
        if (null == file) {
            logger.warn("未找到id为'{}'的文件", id);
            return;
        }
        response.setHeader("Content-Disposition", "attachment;fileName=\" "+ file.getFilename()+"\"");
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
}






