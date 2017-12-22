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
<title>教材申报审核</title> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
 <link rel="stylesheet" href="${ctx}/statics/materialdec/materialAudit.css" type="text/css">
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/base.js"></script>
<script type="text/javascript" src="${ctx}/resources/commuser/materialdec/materialAudit.js"></script>
</head>
<body>
<div style="width: 100%;padding: 0;margin: 0;height: 110px;border: none;overflow: hidden;">
	<jsp:include page="/pages/comm/headGreenBackGround.jsp"></jsp:include> 
</div>
<div class="body">
	<div class="content-wrapper">	
		<div class="tsxz_title">资料审核</div>
		<hr style=" height:1px;border:none;border-top:2px #999999 dashed;margin-top: 10px;">
		<!-- 参数 -->
		<div class="box">
			<!-- 图书选择-->
			<div class="sbxq_item1">
				<div>
					<span id="tsxz_span1"></span>
					<span class="tsxz_title">图书选择</span>
				</div>
				<c:forEach var="list" items="${tsxzList}">
				<div class="item">
					<span>图书：</span>
					<span>${list.textbook_name} — 
					<c:if test="${list.preset_position == '1'}">主编</c:if>
					<c:if test="${list.preset_position == '2'}">副主编</c:if>
					<c:if test="${list.preset_position == '3'}">编委</c:if>
					<c:if test="${list.preset_position == '4'}">数字编委</c:if>
					</span>
					<span>
					<a href="javascript:" class="filename"  onclick="downLoadProxy('${list.syllabus_id}')">${list.syllabus_name}</a>
					</span>
				</div>
				</c:forEach>
			</div>
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
							<td><span>出生年月：${gezlList.birthday}</span></td>
							<td><span>教&emsp;&emsp;龄：${gezlList.experience}</span></td>
						</tr>
						<tr>
							<td><span>工作单位：${gezlList.org_name}</span></td>
							<td><span>职&emsp;&emsp;务：${gezlList.position}</span></td>
							<td><span>职&emsp;&emsp;称：
								<c:if test="${gezlList.title == '0'}">教授</c:if>
								<c:if test="${gezlList.title == '1'}">主任</c:if>
								<c:if test="${gezlList.title == '2'}">副主任</c:if>
							</span></td>
							<td><span>邮&emsp;&emsp;编：${gezlList.postcode}</span></td>
						</tr>
						<tr>
							<td colspan="2"><span>地&emsp;&emsp;址：${gezlList.address}</span></td>
							<td><span>联系电话：${gezlList.telephone}</span></td>
							<td><span>传&emsp;&emsp;真：${gezlList.fax}</span></td>
						</tr>
						<tr>
							<td><span>手&emsp;&emsp;机：${gezlList.handphone}</span></td>
							<td><span style="width: 70px">E-mail：${gezlList.email}</span></td>
							<td><span>证件类型：
								<c:if test="${gezlList.idtype == '0'}">身份证</c:if>
								<c:if test="${gezlList.idtype == '1'}">护照</c:if>
								<c:if test="${gezlList.idtype == '2'}">军官证</c:if>
							</span></td>
							<td><span>证件号码：${gezlList.idcard}</span></td>
						</tr>
					</table>
				</div>
			</div>
			<!--主要学习经历-->
			<div class="sbxq_item" id="zyxxjl">
				<div>
					<span id="tsxz_span3"></span>
					<span class="tsxz_title">主要学习经历</span>
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
			</div>
				<!--主要工作经历-->
			<div class="sbxq_item" id="gzjl">
				<div>
					<span id="tsxz_span4"></span>
					<span class="tsxz_title">主要工作经历</span>
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
									<td>${list.date_begin}-${list.date_end}</td>
									<td>${list.org_name}</td>
									<td>${list.position}</td>
									<td>${list.note}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
				<!--主要教学经历-->
			<div class="sbxq_item" id="jxjl">
				<div>
					<span id="tsxz_span5"></span>
					<span class="tsxz_title">主要教学经历</span>
				</div>
				<div class="content">
					<table class="tab_2" id="tab_jxjz">
						<thead>
							<tr>
								<td width="220px">起止时间</td>
								<td width="220px">学校名称</td>
								<td width="220px">教学科目 </td>
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
			</div>
			<!--扩展信息-->
			<c:forEach var="zjkzxx" items="${zjkzqkList}">
			 	<div class="sbxq_item">
					<div>
						<span id="tsxz_span9"></span>
						<span class="tsxz_title">${zjkzxx.extension_name}</span>
					</div>
					<div class="content">
						<div style="margin: 5px;">${zjkzxx.content}</div>
					</div>
					<hr style=" height:1px;border:none;border-top:1px #c1c1c1 dashed;margin-top: 30px;">
				</div>
			</c:forEach>
			
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
										<c:if test="${list.rank == '1'}">国际</c:if>
										<c:if test="${list.rank == '2'}">国家</c:if>
										<c:if test="${list.rank == '3'}">省部</c:if>
										<c:if test="${list.rank == '4'}">其他</c:if>
									</td>
									<td>${list.position}</td>
									<td>${list.note}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
				<!--上版教材参编情况-->
			<div class="sbxq_item" id="sbjccb">
				<div>
					<span id="tsxz_span6"></span>
					<span class="tsxz_title">上版教材参编情况</span>
				</div>
				<div class="content">
					<table class="tab_2" id="tab_jccb">
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
									<td>
										<c:if test="${list.position == '1'}">主编</c:if>
										<c:if test="${list.position == '2'}">副主编</c:if>
										<c:if test="${list.position == '3'}">编委 </c:if>
										<c:if test="${list.position == '0'}">无</c:if>
									</td>
									<td>${list.note}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
				<!--国家级精品课程建设-->
			<div class="sbxq_item" id="gjjpkcjs">
				<div>
					<span id="tsxz_span7"></span>
					<span class="tsxz_title">国家级精品课程建设情况</span>
				</div>
				<div class="content">
					<table class="tab_2" id="tab_jpkcjs">
						<thead>
							<tr>
								<td width="420px">课程名称</td>
								<td width="200px">该课程全年课时数</td>
								<td>备注</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${gjkcjsList}">
							<c:if test="${list.type == 1}">
								<tr>
									<td>${list.course_name}</td>
									<td>${list.class_hour}</td>
									<td>${list.note}</td>
								</tr>
							</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!--省部级课程建设-->
			<div class="sbxq_item" id="sbkcjs">
				<div>
					<span id="tsxz_span7"></span>
					<span class="tsxz_title">省部级课程建设情况</span>
				</div>
				<div class="content">
					<table class="tab_2" id="tab_sjkcjs">
						<thead>
							<tr>
								<td width="420px">课程名称</td>
								<td width="200px">该课程全年课时数</td>
								<td>备注</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${gjkcjsList}">
							<c:if test="${list.type == 2}">
								<tr>
									<td>${list.course_name}</td>
									<td>${list.class_hour}</td>
									<td>${list.note}</td>
								</tr>
							</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!--学校课程建设情况-->
			<div class="sbxq_item" id="xxkcjs">
				<div>
					<span id="tsxz_span7"></span>
					<span class="tsxz_title">学校课程建设情况</span>
				</div>
				<div class="content">
					<table class="tab_2" id="tab_xskcjs">
						<thead>
							<tr>
								<td width="420px">课程名称</td>
								<td width="200px">该课程全年课时数</td>
								<td>备注</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${gjkcjsList}">
							<c:if test="${list.type == 3}">
								<tr>
									<td>${list.course_name}</td>
									<td>${list.class_hour}</td>
									<td>${list.note}</td>
								</tr>
							</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!--主编国家级规划教材情况-->
			<div class="sbxq_item" id="zbgjjgh">
				<div>
					<span id="tsxz_span7"></span>
					<span class="tsxz_title">主编国家级规划教材情况</span>
				</div>
				<div class="content">
					<table class="tab_2" id="tab_gjghjc">
						<thead>
							<tr>
								<td width="340px">教材名称</td>
								<td width="150px">标准书号</td>
								<td width="330px">教材级别</td>
								<td>备注</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${gjghjcList}">
								<tr>
									<td>${list.material_name}</td>
									<td>${list.isbn}</td>
									<td>
										<c:if test="${list.rank == '1'}">教育部十二五</c:if>
										<c:if test="${list.rank == '2'}">国家卫计委十二五</c:if>
										<c:if test="${list.rank == '3'}">教育部十二五、国家卫计委十二五</c:if>
									</td>
									<td>${list.note}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
				<!--教材编写情况-->
			<div class="sbxq_item" id="jcbxqk">
				<div>
					<span id="tsxz_span7"></span>
					<span class="tsxz_title">教材编写情况</span>
				</div>
				<div class="content">
					<table class="tab_2" id="tab_jcbx">
						<thead>
							<tr>
								<td width="250px">教材名称</td>
								<td width="120px">级别</td>
								<td width="120px">编写职务</td>
								<td width="150px">出版社</td>
								<td width="130px">出版时间</td>
								<td width="130px">标准书号</td>
								<td>备注</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${jcbxList}">
							<tr>
								<td>${list.material_name}</td>
								<td>
									<c:if test="${list.rank == '1'}">其他教材</c:if>
									<c:if test="${list.rank == '2'}">教育部规划</c:if>
									<c:if test="${list.rank == '3'}">卫计委规划</c:if>
									<c:if test="${list.rank == '4'}">区域规划</c:if>
									<c:if test="${list.rank == '5'}">创新教材</c:if>
								</td>
								<td>
									<c:if test="${list.position == '1'}">主编</c:if>
									<c:if test="${list.position == '2'}">副主编</c:if>
									<c:if test="${list.position == '3'}">编委 </c:if>
									<c:if test="${list.position == '0'}">无</c:if>
								</td>
								<td>${list.publisher}</td>
								<td>${list.publish_date}</td>
								<td>${list.isbn}</td>
								<td>${list.note}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!--作家科研情况-->
			<div class="sbxq_item" id="zjkyqk">
				<div>
					<span id="tsxz_span7"></span>
					<span class="tsxz_title">作家科研情况</span>
				</div>
				<div class="content">
					<table class="tab_2" id="tab_zjky">
						<thead>
							<tr>
								<td width="220px">课题名称</td>
								<td width="220px">审批单位</td>
								<td width="320px">获奖情况</td>
								<td width="320px">备注</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${zjkyList}">
								<tr>
									<td>${list.research_name}</td>
									<td>
										<c:if test="${list.approval_unit == '1'}">主编</c:if>
										<c:if test="${list.approval_unit == '2'}">副主编</c:if>
										<c:if test="${list.approval_unit == '3'}">编委 </c:if>
										<c:if test="${list.approval_unit == '0'}">无</c:if>
									</td>
									<td>${list.award}</td>
									<td>${list.note}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<!-- 申报单位-->
			<div class="sbxq_item1">
				<div>
					<span id="tsxz_span8"></span>
					<span class="tsxz_title">请选择你的申报单位</span>
				</div>
				<div class="sbdw">
					<span>申报单位：</span>
					<span>${gezlList.dwmc}</span>
				</div>
			</div>
			<hr style=" height:1px;border:none;border-top:1px #999999 dashed;margin-top: 30px;">
			<div class="button">
			<div class="div_butt">
				<div class="bt_tj" onclick="javascript:toAudit('${gezlList.id}','3')">通过</div>
				<div class="bt_tj" onclick="javascript:toAudit('${gezlList.id}','2')">驳回</div>
			</div>
		</div>
		</div>
	</div>
</div>
<div style="width: 100%;padding: 0;margin: auto;border:none;overflow: hidden;">
		<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
</div>
</body>
</html>
