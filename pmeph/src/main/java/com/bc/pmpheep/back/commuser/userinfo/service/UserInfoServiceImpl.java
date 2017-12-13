package com.bc.pmpheep.back.commuser.userinfo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.userinfo.dao.UserInfoDao;

@Service("com.bc.pmpheep.back.commuser.userinfo.service.UserInfoServiceImpl")
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoDao userinfoDao;

	/**
	 * 根据ID查询数据
	 */
	@Override
	public Map<String, Object> queryWriter(String id) {
		// TODO Auto-generated method stub
		return userinfoDao.queryWriter(id);
	}

	/**
	 * 根据ID修改普通用户信息
	 */
	@Override
	public Map<String, Object> update(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int count=userinfoDao.update(map);
		Map<String, Object> rmap=new HashMap<String, Object>();
		if(count>0){
			rmap.put("returncode", "OK");
			rmap.put("id", map.get("id"));
		}
		return rmap;
	}

	/**
	 * 根据ID修改头像
	 */
	@Override
	public Map<String, Object> updateavatar(String avatar, String id) {
		// TODO Auto-generated method stub
		int count=userinfoDao.updateavatar(avatar, id);
		Map<String, Object> map=new HashMap<String, Object>();
		if(count>0){
			map.put("returncode", "OK");
		}
		return map;
	}

}