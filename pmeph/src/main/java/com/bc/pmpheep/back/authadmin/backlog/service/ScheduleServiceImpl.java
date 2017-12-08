package com.bc.pmpheep.back.authadmin.backlog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.backlog.dao.ScheduleDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.general.pojo.Message;

@Service("com.bc.pmpheep.back.authadmin.backlog.service.ScheduleServiceImpl")
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	ScheduleDao scheduleDao;
	
	//查询待办事项列表
	@Override
	public PageResult<Map<String, Object>> selectScheduleList(PageParameter<Map<String, Object>> pageParameter) {
		PageResult<Map<String, Object>> pageResult= new PageResult<Map<String,Object>>();
		pageResult.setPageNumber(pageParameter.getPageNumber());
		pageResult.setPageSize(pageParameter.getPageSize());
		
		List<Map<String, Object>> list = scheduleDao.selectScheduleList(pageParameter);
		int count = scheduleDao.selectScheduleCount(pageParameter);
		
		pageResult.setRows(list);
		pageResult.setTotal(count);
		return pageResult;
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
	/*@Override
	public List<String> selectUserMessageList(Map<String, Object> paraMap) {
		List<String> list = scheduleDao.selectUserMessageList(paraMap);
		return list;
	}*/
	
	//查询已办事项消息id数量
	/*@Override
	public int selectUserMessageCount(Map<String, Object> paraMap) {
		int count = scheduleDao.selectUserMessageCount(paraMap);
		return count;
	}*/
	
	
	//查询已办事项
	@Override
	public PageResult<Map<String, Object>> selectDoneSchedule(PageParameter<Map<String, Object>> pageParameter) {
		PageResult<Map<String,Object>> pageResult = new PageResult<Map<String,Object>>();
		pageResult.setPageNumber(pageParameter.getPageNumber());
		pageResult.setPageSize(pageParameter.getPageSize());
		
		List<Map<String,Object>> listNameAndTime = scheduleDao.selectDoneSchedule(pageParameter);
		/*List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> NT:listNameAndTime){
			//已办事项内容
			Message message = mssageService.get((String)NT.get("msg_id"));
			if(null!=message&&!message.equals("")){
				NT.put("content",message.getContent());
			}
			list.add(NT);
		}*/
		int count = scheduleDao.selectDoneScheduleCount(pageParameter);
		pageResult.setRows(listNameAndTime);
		pageResult.setTotal(count);
		
		return pageResult;
	}


}
