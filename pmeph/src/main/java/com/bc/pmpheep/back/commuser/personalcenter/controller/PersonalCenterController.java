package com.bc.pmpheep.back.commuser.personalcenter.controller;

import com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService;
import com.bc.pmpheep.back.commuser.messagereport.dao.InfoReportDao;
import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;
import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterUserTrendst;
import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
import com.bc.pmpheep.back.commuser.readpage.dao.ReadDetailDao;
import com.bc.pmpheep.back.commuser.survey.service.SurveyService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.template.service.TemplateService;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.service.ContentService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

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
	//我的收藏
	@Autowired
	private InfoReportDao infoReportDao;
	@Autowired
	private ReadDetailDao readDetailDao;


	@RequestMapping("/tohomepage") // 个人中心动态
	public ModelAndView move(@RequestParam(value = "pagetag", defaultValue = "dt") String pagetag,
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
			String[] names = { "auth_progress", "is_staging", "isMine", "pageinfo1","is_handled" };
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
		}else if ("grsc".equals(pagetag)) { // 个人收藏
			// 从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			// 设定条件名数组
			String[] names = {"is_long"};
			String[] namesChi = {};
			queryConditionOperation(names, namesChi, request, mv, paraMap, vm_map);

			Map<String,Object> userMap=getUserInfo();
			//Long writerId=Long.valueOf(userMap.get("id").toString());
			Long writerId= Long.parseLong(permap.get("id").toString());
			//查询收是否有默认的文章收藏夹，如果没有，就新建一个文章的 默认收藏夹
			Map<String, Object>  dmap = infoReportDao.queryDefaultFavorite(writerId);
			if(dmap==null){
				infoReportDao.insertDefaultFavorite(writerId);
				dmap = infoReportDao.queryDefaultFavorite(writerId);
			}
			BigInteger favoriteId=new BigInteger(dmap.get("id").toString());
			//查询用户是否存在默认的书籍收藏夹，如果没有，就常见一个书籍默认的收藏夹
			Map<String,Object> dmapb=readDetailDao.queryDedaultFavorite(writerId);
			if(dmapb==null){
				readDetailDao.insertFavorite(writerId);
				dmapb=readDetailDao.queryDedaultFavorite(writerId);
			}
			BigInteger favoriteIdb=new BigInteger(dmapb.get("id").toString());
			paraMap.put("favoriteId",favoriteId);
			paraMap.put("favoriteIdb",favoriteIdb);
			pageParameter.setParameter(paraMap);
			List<Map<String, Object>> List_map = personalService.listMyFavorites(pageParameter,contextpath);
			count = personalService.listMyFavoritesCount(pageParameter);
			// 分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/myFavorites.vm", contextpath, pageParameter,
					List_map, vm_map);
			/* mv.addObject("List_map",List_map); */// 测试
			mv.addObject("html", html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
		}else if ("dzfk".equals(pagetag)) { // 读者反馈
			// 从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			// 设定条件名数组
			String[] names = { "is_auth" };
			String[] namesChi = {};
			queryConditionOperation(names, namesChi, request, mv, paraMap, vm_map);
			pageParameter.setParameter(paraMap);

			List<Map<String, Object>> List_map = personalService.queryMyBookFeedBack(pageParameter);
			count = personalService.queryMyBookFeedBackCount(pageParameter);
			// 分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/myBookFeedBack.vm", contextpath, pageParameter,
					List_map, vm_map);
			/* mv.addObject("List_map",List_map); */// 测试
			mv.addObject("html", html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
		}else if("lcjc".equals(pagetag)){//临床决策专家申报
			// 从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			// 设定条件名数组
			String[] names = { "tag_num","online_progress","finalResult","pmphAudit"};
			String[] namesChi = {"search"};
			queryConditionOperation(names, namesChi, request, mv, paraMap, vm_map);
			pageParameter.setParameter(paraMap);
			List<Map<String, Object>> List_map = personalService.queryLcjcVer2(pageParameter);
			for (Map<String, Object> map:List_map) {
				map.put("longinUserId",logUserId);
			}
			count=personalService.queryCountLcjcVer2(pageParameter);
			//count=List_map.size();
			// 分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/clinicalVer2.vm", contextpath, pageParameter,
					List_map, vm_map);
			mv.addObject("html", html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
		}else {

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
				map.put("group_image", RouteUtil.gruopImage(MapUtils.getString(map,"group_image","statics/image/default_image.png")));
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
						List<Map<String, Object>> che = personalService.getCheckAnswers(paraMap);
						boolean flag = false;
						if (null != che) {
							for (Map<String, Object> ch : che) {
								for (Map<String, Object> opt : listOptions) {

									if ((ch.get("option_id").toString()).equals(opt.get("id").toString())) {
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
	public Map<String, Object> dd(HttpServletRequest request) {
		String uid = request.getParameter("uid");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("id", uid);
		Map<String, Object> map1 = personalService.shortComment(paraMap);
		return map1;
	}

	/**
	 * 个人申报信息保存/修改
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("perInfoSave")
	@ResponseBody
	public Map<String,Object> perInfoSave(HttpServletRequest request,
											HttpServletResponse response){
		//公共参数
		Map<String,Object> userMap =  this.getUserInfo();
		String user_id = request.getParameter("user_id"); //系统用户(暂存人)
		//求出信息集合
		//1.作家申报表
		Map<String,Object> perMap = new HashMap<String,Object>();
		//2.作家申报职位
		List<Map<String,Object>> tssbList = new ArrayList<Map<String,Object>>();
		//3.作家学习经历表
		List<Map<String,Object>> stuList = new ArrayList<Map<String,Object>>();
		//4.作家工作经历表
		List<Map<String,Object>> workList = new ArrayList<Map<String,Object>>();
		//5.作家教学经历表
		List<Map<String,Object>> steaList = new ArrayList<Map<String,Object>>();
		//6.作家兼职学术表
		List<Map<String,Object>> zjxsList = new ArrayList<Map<String,Object>>();
		//7.作家上套教材参编情况表
		List<Map<String,Object>> jcbjList = new ArrayList<Map<String,Object>>();
		//8.作家精品课程建设情况表
		List<Map<String,Object>> gjkcjsList = new ArrayList<Map<String,Object>>();
		//9.作家主编国家级规划教材情况表
		List<Map<String,Object>> gjghjcList = new ArrayList<Map<String,Object>>();
		//10.其他社教材编写情况
		List<Map<String,Object>> jcbxList = new ArrayList<Map<String,Object>>();
		//11.作家科研情况表
		List<Map<String,Object>> zjkyList = new ArrayList<Map<String,Object>>();
		//12.作家扩展项填报表
		List<Map<String,Object>> zjkzqkList = new ArrayList<Map<String,Object>>();
		//13.个人成就
		Map<String,Object> achievementMap = new HashMap<String,Object>();
		//14.主编学术专著情况表
		List<Map<String,Object>> monographList = new ArrayList<Map<String,Object>>();
		//15.出版行业获奖情况表
		List<Map<String,Object>> publishList = new ArrayList<Map<String,Object>>();
		//16.SCI论文投稿及影响因子情况表
		List<Map<String,Object>> sciList = new ArrayList<Map<String,Object>>();
		//17.临床医学获奖情况表
		List<Map<String,Object>> clinicalList = new ArrayList<Map<String,Object>>();
		//18.学术荣誉授予情况表
		List<Map<String,Object>> acadeList = new ArrayList<Map<String,Object>>();
		//19.人卫社教材编写情况表
		List<Map<String,Object>> pmphList = new ArrayList<Map<String,Object>>();
		//20.参加人卫慕课、数字教材编写情况
		Map<String,Object> digitalMap = new HashMap<String,Object>();
		//21.编写内容意向表
		Map<String,Object> intentionlMap = new HashMap<String,Object>();

		String is_background = "0";
		String msg = "";
		String online_progress = request.getParameter("online_progress");
		//创建时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());

		//专家信息
		perMap.put("commit_date", date);

		perMap.put("realname", request.getParameter("realname"));
		perMap.put("user_id", user_id);
		perMap.put("is_teacher", userMap.get("is_teacher"));
		String sex = request.getParameter("sex");
		if(sex == null || sex.length() <= 0){
			sex = "1";
		}
		perMap.put("sex",sex);
		perMap.put("birthday", "".equals(request.getParameter("birthday")) ? null:request.getParameter("birthday"));
		perMap.put("experience", "".equals(request.getParameter("experience")) ? null:request.getParameter("experience"));
		perMap.put("org_name", request.getParameter("org_name"));
		perMap.put("position", request.getParameter("position"));
		perMap.put("fax", request.getParameter("fax"));
		perMap.put("title", request.getParameter("title"));
		perMap.put("address", request.getParameter("address"));
		perMap.put("postcode", request.getParameter("postcode"));
		perMap.put("telephone", request.getParameter("telephone"));
		perMap.put("handphone", request.getParameter("handphone"));
		perMap.put("email", request.getParameter("email"));
		perMap.put("idtype", "".equals(request.getParameter("idtype")) ? null:request.getParameter("idtype"));
		perMap.put("idcard", request.getParameter("idcard"));
		perMap.put("org_id", "".equals(request.getParameter("sbdw_id")) ? null:request.getParameter("sbdw_id"));
		perMap.put("is_dispensed", "".equals(request.getParameter("is_dispensed")) ? null:request.getParameter("is_dispensed"));
		perMap.put("is_utec", "".equals(request.getParameter("is_utec")) ? null:request.getParameter("is_utec"));
		perMap.put("degree", "".equals(request.getParameter("degree")) ? null:request.getParameter("degree"));
		perMap.put("rank","2");
		perMap.put("expertise", request.getParameter("expertise"));
		perMap.put("gmt_create", date);



		//主要学习经历
		String xx_kssj[] = request.getParameterValues("xx_kssj");
		String xx_jssj[] = request.getParameterValues("xx_jssj");
		String xx_major[] = request.getParameterValues("xx_major");
		String xx_degree[] = request.getParameterValues("xx_degree");
		String xx_note[] = request.getParameterValues("xx_note");
		String xx_id[] = request.getParameterValues("xx_id");
		String xx_school_name[] = request.getParameterValues("xx_school_name");
		for(int i=0;i<xx_kssj.length;i++) {
			if(!xx_kssj[i].equals("")){
				Map<String,Object> xxjlMap = new HashMap<String,Object>();
				xxjlMap.put("major", xx_major[i]);
				xxjlMap.put("school_name", xx_school_name[i]);
				xxjlMap.put("degree", xx_degree[i]);
				xxjlMap.put("note", xx_note[i]);
				xxjlMap.put("date_begin", "".equals(xx_kssj[i]) ? null:xx_kssj[i]);
				xxjlMap.put("date_end", "".equals(xx_jssj[i]) ? null:xx_jssj[i]);
				xxjlMap.put("sort", i);
				xxjlMap.put("per_id", xx_id[i]);
				//学习经历
				stuList.add(xxjlMap);
			}
		}
		//作家工作经历
		String gz_kssj[] = request.getParameterValues("gz_kssj");
		String gz_jssj[] = request.getParameterValues("gz_jssj");
		String gz_org_name[] = request.getParameterValues("gz_org_name");
		String gz_position[] = request.getParameterValues("gz_position");
		String gz_note[] = request.getParameterValues("gz_note");
		String gz_id[] = request.getParameterValues("gz_id");
		for(int i=0;i<gz_kssj.length;i++) { //遍历数组
			if(!gz_kssj[i].equals("")){ //判断是否存在
				Map<String,Object> workjlMap = new HashMap<String,Object>();
				workjlMap.put("org_name", gz_org_name[i]);
				workjlMap.put("position", gz_position[i]);
				workjlMap.put("note", gz_note[i]);
				workjlMap.put("date_begin", "".equals(gz_kssj[i]) ? null:gz_kssj[i]);
				workjlMap.put("date_end", "".equals(gz_jssj[i]) ? null:gz_jssj[i]);
				workjlMap.put("sort", i);
				workjlMap.put("per_id", gz_id[i]);
				//工作经历
				workList.add(workjlMap);
			}
		}
		//教学经历表
		String jx_kssj[] = request.getParameterValues("jx_kssj");
		String jx_jssj[] = request.getParameterValues("jx_jssj");
		String jx_school_name[] = request.getParameterValues("jx_school_name");
		String jx_subject[] = request.getParameterValues("jx_subject");
		String jx_note[] = request.getParameterValues("jx_note");
		String jx_id[] = request.getParameterValues("jx_id");
		for(int i=0;i<jx_kssj.length;i++) {
			if(!jx_kssj[i].equals("")){ //判断是否存在
				Map<String,Object> jxjlMap = new HashMap<String,Object>();
				jxjlMap.put("school_name", jx_school_name[i]);
				jxjlMap.put("subject", jx_subject[i]);
				jxjlMap.put("note", jx_note[i]);
				jxjlMap.put("date_begin", "".equals(jx_kssj[i]) ? null:jx_kssj[i]);
				jxjlMap.put("date_end", "".equals(jx_jssj[i]) ? null:jx_jssj[i]);
				jxjlMap.put("sort", i);
				jxjlMap.put("per_id", jx_id[i]);
				//教学经历
				steaList.add(jxjlMap);
			}
		}
		//作家兼职学术
		String xs_org_name[] = request.getParameterValues("xs_org_name");
		String xs_rank[] = request.getParameterValues("xs_rank");
		String xs_position[] = request.getParameterValues("xs_position");
		String xs_note[] = request.getParameterValues("xs_note");
		String xs_id[] = request.getParameterValues("xs_id");
		for(int i=0;i<xs_org_name.length;i++) { //遍历数组
			if(!xs_org_name[i].equals("")){ //判断是否存在
				Map<String,Object> xsjzMap = new HashMap<String,Object>();
				xsjzMap.put("org_name", xs_org_name[i]);
				xsjzMap.put("rank", "".equals(request.getParameter(xs_rank[i])) ? null:request.getParameter(xs_rank[i]));
				xsjzMap.put("note", xs_note[i]);
				xsjzMap.put("position", xs_position[i]);
				xsjzMap.put("sort", i);
				xsjzMap.put("per_id", xs_id[i]);
				//作家兼职学术
				zjxsList.add(xsjzMap);
			}
		}
		//上套教材参编情况
		String jc_material_name[] = request.getParameterValues("jc_material_name");
		String jc_position[] = request.getParameterValues("jc_position");
		String jc_note[] = request.getParameterValues("jc_note");
		String jc_publish_date[] = request.getParameterValues("jc_publish_date");
		String jc_publisher[] = request.getParameterValues("jc_publisher");
		String jc_is_digital_editor[] = request.getParameterValues("jc_is_digital_editor");
		String jc_id[] = request.getParameterValues("jc_id");
		for(int i=0;i<jc_material_name.length;i++) { //遍历数组
			if(!jc_material_name[i].equals("")){ //判断是否存在
				Map<String,Object> JcbjMap = new HashMap<String,Object>();
				JcbjMap.put("material_name", jc_material_name[i]);
				JcbjMap.put("is_digital_editor", "".equals(request.getParameter(jc_is_digital_editor[i])) ? null:request.getParameter(jc_is_digital_editor[i]));
				JcbjMap.put("position", "".equals(request.getParameter(jc_position[i])) ? null:request.getParameter(jc_position[i]));
				JcbjMap.put("note", jc_note[i]);
				JcbjMap.put("publisher", jc_publisher[i]==null?"人民卫生出版社":jc_publisher[i]);
				JcbjMap.put("publish_date", "".equals(jc_publish_date[i]) ? null:jc_publish_date[i]);

				JcbjMap.put("sort", i);
				JcbjMap.put("per_id", jc_id[i]);
				//作家上套教材参编
				jcbjList.add(JcbjMap);
			}
		}
		//精品课程建设情况
		String gj_course_name[] = request.getParameterValues("gj_course_name");
		String gj_class_hour[] = request.getParameterValues("gj_class_hour");
		String gj_note[] = request.getParameterValues("gj_note");
		String gj_type[] = request.getParameterValues("gj_type");
		String gj_id[] = request.getParameterValues("gj_id");
		for(int i=0;i<gj_type.length;i++) { //遍历数组
			if(!gj_course_name[i].equals("")){ //判断是否存在
				Map<String,Object> GjkcjsMap = new HashMap<String,Object>();
				GjkcjsMap.put("course_name", gj_course_name[i]);
				GjkcjsMap.put("class_hour", gj_class_hour[i]);
				GjkcjsMap.put("type", "".equals(request.getParameter(gj_type[i])) ? null:request.getParameter(gj_type[i]));
				GjkcjsMap.put("note", gj_note[i]);
				GjkcjsMap.put("sort", i);
				GjkcjsMap.put("per_id", gj_id[i]);
				//精品课程建设
				gjkcjsList.add(GjkcjsMap);
			}
		}
		//主编国家级规划教材情况
		String hj_material_name[] = request.getParameterValues("hj_material_name");
		String hj_isbn[] = request.getParameterValues("hj_isbn");
		String hj_rank_text[] = request.getParameterValues("hj_rank_text");
		String hj_note[] = request.getParameterValues("hj_note");
		String hj_id[] = request.getParameterValues("hj_id");
		for(int i=0;i<hj_material_name.length;i++) { //遍历数组
			if(!hj_material_name[i].equals("")){ //判断是否存在
				Map<String,Object> GjghjcMap = new HashMap<String,Object>();
				GjghjcMap.put("material_name",hj_material_name[i]);
				GjghjcMap.put("isbn",hj_isbn[i]);
				GjghjcMap.put("rank_text",hj_rank_text[i]);
				GjghjcMap.put("note", hj_note[i]);
				GjghjcMap.put("sort", i);
				GjghjcMap.put("per_id",  hj_id[i]);
				//主编国家级规划
				gjghjcList.add(GjghjcMap);
			}
		}
		//其他社教材编写情况
		String jcb_material_name[] = request.getParameterValues("jcb_material_name");
		String jcb_rank[] = request.getParameterValues("jcb_rank");
		String jcb_position[] = request.getParameterValues("jcb_position");
		String jcb_publisher[] = request.getParameterValues("jcb_publisher");
		String jcb_publish_date[] = request.getParameterValues("jcb_publish_date");
		String jcb_isbn[] = request.getParameterValues("jcb_isbn");
		String jcb_is_digital_editor[] = request.getParameterValues("jcb_is_digital_editor");
		String jcb_note[] = request.getParameterValues("jcb_note");
		String jcb_id[] = request.getParameterValues("jcb_id");
		for(int i=0;i<jcb_material_name.length;i++) { //遍历数组
			if(!jcb_material_name[i].equals("")){ //判断是否存在
				Map<String,Object> JcbxMap = new HashMap<String,Object>();
				JcbxMap.put("material_name", jcb_material_name[i]);
				JcbxMap.put("rank", "".equals(jcb_rank[i]) ? null:jcb_rank[i]);
				JcbxMap.put("position", "".equals(jcb_position[i]) ? null:jcb_position[i]);
				JcbxMap.put("publisher", jcb_publisher[i]);
				JcbxMap.put("is_digital_editor", "".equals(request.getParameter(jcb_is_digital_editor[i])) ? null:request.getParameter(jcb_is_digital_editor[i]));
				JcbxMap.put("publish_date", "".equals(jcb_publish_date[i]) ? null:jcb_publish_date[i]);
				JcbxMap.put("isbn", jcb_isbn[i]);
				JcbxMap.put("note", jcb_note[i]);
				JcbxMap.put("sort", i);
				JcbxMap.put("per_id", jcb_id[i]);
				//教材其他情况编写
				jcbxList.add(JcbxMap);
			}
		}
		//作家科研情况
		String zjk_research_name[] = request.getParameterValues("zjk_research_name");
		String zjk_approval_unit[] = request.getParameterValues("zjk_approval_unit");
		String zjk_award[] = request.getParameterValues("zjk_award");
		String zjk_note[] = request.getParameterValues("zjk_note");
		String zjk_id[] = request.getParameterValues("zjk_id");
		for(int i=0;i<zjk_research_name.length;i++) { //遍历数组
			if(!zjk_research_name[i].equals("")){ //判断是否存在
				Map<String,Object> ZjkyqkMap = new HashMap<String,Object>();
				ZjkyqkMap.put("research_name", zjk_research_name[i]);
				ZjkyqkMap.put("approval_unit", zjk_approval_unit[i]);
				ZjkyqkMap.put("award", zjk_award[i]);
				ZjkyqkMap.put("note", zjk_note[i]);
				ZjkyqkMap.put("sort", i);
				ZjkyqkMap.put("per_id", zjk_id[i]);
				//作家科研情况
				zjkyList.add(ZjkyqkMap);
			}
		}
		//个人成就
		String gr_content = request.getParameter("gr_content");
		achievementMap.put("content", gr_content);
		achievementMap.put("grcj_id", request.getParameter("grcj_id"));
		//扩展信息
		String kz_content[] = request.getParameterValues("kz_content");
		String extension_id[] = request.getParameterValues("extension_id");
		if(extension_id!=null && extension_id.length>0){
			for(int i=0;i<extension_id.length;i++) { //遍历数组
				if(!extension_id[i].equals("")){ //判断是否存在
					Map<String,Object> ExtensionMap = new HashMap<String,Object>();
					ExtensionMap.put("extension_id", extension_id[i]);
					ExtensionMap.put("content", kz_content[i]);
					//扩展信息
					zjkzqkList.add(ExtensionMap);
				}
			}
		}
		//主编学术专著情况
		String zb_monograph_name[] = request.getParameterValues("zb_monograph_name");
		String zb_monograph_date[] = request.getParameterValues("zb_monograph_date");
		String zb_publisher[] = request.getParameterValues("zb_publisher");
		String zb_publish_date[] = request.getParameterValues("zb_publish_date");
		String zb_note[] = request.getParameterValues("zb_note");
		String is_self_paid[] = request.getParameterValues("is_self_paid");
		String zb_id[] = request.getParameterValues("zb_id");
		for(int i=0;i<zb_monograph_name.length;i++) { //遍历数组
			if(!zb_monograph_name[i].equals("")){ //判断是否存在
				Map<String,Object> MonographMap = new HashMap<String,Object>();
				MonographMap.put("monograph_name", zb_monograph_name[i]);
				MonographMap.put("monograph_date", "".equals(zb_monograph_date[i]) ? null:zb_monograph_date[i]);
				MonographMap.put("is_self_paid", "".equals(request.getParameter(is_self_paid[i])) ? null:request.getParameter(is_self_paid[i]));
				MonographMap.put("publisher", zb_publisher[i]);
				//	MonographMap.put("publish_date", zb_publish_date[i]);
				MonographMap.put("publish_date", "".equals(zb_publish_date[i]) ? null:zb_publish_date[i]);
				MonographMap.put("note", zb_note[i]);
				MonographMap.put("sort", i);
				MonographMap.put("per_id", zb_id[i]);
				//主编学术
				monographList.add(MonographMap);
			}
		}
		//出版行业获奖情况表
		String pu_reward_name[] = request.getParameterValues("pu_reward_name");
		String pu_award_unit[] = request.getParameterValues("pu_award_unit");
		String pu_reward_date[] = request.getParameterValues("pu_reward_date");
		String pu_note[] = request.getParameterValues("pu_note");
		String pu_id[] = request.getParameterValues("pu_id");
		for(int i=0;i<pu_reward_name.length;i++) { //遍历数组
			if(!pu_reward_name[i].equals("")){ //判断是否存在
				Map<String,Object> MonographMap = new HashMap<String,Object>();
				MonographMap.put("reward_name", pu_reward_name[i]);
				MonographMap.put("award_unit", pu_award_unit[i]);
				MonographMap.put("reward_date", "".equals(pu_reward_date[i]) ? null:pu_reward_date[i]);
				MonographMap.put("note", pu_note[i]);
				MonographMap.put("sort", i);
				MonographMap.put("per_id", pu_id[i]);
				publishList.add(MonographMap);
			}
		}
		//SCI论文投稿及影响因子情况表
		String sci_paper_name[] = request.getParameterValues("sci_paper_name");
		String sci_journal_name[] = request.getParameterValues("sci_journal_name");
		String sci_factor[] = request.getParameterValues("sci_factor");
		String sci_publish_date[] = request.getParameterValues("sci_publish_date");
		String sci_note[] = request.getParameterValues("sci_note");
		String sci_id[] = request.getParameterValues("sci_id");
		for(int i=0;i<sci_paper_name.length;i++) { //遍历数组
			if(!sci_paper_name[i].equals("")){ //判断是否存在
				Map<String,Object> MonographMap = new HashMap<String,Object>();
				MonographMap.put("paper_name", sci_paper_name[i]);
				MonographMap.put("journal_name", sci_journal_name[i]);
				MonographMap.put("factor", sci_factor[i]);
				MonographMap.put("publish_date", "".equals(sci_publish_date[i]) ? null:sci_publish_date[i]);
				MonographMap.put("note", sci_note[i]);
				MonographMap.put("sort", i);
				MonographMap.put("per_id", sci_id[i]);
				sciList.add(MonographMap);
			}
		}
		//临床医学获奖情况表
		String cl_reward_name[] = request.getParameterValues("cl_reward_name");
		String cl_award_unit[] = request.getParameterValues("cl_award_unit");
		String cl_reward_date[] = request.getParameterValues("cl_reward_date");
		String cl_note[] = request.getParameterValues("cl_note");
		String cl_id[] = request.getParameterValues("cl_id");
		for(int i=0;i<cl_reward_name.length;i++) { //遍历数组
			if(!cl_reward_name[i].equals("")){ //判断是否存在
				Map<String,Object> MonographMap = new HashMap<String,Object>();
				MonographMap.put("reward_name", cl_reward_name[i]);
				MonographMap.put("award_unit", request.getParameter( cl_award_unit[i]));
				MonographMap.put("reward_date", "".equals(cl_reward_date[i]) ? null:cl_reward_date[i]);
				MonographMap.put("note", cl_note[i]);
				MonographMap.put("sort", i);
				MonographMap.put("per_id", cl_id[i]);
				clinicalList.add(MonographMap);
			}
		}
		//学术荣誉授予情况表
		String ac_reward_name[] = request.getParameterValues("ac_reward_name");
		String ac_award_unit[] = request.getParameterValues("ac_award_unit");
		String ac_reward_date[] = request.getParameterValues("ac_reward_date");
		String ac_note[] = request.getParameterValues("ac_note");
		String ac_id[] = request.getParameterValues("ac_id");
		for(int i=0;i<ac_reward_name.length;i++) { //遍历数组
			if(!ac_reward_name[i].equals("")){ //判断是否存在
				Map<String,Object> MonographMap = new HashMap<String,Object>();
				MonographMap.put("reward_name", ac_reward_name[i]);
				MonographMap.put("award_unit", request.getParameter( ac_award_unit[i]));
				MonographMap.put("reward_date", "".equals(ac_reward_date[i]) ? null:ac_reward_date[i]);
				MonographMap.put("note", ac_note[i]);
				MonographMap.put("sort", i);
				MonographMap.put("per_id", ac_id[i]);
				acadeList.add(MonographMap);
			}
		}
		//人卫社教材编写情况表
		String pmph_material_name[] = request.getParameterValues("pmph_material_name");
		String pmph_rank[] = request.getParameterValues("pmph_rank");
		String pmph_position[] = request.getParameterValues("pmph_position");
		String pmph_publish_date[] = request.getParameterValues("pmph_publish_date");
		String pmph_isbn[] = request.getParameterValues("pmph_isbn");
		String pmph_is_digital_editor[] = request.getParameterValues("pmph_is_digital_editor");
		String pmph_note[] = request.getParameterValues("pmph_note");
		String pmph_id[] = request.getParameterValues("pmph_id");
		for(int i=0;i<pmph_material_name.length;i++) { //遍历数组
			if(!pmph_material_name[i].equals("")){ //判断是否存在
				Map<String,Object> JcbxMap = new HashMap<String,Object>();
				JcbxMap.put("material_name", pmph_material_name[i]);
				JcbxMap.put("rank", "".equals(pmph_rank[i]) ? null:pmph_rank[i]);
				JcbxMap.put("position", "".equals(pmph_position[i]) ? null:pmph_position[i]);
				JcbxMap.put("is_digital_editor", "".equals(request.getParameter(pmph_is_digital_editor[i])) ? null:request.getParameter(pmph_is_digital_editor[i]));
				JcbxMap.put("publish_date", "".equals(pmph_publish_date[i]) ? null:pmph_publish_date[i]);
				JcbxMap.put("isbn", pmph_isbn[i]);
				JcbxMap.put("note", pmph_note[i]);
				JcbxMap.put("per_id", pmph_id[i]);
				JcbxMap.put("sort", i);
				pmphList.add(JcbxMap);
			}
		}
		//参加人卫慕课、数字教材编写情况
		String mooc_content = request.getParameter("mooc_content");
		digitalMap.put("content", mooc_content);
		//编写内容意向表
		String intention_content = request.getParameter("intention_content");
		intentionlMap.put("content", intention_content);
		Map<String,Object> returnMap =  new HashMap<String,Object>();
		returnMap = this.personalService.savePerInfo(perMap, tssbList, stuList, workList, steaList, zjxsList, jcbjList, gjkcjsList, gjghjcList, jcbxList, zjkyList, zjkzqkList, achievementMap, monographList, publishList, sciList, clinicalList, acadeList, pmphList, digitalMap, intentionlMap);

		return returnMap;
	}


}
