package com.bc.pmpheep.back.authadmin.chooseeditor.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jxl.format.Colour;

import com.bc.pmpheep.back.authadmin.chooseeditor.dao.ChooseEditorDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.general.service.ExcelDownloadService;

/**
 * 选编委界面 导出excel 接口实现类
 * @author liudi
 *
 */
@Service("ChooseEditorExcelServiceImpl")
public class ChooseEditorExcelServiceImpl implements ExcelDownloadService {

	@Autowired
	ChooseEditorDao chooseEditorDao;
	
	@Override
	public String getTitle(Map<String, Object> param) {
		String textBookName ="";
		try {
			textBookName = java.net.URLDecoder.decode(param.get("textBookName").toString(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		return "《"+textBookName+"》-编委遴选名单";
	}

	@Override
	public String[][] getColTitle() {
		
		return new String[][]{{"姓名", "realname"},{"性别", "sex"},{"工作单位", "work_org_name"},{"申报单位", "dec_org_name"}, {"职位", "position"}, {"职称", "title"}, {"选为编委", "choosenEditor"}, {"选为数字编委", "choosenNumEditor"}};
	}

	@Override
	public Colour getTitleColour() {
		return null;
	}

	@Override
	public List<Map<String, Object>> getData(Map<String, Object> param)
			throws Exception {
		 
		 PageParameter<Map<String, Object>> pageParameter = new PageParameter<>(-1, 0,param);
		 pageParameter.setStart(-1);
		 List<Map<String,Object>> resultList = chooseEditorDao.queryEditorToBeList(pageParameter);
		 for (Map<String, Object> rm : resultList) {
			 int cp = Integer.parseInt(rm.get("chosen_position").toString());
			 int sex = Integer.parseInt(rm.get("sex").toString());
			 if (cp==2||cp==1) { //被选为编委
				rm.put("choosenEditor", "√");
			 }else {
				rm.put("choosenEditor", "");
			 }
			 if (cp==3||cp==1) { //被选为数字编委
				rm.put("choosenNumEditor", "√");
			 }else {
				rm.put("choosenNumEditor", "");
			 }
			 if (sex==1) {
				 rm.put("sex", "男");
			 }else if(sex==2){
				 rm.put("sex", "女");
			 }else{
				 rm.put("sex", "保密");
			 }
				 
		}
		 
		return resultList;
	}

}
