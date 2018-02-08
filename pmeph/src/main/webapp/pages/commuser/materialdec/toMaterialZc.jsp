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
			<span>个人中心 > 教材申报 > 填写申报表</span>
		</div>
		<!-- 图书选择-->
		<form id="objForm">
		<div class="sbxq_item1" id="tsxz">
			<div>
				<input type="hidden" id="select_nr" value="${bookSelects}"/>
				<input type="hidden" id="material_id" name="material_id" value="${materialMap.material_id}"/>
				<input type="hidden" id="declaration_id" name="declaration_id" value="${materialMap.declaration_id}"/>
				<!-- 是否编委 -->
				<input type="hidden" id="sfbw" name="sfbw" value="${materialMap.is_digital_editor_optional}"/>
				<!-- 是否书籍多选 -->
				<input type="hidden" id="is_multi_books" name="is_multi_books" value="${materialMap.is_multi_books}"/>
				<!-- 是否职位多选 -->
				<input type="hidden" id="is_multi_position" name="is_multi_position" value="${materialMap.is_multi_position}"/>
				<span id="tsxz_span1"></span>
				<span class="tsxz_title">图书选择(
					<c:if test="${materialMap.is_multi_books =='1'}">
					可以选择多本书籍，</c:if>
					<c:if test="${materialMap.is_multi_books !='1'}">
					只能选择一本书籍，</c:if>
					<c:if test="${materialMap.is_multi_position =='1'}">
					每本书籍可选多个职位，</c:if>
					<c:if test="${materialMap.is_multi_position !='1'}">
					每本书籍只能选择一个职位。
					</c:if>
					)
				</span>
				<span class="tsxz_ts1"><img src="${ctx}/statics/image/btxx.png" /></span>
				<c:if test="${materialMap.is_multi_books =='1' or materialMap.is_multi_position =='1'}">
				<div class="addBtn pull-right" id="sjtj" onclick="javascript:addTsxz()"><span>增加</span></div>
				</c:if>
			</div>
			<c:forEach var="list" items="${tssbList}" varStatus="status">
				<div class="item" id="xz1">
					<span style="float: left;">图书：</span>
					<select id="edu_${status.count}" name="textbook_id" class="st" data-valid="isNonEmpty" data-error="书籍选择不能为空" style="float: left;">
					    	${list.bookSelect}
					</select>
					<input type="hidden" name="sjxz" value="edu_${status.count}" />
					<div style="float: left;margin-left: 30px;" class="ts_radio">
						<table style="width: 280px;">
							<tr>
								<td><input type="radio" name="zw_1_${status.count}" value="1" ${list.preset_position=='1'?'checked':'' }/>主编</td>
								<td><input type="radio" name="zw_1_${status.count}" value="2" ${list.preset_position=='2'?'checked':'' }/>副编委</td>
								<td><input type="radio" name="zw_1_${status.count}" value="3" ${list.preset_position=='3'?'checked':'' }/>编委</td>
								<c:if test="${materialMap.is_digital_editor_optional =='1'}">
								<td><input type="radio" name="zw_1" value="4"/>数字编委</td>
								</c:if>
							</tr>
						</table>
						<!-- 用于遍历radio中的值 -->
						<input type="hidden" name="preset_position" value="zw_1_${status.count}">
					</div>
					<div style="float: left;margin-left: 30px;">
						<span style="float: left;">上传教学大纲：</span>
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
							<input class="cg_input" name="title" value="${gezlList.title}" id="zc"  maxlength="10"/></td>
                        </td>
						<td><span class="btbs">*</span><span>地&emsp;&emsp;址：</span>
							<input class="cg_input" name="address" value="${gezlList.address}" id="address"  maxlength="17"/></td>
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
						<td><span class="btbs">*</span><span style="width: 70px">E-mail：</span>
							<input class="cg_input" name="email" value="${gezlList.email}" id="email" maxlength="40"/></td>
						<td><span class="btbs">*</span><span>证件类型：</span>
                            <select class="select-input" id="zjlx" name="idtype">
                                <option value="0" ${gezlList.idtype=='0'?'selected':'' }>身份证</option>
                                <option value="1" ${gezlList.idtype=='1'?'selected':'' }>护照</option>
                                <option value="2" ${gezlList.idtype=='2'?'selected':'' }>军官证</option>
                            </select></td>
						<td><span class="btbs">*</span><span>证件号码：</span>
							<input class="cg_input" name="idcard" value="${gezlList.idcard}" id="idcard" maxlength="18"/></td>
						<td></td>
					</tr>
				</table>
			</div>
		</div>
		<!--主要学习经历-->
		<div class="sbxq_item" id="zyxxjl">
			<div>
				<span id="tsxz_span3"></span>
				<span class="tsxz_title">主要学习经历</span>
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
						<tr>
							<td>
								<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'" z-index="100" max="'$#xx_jssj'" name="xx_kssj" id="xx_kssj" value="" style="width: 80px;" maxlength="20"/>
								-
								<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'" z-index="100" min="'$#xx_kssj'" name="xx_jssj" id="xx_jssj" value="" style="width: 80px;" maxlength="20"/>
							</td>
							<td><input class="cg_input" name="xx_school_name" value="" placeholder="学校名称"  maxlength="36"/></td>
							<td><input class="cg_input" name="xx_major" value="" placeholder="所学专业"  maxlength="16"/></td>
							<td><input class="cg_input" name="xx_degree" value="" style="width: 120px;" placeholder="学历"  maxlength="10"/></td>
							<td><input class="cg_input" name="xx_note" value="" style="width: 310px;" placeholder="备注"  maxlength="33"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_xxjl()"/></td>
						</tr>
						<c:forEach var="list" items="${stuList}" varStatus="status">
							<tr id="xxjl_${status.count}">
								<td>
									<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'" z-index="100"  name="xx_kssj" max="'$#xx_jssj_${status.count}'" id="xx_kssj_${status.count}" value="${list.date_begin}" style="width: 80px;"/>
									-
									<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'" z-index="100" name="xx_jssj" min="'$#xx_kssj_${status.count}'" id="xx_jssj_${status.count}" value="${list.date_end}" style="width: 80px;"/>
								</td>
								<td><input class="cg_input" name="xx_school_name" value="${list.school_name}" placeholder="学校名称"  maxlength="36"/></td>
								<td><input class="cg_input" name="xx_major" value="${list.major}" placeholder="所学专业"  maxlength="16"/></td>
								<td><input class="cg_input" name="xx_degree" value="${list.degree}" style="width: 120px;" placeholder="学历"  maxlength="10"/></td>
								<td><input class="cg_input" name="xx_note" value="${list.note}" style="width: 310px;" placeholder="备注"  maxlength="33"/></td>
								<td><img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('xxjl_${status.count}')"/></td>
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
						<tr>
							<td>
								<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'" z-index="100"  name="gz_kssj" max="'$#gz_jssj'" id="gz_kssj" value="" style="width: 80px;"/>
								-
								<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="gz_jssj" min="'$#gz_kssj'" id="gz_jssj" value="" style="width: 80px;"/>
							</td>
							<td><input class="cg_input" name="gz_org_name" value="" placeholder="工作单位"  maxlength="33"/></td>
							<td><input class="cg_input" name="gz_position" value="" placeholder="职位"  maxlength="33"/></td>
							<td><input class="cg_input" name="gz_note" value="" style="width: 370px;" placeholder="备注" maxlength="33"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_gzjl()"/></td>
						</tr>
						<c:forEach var="list" items="${workList}" varStatus="status">
							<tr id="gzjl_${status.count}">
								<td>
									<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'" z-index="100"  name="gz_kssj" max="'$#gz_jssj_${status.count}'" id="gz_kssj_${status.count}" value="${list.date_begin}" style="width: 80px;"/>
									-
									<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="gz_jssj" min="'$#gz_kssj_${status.count}'" id="gz_jssj_${status.count}" value="${list.date_end}" style="width: 80px;"/>
								</td>
								<td><input class="cg_input" name="gz_org_name" value="${list.org_name}" placeholder="工作单位" maxlength="33"/></td>
								<td><input class="cg_input" name="gz_position" value="${list.position}" placeholder="职位"  maxlength="33"/></td>
								<td><input class="cg_input" name="gz_note" value="${list.note}" style="width: 370px;" placeholder="备注" maxlength="33"/></td>
								<td><img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('xxjl_${status.count}')"/></td>
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
						<tr>
							<td>
								<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'"  z-index="100"  name="jx_kssj" max="'$#jx_jssj'"  id="jx_kssj" value="" style="width: 80px;"/>
								-
								<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="jx_jssj" min="'#jx_kssj'" id="jx_jssj" value="" style="width: 80px;"/>
							</td>
							<td><input class="cg_input"  maxlength="33" name="jx_school_name" value="" placeholder="学校名称"/></td>
							<td><input class="cg_input" maxlength="50" name="jx_subject" value="" placeholder="教学科目"/></td>
							<td><input class="cg_input" maxlength="33" name="jx_note" value="" style="width: 370px;" placeholder="备注"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jxjl()"/></td>
						</tr>
						<c:forEach var="list" items="${steaList}" varStatus="status">
							<tr id="jxjz_${status.count}">
								<td>
								<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'"  z-index="100"  name="jx_kssj" max="'$#jx_jssj_${status.count}'"  id="jx_kssj_${status.count}" value="${list.date_begin}" style="width: 80px;"/>
								-
								<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="jx_jssj" min="'$#jx_kssj_${status.count}'"  id="jx_jssj_${status.count}" value="${list.date_end}" style="width: 80px;"/>
							</td>
							<td><input class="cg_input" maxlength="33" name="jx_school_name" value="${list.school_name}" placeholder="学校名称"/></td>
							<td><input class="cg_input" maxlength="50" name="jx_subject" value="${list.subject}" placeholder="教学科目"/></td>
							<td><input class="cg_input" maxlength="33" name="jx_note" value="${list.note}" style="width: 370px;" placeholder="备注"/></td>
								<td><img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('jxjz_${status.count}')"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		<!--扩展信息-->
		<c:forEach var="zjkzxx" items="${zjkzxxList}">
		 	<div class="sbxq_item">
				<div>
					<span id="tsxz_span9"></span>
					<span class="tsxz_title">${zjkzxx.extension_name}（选填）</span>
					<input type="hidden" name="extension_id" value="${zjkzxx.id}"/>
				</div>
				<div class="content">
					<textarea class="text_cl" name="content">${zjkzxx.content}</textarea>
				</div>
				<hr style=" height:1px;border:none;border-top:1px #c1c1c1 dashed;margin-top: 30px;">
			</div>
		</c:forEach>
		
		<!--主要学术兼职-->
		<div class="sbxq_item" id="xsjz">
			<div>
				<span id="tsxz_span10"></span>
				<span class="tsxz_title">主要学术兼职</span>
				<span class="tsxz_ts" id="xsjz_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span id="xsjz_xt" class="tsxz_xt">（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_xsjz">
					<thead>
						<tr>
							<td width="220px">兼职学术组织</td>
							<td width="220px">级别</td>
							<td width="220px">职务 </td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="cg_input" maxlength="33" name="xs_org_name" id="xs_org_name" value="" placeholder="学术组织"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 220px;"><tr>
									<td><input type="radio" name="xs_rank_a" value="1" checked="checked"/>国际</td>
									<td><input type="radio" name="xs_rank_a" value="2" />国家</td>
									<td><input type="radio" name="xs_rank_a" value="3" />省部</td>
									<td><input type="radio" name="xs_rank_a" value="4" />其他</td>
								</tr></table>
								<input type="hidden" name="xs_rank" value="xs_rank_a" />
							</td>
							<td><input class="cg_input" maxlength="16" name="xs_position" value="" placeholder="职务"/></td>
							<td><input class="cg_input" maxlength="33" name="xs_note" value="" style="width: 370px;" placeholder="备注"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_xsjz()"/></td>
						</tr>
						<c:forEach var="list" items="${zjxsList}" varStatus="status">
							<tr id="xsjz_${status.count}">
								<td><input class="cg_input" maxlength="33" name="xs_org_name" id="xs_org_name" value="${list.org_name}" placeholder="学术组织"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 220px;"><tr>
									<td><input type="radio" name="xs_rank_${status.count}" value="1" ${list.rank=='1'?'checked':'' }/>国际</td>
									<td><input type="radio" name="xs_rank_${status.count}" value="2" ${list.rank=='2'?'checked':'' }/>国家</td>
									<td><input type="radio" name="xs_rank_${status.count}" value="3" ${list.rank=='3'?'checked':'' }/>省部</td>
									<td><input type="radio" name="xs_rank_${status.count}" value="4" ${list.rank=='4'?'checked':'' }/>其他</td>
								</tr></table>	
									<input type="hidden" name="xs_rank" value="xs_rank_${status.count}" />
							</td>
							<td><input class="cg_input" maxlength="16" name="xs_position" value="${list.position}" placeholder="职务"/></td>
							<td><input class="cg_input" maxlength="33" name="xs_note" value="${list.note}" style="width: 370px;" placeholder="备注"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('xsjz_${status.count}')"/></td>
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
				<span class="tsxz_ts" id="sbjccb_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="sbjccb_xt" >（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_jccb">
					<thead>
						<tr>
							<td width="420px">教材名称</td>
							<td width="320px">编写职务</td>
							<td width="100px">是否数字编委</td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="cg_input" maxlength="33" name="jc_material_name" id="jc_material_name" value="" style="width: 360px;" placeholder="教材名称"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 230px;"><tr>
									<td><input type="radio" name="jc_position_a" value="0" checked="checked"/>无</td>
									<td><input type="radio" name="jc_position_a" value="1" />主编</td>
									<td><input type="radio" name="jc_position_a" value="2" />编委</td>
									<td><input type="radio" name="jc_position_a" value="3" />副编委</td>
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
							<td><input class="cg_input" maxlength="33" name="jc_note" value="" style="width: 230px;" placeholder="备注"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jccb()"/></td>
						</tr>
						<c:forEach var="list" items="${jcbjList}" varStatus="status">
							<tr id="jccb_${status.count}">
								<td><input class="cg_input" maxlength="33" name="jc_material_name" id="jc_material_name" value="${list.material_name}" style="width: 360px;" placeholder="教材名称"/></td>
								<td style="color: #333333;">
									<table class="radio_tb" style="width: 230px;"><tr>
										<td><input type="radio" name="jc_position_${status.count}" value="0" ${list.position=='0'?'checked':'' }/>无</td>
										<td><input type="radio" name="jc_position_${status.count}" value="1" ${list.position=='1'?'checked':'' }/>主编</td>
										<td><input type="radio" name="jc_position_${status.count}" value="2" ${list.position=='2'?'checked':'' }/>编委</td>
										<td><input type="radio" name="jc_position_${status.count}" value="3" ${list.position=='3'?'checked':'' }/>副编委</td>
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
								<td><input class="cg_input" maxlength="33" name="jc_note" value="${list.note}" style="width: 230px;" placeholder="备注"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('jccb_${status.count}')"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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
							<td width="180px">课程全年课时数</td>
							<td width="200px">课程级别</td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="cg_input" maxlength="20" name="gj_course_name" id="gj_course_name" value="" style="width: 300px;" placeholder="课程名称"/></td>
							<td><input class="cg_input" maxlength="20" name="gj_class_hour" value="" style="width: 130px;" placeholder="课时数"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 180px;"><tr>
									<td><input type="radio" name="gj_type_a" value="1" checked="checked"/>国家</td>
									<td><input type="radio" name="gj_type_a" value="2" />省部</td>
									<td><input type="radio" name="gj_type_a" value="3" />学校</td>
								</tr></table>
								<input type="hidden" name="gj_type" value="jp_type_a" />
							</td>
							<td><input class="cg_input" maxlength="20" name="gj_note" value="" style="width: 330px;" placeholder="备注"/>
							</td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jpkcjs('tab_jpkcjs',1)"/></td>
						</tr>
						<c:forEach var="list" items="${gjkcjsList}" varStatus="status">
							<tr id="jpkcjs_${status.count}">
								<td><input class="cg_input" maxlength="20" name="gj_course_name" id="gj_course_name" value="${list.course_name}" style="width: 300px;" placeholder="课程名称"/></td>
								<td><input class="cg_input" maxlength="20" name="gj_class_hour" value="${list.class_hour}" style="width: 130px;" placeholder="课时数"/></td>
								<td style="color: #333333;">
									<table class="radio_tb" style="width: 180px;"><tr>
										<td><input type="radio" name="gj_type_${status.count}" value="1" ${list.position=='1'?'checked':'' }/>国家</td>
										<td><input type="radio" name="gj_type_${status.count}" value="2" ${list.position=='2'?'checked':'' }/>省部</td>
										<td><input type="radio" name="gj_type_${status.count}" value="3" ${list.position=='3'?'checked':'' }/>学校</td>
									</tr></table>
									<input type="hidden" name="gj_type" value="jp_type_${status.count}" />
								</td>
								<td><input class="cg_input" maxlength="20" name="gj_note" value="" style="width: 330px;" placeholder="备注"/>
								</td>
								<td><img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('jpkcjs_${status.count}')"/></td>
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
						<tr>
							<td><input class="cg_input" maxlength="33" name="hj_material_name" id="hj_material_name" value="" style="width: 300px;" placeholder="教材名称"/></td>
							<td><input class="cg_input" maxlength="16" name="hj_isbn" value="" style="width: 110px;" placeholder="标准书号"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 320px;"><tr>
									<td><input type="radio" name="hj_rank_a" value="1" checked="checked" />教育部十二五</td>
									<td><input type="radio" name="hj_rank_a" value="2" />国家卫计委十二五</td>
									<td><input type="radio" name="hj_rank_a" value="3" />其他</td>
								</tr></table>	
								<input type="hidden" name="hj_rank" value="hj_rank_a" />
							</td>
							<td><input class="cg_input" maxlength="33" name="hj_note" value="" style="width: 250px;" placeholder="备注"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_gjghjc()"/></td>
						</tr>
						<c:forEach var="list" items="${gjghjcList}" varStatus="status">
							<tr id="gjghjc_${status.count}">
								<td><input class="cg_input" maxlength="33" name="hj_material_name" id="hj_material_name" value="${list.material_name}" style="width: 300px;" placeholder="教材名称"/></td>
								<td><input class="cg_input" maxlength="16" name="hj_isbn" value="${list.isbn}" style="width: 110px;" placeholder="标准书号"/></td>
								<td style="color: #333333;">
									<table class="radio_tb" style="width: 320px;"><tr>
										<td><input type="radio" name="hj_rank_${status.count}" value="1" ${list.rank=='1'?'checked':'' } />教育部十二五</td>
										<td><input type="radio" name="hj_rank_${status.count}" value="2" ${list.rank=='2'?'checked':'' } />国家卫计委十二五</td>
										<td><input type="radio" name="hj_rank_${status.count}" value="3" ${list.rank=='3'?'checked':'' } />其他</td>
									</tr></table>
									<input type="hidden" name="hj_rank" value="hj_rank_${status.count}" />
								</td>
								<td><input class="cg_input" maxlength="33" name="hj_note" value="${list.note}" style="width: 250px;" placeholder="备注"/></td>
								<td><img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('gjghjc_${status.count}')"/></td>
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
				<span class="tsxz_ts" id="jcbxqk_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="jcbxqk_xt" >（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_jcbx">
					<thead>
						<tr>
							<td width="230px">教材名称</td>
							<td width="120px">教材级别</td>
							<td width="120px">编写职务</td>
							<td width="100px">是否数字编委</td>
							<td width="130px">出版社</td>
							<td width="120px">出版时间</td>
							<td width="120px">标准书号</td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="cg_input" maxlength="33" name="jcb_material_name" id="jcb_material_name" value="" style="width: 200px;" placeholder="教材名称"/></td>
							<td>
								<select id="jcxz" name="jcb_rank">
	                                <option value="1">教育部规划</option>
	                                <option value="2">卫计委规划</option>
	                                <option value="3">区域规划</option>
	                                <option value="4">创新教材</option>
                            	</select>
							</td>
							<td>
								<select id="jcjb" name="jcb_position">
	                                <option value="0">无</option>
	                                <option value="1">主编</option>
	                                <option value="2">副主编</option>
	                                <option value="3">编委</option>
                            	</select>
							</td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 80px;"><tr>
									<td><input type="radio" name="jcb_is_digital_editor_a" value="1" />是</td>
					 				<td><input type="radio" name="jcb_is_digital_editor_a" value="0" checked="checked"/>否</td>
								</tr></table>
								<input type="hidden" name="jcb_is_digital_editor" value="jcb_is_digital_editor_a" />
							</td>
							<td><input class="cg_input" maxlength="16" name="jcb_publisher" value="" style="width: 100px;" placeholder="出版社"/></td>
							<td><input class="cg_input" placeholder="出版时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="jcb_publish_date" value="" style="width: 100px;"/></td>
							<td><input class="cg_input" maxlength="16" name="jcb_isbn" value="" style="width: 100px;" placeholder="标准书号"/></td>
							<td><input class="cg_input" maxlength="33" name="jcb_note" value="" placeholder="备注" style="width: 130px;"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jcbx()"/></td>
						</tr>
						<c:forEach var="list" items="${jcbxList}" varStatus="status">
						<c:if test="${list.rank != '0'}">
						<tr id="jcbx_${status.count}">
							<td><input class="cg_input" name="jcb_material_name" id="jcb_material_name" value="${list.material_name}" style="width: 200px;" placeholder="教材名称"/></td>
							<td>
								<select id="jcxz_${status.count}" name="jcb_rank">
	                                <option value="1" ${list.rank=='1'?'selected':'' }>教育部规划</option>
	                                <option value="2" ${list.rank=='2'?'selected':'' }>卫计委规划</option>
	                                <option value="3" ${list.rank=='3'?'selected':'' }>区域规划</option>
	                                <option value="4" ${list.rank=='4'?'selected':'' }>创新教材</option>
                            	</select>
                            	<input type="hidden" id="jcb_rank_sl" name="jcb_rank_sl" value="jcxz_${status.count}" />
							</td>
							<td>
								<select id="jcjb_${status.count}" name="jcb_position">
	                                <option value="0" ${list.position=='0'?'selected':'' }>无</option>
	                                <option value="1" ${list.position=='1'?'selected':'' }>主编</option>
	                                <option value="2" ${list.position=='2'?'selected':'' }>副主编</option>
	                                <option value="3" ${list.position=='3'?'selected':'' }>编委</option>
                            	</select>
                            	<input type="hidden" id="jcjb_sl" name="jcjb_sl" value="jcjb_${status.count}" />
							</td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 80px;"><tr>
									<td><input type="radio" name="jcb_is_digital_editor_${status.count}" value="1" />是</td>
					 				<td><input type="radio" name="jcb_is_digital_editor_${status.count}" value="0" checked="checked"/>否</td>
								</tr></table>
								<input type="hidden" name="jcb_is_digital_editor" value="jcb_is_digital_editor_${status.count}" />
							</td>
							<td><input class="cg_input" name="jcb_publisher" value="${list.publisher}" style="width: 100px;" placeholder="出版社"/></td>
							<td><input class="cg_input" placeholder="出版时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="jcb_publish_date" value="${list.publish_date}" style="width: 100px;"/></td>
							<td><input class="cg_input" name="jcb_isbn" value="${list.isbn}" style="width: 100px;" placeholder="标准书号"/></td>
							<td><input class="cg_input" name="jcb_note" value="${list.note}" style="width: 130px;" placeholder="备注"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('jcbx_${status.count}')"/></td>
						</tr></c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--其他教材编写情况-->
		<div class="sbxq_item" id="qtjcbxqk">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">其他教材编写情况</span>
				<span class="tsxz_ts" id="qtjcbx_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="qtjcbx_xt" >（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_qtjcbx">
					<thead>
						<tr>
							<td width="250px">教材名称</td>
							<td width="120px">编写职务</td>
							<td width="100px">是否数字编委</td>
							<td width="150px">出版社</td>
							<td width="130px">出版时间</td>
							<td width="130px">标准书号</td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="cg_input" name="jcb_material_name" id="qt_jcb_material_name" value="" style="width: 210px;" placeholder="教材名称"/></td>
							<td>
								<select id="qtjcjb" name="jcb_position">
	                                <option value="0">无</option>
	                                <option value="1">主编</option>
	                                <option value="2">副主编</option>
	                                <option value="3">编委</option>
                            	</select>
							</td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 80px;"><tr>
									<td><input type="radio" name="jcb_is_digital_editor_b" value="1" />是</td>
					 				<td><input type="radio" name="jcb_is_digital_editor_b" value="0" checked="checked"/>否</td>
								</tr></table>
								<input type="hidden" name="jcb_is_digital_editor" value="jcb_is_digital_editor_b" />
							</td>
							<td><input class="cg_input" name="jcb_publisher" value="" style="width: 120px;" placeholder="出版社"/></td>
							<td><input class="cg_input" placeholder="出版时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="jcb_publish_date" value="" style="width: 110px;"/></td>
							<td><input class="cg_input" name="jcb_isbn" value="" style="width: 110px;" placeholder="标准书号"/></td>
							<td><input class="cg_input" name="jcb_note" value="" placeholder="备注" style="width: 180px;"/>
								<input type="hidden" name="jcb_rank" value="0"/>
							</td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_qtjcbx()"/></td>
						</tr>
						<c:forEach var="list" items="${jcbxList}" varStatus="status">
						<c:if test="${list.rank == '0'}">
							<tr id="qtjcbx_${status.count}">
								<td><input class="cg_input" name="jcb_material_name" id="jcb_material_name" value="${list.material_name}" style="width: 210px;" placeholder="教材名称"/></td>
								<td>
									<select id="qtjcjb_${status.count}" name="jcb_position">
		                                <option value="0" ${list.position=='0'?'selected':'' }>无</option>
		                                <option value="1" ${list.position=='1'?'selected':'' }>主编</option>
		                                <option value="2" ${list.position=='2'?'selected':'' }>副主编</option>
		                                <option value="3" ${list.position=='3'?'selected':'' }>编委</option>
	                            	</select>
	                            	<input type="hidden" id="qtjcjb_sl" name="qtjcjb_sl" value="qtjcjb_${status.count}" />
								</td>
								<td style="color: #333333;">
									<table class="radio_tb" style="width: 80px;"><tr>
										<td><input type="radio" name="qtjcb_is_digital_editor_${status.count}" value="1" />是</td>
						 				<td><input type="radio" name="qtjcb_is_digital_editor_${status.count}" value="0" checked="checked"/>否</td>
									</tr></table>
									<input type="hidden" name="jcb_is_digital_editor" value="qtjcb_is_digital_editor_${status.count}" />
								</td>
								<td><input class="cg_input" name="jcb_publisher" value="${list.publisher}" style="width: 120px;" placeholder="出版社"/></td>
								<td><input class="cg_input" placeholder="出版时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="jcb_publish_date" value="${list.publish_date}" style="width: 110px;"/></td>
								<td><input class="cg_input" name="jcb_isbn" value="${list.isbn}" style="width: 110px;" placeholder="标准书号"/></td>
								<td><input class="cg_input" name="jcb_note" value="${list.note}" placeholder="备注" style="width: 180px;"/>
									<input type="hidden" name="jcb_rank" value="0"/>
								</td>
								<td><img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('qtjcbx_${status.count}')"/></td>
							</tr>
						</c:if>
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
						<tr>
							<td><input class="cg_input" maxlength="50" name="zjk_research_name" id="zjk_research_name" value="" style="width: 200px;" placeholder="课题名称"/></td>
							<td><input class="cg_input" maxlength="33" name="zjk_approval_unit" value="" style="width: 200px;" placeholder="审批单位"/></td>
							<td><input class="cg_input" maxlength="33" name="zjk_award" value="" style="width: 300px;" placeholder="获奖情况"/></td>
							<td><input class="cg_input" maxlength="33" name="zjk_note" value="" style="width: 300px;" placeholder="备注"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="add_zjky()"></td>
						</tr>
						<c:forEach var="list" items="${zjkyList}" varStatus="status">
							<tr id="zjky_${status.count}">
								<td><input class="cg_input" maxlength="50" name="zjk_research_name" id="zjk_research_name" value="${list.research_name}" style="width: 200px;" placeholder="课题名称"/></td>
								<td><input class="cg_input" maxlength="33" name="zjk_approval_unit" value="${list.approval_unit}" style="width: 200px;" placeholder="审批单位"/></td>
								<td><input class="cg_input" maxlength="33" name="zjk_award" value="${list.award}" style="width: 300px;" placeholder="获奖情况"/></td>
								<td><input class="cg_input" maxlength="33" name="zjk_note" value="${list.note}" style="width: 300px;" placeholder="备注"/></td>
								<td><img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('zjky_${status.count}')"></td>
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
		<!--主编学术专著情况表-->
		<div class="sbxq_item1" id="zbxszz">
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
							<td width="340px">教材名称</td>
							<td width="120px">是否自费</td>
							<td width="220px">出版单位</td>
							<td width="150px">出版时间</td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="cg_input" name="zb_monograph_name" id="zb_monograph_name" value="" style="width: 300px;" placeholder="教材名称" maxlength="16"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 100px;"><tr>
									<td><input type="radio" name="is_self_paid_a" value="1" checked="checked"/>是</td>
									<td><input type="radio" name="is_self_paid_a" value="2" />否</td>
								</tr></table>
								<input type="hidden" name="is_self_paid" value="is_self_paid_a" />
							</td>
							<td><input class="cg_input" name="zb_publisher" value="" style="width: 180px;" placeholder="出版单位"  maxlength="16"/></td>
							<td><input class="cg_input" name="zb_publish_date" value="" style="width: 120px;" calendar format="'yyyy-mm-dd'" placeholder="出版时间"/></td>
							<td><input class="cg_input" name="zb_note" value="" style="width: 250px;" placeholder="备注"  maxlength="33"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_zbxszz()"/></td>
						</tr>
						<c:forEach var="list" items="${monographList}" varStatus="status">
							<tr id="zbxszz_${status.count}">
								<td><input class="cg_input" name="zb_monograph_name" id="zb_monograph_name" value="${list.monograph_name}" style="width: 300px;" placeholder="教材名称" maxlength="16"/></td>
								<td style="color: #333333;">
									<table class="radio_tb" style="width: 100px;"><tr>
										<td><input type="radio" name="is_self_paid_${status.count}" value="1" ${list.is_self_paid=='1'?'checked':'' }/>是</td>
										<td><input type="radio" name="is_self_paid_${status.count}" value="2"  ${list.is_self_paid=='2'?'checked':'' }/>否</td>
									</tr></table>
									<input type="hidden" name="is_self_paid" value="is_self_paid_${status.count}" />
								</td>
								<td><input class="cg_input" name="zb_publisher" value="${list.publisher}" style="width: 180px;" placeholder="出版单位"  maxlength="16"/></td>
								<td><input class="cg_input" name="zb_publish_date" value="${list.publish_date}" style="width: 120px;" calendar format="'yyyy-mm-dd'" placeholder="出版时间"/></td>
								<td><input class="cg_input" name="zb_note" value="${list.note}" style="width: 250px;" placeholder="备注"  maxlength="33"/></td>
								<td><img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('zbxszz_${status.count}')"></td>
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
				<span class="tsxz_ts" id="publish_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
				<span class="tsxz_xt" id="publish_xt" >（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_publish">
					<thead>
						<tr>
							<td width="340px">奖项名称</td>
							<td width="330px">评奖单位</td>
							<td width="150px">获奖时间</td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="cg_input" name="pu_reward_name" id="pu_reward_name" value="" style="width: 300px;" placeholder="奖项名称" maxlength="16"/></td>
							<td><input class="cg_input" name="pu_award_unit" value="" style="width: 300px;" placeholder="评奖单位" maxlength="16"/></td>
							<td>
								<input class="cg_input" name="pu_reward_date" value="" style="width: 120px;" calendar format="'yyyy-mm-dd'"  placeholder="获奖时间"/>
							</td>
							<td><input class="cg_input" name="pu_note" value="" style="width: 250px;" placeholder="备注" maxlength="33"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_publish()"/></td>
						</tr>
						<c:forEach var="list" items="${publishList}" varStatus="status">
							<tr id="publish_${status.count}">
								<td><input class="cg_input" name="pu_reward_name" id="pu_reward_name" value="${list.reward_name}" style="width: 300px;" placeholder="奖项名称" maxlength="16"/></td>
								<td><input class="cg_input" name="pu_award_unit" value="${list.award_unit}" style="width: 300px;" placeholder="评奖单位" maxlength="16"/></td>
								<td>
									<input class="cg_input" name="pu_reward_date" value="${list.reward_date}" style="width: 120px;" calendar format="'yyyy-mm-dd'"  placeholder="获奖时间"/>
								</td>
								<td><input class="cg_input" name="pu_note" value="${list.note}" style="width: 250px;" placeholder="备注" maxlength="33"/></td>
								<td><img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('publish_${status.count}')"></td>
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
						<tr>
							<td><input class="cg_input" name="sci_paper_name" id="sci_paper_name" value="" style="width: 300px;" placeholder="论文名称" maxlength="16"/></td>
							<td><input class="cg_input" name="sci_journal_name" value="" style="width: 130px;" placeholder="期刊名称" maxlength="16"/></td>
							<td><input class="cg_input" name="sci_factor" value="" style="width: 170px;" placeholder="期刊SCI影响因子" maxlength="7"/></td>
							<td><input class="cg_input" name="sci_publish_date" value="" style="width: 110px;" calendar format="'yyyy-mm-dd'" placeholder="发表时间"/></td>
							<td><input class="cg_input" name="sci_note" value="" style="width: 250px;" placeholder="备注" maxlength="33"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_sci()"/></td>
						</tr>
						<c:forEach var="list" items="${sciList}" varStatus="status">
							<tr id="sci_${status.count}">
								<td><input class="cg_input" name="sci_paper_name" id="sci_paper_name" value="${list.paper_name}" style="width: 300px;" placeholder="论文名称" maxlength="16"/></td>
								<td><input class="cg_input" name="sci_journal_name" value="${list.journal_name}" style="width: 130px;" placeholder="期刊名称" maxlength="16"/></td>
								<td><input class="cg_input" name="sci_factor" value="${list.factor}" style="width: 170px;" placeholder="期刊SCI影响因子" maxlength="7"/></td>
								<td><input class="cg_input" name="sci_publish_date" value="${list.publish_date}" style="width: 110px;" calendar format="'yyyy-mm-dd'" placeholder="发表时间"/></td>
								<td><input class="cg_input" name="sci_note" value="${list.note}" style="width: 250px;" placeholder="备注" maxlength="33"/></td>
								<td><img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('sci_${status.count}')"></td>
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
				<span class="tsxz_title">临床医学获奖情况表</span>
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
						</tr>
						<c:forEach var="list" items="${clinicalList}" varStatus="status">
							<tr id="clinical_${status.count}">
								<td><input class="cg_input" name="cl_reward_name" maxlength="16" id="cl_reward_name" value="${list.reward_name}" style="width: 300px;" placeholder="奖项名称"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 180px;"><tr>
									<td><input type="radio" name="cl_award_unit_${status.count}" ${list.award_unit=='0'?'checked':'' } value="0"/>无</td>
									<td><input type="radio" name="cl_award_unit_${status.count}" ${list.award_unit=='1'?'checked':'' } value="1"/>国际</td>
									<td><input type="radio" name="cl_award_unit_${status.count}" ${list.award_unit=='2'?'checked':'' } value="2"/>国家</td>
								</tr></table>
								<input type="hidden" name="cl_award_unit" value="cl_award_unit_${status.count}" />
							</td>
							<td><input class="cg_input" name="cl_reward_date" value="${list.reward_date}" style="width: 180px;" calendar format="'yyyy-mm-dd'" placeholder="获奖时间"/></td>
							<td><input class="cg_input" name="cl_note" value="${list.note}" style="width: 330px;" placeholder="备注" maxlength="33"/></td>
								<td><img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('clinical_${status.count}')"></td>
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
						</tr>
						<c:forEach var="list" items="${acadeList}" varStatus="status">
							<tr id="acade_${status.count}">
								<td><input class="cg_input" name="ac_reward_name" maxlength="16" id="ac_reward_name" value="${list.reward_name}" style="width: 300px;" placeholder="荣誉名称"/></td>
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
								<td><input class="cg_input" name="ac_reward_date" value="${list.reward_date }" style="width: 150px;" calendar format="'yyyy-mm-dd'" placeholder="授予时间"/></td>
								<td><input class="cg_input" name="ac_note" value="${list.note }" style="width: 280px;" placeholder="备注" maxlength="33"/></td>
								<td><img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('acade_${status.count}')"></td>
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
				<span class="btbs">*</span><span>申报单位：</span>
				<%-- <select id="apply-org" name="edu" class="st_2" >
				    ${gezlList.orgSelect}
				</select>  --%>
				<input class="cg_input" id="sbdw_name" name="sbdw_name" value="${gezlList.dwmc}" style="width: 300px;" onclick="javascript:orgAdd('${materialMap.material_id}')" readonly="readonly"/>
				<input type="hidden" id="sbdw_id" name="sbdw_id" value="${gezlList.org_id}" style="width: 300px;"/>
			</div>
		</div>
		<hr style=" height:1px;border:none;border-top:1px #c1c1c1 dashed;margin-top: 30px;">
		<div class="button">
			<div class="div_butt">
				<div class="bt_tj" onclick="javascript:buttAdd('1')">提交</div>
				<div class="bt_tj" onclick="javascript:buttAdd('2')">暂存</div>
				<div class="bt_tj" onclick="javascript:buttGive()">放弃</div>
			</div>

		</div>
		</form>
	</div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
