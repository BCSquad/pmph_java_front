package com.bc.pmpheep.back.commuser.community.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 教材社区服务接口
 * @param 
 * @return 
 * @throws
 */
public interface CommunityService {
	 /**查询公告列表
     * @param param (startnum  分页开始下角标；size 数据的数量；searchText  查询的内容)
     * @return 
     */
    List<Map<String,Object>> queryNoticeList(Map<String,Object> param);
    
    /**id查询公告
     * @param id
     * @return
     */
    Map<String,Object> queryNoticeById(Long id);
    
    /**根据教材id查询社区快报列表
     * @param id
     * @return
     */
    List<Map<String,Object>> queryMaterialNoticeList(Long id );
    /**根据教材id查询本套教材的图书 
     * @param id  教材id
     * @return
     */
    List<Map<String,Object>> queryTextBookList(Long id );
    /**查询社区主页精选书评
     * @param id 教材id
     * @param startnum  分也开始序号
     * @param size 每页的数据数量
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> querySomeComment(Long id,int startnum ,int size);
    /**查询社区主页精选书评
     * @param id 教材id
     */
    int queryCommentCount(Long id);
}
