package com.bc.pmpheep.back.commuser.research.dao;

import com.bc.pmpheep.back.plugin.PageParameter;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

public interface ReSearchDao {

    /**
     *
     * 查询调研表基本信息
     * @return
     */
    List<Map<String,Object>> querySearchList(PageParameter<Map<String, Object>> pageParameter);

    /**
     * 查询与教材无关调研表总数
     * @return
     */
    int queryCount();

    /**
     * 根据调研表主键查询问题
     * @param id
     * @return
     */
    List<Map<String,Object>> queryQuestionBySearchId(@Param("id") String id);

    /**
     * 通过教材ID查询调研表
     * @param material_id
     * @return
     */
    List<Map<String,Object>> querySearchByMaterialId(@Param("material_id") String material_id);
}