package com.bc.pmpheep.back.commuser.hotComment.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.hotComment.dao.HotCommentDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;


@Service("com.bc.pmpheep.back.commuser.hotComment.service.HotCommentServiceImpl")
public class HotCommentServiceImpl implements HotCommentService {
	
	@Autowired
	HotCommentDao hotCommentDao;
	
	//热门书评列表
	@Override
	public PageResult<Map<String, Object>> selectHotCommentList(PageParameter<Map<String, Object>> pageParameter) {
		PageResult<Map<String, Object>> pageResult= new PageResult<Map<String,Object>>();
		pageResult.setPageNumber(pageParameter.getPageNumber());
		pageResult.setPageSize(pageParameter.getPageSize());
		//列表数据
		List<Map<String, Object>> list = hotCommentDao.selectHotCommentList(pageParameter);
		//查询数量
		int count = hotCommentDao.selectHotCommentCount(pageParameter);
		
		pageResult.setRows(list);
		pageResult.setTotal(count);
		return pageResult;
	}
	
	//热门书评详情
	@Override
	public Map<String, Object> getHotCommentDetail(String id) {
		Map<String,Object> map = hotCommentDao.getHotCommentDetail(id);
		return map;
	}

}
