package com.bc.pmpheep.back.commuser.readpage.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.collection.dao.BookCollectionDao;
import com.bc.pmpheep.back.commuser.collection.service.BookCollectionService;
import com.bc.pmpheep.back.commuser.readpage.service.ReadDetailService;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * @author xieming
 * @Description: 读书详情页控制层
 */
@Controller
@RequestMapping("readdetail")
public class ReadDetailController extends BaseController{

	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.readpage.service.ReadDetaiServicelImpl")
	private ReadDetailService readDetailService;
	 @Autowired
	 @Qualifier("com.bc.pmpheep.back.commuser.collection.service.BookCollectionServiceImpl")
	 private BookCollectionService bookCollectionService;
	/**
	 * 根据图书ID初始化数据
	 * @param request
	 * @return modelAndView
	 */
	@RequestMapping("/todetail")
	public ModelAndView move(HttpServletRequest request){
		ModelAndView modelAndView=new ModelAndView();
		String id=request.getParameter("id");
		Map<String, Object> supMap=readDetailService.querySupport(id);
		Map<String, Object> map=readDetailService.queryReadBook(id);
		String author="%"+map.get("author").toString()+"%";
		List<Map<String, Object>> eMap=readDetailService.queryRecommendByE(0);
		List<Map<String,Object>> flist = bookCollectionService.queryBookCollectionList(new BigInteger("12179"));
		List<Map<String, Object>> listCom=readDetailService.queryComment(id);
		List<Map<String, Object>> auList=readDetailService.queryAuthorType(author);
		for (Map<String, Object> pmap : auList) {
			if(("DEFAULT").equals(pmap.get("image_url"))){
				pmap.put("image_url", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
			}
		}
		Map<String, Object> user=getUserInfo();
		Map<String, Object> zmap=new HashMap<String, Object>();
		zmap.put("book_id", id);
		zmap.put("writer_id", user.get("id"));
		List<Map<String, Object>> list=readDetailService.queryLikes(zmap);
		if(list.size()>0){
			modelAndView.addObject("flag", "yes");
		}else{
			modelAndView.addObject("flag","no");
		}
		modelAndView.addObject("auList", auList);
		if(auList.size()<9){
			//该作者的书籍不足9本，根据书籍的类型凑足9本
			int num=9-auList.size();
			Map<String, Object> typeMap=new HashMap<String, Object>();
			typeMap.put("x", num);
			typeMap.put("type",map.get("type"));
			typeMap.put("author", map.get("author"));
			List<Map<String, Object>> tMaps=readDetailService.queryBookByType(typeMap);
			for (Map<String, Object> pmap : tMaps) {
				if(("DEFAULT").equals(pmap.get("image_url"))){
					pmap.put("image_url", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
				}
			}
			modelAndView.addObject("tMaps", tMaps);
		}
		modelAndView.addObject("flist", flist);
		modelAndView.addObject("id", id);
		modelAndView.addObject("eMap", eMap);
		modelAndView.addObject("supMap", supMap);
		modelAndView.addObject("map", map);
		modelAndView.addObject("listCom", listCom);
		modelAndView.addObject("ComNum", listCom.size());
		modelAndView.setViewName("commuser/readpage/readdetail");
		return modelAndView;
	}
	
	/**
	 * 根据图书ID新增评论
	 * @param request
	 * @return map
	 */
	@RequestMapping("/insertComment")
	@ResponseBody
	public Map<String, Object> insertComment(HttpServletRequest request){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("book_id", request.getParameter("book_id"));
		map.put("content", request.getParameter("content"));
		map.put("score", request.getParameter("score"));
		Map<String, Object> user=getUserInfo();
		map.put("writer_id", user.get("id"));
		map.put("avatar", user.get("avatar"));
		Map<String, Object> rmap=readDetailService.insertComment(map);
		return rmap;
	}
	
	/**
	 * 随机生成人卫推荐的书籍
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public List<Map<String, Object>> change(HttpServletRequest request){
		List<Map<String, Object>> eMap=readDetailService.queryRecommendByE(-1);
		int num=eMap.size();
		if(num>5){
			int x=(int)(Math.random()*(num-5));
			List<Map<String, Object>> cMap=readDetailService.queryRecommendByE(x);
			for (Map<String, Object> pmap : cMap) {
				if(("DEFAULT").equals(pmap.get("image_url"))){
					pmap.put("image_url", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
				}
			}
			return cMap;
		}else{
			return null;
		}
	}
	
	//添加收藏
	@RequestMapping("/addmark")
	@ResponseBody
	public Map<String, Object> addMark(HttpServletRequest request){
		Map<String,Object> map=new HashMap<>();
		long bookId=Long.valueOf(request.getParameter("bookId"));
		long favoriteId=Long.valueOf(request.getParameter("favoriteId"));
		long writerId=12179;
		map=readDetailService.inserMark(bookId,favoriteId,writerId);
		return map;
	}
	
	/**
	 * 根据书籍类型查询相同类型的书
	 * @return
	 */
	@RequestMapping("fresh")
	@ResponseBody
	public List<Map<String, Object>> fresh(HttpServletRequest request){
		int x=Integer.parseInt(request.getParameter("type"));
		Map<String, Object> pmap=new HashMap<String, Object>();
		pmap.put("type", x);
		List<Map<String, Object>> map=readDetailService.fresh(pmap);
		for (Map<String, Object> zmap : map) {
			if(("DEFAULT").equals(zmap.get("image_url"))){
				zmap.put("image_url", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
			}
		}
		return map;
	}
	
	/**
	 * 根据书籍D增加点赞数
	 * @param request
	 * @return
	 */
	@RequestMapping("addlikes")
	@ResponseBody
	public Map<String, Object> addlikes(HttpServletRequest request){
		String id=request.getParameter("id");
		Map<String, Object> pmap=readDetailService.queryReadBook(id);
		int likes=Integer.parseInt(pmap.get("likes").toString());
		Map<String, Object> user=getUserInfo();
		Map<String, Object> zMap=new HashMap<String, Object>();
		zMap.put("book_id", id);
		zMap.put("writer_id", user.get("id"));
		List<Map<String, Object>> list=readDetailService.queryLikes(zMap);
		Map<String, Object> map=new HashMap<String, Object>();
		if(list.size()>0){
			Map<String, Object> iMap=new HashMap<String, Object>();
			iMap.put("id", list.get(0).get("id"));
			iMap.put("book_id", id);
			iMap.put("likes", likes-1);
			iMap.put("flag", "del");
			map=readDetailService.addlikes(iMap);
		}else{
			Map<String, Object> iMap=new HashMap<String, Object>();
			iMap.put("writer_id", user.get("id"));
			iMap.put("book_id", id);
			iMap.put("likes", likes+1);
			iMap.put("flag", "add");
	 		map=readDetailService.addlikes(iMap);
		}
	    return map;
	}
}
