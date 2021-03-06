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
 * 添加好友（普通用户）service层实现类
 * @author liudi
 *
 */
@Service("com.bc.pmpheep.back.commuser.addfriend.service.AddFriendService")
public class AddFriendServiceImpl implements AddFriendService {

	@Autowired
	AddFriendDao addFriendDao;
	
	@Override
	public List<Map<String,Object>> addFriendListQuery(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String,Object>> resultList = addFriendDao.addFriendListQuery(pageParameter);
		return resultList;
	}

	@Override
	public int addFriendListQueryCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count =addFriendDao.addFriendListQueryCount(pageParameter);
		Integer maxPageNum = (int) Math.ceil(1.0*count/pageParameter.getPageSize());
		return maxPageNum;
	}

	@Override
	public Map<String, Object> addFriendRequest(String target_id,String request_id, String status) {
		Map<String,Object> result_map =new HashMap<String,Object>();
		Map<String,Object> para_map =new HashMap<String,Object>();
		
		
		//friendShip初始化为未申请好友状态
		Map<String,Object> friendShip = new HashMap<String,Object>();
		friendShip.put("id", 0);
		friendShip.put("isBeenRequest", 0);
		friendShip.put("hasRequest", 0);
		friendShip.put("status", -1);
		//查询数据库中两人好友状态
		List<Map<String,Object>> r= addFriendDao.queryOurFriendShip(target_id,request_id);
		friendShip = r!=null&&r.size()>0?r.get(0):friendShip;
		
		Integer count = 0;
		if ((int)friendShip.get("status") == -1) {
			para_map.put("status", 0);
			para_map.put("target_id", target_id);
			para_map.put("request_id", request_id);
			count = addFriendDao.addFriendRequest(para_map);
			if (count>0) {
				result_map.put("msg", "已申请加为好友，请等待对方同意。");
			}
		}else if ((int)friendShip.get("status") == 0 && "1".equals(friendShip.get("isBeenRequest").toString())) {
			para_map.put("status", 2);
			para_map.put("target_id", request_id);
			para_map.put("request_id", target_id);
			count = addFriendDao.updateToAgreeFriend(friendShip.get("id").toString(),2);
			if (count>0) {
				result_map.put("msg", "已成为好友");
			}
		}else if ((int)friendShip.get("status") == 0 && "1".equals(friendShip.get("hasRequest").toString())) {
			result_map.put("msg", "已申请加为好友，请等待对方同意!");
		}
		
		return result_map;
	}

}
