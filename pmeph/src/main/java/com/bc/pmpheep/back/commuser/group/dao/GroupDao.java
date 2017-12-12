package com.bc.pmpheep.back.commuser.group.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.group.bean.GroupFile;
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
	 * 
	 * 
	 * 功能描述：前台查询小组列表
	 *
	 * @param start
	 *            起始条数
	 * @param pageSize
	 *            当页条数
	 * @param id
	 *            用户id
	 * @return
	 *
	 */
	List<GroupList> list(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("id") Long id);

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
	 * @author:lyc
	 * @date:2017年12月8日下午4:47:42
	 * @param 
	 * @return List<GroupFile>
	 */
	List<GroupFile> getFiles(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("groupId") Long groupId, @Param("fileName")String fileName);
	
	/**
	 * 
	 * Description:删除小组共享文件
	 * @author:lyc
	 * @date:2017年12月11日上午9:26:31
	 * @param 
	 * @return Integer
	 */
	Integer deleteFile(@Param("id")Long id);
	
	/**
	 * 
	 * Description:根据当前用户id查找成员
	 * 
	 * @author:lyc
	 * @date:2017年10月12日下午2:52:57
	 * @Param:小组内成员id
	 * @Return:PmphGroupMember
	 */
	PmphGroupMemberVO getPmphGroupMemberByMemberId(@Param("groupId") Long groupId, @Param("userId") Long userId);
	
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
	Long addGroupFile(GroupFile groupFile);
	
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
	Integer updateGroupFile(GroupFile groupFile);
}
