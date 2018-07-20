package com.bc.pmpheep.general.service;

import com.bc.pmpheep.general.dao.ContentDao;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: SuiXinYang
 * @Description: MongoDB CMS内容服务
 * @Date: Created in 10:21 2017/11/15
 * @Modified: SuiXinYang
 **/
@Service("com.bc.pmpheep.general.service.ContentService")
public class ContentService {
    @Autowired
    ContentDao contentDao;

    /**
     * 新增Content对象
     *
     * @param content CMS内容对象
     * @return 返回插入对象(包括MongoDB生成的id)
     */
    public Content add(Content content) {
        if (null == content) {
            throw new CheckedServiceException(CheckedExceptionBusiness.CMS,
                    CheckedExceptionResult.NULL_PARAM, "内容更新对象为空");
        }
        if (null == content.getContent() || content.getContent().isEmpty()) {
            throw new CheckedServiceException(CheckedExceptionBusiness.CMS,
                    CheckedExceptionResult.NULL_PARAM, "内容更新对象内容为空");
        }
        return contentDao.save(content);
    }

    /**
     * 根据id查找Content对象
     *
     * @param id Content主键
     * @return 查找结果，未找到时返回null
     */
    public Content get(String id) {
        if (null == id || id.isEmpty()) {
            throw new CheckedServiceException(CheckedExceptionBusiness.CMS,
                    CheckedExceptionResult.NULL_PARAM, "内容获取时ID为空");
        }
        Content content = contentDao.findOne(id);
        if (content == null) {
            return new Content("", "");
        }else if(content.getContent()==null){
        	content.setContent("");
        }else{
        	replaceIp(content);
        }
        return content;
    }

    //去掉如下正则匹配的192.168.100.135/则
	private void replaceIp(Content content) {
		Pattern pa = Pattern.compile("(?<=<img .{0,2000}?src=\")(http://192.168.100.135)(?=/.*?\".*?/>)");
		Matcher ma = pa.matcher(content.getContent());
		String re = ma.replaceAll("");
		content.setContent(re);
	}
    
 

    /**
     * 批量查找Content对象
     *
     * @param ids 主键集合
     * @return 返回Content对象集合
     */
    public List<Content> list(List<String> ids) {
    	List<Content> list =(List<Content>) contentDao.findAll(ids);
    	for (Content content : list) {
            replaceIp(content);
		}
        return list;
    }

    /**
     * 更新Content对象
     *
     * @param content Content对象
     */
    public void update(Content content) {
        if (null == content) {
            throw new CheckedServiceException(CheckedExceptionBusiness.CMS,
                    CheckedExceptionResult.NULL_PARAM, "内容更新对象为空");
        }
        if (null == content.getId() || content.getId().isEmpty()) {
            throw new CheckedServiceException(CheckedExceptionBusiness.CMS,
                    CheckedExceptionResult.NULL_PARAM, "内容更新对象id为空");
        }
        if (null == content.getContent() || content.getContent().isEmpty()) {
            throw new CheckedServiceException(CheckedExceptionBusiness.CMS,
                    CheckedExceptionResult.NULL_PARAM, "Content更新对象内容为空");
        }
        Content ctn = get(content.getId());
        if (null == ctn) {
            throw new CheckedServiceException(CheckedExceptionBusiness.CMS,
                    CheckedExceptionResult.OBJECT_NOT_FOUND, "未找到更新对象");
        }
        ctn.setContent(content.getContent());
        contentDao.save(ctn);
    }

    /**
     * 删除给定id的Content对象
     *
     * @param id Content主键
     */
    public void delete(String id) {
        if (null == id || id.isEmpty()) {
            throw new CheckedServiceException(CheckedExceptionBusiness.CMS,
                    CheckedExceptionResult.NULL_PARAM, "删除内容时ID为空");
        }
        contentDao.delete(id);
    }

    /**
     * 本方法在业务中不提供，仅用于测试
     */
    @Deprecated
    public void removeAll() {
        contentDao.deleteAll();
    }
}
