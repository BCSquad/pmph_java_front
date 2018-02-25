<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <title>帮助</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="<c:url value="/statics/css/base.css"/>" type="text/css">
    <link href="<c:url value="/statics/commuser/userinfo/userinfo.css"/>" rel="stylesheet" type="text/css"/>
    <script src="<c:url value="/resources/comm/jquery/jquery.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/comm/base.js"/>"></script>
    <style type="text/css">
        .question {
            margin: 0 20px;
            height: 60px;
            padding: 18px 0;
            box-sizing: border-box;
        }

        .question .icon {
            float: left;
            height: 24px;
            width: 24px;
            line-height: 24px;
            background: #FF9000;
            color: white;
            font-size: 16px;
            text-align: center;
            border-radius: 12px;
        }

        .question .title {
            margin-left: 15px;
            float: left;
        }

        .answer {
            margin: 0 20px;
            padding: 18px 0;
            box-sizing: border-box;
        }

        .answer .icon {
            float: left;
            height: 24px;
            width: 24px;
            line-height: 24px;
            background: #90BC29;
            color: white;
            font-size: 16px;
            text-align: center;
            border-radius: 12px;
        }

        .answer .title {
            width: 1080px;
            margin-left: 15px;
            float: left;
        }


    </style>
</head>
<body>
<input type="hidden" id="fileid">
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div class="body" style="background: #f6f6f6;">
    <div class="content-wrapper">
        <div style="height: 30px"></div>
        <input type="hidden" id="id" value="${map.id}">

        <div class="sxy-div-content">
            <div style="height:50px;">
                <span style="width:20px;"></span>
                <span class="sxy-div-menu">帮助</span>
            </div>
        </div>
        <div style="height:14px"></div>
        <div class="sxy-div-content" style="height: 60px">
            <div class="question">
                <div class="icon">问</div>
                <div class="title">习近平主持政治局会议 决定召开十九届三中全会</div>
            </div>
        </div>
        <div class="answer">
            <div class="icon">答</div>
            <div class="title">习近平主持政治局会议 决定召开十九届三中全会
                习近平主持政治局会议 决定召开十九届三中全会
                习近平主持政治局会议 决定召开十九届三中全会
                习近平主持政治局会议 决定召开十九届三中全会
                习近平主持政治局会议 决定召开十九届三中全会
                习近平主持政治局会议 决定召开十九届三中全会习近平主持政治局会议 决定召开十九届三中全会
            </div>
        </div>

    </div>
    <div style="height: 60px; width: 100%;"></div>
</div>
<div style="clear: both"></div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>

</html>