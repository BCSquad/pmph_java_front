package com.bc.pmpheep.back.authadmin.backlog.service;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

public interface ScheduleService {
	
	//查询待办事项列表
	PageResult<Map<String, Object>> selectScheduleList(PageParameter<Map<String, Object>> pageParameter);
	
	//查询待办事项列表
	List<Map<String,Object>> selectUserMessageNameAndTime(Map<String, Object> paraMap);
	
	//查询机构用户信息
	Map<String, Object> selectOrgUser(Long userId);
	
	//查询列表数据量
	int selectScheduleCount(Map<String, Object> paraMap);
	
	//查询消息id列表
	List<String> selectUserMessageList(Map<String, Object> paraMap);
	
	//查询已办事项消息id数量
	int selectUserMessageCount(Map<String, Object> paraMap);

}
