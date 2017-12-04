package com.bc.pmpheep.back.commuser.user.dao;

import java.util.List;

import com.bc.pmpheep.back.commuser.user.bean.OrgUser;

/**
 * OrgUser 实体类数据访问层接口
 * 
 * @author mryang
 */
public interface OrgUserDao {
    /**
     * 根据机构id集查询用户 (逻辑没有删除和启用的)
     */
    List<OrgUser> getOrgUserListByOrgIds(List<Long> orgIds);

    /**
     * 
     * @param orgUser 实体对象
     * @return 影响行数
     */
    Integer addOrgUser(OrgUser orgUser);

    /**
     * 根据主键Id查询对象
     * 
     * @param id 主键id
     * @return OrgUser OrgUser对象
     */
    OrgUser getOrgUserById(Long id);

    /**
     * 
     * <pre>
     * 功能描述：根据主键Id集合查询对象集合
     * 使用示范：
     *
     * @param ids 主键id 集合
     * @return
     * </pre>
     */
    List<OrgUser> getOrgUserByIds(List<Long> ids);

    /**
     * 
     * @param id
     * @return 影响行数
     */
    Integer deleteOrgUserById(Long id);

    /**
     * @param orgUser
     * @return 影响行数
     */
    Integer updateOrgUser(OrgUser orgUser);

    /**
     * 
     * <pre>
	 * 功能描述：查询表单总条数
	 * 使用示范：
	 *
	 * @return 表单的总条数
	 * </pre>
     */
    Long getOrgUserCount();

    /**
     * 
     * <pre>
	 * 功能描述：学校管理员审核(按Id更新审核状态)
	 * 使用示范：
	 *
	 * @param orgUser
	 * @return
	 * </pre>
     */
    Integer updateOrgUserProgressById(List<OrgUser> orgUser);

    /**
     * 
     * 
     * 功能描述：通过机构id获取机构用户
     * 
     * @param orgId 机构id
     * @return
     * 
     */
    List<OrgUser> listOrgUserByOrgId(Long orgId);
    
    /**
     * 根据username查询是否存在该机构名称
     * @param username
     * @return
     */
	List<OrgUser> getOrgUsername(String username);
	
	/**
	 * 根据id和机构代码修改机构用户密码
	 * @author tyc
     * @createDate 2017年12月4日 上午09:02:09
     * @param id
     * @param username
     * @return
     */
    Integer updateOrgUserPassWord(Long id, String username);

}
