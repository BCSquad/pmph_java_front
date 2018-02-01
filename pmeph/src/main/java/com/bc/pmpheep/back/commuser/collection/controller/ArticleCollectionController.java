package com.bc.pmpheep.back.commuser.collection.controller;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
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
import com.bc.pmpheep.back.commuser.messagereport.dao.InfoReportDao;
import com.bc.pmpheep.back.commuser.readpage.dao.ReadDetailDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
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
	@Autowired
	private InfoReportDao infoReportDao;
	/**
     *到文章收藏页面
     */
    @RequestMapping(value="/toarticlecollection")
    public ModelAndView toArticleCollecton(){
    	Map<String,Object> map=new HashMap<String, Object>();
    	Map<String,Object> userMap=getUserInfo();
    	
    	Long writerId=Long.valueOf(userMap.get("id").toString());
       //查询收是否有默认的文章收藏夹，如果没有，就新建一个文章的 默认收藏夹
    	Map<String, Object>  dmap = infoReportDao.queryDefaultFavorite(writerId);
     	if(dmap==null){
    		infoReportDao.insertDefaultFavorite(writerId);
 		}
    	
    	List<Map<String, Object>> articleCollection=new ArrayList<>();
    	articleCollection = articleCollectionService.queryArticleCollectionList((BigInteger) userMap.get("id"));
    	map.put("articleCollection", articleCollection);
    	return new ModelAndView("/commuser/collection/articlecollection",map);
    }
    
    /**
     * 初始化某一收藏夹的文章收藏列表
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value="/toarticlecollectionlist")
    public ModelAndView initArticleList(HttpServletRequest request) throws UnsupportedEncodingException{
    	Map<String,Object> userMap=getUserInfo();
    	Long writerId=Long.valueOf(userMap.get("id").toString());
        //查询收是否有默认的文章收藏夹，如果没有，就新建一个文章的 默认收藏夹
     	Map<String, Object>  dmap = infoReportDao.queryDefaultFavorite(writerId);
      	if(dmap==null){
     		infoReportDao.insertDefaultFavorite(writerId);
  		}
    	Map<String,Object> map=new HashMap<String, Object>();
    	Map<String,Object> rmap=new HashMap<>();
    	request.setCharacterEncoding("utf-8");
    	BigInteger favoriteId=new BigInteger(dmap.get("id").toString()); 
    	String pagenum=request.getParameter("pagenum");
    	String pagesize=request.getParameter("pagesize");
    	int curpage=1;
    	int size=5;
    	if(pagenum!=null && !pagenum.equals("") ){
    		curpage=Integer.parseInt(pagenum);
    	}
        if(pagesize!=null && !pagesize.equals("") ){
        	size=Integer.parseInt(pagesize);
    	}
        PageParameter<Map<String,Object>> parm=new PageParameter<>(curpage, size);
        rmap.put("favoriteId", favoriteId);
        rmap.put("writerId",userMap.get("id") );
        parm.setParameter(rmap);
        PageResult<Map<String,Object>> articlelist = articleCollectionService.queryArticleList(parm);
        Map<String,Object> fmap=articleCollectionService.queryFavoriteById(favoriteId);
        map.put("articlelist", articlelist);
        map.put("fmap", fmap);
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
