package com.bc.pmpheep.back.commuser.mygroup.controll;



import java.util.Map;
import com.bc.pmpheep.back.commuser.group.service.GroupService;
import com.bc.pmpheep.back.commuser.mygroup.service.MyGroupService;
import com.bc.pmpheep.general.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by cyx  on 2017/11/21
 */
@RequestMapping("/myGroup")
@Controller
public class MyGroupControl extends BaseController{

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.mygroup.service.MyGroupServiceImpl")
    private MyGroupService myGroupService;
    
    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.group.service.GroupServiceImpl")
    private GroupService groupService;
    
    @RequestMapping("/toMyGroup")
    public ModelAndView toMyGroup(@RequestParam(value="groupId")Long groupId){
    	Map<String, Object> map = this.getUserInfo();
    	Long userId = new Long (String.valueOf(map.get("id")));
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("groupId", groupId);
        //角色
        modelAndView.addObject("role", myGroupService.isFounderOrisAdmin(groupId,userId)?"你是这个小组的管理员":"你是这个小组的普通用户");
        //小组用户
        modelAndView.addObject("gropuMemebers",myGroupService.listPmphGroupMember(groupId,userId));
        //其他小组
        modelAndView.addObject("otherGroup",groupService.groupList(0,1000000,userId));
        modelAndView.setViewName("commuser/mygroup/communication");
        return modelAndView;
    }



}
