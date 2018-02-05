package com.bc.pmpheep.back.commuser.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.book.bean.BookVO;
import com.bc.pmpheep.back.commuser.cms.bean.CmsContentVO;
import com.bc.pmpheep.back.commuser.cms.dao.CmsContentDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.back.util.PageParameterUitl;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.general.service.FileService;
/**
 * 	CmsContentService 实现
 * @author Mr
 *
 */
@Service("com.bc.pmpheep.back.commuser.cms.service.CmsContentServiceImpl")
public class CmsContentServiceImpl implements CmsContentService{
	
	@Autowired
	private CmsContentDao cmsContentDao;
	
	@Autowired
	private FileService fileService;

	@Override
	public List<Map<String, Object>> listCms(
			PageParameter<Map<String, Object>> pageParameter) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("start", pageParameter.getStart());
		paraMap.put("pageSize", pageParameter.getPageSize());
		List<Map<String, Object>> resultList = cmsContentDao.listCmsContentVO(paraMap);
		for (Map<String,Object> cmsContentVO : resultList) {
			String authdate=cmsContentVO.get("auth_date").toString().substring(0, 10);
			cmsContentVO.put("auth_date", authdate);
		}
		
		return resultList;
	}
	
	
	/**
	 * 查询列表数据量
	 */
	@Override
	public Integer getCmsContentCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count = cmsContentDao.getCmsContentCount();
		Integer maxPageNum = (int) Math.ceil(1.0 * count
				/ pageParameter.getPageSize());
		return maxPageNum;
	}

}
