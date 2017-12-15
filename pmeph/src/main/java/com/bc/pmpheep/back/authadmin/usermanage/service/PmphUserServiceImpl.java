package com.bc.pmpheep.back.authadmin.usermanage.service;

import com.bc.pmpheep.back.authadmin.usermanage.bean.PmphUser;
import com.bc.pmpheep.back.authadmin.usermanage.dao.PmphUserDao;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 
 * 功能描述：社内用户业务实现
 * 
 * 
 * 
 * @author (作者) 曾庆峰
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 2017年11月27日
 * @modify (最后修改时间)
 * @修改人 ：曾庆峰
 * @审核人 ：
 *
 */
@Service("com.bc.pmpheep.back.authadmin.usermanage.service.PmphUserServiceImpl")
public class PmphUserServiceImpl implements PmphUserService {
	@Autowired
    PmphUserDao pmphUserDao;

	@Override
	public PmphUser get(Long id) throws CheckedServiceException {
		if (ObjectUtil.isNull(id)) {
			throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
					CheckedExceptionResult.NULL_PARAM, "用户ID为空时禁止查询");
		}
		return pmphUserDao.get(id);
	}
}
