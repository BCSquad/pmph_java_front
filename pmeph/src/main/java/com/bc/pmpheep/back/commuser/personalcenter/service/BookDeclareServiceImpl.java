package com.bc.pmpheep.back.commuser.personalcenter.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.personalcenter.dao.BookDeclareDao;

@Service("com.bc.pmpheep.back.commuser.personalcenter.service.BookDeclareService")
public class BookDeclareServiceImpl implements BookDeclareService {
	
	@Autowired
	private BookDeclareDao bookDecdao;
	
	@Override
	public int insertTopic(Map<String, Object> map) {
		return this.bookDecdao.insertTopic(map);
	}

	@Override
	public int insertTopicExtra(Map<String, Object> map) {
		return this.bookDecdao.insertTopicExtra(map);
	}

	@Override
	public int insertTopicWriter(Map<String, Object> map) {
		return this.bookDecdao.insertTopicWriter(map);
	}

	@Override
	public List<Map<String, Object>> queryTopic(Map<String, Object> map) {
		return this.bookDecdao.queryTopic(map);
	}

	@Override
	public List<Map<String, Object>> queryTopicExtra(Map<String, Object> map) {
		return this.bookDecdao.queryTopicExtra(map);
	}

	@Override
	public List<Map<String, Object>> queryTopicWriter(Map<String, Object> map) {
		return this.bookDecdao.queryTopicWriter(map);
	}

}
