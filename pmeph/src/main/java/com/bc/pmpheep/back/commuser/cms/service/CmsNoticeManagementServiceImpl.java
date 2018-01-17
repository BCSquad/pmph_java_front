package com.bc.pmpheep.back.commuser.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bc.pmpheep.back.commuser.cms.bean.CmsNoticeList;
import com.bc.pmpheep.back.commuser.cms.dao.CmsNoticeManagementDao;


/**
 * 
 * 
 * 功能描述：遴选公告业务实现
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
@Service("com.bc.pmpheep.back.commuser.cms.service.CmsNoticeManagementServiceImpl")
public class CmsNoticeManagementServiceImpl implements CmsNoticeManagementService {
	@Autowired
	CmsNoticeManagementDao cmsNoticeManagementDao;

	@Override
	public List<CmsNoticeList> list(Integer pageSize, Integer pageNumber, Integer order,Long userid){
		if(null == pageSize || pageSize < 1){
			pageSize =10 ;
		}
		if(null == pageNumber || pageNumber < 1){
			pageNumber = 1 ;
		}
		
		Map<String,Object>  map=new HashMap<>();
		map.put("start", (pageNumber-1)*pageSize);
		map.put("pageSize", pageSize);
		map.put("order", order);
		map.put("userid", userid);
		return  cmsNoticeManagementDao.list(map);
	}

}
