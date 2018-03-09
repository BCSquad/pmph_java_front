package com.bc.pmpheep.back.commuser.articlepage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.homepage.service.HomeService;
import com.bc.pmpheep.general.controller.BaseController;

//文章首页控制层
@Controller
@RequestMapping("/articlepage")
public class ArticlePageController extends BaseController{

	@Autowired
    @Qualifier("com.bc.pmpheep.back.homepage.service.HomeServiceImpl")
    private HomeService homeService;
	
	/**
	 * 文章首页初始化
	 * @return
	 */
	@RequestMapping("toarticlepage")
	public ModelAndView index() throws Exception{
		ModelAndView modelAndView=new ModelAndView ();
		Map<String, Object> user=getUserInfo();
		String logUserId= null;
	       if (user!=null && user.get("id")!=null && !"".equals(user.get("id"))) {
	    	   logUserId = user.get("id").toString();
			}
	        
		List<Map<String, Object>> listArt = homeService.queryArticle(8);
		List<Map<String, Object>> listAut = homeService.queryAuthor(logUserId);
		modelAndView.addObject("listArt", listArt);
		modelAndView.addObject("listAut", listAut);
		modelAndView.setViewName("commuser/articlepage/articlepage");
		return modelAndView;
	}
}
