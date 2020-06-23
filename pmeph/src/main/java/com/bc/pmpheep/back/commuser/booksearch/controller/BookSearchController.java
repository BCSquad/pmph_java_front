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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService;
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
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService")
	private ArticleSearchService articleSearchService;
	
	/**
	 * 跳转到查询页面
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("toPage")
	public ModelAndView toPage(@RequestParam(value="search",defaultValue="")String search
								,@RequestParam(value="type",defaultValue="0")String type
								,HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();
		/*String search = request.getParameter("search");*/
		String real_search = request.getParameter("real_search");
		//search = new String((search!=null?search:"").getBytes("iso8859-1"), "utf-8");
		search = java.net.URLDecoder.decode(search.trim(),"UTF-8"); 
		
		long type_long = Long.parseLong(type);
		Map<String, Object> mtMap = bookSearchService.querySortById(type_long);
		String path = "";
		if (mtMap!=null) {
			path = mtMap.get("path").toString();
		}
		path = path + "-" + type_long;
		real_search = new String((real_search!=null?real_search:"").getBytes("iso8859-1"), "utf-8");
		Long id=0L;
		List<Map<String,Object>> fistsort=bookSearchService.queryChildSort(id);
		mv.addObject("fistsort",fistsort);
		mv.addObject("search",search);
		mv.addObject("real_search",search);
		mv.addObject("typeFromRedirectFullPath",path);
		mv.setViewName("commuser/booksearch/booksearch");
		
		return mv;
	}
	
	/**
	 * 从头部搜索框 向 图书搜索和文章搜索的重定向
	 * @param search
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("bookOrArtSpliter")
	public String bookOrArtSpliter(@RequestParam(value="search",defaultValue="")String search,HttpServletRequest request,RedirectAttributes redirectAttributes) throws UnsupportedEncodingException{
		String redirectUrl = "redirect:/booksearch/toPage.action";
		redirectAttributes.addAttribute("search",search);
		String searchName = java.net.URLDecoder.decode(search.trim(),"UTF-8"); 
		
		String[] searchTextArray = fromQueryNameToSearchTextArray(searchName);
        
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("searchTextArray",searchTextArray);
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<Map<String,Object>>(1,1);
		pageParameter.setParameter(paraMap);
		
		int bcount =bookSearchService.getBookTotal(pageParameter);
		int acount =articleSearchService.queryArticleByAdiCount(pageParameter);
		System.out.println("test"+bcount + "" + acount);
		if (bcount==0 && acount >0) {
			redirectAttributes.addAttribute("title",search);
			redirectUrl = "redirect:/articlesearch/queryall.action";
	}
		return redirectUrl;
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

		String str="; \' \\\" \" < > () , \\ / script svg alert confirm prompt onload onmouseover onfocus onerror xss";
			String[] split = str.split(" ");
			for(String s:split){
				if(queryName.contains(s)){
					queryName=queryName.replaceAll(s,"");
				}
			}

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
        
        String[] searchTextArray = fromQueryNameToSearchTextArray(queryName);
        
        smap.put("searchTextArray",searchTextArray);
        //smap.put("searchText", queryName !=null && !"".equals(queryName)? "%"+queryName+"%":null );
        
        
        
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
		paraMap.put("searchTextArray",searchTextArray);
		//paraMap.put("searchText", queryName !=null && !"".equals(queryName)? "%"+queryName+"%":null );
		paraMap.put("logUserId", uid);
		paraMap.put("type", sortid);
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<Map<String,Object>>(pageNum,pageSize);
		pageParameter.setParameter(paraMap);
		List<Map<String, Object>> List_map = bookSearchService.listBook(pageParameter);
		int count =bookSearchService.getBookTotal(pageParameter);
		
		//将传到前端的书名 匹配的文字替换为红色字体
		//此处支持间隔替换 如：  搜索条件为“药理学第3版” 书名为“药理学(第3版)” 那么仅匹配文字变红 中间的括号不变红
		for (Map<String, Object> map : List_map) {
			for (String searchText : searchTextArray) {
				if(searchText.length()>0){
					//String nameReplaceRex = searchText.replaceAll("(.*?)%", "($1)(.*?)");
					String nameReplaceRex = "";
					String[] searchCharArray = searchText.split("%");
					String replacement = "";
					for (int i =1 ;i<=searchCharArray.length;i++) {
						replacement+="<font style=\"color:red;\">$"+(2*i-1)+"</font>";
						nameReplaceRex += "("+searchCharArray[i-1]+")";
						if(i<searchCharArray.length){
							nameReplaceRex += "(.*?)";
							replacement+= "$"+2*i;
						}
					}
					nameReplaceRex = nameReplaceRex.length()>0?nameReplaceRex:" ";
					String bookname = (String) map.get("bookname");
					map.put("bookname", bookname.replaceAll(nameReplaceRex, replacement));
					String author = (String) map.get("author");
					map.put("author", author.replaceAll(nameReplaceRex, replacement));
					String isbn = (String) map.get("isbn");
					map.put("isbn", isbn.replaceAll(nameReplaceRex, replacement));
				}
			}
		}
		
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
	 * 1.将输入按空格分开，作为多个查询条件，全部满足
	 * 2.对各个查询条件 逐字插入% 
	 * @param queryName
	 * @return
	 */
	private String[] fromQueryNameToSearchTextArray(String queryName) {
		queryName = queryName !=null && !"".equals(queryName)? queryName.trim():"";
        String[] searchTextArray = queryName.split(" ");
        searchTextArray = searchTextArray.length>0?searchTextArray:null;
        List<String> searchTextList = new ArrayList<String>();
        for (String searchText : searchTextArray) {
        	searchText = searchText.replaceAll("(.{1})", "$1%").replaceAll("%$", "");
        	searchTextList.add(searchText);
		}
        searchTextArray = searchTextList.toArray(searchTextArray);
		return searchTextArray;
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
