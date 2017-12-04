package com.bc.pmpheep.back.commuser.materialdec.service;

import java.util.List;
import java.util.Map;

public interface MaterialDetailService {
	
	//通过教材ID查出教材
	public Map<String,Object> queryMaterialbyId(String material_id);
	//通过教材ID查出所有教材下的书籍
	public List<Map<String,Object>> queryBookById(String material_id);
	
	//查询专家信息
	public List<Map<String,Object>> queryPerson(Map<String,Object> map);
	public int insertPerson(Map<String,Object> map);
	//图书选择信息
	public List<Map<String,Object>> queryTsxz(Map<String,Object> map);
	public int insertTsxz(Map<String,Object> map);
	
	//查询学习经历
	public List<Map<String,Object>> queryStu(Map<String,Object> map);
	
	//查询工作经历
	public List<Map<String,Object>> queryWork(Map<String,Object> map);
	
	//查询教学经历
	public List<Map<String,Object>> queryStea(Map<String,Object> map);
	
	//查询上版教材编辑
	public List<Map<String,Object>> queryJcbj(Map<String,Object> map);
	
	//查询国家精品课程建设
	public List<Map<String,Object>> queryGjkcjs(Map<String,Object> map);
	
	//查询教材编写情况
	public List<Map<String,Object>> queryJcbx(Map<String,Object> map);
	
}
