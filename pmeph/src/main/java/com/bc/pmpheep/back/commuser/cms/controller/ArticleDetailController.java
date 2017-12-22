package com.bc.pmpheep.back.commuser.cms.controller;

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
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;

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
	private MessageService messageService;

	/**
	 * 跳转至页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toPage", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		// String id=request.getParameter(wid);
		String wid = "10";
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("id", wid);
		Map<String, Object> map = articleDetailService.queryTitle(map1);
		// mongoDB查询通知内容
		Message message = messageService.get(map.get("mid").toString());
		String UEContent="";
		if (message==null||"".equals(message)) {
			 UEContent = "没有内容！！！！！";
		}else{
			 UEContent = message.getContent();
		}
		// 查看作者
		Map<String, Object> Art = articleDetailService.queryAuthor(map1);
		// 最近3条医学随笔
		List<Map<String, Object>> listArt = articleDetailService
				.queryArticle(Art.get("author_id").toString());
		int numArt = articleDetailService.queryArticleCount(Art
				.get("author_id").toString());
		// 相关文章换一换
		List<Map<String, Object>> eMap = articleDetailService
				.queryRecommendByE(0);
		mv.addObject("map", map);
		mv.addObject("UEContent", UEContent);
		mv.addObject("listArt", listArt);
		mv.addObject("Art", Art);
		mv.addObject("numArt", numArt);
		mv.addObject("eMap", eMap);
		mv.setViewName("/commuser/cms/articledetail");
		return mv;
	}

	/**
	 * 随机生成相关文章
	 * 
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public List<Map<String, Object>> change(HttpServletRequest request) {
		List<Map<String, Object>> eMap = articleDetailService
				.queryRecommendByE(-1);
		int num = eMap.size();
		if (num > 5) {
			int x = (int) (Math.random() * (num - 5));
			List<Map<String, Object>> cMap = articleDetailService
					.queryRecommendByE(x);
			return cMap;
		} else {
			return null;
		}
	}
	
	/**
	 * 新增文章评论
	 * @param request
	 * @return map
	 */
	@RequestMapping("/insertComment")
	@ResponseBody
	public Map<String, Object> insertComment(HttpServletRequest request){
		Map<String, Object> map=new HashMap<String, Object>();
		String content=request.getParameter("content");
		//map.put("score", request.getParameter("score"));
		Map<String, Object> user=getUserInfo();
		map.put("parent_id", 0); //上级id
		map.put("category_id",0); //内容类型 0评论
		map.put("author_type",2); //作者类型
		map.put("author_id",user.get("id")); //作者id
		map.put("is_staging",1); //提交
		map.put("path",0); //根路径
		Map<String, Object> flagMap = articleDetailService.insertWriteArticle(map,content);
		return flagMap;
	}
	

}
