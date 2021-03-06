package com.bc.pmpheep.back.commuser.writerArticle.service;

import java.util.Map;

public interface WriterArticleService {
	
	
	/**
	 * 插入作家的随笔文章
	 * @param map
	 */
	public Map<String, Object> insertWriteArticle(Map map,String UEContent);
	
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
	
	
	/**
	 * 删除文章
	 * @param map
	 */
	
	public int updateDelWriter(Map<String, Object> map);
}
