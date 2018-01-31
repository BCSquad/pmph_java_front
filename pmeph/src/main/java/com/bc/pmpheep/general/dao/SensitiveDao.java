/**
 * 
 */
package com.bc.pmpheep.general.dao;

import java.util.List;
import java.util.Map;

/**
 * <p>Title:敏感词数据访问层<p>
 * <p>Description:验证前台用户提交的评论及文章是否存在敏感词汇<p>
 * @author lyc
 * @date 2018年1月31日 下午3:02:34
 */
public interface SensitiveDao {
	
	/**
	 * 
	 * Description:获取系统中的敏感词汇
	 * @author:lyc
	 * @date:2018年1月31日下午3:04:32
	 * @param 
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> getSensitive();

}
