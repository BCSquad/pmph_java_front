/**
 * 
 */
package com.bc.pmpheep.general.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> sensitives = sensitiveDao.getSensitive();
        for (Map<String, Object> map : sensitives){
        	String word = (String) map.get("word");
        	if (StringUtil.notEmpty(title)){
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

}
