package com.bc.pmpheep.back.authadmin.applydocaudit.service;


import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * @author (作者) sunzhuoqun
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期)
 * @modify (最后修改时间)
 * @修改人 ：
 * @审核人 ：
 *
 */
public interface ExpertationListAuditService {

    /**
     *
     * @param pageParameter
     * @return
     */
    PageResult<Map<String, Object>> getOrg(PageParameter<Map<String, Object>> pageParameter) throws CheckedServiceException;

    /**
     * 根据id查询产品名称
     * @param pageParameter
     * @return
     */
    List<Map<String, Object>> productIdList();

}
