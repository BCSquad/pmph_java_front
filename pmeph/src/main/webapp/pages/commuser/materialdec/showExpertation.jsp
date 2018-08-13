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
<title>专家申报详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
 <link rel="stylesheet" href="${ctx}/statics/materialdec/material.css?t=${_timestamp}" type="text/css">
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js?t=${_timestamp}"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.jqprint-0.3.js?t=${_timestamp}"></script>
	<script src="http://www.jq22.com/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
<script type="text/javascript" src="${ctx}/resources/commuser/materialdec/showExpertation.js?t=${_timestamp}"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
	<div class="content-wrapper">
		<input type="hidden" name="material_id" id="material_id" value="${queryMap.expert_type}">
		<div class="sbxq_title">
			<span><a style="text-decoration: none;color: #999999;" href="${contextpath}/personalhomepage/tohomepage.action?pagetag=dt">个人中心</a> > <a style="text-decoration: none;color: #999999;" href="${contextpath}/expertation/declare.action"> 临床决策专家申报 </a> > 查看申报表</span>
		</div>
		<div id="ddd">
			<div class="tsxz_title" id="tnone" style="display: none;text-align: center;font-size: 20px;margin-top: 6px;">${material.material_name}</div>
		<!-- 专家信息-->
		<div class="sbxq_item1">
			<div>
				<span id="tsxz_span2"></span>
				<span class="tsxz_title">专家信息</span>
			</div>
			<div class="content">
				<table class="tab_1">
					<tr>
						<td><span>姓&emsp;&emsp;名：${gezlList.realname}</span></td>
						<td><span>性&emsp;&emsp;别：
								<c:if test="${gezlList.sex == '0'}">保密</c:if>
								<c:if test="${gezlList.sex == '1'}">男</c:if>
								<c:if test="${gezlList.sex == '2'}">女</c:if>
							</span></td>
						<td><span>出生年月：${gezlList.birthday1}</span></td>
						<td><span>教&emsp;&emsp;龄：${gezlList.experience}</span></td>
					</tr>
					<tr>
						<td><span>工作单位：${gezlList.org_name}</span></td>
						<td><span>职&emsp;&emsp;务：${gezlList.position}</span></td>
						<td><span>职&emsp;&emsp;称：${gezlList.title}</span></td>
						<td><span>E-mail：&emsp;${gezlList.email}</span></td>
					</tr>
					<tr>
						<td><span>邮&emsp;&emsp;编：${gezlList.postcode}</span></td>
						<td><span>联系电话：${gezlList.telephone}</span></td>
						<td><span>传&emsp;&emsp;真：${gezlList.fax}</span></td>
						<td><span>手&emsp;&emsp;机：${gezlList.handphone}</span></td>
					</tr>
					<tr>
						<td><span>证件类型：
								<c:if test="${gezlList.idtype == '0'}">身份证</c:if>
								<c:if test="${gezlList.idtype == '1'}">护照</c:if>
								<c:if test="${gezlList.idtype == '2'}">军官证</c:if>
							</span></td>
						<td><span>证件号码：${gezlList.idcard}</span></td>
						<td colspan="2"><span>地&emsp;&emsp;址：${gezlList.address}</span></td>
					</tr>

				</table>
			</div>
		</div>
		<!--主要学习经历-->
		<div class="sbxq_item" id="zyxxjl">
			<div>
				<span id="tsxz_span3"></span>
				<span class="tsxz_title">学习经历</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_xxjl">
					<thead>
						<tr>
							<td width="220px">起止时间</td>
							<td width="210px">学校名称</td>
							<td width="210px">所学专业</td>
							<td width="138px">学历</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="stulist" items="${stuList}">
							<tr>
								<td>${stulist.dbegin}-${stulist.dend}</td>
								<td>${stulist.school_name}</td>
								<td>${stulist.major}</td>
								<td>${stulist.degree}</td>
								<td>${stulist.note}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
			<!--主要工作经历-->
		<div class="sbxq_item" id="gzjl">
			<div>
				<span id="tsxz_span4"></span>
				<span class="tsxz_title">工作经历</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_gzjl">
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
								<td>${list.dbegin}-${list.dend}</td>
								<td>${list.org_name}</td>
								<td>${list.position}</td>
								<td>${list.note}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--主要学术兼职-->
		<div class="sbxq_item" id="xsjz">
			<div>
				<span id="tsxz_span10"></span>
				<span class="tsxz_title">主要学术兼职</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_xsjz">
					<thead>
						<tr>
							<td width="220px">兼职学术组织</td>
							<td width="220px">级别</td>
							<td width="220px">职务 </td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${zjxsList}">
							<tr>
								<td>${list.org_name}</td>
								<td>
									<c:if test="${list.rank == '0'}">无</c:if>
									<c:if test="${list.rank == '1'}">国际</c:if>
									<c:if test="${list.rank == '2'}">国家</c:if>
									<c:if test="${list.rank == '3'}">省部</c:if>
									<c:if test="${list.rank == '4'}">市级</c:if>
								</td>
								<td>${list.position}</td>
								<td>${list.note}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--人卫社教材编写情况表-->
		<div class="sbxq_item" id="rwsjcbx">
			<div>
				<span id="tsxz_span5"></span>
				<span class="tsxz_title">人卫社教材编写情况</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_rwsjcbx">
					<thead>
						<tr>
							<td width="230px">教材名称</td>
							<td width="120px">级别</td>
							<td width="120px">编写职务</td>
							<td width="100px">数字编委</td>
							<td width="120px">出版时间</td>
							<td width="120px">标准书号</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${rwsjcList}" varStatus="status">
						<tr>
							<td>${list.material_name}</td>
							<td>
								<c:if test="${list.rank == '0'}">无</c:if>
								<c:if test="${list.rank == '1'}">国家</c:if>
								<c:if test="${list.rank == '2'}">省部</c:if>
								<c:if test="${list.rank == '3'}">协编</c:if>
								<c:if test="${list.rank == '4'}">校本</c:if>
								<c:if test="${list.rank == '5'}">其他</c:if>
							</td>
							<td>
								<c:if test="${list.position == '0'}">无</c:if>
								<c:if test="${list.position == '1'}">主编</c:if>
								<c:if test="${list.position == '2'}">副主编</c:if>
								<c:if test="${list.position == '3'}">编委</c:if>
							</td>
							<td style="color: #333333;">
								<c:if test="${list.is_digital_editor == '1'}">是</c:if>
								<c:if test="${list.is_digital_editor == '0'}">否</c:if>
							</td>
							<td>${list.publishdate}</td>
							<td>${list.isbn}</td>
							<td>${list.note}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--主编学术专著情况表-->
		<div class="sbxq_item" id="zbxszz">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">主编学术专著情况</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_zbxszz">
					<thead>
						<tr>
							<td width="240px">专著名称</td>
							<td width="140px">专著发表日期</td>
							<td width="120px">出版方式</td>
							<td width="220px">出版单位</td>
							<td width="150px">出版时间</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${monographList}">
						<tr>
							<td>${list.monograph_name}</td>
							<td>${list.monographdate}</td>
							<td>
								<c:if test="${list.is_self_paid == '0'}">公费</c:if>
								<c:if test="${list.is_self_paid == '1'}">自费</c:if>
							</td>
							<td>${list.publisher}</td>
							<td>${list.publishdate}</td>
							<td>${list.note}</td>
							</tr></c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--主编或参编图书情况-->
		<div class="sbxq_item" id="zbcbtsqk">
			<div>
				<span id="tsxz_span6"></span>
				<span class="tsxz_title">主编或参编图书情况</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_zbtsqk">
					<thead>
					<tr>
						<td width="350px">教材名称</td>
						<td width="330px">出版社</td>
						<td width="160px">出版时间</td>
						<td>备注</td>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${editorList}" varStatus="status">
						<tr>
							<td>${list.material_name}</td>
							<td>${list.publisher}</td>
							<td>${list.publish_date}</td>
							<td>${list.note}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--扩展信息-->
		<c:forEach var="zjkzxx" items="${zjkzqkList}">
			<div class="sbxq_item1">
				<div>
					<span id="tsxz_span9"></span>
					<span class="tsxz_title">${zjkzxx.extension_name}</span>
				</div>
				<div class="content">
                    <div class="text_dy">
                            ${zjkzxx.content}
                    </div>
                </div>
			</div>
		</c:forEach>
		<!-- 学科分类-->
		<div class="sbxq_item1">
			<div>
				<span id="tsxz_span8"></span>
				<span class="tsxz_title">学科分类(可多选)</span>
				<span class="el-button" onclick="javascript:SubjectdAdd('${materialMap.product_id}')">添加学科分类</span>
			</div>
			<div class="sbdw" id="xkfladd">
				<span class="btmc">学科分类：</span>
				<c:forEach var="subject" items="${subjectList}" varStatus="status">
				<span class="el-tag" id="xkfl_${status.count}">${subject.type_name}<input name="subjectId" type="hidden" value="${subject.product_subject_type_id}"/>
					</span>
				</c:forEach>
			</div>
		</div>
		<!-- 内容分类-->
		<div class="sbxq_item1">
			<div>
				<span id="tsxz_span12"></span>
				<span class="tsxz_title">内容分类(可多选)</span>
				<span class="el-button" onclick="javascript:ContentAdd('${materialMap.product_id}')">添加内容分类</span>
			</div>
			<div class="sbdw" id="nrfladd">
				<span class="btmc">内容分类：</span>
				<c:forEach var="content" items="${contentList}" varStatus="status">
				<span class="el-tag" id="nrfl_${status.count}">${content.name_path}<input name="contentId" type="hidden" value="${content.product_content_type_id}"/>
					</span>
				</c:forEach>
			</div>
		</div>
		<div class="sbxq_item" id="szdwyj">
			<div>
				<span id="tsxz_span13"></span>
				<span class="tsxz_title"><img src="${ctx}/statics/image/btxx.png" />所在单位意见<span style="color: red">(上传单位盖章的申报表)</span></span>
			</div>
			<div style="height: 30px;margin-top: 10px;">
				<div class="filename"><a href="javascript:" onclick="downLoadProxy('${gezlList.unit_advise}')"
						title="${gezlList.syllabus_name}">${gezlList.syllabus_name}</a>
				</div>
			</div>
		</div>
		<hr style=" height:1px;border:none;border-top:1px #999999 dashed;margin-top: 30px;">
		</div>
		<%--<c:if test="${isSelfLog=='true' }">
			<div class="button">
				<div class="bt_tj" onclick="javascript:buttGive()">返回申报列表</div>
			</div>
			<span style="color: #E31028;font-size: 14px;text-align: center;float: left;margin-left: 350px;">打印推荐使用浏览器：chrome、360浏览器极速模式、IE浏览器支持IE10及以上版本</span>
		</c:if>--%>
		
	</div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
