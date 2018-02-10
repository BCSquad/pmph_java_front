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
	public Map<String, Object> queryTopic(Map<String, Object> map) {
		return this.bookDecdao.queryTopic(map);
	}

	@Override
	public Map<String, Object> queryTopicExtra(Map<String, Object> map) {
		return this.bookDecdao.queryTopicExtra(map);
	}

	@Override
	public List<Map<String, Object>> queryTopicWriter(Map<String, Object> map) {
		return this.bookDecdao.queryTopicWriter(map);
	}

	@Override
	public int updateTopic(Map<String, Object> map) {
		return this.bookDecdao.updateTopic(map);
	}

	@Override
	public int updateTopicExtra(Map<String, Object> map) {
		return this.bookDecdao.updateTopicExtra(map);
	}

	@Override
	public int delTopicWriter(String id) {
		return this.bookDecdao.delTopicWriter(id);
	}

	@Override
	public List<Map<String, Object>> queryBank(Map<String, Object> map) {
		return this.bookDecdao.queryBank(map);
	}

	@Override
	public int insertBank(Map<String, Object> map) {
		return this.bookDecdao.insertBank(map);
	}

	@Override
	public int updateBank(Map<String, Object> map) {
		return this.bookDecdao.updateBank(map);
	}

	@Override
	public String getMaxTopicVn() {
		return this.bookDecdao.getMaxTopicVn();
	}

}
