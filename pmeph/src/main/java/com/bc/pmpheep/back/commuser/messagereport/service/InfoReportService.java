package com.bc.pmpheep.back.commuser.messagereport.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 信息快报服务接口
 * @param 
 * @return 
 * @throws
 */
public interface InfoReportService {
    /**根据信息id查询信息快报 
     * @param id  信息快报id
     * @param usermap 用户相关信息
     * @return
     */
    Map<String,Object> queryInfoReportById(long id, Map<String, Object> usermap);

	/**查询信息快报的列表
	 * @param num  分页查询开始下角标
	 * @param size 每页展示的数量
	 * @return
	 */
	List<Map<String, Object>> queryReportList(int num, int size,String materialId);

	/**查询信息快报的总数量
	 * @return
	 */
	int getInfoReportCount(String materialId);
	/**添加点赞或取消点赞
	 * @param id 信息快报id
	 * @param userId 用户id
	 * @return  Map<String, Object>
	 */
	Map<String, Object> insertLike(long id, long userId);
	/**添加收藏或取消收藏
	 * @param id 信息快报id
	 * @param userId 用户id
	 * @return  Map<String, Object>
	 */
	Map<String, Object> insertMark(long id, long userId);

	/**更新信息快报的查看总数
	 * @param id       信息快报的id
	 * @param clicks   点击总数数
	 */
	void updateClicks(long id, long clicks);
}
