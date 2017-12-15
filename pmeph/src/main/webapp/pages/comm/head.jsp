<%@ page import="java.util.Map" %>
<%@ page import="com.bc.pmpheep.back.util.Const" %><%--
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
                <div class="login-logout">
                    <a href="<c:url value="/pages/comm/login.jsp"/>">登录</a>
                    <span>/</span>
                    <a href="<c:url value="/pages/comm/login.jsp"/>">注册</a>
                    <img src="${ctx}/statics/image/question.png" alt="">
                </div>
            </c:if>

            <c:if test="${userInfo != null}">
                <div class="user-info">
                    <span class="sign"></span>
                    <img class="notice-icon" src="${ctx}/statics/image/message.png" alt=""
                         onclick="location.href='${ctx}/message/applyMessageList.action'">
                    <img class="user-icon" src="${ctx}/statics/pictures/head.png" alt="">
                </div>
                <div class="user-select">
                    <img src="${ctx}/statics/image/userSelectbg.png" alt="">
                    <div class="select">
                        <a class="option" href='<c:url value="/personalhomepage/tohomepage.action"/>'>个人中心</a>
                        <a class="option" href="<c:url value='/group/list.action'/>">我的小组</a>
                        <a class="option" href='<c:url value="/personalhomepage/tohomepageone.action"/>'>教程申报</a>
                        <a class="option out" href='<c:url value="/logout.action"/>'>退出</a>
                    </div>
                </div>
            </c:if>

        </div>
    </div>
</div>

