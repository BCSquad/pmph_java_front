package com.bc.pmpheep.back.commuser.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.cms.dao.ArticleDetailDao;
import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterUserTrendst;
import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.ContentService;
import com.bc.pmpheep.general.service.MessageService;

/**
 * ArticleDetailServiceImpl 实现
 * 
 * @author sunzhuoqun
 *
 */
@Service("com.bc.pmpheep.back.commuser.cms.service.ArticleDetailServiceImpl")
public class ArticleDetailServiceImpl implements ArticleDetailService {

	@Autowired
	private ArticleDetailDao articleDetailDao;
	@Autowired
	private ContentService contentService;

	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
	private PersonalService personalService;

	/**
	 * 查询读书详情页信息
	 */
	@Override
	public Map<String, Object> queryRead(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = articleDetailDao.queryRead(id);
		return map;
	}

	/**
	 * 查询医学随笔详情页信息
	 */
	@Override
	public Map<String, Object> queryTitle(Map<String, Object> map) {
		Map<String, Object> map1 = articleDetailDao.queryTitle(map);
		return map1;
	}

	/**
	 * 插入文章评论
	 */
	public Map<String, Object> insertWriteArticle(Map<String, Object> map, String content) {
		Map<String, Object> rmap = new HashMap<String, Object>();
		// 发送评论到MongoDB
		Content message = new Content();
		message.setContent(content);
		Content messageResult = contentService.add(message);
		String msg_id = messageResult.getId();
		map.put("mid", msg_id); // 评论id
		articleDetailDao.insertWriteArticle(map);
		rmap.put("returncode", "OK");
		return rmap;

	}

	/**
	 * 查询作者
	 */
	@Override
	public Map<String, Object> queryAuthor(Map<String, Object> map) {
		Map<String, Object> map2 = articleDetailDao.queryAuthor(map);
		return map2;
	}

	/**
	 * 查询最近3条医学随笔
	 */
	@Override
	public List<Map<String, Object>> queryArticle(String author_id) {
		List<Map<String, Object>> list = articleDetailDao.queryArticle(author_id);
		return list;
	}

