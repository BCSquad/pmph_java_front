package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import java.util.*;

import com.bc.pmpheep.back.authadmin.applydocaudit.dao.DataAuditDao;
import com.bc.pmpheep.back.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.applydocaudit.dao.DeclareCountDao;
import com.bc.pmpheep.general.service.ExcelDownloadService;

import jxl.format.Colour;



/**
 * 最终结果名单
 * Created by sunzhuoqun on 2017/12/13.
 */
@Service("declareResultExcel")
public class DeclareResultExcelService implements ExcelDownloadService {
	
	@Autowired
	DeclareCountDao declareCountDao;
    @Autowired
    DataAuditDao dataAuditDao;
	
	
    @Override
    public String getTitle(Map<String, Object> param) {
        return "最终结果名单";
    }

    @Override
    public String[][] getColTitle(Map<String, Object> param) {
        return new String[][]{{"书名", "textbook_name"}, {"主编名单", "zb"}, {"副主编名单", "fb"}, {"编委名单", "bw"},{"数字编委名单", "szbw"}};
    }

    @Override
    public Colour getTitleColour() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getData(Map<String, Object> param) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        List<Map<String, Object>> resultList = new ArrayList<>();
         String materialCreateDate = dataAuditDao.findMaterialCreateDate(param);
        Date date1 = DateUtil.fomatDate(materialCreateDate);
        Date date = DateUtil.fomatDate("2019-04-12 12:00");
        /*Date date = DateUtil.fomatDate("2019-4-19 17:00");*/
        if(date1.getTime()>date.getTime()) {
            resultList=declareCountDao.selectResults2(param);
        }else{
            resultList=declareCountDao.selectResults(param);

        }
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("textbook_name", resultList.get(i).get("textbook_name"));
            map.put("zb", resultList.get(i).get("zb"));
            map.put("fb", resultList.get(i).get("fb"));
            map.put("bw", resultList.get(i).get("bw"));
            map.put("szbw", resultList.get(i).get("szbw"));
            list.add(map);
        }

        return list;
    }
}
