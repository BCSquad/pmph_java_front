package com.bc.pmpheep.general.controller;

import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.general.service.FileService;
import com.bc.pmpheep.general.service.UserService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Author: SuiXinYang
 * @Description: MongoDB 图片控制器
 * @Date: Created in 10:56 2017/11/15
 * @Modified: SuiXinYang
 * @updater YangLiang
 **/
@Controller
public class ImageController {
    Logger logger = LoggerFactory.getLogger(ImageController.class);
    @Autowired
    @Qualifier("com.bc.pmpheep.general.service.FileService")
    FileService fileService;

    @Autowired
    UserService userService;
    /**
     * 图片显示
     *
     * @param id 图片在MongoDB中的id
     * @param response 服务响应
     * @throws IOException 
     */
    @ResponseBody
    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public void avatar(@PathVariable("id") String id, HttpServletResponse response)  {
        response.setContentType("image/png");
        GridFSDBFile file = fileService.get(id);
        if (null == file) {

            //throw new IOException();
        }
        try (OutputStream out = response.getOutputStream()) {
            file.writeTo(out);
            out.flush();
            out.close();
        } catch (IOException ex) {
            // logger.error("文件下载时出现IO异常：{}", ex.getMessage());
        } catch (Exception ex) {
            // logger.error("文件下载时出现IO异常：{}", ex.getMessage());
        }
        return ;
    }

    /**
     * 图片上显示好友信息的 提示语
     * @param id 好友的id
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/myFriendInfo/{id}", method = RequestMethod.GET)
    public Map<String,Object> imageTips(@PathVariable("id") String id,HttpServletRequest request, HttpServletResponse response)  {
        HttpSession session = request.getSession();
        Map<String, Object> user = (Map<String, Object>) session.getAttribute(Const.SESSION_USER_CONST_WRITER);
        String userId = user.get("id").toString();
        if(id.equals(userId)){
           return user;
        }
        Map<String,Object> returnMap =userService.getFriendInfo(id,userId);
        if(returnMap==null){
            returnMap = new HashMap<String,Object>();
        }

        return returnMap;
    }
}
