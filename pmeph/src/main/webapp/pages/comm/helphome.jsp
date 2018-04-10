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
    <link rel="stylesheet" href="<c:url value="/statics/css/base.css?t=${_timestamp}"/>" type="text/css">
    <link href="<c:url value="/statics/commuser/userinfo/userinfo.css?t=${_timestamp}"/>" rel="stylesheet" type="text/css"/>
    <script src="<c:url value="/resources/comm/jquery/jquery.js?t=${_timestamp}"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/comm/base.js?t=${_timestamp}"/>"></script>
    <style type="text/css">
        .menus {
            height: 40px;
            border-bottom: 1px solid #F3F2F1;
            margin: 0 20px;
        }

        .menu-item {
            float: left;
            height: 30px;
            margin-top: 9px;
            margin-right: 20px;
            text-align: center;
            line-height: 30px;
            color: #343535;
            font-size: 14px;
            border-bottom: 1px solid white;
            cursor: pointer;
        }

        .menu-item.active {
            color: #00AC8A;
            border-color: #00AC8A;
            cursor: auto;
        }

        .list {
            margin: 0 20px;
        }

        .list .item {
            height: 60px;
            border-bottom: 1px solid #F3F2F1;
        }

        .list .item .icon {
            float: left;
            width: 6px;
            height: 6px;
            border-radius: 3px;
            background: #00AC8A;
            position: relative;
            top: 27px;
        }

        .list .item .content {
            float: left;
            height: 60px;
            line-height: 60px;
            max-width: 900px;
            padding: 0 5px;
            display: block;
            text-decoration: none;
            color: #999999;
            cursor: pointer;
        }

        .list .item .time {
            float: right;
            height: 60px;
            line-height: 60px;
            margin-right: 10px;
        }

        .list .item .time a{
           text-decoration: none;
            color: black;
        }

        .list .item .time a:focus{
            color:blue;
        }
    </style>
    <script >
        $(function () {
            $("#download-title").click(function () {
                $(this).addClass("active");
                $("#questions-title").removeClass("active");
                $("#download").css("display","block");
                $("#questions").css("display","none");
            });

            $("#questions-title").click(function () {
                $(this).addClass("active");
                $("#download-title").removeClass("active");
                $("#questions").css("display","block");
                $("#download").css("display","none");
            });

        });
        
        //问答详情页
        function helpdetail(id) {
            window.location.href=contextpath+'help/helpdetail.action?id='+id;
        }
        
    </script>
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
        <div class="sxy-div-content" style="min-height: 600px">
            <div class="menus">
                <div class="menu-item active" id="questions-title">常见问题</div>
                <div class="menu-item" id="download-title">操作手册下载</div>
            </div>
            <div class="list" id="questions">
                <c:forEach items="${helpList}" var="list" varStatus="status">
                <div class="item">
                    <div class="icon"></div>
                    <a class="content" target="_blank" onclick="helpdetail('${list.id}')">${list.title}</a>
                    <div class="time">[${list.gmt_create}]</div>
                </div>
                </c:forEach>
            </div>

            <div class="list" id="download" style="display: none">
                <c:forEach items="${handbookList}" var="list" >
                <div class="item">
                    <div class="icon"></div>
                    <a class="content">${list.manual_name}</a>
                    <div class="time"><a   href="${pageContext.request.contextPath}/file/download/${list.attachment}.action" >[下载]</a></div>
                </div>
                </c:forEach>
            </div>

        </div>

    </div>
    <div style="height: 60px; width: 100%;"></div>
</div>
<div style="clear: both"></div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>

</html>