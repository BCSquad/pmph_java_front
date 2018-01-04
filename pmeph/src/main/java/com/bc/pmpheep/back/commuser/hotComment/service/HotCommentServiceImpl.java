package com.bc.pmpheep.back.commuser.hotComment.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.hotComment.dao.HotCommentDao;


@Service("com.bc.pmpheep.back.commuser.hotComment.service.HotCommentServiceImpl")
public class HotCommentServiceImpl implements HotCommentService {
	
	@Autowired
	HotCommentDao hotCommentDao;
	
	@Override
	public List<Map<String, Object>> selectHotCommentList() {
		// TODO Auto-generated method stub
		return null;
	}

}
