package com.bc.pmpheep.back.commuser.articlepage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.articlepage.dao.ArticleSearchDao;
import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterUserTrendst;
import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
import com.bc.pmpheep.back.plugin.PageParameter;

@Service("com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService")
public class ArticleSearchServiceImpl implements ArticleSearchService {

	@Autowired
	private ArticleSearchDao articleSearchDao;
	
	@Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
    private PersonalService personalService;
	
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
	 * 查询移动多少条文章数据 此方法废弃
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
	public Map<String, Object> changeLikes(int likes,String id) {
		Map<String, Object> rmap=new HashMap<String, Object>();
		int count=articleSearchDao.changeLikes(likes,id);
		if(count>0){
			rmap.put("returncode", "OK");
		}
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

    /**
     * 查询登陆人是否对指定文章点过赞
     */
	@Override
	public List<Map<String, Object>> querydExit(String id, String writer_id) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=articleSearchDao.querydExit(id, writer_id);
		return list;
	}

	/**
	 * 根据用户ID和文章ID往点赞变里面添加数据
	 */
	@Override
	public String insertPraise(String content_id, String writer_id) {
		// TODO Auto-generated method stub
		String code="";
		int count=articleSearchDao.insertPraise(content_id, writer_id);
		WriterUserTrendst wut = new WriterUserTrendst(writer_id, 4, content_id);
		personalService.saveUserTrendst(wut);//文章搜索界面的点赞 生成动态
		if(count>0){
			 code="OK";
		}
		return code;
	}

	/**
	 * 删除点赞表里面的数据
	 */
	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		articleSearchDao.del(id);
	}

	@Override
	public List<Map<String, Object>> queryArticleByAdi(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String, Object>> result_list = articleSearchDao.queryArticleByAdi(pageParameter);
		return result_list;
	}

	@Override
	public int queryArticleByAdiCount(PageParameter<Map<String, Object>> pageParameter) {
		int count = articleSearchDao.queryArticleByAdiCount(pageParameter);
		return count;
	}
}
