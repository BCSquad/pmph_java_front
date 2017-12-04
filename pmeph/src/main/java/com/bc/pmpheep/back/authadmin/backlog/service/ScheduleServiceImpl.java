package com.bc.pmpheep.back.authadmin.backlog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.backlog.dao.ScheduleDao;

@Service("com.bc.pmpheep.back.authadmin.backlog.service.ScheduleServiceImpl")
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	ScheduleDao scheduleDao;
	
	//查询待办事项列表
	@Override
	public List<Map<String,Object>> selectScheduleList(Map<String,Object> paraMap) {
		List<Map<String,Object>> list = scheduleDao.selectScheduleList(paraMap);
		return list;
	}
	
	//查询机构用户信息
	@Override
	public Map<String, Object> selectOrgUser(Long userId) {
		Map<String, Object> map = scheduleDao.selectOrgUser(userId);
		return map;
	}
	
	//查询待办数据条数
	@Override
	public int selectScheduleCount(Map<String, Object> paraMap) {
		int count = scheduleDao.selectScheduleCount(paraMap);
		return count;
	}
	
	//查询待办消息ID集合
	@Override
	public List<String> selectUserMessageList(Map<String, Object> paraMap) {
		List<String> list = scheduleDao.selectUserMessageList(paraMap);
		return list;
	}
	
	//查询已办事项消息id数量
	@Override
	public int selectUserMessageCount(Map<String, Object> paraMap) {
		int count = scheduleDao.selectUserMessageCount(paraMap);
		return count;
	}
	
	
	//查询已办事项的标题和时间
	@Override
	public List<Map<String,Object>> selectUserMessageNameAndTime(Map<String,Object> paraMap) {
		List<Map<String,Object>> list = scheduleDao.selectUserMessageNameAndTime(paraMap);
		return list;
	}
	

}
