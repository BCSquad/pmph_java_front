package com.bc.pmpheep.back.commuser.personalcenter.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService;
import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;
import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
import com.bc.pmpheep.back.commuser.survey.service.SurveyService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.template.service.TemplateService;
import com.bc.pmpheep.back.util.MD5;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.ContentService;
import com.bc.pmpheep.general.service.MessageService;

//首页controller
@Controller
@RequestMapping("/personalhomepage")
public class PersonalCenterController extends BaseController {

	@Autowired
	@Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
	private TemplateService templateService;

	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
	private PersonalService personalService;

	@Autowired
	private ContentService contentService;

	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService")
	private ArticleSearchService articleSearchService;
	// 我的问卷
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.survey.service.SurveyServiceImpl")
	SurveyService surveyService;

	@RequestMapping("/tohomepage") // 个人中心动态
	public ModelAndView move(@RequestParam(value = "pagetag", defaultValue = "sbwz") String pagetag,
			HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize)
			throws UnsupportedEncodingException {

		ModelAndView mv = new ModelAndView();

		Map<String, Object> vm_map = new HashMap<String, Object>();

		Map<String, Object> paraMap = new HashMap<String, Object>();
		String contextpath = request.getContextPath() + "/";
		String logUserId = getUserInfo().get("id").toString();
		Map<String, Object> permap = new HashMap<String, Object>();

		// String basePath =
		// request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		paraMap.put("contextpath", contextpath);

		// 所进入的是谁的主页
		String userId = request.getParameter("userId");
		//已经提交待审核的文章，不可以再次点发表，也不能编辑，删除，只能查看。只有暂存的文章才有重新编辑、删除和发表
		Boolean selfLog = null;
		Map<String, Object> friendShip = new HashMap<String, Object>();
		friendShip = personalService.queryOurFriendShip(userId, logUserId);
		if (userId != null && !"".equals(userId.trim()) && !logUserId.equals(userId.trim())) {
			paraMap.put("logUserId", userId);
			vm_map.put("logUserId", userId);
			mv.addObject("logUserId", userId);
			permap = personalService.queryUserById(userId);
			selfLog = false;
			// 真正的登录人real_logUserId， 而logUserId是主页主人，查询id
			friendShip.put("logUserId", userId);
		} else {
			paraMap.put("logUserId", logUserId);
			vm_map.put("logUserId", logUserId);
			mv.addObject("logUserId", logUserId);
			permap = this.getUserInfo();// 个人信息
			selfLog = true;
			// 真正的登录人real_logUserId， 而logUserId是主页主人，查询id
			friendShip.put("logUserId", logUserId);
		}
		paraMap.put("selfLog", selfLog);
		mv.addObject("selfLog", selfLog);
		vm_map.put("selfLog", selfLog);

		// 真正的登录人real_logUserId， 而logUserId是主页主人，查询id
		friendShip.put("real_logUserId", logUserId);
		mv.addObject("friendShip", friendShip);

		// 查询个人主页共用部分 收藏 好友 小组
		queryPersonalRightPageInfo(mv, permap);

		mv.addObject("permap", permap);

		// 数据总数初始化
		int count = 0;
		PageParameter<Map<String, Object>> pageParameter = new PageParameter<Map<String, Object>>(pageNum, pageSize);

		// 页签分支
		if ("dt".equals(pagetag)) { // 动态
			// 从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			// 设定条件名数组
			String[] names = {};
			String[] namesChi = {};
			queryConditionOperation(names, namesChi, request, mv, paraMap, vm_map);
			pageParameter.setParameter(paraMap);

			List<Map<String, Object>> List_map = personalService.queryWriterUserTrendst(pageParameter);
			count = personalService.queryWriterUserTrendstCount(pageParameter);
			// 分页数据代码块
			// String html =
			// this.mergeToHtml("commuser/personalcenter/writerUserTrendst.vm",contextpath,
			// pageParameter, List_map,vm_map);
			mv.addObject("List_map", List_map);// 测试
			// mv.addObject("html",html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
		} else if ("tsjc".equals(pagetag)) { // 图书纠错 (我是第一主编)
			// 从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			// 设定条件名数组
			String[] names = { "is_replied" };
			String[] namesChi = {};
			queryConditionOperation(names, namesChi, request, mv, paraMap, vm_map);
			pageParameter.setParameter(paraMap);

			List<Map<String, Object>> List_map = personalService.queryBookCorrectd(pageParameter);
			count = personalService.queryBookCorrectdCount(pageParameter);
			// 分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/bookCorrected.vm", contextpath, pageParameter,
					List_map, vm_map);
			/* mv.addObject("List_map",List_map); */// 测试
			mv.addObject("html", html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
		} else if ("wdjc".equals(pagetag)) { // 我的纠错(我是纠错人)
			// 从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			// 设定条件名数组
			String[] names = { "is_replied" };
			String[] namesChi = {};
			queryConditionOperation(names, namesChi, request, mv, paraMap, vm_map);
			pageParameter.setParameter(paraMap);

			List<Map<String, Object>> List_map = personalService.queryMyCorrection(pageParameter);
			count = personalService.queryMyCorrectionCount(pageParameter);
			// 分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/myCorrection.vm", contextpath, pageParameter,
					List_map, vm_map);
			/* mv.addObject("List_map",List_map); */// 测试
			mv.addObject("html", html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
		} else if ("sbwz".equals(pagetag)) { // 随笔文章
			// 从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			// 设定条件名数组
			String[] names = { "auth_status", "is_staging" };
			String[] namesChi = {};
			queryConditionOperation(names, namesChi, request, mv, paraMap, vm_map);
			pageParameter.setParameter(paraMap);

			List<Map<String, Object>> List_map = personalService.queryMyWritingsNew(pageParameter);
			count = personalService.queryMyWritingsNewCount(pageParameter);
			// 分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/myWritingsList.vm", contextpath, pageParameter,
					List_map, vm_map);
			/* mv.addObject("List_map",List_map); */// 测试
			mv.addObject("html", html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");

		} else if ("jcsb".equals(pagetag)) { // 教材申报
			// 从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			// 设定条件名数组
			String[] names = { "s", "pageinfo", "dateinfo", "online_progress", "is_staging", "pageinfo1" };
			String[] namesChi = { "bookname" };
			queryConditionOperation(names, namesChi, request, mv, paraMap, vm_map);
			pageParameter.setParameter(paraMap);

			List<Map<String, Object>> List_map = personalService.queryMyBooksJoin(pageParameter);// 教材申报最新消息
			count = personalService.queryMyBooksJoinCount(pageParameter);
			// 分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/materialDeclarationList.vm", contextpath,
					pageParameter, List_map, vm_map);
			/* mv.addObject("List_map",List_map); */// 测试
			mv.addObject("html", html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");

		} else if ("wycs".equals(pagetag)) { // 我要出书

			// 从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView，放入模版空间
			// 设定条件名数组
			String[] names = { "auth_progress", "is_staging", "isMine", "pageinfo1" };
			String[] namesChi = { "bookname" };
			queryConditionOperation(names, namesChi, request, mv, paraMap, vm_map);

			pageParameter.setParameter(paraMap);
			List<Map<String, Object>> List_map = personalService.queryMyTopicChoose(pageParameter);
			count = personalService.queryMyTopicChooseCount(pageParameter);
			// 分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/myTopicChoose.vm", contextpath, pageParameter,
					List_map, vm_map);
			/* mv.addObject("List_map",List_map); */// 测试
			mv.addObject("html", html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
		} else if ("wdpl".equals(pagetag)) { // 我的评论
			// 从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			// 设定条件名数组
			String[] names = { "is_long" };
			String[] namesChi = {};
			queryConditionOperation(names, namesChi, request, mv, paraMap, vm_map);
			pageParameter.setParameter(paraMap);

			List<Map<String, Object>> List_map = personalService.myComment(pageParameter);
			count = personalService.myCommentCount(pageParameter);
			// 分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/myComment.vm", contextpath, pageParameter, List_map,
					vm_map);

			mv.addObject("html", html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
		} else if ("wdwj".equals(pagetag)) { // 我的问卷
			// 从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			// 设定条件名数组
			String[] names = {"user_id"};
			String[] namesChi = {};
			queryConditionOperation(names, namesChi, request, mv, paraMap, vm_map);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String dateStr = sdf.format(date);
			paraMap.put("dateStr", dateStr);
			pageParameter.setParameter(paraMap);

			List<Map<String, Object>> List_map = personalService.whetherSurvey(pageParameter);
			count = personalService.mySurveyCount(pageParameter);

			// 分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/mySurvey.vm", contextpath, pageParameter, List_map,
					vm_map);

			mv.addObject("html", html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
		} else {

		}

		// 总页数
		Integer maxPageNum = (int) Math.ceil(1.0 * count / pageSize);

		mv.addObject("listCount", count);
		mv.addObject("maxPageNum", maxPageNum);
		mv.addObject("pagetag", pagetag);
		mv.addObject("pageNum", pageNum);
		mv.addObject("pageSize", pageSize);

		return mv;
	}

