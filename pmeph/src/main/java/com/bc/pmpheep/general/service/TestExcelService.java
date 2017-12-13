package com.bc.pmpheep.general.service;

import jxl.format.Colour;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuan on 2017/12/13.
 */
@Service("testExcel")
public class TestExcelService implements ExcelDownloadService {
    @Override
    public String getTitle() {
        return "测试的";
    }

    @Override
    public String[][] getColTitle() {
        return new String[][]{{"数据1", "data1"}, {"数据2", "data2"}};
    }

    @Override
    public Colour getTitleColour() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getData(Map<String, Object> param) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data1", "ewqewqewq" + i);
            map.put("data2", "大撒旦撒旦撒法" + i);
            list.add(map);
        }

        return list;
    }
}
