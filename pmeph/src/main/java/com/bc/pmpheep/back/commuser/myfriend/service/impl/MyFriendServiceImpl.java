package com.bc.pmpheep.back.commuser.myfriend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.myfriend.bean.WriterFriendVO;
import com.bc.pmpheep.back.commuser.myfriend.dao.MyFriendDao;
import com.bc.pmpheep.back.commuser.myfriend.service.MyFriendService;
import com.bc.pmpheep.back.commuser.user.bean.CommuserWriterUser;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 
 * <pre>
 * 功能描述：好友 业务层接口实现类
 * 使用示范：
 * 
 * 
 * @author (作者) nyz
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 2017-11-29
 * @modify (最后修改时间) 
 * @修改人 ：nyz 
 * @审核人 ：
 * </pre>
 */
@Service("com.bc.pmpheep.back.commuser.myfriend.service.MyFriendServiceImpl")
public class MyFriendServiceImpl implements MyFriendService {
    @Autowired
    MyFriendDao myFriendDao;

    @Override
    public List<Map<String, Object>> listMyFriend(String groupId,CommuserWriterUser writerUser,int startrow) throws Exception {
        Long userId = writerUser.getId();
        if (ObjectUtil.isNull(userId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.WRITER_FRIEND,
                                              CheckedExceptionResult.NULL_PARAM, "用户Id为空");
        }
        List<Map<String, Object>> lst  =  myFriendDao.listMyFriend(groupId,userId,startrow);
//        for(WriterFriendVO writerFriendVO:  lst){
//        	String avatar =  writerFriendVO.getAvatar();
//        	writerFriendVO.setAvatar(RouteUtil.userAvatar(avatar));
//        }
        return lst ;
    }

	@Override
	public int listMyFriendCount(String groupId,CommuserWriterUser writerUser, int startrow) {
		Long userId = writerUser.getId();
        if (ObjectUtil.isNull(userId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.WRITER_FRIEND,
                                              CheckedExceptionResult.NULL_PARAM, "用户Id为空");
        }
        int count  =  myFriendDao.listMyFriendCount(groupId,userId,startrow);
		return count;
	}

	/**
	 * 
	 */
	@Override
	public void invite(String id, String groupId) {
		// TODO Auto-generated method stub
		myFriendDao.invite(id,groupId);
	}
    /**
     * 过作家用户id查询小组中被删除的成员
     */
	@Override
	public Map<String, Object> queryDelGMById(String id, String groupId) {
		// TODO Auto-generated method stub
		return myFriendDao.queryDelGMById(id,groupId);
	}
     /**
      *恢复被小组删除的作家用户
      */
	@Override
	public void recoverMember(String id, String groupId) {
		// TODO Auto-generated method stub
		myFriendDao.recoverMember(id,groupId);
	}


}
