package com.bc.pmpheep.back.commuser.booksearch.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.bc.pmpheep.back.commuser.booksearch.service.BookSearchService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.template.service.TemplateService;
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * 书籍查询 控制层
 * @author liudi
 *
 */
@Controller
@RequestMapping("booksearch")
public class BookSearchController extends BaseController {
	
	@Autowired
    @Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
    private TemplateService templateService;
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.booksearch.service.BookSearchServiceImpl")
	BookSearchService bookSearchService;
	
	/**
	 * 跳转到查询页面
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("toPage")
	public ModelAndView toPage(@RequestParam(value="search",defaultValue="")String search,HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();
		/*String search = request.getParameter("search");*/
		String real_search = request.getParameter("real_search");
		//search = new String((search!=null?search:"").getBytes("iso8859-1"), "utf-8");
		search = java.net.URLDecoder.decode(search,"UTF-8"); 
		real_search = new String((real_search!=null?real_search:"").getBytes("iso8859-1"), "utf-8");
		Long id=0L;
		List<Map<String,Object>> fistsort=bookSearchService.queryChildSort(id);
//		List<Map<String,Object>> childsort=null;
//		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
//		Map<String,Object> allsort=null;
//		for (Map<String, Object> map : fistsort) {
//			childsort=bookSearchService.queryChildSort(Long.valueOf(map.get("id").toString()));
//			allsort=new HashMap<String, Object>();
//			allsort.put("parent", map);
//			allsort.put("child", childsort);
//			list.add(allsort);
//		}
//		mv.addObject("list",list);
		mv.addObject("fistsort",fistsort);
		mv.addObject("search",search);
		mv.addObject("real_search",search);
		mv.setViewName("commuser/booksearch/booksearch");
		
		return mv;
	}
	
	/**
	 * 查询列表刷新
	 */
	@RequestMapping(value = "/querybooklist",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> querybooklist(HttpServletRequest request){
		Map<String, Object> user = getUserInfo();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String queryName = request.getParameter("queryName");
        
		
		
		String sortid=request.getParameter("id");
        Map<String,Object> bigsort=new HashMap<String, Object>();;
        List<Map<String,Object>> smallsort=null;
       if(sortid!=null && !"".equals(sortid)){
    	   
        	bigsort=bookSearchService.querySortById(Long.valueOf(sortid));
//        	smallsort=bookSearchService.queryChildSort(Long.valueOf(bigsort.get("id").toString()));
        }
//        resultMap.put("parent", bigsort);
//        resultMap.put("child", smallsort);
		String order=request.getParameter("order");
        Map<String,Object> smap=new HashMap<String, Object>();
        smap.put("searchText", queryName !=null && !"".equals(queryName)? "%"+queryName+"%":null );
        smap.put("order", order !=null && !"".equals(order)? Long.valueOf(order)+2:2);
        smap.put("sortId", sortid!=null && !"".equals(sortid)? sortid:0 );
        smallsort= bookSearchService.querySearchSort(smap);
        Long nextorder=1L;
        if(order !=null && !"".equals(order)){
        	nextorder=Long.valueOf(order)+1;
        	
        }else{
        	bigsort.put("type_name", "全部分类");
        }
        bigsort.put("order", nextorder);
        resultMap.put("child", smallsort);
        resultMap.put("parent", bigsort);
        
        String uid = null;
		if (user != null && getUserInfo().get("id")!=null && getUserInfo().get("id").toString() != null) {
			uid = getUserInfo().get("id").toString();
		}
		String contextpath = request.getParameter("contextpath");
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("searchText", queryName !=null && !"".equals(queryName)? "%"+queryName+"%":null );
		paraMap.put("logUserId", uid);
		paraMap.put("type", sortid);
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<Map<String,Object>>(pageNum,pageSize);
		pageParameter.setParameter(paraMap);
		List<Map<String, Object>> List_map = bookSearchService.listBook(pageParameter);
		int count =bookSearchService.getBookTotal(pageParameter);
		Integer maxPageNum = (int) Math.ceil(1.0*count/pageParameter.getPageSize());
//		Map<String, Object> paraMap = new HashMap<String, Object>();
//		paraMap.put("queryName", queryName);
//		paraMap.put("uid", uid);
//		PageParameter<Map<String,Object>> pageParameter = new PageParameter<Map<String,Object>>(pageNum,pageSize);
//		pageParameter.setParameter(paraMap);
//		List<Map<String, Object>> List_map = bookSearchService.queryBookList(pageParameter);
//		int totoal_count = bookSearchService.queryBookCount(pageParameter);
		Map<String, Object> vm_map = new HashMap<String, Object>();
		vm_map.put("List_map", List_map);
		vm_map.put("startNum", pageParameter.getStart()+1);
		vm_map.put("contextpath", contextpath);
		String html ="";
		String vm = "commuser/booksearch/booksearch.vm";
		html = templateService.mergeTemplateIntoString(vm, vm_map);
		resultMap.put("html", html);
		resultMap.put("totoal_count", maxPageNum);
		resultMap.put("count", count);
		return resultMap;
	}
	
	/**
	 * 当前用户（作家用户）点赞或取消赞
	 */
	@RequestMapping(value="likeSwitch" ,method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> likeSwitch(HttpServletRequest request){
		String bookId = request.getParameter("bookId");
		String uid = getUserInfo().get("id").toString();
		Map<String,Object> resultMap = bookSearchService.likeSwitch(uid,bookId);
		
		return resultMap;
	}
	
	
}
