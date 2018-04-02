package com.bc.pmpheep.back.commuser.readpage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * 注释:ReadDao类
 * @Author:黄琼飞
 * @date 2017-11-22下午3:32:35
 */
public interface ReadDao {
	
	//重点推荐
	List<Map<String,Object>> queryZdtjReadList(Map<String,Object> map);

	//获取图书分类信息
	public List<Map<String,Object>> queryMaterialType(@Param("firstTypeIds")String firstTypeIds);

	//新书推荐
	List<Map<String,Object>> queryXstjReadList(Map<String,Object> map); 
	//热门书评
	List<Map<String,Object>> queryRmspReadList(Map<String,Object> map); 
	//图书畅销
	List<Map<String,Object>> queryTscxReadList(Map<String,Object> map); 
}
