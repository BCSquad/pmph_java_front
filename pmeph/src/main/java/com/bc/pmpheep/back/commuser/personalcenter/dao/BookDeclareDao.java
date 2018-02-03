package com.bc.pmpheep.back.commuser.personalcenter.dao;

import java.util.List;
import java.util.Map;

public interface BookDeclareDao {
	
	/**
	 * 选题申报信息插入
	 */
	public int insertTopic(Map<String,Object> map);
	/**
	 * 根据条件查询申报信息
	 * @param map
	 * @return
	 */
	public Map<String,Object> queryTopic(Map<String,Object> map);
	/**
	 * 申报信息修改
	 */
	public int updateTopic (Map<String,Object> map);
	
	/**
	 * 选题申报-额外信息
	 */
	public int insertTopicExtra(Map<String,Object> map);
	/**
	 * 根据条件查询选题申报-额外信息
	 * @param map
	 * @return
	 */
	public Map<String,Object> queryTopicExtra(Map<String,Object> map);
	/**
	 * 额外信息修改
	 */
	public int updateTopicExtra(Map<String,Object> map);
	
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
	
	/**
	 * 删除编者情况
	 */
	public int delTopicWriter(String id);
	
	/**
	 * 查询作家用户关联银行信息
	 */
	public List<Map<String,Object>> queryBank(Map<String,Object> map);
	
	/**
	 * 添加银行账号信息
	 */
	public int insertBank(Map<String,Object> map);
	
	/**
	 * 添加银行账号信息
	 */
	public int updateBank(Map<String,Object> map);
	
}
