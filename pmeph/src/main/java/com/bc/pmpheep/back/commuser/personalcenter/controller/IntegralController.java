package com.bc.pmpheep.back.commuser.personalcenter.controller;
/**
 *积分  
 */

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterPointActivityVO;
import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterPointRuleVO;
import com.bc.pmpheep.back.util.*;
import com.bc.pmpheep.controller.bean.ResponseBean;

import org.apache.commons.codec.binary.Base64;
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
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


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
		Map<String, Object> res=new HashMap<>();
		res.put("rule",map);
		Long id = MapUtils.getLong(map, "id");
		Map<String, Object> writerPointActivityVO = integralService.queryMallExchangeRule(id);
		if(ObjectUtil.notNull(writerPointActivityVO)){
			res.put("activity",true);
			res.put("ruleActivity",writerPointActivityVO);
		}else{
			res.put("activity",false);
		}

		return res;
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
    public static Key getKey(String keySeed) {
        if (keySeed == null) {
            keySeed = System.getenv("AES_SYS_KEY");
        }
        if (keySeed == null) {
            keySeed = System.getProperty("AES_SYS_KEY");
        }
        if (keySeed == null || keySeed.trim().length() == 0) {
            keySeed = "abcd1234!@#$";// 默认种子
        }
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keySeed.getBytes());
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(secureRandom);
            return generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 根据密钥对指定的密文cipherText进行解密.
     *
     * @param cipherText 密文
     * @return 解密后的明文.
     */
    public static final String decrypt(String cipherText) {
        Key secretKey = getKey("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] c = decoder.decodeBuffer(cipherText);
            byte[] result = cipher.doFinal(c);
            String plainText = new String(result, "UTF-8");
            return plainText;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 根据密钥对指定的明文plainText进行加密.
     *
     * @param plainText 明文
     * @return 加密后的密文.
     */
    public static final String encrypt(String plainText) {
        Key secretKey = getKey("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] p = plainText.getBytes("UTF-8");
            byte[] result = cipher.doFinal(p);
            BASE64Encoder encoder = new BASE64Encoder();
            String encoded = encoder.encode(result);
            return encoded;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseBody
	@RequestMapping(value = "/confirmPointExchange", method = RequestMethod.POST)
	public ResponseBean confirmPointExchange(HttpServletRequest request) throws IOException {
		ResponseBean<Object> responseBean = new ResponseBean<>();

		Map<String, Object> usermap = this.getUserInfo();
		Long userId = new Long(String.valueOf(usermap.get("id")));
		String rule_Code = request.getParameter("ruleCode");
		String countBase=request.getParameter("count")==null?null:request.getParameter("count");
        WriterPointRuleVO writerPointRuleVO = integralService.findWrterPointRulePointByRuleCode(rule_Code);
		Integer countInt = null;
		Integer tag;
		if(countBase!=null){
            BASE64Decoder base64dec = new BASE64Decoder();
            countInt=Integer.parseInt(new String(base64dec.decodeBuffer(request.getParameter("count"))).replaceAll("\\\"",""));

        }else{
            responseBean.setMsg("您输入兑换数量的为空");
            responseBean.setCode(0);
        }

        Map<String, Object> writerPoint = integralService.findWriterPointByid(userId);
		Integer totalCount = MapUtils.getInteger(writerPoint, "total");
		//byte[] countByte = Base64.decodeBase64(countBase.getBytes("UTF-8"));
		//Integer count = new Integer(new String(countByte));//获得解密后的用户名

		totalCount=totalCount-countInt;
		if(totalCount<0){
			responseBean.setMsg("兑换积分数大于您当前的总积分");
			responseBean.setCode(0);
		}else if(totalCount>=0){
            Map<String, Object> writerPointByid = integralService.findWriterPointByid(userId);
            Integer total = MapUtils.getInteger(writerPointByid, "total");
            total=total-countInt;
            Integer loss = MapUtils.getInteger(writerPointByid, "loss")==null?0:MapUtils.getInteger(writerPointByid, "loss");
            loss+=countInt;
            HashMap<String, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("loss", loss);
            objectObjectHashMap.put("total",total);
            objectObjectHashMap.put("id", userId);
            tag=integralService.PointChange(objectObjectHashMap);
            Map<String,Object> pointLog= new HashMap<>();
            pointLog.put("user_id", userId);
            pointLog.put("rule_id", writerPointRuleVO.getId());
            pointLog.put("point", countInt-(countInt*2));
            integralService.addPointlog(pointLog);

            Integer code = 1;
            if(tag>0){
                StringBuilder sb=new StringBuilder();
                sb.append("{\"staff_code\":\""+usermap.get("username")+"\",");
                if(countInt!=null){
                    Integer point = writerPointRuleVO.getPoint();
                    Integer exchangePoint = writerPointRuleVO.getExchangePoint();
                    double v = exchangePoint.doubleValue();
                    double v1 = point.doubleValue();
                    Double d=v/v1;
                    if(countInt%point==0){
                        Integer res= (int) ((countInt*1.00)*d);
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
              code= jsonObject.getInteger("code");
                jsonObject.getString("msg");
            }



		if(code==0){
			responseBean.setMsg("积分兑换成功");

		}else{
		    responseBean.setCode(0);
            responseBean.setMsg("积分兑换失败");
        }
		}
		return responseBean;
	}


}
