package com.bc.pmpheep.back.commuser.homepage.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService;
import com.bc.pmpheep.back.commuser.homepage.dao.HomeDao;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.service.ContentService;


@Service("com.bc.pmpheep.back.homepage.service.HomeServiceImpl")
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeDao homeDao;
    
    @Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService")
	private ArticleSearchService articleSearchService;
    
    @Autowired
	private ContentService contentService;

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
    //@Cacheable(value = "commDataCache", key = "#root.targetClass+#root.methodName")
    public List<Map<String, Object>> queryNotice(String contextpath) {
        List<Map<String, Object>> list = homeDao.queryNotice();
        if (list.size()>0) {
        	String mid = list.get(0).get("mid").toString();
        	//抓取文章图片
			if (mid!=null) {
				Content content = contentService.get(mid);
				String img_url = getFirstImgUrlFromHtmlStr(content,contextpath);
				list.get(0).put("first_img_url", img_url);
			}
		}
        return list;
    }
    
 // 获取html图片
 	private String getFirstImgUrlFromHtmlStr(Content content,String contextpath) {
 		String img_url = 
 				"none";
 		if (content != null) {
 			List<String> imglist = this.getImgSrc(content
 					.getContent(),contextpath);
 			if (imglist.size() > 0) {
 				img_url = imglist.get(0);
 			}
 		}
 		return img_url;
 	}
 	
 	private List<String> getImgSrc(String html,String contextpath){
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
            
            Matcher m = Pattern.compile("src\\s*=\\s*\".*?\"").matcher(img);
            while (m.find()) {
            	String first_src = m.group();
            	Matcher mdb_src = Pattern.compile("src\\s*=\\s*\".*?([A-z0-9]{24}?)").matcher(first_src);
            	Matcher http_src = Pattern.compile("src\\s*=\\s*\"(.*?)\"").matcher(first_src);
            	if (mdb_src.find()) {
            		pics.add(contextpath+"/image/"+mdb_src.group(1)+".action");
				}else if(http_src.find()){
					pics.add(http_src.group(1));
				}else{
					pics.add("");
            }
                
        }
        }
        return pics;
 	}

    /**
     * 查询医学随笔
     */
    @Override
    @Cacheable(value = "commDataCache", key = "#root.targetClass+#root.methodName+#endrow")
    public List<Map<String, Object>> queryArticle(int endrow) throws Exception {
        List<Map<String, Object>> list = homeDao.queryArticle(endrow);
        for (Map<String, Object> map : list) {
            String time = map.get("auth_date").toString().substring(0, 10);
            map.put("auth_date", time);
            map.put("cover", RouteUtil.articleAvatar(MapUtils.getString(map, "cover")));
            String con = MapUtils.getString(map, "summary", "");
            String content = omit(con, 208);
            map.put("content", content);
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
    public List<Map<String, Object>> queryComment() throws UnsupportedEncodingException {
        List<Map<String, Object>> list = homeDao.queryComment();
        for (Map<String, Object> map : list) {
            String con = map.get("content").toString();
            String content = omit(con, 200);
            map.put("content", content);
        }
        return list;
    }

    /**
     * 查询书籍
     */
    @Override
    //@Cacheable(value = "commDataCache", key = "#root.targetClass+#root.methodName+#map['type']+'_'+#map['startrows']")
    public List<Map<String, Object>> queryBook(Map<String, Object> map) {
        List<Map<String, Object>> list = homeDao.queryBook(map);
        for (Map<String, Object> pmap : list) {
        	
            String scoreStr = pmap.get("score").toString();
            int score = (int) Math.floor(Double.parseDouble(scoreStr));
            pmap.put("score", score);
        }
        return list;
    }

    /**
     * 查询销量最高的书
     */
    @Override
    @Cacheable(value = "commDataCache", key = "#root.targetClass+#root.methodName+#type")
    public List<Map<String, Object>> querySale(int type) throws UnsupportedEncodingException {
        List<Map<String, Object>> list = homeDao.querySale(type);
        for (Map<String, Object> map : list) {
            String a = map.get("bookname").toString();
            String name = omit(a, 52);
            map.put("bookname", name);
        }
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
        Map<String, Object> adInfo =new HashMap<String, Object>();
        adInfo= homeDao.getPageAdInfo(adName);
        if(adInfo!=null){
            adInfo.put("detailList", homeDao.getPageAdDetail(adName));
        }
        return adInfo;
    }

    /**
     * 添加好友
     */
    @Override
    public String addfriend(String request_id, String target_id) {
        // TODO Auto-generated method stub
        String returncode = "";
        int count = homeDao.addfriend(request_id, target_id);
        if (count > 0) {
            returncode = "OK";
        }
        return returncode;
    }

    /**
     * 查询教材社公告
     */
    @Override
    public List<Map<String, Object>> queryMaterial(String id) {
        // TODO Auto-generated method stub
        List<Map<String, Object>> list = homeDao.queryMaterial(id);
        return list;
    }

    /**
     * 超出部分省略号显示
     *
     * @param content
     * @return
     */
    @Override
    public String omit(String content, int length) throws UnsupportedEncodingException {
        String returncontent = "";
        int le = content.getBytes("UTF-8").length;
        if (le > length && !content.equals(null)) {
            int n = length / 4;
            returncontent =removeHtml(content).substring(0, n) + "...";
        } else {
            returncontent = removeHtml(content);
        }
        return returncontent;
    }


    @Override
    public int countBookByType(String type) {
        return this.homeDao.countBookByType(type);
    }

    @Override
    public int countSurvey(String logUserId) {
    	if (logUserId == null) {
    		return 0;
		}else{
			return homeDao.countSurvey(logUserId);
		}
        
    }

    @Override
    public List<Map<String, Object>> queryNotReadMessages(String id) {
        return homeDao.queryNotReadMessages(id);
    }

    @Override
    public List<Map<String, Object>> queryHotCommentList(int startnum,int size,String contextpath) throws UnsupportedEncodingException  {
        // TODO Auto-generated method stub
        Map<String,Object> param=new HashMap<String, Object>();
        param.put("startnum", startnum);
        param.put("size", size);
        List<Map<String, Object>> list = homeDao.queryHotCommentList(param);
        if(list!=null){
            for (Map<String, Object> map : list) {
                //List<String> imglist = getImgSrc(map.get("content").toString(),contextpath);
                map.put("imagepath",map.get("image_url").toString() );
                String con = map.get("content").toString();
                String contentxt=omit(con,500);
                map.put("contentxt", removeHtml(contentxt));
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
    public int queryHotCommentListCount() {
        // TODO Auto-generated method stub
        return homeDao.queryHotCommentListCount();
    }

    //去掉字符串中的html标签
    public String removeHtml(String str){
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(str);
        str=m_html.replaceAll(""); //过滤html标签
        return str;
    }

}
