package com.bc.pmpheep.back.commuser.personalcenter.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.personalcenter.service.BookDeclareService;
import com.bc.pmpheep.back.util.StringUtil;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * 我要出书-申报
 * 
 * @author hqf
 * @timer 2017/12/26
 */
@Controller
@RequestMapping("/bookdeclare/")
public class BookDeclareController extends BaseController {

	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.BookDeclareService")
	private BookDeclareService bdecService;

	/**
	 * 跳转到申报页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toBookdeclareAdd")
	public ModelAndView toBookdeclareAdd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("commuser/personalcenter/toBookdeclareAdd");
		Map<String, Object> userMap = this.getUserInfo();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("user_id", userMap.get("id"));
		mav.addObject("userMap", userMap);
		return mav;
	}

	/**
	 * 申报添加
	 */
	@RequestMapping("doBookdeclareAdd")
	@ResponseBody
	public String doBookdeclareAdd(HttpServletRequest request, HttpServletResponse response) {
		String msg = "";
		// 创建一个唯一标识
		String editionnum = "10" + new SimpleDateFormat("yyyy").format(new Date());
		String vn = bdecService.getMaxTopicVn();
		if (StringUtil.isEmpty(vn)) {
			vn = "000001";
		} else {
			vn = Integer.parseInt(vn.substring(7)) + 1000000 + 1 + "";
		}
		String stype = request.getParameter("stype"); // 申报信息存储方式
		//int类型
		String word_number = request.getParameter("word_number");
		String picture_number = request.getParameter("picture_number");
		String purchase = request.getParameter("purchase");
		String sponsorship = request.getParameter("sponsorship");
		if(word_number.equals("")){
			word_number = "0";
		}
		if(picture_number.equals("")){
			picture_number = "0";
		}
		if(purchase.equals("")){
			purchase = "0";
		}
		if(sponsorship.equals("")){
			sponsorship = "0";
		}
		
		// 插入银行信息
		Map<String, Object> BankMap = new HashMap<String, Object>();
		BankMap.put("user_id", request.getParameter("user_id"));
		BankMap.put("account_name", request.getParameter("realname"));
		BankMap.put("account_number", request.getParameter("bank"));
		BankMap.put("bank", request.getParameter("account_number"));
		int bankCount = this.bdecService.insertBank(BankMap);
		if (bankCount > 0) {
			List<Map<String, Object>> BankList = new ArrayList<Map<String, Object>>();
			BankList = this.bdecService.queryBank(BankMap);
			String bankid = "";
			if (BankList.size() > 0) {
				bankid = BankList.get(0).get("id").toString();
			}
			// 获取申报信息
			Map<String, Object> topicMap = new HashMap<String, Object>();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String currentDate = df.format(new Date());
			if (stype.equals("1")) { // 表示提交
				topicMap.put("is_staging", "0");
				topicMap.put("auth_progress", "1");
				topicMap.put("is_opts_handling", "1");
				topicMap.put("submit_time", currentDate);
			} else {// 2 表示暂存
				topicMap.put("is_staging", "1");
				topicMap.put("auth_progress", "0");
				topicMap.put("is_opts_handling", "0");
				topicMap.put("submit_time", "");
			}
			topicMap.put("bookname", request.getParameter("bookname"));
			topicMap.put("reader", request.getParameter("reader"));
			topicMap.put("user_id", request.getParameter("user_id"));
			topicMap.put("deadline", request.getParameter("deadline"));
			topicMap.put("source", request.getParameter("source"));
			topicMap.put("word_number", word_number);
			topicMap.put("picture_number", picture_number);
			topicMap.put("subject", request.getParameter("subject"));
			topicMap.put("rank", request.getParameter("rank"));
			topicMap.put("type", request.getParameter("type"));
			topicMap.put("bank_account_id", bankid);
			topicMap.put("purchase", purchase);
			topicMap.put("sponsorship", sponsorship);
		//	topicMap.put("purchase",
		//			"".equals(request.getParameter("purchase")) ? null : request.getParameter("purchase"));
		//	topicMap.put("sponsorship",
		//			"".equals(request.getParameter("sponsorship")) ? null : request.getParameter("sponsorship"));
			topicMap.put("original_bookname", request.getParameter("original_bookname"));
			// 判断是否为翻译书稿，若有值则表示为翻译书籍
			if (request.getParameter("original_bookname").toString().equals("")) {
				topicMap.put("is_translation", "0"); // 表示原作
			} else {
				topicMap.put("is_translation", "1"); // 表示翻作
			}
			topicMap.put("original_author", request.getParameter("original_author"));
			topicMap.put("nation", request.getParameter("nation"));
			topicMap.put("edition", request.getParameter("edition"));
			topicMap.put("vn", editionnum + vn);
			int count = this.bdecService.insertTopic(topicMap);
			if (count > 0) {
				Map<String, Object> topicLsit = this.bdecService.queryTopic(topicMap);
				String topic_id = topicLsit.get("id").toString();
				// 选题申报额外信息topic_extra
				Map<String, Object> extraMap = new HashMap<String, Object>();
				extraMap.put("reason", request.getParameter("reason"));
				extraMap.put("price", request.getParameter("price"));
				extraMap.put("score", request.getParameter("extra_score"));
				extraMap.put("topic_id", topic_id);
				this.bdecService.insertTopicExtra(extraMap);
				// 申报编者情况
				String[] realnames = request.getParameterValues("write_realname");
				String[] sexs = request.getParameterValues("sex");
				String[] prices = request.getParameterValues("write_price");
				String[] positions = request.getParameterValues("write_position");
				String[] workplaces = request.getParameterValues("workplace");
				for (int i = 0; i < realnames.length; i++) { // 遍历数组
					if (!realnames[i].equals("")) {
						Map<String, Object> writeMap = new HashMap<String, Object>();
						writeMap.put("topic_id", topic_id);
						writeMap.put("realname", realnames[i]);
						writeMap.put("sex", sexs[i]);
						writeMap.put("price", prices[i]);
						writeMap.put("position", positions[i]);
						writeMap.put("workplace", workplaces[i]);
						this.bdecService.insertTopicWriter(writeMap);
					}
				}
				msg = "OK";
			}
		}
		return msg;
	}

