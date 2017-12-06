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
<base href="<%=basePath%>">
	<script>
		var contextpath = "${pageContext.request.contextPath}/";
	</script>
    <title>申报资料审核</title>
    <script type="text/javascript">
        var contextpath="<%=request.getContextPath()+"/"%>";
        
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/authadmin/applydocaudit/applydocaudit.css" type="text/css">
    <script type="text/javascript" src="<%=path%>/resources/comm/jquery/jquery.min.js"></script>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css"/>
    <script src="<%=path%>/resources/comm/jquery/jquery.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js"></script>
    <script src="<%=path%>/resources/comm/base.js"></script>
    <script src="<%=path%>/resources/authadmin/applydocaudit/applydocaudit.js"></script>
</head>
<body>

<jsp:include page="/pages/comm/headGreenBackGround.jsp">
    <jsp:param name="pageTitle" value="audit"></jsp:param>
</jsp:include>

<!-- 隐藏域 -->
<input type="hidden" id="status-temp" value="0">
<input type="hidden" id="query-condition-temp" >
<input type="hidden" id="page-num-temp" value="1">

<!-- <iframe style="width: 100%;padding: 0;margin: 0;height: 110px;border: none;overflow: hidden;" src="../accountSettings/masters/head_1.html"></iframe> -->
    <div class="body">
        <div class="content-wrapper">
            <div class="material-body-wrapper">
                <div id="status-tab-line">
                    <button value="0" class="status-tab selected">全部</button>
                    <button value="1" class="status-tab ">正在进行</button>
                    <button value="2" class="status-tab ">已结束</button>
                </div>
                <div class="search-tag right">
                    <input id="search_condition_input" type="text" placeholder="公告搜索">
                    <button onclick="search()"></button>
                </div>
                <div id="nine-block-box-container" class="nine-block-box-container">
                	${materialNineTable }
                </div>
                <div class="pagination-wrapper">
                    <ul class="pagination" id="page1">
                    </ul>

                    <div class="pageJump">
                        <span id="totoal_count" >共${totoal_count }页</span><span>，跳转到</span>
                        <input type="text"/>
                        <span class="pp">页</span>
                        <button type="button" class="button">确定</button>
                    </div>
                </div>
                
            </div>

        </div>
    </div>
    <!-- <iframe src="../comm/tail.html" style="width: 100%;padding: 0;margin: 0;height: 220px;border: none;overflow: hidden;"></iframe> -->
	<div style="background-color: white;width: 100%;padding: 0;margin: 0;height: 220px;border: none;overflow: hidden;">
		<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
	</div>
</body>
</html>