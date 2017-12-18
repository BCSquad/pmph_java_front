package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import jxl.format.Colour;
import org.apache.commons.collections.map.HashedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.applydocaudit.dao.DataAuditDao;
import com.bc.pmpheep.back.authadmin.applydocaudit.dao.DeclareCountDao;
import com.bc.pmpheep.general.service.ExcelDownloadService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sunzhuoqun on 2017/12/13.
 */
@Service("declareCountExcel")
public class DeclareCountExcelService implements ExcelDownloadService {
	
	@Autowired
	DeclareCountDao declareCountDao;
	
	
    @Override
    public String getTitle() {
        return "我校申报情况统计";
    }

    @Override
    public String[][] getColTitle() {
        return new String[][]{{"书名", "textbook_name"}, {"主编申报数", "dp1"}, {"副主编申报数", "dp2"}, {"编委申报数", "dp3"}, {"主编当选数", "decid1"}, {"副主编当选数", "decid2"}, {"编委当选数", "decid3"}};
    }

    @Override
    public Colour getTitleColour() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getData(Map<String, Object> param) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        List<Map<String, Object>> resultList = declareCountDao
				.selectAll(param);
        
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("textbook_name", resultList.get(i).get("textbook_name"));
            map.put("dp1", resultList.get(i).get("dp1"));
            map.put("dp2", resultList.get(i).get("dp2"));
            map.put("dp3", resultList.get(i).get("dp3"));
            map.put("decid1", resultList.get(i).get("decid1"));
            map.put("decid2", resultList.get(i).get("decid2"));
            map.put("decid3", resultList.get(i).get("decid3"));
            
            list.add(map);
        }

        return list;
    }
}