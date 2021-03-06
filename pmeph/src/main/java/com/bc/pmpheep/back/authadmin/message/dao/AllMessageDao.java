/**
 * create by xcy on 2017-12-7
 */
package com.bc.pmpheep.back.authadmin.message.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/** 
 * @ClassName: AllMessageDao 
 * @Description: TODO
 * @author xcy
 * @date 2017-12-7 上午11:33:23  
 */
public interface AllMessageDao {

	/** 
	* @Title: getAllMessageInit 
	* @Description: 获取机构用户全部消息——初始化
	* @return   
	* @throws 
	*/
	public List<Map<String, Object>> getAllMessageInit(Map<String, Object> map);
    /**
     *  根据系统消息内容id更改系统消息是否已读
     * @param mid
     * @param userid 
     * @return
     */
	public int updateIsRead(@Param("mid") String mid);
	/**
	 * 删除消息
	 * @param mid
	 * @param tag
	 * tag :
	 * 1删除收到的消息 根据主键id删除此条
	 * 2删除发送的信息 根据msg_id删除此次发送的消息 应该对多人发送所以有多条
	 */
	public int deletemsg(@Param("mid") String mid,@Param("tag") String tag);
	
	/**
	 * 获取机构用户发送的消息
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getSendMessage(Map<String, Object> param);

}