	@RequestMapping(value = "authorReply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> authorReply(HttpServletRequest request) {
		String id = request.getParameter("id");
		String author_reply = request.getParameter("author_reply");

		Map<String, Object> result_map = personalService.authorReply(id, author_reply);

		return result_map;
	}

	/**
	 * 分页数据执行模版转化为html
	 * 
	 * @param vm
	 *            模版地址
	 * @param contextpath
	 * @param pageParameter
	 * @param List_map
	 * @return
	 */
	private String mergeToHtml(String vm, String contextpath, PageParameter<Map<String, Object>> pageParameter,
			List<Map<String, Object>> List_map, Map<String, Object> vm_map) {

		vm_map.put("List_map", List_map);
		vm_map.put("startNum", pageParameter.getStart() + 1);
		vm_map.put("para", pageParameter.getParameter());
		vm_map.put("contextpath", contextpath);
		String html = "";
		html = templateService.mergeTemplateIntoString(vm, vm_map);
		return html;
	}

	/**
	 * 从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView，放入模版空间
	 * 
	 * @param names
	 *            数字字母查询条件
	 * @param namesChi
	 *            js中编码过的中文查询条件
	 * @param request
	 * @param modelAndView
	 * @param paraMap
	 * @throws UnsupportedEncodingException
	 */
	private void queryConditionOperation(String[] names, String[] namesChi, HttpServletRequest request,
			ModelAndView modelAndView, Map<String, Object> paraMap, Map<String, Object> vm_map)
			throws UnsupportedEncodingException {
		for (String queryName : names) {
			// 查询条件
			String queryValue = request.getParameter(queryName);
			// 封装查询条件入pageParameter 用以查询
			paraMap.put(queryName, queryValue);
			// 传回查询条件
			modelAndView.addObject(queryName, queryValue);
			// 放入模版空间
			vm_map.put(queryName, queryValue);
		}
		for (String queryName : namesChi) {
			// 查询条件
			String queryValue = request.getParameter(queryName);
			queryValue = java.net.URLDecoder.decode((queryValue != null ? queryValue : ""), "UTF-8");
			// 封装查询条件入pageParameter 用以查询
			paraMap.put(queryName, queryValue);
			// 传回查询条件
			modelAndView.addObject(queryName, queryValue);
			// 放入模版空间
			vm_map.put(queryName, queryValue);
		}
	}

