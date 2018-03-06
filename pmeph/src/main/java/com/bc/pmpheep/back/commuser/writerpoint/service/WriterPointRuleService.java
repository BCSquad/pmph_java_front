package com.bc.pmpheep.back.commuser.writerpoint.service;

import com.bc.pmpheep.back.commuser.writerpoint.bean.WriterPointRule;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 积分规则 业务层
 * @author mr
 *
 */
public interface WriterPointRuleService {

    /**
     * 通过积分名称查询积分规则
     * @param ruleName
     * @return
     */
	WriterPointRule getWriterPointRuleByName(String ruleName)throws CheckedServiceException;
}
