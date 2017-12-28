package com.bc.pmpheep.back.commuser.personalcenter.service;

import java.util.List;
import java.util.Map;

public interface BookDeclareService {
	
	/**
	 * 选题申报信息插入
	 */
	public int insertTopic(Map<String,Object> map);
	/**
	 * 根据条件查询申报信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryTopic(Map<String,Object> map);
	/**
	 * 选题申报-额外信息
	 */
	public int insertTopicExtra(Map<String,Object> map);
	/**
	 * 根据条件查询选题申报-额外信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryTopicExtra(Map<String,Object> map);
	
	/**
	 * 选题申报-编者情况
	 */
	public int insertTopicWriter(Map<String,Object> map);
	
	/**
	 * 根据条件查询选题申报-编者情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryTopicWriter(Map<String,Object> map);
}
