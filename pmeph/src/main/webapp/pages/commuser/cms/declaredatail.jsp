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
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
</head>
<body >
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div class="body">
    <div class="content-wrapper">
    <div style="margin-top: 15px">首页>临床决策专家申报</div>
    <%--<c:if test="${state==1}">--%>
        <%--<div class="name">人卫临床助手专家申报报名须知</div>--%>
    <%--</c:if>--%>
    <%--<c:if test="${state==2}">--%>
        <%--<div class="name">人卫用药助手专家申报报名须知</div>--%>
    <%--</c:if>--%>
    <%--<c:if test="${state==3}">--%>
        <%--<div class="name">人卫中医助手专家申报报名须知</div>--%>
    <%--</c:if>--%>
    <div>${description}</div>
    <div style="float: left;width: 100%">
        <c:forEach varStatus="status" var="list" items="${list_scanimg}">
            <div class="picture01"><img src="${ctx}/${list.attachment}" class="picture02"></div>
        </c:forEach>
    </div>

    <div style="float: left;width: 100%">
        <div>${note_detail}</div>
    </div>
    <div style="clear: both"></div>

    <div style="float: left;width: 100%">
        <div style="color: #70BBC2">相关附件：</div>
        <div class="att">
            <c:forEach items="${list_unscanimg}" var="list" varStatus="status">
                <div>
                <span><img src="${ctx}/statics/pictures/attachment.png">附件${status.index+1}:</span>
                <span style="margin-left: 5px"><a href="${ctx}/${list.attachment}">${list.attachment_name}</a></span>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="join" onclick="tojoin(${state})">报名参加</div>
        <div style="clear: both"></div>
</div>
</div>

<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>

<style>
    .picture01{
        float: left;
        width: 100%;
        margin: auto;
        margin-top: 10px;
    }

    .picture02{
        max-width: 100%;
        margin: auto;
        display: block;
    }

    a{
        color: #70BBC2;
        cursor: pointer;
    }

    img{
        margin-top: 5px;
        margin-right: 5px;

    }

    .att{
        margin-left: 80px;
    }

    .name{
        margin-top: 50px;
        width: 100%;
        text-align: center;
        font-size: 20px;
    }

    .join{
        margin-left: 45%;
        margin-top: 50px;
        background-color: #70BBC2;
        width: 143px;
        border-radius: 2px;
        height: 47px;
        text-align: center;
        color: white;
        cursor: pointer;
        line-height: 45px;
        float: left;
        margin-bottom: 150px;
    }
</style>
<script>
    function tojoin(state) {
        location.href = contextpath + 'expertation/lookforward.action?expert_type='+state;
    }
</script>
</html>
