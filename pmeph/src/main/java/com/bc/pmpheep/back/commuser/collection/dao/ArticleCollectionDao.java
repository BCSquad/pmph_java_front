package com.bc.pmpheep.back.commuser.collection.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.plugin.PageParameter;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 文章收藏dao
 * @param 
 * @return 
 * @throws
 */
public interface ArticleCollectionDao {
	/**查询文章收藏夹以及收藏夹中收藏文章的数量
	 * @param  writerId   BigInteger  作家用户id
     * @return List<Map<String,Object>>  
     */
    List<Map<String,Object>> queryArticleCollectionList(@Param("writerId") BigInteger writerId );
    
    /**根据收藏夹id获取收藏夹内收藏的文章
     * @param param
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryArticleList(PageParameter<Map<String,Object>> param);
    /**查询某一收藏夹下文章的数量
	 * @param map
	 * @return
	 */
	int queryArticleCont(Map<String,Object> map);
    /**查询用户为某一文章是否点赞
     * @param contentId  文章id
     * @param writerId   用户id
     * @return
     */
    int queryLikes(@Param("contentId") BigInteger contentId,@Param("writerId") BigInteger writerId );
    /**向用户文章点赞表中添加数据
     * @param contentId   文章id
     * @param writerId    用户id
     */
    void insertArticelLike(@Param("contentId") BigInteger contentId,@Param("writerId") BigInteger writerId);
    /**删除用户文章点赞表中的数据
     * @param contentId 文章id
     * @param writerId 用户id
     */
    void deleteArticleLike(@Param("contentId") BigInteger contentId,@Param("writerId") BigInteger writerId);
    /**更改cms_content表中文章点赞的数量
     * @param contentId 文章id
     * @param likes   点赞数
     */
    void updateArticleLikes(@Param("contentId") BigInteger contentId,@Param("likes") BigInteger likes);
    /**更改cms_content表中文章收藏的数量
     * @param contentId  文章id
     * @param markes  收藏数
     */
    void updateMarkes(@Param("contentId") BigInteger contentId, @Param("markes") BigInteger markes);
	/**删除文章的收藏
	 * @param markId  收藏id
	 * @param writerId  用户id
	 * @param favoriteId  收藏夹id
	 */
	void deleteMark(@Param("markId") BigInteger markId,
			@Param("writerId") BigInteger writerId,
			@Param("favoriteId") BigInteger favoriteId);
	/**删除啊文章收藏夹
	 * @param writerId   用户id
	 * @param favoriteId 收藏夹id
	 */
	void deleteFavorite(@Param("writerId") BigInteger writerId,@Param("favoriteId")  BigInteger favoriteId);

	/**查询用户收藏夹下的文章收藏数，点赞数，点击数和评论数
	 * @param writerId   用户id
	 * @param favoriteId  收藏夹id
	 * @return
	 */
	List<Map<String, Object>> queryOther(@Param("writerId") BigInteger writerId,
			@Param("favoriteId") BigInteger favoriteId);
	/**查询一篇文章收藏数，点赞数，点击数和评论数
	 * @param contentId  文章id
	 * @return
	 */
	Map<String,Object> queryNum(@Param("contentId") BigInteger contentId);

	/**根据id查询文章收藏夹
	 * @param favoriteId
	 * @return
	 */
	Map<String, Object> queryFavoriteById(@Param("favoriteId") BigInteger favoriteId);
}
