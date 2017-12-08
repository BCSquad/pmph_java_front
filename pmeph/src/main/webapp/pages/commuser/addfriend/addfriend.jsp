<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script>
		var contextpath = "${pageContext.request.contextPath}/";
	</script>
	<base href="<%=basePath%>">
    <title>添加好友</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/commuser/addfriend/addfriend.css" type="text/css">
    <script type="text/javascript" src="<%=path%>/resources/comm/jquery/jquery.min.js"></script>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css"/>
    <script src="<%=path%>/resources/comm/jquery/jquery.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js"></script>
    <script src="<%=path%>/resources/comm/base.js"></script>
    <script src="<%=path%>/resources/commuser/addfriend/addfriend.js"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
	<!-- 隐藏域 -->
	
	<input type="hidden" id="search-name-temp" value="">
	<input type="hidden" id="page-num-temp" value="1">
	
    <div class="content-wrapper">
        <div class="white-back-ground-float-box">
            <div class="add-friend-label">添加好友</div>
            <div>
                <button class="search-btn" onclick="queryBtnClick()"></button>
                <input class="search-condition" id="search-name">
            </div>
            <div class="friend-box-wrapper" id="table-15">
            	
                
                
            </div>
            <div style="clear:both;"></div>
            <div class="pagination-wrapper">
                <ul class="pagination" id="page1">
                </ul>
                
                <div class="pageJump">
                    <span>共<span id="totoal_count" >${totoal_count }</span>页，跳转到</span>
                    <input type="text"/>
                    <span class="pp">页</span>
                    <button type="button" class="button">确定</button>
                </div>
            </div>
            <script>
                Page({
                    num: 16,					//页码数
                    startnum: 3,				//指定页码
                    elem: $('#page1'),		//指定的元素
                    callback: function (n) {	//回调函数
                        console.log(n);
                    }
                });
                
            </script>

        </div>
    </div>
</div>
<div style="background-color: #f4f4f4;width: 100%;padding: 0;margin: 0;height: 220px;border: none;overflow: hidden;">
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
</div>
<!-- <iframe style="width: 100%;clear:both;padding: 0;margin: 0;height: 220px;border: none;background-color: #f4f4f4;" src="../comm/tail.html"></iframe> -->
</body>
</html>