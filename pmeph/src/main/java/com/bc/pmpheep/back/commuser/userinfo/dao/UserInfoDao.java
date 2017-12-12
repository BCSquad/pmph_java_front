package com.bc.pmpheep.back.commuser.userinfo.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserInfoDao {

	/**
	 * 根据ID查询作家表
	 * @param id write_user表的主键
	 * @return Map
	 */
	Map<String, Object> queryWriter(@Param("id") String id);
	/**
	 * 根据ID修改数据
	 * @param map
	 * @return int
	 */
	int update(Map<String, Object> map);
	/**
	 * 根据ID修改头像
	 * @param avatar 上传图片ID
	 * @param id 主键
	 * @return
	 */
	int updateavatar(@Param("avatar") String avatar,@Param("id") String id);
}
