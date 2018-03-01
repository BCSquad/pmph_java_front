package com.bc.pmpheep.back.commuser.personalcenter.service;

import java.util.ArrayList;
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

	@Override
	public String insertBookDeclare(Map<String, Object> BankMap,
			List<Map<String, Object>> twriteList, Map<String, Object> topicMap,
			Map<String, Object> extraMap) {
		//插入银行信息
		this.bookDecdao.insertBank(BankMap);
		//获取银行信息
		List<Map<String, Object>> BankList = new ArrayList<Map<String, Object>>();
		BankList = this.bookDecdao.queryBank(BankMap);
		String bankid = "";
		if (BankList.size() > 0) {
			bankid = BankList.get(0).get("id").toString();
		}
		// 插入申报信息
		topicMap.put("bank_account_id", bankid);
		this.bookDecdao.insertTopic(topicMap);
		//获取申报ID
		Map<String, Object> topicLsit = this.bookDecdao.queryTopic(topicMap);
		String topic_id = topicLsit.get("id").toString();
		//插入选题申报额外信息
		extraMap.put("topic_id", topic_id);
		this.bookDecdao.insertTopicExtra(extraMap);
		//插入编者情况
		if(twriteList!=null && !twriteList.isEmpty()){
			for (Map<String, Object> map : twriteList) {
				map.put("topic_id", topic_id);
				this.bookDecdao.insertTopicWriter(map);
			}
		}
		return "OK";
	}

	@Override
	public String updateBookDeclare(Map<String, Object> BankMap,
			List<Map<String, Object>> twriteList, Map<String, Object> topicMap,
			Map<String, Object> extraMap) {
		//修改申报信息
		this.bookDecdao.updateTopic(topicMap);
		//修改选题申报额外信息topic_extra
		this.bookDecdao.updateTopicExtra(extraMap);
		//删除编者情况
		this.bookDecdao.delTopicWriter(topicMap.get("topic_id").toString());
		//插入编者情况
		if(twriteList!=null && !twriteList.isEmpty()){
			for (Map<String, Object> map : twriteList) {
				this.bookDecdao.insertTopicWriter(map);
			}
		}
		//修改银行信息
		this.bookDecdao.updateBank(BankMap);
		return "OK";
	}

}
