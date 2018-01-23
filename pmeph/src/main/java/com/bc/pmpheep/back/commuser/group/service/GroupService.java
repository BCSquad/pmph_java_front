package com.bc.pmpheep.back.commuser.group.service;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.bc.pmpheep.back.commuser.group.bean.GroupFileVO;
import com.bc.pmpheep.back.commuser.group.bean.GroupList;
import com.bc.pmpheep.back.commuser.group.bean.GroupMember;
import com.bc.pmpheep.back.commuser.group.bean.GroupMessageVO;
import com.bc.pmpheep.back.commuser.group.bean.PmphGroupMemberVO;
import com.bc.pmpheep.service.exception.CheckedServiceException;


public interface GroupService {
	
	/**
	 * 小组上传文件信息保存
	 * @introduction 
	 * @author Mryang
	 * @createDate 2018年1月17日 上午10:58:46
	 * @param groupId
	 * @param fileId
	 * @param fileName
	 * @param fileSize
	 * @param thisId
	 * @return
	 * @throws CheckedServiceException
	 * @throws IOException
	 */
	Integer addFile(Long groupId,String fileId,String fileName,Long fileSize,Long thisId)throws CheckedServiceException , IOException;
	
	/**
	 * 退出小组
	 * @introduction 
	 * @author Mryang
	 * @createDate 2017年12月13日 上午11:33:52
	 * @param groupId
	 * @param thisId
	 * @return
	 * @throws CheckedServiceException
	 */
	Boolean quitGroup(Long groupId,Long thisId) throws CheckedServiceException;

	/**
	 * 获取某小组文件的文件总数
	 * @introduction 
	 * @author Mryang
	 * @createDate 2017年12月13日 上午10:13:15
	 * @param groupId
	 * @param fileName
	 * @return
	 */
	Integer getFilesTotal(Long groupId, String fileName,Long thisId) throws CheckedServiceException;

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
	List<GroupList> groupList(Integer start, Integer pageSize, Long id,String order) throws CheckedServiceException;
	
	/**
	 * 
	 * Description:前台获取文件小组列表
	 * @author:lyc
	 * @date:2017年12月13日 上午10:28:14
	 * @param 
	 * @return List<GroupFileVO>
	 */
	List<GroupFileVO> groupFiles (Integer pageNumber,Integer pageSize,Long groupId,String fileName,Long thisId,String order,String rank) throws CheckedServiceException;
	
	/**
	 * 删除我能删除的文件
	 * @introduction 
	 * @author Mryang
	 * @createDate 2017年12月13日 下午4:52:11
	 * @param groupId
	 * @param id
	 * @param fileId
	 * @param thisId
	 * @return
	 */
	Integer deleteFile(Long id,Long groupId,String fileId,Long thisId) throws CheckedServiceException ;
	
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
	Boolean isFounderOrisAdmin(String groupId, String memberId) throws CheckedServiceException;
	
	/**
	 * 
	 * Description:进行各种操作之前判断是否为创建者
	 * 
	 * @author:Administrator
	 * @date:2017年10月12日上午11:18:34
	 * @param
	 * @return Boolean
	 */
	Boolean isFounder(String groupId,  String memberId) throws CheckedServiceException;
	
	/**
	 * 获取小组讨论
	 * @introduction 
	 * @author Mryang
	 * @createDate 2017年12月12日 下午5:40:07
	 * @return
	 */
	List<GroupMessageVO> getTalks(Long thisId,Long groupId,Integer pageNumber,Integer pageSize);
	
	/**
	 * 查询小组消息
	 */
	public List<Map<String,Object>> messageList(Map<String,Object> map);
	/**
	 * 查询小组成员
	 */
	public List<Map<String,Object>> memberList(Map<String,Object> map);
	/**
	 * 查询成员总数
	 */
	public int countMember(Map<String,Object> map);
	/**
	 * 查询文章共享总数
	 */
	public int countFile(Map<String,Object> map);
	
	/**
	 * 获取小组名称
	 */
	public Map<String,Object> queryGroup(Map<String,Object> map);
	/**
	 * 添加发送的小组信息
	 */
	public int addMessage(Map<String,Object> map);
	
	/**
	 * 添加小组文件
	 */
	public int addFile(Map<String,Object> map);
	
	/**
	 * 查询文件信息
	 */
	public List<Map<String,Object>> fileList(Map<String,Object> map);
	
	/**
	 * 更新下载数
	 * @introduction 
	 * @author Mryang
	 * @createDate 2018年1月22日 下午3:29:33
	 * @param groupId
	 * @param fileId
	 * @return
	 */
	Integer updateDownload(Long groupId,String fileId);
	
	/**
	 * 根据用户user_id和is_writer 修改用户在小组里面的名字
	 * @introduction 
	 * @author Mryang
	 * @createDate 2018年1月22日 下午3:31:12
	 * @param map
	 * @return
	 */
	Integer updateName( Map<String, Object> map );
}
