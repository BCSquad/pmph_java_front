<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextpath=request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script>
		var contextpath = "${pageContext.request.contextPath}/";
	</script>
	<base href="<%=basePath%>">
    <title>图书搜索</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/commuser/booksearch/booksearch.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css"/>
    <script src="<%=path%>/resources/comm/jquery/jquery.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js"></script>
    <script src="<%=path%>/resources/comm/base.js"></script>
    <script src="<%=path%>/resources/commuser/booksearch/booksearch.js"></script>
</head>
<body>
	<!-- 隐藏域 -->
	<input type="hidden" id="search-name-temp" value="${real_search }">
	<input type="hidden" id="page-num-temp" value="1">


	<jsp:include page="/pages/comm/head.jsp"></jsp:include> 

<!-- <iframe style="width: 100%;padding: 0;margin: 0;height: 81px;border: none" src="../comm/head.html"></iframe> -->
<div class="body" style="background-color: #f6f6f6;padding-bottom:60px">
	<div class="nav" style="background-color: white">
       	<div style="width:1200px;height: 85px;margin: 0 auto;">
            <div class="searchDiv">
                <div class="searchInputLeft" onclick="queryBtnClick();">
                    <img src="<%=path%>/statics/image/sousuo-helpCenter.png">
                </div>
                <div class="searchInputRight">
                    <input class="input" id="search-name" value="${search }">
                </div>
            </div>
            <div class="selectDiv">
                <div class="tab" onclick="switchBetweenBookAndArticle('article')">文章</div>
                <div class="tab active" onclick="switchBetweenBookAndArticle('book')">书籍
                </div>
            </div>
        </div>
    </div>
    <div class="content-wrapper" >
        
        <div class="bigContent">
            <div  style="background-color: #f6f6f6;width: 100%;height: 15px;"></div>
            <div class="list" style="background-color: white" >
            	<div id="book-list-table"></div>
                <div class="pageDiv" >
                    <ul class="pagination" id="page1">
                    </ul>
                    <div style="display: inline-block;    vertical-align: top">
                        <select id="page-size-select" name="page-size-select">
                        	<option value="5">每页5条</option>
                            <option value="10">每页10条</option>
                            <option value="15">每页15条</option>
                            <option value="20">每页20条</option>
                            
                        </select>
                    </div>
                    <div class="pageJump">
                        <span>共<span id="totoal_count" >${totoal_count }</span>页，跳转到</span>
                        <input type="text"/>
                        <span class="pp">页</span>
                        <button type="button" class="button">确定</button>
                    </div>
                </div>
            </div>

        </div>
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
    $(function () {
        $('select').selectlist({
            zIndex: 10,
            width: 110,
            height: 30,
            optionHeight: 30
        });
    })
</script>
<!-- <iframe style="width: 100%;clear:both;padding: 0;margin: 0;height: 190px;border: none" src="../comm/tail.html"></iframe> -->
<div style="background-color: white;width: 100%;padding: 0;margin: 0;height: 220px;border: none;overflow: hidden;">
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
</div>
</body>
</html>