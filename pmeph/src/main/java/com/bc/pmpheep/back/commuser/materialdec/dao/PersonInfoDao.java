package com.bc.pmpheep.back.commuser.materialdec.dao;

import com.bc.pmpheep.back.plugin.PageParameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PersonInfoDao {

		//查询学习经历
		public List<Map<String,Object>> queryPerStu(Map<String, Object> map);
		public int insertPerStu(Map<String, Object> map);
		public int DelPerStu(Map<String, Object> map);
		//查询工作经历
		public List<Map<String,Object>> queryPerWork(Map<String, Object> map);
		public int insertPerWork(Map<String, Object> map);
		public int DelPerWork(Map<String, Object> map);
		//查询教学经历
		public List<Map<String,Object>> queryPerStea(Map<String, Object> map);
		public int insertPerStea(Map<String, Object> map);
		public int DelPerStea(Map<String, Object> map);
		//作家学术
		public List<Map<String,Object>> queryPerZjxs(Map<String, Object> map);
		public int insertPerZjxs(Map<String, Object> map);
		public int DelPerZjxs(Map<String, Object> map);
		//查询上版教材编辑
		public List<Map<String,Object>> queryPerJcbj(Map<String, Object> map);
		public int insertPerJcbj(Map<String, Object> map);
		public int DelPerJcbj(Map<String, Object> map);
		//查询精品课程建设
		public List<Map<String,Object>> queryPerGjkcjs(Map<String, Object> map);
		public int insertPerGjkcjs(Map<String, Object> map);
		public int DelPerGjkcjs(Map<String, Object> map);
		//作家主编国家级规划教材情况
		public List<Map<String,Object>> queryPerGjghjc(Map<String, Object> map);
		public int insertPerGjghjc(Map<String, Object> map);
		public int DelPerGjghjc(Map<String, Object> map);
		//查询其他社教材编写情况
		public List<Map<String,Object>> queryPerJcbx(Map<String, Object> map);
		public int insertPerJcbx(Map<String, Object> map);
		public int DelPerJcbx(Map<String, Object> map);
		//查询人卫社教材编写情况
		public List<Map<String,Object>> queryPerRwsjc(Map<String, Object> map);
		public int insertPerRwsjc(Map<String, Object> map);
		public int DelPerRwsjc(Map<String, Object> map);
		//作家科研情况表
		public List<Map<String,Object>> queryPerZjkyqk(Map<String, Object> map);
		public int insertPerZjkyqk(Map<String, Object> map);
		public int DelPerZjkyqk(Map<String, Object> map);

		//个人成就
		public Map<String,Object> queryPerAchievement(Map<String, Object> map);
		public int insertPerAchievement(Map<String, Object> map);
		public int updatePerAchievement(Map<String, Object> map);
		public int DelPerAchievement(Map<String, Object> map);
		//主编学术专著情况
		public List<Map<String,Object>> queryPerMonograph(Map<String, Object> map);
		public int insertPerMonograph(Map<String, Object> map);
		public int DelPerMonograph(Map<String, Object> map);
		//出版行业获奖情况
		public List<Map<String,Object>> queryPerPublish(Map<String, Object> map);
		public int insertPerPublish(Map<String, Object> map);
		public int DelPerPublish(Map<String, Object> map);
		//SCI论文投稿及影响因子
		public List<Map<String,Object>> queryPerSci(Map<String, Object> map);
		public int insertPerSci(Map<String, Object> map);
		public int DelPerSci(Map<String, Object> map);
		//临床医学获奖情况
		public List<Map<String,Object>> queryPerClinicalreward(Map<String, Object> map);
		public int insertPerClinicalreward(Map<String, Object> map);
		public int DelPerClinicalreward(Map<String, Object> map);
		//学术荣誉授予情况
		public List<Map<String,Object>> queryPerAcadereward(Map<String, Object> map);
		public int insertPerAcadereward(Map<String, Object> map);
		public int DelPerAcadereward(Map<String, Object> map);

}
