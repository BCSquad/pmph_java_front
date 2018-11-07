package com.bc.pmpheep.back.commuser.research.service;

import com.bc.pmpheep.back.authadmin.usermanage.bean.WriterUser;
import com.bc.pmpheep.back.commuser.research.dao.ReSearchDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("com.bc.pmpheep.back.commuser.research.service.ReSearchServiceImpl")
public class ReSearchServiceImpl implements ReSearchService{

    @Autowired
    private ReSearchDao reSearchDao;

    @Override
    public PageResult<Map<String, Object>> querySearchList(PageParameter<Map<String, Object>> pageParameter) {
        PageResult<Map<String, Object>> pageResult= new PageResult<Map<String,Object>>();
        pageResult.setPageNumber(pageParameter.getPageNumber());
        pageResult.setPageSize(pageParameter.getPageSize());
        List<Map<String,Object>> list = reSearchDao.querySearchList(pageParameter);
        int count = reSearchDao.queryCount();
        pageResult.setRows(list);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public List<Map<String,Object>> querySearchByMaterialId(String material_id) {
        return reSearchDao.querySearchByMaterialId(material_id);
    }

    /*@Override
    public List<Map<String, Object>> queryQuestionBySearchId(String id) {
        List<Map<String, Object>> list=reSearchDao.queryQuestionBySearchId(id);
        List<Map<String,Object>> list_Sur=new ArrayList<>();
        for (int i=0;i<list.size();i++) {
            String question_id= list.get(i).get("question_id").toString();
            for(int j=0;j<list.size();j++){
                String question_id_ano=list.get(i).get("question_id").toString();
                if(question_id.equals(question_id_ano)){
                    Map<String,Object> map = new HashMap<>();
                    map.put("question_title",list.get(j).get("question_title"));
                    map.put("option_content",list.get(j).get("option_content"));
                    map.put("type",list.get(j).get("type"));
                    list_Sur.add(map);
                }
            }
        }
        return null;
    }*/
}
