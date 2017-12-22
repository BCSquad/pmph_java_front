package com.bc.pmpheep.back.commuser.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.cms.dao.ArticleDetailDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;
/**
 * 	ArticleDetailServiceImpl 实现
 * @author sunzhuoqun
 *
 */
@Service("com.bc.pmpheep.back.commuser.cms.service.ArticleDetailServiceImpl")
public class ArticleDetailServiceImpl implements ArticleDetailService{
	
	@Autowired
	private ArticleDetailDao articleDetailDao;
	@Autowired
	MessageService mssageService;
	
	/**
	 * 查询医学随笔详情页信息
	 */
	@Override
	public Map<String, Object> queryTitle(Map<String, Object> map) {
		Map<String, Object> map1=articleDetailDao.queryTitle(map);
		return map1;
	}
	
	/**
	 * 插入文章评论
	 */
	public Map<String, Object> insertWriteArticle(Map<String, Object> map,String content) {
		Map<String, Object> rmap=new HashMap<String, Object>();
		//发送评论到MongoDB 
		Message message = new Message();
		message.setContent(content);
		Message messageResult = mssageService.add(message);
		String msg_id = messageResult.getId();
		map.put("mid", msg_id); //评论id
		articleDetailDao.insertWriteArticle(map);
	    rmap.put("returncode", "OK");
	    return rmap;
	}	
	
	/**
	 * 查询作者
	 */
	@Override
	public Map<String, Object> queryAuthor(Map<String, Object> map){
		Map<String, Object> map2=articleDetailDao.queryAuthor(map);
		return map2;
	}
	
	/**
	 * 查询最近3条医学随笔
	 */
	@Override
	public List<Map<String, Object>> queryArticle(String author_id){
		List<Map<String, Object>> list=articleDetailDao.queryArticle(author_id);
		return list;
	}
	
	/**
	 * 查询相关文章
	 */
	@Override
	public List<Map<String, Object>> queryRecommendByE(int num) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> map=articleDetailDao.queryRecommendByE(num);
		return map;
	}

	/**
	 * 查询相关文章条数
	 */
	@Override
	public int queryArticleCount(String author_id) {
		return articleDetailDao.queryArticleCount(author_id);
	}

}
