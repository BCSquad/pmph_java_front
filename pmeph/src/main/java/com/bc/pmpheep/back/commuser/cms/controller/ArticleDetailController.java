package com.bc.pmpheep.back.commuser.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.cms.service.ArticleDetailService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.ContentService;
import com.bc.pmpheep.general.service.MessageService;
import com.bc.pmpheep.general.service.SensitiveService;

/**
 * 医学随笔文章详情
 * 
 * @author sunzhuoqun
 */
@Controller
@RequestMapping(value = "/articledetail")
public class ArticleDetailController extends BaseController {

	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.cms.service.ArticleDetailServiceImpl")
	private ArticleDetailService articleDetailService;
	@Autowired
	private ContentService contentService;
	@Autowired
	@Qualifier("com.bc.pmpheep.general.service.SensitiveService")
	private SensitiveService sensitiveService;

	/**
	 * 跳转至页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toPage", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String wid = request.getParameter("wid");
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("id", wid);
		Map<String, Object> map = articleDetailService.queryTitle(map1);
		// mongoDB查询内容
		Content message = contentService.get(map.get("mid").toString());
		// Content message = contentService.get("5a6544f290baad7f01b9bc52");
		String UEContent = "";
		if (message == null || "".equals(message.getContent().trim())) {
			if (null==(map.get("summary"))||"".equals(map.get("summary").toString().trim())) {
				UEContent = "";
			}else{
				UEContent = map.get("summary").toString();
			}
		} else {
			UEContent = message.getContent();
		}
		//是否已通过审核
		Boolean is_audit = false;
		if("2".equals(map.get("auth_status").toString()) /*&& "false".equals(map.get("is_staging").toString())*/){
			is_audit = true;
		}

		// cms附件
		List<Map<String, Object>> cmsAttach = articleDetailService.queryCMSAttach(map1);
		mv.addObject("cmsAttach", cmsAttach);
		// 查看作者
		Map<String, Object> Art = articleDetailService.queryAuthor(map1);
		// 最近3条医学随笔
		List<Map<String, Object>> listArt = articleDetailService.queryArticle(Art.get("author_id").toString());
		int numArt = articleDetailService.queryArticleCount(Art.get("author_id").toString());
		PageParameter<Map<String, Object>> pageParameter = new PageParameter<Map<String, Object>>(1, 2);
		pageParameter.setParameter(map1);
		PageResult<Map<String, Object>> listCom = articleDetailService.queryComment(pageParameter);

		mv.addObject("listCom", listCom);

		if (is_audit) {
			// 增加点击数
			int clinum = (Integer.parseInt(map.get("clicks").toString()) + 1);
			articleDetailService.changeClicks(wid, clinum);
		}
		
		// 猜您喜欢
		List<Map<String, Object>> listArtSix = articleDetailService.queryArticleSix();
		// 点赞
		Map<String, Object> user = getUserInfo();
		if (user != null) {
			Map<String, Object> zmap = new HashMap<String, Object>();
			zmap.put("content_id", wid);
			zmap.put("writer_id", user.get("id"));
			List<Map<String, Object>> list = articleDetailService.queryLikes(zmap);
			if (list != null && list.size() > 0) {
				mv.addObject("like", "yes");
			} else {
				mv.addObject("like", "no");
			}
			Map<String, Object> amap = articleDetailService.queryDedaultFavorite(user.get("id").toString());
			if (amap == null) {
				mv.addObject("mark", "no");
			} else {
				int x = articleDetailService.queryMark(wid, amap.get("id").toString(), user.get("id").toString());
				if (x > 0) {
					mv.addObject("mark", "yes");
				} else {
					mv.addObject("mark", "no");
				}
			}
			if (list.size() > 0) {
				mv.addObject("flag", "yes");
			} else {
				mv.addObject("flag", "no");
			}
		} else {
			mv.addObject("flag", "no");
			// mv.addObject("mark", "no");
		}

		mv.addObject("wid", wid);
		mv.addObject("map", map);
		mv.addObject("UEContent", UEContent);
		mv.addObject("listArt", listArt);
		mv.addObject("Art", Art);
		mv.addObject("numArt", numArt);
		mv.addObject("listArtSix", listArtSix);
		mv.addObject("is_audit", is_audit);
		mv.setViewName("/commuser/cms/articledetail");
		return mv;
	}

	/**
	 * 分页的具体实现
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("changepage")
	@ResponseBody
	public PageResult<Map<String, Object>> changepage(HttpServletRequest request) {
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int allppage = Integer.parseInt(request.getParameter("allppage"));
		String wid = request.getParameter("wid");
		// String wid = "10";
		PageParameter<Map<String, Object>> pageParameter = new PageParameter<Map<String, Object>>(pageNumber, allppage);
		Map<String, Object> wMap = new HashMap<String, Object>();
		wMap.put("id", wid);
		pageParameter.setParameter(wMap);
		PageResult<Map<String, Object>> listCom = articleDetailService.queryComment(pageParameter);
		// mongoDB查询评论
		for (Map<String, Object> pmap : listCom.getRows()) {
			Content content = contentService.get((String) pmap.get("mid"));
			String comment = "(内容为空)";
			if (content != null) {
				comment = content.getContent();
			}
			pmap.put("mid", comment);
		}

		return listCom;
	}

	/**
	 * 随机生成相关文章
	 * 
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public List<Map<String, Object>> change(HttpServletRequest request) {
		String wid = request.getParameter("wid");
		int startrow = Integer.parseInt(request.getParameter("startrow"));
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		//查询后台是否配置了相关文章
		list=articleDetailService.QueryShipByID(wid,startrow);
		if(list.size()>0){
			for (Map<String, Object> map: list) {
				map.put("startrow",startrow);
				map.put("end",list.size());
			}
		}else{
			list = articleDetailService.queryRecommendByE(startrow, wid);
		}
		return list;
	}

	/**
	 * 新增文章评论
	 * 
	 * @param request
	 * @return map
	 */
	@RequestMapping("/insertComment")
	@ResponseBody
	public Map<String, Object> insertComment(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String wid = request.getParameter("wid");
		String title = request.getParameter("title");
		// String wid = "10";
		String content = request.getParameter("content");
		Map<String, Object> flagMap = new HashMap<String, Object>();
		if (sensitiveService.confirmSensitive(content)) {
			List<String> sensitives = sensitiveService.getSensitives(null, content);
			flagMap.put("returncode", "ERROR");
			flagMap.put("content", sensitiveService.delHTMLTag(content));
			flagMap.put("value", sensitives);
			return flagMap;
		}
		Map<String, Object> user = this.getUserInfo();
		/* map.put("score", request.getParameter("score")); */
		map.put("parent_id", wid); // 上级id
		map.put("category_id", 0); // 内容类型 0评论
		map.put("author_type", 2); // 作者类型
		map.put("author_id", user.get("id")); // 作者id
		map.put("is_staging", 0); // 提交
		map.put("path", 0); // 根路径
		map.put("title", title); // 标题

		flagMap = articleDetailService.insertWriteArticle(map, content);
		//articleDetailService.updateComments(Long.valueOf(wid));
		return flagMap;
	}

	/**
	 * 根据增加点赞数
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("addlikes")
	@ResponseBody
	public Map<String, Object> addlikes(HttpServletRequest request) {
		String wid = request.getParameter("wid");
		// String wid = "10";
		Map<String, Object> pmap = articleDetailService.queryRead(wid);
		int likes = Integer.parseInt(pmap.get("likes").toString());
		Map<String, Object> user = getUserInfo();
		Map<String, Object> zMap = new HashMap<String, Object>();
		zMap.put("content_id", wid);
		zMap.put("writer_id", user.get("id"));
		List<Map<String, Object>> list = articleDetailService.queryLikes(zMap);
		Map<String, Object> map = new HashMap<String, Object>();
		if (list.size() > 0) {
			Map<String, Object> iMap = new HashMap<String, Object>();
			iMap.put("id", list.get(0).get("id"));
			iMap.put("content_id", wid);
			iMap.put("likes", likes - 1);
			iMap.put("flag", "del");
			map = articleDetailService.addlikes(iMap);
		} else {
			Map<String, Object> iMap = new HashMap<String, Object>();
			iMap.put("writer_id", user.get("id"));
			iMap.put("content_id", wid);
			iMap.put("likes", likes + 1);
			iMap.put("flag", "add");
			map = articleDetailService.addlikes(iMap);
		}
		return map;
	}

	// 添加收藏
	@RequestMapping("/addmark")
	@ResponseBody
	public Map<String, Object> addMark(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		long wid = Long.valueOf(request.getParameter("wid"));
		Map<String, Object> userMap = getUserInfo();
		long writer_id = Long.valueOf(userMap.get("id").toString());
		map = articleDetailService.inserMark(wid, writer_id);
		return map;
	}

}
