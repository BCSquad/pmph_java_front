package com.bc.pmpheep.back.authadmin.backlog.dao;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

public interface ScheduleDao {
	
	//查询待办事项
	List<Map<String, Object>> selectScheduleList(PageParameter<Map<String, Object>> pageParameter);
	
	//查询机构用户信息
	Map<String, Object> selectOrgUser(Long userId);
	
	//查询待办数据条数
	int selectScheduleCount(Map<String, Object> paraMap);
	
	//查询消息id列表
	List<String> selectUserMessageList(Map<String, Object> paraMap);
	
	
	//int selectUserMessageCount(PageParameter<Map<String, Object>> pageParameter);
	
	
	//List<Map<String,Object>> selectUserMessageNameAndTime(PageParameter<Map<String, Object>> pageParameter);
	
	//查询待办数据条数
	int selectScheduleCount(PageParameter<Map<String, Object>> pageParameter);
	
	//查询已办事项列表
	List<Map<String, Object>> selectDoneSchedule(PageParameter<Map<String, Object>> pageParameter);
	
	//查询已办事项数量
	int selectDoneScheduleCount(PageParameter<Map<String, Object>> pageParameter);

}
