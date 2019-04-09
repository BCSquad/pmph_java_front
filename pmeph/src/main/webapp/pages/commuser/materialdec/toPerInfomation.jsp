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
	<link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}" type="text/css">
	<link rel="stylesheet" href="${ctx}/statics/css/jquery.tipso.css?t=${_timestamp}" type="text/css">
	<link rel="stylesheet" href="${ctx}/statics/css/jquery.calendar.css?t=${_timestamp}" type="text/css">
	<link rel="stylesheet" href="${ctx}/statics/materialdec/material.css?t=${_timestamp}" type="text/css">
	<link rel="stylesheet" href="${ctx}/statics/commuser/personalcenter/perInformation.css?t=${_timestamp}" type="text/css">
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js?t=${_timestamp}"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.jqprint-0.3.js?t=${_timestamp}"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.calendar.js?t=${_timestamp}"></script>

	<script src="http://www.jq22.com/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.tipso.js?t=${_timestamp}"></script>
<%--
<script type="text/javascript" src="${ctx}/resources/commuser/materialdec/materialdetail.js?t=${_timestamp}"></script>
--%>
	<script type="text/javascript" src="${ctx}/resources/commuser/personalcenter/perInfo.js?t=${_timestamp}"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
	<div class="content-wrapper">

		<%--<div class="sbxq_title">
			<span><a style="text-decoration: none;color: #999999;" href="${ctx}/personalhomepage/tohomepage.action?pagetag=dt">个人中心</a> ><a style="text-decoration: none;color: #999999;" href="${ctx}/medu/personalhomepage/tohomepage.action?pagetag=jcsb&pageNum=1&pageSize=10"> 教材申报 </a> > ${material.material_name}</span>
		</div>--%>
		<form id="objForm">
		<!-- 专家信息-->
			<div class="sbxq_item1">
				<div>
					<span id="tsxz_span2"></span>
					<span class="tsxz_title">专家信息</span>
				</div>
				<div class="content">
					<table class="tab_1">
						<tr>
							<td><span>姓&emsp;&emsp;名：</span>

										<input class="cg_input" name="realname" id="realname"  value="${userMap.realname}" validator = "isNonEmpty" message="姓名不能为空"
											   maxlength="20"/>


							<%--	<input class="cg_input" name="realname" id="realname" value="${userMap.realname}" validator = "isNonEmpty" message="姓名不能为空"
									   maxlength="20"/>--%>
								<input class="cg_input" name="user_id" type="hidden" value="${userMap.id}"/>
								<input class="cg_input" name="username" id="username" type="hidden"
									   value="${userMap.username}"/>
							</td>
							<td><span>性&emsp;&emsp;别：</span>


                                        <select class="select-input"  id="sex" name="sex">
                                            <option value="1" ${userMap.sex=='1'?'selected':'' }>男</option>
                                            <option value="2" ${userMap.sex=='2'?'selected':'' }>女</option>
                                        </select>



							</td>
							<td><span>出生年月：</span>

                                        <input class="cg_input" calendar format="'yyyy-mm-dd'" name="birthday"
                                               validator="isNonEmpty" message="出生日期不能为空"
                                               value="${userMap.birthday}" id="birthday"/>

                            </td>
							<td><span>教&emsp;&emsp;龄：</span>
								<input class="cg_input" name="experience" value="${userMap.experience}" id="experience"
									   onkeyup="this.value=this.value.replace(/\D/g,'')"
									   onafterpaste="this.value=this.value.replace(/\D/g,'')"
									   maxlength="2"
									   validator="isNonEmpty|onlyInt"
									   message="教龄不能为空|教龄必须是数字"
								/></td>
						</tr>
						<tr>
							<td><span>工作单位：</span>
								<input class="cg_input" name="org_name" value="${userMap.workplace}" id="org_name"
									   validator="isNonEmpty"
									   message="工作单位不能为空"
									   maxlength="36"/></td>
							<td><span>职&emsp;&emsp;务：</span>
								<select id="position" class="select-input"  name="position">
									<c:forEach items="${pmphPosition}" var="dic">
										<option value="${dic.code}" ${userMap.title == dic.code ? 'selected':''}>${dic.name}</option>
									</c:forEach>
								</select></td>
							<td><span>职&emsp;&emsp;称：</span>
								<select id="zclx" class="select-input" name="title">
									<c:forEach items="${writerUserTitle}" var="dic">
										<option value="${dic.code}" ${userMap.title == dic.code ? 'selected':''}>${dic.name}</option>
									</c:forEach>
								</select>
							</td>
							</td>
							<td><span style="width: 70px">E-mail：</span>
								<input class="cg_input" name="email" value="${userMap.email}" id="email"
									   validator="isNonEmpty|isEmail"
									   message="邮箱不能为空|邮箱格式不正确"
									   maxlength="40"/></td>
						</tr>
						<tr>
							<td><span>邮&emsp;&emsp;编：</span>
								<input class="cg_input" name="postcode" value="${userMap.postcode}" id="postcode"
									   onblur="LengthLimit(this,20)" maxlength="20"/>
							</td>
							<td><span>联系电话：</span>
								<input class="cg_input" name="telephone" value="${userMap.telephone}" id="telephone"
									   onblur="LengthLimit(this,30)" maxlength="30"/>
							</td>
							<td><span>传&emsp;&emsp;真：</span>
								<input class="cg_input" name="fax" value="${userMap.fax}" id="fax" maxlength="20"
									   onblur="LengthLimit(this,20)"/>
							</td>
							<td><span>手&emsp;&emsp;机：</span>
								<input class="cg_input" name="handphone" value="${userMap.handphone}" id="handphone"
									   validator="isNonEmpty|isMobile"
									   message="手机号码不能为空|手机号码格式不正确"
									   onblur="LengthLimit(this,30)" maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td><span>证件号码：</span>


                                        <input class="cg_input" name="idcard" value="${userMap.idcard}" id="idcard"
                                               validator="isNonEmpty|isCard"
                                               message="证件号码不能为空|请填写正确的证件号码"
                                               maxlength="18"/>

							</td>
							<td><span>学&emsp;&emsp;历：</span>
								<select id="degree" class="select-input" name="degree">
									<c:forEach items="${writerUserDegree}" var="dic">
										<option value="${dic.code}" ${userMap.education==dic.code?'selected':'' }>${dic.name}</option>
									</c:forEach>

								</select>
							</td>
							<td colspan="2"><span>地&emsp;&emsp;址：</span>
								<input class="cg_input" style="width: 488px;" name="address" value="${userMap.address}"
									   validator="isNonEmpty"
									   message="地址不能为空"
									   id="address" maxlength="50"/></td>
						</tr>
					</table>

				</div>
			</div>

			<div id="tables">
				<!--主要学习经历-->
				<div class="sbxq_item1" id="zyxxjl">
					<div>
						<span id="tsxz_span3"></span>
						<span class="tsxz_title">学习经历</span>

					</div>
					<div class="content">
						<table class="tab_3" id="tab_xxjl">
							<thead>
							<tr>
								<td width="220px">起止时间</td>
								<td width="260px">学校名称</td>
								<td width="210px">所学专业</td>
								<td width="138px">学历</td>
								<td>备注</td>
								<td width="78px">添加</td>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty perstuList[0]}">
								<tr>
									<td>
										<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'"
											   z-index="100" max="'$#xx_jssj'" name="xx_kssj" id="xx_kssj" value=""
											   validator="isNonEmpty"
											   message="学习开始时间必填"
											   style="width: 80px;" maxlength="20"/>
										-
										<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"
											   z-index="100" min="'$#xx_kssj'" name="xx_jssj" id="xx_jssj" value=""
											   validator="isNonEmpty"
											   message="学习结束时间必填"
											   style="width: 80px;" maxlength="20"/>
									</td>
									<td><input class="cg_input" id="xx_school_name" style="width: 230px"
											   validator="isNonEmpty"
											   message="学校名称必填"
											   name="xx_school_name" value="" placeholder="学校名称" maxlength="80"/></td>
									<td><input class="cg_input" id="xx_major" name="xx_major" value="" placeholder="所学专业"
											   validator="isNonEmpty"
											   maxlength="50"/></td>
									<td><input class="cg_input" id="xx_degree" name="xx_degree" value=""
											   validator="isNonEmpty"
											   style="width: 110px;" placeholder="学历" maxlength="30"/></td>
									<td><input class="cg_input" name="xx_note" value="" style="width: 240px;"
											   placeholder="备注" maxlength="100"/>
										<input type="hidden" name="xx_id" value="">
									</td>
									<td><img class="add_img" src="${ctx}/statics/image/add.png"
											 onclick="add_line(this)"/></td>
								</tr>
							</c:if>
							<c:forEach var="list" items="${perstuList}" varStatus="status">
								<tr id="xxjl_${status.count}">
									<td>
										<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'"
											   z-index="100" name="xx_kssj" max="'$#xx_jssj_${status.count}'"
											   id="xx_kssj_${status.count}" value="${list.date_begin}"
											   validator="isNonEmpty"
											   message="学习开始时间必填"
											   style="width: 80px;"/>
										-
										<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"
											   z-index="100" name="xx_jssj" min="'$#xx_kssj_${status.count}'"
											   validator="isNonEmpty"
											   message="学习结束时间必填"
											   id="xx_jssj_${status.count}" value="${list.date_end}" style="width: 80px;"/>
									</td>
									<td><input class="cg_input" style="width: 230px" name="xx_school_name"
											   id="xx_school_name_${status.count}" value="${list.school_name}"
											   validator="isNonEmpty"
											   placeholder="学校名称" maxlength="80"/></td>
									<td><input class="cg_input" name="xx_major" id="xx_major_${status.count}"
											   validator="isNonEmpty"
											   value="${list.major}" placeholder="所学专业" maxlength="50"/></td>
									<td><input class="cg_input" name="xx_degree" id="xx_degree_${status.count}"
											   value="${list.degree}" style="width: 110px;" placeholder="学历"
											   validator="isNonEmpty"
											   maxlength="30"/></td>
									<td><input class="cg_input" name="xx_note" value="${list.note}" style="width: 240px;"
											   placeholder="备注" maxlength="100"/>
										<input type="hidden" name="zdjy" value="xx_kssj_${status.count}"/>
										<input type="hidden" name="xx_id" value="${list.id}">
											<%--<input type="hidden" name="zdjy" value="xx_kssj_${status.count},xx_jssj_${status.count},xx_school_name_${status.count},xx_major_${status.count},xx_degree_${status.count}"/>--%>
									</td>
									<td>
										<c:choose>
											<%-- <c:when test="${status.count == fn:length(perstuList)}"> --%>
											<c:when test="${status.count == 1}">
												<img class="add_img" src="${ctx}/statics/image/add.png"
													 onclick="add_line(this)"/>
											</c:when>
											<c:otherwise>
												<img class="add_img" src="${ctx}/statics/image/del.png"
													 onclick="javascript:del_tr(this)"/>
											</c:otherwise>
										</c:choose>
									</td>
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
						<table class="tab_3" id="tab_gzjl">
							<thead>
							<tr>
								<td width="220px">起止时间</td>
								<td width="400px">工作单位</td>
								<td width="220px">职位</td>
								<td>备注</td>
								<td width="78px">添加</td>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty perworkList[0]}">
								<tr>
									<td>
										<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'"
											   z-index="100" name="gz_kssj" max="'$#gz_jssj'" id="gz_kssj" value=""
											   validator="isNonEmpty"
											   style="width: 80px;"/>
										-
										<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"
											   z-index="100" name="gz_jssj" min="'$#gz_kssj'" id="gz_jssj" value=""
											   validator="isNonEmpty"
											   style="width: 80px;"/>
									</td>
									<td><input class="cg_input" id="gz_org_name" style="width: 370px;" name="gz_org_name"
											   validator="isNonEmpty"
											   value="" placeholder="工作单位" maxlength="100"/></td>
									<td><input class="cg_input" id="gz_position" name="gz_position" value=""
											   placeholder="职位" maxlength="100"/></td>
									<td><input class="cg_input" name="gz_note" value="" style="width: 230px;"
											   placeholder="备注" maxlength="100"/>
										<input type="hidden" name="gz_id" value=""></td>
									<td><img class="add_img" src="${ctx}/statics/image/add.png"
											 onclick="add_line(this)"/></td>
								</tr>
							</c:if>
							<c:forEach var="list" items="${perworkList}" varStatus="status">
								<tr id="gzjl_${status.count}">
									<td>
										<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'"
											   validator="isNonEmpty"
											   z-index="100" name="gz_kssj" max="'$#gz_jssj_${status.count}'"
											   id="gz_kssj_${status.count}" value="${list.date_begin}"
											   style="width: 80px;"/>
										-
										<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"
											   validator="isNonEmpty"
											   z-index="100" name="gz_jssj" min="'$#gz_kssj_${status.count}'"
											   id="gz_jssj_${status.count}" value="${list.date_end}" style="width: 80px;"/>
									</td>
									<td><input class="cg_input" style="width: 370px;" name="gz_org_name"
											   id="gz_org_name_${status.count}" value="${list.org_name}" placeholder="工作单位"
											   validator="isNonEmpty"
											   maxlength="100"/></td>
									<td><input class="cg_input" name="gz_position" id="gz_position_${status.count}"
											   value="${list.position}" placeholder="职位" maxlength="100"/></td>
									<td><input class="cg_input" name="gz_note" value="${list.note}" style="width: 230px;"
											   placeholder="备注" maxlength="100"/>
										<input type="hidden" name="zdjy" value="gz_kssj_${status.count}"/>
										<input type="hidden" name="gz_id" value="${list.id}">
											<%--<input type="hidden" name="zdjy" value="gz_kssj_${status.count},gz_jssj_${status.count},gz_org_name_${status.count},gz_position_${status.count}"/>--%>
									</td>
									<td><c:choose>
										<c:when test="${status.count == 1}">
											<img class="add_img" src="${ctx}/statics/image/add.png"
												 onclick="add_line(this)"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png"
												 onclick="javascript:del_tr(this)"/>
										</c:otherwise>
									</c:choose></td>
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
						<table class="tab_3" id="tab_jxjz">
							<thead>
							<tr>
								<td width="220px">起止时间</td>
								<td width="350px">学校名称</td>
								<td width="320px">教学科目</td>
								<td>备注</td>
								<td width="78px">添加</td>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty persteaList[0]}">
								<tr>
									<td>
										<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'"
											   z-index="100" name="jx_kssj" max="'$#jx_jssj'" id="jx_kssj" value=""
											   validator="isNonEmpty"
											   style="width: 80px;"/>
										-
										<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"
											   z-index="100" name="jx_jssj" min="'#jx_kssj'" id="jx_jssj" value=""
											   validator="isNonEmpty"
											   style="width: 80px;"/>
									</td>
									<td><input class="cg_input" maxlength="100" style="width: 320px" id="jx_school_name"
											   validator="isNonEmpty"
											   name="jx_school_name" value="" placeholder="学校名称"/></td>
									<td><input class="cg_input" maxlength="150" style="width: 290px" id="jx_subject"
											   validator="isNonEmpty"
											   name="jx_subject" value="" placeholder="教学科目"/></td>
									<td><input class="cg_input" maxlength="100" name="jx_note" value=""
											   style="width: 180px;" placeholder="备注"/>
										<input type="hidden" name="jx_id" value=""></td>
									<td><img class="add_img" src="${ctx}/statics/image/add.png"
											 onclick="add_line(this)"/></td>
								</tr>
							</c:if>
							<c:forEach var="list" items="${persteaList}" varStatus="status">
								<tr id="jxjz_${status.count}">
									<td>
										<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'"
											   z-index="100" name="jx_kssj" max="'$#jx_jssj_${status.count}'"
											   validator="isNonEmpty"
											   id="jx_kssj_${status.count}" value="${list.date_begin}"
											   style="width: 80px;"/>
										-
										<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"
											   validator="isNonEmpty"
											   z-index="100" name="jx_jssj" min="'$#jx_kssj_${status.count}'"
											   id="jx_jssj_${status.count}" value="${list.date_end}" style="width: 80px;"/>
									</td>
									<td><input class="cg_input" maxlength="100" style="width: 320px" name="jx_school_name"
											   validator="isNonEmpty"
											   id="jx_school_name_${status.count}" value="${list.school_name}"
											   placeholder="学校名称"/></td>
									<td><input class="cg_input" maxlength="150" style="width: 290px" name="jx_subject"
											   id="jx_subject_${status.count}" value="${list.subject}" placeholder="教学科目"/>
									</td>
									<td><input class="cg_input" maxlength="100" name="jx_note" value="${list.note}"
											   style="width: 180px;" placeholder="备注"/>
										<input type="hidden" name="jx_id" value="${list.id}">
											<%-- <input type="hidden" name="zdjy" value="jx_kssj_${status.count},jx_jssj_${status.count},jx_school_name_${status.count},jx_subject_${status.count}"/>--%>
										<input type="hidden" name="zdjy" value="jx_kssj_${status.count}"/>
									</td>
									<td><c:choose>
										<c:when test="${status.count == 1}">
											<img class="add_img" src="${ctx}/statics/image/add.png"
												 onclick="add_line(this)"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png"
												 onclick="javascript:del_tr(this)"/>
										</c:otherwise>
									</c:choose></td>
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
						<textarea class="text_cl" maxlength="1000" name="gr_content" id="gr_content"
								  style="width: 100%;box-sizing: border-box;min-height: 7em;resize: vertical;margin-top: 0px;margin-bottom: 0px;opacity: 0.8;font-size: 1em;"
								  maxlength="1000">${achievementMap.content}</textarea>
					</div>
				</div>
				<!--主要学术兼职-->
				<div class="sbxq_item1" id="xsjz">
					<div>
						<span id="tsxz_span10"></span>
						<span class="tsxz_title">学术兼职</span>

						<span id="xsjz_xt" class="tsxz_xt">（选填）</span>
					</div>
					<div class="content">
						<table class="tab_3" id="tab_xsjz">
							<thead>
							<tr>
								<td width="400px">兼职学术组织</td>
								<td width="280px">级别</td>
								<td width="220px">职务</td>
								<td>备注</td>
								<td width="78px">添加</td>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty perzjxsList[0]}">
								<tr>
									<td><input class="cg_input" maxlength="100" style="width: 370px" name="xs_org_name"
											   validator="isNonEmpty"
											   id="xs_org_name" value="" placeholder="学术组织"/></td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 100%;">
											<tr>
												<td><input type="radio" name="xs_rank_a" value="0" checked="checked"/>无</td>
												<td><input type="radio" name="xs_rank_a" value="1"/>国际</td>
												<td><input type="radio" name="xs_rank_a" value="2"/>国家</td>
												<td><input type="radio" name="xs_rank_a" value="3"/>省部</td>
												<td><input type="radio" name="xs_rank_a" value="4"/>市级</td>
											</tr>
										</table>
										<input type="hidden" name="xs_rank" value="xs_rank_a"/>
									</td>
									<td><input class="cg_input" maxlength="50" id="xs_position" name="xs_position" value=""
											   placeholder="职务"/></td>
									<td><input class="cg_input" maxlength="100" name="xs_note" value=""
											   style="width: 180px;" placeholder="备注"/>
										<input type="hidden" name="xs_id" value=""></td>
									</td>
									<td><img class="add_img" src="${ctx}/statics/image/add.png"
											 onclick="add_line(this)"/></td>
								</tr>
							</c:if>
							<c:forEach var="list" items="${perzjxsList}" varStatus="status">
								<tr id="xsjz_${status.count}">
									<td><input class="cg_input" maxlength="100" style="width: 370px" name="xs_org_name"
											   validator="isNonEmpty"
											   id="xs_org_name_${status.count}" value="${list.org_name}"
											   placeholder="学术组织"/></td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 100%;">
											<tr>
												<td><input type="radio" name="xs_rank_${status.count}"
														   value="0" ${list.rank=='0'?'checked':'' }/>无
												</td>
												<td><input type="radio" name="xs_rank_${status.count}"
														   value="1" ${list.rank=='1'?'checked':'' }/>国际
												</td>
												<td><input type="radio" name="xs_rank_${status.count}"
														   value="2" ${list.rank=='2'?'checked':'' }/>国家
												</td>
												<td><input type="radio" name="xs_rank_${status.count}"
														   value="3" ${list.rank=='3'?'checked':'' }/>省部
												</td>
												<td><input type="radio" name="xs_rank_${status.count}"
														   value="4" ${list.rank=='4'?'checked':'' }/>其他
												</td>
											</tr>
										</table>
										<input type="hidden" name="xs_rank" value="xs_rank_${status.count}"/>
									</td>
									<td><input class="cg_input" maxlength="50" id="xs_position_${status.count}"
											   name="xs_position" value="${list.position}" placeholder="职务"/></td>
									<td><input class="cg_input" maxlength="100" name="xs_note" value="${list.note}"
											   style="width: 180px;" placeholder="备注"/>
										<input type="hidden" name="zdjy" value="xs_org_name_${status.count}"/>
										<input type="hidden" name="xs_id" value="${list.id}">
											<%--<input type="hidden" name="zdjy" value="xs_org_name_${status.count},xs_position_${status.count}"/>--%>
									</td>
									<td><c:choose>
										<c:when test="${status.count == 1}">
											<img class="add_img" src="${ctx}/statics/image/add.png"
												 onclick="add_line(this)"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png"
												 onclick="javascript:del_tr(this)"/>
										</c:otherwise>
									</c:choose></td>
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
						<table class="tab_3" id="tab_jccb">
							<thead>
							<tr>
								<td width="350px">教材名称</td>
								<td width="280px">编写职务</td>
								<td width="100px">数字编委</td>
								<td width="120px">出版社</td>
								<td width="120px">出版时间</td>
								<td>备注</td>
								<td width="78px">添加</td>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty perjcbjList[0]}">
								<tr>
									<td><input class="cg_input" maxlength="100" style="width: 320px" id="jc_material_name"
											   name="jc_material_name" id="jc_material_name" value="" style="width: 260px;"
											   validator="isNonEmpty"
											   placeholder="教材名称"/></td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 100%;">
											<tr>
												<td><input type="radio" name="jc_position_a" value="0" checked="checked"/>无
												</td>
												<td><input type="radio" name="jc_position_a" value="1"/>主编</td>
												<td><input type="radio" name="jc_position_a" value="2"/>副主编</td>
												<td><input type="radio" name="jc_position_a" value="3"/>编委</td>
											</tr>
										</table>
										<input type="hidden" name="jc_position" value="jc_position_a"/>
									</td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 80px;">
											<tr>
												<td><input type="radio" name="jc_is_digital_editor_a" value="1"/>是</td>
												<td><input type="radio" name="jc_is_digital_editor_a" value="0"
														   checked="checked"/>否
												</td>
											</tr>
										</table>
										<input type="hidden" name="jc_is_digital_editor" value="jc_is_digital_editor_a"/>
									</td>
									<td><%--<input class="cg_input" name="jc_publisher" value="人民卫生出版社" readonly="true" placeholder="出版社"
											   validator="isNonEmpty"
											   style="width: 100px;" maxlength="20"/>--%>
										人民卫生出版社
									</td>
									<td><input class="cg_input" name="jc_publish_date" id="jc_publish_date" value=""
											   placeholder="出版时间" calendar format="'yyyy-mm-dd'" z-index="100"
											   style="width: 100px;"/></td>
									<td><input class="cg_input" maxlength="100" name="jc_note" value=""
											   style="width: 100px;" placeholder="备注"/>
										<input type="hidden" name="jc_id" value=""></td>
									</td>
									<td><img class="add_img" src="${ctx}/statics/image/add.png"
											 onclick="add_line(this)"/></td>
								</tr>
							</c:if>
							<c:forEach var="list" items="${perjcbjList}" varStatus="status">
								<tr id="jccb_${status.count}">
									<td><input class="cg_input" maxlength="100" style="width: 320px" name="jc_material_name"
											   id="jc_material_name_${status.count}" value="${list.material_name}"
											   validator="isNonEmpty"
											   style="width: 260px;" placeholder="教材名称"/></td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 100%;">
											<tr>
												<td><input type="radio" name="jc_position_${status.count}"
														   value="0" ${list.position=='0'?'checked':'' }/>无
												</td>
												<td><input type="radio" name="jc_position_${status.count}"
														   value="1" ${list.position=='1'?'checked':'' }/>主编
												</td>
												<td><input type="radio" name="jc_position_${status.count}"
														   value="2" ${list.position=='2'?'checked':'' }/>副主编
												</td>
												<td><input type="radio" name="jc_position_${status.count}"
														   value="3" ${list.position=='3'?'checked':'' }/>编委
												</td>
											</tr>
										</table>
										<input type="hidden" name="jc_position" value="jc_position_${status.count}"/>
									</td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 80px;">
											<tr>
												<td><input type="radio" name="jc_is_digital_editor_${status.count}"
														   value="1"  ${list.is_digital_editor=='1'?'checked':'' } />是
												</td>
												<td><input type="radio" name="jc_is_digital_editor_${status.count}"
														   value="0"  ${list.is_digital_editor=='0'?'checked':'' }/>否
												</td>
											</tr>
										</table>
										<input type="hidden" name="jc_is_digital_editor"
											   value="jc_is_digital_editor_${status.count}"/>
									</td>
									<td>
										<%--<input class="cg_input" name="jc_publisher" value="人民卫生出版社" readonly="true" placeholder="出版社"
											   validator="isNonEmpty"
											   style="width: 100px;" maxlength="20"/>--%>
											人民卫生出版社
									</td>
									<td><input class="cg_input" name="jc_publish_date" id="jc_publish_date_${status.count}"
											   value="${list.publish_date}" placeholder="出版时间" calendar
											   format="'yyyy-mm-dd'" z-index="100" style="width: 100px;"/></td>
									<td><input class="cg_input" maxlength="100" name="jc_note" value="${list.note}"
											   style="width: 100px;" placeholder="备注"/>
										<input type="hidden" name="zdjy" value="jc_material_name_${status.count}"/>
										<input type="hidden" name="jc_id" value="${list.id}">
									</td>
									<td><c:choose>
										<c:when test="${status.count == 1}">
											<img class="add_img" src="${ctx}/statics/image/add.png"
												 onclick="add_line(this)"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png"
												 onclick="javascript:del_tr(this)"/>
										</c:otherwise>
									</c:choose></td>
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
						<table class="tab_3" id="tab_gjghjc">
							<thead>
							<tr>
								<td width="340px">教材名称</td>
								<td width="150px">标准书号</td>
								<td width="330px">教材级别</td>
								<td>备注</td>
								<td width="78px">添加</td>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty pergjghjcList[0]}">
								<tr>
									<td><input class="cg_input" maxlength="100" name="hj_material_name"
											   validator="isNonEmpty"
											   id="hj_material_name" value="" style="width: 300px;" placeholder="教材名称"/>
									</td>
									<td><input class="cg_input" maxlength="50" id="hj_isbn" name="hj_isbn" value=""
											   validator="isNonEmpty"
											   style="width: 110px;" placeholder="标准书号"/></td>
									<td><input class="cg_input" name="hj_rank_text" id="hj_rank_text" value=""
											   style="width: 300px;" placeholder="教材级别" maxlength="50"/></td>
									<td><input class="cg_input" maxlength="100" name="hj_note" value=""
											   style="width: 250px;" placeholder="备注"/>
										<input type="hidden" name="hj_id" value=""></td>
									</td>
									<td><img class="add_img" src="${ctx}/statics/image/add.png"
											 onclick="add_line(this)"/></td>
								</tr>
							</c:if>
							<c:forEach var="list" items="${pergjghjcList}" varStatus="status">
								<tr id="gjghjc_${status.count}">
									<td><input class="cg_input" maxlength="100" name="hj_material_name"
											   id="hj_material_name_${status.count}" value="${list.material_name}"
											   validator="isNonEmpty"
											   style="width: 300px;" placeholder="教材名称"/></td>
									<td><input class="cg_input" maxlength="50" name="hj_isbn" value="${list.isbn}"
											   validator="isNonEmpty"
											   id="hj_isbn_${status.count}" style="width: 110px;" placeholder="标准书号"/></td>
									<td><input class="cg_input" name="hj_rank_text" id="hj_rank_text_${status.count}"
											   value="${list.rank_text}" style="width: 300px;" placeholder="教材级别"
											   maxlength="50"/></td>
									<td><input class="cg_input" maxlength="100" name="hj_note" value="${list.note}"
											   style="width: 250px;" placeholder="备注"/>
										<input type="hidden" name="zdjy" value="hj_material_name_${status.count}"/>
										<input type="hidden" name="hj_id" value="${list.id}">
											<%--
												<input type="hidden" name="zdjy" value="hj_material_name_${status.count},hj_isbn_${status.count},hj_rank_text_${status.count}"/>
			--%>
									</td>
									<td><c:choose>
										<c:when test="${status.count == 1}">
											<img class="add_img" src="${ctx}/statics/image/add.png"
												 onclick="add_line(this)"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png"
												 onclick="javascript:del_tr(this)"/>
										</c:otherwise>
									</c:choose></td>
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
						<span class="tsxz_title">人卫社教材编写情况表</span>


					</div>
					<div class="content">
						<table class="tab_3" id="tab_rwsjcbx">
							<thead>
							<tr>
								<td width="350px">教材名称</td>
								<td width="120px">级别</td>
								<td width="120px">编写职务</td>
								<td width="100px">数字编委</td>
								<td width="120px">出版时间</td>
								<td width="120px">标准书号</td>
								<td>备注</td>
								<td width="78px">添加</td>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty perpmphList[0]}">
								<tr>
									<td><input class="cg_input" maxlength="100" style="width: 320px;"
											   validator="isNonEmpty"
											   name="pmph_material_name" id="pmph_material_name" value=""
											   style="width: 200px;" placeholder="教材名称"/></td>
									<td>
										<select id="pmph_rank" name="pmph_rank" class="select-input">
											<c:forEach items="${pmphRank}" var="dic">
												<option value="${dic.code}" >${dic.name}</option>
											</c:forEach>
												<%--<option value="0">无</option>
												<option value="1">国家</option>
												<option value="2">省部</option>
												<option value="3">协编</option>
												<option value="4">校本</option>
												<option value="5">其他</option>--%>
										</select>
									</td>
									<td>
										<select id="pmph_position" name="pmph_position" class="select-input">
											<c:forEach items="${pmphPosition}" var="dic">
												<option value="${dic.code}" >${dic.name}</option>
											</c:forEach>
												<%--<option value="0">无</option>
												<option value="1">主编</option>
												<option value="2">副主编</option>
												<option value="3">编委</option>--%>
										</select>
									</td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 80px;">
											<tr>
												<td><input type="radio" name="pmph_is_digital_editor_a" value="1"/>是</td>
												<td><input type="radio" name="pmph_is_digital_editor_a" value="0"
														   checked="checked"/>否
												</td>
											</tr>
										</table>
										<input type="hidden" name="pmph_is_digital_editor"
											   value="pmph_is_digital_editor_a"/>
									</td>
									<td><input class="cg_input" id="pmph_publish_date" name="pmph_publish_date"
											   placeholder="出版时间" calendar format="'yyyy-mm-dd'" z-index="100" value=""
											   style="width: 100px;"/></td>
									<td><input class="cg_input" maxlength="50" id="pmph_isbn" name="pmph_isbn" value=""
											   style="width: 100px;" placeholder="978-7-117-"/></td>
									<td><input class="cg_input" maxlength="100" id="pmph_note" name="pmph_note" value=""
											   placeholder="备注" style="width: 140px;"/>
										<input type="hidden" name="pmph_id" value=""></td>
									</td>
									<td><img class="add_img" src="${ctx}/statics/image/add.png"
											 onclick="add_line(this)"/></td>
								</tr>
							</c:if>
							<c:forEach var="list" items="${perpmphList}" varStatus="status">
								<tr id="rwsjcbx_${status.count}">
									<td><input class="cg_input" maxlength="100" style="width: 320px;"
											   name="pmph_material_name" id="pmph_material_name_${status.count}"
											   validator="isNonEmpty"
											   value="${list.material_name}" style="width: 200px;" placeholder="教材名称"/></td>
									<td>
										<select id="pmph_rank_${status.count}" name="pmph_rank" class="select-input">
											<c:forEach items="${pmphRank}" var="dic">
												<option value="${dic.code}" ${list.rank==dic.code?'selected':'' }>${dic.name}</option>
											</c:forEach>
												<%--<option value="0" ${list.rank=='0'?'selected':'' }>无</option>
												<option value="1" ${list.rank=='1'?'selected':'' }>国家</option>
												<option value="2" ${list.rank=='2'?'selected':'' }>省部</option>
												<option value="3" ${list.rank=='3'?'selected':'' }>协编</option>
												<option value="4" ${list.rank=='4'?'selected':'' }>校本</option>
												<option value="5" ${list.rank=='5'?'selected':'' }>其他</option>--%>
										</select>
										<input type="hidden" id="pmph_rank_sl" name="pmph_rank_sl"
											   value="pmph_rank_${status.count}"/>
									</td>
									<td>
										<select id="pmph_position_${status.count}" name="pmph_position" class="select-input">
											<c:forEach items="${pmphPosition}" var="dic">
												<option value="${dic.code}" ${list.position==dic.code?'selected':''}>${dic.name}</option>
											</c:forEach>
												<%--<option value="0" ${list.position=='0'?'selected':'' }>无</option>
												<option value="1" ${list.position=='1'?'selected':'' }>主编</option>
												<option value="2" ${list.position=='2'?'selected':'' }>副主编</option>
												<option value="3" ${list.position=='3'?'selected':'' }>编委</option>--%>
										</select>
										<input type="hidden" id="pmph_sl" name="pmph_sl"
											   value="pmph_position_${status.count}"/>
									</td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 80px;">
											<tr>
												<td><input type="radio" name="pmph_is_digital_editor_${status.count}"
														   value="1" ${list.is_digital_editor=='1'?'checked':'' } />是
												</td>
												<td><input type="radio" name="pmph_is_digital_editor_${status.count}"
														   value="0" ${list.is_digital_editor=='0'?'checked':'' }/>否
												</td>
											</tr>
										</table>
										<input type="hidden" name="pmph_is_digital_editor"
											   value="pmph_is_digital_editor_${status.count}"/>
									</td>
									<td><input class="cg_input" placeholder="出版时间" id="pmph_publish_date_${status.count}"
											   calendar format="'yyyy-mm-dd'" z-index="100" name="pmph_publish_date"
											   value="${list.publish_date}" style="width: 100px;"/></td>
									<td><input class="cg_input" name="pmph_isbn" id="pmph_isbn_${status.count}"
											   value="${list.isbn}" style="width: 100px;" placeholder="标准书号"
											   maxlength="50"/></td>
									<td><input class="cg_input" maxlength="100" name="pmph_note" value="${list.note}"
											   style="width: 140px;" placeholder="备注"/>
										<input type="hidden" name="zdjy" value="pmph_material_name_${status.count}"/>
										<input type="hidden" name="pmph_id" value="${list.id}">
											<%--<input type="hidden" name="zdjy" value="pmph_material_name_${status.count},pmph_publish_date_${status.count},pmph_isbn_${status.count}"/>--%>
									</td>
									<td><c:choose>
										<c:when test="${status.count == 1}">
											<img class="add_img" src="${ctx}/statics/image/add.png"
												 onclick="add_line(this)"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png"
												 onclick="javascript:del_tr(this)"/>
										</c:otherwise>
									</c:choose></td>
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
						<table class="tab_3" id="tab_qtjcbxqk">
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
								<td width="78px">添加</td>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty perjcbxList[0]}">
								<tr>
									<td><input class="cg_input" maxlength="100" name="jcb_material_name"
											   validator="isNonEmpty"
											   id="jcb_material_name" value="" style="width: 200px;" placeholder="教材名称"/>
									</td>
									<td>
										<select id="jcb_rank" name="jcb_rank" class="select-input">
											<c:forEach items="${pmphRank}" var="dic">
												<option value="${dic.code}">${dic.name}</option>
											</c:forEach>
												<%--<option value="0">无</option>
												<option value="1">国家</option>
												<option value="2">省部</option>
												<option value="3">协编</option>
												<option value="4">校本</option>
												<option value="5">其他</option>--%>
										</select>
									</td>
									<td>
										<select id="jcb_position" name="jcb_position" class="select-input">
											<c:forEach items="${pmphPosition}" var="dic">
												<option value="${dic.code}" >${dic.name}</option>
											</c:forEach>
												<%--<option value="0">无</option>
												<option value="1">主编</option>
												<option value="2">副主编</option>
												<option value="3">编委</option>--%>
										</select>
									</td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 100%;">
											<tr>
												<td><input type="radio" name="jcb_is_digital_editor_a" value="1"/>是</td>
												<td><input type="radio" name="jcb_is_digital_editor_a" value="0"
														   checked="checked"/>否
												</td>
											</tr>
										</table>
										<input type="hidden" name="jcb_is_digital_editor" value="jcb_is_digital_editor_a"/>
									</td>
									<td><input class="cg_input" id="jcb_publisher" maxlength="50" name="jcb_publisher"
											   validator="isNonEmpty"
											   value="" style="width: 100px;" placeholder="出版社"/></td>
									<td><input class="cg_input" id="jcb_publish_date" placeholder="出版时间" calendar
											   format="'yyyy-mm-dd'" z-index="100" name="jcb_publish_date" value=""
											   style="width: 100px;"/></td>
									<td><input class="cg_input" id="jcb_isbn" maxlength="50" name="jcb_isbn" id="jcb_isbn"
											   value="" style="width: 100px;" placeholder="978-7-"/></td>
									<td><input class="cg_input" maxlength="100" name="jcb_note" value="" placeholder="备注"
											   style="width: 130px;"/>
										<input type="hidden" name="jcb_id" value=""></td>
									</td>
									<td><img class="add_img" src="${ctx}/statics/image/add.png"
											 onclick="add_line(this)"/></td>
								</tr>
							</c:if>
							<c:forEach var="list" items="${perjcbxList}" varStatus="status">
								<tr id="jcbx_${status.count}">
									<td><input class="cg_input" name="jcb_material_name" maxlength="100"
											   id="jcb_material_name_${status.count}" value="${list.material_name}"
											   validator="isNonEmpty"
											   style="width: 200px;" placeholder="教材名称"/></td>
									<td>
										<select id="jcb_rank_${status.count}" name="jcb_rank" class="select-input">
											<c:forEach items="${pmphRank}" var="dic">
												<option value="${dic.code}" ${list.rank==dic.code?'selected':'' }>${dic.name}</option>
											</c:forEach>
												<%--<option value="0" ${list.rank=='0'?'selected':'' }>无</option>
												<option value="1" ${list.rank=='1'?'selected':'' }>国家</option>
												<option value="2" ${list.rank=='2'?'selected':'' }>省部</option>
												<option value="3" ${list.rank=='3'?'selected':'' }>协编</option>
												<option value="4" ${list.rank=='4'?'selected':'' }>校本</option>
												<option value="5" ${list.rank=='5'?'selected':'' }>其他</option>--%>
										</select>
										<input type="hidden" id="jcb_rank_sl" name="jcb_rank_sl"
											   value="jcb_rank_${status.count}"/>
									</td>
									<td>
										<select id="jcb_position_${status.count}" name="jcb_position" class="select-input">
											<c:forEach items="${pmphPosition}" var="dic">
												<option value="${dic.code}" ${list.position==dic.code?'selected':'' }>${dic.name}</option>
											</c:forEach>
												<%--<option value="0" ${list.position=='0'?'selected':'' }>无</option>
												<option value="1" ${list.position=='1'?'selected':'' }>主编</option>
												<option value="2" ${list.position=='2'?'selected':'' }>副主编</option>
												<option value="3" ${list.position=='3'?'selected':'' }>编委</option>--%>
										</select>
										<input type="hidden" id="jcjb_sl" name="jcjb_sl"
											   value="jcb_position_${status.count}"/>
									</td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 80px;">
											<tr>
												<td><input type="radio" name="jcb_is_digital_editor_${status.count}"
														   value="1" ${list.is_digital_editor=='1'?'checked':'' } />是
												</td>
												<td><input type="radio" name="jcb_is_digital_editor_${status.count}"
														   value="0" ${list.is_digital_editor=='0'?'checked':'' }/>否
												</td>
											</tr>
										</table>
										<input type="hidden" name="jcb_is_digital_editor"
											   value="jcb_is_digital_editor_${status.count}"/>
									</td>
									<td><input class="cg_input" maxlength="50" name="jcb_publisher"
											   id="jcb_publisher_${status.count}" value="${list.publisher}"
											   validator="isNonEmpty"
											   style="width: 100px;" placeholder="出版社"/></td>
									<td><input class="cg_input" placeholder="出版时间" id="jcb_publish_date_${status.count}"
											   calendar format="'yyyy-mm-dd'" z-index="100" name="jcb_publish_date"
											   value="${list.publish_date}" style="width: 100px;"/></td>
									<td><input class="cg_input" name="jcb_isbn" id="jcb_isbn_${status.count}"
											   value="${list.isbn}" style="width: 100px;" placeholder="标准书号"
											   maxlength="50"/></td>
									<td><input class="cg_input" maxlength="100" name="jcb_note" value="${list.note}"
											   style="width: 130px;" placeholder="备注"/>
										<input type="hidden" name="zdjy" value="jcb_material_name_${status.count}"/>
										<input type="hidden" name="jcb_id" value="${list.id}">
											<%--
																				<input type="hidden" name="zdjy" value="jcb_material_name_${status.count},jcb_publisher_${status.count},jcb_publish_date_${status.count},jcb_isbn_${status.count}"/>
											--%>
									</td>
									<td><c:choose>
										<c:when test="${status.count == 1}">
											<img class="add_img" src="${ctx}/statics/image/add.png"
												 onclick="add_line(this)"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png"
												 onclick="javascript:del_tr(this)"/>
										</c:otherwise>
									</c:choose></td>
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
						<textarea class="text_cl" name="mooc_content" id="mooc_content"
								  maxlength="1000">${digitalMap.content}</textarea>
					</div>
				</div>--%>
				<!--精品课程建设-->
				<div class="sbxq_item1" id="gjjpkcjs">
					<div>
						<span id="tsxz_span7"></span>
						<span class="tsxz_title">精品课程建设情况</span>


					</div>
					<div class="content">
						<table class="tab_3" id="tab_jpkcjs">
							<thead>
							<tr>
								<td width="450px">课程名称</td>
								<td width="180px">全年课时</td>
								<td width="200px">课程级别</td>
								<td>备注</td>
								<td width="78px">添加</td>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty pergjkcjsList[0]}">
								<tr>
									<td><input class="cg_input" maxlength="50" name="gj_course_name" id="gj_course_name"
											   validator="isNonEmpty"
											   value="" style="width: 420px;" placeholder="课程名称"/></td>
									<td><input class="cg_input" maxlength="50" id="gj_class_hour" name="gj_class_hour"
											   value="" style="width: 130px;" placeholder="课时数"/></td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 100%;">
											<tr>
												<td><input type="radio" name="gj_type_a" value="0" checked="checked"/>无</td>
												<td><input type="radio" name="gj_type_a" value="1"/>国际</td>
												<td><input type="radio" name="gj_type_a" value="2"/>国家</td>
												<td><input type="radio" name="gj_type_a" value="3"/>省部</td>
											</tr>
										</table>
										<input type="hidden" name="gj_type" value="gj_type_a"/>
									</td>
									<td><input class="cg_input" maxlength="100" name="gj_note" value=""
											   style="width: 240px;" placeholder="备注"/>
										<input type="hidden" name="gj_id" value=""></td>
									</td>
									<td><img class="add_img" src="${ctx}/statics/image/add.png"
											 onclick="javascript:add_line(this)('tab_jpkcjs',1)"/></td>
								</tr>
							</c:if>
							<c:forEach var="list" items="${pergjkcjsList}" varStatus="status">
								<tr id="jpkcjs_${status.count}">
									<td><input class="cg_input" maxlength="50" name="gj_course_name"
											   id="gj_course_name_${status.count}" value="${list.course_name}"
											   validator="isNonEmpty"
											   style="width: 420px;" placeholder="课程名称"/></td>
									<td><input class="cg_input" maxlength="50" name="gj_class_hour"
											   id="gj_class_hour_${status.count}" value="${list.class_hour}"
											   style="width: 130px;" placeholder="课时数"/></td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 100%;">
											<tr>
												<td><input type="radio" name="gj_type_${status.count}"
														   value="0" ${list.type=='0'?'checked':'' }/>无
												</td>
												<td><input type="radio" name="gj_type_${status.count}"
														   value="1" ${list.type=='1'?'checked':'' }/>国际
												</td>
												<td><input type="radio" name="gj_type_${status.count}"
														   value="2" ${list.type=='2'?'checked':'' }/>国家
												</td>
												<td><input type="radio" name="gj_type_${status.count}"
														   value="3" ${list.type=='3'?'checked':'' }/>省部
												</td>
											</tr>
										</table>
										<input type="hidden" name="gj_type" value="gj_type_${status.count}"/>
									</td>
									<td><input class="cg_input" maxlength="100" name="gj_note" value="${list.note}"
											   style="width: 240px;" placeholder="备注"/>
										<input type="hidden" name="zdjy" value="gj_course_name_${status.count}"/>
										<input type="hidden" name="gj_id" value="${list.id}">
											<%--  <input type="hidden" name="zdjy" value="gj_course_name_${status.count},gj_class_hour_${status.count}"/>--%>
									</td>
									<td><c:choose>
										<c:when test="${status.count == 1}">
											<img class="add_img" src="${ctx}/statics/image/add.png"
												 onclick="javascript:add_line(this)"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png"
												 onclick="javascript:del_tr(this)"/>
										</c:otherwise>
									</c:choose></td>
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
						<table class="tab_3" id="tab_zjky">
							<thead>
							<tr>
								<td width="330px">课题名称</td>
								<td width="330px">审批单位</td>
								<td width="330px">获奖情况</td>
								<td>备注</td>
								<td width="78px">添加</td>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty perzjkyList[0]}">
								<tr>
									<td><input class="cg_input" maxlength="150" name="zjk_research_name"
											   validator="isNonEmpty"
											   id="zjk_research_name" value="" style="width: 300px;" placeholder="课题名称"/>
									</td>
									<td><input class="cg_input" maxlength="100" id="zjk_approval_unit"
											   name="zjk_approval_unit" value="" style="width: 300px;" placeholder="审批单位"/>
									</td>
									<td><input class="cg_input" maxlength="100" id="zjk_award" name="zjk_award" value=""
											   style="width: 300px;" placeholder="获奖情况"/></td>
									<td><input class="cg_input" maxlength="100" name="zjk_note" value=""
											   style="width: 90px;" placeholder="备注"/>
										<input type="hidden" name="zjk_id" value=""></td>
									</td>
									<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="add_line(this)"></td>
								</tr>
							</c:if>
							<c:forEach var="list" items="${perzjkyList}" varStatus="status">
								<tr id="zjky_${status.count}">
									<td><input class="cg_input" maxlength="150" name="zjk_research_name"
											   validator="isNonEmpty"
											   id="zjk_research_name_${status.count}" value="${list.research_name}"
											   style="width: 300px;" placeholder="课题名称"/></td>
									<td><input class="cg_input" maxlength="100" name="zjk_approval_unit"
											   id="zjk_approval_unit_${status.count}" value="${list.approval_unit}"
											   style="width: 300px;" placeholder="审批单位"/></td>
									<td><input class="cg_input" maxlength="100" name="zjk_award"
											   id="zjk_award_${status.count}" value="${list.award}" style="width: 300px;"
											   placeholder="获奖情况"/></td>
									<td><input class="cg_input" maxlength="100" name="zjk_note" value="${list.note}"
											   style="width: 90px;" placeholder="备注"/>
										<input type="hidden" name="zdjy" value="zjk_research_name_${status.count}"/>
										<input type="hidden" name="zjk_id" value="${list.id}">
											<%--
																				<input type="hidden" name="zdjy" value="zjk_research_name_${status.count},zjk_approval_unit_${status.count},zjk_award_${status.count}"/>
											--%>
									</td>
									<td><c:choose>
										<c:when test="${status.count == 1}">
											<img class="add_img" src="${ctx}/statics/image/add.png"
												 onclick="add_line(this)"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png"
												 onclick="javascript:del_tr(this)"/>
										</c:otherwise>
									</c:choose></td>
								</tr>
							</c:forEach>
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
						<table class="tab_3" id="tab_zbxszz">
							<thead>
							<tr>
								<td width="230px">专著名称</td>
								<td width="150px">专著发表日期</td>
								<td width="140px">出版方式</td>
								<td width="200px">出版单位</td>
								<td width="150px">出版时间</td>
								<td>备注</td>
								<td width="78px">添加</td>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty permonographList[0]}">
								<tr>
									<td><input class="cg_input" name="zb_monograph_name" id="zb_monograph_name" value=""
											   validator="isNonEmpty"
											   style="width: 200px;" placeholder="专著名称" maxlength="50"/></td>
									<td><input class="cg_input" name="zb_monograph_date" id="zb_monograph_date" value=""
											   style="width: 120px;" calendar format="'yyyy-mm-dd'" placeholder="发表日期"/>
									</td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 140px;">
											<tr>
												<td><input type="radio" name="is_self_paid_a" value="0" checked="checked"/>公费
												</td>
												<td><input type="radio" name="is_self_paid_a" value="1"/>自费</td>
											</tr>
										</table>
										<input type="hidden" name="is_self_paid" value="is_self_paid_a"/>
									</td>
									<td><input class="cg_input" id="zb_publisher" name="zb_publisher" value=""
											   validator="isNonEmpty"
											   style="width: 180px;" placeholder="出版单位" maxlength="50"/></td>
									<td><input class="cg_input" id="zb_publish_date" name="zb_publish_date" value=""
											   style="width: 120px;" calendar format="'yyyy-mm-dd'" placeholder="出版时间"/>
									</td>
									<td><input class="cg_input" name="zb_note" value="" style="width: 200px;"
											   placeholder="备注" maxlength="100"/>
										<input type="hidden" name="zb_id" value=""></td>
									</td>
									<td><img class="add_img" src="${ctx}/statics/image/add.png"
											 onclick="add_line(this)"/></td>
								</tr>
							</c:if>
							<c:forEach var="list" items="${permonographList}" varStatus="status">
								<tr id="zbxszz_${status.count}">
									<td><input class="cg_input" name="zb_monograph_name"
											   validator="isNonEmpty"
											   id="zb_monograph_name_${status.count}" value="${list.monograph_name}"
											   style="width: 200px;" placeholder="专著名称" maxlength="50"/></td>
									<td><input class="cg_input" name="zb_monograph_date"
											   id="zb_monograph_date_${status.count}" value="${list.monograph_date}"
											   style="width: 120px;" calendar format="'yyyy-mm-dd'" placeholder="出版时间"/>
									</td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 140px;">
											<tr>
												<td><input type="radio" name="is_self_paid_${status.count}"
														   value="0" ${list.is_self_paid=='0'?'checked':'' }/>公费
												</td>
												<td><input type="radio" name="is_self_paid_${status.count}"
														   value="1"  ${list.is_self_paid=='1'?'checked':'' }/>自费
												</td>
											</tr>
										</table>
										<input type="hidden" name="is_self_paid" value="is_self_paid_${status.count}"/>
									</td>
									<td><input class="cg_input" name="zb_publisher" id="zb_publisher_${status.count}"
											   validator="isNonEmpty"
											   value="${list.publisher}" style="width: 180px;" placeholder="出版单位"
											   maxlength="50"/></td>
									<td><input class="cg_input" name="zb_publish_date" id="zb_publish_date_${status.count}"
											   value="${list.publish_date}" style="width: 120px;"
											   calendar format="'yyyy-mm-dd'" placeholder="出版时间"/></td>
									<td><input class="cg_input" name="zb_note" value="${list.note}" style="width: 200px;"
											   placeholder="备注" maxlength="100"/>
										<input type="hidden" name="zdjy" value="zb_monograph_name_${status.count}"/>
										<input type="hidden" name="zb_id" value="${list.id}">
											<%-- <input type="hidden" name="zdjy" value="zb_monograph_name_${status.count},zb_publisher_${status.count},zb_publish_date_${status.count}"/>--%>
									</td>
									<td><c:choose>
										<c:when test="${status.count == 1}">
											<img class="add_img" src="${ctx}/statics/image/add.png"
												 onclick="add_line(this)"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png"
												 onclick="javascript:del_tr(this)"/>
										</c:otherwise>
									</c:choose></td>
								</tr>
							</c:forEach>
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
						<table class="tab_3" id="tab_publish">
							<thead>
							<tr>
								<td width="340px">奖项名称</td>
								<td width="330px">评奖组织</td>
								<td width="150px">获奖时间</td>
								<td>备注</td>
								<td width="78px">添加</td>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty perpublishList[0]}">
								<tr>
									<td><input class="cg_input" name="pu_reward_name" id="pu_reward_name" value=""
											   validator="isNonEmpty"
											   style="width: 300px;" placeholder="奖项名称" maxlength="50"/></td>
									<td><input class="cg_input" name="pu_award_unit" id="pu_award_unit" value=""
											   validator="isNonEmpty"
											   style="width: 300px;" placeholder="评奖单位" maxlength="50"/></td>
									<td>
										<input class="cg_input" name="pu_reward_date" id="pu_reward_date" value=""
											   style="width: 120px;" calendar format="'yyyy-mm-dd'" placeholder="获奖时间"/>
									</td>
									<td><input class="cg_input" name="pu_note" value="" style="width: 250px;"
											   placeholder="备注" maxlength="100"/>
										<input type="hidden" name="pu_id" value=""></td>
									</td>
									<td><img class="add_img" src="${ctx}/statics/image/add.png"
											 onclick="add_line(this)"/></td>
								</tr>
							</c:if>
							<c:forEach var="list" items="${perpublishList}" varStatus="status">
								<tr id="publish_${status.count}">
									<td><input class="cg_input" name="pu_reward_name" id="pu_reward_name_${status.count}"
											   validator="isNonEmpty"
											   value="${list.reward_name}" style="width: 300px;" placeholder="奖项名称"
											   maxlength="50"/></td>
									<td><input class="cg_input" name="pu_award_unit" id="pu_award_unit_${status.count}"
											   validator="isNonEmpty"
											   value="${list.award_unit}" style="width: 300px;" placeholder="评奖单位"
											   maxlength="50"/></td>
									<td>
										<input class="cg_input" name="pu_reward_date" id="pu_reward_date_${status.count}"
											   value="${list.reward_date}" style="width: 120px;" calendar
											   format="'yyyy-mm-dd'" placeholder="获奖时间"/>
									</td>
									<td><input class="cg_input" name="pu_note" value="${list.note}" style="width: 250px;"
											   placeholder="备注" maxlength="100"/>
										<input type="hidden" name="zdjy" value="pu_reward_name_${status.count}"/>
										<input type="hidden" name="pu_id" value="${list.id}">
											<%--<input type="hidden" name="zdjy" value="pu_reward_name_${status.count},pu_award_unit_${status.count},pu_reward_date_${status.count}"/>--%>
									</td>
									<td><c:choose>
										<c:when test="${status.count == 1}">
											<img class="add_img" src="${ctx}/statics/image/add.png"
												 onclick="add_line(this)"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png"
												 onclick="javascript:del_tr(this)"/>
										</c:otherwise>
									</c:choose></td>
								</tr>
							</c:forEach>
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
						<table class="tab_3" id="tab_sci">
							<thead>
							<tr>
								<td width="440px">论文名称</td>
								<td width="150px">期刊名称</td>
								<td width="200px">期刊SCI影响因子</td>
								<td width="130px">发表时间</td>
								<td>备注</td>
								<td width="78px">添加</td>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty persciList[0]}">
								<tr>
									<td><input class="cg_input" name="sci_paper_name" id="sci_paper_name" value=""
											   validator="isNonEmpty"
											   style="width: 410px;" placeholder="论文名称" maxlength="100"/></td>
									<td><input class="cg_input" name="sci_journal_name" id="sci_journal_name" value=""
											   validator="isNonEmpty"
											   style="width: 130px;" placeholder="期刊名称" maxlength="50"/></td>
									<td><input class="cg_input" name="sci_factor" id="sci_factor" value=""
											   style="width: 170px;" placeholder="期刊SCI影响因子" maxlength="20"/></td>
									<td><input class="cg_input" name="sci_publish_date" id="sci_publish_date" value=""
											   style="width: 110px;" calendar format="'yyyy-mm-dd'" placeholder="发表时间"/>
									</td>
									<td><input class="cg_input" name="sci_note" value="" style="width: 140px;"
											   placeholder="备注" maxlength="100"/>
										<input type="hidden" name="sci_id" value=""></td>
									</td>
									<td><img class="add_img" src="${ctx}/statics/image/add.png"
											 onclick="add_line(this)"/></td>
								</tr>
							</c:if>
							<c:forEach var="list" items="${persciList}" varStatus="status">
								<tr id="sci_${status.count}">
									<td><input class="cg_input" name="sci_paper_name" id="sci_paper_name_${status.count}"
											   validator="isNonEmpty"
											   value="${list.paper_name}" style="width: 410px;" placeholder="论文名称"
											   maxlength="100"/></td>
									<td><input class="cg_input" name="sci_journal_name"
											   id="sci_journal_name_${status.count}" value="${list.journal_name}"
											   validator="isNonEmpty"
											   style="width: 130px;" placeholder="期刊名称" maxlength="50"/></td>
									<td><input class="cg_input" name="sci_factor" id="sci_factor_${status.count}"
											   value="${list.factor}" style="width: 170px;" placeholder="期刊SCI影响因子"
											   maxlength="20"/></td>
									<td><input class="cg_input" name="sci_publish_date"
											   id="sci_publish_date_${status.count}" value="${list.publish_date}"
											   style="width: 110px;" calendar format="'yyyy-mm-dd'" placeholder="发表时间"/>
									</td>
									<td><input class="cg_input" name="sci_note" value="${list.note}" style="width: 140px;"
											   placeholder="备注" maxlength="100"/>
										<input type="hidden" name="zdjy" value="sci_paper_name_${status.count}"/>
										<input type="hidden" name="sci_id" value="${list.id}">
											<%--   <input type="hidden" name="zdjy" value="sci_paper_name_${status.count},sci_journal_name_${status.count},sci_factor_${status.count},sci_publish_date_${status.count}"/>--%>
									</td>
									<td><c:choose>
										<c:when test="${status.count == 1}">
											<img class="add_img" src="${ctx}/statics/image/add.png"
												 onclick="add_line(this)"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png"
												 onclick="javascript:del_tr(this)"/>
										</c:otherwise>
									</c:choose></td>
								</tr>
							</c:forEach>
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
						<table class="tab_3" id="tab_clinical">
							<thead>
							<tr>
								<td width="440px">奖项名称</td>
								<td width="180px">奖项级别</td>
								<td width="210px">获奖时间</td>
								<td>备注</td>
								<td width="78px">添加</td>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty perclinicalList[0]}">
								<tr>
									<td><input class="cg_input" name="cl_reward_name" maxlength="50" id="cl_reward_name"
											   validator="isNonEmpty"
											   value="" style="width: 410px;" placeholder="奖项名称"/></td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 180px;">
											<tr>
												<td><input type="radio" name="cl_award_unit_a" value="0" checked="checked"/>无
												</td>
												<td><input type="radio" name="cl_award_unit_a" value="1"/>国际</td>
												<td><input type="radio" name="cl_award_unit_a" value="2"/>国家</td>
											</tr>
										</table>
										<input type="hidden" name="cl_award_unit" value="cl_award_unit_a"/>
									</td>
									<td><input class="cg_input" id="cl_reward_date" name="cl_reward_date" value=""
											   style="width: 180px;" calendar format="'yyyy-mm-dd'" placeholder="获奖时间"/>
									</td>
									<td><input class="cg_input" name="cl_note" value="" style="width: 230px;"
											   placeholder="备注" maxlength="100"/>
										<input type="hidden" name="cl_id" value=""></td>
									</td>
									<td><img class="add_img" src="${ctx}/statics/image/add.png"
											 onclick="add_line(this)"/></td>
								</tr>
							</c:if>
							<c:forEach var="list" items="${perclinicalList}" varStatus="status">
								<tr id="clinical_${status.count}">
									<td><input class="cg_input" name="cl_reward_name" maxlength="50"
											   id="cl_reward_name_${status.count}" value="${list.reward_name}"
											   validator="isNonEmpty"
											   style="width: 410px;" placeholder="奖项名称"/></td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width: 180px;">
											<tr>
												<td><input type="radio"
														   name="cl_award_unit_${status.count}" ${list.award_unit=='0'?'checked':'' }
														   value="0"/>无
												</td>
												<td><input type="radio"
														   name="cl_award_unit_${status.count}" ${list.award_unit=='1'?'checked':'' }
														   value="1"/>国际
												</td>
												<td><input type="radio"
														   name="cl_award_unit_${status.count}" ${list.award_unit=='2'?'checked':'' }
														   value="2"/>国家
												</td>
											</tr>
										</table>
										<input type="hidden" name="cl_award_unit" value="cl_award_unit_${status.count}"/>
									</td>
									<td><input class="cg_input" name="cl_reward_date" id="cl_reward_date_${status.count}"
											   value="${list.reward_date}" style="width: 180px;" calendar
											   format="'yyyy-mm-dd'" placeholder="获奖时间"/></td>
									<td><input class="cg_input" name="cl_note" value="${list.note}" style="width: 230px;"
											   placeholder="备注" maxlength="100"/>
										<input type="hidden" name="zdjy" value="cl_reward_name_${status.count}"/>
										<input type="hidden" name="cl_id" value="${list.id}">
											<%--   <input type="hidden" name="zdjy" value="cl_reward_name_${status.count},cl_reward_date_${status.count}"/>--%>
									</td>
									<td><c:choose>
										<c:when test="${status.count == 1}">
											<img class="add_img" src="${ctx}/statics/image/add.png"
												 onclick="add_line(this)"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png"
												 onclick="javascript:del_tr(this)"/>
										</c:otherwise>
									</c:choose></td>
								</tr>
							</c:forEach>
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
						<table class="tab_3" id="tab_acade">
							<thead>
							<tr>
								<td width="440px">荣誉名称</td>
								<td width="280px">荣誉级别</td>
								<td width="180px">授予时间</td>
								<td>备注</td>
								<td width="78px">添加</td>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty peracadeList[0]}">
								<tr>
									<td><input class="cg_input" name="ac_reward_name" maxlength="50" id="ac_reward_name"
											   validator="isNonEmpty"
											   value="" style="width:410px;" placeholder="荣誉名称"/></td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width:280px;">
											<tr>
												<td><input type="radio" name="ac_award_unit_a" value="0" checked="checked"/>无
												</td>
												<td><input type="radio" name="ac_award_unit_a" value="1"/>国际</td>
												<td><input type="radio" name="ac_award_unit_a" value="2"/>国家</td>
												<td><input type="radio" name="ac_award_unit_a" value="3"/>省部</td>
												<td><input type="radio" name="ac_award_unit_a" value="4"/>市级</td>
											</tr>
										</table>
										<input type="hidden" name="ac_award_unit" value="ac_award_unit_a"/>
									</td>
									<td><input class="cg_input" id="ac_reward_date" name="ac_reward_date" value=""
											   style="width: 150px;" calendar format="'yyyy-mm-dd'" placeholder="授予时间"/>
									</td>
									<td><input class="cg_input" name="ac_note" value="" style="width: 180px;"
											   placeholder="备注" maxlength="100"/>
										<input type="hidden" name="ac_id" value=""></td>
									</td>
									<td><img class="add_img" src="${ctx}/statics/image/add.png"
											 onclick="add_line(this)"/></td>
								</tr>
							</c:if>
							<c:forEach var="list" items="${peracadeList}" varStatus="status">
								<tr id="acade_${status.count}">
									<td><input class="cg_input" name="ac_reward_name" maxlength="50"
											   id="ac_reward_name_${status.count}" value="${list.reward_name}"
											   validator="isNonEmpty"
											   style="width: 410px;" placeholder="荣誉名称"/></td>
									<td style="color: #333333;">
										<table class="radio_tb" style="width:280px;">
											<tr>
												<td><input type="radio" name="ac_award_unit_${status.count}"
														   value="0" ${list.award_unit=='0'?'checked':'' }/>无
												</td>
												<td><input type="radio" name="ac_award_unit_${status.count}"
														   value="1" ${list.award_unit=='1'?'checked':'' }/>国际
												</td>
												<td><input type="radio" name="ac_award_unit_${status.count}"
														   value="2" ${list.award_unit=='2'?'checked':'' }/>国家
												</td>
												<td><input type="radio" name="ac_award_unit_${status.count}"
														   value="3" ${list.award_unit=='3'?'checked':'' }/>省部
												</td>
												<td><input type="radio" name="ac_award_unit_${status.count}"
														   value="4" ${list.award_unit=='4'?'checked':'' }/>市级
												</td>
											</tr>
										</table>
										<input type="hidden" name="ac_award_unit" value="ac_award_unit_${status.count}"/>
									</td>
									<td><input class="cg_input" name="ac_reward_date" id="ac_reward_date_${status.count}"
											   value="${list.reward_date }" style="width: 150px;" calendar
											   format="'yyyy-mm-dd'" placeholder="授予时间"/></td>
									<td><input class="cg_input" name="ac_note" value="${list.note }" style="width: 180px;"
											   placeholder="备注" maxlength="100"/>
										<input type="hidden" name="zdjy" value="ac_reward_name_${status.count}"/>
										<input type="hidden" name="ac_id" value="${list.id}">
											<%-- <input type="hidden" name="zdjy" value="ac_reward_name_${status.count},ac_reward_date_${status.count}"/>--%>
									</td>
									<td><c:choose>
										<c:when test="${status.count == 1}">
											<img class="add_img" src="${ctx}/statics/image/add.png"
												 onclick="add_line(this)"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png"
												 onclick="javascript:del_tr(this)"/>
										</c:otherwise>
									</c:choose></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="button" style="width: 100%;">
				<div class="div_butt" style="height: 30px;">
					<div class="bt_tj" id="butj" onclick="javascript:btnSave()">保存</div>
					<div class="bt_tj"  onclick="javascript:history.go(-1)">返回</div>
					<%--<div class="bt_tj" onclick="javascript:buttGive()">放弃</div>--%>
				</div>
			</div>
			<div style="color: red;font-size: 16px;margin-top: 15px;text-align: center">（提示：如暂存或提交不成功请使用360浏览器极速模式或谷歌浏览器，请使用本人账号登录进行申报，否则可能会影响遴选结果）</div>
		</form>

	</div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
