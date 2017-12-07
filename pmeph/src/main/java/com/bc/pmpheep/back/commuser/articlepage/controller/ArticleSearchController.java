package com.bc.pmpheep.back.commuser.articlepage.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;

@Controller
@RequestMapping("/articlesearch")
public class ArticleSearchController extends BaseController{

	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService")
	private ArticleSearchService articleSearchService;
	@Autowired
	private MessageService messageService;
	/**
	 * 初始化文章搜索
	 * @return
	 */
	@RequestMapping("/toarticle")
	public ModelAndView search(HttpServletRequest request){
		ModelAndView modelAndView=new ModelAndView();
		List<Map<String, Object>> list=articleSearchService.queryList();
 		int allpage=0;
		if(list.size()% 5 == 0){
			allpage=list.size()/5;
		}else{
			allpage=list.size()/5+1;
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("startrow", 0);
		map.put("endrow", 5);
		List<Map<String, Object>> artlist=articleSearchService.searchArticle(map);
		for (Map<String, Object> pmap : artlist) {
			Message message=messageService.get((String) pmap.get("mid"));
			if(message!=null){
				List<String> imglist = articleSearchService.getImgSrc(message.getContent());
			    if(imglist.size()>0){
			    	pmap.put("imgpath", imglist.get(0));
			    }else{//没有图片放置默认图片
			    	pmap.put("imgpath",request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
			    }
			}else{//没有图片放置默认图片
				pmap.put("imgpath", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
			}
		}
		modelAndView.addObject("artlist", artlist);
		modelAndView.addObject("allpage", allpage);
		modelAndView.setViewName("commuser/articlepage/articleSearch");
		return modelAndView;
	}
	
	/**
	 * 根据前台传递过来的数据计算一共有多少页
	 * @param request
	 * @return map
	 */
	@RequestMapping("changepage")
	@ResponseBody
	public Map<String, Object> changepage(HttpServletRequest request){
		int page=Integer.parseInt(request.getParameter("changepage"));
		List<Map<String, Object>> list=articleSearchService.queryList();
 		int allpage=0;
		if(list.size()% page == 0){
			allpage=list.size()/page;
		}else{
			allpage=list.size()/page+1;
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("page", allpage);
		return map;
	}
	
	/**
	 * 分页的具体实现
	 * @param request
	 * @return
	 */
	@RequestMapping("change")
	public ModelAndView change(HttpServletRequest request){
		ModelAndView modelAndView=new ModelAndView();
		int n=Integer.parseInt(request.getParameter("n"));
		int m=Integer.parseInt(request.getParameter("m"));
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("startrow", (n-1)*m);
		map.put("endrow", m);
		List<Map<String, Object>> artlist=articleSearchService.searchArticle(map);
		List<Map<String, Object>> list=articleSearchService.queryList();
 		int allpage=0;
		if(list.size()% m == 0){
			allpage=list.size()/m;
		}else{
			allpage=list.size()/m+1;
		}
		modelAndView.addObject("allpage", allpage);
		modelAndView.addObject("artlist", artlist);
		modelAndView.addObject("n", n);
		modelAndView.addObject("m", m);
		modelAndView.setViewName("commuser/articlepage/articleSearch");
		return modelAndView;
	}
	
	/**
	 * 改变点赞数
	 * @param request
	 * @return
	 */
	@RequestMapping("changelikes")
	@ResponseBody
	public Map<String, Object> changelikes(HttpServletRequest request){
		Map<String, Object> map=new HashMap<String, Object>();
		String id=request.getParameter("id");
		String status=request.getParameter("status");
		Map<String, Object> user=getUserInfo();
		Map<String, Object> lmap=articleSearchService.queryById(id);
		Map<String, Object> pmap=new HashMap<String, Object>();
		pmap.put("writer_id", user.get("id"));
		pmap.put("content_id", id);
		List<Map<String, Object>> tList=articleSearchService.queryPraise(pmap);
		int likes=Integer.parseInt(lmap.get("likes").toString());
		if(("add").equals(status)){
			map.put("likes", likes+1);
			map.put("status", "add");
			map.put("delete_id", "");
		}else{
			map.put("likes", likes-1);
			map.put("status", "down");
			map.put("delete_id", tList.get(0).get("id"));
		}
		
		map.put("writer_id", user.get("id"));
		map.put("id", id);
		Map<String, Object> rmap=articleSearchService.changeLikes(map);
		rmap.put("likes", map.get("likes"));
		return rmap;
	}
	
	/**
	 * 模糊查询
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("queryall")
	public ModelAndView queryall(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		ModelAndView modelAndView=new ModelAndView();
		//String title = new String(request.getParameter("title").getBytes("ISO-8859-1"),"utf-8");
		String title = java.net.URLDecoder.decode(request.getParameter("title"),"UTF-8"); 
		if(("").equals(title)){
			List<Map<String, Object>> list=articleSearchService.queryList();
	 		int allpage=0;
			if(list.size()% 5 == 0){
				allpage=list.size()/5;
			}else{
				allpage=list.size()/5+1;
			}
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("startrow", 0);
			map.put("endrow", 5);
			List<Map<String, Object>> artlist=articleSearchService.searchArticle(map);
			modelAndView.addObject("artlist", artlist);
			modelAndView.addObject("allpage", allpage);
		}else{
			List<Map<String, Object>> artlist=articleSearchService.queryall("%"+title+"%");
	 		int allpage=0;
			if(artlist.size()% 5 == 0){
				allpage=artlist.size()/5;
			}else{
				allpage=artlist.size()/5+1;
			}
			modelAndView.addObject("artlist", artlist);
			modelAndView.addObject("allpage", allpage);
		}
		modelAndView.addObject("title", title);
		modelAndView.setViewName("commuser/articlepage/articleSearch");
		return modelAndView;
	}
}
