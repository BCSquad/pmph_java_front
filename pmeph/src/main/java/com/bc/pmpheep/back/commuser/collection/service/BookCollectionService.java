package com.bc.pmpheep.back.commuser.collection.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

/**
 * @author guoxiaobao
 *@Title: 
 * @Description: 书记收藏服务接口
 * @param 
 * @return 
 * @throws
 */
public interface BookCollectionService {
	 /**查询书籍收藏夹以及收藏夹中收藏书的数量
     * @return List<Map<String,Object>>  
     */
    List<Map<String,Object>> queryBookCollectionList(BigInteger writerId);
    /**根据收藏夹id获取收藏夹内收藏的书籍
     * @param favoriteId   收藏夹id
     * @return List<Map<String,Object>>
     */
    PageResult<Map<String,Object>> queryBookList(PageParameter<Map<String,Object>> param);
	/**查询某一收藏夹下书籍的数量
	 * @param favoriteId  收藏夹id
	 * @return
	 */
	int queryBookCont(BigInteger favoriteId,BigInteger writerId);
	/**查询用户是否对某一本书点赞
	 * @param bookId  BigInteger 书籍id
	 * @param writerId BigInteger 用户id
	 * @return
	 */
	int queryLikes(BigInteger bookId,BigInteger writerId);
	/**用户更改是否点赞
	 * @param bookId BigInteger 书籍id
	 * @param writerId BigInteger 用户id
	 * @return  Map<String,Object>
	 */
	Map<String,Object> updateLike(BigInteger bookId,BigInteger writerId);
	/**取消书籍的收藏
	 * @param markId  BigInteger 书籍收藏id
	 * @param writerId BigInteger 用户id
	 * @param favoriteId  BigInteger 收藏夹id
	 * @param bookId    BigInteger   书籍id
	 * @return Map<String, Object>
	 */
	Map<String, Object>  deleteMark(BigInteger markId, BigInteger writerId,
			BigInteger favoriteId, BigInteger bookId);
	/**删除收藏夹
	 * @param writerId   BigInteger   用户id
	 * @param favoriteId   BigInteger  收藏夹id
	 * @return
	 */
	Map<String, Object> deleteFavorite(BigInteger writerId,BigInteger favoriteId);
}
