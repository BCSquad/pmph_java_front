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

import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterUserTrendst;
import com.bc.pmpheep.back.commuser.personalcenter.service.BookDeclareService;
import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
import com.bc.pmpheep.back.util.ArrayUtil;
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
	
	@Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
    private PersonalService personalService;

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
	public Map<String,Object> doBookdeclareAdd(HttpServletRequest request, HttpServletResponse response) {
		// 创建一个唯一标识
		String editionnum = "10" + new SimpleDateFormat("yyyy").format(new Date());
		String vn = bdecService.getMaxTopicVn();
		if (StringUtil.isEmpty(vn)) {
			vn = "000001";
		} else {
			vn = Integer.parseInt(vn.substring(7)) + 1000000 + 1 + "";
		}
		String stype = request.getParameter("stype"); // 申报信息存储方式
		String topic_id = request.getParameter("topic_id"); // 申报信息存储方式
		List<Map<String, Object>> twriteList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> similarList = new ArrayList<Map<String, Object>>();
		// 获取申报信息
		Map<String, Object> topicMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
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
		}
		//选题申报表
		topicMap.put("reader", request.getParameter("reader"));
		topicMap.put("bookname", request.getParameter("bookname"));
		topicMap.put("bank_account_id", "0");
		topicMap.put("user_id", request.getParameter("user_id"));
		topicMap.put("deadline",
				"".equals(request.getParameter("deadline")) ? null : request.getParameter("deadline"));
		topicMap.put("source", request.getParameter("source"));
		topicMap.put("word_number",
				"".equals(request.getParameter("word_number")) ? null : request.getParameter("word_number"));
		topicMap.put("picture_number",
				"".equals(request.getParameter("picture_number")) ? null : request.getParameter("picture_number"));
		topicMap.put("subject", request.getParameter("subject"));
		topicMap.put("rank", request.getParameter("rank"));
		topicMap.put("type", request.getParameter("type"));
		topicMap.put("reader_quantity", request.getParameter("reader_quantity"));
		topicMap.put("purchase",
				"".equals(request.getParameter("purchase")) ? null : request.getParameter("purchase"));
		topicMap.put("campaign", request.getParameter("campaign"));
		topicMap.put("sales_channel", request.getParameter("sales_channel"));
		topicMap.put("lifecycle", request.getParameter("lifecycle"));
		topicMap.put("sponsorship",
				"".equals(request.getParameter("sponsorship")) ? null : request.getParameter("sponsorship"));
		topicMap.put("print_advise", request.getParameter("print_advise"));
		topicMap.put("price_advise", request.getParameter("price_advise"));
		topicMap.put("print_number", request.getParameter("print_number"));
		topicMap.put("cost", request.getParameter("cost"));
		topicMap.put("min_print_number", request.getParameter("min_print_number"));
		topicMap.put("benefit", request.getParameter("benefit"));
		// 判断是否修订书稿
		if (request.getParameter("revision_bookname").toString().equals("")) {
			topicMap.put("is_revision", "0"); // 表示有
		} else {
			topicMap.put("is_revision", "1"); // 表示无
		}
		topicMap.put("revision_bookname", request.getParameter("revision_bookname"));
		topicMap.put("revision_author", request.getParameter("revision_author"));
		topicMap.put("revision_publish_date",
				"".equals(request.getParameter("revision_publish_date")) ? null : request.getParameter("revision_publish_date"));
		topicMap.put("revision_print",
				"".equals(request.getParameter("revision_print")) ? null : request.getParameter("revision_print"));
		topicMap.put("revision_stock",
				"".equals(request.getParameter("revision_stock")) ? null : request.getParameter("revision_stock"));
		// 判断是否为翻译书稿，若有值则表示为翻译书籍
		if (request.getParameter("original_bookname").toString().equals("")) {
			topicMap.put("is_translation", "0"); // 表示原作
		} else {
			topicMap.put("is_translation", "1"); // 表示翻作
		}
		topicMap.put("original_bookname", request.getParameter("original_bookname"));
		topicMap.put("original_author", request.getParameter("original_author"));
		topicMap.put("original_publisher", request.getParameter("original_publisher"));
		topicMap.put("nation", request.getParameter("nation"));
		topicMap.put("edition", request.getParameter("edition"));
		//主编信息
		topicMap.put("realname", request.getParameter("realname"));
		topicMap.put("sex", request.getParameter("sex"));
		topicMap.put("price",
				"".equals(request.getParameter("price")) ? null : request.getParameter("price"));
		topicMap.put("position", request.getParameter("position"));
		topicMap.put("position_profession", request.getParameter("position_profession"));
		topicMap.put("degree", request.getParameter("degree"));
		topicMap.put("workplace", request.getParameter("workplace"));
		topicMap.put("phone", request.getParameter("phone"));
		topicMap.put("address", request.getParameter("address"));
		topicMap.put("postcode", request.getParameter("postcode"));
		topicMap.put("email", request.getParameter("email"));

		topicMap.put("vn", editionnum + vn);
			
		// 选题申报额外信息topic_extra
		Map<String, Object> extraMap = new HashMap<String, Object>();
		extraMap.put("reason", request.getParameter("extra_reason"));
		extraMap.put("score", request.getParameter("extra_score"));
		extraMap.put("achievement", request.getParameter("extra_achievement"));
		extraMap.put("ability", request.getParameter("extra_ability"));

		// 申报编者情况
		String[] realnames = request.getParameterValues("write_realname");
		String[] sexs = request.getParameterValues("write_sex");
		String[] prices = request.getParameterValues("write_price");
		String[] positions = request.getParameterValues("write_position");
		String[] workplaces = request.getParameterValues("write_workplace");
		String[] phones = request.getParameterValues("write_phone");
		String[] degrees = request.getParameterValues("write_degree");
		for (int i = 0; i < realnames.length; i++) { // 遍历数组
			if (!realnames[i].equals("")) {
				Map<String, Object> writeMap = new HashMap<String, Object>();
				writeMap.put("realname", realnames[i]);
				writeMap.put("sex", sexs[i]);
				writeMap.put("price", prices[i]);
				writeMap.put("position", positions[i]);
				writeMap.put("workplace", workplaces[i]);
				writeMap.put("phone", phones[i]);
				writeMap.put("degree", degrees[i]);
				twriteList.add(writeMap);
			}
		}

		// 社外同类书情况表
		String[] booknames = request.getParameterValues("similar_bookname");
		String[] editions = request.getParameterValues("similar_edition");
		String[] authors = request.getParameterValues("similar_author");
		String[] booksizes = request.getParameterValues("similar_booksize");
		String[] publishers = request.getParameterValues("similar_publisher");
		String[] print_numbers = request.getParameterValues("similar_print_number");
		String[] similar_prices = request.getParameterValues("similar_price");
		String[] publish_dates = request.getParameterValues("similar_publish_date");
		for (int i = 0; i < realnames.length; i++) { // 遍历数组
			if (!booknames[i].equals("")) {
				Map<String, Object> similarMap = new HashMap<String, Object>();
				similarMap.put("bookname", booknames[i]);
				similarMap.put("edition", editions[i]);
				similarMap.put("author", authors[i]);
				similarMap.put("booksize", booksizes[i]);
				similarMap.put("publisher", publishers[i]);
				similarMap.put("print_number", print_numbers[i]);
				similarMap.put("price", similar_prices[i]);
				similarMap.put("publish_date", publish_dates[i]);
				similarList.add(similarMap);
			}
		}
		Map<String,Object> returnMap =  new HashMap<String,Object>();
		if(topic_id == null || topic_id.length() <= 0) {//表示新增
			returnMap = this.bdecService.insertBookDeclare(similarList, twriteList, topicMap, extraMap);
		}else{
			returnMap = this.bdecService.updateBookDeclare(similarList, twriteList, topicMap, extraMap,topic_id);
		}
		//TODO 选题申报 提交 时生成动态
		if(returnMap.get("msg").toString().equals("ok")) {
			if ("1".equals(stype)) {
				Map<String, Object> userMap = this.getUserInfo();
				WriterUserTrendst wut = new WriterUserTrendst(request.getParameter("user_id"), 9, topicMap.get("table_trendst_id").toString());
				wut.setDetail("提交选题申报", userMap.get("realname").toString() + " 提交了选题申报《" + request.getParameter("bookname").toString() + "》。", 0);
				personalService.saveUserTrendst(wut);//选题申报 生成动态
			}
		}
		return returnMap;
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
		Map<String, Object> textraMap = this.bdecService.queryTopicExtra(queryMap);
		List<Map<String, Object>> twriteList = this.bdecService.queryTopicWriter(queryMap);
		List<Map<String, Object>> similarList = this.bdecService.querySimilarBook(queryMap);

		mav.addObject("topicMap", topicMap);
		mav.addObject("textraMap", textraMap);
		mav.addObject("twriteList", twriteList);
		mav.addObject("similarList", similarList);
		mav.addObject("twriteCount", twriteList.size());

		return mav;
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
		List<Map<String, Object>> similarList = this.bdecService.querySimilarBook(queryMap);

		mav.addObject("topicMap", topicMap);
		mav.addObject("textraMap", textraMap);
		mav.addObject("twriteList", twriteList);
		mav.addObject("similarList", similarList);
		return mav;
	}
}
