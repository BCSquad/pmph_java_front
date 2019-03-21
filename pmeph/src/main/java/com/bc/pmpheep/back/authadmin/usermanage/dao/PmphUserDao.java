package com.bc.pmpheep.back.authadmin.usermanage.dao;

import com.bc.pmpheep.back.authadmin.usermanage.bean.PmphUser;

import java.util.List;

/**
 * 
 * 
 * 功能描述：社内用户持久层
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
public interface PmphUserDao {
	/**
	 * 
	 * 
	 * 功能描述：根据id获取社内用户信息
	 *
	 * @param id
	 * @return
	 *
	 */
	PmphUser get(Long id);

	//获取系统管理员的对象
	List<PmphUser> getPmphUserByRole();
}
