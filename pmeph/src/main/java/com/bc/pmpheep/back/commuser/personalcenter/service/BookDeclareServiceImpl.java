package com.bc.pmpheep.back.commuser.personalcenter.service;

import com.bc.pmpheep.back.commuser.personalcenter.dao.BookDeclareDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

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
	public Map<String,Object> insertBookDeclare(List<Map<String, Object>> similarList,
			List<Map<String, Object>> twriteList, Map<String, Object> topicMap,
			Map<String, Object> extraMap) {
		// 插入申报信息
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
		//社外同类书情况
		if(similarList!=null && !similarList.isEmpty()){
			for (Map<String, Object> map : similarList) {
				map.put("topic_id", topic_id);
				this.bookDecdao.insertSimilarBook(map);
			}
		}
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("msg","OK");
		return returnMap;
	}

	@Override
	public Map<String,Object> updateBookDeclare(List<Map<String, Object>> similarList,
			List<Map<String, Object>> twriteList, Map<String, Object> topicMap,
			Map<String, Object> extraMap,String topic_id) {
		//修改申报信息
		topicMap.put("topic_id", topic_id);
		this.bookDecdao.updateTopic(topicMap);
		extraMap.put("topic_id", topic_id);
		//修改选题申报额外信息topic_extra
		this.bookDecdao.updateTopicExtra(extraMap);
		//删除编者情况
		this.bookDecdao.delTopicWriter(topic_id);
		//删除编者情况
		this.bookDecdao.delSimilarBook(topic_id);
		//插入编者情况
		if(twriteList!=null && !twriteList.isEmpty()){
			for (Map<String, Object> map : twriteList) {
				map.put("topic_id", topic_id);
				this.bookDecdao.insertTopicWriter(map);
			}
		}
		//社外同类书情况
		if(similarList!=null && !similarList.isEmpty()){
			for (Map<String, Object> map : similarList) {
				map.put("topic_id", topic_id);
				this.bookDecdao.insertSimilarBook(map);
			}
		}
		//修改银行信息
		//this.bookDecdao.updateBank(BankMap);
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("msg","OK");
		return returnMap;
	}

	@Override
	public List<Map<String, Object>> querySimilarBook(Map<String, Object> map) {
		return this.bookDecdao.querySimilarBook(map);
	}

}
