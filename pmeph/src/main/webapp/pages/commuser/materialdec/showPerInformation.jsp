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
<title>个人资料信息库</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
 <link rel="stylesheet" href="${ctx}/statics/materialdec/material.css?t=${_timestamp}" type="text/css">
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js?t=${_timestamp}"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.jqprint-0.3.js?t=${_timestamp}"></script>
	<script src="http://www.jq22.com/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
<%--
<script type="text/javascript" src="${ctx}/resources/commuser/materialdec/materialdetail.js?t=${_timestamp}"></script>
--%>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
	<div class="content-wrapper">

		<%--<div class="sbxq_title">
			<span><a style="text-decoration: none;color: #999999;" href="${ctx}/personalhomepage/tohomepage.action?pagetag=dt">个人中心</a> ><a style="text-decoration: none;color: #999999;" href="${ctx}/medu/personalhomepage/tohomepage.action?pagetag=jcsb&pageNum=1&pageSize=10"> 教材申报 </a> > ${material.material_name}</span>
		</div>--%>
		<div id="ddd">
			<div class="tsxz_title" id="tnone" style="display: none;text-align: center;font-size: 20px;margin-top: 6px;">${material.material_name}</div>
		<!-- 图书选择-->
		<%--<div class="sbxq_item1">
			<div>
				<input type="hidden" id="material_id" name="material_id" value="${material.id}"/>
				<span id="tsxz_span1"></span>
				<span class="tsxz_title">作家申报职位选择</span>
			</div>
			<c:forEach var="list" items="${tssbList}">
			<div class="item">
				<span>图书：</span>
				<span>${list.textbook_name} — ${list.preset_position}</span>
				<span style="margin-left: 10px;">
					<a href="javascript:" class="filename"  onclick="downLoadProxy('${list.syllabus_id}')">${list.syllabus_name}</a>
				</span>
			</div>
			</c:forEach>
		</div>--%>
		<!-- 专家信息-->
		<div class="sbxq_item1">
			<div>
				<span id="tsxz_span2"></span>
				<span class="tsxz_title">专家信息</span>
			</div>
			<div class="content">
				<table class="tab_1">
					<tr>
						<td><span>姓&emsp;&emsp;名：${userMap.realname}</span></td>
						<td><span>性&emsp;&emsp;别：
								<c:if test="${userMap.sex == '0'}">保密</c:if>
								<c:if test="${userMap.sex == '1'}">男</c:if>
								<c:if test="${userMap.sex == '2'}">女</c:if>
							</span></td>
						<td><span>出生年月：${userMap.birthday}</span></td>
						<td><span>教&emsp;&emsp;龄：${userMap.experience}</span></td>
					</tr>
					<tr>
						<td><span>工作单位：${userMap.workplace}</span></td>
						<td><span>职&emsp;&emsp;务：${userMap.position}</span></td>
						<td><span>职&emsp;&emsp;称：${userMap.title}</span></td>
						<td><span>E-mail：&emsp;${userMap.email}</span></td>
					</tr>
					<tr>
						<td><span>邮&emsp;&emsp;编：${userMap.postcode}</span></td>
						<td><span>联系电话：${userMap.telephone}</span></td>
						<td><span>传&emsp;&emsp;真：${userMap.fax}</span></td>
						<td><span>手&emsp;&emsp;机：${userMap.handphone}</span></td>
					</tr>
					<tr>
						<%--<td><span>证件类型：
								<c:if test="${userMap.idtype == '0'}">身份证</c:if>
								<c:if test="${userMap.idtype == '1'}">护照</c:if>
								<c:if test="${userMap.idtype == '2'}">军官证</c:if>
							</span></td>--%>
						<td><span>证件号码：${userMap.idcard}</span></td>
						<td colspan="3"><span>地&emsp;&emsp;址：${userMap.address}</span></td>
					</tr>
				<%--	<tr>
						<td><span>服从调剂：</span>
							<c:if test="${userMap.is_dispensed == '0'}">否</c:if>
							<c:if test="${userMap.is_dispensed == '1'}">是</c:if>
						</td>
						<td><span>参与本科教学评估认证：</span>
							<c:if test="${userMap.is_utec == '0'}">否</c:if>
							<c:if test="${userMap.is_utec == '1'}">是</c:if>
						</td>
						<td><span>学&emsp;&emsp;历：</span>
	                                <c:if test="${userMap.degree=='0'}">无</c:if>
	                                <c:if test="${userMap.degree=='1'}">大专</c:if>
	                                <c:if test="${userMap.degree=='2'}">本科</c:if>
	                                <c:if test="${userMap.degree=='3'}">硕士</c:if>
	                                <c:if test="${userMap.degree=='4'}">博士</c:if>
	                    </td>
						<td><span>专业特长：</span>
							${userMap.expertise}
						</td>
					</tr>--%>
				</table>
			</div>
		</div>
		<!--主要学习经历-->
		<div class="sbxq_item1" id="zyxxjl">
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
						<c:forEach var="stulist" items="${perstuList}">
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
		<div class="sbxq_item1" id="gzjl">
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
						<c:forEach var="list" items="${perworkList}">
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
			<!--主要教学经历-->
		<div class="sbxq_item1" id="jxjl">
			<div>
				<span id="tsxz_span5"></span>
				<span class="tsxz_title">教学经历</span>
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
						<c:forEach var="list" items="${persteaList}">
							<tr>
								<td>${list.dbegin}-${list.dend}</td>
								<td>${list.school_name}</td>
								<td>${list.subject}</td>
								<td>${list.note}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--个人成就-->
		<div class="sbxq_item1" id="grcjqk">
			<div>
				<span id="tsxz_span9"></span>
				<span class="tsxz_title">个人成就</span>
			</div>
			<div class="content">
				<div class="text_dy">
					${achievementMap.content}
				</div>
				<%--<textarea class="text_cl" name="gr_content" id="gr_content" maxlength="1000" readonly="readonly">${achievementMap.content}</textarea>--%>
			</div>
		</div>
		<!--主要学术兼职-->
		<div class="sbxq_item" id="xsjz">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">学术兼职</span>
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
						<c:forEach var="list" items="${perzjxsList}">
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
		<!--上版教材参编情况-->
		<div class="sbxq_item1" id="sbjccb">
			<div>
				<span id="tsxz_span6"></span>
				<span class="tsxz_title">本套上版教材参编情况</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_jccb">
					<thead>
						<tr>
							<td width="340px">教材名称</td>
							<td width="260px">编写职务</td>
							<td width="120px">数字编委</td>
							<td width="120px">出版单位</td>
							<td width="120px">出版时间</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${perjcbjList}">
						<tr>
							<td>${list.material_name}</td>
							<td>
								<c:if test="${list.position == '0'}">无</c:if>
								<c:if test="${list.position == '1'}">主编</c:if>
								<c:if test="${list.position == '2'}">副主编</c:if>
								<c:if test="${list.position == '3'}">编委</c:if>
							</td>
							<td>
								<c:if test="${list.is_digital_editor == '1'}">是</c:if>
								<c:if test="${list.is_digital_editor == '0'}">否</c:if>
							</td>
							<td>${list.publisher}</td>
							<td>${list.publishdate}</td>
							<td>${list.note}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--主编国家级规划教材情况-->
		<div class="sbxq_item1" id="zbgjjgh">
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
					<c:forEach var="list" items="${pergjghjcList}">
						<tr>
							<td>${list.material_name}</td>
							<td>${list.isbn}</td>
							<td>${list.rank_text}</td>
							<td>${list.note}</td>
						</tr>
					</c:forEach>	
					</tbody>
				</table>
			</div>
		</div>
		<!--人卫社教材编写情况表-->
		<div class="sbxq_item1" id="rwsjcbx">
			<div>
				<span id="tsxz_span7"></span>
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
						<c:forEach var="list" items="${perrwsjcList}" varStatus="status">
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
			<!--其他社教材编写情况-->
		<div class="sbxq_item1" id="qtjcbxqk">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">其他社教材编写情况</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_qtjcbxqk">
					<thead>
						<tr>
							<td width="230px">教材名称</td>
							<td width="120px">级别</td>
							<td width="120px">编写职务</td>
							<td width="100px">数字编委</td>
							<td width="130px">出版单位</td>
							<td width="120px">出版时间</td>
							<td width="120px">标准书号</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${perjcbxqtList}" varStatus="status">
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
								<td>${list.publisher}</td>
								<td>${list.publishdate}</td>
								<td>${list.isbn}</td>
								<td>${list.note}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--参加人卫慕课、数字教材编写情况-->
		<%--<div class="sbxq_item1" id="digital">
			<div>
				<span id="tsxz_span9"></span>
				<span class="tsxz_title">参加人卫慕课、数字教材编写情况</span>
			</div>
			<div class="content">
                <div class="text_dy">
                    ${digitalMap.content}
                </div>
			</div>
		</div>--%>
		<!--精品课程建设-->
		<div class="sbxq_item" id="gjjpkcjs">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">精品课程建设情况</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_jpkcjs">
					<thead>
					<tr>
						<td width="350px">课程名称</td>
						<td width="180px">全年课时</td>
						<td width="200px">课程级别</td>
						<td>备注</td>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${pergjkcjsList}">
						<tr>
							<td>${list.course_name}</td>
							<td>${list.class_hour}</td>
							<td>
								<c:if test="${list.type == '0'}">无</c:if>
								<c:if test="${list.type == '1'}">国际</c:if>
								<c:if test="${list.type == '2'}">国家</c:if>
								<c:if test="${list.type == '3'}">省部</c:if>
							</td>
							<td>${list.note}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--作家科研情况-->
		<div class="sbxq_item1" id="zjkyqk">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">科研情况</span>
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
					<c:forEach var="list" items="${perzjkyList}">
						<tr>
							<td>${list.research_name}</td>
							<td>${list.approval_unit}</td>
							<td>${list.award}</td>
							<td>${list.note}</td>
						</tr></c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--主编学术专著情况表-->
		<div class="sbxq_item1" id="zbxszz">
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
					<c:forEach var="list" items="${permonographList}">
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
		<!--出版行业获奖情况表-->
		<div class="sbxq_item1" id="publish">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">出版行业获奖情况</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_publish">
					<thead>
						<tr>
							<td width="340px">奖项名称</td>
							<td width="330px">评奖单位</td>
							<td width="150px">获奖时间</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${perpublishList}">
						<tr>
							<td>${list.reward_name}</td>
							<td>${list.award_unit}</td>
							<td>${list.rewarddate}</td>
							<td>${list.note}</td>
						</tr></c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--SCI论文投稿及影响因子情况表-->
		<div class="sbxq_item1" id="sci">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">SCI论文投稿及影响因子情况</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_sci">
					<thead>
						<tr>
							<td width="340px">论文名称</td>
							<td width="150px">期刊名称</td>
							<td width="200px">期刊SCI影响因子</td>
							<td width="130px">发表时间</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${persciList}">
						<tr>
							<td>${list.paper_name}</td>
							<td>${list.journal_name}</td>
							<td>${list.factor}</td>
							<td>${list.publishdate}</td>
							<td>${list.note}</td>
						</tr></c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--临床医学获奖情况表-->
		<div class="sbxq_item1" id="clinical">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">临床医学获奖情况</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_clinical">
					<thead>
						<tr>
							<td width="340px">奖项名称</td>
							<td width="180px">奖项级别</td>
							<td width="210px">获奖时间</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${perclinicalList}">
						<tr>
							<td>${list.reward_name}</td>
							<td>
								<c:if test="${list.award_unit == '0'}">无</c:if>
								<c:if test="${list.award_unit == '1'}">国际</c:if>
								<c:if test="${list.award_unit == '2'}">国家</c:if>
							</td>
							<td>${list.rewarddate}</td>
							<td>${list.note}</td>
						</tr></c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--学术荣誉授予情况表-->
		<div class="sbxq_item1" id="acade">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">学术荣誉授予情况</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_acade">
					<thead>
						<tr>
							<td width="340px">荣誉名称</td>
							<td width="280px">荣誉级别</td>
							<td width="180px">授予时间</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${peracadeList}">
						<tr>
							<td>${list.reward_name}</td>
							<td>
								<c:if test="${list.award_unit == '0'}">无</c:if>
								<c:if test="${list.award_unit == '1'}">国际</c:if>
								<c:if test="${list.award_unit == '2'}">国家</c:if>
								<c:if test="${list.award_unit == '3'}">省部</c:if>
								<c:if test="${list.award_unit == '4'}">市</c:if>
							</td>
							<td>${list.rewarddate}</td>
							<td>${list.note}</td>
						</tr></c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--编写内容意向表-->
	 	<%--<div class="sbxq_item1" id="intention">
			<div>
				<span id="tsxz_span9"></span>
				<span class="tsxz_title">编写内容意向表</span>
			</div>
			<div class="content">
				&lt;%&ndash;<textarea class="text_cl" readonly="readonly">${intentionMap.content}</textarea>&ndash;%&gt;
                <div class="text_dy">
                    ${intentionMap.content}
                </div>
			</div>
		</div>--%>
	</div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
