package com.bc.pmpheep.back.commuser.group.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.group.bean.Group;
import com.bc.pmpheep.back.commuser.group.bean.GroupFileVO;
import com.bc.pmpheep.back.commuser.group.bean.GroupList;
import com.bc.pmpheep.back.commuser.group.bean.GroupMember;
import com.bc.pmpheep.back.commuser.group.bean.GroupMessageVO;
import com.bc.pmpheep.back.commuser.group.bean.PmphGroupMemberVO;



/**
 * PmphGroup 实体类数据访问层接口
 * 
 * @author mryang
 */
public interface GroupDao {
	
	/**
	 * 查询我在这个小组上传的文件
	 * @introduction 
	 * @author Mryang
	 * @createDate 2017年12月13日 上午11:47:24
	 * @param groupId
	 * @param userId
	 * @return
	 */
	List<GroupFileVO> getMyFiles(@Param("groupId")Long groupId,@Param("userId")Long userId);
	
	/**
	 * 根据用户id删除文件
	 * @introduction 
	 * @author Mryang
	 * @createDate 2017年12月13日 上午11:38:39
	 * @param userId
	 * @return
	 */
	Integer deletePmphGroupFile(@Param("groupId")Long groupId,@Param("userId")Long userId);
	
	/**
	 * 根据用户id删除对话
	 * @introduction 
	 * @author Mryang
	 * @createDate 2017年12月13日 上午11:39:00
	 * @param userId
	 * @return
	 */
	Integer deleteMessage(@Param("groupId")Long groupId,@Param("userId")Long userId);
	
	/**
	 * 根据用户id删除成员
	 * @introduction 
	 * @author Mryang
	 * @createDate 2017年12月13日 上午11:39:06
	 * @param userId
	 * @return
	 */
	Integer deletePmphGroupMember(@Param("groupId")Long groupId, @Param("userId")Long userId);
	
	/**
	 * 
	 * 
	 * 功能描述：前台查询小组列表
	 *  @param order 排序字段  null时候 随机排序
	 * @param start
	 *            起始条数
	 * @param pageSize
	 *            当页条数
	 * @param id
	 *            用户id
	 * @return
	 *
	 */
	List<GroupList> list(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("id") Long id,@Param("order") String order);

	/**
	 * 
	 * 
	 * 功能描述：前台查询该用户的小组总数
	 *
	 * @param id
	 *            用户id
	 * 
	 * @return
	 *
	 */
	Integer getTotal(@Param("id") Long id);
	
	/**
	 * 获取小组讨论 
	 * @introduction 
	 * @author Mryang
	 * @createDate 2017年12月12日 下午5:38:47
	 * @return
	 */
	List<GroupMessageVO> getTalks(
			@Param("thisId")Long thisId,
			@Param("groupId")Long groupId,
			@Param("start")Integer start ,
			@Param("pageSize")Integer pageSize);

	/**
	 * 
	 * Description:统计小组人数
	 * @author:lyc
	 * @date:2017年12月2日下午5:06:04
	 * @param 小组id
	 * @return Integer
	 */
	Integer getMemberTotal(@Param("groupId") Long groupId);
	
	/**
	 * 
	 * Description:统计小组文件数
	 * @author:lyc
	 * @date:2017年12月2日下午5:09:31
	 * @param 小组id
	 * @return Integer
	 */
	Integer getFileTotal(@Param("groupId") Long groupId);
	
	/**
	 * 
	 * Description:获取小组中所有成员的头像
	 * @author:lyc
	 * @date:2017年12月4日下午3:14:09
	 * @param 
	 * @return List<String>
	 */
	List<String> getAvatars(@Param("groupId") Long groupId);
	
	/**
	 * 
	 * Description:获取小组动态
	 * @author:lyc
	 * @date:2017年12月4日下午3:34:58
	 * @param 
	 * @return List<String>
	 */
	List<GroupMessageVO> getMessages(@Param("groupId") Long groupId);
	
	/**
	 * 
	 * Description:获取小组文件列表
	 * @author:mryang
	 * @date:2017年12月8日下午4:47:42
	 * @param 
	 * @return List<GroupFile>
	 */
	List<GroupFileVO> getFiles(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("groupId") Long groupId, @Param("fileName")String fileName, @Param("thisId")Long thisId,@Param("order")String order,@Param("rank")String rank);
	
