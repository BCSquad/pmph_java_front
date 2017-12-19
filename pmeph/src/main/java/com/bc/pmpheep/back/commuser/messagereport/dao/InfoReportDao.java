package com.bc.pmpheep.back.commuser.messagereport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 信息快报数据连接层
 * @param 
 * @return 
 * @throws
 */
public interface InfoReportDao {

	/**根据信息id查询信息快报 
	 * @param id 信息快报id
	 * @return
	 */
	Map<String,Object> queryInfoReportById(@Param("id") long id);

	/**查询信息快报的列表
	 * @param num  分页查询开始的下角标
	 * @param size  每页展示的数据量
	 * @return
	 */
	List<Map<String, Object>> queryReportList(@Param("num") int num,@Param("size") int size);

	/** 查询信息快报的总数量
	 * @return int
	 */
	int queryInfoReportCount();

	/**查询用户对信息快报的点赞的次数的次数
	 * @param id  信息快报的id
	 * @param userId  用户id
	 * @return
	 */
	int queryLike(@Param("id") long id,@Param("userId") long userId);
    
	/**查询用户对信息快报收藏的次数
	 * @param id  信息快报的id
	 * @param userId 用户id
	 * @return
	 */
	int queryMark(@Param("id")  long id,@Param("userId") long userId);

	/**取消点赞     即删除信息快报在点赞中的记录
	 * @param id 信息快报的id
	 * @param userId 用户id
	 */
	void deleteLike( @Param("id") long id,@Param("userId") long userId);

	/**更新信息快报的总点赞数
	 * @param id  信息快报的id
	 * @param likes  总点赞数
	 */
	void updateLikes(@Param("id") long id,@Param("likes") long likes);

	/**添加信息快报的点赞记录
	 * @param id
	 * @param userId
	 */
	void insertLike(@Param("id") long id,@Param("userId") long userId);

	/**查询在默认收藏夹下对信息快报有没有收藏
	 * @param param
	 * @return
	 */
	int queryDefaultMark(Map<String, Object> param);

	/**查询默认文章收藏夹  
	 * @param userId 用户id
	 * @return
	 */
	Map<String, Object> queryDefaultFavorite(long userId);

	/**删除收藏夹中对信息快报的收藏记录
	 * @param param   （id 信息快报id;userId 用户id;fid  收藏夹id）
	 */
	void deleteMark(Map<String, Object> param);
    
	/**更新信息快报的总收藏数
	 * @param id  信息快报id
	 * @param marks  收藏的总数量
	 */
	void updateMarks(@Param("id") long id,@Param("marks")  long marks);

	/**添加收藏记录
	 * @param param   （id 信息快报id;userId 用户id;fid  收藏夹id）
	 */
	void insertMark(Map<String, Object> param);

	/**为用户添加默认文章收藏夹
	 * @param userId
	 */
	void insertDefaultFavorite(long userId);

	/**更改信息快报的点击总数
	 * @param id      信息快报id
	 * @param clicks  点击总数
	 */
	void updateClicks(@Param("id") long id,@Param("clicks") long clicks);
}
