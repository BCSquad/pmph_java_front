package com.bc.pmpheep.back.authadmin.chooseeditor.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.controller.bean.ResponseBean;

/**
 * 
 * @author liudi
 * 第一主编选择编委 服务层接口
 */

public interface ChooseEditorService {

	/**
	 * 查询列表
	 * @param pageParameter
	 * @return
	 */
	List<Map<String, Object>> queryEditorToBeList(PageParameter<Map<String, Object>> pageParameter);
	
	/**
	 * 查询不分页的总列表 条件同分页 只查询职位申报主键和职位申报暂存遴选职位 用于复选框回填 同时list长度做总数
	 * @param pageParameter
	 * @return
	 */
	List<Map<String,Object>> queryEditorToBeCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 通过id查询单条书籍
	 * @param textBookId 书籍id
	 * @return 书籍表同名字段键值对Map
	 */
	Map<String, Object> queryTextBookById(String textBookId);

	/**
	 * 获取机构列表 
	 * @param material_id 
	 * @return
	 */
	List<Map<String, Object>> getOrgList(String material_id);

	/**
	 * 暂存 两步：1.删除本人本书籍的所有职位申报暂存 2.将现选择的职位暂存id对应数据复制入暂存表
	 * @param paraMap 如此型：{selectedIds=1,2,3, logUserId=24966, textBookId=21524, is_background=0,selectedNumIds=2,3,4}
	 * @return
	 */
	Map<String, Object> tempSave(Map<String, Object> paraMap);

	
	/**
	 * 
	 * @param paraMap
	 * @return
	 */
	Map<String, Object> editorSelectRubmit(Map<String, Object> paraMap);

	//查询所有数字编委
	List<Map<String, Object>> queryNumEditorToBeCount(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 是否第一主编登录
	 * @param logUserId
	 * @param textBookId 
	 * @return
	 */
	Boolean isFirstEditorLogIn(BigInteger logUserId, String textBookId);

	

	
	
}
