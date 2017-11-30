package com.bc.pmpheep.back.commuser.book.dao;

import org.springframework.stereotype.Repository;

import com.bc.pmpheep.back.commuser.book.bean.BookUserLike;

@Repository
public interface BookUserLikeDao {

    /**
     * 新增一个Book
     * 
     * @param BookUserLike 实体对象
     * @return 影响行数
     */
    Integer addBookUserLike(BookUserLike bookUserLike);

    /**
     * 删除Book 通过主键id
     * 
     * @param BookUserLike
     * @return 影响行数
     */
    Integer deleteBookUserLikeById(Long id);

    /**
     * 更新一个 Book通过主键id
     * 
     * @param BookUserLike
     * @return 影响行数
     */
    Integer updateBookUserLike(BookUserLike bookUserLike);

    /**
     * 查询一个 Book 通过主键id
     * 
     * @param BookUserLike 必须包含主键ID
     * @return BookUserLike
     */
    BookUserLike getBookUserLikeById(Long id);

    /**
     * 
     * 
     * 功能描述：根据书籍id删除书籍点赞
     * 
     * @param bookId 书籍id
     * @return
     * 
     */
    Integer deleteBookUserLikeByBookId(Long bookId);
}
