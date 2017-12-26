package com.bc.pmpheep.back.commuser.community.controller;

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

import com.bc.pmpheep.back.commuser.community.service.CommunityService;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 教材社区控制层
 * @param 
 * @return 
 * @throws
 */

@Controller
@RequestMapping("/community")
public class CommunityController {
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.community.service.CommunityServiceImpl")
	private CommunityService communityService;
    
	/**
	 * 到教材社区列表界面
	 */
	@RequestMapping("/tolist")
	public ModelAndView toList(){
		return new ModelAndView("commuser/community/communitylist");
	}
	
    
	/**
	 *为社区列表加载数据
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<Map<String,Object>> initList(HttpServletRequest request){ Integer pagenum=Integer.parseInt(request.getParameter("pageNumber"));
		Integer size=Integer.parseInt(request.getParameter("pageSize"));
		String searchText=request.getParameter("searchText");
		int star=(pagenum-1)*size;
		Map<String,Object> map=new HashMap<>();
		map.put("startnum", star);
		map.put("size", size);
		map.put("searchText", searchText==null || "".equals(searchText)?null:"%"+searchText+"%" );
		List<Map<String,Object>> list=communityService.queryNoticeList(map);
	    return list;
	}
	
	/**
	 * 到教材社区主界面
	 */
	@RequestMapping("/toCommunity")
	public ModelAndView toCommunity(HttpServletRequest req){
		Long noticeId=Long.valueOf(req.getParameter("id"));
		Map<String,Object> notice=communityService.queryNoticeById(noticeId);
		List<Map<String,Object>> reportlist=communityService.queryMaterialNoticeList(Long.valueOf(notice.get("material_id").toString()));
		List<Map<String,Object>> booklist=communityService.queryTextBookList(Long.valueOf(notice.get("material_id").toString()));
		List<Map<String,Object>> someComments=communityService.querySomeComment(Long.valueOf(notice.get("material_id").toString()));
		Map<String,Object> map=new HashMap<>();
		map.put("notice", notice);
		map.put("reportlist", reportlist);
		map.put("booklist", booklist);
		map.put("someComments", someComments);
		return new ModelAndView("commuser/community/community",map);
	}
	
	@RequestMapping("/getComments")
	@ResponseBody
	public Map<String,Object> getComments(HttpServletRequest req){
		Map<String,Object> map=new HashMap<String, Object>();
		Long materialId=Long.valueOf(req.getParameter("materialId"));
		List<Map<String,Object>> comments=communityService.querySomeComment(materialId);
		map.put("comments", comments);
		return map;
	}
}
