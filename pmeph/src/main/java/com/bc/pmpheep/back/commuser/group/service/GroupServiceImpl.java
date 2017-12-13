/**
 * 
 */
package com.bc.pmpheep.back.commuser.group.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bc.pmpheep.back.commuser.group.bean.Group;
import com.bc.pmpheep.back.commuser.group.bean.GroupFile;
import com.bc.pmpheep.back.commuser.group.bean.GroupList;
import com.bc.pmpheep.back.commuser.group.bean.GroupMember;
import com.bc.pmpheep.back.commuser.group.bean.GroupMessageVO;
import com.bc.pmpheep.back.commuser.group.bean.PmphGroupMemberVO;
import com.bc.pmpheep.back.commuser.group.dao.GroupDao;
import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.back.util.StringUtil;
import com.bc.pmpheep.general.bean.FileType;
import com.bc.pmpheep.general.service.FileService;
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
	@Autowired
	private FileService fileService;
	
	@Override
	public List<GroupMessageVO> getTalks(Long thisId,Long groupId,Integer pageNumber,Integer pageSize){
		if(null == pageNumber || pageNumber < 1){
    		pageNumber = 1 ;
    	}
    	if(null == pageSize   || pageSize < 1) {
    		pageSize = 100000 ;  //默认100000,差不多就是查询全部
    	}
		return groupDao.getTalks(thisId, groupId , (pageNumber-1)*pageSize ,pageSize);
	}
	
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
			for(String avatar: avatars){
				avatar = RouteUtil.userAvatar(avatar);
			}
			List<GroupMessageVO> messages = groupDao.getMessages(groupId);
			String gruopImage =  group.getGroupImage();
			group.setGroupImage(RouteUtil.gruopImage(gruopImage));
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
			Long groupId, String fileName) {
		if (ObjectUtil.isNull(groupId)){
			throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
					CheckedExceptionResult.NULL_PARAM, "小组id不能为空");
		}
		List<GroupFile> list = groupDao.getFiles(start, pageSize, groupId, fileName);
		return list;
	}

	@Override
	public String deleteFile(List<GroupFile> list, Long userId)
			throws CheckedServiceException {
		if (ObjectUtil.isNull(userId)){
			throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
					CheckedExceptionResult.NULL_PARAM, "当前用户id不能为空");
		}
		String result = "FAIL";
		for (GroupFile groupFile : list){
			if (ObjectUtil.isNull(groupFile.getId())){
				throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
						CheckedExceptionResult.NULL_PARAM, "文件id不能为空");
			}
			if (ObjectUtil.isNull(groupFile.getGroupId())){
				throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
						CheckedExceptionResult.NULL_PARAM, "小组id不能为空");
			}
			//判断当前用户是否为管理员，若为管理员，可以删除除创建者和其他管理员以外的人上传的文件
			if (isFounderOrisAdmin(groupFile.getGroupId(), userId)){
				if (isFounderOrisAdmin(groupFile.getGroupId(), groupFile.getMemberId())){
					throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
							CheckedExceptionResult.ILLEGAL_PARAM, "无法删除其他管理员或创建者所上传的文件");
				}else{
					groupDao.deleteFile(groupFile.getId());
					result = "SUCCESS";
				}
				//若不为管理员，只能删除自己上传的文件
			} else {
				if (userId != groupFile.getMemberId()){
					throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
							CheckedExceptionResult.ILLEGAL_PARAM, "没有此操作权限,只能删除自己上传的文件");
				 }else{
					groupDao.deleteFile(groupFile.getId());
					result = "SUCCESS";
				 }
			}
		}
		return result;
	}

	@Override
	public GroupMember getGroupMember(Long groupId, Long userId) throws CheckedServiceException {
		if (ObjectUtil.isNull(userId)){
			throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
					CheckedExceptionResult.NULL_PARAM, "小组成员id不能为空");
		}
		GroupMember groupMember = groupDao.getGroupMember(groupId, userId);
		//以防万一所加的判断，但是前台功能正常来说是绝对不会走这个判断，用户点进这个小组页面是肯定属于此小组的
		if (ObjectUtil.isNull(groupMember)){
			throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
					CheckedExceptionResult.NULL_PARAM, "你不是该小组成员");
		}
		return groupMember;
	}
	
	@Override
	public String addGroupFiles(Long[] groupIds, MultipartFile file, Long userId)
			throws CheckedServiceException,IOException {
		if (ObjectUtil.isNull(userId)){
			throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
					CheckedExceptionResult.NULL_PARAM, "当前用户id不能为空");
		}
		if (ObjectUtil.isNull(groupIds) || groupIds.length == 0){
			throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
					CheckedExceptionResult.NULL_PARAM, "参数不能为空");
		}
		if (ObjectUtil.isNull(file)){
			throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
					CheckedExceptionResult.NULL_PARAM, "找不到文件");
		}
		List<GroupFile> list = new ArrayList<>();
		for (Long groupId : groupIds){
			if (ObjectUtil.isNull(groupId)){
				throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
						CheckedExceptionResult.NULL_PARAM, "小组id不能为空");
			}
			GroupMember groupMember = getGroupMember(groupId, userId);
			GroupFile groupFile = new GroupFile(groupId, groupMember.getId(), "0" + groupMember.getId(),
					file.getOriginalFilename(), 0, null);
			groupDao.addGroupFile(groupFile);
			list.add(groupFile);
		}
		String fileId = fileService.save(file, FileType.GROUP_FILE, list.get(0).getId());
		for (GroupFile groupFile : list){
			groupFile.setFileId(fileId);
			groupDao.updateGroupFile(groupFile);
			GroupMember groupMember = groupDao.getGroupMember(groupFile.getGroupId(), userId);
			addGroupMessage(groupMember.getDisplayName() + "共享了文件" + file.getOriginalFilename(),
					groupFile.getGroupId());
		}
		return null;
	}
	
	@Override
	public List<PmphGroupMemberVO> listPmphGroupMember(Long groupId, Long memberId)
			throws CheckedServiceException {
    	List<PmphGroupMemberVO> list = groupDao.listPmphGroupMember(groupId);
		for (PmphGroupMemberVO pmphGroupMemberVO : list) {
			if (pmphGroupMemberVO.getIsWriter()) {
				//用户头像
				//pmphGroupMemberVO.setAvatar(RouteUtil.userAvatar(writerUserService.get(pmphGroupMemberVO.getUserId()).getAvatar()));
				pmphGroupMemberVO.setUserType(Const.SENDER_TYPE_2);
			} else {
				//用户头像
				//pmphGroupMemberVO.setAvatar(RouteUtil.userAvatar(pmphUserService.get(pmphGroupMemberVO.getUserId()).getAvatar()));
				pmphGroupMemberVO.setUserType(Const.SENDER_TYPE_1);
			}
		}
		return list;
	}
    
    @Override
	public Boolean isFounderOrisAdmin(Long groupId,  Long memberId) throws CheckedServiceException {
		boolean flag = false;
		PmphGroupMemberVO currentUser = groupDao.getPmphGroupMemberByMemberId(groupId, memberId);
		if(null == currentUser){
			return flag;
		}
		if (currentUser.getIsFounder() || currentUser.getIsAdmin()) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Boolean isFounder(Long groupId, Long memberId ) throws CheckedServiceException {
		boolean flag = false;
		PmphGroupMemberVO currentUser = groupDao.getPmphGroupMemberByMemberId(groupId, memberId);
		
		if(null == currentUser){
			return flag;
		}
		if (currentUser.getIsFounder()) {
			flag = true;
		}
		return flag;
	}

	@Override
	public String addGroupMessage(String msgConrent, Long groupId) throws CheckedServiceException,IOException {
		GroupMessageVO groupMessage = new GroupMessageVO(groupId, 0L, msgConrent);
		groupDao.addGroupMessage(groupMessage);
		groupMessage = groupDao.getGroupMessageById(groupMessage.getId());
		Group group = new Group();
		group.setId(groupId);
		group.setGmtLastMessage(groupMessage.getGmtCreate());
		groupDao.updateGroup(group);
		return "SUCCESS";
	}

	@Override
	public Integer updateGroupFileOfDown(Long groupId, String fileId)
			throws CheckedServiceException {
		if (ObjectUtil.isNull(groupId)){
			throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
					CheckedExceptionResult.NULL_PARAM, "小组id不能为空");
		}
		if (StringUtil.isEmpty(fileId)){
			throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
					CheckedExceptionResult.NULL_PARAM, "文件id不能为空");
		}	
		return groupDao.updateGroupFileOfDownload(groupId, fileId);
	}


	
}
