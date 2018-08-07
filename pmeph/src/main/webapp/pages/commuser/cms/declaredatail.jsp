<%--
  Created by IntelliJ IDEA.
  User: xieming
  Date: 2018/7/26
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>人卫E教平台</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div class="content-wrapper">
    <div style="margin-top: 15px">首页>临床决策专家申报</div>
    <div class="name">人卫临床助手专家申报报名须知</div>
    <div style="color: red;
    text-align: center;
    margin-top: 50px;
    font-size: 50px;
    line-height: 60px;">公告展示目前正在完善，请下个版本再测试！点击按钮报名参加。</div>
    <div class="join" onclick="tojoin(${state})">报名参加</div>
</div>
</body>
<style>
    .name{
        margin-top: 50px;
        width: 100%;
        text-align: center;
        font-size: 20px;
    }

    .join{
        margin-left: 45%;
        margin-top: 50px;
        background-color: #33CAA9;
        width: 70px;
        border-radius: 2px;
        text-align: center;
        color: white;
    }
</style>
<script>
    function tojoin(state) {
        location.href = contextpath + 'expertation/lookforward.action?expert_type='+state;
    }
</script>
</html>
