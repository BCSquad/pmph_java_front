package com.bc.pmpheep.back.commuser.user.service;

import java.io.IOException;

import com.bc.pmpheep.back.commuser.user.bean.WriterUser;
import com.bc.pmpheep.back.commuser.user.bean.WriterUserCertification;
import com.bc.pmpheep.back.commuser.user.bean.WriterUserCertificationVO;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 
 * 
 * 功能描述：普通用户实现层
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
public interface WriterUserService {
	/**
	 * 根据主键 id 加载用户对象
	 * 
	 * @param id
	 * @return
	 */
	WriterUser get(Long id) throws CheckedServiceException;
	
	/**
	 * 根据机构orgid 加载机构用户下的作家
	 * @param orgId
	 * @return
	 * @throws CheckedServiceException
	 */
	WriterUser getOrg(Long orgId) throws CheckedServiceException;
	
	/**
	 * 条件搜索 必传orgid
	 * @param writerUser
	 * @return
	 * @throws CheckedServiceException
	 */
	WriterUser getByOrgId(WriterUser writerUser) throws CheckedServiceException;
	
	/**
	 * 查看学校教师认证信息
	 * @author tyc
     * @createDate 2017年11月30日 上午10:44:09
	 * @param id
	 * @return
	 */
	WriterUserCertificationVO showTeacherCertification(Long id);
	
	/**
	 * 修改学校教师认证
	 * @author tyc
     * @createDate 2017年11月30日 上午10:44:09
	 * @param writerUserCertificationVO
	 * @return
	 */
	WriterUserCertification updateTeacherCertification(WriterUserCertificationVO writerUserCertificationVO) 
			throws IOException ;
	
	/**
	 * 根据id和用户名修改普通用户密码
	 * @author tyc
     * @createDate 2017年12月4日 上午10:34:43
     * @param id
     * @param username
     * @return
     */
    Integer updateUserPassWord(Long id, String username);
}
