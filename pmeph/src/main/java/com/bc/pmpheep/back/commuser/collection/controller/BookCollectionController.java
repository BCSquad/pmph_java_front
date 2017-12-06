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
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

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
    	Map<String,Object> pmap=new HashMap<>();
    	request.setCharacterEncoding("utf-8");
    	BigInteger favoriteId=new BigInteger(request.getParameter("favoriteId"));
    	String favoriteName = new String(request.getParameter("favoriteName").getBytes("ISO-8859-1"),"utf-8");
    	String pagenum=request.getParameter("pagenum");
    	String pagesize=request.getParameter("pagesize");
    	int curpage=1;
    	int size=5;
    	if(pagenum!=null && !pagenum.equals("")){
    		curpage=Integer.parseInt(pagenum);
    	}
    	if(pagesize!=null && !"".equals(pagesize)){
    		size=Integer.parseInt(pagesize);
    	}
    	PageParameter<Map<String,Object>> param=new PageParameter<>(curpage,size);
    	pmap.put("favoriteId", favoriteId);
    	pmap.put("writerId", (BigInteger) userMap.get("id"));
        param.setParameter(pmap);
        PageResult<Map<String,Object>> booklist = bookCollectionService.queryBookList(param);
        map.put("booklist", booklist);
    	map.put("favoriteName",favoriteName);
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
