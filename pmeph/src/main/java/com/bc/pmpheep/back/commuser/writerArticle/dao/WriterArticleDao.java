package com.bc.pmpheep.back.commuser.writerArticle.dao;

import java.util.Map;

public interface WriterArticleDao {

	//插入写文章信息
		void insertWriteArticle(Map map);

		//更新写文章信息
		void updateIsStaging(Map map);
		
		//
		Map<String, Object>  queryWriteArticleInfo(Map<String, Object> map);
		/**
		 * 删除文章状态
		 * @param map
		 * @return
		 */
		int updateDelWriter(Map<String, Object> map);

		
		/**
		 * 更新文章封面信息
		 * @param map
		 */
		void updateArticleCover(Map map);
}
