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
    /**
     * 根据系统消息内容id更改系统消息是否已读
     * @param mid
     * @param userid 
     */
	@Override
	public int updateIsRead(String mid, String userid) {
		// TODO Auto-generated method stub
		return allMessageDao.updateIsRead(mid,userid);
	}
	/**
	 * 删除消息
	 * @param parameter
	 * @param string
	 */
	@Override
	public int deletemsg(String mid, String userid) {
		// TODO Auto-generated method stub
		return allMessageDao.deletemsg(mid,userid);
	}


}
