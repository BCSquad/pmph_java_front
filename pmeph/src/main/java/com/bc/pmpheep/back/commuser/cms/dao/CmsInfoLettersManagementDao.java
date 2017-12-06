package com.bc.pmpheep.back.commuser.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.cms.bean.CmsInfoLettersList;

/**
 * 
 * 
 * 功能描述：信息快报持久层
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
public interface CmsInfoLettersManagementDao {

	/**
	 * 
	 * 
	 * 功能描述：获取信息快报的总条数
	 *
	 * @return
	 *
	 */
	Integer getCmsInfoLettersListTotal();

	/**
	 * 
	 * 
	 * 功能描述：前台获取信息快报列表
	 *
	 * @return
	 *
	 */
	List<CmsInfoLettersList> list(@Param("start")Integer start, @Param("pageSize")Integer pageSize,@Param("order")Integer order);
}
