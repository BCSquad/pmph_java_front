<%@ page import="java.util.Map" %>
<%@ page import="com.bc.pmpheep.back.util.Const" %>
<%@ page import="org.apache.commons.collections.MapUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.bc.pmpheep.back.commuser.homepage.service.HomeService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: SuiXinYang
  Date: 2017/11/21
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="head">
    <div class="content-wrapper">
        <div class="content">
            <div class="image">
                <img src="${ctx}/statics/image/logo.png" alt="" onclick="window.location='${ctx}/homepage/tohomepage.action'">
            </div>
            <div class="links">
                <a class="link <%="homepage".equals(request.getParameter("pageTitle"))?"active":""%>"
                   onclick="window.location='${ctx}/homepage/tohomepage.action'">首页</a>
                <a class="link <%="readpage".equals(request.getParameter("pageTitle"))?"active":""%>"
                   onclick="window.location='${ctx}/readpage/main.action'">读书</a>
                <a class="link <%="articlepage".equals(request.getParameter("pageTitle"))?"active":""%>"
                   onclick="window.location='${ctx}/articlepage/toarticlepage.action'">文章</a>
            </div>
            <span class="delete"></span>
            <input class="search-input" id="search-input" placeholder="图书/文章" maxlength="50">

            <img class="search-icon" src="${ctx}/statics/image/search.png" alt="">

            <%--         <span class="write" onclick="window.location.href='${ctx}/writerArticle/initWriteArticle.action'">写文章</span>
         --%>
            <%--<span class="download">下载APP</span>

            <img class="download-pic" src="${ctx}/statics/image/APP-download.png">--%>

            <%
                Map<String, Object> userInfo = null;
                if ("1".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
                    userInfo = (Map<String, Object>) session.getAttribute(Const.SESSION_USER_CONST_WRITER);
                }

                if (userInfo == null || userInfo.isEmpty()) {
                    request.setAttribute("userInfo", null);
                } else {
                    request.setAttribute("userInfo", userInfo);
                }
            %>
            <c:if test="${userInfo == null}">
                <%--<div class="login-logout">
                    <a onclick="window.location.href=contextpath+'pages/comm/login.jsp?refer='+encodeURIComponent(window.location.href)"
                       href="javascript:;">登录</a>
                    <span>/</span>
                    <a onclick="window.location.href=contextpath+'pages/comm/login.jsp?refer='+encodeURIComponent(window.location.href)"
                       href="javascript:;">注册</a>
                    <img src="${ctx}/statics/image/question.png" alt="">
                </div>--%>

                <div class="loginout"
                     onclick="window.location.href=contextpath+'pages/comm/login.jsp?refer='+encodeURIComponent(window.location.href)">
                    <span class="login">您好,请登录</span>
                    <span class="logout">免费注册</span>
                </div>
                <span class="writing" onclick="window.location.href='${ctx}/writerArticle/initWriteArticle.action'">
                    <span class="icon"></span>
                    <span class="wtext">写文章</span>
                </span>
                <span class="help" onclick="window.location.href='${ctx}/help/helpList.action'">
                    <span class="icon"></span>
                    <span class="wtext" >帮助中心</span>
                </span>
            </c:if>


            <%
                String type = "notice";
                int messageNum = 0;
                Map<String, String> typeUrl = new HashMap<String, String>();
                typeUrl.put("notice", "/message/noticeMessageList.action");
                typeUrl.put("apply", "/message/applyMessageList.action");
                typeUrl.put("message", "/mymessage/listMyMessage.action");


                if (userInfo != null) {
                    ApplicationContext applicationContext =
                            WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
                    HomeService homeService = applicationContext.getBean("com.bc.pmpheep.back.homepage.service.HomeServiceImpl", HomeService.class);
                    List<Map<String, Object>> list = homeService.queryNotReadMessages(MapUtils.getString(userInfo, "id"));

                    for (Map<String, Object> item : list) {
                        messageNum += MapUtils.getIntValue(item, "a");
                    }

                    for (Map<String, Object> item : list) {
                        if (MapUtils.getIntValue(item, "a", 0) > 0) {
                            type = MapUtils.getString(item, "type");
                            break;
                        }
                    }


                }
                request.setAttribute("NOT_READ_MESSAGE_NUM", messageNum);
                request.setAttribute("NOT_READ_MESSAGE_URL", typeUrl.get(type));

            %>

            <c:if test="${userInfo != null}">
                <span class="logininfo">
                    您好,<%=MapUtils.getString(userInfo, "realname")%>
                </span>

                <span class="logininfoout"
                      onclick="window.location.href='<c:url value="/logout.action"/>'">
                    <%--<span class="icon"></span>--%>
                    <span class="wtext">退出</span>
                </span>


                <span class="writing logined"
                      onclick="window.location.href='${ctx}/writerArticle/initWriteArticle.action'">
                    <span class="icon"></span>
                    <span class="wtext">写文章</span>
                </span>

                <div class="user-info">

                    <c:if test="${NOT_READ_MESSAGE_NUM>0}">
                        <span class="sign"></span>
                    </c:if>

                    <img class="notice-icon" src="${ctx}${NOT_READ_MESSAGE_NUM>0?'/statics/image/message1.gif':'/statics/image/message.png'}" alt=""
                         onclick="location.href='${ctx}${NOT_READ_MESSAGE_URL}'">

                    <img class="user-icon"
                         src="<%
                        if ("DEFAULT".equals(MapUtils.getString(userInfo, "avatar", "DEFAULT"))) {
                            out.println(request.getContextPath() + "/statics/image/default_image.png");
                        } else {
                            out.println(request.getContextPath() + "/image/" + MapUtils.getString(userInfo, "avatar") + ".action");
                        }
                    %>"
                         alt="">
                </div>
                <div class="user-select">
                    <img src="${ctx}/statics/image/userSelectbg.png" alt="">
                    <div class="select">
                      <%--  <a class="option wide" href="javascript:;">您好,<%=MapUtils.getString(userInfo, "realname")%></a>--%>
                        <a class="option"
                           href='<c:url value="/personalhomepage/tohomepage.action?pagetag=dt"/>'>个人中心</a>
                        <a class="option" href="<c:url value='/group/list.action'/>">我的小组</a>
                          <a class="option" href="<c:url value='/myFriend/listMyFriend.action'/>">我的好友</a>
                        <a class="option"
                           href='<c:url value="/personalhomepage/tohomepage.action?pagetag=jcsb"/>'>教材申报</a>
                        <a class="option"
                           href='<c:url value="/teacherCertification/showTeacherCertification.action"/>'>教师认证</a>
                        <%--<a class="option out" href='<c:url value="/logout.action"/>'>退出</a>--%>
                    </div>
                </div>
            </c:if>

        </div>
    </div>
</div>
<c:if test="${NOT_READ_MESSAGE_NUM>0}">
    <div class="btm-tips">
        <div class="btm-text" onclick="location.href='${ctx}${NOT_READ_MESSAGE_URL}'">您有未读消息!!!</div>
    </div>
</c:if>


