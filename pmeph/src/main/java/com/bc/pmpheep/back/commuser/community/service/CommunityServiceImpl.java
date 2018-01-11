package com.bc.pmpheep.back.commuser.community.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.community.dao.CommunityDao;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 教材服务接口实现类
 * @param 
 * @return 
 * @throws
 */

@Service("com.bc.pmpheep.back.commuser.community.service.CommunityServiceImpl")
public class CommunityServiceImpl implements CommunityService {
	@Autowired
	private CommunityDao communityDao;
	/* 
	 * 查询公告列表
	 */
	@Override
	public List<Map<String, Object>> queryNoticeList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return communityDao.queryNoticeList(param);
	}
	/* 
	 * id查询公告
	 */
	@Override
	public Map<String, Object> queryNoticeById(Long id) {
		// TODO Auto-generated method stub
		return communityDao.queryNoticeById(id);
	}
	/* 
	 * 根据教材id查询社区快报列表
	 */
	@Override
	public List<Map<String, Object>> queryMaterialNoticeList(Long id) {
		// TODO Auto-generated method stub
		return communityDao.queryMaterialNoticeList(id);
	}
	/* 
	 * 根据教材id查询本套教材的图书 
	 */
	@Override
	public List<Map<String, Object>> queryTextBookList(Long id) {
		// TODO Auto-generated method stub
		return communityDao.queryTextBookList(id);
	}
	@Override
	public List<Map<String, Object>> querySomeComment(Long id) {
		// TODO Auto-generated method stub
		return communityDao.querySomeComment(id);
	}

}
