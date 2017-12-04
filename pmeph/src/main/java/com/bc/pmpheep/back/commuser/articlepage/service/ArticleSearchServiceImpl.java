package com.bc.pmpheep.back.commuser.articlepage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.articlepage.dao.ArticleSearchDao;

@Service("com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService")
public class ArticleSearchServiceImpl implements ArticleSearchService {

	@Autowired
	private ArticleSearchDao articleSearchDao;
	
	/**
	 * 初始化文章页面
	 */
	@Override
	public List<Map<String, Object>> searchArticle(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=articleSearchDao.searchArticle(map);
		for (Map<String, Object> imap : list) {
			String time=imap.get("gmt_create").toString().substring(0, 10);
			imap.put("gmt_create", time);
		}
		return list;
	}

	/**
	 * 查询移动多少条文章数据
	 */
	@Override
	public List<Map<String, Object>> queryList() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=articleSearchDao.queryList();
		return list;
	}

	/**
	 * 改变点赞数
	 */
	@Override
	public Map<String, Object> changeLikes(Map<String, Object> map) {
		Map<String, Object> rmap=new HashMap<String, Object>();
		articleSearchDao.changeLikes(map);
		if(map.get("status").equals("down")){
			articleSearchDao.del(map);
		}else{
			articleSearchDao.insertPraise(map);
		}
		rmap.put("returncode", "OK");
		return rmap;
	}

	/**
	 * 根据ID查询
	 */
	@Override
	public Map<String, Object> queryById(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> map=articleSearchDao.queryById(id);
		return map;
	}

	/**
	 * 查询当前文章是否点过赞
	 */
	@Override
	public List<Map<String, Object>> queryPraise(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=articleSearchDao.queryPraise(map);
		return list;
	}

	/**
	 * 模糊查询
	 */
	@Override
	public List<Map<String, Object>> queryall(String title) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=articleSearchDao.queryall(title);
		return list;
	}

	/**
     * 抓取HTML中src地址
     * @param html html字符串
     * @return 符合图片特征的集合
     */
    public List<String> getImgSrc(String html) {
        String img = "";
        Pattern p_image;
        Matcher m_image;
        List<String> pics = new ArrayList<>(16);
        //String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址  
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(html);
        while (m_image.find()) {
            img = img + "," + m_image.group();
            // Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //匹配src  
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }
}
