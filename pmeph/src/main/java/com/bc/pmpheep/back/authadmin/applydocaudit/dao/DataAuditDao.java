package com.bc.pmpheep.back.authadmin.applydocaudit.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.back.plugin.PageParameter;

public interface DataAuditDao {

	/**
	 * 
	 * @Title: findDataAudit
	 * @Description: 查询列表
	 * @param @param paraMap
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> findDataAudit(PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 
	 * @Title: findDataAuditCount
	 * @Description: 查询总数
	 * @param @param paraMap
	 * @param @return
	 * @return Integer 返回类型
	 * @throws
	 */
	Integer findDataAuditCount(PageParameter<Map<String, Object>> pageParameter);


	/**
	 * 
	 * @Title: findTitleName
	 * @Description: 查询列表
	 * @param @param paraMap
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	String findTitleName(Map<String, Object> paraMap);

	String findMaterialCreateDate(Map<String, Object> paraMap);

	String findDeclarationCreateDate(Map<String, Object> paraMap);
	/**
	 * 导出excle表
	 * @Title: findDataAuditExcel
	 * @Description: TODO
	 * @param @param paraMap
	 * @param @return 设定文件
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> findDataAuditExcel(Map<String, Object> paraMap);
	
	//申报审核通过
	public int updateDeclarationPass(Map<String,Object> map);
	//申报审核退回
	public int updateDeclaration(Map<String,Object> map);
	
	//更新Declaration时间
	void updateDeclarationUpdateTime(Map<String, Object> queryMap);
	
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
			
			//图书申报职位暂存
			public List<Map<String,Object>> queryTssbZc(Map<String, Object> map);
			
			//查询专家信息
			public List<Map<String,Object>> queryPerson(Map<String, Object> map);
			
			//查询学习经历
			public List<Map<String,Object>> queryStu(Map<String, Object> map);
			//查询工作经历
			public List<Map<String,Object>> queryWork(Map<String, Object> map);
			//查询教学经历
			public List<Map<String,Object>> queryStea(Map<String, Object> map);
			//作家学术
			public List<Map<String,Object>> queryZjxs(Map<String, Object> map);
			//查询上版教材编辑
			public List<Map<String,Object>> queryJcbj(Map<String, Object> map);
			//查询精品课程建设
			public List<Map<String,Object>> queryGjkcjs(Map<String, Object> map);
			//作家主编国家级规划教材情况
			public List<Map<String,Object>> queryGjghjc(Map<String, Object> map);
			//查询其他社教材编写情况
			public List<Map<String,Object>> queryJcbx(Map<String, Object> map);
			//查询人卫社教材编写情况
			public List<Map<String,Object>> queryRwsjc(Map<String, Object> map);
			//作家科研情况表
			public List<Map<String,Object>> queryZjkyqk(Map<String, Object> map);
			//作家扩展项填报表
			public List<Map<String,Object>> queryZjkzbb(Map<String, Object> map);
			
			//个人成就
			public Map<String,Object> queryAchievement(Map<String, Object> map);
			//主编学术专著情况
			public List<Map<String,Object>> queryMonograph(Map<String, Object> map);
			//出版行业获奖情况
			public List<Map<String,Object>> queryPublish(Map<String, Object> map);
			//SCI论文投稿及影响因子
			public List<Map<String,Object>> querySci(Map<String, Object> map);
			//临床医学获奖情况
			public List<Map<String,Object>> queryClinicalreward(Map<String, Object> map);
			//学术荣誉授予情况
			public List<Map<String,Object>> queryAcadereward(Map<String, Object> map);
			//人卫慕课、数字教材编写情况
			public Map<String,Object> queryMoocdigital(Map<String, Object> map);
			//内容意向表情况
			public Map<String,Object> queryIntention(Map<String, Object> map);

			//打印状态改变
			void updPrintStatus(String id);

			/**
			 * 在dec_position和dec_position_published表中查询choosen_position大于0的项 即被遴选中的
			 * @param declaration_id
			 * @return
			 */
			List<Map<String, Object>> queryChoosenListByDeclarationId(@Param(value = "declaration_id")String declaration_id);

}
