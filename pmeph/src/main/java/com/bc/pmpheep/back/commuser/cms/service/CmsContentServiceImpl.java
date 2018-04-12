package com.bc.pmpheep.back.commuser.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService;
import com.bc.pmpheep.back.commuser.book.bean.BookVO;
import com.bc.pmpheep.back.commuser.cms.bean.CmsContentVO;
import com.bc.pmpheep.back.commuser.cms.dao.CmsContentDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.back.util.PageParameterUitl;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.service.ContentService;
import com.bc.pmpheep.general.service.FileService;
/**
 * 	CmsContentService 实现
 * @author Mr
 *
 */
@Service("com.bc.pmpheep.back.commuser.cms.service.CmsContentServiceImpl")
public class CmsContentServiceImpl implements CmsContentService{
	
	@Autowired
	private CmsContentDao cmsContentDao;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService")
	private ArticleSearchService articleSearchService;

	@Override
	public List<Map<String, Object>> listCms(
			PageParameter<Map<String, Object>> pageParameter,String contextpath) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("start", pageParameter.getStart());
		paraMap.put("pageSize", pageParameter.getPageSize());
		List<Map<String, Object>> resultList = cmsContentDao.listCmsContentVO(paraMap);
		
		for (Map<String,Object> cmsContentVO : resultList) {
			String authdate = "";
			if(cmsContentVO.get("auth_date")!= null) {
				 authdate = cmsContentVO.get("auth_date").toString().substring(0, 10);
			}
			cmsContentVO.put("auth_date", authdate);
			cmsContentVO.put("cover", RouteUtil.articleAvatar(MapUtils.getString(cmsContentVO, "cover")));
			//抓取文章图片
			/*if (cmsContentVO.get("mid")!=null) {
				Content content = contentService.get(cmsContentVO.get("mid").toString());
				String img_url = getFirstImgUrlFromHtmlStr(contextpath, content);
				cmsContentVO.put("first_img_url", img_url);
			}*/
		}
		
		return resultList;
	}
	
	
	/**
	 * 查询列表数据量
	 */
	@Override
	public Integer getCmsContentCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count = cmsContentDao.getCmsContentCount();
		Integer maxPageNum = (int) Math.ceil(1.0 * count
				/ pageParameter.getPageSize());
		return maxPageNum;
	}
	
	
	// 获取html图片
	private String getFirstImgUrlFromHtmlStr(String contextpath, Content content) {
		String img_url = contextpath
				+ "statics/image/564f34b00cf2b738819e9c35_122x122!.jpg";
		if (content != null) {
			List<String> imglist = articleSearchService.getImgSrc(content
					.getContent());
			if (imglist.size() > 0) {
				img_url = imglist.get(0);
				if (img_url.length() > 0) {
					img_url = contextpath + img_url;
				}
			}
		}
		return img_url;
	}

	
}
