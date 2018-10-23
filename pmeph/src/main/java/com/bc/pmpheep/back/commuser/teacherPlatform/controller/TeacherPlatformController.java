package com.bc.pmpheep.back.commuser.teacherPlatform.controller;

import com.bc.pmpheep.back.commuser.teacherPlatform.service.TeacherPlatformService;
import com.bc.pmpheep.general.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//师资平台控制层
@Controller
@RequestMapping("/teacherPlatform")
public class TeacherPlatformController extends BaseController{

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.teacherPlatform.service.TeacherPlatformServiceImpl")
    private TeacherPlatformService teacherPlatformService;

    //跳转到师资平台详情页
    @RequestMapping("/todetail")
    public ModelAndView todetail(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        //信息快报ID
        String id=request.getParameter("id");
        //活动ID
        String activity_id=request.getParameter("activity_id");
        Map<String,Object> voideomap = new HashMap<>();
        voideomap.put("id",activity_id);
        voideomap.put("startrow",0);
        voideomap.put("endrow",6);
        Map<String,Object> sourcemap = new HashMap<>();
        sourcemap.put("id",activity_id);
        sourcemap.put("startrow",0);
        sourcemap.put("endrow",5);
        Map<String,Object> map = teacherPlatformService.queryXikb(id);
        List<Map<String,Object>> voideolist=teacherPlatformService.Queryvideo(voideomap);
        List<Map<String,Object>> sourcelist=teacherPlatformService.Querysource(sourcemap);
        modelAndView.addObject("map",map);
        modelAndView.addObject("voideolist",voideolist);
        modelAndView.addObject("sourcelist",sourcelist);
        modelAndView.addObject("activity_id",activity_id);
        modelAndView.setViewName("commuser/teacherPlatform/teacherPlatformDetail");
        return modelAndView;
    };

    //跳转到师资平台视频列表
    @RequestMapping("/videotolist")
    public ModelAndView tolist(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        String id=request.getParameter("id");
        String startrow=request.getParameter("startrow");
        String endrow=request.getParameter("endrow");
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("startrow",startrow);
        map.put("endrow",endrow);
        List<Map<String,Object>> list=teacherPlatformService.Queryvideo(map);
        //查询总数
        int count =teacherPlatformService.VideoCount(id);
        int pagesize=0;
        if(count<=Integer.parseInt(endrow)){
            pagesize=1;
        }else{
            int t=count%Integer.parseInt(endrow);
            if(t==0){
                pagesize=t;
            }else{
                pagesize=t+1;
            }
        }
        modelAndView.addObject("id",id);
        modelAndView.addObject("list",list);
        modelAndView.addObject("startrow",startrow);
        modelAndView.addObject("endrow",endrow);
        modelAndView.addObject("pagesize",pagesize);
        modelAndView.setViewName("commuser/teacherPlatform/teacherPlatformVideoList");
        return modelAndView;
    };

    //跳转到相关资源列表
    @RequestMapping("/tosourcelist")
    public ModelAndView tosourcelist(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        String id=request.getParameter("id");
        String startrow=request.getParameter("startrow");
        String endrow=request.getParameter("endrow");
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("startrow",startrow);
        map.put("endrow",endrow);
        List<Map<String,Object>> list = teacherPlatformService.Querysource(map);
        //查询总数
        int count=teacherPlatformService.sourceCount(id);
        int pagesize=0;
        if(count<=Integer.parseInt(endrow)){
            pagesize=1;
        }else{
            int t=count%Integer.parseInt(endrow);
            if(t==0){
                pagesize=t;
            }else{
                pagesize=t+1;
            }
        }
        modelAndView.addObject("id",id);
        modelAndView.addObject("list",list);
        modelAndView.addObject("startrow",startrow);
        modelAndView.addObject("endrow",endrow);
        modelAndView.addObject("pagesize",pagesize);
        modelAndView.setViewName("commuser/teacherPlatform/relatedresourceslist");
        return modelAndView;
    };

    //跳转到师资平台列表页面
    @RequestMapping("szpt")
    public ModelAndView szpt(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("commuser/teacherPlatform/teacherPlatformSourceList");
        return modelAndView;
    }

    //师资平台列表查询方法
    @RequestMapping("/sourcelist")
    @ResponseBody
    public List<Map<String,Object>> sourcelist(HttpServletRequest request){
        String startrow=request.getParameter("startrow");
        List<Map<String,Object>> list = teacherPlatformService.QuerySourceList(startrow);
        return list;
    }

    //增加播放次数
    @RequestMapping("updateclicks")
    @ResponseBody
    public String updateclicks(HttpServletRequest request){
        String clicks=request.getParameter("clicks");
        String id=request.getParameter("id");
        String returncode="";
        if(!clicks.equals("")){
            int t=Integer.parseInt(clicks);
            int count = teacherPlatformService.updateclicks(t+1,id);
            if(count>0){
                returncode="播放次数增加成功";
            }
        }else{
            returncode="播放次数增加失败，获取数据为空";
        }
        return returncode;
    };
}
