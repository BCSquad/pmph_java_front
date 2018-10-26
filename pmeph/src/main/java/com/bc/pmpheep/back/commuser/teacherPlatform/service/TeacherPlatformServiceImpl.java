package com.bc.pmpheep.back.commuser.teacherPlatform.service;
import com.bc.pmpheep.back.commuser.teacherPlatform.dao.TeacherPlatformDao;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.service.ContentService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("com.bc.pmpheep.back.commuser.teacherPlatform.service.TeacherPlatformServiceImpl")
public class TeacherPlatformServiceImpl  implements TeacherPlatformService{

    @Autowired
    private TeacherPlatformDao teacherPlatformDao;

    @Autowired
    private ContentService contentService;

    @Override
    public Map<String, Object> queryXikb(String id) {
        Map<String, Object> map = teacherPlatformDao.queryXikb(id);
        String mid= MapUtils.getString(map,"activity_desc_cms_id");
        Content content = contentService.get(mid);
        map.put("content",content.getContent());
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

    @Override
    public List<Map<String, Object>> QuerySourceList(String startrow) {
        List<Map<String, Object>> list=teacherPlatformDao.QuerySourceList(startrow);
        for (Map<String, Object> map: list) {
            String mid= MapUtils.getString(map,"activity_desc_cms_id");
            if(!mid.equals("")){
                Content content = contentService.get(mid);
                map.put("content",content.getContent());
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
        return list;
    }

    @Override
    public int queryClicks(String id) {
        return teacherPlatformDao.queryClicks(id);
    }
}