	/**
	 * 查询相关文章
	 */
	@Override
	public List<Map<String, Object>> queryRecommendByE(int num,String wid) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> map = articleDetailDao.queryRecommendByE(num, wid);
		int count=articleDetailDao.QueryAllShip(wid);
        for (Map<String, Object> smap: map ) {
            smap.put("end",count);
        }
		return map;
	}

	/**
	 * 查询相关文章条数
	 */
	@Override
	public int queryArticleCount(String author_id) {
		return articleDetailDao.queryArticleCount(author_id);
	}

	/**
	 * 查询相关评论
	 */
	@Override
	public PageResult<Map<String, Object>> queryComment(PageParameter<Map<String, Object>> pageParameter) {
		// TODO Auto-generated method stub
		PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
		pageResult.setPageSize(pageParameter.getPageSize());
		List<Map<String, Object>> map = articleDetailDao.queryComment(pageParameter);
		for (Map<String, Object> pmap : map) {
			String time = pmap.get("gmt_create").toString().substring(0, 16);
			pmap.put("gmt_create", time);
		}
		int count = articleDetailDao.querySize(pageParameter.getParameter().get("id").toString());
		pageResult.setTotal(count);
		pageResult.setRows(map);
		pageResult.setPageNumber(pageParameter.getPageNumber());
		return pageResult;
	}

	/**
	 * 查询猜您喜欢
	 */
	@Override
	public List<Map<String, Object>> queryArticleSix() {
		List<Map<String, Object>> list = articleDetailDao.queryArticleSix();
		for (Map<String, Object> map2 : list) {
			map2.put("cover", RouteUtil.articleAvatar(MapUtils.getString(map2, "cover")));
		}
		return list;
	}

	/**
	 * 根据书籍ID增加点赞数
	 */
	@Override
	public Map<String, Object> addlikes(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> pmap = new HashMap<String, Object>();
		if (("del").equals(map.get("flag"))) {
			articleDetailDao.addlikes(map);
			int count = articleDetailDao.dellikes(map);
			if (count > 0) {
				pmap.put("returncode", "yes");
			}
		} else {
			articleDetailDao.addlikes(map);
			articleDetailDao.insertlikes(map);
			WriterUserTrendst wut = new WriterUserTrendst(map.get("writer_id").toString(), 4,
					map.get("content_id").toString());
			personalService.saveUserTrendst(wut);// 文章详情界面的文章点赞 生成动态
			pmap.put("returncode", "no");
		}
		return pmap;
	}

	@Override
	public List<Map<String, Object>> queryLikes(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = articleDetailDao.queryLikes(map);
		return list;
	}

	/**
	 * 查询是否有收藏夹
	 */
	@Override
	public Map<String, Object> queryDedaultFavorite(String id) {
		// TODO Auto-generated method stub
		long userId = Long.valueOf(id);
		return articleDetailDao.queryDedaultFavorite(userId);
	}

	/**
	 * 查询默认收藏夹是否收藏本书
	 */
	@Override
	public int queryMark(String wid, String favorite_id, String writer_id) {
		// TODO Auto-generated method stub
		long wid1 = Long.valueOf(wid);
		long favorite_id1 = Long.valueOf(favorite_id);
		long writer_id1 = Long.valueOf(writer_id);
		return articleDetailDao.queryMark(wid1, favorite_id1, writer_id1);
	}

	/*
	 * 添加收藏
	 */
	@Override
	public Map<String, Object> inserMark(long wid, long writer_id) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> dmap = articleDetailDao.queryDedaultFavorite(writer_id);
		if (dmap == null) {
			articleDetailDao.insertFavorite(writer_id);
			dmap = articleDetailDao.queryDedaultFavorite(writer_id);
		}
		long favorite_id = Long.valueOf(dmap.get("id").toString());
		int count = articleDetailDao.queryMark(wid, favorite_id, writer_id);// 查询用户是否收藏某一本书，如果收藏 count大于0，否则，等于0
		long bookmarks = articleDetailDao.queryBookMarks(wid);// 查询书籍的收藏数
		if (count > 0) {
			articleDetailDao.deleteMark(wid, favorite_id, writer_id);
			articleDetailDao.updateMarks(wid, bookmarks - 1);// 更新书籍表中的收藏数量
			map.put("returncode", "remain");
		} else {
			articleDetailDao.insertMark(wid, favorite_id, writer_id);// 向用户书籍收藏表中加入收藏记录
			articleDetailDao.updateMarks(wid, bookmarks + 1);// 更新书籍表中的收藏数量
			WriterUserTrendst wut = new WriterUserTrendst(String.valueOf(writer_id), 3, String.valueOf(wid));
			personalService.saveUserTrendst(wut);// 文章详情界面的文章收藏 生成动态
			map.put("returncode", "OK");
		}
		return map;
	}

	/**
	 * 根据ID改变点击数
	 */
	@Override
	public void changeClicks(String id, int clicks) {
		// TODO Auto-generated method stub
		articleDetailDao.changeClicks(id, clicks);
	}

	/**
	 * 查询附件
	 * 
	 * @param paraMap
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryCMSAttach(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return articleDetailDao.queryCMSAttach(paraMap);
	}

	@Override
	public List<Map<String, Object>> QueryShipByID(String id,int startrow) {
		List<Map<String, Object>> list=new ArrayList<>();
		list=articleDetailDao.QueryShipByID(id,startrow);
		return list;
	}


    @Override
    public int QueryAllShip(String id) {
        return articleDetailDao.QueryAllShip(id);
    }

//	@Override
//	public void updateComments(Long id) {
//		// TODO Auto-generated method stub
//		articleDetailDao.updateComments(id);
//	}

}
