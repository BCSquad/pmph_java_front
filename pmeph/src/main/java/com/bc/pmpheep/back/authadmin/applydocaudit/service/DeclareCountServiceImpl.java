package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.applydocaudit.dao.DeclareCountDao;


@Service("com.bc.pmpheep.back.authadmin.applydocaudit.service.DeclareCountServiceImpl")
public class DeclareCountServiceImpl implements DeclareCountService {
    @Autowired
    DeclareCountDao       declareCountDao;
   
    
    //
  	@Override
  	public List<Map<String,Object>> findDeclareCount() {
  		List<Map<String,Object>> list = declareCountDao.findDeclareCount();
  		return list;
  	}
  	//最终结果名单列表 
  	@Override
  	public List<Map<String,Object>> findNameList() {
  		List<Map<String,Object>> list = declareCountDao.findNameList();
  		return list;
  	}
	@Override
	public List<Map<String, Object>> selectNoticeMessage(
			Map<String, Object> paraMap) {
		List<Map<String, Object>> list= declareCountDao.selectNoticeMessage(paraMap);
  		return list;
	}
  	
  	
  	

}
