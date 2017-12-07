<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="org-head">
    <div >
        <%--<div style="width: 100%;background-color:White;">--%>
        <div class="div-content">
            <div id="div-titletop">
                <span class="top-lable1">欢迎访问人教e卫平台！</span>
                <span class="top-lable2">∨</span>
                <span class="top-lable2">&nbsp</span>
                <span class="top-lable2">哈尔滨医科大学的账号</span>
                <span class="top-lable2">&nbsp&nbsp&nbsp&nbsp&nbsp</span>
                <span class="top-lable2">下载手机客户端！</span>
            </div>
        </div>
    </div>
    <div class="div-menu">
        <div class="div-content">
            <div style="width:176px;float:left;"><img alt="" src="<c:url value="/statics/image/_logo.jpg"/>"/></div>
            <div style="width:90px;float:left;">&nbsp</div>
            <div class="div-menu-child <%="backlog".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"     >待办事项</div>
            <div class="div-menu-child <%="audit".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"       >申报资料审核</div>
            <div class="div-menu-child <%="teachercert".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>" onclick="window.location.href='<c:url value="/teacherauth/toPage.action"/>'">教师认证</div>
            <div class="div-menu-child <%="usermanage".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"  onclick="window.location.href='<c:url value="/user/writerLists.action"/>'">用户管理</div>
            <div class="div-menu-child <%="account".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"     onclick="window.location.href='<c:url value="/teacherauth/toPage.action"/>'">账户设置</div>
            <div class="div-menu-child <%="message".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"     onclick="window.location.href='<c:url value="/teacherauth/toPage.action"/>'">消息</div>
        </div>
    </div>
</div>


