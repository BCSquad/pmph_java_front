package com.bc.pmpheep.general.controller;

import com.bc.pmpheep.general.service.FileService;
import com.mongodb.gridfs.GridFSDBFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * @Author: SuiXinYang
 * @Description: MongoDB 文件下载控制器
 * @Date: Created in 10:48 2017/11/15
 * @Modified: SuiXinYang
 **/
@Controller
public class FileDownLoadController {
    Logger logger = LoggerFactory.getLogger(FileDownLoadController.class);
    @Autowired
    @Qualifier("com.bc.pmpheep.general.service.FileService")
    FileService fileService;

    /**
     * 普通文件下载
     *
     * @param id 文件在MongoDB中的id
     * @param response 服务响应
     */
    @RequestMapping(value = "/file/download/{id}", method = RequestMethod.GET)
    public void download(@PathVariable("id") String id, HttpServletResponse response) throws UnsupportedEncodingException {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/force-download");
        GridFSDBFile file = fileService.get(id);
        if (null == file) {
            logger.warn("未找到id为'{}'的文件", id);
            return;
        }
        response.setHeader("Content-Disposition", "attachment;fileName=\" "+new String((file.getFilename()).getBytes("gbk"), "ISO-8859-1") +"\"");
        try (OutputStream out = response.getOutputStream()) {
            file.writeTo(out);
            out.flush();
            out.close();
        } catch (IOException ex) {
            logger.error("文件下载时出现IO异常：{}", ex.getMessage());
        }
    }
    
    
    /**
     * 文件名称
     *
     * @param id 文件在MongoDB中的id
     */
    @RequestMapping(value = "/file/{id}/name", method = RequestMethod.GET)
    @ResponseBody
    public String getFileName(@PathVariable("id") String id) {
        GridFSDBFile file = fileService.get(id);
        if (null == file) {
            logger.warn("未找到id为'{}'的文件", id);
           return "文件不存在";
        }
        return file.getFilename();
    }
    
    
    /**
     * 普通文件下载并且更新下载次数
     *
     * @param id 文件在MongoDB中的id
     * @param response 服务响应
     */


    /**
     * 小组文件下载
     *
     * @param id 图片在MongoDB中的id
     * @param groupId 小组id
     * @param response 服务响应
     * @return ResponseBean对象
     */

}
