package com.bc.pmpheep.back.commuser.writerArticle.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.message.bean.UserMessage;
import com.bc.pmpheep.back.authadmin.message.dao.OrgMessageDao;
import com.bc.pmpheep.back.commuser.writerArticle.dao.WriterArticleDao;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;

@Service("com.bc.pmpheep.back.commuser.writerArticle.service.WriterArticleServiceImpl")
public class WriterArticleServiceImpl implements WriterArticleService {

	@Autowired
	WriterArticleDao writerArticleDao;
	@Autowired
	MessageService mssageService;
	
	public String insertWriteArticle(Map map,String UEContent) {
		// TODO Auto-generated method stub
		//发送消息 到MongoDB 
		Message message = new Message();
		message.setContent(UEContent);
		Message messageResult = mssageService.add(message);
		String msg_id = messageResult.getId();
		map.put("mid", msg_id); //内容id
		writerArticleDao.insertWriteArticle(map);
		return msg_id;
	}

	public String  updateIsStaging(Map map,String UEContent) {
		// TODO Auto-generated method stub
		//发送消息 到MongoDB  根据传过去的msg_id 去找到写的这个文章
		Message message = new Message();
		message.setContent(UEContent);
		Message messageResult = mssageService.add(message);
		String mid = messageResult.getId();
		map.put("mid", mid); //内容id
		writerArticleDao.updateIsStaging(map);
		return mid;
	}

	@Override
	public Map<String, Object> queryWriteArticleInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		return writerArticleDao.queryWriteArticleInfo(map);
	}

}
