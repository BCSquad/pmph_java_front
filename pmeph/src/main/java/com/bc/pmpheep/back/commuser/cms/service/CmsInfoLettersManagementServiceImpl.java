package com.bc.pmpheep.back.commuser.cms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bc.pmpheep.back.commuser.cms.bean.CmsInfoLettersList;
import com.bc.pmpheep.back.commuser.cms.dao.CmsInfoLettersManagementDao;


/**
 * 
 * 
 * 功能描述：信息快报业务实现
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
@Service("com.bc.pmpheep.back.commuser.cms.service.CmsInfoLettersManagementServiceImpl")
public class CmsInfoLettersManagementServiceImpl implements CmsInfoLettersManagementService {
	
	@Autowired
	CmsInfoLettersManagementDao cmsInfoLettersManagementDao;

	@Override
	public List<CmsInfoLettersList> list(Integer pageSize, Integer pageNumber) {
		if(null == pageSize || pageSize <1 ){
			pageSize   = 10;                 //默认10条
		}
		if(null == pageNumber || pageNumber < 1){
			pageNumber =1;
		}
		return cmsInfoLettersManagementDao.list((pageNumber-1)*pageSize,pageSize);
	}
	
	@Override
	public Integer getCmsInfoLettersListTotal(Integer pageSize, Integer pageNumber) {
		return cmsInfoLettersManagementDao.getCmsInfoLettersListTotal();
	}
		
}
