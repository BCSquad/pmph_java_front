package com.bc.pmpheep.back.commuser.readpage.service;

import com.bc.pmpheep.back.commuser.readpage.bean.BookVideo;
import com.bc.pmpheep.back.commuser.readpage.dao.BookVideoDao;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.back.util.StringUtil;
import com.bc.pmpheep.general.bean.FileType;
import com.bc.pmpheep.general.service.FileService;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@Service("com.bc.pmpheep.back.commuser.readpage.service.BookVideoServiceImpl")
public class BookVideoServiceImpl implements BookVideoService {

    static Logger logger = LoggerFactory.getLogger(BookVideoServiceImpl.class);

    @Autowired
    private BookVideoDao bookVideoDao;
    @Autowired
    private FileService fileService;



    @Override
    public Integer updateBookVideo(BookVideo bookVideo)
            throws CheckedServiceException {
        if (null == bookVideo || null == bookVideo.getId()) {
            throw new CheckedServiceException("微视频", CheckedExceptionResult.NULL_PARAM, "参数为空");
        }
        return bookVideoDao.updateBookVideo(bookVideo);
    }


    @Override
    public Integer addBookVideoFront(Long userId, BookVideo bookVideo, MultipartFile cover) throws IOException {
        if (ObjectUtil.isNull(bookVideo.getBookId()) || StringUtil.isEmpty(bookVideo.getTitle())
                || StringUtil.isEmpty(bookVideo.getFileName()) || StringUtil.isEmpty(bookVideo.getPath())
                || StringUtil.isEmpty(bookVideo.getOrigFileName()) || StringUtil.isEmpty(bookVideo.getOrigPath())
                || ObjectUtil.isNull(bookVideo.getFileSize()) || ObjectUtil.isNull(bookVideo.getOrigFileSize())) {
            throw new CheckedServiceException("微视频", CheckedExceptionResult.NULL_PARAM, "参数为空");
        }
        bookVideo.setUserId(userId);
        bookVideo.setIsAuth(false);
        bookVideo.setCover("DEFAULT");//暂设为默认值
        bookVideoDao.addBookVideo(bookVideo);
        /* 保存封面 */
        String coverId = fileService.save(cover, FileType.BOOKVEDIO_CONER, bookVideo.getId());
        bookVideo.setCover(coverId);
        return bookVideoDao.updateBookVideo(bookVideo);
    }
}
