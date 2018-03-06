package com.bc.pmpheep.back.authadmin.teacherauth.dao;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;
import org.apache.ibatis.annotations.Param;

/**
 * 教师认证dao接口
 * @author Administrator
 *
 */
public interface TeacherAuthDao {
	/**
	 * 查询认证列表
	 * @param pageParameter
	 * @return
	 */
	List<Map<String, Object>> queryTeacherAuthList(PageParameter<Map<String, Object>> pageParameter);
	
	/**
	 * 查询认证总数
	 * @param pageParameter
	 * @return
	 */
	Integer queryTeacherAuthCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 修改状态
	 * @param paraMap
	 * @return
	 */
	Integer statusModify(Map<String, Object> paraMap);


	Map<String, Object> getSchoolInfo(@Param("orgId") String id);

	Map<String, Object> getWriterId(@Param("id") String id);
}
