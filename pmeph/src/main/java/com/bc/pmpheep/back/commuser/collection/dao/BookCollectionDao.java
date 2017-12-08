package com.bc.pmpheep.back.commuser.collection.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.plugin.PageParameter;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 书籍收藏dao
 * @param 
 * @return 
 * @throws
 */
public interface BookCollectionDao {
     /**查询书籍收藏夹以及收藏夹中收藏书的数量
      * @param  writerId   BigInteger  作家用户id
     * @return List<Map<String,Object>>  
     */
    List<Map<String,Object>> queryBookCollectionList(@Param("writerId") BigInteger writerId );
    
    /**根据收藏夹id获取收藏夹内收藏的书籍
     * @param favoriteId   BigInteger    收藏夹id
     * @param size         int 分页参数，每一页展示的数据量
     * @param startnum     int 分页开始的下角标
     * @param  writerId   BigInteger  作家用户id
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryBookList(PageParameter<Map<String,Object>> param);
    /**查询某一收藏夹下书籍的数量
	 * @param map     favoriteId,收藏夹id; writerId,作家用户id
	 * @return int
	 */
	int queryBookCont(Map<String,Object> map);
	/**查询用户是否对某一本书点赞
	 * @param bookId  BigInteger 书籍id
	 * @param writerId BigInteger 用户id
	 * @return
	 */
	int queryLikes(@Param("bookId") BigInteger bookId,@Param("writerId") BigInteger writerId);
    /**向用户书籍点赞表中加入数据  
     * @param map
     */
    void insertBookLike(Map<String,Object> map);
    /**删除用户书籍点赞表中一条数据
     * @param bookId BigInteger 书籍id
     * @param writerId BigInteger  用户id
     */
    void deleteBookLike(@Param("bookId") BigInteger bookId,@Param("writerId") BigInteger writerId);
    /**更改book表中点赞的数量
     * @param bookId BigInteger 书籍id
     * @param likes BigInteger  用户id
     */
    void updateBookLikes(@Param("bookId") BigInteger bookId,@Param("likes") BigInteger likes);
	/**删除书籍的收藏
	 * @param markId   BigInteger 书籍id
     * @param writerId BigInteger  用户id
     * @param favoriteId  BigInteger  收藏夹id
	 */
	void deleteMark(@Param("markId") BigInteger markId,
			@Param("writerId") BigInteger writerId,
			@Param("favoriteId") BigInteger favoriteId);

	/**更改book表中收藏的数量
	 * @param bookId 书籍id
	 * @param markes 书籍收藏的数量
	 */
	void updateMarkes(@Param("bookId") BigInteger bookId, @Param("markes") BigInteger markes);
	/**删除收藏夹
	 * @param writerId   用户id
	 * @param favoriteId 收藏夹id
	 */
	void deleteFavorite(@Param("writerId") BigInteger writerId,@Param("favoriteId")  BigInteger favoriteId);
	/**查询一个收藏夹下书籍的收藏数量、点赞数量、点击数和收藏数
	 * @param writerId   
	 * @param favoriteId
	 * @return
	 */
	List<Map<String,Object>> queryOther(@Param("writerId") BigInteger writerId,@Param("favoriteId")  BigInteger favoriteId);
    /**查询一本书籍的收藏数量、点赞数量、点击数和收藏数 
     * @param bookId  书籍id
     * @return
     */
    Map<String,Object> queryNum(@Param("bookId") BigInteger bookId);
    /**根据id查询书籍收藏夹
     * @param favoriteId
     * @return
     */
    Map<String,Object> queryFavoriteById(@Param("favoriteId")  BigInteger favoriteId);
}
