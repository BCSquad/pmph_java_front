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
        return new String[][]{{"姓名", "realname"}, {"职务", "position"}, {"职称", "title"}, {"所选书籍与职务", "textbook_name"}, {"学校审核", "online_progress"}, {"出版社审核", "offline_progress"}, {"遴选结果", "chosen_position"}};
    }

    @Override
    public Colour getTitleColour() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getData(Map<String, Object> param) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        List<Map<String, Object>> resultList = dataAuditDao
				.findDataAudit(param);
        
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("realname", resultList.get(i).get("realname"));
            map.put("position", resultList.get(i).get("position"));
            map.put("title", resultList.get(i).get("title"));
            if( null !=resultList.get(i).get("preset_position")&&"0".equals(resultList.get(i).get("preset_position").toString())){
            	String aString=resultList.get(i).get("textbook_name").toString();
            	String textbook_name=aString+"-未选择";
            	map.put("textbook_name", textbook_name);
            }else if(null !=resultList.get(i).get("preset_position")&& "1".equals(resultList.get(i).get("preset_position").toString())){
            	String aString=resultList.get(i).get("textbook_name").toString();
            	String textbook_name=aString+"-编委";
            	map.put("textbook_name", textbook_name);
            }else if(null !=resultList.get(i).get("preset_position")&& "2".equals(resultList.get(i).get("preset_position").toString())){
            	String aString=resultList.get(i).get("textbook_name").toString();
            	String textbook_name=aString+"-主编";
            	map.put("textbook_name", textbook_name);
            }else if(null !=resultList.get(i).get("preset_position")&& "3".equals(resultList.get(i).get("preset_position").toString())){
            	String aString=resultList.get(i).get("textbook_name").toString();
            	String textbook_name=aString+"-副主编";
            	map.put("textbook_name", textbook_name);
            }else {
				map.put("textbook_name", "");
			}
            
            if (null !=resultList.get(i).get("online_progress")&&"0".equals(resultList.get(i).get("online_progress").toString())) {
            	map.put("online_progress", "未提交");
			}else if (null !=resultList.get(i).get("online_progress")&&"1".equals(resultList.get(i).get("online_progress").toString())) {
            	map.put("online_progress", "已提交");
			}else if (null !=resultList.get(i).get("online_progress")&&"2".equals(resultList.get(i).get("online_progress").toString())) {
            	map.put("online_progress", "被退回");
			}else if (null !=resultList.get(i).get("online_progress")&&"3".equals(resultList.get(i).get("online_progress").toString())) {
            	map.put("online_progress", "通过");
			}else {
				map.put("online_progress", "");
			}
            
            if (null !=resultList.get(i).get("offline_progress")&&"0".equals(resultList.get(i).get("offline_progress").toString())) {
            	map.put("offline_progress", "未收到");
			}else if (null !=resultList.get(i).get("offline_progress")&&"1".equals(resultList.get(i).get("offline_progress").toString())) {
            	map.put("offline_progress", "被退回");
			}else if (null !=resultList.get(i).get("offline_progress")&&"2".equals(resultList.get(i).get("offline_progress").toString())) {
            	map.put("offline_progress", "已收到");
			}else  {
            	map.put("offline_progress", "");
			}
            
            if (null !=resultList.get(i).get("chosen_position")&&"1".equals(resultList.get(i).get("chosen_position").toString())) {
            	map.put("chosen_position", "主编");
			}else if (null !=resultList.get(i).get("chosen_position")&&"2".equals(resultList.get(i).get("chosen_position").toString())) {
            	map.put("chosen_position", "副主编");
			}else if (null !=resultList.get(i).get("chosen_position")&&"3".equals(resultList.get(i).get("chosen_position").toString())) {
            	map.put("chosen_position", "编委");
			}else  {
            	map.put("chosen_position", "无");
			}
            list.add(map);
        }

        return list;
    }
}
