package com.bc.pmpheep.back.commuser.group.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.group.bean.GroupList;
import com.bc.pmpheep.back.commuser.group.service.GroupService;


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
        List<GroupList> lst=groupService.groupList((pageNumber-1)*pageSize, pageSize, userId) ;
        model.setViewName("commuser/mygroup/groupList");
        model.addObject("listgroup", lst);
        return model;
    }
    
    @RequestMapping("/toMyGroup")
    public ModelAndView toMyGroup(@RequestParam(value="groupId")Long groupId){
    	Map<String, Object> map = this.getUserInfo();
    	Long userId = new Long (String.valueOf(map.get("id")));
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("groupId", groupId);
        //角色
        modelAndView.addObject("role", groupService.isFounderOrisAdmin(groupId,userId)?"你是这个小组的管理员":"你是这个小组的普通用户");
        //小组用户
        modelAndView.addObject("gropuMemebers",groupService.listPmphGroupMember(groupId,userId));
        //其他小组
        modelAndView.addObject("otherGroup",groupService.groupList(0,1000000,userId));
        modelAndView.setViewName("commuser/mygroup/communication");
        return modelAndView;
    }
}






