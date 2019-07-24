package com.bc.pmpheep.general.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 */
public interface DataDictionaryDao {

    /**
     * 获取某分类的数据字典项列表
     * @param type_code
     * @return
     */
    List<Map<String,Object>> getDataDictionaryListByType(String type_code);

    /**
     * 获取某分类某项的数据字典项名称
     * @param type_code
     * @param code
     * @return
     */
    String getDataDictionaryItemNameByCode(@Param("type_code") String type_code,@Param("code") String code);

    String getDataDictionaryItemNameByCode2(@Param("type_code") String type_code,@Param("code") String code);

}
