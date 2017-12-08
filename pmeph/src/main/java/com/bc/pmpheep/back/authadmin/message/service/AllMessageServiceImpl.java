/**
 * create by xcy on 2017-12-7
 */
package com.bc.pmpheep.back.authadmin.message.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.message.dao.AllMessageDao;

/** 
 * @ClassName: AllMessageServiceImpl 
 * @Description: TODO
 * @author xcy
 * @date 2017-12-7 上午11:33:05  
 */
@Service("com.bc.pmpheep.back.authadmin.message.service.AllMessageServiceImpl")
public class AllMessageServiceImpl implements AllMessageService {
	
	@Autowired
	AllMessageDao allMessageDao;

	/** 
	* @Title: getAllMessageInit 
	* @Description: 获取机构用户全部消息——初始化
	* @return   
	* @throws 
	*/
	@Override
	public List<Map<String, Object>> getAllMessageInit(Map<String, Object> map) {
		return allMessageDao.getAllMessageInit(map);
	}


}
