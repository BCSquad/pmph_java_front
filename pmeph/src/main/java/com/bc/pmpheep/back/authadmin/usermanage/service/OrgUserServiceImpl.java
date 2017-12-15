package com.bc.pmpheep.back.authadmin.usermanage.service;

import com.bc.pmpheep.back.authadmin.usermanage.bean.OrgUser;
import com.bc.pmpheep.back.authadmin.usermanage.dao.OrgUserDao;
import com.bc.pmpheep.back.common.service.BaseService;
import com.bc.pmpheep.back.util.*;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * OrgUserServiceImpl 接口实现
 * 
 * @author tyc
 * 
 */
@Service("com.bc.pmpheep.back.commuser.user.service.OrgUserServiceImpl")
public class OrgUserServiceImpl extends BaseService implements OrgUserService {
    @Autowired
    private OrgUserDao orgUserDao;

    @Override
    public List<OrgUser> getOrgUserListByOrgIds(List<Long> orgIds) throws CheckedServiceException {
        if (null == orgIds) {
            throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                                              CheckedExceptionResult.NULL_PARAM, "参数为空");
        }
        return orgUserDao.getOrgUserListByOrgIds(orgIds);
    }

    /**
     * 
     * @param orgUser 实体对象
     * @return 带主键的 OrgUser
     * @throws CheckedServiceException
     */
    @Override
    public OrgUser addOrgUser(OrgUser orgUser) throws CheckedServiceException {
        if (ObjectUtil.isNull(orgUser)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                                              CheckedExceptionResult.ILLEGAL_PARAM, "参数为空");
        }
        if (StringUtil.isEmpty(orgUser.getUsername())) {
            throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                                              CheckedExceptionResult.ILLEGAL_PARAM, "用户名不能为空");
        }
        if (StringUtil.isEmpty(orgUser.getPassword())) {
            throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                                              CheckedExceptionResult.ILLEGAL_PARAM, "密码不能为空");
        }
        if (StringUtil.strLength(orgUser.getUsername()) > 20) {
            throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                                              CheckedExceptionResult.ILLEGAL_PARAM, "用户名不能超过20个字符");
        }

        if (StringUtil.isEmpty(orgUser.getRealname())) {
            orgUser.setRealname(orgUser.getUsername());
        }
        orgUserDao.addOrgUser(orgUser);
        return orgUser;
    }

    /**
     * 
     * @param id
     * @return OrgUser
     * @throws CheckedServiceException
     */
    @Override
    public OrgUser getOrgUserById(Long id) throws CheckedServiceException {
        if (null == id) {
            throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                                              CheckedExceptionResult.NULL_PARAM, "主键为空");
        }
        return orgUserDao.getOrgUserById(id);
    }

    /**
     * 
     * @param id
     * @return 影响行数
     * @throws CheckedServiceException
     */
    @Override
    public Integer deleteOrgUserById(Long id) throws CheckedServiceException {
        if (null == id) {
            throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                                              CheckedExceptionResult.NULL_PARAM, "主键为空");
        }
        return orgUserDao.deleteOrgUserById(id);
    }

    /**
     * @param orgUser
     * @return 影响行数
     * @throws CheckedServiceException
     */
    @Override
    public Integer updateOrgUser(OrgUser orgUser) throws CheckedServiceException {
        if (null == orgUser.getId()) {
            throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                                              CheckedExceptionResult.NULL_PARAM, "主键为空");
        }
        return orgUserDao.updateOrgUser(orgUser);
    }

    @Override
    public Integer updateOrgUserProgressById(Integer progress, List<Long> orgUserIds)
    throws CheckedServiceException {
        if (CollectionUtil.isEmpty(orgUserIds) || ObjectUtil.isNull(progress)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.SCHOOL_ADMIN_CHECK,
                                              CheckedExceptionResult.NULL_PARAM, "参数为空");
        }
        List<OrgUser> orgUserList = orgUserDao.getOrgUserByIds(orgUserIds);
        Integer count = 0;
        if (CollectionUtil.isNotEmpty(orgUserList)) {
            List<OrgUser> orgUsers = new ArrayList<OrgUser>(orgUserList.size());
            for (OrgUser orgUser : orgUserList) {
                if (Const.ORG_USER_PROGRESS_1 == orgUser.getProgress()
                    || Const.ORG_USER_PROGRESS_2 == orgUser.getProgress()) {
                    throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                                                      CheckedExceptionResult.NULL_PARAM,
                                                      "已审核的用户不能再次审核");
                }
                orgUsers.add(new OrgUser(orgUser.getId(), progress));
            }
            if (CollectionUtil.isNotEmpty(orgUsers)) {
                count = orgUserDao.updateOrgUserProgressById(orgUsers);
            }
        }
        return count;
    }

    @Override
    public String addOrgUserOfBack(OrgUser orgUser) throws CheckedServiceException {
        if (StringUtil.isEmpty(orgUser.getUsername())) {
            throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                                              CheckedExceptionResult.NULL_PARAM, "机构代码不能为空");
        }
        if (StringUtil.strLength(orgUser.getUsername()) > 20) {
            throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                                              CheckedExceptionResult.ILLEGAL_PARAM, "用户名不能超过20个字符");
        }
        if (!StringUtil.isEmpty(orgUser.getNote())) {
            if (StringUtil.strLength(orgUser.getNote()) > 100) {
                throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                                                  CheckedExceptionResult.ILLEGAL_PARAM,
                                                  "备注不能超过100个字符");
            }
        }
        if (StringUtil.isEmpty(orgUser.getRealname())) {
        	orgUser.setRealname(orgUser.getUsername());
        }
        orgUser.setPassword(new DesRun(Const.DEFAULT_PASSWORD, "").enpsw);// 后台添加用户设置默认密码为123456
        int num = orgUserDao.addOrgUser(orgUser);// 返回的影响行数，如果不是影响0行就是添加成功
        String result = "FAIL";
        if (num > 0) {
            result = "SUCCESS";
        }
        return result;
    }

	@Override
	public Integer updateOrgUserPassWord(Long id, String username) {
		if (ObjectUtil.isNull(id)) {
			throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                    CheckedExceptionResult.NULL_PARAM, "id不能为空");
		}
		if (StringUtil.isEmpty(username)) {
			throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                    CheckedExceptionResult.NULL_PARAM, "机构代码不能为空");
		}
		Integer orgUser = orgUserDao.updateOrgUserPassWord(id, username);
		return orgUser;
	}

}
