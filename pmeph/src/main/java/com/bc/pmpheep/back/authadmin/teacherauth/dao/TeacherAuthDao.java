package com.bc.pmpheep.back.authadmin.teacherauth.dao;

import java.util.List;
import java.util.Map;
/**
 * 教师认证dao接口
 * @author Administrator
 *
 */
public interface TeacherAuthDao {
	/**
	 * 查询认证列表
	 * @param paraMap
	 * @return
	 */
	List<Map<String, Object>> queryTeacherAuthList(Map<String, Object> paraMap);
	
	/**
	 * 查询认证总数
	 * @param paraMap
	 * @return
	 */
	Integer queryTeacherAuthCount(Map<String, Object> paraMap);

	/**
	 * 修改状态
	 * @param paraMap
	 * @return
	 */
	Integer statusModify(Map<String, Object> paraMap);
	
}
