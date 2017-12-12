package com.bc.pmpheep.back.commuser.userinfo.service;

import java.util.Map;

public interface UserInfoService {

	/**
	 * 根据ID查询作家表
	 * @param id write_user表的主键
	 * @return Map
	 */
	Map<String, Object> queryWriter(String id);
	/**
	 * 根据ID修改数据
	 * @param map
	 * @return int
	 */
	Map<String, Object> update(Map<String, Object> map);
	/**
	 * 根据ID修改普通用户头像
	 * @param avatar 上传图片ID
	 * @param id 主键
	 * @return
	 */
	Map<String, Object> updateavatar(String avatar,String id);
}
