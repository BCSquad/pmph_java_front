package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.applydocaudit.dao.DataAuditDao;
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

/**
 * 
 * @ClassName: DataAuditServiceImpl
 * @Description: TODO
 * @author SunZhuoQun
 * @date 2017-12-5 上午9:29:34
 * 
 */
@Service("com.bc.pmpheep.back.authadmin.applydocaudit.service.DataAuditServiceImpl")
public class DataAuditServiceImpl implements DataAuditService {
	@Autowired
	DataAuditDao dataAuditDao;

	/**
	 * 查询列表
	 */
	@Override
	public List<Map<String, Object>> findDataAudit(
			PageParameter<Map<String, Object>> pageParameter) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("startNum", pageParameter.getStart());
		paraMap.put("pageSize", pageParameter.getPageSize());
		paraMap.put("material_id",
				pageParameter.getParameter().get("material_id"));
		paraMap.put("queryName", pageParameter.getParameter().get("queryName"));
		List<Map<String, Object>> resultList = dataAuditDao
				.findDataAudit(paraMap);
		return resultList;
	}

	/**
	 * 查询条数
	 */
	@Override
	public int findDataAuditCount(
			PageParameter<Map<String, Object>> pageParameter) {
		Map<String, Object> paraMap = pageParameter.getParameter();
		paraMap.put("queryName", pageParameter.getParameter().get("queryName"));
		paraMap.put("material_id",
				pageParameter.getParameter().get("material_id"));
		Integer count = dataAuditDao.findDataAuditCount(paraMap);
		Integer maxPageNum = (int) Math.ceil(1.0 * count
				/ pageParameter.getPageSize());
		return maxPageNum;
	}
	
	/**
	 * 标题
	 */
	@Override
	public String findTitleName(Map<String, Object> map) {
		String resultList = dataAuditDao.findTitleName(map);
		return resultList;
	}
	
	@Override
	public List<Map<String, Object>> queryPerson(Map<String, Object> map) {
		return this.dataAuditDao.queryPerson(map);
	}
	
	@Override
	public List<Map<String, Object>> queryTsxz(Map<String, Object> map) {
		return this.dataAuditDao.queryTsxz(map);
	}
	
	@Override
	public List<Map<String, Object>> queryStu(Map<String, Object> map) {
		return this.dataAuditDao.queryStu(map);
	}
	
	@Override
	public List<Map<String, Object>> queryWork(Map<String, Object> map) {
		return this.dataAuditDao.queryWork(map);
	}
	
	@Override
	public List<Map<String, Object>> queryStea(Map<String, Object> map) {
		return this.dataAuditDao.queryStea(map);
	}
	@Override
	public List<Map<String, Object>> queryZjxs(Map<String, Object> map) {
		return this.dataAuditDao.queryZjxs(map);
	}
	@Override
	public List<Map<String, Object>> queryJcbj(Map<String, Object> map) {
		return this.dataAuditDao.queryJcbj(map);
	}
	@Override
	public List<Map<String, Object>> queryGjkcjs(Map<String, Object> map) {
		return this.dataAuditDao.queryGjkcjs(map);
	}
	@Override
	public List<Map<String, Object>> queryGjghjc(Map<String, Object> map) {
		return this.dataAuditDao.queryGjghjc(map);
	}
	@Override
	public List<Map<String, Object>> queryJcbx(Map<String, Object> map) {
		return this.dataAuditDao.queryJcbx(map);
	}
	@Override
	public List<Map<String, Object>> queryZjkyqk(Map<String, Object> map) {
		return this.dataAuditDao.queryZjkyqk(map);
	}
	@Override
	public List<Map<String, Object>> queryZjkzbb(Map<String, Object> map) {
		return this.dataAuditDao.queryZjkzbb(map);
	}
	
	@Override
	public int updateDeclaration(Map<String, Object> map) {
		return this.dataAuditDao.updateDeclaration(map);
	}
	//通过教材ID查出教材
	@Override
	public Map<String, Object> queryMaterialbyId(String material_id) {
		return this.dataAuditDao.queryMaterialbyId(material_id);
	}

}
