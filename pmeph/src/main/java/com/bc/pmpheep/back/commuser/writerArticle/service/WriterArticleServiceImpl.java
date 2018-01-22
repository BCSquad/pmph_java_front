package com.bc.pmpheep.back.commuser.writerArticle.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.message.bean.UserMessage;
import com.bc.pmpheep.back.authadmin.message.dao.OrgMessageDao;
import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
import com.bc.pmpheep.back.commuser.writerArticle.dao.WriterArticleDao;
import com.bc.pmpheep.back.uncertainfieldcom.bean.CmsCategoryConfig;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.service.ContentService;
import com.bc.pmpheep.general.service.MessageService;
import com.bc.pmpheep.utils.SummaryUtil;

@Service("com.bc.pmpheep.back.commuser.writerArticle.service.WriterArticleServiceImpl")
public class WriterArticleServiceImpl implements WriterArticleService {

	@Autowired
	WriterArticleDao writerArticleDao;
	@Autowired
	ContentService contentService;
	@Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
    private PersonalService personalService;
	
	@Autowired
	CmsCategoryConfig cmsCategoryConfig;
	
	public Map<String, Object> insertWriteArticle(Map map,String UEContent) {
		// TODO Auto-generated method stub
		//发送消息 到MongoDB 
		Content content = new Content();
		content.setContent(UEContent);
		Content messageResult = contentService.add(content);
		String msg_id = messageResult.getId();
		map.put("mid", msg_id); //内容id
		String summary = SummaryUtil.htmlToText(UEContent);
		summary = summary.substring(0,Math.min(summary.length(), 60));
		map.put("summary", summary);
		map.put("category_id", cmsCategoryConfig.getId("医学随笔"));
		writerArticleDao.insertWriteArticle(map);
		/*if ("0".equals(map.get("is_staging").toString())) {
			personalService.saveUserTrendst("sbwz", map.get("table_trendst_id").toString(), 0, map.get("author_id").toString());
		}*/
		
		Map<String,Object> result_map = new HashMap<String, Object>();
		result_map.put("msg_id", msg_id);
		result_map.put("atrticle_id", map.get("table_trendst_id"));
		return result_map;
	}

	public String  updateIsStaging(Map map,String UEContent) {
		// TODO Auto-generated method stub
		//发送消息 到MongoDB  根据传过去的msg_id 去找到写的这个文章
		Content content = new Content();
		content.setContent(UEContent);
		Content messageResult = contentService.add(content);
		String mid = messageResult.getId();
		map.put("mid", mid); //内容id
		String summary = SummaryUtil.htmlToText(UEContent);
		summary = summary.substring(0,Math.min(summary.length(), 60));
		map.put("summary", summary);
		map.put("category_id", cmsCategoryConfig.getId("医学随笔"));
		writerArticleDao.updateIsStaging(map);
		/*if ("0".equals(map.get("is_staging").toString())) {
			personalService.saveUserTrendst("sbwz", map.get("atrticle_id").toString(), 0, map.get("author_id").toString());
		}*/
		
		return mid;
	}

	@Override
	public Map<String, Object> queryWriteArticleInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		return writerArticleDao.queryWriteArticleInfo(map);
	}

	public int updateDelWriter(Map<String, Object> map) {
		// TODO 自动生成的方法存根
		return writerArticleDao.updateDelWriter(map);
	}

}
