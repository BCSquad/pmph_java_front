<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<script type="text/javascript" >
	var contxtpath='${pageContext.request.contextPath}/';
</script>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>医学随笔列表</title>
    <script type="text/javascript">
        var contextpath = "${pageContext.request.contextPath}/";
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
     <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css?t=${_timestamp}"/>
        <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="${ctx}/statics/commuser/cms/cmscontents.css?t=${_timestamp}" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
     <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
	<script src="${ctx}/resources/commuser/cms/cmscontents.js?t=${_timestamp}"></script>
</head>
<body>
	<input type="hidden" id="page-num-temp" value="1">
	<jsp:include page="/pages/comm/head.jsp">
		<jsp:param value="articlepage" name="pageTitle" />
	</jsp:include> 
<div class="body">
    <div class="content-wrapper">
    	<div class="sbxq_title">
			<span>文章 &gt;医学随笔</span>
        <div class="notes" id="tbody1">

        </div>
      <!--   分页 -->
				<div class="pagination-wrapper">
					<ul class="pagination" id="page1">
					</ul>
					<div style="display: inline-block; vertical-align: top">
						<select id="page-size-select" name="page-size-select"
							onchange="selectPageSize();">
							<option value="12">每页12条</option>
							<option value="10">每页10条</option>
							<option value="20">每页20条</option>
							<option value="50">每页50条</option>
						</select>
					</div>
					<div class="pageJump">
						<span>共 <span><input type="text" id="total"
								value="${total}"></span> 页，跳转到
						</span> <input type="text" /> <span class="pp">页</span>
						<button type="button" class="button">确定</button>
					</div>
				</div>

				<div style="clear: both"></div>
    </div>
</div>
		<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
</div>
</body>
</html>