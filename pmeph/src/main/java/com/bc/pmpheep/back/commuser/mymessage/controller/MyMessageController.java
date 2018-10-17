package com.bc.pmpheep.back.commuser.mymessage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bc.pmpheep.back.util.Const;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.mymessage.bean.DialogueVO;
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.back.commuser.mymessage.service.MyMessageService;


/**
 * @author 曾庆峰
 * @CreateDate 2017年11月26日 下午1:50:12
 **/
@Controller
@RequestMapping("/mymessage")
public class MyMessageController extends com.bc.pmpheep.general.controller.BaseController {
    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.mymessage.service.MyMessageServiceImpl")
    MyMessageService myMessageService;

    @RequestMapping(value = "/listMyMessage", method = RequestMethod.GET)
    public ModelAndView listMyFriend() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> writerUser = this.getUserInfo();
        Long userId = new Long(String.valueOf(writerUser.get("id")));
        List<MyMessageVO> list = myMessageService.listMyMessage(1, 999999, "noreade", userId);
        int no_read_count = list.size();
        modelAndView.addObject("no_read_count",no_read_count);
        modelAndView.setViewName("commuser/mymessage/personnelMessage");
        return modelAndView;
    }

    /**
     * 功能描述：获取我的消息列表
     *
     * @param pageSize   当页条数
     * @param pageNumber 当前页码
     * @param title      标题
     * @param isRead     是否已读
     * @param userId     用户id
     * @param userType   用户类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tolist", method = RequestMethod.GET)
    public List<MyMessageVO> list(Integer pageSize, Integer pageNumber, String state) {
        Map<String, Object> writerUser = this.getUserInfo();
        Long userId = new Long(String.valueOf(writerUser.get("id")));
        //Long userId =  new Long(1456);
        List<MyMessageVO> list = myMessageService.listMyMessage(pageNumber, pageSize, state, userId);
         /*for(MyMessageVO message:list){
	         	if("/image/DEFAULT.action".equals(message.getAvatar().toString())){
	         		message.setAvatar("statics/pictures/head.png");
	 			}else{
	 				message.setAvatar("file/download"+message.getAvatar().replace("/image/","/"));
	 			}
	         }*/
        return list;
    }

    /**
     * 功能描述： 获取消息详情
     *
     * @param senderId
     * @param senderType
     * @param receiverId
     * @param receiverType
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/todetail", method = RequestMethod.GET)
    public List<MyMessageVO> detail(Long senderId, Integer senderType, Long receiverId, Integer receiverType) {
        Map<String, Object> writerUser = this.getUserInfo();
        //Long userId = new Long(String.valueOf(writerUser.get("id")));
        Long userId = new Long(24967);
        List<MyMessageVO> list = new ArrayList<>();
        if (senderId.equals(userId) && senderType == 2) {
            list = myMessageService.updateMyMessage(senderId, senderType, userId, 2);
        } else {
            list = myMessageService.updateMyMessage(receiverId, receiverType, userId, 2);
        }

        return list;
    }

    /**
     * 获取我和朋友的对话记录
     *
     * @param friendId
     * @return
     * @introduction
     * @author Mryang
     * @createDate 2017年12月7日 下午2:30:29
     */
    @ResponseBody
    @RequestMapping(value = "/getDialogue", method = RequestMethod.GET)
    public List<DialogueVO> getDialogue(@RequestParam(value = "friendId") Long friendId, Integer friendType) {
        Map<String, Object> writerUser = this.getUserInfo();
        Long thisId = new Long(String.valueOf(writerUser.get("id")));
        List<DialogueVO> lst = myMessageService.findMyDialogue(thisId, friendId, friendType);
		 /*for(DialogueVO dialogueVO:lst){
         	if("/image/DEFAULT.action".equals(dialogueVO.getAvatar())){
         		dialogueVO.setAvatar("statics/pictures/head.png");
 			}else{
 				dialogueVO.setAvatar("file/download"+dialogueVO.getAvatar().replace("/image/","/"));
 			}
         }*/
        return lst;
    }

    @ResponseBody
    @RequestMapping(value = "/senNewMsg", method = RequestMethod.POST)
    public Map<String, Object> senNewMsg(HttpServletRequest request) {
        Map<String, Object> writerUser = this.getUserInfo();
        String idStr = request.getParameter("friendId");
        Long friendId = 0L;
        if (null != idStr && !idStr.equals("")) {
            friendId = new Long(idStr);
        }

        String title = request.getParameter("title");
        String typeStr = request.getParameter("friendIdType");
        Short friendIdType = null;
        if (null != typeStr && !typeStr.equals("")) {
            friendIdType = new Short(typeStr);
        }

        String content = request.getParameter("content");
        Long thisId = new Long(String.valueOf(writerUser.get("id")));
        String name = (String) writerUser.get("realname");
        //Long thisId = new Long(24967);
        //String name = "测试";
        String avatar = "static/default_image.png";

        HttpSession session = request.getSession();
        int sendType = "1".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE)) ? 2 : 3;

        myMessageService.senNewMsg(thisId, (short) sendType, friendId, friendIdType, title, content);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "success");
        map.put("name", name);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/updateMyTalk", method = RequestMethod.GET)
    public String senNewMsg(@RequestParam(value = "senderId") Long senderId, @RequestParam(value = "senderType") Short senderType, HttpServletRequest request) {
        Map<String, Object> writerUser = this.getUserInfo();
        Long thisId = new Long(String.valueOf(writerUser.get("id")));
		/*Long thisId = new Long(24967);*/
        HttpSession session = request.getSession();
        int receiverType = "1".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE)) ? 2 : 3;
        myMessageService.updateMyTalk(senderId, senderType, thisId, (short) receiverType);
        return "success";
    }



}
