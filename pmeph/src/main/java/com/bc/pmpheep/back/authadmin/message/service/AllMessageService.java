/**
 * create by xcy on 2017-12-7
 */
package com.bc.pmpheep.back.authadmin.message.service;

import java.util.List;
import java.util.Map;

/** 
 * @ClassName: AllMessage 
 * @Description: 机构用户 全部消息
 * @author xcy
 * @date 2017-12-7 上午11:31:57  
 */
public interface AllMessageService {

	/**
	 * 
	* @Title: getAllMessageInit 
	* @Description: 获取机构用户全部消息——初始化
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> getAllMessageInit(Map<String,Object> map);
}
