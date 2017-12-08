package com.bc.pmpheep.back.authadmin.message.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.message.dao.InfoReleaseDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.controller.bean.ResponseBean;

/**
 * 
 * @ClassName: InfoReleaseServiceImpl
 * @Description: 选择消息发布对象(机构用户) 实现类
 * @author SunZhuoQun
 * @date 2017-12-5 上午9:49:56
 * 
 */
@Service("com.bc.pmpheep.back.authadmin.message.service.InfoReleaseServiceImpl")
public class InfoReleaseServiceImpl implements InfoReleaseService {

	@Autowired
	InfoReleaseDao infoReleaseDao;

	/**
	 * 查询下拉动态菜单
	 */

	@Override
	public List<Map<String, Object>> selectMenu() {
		List<Map<String, Object>> list = infoReleaseDao.selectMenu();
		return list;
	}

	/**
	 * 查询列表
	 */
	@Override
	public List<Map<String, Object>> selectInfoRelease(
			PageParameter<Map<String, Object>> pageParameter) {
	/*	Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("startNum", pageParameter.getStart());
		paraMap.put("pageSize", pageParameter.getPageSize());
		paraMap.put("queryName", pageParameter.getParameter().get("queryName"));*/
		List<Map<String, Object>> resultList = infoReleaseDao
				.selectInfoRelease(pageParameter);
		return resultList;
	}

	/**
	 * 查询列表数据量
	 */
	@Override
	public int selectInfoReleaseCount(
			PageParameter<Map<String, Object>> pageParameter) {
		/*Map<String, Object> paraMap = pageParameter.getParameter();
		paraMap.put("queryName", pageParameter.getParameter().get("queryName"));*/
		Integer count = infoReleaseDao.selectInfoReleaseCount(pageParameter);
		Integer maxPageNum = (int) Math.ceil(1.0 * count
				/ pageParameter.getPageSize());
		return maxPageNum;
	}

}
