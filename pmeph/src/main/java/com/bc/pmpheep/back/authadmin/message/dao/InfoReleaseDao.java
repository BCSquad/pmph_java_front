package com.bc.pmpheep.back.authadmin.message.dao;

import java.util.List;
import java.util.Map;

public interface InfoReleaseDao {
	
	//查询选择消息发布对象(机构用户)
	List<Map<String,Object>> selectInfoRelease(Map<String, Object> paraMap);
	
	List<Map<String,Object>> selectMenu();
	
	//查询数据条数
	Integer selectInfoReleaseCount(Map<String, Object> paraMap);
	
	

}
