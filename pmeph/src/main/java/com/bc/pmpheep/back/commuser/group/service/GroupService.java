package com.bc.pmpheep.back.commuser.group.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.group.bean.GroupList;
import com.bc.pmpheep.service.exception.CheckedServiceException;

public interface GroupService {

	/**
	 * 
	 * Description:前台获取小组列表
	 * @author:lyc
	 * @date:2017年12月2日下午3:34:42
	 * @param start 起始条数
	 * @param pageSize 当页条数
	 * @param id 用户id
	 * @return List<GroupList>
	 */
	List<GroupList> groupList(@Param("start") Integer start, @Param("pageSize") Integer pageSize, 
			@Param("id") Long id) throws CheckedServiceException;
}
