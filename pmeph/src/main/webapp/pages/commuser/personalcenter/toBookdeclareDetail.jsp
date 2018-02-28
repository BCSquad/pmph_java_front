<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
 <script type="text/javascript">
           var contextpath = '${pageContext.request.contextPath}/';
  </script>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>我要出书-申报</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/commuser/bookdeclare/bookdeclareadd.css" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/css/jquery.calendar.css" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css" type="text/css">
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.calendar.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/base.js"></script>
<script src="${ctx}/resources/comm/jquery/jquery.fileupload.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/commuser/bookdeclare/bookdeclarezc.js"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
	<div class="content-wrapper">
		<div class="sbxq_title">
			<span>个人中心 > 我要出书 > 填写申报表</span>
		</div>
		<form id="objForm">
		<!-- 图书书稿情况-->
		 <div class="sbxq_item">
			
			<div class="content">
				<table class="tab_1">
					<tr>
						<td colspan="4">
							<div>
								<span id="tsxz_span1"></span>
								<span class="tsxz_title">图书书稿情况</span>
								<span class="tsxz_ts1"><img src="${ctx}/statics/image/btxx.png" /></span>
							</div>
						</td>
					</tr>
					<tr>
						<td width="140px"><span>选题名称：</span></td>
						<td width="460px">
							${topicMap.bookname}
							<input type="hidden" name="user_id" value="${topicMap.user_id}"/>
							<input type="hidden" name="topic_id" value="${topicMap.id}"/>
						</td>
						<td width="100px"><span>读者对象：</span></td>
						<td width="300px">
								<c:if test="${topicMap.reader == '0'}">医务工作者</c:if>
								<c:if test="${topicMap.reader == '1'}">医学院校师生</c:if>
								<c:if test="${topicMap.reader == '2'}">大众</c:if>
						</td>
					</tr>
					<tr>
						<td><span>预计交稿时间：</span></td>
						<td>
							${topicMap.deadline}
						</td>
						<td><span>选题来源：</span></td>
						<td>
								<c:if test="${topicMap.source == '0'}">社策划</c:if>
								<c:if test="${topicMap.source == '1'}">编辑策划</c:if>
								<c:if test="${topicMap.source == '2'}">专家策划</c:if>
								<c:if test="${topicMap.source == '3'}">离退休编审策划</c:if>
								<c:if test="${topicMap.source == '4'}">上级交办</c:if>
								<c:if test="${topicMap.source == '5'}">作者投稿</c:if>
						</td>
					</tr>
					<tr>
						<td><span>预估字数：</span></td>
						<td>${topicMap.word_number}</td>
						<td><span>预估图数：</span></td>
						<td>${topicMap.picture_number}</td>
					</tr>
						<tr>
						<td><span>学科及专业：</span></td>
						<td>${topicMap.subject}</td>
						<td><span>级别：</span></td>
						<td>
							<c:if test="${topicMap.rank == '0'}">低</c:if>
							<c:if test="${topicMap.rank == '1'}">中</c:if>
							<c:if test="${topicMap.rank == '2'}">高</c:if>
						</td>
					</tr>
					<tr>
						<td><span>图书类别：</span></td>
						<td colspan="3">
							<c:if test="${topicMap.type == '0'}">专著</c:if>
							<c:if test="${topicMap.type == '1'}">基础理论</c:if>
							<c:if test="${topicMap.type == '2'}">论文集</c:if>
							<c:if test="${topicMap.type == '3'}">科普</c:if>
							<c:if test="${topicMap.type == '4'}">应用技术</c:if>
							<c:if test="${topicMap.type == '5'}">工具书</c:if>
							<c:if test="${topicMap.type == '6'}">其他</c:if>
						</td>
					</tr>
					<!-- 结算信息 -->
					<tr>
						<td colspan="4">
							<div>
								<span id="tsxz_span2"></span>
								<span class="tsxz_title">结算信息</span>
							</div>
						</td>
					</tr>
					<tr>
						<td><span>银行账户：</span></td>
						<td>${BankMap.account_number}
						</td>
						<td><span>开户银行：</span></td>
						<td>${BankMap.bank}
						</td>
					</tr>
					<!-- 选题情况 -->
					<tr>
						<td colspan="4">
							<div>
								<span id="tsxz_span3"></span>
								<span class="tsxz_title">选题情况</span>
								<span class="tsxz_ts1"><img src="${ctx}/statics/image/btxx.png" /></span>
							</div>
						</td>
					</tr>
					<tr>
						<td><span class="btbs">*</span><span>一、选题理由：</span></td>
						<td colspan="3">
							<div class="content">
								<textarea class="text_cl" id="reason" name="reason" readonly="readonly">${textraMap.reason}</textarea>
							</div>
						</td>
					</tr>
					<tr>
						<td><span class="btbs">*</span><span>二、出版价值：</span></td>
						<td colspan="3">
							<div class="content">
								<textarea class="text_cl" id="price" name="price" readonly="readonly">${textraMap.price}</textarea>
							</div>
						</td>
					</tr>
					<tr>
						<td><span class="btbs">*</span><span>三、主要内容：</span></td>
						<td colspan="3">
							<div class="content">
								<textarea class="text_cl" id="extra_score" name="extra_score" readonly="readonly">${textraMap.score}</textarea>
							</div>
						</td>
					</tr>
					<!-- 读者情况及印刷预测  -->
					<tr>
						<td colspan="4">
							<div>
								<span id="tsxz_span4"></span>
								<span class="tsxz_title">读者情况及印刷预测 </span>
							</div>
						</td>
					</tr>
					<tr>
						<td><span>作者购书：</span></td>
						<td>${topicMap.purchase}
						</td>
						<td><span>作者赞助：</span></td>
						<td>${topicMap.sponsorship}
						</td>
					</tr>
				</table>
				<!-- 翻译书稿 -->
				<table class="tab_1">
					<tr>
						<td colspan="4">
							<div>
								<span id="tsxz_span5"></span>
								<span class="tsxz_title">翻译书稿</span>
							</div>
						</td>
					</tr>
					<tr>
						<td width="106px"><span>译稿原书名：</span></td>
						<td width="400px">${topicMap.original_bookname}
						</td>
						<td style="text-align: center;"><span>&emsp;&emsp;&emsp;原编著者：</span></td>
						<td width="308px">${topicMap.original_author}
						</td>
					</tr>
					<tr>
						<td><span>国籍：</span></td>
						<td>${topicMap.nation}
						</td>
						<td style="text-align: center;"><span>出版年代及版次：</span></td>
						<td>${topicMap.edition}
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!-- 申报编者情况 -->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span6"></span>
				<span class="tsxz_title">申报编者情况 </span>
			</div>
			<div class="content">
				<table class="tab_2" id="sbbzqk">
					<thead>
						<tr>
							<td width="120px">姓名</td>
							<td width="100px">性别</td>
							<td width="100px">年龄 </td>
							<td>行政职务</td>
							<td width="220px">工作单位</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${twriteList}" varStatus="status">
							<tr id="sbbzqk_${status.count}">
								<td>${list.realname}</td>
								<td>
									<c:if test="${list.sex == '0'}">男</c:if>
									<c:if test="${list.sex == '1'}">女</c:if>
								</td>
								<td>${list.price}</td>
								<td>${list.position}</td>
								<td>${list.workplace}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>	
		<div style=" height:1px;border:none;border-top:1px #c1c1c1 dashed;margin-top: 30px;width: 1000px;"></div>
		<div class="button">
			<div class="div_butt1">
				<div class="bt_tj" onclick="javascript:buttGive()">返回</div>
			</div>

		</div>
		</form>
	</div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
