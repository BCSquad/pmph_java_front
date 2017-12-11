package com.bc.pmpheep.back.commuser.writerArticle.service;

import java.util.Map;

public interface WriterArticleService {
	
	
	/**
	 * 插入作家的随笔文章
	 * @param map
	 */
	public String insertWriteArticle(Map map,String UEContent);
	
	/**
	 * 更改写文章的状态 
	 * @param map
	 */
	public String  updateIsStaging(Map map,String UEContent);
	
	/**
	 * 初始化显示未保存的文章信息
	 * @param map
	 */
	public Map<String, Object> queryWriteArticleInfo(Map<String, Object> map);
}
