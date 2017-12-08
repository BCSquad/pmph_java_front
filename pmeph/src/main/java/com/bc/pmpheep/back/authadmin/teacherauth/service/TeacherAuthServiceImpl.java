package com.bc.pmpheep.back.authadmin.teacherauth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.teacherauth.dao.TeacherAuthDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.controller.bean.ResponseBean;
/**
 * 教师认证服务层实现类
 * @author liudi
 *
 */
@Service("com.bc.pmpheep.back.authadmin.teacherauth.service.TeacherAuthServiceImpl")
public class TeacherAuthServiceImpl implements TeacherAuthService {

	@Autowired
	TeacherAuthDao teacherAuthDao;
	
	@Override
	public List<Map<String, Object>> queryTeacherAuthList(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String,Object>> resultList = teacherAuthDao.queryTeacherAuthList(pageParameter);
		return resultList;
	}

	@Override
	public int queryTeacherAuthCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count =teacherAuthDao.queryTeacherAuthCount(pageParameter);
		Integer maxPageNum = (int) Math.ceil(1.0*count/pageParameter.getPageSize());
		return maxPageNum;
	}

	@Override
	public Map<String,Object> statusModify(String id,String status) {
		Map<String,Object> rb = new HashMap<String,Object>();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("id", id);
		paraMap.put("status", status);
		Integer count = teacherAuthDao.statusModify(paraMap);
		rb.put("count", count);
		return rb;
	}
	
}
