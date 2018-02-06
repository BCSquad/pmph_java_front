/**
 * 
 */
package com.bc.pmpheep.general.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.util.StringUtil;
import com.bc.pmpheep.general.dao.SensitiveDao;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * <p>Title:敏感词<p>
 * <p>Description:敏感词过滤<p>
 * @author Administrator
 * @date 2018年2月1日 下午5:01:05
 */
@Service(("com.bc.pmpheep.general.service.SensitiveService"))
public class SensitiveService {
	private static final String regexScript = "<script[^>]*?>[\\s\\S]*?<\\/script>";//定义script的正则表达式
	private static final String regexStyle = "<style[^>]*?>[\\s\\S]*?<\\/style>";//定义style的正则表达式
	private static final String regexHTML = "<[^>]+>";//定义HTML的正则表达式
	private static final String regexSpace = "\\s*|\t|\r|\n";//定义空格回车换行符
	
	@Autowired
	SensitiveDao sensitiveDao;
	
	/**
	 * 
	 * Description:验证内容是否包含敏感词
	 * @author:lyc
	 * @date:2018年2月1日下午5:06:09
	 * @param 
	 * @return Boolean
	 */
	public Boolean confirmSensitive(String content){
		Boolean flag = false;
		if (StringUtil.isEmpty(content)){
			throw new CheckedServiceException(CheckedExceptionBusiness.CMS,
					CheckedExceptionResult.NULL_PARAM, "内容不能为空");
		}
		content = delHTMLTag(content);
		List<Map<String, Object>> sensitives = sensitiveDao.getSensitives();
		for (Map<String, Object> map : sensitives){
			String word = (String) map.get("word");
			if (content.indexOf(word) != -1){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * Description:找出内容中所包含的敏感词汇
	 * @author:lyc
	 * @date:2018年2月1日下午5:13:39
	 * @param 
	 * @return List<String>
	 */
	public List<String> getSensitives(String title, String content){
		if (StringUtil.isEmpty(content)){
			throw new CheckedServiceException(CheckedExceptionBusiness.CMS,
					CheckedExceptionResult.NULL_PARAM, "内容不能为空");
		}
		content = delHTMLTag(content);
		List<String> listSensitive = new ArrayList<>();
		List<Map<String, Object>> sensitives = sensitiveDao.getSensitives();
		for (Map<String, Object> sensitive : sensitives){
			String word = (String) sensitive.get("word");
			if (StringUtil.notEmpty(title)){
				title = delHTMLTag(title);
				if (title.indexOf(word) != -1){
					listSensitive.add(word);
					continue;
				}
			} 
			if (content.indexOf(word) != -1){
				listSensitive.add(word);
			}
		}
		return listSensitive;
	}

	/**
	 * 
	 * Description:去除内容中的HTML元素
	 * @author:lyc
	 * @date:2018年2月1日下午5:25:00
	 * @param 
	 * @return String
	 */
	public String delHTMLTag(String str){
		Pattern pScript = Pattern.compile(regexScript, Pattern.CASE_INSENSITIVE);
		Matcher mScript = pScript.matcher(str);
		str = mScript.replaceAll("");
		
		Pattern pStyle = Pattern.compile(regexStyle, Pattern.CASE_INSENSITIVE);
		Matcher mStyle = pStyle.matcher(str);
		str = mStyle.replaceAll("");
		
		Pattern pHTML = Pattern.compile(regexHTML,Pattern.CASE_INSENSITIVE);
		Matcher mHTML = pHTML.matcher(str);
		str = mHTML.replaceAll("");
		
		Pattern pSpace = Pattern.compile(regexSpace, Pattern.CASE_INSENSITIVE);
		Matcher mSpace = pSpace.matcher(str);
		str = mSpace.replaceAll("");
		str = str.replaceAll(" ", "");
		return str;
	}
}
