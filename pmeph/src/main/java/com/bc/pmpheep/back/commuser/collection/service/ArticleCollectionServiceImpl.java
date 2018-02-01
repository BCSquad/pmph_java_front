package com.bc.pmpheep.back.commuser.collection.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bc.pmpheep.back.commuser.collection.dao.ArticleCollectionDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.ContentService;
import com.bc.pmpheep.general.service.MessageService;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 文章收藏服务层实现类
 * @param 
 * @return 
 * @throws
 */
@Service("com.bc.pmpheep.back.commuser.collection.service.ArticleCollectionServiceImpl")
public class ArticleCollectionServiceImpl implements ArticleCollectionService {
    @Autowired
    private ArticleCollectionDao articleCollectionDao;
    @Autowired
    private MessageService messageService;
    @Autowired
	ContentService contentService;
    //查询文章收藏夹以及收藏夹中收藏文章的数量
	@Override
	public List<Map<String, Object>> queryArticleCollectionList(BigInteger writerId) {
		// TODO Auto-generated method stub
		return articleCollectionDao.queryArticleCollectionList(writerId);
	}

	@Override
	public PageResult<Map<String,Object>> queryArticleList(PageParameter<Map<String,Object>> param) {
		PageResult<Map<String,Object>> result=new PageResult<>();
		result.setPageNumber(param.getPageNumber());
		result.setPageSize(param.getPageSize());
		int articlecont=articleCollectionDao.queryArticleCont(param.getParameter());
		List<Map<String, Object>> list=articleCollectionDao.queryArticleList(param);
		if(list.size()>0){
		for (Map<String, Object> map : list) {
			int like=articleCollectionDao.queryLikes((BigInteger) map.get("cid"), (BigInteger) param.getParameter().get("writerId"));
			map.put("like", like);
			if("DEFAULT".equals(map.get("avatar").toString())){
				map.put("avatar", "statics/image/deficon.png");
			}else{
				map.put("avatar", "file/download/"+map.get("avatar")+".action");
			}
			Content message=contentService.get((String) map.get("mid"));
			List<String> imglist = getImgSrc(message.getContent());
			    if(imglist!=null&&imglist.size()>0){
			    	map.put("imgpath", imglist.get(0));
			    }else{
			    	map.put("imgpath", "statics/image/articon.png");
			    }
		 }
		}
		result.setTotal(articlecont);
		result.setRows(list);
		return result;
	}

	//根据收藏夹id获取收藏夹内收藏的文章
	@Override
	public int queryArticleCont(BigInteger favoriteId, BigInteger writerId) {
		// TODO Auto-generated method stub
		return 0;
	}

    //对文章点赞或取消点赞
	@Override
	public Map<String,Object> updateLike(BigInteger contentId, BigInteger writerId) {
		Map<String,Object> map=new HashMap<String, Object>();
		BigInteger step=new BigInteger("1");
		Map<String,Object> numMap=articleCollectionDao.queryNum(contentId);
		BigInteger likes =(BigInteger) numMap.get("likes");
		BigInteger mlike=null;
		int count=articleCollectionDao.queryLikes(contentId, writerId);
		if(count>0){
			articleCollectionDao.deleteArticleLike(contentId, writerId);
			mlike=likes.subtract(step);
		}else{
			articleCollectionDao.insertArticelLike(contentId, writerId);
			mlike=likes.add(step);
		}
		articleCollectionDao.updateArticleLikes(contentId,mlike);
		map.put("likes", mlike);
		map.put("returncode", "OK");
		return map;
	}
	
	//取消对一篇文章的收藏
	@Override
	public Map<String, Object> deleteMark(BigInteger markId,BigInteger writerId, BigInteger favoriteId,
		BigInteger contentId) {
		Map<String,Object> map=new HashMap<>();
		BigInteger step=new BigInteger("1");
		Map<String,Object> numMap=articleCollectionDao.queryNum(contentId);
		BigInteger markes =(BigInteger) numMap.get("bookmarks");
		articleCollectionDao.updateMarkes(contentId, markes.subtract(step));
		articleCollectionDao.deleteMark(markId, writerId, favoriteId);
		map.put("returncode", "Ok");
		return map;
	}
	
	//删除收藏夹
	@Override
	public Map<String, Object> deleteFavorite(BigInteger writerId,
			BigInteger favoriteId) {
        Map<String,Object> map=new HashMap<>();
        BigInteger step=new BigInteger("1");
        List<Map<String,Object>> list=articleCollectionDao.queryOther(writerId, favoriteId);
        if(list.size()>0){
        for (Map<String, Object> art : list) {
        	articleCollectionDao.updateMarkes((BigInteger)art.get("cid"), ((BigInteger)art.get("bookmarks")).subtract(step));
		}
        }
        articleCollectionDao.deleteMark(null, writerId, favoriteId);
        articleCollectionDao.deleteFavorite(writerId, favoriteId);
		return map;
	}
	
    //	根据id查询文章收藏夹
	@Override
	public Map<String, Object> queryFavoriteById(BigInteger favoriteId) {
		// TODO Auto-generated method stub
		return articleCollectionDao.queryFavoriteById(favoriteId);
	}
	/**
     * 抓取HTML中src地址
     *
     * @param html html字符串
     * @return 符合图片特征的集合
     */
    public static List<String> getImgSrc(String html) {
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
