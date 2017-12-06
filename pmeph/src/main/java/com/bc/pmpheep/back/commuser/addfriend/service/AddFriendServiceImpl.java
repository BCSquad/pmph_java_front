package com.bc.pmpheep.back.commuser.addfriend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bc.pmpheep.back.commuser.addfriend.dao.AddFriendDao;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.controller.bean.ResponseBean;

/**
 * 资料申报审核（机构用户）service层实现类
 * @author liudi
 *
 */
@Service("com.bc.pmpheep.back.commuser.addfriend.service.AddFriendService")
public class AddFriendServiceImpl implements AddFriendService {

	@Autowired
	AddFriendDao addFriendDao;
	
	@Override
	public List<Map<String,Object>> addFriendListQuery(PageParameter<Map<String, Object>> pageParameter) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("startNum", pageParameter.getStart());
		paraMap.put("pageSize", pageParameter.getPageSize());
		paraMap.put("queryName", pageParameter.getParameter().get("queryName"));
		paraMap.put("uid", pageParameter.getParameter().get("uid"));
		/*paraMap.put("uid", "24966");*/
		List<Map<String,Object>> resultList = addFriendDao.addFriendListQuery(paraMap);
		return resultList;
	}

	@Override
	public int addFriendListQueryCount(PageParameter<Map<String, Object>> pageParameter) {
		Map<String, Object> paraMap = pageParameter.getParameter();
		paraMap.put("queryName", pageParameter.getParameter().get("queryName"));
		paraMap.put("uid", pageParameter.getParameter().get("uid"));
		/*paraMap.put("uid", "24966");*/
		Integer count =addFriendDao.addFriendListQueryCount(paraMap);
		Integer maxPageNum = (int) Math.ceil(1.0*count/pageParameter.getPageSize());
		return maxPageNum;
	}

	@Override
	public Map<String, Object> addFriendRequest(String target_id,String request_id, String status) {
		Map<String,Object> result_map =new HashMap<String,Object>();
		Map<String,Object> para_map =new HashMap<String,Object>();
		para_map.put("target_id", target_id);
		para_map.put("request_id", request_id);
		para_map.put("status", status);
		Integer count = addFriendDao.addFriendRequest(para_map);
		return result_map;
	}

}