<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>申报详情</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/materialdec/material.css" type="text/css">
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.js"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
	<div class="content-wrapper">
		<div class="sbxq_title">
			<span>个人中心 > 教材申报 > 申报详情</span>
		</div>
		<!-- 图书选择-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span1"></span>
				<span class="tsxz_title">图书选择</span>
			</div>
			<c:forEach var="list" items="${tsxzList}">
			<div class="item">
				<span>图书：</span>
				<span>${list.textbook_name} — ${list.preset_position}</span>
				<span><a>${list.syllabus_name}</a></span>
			</div>
			</c:forEach>
		<div>
		<!-- 专家信息-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span2"></span>
				<span class="tsxz_title">专家信息</span>
			</div>
			<div class="content">
				<table class="tab_1">
					<tr>
						<td><span>姓名：${gezlList.realname}</span></td>
						<td><span>性别：${gezlList.sex}</span></td>
						<td><span>出生年月：${gezlList.birthday}</span></td>
						<td><span>教龄：${gezlList.experience}</span></td>
					</tr>
					<tr>
						<td><span>工作单位：${gezlList.org_name}</span></td>
						<td><span>职务：${gezlList.position}</span></td>
						<td><span>职称：${gezlList.title}</span></td>
						<td><span>邮编：${gezlList.postcode}</span></td>
					</tr>
					<tr>
						<td colspan="2"><span>地址：${gezlList.address}</span></td>
						<td><span>  联系电话：${gezlList.telephone}</span></td>
						<td><span>传真：${gezlList.fax}</span></td>
					</tr>
					<tr>
						<td><span>手机：${gezlList.handphone}</span></td>
						<td><span>E-mail：${gezlList.email}</span></td>
						<td><span>证件类型：${gezlList.idtype}</span></td>
						<td><span>证件号码：${gezlList.idcard}</span></td>
					</tr>
				</table>
			</div>
		<div>
		<!--主要学习经历-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span3"></span>
				<span class="tsxz_title">主要学习经历</span>
			</div>
			<div class="content">
				<table class="tab_2">
					<thead>
						<tr>
							<td width="220px">起止时间</td>
							<td width="220px">学校名称</td>
							<td width="220px">所学专业</td>
							<td width="170px">学历</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="stulist" items="${stuList}">
						<tr>
							<td>${stulist.date_begin}-${stulist.date_end}</td>
							<td>${stulist.school_name}</td>
							<td>${stulist.major}</td>
							<td>${stulist.degree}</td>
							<td>${stulist.note}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		<div>
			<!--主要工作经历-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span4"></span>
				<span class="tsxz_title">主要工作经历</span>
			</div>
			<div class="content">
				<table class="tab_2">
					<thead>
						<tr>
							<td width="220px">起止时间</td>
							<td width="220px">工作单位</td>
							<td width="220px">职位</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${workList}">
						<tr>
							<td>${list.date_begin}-${list.date_end}</td>
							<td>${list.org_name}</td>
							<td>${list.position}</td>
							<td>${list.note}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		<div>
			<!--主要教学经历-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span5"></span>
				<span class="tsxz_title">主要教学经历</span>
			</div>
			<div class="content">
				<table class="tab_2">
					<thead>
						<tr>
							<td width="220px">起止时间</td>
							<td width="220px">学校名称</td>
							<td width="420px">教学科目 </td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${steaList}">
						<tr>
							<td>${list.date_begin}-${list.date_end}</td>
							<td>${list.school_name}</td>
							<td>${list.subject}</td>
							<td>${list.note}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		<div>
			<!--上版教材参编情况-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span6"></span>
				<span class="tsxz_title">上版教材参编情况</span>
			</div>
			<div class="content">
				<table class="tab_2">
					<thead>
						<tr>
							<td width="420px">教材名称</td>
							<td width="320px">编写职务</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${jcbjList}">
						<tr>
							<td>${list.material_name}</td>
							<td>${list.position}</td>
							<td>${list.note}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		<div>
			<!--国家精品课程建设情况-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">国家精品课程建设情况</span>
			</div>
			<div class="content">
				<table class="tab_2">
					<thead>
						<tr>
							<td width="420px">课程名称</td>
							<td width="300px">该课程全年课时数</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${gjkcjsList}">
						<tr>
							<td>${list.course_name}</td>
							<td>${list.class_hour}</td>
							<td>${list.note}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		<div>
			<!--教材编写情况-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">教材编写情况</span>
			</div>
			<div class="content">
				<table class="tab_2">
					<thead>
						<tr>
							<td width="220px">教材名称</td>
							<td width="120px">级别</td>
							<td width="120px">职责</td>
							<td width="170px">出版社</td>
							<td width="170px">出版时间</td>
							<td width="170px">标准书号</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${jcbxList}">
						<tr>
							<td>${list.material_name}</td>
							<td>${list.rank}</td>
							<td>${list.position}</td>
							<td>${list.publisher}</td>
							<td>${list.publish_date}</td>
							<td>${list.isbn}</td>
							<td>${list.note}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<hr style=" height:1px;border:none;border-top:1px #999999 dashed;margin-top: 30px;">
		<div>
		<!-- 申报单位-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span8"></span>
				<span class="tsxz_title">申报单位</span>
			</div>
			<div class="sbdw">
				<span>申报单位：</span>
				<span>${gezlList.org_name}</span>
			</div>
		<div>
		<hr style=" height:1px;border:none;border-top:1px #999999 dashed;margin-top: 30px;">
		<div class="button">
			<div class="bt_tj">返回申报列表</div>
		</div>
	</div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
