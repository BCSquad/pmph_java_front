package com.bc.pmpheep.back.commuser.myfriend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.myfriend.bean.WriterFriendVO;

/**
 * 
 * <pre>
 * 功能描述：好友 数据访问层接口
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
public interface MyFriendDao {
    /**
     * 
     * <pre>
     * 功能描述：获取当前用户好友列表 
     * 使用示范：
     *
     * @param userId 用户ID
     * @return WriterFriend 好友对象集合
     * </pre>
     */
    List<Map<String, Object>> listMyFriend(@Param("groupId") String groupId,@Param("userId") Long userId,@Param("startrow") int startrow);

    /**
     * 查询剩余总数
     * @param userId
     * @param startrow
     * @return
     */
	int listMyFriendCount(@Param("groupId") String groupId,@Param("userId") Long userId,@Param("startrow") int startrow);

	void invite(@Param("id") String id,@Param("groupId") String groupId);
    /**
     * 通过作家用户id查询小组中被删除的成员
     * @param id
     * @param groupId
     * @return
     */
	Map<String,Object> queryDelGMById(@Param("id") String id,@Param("groupId") String groupId);
    /**
     * 恢复被小组删除的作家用户
     * @param id
     * @param groupId
     */
	void recoverMember(@Param("id") String id,@Param("groupId") String groupId);
}
