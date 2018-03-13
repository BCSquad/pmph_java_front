package com.bc.pmpheep.back.commuser.help.controller;

import com.bc.pmpheep.back.commuser.help.service.HelpService;

import com.bc.pmpheep.general.controller.BaseController;

import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/help")
public class HelpController extends BaseController {

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.help.service.HelpServiceImpl")
    private HelpService helpService;

    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/helpList")
    public ModelAndView helpList(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        Map map = new HashMap();

        List<Map<String, Object>> helpList = helpService.queryHelpList(map);
        //帮助手册列表
        List<Map<String, Object>> handbookList = helpService.handbookList(map);
        model.setViewName("comm/helphome");
        model.addObject("helpList", helpList);
        model.addObject("handbookList", handbookList);
        return model;
    }


    @RequestMapping(value = "/helpdetail")
    public ModelAndView detail(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        String id = request.getParameter("id");
        Map map=new HashMap();
        map.put("id",id);
        Map<String, Object> detailMap = helpService.queryDetail(map);
        // mongoDB查询内容
        Content content = contentService.get(detailMap.get("mid").toString());
        String dContent = "";
        if (content == null || "".equals(content)) {
            dContent = "没有内容！";
        } else {
            dContent = content.getContent();
        }
        model.addObject("title",detailMap.get("title").toString());
        model.addObject("dContent",dContent);
        model.addObject("detailMap",detailMap);
        model.setViewName("comm/helpdetail");
        return model;
    }
}

