package com.bc.pmpheep.back.commuser.articlepage.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	 * 初始化文章搜索 修改后 只有这一个搜索方法 下面的翻页 查询 等全部调用此方法
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	/*@RequestMapping("/toarticle")*/
	@RequestMapping("queryall")
	public ModelAndView search(HttpServletRequest request
			,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum
			,@RequestParam(value="pageSize",defaultValue="5")Integer pageSize
			,@RequestParam(value="title",defaultValue="")String title) throws UnsupportedEncodingException{
		ModelAndView modelAndView=new ModelAndView();
		title = java.net.URLDecoder.decode(title,"UTF-8").trim(); 
		Map<String, Object> user=getUserInfo();
		String uid = null; //登录了就是登录人id 没登录就留null
		if (user != null && getUserInfo().get("id")!=null && getUserInfo().get("id").toString() != null) {
			uid = getUserInfo().get("id").toString();
		}
        String[] searchTextArray = title.split(" ");
        searchTextArray = searchTextArray.length>0?searchTextArray:null; //支持多个查询条件用空格隔开 
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("searchTextArray",searchTextArray);
		paraMap.put("logUserId", uid);
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<Map<String,Object>>(pageNum,pageSize);
		pageParameter.setParameter(paraMap);
		List<Map<String, Object>> artlist = articleSearchService.queryArticleByAdi(pageParameter);
		int count =articleSearchService.queryArticleByAdiCount(pageParameter);
		Integer maxPageNum = (int) Math.ceil(1.0*count/pageParameter.getPageSize());//总页数
		//下面是小明的方法 没动
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
		modelAndView.addObject("listCount", count);
		modelAndView.addObject("pageNum", pageNum);
		modelAndView.addObject("title", title);
		modelAndView.addObject("m", pageSize);
		modelAndView.addObject("allpage", maxPageNum);
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
		Map<String, Object> user=getUserInfo();
		Map<String, Object> lmap=articleSearchService.queryById(id);
		Map<String, Object> pmap=new HashMap<String, Object>();
		pmap.put("writer_id", user.get("id"));
		pmap.put("content_id", id);
		List<Map<String, Object>> tList=articleSearchService.queryPraise(pmap);
		int likes=Integer.parseInt(lmap.get("likes").toString());
		Map<String, Object> rmap=new HashMap<String, Object>();
		if(tList.size()<1){
			String str=articleSearchService.insertPraise(id, user.get("id").toString());
			if(str.equals("OK")){
				rmap=articleSearchService.changeLikes(likes+1,id);
				rmap.put("likes", likes+1);
			}
		}else{
			articleSearchService.del(tList.get(0).get("id").toString());
			rmap=articleSearchService.changeLikes(likes-1,id);
			rmap.put("likes", likes-1);
		}
		return rmap;
	}
}

/**
 * 根据前台传递过来的数据计算一共有多少页
 * @param request
 * @return map
 *//*
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
}*/

/**
 * 分页的具体实现
 * @param request
 * @return
 *//*
@RequestMapping("change")
public ModelAndView change(HttpServletRequest request){
	ModelAndView modelAndView=new ModelAndView();
	int n=Integer.parseInt(request.getParameter("n"));
	int m=Integer.parseInt(request.getParameter("m"));
	Map<String, Object> map=new HashMap<String, Object>();
	map.put("startrow", (n-1)*m);
	map.put("endrow", m);
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
}*/

/**
 * 模糊查询
 * @param request
 * @return
 * @throws UnsupportedEncodingException 
 */
/*@RequestMapping("queryall")
public ModelAndView queryall(HttpServletRequest request
							,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum
							,@RequestParam(value="pageSize",defaultValue="5")Integer pageSize) throws UnsupportedEncodingException{
	request.setCharacterEncoding("utf-8");
	
	
	
	
	
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
		for (Map<String, Object> pmap : artlist) {
			Message message=messageService.get((String) pmap.get("mid"));
			//判断文章是否被点过赞
			if(user!=null){
				List<Map<String, Object>> list2=articleSearchService.querydExit(pmap.get("id").toString(), user.get("id").toString());
				if(list2.size()>0){
					pmap.put("code", "yes");
				}else{
					pmap.put("code", "no");
				}
			}else{
				pmap.put("code", "no");
			}
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
	}else{
		List<Map<String, Object>> artlist=articleSearchService.queryall("%"+title+"%");
		for (Map<String, Object> pmap : artlist) {
			//判断登陆人是否点过赞
			if(user!=null){
				List<Map<String, Object>> list2=articleSearchService.querydExit(pmap.get("id").toString(), user.get("id").toString());
				if(list2.size()>0){
					pmap.put("code", "yes");
				}else{
					pmap.put("code", "no");
				}
			}else{
				pmap.put("code", "no");
			}
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
 		int allpage=0;
		if(artlist.size()% 5 == 0){
			allpage=artlist.size()/5;
		}else{
			allpage=artlist.size()/5+1;
		}
		modelAndView.addObject("artlist", artlist);
		modelAndView.addObject("allpage", maxPageNum);
	}
	modelAndView.addObject("title", title);
	modelAndView.setViewName("commuser/articlepage/articleSearch");
	return modelAndView;
}*/
