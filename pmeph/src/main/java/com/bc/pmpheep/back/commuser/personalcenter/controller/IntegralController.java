package com.bc.pmpheep.back.commuser.personalcenter.controller;
/**
 *积分  
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterPointRuleVO;
import com.bc.pmpheep.back.util.CodecUtil;
import com.bc.pmpheep.back.util.DateUtil;
import com.bc.pmpheep.back.util.DigestUtil;
import com.bc.pmpheep.back.util.SyncUtils;
import com.bc.pmpheep.controller.bean.ResponseBean;

import org.apache.commons.collections.MapUtils;
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

	/**
	 *
	 * @Title: findPointByMonth
	 * @Description: 三个月内积分记录
	 * @param @param request
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/findPointExchange", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findPointExchange(HttpServletRequest request) {
		Map<String, Object> usermap = this.getUserInfo();
		Long userId = new Long(String.valueOf(usermap.get("id")));
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("rule_code", request.getParameter("ruleCode"));
		Map<String, Object> map = integralService.findPointExchange(paraMap);
		return map;
	}

	/**
	 * 分页查询兑换规则列表
	 * @param pageSize
	 * @param pageNumber
	 * @param ruleCode
	 * @param ruleName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exchangeList", method = RequestMethod.GET)
	public List<WriterPointRuleVO> exchangeList(){
		return integralService.getlistWriterPointRulePoint();
	}
	/**
	 * 确认兑换积分
	 * @param pageSize
	 * @param pageNumber
	 * @param ruleCode
	 * @param ruleName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/confirmPointExchange", method = RequestMethod.GET)
	public ResponseBean confirmPointExchange(HttpServletRequest request){
		ResponseBean<Object> responseBean = new ResponseBean<>();

		Map<String, Object> usermap = this.getUserInfo();
		Long userId = new Long(String.valueOf(usermap.get("id")));
		String rule_Code = request.getParameter("ruleCode");
		Integer count=new Integer(request.getParameter("count")==null?null:request.getParameter("count"));


		WriterPointRuleVO writerPointRuleVO = integralService.findWrterPointRulePointByRuleCode(rule_Code);
		StringBuilder sb=new StringBuilder();
		sb.append("{\"staff_code\":\""+usermap.get("username")+"\",");
		if(count!=null){
			Integer point = writerPointRuleVO.getPoint();
			Integer exchangePoint = writerPointRuleVO.getExchangePoint();
			double v = exchangePoint.doubleValue();
			double v1 = point.doubleValue();
			Double d=v/v1;
			if(count%point==0){
				Integer res= (int) ((count*1.00)*d);
				sb.append("\"score\":\""+res+"\"}");
			}

		}

		Map<String,String> api=new HashMap<String,String>();
		api.put("app_key","nmkt8v9NkWbQ9WPFl3l6lFNsyThsfcep");
		api.put("format","json");
		api.put("method","com.ai.ecp.pmph.staff.scoreCal");
		api.put("session","MDzjI012CaqX4HG1HbOj35ps1yOYxJ7KfL1ezjKT89OLZZe0f22S6LY6eZ4DleBR");
		api.put("sign_method","md5");
		api.put("timestamp", DateUtil.formatTimeStamp("yyy-MM-dd HH:mm:ss",DateUtil.getCurrentTime()));
		api.put("v","1.0");
		String sign = DigestUtil.digest(api, "hbP5YsbmiWnkOP4IPtXE126JiIaFRCWD4gpfrcULPbs5hytCw06T2SooKfcUnc2g");

		String params = SyncUtils.getUrlApi(api);
		params+="&sign="+sign;

		params+="&biz_content="+ CodecUtil.encodeURL(sb.toString());

		String url="http://aip.pmph.com/route/rest";

		String urlapi=url+"?"+params;
		String s1 = SyncUtils.StringGet(params,url);
		JSONObject jsonObject = JSON.parseObject(s1);
		Integer code = jsonObject.getInteger("code");
		jsonObject.getString("msg");
		if(code==0){
			responseBean.setMsg("积分兑换成功");
			Map<String, Object> writerPointByid = integralService.findWriterPointByid(userId);
			Integer total = MapUtils.getInteger(writerPointByid, "total");
			total=total-count;
			Integer loss = MapUtils.getInteger(writerPointByid, "loss")==null?0:MapUtils.getInteger(writerPointByid, "loss");
			loss+=count;
			HashMap<String, Object> objectObjectHashMap = new HashMap<>();
			objectObjectHashMap.put("loss", loss);
			objectObjectHashMap.put("total",total);
			objectObjectHashMap.put("id", userId);
			integralService.PointChange(objectObjectHashMap);
			Map<String,Object> pointLog= new HashMap<>();
			pointLog.put("user_id", userId);
			pointLog.put("rule_id", writerPointRuleVO.getId());
			pointLog.put("point", count-(count*2));
			integralService.addPointlog(pointLog);
		}

		return responseBean;
	}


}
