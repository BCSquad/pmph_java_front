/**
 * 
 */
package com.bc.pmpheep.general.dao;

import java.util.List;
import java.util.Map;

/**
 * <p>Title:敏感词数据访问层<p>
 * <p>Description:获取敏感词<p>
 * @author Administrator
 * @date 2018年2月1日 下午4:56:53
 */
public interface SensitiveDao {

	List<Map<String, Object>> getSensitives();
}
