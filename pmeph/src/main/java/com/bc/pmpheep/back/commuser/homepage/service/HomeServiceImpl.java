package com.bc.pmpheep.back.commuser.homepage.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.homepage.dao.HomeDao;
import com.bc.pmpheep.back.util.RouteUtil;


@Service("com.bc.pmpheep.back.homepage.service.HomeServiceImpl")
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeDao homeDao;

    /**
     * 查询公告
     */
    @Override
   /* @Cacheable(value = "commDataCache", key = "#root.targetClass+#root.methodName+#id")*/
    public List<Map<String, Object>> queryDocument(String id) {
        List<Map<String, Object>> list = homeDao.queryDocument(id);
        return list;
    }

    /**
     * 查询信息快报
     */
    @Override
    @Cacheable(value = "commDataCache", key = "#root.targetClass+#root.methodName")
    public List<Map<String, Object>> queryNotice() {
        List<Map<String, Object>> list = homeDao.queryNotice();
        return list;
    }

    /**
     * 查询医学随笔
     */
    @Override
    @Cacheable(value = "commDataCache", key = "#root.targetClass+#root.methodName+#endrow")
    public List<Map<String, Object>> queryArticle(int endrow) {
        List<Map<String, Object>> list = homeDao.queryArticle(endrow);
        for (Map<String, Object> map : list) {
            String time = map.get("auth_date").toString().substring(0, 10);
            map.put("auth_date", time);
            map.put("cover", RouteUtil.articleAvatar(MapUtils.getString(map, "cover")));
        }
        return list;
    }

    /**
     * 查询推荐作者
     */
    @Override
    //@Cacheable(value = "commDataCache", key = "#root.targetClass+#root.methodName")
    public List<Map<String, Object>> queryAuthor(String logUserId) {
        List<Map<String, Object>> list = homeDao.queryAuthor(logUserId);
        return list;
    }

    /**
     * 查询书评
     */
    @Override
    @Cacheable(value = "commDataCache", key = "#root.targetClass+#root.methodName")
    public List<Map<String, Object>> queryComment() {
        List<Map<String, Object>> list = homeDao.queryComment();
        return list;
    }

    /**
     * 查询书籍
     */
    @Override
    @Cacheable(value = "commDataCache", key = "#root.targetClass+#root.methodName+#map['type']+'_'+#map['startrows']")
    public List<Map<String, Object>> queryBook(Map<String, Object> map) {
        List<Map<String, Object>> list = homeDao.queryBook(map);
        for (Map<String, Object> pmap: list) {
            String score=pmap.get("score").toString().substring(0,1);
            pmap.put("score",score);
        }
        return list;
    }

    /**
     * 查询销量最高的书
     */
    @Override
    @Cacheable(value = "commDataCache", key = "#root.targetClass+#root.methodName+#type")
    public List<Map<String, Object>> querySale(int type) {
        List<Map<String, Object>> list = homeDao.querySale(type);

        return list;
    }

    /**
     * 查询书籍分类
     */
    @Override
    public List<Map<String, Object>> queryBookType(int parent_id) {
        List<Map<String, Object>> list = homeDao.queryBookType(parent_id);
        return list;
    }

    /**
     * 查询热门标签
     */
    @Override
    @Cacheable(value = "commDataCache", key = "#root.targetClass+#root.methodName+#typeid")
    public List<Map<String, Object>> queryLabel(long typeid) {
        String typepath = "0-" + typeid + "-%";
        List<Map<String, Object>> list = homeDao.queryLabel(typepath);
        return list;
    }


    /**
     * 获取页面广告，用名称匹配
     */
    @Override
    public Map<String, Object> getPageAdInfo(String adName) {
        Map<String, Object> adInfo = homeDao.getPageAdInfo(adName);
        adInfo.put("detailList", homeDao.getPageAdDetail(adName));
        return adInfo;
    }

    /**
     * 添加好友
     */
	@Override
	public String addfriend(String request_id, String target_id) {
		// TODO Auto-generated method stub
		String returncode="";
		int count=homeDao.addfriend(request_id, target_id);
		if(count>0){
			returncode="OK";
		}
		return returncode;
	}

	/**
	 * 查询教材社公告
	 */
	@Override
	public List<Map<String, Object>> queryMaterial(String id) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=homeDao.queryMaterial(id);
		return list;
	}

	@Override
	public int countBookByType(String type) {
		return this.homeDao.countBookByType(type);
	}

    @Override
    public int countSurvey() {
        return homeDao.countSurvey();
    }

    @Override
    public List<Map<String, Object>> queryNotReadMessages(String id) {
        return homeDao.queryNotReadMessages(id);
    }
}
