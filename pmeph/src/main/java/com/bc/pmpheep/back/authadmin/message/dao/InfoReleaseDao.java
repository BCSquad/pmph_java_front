package com.bc.pmpheep.back.authadmin.message.dao;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;

/**
 * 
 * @ClassName: InfoReleaseDao
 * @Description: 查询选择消息发布对象(机构用户)
 * @author SunZhuoQun
 * @date 2017-12-5 上午9:52:21
 * 
 */
public interface InfoReleaseDao {

	// 查询选择消息发布对象(机构用户)

	/**
	 * 
	 * @Title: selectInfoRelease
	 * @Description: 查询列表
	 * @param @param paraMap
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> selectInfoRelease(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 
	 * @Title: selectMenu
	 * @Description: 下拉动态菜单
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> selectMenu();

	/**
	 * 
	 * @Title: selectInfoReleaseCount
	 * @Description: 查询数据条数
	 * @param @param paraMap
	 * @param @return
	 * @return Integer 返回类型
	 * @throws
	 */
	Integer selectInfoReleaseCount(PageParameter<Map<String, Object>> pageParameter);

}
