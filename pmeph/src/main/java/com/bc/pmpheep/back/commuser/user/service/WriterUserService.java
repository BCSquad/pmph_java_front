package com.bc.pmpheep.back.commuser.user.service;

import com.bc.pmpheep.back.commuser.user.bean.WriterUser;
import com.bc.pmpheep.back.commuser.user.bean.WriterUserCertification;
import com.bc.pmpheep.back.commuser.user.bean.WriterUserCertificationVO;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

import java.io.IOException;

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
	 * 查看学校教师认证信息
	 * @author tyc
     * @createDate 2017年11月30日 上午10:44:09
	 * @param userId
	 * @return
	 */
	WriterUserCertificationVO showTeacherCertification(Long userId);
	
	/**
	 * 修改学校教师认证
	 * @author tyc
     * @createDate 2017年11月30日 上午10:44:09
	 * @param writerUserCertification
	 * @return
	 */
	WriterUserCertification updateTeacherCertification(WriterUserCertification writerUserCertification,
                                                       String realName) throws IOException ;
	
	/**
	 * 根据id和用户名修改用户密码
	 * @author tyc
     * @createDate 2017年12月4日 上午10:34:43
     * @param id
     * @param username
     * @return
     */
    Integer updateUserPassWord(Long id, String username);
    /**
     * 根据机构orgid 分页加载机构用户下的作家
     * @param pageParameter
     * @return
     */
	PageResult<WriterUser> getOrg(PageParameter<WriterUser> pageParameter) throws CheckedServiceException;
}
