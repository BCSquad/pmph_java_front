package com.bc.pmpheep.back.commuser.mygroup.service;

import java.util.List;
import com.bc.pmpheep.back.commuser.mygroup.bean.PmphGroupMemberVO;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * Created by cyx  on 2017/11/21
 */
public interface MyGroupService {


	/**
	 * 
	 * 
	 * 功能描述：根据小组id加载小组用户
	 *
	 * @param groupId
	 *            小组id
	 * @return 小组成员
	 *
	 */
	List<PmphGroupMemberVO> listPmphGroupMember(Long groupId, Long memberId) throws CheckedServiceException;
	
	/**
	 * 
	 * Description:进行各种操作之前判断是否为创建者或管理者
	 * 
	 * @author:lyc
	 * @date:2017年10月12日上午11:18:08
	 * @param:
	 * @return:Boolean
	 */
	Boolean isFounderOrisAdmin(Long groupId, Long memberId) throws CheckedServiceException;
	
	/**
	 * 
	 * Description:进行各种操作之前判断是否为创建者
	 * 
	 * @author:Administrator
	 * @date:2017年10月12日上午11:18:34
	 * @param
	 * @return Boolean
	 */
	Boolean isFounder(Long groupId,  Long memberId) throws CheckedServiceException;
	
	
}