	/*
	 * @RequestMapping("/tohomepageout")//个人中心展示页面 public ModelAndView
	 * moveout(HttpServletRequest request) { ModelAndView modelAndView = new
	 * ModelAndView(); String uid = request.getParameter("uid"); Map<String, Object>
	 * permap = this.getUserInfo(); ;//个人信息 permap.put("id", uid);
	 * List<PersonalNewMessage> listmycol =
	 * personalService.queryMyCol(permap);//我的收藏 List<PersonalNewMessage>
	 * listmyfriend = personalService.queryMyFriend(permap);//我的好友
	 * List<PersonalNewMessage> listmywritingsnew =
	 * personalService.queryMyWritingsNew(permap);//我的随笔文章动态最新消息
	 * List<PersonalNewMessage> listmybooknews =
	 * personalService.queryMyBooksNew(permap);//我的书评消息动态最新消息
	 * modelAndView.addObject("permap", permap); modelAndView.addObject("listmycol",
	 * listmycol); modelAndView.addObject("listmyfriend", listmyfriend);
	 * modelAndView.addObject("listmywritingsnew", listmywritingsnew);
	 * modelAndView.addObject("listmybooknews", listmybooknews);
	 * modelAndView.setViewName("commuser/personalcenter/PersonalHomeOut"); return
	 * modelAndView; }
	 */

