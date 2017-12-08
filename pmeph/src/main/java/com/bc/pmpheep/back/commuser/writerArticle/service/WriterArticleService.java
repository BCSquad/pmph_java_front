package com.bc.pmpheep.back.commuser.writerArticle.service;

import java.util.Map;

public interface WriterArticleService {
	
	
	/**
	 * 插入作家的随笔文章
	 * @param map
	 */
	public void insertWriteArticle(Map map);
	
	/**
	 * 更改写文章的状态 
	 * @param map
	 */
	public void updateIsStaging(Map map);
	
	/**
	 * 初始化显示未保存的文章信息
	 * @param map
	 */
	public Map<String, Object> queryWriteArticleInfo(Map map);
}
