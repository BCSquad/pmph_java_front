package com.bc.pmpheep.back.commuser.addfriend.service;

/**
 * 资料申报审核（机构用户）service层
 */
import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.controller.bean.ResponseBean;
public interface AddFriendService {

	/**
	 * 查询教材列表
	 * @param pageParameter 
	 * @return
	 */
	public List<Map<String,Object>> addFriendListQuery(PageParameter<Map<String, Object>> pageParameter);
	
	/**
	 * 查询教材总数量
	 * @param pageParameter
	 * @return
	 */
	public int addFriendListQueryCount(PageParameter<Map<String, Object>> pageParameter);
	
	/**
	 * 插入一条好友申请
	 * @param target_id
	 * @param request_id
	 * @param status
	 * @return
	 */
	public Map<String, Object> addFriendRequest(String target_id,String request_id, String status);
}
