package com.bc.pmpheep.back.commuser.addfriend.dao;

import java.util.List;
import java.util.Map;
/**
 * 资料申报审核（机构用户）dao层
 * @author Administrator
 *
 */
public interface AddFriendDao {
	/**
	 * 查询教材列表
	 * @param paraMap
	 * @return
	 */
	public List<Map<String,Object>> addFriendListQuery(Map<String,Object> paraMap);
	/**
	 * 查询教材总数
	 * @param map
	 * @return
	 */
	public Integer addFriendListQueryCount(Map<String, Object> map);
	/**
	 * 添加一条好友申请
	 * @param para_map
	 * @return
	 */
	public Integer addFriendRequest(Map<String, Object> para_map);
	
	
	
}
