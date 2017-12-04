package com.bc.pmpheep.back.commuser.myfriend.controll;


import com.bc.pmpheep.back.commuser.myfriend.service.MyFriendService;
import com.bc.pmpheep.back.util.getRequestParamsUtil;
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
@RequestMapping("/myFriend")
@Controller
public class MyFriendControl extends getRequestParamsUtil {

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.myfriend.service.MyFriendServiceImpl")
    private MyFriendService myFriendService;
    @RequestMapping("/toMyFriend")
    public ModelAndView toMyFriend(){
        Map<String,Object> map=new HashMap<String, Object>();
        //初始化 通过session去获取到参数  待写
        List<Map<String,Object>> myFriendsInfo = myFriendService.getMyFriends("12179");
        map.put("myFriendsInfo",myFriendsInfo);
        return new ModelAndView("/commuser/myFriend/myFriend",map);

    }

}
