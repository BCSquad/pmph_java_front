package com.bc.pmpheep.back.commuser.addfriend.dao;

import java.util.List;
import java.util.Map;

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
	
	
	
}
