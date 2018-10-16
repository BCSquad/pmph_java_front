package com.bc.pmpheep.back.commuser.teacherPlatform.controller;

import com.bc.pmpheep.general.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//师资平台控制层
@Controller
@RequestMapping("/teacherPlatform")
public class TeacherPlatformController extends BaseController{

    //跳转到师资平台详情页
    @RequestMapping("/todetail")
    public ModelAndView todetail(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("commuser/teacherPlatform/teacherPlatformDetail");
        return modelAndView;
    };

}
