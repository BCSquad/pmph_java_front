/**
 * create by xcy on 2017-12-7
 */
package com.bc.pmpheep.back.authadmin.message.dao;

import java.util.List;
import java.util.Map;

/** 
 * @ClassName: AllMessageDao 
 * @Description: TODO
 * @author xcy
 * @date 2017-12-7 上午11:33:23  
 */
public interface AllMessageDao {

	/** 
	* @Title: getAllMessageInit 
	* @Description: 获取机构用户全部消息——初始化
	* @return   
	* @throws 
	*/
	public List<Map<String, Object>> getAllMessageInit(Map<String, Object> map);

}
