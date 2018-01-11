package com.bc.pmpheep.back.commuser.hotComment.dao;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;

public interface HotCommentDao {
	
	//热门书评列表
	List<Map<String, Object>> selectHotCommentList(PageParameter<Map<String, Object>> pageParameter);
	
	//热门书评数量
	int selectHotCommentCount(PageParameter<Map<String, Object>> pageParameter);
	
	//热门书评详情
	Map<String, Object> getHotCommentDetail(String id);

}
