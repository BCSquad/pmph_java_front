package com.bc.pmpheep.back.authadmin.accountset.service;


import com.bc.pmpheep.back.authadmin.accountset.bean.OrgAdminUser;

import java.util.Map;

/**
 * @Author: SuiXinYang
 * @Description:
 * @Date: Created in 10:13 2017/11/30
 * @Modified: SuiXinYang
 **/
public interface AdminInfoService {
    /**
     * 通过机构用户ID获取机构用户信息
     * @param id
     * @return
     */
    public Map<String,Object> getOrgUserById(Long id);

    /**
     * 修改机构用户信息
     * @param orgUser
     */
    public void updateOrgUser(OrgAdminUser orgUser);

    /**
     * 修改密码
     * @param orgUser
     */
    public void updatePassword(OrgAdminUser orgUser);
    
    //上传委托书
	public void uploadProxy(Map<String, Object> map);
	
	/**
	 * 根据ID修改机构用户头像
	 * @param avatar 上传图片ID
	 * @param id 主键
	 * @return
	 */
	Map<String, Object> updateavatar(Map<String, Object> map1);
}