	/*
	 * @RequestMapping("/tohomepageone")//个人中心教材申报列表 public ModelAndView
	 * moveoutone(HttpServletRequest request) { ModelAndView modelAndView = new
	 * ModelAndView(); String bookname = null; if (request.getParameter("bookname")
	 * != null) { try { bookname = new
	 * String(request.getParameter("bookname").getBytes("iso-8859-1"), "utf-8"); }
	 * catch (UnsupportedEncodingException e) { // TODO 自动生成的 catch 块
	 * e.printStackTrace(); } } String s = request.getParameter("s"); String
	 * pageinfo = request.getParameter("pageinfo"); String dateinfo =
	 * request.getParameter("dateinfo"); String online_progress =
	 * request.getParameter("online_progress"); String is_staging =
	 * request.getParameter("is_staging"); Map<String, Object> permap =
	 * this.getUserInfo();//个人信息 List<PersonalNewMessage> listmycol =
	 * personalService.queryMyCol(permap);//我的收藏 List<PersonalNewMessage>
	 * listmyfriend = personalService.queryMyFriend(permap);//我的好友 permap.put("s",
	 * s); permap.put("pageinfo", pageinfo); permap.put("dateinfo", dateinfo);
	 * permap.put("online_progress", online_progress); permap.put("is_staging",
	 * is_staging); permap.put("bookname", bookname); List<PersonalNewMessage>
	 * listbookjoins = personalService.queryMyBooksJoin(permap);//教材申报最新消息
	 * modelAndView.addObject("permap", permap); modelAndView.addObject("listmycol",
	 * listmycol); modelAndView.addObject("listmyfriend", listmyfriend);
	 * modelAndView.addObject("listbookjoins", listbookjoins);
	 * modelAndView.setViewName("commuser/personalcenter/PersonalHomeOne");
	 * modelAndView.addObject("serchbox", bookname); return modelAndView; }
	 */

	/*
	 * @RequestMapping("/tohomepageonelist")//我的申请个人中心教材申报列表 public ModelAndView
	 * moveoutone1(HttpServletRequest request) { ModelAndView modelAndView = new
	 * ModelAndView(); String bookname = null; if (request.getParameter("bookname")
	 * != null) { try { bookname = new
	 * String(request.getParameter("bookname").getBytes("iso-8859-1"), "utf-8"); }
	 * catch (UnsupportedEncodingException e) { // TODO 自动生成的 catch 块
	 * e.printStackTrace(); } } String s = "1"; String pageinfo =
	 * request.getParameter("pageinfo"); String dateinfo =
	 * request.getParameter("dateinfo"); String online_progress =
	 * request.getParameter("online_progress"); String is_staging =
	 * request.getParameter("is_staging"); Map<String, Object> permap =
	 * this.getUserInfo();//个人信息 List<PersonalNewMessage> listmycol =
	 * personalService.queryMyCol(permap);//我的收藏 List<PersonalNewMessage>
	 * listmyfriend = personalService.queryMyFriend(permap);//我的好友 permap.put("s",
	 * s); permap.put("pageinfo", pageinfo); permap.put("dateinfo", dateinfo);
	 * permap.put("online_progress", online_progress); permap.put("is_staging",
	 * is_staging); permap.put("bookname", bookname); List<PersonalNewMessage>
	 * listbookjoins = personalService.queryMyBooksJoin(permap);//教材申报最新消息
	 * modelAndView.addObject("permap", permap); modelAndView.addObject("listmycol",
	 * listmycol); modelAndView.addObject("listmyfriend", listmyfriend);
	 * modelAndView.addObject("listbookjoins", listbookjoins);
	 * modelAndView.setViewName("commuser/personalcenter/PersonalHomeOneMy");
	 * modelAndView.addObject("serchbox", bookname); return modelAndView; }
	 */

