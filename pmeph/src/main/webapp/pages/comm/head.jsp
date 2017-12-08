<%@ page import="java.util.Map" %><%--
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
                <img src="${ctx}/statics/image/logo.png" alt="">
            </div>
            <div class="links">
                <a class="link <%="homepage".equals(request.getParameter("pageTitle"))?"active":""%>"
                   onclick="window.location='${ctx}/homepage/tohomepage.action'">首页</a>
                <a class="link <%="readpage".equals(request.getParameter("pageTitle"))?"active":""%>"
                   onclick="window.location='${ctx}/readpage/main.action'">读书</a>
                <a class="link <%="articlepage".equals(request.getParameter("pageTitle"))?"active":""%>"
                   onclick="window.location='${ctx}/pages/commuser/articlepage/articlepage.jsp'">文章</a>
            </div>
            <span class="delete"></span>
            <input class="search-input" id="search-input" placeholder="图书/文章">

            <img class="search-icon" src="${ctx}/statics/image/search.png" alt="">

            <span class="write" onclick="window.location.href='${ctx}/writerArticle/initWriteArticle.action'">写文章</span>

            <span class="download">下载APP</span>

            <img class="download-pic" src="${ctx}/statics/image/APP-download.png">

            <%
                Map<String, Object> userInfo = (Map<String, Object>) request.getSession().getAttribute("_CONST_USER_");

                if (userInfo == null || userInfo.isEmpty()) {
                    request.setAttribute("userInfo", null);
                } else {
                    request.setAttribute("userInfo", userInfo);
                }
            %>
            <c:if test="${userInfo == null}">
                <div class="login-logout">
                    <a href="#">登录</a>
                    <span>/</span>
                    <a href="#">注册</a>
                    <img src="${ctx}/statics/image/question.png" alt="">
                </div>
            </c:if>

            <c:if test="${userInfo != null}">
                <div class="user-info">
                    <span class="sign"></span>
                    <img class="notice-icon" src="${ctx}/statics/image/message.png" alt="" onclick="location.href='${ctx}/message/applyMessageList.action'">
                    <img class="user-icon" src="${ctx}/statics/pictures/head.png" alt="">
                </div>
                <div class="user-select">
                    <img src="${ctx}/statics/image/userSelectbg.png" alt="">
                    <div class="select">
                        <div class="option">个人中心</div>
                        <div class="option">我的小组</div>
                        <div class="option">教程申报</div>
                        <div class="option out">退出</div>
                    </div>
                </div>
            </c:if>

        </div>
    </div>
</div>

