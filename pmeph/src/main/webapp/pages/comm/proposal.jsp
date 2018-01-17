<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>投诉建议</title>
<link rel="stylesheet" href="${ctx}/statics/css/base.css"
	type="text/css">
<link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css" />
<script src="${ctx}/resources/comm/jquery/jquery.js"></script>
<script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
<script src="${ctx}/resources/comm/base.js"></script>
</head>
<body>
	<jsp:include page="/pages/comm/head.jsp">
		<jsp:param value="homepage" name="pageTitle" />
	</jsp:include>

	<div
		style="background-color: #f4f4f4; width: 100%; padding: 0; margin: 0; min-height: 10px; border: none; overflow: hidden;">
		<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
	</div>
</body>
</html>