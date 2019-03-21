package com.bc.pmpheep.back.commuser.readpage.dao;

import com.bc.pmpheep.back.commuser.readpage.bean.BookVideo;

import java.util.Map;

@org.springframework.stereotype.Repository
public interface BookVideoDao {

	/**
	 * 新增 vedio
	 *
	 * @param bookVideo
	 * @return
	 * @introduction
	 * @author Mryang
	 * @createDate 2018年1月31日 9:24:01
	 */
	Integer addBookVideo(BookVideo bookVideo);

	/**
	 * 动态更新
	 *
	 * @introduction
	 * @author Mryang
	 * @createDate 2018年2月6日 下午5:10:58
	 * @param bookVideo
	 * @return
	 */
	Integer updateBookVideo(BookVideo bookVideo);
}
