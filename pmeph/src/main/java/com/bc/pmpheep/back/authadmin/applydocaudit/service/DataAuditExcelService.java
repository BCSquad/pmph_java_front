package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.back.util.DateUtil;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.general.dao.DataDictionaryDao;
import jxl.format.Colour;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.applydocaudit.dao.DataAuditDao;
import com.bc.pmpheep.general.service.ExcelDownloadService;

import java.util.*;

/**
 * Created by sunzhuoqun on 2017/12/13.
 */
@Service("dataAuditExcel")
public class DataAuditExcelService implements ExcelDownloadService {

    @Autowired
    DataAuditDao dataAuditDao;
    @Autowired
    DataDictionaryDao dataDictionaryDao;


    @Override
    public String getTitle(Map<String, Object> param) {
        Map<String, Object> material = dataAuditDao.queryMaterialbyId(MapUtils.getString(param, "material_id"));
        String material_name = MapUtils.getString(material, "material_name");
        return material_name + "教材资料申报表";
    }

    @Override
    public String[][] getColTitle(Map<String, Object> param) {
        return new String[][]{{"姓名", "drealname"}, {"职务", "dposition"}, {"职称", "dtitle"}, {"所选书籍与职务", "bpp"}, {"学校审核", "onlineprogress"}, {"出版社审核", "offlineprogress"}, {"遴选结果", "cp"}};
    }

    @Override
    public Colour getTitleColour() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getData(Map<String, Object> param) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultList = dataAuditDao.findDataAuditExcel(param);


        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("drealname", resultList.get(i).get("drealname"));
            map.put("dposition", resultList.get(i).get("dposition"));
            map.put("dtitle", resultList.get(i).get("dtitle"));
            Map<String, Object> params = new HashMap<>();
            params.put("declarationId",resultList.get(i).get("did"));
            String materialCreateDate = dataAuditDao.findDeclarationCreateDate(params);
            Date date1 = DateUtil.fomatDate(materialCreateDate);
            Date date = DateUtil.fomatDate("2019-04-12 12:00");
            /*Date date = DateUtil.fomatDate("2019-04-20 15:30");*/

            if(date1.getTime()>date.getTime()) {
                String post =resultList.get(i).get("preset_position").toString();

                if(post!=null){
                    if(ObjectUtil.isNumber(post)){
                        post = dataDictionaryDao.getDataDictionaryItemNameByCode(Const.PMPH_POSITION,post);
                    }
                }
                map.put("bpp",resultList.get(i).get("textbook_name")+"-"+post);

            }else{
                map.put("bpp", resultList.get(i).get("bpp"));
            }

            String tit = resultList.get(i).get("dtitle").toString();
            if (tit != null) {
                if (ObjectUtil.isNumber(tit)) {
                    tit = dataDictionaryDao.getDataDictionaryItemNameByCode(Const.WRITER_USER_TITLE, tit);
                }

            }
            map.put("dtitle",tit);

            map.put("onlineprogress", resultList.get(i).get("onlineprogress"));
            map.put("offlineprogress", resultList.get(i).get("offlineprogress"));
            map.put("cp", resultList.get(i).get("cp"));
            list.add(map);
        }

        return list;
    }
}
