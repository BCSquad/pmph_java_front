/**
 * 
 */
package com.bc.pmpheep.back.commuser.group.service;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.commuser.mymessage.service.MyMessageService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bc.pmpheep.back.authadmin.usermanage.bean.PmphUser;
import com.bc.pmpheep.back.authadmin.usermanage.bean.WriterUser;
import com.bc.pmpheep.back.authadmin.usermanage.service.PmphUserService;
import com.bc.pmpheep.back.authadmin.usermanage.service.WriterUserService;
import com.bc.pmpheep.back.commuser.group.bean.Group;
import com.bc.pmpheep.back.commuser.group.bean.GroupFileVO	;
import com.bc.pmpheep.back.commuser.group.bean.GroupList;
import com.bc.pmpheep.back.commuser.group.bean.GroupMember;
import com.bc.pmpheep.back.commuser.group.bean.GroupMessageVO;
import com.bc.pmpheep.back.commuser.group.bean.PmphGroupMemberVO;
import com.bc.pmpheep.back.commuser.group.dao.GroupDao;
import com.bc.pmpheep.back.plugin.PageParameter;
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
 *
 * @author lyc
 * @date 2017年12月2日 下午3:38:03
 */
@Service("com.bc.pmpheep.back.commuser.group.service.GroupServiceImpl")
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.mymessage.service.MyMessageServiceImpl")
    MyMessageService myMessageService;

    @Autowired
    private FileService fileService;
    @Autowired
    @Qualifier("com.bc.pmpheep.back.authadmin.usermanage.service.WriterUserServiceImpl")
    private WriterUserService writerUserService;
    @Autowired
    @Qualifier("com.bc.pmpheep.back.authadmin.usermanage.service.PmphUserServiceImpl")
    private PmphUserService pmphUserService;

    @Override
    public Integer addFile(Long groupId, String fileId, String fileName, Long fileSize, Long thisId) throws CheckedServiceException, IOException {
        if (null == groupId) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP, CheckedExceptionResult.NULL_PARAM, "小组id不能为空");
        }
        if (StringUtil.isEmpty(fileId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP, CheckedExceptionResult.NULL_PARAM, "文件id不能为空");
        }
        if (StringUtil.isEmpty(fileName)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP, CheckedExceptionResult.NULL_PARAM, "文件名称不能为空");
        }
        if (null == fileSize) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP, CheckedExceptionResult.NULL_PARAM, "文件大小不能为空");
        }
        if (null == thisId) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP, CheckedExceptionResult.NULL_PARAM, "用户不能为空");
        }
        //获取成员id
        GroupMember groupMember = groupDao.getGroupMember(groupId, thisId);
        //创建对象
        GroupFileVO groupFileVO = new GroupFileVO(groupId, groupMember.getId(), fileId, fileName, 0, fileSize / 1024.0, null);
        //保存对象
        return groupDao.addGroupFile(groupFileVO);
    }

    @Override
    public Boolean quitGroup(Long groupId, Long thisId) throws CheckedServiceException {
        if (null == groupId) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP, CheckedExceptionResult.NULL_PARAM, "小组id不能为空");
        }
        if (null == thisId) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP, CheckedExceptionResult.NULL_PARAM, "用户不能为空");
        }
        List<GroupFileVO> myFiles = groupDao.getMyFiles(groupId, thisId);
        for (GroupFileVO file : myFiles) {
            String mongoId = file.getFileId();
            fileService.remove(mongoId);
        }


        List<Map<String, Object>> list = groupDao.getAdminOrFounder(groupId + "");
        Map<String, Object> userAndGroupInfp = groupDao.getUserAndGroupInfo(groupId+"", thisId+"");
        for (Map<String, Object> item : list) {
            if (MapUtils.getString(item, "is_writer", "").equals("true")) {
                myMessageService.sendMsg(new Short("0"), new Short("0"), 0L, new Short("2"), MapUtils.getLong(item, "user_id"), "系统消息",
                        "[" + MapUtils.getString(userAndGroupInfp, "username") + "]退出了[" + MapUtils.getString(userAndGroupInfp, "group_name") + "]小组");
            } else {
                myMessageService.sendMsg(new Short("0"), new Short("0"), 0L, new Short("1"), MapUtils.getLong(item, "user_id"), "系统消息",
                        "[" + MapUtils.getString(userAndGroupInfp, "username") + "]退出了[" + MapUtils.getString(userAndGroupInfp, "group_name") + "]小组");
            }
        }
        groupDao.deletePmphGroupFile(groupId, thisId);
        groupDao.deleteMessage(groupId, thisId);
        groupDao.deletePmphGroupMember(groupId, thisId);

        return true;
    }

    @Override
    public Integer getFilesTotal(Long groupId, String fileName, Long thisId) throws CheckedServiceException {
        if (null == groupId) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP, CheckedExceptionResult.NULL_PARAM, "小组id不能为空");
        }
        return groupDao.getFilesTotal(groupId, thisId, fileName);
    }

    @Override
    public List<GroupMessageVO> getTalks(Long thisId, Long groupId, Integer pageNumber, Integer pageSize) {
        if (null == pageNumber || pageNumber < 1) {
            pageNumber = 1;
        }
        if (null == pageSize || pageSize < 1) {
            pageSize = 100000;  //默认100000,差不多就是查询全部
        }
        List<GroupMessageVO> msgs = groupDao.getTalks(thisId, groupId, (pageNumber - 1) * pageSize, pageSize);
        for (GroupMessageVO msg : msgs) {
            String avatar = msg.getAvatar();
            msg.setAvatar(RouteUtil.userAvatar(avatar));
        }
        return msgs;
    }

    @Override
    public List<GroupList> groupList(Integer start, Integer pageSize, Long id, String order)
            throws CheckedServiceException {
        if (ObjectUtil.isNull(id)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
                    CheckedExceptionResult.NULL_PARAM, "用户id不能为空");
        }
        List<GroupList> groups = groupDao.list(start, pageSize, id, order);
        List<GroupList> groupList = new ArrayList<>();
        for (GroupList group : groups) {
            Long groupId = group.getId();
            String temp = group.getGmtCreate();
            String createTime = temp.substring(0, 4) + "年" + temp.substring(5, 7) + "月" + temp.substring(8, 10) + "日";
            Integer members = groupDao.getMemberTotal(groupId);
            Integer files = groupDao.getFileTotal(groupId);
            List<String> avatars = groupDao.getAvatars(groupId);
            for (int i = 0;i<avatars.size();i++) {
                String avatar = RouteUtil.userAvatar(avatars.get(i));
                avatars.remove(i);
                avatars.add(i,avatar);
            }
            List<GroupMessageVO> messages = groupDao.getMessages(groupId,id);
            String gruopImage = group.getGroupImage();
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
    public List<GroupFileVO> groupFiles(Integer pageNumber, Integer pageSize, Long groupId, String fileName, Long thisId, String order, String rank) {
        if (ObjectUtil.isNull(groupId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP, CheckedExceptionResult.NULL_PARAM, "小组id不能为空");
        }
        if (null == pageNumber || pageNumber < 1) {
            pageNumber = 1;
        }
        if (null == pageSize || pageSize < 1) {
            pageSize = 100000;  //默认100000,差不多就是查询全部
        }
        //设置排序规则
        if (StringUtil.isEmpty(order)) {
            order = null;
        } else {
            if (StringUtil.isEmpty(rank)) {
                rank = "asc";
            } else if ("asc".equals(rank.toLowerCase()) || "desc".equals(rank.toLowerCase())) {
                rank = rank.toLowerCase();
            } else {
                rank = "asc";
            }
        }
        List<GroupFileVO> list = groupDao.getFiles((pageNumber - 1) * pageSize, pageSize, groupId, fileName, thisId, order, rank);
        return list;
    }

    @Override
    public Integer deleteFile(Long id, Long groupId, String fileId, Long thisId) throws CheckedServiceException {
        return groupDao.deleteMyPowerFile(id, thisId, groupId);
    }

    /**
     * 判断是否有权限删除文件
     */
    @Override
    public boolean deleteFileAuthority(String groupId,String userId,Long id) throws CheckedServiceException {
        Map<String,Object> queryMap = new HashMap<String,Object>();
        queryMap.put("group_id", groupId);
        queryMap.put("user_id", userId);
        Boolean flag = false;
        Map<String,Object> map = groupDao.memberRole(queryMap);
        //获取登录人在小组中的id
        GroupMember groupMember = groupDao.getGroupMember(Long.parseLong(groupId), Long.parseLong(userId));

        String member_id = groupDao.getMemberId(id); //获取文件上传者id
        if(null == map){
            return flag;
        }
        if (((Boolean)map.get("is_founder"))||((Boolean)map.get("is_admin"))|| groupMember.getId().equals(member_id)) {
            flag = true;
        }

        return flag;
    }

    @Override
    public GroupMember getGroupMember(Long groupId, Long userId) throws CheckedServiceException {
        if (ObjectUtil.isNull(userId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
                    CheckedExceptionResult.NULL_PARAM, "小组成员id不能为空");
        }
        GroupMember groupMember = groupDao.getGroupMember(groupId, userId);
        //以防万一所加的判断，但是前台功能正常来说是绝对不会走这个判断，用户点进这个小组页面是肯定属于此小组的
        if (ObjectUtil.isNull(groupMember)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
                    CheckedExceptionResult.NULL_PARAM, "您不是该小组成员");
        }
        return groupMember;
    }



    @Override
    public String addGroupFiles(Long[] groupIds, MultipartFile file, Long userId)
            throws CheckedServiceException, IOException {
        if (ObjectUtil.isNull(userId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
                    CheckedExceptionResult.NULL_PARAM, "当前用户id不能为空");
        }
        if (ObjectUtil.isNull(groupIds) || groupIds.length == 0) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
                    CheckedExceptionResult.NULL_PARAM, "参数不能为空");
        }
        if (ObjectUtil.isNull(file)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
                    CheckedExceptionResult.NULL_PARAM, "找不到文件");
        }
        List<GroupFileVO> list = new ArrayList<>();
        for (Long groupId : groupIds) {
            if (ObjectUtil.isNull(groupId)) {
                throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
                        CheckedExceptionResult.NULL_PARAM, "小组id不能为空");
            }
            GroupMember groupMember = getGroupMember(groupId, userId);
            GroupFileVO groupFile = new GroupFileVO(groupId, groupMember.getId(), "0" + groupMember.getId(),
                    file.getOriginalFilename(), 0, file.getSize() / 1024.0, null);
            groupDao.addGroupFile(groupFile);
            list.add(groupFile);
        }
        String fileId = fileService.save(file, FileType.GROUP_FILE, list.get(0).getId());
        for (GroupFileVO groupFile : list) {
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
                WriterUser writerUser = writerUserService.get(pmphGroupMemberVO.getUserId());
                pmphGroupMemberVO.setAvatar(RouteUtil.userAvatar(writerUser == null ? "" : writerUser.getAvatar()));
                pmphGroupMemberVO.setUserType(Const.SENDER_TYPE_2);
            } else {
                //用户头像
                PmphUser pmphUser = pmphUserService.get(pmphGroupMemberVO.getUserId());
                pmphGroupMemberVO.setAvatar(RouteUtil.userAvatar(pmphUser == null ? "" : pmphUser.getAvatar()));
                pmphGroupMemberVO.setUserType(Const.SENDER_TYPE_1);
            }
        }
        return list;
    }

    /**
     * 判断成员角色
     */
    @Override
    public String isFounderOrisAdmin(String groupId,String memberId) throws CheckedServiceException {
        Map<String,Object> queryMap = new HashMap<String,Object>();
        queryMap.put("group_id", groupId);
        queryMap.put("user_id", memberId);
       // String flag = "您是这个小组的普通用户";
        String flag = "普通用户";
        Map<String,Object> map = groupDao.memberRole(queryMap);
        if(null == map){
            return flag;
        }
        if(((Boolean)map.get("is_admin"))){
            flag = "管理员";
        }
        if (((Boolean)map.get("is_founder")) ) {
            flag = "创建者";
        }
        
        return flag;
    }



    @Override
    public Boolean isFounder(String groupId, String memberId) throws CheckedServiceException {
        boolean flag = false;
        PmphGroupMemberVO currentUser = groupDao.getPmphGroupMemberByMemberId(groupId, memberId);

        if (null == currentUser) {
            return flag;
        }
        if (currentUser.getIsFounder()) {
            flag = true;
        }
        return flag;
    }

    @Override
    public String addGroupMessage(String msgConrent, Long groupId) throws CheckedServiceException, IOException {
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
        if (ObjectUtil.isNull(groupId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
                    CheckedExceptionResult.NULL_PARAM, "小组id不能为空");
        }
        if (StringUtil.isEmpty(fileId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
                    CheckedExceptionResult.NULL_PARAM, "文件id不能为空");
        }
        return groupDao.updateGroupFileOfDownload(groupId, fileId);
    }

    @Override
    public Integer updateDownload(Long groupId, String fileId) {
        if (ObjectUtil.isNull(groupId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
                    CheckedExceptionResult.NULL_PARAM, "小组id不能为空");
        }
        if (StringUtil.isEmpty(fileId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP,
                    CheckedExceptionResult.NULL_PARAM, "文件id不能为空");
        }
        return groupDao.updateGroupFileOfDownload(groupId, fileId);
    }

    @Override
    public List<Map<String, Object>> messageList(Map<String, Object> map) {
        return this.groupDao.messageList(map);
    }

    @Override
    public List<Map<String, Object>> memberList(Map<String, Object> map) {
        return this.groupDao.memberList(map);
    }

    @Override
    public int countMember(Map<String, Object> map) {
        return this.groupDao.countMember(map);
    }

    @Override
    public int countFile(Map<String, Object> map) {
        return this.groupDao.countFile(map);
    }

    @Override
    public Map<String, Object> queryGroup(Map<String, Object> map) {
        return this.groupDao.groupMap(map);
    }

    @Override
    public int addMessage(Map<String, Object> map) {
        return this.groupDao.addMessage(map);
    }

    @Override
    public int addFile(Map<String, Object> map) {
        return this.groupDao.addFile(map);
    }

    @Override
    public List<Map<String, Object>> fileList(Map<String, Object> map) {
        return this.groupDao.fileList(map);
    }

    @Override
    public Integer updateName(Map<String, Object> map) {
        if (null == map || null == map.get("user_id") || null == map.get("is_writer") || null == map.get("display_name")) {
            throw new CheckedServiceException(CheckedExceptionBusiness.GROUP, CheckedExceptionResult.NULL_PARAM, "更新参数不能为空");
        }
        return this.groupDao.updateName(map);
    }

    /**
     * 根据文件id查询文件（小组文件）
     */
    @Override
    public Map<String, Object> queryGroupFileByFileId(String id) {
        // TODO Auto-generated method stub
        return groupDao.queryGroupFileByFileId(id);
    }

	@Override
	public Map<String, Object> webSocketSentForIE(Map<String, Object> paraMap) {

		
		Map<String, Object> memberMap = groupDao.getMember(paraMap.get("senderId").toString(),paraMap.get("groupId").toString());
		paraMap.put("member_id", memberMap.get("id").toString());
		int count = groupDao.webSocketSentForIE(paraMap);
		String msgId = paraMap.get("msgId").toString();
		//新生成的消息
		Map<String, Object> msgMap = groupDao.getMsgById(msgId);
		

		if("0".equals(paraMap.get("senderType").toString()) || "0".equals(paraMap.get("senderId").toString()) ){
			//系统消息
			paraMap.put("sender", 0); 
	    }else if("2".equals(paraMap.get("senderType").toString()) || paraMap.get("logUserId").toString().equals(paraMap.get("senderId").toString()) 
	    		){//我自己的
	    	paraMap.put("sender", 1); 
	    }
		
		
		paraMap.put("senderName", memberMap.get("display_name"));
		paraMap.put("senderIcon", memberMap.get("avatar"));
		paraMap.put("time", msgMap.get("gmt_create"));
		
		//loadNewGroupMsg(sender,data.senderName,data.senderIcon,data.content,data.time);
		return paraMap;
	}

	@Override
	public List<Map<String, Object>> queryMemberListForManage(
			PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String, Object>> list = groupDao.queryMemberListForManage(pageParameter);
		return list;
	}

	@Override
	public int queryMemberListForManageCount(
			PageParameter<Map<String, Object>> pageParameter) {
		int count = groupDao.queryMemberListForManageCount(pageParameter);
		return count;
	}

	@Override
	public int updateDisplayName(Map<String, Object> paraMap) {
		int count = groupDao.updateDisplayName(paraMap);
		return count;
	}

    @Override
    public String deletePmphGroupMemberById(String id) {
        String returncode ="OK";
        int count=groupDao.deletePmphGroupMemberById(id);
        return returncode;
    }

    @Override
    public Map<String, Object> queryAdmin(String user_id, Long group_id) {
        Map<String, Object> map=groupDao.queryAdmin(user_id,group_id);
        return map;
    }
}
