package com.bc.pmpheep.back.commuser.help.service;
import com.bc.pmpheep.back.commuser.help.dao.HelpDao;
import com.bc.pmpheep.general.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("com.bc.pmpheep.back.commuser.help.service.HelpServiceImpl")
public class HelpServiceImpl implements HelpService {

    @Autowired
    private HelpDao helpDao;

    @Override
    public List<Map<String, Object>> queryHelpList(Map<String, Object> param) {
        // TODO Auto-generated method stub
        List<Map<String, Object>> list = helpDao.queryHelpList(param);
        if (list != null) {
            for (Map<String, Object> map : list) {
                String gmt_create = map.get("gmt_create").toString().substring(0, 10);
                map.put("gmt_create", gmt_create);
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> handbookList(Map<String, Object> param) {
        List<Map<String, Object>> list = helpDao.handbookList(param);
        return list;
    }

    @Override
    public Map<String, Object> queryDetail(Map<String, Object> param) {
        Map<String, Object> map = helpDao.queryDetail(param);
        return map;
    }

}