	/*
	 * @RequestMapping("/tohomepagetwo")//个人中心随笔文章列表 public ModelAndView
	 * moveouttwo() { ModelAndView modelAndView = new ModelAndView(); Map<String,
	 * Object> permap = this.getUserInfo();//个人信息 List<PersonalNewMessage> listmycol
	 * = personalService.queryMyCol(permap);//我的收藏 List<PersonalNewMessage>
	 * listmyfriend = personalService.queryMyFriend(permap);//我的好友
	 * List<PersonalNewMessage> listmywritingsnew =
	 * personalService.queryMyWritingsNew(permap);//我的随笔文章动态最新消息
	 * modelAndView.addObject("permap", permap); modelAndView.addObject("listmycol",
	 * listmycol); modelAndView.addObject("listmyfriend", listmyfriend);
	 * modelAndView.addObject("listmywritingsnew", listmywritingsnew);
	 * modelAndView.setViewName("commuser/personalcenter/PersonalHomeTwo"); return
	 * modelAndView; }
	 */

	/*
	 * @RequestMapping("/tohomepagethe")//个人中心我的书评列表 public ModelAndView
	 * moveoutthe() { ModelAndView modelAndView = new ModelAndView(); Map<String,
	 * Object> permap = this.getUserInfo();//个人信息 List<PersonalNewMessage> listmycol
	 * = personalService.queryMyCol(permap);//我的收藏 List<PersonalNewMessage>
	 * listmyfriend = personalService.queryMyFriend(permap);//我的好友
	 * List<PersonalNewMessage> listmybooknews =
	 * personalService.queryMyBooksNew(permap);//我的书评消息动态最新消息
	 * modelAndView.addObject("permap", permap); modelAndView.addObject("listmycol",
	 * listmycol); modelAndView.addObject("listmyfriend", listmyfriend);
	 * modelAndView.addObject("listmybooknews", listmybooknews);
	 * modelAndView.setViewName("commuser/personalcenter/PersonalHomeThere"); return
	 * modelAndView; }
	 */

	/**
	 * 我要出书
	 *//*
		 * @RequestMapping("/toBookList") public ModelAndView
		 * toBookList(HttpServletRequest request, HttpServletResponse response){
		 * ModelAndView mav = new ModelAndView("commuser/personalcenter/toBookList");
		 * 
		 * return mav; }
		 */

	// 内部方法***************************************************************************

	/**
	 * 查询动态
	 * 
	 * @param modelAndView
	 * @param permap
	 */
	private void queryPersonalNewMessage(ModelAndView modelAndView, Map<String, Object> permap) {
		/*
		 * List<PersonalNewMessage> listmyofeernew =
		 * personalService.queryMyOfeerNew(permap);//我的申请动态最新消息 List<PersonalNewMessage>
		 * listmywritingsnew = personalService.queryMyWritingsNew(permap);//我的随笔文章动态最新消息
		 * List<PersonalNewMessage> listmybooknews =
		 * personalService.queryMyBooksNew(permap);//我的书评消息动态最新消息
		 * //List<PersonalNewMessage> listbookjoins =
		 * personalService.queryMyBooksJoin(permap);//教材申报最新消息
		 * 
		 * List<PersonalNewMessage> newMessages = new ArrayList<PersonalNewMessage>();
		 * for (PersonalNewMessage m : listmyofeernew) { newMessages.add(m); }
		 * 
		 * for (PersonalNewMessage m : listmywritingsnew) { newMessages.add(m); }
		 * 
		 * for (PersonalNewMessage m : listmybooknews) { newMessages.add(m); }
		 * 
		 * Collections.sort(newMessages, new Comparator<PersonalNewMessage>() {
		 * 
		 * @Override public int compare(PersonalNewMessage o1, PersonalNewMessage o2) {
		 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm"); try {
		 * return
		 * format.parse(o1.getGmt_update()).compareTo(format.parse(o2.getGmt_update()));
		 * } catch (ParseException e) { e.printStackTrace(); throw new
		 * RuntimeException(e); } } }); modelAndView.addObject("newMessages",
		 * newMessages);
		 */}

