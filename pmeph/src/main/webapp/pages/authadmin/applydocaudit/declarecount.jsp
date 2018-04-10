<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>申报统计（机构用户）</title>
<script type="text/javascript">
	var contextpath = "${pageContext.request.contextPath}/";
</script>
<link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}"
	type="text/css">
<link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css?t=${_timestamp}" />
<link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}" />
<script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
<script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
<script src="${ctx}/resources/comm/jquery/jquery.min.js?t=${_timestamp}"></script>
<script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
<script src="${ctx}/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
<link rel="stylesheet"
	href="${ctx}/statics/authadmin/applydocaudit/declarecount.css?t=${_timestamp}"
	type="text/css">
<script src="${ctx}/resources/authadmin/applydocaudit/declarecount.js?t=${_timestamp}"
	type="text/javascript"></script>

</head>
<body>
	<input type="hidden" id="userId" value="${userId }">
	<input type="hidden" id="material_id" value="${material_id }" />
	<div
		style="padding: 0; margin: 0; height: 110px; border: none; overflow: hidden;">
		<jsp:include page="/pages/comm/headGreenBackGround.jsp"></jsp:include>
	</div>
	<div class="body">
		<div class="content-wrapper" style="background-color: #f0f0f0;">
			<div class="title">查询统计>${material_name }</div>
			<div class="bigbox">
				<div class="top-title">
					<div class="top-title-bg">
						<div class="span-1">
							<div class="span-w">我校申报情况统计</div>
						</div>
						<span class="span-1-2"><a href="#jieguo">最终结果名单</a></span>
					</div>

				</div>

				<div class="excel">
					<div class="excel_left">
						<div class="excel_left-1">我校申报情况统计</div>
					</div>
					<div class="excel_right">
						<div class="excel_right_img">
							<img src="${ctx}/statics/image/iconfont-export-01.png" />
						</div>
						<div class="excel_right_e">
							<a class="aa" onclick="exportExcel();">导出Excel</a>
						</div>
						<div class="excel_right_q" style="cursor: pointer;"
							onclick='selectAll()'>查看全部</div>
						<div class="excel_right_xljt">
							<img src="${ctx}/statics/image/xljt.png" />
						</div>
					</div>
				</div>


				<div class="message">
					<table class="table" id="tableCount">
						<thead>
							<tr>
								<td class="">序号</td>
								<td class="">书名</td>
								<td class="">主编申报数</td>
								<td class="">副主编申报数</td>
								<td class="">编委申报数</td>
								<td class="">数字编委申报数</td>
								<td class="">主编当选数</td>
								<td class="">副主编当选数</td>
								<td class="">编委当选数</td>
								<td class="">数字编委当选数</td>
							</tr>
						</thead>
						<tbody id="queryTable">
							<c:forEach items="${listMap}" var="one" varStatus="status">
								<tr>
									<td>${ status.index + 1}</td>
									<td>${one.textbook_name}</td>
									<td>${one.decid1}</td>
									<td>${one.decid2}</td>
									<td>${one.decid3}</td>
									<td>${one.decid4}</td>
									<td>${one.dp1}</td>
									<td>${one.dp2}</td>
									<td>${one.dp3}</td>
									<td>${one.dp4}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="excel">
					<div class="excel_left">
						<div class="excel_left-1" id="jieguo">最终结果名单</div>
					</div>
					<div class="excel_right">
						<div class="excel_right_img">
							<img src="${ctx}/statics/image/iconfont-export-01.png" />
						</div>
						<div class="excel_right_e">
							<a class="aa" onclick="exportResultExcel();">导出Excel</a>
						</div>
						<div class="excel_right_q" style="cursor: pointer;"
							onclick='selectResults()'>查看全部</div>
						<div class="excel_right_xljt">
							<img src="${ctx}/statics/image/xljt.png" />
						</div>
					</div>
				</div>

				<div class="message">
					<table class="table">
						<thead>
							<tr>
								<td>序号</td>
								<td>书名</td>
								<td>主编名单</td>
								<td>副主编名单</td>
								<td>编委名单</td>
								<td>数字编委名单</td>
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
				<div class="js-load-more" style="cursor: pointer;">加载更多...</div>
			</div>
		</div>
	</div>

	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
