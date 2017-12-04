package com.bc.pmpheep.back.commuser.mygroup.controll;


import com.bc.pmpheep.back.commuser.myfriend.service.MyFriendService;
import com.bc.pmpheep.back.commuser.mygroup.service.MyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cyx  on 2017/11/21
 */
@RequestMapping("/myGroup")
@Controller
public class MyGroupControl {

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.mygroup.service.MyGroupServiceImpl")
    private MyGroupService myGroupService;
    @RequestMapping("/toMyGroup")
    public ModelAndView toMyGroup(){
        Map<String,Object> map=new HashMap<String, Object>();
        List<Map<String,Object>> myGroupsInfo = myGroupService.getMyGroups("12179");
        map.put("myGroupsInfo",myGroupsInfo);
        return new ModelAndView("/commuser/mygroup/mygroup",map);
    }



}
