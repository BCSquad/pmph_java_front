package com.bc.pmpheep.general.service;

import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.general.dao.DataDictionaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataDictionaryService {

    @Autowired
    DataDictionaryDao dataDictionaryDao;

    /**
     * 获取某分类的数据字典项
     * @param type_code
     * @return
     */
    public List<Map<String,Object>> getDataDictionaryListByType(String type_code){

        List<Map<String,Object>> list = new ArrayList<>();
        list = dataDictionaryDao.getDataDictionaryListByType(type_code);

        return list ;
    }

    public String getDataDictionaryItemNameByCode(String writerUserTitle, String title) {
        String result = "";
        result = dataDictionaryDao.getDataDictionaryItemNameByCode(writerUserTitle,title);
        if(ObjectUtil.isNull(result)){
            result = "";
        }
        return result;
    }
}
