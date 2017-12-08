package com.bc.pmpheep.back.commuser.group.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.group.bean.GroupFile;
import com.bc.pmpheep.back.commuser.group.bean.GroupList;
import com.bc.pmpheep.back.commuser.group.bean.GroupMessage;

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
	List<GroupMessage> getMessages(@Param("groupId") Long groupId);
	
	/**
	 * 
	 * Description:获取小组文件列表
	 * @author:lyc
	 * @date:2017年12月8日下午4:47:42
	 * @param 
	 * @return List<GroupFile>
	 */
	List<GroupFile> getFiles(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("groupId") Long groupId);
}
