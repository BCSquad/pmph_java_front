package com.bc.pmpheep.back.commuser.user.service;

import com.bc.pmpheep.back.commuser.user.bean.OrgUser;
import com.bc.pmpheep.service.exception.CheckedServiceException;

import java.util.List;

/**
 * OrgUserService 接口
 * 
 * @author tyc
 */
public interface OrgUserService {
    /**
     * 根据机构id集查询用户(逻辑没有删除和启用的)
     */
    List<OrgUser> getOrgUserListByOrgIds(List<Long> orgIds) throws CheckedServiceException;

    /**
     * 
     * @param orgUser 实体对象
     * @return 带主键的 OrgUser
     * @throws CheckedServiceException
     */
    OrgUser addOrgUser(OrgUser orgUser) throws CheckedServiceException;

    /**
     * 
     * @param id
     * @return OrgUser
     * @throws CheckedServiceException
     */
    OrgUser getOrgUserById(Long id) throws CheckedServiceException;

    /**
     * 
     * @param id
     * @return 影响行数
     * @throws CheckedServiceException
     */
    Integer deleteOrgUserById(Long id) throws CheckedServiceException;

    /**
     * @param orgUser
     * @return 影响行数
     * @throws CheckedServiceException
     */
    Integer updateOrgUser(OrgUser orgUser) throws CheckedServiceException;

    /**
     * 
     * <pre>
     * 功能描述：学校管理员审核(按Id更新审核状态)
     * 使用示范：
     *
     * @param progress 审核状态
     * @param orgUserIds 机构用户IDS
     * @return
     * @throws CheckedServiceException
     * </pre>
     */
    Integer updateOrgUserProgressById(Integer progress, List<Long> orgUserIds)
    throws CheckedServiceException;

    /**
     * 
     * 
     * 功能描述：后台机构用户管理页面添加机构用户
     * 
     * @param OrgUser 添加的机构用户用户属性
     * @return 是否成功
     * @throws CheckedServiceException
     * 
     */
    String addOrgUserOfBack(OrgUser orgUser) throws CheckedServiceException;
    
	/**
	 * 根据id和机构代码修改机构用户密码
	 * @author tyc
     * @createDate 2017年12月4日 上午09:09:09
     * @param id
     * @param username
     * @return
     */
    Integer updateOrgUserPassWord(Long id, String username);
}
