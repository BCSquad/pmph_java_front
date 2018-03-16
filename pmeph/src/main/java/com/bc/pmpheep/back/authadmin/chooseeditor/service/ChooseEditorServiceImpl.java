package com.bc.pmpheep.back.authadmin.chooseeditor.service;

import java.math.BigInteger;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bc.pmpheep.back.authadmin.chooseeditor.dao.ChooseEditorDao;
import com.bc.pmpheep.back.plugin.PageParameter;
/**
 * 第一主编选择编委服务层实现类
 * @author liudi
 *
 */
@Service("com.bc.pmpheep.back.authadmin.chooseeditor.service.ChooseEditorServiceImpl")
public class ChooseEditorServiceImpl implements ChooseEditorService {

	@Autowired
	ChooseEditorDao chooseEditorDao;
	
	@Override
	public List<Map<String, Object>> queryEditorToBeList(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String,Object>> resultList = chooseEditorDao.queryEditorToBeList(pageParameter);
		return resultList;
	}

	@Override
	public List<Map<String,Object>> queryEditorToBeCount(PageParameter<Map<String, Object>> pageParameter) {
		
		List<Map<String,Object>> resultList =chooseEditorDao.queryEditorToBeCount(pageParameter);
		
		return resultList;
	}
	//查询数字编委id集合
	@Override
	public List<Map<String,Object>> queryNumEditorToBeCount(PageParameter<Map<String, Object>> pageParameter) {
		
		List<Map<String,Object>> resultList =chooseEditorDao.queryNumEditorToBeCount(pageParameter);
		
		return resultList;
	}

	@Override
	public Map<String, Object> queryTextBookById(String textBookId) {
		Map<String, Object> resultMap = chooseEditorDao.queryTextBookById(textBookId);
		return resultMap;
	}

	@Override
	public List<Map<String, Object>> getOrgList(String material_id) {
		List<Map<String, Object>> orgList = chooseEditorDao.getOrgList(material_id);
		return orgList;
	}

	@Override
	public Map<String, Object> tempSave(Map<String, Object> paraMap) {
		Map<String,Object> resultMap= new HashMap<String,Object>();
		String selectedIds= (String) paraMap.get("selectedIds");
		String selectedNumIds= (String) paraMap.get("selectedNumIds");
		if (selectedIds==null || selectedIds.length()==0) {
			selectedIds = "-100"; //sql中此数组不可为空，当为空时，给予不可能出现的id（从1开始自增，无负数）
			paraMap.put("selectedIds", selectedIds);
		}
		if (selectedNumIds==null || selectedNumIds.length()==0) {
			selectedNumIds = "-100"; //sql中此数组不可为空，当为空时，给予不可能出现的id（从1开始自增，无负数）
			paraMap.put("selectedNumIds", selectedNumIds);
		}
		
		Integer del_count = chooseEditorDao.deleteTempByAuthorIdAndTextbookId(paraMap);
		Integer copy_count = chooseEditorDao.copyTempBySelectedIds(paraMap);
		paraMap.put("is_list_selected", 0);
		Integer b_count=chooseEditorDao.updateTextBookListSelected(paraMap);
		//暂存数字编委
		//chooseEditorDao.updateTempBySelectedNumIds(paraMap);
		if (del_count>=0 && copy_count >=0 && b_count>0) {
			resultMap = paraMap;
			resultMap.put("msg", "已暂存");
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> editorSelectRubmit(Map<String, Object> paraMap) {
		Map<String,Object> resultMap= new HashMap<String,Object>();
		resultMap = paraMap;
		String selectedIds= (String) paraMap.get("selectedIds");
		String selectedNumIds= (String) paraMap.get("selectedNumIds");
		if (selectedIds==null || selectedIds.length()==0) {
			selectedIds = "-100"; //sql中此数组不可为空，当为空时，给予不可能出现的id（从1开始自增，无负数）
			paraMap.put("selectedIds", selectedIds);
		}
		if (selectedNumIds==null || selectedNumIds.length()==0) {
			selectedNumIds = "-100"; //sql中此数组不可为空，当为空时，给予不可能出现的id（从1开始自增，无负数）
			paraMap.put("selectedNumIds", selectedNumIds);
		}
		
		Integer del_count = chooseEditorDao.deleteTempByAuthorIdAndTextbookId(paraMap);
		Integer copy_count = chooseEditorDao.copyTempBySelectedIds(paraMap);
		//保存编委和数字编委
		Integer u_count = chooseEditorDao.updateDecPositionBySelectIds(paraMap);
		//保存数字编委
		//chooseEditorDao.updateDecPositionBySelectNumIds(paraMap);
		if (u_count>0) {
			//Integer del_count = chooseEditorDao.deleteTempByAuthorIdAndTextbookId(paraMap);
			paraMap.put("is_list_selected", 1);
			Integer b_count=chooseEditorDao.updateTextBookListSelected(paraMap);
			
			if (u_count >=0 && b_count>0) {
				resultMap.put("msg", "已提交,主编已选定编委");
			}
		}else{
			resultMap.put("msg", "保存失败！");
		}
		return resultMap;
	}

	@Override
	public Boolean isFirstEditorLogIn(BigInteger logUserId, String textBookId) {
		Integer id = chooseEditorDao.isFirstEditorLogIn(logUserId,textBookId);
		Boolean result = id!=null;
		return result;
	}

	
	
}
