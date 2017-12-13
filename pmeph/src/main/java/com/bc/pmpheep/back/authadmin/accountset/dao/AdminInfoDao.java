package com.bc.pmpheep.back.authadmin.accountset.dao;

import com.bc.pmpheep.back.authadmin.accountset.bean.OrgAdminUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: SuiXinYang
 * @Description: 机构用户管理数据持久层接口
 * @Date: Created in 10:12 2017/11/30
 * @Modified: SuiXinYang
 **/
@Repository
public interface AdminInfoDao {
    /**
     * 通过机构用户ID获取机构用户信息
     * @param id
     * @return
     */
    public Map<String,Object> getOrgUserById(Long id);

    /**
     * 根据机构用户ID修改机构用户基本信息
     * @param orgUser
     */
    public void updateOrgUserById(OrgAdminUser orgUser);

    /**
     * 修改密码
     * @param orgUser
     */
    public void updatePassword(OrgAdminUser orgUser);
    
    //上传委托书
	public void uploadProxy(Map<String, Object> map);
}
