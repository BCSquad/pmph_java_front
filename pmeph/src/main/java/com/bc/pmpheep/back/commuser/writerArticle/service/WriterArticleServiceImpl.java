package com.bc.pmpheep.back.commuser.writerArticle.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.message.bean.UserMessage;
import com.bc.pmpheep.back.authadmin.message.dao.OrgMessageDao;
import com.bc.pmpheep.back.commuser.writerArticle.dao.WriterArticleDao;

@Service("com.bc.pmpheep.back.commuser.writerArticle.service.WriterArticleServiceImpl")
public class WriterArticleServiceImpl implements WriterArticleService {

	@Autowired
	WriterArticleDao writerArticleDao;
	
	
	public void insertWriteArticle(Map map) {
		// TODO Auto-generated method stub
		writerArticleDao.insertWriteArticle(map);
	}

	public void updateIsStaging(Map map) {
		// TODO Auto-generated method stub
		writerArticleDao.updateIsStaging(map);
	}

	@Override
	public Map<String, Object> queryWriteArticleInfo(Map map) {
		// TODO Auto-generated method stub
		
		return writerArticleDao.queryWriteArticleInfo(map);
	}

}
