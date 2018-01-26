package com.bc.pmpheep.back.authadmin.accountset.service;

import com.bc.pmpheep.back.authadmin.accountset.bean.OrgAdminUser;
import com.bc.pmpheep.back.authadmin.accountset.dao.AdminInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: SuiXinYang
 * @Description:
 * @Date: Created in 10:14 2017/11/30
 * @Modified: SuiXinYang
 **/
@Service("com.bc.pmpheep.back.authadmin.accountset.service.AdminInfoServiceImpl")
public class AdminInfoServiceImpl implements AdminInfoService{
   
    @Autowired
    private AdminInfoDao adminInfoDao;
	
	@Override
    public void updatePassword(OrgAdminUser orgUser) {
        adminInfoDao.updatePassword(orgUser);
    }

    @Override
    public Map<String, Object> getOrgUserById(Long id) {
        Map<String,Object> map=adminInfoDao.getOrgUserById(id);
        return map;
    }

    @Override
    public void updateOrgUser(OrgAdminUser orgUser) {
        adminInfoDao.updateOrgUserById(orgUser);
    }

    //上传委托书
	@Override
	public void uploadProxy(Map<String, Object>  map) {
		adminInfoDao.uploadProxy(map);
		
	}
	
	/**
	 * 根据ID修改头像
	 */
	@Override
	public Map<String, Object> updateavatar(Map<String, Object> map1) {
		// TODO Auto-generated method stub
		int count=adminInfoDao.updateavatar(map1);
		Map<String, Object> map=new HashMap<String, Object>();
		if(count>0){
			map.put("returncode", "OK");
		}
		return map;
	}
	
}
