package com.bc.pmpheep.back.commuser.materialdec.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.materialdec.dao.MaterialDetailDao;
import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
@Service("com.bc.pmpheep.back.commuser.materialdec.service.MaterialDetailServiceImpl")
public class MaterialDetailServiceImpl implements MaterialDetailService {
	
	@Autowired 
	private MaterialDetailDao madd;
	
	@Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
    private PersonalService personalService;
	
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
		int result = this.madd.insertTsxz(map);
		personalService.saveUserTrendst("jcsb", map.get("table_trendst_id").toString(), 0, map.get("author_id").toString());
		return result;
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
		
		this.madd.DelGjghjc(map); //作家主编国家级规划教材情况
		this.madd.DelGjkcjs(map); //精品课程建设
		this.madd.DelJcbj(map);  //上版教材编辑
		this.madd.DelJcbx(map);  //教材编写情况
		this.madd.DelTssbZc(map); //图书申报职位暂存
		this.madd.DelStea(map);   //教学经历
		this.madd.DelZjkyqk(map); //作家科研情况表
		this.madd.DelZjxs(map);   //作家学术
		this.madd.DelStu(map);    //学习经历
		this.madd.DelWork(map);   //工作经历
		this.madd.DelAcadereward(map); //学术荣誉授予情况
		this.madd.DelMonograph(map); ////主编学术专著情况
		this.madd.DelClinicalreward(map); //临床医学获奖情况
		this.madd.DelPublish(map);  //出版行业获奖情况
		this.madd.DelSci(map);   //SCI论文投稿及影响因子
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
	@Override
	public PageResult<Map<String, Object>> selectOrgList(
			PageParameter<Map<String, Object>> pageParameter) {
		PageResult<Map<String, Object>> pageResult= new PageResult<Map<String,Object>>();
		pageResult.setPageNumber(pageParameter.getPageNumber());
		pageResult.setPageSize(pageParameter.getPageSize());
		
		
		int count = madd.queryOrgCount(pageParameter);
		List<Map<String, Object>> list = null ;
		if(0 == count){
			list = new java.util.ArrayList<Map<String, Object>>(1);
			Map<String, Object> map = new java.util.HashMap<String, Object>();
			map.put("org_id", 0);
			map.put("id", 0);
			map.put("parent_id", 0);
			map.put("material_id", pageParameter.getParameter().get("material_id"));
			map.put("org_name", "人民卫生出版社");
			list.add(map);
		}else{
			list = madd.queryOrgList(pageParameter);
			
		}
		pageResult.setRows(list);
		pageResult.setTotal(count);
		return pageResult;
	}
	@Override
	public Map<String,Object> queryAchievement(Map<String, Object> map) {
		return this.madd.queryAchievement(map);
	}
	@Override
	public int insertAchievement(Map<String, Object> map) {
		return this.madd.insertAchievement(map);
	}
	@Override
	public int updateAchievement(Map<String, Object> map) {
		return this.madd.updateAchievement(map);
	}
	@Override
	public List<Map<String, Object>> queryMonograph(Map<String, Object> map) {
		return this.madd.queryMonograph(map);
	}
	@Override
	public int insertMonograph(Map<String, Object> map) {
		return this.madd.insertMonograph(map);
	}
	@Override
	public List<Map<String, Object>> queryPublish(Map<String, Object> map) {
		return this.madd.queryPublish(map);
	}
	@Override
	public int insertPublish(Map<String, Object> map) {
		return this.madd.insertPublish(map);
	}
	@Override
	public List<Map<String, Object>> querySci(Map<String, Object> map) {
		return this.madd.querySci(map);
	}
	@Override
	public int insertSci(Map<String, Object> map) {
		return this.madd.insertSci(map);
	}
	@Override
	public List<Map<String, Object>> queryClinicalreward(Map<String, Object> map) {
		return this.madd.queryClinicalreward(map);
	}
	@Override
	public int insertClinicalreward(Map<String, Object> map) {
		return this.madd.insertClinicalreward(map);
	}
	@Override
	public List<Map<String, Object>> queryAcadereward(Map<String, Object> map) {
		return this.madd.queryAcadereward(map);
	}
	@Override
	public int insertAcadereward(Map<String, Object> map) {
		return this.madd.insertAcadereward(map);
	}
	
}
