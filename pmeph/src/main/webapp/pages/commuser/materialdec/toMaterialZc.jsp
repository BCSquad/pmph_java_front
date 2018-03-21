<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
 <script type="text/javascript">
           var contextpath = '${pageContext.request.contextPath}/';
  </script>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>申报表修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/materialdec/materialadd.css" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/css/jquery.calendar.css" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css" type="text/css">
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery-validate.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.calendar.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/base.js"></script>
<script src="${ctx}/resources/comm/jquery/jquery.fileupload.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/commuser/materialdec/materialZc.js"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
	<div class="content-wrapper">
		<div class="sbxq_title">
			<span><a style="text-decoration: none;color: #999999;" href="${contextpath}/personalhomepage/tohomepage.action?pagetag=dt">个人中心</a> ><a style="text-decoration: none;color: #999999;" href="${contextpath}/personalhomepage/tohomepage.action?pagetag=jcsb&pageNum=1&pageSize=10"> 教材申报 </a> > 修改申报表</span>
		</div>
		<!-- 图书选择-->
		<form id="objForm">
		<div class="sbxq_item1" id="tsxz">
			<div>
				<input type="hidden" id="select_nr" value="${bookSelects}"/>
				<input type="hidden" id="material_id" name="material_id" value="${materialMap.material_id}"/>
				<input type="hidden" id="declaration_id" name="declaration_id" value="${materialMap.declaration_id}"/>
				<input type="hidden" id="online_progress" name="online_progress" value="${gezlList.online_progress}"/>
				<!-- 是否编委 -->
				<input type="hidden" id="sfbw" name="sfbw" value="${materialMap.is_digital_editor_optional}"/>
				<!-- 是否书籍多选 -->
				<input type="hidden" id="is_multi_books" name="is_multi_books" value="${materialMap.is_multi_books}"/>
				<!-- 是否职位多选 -->
				<input type="hidden" id="is_multi_position" name="is_multi_position" value="${materialMap.is_multi_position}"/>
				<span id="tsxz_span1"></span>
				<span class="tsxz_title">作家申报职位选择(
					<c:if test="${materialMap.is_multi_books =='1'}">
					可以选择多本书籍</c:if>
					<c:if test="${materialMap.is_multi_books !='1'}">
					只能选择一本书籍</c:if>
					<c:if test="${materialMap.is_multi_position =='1'}">
					，每本书籍可选多个职位</c:if>
					<c:if test="${materialMap.is_multi_position !='1'}">
					，每本书籍只能选择一个职位。
					</c:if>
					)
				</span>
				<span class="tsxz_ts1"><img src="${ctx}/statics/image/btxx.png" /></span>
				<c:if test="${materialMap.is_multi_books =='1'}">
				<div class="addBtn pull-right" id="sjtj" onclick="javascript:addTsxz()"><span>增加</span></div>
				</c:if>
			</div>
			<c:forEach var="list" items="${tssbList}" varStatus="status">
				<div class="item" id="xz1">
					<span style="float: left;line-height: 30px;">图书：</span>
					<select id="edu_${status.count}" name="textbook_id" class="st" data-valid="isNonEmpty" data-error="书籍选择不能为空" style="float: left;">
					    	${list.bookSelect}
					</select>
					<input type="hidden" name="sjxz" value="edu_${status.count}" />
					<div style="float: left;margin-left: 30px;" class="ts_radio">
						<table style="width: 280px;border:0" cellspacing="0" cellpadding="0">
							<tr>
								<c:if test="${materialMap.is_multi_position =='1'}">
									<td height="30px;"><input type="checkbox" name="zw_1_${status.count}" value="4" ${list.pos_a=='1'?'checked':'' }/>主编</td>
									<td><input type="checkbox" name="zw_1_${status.count}" value="2" ${list.pos_b=='1'?'checked':'' }/>副主编</td>
									<td><input type="checkbox" name="zw_1_${status.count}" value="1" ${list.pos_c=='1'?'checked':'' }/>编委</td>
									<c:if test="${materialMap.is_digital_editor_optional =='1'}">
									<td><input type="checkbox" name="zw_1_${status.count}" value="8" ${list.pos_d=='1'?'checked':'' }/>数字编委</td>
									</c:if>
								</c:if>
								<c:if test="${materialMap.is_multi_position !='1'}">
									<td height="30px;"><input type="radio" name="zw_1_${status.count}" value="4" ${list.preset_position=='4'?'checked':'' }/>主编</td>
									<td><input type="radio" name="zw_1_${status.count}" value="2" ${list.preset_position=='2'?'checked':'' }/>副主编</td>
									<td><input type="radio" name="zw_1_${status.count}" value="1" ${list.preset_position=='1'?'checked':'' }/>编委</td>
									<c:if test="${materialMap.is_digital_editor_optional =='1'}">
									<td><input type="radio" name="zw_1_${status.count}" value="8" ${list.preset_position=='8'?'checked':'' }/>数字编委</td>
									</c:if>
								</c:if>	
							</tr>
						</table>
						<!-- 用于遍历radio中的值 -->
						<input type="hidden" name="preset_position" value="zw_1_${status.count}">
					</div>
					<div style="float: left;margin-left: 30px;height: 30px;">
						<span style="float: left;line-height: 30px;">上传教学大纲：</span>
						<div id="fileNameDiv_${status.count}" class="fileNameDiv"></div>
						<input type="hidden" name="syllabus_id" id="syllabus_id_${status.count}" value="${list.syllabus_id}"/>
						<input type="hidden" name="syllabus_name" id="syllabus_name_${status.count}" value="${list.syllabus_name}"/>
						<span style="float: left;margin-right: 10px;" id="fjxq_${status.count}">
							<a href="javascript:" class="filename"  onclick="downLoadProxy('${list.syllabus_id}')">${list.syllabus_name}</a>
						</span>
						<div class="scys" id="scjxdg_${status.count}"><span>上传文件</span></div>
						<input type="hidden" name="scfjb" id="scfjb" value="${status.count}">
					</div>
				</div>
			</c:forEach>	
		</div>
		<!-- 专家信息-->
		<div class="sbxq_item1">
			<div>
				<span id="tsxz_span2"></span>
				<span class="tsxz_title">专家信息</span>
				<span class="tsxz_ts1"><img src="${ctx}/statics/image/btxx.png" /></span>
			</div>
			<div class="content">
				<table class="tab_1">
					<tr>
						<td><span class="btbs">*</span><span>姓&emsp;&emsp;名：</span>
							<input class="cg_input" name="realname" id="realname" value="${gezlList.realname}" maxlength="6"/>
							<input class="cg_input" name="user_id" type="hidden" value="${userMap.id}" />
							</td>
						<td><span class="btbs">*</span><span>性&emsp;&emsp;别：</span>
                            <select class="select-input" id="sex" name="sex">
                                <option value="0" ${gezlList.sex=='0'?'selected':'' }>保密</option>
                                <option value="1" ${gezlList.sex=='1'?'selected':'' }>男</option>
                                <option value="2" ${gezlList.sex=='2'?'selected':'' }>女</option>
                            </select></td>
						<td><span class="btbs">*</span><span>出生年月：</span>
							<input class="cg_input" calendar format="'yyyy-mm-dd'"  name="birthday" value="${gezlList.birthday}"  id="birthday" /></td>
						<td><span class="btbs">*</span><span>教&emsp;&emsp;龄：</span>
							<input class="cg_input" name="experience" value="${gezlList.experience}" id="experience" 
							 onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
							 maxlength="2"
							/></td>
					</tr>
					<tr>
						<td><span class="btbs">*</span><span>工作单位：</span>
							<input class="cg_input" name="org_name" value="${gezlList.org_name}" id="org_name"  maxlength="20"/></td>
						<td><span class="btbs">*</span><span>职&emsp;&emsp;务：</span>
							<input class="cg_input" name="position" value="${gezlList.position}" id="position"  maxlength="12"/></td>
						<td><span class="btbs">*</span><span>职&emsp;&emsp;称：</span>
								<select id="zclx" name="title">
	                                <option value="教授"  ${gezlList.title=='教授'?'selected':'' }>教授</option>
	                                <option value="讲师" ${gezlList.title=='讲师'?'selected':'' }>讲师</option>
	                                <option value="高级讲师" ${gezlList.title=='高级讲师'?'selected':'' }>高级讲师</option>
	                                <option value="副教" ${gezlList.title=='副教'?'selected':'' }>副教授</option>
	                                <option value="院士" ${gezlList.title=='院士'?'selected':'' }>院士</option>
	                                <option value="其他" ${gezlList.title=='其他'?'selected':'' }>其他</option>
	                            </select>
							</td>
                        </td>
						<td><span class="btbs">*</span><span style="width: 70px">E-mail：</span>
							<input class="cg_input" name="email" value="${gezlList.email}" id="email" maxlength="40"/></td>
					</tr>
					<tr>
						<td><span>&ensp;邮&emsp;&emsp;编：</span>
							<input class="cg_input" name="postcode" value="${gezlList.postcode}" id="postcode" onblur="LengthLimit(this,20)" maxlength="20"/>
						</td>
						<td><span>&ensp;联系电话：</span>
							<input class="cg_input" name="telephone" value="${gezlList.telephone}" id="telephone" onblur="LengthLimit(this,20)" maxlength="13"/>
						</td>
						<td><span>&ensp;传&emsp;&emsp;真：</span>
							<input class="cg_input" name="fax" value="${gezlList.fax}" id="fax" maxlength="45" onblur="LengthLimit(this,45)"/>
						</td>
						<td><span class="btbs">*</span><span>手&emsp;&emsp;机：</span>
							<input class="cg_input" name="handphone" value="${gezlList.handphone}" id="handphone" onblur="LengthLimit(this,15)"  maxlength="11"/>
						</td>
					</tr>
					<tr>
						<td><span class="btbs">*</span><span>证件类型：</span>
                            <select class="select-input" id="zjlx" name="idtype">
                                <option value="0" ${gezlList.idtype=='0'?'selected':'' }>身份证</option>
                                <option value="1" ${gezlList.idtype=='1'?'selected':'' }>护照</option>
                                <option value="2" ${gezlList.idtype=='2'?'selected':'' }>军官证</option>
                            </select></td>
						<td><span class="btbs">*</span><span>证件号码：</span>
							<input class="cg_input" name="idcard" value="${gezlList.idcard}" id="idcard" maxlength="18"/></td>
						<td colspan="2"><span class="btbs">*</span><span>地&emsp;&emsp;址：</span>
							<input class="cg_input" style="width: 488px;" name="address" value="${gezlList.address}" id="address"  maxlength="17"/></td>
					</tr>
					<tr>
						<td><span>&ensp;服从调剂：</span>
							<input type="radio" name="is_dispensed" value="1" ${gezlList.is_dispensed=='1'?'checked':'' }/>是
							<input type="radio" name="is_dispensed" value="0" ${gezlList.is_dispensed=='0'?'checked':'' }/>否
						</td>
						<td><span>&ensp;参与本科教学评估认证：</span>
							<input type="radio" name="is_utec" value="1" ${gezlList.is_utec=='1'?'checked':'' }/>是
							<input type="radio" name="is_utec" value="0" ${gezlList.is_utec=='0'?'checked':'' }/>否
						</td>
						<td><span>&ensp;学&emsp;&emsp;历：</span>
							<select id="degree" name="degree">
	                                <option value="0" ${gezlList.degree=='0'?'selected':'' }>无</option>
	                                <option value="1" ${gezlList.degree=='1'?'selected':'' }>专科</option>
	                                <option value="2" ${gezlList.degree=='2'?'selected':'' }>本科</option>
	                                <option value="3" ${gezlList.degree=='3'?'selected':'' }>硕士</option>
	                                <option value="4" ${gezlList.degree=='4'?'selected':'' }>博士</option>
	                            </select></td>
						<td><span>&ensp;专业特长：</span>
							<input class="cg_input" name="expertise" value="${gezlList.expertise}" id="expertise" maxlength="50"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!--主要学习经历-->
		<div class="sbxq_item" id="zyxxjl">
			<div>
				<span id="tsxz_span3"></span>
				<span class="tsxz_title">学习经历</span>
				<span class="tsxz_ts" id="zyxxjl_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="zyxxjl_xt">（选填）</span>
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
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty stuList[0]}">
							<tr>
								<td>
									<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'" z-index="100" max="'$#xx_jssj'" name="xx_kssj" id="xx_kssj" value="" style="width: 80px;" maxlength="20"/>
									-
									<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'" z-index="100" min="'$#xx_kssj'" name="xx_jssj" id="xx_jssj" value="" style="width: 80px;" maxlength="20"/>
								</td>
								<td><input class="cg_input" name="xx_school_name" value="" placeholder="学校名称"  maxlength="36"/></td>
								<td><input class="cg_input" name="xx_major" value="" placeholder="所学专业"  maxlength="16"/></td>
								<td><input class="cg_input" name="xx_degree" value="" style="width: 110px;" placeholder="学历"  maxlength="10"/></td>
								<td><input class="cg_input" name="xx_note" value="" style="width: 290px;" placeholder="备注"  maxlength="33"/></td>
								<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_xxjl()"/></td>
							</tr>
						</c:if>
						<c:forEach var="list" items="${stuList}" varStatus="status">
							<tr id="xxjl_${status.count}">
								<td>
									<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'" z-index="100"  name="xx_kssj" max="'$#xx_jssj_${status.count}'" id="xx_kssj_${status.count}" value="${list.date_begin}" style="width: 80px;"/>
									-
									<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'" z-index="100" name="xx_jssj" min="'$#xx_kssj_${status.count}'" id="xx_jssj_${status.count}" value="${list.date_end}" style="width: 80px;"/>
								</td>
								<td><input class="cg_input" name="xx_school_name" id="xx_school_name_${status.count}" value="${list.school_name}" placeholder="学校名称"  maxlength="36"/></td>
								<td><input class="cg_input" name="xx_major" id="xx_major_${status.count}" value="${list.major}" placeholder="所学专业"  maxlength="16"/></td>
								<td><input class="cg_input" name="xx_degree" id="xx_degree_${status.count}" value="${list.degree}" style="width: 110px;" placeholder="学历"  maxlength="10"/></td>
								<td><input class="cg_input" name="xx_note" value="${list.note}" style="width: 290px;" placeholder="备注"  maxlength="33"/>
									<input type="hidden" name="zdjy" value="xx_kssj_${status.count},xx_jssj_${status.count},xx_major_${status.count},xx_degree_${status.count}"/>
								</td>
								<td>
									<c:choose>
										<%-- <c:when test="${status.count == fn:length(stuList)}"> --%>
										<c:when test="${status.count == 1}"> 
											<img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_xxjl()"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('xxjl_${status.count}')"/>
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
		<div class="sbxq_item" id="gzjl">
			<div>
				<span id="tsxz_span4"></span>
				<span class="tsxz_title">工作经历</span>
				<span class="tsxz_ts" id="gzjl_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="gzjl_xt" >（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_gzjl">
					<thead>
						<tr>
							<td width="220px">起止时间</td>
							<td width="220px">工作单位</td>
							<td width="220px">职位</td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty workList[0]}">
							<tr>
								<td>
									<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'" z-index="100"  name="gz_kssj" max="'$#gz_jssj'" id="gz_kssj" value="" style="width: 80px;"/>
									-
									<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="gz_jssj" min="'$#gz_kssj'" id="gz_jssj" value="" style="width: 80px;"/>
								</td>
								<td><input class="cg_input" name="gz_org_name" value="" placeholder="工作单位"  maxlength="33"/></td>
								<td><input class="cg_input" name="gz_position" value="" placeholder="职位"  maxlength="33"/></td>
								<td><input class="cg_input" name="gz_note" value="" style="width: 410px;" placeholder="备注" maxlength="33"/></td>
								<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_gzjl()"/></td>
							</tr> 
						</c:if>
						<c:forEach var="list" items="${workList}" varStatus="status">
							<tr id="gzjl_${status.count}">
								<td>
									<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'" z-index="100"  name="gz_kssj" max="'$#gz_jssj_${status.count}'" id="gz_kssj_${status.count}" value="${list.date_begin}" style="width: 80px;"/>
									-
									<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="gz_jssj" min="'$#gz_kssj_${status.count}'" id="gz_jssj_${status.count}" value="${list.date_end}" style="width: 80px;"/>
								</td>
								<td><input class="cg_input" name="gz_org_name" id="gz_org_name_${status.count}" value="${list.org_name}" placeholder="工作单位" maxlength="33"/></td>
								<td><input class="cg_input" name="gz_position" id="gz_position_${status.count}"  value="${list.position}" placeholder="职位"  maxlength="33"/></td>
								<td><input class="cg_input" name="gz_note" value="${list.note}" style="width: 410px;" placeholder="备注" maxlength="33"/>
									<input type="hidden" name="zdjy" value="gz_kssj_${status.count},gz_jssj_${status.count},gz_org_name_${status.count},gz_position_${status.count}"/>
								</td>
								<td><c:choose>
									<c:when test="${status.count == 1}"> 
										<img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_gzjl()"/>
									</c:when>
									<c:otherwise>
										<img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('gzjl_${status.count}')"/>
									</c:otherwise>
								</c:choose></td>
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
				<span class="tsxz_title">教学经历</span>
				<span class="tsxz_ts" id="jxjl_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="jxjl_xt" >（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_jxjz">
					<thead>
						<tr>
							<td width="220px">起止时间</td>
							<td width="220px">学校名称</td>
							<td width="220px">教学科目 </td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty steaList[0]}">
							 <tr>
								<td>
									<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'"  z-index="100"  name="jx_kssj" max="'$#jx_jssj'"  id="jx_kssj" value="" style="width: 80px;"/>
									-
									<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="jx_jssj" min="'#jx_kssj'" id="jx_jssj" value="" style="width: 80px;"/>
								</td>
								<td><input class="cg_input"  maxlength="33" name="jx_school_name" value="" placeholder="学校名称"/></td>
								<td><input class="cg_input" maxlength="50" name="jx_subject" value="" placeholder="教学科目"/></td>
								<td><input class="cg_input" maxlength="33" name="jx_note" value="" style="width: 410px;" placeholder="备注"/></td>
								<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jxjl()"/></td>
							</tr> 
						</c:if>
						<c:forEach var="list" items="${steaList}" varStatus="status">
							<tr id="jxjz_${status.count}">
								<td>
								<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'"  z-index="100"  name="jx_kssj" max="'$#jx_jssj_${status.count}'"  id="jx_kssj_${status.count}" value="${list.date_begin}" style="width: 80px;"/>
								-
								<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="jx_jssj" min="'$#jx_kssj_${status.count}'"  id="jx_jssj_${status.count}" value="${list.date_end}" style="width: 80px;"/>
							</td>
							<td><input class="cg_input" maxlength="33" name="jx_school_name" id="jx_school_name_${status.count}" value="${list.school_name}" placeholder="学校名称"/></td>
							<td><input class="cg_input" maxlength="50" name="jx_subject" id="jx_subject_${status.count}" value="${list.subject}" placeholder="教学科目"/></td>
							<td><input class="cg_input" maxlength="33" name="jx_note" value="${list.note}" style="width: 410px;" placeholder="备注"/>
								<input type="hidden" name="zdjy" value="jx_kssj_${status.count},jx_jssj_${status.count},jx_school_name_${status.count},jx_subject_${status.count}"/>
							</td>
							<td><c:choose>
									<c:when test="${status.count == 1}"> 
										<img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jxjl()"/>
									</c:when>
									<c:otherwise>
										<img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('jxjz_${status.count}')"/>
									</c:otherwise>
								</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--个人成就-->
		<div class="sbxq_item" id="grcjqk">
			<div>
				<span id="tsxz_span9"></span>
				<span class="tsxz_title">个人成就</span>
				<span class="tsxz_ts" id="grcj_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="grcj_xt" >（选填）</span>
			</div>
			<div class="content">
				<textarea class="text_cl" maxlength="1000"  name="gr_content" id="gr_content" maxlength="1000">${achievementMap.content}</textarea>
			</div>
		</div>
		<!--主要学术兼职-->
		<div class="sbxq_item" id="xsjz">
			<div>
				<span id="tsxz_span10"></span>
				<span class="tsxz_title">学术兼职</span>
				<span class="tsxz_ts" id="xsjz_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span id="xsjz_xt" class="tsxz_xt">（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_xsjz">
					<thead>
						<tr>
							<td width="220px">兼职学术组织</td>
							<td width="250px">级别</td>
							<td width="220px">职务 </td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty zjxsList[0]}">
							 <tr>
								<td><input class="cg_input" maxlength="33" name="xs_org_name" id="xs_org_name" value="" placeholder="学术组织"/></td>
								<td style="color: #333333;">
									<table class="radio_tb" style="width: 100%;"><tr>
										<td><input type="radio" name="xs_rank_a" value="0" checked="checked"/>无</td>
										<td><input type="radio" name="xs_rank_a" value="1" />国际</td>
										<td><input type="radio" name="xs_rank_a" value="2" />国家</td>
										<td><input type="radio" name="xs_rank_a" value="3" />省部</td>
										<td><input type="radio" name="xs_rank_a" value="4" />市级</td>
									</tr></table>
									<input type="hidden" name="xs_rank" value="xs_rank_a" />
								</td>
								<td><input class="cg_input" maxlength="16" name="xs_position" value="" placeholder="职务"/></td>
								<td><input class="cg_input" maxlength="33" name="xs_note" value="" style="width: 370px;" placeholder="备注"/></td>
								<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_xsjz()"/></td>
							</tr> 
						</c:if>
						<c:forEach var="list" items="${zjxsList}" varStatus="status">
							<tr id="xsjz_${status.count}">
								<td><input class="cg_input" maxlength="33" name="xs_org_name" id="xs_org_name_${status.count}" value="${list.org_name}" placeholder="学术组织"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 100%;"><tr>
									<td><input type="radio" name="xs_rank_${status.count}" value="0" ${list.rank=='0'?'checked':'' }/>无</td>
									<td><input type="radio" name="xs_rank_${status.count}" value="1" ${list.rank=='1'?'checked':'' }/>国际</td>
									<td><input type="radio" name="xs_rank_${status.count}" value="2" ${list.rank=='2'?'checked':'' }/>国家</td>
									<td><input type="radio" name="xs_rank_${status.count}" value="3" ${list.rank=='3'?'checked':'' }/>省部</td>
									<td><input type="radio" name="xs_rank_${status.count}" value="4" ${list.rank=='4'?'checked':'' }/>其他</td>
								</tr></table>	
									<input type="hidden" name="xs_rank" value="xs_rank_${status.count}" />
							</td>
							<td><input class="cg_input" maxlength="16" id="xs_position_${status.count}" name="xs_position" value="${list.position}" placeholder="职务"/></td>
							<td><input class="cg_input" maxlength="33" name="xs_note" value="${list.note}" style="width: 370px;" placeholder="备注"/>
								<input type="hidden" name="zdjy" value="xs_org_name_${status.count},xs_position_${status.count}"/>
							</td>
							<td><c:choose>
									<c:when test="${status.count == 1}"> 
										<img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_xsjz()"/>
									</c:when>
									<c:otherwise>
										<img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('xsjz_${status.count}')"/>
									</c:otherwise>
								</c:choose></td>
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
				<span class="tsxz_title">本套上版教材参编情况</span>
				<span class="tsxz_ts" id="sbjccb_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="sbjccb_xt" >（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_jccb">
					<thead>
						<tr>
							<td width="400px">教材名称</td>
							<td width="260px">编写职务</td>
							<td width="100px">数字编委</td>
							<td width="120px">出版时间</td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty jcbjList[0]}">
						 <tr>
							<td><input class="cg_input" maxlength="33" name="jc_material_name" id="jc_material_name" value="" style="width: 360px;" placeholder="教材名称"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 100%;"><tr>
									<td><input type="radio" name="jc_position_a" value="0" checked="checked"/>无</td>
									<td><input type="radio" name="jc_position_a" value="1" />主编</td>
									<td><input type="radio" name="jc_position_a" value="2" />副主编</td>
									<td><input type="radio" name="jc_position_a" value="3" />编委</td>
								</tr></table>	
								<input type="hidden" name="jc_position" value="jc_position_a" />
							</td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 80px;"><tr>
									<td><input type="radio" name="jc_is_digital_editor_a" value="1" />是</td>
					 				<td><input type="radio" name="jc_is_digital_editor_a" value="0" checked="checked"/>否</td>
								</tr></table>
								<input type="hidden" name="jc_is_digital_editor" value="jc_is_digital_editor_a" />
							</td>
							 <td><input class="cg_input" name="jc_publish_date" id="jc_publish_date" value="" placeholder="出版时间" calendar format="'yyyy-mm-dd'"  z-index="100"  style="width: 100px;"/></td>
							<td><input class="cg_input" maxlength="33" name="jc_note" value="" style="width: 190px;" placeholder="备注"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jccb()"/></td>
						</tr></c:if>
						<c:forEach var="list" items="${jcbjList}" varStatus="status">
							<tr id="jccb_${status.count}">
								<td><input class="cg_input" maxlength="33" name="jc_material_name" id="jc_material_name_${status.count}" value="${list.material_name}" style="width: 360px;" placeholder="教材名称"/></td>
								<td style="color: #333333;">
									<table class="radio_tb" style="width: 100%;"><tr>
										<td><input type="radio" name="jc_position_${status.count}" value="0" ${list.position=='0'?'checked':'' }/>无</td>
										<td><input type="radio" name="jc_position_${status.count}" value="1" ${list.position=='1'?'checked':'' }/>主编</td>
										<td><input type="radio" name="jc_position_${status.count}" value="2" ${list.position=='2'?'checked':'' }/>副主编</td>
										<td><input type="radio" name="jc_position_${status.count}" value="3" ${list.position=='3'?'checked':'' }/>编委</td>
									</tr></table>		
									<input type="hidden" name="jc_position" value="jc_position_${status.count}" />
								</td>
								<td style="color: #333333;">
								<table class="radio_tb" style="width: 80px;"><tr>
									<td><input type="radio" name="jc_is_digital_editor_${status.count}" value="1"  ${list.is_digital_editor=='1'?'checked':'' } />是</td>
					 				<td><input type="radio" name="jc_is_digital_editor_${status.count}" value="0"  ${list.is_digital_editor=='0'?'checked':'' }/>否</td>
								</tr></table>
								<input type="hidden" name="jc_is_digital_editor" value="jc_is_digital_editor_${status.count}" />
							</td>
								<td><input class="cg_input" name="jc_publish_date" id="jc_publish_date_${status.count}" value="${list.publish_date}" placeholder="出版时间" calendar format="'yyyy-mm-dd'"  z-index="100"  style="width: 100px;"/></td>
								<td><input class="cg_input" maxlength="33" name="jc_note" value="${list.note}" style="width: 190px;" placeholder="备注"/>
									<input type="hidden" name="zdjy" value="jc_material_name_${status.count}"/>
								</td>
							<td><c:choose>
									<c:when test="${status.count == 1}"> 
										<img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jccb()"/>
									</c:when>
									<c:otherwise>
										<img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('jccb_${status.count}')"/>
									</c:otherwise>
								</c:choose></td>
							</tr>
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
				<span class="tsxz_ts" id="zbgjjgh_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="zbgjjgh_xt" >（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_gjghjc">
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
						<c:if test="${empty gjghjcList[0]}">
						 <tr>
							<td><input class="cg_input" maxlength="80" name="hj_material_name" id="hj_material_name" value="" style="width: 300px;" placeholder="教材名称"/></td>
							<td><input class="cg_input" maxlength="50" name="hj_isbn" value="" style="width: 110px;" placeholder="标准书号"/></td>
							 <td><input class="cg_input" name="hj_rank_text" id="hj_rank_text" value="" style="width: 300px;" placeholder="教材级别" maxlength="50"/></td>
							<td><input class="cg_input" maxlength="33" name="hj_note" value="" style="width: 250px;" placeholder="备注"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_gjghjc()"/></td>
						</tr> </c:if>
						<c:forEach var="list" items="${gjghjcList}" varStatus="status">
							<tr id="gjghjc_${status.count}">
								<td><input class="cg_input" maxlength="33" name="hj_material_name" id="hj_material_name_${status.count}" value="${list.material_name}" style="width: 300px;" placeholder="教材名称"/></td>
								<td><input class="cg_input" maxlength="50" name="hj_isbn" value="${list.isbn}" id="hj_isbn_${status.count}" style="width: 110px;" placeholder="标准书号"/></td>
								<td><input class="cg_input" name="hj_rank_text" id="hj_rank_text_${status.count}" value="${list.rank_text}" style="width: 300px;" placeholder="教材级别" maxlength="50"/></td>
								<td><input class="cg_input" maxlength="33" name="hj_note" value="${list.note}" style="width: 250px;" placeholder="备注"/>
									<input type="hidden" name="zdjy" value="hj_material_name_${status.count},hj_isbn_${status.count}"/>
								</td>
								<td><c:choose>
									<c:when test="${status.count == 1}"> 
										<img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_gjghjc()"/>
									</c:when>
									<c:otherwise>
										<img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('gjghjc_${status.count}')"/>
									</c:otherwise>
								</c:choose></td>
							</tr>
						</c:forEach>	
					</tbody>
				</table>
			</div>
		</div>
			<!--人卫社教材编写情况表-->
		<div class="sbxq_item" id="rwsjcbx">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">人卫社教材编写情况表</span>
				<span class="tsxz_ts" id="rwsjcbx_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="rwsjcbx_xt" >（选填）</span>
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
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty rwsjcList[0]}">
						<tr>
							<td><input class="cg_input" maxlength="33" name="pmph_material_name" id="pmph_material_name" value="" style="width: 200px;" placeholder="教材名称"/></td>
							<td>
								<select id="pmph_rank" name="pmph_rank">
	                                <option value="0">无</option>
	                                <option value="1">国家</option>
	                                <option value="2">省部</option>
	                                <option value="3">协编</option>
	                                <option value="4">校本</option>
	                                <option value="5">其他</option>
                            	</select>
							</td>
							<td>
								<select id="pmph_position" name="pmph_position">
	                                <option value="0">无</option>
	                                <option value="1">主编</option>
	                                <option value="2">副主编</option>
	                                <option value="3">编委</option>
                            	</select>
							</td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 80px;"><tr>
									<td><input type="radio" name="pmph_is_digital_editor_a" value="1" />是</td>
					 				<td><input type="radio" name="pmph_is_digital_editor_a" value="0" checked="checked"/>否</td>
								</tr></table>
								<input type="hidden" name="pmph_is_digital_editor" value="pmph_is_digital_editor_a" />
							</td>
							<td><input class="cg_input" id="pmph_publish_date" name="pmph_publish_date" placeholder="出版时间" calendar format="'yyyy-mm-dd'"  z-index="100"  value="" style="width: 100px;"/></td>
							<td><input class="cg_input" maxlength="50" id="pmph_isbn" name="pmph_isbn" value="" style="width: 100px;" placeholder="标准书号"/></td>
							<td><input class="cg_input" maxlength="33" id="pmph_note" name="pmph_note" value="" placeholder="备注" style="width: 260px;"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_rwsjcbx()"/></td>
						</tr> </c:if>
						<c:forEach var="list" items="${rwsjcList}" varStatus="status">
						<tr id="rwsjcbx_${status.count}">
							<td><input class="cg_input" name="pmph_material_name" id="pmph_material_name_${status.count}" value="${list.material_name}" style="width: 200px;" placeholder="教材名称"/></td>
							<td>
								<select id="pmph_rank_${status.count}" name="pmph_rank">
	                                <option value="0" ${list.rank=='0'?'selected':'' }>无</option>
	                                <option value="1" ${list.rank=='1'?'selected':'' }>国家</option>
	                                <option value="2" ${list.rank=='2'?'selected':'' }>省部</option>
	                                <option value="3" ${list.rank=='3'?'selected':'' }>协编</option>
	                                <option value="4" ${list.rank=='4'?'selected':'' }>校本</option>
	                                <option value="5" ${list.rank=='5'?'selected':'' }>其他</option>
                            	</select>
                            	<input type="hidden" id="pmph_rank_sl" name="pmph_rank_sl" value="pmph_rank_${status.count}" />
							</td>
							<td>
								<select id="pmph_position_${status.count}" name="pmph_position">
	                                <option value="0" ${list.position=='0'?'selected':'' }>无</option>
	                                <option value="1" ${list.position=='1'?'selected':'' }>主编</option>
	                                <option value="2" ${list.position=='2'?'selected':'' }>副主编</option>
	                                <option value="3" ${list.position=='3'?'selected':'' }>编委</option>
                            	</select>
                            	<input type="hidden" id="pmph_sl" name="pmph_sl" value="pmph_position_${status.count}" />
							</td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 80px;"><tr>
									<td><input type="radio" name="pmph_is_digital_editor_${status.count}" value="1" ${list.is_digital_editor=='1'?'checked':'' } />是</td>
					 				<td><input type="radio" name="pmph_is_digital_editor_${status.count}" value="0" ${list.is_digital_editor=='0'?'checked':'' }/>否</td>
								</tr></table>
								<input type="hidden" name="pmph_is_digital_editor" value="pmph_is_digital_editor_${status.count}" />
							</td>
							<td><input class="cg_input" placeholder="出版时间" id="pmph_publish_date_${status.count}" calendar format="'yyyy-mm-dd'"  z-index="100" name="pmph_publish_date" value="${list.publish_date}" style="width: 100px;"/></td>
							<td><input class="cg_input" name="pmph_isbn" id="pmph_isbn_${status.count}" value="${list.isbn}" style="width: 100px;" placeholder="标准书号" maxlength="50"/></td>
							<td><input class="cg_input" name="pmph_note" value="${list.note}" style="width: 260px;" placeholder="备注"/>
								<input type="hidden" name="zdjy" value="pmph_material_name_${status.count},pmph_publish_date_${status.count},pmph_isbn_${status.count}"/>
							</td>
							<td><c:choose>
									<c:when test="${status.count == 1}"> 
										<img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_rwsjcbx()"/>
									</c:when>
									<c:otherwise>
										<img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('rwsjcbx_${status.count}')"/>
									</c:otherwise>
								</c:choose></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
			<!--其他社教材编写情况-->
		<div class="sbxq_item" id="qtjcbxqk">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">其他社教材编写情况</span>
				<span class="tsxz_ts" id="qtjcbxqk_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="qtjcbxqk_xt" >（选填）</span>
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
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty jcbxqtList[0]}">
						<tr>
							<td><input class="cg_input" maxlength="33" name="jcb_material_name" id="jcb_material_name" value="" style="width: 200px;" placeholder="教材名称"/></td>
							<td>
								<select id="jcb_rank" name="jcb_rank">
	                                <option value="0">无</option>
	                                <option value="1">国家</option>
	                                <option value="2">省部</option>
	                                <option value="3">协编</option>
	                                <option value="4">校本</option>
	                                <option value="5">其他</option>
                            	</select>
							</td>
							<td>
								<select id="jcb_position" name="jcb_position">
	                                <option value="0">无</option>
	                                <option value="1">主编</option>
	                                <option value="2">副主编</option>
	                                <option value="3">编委</option>
                            	</select>
							</td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 100%;"><tr>
									<td><input type="radio" name="jcb_is_digital_editor_a" value="1" />是</td>
					 				<td><input type="radio" name="jcb_is_digital_editor_a" value="0" checked="checked"/>否</td>
								</tr></table>
								<input type="hidden" name="jcb_is_digital_editor" value="jcb_is_digital_editor_a" />
							</td>
							<td><input class="cg_input" maxlength="16" name="jcb_publisher" value="" style="width: 100px;" placeholder="出版社"/></td>
							<td><input class="cg_input" placeholder="出版时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="jcb_publish_date" value="" style="width: 100px;"/></td>
							<td><input class="cg_input" maxlength="50" name="jcb_isbn" value="" style="width: 100px;" placeholder="标准书号"/></td>
							<td><input class="cg_input" maxlength="33" name="jcb_note" value="" placeholder="备注" style="width: 130px;"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jcbx()"/></td>
						</tr> </c:if>
						<c:forEach var="list" items="${jcbxqtList}" varStatus="status">
							<tr id="jcbx_${status.count}">
								<td><input class="cg_input" name="jcb_material_name" id="jcb_material_name_${status.count}" value="${list.material_name}" style="width: 200px;" placeholder="教材名称"/></td>
								<td>
									<select id="jcb_rank_${status.count}" name="jcb_rank">
		                                <option value="0" ${list.rank=='0'?'selected':'' }>无</option>
		                                <option value="1" ${list.rank=='1'?'selected':'' }>国家</option>
		                                <option value="2" ${list.rank=='2'?'selected':'' }>省部</option>
		                                <option value="3" ${list.rank=='3'?'selected':'' }>协编</option>
		                                <option value="4" ${list.rank=='4'?'selected':'' }>校本</option>
		                                <option value="5" ${list.rank=='5'?'selected':'' }>其他</option>
	                            	</select>
	                            	<input type="hidden" id="jcb_rank_sl" name="jcb_rank_sl" value="jcb_rank_${status.count}" />
								</td>
								<td>
									<select id="jcb_position_${status.count}" name="jcb_position">
		                                <option value="0" ${list.position=='0'?'selected':'' }>无</option>
		                                <option value="1" ${list.position=='1'?'selected':'' }>主编</option>
		                                <option value="2" ${list.position=='2'?'selected':'' }>副主编</option>
		                                <option value="3" ${list.position=='3'?'selected':'' }>编委</option>
	                            	</select>
	                            	<input type="hidden" id="jcjb_sl" name="jcjb_sl" value="jcb_position_${status.count}" />
								</td>
								<td style="color: #333333;">
									<table class="radio_tb" style="width: 80px;"><tr>
										<td><input type="radio" name="jcb_is_digital_editor_${status.count}" value="1" ${list.is_digital_editor=='1'?'checked':'' } />是</td>
						 				<td><input type="radio" name="jcb_is_digital_editor_${status.count}" value="0" ${list.is_digital_editor=='0'?'checked':'' }/>否</td>
									</tr></table>
									<input type="hidden" name="jcb_is_digital_editor" value="jcb_is_digital_editor_${status.count}" />
								</td>
								<td><input class="cg_input" name="jcb_publisher" id="jcb_publisher_${status.count}" value="${list.publisher}" style="width: 100px;" placeholder="出版社"/></td>
								<td><input class="cg_input" placeholder="出版时间" id="jcb_publish_date_${status.count}" calendar format="'yyyy-mm-dd'"  z-index="100" name="jcb_publish_date" value="${list.publish_date}" style="width: 100px;"/></td>
								<td><input class="cg_input" name="jcb_isbn" id="jcb_isbn_${status.count}" value="${list.isbn}" style="width: 100px;" placeholder="标准书号" maxlength="50"/></td>
								<td><input class="cg_input" name="jcb_note" value="${list.note}" style="width: 230px;" placeholder="备注"/>
									<input type="hidden" name="zdjy" value="jcb_material_name_${status.count},jcb_publisher_${status.count},jcb_publish_date_${status.count},jcb_isbn_${status.count}"/>
								</td>
								<td><c:choose>
										<c:when test="${status.count == 1}"> 
											<img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jcbx()"/>
										</c:when>
										<c:otherwise>
											<img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('jcbx_${status.count}')"/>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--参加人卫慕课、数字教材编写情况-->
		<div class="sbxq_item" id="digital">
			<div>
				<span id="tsxz_span9"></span>
				<span class="tsxz_title">参加人卫慕课、数字教材编写情况</span>
				<span class="tsxz_ts" id="digital_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="digital_xt" >（选填）</span>
			</div>
			<div class="content">
				<textarea class="text_cl" name="mooc_content" id="mooc_content" maxlength="500">${digitalMap.content}</textarea>
			</div>
		</div>
		<!--精品课程建设-->
		<div class="sbxq_item" id="gjjpkcjs">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">精品课程建设情况</span>
				<span class="tsxz_ts" id="gjjpkcjs_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="gjjpkcjs_xt" >（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_jpkcjs">
					<thead>
					<tr>
						<td width="350px">课程名称</td>
						<td width="180px">全年课时</td>
						<td width="200px">课程级别</td>
						<td>备注</td>
						<td width="78px">添加</td>
					</tr>
					</thead>
					<tbody>
					<c:if test="${empty gjkcjsList[0]}">
						<tr>
							<td><input class="cg_input" maxlength="20" name="gj_course_name" id="gj_course_name" value="" style="width: 300px;" placeholder="课程名称"/></td>
							<td><input class="cg_input" maxlength="20" name="gj_class_hour" value="" style="width: 130px;" placeholder="课时数"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 100%;"><tr>
									<td><input type="radio" name="gj_type_a" value="0" checked="checked"/>无</td>
									<td><input type="radio" name="gj_type_a" value="1" />国际</td>
									<td><input type="radio" name="gj_type_a" value="2" />国家</td>
									<td><input type="radio" name="gj_type_a" value="3" />省部</td>
								</tr></table>
								<input type="hidden" name="gj_type" value="gj_type_a" />
							</td>
							<td><input class="cg_input" maxlength="20" name="gj_note" value="" style="width: 340px;" placeholder="备注"/>
							</td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jpkcjs('tab_jpkcjs',1)"/></td>
						</tr> </c:if>
					<c:forEach var="list" items="${gjkcjsList}" varStatus="status">
						<tr id="jpkcjs_${status.count}">
							<td><input class="cg_input" maxlength="20" name="gj_course_name" id="gj_course_name_${status.count}" value="${list.course_name}" style="width: 300px;" placeholder="课程名称"/></td>
							<td><input class="cg_input" maxlength="20" name="gj_class_hour" id="gj_class_hour_${status.count}" value="${list.class_hour}" style="width: 130px;" placeholder="课时数"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 100%;"><tr>
									<td><input type="radio" name="gj_type_${status.count}" value="0" ${list.type=='0'?'checked':'' }/>无</td>
									<td><input type="radio" name="gj_type_${status.count}" value="1" ${list.type=='1'?'checked':'' }/>国际</td>
									<td><input type="radio" name="gj_type_${status.count}" value="2" ${list.type=='2'?'checked':'' }/>国家</td>
									<td><input type="radio" name="gj_type_${status.count}" value="3" ${list.type=='3'?'checked':'' }/>省部</td>
								</tr></table>
								<input type="hidden" name="gj_type" value="gj_type_${status.count}" />
							</td>
							<td><input class="cg_input" maxlength="20" name="gj_note" value="${list.note}" style="width: 340px;" placeholder="备注"/>
								<input type="hidden" name="zdjy" value="gj_course_name_${status.count},gj_class_hour_${status.count}"/>
							</td>
							<td><c:choose>
								<c:when test="${status.count == 1}">
									<img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jpkcjs('tab_jpkcjs',1)"/>
								</c:when>
								<c:otherwise>
									<img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('jpkcjs_${status.count}')"/>
								</c:otherwise>
							</c:choose></td>
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
				<span class="tsxz_title">科研情况</span>
				<span class="tsxz_ts" id="zjkyqk_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="zjkyqk_xt" >（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_zjky">
					<thead>
						<tr>
							<td width="220px">课题名称</td>
							<td width="220px">审批单位</td>
							<td width="320px">获奖情况</td>
							<td width="320px">备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty zjkyList[0]}">
							 <tr>
								<td><input class="cg_input" maxlength="50" name="zjk_research_name" id="zjk_research_name" value="" style="width: 200px;" placeholder="课题名称"/></td>
								<td><input class="cg_input" maxlength="33" name="zjk_approval_unit" value="" style="width: 200px;" placeholder="审批单位"/></td>
								<td><input class="cg_input" maxlength="33" name="zjk_award" value="" style="width: 300px;" placeholder="获奖情况"/></td>
								<td><input class="cg_input" maxlength="33" name="zjk_note" value="" style="width: 300px;" placeholder="备注"/></td>
								<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="add_zjky()"></td>
							</tr> 
						</c:if>
						<c:forEach var="list" items="${zjkyList}" varStatus="status">
							<tr id="zjky_${status.count}">
								<td><input class="cg_input" maxlength="50" name="zjk_research_name" id="zjk_research_name_${status.count}" value="${list.research_name}" style="width: 200px;" placeholder="课题名称"/></td>
								<td><input class="cg_input" maxlength="33" name="zjk_approval_unit" id="zjk_approval_unit_${status.count}" value="${list.approval_unit}" style="width: 200px;" placeholder="审批单位"/></td>
								<td><input class="cg_input" maxlength="33" name="zjk_award" id="zjk_award_${status.count}" value="${list.award}" style="width: 300px;" placeholder="获奖情况"/></td>
								<td><input class="cg_input" maxlength="33" name="zjk_note" value="${list.note}" style="width: 300px;" placeholder="备注"/>
									<input type="hidden" name="zdjy" value="zjk_research_name_${status.count},zjk_approval_unit_${status.count},zjk_award_${status.count}"/>
								</td>
								<td><c:choose>
									<c:when test="${status.count == 1}"> 
										<img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_zjky()"/>
									</c:when>
									<c:otherwise>
										<img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('zjky_${status.count}')"/>
									</c:otherwise>
								</c:choose></td>
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
				<span class="tsxz_ts" id="zbxszz_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="zbxszz_xt" >（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_zbxszz">
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
						<c:if test="${empty monographList[0]}">
						 <tr>
							<td><input class="cg_input" name="zb_monograph_name" id="zb_monograph_name" value="" style="width: 200px;" placeholder="教材名称" maxlength="16"/></td>
							 <td><input class="cg_input" name="zb_monograph_date" id="zb_monograph_date" value="" style="width: 120px;" calendar format="'yyyy-mm-dd'" placeholder="发表日期"/></td>
							 <td style="color: #333333;">
								<table class="radio_tb" style="width: 140px;"><tr>
									<td><input type="radio" name="is_self_paid_a" value="0" checked="checked"/>公费</td>
									<td><input type="radio" name="is_self_paid_a" value="1" />自费</td>
								</tr></table>
								<input type="hidden" name="is_self_paid" value="is_self_paid_a" />
							</td>
							<td><input class="cg_input" name="zb_publisher" value="" style="width: 180px;" placeholder="出版单位"  maxlength="16"/></td>
							<td><input class="cg_input" name="zb_publish_date" value="" style="width: 120px;" calendar format="'yyyy-mm-dd'" placeholder="出版时间"/></td>
							<td><input class="cg_input" name="zb_note" value="" style="width: 200px;" placeholder="备注"  maxlength="33"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_zbxszz()"/></td>
						</tr></c:if>
						<c:forEach var="list" items="${monographList}" varStatus="status">
							<tr id="zbxszz_${status.count}">
								<td><input class="cg_input" name="zb_monograph_name" id="zb_monograph_name_${status.count}" value="${list.monograph_name}" style="width: 200px;" placeholder="教材名称" maxlength="16"/></td>
								<td><input class="cg_input" name="zb_monograph_date" id="zb_monograph_date_${status.count}" value="${list.monograph_date}" style="width: 120px;" calendar format="'yyyy-mm-dd'" placeholder="出版时间"/></td>
								<td style="color: #333333;">
									<table class="radio_tb" style="width: 140px;"><tr>
										<td><input type="radio" name="is_self_paid_${status.count}" value="0" ${list.is_self_paid=='0'?'checked':'' }/>自费</td>
										<td><input type="radio" name="is_self_paid_${status.count}" value="1"  ${list.is_self_paid=='1'?'checked':'' }/>公费</td>
									</tr></table>
									<input type="hidden" name="is_self_paid" value="is_self_paid_${status.count}" />
								</td>
								<td><input class="cg_input" name="zb_publisher" id="zb_publisher_${status.count}" value="${list.publisher}" style="width: 180px;" placeholder="出版单位"  maxlength="16"/></td>
								<td><input class="cg_input" name="zb_publish_date" id="zb_publish_date_${status.count}" value="${list.publish_date}" style="width: 120px;" 
								calendar format="'yyyy-mm-dd'" placeholder="出版时间"/></td>
								<td><input class="cg_input" name="zb_note" value="${list.note}" style="width: 200px;" placeholder="备注"  maxlength="33"/>
									<input type="hidden" name="zdjy" value="zb_monograph_name_${status.count},zb_publisher_${status.count},zb_publish_date_${status.count}"/>
								</td>
								<td><c:choose>
									<c:when test="${status.count == 1}"> 
										<img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_zbxszz()"/>
									</c:when>
									<c:otherwise>
										<img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('zbxszz_${status.count}')"/>
									</c:otherwise>
								</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--出版行业获奖情况表-->
		<div class="sbxq_item" id="publish">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">出版行业获奖情况</span>
				<span class="tsxz_ts" id="publish_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="publish_xt" >（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_publish">
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
						<c:if test="${empty publishList[0]}">
						 <tr>
							<td><input class="cg_input" name="pu_reward_name" id="pu_reward_name" value="" style="width: 300px;" placeholder="奖项名称" maxlength="16"/></td>
							<td><input class="cg_input" name="pu_award_unit" value="" style="width: 300px;" placeholder="评奖单位" maxlength="16"/></td>
							<td>
								<input class="cg_input" name="pu_reward_date" value="" style="width: 120px;" calendar format="'yyyy-mm-dd'"  placeholder="获奖时间"/>
							</td>
							<td><input class="cg_input" name="pu_note" value="" style="width: 250px;" placeholder="备注" maxlength="33"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_publish()"/></td>
						</tr> </c:if>
						<c:forEach var="list" items="${publishList}" varStatus="status">
							<tr id="publish_${status.count}">
								<td><input class="cg_input" name="pu_reward_name" id="pu_reward_name_${status.count}" value="${list.reward_name}" style="width: 300px;" placeholder="奖项名称" maxlength="16"/></td>
								<td><input class="cg_input" name="pu_award_unit" id="pu_award_unit_${status.count}"  value="${list.award_unit}" style="width: 300px;" placeholder="评奖单位" maxlength="16"/></td>
								<td>
									<input class="cg_input" name="pu_reward_date" id="pu_reward_date_${status.count}" value="${list.reward_date}" style="width: 120px;" calendar format="'yyyy-mm-dd'"  placeholder="获奖时间"/>
								</td>
								<td><input class="cg_input" name="pu_note" value="${list.note}" style="width: 250px;" placeholder="备注" maxlength="33"/>
									<input type="hidden" name="zdjy" value="pu_reward_name_${status.count},pu_award_unit_${status.count},pu_reward_date_${status.count}"/>
								</td>
								<td><c:choose>
									<c:when test="${status.count == 1}"> 
										<img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_publish()"/>
									</c:when>
									<c:otherwise>
										<img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('publish_${status.count}')"/>
									</c:otherwise>
								</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--SCI论文投稿及影响因子情况表-->
		<div class="sbxq_item" id="sci">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">SCI论文投稿及影响因子情况</span>
				<span class="tsxz_ts" id="sci_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="sci_xt" >（选填）</span>
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
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty sciList[0]}">
						 <tr>
							<td><input class="cg_input" name="sci_paper_name" id="sci_paper_name" value="" style="width: 300px;" placeholder="论文名称" maxlength="50"/></td>
							<td><input class="cg_input" name="sci_journal_name" value="" style="width: 130px;" placeholder="期刊名称" maxlength="50"/></td>
							<td><input class="cg_input" name="sci_factor" value="" style="width: 170px;" placeholder="期刊SCI影响因子" maxlength="7"/></td>
							<td><input class="cg_input" name="sci_publish_date" value="" style="width: 110px;" calendar format="'yyyy-mm-dd'" placeholder="发表时间"/></td>
							<td><input class="cg_input" name="sci_note" value="" style="width: 250px;" placeholder="备注" maxlength="33"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_sci()"/></td>
						</tr> </c:if>
						<c:forEach var="list" items="${sciList}" varStatus="status">
							<tr id="sci_${status.count}">
								<td><input class="cg_input" name="sci_paper_name" id="sci_paper_name_${status.count}" value="${list.paper_name}" style="width: 300px;" placeholder="论文名称" maxlength="50"/></td>
								<td><input class="cg_input" name="sci_journal_name" id="sci_journal_name_${status.count}" value="${list.journal_name}" style="width: 130px;" placeholder="期刊名称" maxlength="50"/></td>
								<td><input class="cg_input" name="sci_factor" id="sci_factor_${status.count}" value="${list.factor}" style="width: 170px;" placeholder="期刊SCI影响因子" maxlength="7"/></td>
								<td><input class="cg_input" name="sci_publish_date" id="sci_publish_date_${status.count}" value="${list.publish_date}" style="width: 110px;" calendar format="'yyyy-mm-dd'" placeholder="发表时间"/></td>
								<td><input class="cg_input" name="sci_note" value="${list.note}" style="width: 250px;" placeholder="备注" maxlength="33"/>
									<input type="hidden" name="zdjy" value="sci_paper_name_${status.count},sci_journal_name_${status.count},sci_factor_${status.count},sci_publish_date_${status.count}"/>
								</td>
								<td><c:choose>
									<c:when test="${status.count == 1}"> 
										<img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_sci()"/>
									</c:when>
									<c:otherwise>
										<img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('sci_${status.count}')"/>
									</c:otherwise>
								</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--临床医学获奖情况表-->
		<div class="sbxq_item" id="clinical">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">临床医学获奖情况</span>
				<span class="tsxz_ts" id="clinical_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="clinical_xt" >（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_clinical">
					<thead>
						<tr>
							<td width="340px">奖项名称</td>
							<td width="180px">奖项级别</td>
							<td width="210px">获奖时间</td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty clinicalList[0]}">
						 <tr>
							<td><input class="cg_input" name="cl_reward_name" maxlength="16" id="cl_reward_name" value="" style="width: 300px;" placeholder="奖项名称"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 180px;"><tr>
									<td><input type="radio" name="cl_award_unit_a" value="0" checked="checked"/>无</td>
									<td><input type="radio" name="cl_award_unit_a" value="1"/>国际</td>
									<td><input type="radio" name="cl_award_unit_a" value="2" />国家</td>
								</tr></table>
								<input type="hidden" name="cl_award_unit" value="cl_award_unit_a" />
							</td>
							<td><input class="cg_input" name="cl_reward_date" value="" style="width: 180px;" calendar format="'yyyy-mm-dd'" placeholder="获奖时间"/></td>
							<td><input class="cg_input" name="cl_note" value="" style="width: 330px;" placeholder="备注" maxlength="33"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_clinical()"/></td>
						</tr> </c:if>
						<c:forEach var="list" items="${clinicalList}" varStatus="status">
							<tr id="clinical_${status.count}">
								<td><input class="cg_input" name="cl_reward_name" maxlength="16" id="cl_reward_name_${status.count}" value="${list.reward_name}" style="width: 300px;" placeholder="奖项名称"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 180px;"><tr>
									<td><input type="radio" name="cl_award_unit_${status.count}" ${list.award_unit=='0'?'checked':'' } value="0"/>无</td>
									<td><input type="radio" name="cl_award_unit_${status.count}" ${list.award_unit=='1'?'checked':'' } value="1"/>国际</td>
									<td><input type="radio" name="cl_award_unit_${status.count}" ${list.award_unit=='2'?'checked':'' } value="2"/>国家</td>
								</tr></table>
								<input type="hidden" name="cl_award_unit" value="cl_award_unit_${status.count}" />
							</td>
							<td><input class="cg_input" name="cl_reward_date" id="cl_reward_date_${status.count}" value="${list.reward_date}" style="width: 180px;" calendar format="'yyyy-mm-dd'" placeholder="获奖时间"/></td>
							<td><input class="cg_input" name="cl_note" value="${list.note}" style="width: 330px;" placeholder="备注" maxlength="33"/>
								<input type="hidden" name="zdjy" value="cl_reward_name_${status.count},cl_reward_date_${status.count}"/>
							</td>
							<td><c:choose>
									<c:when test="${status.count == 1}"> 
										<img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_clinical()"/>
									</c:when>
									<c:otherwise>
										<img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('clinical_${status.count}')"/>
									</c:otherwise>
								</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--学术荣誉授予情况表-->
		<div class="sbxq_item" id="acade">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">学术荣誉授予情况</span>
				<span class="tsxz_ts" id="acade_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="acade_xt" >（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_acade">
					<thead>
						<tr>
							<td width="340px">荣誉名称</td>
							<td width="280px">荣誉级别</td>
							<td width="180px">授予时间</td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty acadeList[0]}">
						 <tr>
							<td><input class="cg_input" name="ac_reward_name" maxlength="16" id="ac_reward_name" value="" style="width: 300px;" placeholder="荣誉名称"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width:280px;"><tr>
									<td><input type="radio" name="ac_award_unit_a" value="0" checked="checked"/>无</td>
									<td><input type="radio" name="ac_award_unit_a" value="1"/>国际</td>
									<td><input type="radio" name="ac_award_unit_a" value="2"/>国家</td>
									<td><input type="radio" name="ac_award_unit_a" value="3"/>省部</td>
									<td><input type="radio" name="ac_award_unit_a" value="4" />市</td>
								</tr></table>
								<input type="hidden" name="ac_award_unit" value="ac_award_unit_a" />
							</td>
							<td><input class="cg_input" name="ac_reward_date" value="" style="width: 150px;" calendar format="'yyyy-mm-dd'" placeholder="授予时间"/></td>
							<td><input class="cg_input" name="ac_note" value="" style="width: 280px;" placeholder="备注" maxlength="33"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_acade()"/></td>
						</tr> </c:if>
						<c:forEach var="list" items="${acadeList}" varStatus="status">
							<tr id="acade_${status.count}">
								<td><input class="cg_input" name="ac_reward_name" maxlength="16" id="ac_reward_name_${status.count}" value="${list.reward_name}" style="width: 300px;" placeholder="荣誉名称"/></td>
								<td style="color: #333333;">
									<table class="radio_tb" style="width:280px;"><tr>
										<td><input type="radio" name="ac_award_unit_${status.count}" value="0" ${list.award_unit=='0'?'checked':'' }/>无</td>
										<td><input type="radio" name="ac_award_unit_${status.count}" value="1" ${list.award_unit=='1'?'checked':'' }/>国际</td>
										<td><input type="radio" name="ac_award_unit_${status.count}" value="2" ${list.award_unit=='2'?'checked':'' }/>国家</td>
										<td><input type="radio" name="ac_award_unit_${status.count}" value="3" ${list.award_unit=='3'?'checked':'' }/>省部</td>
										<td><input type="radio" name="ac_award_unit_${status.count}" value="4" ${list.award_unit=='4'?'checked':'' }/>市</td>
									</tr></table>
									<input type="hidden" name="ac_award_unit" value="ac_award_unit_${status.count}" />
								</td>
								<td><input class="cg_input" name="ac_reward_date" id="ac_reward_date_${status.count}" value="${list.reward_date }" style="width: 150px;" calendar format="'yyyy-mm-dd'" placeholder="授予时间"/></td>
								<td><input class="cg_input" name="ac_note" value="${list.note }" style="width: 280px;" placeholder="备注" maxlength="33"/>
									<input type="hidden" name="zdjy" value="ac_reward_name_${status.count},ac_reward_date_${status.count}"/>
								</td>
								<td><c:choose>
									<c:when test="${status.count == 1}"> 
										<img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_acade()"/>
									</c:when>
									<c:otherwise>
										<img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('acade_${status.count}')"/>
									</c:otherwise>
								</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--编写内容意向表-->
	 	<div class="sbxq_item" id="intention">
			<div>
				<span id="tsxz_span9"></span>
				<span class="tsxz_title">编写内容意向</span>
				<span class="tsxz_ts" id="intention_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="intention_xt" >（选填）</span>
			</div>
			<div class="content">
				<textarea class="text_cl" name="intention_content" id="intention_content" maxlength="500">${intentionMap.content}</textarea>
			</div>
		</div>
		<!--扩展信息-->
		<c:forEach var="zjkzxx" items="${zjkzqkList}" varStatus="status">
			<div class="sbxq_item1">
				<div>
					<span id="tsxz_span9"></span>
					<span class="tsxz_title">${zjkzxx.extension_name}</span>
					<c:choose>
						<c:when test="${zjkzxx.is_required == true}">
							<span class="tsxz_ts" style="display: inline;"><img src="${ctx}/statics/image/btxx.png" /></span>
						</c:when>
						<c:otherwise>
							<span class="tsxz_xt" style="display: inline;">（选填）</span>
						</c:otherwise>
					</c:choose>
					<input type="hidden" name="extension_id" value="${zjkzxx.extension_id}"/>
				</div>
				<div class="content">
					<textarea class="text_cl" id="${zjkzxx.is_required}_${status.count}" name="kz_content" maxlength="1000">${zjkzxx.content}</textarea>
					<input type="hidden" name="zjkzxx" value="${zjkzxx.is_required}_${status.count}"/>
				</div>
			</div>
		</c:forEach>

			<!-- 申报单位-->
		<div class="sbxq_item1">
			<div>
				<span id="tsxz_span8"></span>
				<span class="tsxz_title">请选择你的申报单位</span>
			</div>
			<div class="sbdw">
				<span class="btbs">*</span><span class="btmc">申报单位：</span>
				<input class="cg_input" id="sbdw_name" name="sbdw_name" value="${gezlList.dwmc}" style="width: 300px;" onclick="javascript:orgAdd('${materialMap.material_id}')" readonly="readonly"/>
				<input type="hidden" id="sbdw_id" name="sbdw_id" value="${gezlList.org_id}" style="width: 300px;"/>
			</div>
		</div>
		<hr style=" height:1px;border:none;border-top:1px #c1c1c1 dashed;margin-top: 30px;">
		<div class="button">
			<div class="div_butt">
				<div class="bt_tj" id="butj" onclick="javascript:buttAdd('1')">提交</div>
				<div class="bt_tj" id="buzc" onclick="javascript:buttAdd('2')">暂存</div>
				<%--<div class="bt_tj" onclick="javascript:buttGive()">放弃</div>--%>
			</div>

		</div>
		</form>
	</div>
</div>

<!-- 退回原因显示悬浮框 -->
 <div class="bookmistake" id="return_cause_div">
         <div class="apache">
             <div class="mistitle">退回原因:</div>
             <div class="xx" onclick="$('#return_cause_div').fadeOut(500);"></div>
         </div>
         
         <div class="info">
         	<input id="return_cause_hidden" type="hidden" value="${return_cause }">
             <textarea class="misarea" disabled="disabled">${return_cause }</textarea>
         </div>
   
         <div class="">
             <button class="btn" type="button" onclick="$('#return_cause_div').fadeOut(500);">确认</button>
         </div>
 </div>

<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
