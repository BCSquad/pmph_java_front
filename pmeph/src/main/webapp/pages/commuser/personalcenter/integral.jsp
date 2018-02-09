<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>积分</title>
<script type="text/javascript">
	var contextpath = '${pageContext.request.contextPath}/';
</script>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${ctx}/statics/css/base.css"
	type="text/css">
<link rel="stylesheet"
	href="${ctx}/statics/commuser/personalcenter/integral.css"
	type="text/css">
<script type="text/javascript"
	src="${ctx}/resources/comm/jquery/jquery.js"></script>
<script src="${ctx}/resources/comm/base.js"></script>
<script src="${ctx}/resources/commuser/personalcenter/integral.js"></script>
</head>
<body>
	<jsp:include page="/pages/comm/head.jsp">
		<jsp:param value="homepage" name="pageTitle" />
	</jsp:include>
	<div class="body" style="background: #f6f6f6;">
		<div class="content-wrapper" >
			<div style="height: 30px"></div>
				<div class="top">
					<div class="top1">积分</div>
				</div>
			
			<div id="table">
				<div class="top2">总积分：</div>
				<div class="top3">${total.total }</div>
				<div class="line"></div>
				<div class="tb2">
					<div class="top-three">
						<div class="top4">积分记录：</div>
						<div class="top5">筛选：</div>
						<div class="top6" onclick='queryTime()'>三个月内<img src="${ctx}/statics/image/down.png"/></div>
					</div>
					<div class="message">
						<table class="table">
							<thead>
								<tr>
									<td class="">来源/用途</td>
									<td class="">积分变化</td>
									<td class="">时间</td>
								</tr>
							</thead>
							<tbody id="messageTable">
							</tbody>
						</table>
						<div class="no-more" >
						<img src="<c:url value="/statics/image/aaa4.png"></c:url>">
						<span>木有内容呀~~</span>
					</div>
					</div>
				</div>
				<div style="clear: both"></div>
				<!-- <div class="jzgd">加载更多...</div> -->
				<div class="js-load-more" style="cursor: pointer;">加载更多...</div>
			</div>
		</div>
		<div style="height: 60px; width: 100%;"></div>
	</div>
	<div style="clear: both"></div>
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>