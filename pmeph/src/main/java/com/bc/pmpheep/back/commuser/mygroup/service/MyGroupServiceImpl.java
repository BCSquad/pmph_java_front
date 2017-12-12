package com.bc.pmpheep.back.commuser.mygroup.service;


import com.bc.pmpheep.back.commuser.mygroup.bean.PmphGroupMemberVO;
import com.bc.pmpheep.back.commuser.mygroup.dao.MyGroupDao;
import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.service.exception.CheckedServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Created by cyx  on 2017/11/21
 */
@Service("com.bc.pmpheep.back.commuser.mygroup.service.MyGroupServiceImpl")
public class MyGroupServiceImpl implements MyGroupService {

    @Autowired
    private MyGroupDao myGroupDao;
    
    
    
    @Override
	public List<PmphGroupMemberVO> listPmphGroupMember(Long groupId, Long memberId)
			throws CheckedServiceException {
    	List<PmphGroupMemberVO> list = myGroupDao.listPmphGroupMember(groupId);
		for (PmphGroupMemberVO pmphGroupMemberVO : list) {
			if (pmphGroupMemberVO.getIsWriter()) {
				//用户头像
				//pmphGroupMemberVO.setAvatar(RouteUtil.userAvatar(writerUserService.get(pmphGroupMemberVO.getUserId()).getAvatar()));
				pmphGroupMemberVO.setUserType(Const.SENDER_TYPE_2);
			} else {
				//用户头像
				//pmphGroupMemberVO.setAvatar(RouteUtil.userAvatar(pmphUserService.get(pmphGroupMemberVO.getUserId()).getAvatar()));
				pmphGroupMemberVO.setUserType(Const.SENDER_TYPE_1);
			}
		}
		return list;
	}
    
    @Override
	public Boolean isFounderOrisAdmin(Long groupId,  Long memberId) throws CheckedServiceException {
		boolean flag = false;
		PmphGroupMemberVO currentUser = myGroupDao.getPmphGroupMemberByMemberId(groupId, memberId, false);
		if(null == currentUser){
			return flag;
		}
		if (currentUser.getIsFounder() || currentUser.getIsAdmin()) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Boolean isFounder(Long groupId, Long memberId ) throws CheckedServiceException {
		boolean flag = false;
		PmphGroupMemberVO currentUser = myGroupDao.getPmphGroupMemberByMemberId(groupId, memberId, false);
		
		if(null == currentUser){
			return flag;
		}
		if (currentUser.getIsFounder()) {
			flag = true;
		}
		return flag;
	}
	 
    
}
