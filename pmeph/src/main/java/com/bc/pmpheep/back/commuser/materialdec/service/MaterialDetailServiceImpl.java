package com.bc.pmpheep.back.commuser.materialdec.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.materialdec.dao.MaterialDetailDao;
@Service("com.bc.pmpheep.back.commuser.materialdec.service.MaterialDetailServiceImpl")
public class MaterialDetailServiceImpl implements MaterialDetailService {
	
	@Autowired 
	private MaterialDetailDao madd;
	
	//通过教材ID查出教材
	@Override
	public Map<String, Object> queryMaterialbyId(String material_id) {
		return this.madd.queryMaterialbyId(material_id);
	}
	//通过教材ID查出所有教材下的书籍
	@Override
	public List<Map<String, Object>> queryBookById(String material_id) {
		return this.madd.queryBookById(material_id);
	}
	@Override
	public List<Map<String, Object>> queryZjkzxxById(String material_id) {
		return this.madd.queryZjkzxxById(material_id);
	}
	
	/**
	 * 图书申报职位暂存
	 */
	@Override
	public List<Map<String, Object>> queryTssbZc(Map<String, Object> map) {
		return this.madd.queryTssbZc(map);
	}
	@Override
	public int insertTssbZc(Map<String, Object> map) {
		return this.madd.insertTssbZc(map);
	}
	
	@Override
	public List<Map<String, Object>> queryPerson(Map<String, Object> map) {
		return this.madd.queryPerson(map);
	}
	
	@Override
	public int insertPerson(Map<String, Object> map) {
		return this.madd.insertPerson(map);
	}
	
	@Override
	public List<Map<String, Object>> queryStu(Map<String, Object> map) {
		return this.madd.queryStu(map);
	}

	@Override
	public List<Map<String, Object>> queryWork(Map<String, Object> map) {
		return this.madd.queryWork(map);
	}

	@Override
	public List<Map<String, Object>> queryStea(Map<String, Object> map) {
		return this.madd.queryStea(map);
	}

	@Override
	public List<Map<String, Object>> queryJcbj(Map<String, Object> map) {
		return this.madd.queryJcbj(map);
	}

	@Override
	public List<Map<String, Object>> queryGjkcjs(Map<String, Object> map) {
		return this.madd.queryGjkcjs(map);
	}

	@Override
	public List<Map<String, Object>> queryJcbx(Map<String, Object> map) {
		return this.madd.queryJcbx(map);
	}

	@Override
	public List<Map<String, Object>> queryTsxz(Map<String, Object> map) {
		return this.madd.queryTsxz(map);
	}
	@Override
	public int insertTsxz(Map<String, Object> map) {
		return this.madd.insertTsxz(map);
	}
	@Override
	public int insertStu(Map<String, Object> map) {
		return this.madd.insertStu(map);
	}
	@Override
	public int insertWork(Map<String, Object> map) {
		return this.madd.insertWork(map);
	}
	@Override
	public List<Map<String, Object>> queryZjxs(Map<String, Object> map) {
		return this.madd.queryZjxs(map);
	}
	@Override
	public int insertZjxs(Map<String, Object> map) {
		return this.madd.insertZjxs(map);
	}
	@Override
	public int insertStea(Map<String, Object> map) {
		return this.madd.insertStea(map);
	}
	@Override
	public int insertJcbj(Map<String, Object> map) {
		return this.madd.insertJcbj(map);
	}
	@Override
	public int insertGjkcjs(Map<String, Object> map) {
		return this.madd.insertGjkcjs(map);
	}
	@Override
	public int insertJcbx(Map<String, Object> map) {
		return this.madd.insertJcbx(map);
	}
	@Override
	public List<Map<String, Object>> queryZjkyqk(Map<String, Object> map) {
		return this.madd.queryZjkyqk(map);
	}
	@Override
	public int insertZjkyqk(Map<String, Object> map) {
		return this.madd.insertZjkyqk(map);
	}
	@Override
	public List<Map<String, Object>> queryGjghjc(Map<String, Object> map) {
		return this.madd.queryGjghjc(map);
	}
	@Override
	public int insertGjghjc(Map<String, Object> map) {
		return this.madd.insertGjghjc(map);
	}
	@Override
	public List<Map<String, Object>> queryOrgById(String material_id) {
		return this.madd.queryOrgById(material_id);
	}
	@Override
	public List<Map<String, Object>> queryZjkzbb(Map<String, Object> map) {
		return this.madd.queryZjkzbb(map);
	}
	@Override
	public int insertZjZjkzbb(Map<String, Object> map) {
		return this.madd.insertZjkzbb(map);
	}
	@Override
	public int delGlxx(Map<String, Object> map) {
		
		this.madd.DelGjghjc(map);
		this.madd.DelGjkcjs(map);
		this.madd.DelJcbj(map);
		this.madd.DelJcbx(map);
		this.madd.DelTssbZc(map);
		this.madd.DelStea(map);
		this.madd.DelZjkyqk(map);
		this.madd.DelZjxs(map);
		this.madd.DelStu(map);
		this.madd.DelWork(map);
		return 1;
	}
	@Override
	public int updateDeclaration(Map<String, Object> map) {
		return this.madd.updateDeclaration(map);
	}
	@Override
	public int updatePerson(Map<String, Object> map) {
		return this.madd.updatePerson(map);
	}
	
}
