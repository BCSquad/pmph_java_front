package com.bc.pmpheep.back.commuser.myfriend.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.user.bean.CommuserWriterUser;

/**
 * 
 * <pre>
 * 功能描述：好友 业务层接口
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
public interface MyFriendService {
    /**
     * 
     * <pre>
     * 功能描述：获取当前用户好友列表 
     * 使用示范：
     *
     * @param userId 用户ID
     * @return WriterFriend 好友对象集合
     * @throws Exception
     * </pre>
     */
    List<Map<String, Object>> listMyFriend(String groupId,CommuserWriterUser writerUser,int startrow) throws Exception;

    /**
     * 剩余未加载好友数量
     * @param writerUser
     * @param startrow
     * @return
     */
	int listMyFriendCount(String groupId,CommuserWriterUser writerUser, int startrow);

	void invite(String id, String groupId);

	/**
     * 通过作家用户id查询小组中被删除的成员
     * @param id
     * @param groupId
     * @return
     */
	Map<String,Object> queryDelGMById(String id,String groupId);
    /**
     * 恢复被小组删除的作家用户
     * @param id
     * @param groupId
     */
	void recoverMember(String id, String groupId);
}
