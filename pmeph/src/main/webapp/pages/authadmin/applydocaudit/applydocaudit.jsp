<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <script type="text/javascript">
        var contextpath = "${pageContext.request.contextPath}/";
    </script>
    <title>申报资料审核</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/authadmin/applydocaudit/applydocaudit.css?t=${_timestamp}" type="text/css">
    <script type="text/javascript" src="<%=path%>/resources/comm/jquery/jquery.min.js?t=${_timestamp}"></script>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <script src="<%=path%>/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/authadmin/applydocaudit/applydocaudit.js?t=${_timestamp}"></script>
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
                    <button value="0" class="status-tab selected forXMirrorImg"><span class="forXMirrorImg">全部</span></button>
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
                <div class="no-more" style="display: none;">
                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
                    <span>木有内容呀~~</span>
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
	
		<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
	
</body>
</html>