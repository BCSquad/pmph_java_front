package com.bc.pmpheep.back.commuser.group.controller;


import java.io.IOException;
import java.util.ArrayList;
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
import com.bc.pmpheep.back.commuser.group.bean.PmphGroupMemberVO;
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
    public ModelAndView listMyGroup(Integer pageNumber,Integer pageSize )  {
    	Map<String, Object> map = this.getUserInfo();
    	Long userId =  Long.parseLong(map.get("id").toString()) ;
        //Long userId = 24975L;
    	if(null == pageNumber || pageNumber < 1){
    		pageNumber = 1 ;
    	}
    	if(null == pageSize   || pageSize < 1){
    		pageSize = 100000 ;  //默认100000,差不多就是查询全部
    	}
        ModelAndView model = new ModelAndView();
        // 获取用户
        Map<String, Object> writerUserMap = this.getUserInfo();
        
        List<GroupList> lst=groupService.groupList((pageNumber-1)*pageSize, pageSize, userId) ;
        model.setViewName("commuser/mygroup/groupList");
        model.addObject("listgroup", lst);
        model.addObject("listSize", lst.size());
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
    	List<GroupList> myGroupList     = groupService.groupList(0,1000000,userId);
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
        modelAndView.addObject("role", groupService.isFounderOrisAdmin(groupId,userId)?"你是这个小组的管理员":"你是这个小组的普通用户");
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
    public List<GroupFileVO> getFiles(Integer pageNumber,Integer pageSize,@RequestParam(value="groupId")Long  groupId,String  fileName)  {
    	Map<String, Object> map = this.getUserInfo();
    	Long thisId = new Long (String.valueOf(map.get("id")));
        return groupService.groupFiles(pageNumber,pageSize,groupId,fileName,thisId);
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
}






