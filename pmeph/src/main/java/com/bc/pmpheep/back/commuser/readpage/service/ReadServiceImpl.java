package com.bc.pmpheep.back.commuser.readpage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.readpage.dao.ReadDao;

@Service("com.bc.pmpheep.back.commuser.readpage.service.ReadServiceImpl")
public class ReadServiceImpl implements ReadService {

	@Autowired
	private ReadDao readDao;
	
	@Override
	public List<Map<String, Object>> queryZdtjReadList(Map<String, Object> map) {
		return this.readDao.queryZdtjReadList(map);
	}

	@Override
	public List<Map<String, Object>> queryMaterialType(String firstTypeId) {
		return readDao.queryMaterialType(firstTypeId);
	}

	@Override
	public List<Map<String, Object>> queryXstjReadList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.readDao.queryXstjReadList(map);
	}

	@Override
	public List<Map<String, Object>> queryRmspReadList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.readDao.queryRmspReadList(map);
	}

	@Override
	public List<Map<String, Object>> queryTscxReadList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.readDao.queryTscxReadList(map);
	}

}
