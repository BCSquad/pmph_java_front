package com.bc.pmpheep.back.authadmin.message.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.pmpheep.back.authadmin.message.service.SendMessageService;
import com.bc.pmpheep.general.service.SensitiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.authadmin.message.bean.UserMessage;
import com.bc.pmpheep.back.authadmin.message.service.InfoReleaseService;
import com.bc.pmpheep.back.authadmin.message.service.SendMessageServiceImpl;
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.general.bean.FileType;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.FileService;
import com.bc.pmpheep.general.service.MessageService;

@RequestMapping("/authSendMessage")
@Controller
public class SendMessage extends BaseController {
	
	@Autowired
	MessageService mssageService;
	@Autowired
    @Qualifier("com.bc.pmpheep.general.service.FileService")
    FileService fileService;
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.message.service.SendMessageServiceImpl")
	private SendMessageService sendMessageService;
	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.message.service.InfoReleaseServiceImpl")
	InfoReleaseService infoReleaseService;
	Logger logger = LoggerFactory.getLogger(SendMessage.class);
	@Autowired
	@Qualifier("com.bc.pmpheep.general.service.SensitiveService")
	SensitiveService sensitiveService;
	
	/**
	 * 发送新消息——机构用户 页面
	 * @return
	 */
	@RequestMapping("/init")
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		Map<String, Object> user = this.getUserInfo();
		BigInteger uid = (BigInteger) user.get("id");//用户的id
		List<Map<String, Object>> List_map = infoReleaseService.selectMenu(uid);
//		mv.addObject("resultFlag", "2");
//		mv.addObject("titleValue", "");
//		mv.addObject("UEContent", "");
		mv.addObject("listMenu", List_map);
		mv.setViewName("/authadmin/message/sendmessage");
		return mv;
		
	}
	
	/**
	 * 
	* @Title: showSelectBook 
	* @Description: 初始化 选择教材报名者界面
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	/*@RequestMapping(value="showSelectBook",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String,Object>> showSelectBook(){
		List<Map<String, Object>> List_map = infoReleaseService.selectMenu();
		return List_map;
	}
*/
	/**
	 * 机构用户发送新消息 （批量）
	 * @return
	 */
	
	@RequestMapping(value="/sendMessage",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> sendMessage(HttpServletRequest request/*,@RequestParam(value="file",required=false)MultipartFile file*/,HttpServletResponse response){
		
		String resultFlag = "true";
		//String fileName = file.getOriginalFilename(); //文件名
		//long fileSize = file.getSize(); //文件大小
		Map<String, Object> user = this.getUserInfo();
		BigInteger uid = (BigInteger) user.get("id");//用户的id
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("userId", uid);
		String titleValue = request.getParameter("titleValue");
		String radioValue = request.getParameter("radioValue");
		String UEContent = request.getParameter("UEContent");
		String fileName = request.getParameter("messageFileName");
		String attachment_id = request.getParameter("messageFileId");
		ModelAndView mv = new ModelAndView();
		String address = "/authadmin/message/sendmessage";
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String flag = "0";
		if(titleValue.length()==0||UEContent.length()==0){
			resultMap.put("isValidate","必填项不为空");
			resultMap.put("flag","2");
			resultMap.put("titleValue",titleValue);
			resultMap.put("UEContent",UEContent);
			return resultMap;
		}else if(titleValue.length()>30){
			resultMap.put("isValidate","标题不能超过30个字");
			resultMap.put("flag","3");
			resultMap.put("titleValue",titleValue);
			resultMap.put("UEContent",UEContent);
			return resultMap;
		}

		if (sensitiveService.confirmSensitive(titleValue) || sensitiveService.confirmSensitive(UEContent)){
			List<String> sensitives = sensitiveService.getSensitives(titleValue, UEContent);
			resultMap.put("flag", "4");
			resultMap.put("value", sensitives);
			resultMap.put("UEContent", sensitiveService.delHTMLTag(UEContent));
			return resultMap;
		}
		//发送消息 到MongoDB 
		Message message = new Message();
		message.setContent(UEContent);
		Message messageResult = mssageService.add(message);
		String msg_id = messageResult.getId();
		
		
		List<Map<String,Object>> userIdList = null;
		List<UserMessage> userMessageList = new ArrayList<UserMessage>();
		//获取接受者的id
		if("0".equals(radioValue)){
			userIdList = sendMessageService.findOrgUserAndWriterUser(paraMap);
			for(int i =0;i<userIdList.size();i++){
				UserMessage userMessage = new UserMessage();
				userMessage.setMsg_id(msg_id);
				userMessage.setMsg_type(1);
				userMessage.setReceiver_id(((BigInteger)userIdList.get(i).get("receiver_id")).longValue());
				userMessage.setReceiver_type(2);
				userMessage.setSender_id(uid);
				userMessage.setSender_type(3);
				userMessage.setTitle(titleValue);
				userMessageList.add(userMessage);
				
			}
		}else{
			String[] partSelect =  null;
			if(radioValue==null||"".equals(radioValue)){
				partSelect = new String[]{};
			}else{
				partSelect = radioValue.split(",");
			}

			for(int i =0;i<partSelect.length;i++){
				userIdList =sendMessageService.findApplyId(partSelect[i]);
				for(int j =0;j<userIdList.size();j++) {
					UserMessage userMessage = new UserMessage();
					userMessage.setMsg_id(msg_id);
					userMessage.setMsg_type(1);
					userMessage.setReceiver_id(((BigInteger)userIdList.get(j).get("receiver_id")).longValue());
					userMessage.setReceiver_type(2);
					userMessage.setSender_id(uid);
					userMessage.setSender_type(3);
					userMessage.setTitle(titleValue);
					userMessageList.add(userMessage);
				}
				
			}
		}
			
			try {
				//批量操作
				if(userMessageList.size()<=0){
					flag ="5";
				}else{
					sendMessageService.batchInsertMessage(userMessageList);
				}

				//return new ModelAndView("redirect:/authSendMessage/init.action");
				//文件上传  到MongoDB 
				/*if(fileSize>0){
		           String attachment_id =fileService.save(file, FileType.MSG_FILE, 0);*/
		           Map map = new HashMap();
		           map.put("msg_id", msg_id);
		           map.put("attachment", attachment_id);
		           map.put("attachment_name", fileName);
				sendMessageService.insertAttachmentInfo(map);
				/*}*/
				
				//return new ModelAndView("/authadmin/message/sendmessage");
			} catch (Exception e) {
				// TODO: handle exception
				flag ="1";
				e.printStackTrace();
				resultMap.put("flag", flag);
				return resultMap;
			}


		resultMap.put("flag", flag);
		return resultMap;
	}
	
	
}
