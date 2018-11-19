package com.bc.pmpheep.back.commuser.community.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description:教材社区dao层 
 * @param 
 * @return 
 * @throws
 */
public interface CommunityDao {
    /**查询公告列表
     * @param param (startnum  分页开始下角标；size 数据的数量；searchText  查询的内容)
     * @return 
     */
    List<Map<String,Object>> queryNoticeList(Map<String,Object> param);
    
    /**id查询公告
     * @param id 公告id
     * @return
     */
    Map<String,Object> queryNoticeById(@Param("Id") Long id);
    
    /**根据教材id查询社区快报列表
     * @param id  教材id
     * @return
     */
    List<Map<String,Object>> queryMaterialNoticeList(@Param("materialId") Long id );
    /**根据教材id查询本套教材的图书 
     * @param id  教材id
     * @return
     */
    List<Map<String,Object>> queryTextBookList(@Param("materialId") Long id,@Param("start")int start,@Param("pageSize")int pageSize );
    /**查询社区主页精选书评
     * @param map
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> querySomeComment(Map<String,Object> map);
    /**查询社区主页精选书评的总数量
     * @param map
     * @return  List<Map<String,Object>>
     */
    int queryCommentCount(Map<String,Object> map);
    /**查询教材中书籍微视频
     * @param map  startnum:分页的开始的序号      size:分页的数据容量   materialId:教材id
     * @return
     */
    List<Map<String,Object>> queryVidos(Map<String,Object> map);
    /**查询教材中书籍微视频的总数
     * @param map   materialId:教材id
     * @return int
     */
    int queryVidoCount(Map<String,Object> map);
    /**id查询视频播放量
     * @param id
     * @return
     */
    public Map<String,Object> videoCount(String vid);

    /**
     * 根据ID修改播放量
     *
     * @param id
     * @param clicks
     * @return
     */
    void changeClicks(Map<String,Object> map);

    int countTextBookList(Long material_id);

    /**
     * 查询活动信息
     * @param material_id 教材ID
     * @return
     */
    List<Map<String,Object>> QueryActivitiById(Map<String,Object> map);

    /**
     * 查询活动总数
     * @param material_id
     * @return
     */
    int QueryCountById(@Param("material_id") String material_id);
}
