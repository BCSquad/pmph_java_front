package com.bc.pmpheep.back.uncertainfieldcom.dao;

/**
 * 注：当分类id固定而分类名称不确定时，便不再使用此类
 * 各种分类表分类名称和id对应关系不确定性使开发难以适应
 * 现将表实例给出，当id不确定时id的get方法以中文分类名查询数据库返回id，当id确定名字不确定时直接常数写定id。
 * @author Liudi
 *
 */
public interface TypeConfigDao {
	/**
	 * 通过CmsCategory分类名称返回现数据库中该分类的主键id
	 * @param cmsCategoryName
	 * @return
	 */
	public String queryCmsCategoryIdByCmsCategoryName(String cmsCategoryName);
}
