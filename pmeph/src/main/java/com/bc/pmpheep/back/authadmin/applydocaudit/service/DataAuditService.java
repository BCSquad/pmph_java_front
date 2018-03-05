package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import java.util.List;
import java.util.Map;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 
 * @ClassName: DataAuditService
 * @Description: 申报资料审核（机构用户）
 * @author SunZhuoQun
 * @date 2017-12-5 上午9:28:01
 * 
 */
public interface DataAuditService {

	/**
	 * 
	 * @Title: findDataAudit
	 * @Description: 查询列表
	 * @param @param pageParameter
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	List<Map<String, Object>> findDataAudit(
			PageParameter<Map<String, Object>> pageParameter);

	/**
	 * 
	 * @Title: findDataAuditCount
	 * @Description: 查询总数
	 * @param @param pageParameter
	 * @param @return
	 * @return int 返回类型
	 * @throws
	 */
	int findDataAuditCount(PageParameter<Map<String, Object>> pageParameter);


	/**
	 * 
	 * @Title: findTitleName
	 * @Description: 查询列表
	 * @param @param pageParameter
	 * @param @return
	 * @return List<Map<String,Object>> 返回类型
	 * @throws
	 */
	String findTitleName(Map<String, Object> map);
	
	/**
	 * 通过教材ID查出教材
	 * @param material_id
	 * @return
	 */
	public Map<String,Object> queryMaterialbyId(String material_id);
	/**
	 * 通过教材ID查出所有教材下的书籍
	 * @param material_id
	 * @return
	 */
	public List<Map<String,Object>> queryBookById(String material_id);
	/**
	 * 通过教材ID查出相关的机构信息
	 * @param material_id
	 * @return
	 */
	public List<Map<String,Object>> queryOrgById(String material_id);
	/**
	 * 通过教材ID查出扩展信息
	 * @param material_id
	 * @return
	 */
	public List<Map<String,Object>> queryZjkzxxById(String material_id);
	/**
	 * 查询专家信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerson(Map<String,Object> map);
	
	/**
	 * 作家申报职位-图书选择信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryTsxz(Map<String,Object> map);
	
	/**
	 * 图书申报职位暂存
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryTssbZc(Map<String,Object> map);
	
	
	/**
	 * 查询学习经历
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryStu(Map<String,Object> map);
	
	/**
	 * 查询工作经历
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryWork(Map<String,Object> map);
	
	/**
	 * 作家学术
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryZjxs(Map<String,Object> map);
	
	/**
	 * 查询教学经历
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryStea(Map<String,Object> map);
	
	/**
	 * 查询上版教材编辑
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryJcbj(Map<String,Object> map);
	
	/**
	 * 查询国家精品课程建设
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryGjkcjs(Map<String,Object> map);
	
	/**
	 * 作家主编国家级规划教材情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryGjghjc(Map<String,Object> map);
	
	/**
	 * 查询教材编写情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryJcbx(Map<String,Object> map);
	public List<Map<String,Object>> queryqtJcbx(Map<String,Object> map);
	
	/**
	 * 作家科研情况表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryZjkyqk(Map<String,Object> map);
	
	/**
	 * 作家扩展项填报表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryZjkzbb(Map<String,Object> map);
	
	/**
	 * 个人成就
	 * @param map
	 * @return
	 */
	public Map<String,Object> queryAchievement(Map<String,Object> map);
	
	/**
	 * 主编学术专著情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryMonograph(Map<String,Object> map);
	/**
	 * 出版行业获奖情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPublish(Map<String,Object> map);
	/**
	 * SCI论文投稿及影响因子
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> querySci(Map<String,Object> map);
	/**
	 * 临床医学获奖情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryClinicalreward(Map<String,Object> map);
	/**
	 * 学术荣誉授予情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryAcadereward(Map<String,Object> map);
	
	
	
	
	/**
	 * 申报审核通过
	 */
	public int updateDeclarationPass(Map<String,Object> map);
	
	/**
	 * 通过后发送消息给申报人员
	 */
	void senNewMsgPass(Long material_id,Long thisId,Short thisType,Long frendId,Short friendIdType,String title,String content);
	
	/**
	 * 申报审核退回
	 */
	public int updateDeclaration(Map<String,Object> map);
	/**
	 * 退回后发送消息给申报人员
	 */
	void senNewMsgBack(Long material_id,Long thisId,Short thisType,Long frendId,Short friendIdType,String title,String content);
	
	
	
	//更新Declaration修改时间
	void updateDeclarationUpdateTime(Map<String, Object> queryMap);
	
}
