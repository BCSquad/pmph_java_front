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
	public List<Map<String,Object>> queryStu(Map<String, Object> map);
	public int insertStu(Map<String, Object> map);
	/**
	 * 查询工作经历
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryWork(Map<String, Object> map);
	public int insertWork(Map<String, Object> map);
	/**
	 * 作家学术
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryZjxs(Map<String, Object> map);
	public int insertZjxs(Map<String, Object> map);
	/**
	 * 查询教学经历
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryStea(Map<String, Object> map);
	public int insertStea(Map<String, Object> map);
	/**
	 * 查询上版教材编辑
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryJcbj(Map<String, Object> map);
	public int insertJcbj(Map<String, Object> map);
	/**
	 * 查询国家精品课程建设
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryGjkcjs(Map<String, Object> map);
	public int insertGjkcjs(Map<String, Object> map);
	/**
	 * 作家主编国家级规划教材情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryGjghjc(Map<String, Object> map);
	public int insertGjghjc(Map<String, Object> map);
	/**
	 * 查询教材编写情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> rwsjcList(Map<String, Object> map);
	public List<Map<String,Object>> queryqtJcbx(Map<String, Object> map);
	public int insertJcbx(Map<String, Object> map);
	/**
	 * 作家科研情况表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryZjkyqk(Map<String, Object> map);
	public int insertZjkyqk(Map<String, Object> map);
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
	public List<Map<String,Object>> queryMonograph(Map<String, Object> map);
	public int insertMonograph(Map<String, Object> map);
	/**
	 * 出版行业获奖情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPublish(Map<String, Object> map);
	public int insertPublish(Map<String, Object> map);
	/**
	 * SCI论文投稿及影响因子
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> querySci(Map<String, Object> map);
	public int insertSci(Map<String, Object> map);
	/**
	 * 临床医学获奖情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryClinicalreward(Map<String, Object> map);
	public int insertClinicalreward(Map<String, Object> map);
	/**
	 * 学术荣誉授予情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryAcadereward(Map<String, Object> map);
	public int insertAcadereward(Map<String, Object> map);

	/**
	 * 查询参加人卫慕课、数字教材编写情况
	 */
	public Map<String,Object> queryMoocdigital(Map<String, Object> map);
	/**
	 * 编写内容意向表
	 */
	public Map<String,Object> queryIntention(Map<String, Object> map);
}
