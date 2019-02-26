package com.bc.pmpheep.back.commuser.readpage.service;


import com.bc.pmpheep.back.commuser.readpage.bean.BookVideo;
import com.bc.pmpheep.service.exception.CheckedServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookVideoService {

    Integer addBookVideoFront(Long userId, BookVideo bookVideo, MultipartFile cover) throws CheckedServiceException, IOException;
    Integer updateBookVideo(BookVideo bookVideo);


}
