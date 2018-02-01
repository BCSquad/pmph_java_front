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
 * <p>Title:验证敏感词汇<p>
 * <p>Description:验证前台用户提交的评论与文章是否存在敏感词汇<p>
 * @author lyc
 * @date 2018年1月31日 下午3:14:50
 */
@Service("com.bc.pmpheep.general.service.SensitiveService")
public class SensitiveService {
	private static final String regexScript = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
	private static final String regexStyle = "<style[^>]*?>[\\s\\S]*?<\\/style>";//定义style的正则表达式
	private static final String regexHTML = "<[^>]+>";//定义HTML标签的正则表达式
	private static final String regexSpace = "\\s*|\t|\r|\n";//定义空格回车换行符

	@Autowired
	SensitiveDao sensitiveDao;
	
	/**
	 * 
	 * Description:验证文章或评论是否包含敏感词汇
	 * @author:lyc
	 * @date:2018年1月31日下午3:19:18
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
		List<Map<String, Object>> sensitives = sensitiveDao.getSensitive();
		for (Map<String,Object> map : sensitives){
			String word = (String) map.get("word");
			if (content.indexOf(word)!= -1){
				flag = true;
				break;
			}
		}
		return flag;		
	}
	
	/**
	 * 
	 * Description:返回敏感词汇集合给前台
	 * @author:lyc
	 * @date:2018年1月31日下午4:04:18
	 * @param title 文章标题（用于随笔文章发表，评论为null）
	 *         content 文章内容或评论
	 * @return List<String>
	 */
	public List<String> getSensitives(String title, String content){
		if (StringUtil.isEmpty(content)){
			throw new CheckedServiceException(CheckedExceptionBusiness.CMS,
					CheckedExceptionResult.NULL_PARAM, "内容不能为空");
		}
		content = delHTMLTag(content);
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> sensitives = sensitiveDao.getSensitive();
        for (Map<String, Object> map : sensitives){
        	String word = (String) map.get("word");
        	if (StringUtil.notEmpty(title)){
        		title = delHTMLTag(title);
        		if (title.indexOf(word) != -1){
        			list.add(word);
        		}
        	}
        	if (content.indexOf(word) != -1){
        		list.add(word);
        	}
        }
		return list;
	}

	/**
	 * 
	 * Description:过滤内容中的HTML标签
	 * @author:lyc
	 * @date:2018年2月1日下午3:22:46
	 * @param 
	 * @return String
	 */
	public String delHTMLTag(String str){
		Pattern pScript = Pattern.compile(regexScript,Pattern.CASE_INSENSITIVE);
		Matcher mScript = pScript.matcher(str);
		str = mScript.replaceAll("");
		
		Pattern pStyle = Pattern.compile(regexStyle, Pattern.CASE_INSENSITIVE);
		Matcher mStyle = pStyle.matcher(regexStyle);
		str = mStyle.replaceAll("");
		
		Pattern pHTML = Pattern.compile(regexHTML, Pattern.CASE_INSENSITIVE);
		Matcher mHTML = pHTML.matcher(regexHTML);
		str = mHTML.replaceAll("");
		
		Pattern pSpace = Pattern.compile(regexSpace, Pattern.CASE_INSENSITIVE);
		Matcher mSpace = pSpace.matcher(regexSpace);
		str = mSpace.replaceAll("");
		
		str = str.replaceAll(" ", "");
		return str;
	}
	
}
