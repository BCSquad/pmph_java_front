package com.bc.pmpheep.back.commuser.cms.service;

import java.util.List;

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
	public PageResult<CmsContentVO> listCms(PageParameter<CmsContentVO> pageParameter) {
		PageResult<CmsContentVO> pageResult = new PageResult<>();
		int total = cmsContentDao.getCmsContentCount(pageParameter);
        if (total > 0) {
            PageParameterUitl.CopyPageParameter(pageParameter, pageResult);
            pageResult.setRows(cmsContentDao.listCmsContentVO(pageParameter));
        }
        pageResult.setTotal(total);
        return pageResult;
	}

}
