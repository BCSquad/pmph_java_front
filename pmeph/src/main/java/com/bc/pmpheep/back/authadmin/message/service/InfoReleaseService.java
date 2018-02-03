package com.bc.pmpheep.back.authadmin.message.service;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.controller.bean.ResponseBean;
/**
 * 
* @ClassName: InfoReleaseService 
* @Description: 选择消息发布对象(机构用户)  接口
* @author SunZhuoQun
* @date 2017-12-5 上午9:50:23 
*
 */
public interface InfoReleaseService {
	/**
	 * 
	 * @Title: selectInfoRelease
	 * @Description: 查询列表
	 * @param @param pageParameter
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> selectInfoRelease(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 
	 * @Title: selectMenu
	 * @Description: 查询下拉动态菜单
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> selectMenu();

	/**
	 * 
	 * @Title: selectInfoReleaseCount
	 * @Description: 查询列表数据量
	 * @param @param pageParameter
	 * @param @return
	 * @return int 返回类型
	 * @throws
	 */
	int selectInfoReleaseCount(PageParameter<Map<String, Object>> pageParameter);

}
