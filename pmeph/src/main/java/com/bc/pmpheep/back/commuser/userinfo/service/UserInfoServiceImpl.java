package com.bc.pmpheep.back.commuser.userinfo.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.bc.pmpheep.back.commuser.group.service.GroupService;
import com.bc.pmpheep.back.commuser.userinfo.dao.UserInfoDao;

@Service("com.bc.pmpheep.back.commuser.userinfo.service.UserInfoServiceImpl")
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoDao userinfoDao;
	
	@Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.group.service.GroupServiceImpl")
    private GroupService groupService;

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
		userinfoDao.updateMyTag(map);
		map.put("display_name", map.get("realname"));
		map.put("user_id"     , map.get("id")      );
		map.put("is_writer"   , true);
		//修改我在小组里面的名字
		groupService.updateName(map);
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
	
	
    @Override
    public void updatePassword(Map<String, Object> map) {
    	userinfoDao.updatePassword(map);
    }

}
