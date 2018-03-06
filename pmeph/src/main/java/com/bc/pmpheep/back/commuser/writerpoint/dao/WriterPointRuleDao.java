package com.bc.pmpheep.back.commuser.writerpoint.dao;

import com.bc.pmpheep.back.commuser.writerpoint.bean.WriterPointRule;

/**
 * WriterPointRule实体类的数据访问层接口
 * 
 * @author tyc
 * 
 */
public interface WriterPointRuleDao {
	
	
	/**
	 * 通过名称查询 积分规则
	 * @param ruleName
	 * @return
	 */
	WriterPointRule getWriterPointRuleByRuleName(String ruleName);
}