	/**
	 * 获取某小组文件的文件总数
	 * @introduction 
	 * @author Mryang
	 * @createDate 2017年12月13日 上午10:13:15
	 * @param groupId
	 * @param fileName
	 * @return
	 */
	Integer getFilesTotal(@Param("groupId") Long groupId,@Param("thisId")Long thisId ,@Param("fileName")String fileName);
	
	/**
	 * 
	 * Description:删除小组共享文件
	 * @author:lyc
	 * @date:2017年12月11日上午9:26:31
	 * @param 
	 * @return Integer
	 */
	Integer deleteMyPowerFile(@Param("id")Long id,@Param("thisId")Long thisId,@Param("groupId")Long groupId);
	
	/**
	 * 
	 * Description:根据当前用户id查找成员
	 * 
	 * @author:lyc
	 * @date:2017年10月12日下午2:52:57
	 * @Param:小组内成员id
	 * @Return:PmphGroupMember
	 */
	PmphGroupMemberVO getPmphGroupMemberByMemberId(String groupId, String userId);
	
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
	List<PmphGroupMemberVO> listPmphGroupMember(Long groupId);
	
	/**
	 * 
	 * Description:上传文件
	 * @author:lyc
	 * @date:2017年12月11日上午11:39:45
	 * @param 
	 * @return Long
	 */
	Integer addGroupFile(GroupFileVO groupFile);
	
	/**
	 * 
	 * Description:查询一个小组成员
	 * @author:lyc
	 * @date:2017年12月11日下午5:01:13
	 * @param 
	 * @return GroupMember
	 */
	GroupMember getGroupMember(@Param("groupId")Long groupId, @Param("userId")Long userId);
	
	/**
	 * 
	 * Description:修改小组文件
	 * @author:lyc
	 * @date:2017年12月11日下午5:45:04
	 * @param 
	 * @return Integer
	 */
	Integer updateGroupFile(GroupFileVO groupFile);
	
	/**
	 * 
	 * Description:添加小组信息
	 * @author:lyc
	 * @date:2017年12月12日下午3:43:22
	 * @param 
	 * @return Integer
	 */
	Integer addGroupMessage(GroupMessageVO groupMessage);
	
	/**
	 * 
	 * Description:获取小组信息
	 * @author:lyc
	 * @date:2017年12月12日下午4:05:40
	 * @param 
	 * @return GroupMessage
	 */
	GroupMessageVO getGroupMessageById(Long id);
	
	/**
	 * 
	 * Description:更新小组信息
	 * @author:lyc
	 * @date:2017年12月12日下午4:17:43
	 * @param 
	 * @return Integer
	 */
	Integer updateGroup(Group group);
	
	/**
	 * 
	 * Description:下载成功后下载次数+1
	 * @author:lyc
	 * @date:2017年12月12日下午4:31:02
	 * @param 
	 * @return Integer
	 */
	Integer updateGroupFileOfDownload(@Param("groupId") Long groupId, @Param("fileId") String fileId);
	
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
	 * 查询成员角色
	 */
	public Map<String,Object> memberRole(Map<String,Object> map);
	/**
	 * 查询小组信息
	 */
	public Map<String,Object> groupMap(Map<String,Object> map);
	
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
	 * 根据用户user_id和is_writer 修改用户在小组里面的名字
	 * @introduction 
	 * @author Mryang
	 * @createDate 2018年1月22日 下午3:31:12
	 * @param map
	 * @return
	 */
	Integer updateName( Map<String, Object> map );
    /**
     * 根据文件id查询文件（小组文件）
     * @param id
     * @return
     */
	Map<String, Object> queryGroupFileByFileId(@Param("id") String id);

	/**
	 *
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getAdminOrFounder(@Param("id") String id);


	Map<String, Object> getUserAndGroupInfo(@Param("groupid") String groupid,@Param("userid") String userid);

	/**
	 * 获取文件上传者id
	 * @param id
	 * @return
	 */
	String getMemberId(@Param("id") Long id);
}
