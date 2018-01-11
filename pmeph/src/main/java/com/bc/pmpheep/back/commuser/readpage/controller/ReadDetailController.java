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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.book.service.BookService;
import com.bc.pmpheep.back.commuser.collection.dao.BookCollectionDao;
import com.bc.pmpheep.back.commuser.collection.service.BookCollectionService;
import com.bc.pmpheep.back.commuser.readpage.service.ReadDetailService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
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
	 @Autowired
	 @Qualifier("com.bc.pmpheep.back.commuser.book.service.BookServiceImpl")
	 private BookService bookService;
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
		if(("DEFAULT").equals(map.get("image_url"))){
			map.put("image_url", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
		}
		String author="%"+map.get("author").toString()+"%";
		List<Map<String, Object>> eMap=readDetailService.queryRecommendByE(0);
		List<Map<String, Object>> listCom=readDetailService.queryComment(id,0);
		List<Map<String, Object>> ComNum=readDetailService.queryComment(id,-1);
		List<Map<String, Object>> auList=readDetailService.queryAuthorType(author);
		List<Map<String, Object>> longList=readDetailService.queryLong(id,0);
		if(longList.size()==0){
			modelAndView.addObject("longcom", "nothing");
		}
		if(listCom.size()==0){
			modelAndView.addObject("shortcom", "nothing");
		}
		//增加点击数
		int clinum=(Integer.parseInt(map.get("clicks").toString())+1);
		readDetailService.changeClicks(id, clinum);
		Long typeid=Long.valueOf(map.get("type").toString());
		List<Map<String, Object>> typeList=bookService.queryParentTypeListByTypeId(typeid);
		for (Map<String, Object> pmap : auList) {
			if(("DEFAULT").equals(pmap.get("image_url"))){
				pmap.put("image_url", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
			}
		}
		Map<String, Object> fmap=new HashMap<String, Object>();
		fmap.put("type", map.get("type"));
		fmap.put("row", 6);
		List<Map<String, Object>> frList=readDetailService.fresh(fmap);
		for (Map<String, Object> pmap : frList) {
			if(("DEFAULT").equals(pmap.get("image_url"))){
				pmap.put("image_url", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
			}
		}
		Map<String, Object> user=getUserInfo();
		if(user!=null){
			Map<String, Object> zmap=new HashMap<String, Object>();
			zmap.put("book_id", id);
			zmap.put("writer_id", user.get("id"));
			List<Map<String, Object>> list=readDetailService.queryLikes(zmap);
			Map<String, Object> amap=readDetailService.queryDedaultFavorite(user.get("id").toString());
			if(amap==null){
				modelAndView.addObject("mark", "no");
			}else{
				int x=readDetailService.queryMark(id,amap.get("id").toString(),user.get("id").toString());
				if(x>0){
					modelAndView.addObject("mark", "yes");	
				}else{
					modelAndView.addObject("mark", "no");	
				}
			}
			if(list.size()>0){
				modelAndView.addObject("flag", "yes");
			}else{
				modelAndView.addObject("flag","no");
			}
		}else{
			modelAndView.addObject("flag","no");
			modelAndView.addObject("mark", "no");
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
		modelAndView.addObject("id", id);
		modelAndView.addObject("eMap", eMap);
		modelAndView.addObject("ComNum", ComNum.size());
		modelAndView.addObject("supMap", supMap);
		modelAndView.addObject("map", map);
		modelAndView.addObject("listCom", listCom);
		modelAndView.addObject("frList", frList);
		modelAndView.addObject("longList", longList);
		modelAndView.addObject("typeList", typeList);
		modelAndView.addObject("start", 2);
		if(null!=(request.getParameter("state"))){
			modelAndView.setViewName("commuser/readpage/writecom");
		}else{
			modelAndView.setViewName("commuser/readpage/readdetail");
		}
		return modelAndView;
	}
	
	/**
	 * 新增图书纠错
	 * @param request
	 * @return
	 */
	@RequestMapping("/correction")
	@ResponseBody
	public String correction(HttpServletRequest request){
		String returncode="";
		Map<String, Object> map = new HashMap<String, Object>();
		String book_id=request.getParameter("book_id");
		String page=request.getParameter("page");
		String line=request.getParameter("line");
		String content=request.getParameter("content");
		String attachment=request.getParameter("attachment");
		String attachment_name=request.getParameter("attachment_name");
		if(StringUtils.isEmpty(book_id)||
			StringUtils.isEmpty(page)||
			StringUtils.isEmpty(line)||
			StringUtils.isEmpty(content)){
			returncode="NO";
		}else{
			Map<String, Object> user=getUserInfo();
			map.put("user_id", user.get("id"));
			map.put("book_id", book_id);
			map.put("page", page);
			map.put("line", line);
			map.put("content", content);
			map.put("attachment", attachment);
			map.put("attachment_name", attachment_name);
			returncode=readDetailService.correction(map);
		}
		return returncode;
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
		Map<String,Object> userMap=getUserInfo();
		long writerId=Long.valueOf(userMap.get("id").toString());
		map=readDetailService.inserMark(bookId,writerId);
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
		String row=request.getParameter("row");
		Map<String, Object> pmap=new HashMap<String, Object>();
		if(row.equals("6")){
			pmap.put("row", 6);
		}else{
			pmap.put("row", 9);
		}
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
	
	/**
	 * 评论分页的具体实现
	 * @param request
	 * @return
	 */
	@RequestMapping("changepage")
	@ResponseBody
	public List<Map<String, Object>> changepage(HttpServletRequest request){
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		String id=request.getParameter("id");
		List<Map<String, Object>> listCom=readDetailService.queryComment(id,pageNumber);
		return listCom;
	}
	
	/**
	 * 长评的具体实现
	 * @param request
	 * @return
	 */
	@RequestMapping("longcom")
	@ResponseBody
	public List<Map<String, Object>> longcom(HttpServletRequest request){
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		String id=request.getParameter("id");
		List<Map<String, Object>> longcom=readDetailService.queryLong(id, pageNumber);
		return longcom;
	}
	
	/**
	 * 删除相关书评
	 * @param request
	 * @return
	 */
	@RequestMapping("updateDelBookWriter")
	@ResponseBody
	public Map<String,Object> updateDelBookWriter(HttpServletRequest request){
		String bookwriterid = request.getParameter("id");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String  flag="0";
		Map<String, Object> user = this.getUserInfo();
		BigInteger uid = (BigInteger) user.get("id");//用户的id
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("bookwriterid",bookwriterid); //书评id
			//修改上一次的全部内容
			 flag = readDetailService.delbookwriter(map);
		} catch (Exception e) {
			// TODO: handle exception
			flag ="1";
			e.printStackTrace();
			resultMap.put("flag", flag);
			return resultMap;
			
		}
		resultMap.put("flag", flag);
		return resultMap;
	
	}
	
	/**
	 * 强制登录方法
	 * @return
	 */
	@RequestMapping("tologin")
	@ResponseBody
	public String tologin(){
		return "";
	}
	
	/**
	 * 查询登陆人是否写过长评
	 * @return returncode;
	 */
	@RequestMapping("queryLoginLong")
	@ResponseBody
	public Map<String, Object> queryLoginLong(HttpServletRequest request){
		String book_id=request.getParameter("book_id");
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> user=getUserInfo();
		List<Map<String, Object>> list=readDetailService.queryLoginLong(user.get("id").toString(),book_id);
		if(list.size()>0){
			map.put("returncode", "yes");
			map.put("list", list);
		}else{
			map.put("returncode", "no");
		}
		return map;
	}
	
	/**
	 * 写长评
	 * @param request
	 * @return
	 */
	@RequestMapping("insertlong")
	@ResponseBody
	public String insertlong(HttpServletRequest request){
		String returncode="";
		String book_id=request.getParameter("book_id");
		String score=request.getParameter("score");
		String content=request.getParameter("content");
		String title=request.getParameter("title");
		if(StringUtils.isEmpty(book_id)||
		   StringUtils.isEmpty(score)||
		   StringUtils.isEmpty(content)||
		   StringUtils.isEmpty(title)){
		   returncode="NO";
		}else{
		   Map<String, Object> map=new HashMap<String, Object>();
		   Map<String, Object> user=getUserInfo();
		   map.put("book_id", book_id);
		   map.put("score", score);
		   map.put("content", content);
		   map.put("title", title);
		   map.put("is_long", "1");
		   map.put("writer_id", user.get("id"));
		   returncode=readDetailService.insertlong(map);
		}
		return returncode;
	}
}
