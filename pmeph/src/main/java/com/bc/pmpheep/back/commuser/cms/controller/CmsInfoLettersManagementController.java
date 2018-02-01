package com.bc.pmpheep.back.commuser.cms.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.bc.pmpheep.back.commuser.homepage.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.cms.bean.CmsInfoLettersList;
import com.bc.pmpheep.back.commuser.cms.service.CmsInfoLettersManagementService;
import com.bc.pmpheep.back.commuser.collection.dao.ArticleCollectionDao;
import com.bc.pmpheep.back.commuser.collection.service.ArticleCollectionService;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.service.ContentService;


/**
 * 
 * 
 * 功能描述：焦点信息列表
 * 
 * 
 * 
 * @author (作者) 曾庆峰
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 2017年11月27日
 * @modify (最后修改时间)
 * @修改人 ：曾庆峰
 * @审核人 ：
 *
 */
@Controller
@RequestMapping(value = "/cmsinfoletters")
public class CmsInfoLettersManagementController extends BaseController{
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.cms.service.CmsInfoLettersManagementServiceImpl")
	CmsInfoLettersManagementService cmsInfoLettersManagementService;
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.collection.service.ArticleCollectionServiceImpl")
	private ArticleCollectionService articleCollectionService;


	@Autowired
	@Qualifier("com.bc.pmpheep.back.homepage.service.HomeServiceImpl")
	private HomeService homeService;

	@Autowired
	private ArticleCollectionDao articleCollectionDao;
	
	@Autowired
	ContentService contentService;
	/**
	 * 
	 * 
	 * 功能描述：前台获取信息快报的列表
	 *
	 * @param pageSize
	 *            一页的数据数量
	 * @param pageNumber
	 *            当前第几页
	 * @param isHot
	 *            是否热门
	 * @return
	 *
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> topage(HttpServletRequest req,Integer pageSize, Integer pageNumber, Integer order) {
		Map<String,Object> map=new HashMap<>();
		Map<String, Object> usermap = getUserInfo();
		String id=req.getParameter("materialId");
		Long materialId=null;
		if(id!=null && !"".equals(id)){
			materialId=Long.valueOf(id);
		}
		List<CmsInfoLettersList> mylist = cmsInfoLettersManagementService.list(pageSize,pageNumber, order,materialId);
		BigInteger writerId=null;
		int islike=0;
		if(usermap!=null){
		  writerId = new BigInteger(usermap.get("id").toString());
		}
		  if(mylist !=null && mylist.size()>0){
			for (CmsInfoLettersList cmsInfo : mylist) {
			 if(writerId!=null){
			    islike=articleCollectionDao.queryLikes(new BigInteger(cmsInfo.getId().toString()), writerId);
			 }
			 Content content=contentService.get(cmsInfo.getMid());
			 map.put(cmsInfo.getTitle()+cmsInfo.getId(),removeHtml(content.getContent()));
			 map.put("cms"+cmsInfo.getId().toString(), islike);
			}
		}
	
		map.put("list", mylist);
		return map;
	}
	
	/**
	 * 页面跳转
	 * @author Mryang
	 * @createDate 2017年12月5日 下午2:30:24
	 * @param pageSize
	 * @param pageNumber
	 * @param isHot
	 * @return
	 */
	@RequestMapping(value = "/tolist", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest req,Integer pageSize, Integer pageNumber, Integer order) {
		ModelAndView model = new ModelAndView();
		Map<String, Object> adInfo = homeService.getPageAdInfo("信息快报和遴选公告列表 ");
		model.addObject("adInfo", adInfo);
		model.setViewName("commuser/focusAndSelect/newsReport");
		model.addObject("materialId", req.getParameter("materialId"));
		return model;
	}
	
	/**信息快报点赞或取消点赞
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addlike", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> addLike(HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String, Object>();
		BigInteger contentId=new BigInteger(request.getParameter("id"));
		Map<String, Object> usermap = getUserInfo();
		BigInteger writerId=new BigInteger(usermap.get("id").toString());
		map=articleCollectionService.updateLike(contentId, writerId);
		return map;
	}
	
	//去掉字符串中的html标签
		public String removeHtml(String str){
			String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
			Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	        Matcher m_html=p_html.matcher(str); 
	        str=m_html.replaceAll(""); //过滤html标签 
			return str;
		}
}
