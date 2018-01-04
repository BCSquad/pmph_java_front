package com.bc.pmpheep.back.commuser.hotComment.service;

import java.util.List;
import java.util.Map;

public interface HotCommentService {
	
	//热门长评列表
	List<Map<String, Object>> selectHotCommentList();

}
