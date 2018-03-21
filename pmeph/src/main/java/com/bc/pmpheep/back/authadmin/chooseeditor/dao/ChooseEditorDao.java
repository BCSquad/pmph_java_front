package com.bc.pmpheep.back.authadmin.chooseeditor.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.plugin.PageParameter;
/**
 * 第一主编选择编委dao接口
 * @author Administrator
 *
 */

public interface ChooseEditorDao {
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
	List<Map<String, Object>> queryEditorToBeCount(PageParameter<Map<String, Object>> pageParameter);

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
	List<Map<String, Object>> getOrgList(@Param("material_id")String material_id);

	/**
	 * 通过记录人和书籍id删除暂存
	 * @param paraMap
	 * @return
	 */
	Integer deleteTempByAuthorIdAndTextbookId(Map<String, Object> paraMap);

	/**
	 * 将现选择的职位暂存id对应数据复制入暂存表
	 * @param paraMap
	 * @return
	 */
	Integer copyTempBySelectedIds(Map<String, Object> paraMap);

	/**
	 * 将作家申报职位表中被选定的设定为编委
	 * @param paraMap
	 * @return
	 */
	Integer updateDecPositionBySelectIds(Map<String, Object> paraMap);

	/**
	 * 书籍表 is_list_selected 主编是否选定编委 1为提交状态0为暂存状态
	 * @param paraMap
	 * @return
	 */
	Integer updateTextBookListSelected(Map<String, Object> paraMap);
	
	//查询所有数字编委
	List<Map<String, Object>> queryNumEditorToBeCount(
			PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 查询这样的第一主编公布id
	 * @param logUserId
	 * @param textBookId
	 * @return
	 */
	Integer isFirstEditorLogIn(@Param("logUserId")BigInteger logUserId,@Param("textBookId") String textBookId);

	
	
	//暂存数字编委
	/*void updateTempBySelectedNumIds(Map<String, Object> paraMap);*/
	
	//保存数字编委
	/*void updateDecPositionBySelectNumIds(Map<String, Object> paraMap);*/

	
}
