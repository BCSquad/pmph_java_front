package com.bc.pmpheep.back.commuser.collection.controller;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.collection.service.ArticleCollectionService;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 文章收藏控制类
 * @param 
 * @return 
 * @throws
 */
@Controller
@RequestMapping("/articlecollection")
public class ArticleCollectionController extends BaseController{
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.collection.service.ArticleCollectionServiceImpl")
	private ArticleCollectionService articleCollectionService;
	/**
     *到文章收藏页面
     */
    @RequestMapping(value="/toarticlecollection")
    public ModelAndView toArticleCollecton(){
    	Map<String,Object> map=new HashMap<String, Object>();
    	Map<String,Object> userMap=getUserInfo();
    	List<Map<String, Object>> articleCollection = articleCollectionService.queryArticleCollectionList((BigInteger) userMap.get("id"));
    	map.put("articleCollection", articleCollection);
    	return new ModelAndView("/commuser/collection/articlecollection",map);
    }
    
    /**
     * 初始化某一收藏夹的文章收藏列表
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value="/toarticlecollectionlist")
    public ModelAndView initArticleList(HttpServletRequest request) throws UnsupportedEncodingException{
    	Map<String,Object> map=new HashMap<String, Object>();
    	Map<String,Object> userMap=getUserInfo();
    	request.setCharacterEncoding("utf-8");
    	BigInteger favoriteId=new BigInteger(request.getParameter("favoriteId")); 
    	int articlecount = articleCollectionService.queryArticleCont(favoriteId,(BigInteger) userMap.get("id"));
    	String favoriteName = new String(request.getParameter("favoriteName").getBytes("ISO-8859-1"),"utf-8");
    	String pagenum=request.getParameter("pagenum");
    	String pagesize=request.getParameter("pagesize");
    	int startnum=0;
    	int size=5;
    	int pages=0;
    	if(pagenum!=null&&!"".equals(pagenum)&&pagesize!=null&&!"".equals(pagesize)){
    		startnum=Integer.parseInt(pagenum)*Integer.parseInt(pagesize)-Integer.parseInt(pagesize);
    	    size=Integer.parseInt(pagesize);
    	    if((articlecount%Integer.parseInt(pagesize))==0){
    	    	pages=articlecount/Integer.parseInt(pagesize);
    	    }else{
    	    	pages=articlecount/Integer.parseInt(pagesize)+1;
    	    }
    	}else{
    		if(articlecount%size==0){
    			pages=articlecount/size;
    		}else{
    			pages=articlecount/size+1;
    		}
    		pagenum="1";
    		pagesize="5";
    	}
    	List<Map<String, Object>> booklist = articleCollectionService.queryArticleList(favoriteId,startnum,size,(BigInteger) userMap.get("id"));
    	map.put("booklist", booklist);
    	map.put("favoriteName",favoriteName);
    	map.put("bookcount", articlecount);
    	map.put("pagenum",pagenum);
    	map.put("pagesize",pagesize);
    	map.put("pages", pages+"");
    	map.put("favoriteId", request.getParameter("favoriteId"));
    	return new ModelAndView("/commuser/collection/articlelist",map);
    }
    
    /**
     * 对文章点赞或取消点赞
     */
    @RequestMapping(value="/changelike")
    @ResponseBody
    public Map<String,Object> changeLike(HttpServletRequest request){
    	Map<String,Object> map=new HashMap<>();
    	Map<String,Object> userMap=getUserInfo();
    	BigInteger contentId=new BigInteger(request.getParameter("contentId"));
    	map=articleCollectionService.updateLike(contentId, (BigInteger) userMap.get("id"));
    	return map;
    }
    
    /**
     *取消一篇文章的收藏
     */
    @RequestMapping(value="/cancelmark")
    @ResponseBody
    public Map<String,Object> cancelMark(HttpServletRequest request){
    	Map<String,Object> map=new HashMap<String, Object>();
    	Map<String,Object> userMap=getUserInfo();
    	BigInteger markId=new BigInteger(request.getParameter("markId"));
    	BigInteger favoriteId=new BigInteger(request.getParameter("favoriteId"));
    	BigInteger contentId=new BigInteger(request.getParameter("contentId"));
    	map=articleCollectionService.deleteMark(markId,  (BigInteger) userMap.get("id"), favoriteId, contentId);
    	return map;
    }
    
    
    /**
     *删除收藏夹
     */
    @RequestMapping(value="/delfavorite")
    public ModelAndView delFavorite(HttpServletRequest request){
    	Map<String,Object> map=new HashMap<String, Object>();
    	Map<String,Object> userMap=getUserInfo();
    	BigInteger favoriteId=new BigInteger(request.getParameter("favoriteId"));
    	map=articleCollectionService.deleteFavorite((BigInteger) userMap.get("id"),favoriteId);
    	return new ModelAndView("redirect:/articlecollection/toarticlecollection.action");
    }
}
