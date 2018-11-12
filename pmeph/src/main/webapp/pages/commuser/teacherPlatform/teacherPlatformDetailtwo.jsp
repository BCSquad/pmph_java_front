<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>活动详情页</title>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/teacherPlatform/teacherPlatformDetail.css" type="text/css">
    <script type="text/javascript" src="${ctx}/resources/comm/ckplayer/ckplayer.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div class="content-wrapper">
    <div class="pagedatail">师资培训>活动详情</div>
    <div style="margin-top: 20px">
        <div class="name">师资培训：</div>
        <div class="title">${map.activity_name}</div>
    </div>
    <div style="margin-top: 20px">
        <div class="time">活动日期：${map.activity_date}</div>
        <div class="picture"></div>
        <div class="read">${map.times}</div>
    </div>
    <div style="margin-top: 50px">
        <div style="max-width: 100%;">
            <img src="${ctx}/image/${map.cover}.action" class="szpt-img">
        </div>
        <div>
            <%--<div class="part1" onclick="toxikb(${map.cms_id})">${map.title}</div>--%>
            <div>
                <div>${map.content}</div>
            </div>
        </div>
    </div>
    <div style="clear: both"></div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
