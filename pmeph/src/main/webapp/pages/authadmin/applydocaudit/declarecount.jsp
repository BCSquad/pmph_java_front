<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>申报统计（机构用户）</title>
<link rel="stylesheet" href="${ctx}/statics/css/base.css"
	type="text/css">
<link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css" />
<link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css" />
<script src="${ctx}/resources/comm/jquery/jquery.js"></script>
<script src="${ctx}/resources/comm/jquery/jquery.min.js"></script>
<script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
<script src="${ctx}/resources/comm/jquery/jquery.pager.js"></script>
<script src="${ctx}/resources/comm/base.js"></script>
<link rel="stylesheet"
	href="${ctx}/statics/authadmin/applydocaudit/declarecount.css"
	type="text/css">
<script src="${ctx}/resources/authadmin/applydocaudit/declarecount.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var contextpath=<%=request.getContextPath()%>
</script>
</head>
<body >
	<div style=" padding: 0; margin: 0; height: 110px; border: none; overflow: hidden;">
		<jsp:include page="/pages/comm/headGreenBackGround.jsp"></jsp:include>
	</div>
	<div class="body" >
		<div class="content-wrapper" style="background-color: #f0f0f0;">
			<div class="title">查询统计>全国高等学校五年制临床医学专业第九轮规划教材</div>
			<div class="bigbox" >
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
							<a href="#" class="aa">导出Excel</a>
						</div>
						<div class="excel_right_q">查看全部</div>
						<div class="excel_right_xljt">
							<img src="${ctx}/statics/image/xljt.png" />
						</div>
					</div>
				</div>


				<div class="message">
					<table class="table">
						<thead>
							<tr>
								<td class="">序号</td>
								<td class="">书名</td>
								<td class="">主编申报数</td>
								<td class="">副主编申报数</td>
								<td class="">编委申报数</td>
								<td class="">主编当选数</td>
								<td class="">副主编当选数</td>
								<td class="">编委当选数</td>
							</tr>
						</thead>
						<tbody>
						 <c:set var="sum" value="10"></c:set>
						 <c:set var="decid1" value="0"></c:set>
						 <c:set var="decid2" value="0"></c:set>
						 <c:set var="decid3" value="0"></c:set>
						 <c:set var="dp1" value="0"></c:set>
						 <c:set var="dp2" value="0"></c:set>
						 <c:set var="dp3" value="0"></c:set>
							<c:forEach items="${listMap}" var="one" varStatus="status">
								<tr>
									<td>${ status.index + 1}</td>    
									<td >${one.textbook_name}</td>
									<td>${one.decid1}</td>
									 <c:set var="decid1" value="${decid1+one.decid1}"></c:set>
									<td>${one.decid2}</td>
									<c:set var="decid2" value="${decid2+one.decid2}"></c:set>
									<td>${one.decid3}</td>
									<c:set var="decid3" value="${decid3+one.decid3}"></c:set>
									<td>${one.dp1}</td>
									<c:set var="dp1" value="${dp1+one.dp1}"></c:set>
									<td>${one.dp2}</td>
									<c:set var="dp2" value="${dp2+one.dp2}"></c:set>
									<td>${one.dp3}</td>
									<c:set var="dp3" value="${dp3+one.dp3}"></c:set>
								</tr>
							</c:forEach>
							
								<tr>
									<td>${sum }</td>    
									<td >合计</td>
									<td>${decid1}</td>
									<td>${decid2}</td>
									<td>${decid3}</td>
									<td>${dp1}</td>
									<td>${dp2}</td>
									<td>${dp3}</td>
								</tr>
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
							<a href="#" class="aa">导出Excel</a>
						</div>
						<div class="excel_right_q">查看全部</div>
						<div class="excel_right_xljt">
							<img src="${ctx}/statics/image/xljt.png" />
						</div>
					</div>
				</div>

				<div class="message">
					<table class="table" >
						<thead>
							<tr>
								<td >序号</td>
								<td >书名</td>
								<td >主编名单</td>
								<td >副主编名单</td>
								<td >编委名单</td>
							</tr>
						</thead>
						<tbody id="messageTable">
							<c:forEach items="${listName}" var="two" varStatus="status">
								<tr>
									<td   >${ status.index + 1}</td>    
									<td   >${two.textbook_name}</td>
									<td   >${two.zb}</td>
									<td   >${two.fb}</td>
									<td   >${two.bw}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="jiazaigengduo" style="cursor: pointer;" onclick='loadMore()'>加载更多……</div>
				
				<input id="startPara" name="startPara" type="hidden">
				<input id="n" name="n" type="hidden">
			</div>
		</div>
	</div>

	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
