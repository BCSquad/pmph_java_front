package com.bc.pmpheep.back.commuser.materialdec.service;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

import java.util.List;
import java.util.Map;

public interface PersonInfoService {

	/**
	 * 查询学习经历
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerStu(Map<String, Object> map);
	public int insertPerStu(Map<String, Object> map);
	/**
	 * 查询工作经历
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerWork(Map<String, Object> map);
	public int insertPerWork(Map<String, Object> map);
	/**
	 * 作家学术
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerZjxs(Map<String, Object> map);
	public int insertPerZjxs(Map<String, Object> map);
	/**
	 * 查询教学经历
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerStea(Map<String, Object> map);
	public int insertPerStea(Map<String, Object> map);
	/**
	 * 查询上版教材编辑
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerJcbj(Map<String, Object> map);
	public int insertPerJcbj(Map<String, Object> map);
	/**
	 * 查询国家精品课程建设
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerGjkcjs(Map<String, Object> map);
	public int insertPerGjkcjs(Map<String, Object> map);
	/**
	 * 作家主编国家级规划教材情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerGjghjc(Map<String, Object> map);
	public int insertPerGjghjc(Map<String, Object> map);
	/**
	 * 查询教材编写情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> rwsjcPerList(Map<String, Object> map);
	public List<Map<String,Object>> queryqtPerJcbx(Map<String, Object> map);
	public int insertPerJcbx(Map<String, Object> map);
	/**
	 * 作家科研情况表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerZjkyqk(Map<String, Object> map);
	public int insertPerZjkyqk(Map<String, Object> map);
	/**
	 * 个人成就
	 * @param map
	 * @return
	 */
	public Map<String,Object> queryAchievement(Map<String, Object> map);
	public int insertAchievement(Map<String, Object> map);
	public int updateAchievement(Map<String, Object> map);
	/**
	 * 主编学术专著情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerMonograph(Map<String, Object> map);
	public int insertPerMonograph(Map<String, Object> map);
	/**
	 * 出版行业获奖情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerPublish(Map<String, Object> map);
	public int insertPerPublish(Map<String, Object> map);
	/**
	 * SCI论文投稿及影响因子
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerSci(Map<String, Object> map);
	public int insertPerSci(Map<String, Object> map);
	/**
	 * 临床医学获奖情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerClinicalreward(Map<String, Object> map);
	public int insertPerClinicalreward(Map<String, Object> map);
	/**
	 * 学术荣誉授予情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPerAcadereward(Map<String, Object> map);
	public int insertPerAcadereward(Map<String, Object> map);

	/**
	 * 查询参加人卫慕课、数字教材编写情况
	 */
	public Map<String,Object> queryPerMoocdigital(Map<String, Object> map);
	/**
	 * 编写内容意向表
	 */
	public Map<String,Object> queryPerIntention(Map<String, Object> map);
}
