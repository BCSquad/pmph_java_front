package com.bc.pmpheep.back.authadmin.applydocaudit.dao;

import com.bc.pmpheep.back.plugin.PageParameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * @author (作者)
 *
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期)
 * @modify (最后修改时间)
 * @修改人 ：
 * @审核人 ：
 *
 */
public interface ExpertationListAuditDao {

    /**
     *
     * @param pageParameter
     * @return
     */
    Integer getOrgTotal(Map<String, Object> pageParameter);
    /**
     * 分页查询
     * @param pageParameter
     * @return
     */
    List<Map<String, Object>> getOrg(Map<String, Object> pageParameter);

    /**
     * 根据id查询产品名称
     * @param pageParameter
     * @return
     */
    List<Map<String, Object>> productIdList();
}
