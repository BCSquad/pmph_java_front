package com.bc.pmpheep.back.commuser.mymessage.controller;

import java.util.HashMap;
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
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.general.controller.BaseController;

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
	@RequestMapping(value = "/tolist", method = RequestMethod.GET)
	public PageResult<MyMessageVO> list(Integer pageSize, Integer pageNumber, Long userId, Integer userType) {
		PageParameter<MyMessageVO> pageParameter = new PageParameter<>(pageNumber, pageSize);
		MyMessageVO myMessageVO = new MyMessageVO();
		myMessageVO.setUserId(userId);
		myMessageVO.setUserType(userType);
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
	@RequestMapping(value = "/todetail", method = RequestMethod.PUT)
	public List<MyMessageVO> detail(Long senderId, Integer senderType) {
		Map<String, Object> writerUser = this.getUserInfo();
		Long userId = new Long(String.valueOf(writerUser.get("id")));
		Integer userType = new Integer(String.valueOf(writerUser.get("userType")));
		List<MyMessageVO> list = myMessageService.updateMyMessage(senderId, senderType, userId, userType);
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

}