	/**
	 * 查询个人主页共用部分 收藏 好友 小组
	 * 
	 * @param modelAndView
	 * @param permap
	 */
	private void queryPersonalRightPageInfo(ModelAndView modelAndView, Map<String, Object> permap) {
		List<PersonalNewMessage> listmycol = personalService.queryMyCol(permap);// 我的收藏
		List<PersonalNewMessage> listmyfriend = personalService.queryMyFriend(permap);// 我的好友
		List<Map<String, Object>> listmygroup = personalService.queryMyGroup(permap);// 我的小组
		modelAndView.addObject("listmycol", listmycol);
		modelAndView.addObject("listmyfriend", listmyfriend);
		if(listmygroup.size()>0){
			for(Map<String, Object> map : listmygroup){
				map.put("group_image", RouteUtil.gruopImage(map.get("group_image").toString()));
			}
		}
		modelAndView.addObject("listmygroup", listmygroup);

		/*
		 * modelAndView.addObject("listmyofeernew", listmyofeernew);
		 * modelAndView.addObject("listmywritingsnew", listmywritingsnew);
		 * modelAndView.addObject("listmybooknews", listmybooknews);
		 * modelAndView.addObject("listbookjoins", listbookjoins);
		 */
	}

	/**
	 * 我的纠错删除方法 伪删除
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteMyCorrection", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteMyCorrection(@RequestParam(value = "id", required = true) String id,
			HttpServletRequest request) {
		String logUserId = getUserInfo().get("id").toString();
		Map<String, Object> result_map = personalService.deleteMyCorrection(id, logUserId);
		return result_map;

	}

	/**
	 * 我的书评删除方法 伪删除
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteMyBookComment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteMyBookComment(@RequestParam(value = "id", required = true) String id,
			HttpServletRequest request) {
		String logUserId = getUserInfo().get("id").toString();
		Map<String, Object> result_map = personalService.deleteMyBookComment(id, logUserId);
		return result_map;
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "getFirstImgByMid")
	@ResponseBody
	public String getFirstImgByMid(@RequestParam(value = "mid", defaultValue = "") String mid,
			HttpServletRequest request, HttpServletResponse response) {
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/";
		String img_url = basePath + "statics/image/564f34b00cf2b738819e9c35_122x122!.jpg";
		if (!"".equals(mid)) {
			Content content = contentService.get(mid);
			if (content != null) {
				List<String> imglist = articleSearchService.getImgSrc(content.getContent());
				if (imglist.size() > 0) {
					img_url = imglist.get(0);
					if (img_url.length() > 0) {
						img_url = basePath + img_url;
					}
				}
			}
		}
		return img_url;
	}

	/**
	 * 跳转到某一个问卷页面 @Title: writeSurvey @Description: TODO @param @param
	 * request @param @return 设定文件 @return ModelAndView 返回类型 @throws
	 */

