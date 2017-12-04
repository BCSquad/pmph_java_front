package com.bc.pmpheep.back.commuser.collection.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bc.pmpheep.general.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.collection.service.BookCollectionService;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description:  书籍收藏控制层
 * @param 
 * @return 
 * @throws
 */
@Controller
@RequestMapping(value="/bookcollection")

public class BookCollectionController  extends BaseController{
    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.collection.service.BookCollectionServiceImpl")
    private BookCollectionService bookCollectionService;
    
    /**
     *到书籍收藏页面
     */
    @RequestMapping(value="/tobookcollection")
    public ModelAndView toBookCollecton(){
    	Map<String,Object> map=new HashMap<String, Object>();
    	Map<String,Object> userMap=getUserInfo();
    	List<Map<String, Object>> bookCollection = bookCollectionService.queryBookCollectionList((BigInteger) userMap.get("id"));
    	map.put("bookCollection", bookCollection);
    	return new ModelAndView("/commuser/collection/bookcollection",map);
    }
    /**
     * 初始化某一收藏夹的书籍收藏列表
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value="/tobookcollectionlist")
    public ModelAndView initBookList(HttpServletRequest request) throws UnsupportedEncodingException{
    	Map<String,Object> map=new HashMap<String, Object>();
    	Map<String,Object> userMap=getUserInfo();
    	request.setCharacterEncoding("utf-8");
    	BigInteger favoriteId=new BigInteger(request.getParameter("favoriteId"));
    	//根据书籍收藏夹的id获取收藏夹下收藏书籍的总数
    	int bookcount = bookCollectionService.queryBookCont(favoriteId,(BigInteger) userMap.get("id"));
    	String favoriteName = new String(request.getParameter("favoriteName").getBytes("ISO-8859-1"),"utf-8");
    	String pagenum=request.getParameter("pagenum");
    	String pagesize=request.getParameter("pagesize");
    	int startnum=0;
    	int size=5;
    	int pages=0;
    	if(pagenum!=null&&!"".equals(pagenum)&&pagesize!=null&&!"".equals(pagesize)){
    		startnum=Integer.parseInt(pagenum)*Integer.parseInt(pagesize)-Integer.parseInt(pagesize);
    	    size=Integer.parseInt(pagesize);
    	    if((bookcount%Integer.parseInt(pagesize))==0){
    	    	pages=bookcount/Integer.parseInt(pagesize);
    	    }else{
    	    	pages=bookcount/Integer.parseInt(pagesize)+1;
    	    }
    	}else{
    		if(bookcount%size==0){
    			pages=bookcount/size;
    		}else{
    			pages=bookcount/size+1;
    		}
    		pagenum="1";
    		pagesize="5";
    	}
    	List<Map<String, Object>> booklist = bookCollectionService.queryBookList(favoriteId,startnum,size,(BigInteger) userMap.get("id"));
    	map.put("booklist", booklist);
    	map.put("favoriteName",favoriteName);
    	map.put("bookcount", bookcount);
    	map.put("pagenum",pagenum);
    	map.put("pagesize",pagesize);
    	map.put("pages", pages+"");
    	map.put("favoriteId", request.getParameter("favoriteId"));
    	return new ModelAndView("/commuser/collection/booklist",map);
    }
     
    /**
     *对收藏的书籍点赞或取消点赞
     */
    @RequestMapping(value="/changelike")
    @ResponseBody
    public Map<String,Object> changeLike(HttpServletRequest request){
    	Map<String,Object> map=new HashMap<String, Object>();
    	Map<String,Object> userMap=getUserInfo();
    	BigInteger bookId=new BigInteger(request.getParameter("bookId"));
    	map=bookCollectionService.updateLike(bookId, (BigInteger) userMap.get("id"));
    	return map;
    }
    
    /**
     *取消对书籍的收藏
     */
    @RequestMapping(value="/cancelmark")
    @ResponseBody
    public Map<String,Object> cancelMark(HttpServletRequest request){
    	Map<String,Object> map=new HashMap<String, Object>();
    	Map<String,Object> userMap=getUserInfo();
    	BigInteger markId=new BigInteger(request.getParameter("markId"));
    	BigInteger favoriteId=new BigInteger(request.getParameter("favoriteId"));
    	BigInteger bookId=new BigInteger(request.getParameter("bookId"));
    	map=bookCollectionService. deleteMark(markId, (BigInteger) userMap.get("id"), favoriteId,bookId);
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
    	map=bookCollectionService. deleteFavorite((BigInteger) userMap.get("id"), favoriteId);
    	return new ModelAndView("redirect:/bookcollection/tobookcollection.action");
    }
}
