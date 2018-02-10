package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import jxl.format.Colour;
import org.apache.commons.collections.map.HashedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.applydocaudit.dao.DataAuditDao;
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
@Service("dataAuditExcel")
public class DataAuditExcelService implements ExcelDownloadService {
	
	@Autowired
	DataAuditDao dataAuditDao;
	
	
    @Override
    public String getTitle() {
        return "资料审核表格";
    }

    @Override
    public String[][] getColTitle() {
        return new String[][]{{"姓名", "drealname"}, {"职务", "dposition"}, {"职称", "dtitle"}, {"所选书籍与职务", "bpp"}, {"学校审核", "onlineprogress"}, {"出版社审核", "offlineprogress"}, {"遴选结果", "cp"}};
    }

    @Override
    public Colour getTitleColour() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getData(Map<String, Object> param) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultList = dataAuditDao .findDataAuditExcel(param);
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("drealname", resultList.get(i).get("drealname"));
            map.put("dposition", resultList.get(i).get("dposition"));
            map.put("dtitle", resultList.get(i).get("dtitle"));
            map.put("bpp", resultList.get(i).get("bpp"));
            map.put("onlineprogress", resultList.get(i).get("onlineprogress"));
            map.put("offlineprogress", resultList.get(i).get("offlineprogress"));
            map.put("cp", resultList.get(i).get("cp"));
            list.add(map);
        }

        return list;
    }
}
