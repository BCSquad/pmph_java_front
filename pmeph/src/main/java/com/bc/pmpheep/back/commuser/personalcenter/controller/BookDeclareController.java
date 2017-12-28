package com.bc.pmpheep.back.commuser.personalcenter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.personalcenter.service.BookDeclareService;
import com.bc.pmpheep.general.controller.BaseController;
/**
 * 我要出书-申报
 * @author hqf
 * @timer 2017/12/26
 */
@Controller
@RequestMapping("/bookdeclare/")
public class BookDeclareController extends BaseController{
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.BookDeclareService")
	private BookDeclareService bdecService;
	
	/**
	 * 跳转到申报页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toBookdeclareAdd")
	public ModelAndView toBookdeclareAdd(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView mav= new ModelAndView("commuser/personalcenter/toBookdeclareAdd");
		Map<String,Object> userMap =  this.getUserInfo();
		mav.addObject("userMap", userMap);
		return mav;
	}
	
	/**
	 * 申报添加
	 */
	@RequestMapping("doBookdeclareAdd")
	public String doBookdeclareAdd(HttpServletRequest request,
			HttpServletResponse response){
		//创建一个唯一标识
		String ssid = UUID.randomUUID().toString().substring(0, 28);
		String stype = request.getParameter("stype"); //申报信息存储方式
		//获取申报信息
		Map<String,Object> topicMap = new HashMap<String,Object>();
		String msg = "";
		if(stype.equals("1")){ //表示提交
			topicMap.put("is_staging", "0");
			topicMap.put("auth_progress", "1");
		}else{//2 表示暂存
			topicMap.put("is_staging","1");
			topicMap.put("auth_progress","0");
		}
		topicMap.put("bookname", request.getParameter("bookname"));
		topicMap.put("reader", request.getParameter("reader"));
		topicMap.put("user_id", request.getParameter("user_id"));
		topicMap.put("deadline", request.getParameter("deadline"));
		topicMap.put("source", request.getParameter("source"));
		topicMap.put("word_number", request.getParameter("word_number"));
		topicMap.put("picture_number", request.getParameter("picture_number"));
		topicMap.put("subject", request.getParameter("subject"));
		topicMap.put("rank", request.getParameter("rank"));
		topicMap.put("type", request.getParameter("type"));
		topicMap.put("bank_account_id", request.getParameter("bank_account_id"));
		topicMap.put("purchase", request.getParameter("purchase"));
		topicMap.put("sponsorship", request.getParameter("sponsorship"));
		topicMap.put("original_bookname", request.getParameter("original_bookname"));
		//判断是否为翻译书稿，若有值则表示为翻译书籍
		if(request.getParameter("original_bookname").toString().equals("")){
			topicMap.put("is_translation","0"); //表示原作
		}else{
			topicMap.put("is_translation","1"); //表示翻作
		}
		topicMap.put("original_author", request.getParameter("original_author"));
		topicMap.put("nation", request.getParameter("nation"));
		topicMap.put("edition", request.getParameter("edition"));
		topicMap.put("vn",ssid);
		int count = this.bdecService.insertTopic(topicMap);
		if(count>0){
			List<Map<String,Object>> topicLsit = this.bdecService.queryTopic(topicMap);
			String topic_id = topicLsit.get(0).get("id").toString();
			//选题申报额外信息topic_extra
			Map<String,Object> extraMap = new HashMap<String,Object>();
			extraMap.put("reason", request.getParameter("reason"));
			extraMap.put("price", request.getParameter("price"));
			extraMap.put("score", request.getParameter("extra_score"));
			extraMap.put("topic_id", topic_id);
			this.bdecService.insertTopicExtra(extraMap);
			//申报编者情况
			String[] realnames = request.getParameterValues("write_realname");
			String[] sexs = request.getParameterValues("sex");
			String[] prices = request.getParameterValues("write_price");
			String[] positions = request.getParameterValues("write_position");
			String[] workplaces = request.getParameterValues("workplace");
			for(int i=0;i<realnames.length;i++) { //遍历数组
				if(!realnames[i].equals("")){
					Map<String,Object> writeMap = new HashMap<String,Object>();
					writeMap.put("topic_id", topic_id);
					writeMap.put("realname", realnames[i]);
					writeMap.put("sex", sexs[i]);
					writeMap.put("price", prices[i]);
					writeMap.put("position", positions[i]);
					writeMap.put("workplace", workplaces[i]);
					this.bdecService.insertTopicWriter(writeMap);
					msg = "OK";
				}
			}
		}
		return msg;
	}
	
	/**
	 * 跳转到暂存页面
	 */
	@RequestMapping("toBookdeclareZc")
	public ModelAndView toBookdeclareZc(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView mav= new ModelAndView("commuser/personalcenter/toBookdeclareZc");
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("topic_id", request.getParameter("topic_id"));
		List<Map<String,Object>> topicList = this.bdecService.queryTopic(queryMap);
		List<Map<String,Object>> textraList = this.bdecService.queryTopicExtra(queryMap);
		List<Map<String,Object>> twriteList = this.bdecService.queryTopicWriter(queryMap);
		
		mav.addObject("topicList", topicList);
		mav.addObject("textraList", textraList);
		mav.addObject("twriteList", twriteList);
		
		return mav;
	}
}
