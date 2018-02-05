package com.bc.pmpheep.back.commuser.cms.dao;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.commuser.cms.bean.CmsContentVO;
import com.bc.pmpheep.back.plugin.PageParameter;

/**
 * CmsContentDao实体类数据访问层接口
 * 
 * @author Mr
 * 
 */
public interface  CmsContentDao{
	

	/**
	 * 查询文章总条数
	 * @return
	 */
	Integer getCmsContentCount();
	
	/**
	 * 查询文章(医学随笔列表)
	 * @param pageParameter
	 * @return
	 */
	List<Map<String,Object>> listCmsContentVO(Map<String,Object> pageParameter);
	
	
}
