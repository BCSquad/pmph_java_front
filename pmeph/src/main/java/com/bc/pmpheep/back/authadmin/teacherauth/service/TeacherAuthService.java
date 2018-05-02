package com.bc.pmpheep.back.authadmin.teacherauth.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.controller.bean.ResponseBean;

/**
 *
 * @author liudi
 * 教师认证 服务层接口
 */
public interface TeacherAuthService {

	/**
	 * 查询认证列表
	 * @param pageParameter
	 * @return
	 */
	List<Map<String, Object>> queryTeacherAuthList(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 查询认证分页总页数
	 * @param pageParameter
	 * @return
	 */
	int queryTeacherAuthCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 修改主键为id的条目状态为status
	 * @param id
	 * @param status
	 * @param string 
	 * @return
	 */
	Map<String, Object> statusModify(String id, String status, String backReason, String orgId);



}
