package com.bc.pmpheep.back.commuser.cms.service;

import java.util.List;

import com.bc.pmpheep.back.commuser.cms.bean.CmsInfoLettersList;

/**
 * 
 * 
 * 功能描述：信息快报业务层
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
public interface CmsInfoLettersManagementService {
	
	/**
	 * 
	 * 
	 * 功能描述：前台获取信息快报列表
	 *
	 * @return
	 *
	 */
	List<CmsInfoLettersList> list(Integer pageSize, Integer pageNumber) ;
	
	/**
	 * 获取前台获取信息快报列表总数
	 * @author Mryang
	 * @createDate 2017年12月5日 下午1:51:28
	 * @param pageSize
	 * @param pageNumber
	 * @param pageParameter
	 * @return
	 */
	Integer getCmsInfoLettersListTotal(Integer pageSize, Integer pageNumber) ;

}
