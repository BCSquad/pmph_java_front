package com.bc.pmpheep.back.commuser.materialdec.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.plugin.PageParameter;

public interface MaterialDetailDao {
		
		//通过教材ID查出教材
		public Map<String,Object> queryMaterialbyId(String material_id);
		//通过教材ID查出所有教材下的书籍
		public List<Map<String,Object>> queryBookById(String material_id);
		//通过教材ID查出相关的机构信息
		public List<Map<String,Object>> queryOrgById(String material_id);
		//通过教材ID查出扩展信息
		public List<Map<String,Object>> queryZjkzxxById(String material_id);
		
		//图书申报职位
		public List<Map<String,Object>> queryTsxz(Map<String, Object> map);
		public int insertTsxz(Map<String, Object> map);
		public int DelTsxz(Map<String, Object> map);
		
		//图书申报职位暂存
		public List<Map<String,Object>> queryTssbZc(Map<String, Object> map);
		public int insertTssbZc(Map<String, Object> map);
		public int DelTssbZc(Map<String, Object> map);
		
		//查询专家信息
		public List<Map<String,Object>> queryPerson(Map<String, Object> map);
		public int insertPerson(Map<String, Object> map);
		public int DelPerson(Map<String, Object> map);
		public int updatePerson(Map<String, Object> map);
		
		//查询学习经历
		public List<Map<String,Object>> queryStu(Map<String, Object> map);
		public int insertStu(Map<String, Object> map);
		public int DelStu(Map<String, Object> map);
		//查询工作经历
		public List<Map<String,Object>> queryWork(Map<String, Object> map);
		public int insertWork(Map<String, Object> map);
		public int DelWork(Map<String, Object> map);
		//查询教学经历
		public List<Map<String,Object>> queryStea(Map<String, Object> map);
		public int insertStea(Map<String, Object> map);
		public int DelStea(Map<String, Object> map);
		//作家学术
		public List<Map<String,Object>> queryZjxs(Map<String, Object> map);
		public int insertZjxs(Map<String, Object> map);
		public int DelZjxs(Map<String, Object> map);
		//查询上版教材编辑
		public List<Map<String,Object>> queryJcbj(Map<String, Object> map);
		public int insertJcbj(Map<String, Object> map);
		public int DelJcbj(Map<String, Object> map);
		//查询精品课程建设
		public List<Map<String,Object>> queryGjkcjs(Map<String, Object> map);
		public int insertGjkcjs(Map<String, Object> map);
		public int DelGjkcjs(Map<String, Object> map);
		//作家主编国家级规划教材情况
		public List<Map<String,Object>> queryGjghjc(Map<String, Object> map);
		public int insertGjghjc(Map<String, Object> map);
		public int DelGjghjc(Map<String, Object> map);
		//查询其他社教材编写情况
		public List<Map<String,Object>> queryJcbx(Map<String, Object> map);
		public int insertJcbx(Map<String, Object> map);
		public int DelJcbx(Map<String, Object> map);
		//查询人卫社教材编写情况
		public List<Map<String,Object>> queryRwsjc(Map<String, Object> map);
		public int insertRwsjc(Map<String, Object> map);
		public int DelRwsjc(Map<String, Object> map);
		//作家科研情况表
		public List<Map<String,Object>> queryZjkyqk(Map<String, Object> map);
		public int insertZjkyqk(Map<String, Object> map);
		public int DelZjkyqk(Map<String, Object> map);
		//作家扩展项填报表
		public List<Map<String,Object>> queryZjkzbb(Map<String, Object> map);
		public int insertZjkzbb(Map<String, Object> map);
		public int delZjkzbb(Map<String, Object> map);
		
		//个人成就
		public Map<String,Object> queryAchievement(Map<String, Object> map);
		public int insertAchievement(Map<String, Object> map);
		public int updateAchievement(Map<String, Object> map);
		public int DelAchievement(Map<String, Object> map);
		//主编学术专著情况
		public List<Map<String,Object>> queryMonograph(Map<String, Object> map);
		public int insertMonograph(Map<String, Object> map);
		public int DelMonograph(Map<String, Object> map);
		//出版行业获奖情况
		public List<Map<String,Object>> queryPublish(Map<String, Object> map);
		public int insertPublish(Map<String, Object> map);
		public int DelPublish(Map<String, Object> map);
		//SCI论文投稿及影响因子
		public List<Map<String,Object>> querySci(Map<String, Object> map);
		public int insertSci(Map<String, Object> map);
		public int DelSci(Map<String, Object> map);
		//临床医学获奖情况
		public List<Map<String,Object>> queryClinicalreward(Map<String, Object> map);
		public int insertClinicalreward(Map<String, Object> map);
		public int DelClinicalreward(Map<String, Object> map);
		//学术荣誉授予情况
		public List<Map<String,Object>> queryAcadereward(Map<String, Object> map);
		public int insertAcadereward(Map<String, Object> map);
		public int DelAcadereward(Map<String, Object> map);
		//人卫慕课、数字教材编写情况
		public Map<String,Object> queryMoocdigital(Map<String, Object> map);
		public int insertMoocdigital(Map<String, Object> map);
		public int updateMoocdigital(Map<String, Object> map);
		//内容意向表情况
		public Map<String,Object> queryIntention(Map<String, Object> map);
		public int insertIntention(Map<String, Object> map);
		public int updateIntention(Map<String, Object> map);
		//申报审核
		public int updateDeclaration(Map<String, Object> map);
		
		//查出机构列表
		public List<Map<String,Object>> queryOrgList(PageParameter<Map<String, Object>> pageParameter);
		public int queryOrgCount(PageParameter<Map<String, Object>> pageParameter);
		
		/**
		 * 更新个人资料
		 */
		public int updateWriter(Map<String, Object> map);
		
		/**
		 * 通过以下参数查找已存在的申报
		 * @param user_id
		 * @param material_id
		 * @param declaration_id
		 * @return key：dec_editable 申报可编辑，notEnd 教材报名未结束
		 */
		public Map<String, Object> queryDeclarationByUserIdAndMaterialIdOrDeclarationId(
				@Param("user_id")String user_id, @Param("material_id")String material_id, @Param("declaration_id")String declaration_id);

		/**
		 * 查看用户信息
		 */
		public Map<String, Object> queryUserInfo(String user_id);

		Integer queryDeclarationCountByUserId(Long user_id);

	    Map<String, Object> queryIdcar(Long paramLong);
}
