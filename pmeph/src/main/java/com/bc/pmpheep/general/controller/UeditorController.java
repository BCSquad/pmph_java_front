/**
 * 
 */
package com.bc.pmpheep.general.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.pmpheep.back.util.DateUtils;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.define.ActionMap;


/**
 * Ueditor富文本框后台
 * @author yanglei
 *
 */
@Controller
@RequestMapping("ueditor")
public class UeditorController  extends BaseController {

	 protected Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@RequestMapping(value = "upload/ue/{appPath:.*}", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> ueUpload(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String appPath) throws IllegalStateException, IOException,
            FileUploadException {

        Map<String, Object> m = new HashMap<String, Object>();
        // 对上传文件夹和临时文件夹进行初始化
        String rootDir = "/ueditor";

        String tmpDir = rootDir + "/tmp";
        File tmpDirPath = new File(tmpDir);

        if (ServletFileUpload.isMultipartContent(request)) {
            request.setCharacterEncoding("utf-8");


            DiskFileItemFactory dff = new DiskFileItemFactory();// 创建该对象
            dff.setRepository(tmpDirPath);// 指定上传文件的临时目录
            dff.setSizeThreshold(2 * 1024 * 1024);// 指定在内存中缓存数据大小,单位为byte
            ServletFileUpload sfu = new ServletFileUpload(dff);// 创建该对象
            sfu.setFileSizeMax(1000000000);// 指定单个上传文件的最大尺寸
            sfu.setSizeMax(1000000000);// 指定一次上传多个文件的总尺寸
            FileItemIterator fii = sfu.getItemIterator(request);// 解析request
            // 请求,并返回FileItemIterator集合
            while (fii.hasNext()) {
                FileItemStream fis = fii.next();// 从集合中获得一个文件流
                if (!fis.isFormField() && fis.getName().length() > 0) {// 过滤掉表单中非文件域

                    String filename = fis.getName();

                    String[] FileName = filename.split("\\.");

                    String preFile = FileName[0];
                    String endFile = FileName[1];


                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String nowdate = sdf.format(date);


                    String newFileName = preFile + "_" + nowdate + "." + endFile;
                    
                    appPath = appPath.trim().replaceAll("\\.", "/");

                    File appDir = new File(rootDir + "/" + appPath);
                    if (!appDir.isDirectory()) {

                        appDir.mkdir();
                    }
                    // 创建按月分类的子文件夹
                    String currentMonth = DateUtils.getCurrentDateAsString();
                    File appSubDir = new File(appDir + "/" + currentMonth);
                    if (!appSubDir.isDirectory()) {
                        appSubDir.mkdir();
                    }

                    String newFilepath = appSubDir + "/" + newFileName;

                    BufferedInputStream in = new BufferedInputStream(fis.openStream());// 获得文件输入流
                    BufferedOutputStream out =
                            new BufferedOutputStream(new FileOutputStream(new File(newFilepath)));// 获得文件输出流
                    Streams.copy(in, out, true);// 开始把文件写到你指定的上传文件夹
                    m.put("path", appPath + "/" + currentMonth + "/");
                    m.put("filename", newFileName);
                    
                    m.put("original", filename);
                    m.put("name", newFileName);
                    m.put("url", appPath + "/" + currentMonth + "/"+newFileName);
                    m.put("state", "SUCCESS");
                    m.put("type", ".jpg");
                    m.put("size", "99697");

                }
            }
        }

        return m;
    }
	
	@RequestMapping("resources/plugin")  
    public void baiduEdit(HttpServletRequest request, HttpServletResponse response) {  
        try {  
            request.setCharacterEncoding("utf-8");  
            response.setHeader("Content-Type", "text/html");  
              
            String rootPath = request.getServletContext().getRealPath("/");  
            logger.debug("================>{}", rootPath);  
            //针对配置百度上传附件读取配置文件  
            ActionMap.mapping.put("config", ActionMap.CONFIG);  
            //上传文件  
            ActionMap.mapping.put("uploadfile", ActionMap.UPLOAD_FILE);  
            ActionEnter actionEnter = new ActionEnter(request, rootPath);  
            response.getWriter().write(actionEnter.exec());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
