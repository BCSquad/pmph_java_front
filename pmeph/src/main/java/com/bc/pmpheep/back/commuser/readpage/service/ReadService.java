package com.bc.pmpheep.back.commuser.readpage.service;

import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.commuser.readpage.bean.MaterialType;

public interface ReadService {
	
	//重点推荐查询
	public List<Map<String,Object>> queryZdtjReadList(Map<String,Object> map);

	//获取图书分类信息
	public List<MaterialType> queryMaterialType(String firstTypeIds);

	//新书推荐查询
	public List<Map<String,Object>> queryXstjReadList(Map<String,Object> map);
	//热评查询
	public List<Map<String,Object>> queryRmspReadList(Map<String,Object> map);
	//热评查询
	public List<Map<String,Object>> queryTscxReadList(Map<String,Object> map);
}
