package com.bc.pmpheep.back.commuser.mygroup.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.mygroup.bean.PmphGroupMemberVO;





/**
 * Created by cyx  on 2017/11/21
 */
public interface MyGroupDao {
	/**
	 * 
	 * Description:根据当前用户id查找成员
	 * 
	 * @author:lyc
	 * @date:2017年10月12日下午2:52:57
	 * @Param:小组内成员id
	 * @Return:PmphGroupMember
	 */
	PmphGroupMemberVO getPmphGroupMemberByMemberId(@Param("groupId") Long groupId, @Param("userId") Long userId,
			@Param("isWriter")Boolean isWriter);
	
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
    
}
