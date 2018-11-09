package com.bc.pmpheep.back.commuser.research.service;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

import java.util.List;
import java.util.Map;

public interface ReSearchService {
    /**
     *
     * 查询调研表基本信息
     * @return
     */
    PageResult<Map<String, Object>> querySearchList(PageParameter<Map<String, Object>> pageParameter);

    /**
     * 根据调研表主键查询问题
     * @param id
     * @return
     */
    //List<Map<String,Object>> queryQuestionBySearchId(String id);
    /**
     * 通过教材ID查询调研表
     * @param material_id
     * @return
     */
    List<Map<String,Object>> querySearchByMaterialId(String material_id,String user_id);
    /**
     * 查询与教材下图书有关的调研表
     * @param textbook_id
     * @return
     */
    List<Map<String,Object>> querySearchByTextbookId(String textbook_id);
}
