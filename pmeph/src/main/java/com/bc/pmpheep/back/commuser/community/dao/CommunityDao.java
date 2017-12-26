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
    List<Map<String,Object>> queryTextBookList(@Param("materialId") Long id );
}
