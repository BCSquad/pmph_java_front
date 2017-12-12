package com.bc.pmpheep.back.commuser.group.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.bc.pmpheep.back.commuser.group.bean.GroupFile;
import com.bc.pmpheep.back.commuser.group.bean.GroupList;
import com.bc.pmpheep.back.commuser.group.bean.GroupMember;
import com.bc.pmpheep.back.commuser.mygroup.bean.PmphGroupMemberVO;
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
	
	/**
	 * 
	 * Description:前台获取文件小组列表
	 * @author:lyc
	 * @date:2017年12月8日下午5:59:07
	 * @param 
	 * @return List<GroupFile>
	 */
	List<GroupFile> groupFiles(@Param("start") Integer start, @Param("pageSize") Integer pageSize, 
			@Param("groupId") Long groupId,  @Param("fileName") String fileName) throws CheckedServiceException;
	
	/**
	 * 
	 * Description:删除文件（可批量删除）
	 * @author:lyc
	 * @date:2017年12月11日上午9:35:21
	 * @param 
	 * @return Integer
	 */
	String deleteFile(List<GroupFile> list, @Param("userId")Long userId) throws CheckedServiceException;
	
	/**
	 * 
	 * Description:上传小组共享文件（可批量上传到多个小组）
	 * @author:lyc
	 * @date:2017年12月11日上午11:34:22
	 * @param 
	 * @return String
	 */
	String addGroupFiles(Long[] groupIds, MultipartFile file, Long userId) throws CheckedServiceException,IOException;
	
	/**
	 * 
	 * Description:获取一个小组成员信息
	 * @author:lyc
	 * @date:2017年12月11日下午5:21:45
	 * @param 
	 * @return GroupMember
	 */
	GroupMember getGroupMember(@Param("groupId")Long groupId, @Param("userId")Long userId) throws CheckedServiceException;
	/**
	 * 
	 * 
	 * 功能描述：根据小组id加载小组用户
	 *
	 * @param groupId
	 *            小组id
	 * @return 小组成员
	 *
	 */
	List<PmphGroupMemberVO> listPmphGroupMember(Long groupId, Long memberId) throws CheckedServiceException;
	
	/**
	 * 
	 * Description:添加小组信息
	 * @author:lyc
	 * @date:2017年12月12日下午3:56:57
	 * @param 
	 * @return String
	 */
	String addGroupMessage(String msgConrent, Long groupId) throws CheckedServiceException,IOException;
	
	/**
	 * 
	 * Description:更新小组文件下载次数
	 * @author:lyc
	 * @date:2017年12月12日下午4:34:43
	 * @param 
	 * @return Integer
	 */
	Integer updateGroupFileOfDown(@Param("groupId")Long groupId, @Param("fileId")String fileId) 
			throws CheckedServiceException;
	
	/**
	 * 
	 * Description:进行各种操作之前判断是否为创建者或管理者
	 * 
	 * @author:lyc
	 * @date:2017年10月12日上午11:18:08
	 * @param:
	 * @return:Boolean
	 */
	Boolean isFounderOrisAdmin(Long groupId, Long memberId) throws CheckedServiceException;
	
	/**
	 * 
	 * Description:进行各种操作之前判断是否为创建者
	 * 
	 * @author:Administrator
	 * @date:2017年10月12日上午11:18:34
	 * @param
	 * @return Boolean
	 */
	Boolean isFounder(Long groupId,  Long memberId) throws CheckedServiceException;
}
