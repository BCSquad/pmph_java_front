package com.bc.pmpheep.back.commuser.writerpoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.writerpoint.bean.WriterPointRule;
import com.bc.pmpheep.back.commuser.writerpoint.dao.WriterPointRuleDao;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 积分规则 业务层
 * @author mr
 *
 */
@Service("com.bc.pmpheep.back.commuser.writerpoint.service.WriterPointRuleServiceImpl")
public class WriterPointRuleServiceImpl implements WriterPointRuleService{
	
	@Autowired
	WriterPointRuleDao writerPointRuleDao;
	

	@Override
	public WriterPointRule getWriterPointRuleByName(String ruleName) throws CheckedServiceException {
		return writerPointRuleDao.getWriterPointRuleByRuleName(ruleName);
	}

}
