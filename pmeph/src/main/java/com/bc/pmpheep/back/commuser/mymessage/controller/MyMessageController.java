package com.bc.pmpheep.back.commuser.mymessage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

/**
 * @author 曾庆峰
 * @CreateDate 2017年11月26日 下午1:50:12
 * 
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
		modelAndView.setViewName("commuser/mymessage/personnelMessage");
		return modelAndView;
	}

	/**
	 * 
	 * 
	 * 功能描述：获取我的消息列表
	 * 
	 * @param pageSize
	 *            当页条数
	 * @param pageNumber
	 *            当前页码
	 * @param title
	 *            标题
	 * @param isRead
	 *            是否已读
	 * @param userId
	 *            用户id
	 * @param userType
	 *            用户类型
	 * @return
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/tolist", method = RequestMethod.GET)
	public PageResult<MyMessageVO> list(Integer pageSize, Integer pageNumber, Boolean isRead) {
		Map<String, Object> writerUser = this.getUserInfo();
		Long userId = new Long(String.valueOf(writerUser.get("id")));
		PageParameter<MyMessageVO> pageParameter = new PageParameter<>(pageNumber, pageSize);
		MyMessageVO myMessageVO = new MyMessageVO();
		myMessageVO.setUserId(userId);
		myMessageVO.setUserType(2);
		myMessageVO.setIsRead(isRead);
		pageParameter.setParameter(myMessageVO);
		return myMessageService.listMyMessage(pageParameter);
	}

	/**
	 * 
	 * 
	 * 功能描述： 获取消息详情
	 * 
	 * @param id
	 *            消息id
	 * @return
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/todetail", method = RequestMethod.GET)
	public List<MyMessageVO> detail(Long senderId, Integer senderType, Long receiverId, Integer receiverType) {
		Map<String, Object> writerUser = this.getUserInfo();
		Long userId = new Long(String.valueOf(writerUser.get("id")));
		List<MyMessageVO> list = new ArrayList<>();
		if (senderId.equals(userId) && senderType == 2) {
			list = myMessageService.updateMyMessage(senderId, senderType, userId, 2);
		}else {
			list = myMessageService.updateMyMessage(receiverId, receiverType, userId, 2);
		}

		return list;
	}

	/**
	 * 获取我和朋友的对话记录
	 * 
	 * @introduction
	 * @author Mryang
	 * @createDate 2017年12月7日 下午2:30:29
	 * @param friendId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getDialogue", method = RequestMethod.GET)
	public List<DialogueVO> getDialogue(@RequestParam(value = "friendId") Long friendId) {
		Map<String, Object> writerUser = this.getUserInfo();
		Long thisId = new Long(String.valueOf(writerUser.get("id")));
		List<DialogueVO> lst = myMessageService.findMyDialogue(thisId, friendId);
		return lst;
	}

	@ResponseBody
	@RequestMapping(value = "/senNewMsg", method = RequestMethod.GET)
	public String senNewMsg(@RequestParam(value = "friendId") Long friendId, Short friendIdType,
			@RequestParam(value = "title") String title, @RequestParam(value = "content") String content) {
		Map<String, Object> writerUser = this.getUserInfo();
		Long thisId = new Long(String.valueOf(writerUser.get("id")));
		myMessageService.senNewMsg(thisId, friendId, friendIdType, title, content);
		return "success";
	}

}