	/**
	 * 跳转到暂存页面
	 */
	@RequestMapping("toBookdeclareZc")
	public ModelAndView toBookdeclareZc(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("commuser/personalcenter/toBookdeclareZc");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("topic_id", request.getParameter("topic_id"));
		Map<String, Object> topicMap = this.bdecService.queryTopic(queryMap);
		queryMap.put("bank_account_id", topicMap.get("bank_account_id"));
		Map<String, Object> textraMap = this.bdecService.queryTopicExtra(queryMap);
		List<Map<String, Object>> twriteList = this.bdecService.queryTopicWriter(queryMap);
		List<Map<String, Object>> BankList = this.bdecService.queryBank(queryMap);

		if (BankList.size() > 0) {
			mav.addObject("BankMap", BankList.get(0));
		}
		mav.addObject("topicMap", topicMap);
		mav.addObject("textraMap", textraMap);
		mav.addObject("twriteList", twriteList);
		mav.addObject("twriteCount", twriteList.size());

		return mav;
	}

	/**
	 * 修改保存
	 */
	@RequestMapping("doBookdeclareZc")
	@ResponseBody
	public String doBookdeclareZc(HttpServletRequest request, HttpServletResponse response) {
		String topic_id = request.getParameter("topic_id"); // 主键id
		String stype = request.getParameter("stype"); // 申报信息存储方式
		//int类型
		String word_number = request.getParameter("word_number");
		String picture_number = request.getParameter("picture_number");
		String purchase = request.getParameter("purchase");
		String sponsorship = request.getParameter("sponsorship");
		if(word_number.equals("")){
			word_number = "0";
		}
		if(picture_number.equals("")){
			picture_number = "0";
		}
		if(purchase.equals("")){
			purchase = "0";
		}
		if(sponsorship.equals("")){
			sponsorship = "0";
		}
		
		// 获取申报信息
		Map<String, Object> topicMap = new HashMap<String, Object>();
		String msg = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String currentDate = df.format(new Date());
		if (stype.equals("1")) { // 表示提交
			topicMap.put("is_staging", "0");
			topicMap.put("auth_progress", "1");
			topicMap.put("is_opts_handling", "1");
			topicMap.put("submit_time", currentDate);
		} else {// 2 表示暂存
			topicMap.put("is_staging", "1");
			topicMap.put("auth_progress", "0");
			topicMap.put("is_opts_handling", "0");
			topicMap.put("submit_time", "");
		}
		topicMap.put("topic_id", topic_id);
		topicMap.put("bookname", request.getParameter("bookname"));
		topicMap.put("reader", request.getParameter("reader"));
		topicMap.put("user_id", request.getParameter("user_id"));
		topicMap.put("deadline", request.getParameter("deadline"));
		topicMap.put("source", request.getParameter("source"));
		topicMap.put("word_number", word_number);
		topicMap.put("picture_number", picture_number);
		topicMap.put("subject", request.getParameter("subject"));
		topicMap.put("rank", request.getParameter("rank"));
		topicMap.put("type", request.getParameter("type"));
		topicMap.put("bank_account_id", request.getParameter("bank_account_id"));
		topicMap.put("purchase", purchase);
		topicMap.put("sponsorship", sponsorship);
		topicMap.put("original_bookname", request.getParameter("original_bookname"));
		// 判断是否为翻译书稿，若有值则表示为翻译书籍
		if (request.getParameter("original_bookname").toString().equals("")) {
			topicMap.put("is_translation", "0"); // 表示原作
		} else {
			topicMap.put("is_translation", "1"); // 表示翻作
		}
		topicMap.put("original_author", request.getParameter("original_author"));
		topicMap.put("nation", request.getParameter("nation"));
		topicMap.put("edition", request.getParameter("edition"));
		int count = this.bdecService.updateTopic(topicMap);
		if (count > 0) {
			// 修改选题申报额外信息topic_extra
			Map<String, Object> extraMap = new HashMap<String, Object>();
			extraMap.put("extra_id", request.getParameter("extra_id"));
			extraMap.put("reason", request.getParameter("reason"));
			extraMap.put("price", request.getParameter("price"));
			extraMap.put("score", request.getParameter("extra_score"));
			extraMap.put("topic_id", topic_id);
			this.bdecService.updateTopicExtra(extraMap);
			// 申报编者情况
			this.bdecService.delTopicWriter(topic_id);
			String[] realnames = request.getParameterValues("write_realname");
			String[] sexs = request.getParameterValues("sex");
			String[] prices = request.getParameterValues("write_price");
			String[] positions = request.getParameterValues("write_position");
			String[] workplaces = request.getParameterValues("workplace");
			for (int i = 0; i < realnames.length; i++) { // 遍历数组
				if (!realnames[i].equals("")) {
					Map<String, Object> writeMap = new HashMap<String, Object>();
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
			// 银行信息更改
			Map<String, Object> bankMap = new HashMap<String, Object>();
			bankMap.put("bank_account_id", request.getParameter("bank_account_id"));
			bankMap.put("account_number", request.getParameter("account_number"));
			bankMap.put("bank", request.getParameter("bank"));
		}
		return msg;
	}

	/**
	 * 详情
	 */
	@RequestMapping("toBookdeclareDetail")
	public ModelAndView toBookdeclareDetail(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("commuser/personalcenter/toBookdeclareDetail");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("topic_id", request.getParameter("topic_id"));
		Map<String, Object> topicMap = this.bdecService.queryTopic(queryMap);
		Map<String, Object> textraMap = this.bdecService.queryTopicExtra(queryMap);
		List<Map<String, Object>> twriteList = this.bdecService.queryTopicWriter(queryMap);
		// 查询银行信息
		queryMap.put("bank_account_id", topicMap.get("bank_account_id"));
		List<Map<String, Object>> BankList = this.bdecService.queryBank(queryMap);

		if (BankList.size() > 0) {
			mav.addObject("BankMap", BankList.get(0));
		}

		mav.addObject("topicMap", topicMap);
		mav.addObject("textraMap", textraMap);
		mav.addObject("twriteList", twriteList);
		return mav;
	}
}
