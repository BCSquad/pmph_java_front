package com.bc.pmpheep.back.commuser.personalcenter.controller;
/**
 *积分  
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.personalcenter.service.IntegralService;
import com.bc.pmpheep.general.controller.BaseController;


@Controller
@RequestMapping("/integral")
public class IntegralController extends BaseController {

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.IntegralServiceImpl")
    private IntegralService integralService;

    @RequestMapping("/toPage")
    public ModelAndView toPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> usermap = this.getUserInfo();
    	Long userId = new Long(String.valueOf(usermap.get("id")));
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("userId", userId);
        //积分
        Map<String, Object> total = integralService.findTotalPoint(paraMap);
        mv.addObject("total", total);
        mv.setViewName("commuser/personalcenter/integral");
        return mv;
    }

    
	/**
	 * 
	 * @Title: loadMore
	 * @Description: 加载更多
	 * @param @param request
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/loadMore", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> loadMore(HttpServletRequest request) {
		 Map<String, Object> usermap = this.getUserInfo();
    	Long userId = new Long(String.valueOf(usermap.get("id")));
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("condition", request.getParameter("condition"));
        paraMap.put("userId", userId);
		List<Map<String, Object>> list = integralService.findPointList(paraMap);
		return list;
	}
	
	/**
	 * 
	 * @Title: findPointByMonth
	 * @Description: 三个月内积分记录
	 * @param @param request
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/findPointByMonth", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> findPointByMonth(HttpServletRequest request) {
		 Map<String, Object> usermap = this.getUserInfo();
	    	Long userId = new Long(String.valueOf(usermap.get("id")));
	        Map<String, Object> paraMap = new HashMap<String, Object>();
	        paraMap.put("condition", request.getParameter("condition"));
	        paraMap.put("userId", userId);
		List<Map<String, Object>> list = integralService.findPointByMonth(paraMap);
		return list;
	}
	

   
}
