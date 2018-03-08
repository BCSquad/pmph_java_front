package com.bc.pmpheep.back.commuser.messagereport.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.messagereport.dao.InfoReportDao;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.ContentService;
import com.bc.pmpheep.general.service.MessageService;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 信息快报服务接口实现
 * @param 
 * @return 
 * @throws
 */
/**
 * @author 
 *@Title: 
 * @Description: 
 * @param 
 * @return 
 * @throws
 */
@Service("com.bc.pmpheep.back.commuser.messagereport.service.InfoReportServiceImpl")
public class InfoReportServiceImpl implements InfoReportService {

	@Autowired
	private InfoReportDao infoReportDao;
	@Autowired
    private MessageService messageService;
	@Autowired
	ContentService contentService;
	/* 
	 * 根据信息id查询信息快报
	 */
	@Override
	public Map<String, Object> queryInfoReportById(long id, Map<String, Object> usermap) {
		// TODO Auto-generated method stub
		Map<String,Object> map=infoReportDao.queryInfoReportById(id);
		int likecount=0;
		int markcount=0;
		if(usermap!=null){
		long userId=Long.valueOf(usermap.get("id").toString());	
		likecount=infoReportDao.queryLike(id,userId);
		markcount=infoReportDao.queryMark(id,userId);
		}
		map.put("likecount", likecount);
		map.put("markcount", markcount);
		if(map!=null){
			Content message=contentService.get(map.get("mid").toString());
		    if(message!=null){
			 map.put("cmsText", message.getContent());
		    }else{
		    	map.put("cmsText","");
		    }
		}
		return map;
	}
	/* 
	 * 查询信息快报的列表
	 */
	@Override
	public List<Map<String, Object>> queryReportList(int num, int size,String materialId) {
		// TODO Auto-generated method stub
		return infoReportDao.queryReportList(num,size,materialId);
	}
	@Override
	public int getInfoReportCount(String materialId) {
		// TODO Auto-generated method stub
		return infoReportDao.queryInfoReportCount(materialId);
	}
	
	/* 
	 * 添加点赞或取消点赞
	 */
	@Override
	public Map<String,Object> insertLike(long id, long userId) {
		Map<String,Object> map=new HashMap<>();
		int likecount=infoReportDao.queryLike(id, userId);
		Map<String,Object> rmap=infoReportDao.queryInfoReportById(id);
        long likes=Long.valueOf(rmap.get("likes").toString());
		// likecount>0  表示已点过赞了，就要取消点赞；否则，添加点赞记录		
		if(likecount>0){
			infoReportDao.deleteLike(id, userId);
			infoReportDao.updateLikes(id,likes-1);
			map.put("returncode", "NO");
		}else{
			infoReportDao.insertLike(id, userId);
			infoReportDao.updateLikes(id,likes+1);
			map.put("returncode", "YES");
		}
		return map;
	}
	
	
	/* 
	 * 添加收藏或取消收藏	 
	 */
	@Override
	public Map<String,Object> insertMark(long id, long userId) {
		Map<String,Object> map=new HashMap<>();
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("id", id);
		param.put("userId", userId);
		//查询信息快报相关信息
		Map<String, Object> rmap = infoReportDao.queryInfoReportById(id);
		long marks=Long.valueOf(rmap.get("bookmarks").toString());
		//查询默认收藏夹
		Map<String,Object> dmap=infoReportDao.queryDefaultFavorite(userId);
		if(dmap!=null){
			//用户存在默认收藏夹
			param.put("fid", dmap.get("id"));
			//查询信息快报在默认收藏夹中是否有收藏记录
			int count = infoReportDao.queryDefaultMark(param);
			if(count>0){
				//有收藏记录删除收藏记录，更新信息快报的总收藏数
				infoReportDao.deleteMark(param);
				infoReportDao.updateMarks(id, marks-1);
				map.put("returncode", "NO");
			}else{
				//没有收藏记录删除收藏记录，添加收藏记录，更新信息快报的总收藏数
				infoReportDao.insertMark(param);
				infoReportDao.updateMarks(id, marks+1);
				map.put("returncode", "YES");
			}
		}else{
			//用户不存在默认收藏夹
			//为用户添加默认收藏夹，添加收藏记录，更新总收藏数
			infoReportDao.insertDefaultFavorite(userId);
			dmap=infoReportDao.queryDefaultFavorite(userId);
			param.put("fid", dmap.get("id"));
			infoReportDao.insertMark(param);
			infoReportDao.updateMarks(id, marks+1);
			map.put("returncode", "YES");
		}
		
		return map;
	}
	@Override
	public void updateClicks(long id, long clicks) {
		infoReportDao.updateClicks(id,clicks);
	}

}
