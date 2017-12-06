package com.bc.pmpheep.back.commuser.cms.service;

import java.util.List;
import com.bc.pmpheep.back.commuser.cms.bean.CmsNoticeList;


/**
 * 
 * 
 * 功能描述：遴选公告列表业务层
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
public interface CmsNoticeManagementService {
	
	/**
	 * 分页查询公告
	 * @introduction 
	 * @author Mryang
	 * @createDate 2017年12月5日 下午4:41:46
	 * @param pageSize
	 * @param pageNumber
	 * @param order
	 * @return
	 */
	List<CmsNoticeList> list(Integer pageSize, Integer pageNumber, Integer order) ;
	
}
