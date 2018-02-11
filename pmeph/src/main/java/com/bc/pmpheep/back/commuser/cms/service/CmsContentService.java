package com.bc.pmpheep.back.commuser.cms.service;

import java.util.List;
import java.util.Map;

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
	 *  查询医学随笔列表(文章)
	 * @param pageParameter
	 * @return
	 */
	List<Map<String, Object>> listCms(PageParameter<Map<String, Object>> pageParameter,String contextpath);
	
	/**
	 * 查询文章总条数
	 * @return
	 */
	Integer getCmsContentCount(PageParameter<Map<String, Object>> pageParameter);
	
}
