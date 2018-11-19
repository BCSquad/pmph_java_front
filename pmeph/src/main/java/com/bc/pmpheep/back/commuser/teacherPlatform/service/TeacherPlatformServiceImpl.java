package com.bc.pmpheep.back.commuser.teacherPlatform.service;
import com.bc.pmpheep.back.commuser.teacherPlatform.dao.TeacherPlatformDao;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.service.ContentService;
import org.apache.commons.collections.MapUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("com.bc.pmpheep.back.commuser.teacherPlatform.service.TeacherPlatformServiceImpl")
public class TeacherPlatformServiceImpl  implements TeacherPlatformService{

    @Autowired
    private TeacherPlatformDao teacherPlatformDao;

    @Autowired
    private ContentService contentService;

    @Override
    public Map<String, Object> queryXikb(String id,String state){
        Map<String, Object> map = teacherPlatformDao.queryXikb(id);
        String mid= MapUtils.getString(map,"activity_desc_cms_id");
        Content content = contentService.get(mid);
        if(state.equals("res")){
            map.put("content",omit(content.getContent().replaceAll("&nbsp;",""),1800));
        }else{
            map.put("content",content.getContent());
        }
        String t=map.get("times").toString();
        int times=Integer.parseInt(t)+1;
        teacherPlatformDao.addtimes(times,id);
        return map;
    }

    @Override
    public List<Map<String, Object>> Queryvideo(Map<String, Object> map) {
        List<Map<String, Object>> list=teacherPlatformDao.Queryvideo(map);
        return list;
    }

    @Override
    public List<Map<String, Object>> Querysource(Map<String, Object> map) {
        List<Map<String, Object>> list=teacherPlatformDao.Querysource(map);
        return list;
    }

    @Override
    public int updateclicks(int clicks, String id) {
        int count = teacherPlatformDao.updateclicks(clicks,id);
        return count;
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
     * 超出部分省略号显示
     *
     * @param content
     * @return
     */
    public String omit(String content, int length){
        String returncontent = "";
        content = removeHtml(content);
        //int le = content.getBytes("UTF-8").length;
        int le = content.length();
        if (le > length/4) {
            int n = length / 4;
            returncontent =content.substring(0, n) + "...";
        } else {
            returncontent = content;
        }
        return returncontent;
    }

    @Override
    public List<Map<String, Object>> QuerySourceList(String startrow)  {
        List<Map<String, Object>> list=teacherPlatformDao.QuerySourceList(startrow);
        for (Map<String, Object> map: list) {
            String mid= MapUtils.getString(map,"activity_desc_cms_id");
            if(!mid.equals("")){
                Content content = contentService.get(mid);
                map.put("content",omit(content.getContent().replaceAll("&nbsp;",""),1300));
            }
        }
        return list;
    }

    @Override
    public int sourceCount(String id) {
        return teacherPlatformDao.sourceCount(id);
    }

    @Override
    public int VideoCount(String id) {
        return teacherPlatformDao.VideoCount(id);
    }

    @Override
    public List<Map<String, Object>> QueryActivitiById(Map<String,Object> map) {
        List<Map<String, Object>> list=teacherPlatformDao.QueryActivitiById(map);
        for (Map<String, Object> pmap: list) {
            String mid= MapUtils.getString(pmap,"activity_desc_cms_id");
            if(!mid.equals("")){
                Content content = contentService.get(mid);
                pmap.put("content",removeHtml(content.getContent()));
            }
        }
        return list;
    }

    @Override
    public int queryClicks(String id) {
        return teacherPlatformDao.queryClicks(id);
    }
}
