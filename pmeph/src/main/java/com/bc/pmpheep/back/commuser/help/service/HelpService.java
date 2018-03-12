package com.bc.pmpheep.back.commuser.help.service;

import java.util.List;
import java.util.Map;

public interface HelpService {

	/**查询帮助列表
	 * @param
	 * @return
	 */
	 List<Map<String,Object>> queryHelpList(Map<String,Object> param);

	/**查询帮助手册列表
	 * @param
	 * @return
	 */
	List<Map<String,Object>> handbookList(Map<String,Object> param);

	/**查询详情
	 * @param
	 * @return
	 */
	Map<String,Object> queryDetail(Map<String,Object> param);

}
	
