package com.bc.pmpheep.back.commuser.collection.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 文章收藏服务层接口
 * @param 
 * @return 
 * @throws
 */
public interface ArticleCollectionService {
	/**查询文章收藏夹以及收藏夹中收藏文章的数量
	 * @param  writerId   BigInteger  作家用户id
     * @return List<Map<String,Object>>  
     */
    List<Map<String,Object>> queryArticleCollectionList(BigInteger writerId );
    
    /**根据收藏夹id获取收藏夹内收藏的文章
     * @param favoriteId   收藏夹id
     * @param size         分页参数，每一页展示的数据的数量
     * @param startnum     分页开始下角标
     * @param  writerId   BigInteger  作家用户id
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryArticleList(BigInteger favoriteId,
         int startnum,int size,BigInteger writerId);
    /**查询某一收藏夹下文章的数量
	 * @param favoriteId  收藏夹id
	 * @param  writerId   BigInteger  作家用户id
	 * @return
	 */
	int queryArticleCont( BigInteger favoriteId, BigInteger writerId);

	/**对文章点赞或取消点赞
	 * @param contentId   文章id
	 * @param writerId    作家用户id
	 * @param likes      点赞数
	 */
	Map<String,Object> updateLike(BigInteger contentId, BigInteger writerId);

	/**取消对一篇文章的收藏
	 * @param markId     文章收藏id
	 * @param writerId   作家用户id
	 * @param favoriteId 收藏夹id
	 * @param markes     文章收藏的数量
	 * @param contentId  文章id
	 * @return
	 */
	Map<String, Object> deleteMark(BigInteger markId, BigInteger writerId,
			BigInteger favoriteId, BigInteger contentId);

	/**删除收藏夹
	 * @param writerId     作家用户id
	 * @param favoriteId   收藏夹id
	 * @return
	 */
	Map<String, Object> deleteFavorite(BigInteger writerId,
			BigInteger favoriteId);
}
