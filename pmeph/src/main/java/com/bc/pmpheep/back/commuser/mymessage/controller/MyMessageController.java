package com.bc.pmpheep.back.commuser.mymessage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.back.commuser.mymessage.service.MyMessageService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.controller.bean.ResponseBean;

/**
 * @author 曾庆峰
 * @CreateDate 2017年11月26日 下午1:50:12
 * 
 **/
@Controller
@RequestMapping("/mymessage")
public class MyMessageController  extends  com.bc.pmpheep.general.controller.BaseController{

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.mymessage.service.MyMessageServiceImpl")
    MyMessageService myMessageService;

    /**
     * 
     * 
     * 功能描述：获取我的消息列表
     * 
     * @param pageSize 当页条数
     * @param pageNumber 当前页码
     * @param title 标题
     * @param isRead 是否已读
     * @param userId 用户id
     * @param userType 用户类型
     * @return
     * 
     */
    @RequestMapping(value = "/tolist", method = RequestMethod.GET)
    public ModelAndView list(Integer pageSize, Integer pageNumber, Long userId, Integer userType,
    String title, Boolean isRead) {
        PageParameter<MyMessageVO> pageParameter = new PageParameter<>(pageNumber, pageSize);
        MyMessageVO myMessageVO = new MyMessageVO();
        myMessageVO.setUserId(userId);
        myMessageVO.setUserType(userType);
        myMessageVO.setTitle(title);
        myMessageVO.setIsRead(isRead);
        pageParameter.setParameter(myMessageVO);
        Map<String, ResponseBean<MyMessageVO>> map = new HashMap<>();
        map.put("mymessage", new ResponseBean(myMessageService.listMyMessage(pageParameter)));
        return new ModelAndView("commuser/mymessage/list", map);
    }

    /**
     * 
     * 
     * 功能描述：获取前几条未读消息
     * 
     * @param pageSize 前几条消息
     * @param pageNumber 当前页码
     * @param userId 用户id
     * @param userType 用户类型
     * @return
     * 
     */
    @RequestMapping(value = "/toicon", method = RequestMethod.GET)
    public ModelAndView icon(Integer pageSize, Integer pageNumber, Long userId, Integer userType) {
        PageParameter<MyMessageVO> pageParameter = new PageParameter<>(pageNumber, pageSize);
        MyMessageVO myMessageVO = new MyMessageVO();
        myMessageVO.setUserId(userId);
        myMessageVO.setUserType(userType);
        myMessageVO.setIsRead(false);
        pageParameter.setParameter(myMessageVO);
        Map<String, ResponseBean<MyMessageVO>> map = new HashMap<>();
        map.put("mymessage", new ResponseBean(myMessageService.listMyMessageOfIcon(pageParameter)));
        return new ModelAndView("commuser/mymessage/icon", map);
    }

    /**
     * 
     * 
     * 功能描述： 获取消息详情
     * 
     * @param id 消息id
     * @return
     * 
     */
    @RequestMapping(value = "/todetail", method = RequestMethod.PUT)
    public ModelAndView detail(Long id) {
        Map<String, ResponseBean<MyMessageVO>> map = new HashMap<>();
        map.put("mymessage", new ResponseBean(myMessageService.updateMyMessageDetail(id)));
        return new ModelAndView("commuser/mymessage/detail", map);
    }

}
