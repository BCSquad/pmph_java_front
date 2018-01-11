package com.bc.pmpheep.back.uncertainfieldcom.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bc.pmpheep.back.uncertainfieldcom.dao.TypeConfigDao;

/**
 * 注：当分类id固定而分类名称不确定时，进入此类修改代码
 * 各种分类表分类名称和id对应关系不确定性使开发难以适应
 * 现将表实例给出，当id不确定时id的get方法以中文分类名查询数据库返回id，当id确定名字不确定时直接常数写定id，在此类外就可以统一认为id确定。
 * @author Liudi
 *
 */
@Repository
public class CmsCategoryConfig {
	private String  id;
	
	@Autowired
	TypeConfigDao typeDao;

	/**
	 * 通过CmsCategory分类名称返回现数据库中该分类的主键id
	 * @param CmsCategoryName
	 * @return
	 */
	public String getId(String CmsCategoryName) {
		
		String id = typeDao.queryCmsCategoryIdByCmsCategoryName(CmsCategoryName);
		
		//id确定时解开，即使名称变动，后台java代码依然有一一对应关系，只用修改此一处的id
		/*
		switch (CmsCategoryName) {
		case "医学随笔":
			id="165";
			break;
		case "快报管理":
			id="164";
			break;
		case "公告管理":
			id="163";
			break;
		default:
			break;
		}*/
		return id;
	}

	
	
}
