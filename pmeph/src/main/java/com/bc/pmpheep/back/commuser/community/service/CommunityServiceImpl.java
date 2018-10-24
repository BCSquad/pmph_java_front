package com.bc.pmpheep.back.commuser.community.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.MapUtils;
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
				if(content!=null){
					map.put("content", removeHtml(content.getContent()));
				}else{
					map.put("content", "(内容为空)");
				}	
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
	public List<Map<String, Object>> queryTextBookList(Long id,int start,int pageSize) {
		// TODO Auto-generated method stub
		return communityDao.queryTextBookList(id,start,pageSize);
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
				//map.put("imagepath",imglist.size()>0? imglist.get(0):"defualt" );
				map.put("imagepath", MapUtils.getString(map,"image_url","/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg"));
				map.put("contentxt", removeHtml(map.get("content").toString()));
				if(map.get("avatar") !=null && (map.get("avatar").equals("DEFAULT")
				  ||map.get("avatar").equals(""))){
					map.put("avatar", "statics/image/default_image.png");
				}else{
					map.put("avatar", "image/"+map.get("avatar")+".action");
				}
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
	/**查询教材中书籍微视频
     * @param map  startnum:分页的开始的序号      size:分页的数据容量   materialId:教材id
     * @return
     */
	@Override
	public List<Map<String, Object>> queryVidos(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return communityDao.queryVidos(map);
	}
	
	 /**查询教材中书籍微视频的总数
     * @param map   materialId:教材id
     * @return int
     */
	@Override
	public int queryVidoCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return communityDao.queryVidoCount(map);
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

	/*
	 * id查询视频播放量
	 */
	@Override
	public Map<String, Object> videoCount(String vid) {
		// TODO Auto-generated method stub
		return communityDao.videoCount(vid);
	}

	/**
	 * 根据ID改变播放量
	 */
	@Override
	public void changeClicks(Map<String, Object> map) {
		// TODO Auto-generated method stub
		communityDao.changeClicks(map);
	}

	@Override
	public int countTextBookList(Long material_id) {
		return communityDao.countTextBookList(material_id);
	}

	@Override
	public List<Map<String, Object>> QueryActivitiById(Map<String,Object> map) {
		List<Map<String, Object>> list=communityDao.QueryActivitiById(map);
		return list;
	}


}
