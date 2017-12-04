package com.bc.pmpheep.back.commuser.user.dao;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.user.bean.WriterUser;
import com.bc.pmpheep.back.commuser.user.bean.WriterUserCertification;
import com.bc.pmpheep.back.commuser.user.bean.WriterUserCertificationVO;

/**
 * 
 * 
 * 功能描述：普通用户持久层
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
public interface WriterUserDao {
	
	/**
	 * 
	 * 
	 * 功能描述：根据id获取作家用户信息
	 *
	 * @param id
	 * @return
	 *
	 */
	WriterUser get(Long id);
	
	/**
	 * 根据机构orgid 加载机构用户下的作家
	 * @param orgId
	 * @return
	 */
	WriterUser getOrg(Long orgId);
	
	/**
	 * 根据用户输入查询 必传orgid
	 * @param writerUser
	 * @return
	 */
	WriterUser getByOrgId(WriterUser writerUser);
	
	/**
	 * 查看学校教师认证信息
	 * @author tyc
     * @createDate 2017年11月30日 上午10:44:09
	 * @param id
	 * @return
	 */
	WriterUserCertificationVO showTeacherCertification(@Param("id") Long id);
	
	/**
	 * 添加学校教师认证
	 * @author tyc
     * @createDate 2017年12月1日 上午09:39:09
	 * @param writerUserCertification
	 * @return
	 */
	WriterUserCertification addCertification(WriterUserCertification writerUserCertification);
	
	/**
	 * 修改学校教师认证
	 * @author tyc
     * @createDate 2017年11月30日 上午10:44:09
	 * @param id
	 * @return
	 */
	WriterUserCertification updateCertification(Long id);
	
	/**
	 * 根据id和用户名修改用户密码
	 * @author tyc
     * @createDate 2017年12月4日 上午10:31:11
     * @param id
     * @param username
     * @return
     */
    Integer updateUserPassWord(Long id, String username);
}
