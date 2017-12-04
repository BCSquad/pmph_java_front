package com.bc.pmpheep.back.commuser.mygroup.service;


import com.bc.pmpheep.back.commuser.mygroup.dao.MyGroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cyx  on 2017/11/21
 */
@Service("com.bc.pmpheep.back.commuser.mygroup.service.MyGroupServiceImpl")
public class MyGroupServiceImpl implements MyGroupService {

    @Autowired
    private MyGroupDao myGroupDao;
    @Override
    public List<Map<String, Object>> getMyGroups(String userId) {
        List<Map<String,Object>> list2 = myGroupDao.getMyGroups(userId);
        List<Map<String,Object>> list3 = myGroupDao.getThreeInfo();
        // 以兩個for循環 拼接数据
        for(int i =0;i<list2.size();i++){
            Map<String, Object> map = new HashMap<String,Object>();
            List list4 = new ArrayList();
            for(int j =0;j<list3.size();j++){
                if(list2.get(i).get("id").toString().equals(list3.get(j).get("group_id").toString())){
                    list4.add(list3.get(j).get("threeInfo").toString());
                }
            }
            map.put("threeInfo",list4);
            list2.add(map);
        }

        return list2;
    }
}
