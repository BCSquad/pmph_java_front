package com.bc.pmpheep.back.commuser.cms.service;

import com.bc.pmpheep.back.commuser.cms.bean.CmsContentVO;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

/**
 * CmsContent 接口
 * @author Mr
 *
 */
public interface CmsContentService {
	/**
	 * 查询医学随笔列表
	 * @return
	 * @throws Exception
	 */
	PageResult<CmsContentVO> list(PageParameter<CmsContentVO> pageParameter);
	/**
	 *  文章
	 * @param pageParameter
	 * @return
	 */
	PageResult<CmsContentVO> listCms(PageParameter<CmsContentVO> pageParameter);
	
}
