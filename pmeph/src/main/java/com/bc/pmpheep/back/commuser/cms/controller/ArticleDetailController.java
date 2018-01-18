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
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.ContentService;
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
	private ContentService contentService;
	

	/**
	 * 跳转至页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toPage", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		 String wid=request.getParameter("wid");
//		String wid = "10";
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("id", wid);
		Map<String, Object> map = articleDetailService.queryTitle(map1);
		// mongoDB查询内容
		Content message = contentService.get(map.get("mid").toString());
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
		
		
		PageParameter<Map<String, Object>> pageParameter=new PageParameter<Map<String, Object>>(1,2);
		pageParameter.setParameter(map1);
		PageResult<Map<String, Object>> listCom=articleDetailService.queryComment(pageParameter);
		mv.addObject("listCom", listCom);
		
		 List<Map<String, Object>> listArtSix = articleDetailService.queryArticleSix();
		 //点赞
		 Map<String, Object> user=getUserInfo();
			if(user!=null){
				Map<String, Object> zmap=new HashMap<String, Object>();
				zmap.put("content_id", wid);
				zmap.put("writer_id", user.get("id"));
				List<Map<String, Object>> list=articleDetailService.queryLikes(zmap);
				Map<String, Object> amap=articleDetailService.queryDedaultFavorite(user.get("id").toString());
				if(amap==null){
					mv.addObject("mark", "no");
				}else{
					int x=articleDetailService.queryMark(wid,amap.get("id").toString(),user.get("id").toString());
					if(x>0){
						mv.addObject("mark", "yes");	
					}else{
						mv.addObject("mark", "no");	
					}
				}
				if(list.size()>0){
					mv.addObject("flag", "yes");
				}else{
					mv.addObject("flag","no");
				}
			}else{
				mv.addObject("flag","no");
				//mv.addObject("mark", "no");
			}
		mv.addObject("wid", wid); 
		mv.addObject("map", map);
		mv.addObject("UEContent", UEContent);
		mv.addObject("listArt", listArt);
		mv.addObject("Art", Art);
		mv.addObject("numArt", numArt);
		mv.addObject("eMap", eMap);
		mv.addObject("listArtSix", listArtSix);
		mv.setViewName("/commuser/cms/articledetail");
		return mv;
	}
	
	/**
	 * 分页的具体实现
	 * @param request
	 * @return
	 */
	@RequestMapping("changepage")
	@ResponseBody
	public PageResult<Map<String, Object>> changepage(HttpServletRequest request){
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		int allppage=Integer.parseInt(request.getParameter("allppage"));
		 String wid=request.getParameter("wid");
//		String wid = "10";
		PageParameter<Map<String, Object>> pageParameter=new PageParameter<Map<String, Object>>(pageNumber,allppage);
		Map<String, Object> wMap=new HashMap<String, Object>();
		wMap.put("id", wid);
		pageParameter.setParameter(wMap);
		PageResult<Map<String, Object>> listCom=articleDetailService.queryComment(pageParameter);
		// mongoDB查询评论
		for (Map<String, Object> pmap : listCom.getRows()) {
			String comment = contentService.get((String) pmap.get("mid")).getContent();
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
		 String wid=request.getParameter("wid");
//		String wid = "10";
		String content=request.getParameter("content");
		
		Map<String, Object> user=getUserInfo();
		map.put("score", request.getParameter("score"));
		map.put("parent_id", wid); //上级id
		map.put("category_id",0); //内容类型 0评论
		map.put("author_type",2); //作者类型
		map.put("author_id",user.get("id")); //作者id
		map.put("is_staging",1); //提交
		map.put("path",0); //根路径
		
		Map<String, Object> flagMap = articleDetailService.insertWriteArticle(map,content);
		return flagMap;
	}
	
	/**
	 * 根据增加点赞数
	 * @param request
	 * @return
	 */
	@RequestMapping("addlikes")
	@ResponseBody
	public Map<String, Object> addlikes(HttpServletRequest request){
		 String wid=request.getParameter("wid");
//		String wid = "10";
		Map<String, Object> pmap=articleDetailService.queryRead(wid);
		int likes=Integer.parseInt(pmap.get("likes").toString());
		Map<String, Object> user=getUserInfo();
		Map<String, Object> zMap=new HashMap<String, Object>();
		zMap.put("content_id", wid);
		zMap.put("writer_id", user.get("id"));
		List<Map<String, Object>> list=articleDetailService.queryLikes(zMap);
		Map<String, Object> map=new HashMap<String, Object>();
		if(list.size()>0){
			Map<String, Object> iMap=new HashMap<String, Object>();
			iMap.put("id", list.get(0).get("id"));
			iMap.put("content_id", wid);
			iMap.put("likes", likes-1);
			iMap.put("flag", "del");
			map=articleDetailService.addlikes(iMap);
		}else{
			Map<String, Object> iMap=new HashMap<String, Object>();
			iMap.put("writer_id", user.get("id"));
			iMap.put("content_id", wid);
			iMap.put("likes", likes+1);
			iMap.put("flag", "add");
	 		map=articleDetailService.addlikes(iMap);
		}
	    return map;
	}
	
	//添加收藏
	@RequestMapping("/addmark")
	@ResponseBody
	public Map<String, Object> addMark(HttpServletRequest request){
		Map<String,Object> map=new HashMap<>();
		long wid=Long.valueOf(request.getParameter("wid"));
		Map<String,Object> userMap=getUserInfo();
		long writer_id=Long.valueOf(userMap.get("id").toString());
		map=articleDetailService.inserMark(wid,writer_id);
		return map;
	}
	

}
