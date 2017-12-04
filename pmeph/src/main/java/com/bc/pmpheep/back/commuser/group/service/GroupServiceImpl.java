/**
 * 
 */
package com.bc.pmpheep.back.commuser.group.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
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
			String createTime = (new SimpleDateFormat("yyyy年MM月dd日").format(group.getCreateTime()));
			Integer members = groupDao.getMemberTotal(groupId);
			Integer files = groupDao.getFileTotal(groupId);
			List<String> avatars = groupDao.getAvatars(groupId);
			List<GroupMessage> messages = groupDao.getMessages(groupId);
			group.setCreateTime(createTime);
			group.setMembers(members);
			group.setFiles(files);
			group.setAvatars(avatars);
			group.setGroupMassages(messages);
			groupList.add(group);
		}		
		return groupList;
	}

}
