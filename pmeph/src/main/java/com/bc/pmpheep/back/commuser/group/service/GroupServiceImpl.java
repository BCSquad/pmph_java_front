/**
 * 
 */
package com.bc.pmpheep.back.commuser.group.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.group.bean.GroupFile;
import com.bc.pmpheep.back.commuser.group.bean.GroupList;
import com.bc.pmpheep.back.commuser.group.bean.GroupMessage;
import com.bc.pmpheep.back.commuser.group.dao.GroupDao;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * <p>Title:GroupServiceImpl<p>
 * <p>Description:前台小组模块功能业务<p>
 * @author lyc
 * @date 2017年12月2日 下午3:38:03
 */
@Service("com.bc.pmpheep.back.commuser.group.service.GroupServiceImpl")
public class GroupServiceImpl implements GroupService{

	@Autowired
	private GroupDao groupDao;
	
	@Override
	public List<GroupList> groupList(Integer start, Integer pageSize, Long id)
			throws CheckedServiceException {
		if (ObjectUtil.isNull(id)){
			throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
					CheckedExceptionResult.NULL_PARAM, "用户id不能为空");
		}
		List<GroupList> groups = groupDao.list(start, pageSize, id);
		List<GroupList> groupList = new ArrayList<>();
		for (GroupList group : groups){
			Long groupId = group.getId();
			String temp = group.getGmtCreate() ;
			String createTime = temp.substring(0,4)+"年"+temp.substring(5,7)+"月"+temp.substring(8,10)+"日";
			Integer members = groupDao.getMemberTotal(groupId);
			Integer files = groupDao.getFileTotal(groupId);
			List<String> avatars = groupDao.getAvatars(groupId);
			List<GroupMessage> messages = groupDao.getMessages(groupId);
			group.setGmtCreate(createTime);
			group.setMembers(members);
			group.setFiles(files);
			group.setAvatars(avatars);
			group.setGroupMassages(messages);
			groupList.add(group);
		}		
		return groupList;
	}

	@Override
	public List<GroupFile> groupFiles(Integer start, Integer pageSize,
			Long groupId, Long id, String fileName) {
		if (ObjectUtil.isNull(groupId)){
			throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
					CheckedExceptionResult.NULL_PARAM, "小组id不能为空");
		}
		if (ObjectUtil.isNull(id)){
			throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
					CheckedExceptionResult.NULL_PARAM, "用户id不能为空");
		}
		return null;
	}

}
