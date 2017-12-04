package com.bc.pmpheep.back.authadmin.message.service;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.controller.bean.ResponseBean;

public interface InfoReleaseService {
	
	//查询待办事项列表
	List<Map<String,Object>> selectInfoRelease(PageParameter<Map<String, Object>> pageParameter);
	
	List<Map<String,Object>> selectMenu();
	
	//查询列表数据量
	int selectInfoReleaseCount(PageParameter<Map<String, Object>> pageParameter);
	
	
	
}
