package com.bc.pmpheep.back.commuser.addfriend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.plugin.PageParameter;
/**
 * 添加好友 列表 dao层
 * @author Administrator
 *
 */
public interface AddFriendDao {
	/**
	 * 查询未添加的好友（分页）
	 * @param pageParameter
	 * @return
	 */
	public List<Map<String,Object>> addFriendListQuery(PageParameter<Map<String, Object>> pageParameter);
	/**
	 * 查询未添加的好友总数
	 * @param pageParameter
	 * @return
	 */
	public Integer addFriendListQueryCount(PageParameter<Map<String, Object>> pageParameter);
	/**
	 * 添加一条好友申请
	 * @param para_map
	 * @return
	 */
	public Integer addFriendRequest(Map<String, Object> para_map);
	
	/**
	 * 查询两人除拒绝（1）外最亲密的好友关系（0申请，2接收 取max）
	 * @param userId
	 * @param logUserId
	 * @return
	 */
	public List<Map<String, Object>> queryOurFriendShip(@Param("userId")String userId,@Param("logUserId")String logUserId);
	
	/**
	 * 更新好友表以同意好友申请
	 * @param string
	 * @param i
	 * @return
	 */
	public Integer updateToAgreeFriend(@Param("id")String string,@Param("status") int i);
	
	
	
}
