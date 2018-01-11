package com.bc.pmpheep.back.commuser.hotComment.service;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

public interface HotCommentService {
	
	//热门长评列表
	PageResult<Map<String, Object>> selectHotCommentList(PageParameter<Map<String, Object>> pageParameter);
	
	//展开热门书评详情
	Map<String, Object> getHotCommentDetail(String id);

}
