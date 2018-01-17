package com.bc.pmpheep.general.controller;

import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.general.bean.FileType;
import com.bc.pmpheep.general.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: SuiXinYang
 * @Description: 文件上传控制器
 * @Date: Created in 10:54 2017/11/15
 * @Modified: SuiXinYang
 **/
@Controller
public class FileUploadController {

    Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    @Autowired
    @Qualifier("com.bc.pmpheep.general.service.FileService")
    FileService fileService;

    /**
     * 上传文件并保存在Mongodb中
     *
     * @param model 状态
     * @param file 文件
     * @return 返回上传结果
     */
    @ResponseBody
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public ResponseBean<Object> upload(Model model, @RequestParam("file") MultipartFile file) {
    	Map<String,Object> map = new HashMap<String,Object>();
    	try {
        	Long fileSize = file.getSize();
        	map.put("fileId",  fileService.save(file, FileType.GROUP_FILE, 0));
        	map.put("fileSize",fileSize);
            return new ResponseBean<Object>(map);
        } catch (IOException ex) {
            return new ResponseBean<Object>(ex);
        } catch (Exception ex) {
            return new ResponseBean<Object>("未知异常");
        } 
    }
}
