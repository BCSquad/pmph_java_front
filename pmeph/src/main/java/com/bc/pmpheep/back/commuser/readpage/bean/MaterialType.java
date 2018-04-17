package com.bc.pmpheep.back.commuser.readpage.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 读书首页 图书分类三级目录用 实体类
 */
public class MaterialType {
	
	private String id = "";
	private String path = "";
	private String type_name = "";
	private List<MaterialType> dataList = new ArrayList<MaterialType>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public List<MaterialType> getDataList() {
		return dataList;
	}
	public void setDataList(List<MaterialType> dataList) {
		this.dataList = dataList;
	}
	
	
	

}
