<%@ page language="java" contentType="text/html; charset=UTF-8"
         import="com.baidu.ueditor.ActionEnter"
         pageEncoding="UTF-8" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.collections.MapUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.bc.pmpheep.general.service.FileService" %>
<%@ page import="java.io.File" %>
<%@ page import="com.bc.pmpheep.general.bean.FileType" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding("utf-8");
    response.setHeader("Content-Type", "text/html");

    String rootPath = application.getRealPath("/");
    //rootPath ="D:\\Program Files\\apache-tomcat-7.0.78\\webapps\\pmeph1\\";
    String result = new ActionEnter(request, rootPath).exec();

    Map<String, Object> resultMap = JSON.parseObject(result, Map.class);
    String action = request.getParameter("action");
    System.out.println(action + ":" + result);
    if (MapUtils.getString(resultMap, "state", "").equals("SUCCESS")) {
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        FileService fileService = applicationContext.getBean(FileService.class);
        if (action.equals("catchimage")) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) resultMap.get("list");
            for (Map<String, Object> item : list) {
                String url = MapUtils.getString(item, "url", "");
                String id = fileService.saveLocalFile(Thread.currentThread().getContextClassLoader().getResourceAsStream("../../" + url), FileType.CMS_ATTACHMENT, 0);
                item.put("url", "/image/" + id + ".action");
            }
        } else if (action.equals("uploadimage")||action.equals("uploadscrawl")) {//uploadscrawl 涂鸦图片 uploadimage 上传图片
            String url = MapUtils.getString(resultMap, "url", "");
            String id = fileService.saveLocalFile(Thread.currentThread().getContextClassLoader().getResourceAsStream("../../" + url), FileType.CMS_ATTACHMENT, 0);
            resultMap.put("url", "/image/" + id + ".action");
        } else {

            if (action != null &&
                    (action.equals("listfile") || action.equals("listimage"))) {
                rootPath = rootPath.replace("\\", "/");
                result = result.replaceAll(rootPath, "/");//把返回路径中的物理路径替换为 '/'
            }
        }


    }
    result = JSON.toJSONString(resultMap);


    System.out.println(result);
    out.write(result);
    //out.write( new ActionEnter( request, rootPath ).exec() );

%>