	@RequestMapping(value = "/queryMySurvey")
	public ModelAndView queryMySurvey(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String surveyIdStr = request.getParameter("surveyId");
		long surveyId = new Long(surveyIdStr);
		// 获取调查基本信息
		Map<String, Object> mapSurvey = surveyService.getSurveyBaseInfo(surveyId);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String logUserId = getUserInfo().get("id").toString();
		paraMap.put("logUserId", logUserId);
		paraMap.put("surveyId", surveyId);
		// 查询该调查包含的所有题目
		List<Map<String, Object>> list = personalService.getSurvey(surveyId);
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		if (list.size() > 0) {
			// 遍历题目
			for (Map<String, Object> question : list) {
				// 查询每一个单选和多选题对应的选项
				if (question.get("type").equals(1) || question.get("type").equals(2)) {
					String questionIdStr = question.get("id").toString();
					if (null != questionIdStr && !questionIdStr.equals("")) {
						long questionId = Long.valueOf(questionIdStr).longValue();
						List<Map<String, Object>> listOptions = surveyService.getOptions(questionId);
						// 查询单选答案
						paraMap.put("questionId", questionId);
						String answer = personalService.getAnswers(paraMap);
						// 查询多选答案
						String che = personalService.getCheckAnswers(paraMap);
						boolean flag = false;
						if (null != che && "" != che) {
							String[] checkAnswer = che.split(",");
							for (String ch : checkAnswer) {
								for (Map<String, Object> opt : listOptions) {

									if (ch.equals(opt.get("id").toString())) {
										flag = true;
										opt.put("flag", flag);

									} else {
										flag = false;
									}
								}
							}
						}
						question.put("listOptions", listOptions);
						question.put("answer", answer);
						listResult.add(question);
					}
				} else if (question.get("type").equals(4) || question.get("type").equals(5)) {
					// 查询填空答案
					String questionIdStr = question.get("id").toString();
					if (null != questionIdStr && !questionIdStr.equals("")) {
						long questionId = Long.valueOf(questionIdStr).longValue();
						paraMap.put("questionId", questionId);
						String inp = personalService.getInpAnswers(paraMap);
						question.put("inp", inp);
						listResult.add(question);
					}
				} else {
					listResult.add(question);
				}

			}
		}
		Map<String, Object> btn = personalService.btnSaveOrHidden(paraMap);
		mv.addObject("btn", btn);
		mv.addObject("logUserId", logUserId);
		mv.addObject("mapSurvey", mapSurvey);
		mv.addObject("listSesult", listResult);
		mv.addObject("surveyId", surveyId);
		mv.addObject("listSize", listResult.size());
		mv.setViewName("commuser/personalcenter/queryMySurvey");
		return mv;
	}

	/**
	 * 强制登录方法
	 * 
	 * @return
	 */
	@RequestMapping("tologin")
	@ResponseBody
	public String tologin() {
		return "";
	}

	/**
	 * 修改个人中心-我的评论
	 * 
	 * @param request
	 * @return map
	 */
	@RequestMapping("/updateComment")
	@ResponseBody
	public Map<String, Object> updateComment(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", request.getParameter("comm_id"));
		map.put("content", request.getParameter("content"));
		map.put("score", request.getParameter("score"));
		Map<String, Object> user = getUserInfo();
		map.put("writer_id", user.get("id").toString());
		Map<String, Object> rmap = personalService.updateComment(map);
		return rmap;
	}

	/**
	 * 删除个人中心-我的评论
	 * 
	 * @param request
	 * @return map
	 */
	@RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteComment(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String flag = "0";
		try {
			String id = request.getParameter("comm_id");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			Map<String, Object> user = getUserInfo();
			map.put("writer_id", user.get("id").toString());
			//查询评论数
			Long comments = personalService.getCommentBycid(Long.valueOf(id));
			if(comments>0){
				personalService.deleteComment(map);
				personalService.updateDownComments(Long.valueOf(id));
			}else{
				personalService.deleteComment(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
			flag = "1";
			e.printStackTrace();
			resultMap.put("flag", flag);
			return resultMap;
		}
		resultMap.put("flag", flag);
		return resultMap;
	}

	// 弹框回显短评内容
	@RequestMapping(value = "/shortComment")
	@ResponseBody
	public Map<String, Object> shortComment(HttpServletRequest request) {
		String uid = request.getParameter("uid");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("id", uid);
		Map<String, Object> map1 = personalService.shortComment(paraMap);
		return map1;
	}

}
