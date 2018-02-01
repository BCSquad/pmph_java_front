package com.bc.pmpheep.back.commuser.community.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.community.dao.CommunityDao;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.service.ContentService;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 教材服务接口实现类
 * @param 
 * @return 
 * @throws
 */

@Service("com.bc.pmpheep.back.commuser.community.service.CommunityServiceImpl")
public class CommunityServiceImpl implements CommunityService {
	@Autowired
	private CommunityDao communityDao;
	@Autowired
	ContentService contentService;
	/* 
	 * 查询公告列表
	 */
	@Override
	public List<Map<String, Object>> queryNoticeList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=communityDao.queryNoticeList(param);
		if(list!=null){
			for (Map<String, Object> map : list) {
				Content content = contentService.get(map.get("mid").toString());
				map.put("content", removeHtml(content.getContent()));	
			}
		}
		return list;
	}
	/* 
	 * id查询公告
	 */
	@Override
	public Map<String, Object> queryNoticeById(Long id) {
		// TODO Auto-generated method stub
		return communityDao.queryNoticeById(id);
	}
	/* 
	 * 根据教材id查询社区快报列表
	 */
	@Override
	public List<Map<String, Object>> queryMaterialNoticeList(Long id) {
		// TODO Auto-generated method stub
		return communityDao.queryMaterialNoticeList(id);
	}
	/* 
	 * 根据教材id查询本套教材的图书 
	 */
	@Override
	public List<Map<String, Object>> queryTextBookList(Long id) {
		// TODO Auto-generated method stub
		return communityDao.queryTextBookList(id);
	}
	@Override
	public List<Map<String, Object>> querySomeComment(Long id,int startnum,int size) {
		// TODO Auto-generated method stub
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("startnum", startnum);
		param.put("size", size);
		param.put("materialId", id);
		List<Map<String, Object>> list = communityDao.querySomeComment(param);
		if(list!=null){
			for (Map<String, Object> map : list) {
				List<String> imglist = getImgSrc(map.get("content").toString());
				map.put("imagepath",imglist.size()>0? imglist.get(0):"defualt" );
				map.put("contentxt", removeHtml(map.get("content").toString()));
			}
		}
		return list;
	}
	
    //查询社区精彩书评的总数量
	@Override
	public int queryCommentCount(Long id) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("materialId", id);
		return communityDao.queryCommentCount(map);
	}
	
	//去掉字符串中的html标签
	public String removeHtml(String str){
		String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
		Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(str); 
        str=m_html.replaceAll(""); //过滤html标签 
		return str;
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
