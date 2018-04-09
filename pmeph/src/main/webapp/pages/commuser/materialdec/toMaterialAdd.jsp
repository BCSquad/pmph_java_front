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
	<title>教材申报填写</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/statics/materialdec/materialadd.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/statics/css/jquery.calendar.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css" type="text/css">
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.calendar.js"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/base.js"></script>
	<script src="${ctx}/resources/comm/jquery/jquery.fileupload.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/resources/commuser/materialdec/material.js"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
	<div class="content-wrapper">
		<div style="color: red;font-size: 16px;margin-top: 28px;">（提示：为确保填写成功，请用360极速浏览器或谷歌浏览器）</div>
		<div class="sbxq_title">
			<span><a style="text-decoration: none;color: #999999;" href="${contextpath}/personalhomepage/tohomepage.action?pagetag=dt">个人中心</a> ><a style="text-decoration: none;color: #999999;" href="${contextpath}/personalhomepage/tohomepage.action?pagetag=jcsb&pageNum=1&pageSize=10"> 教材申报 </a> > 填写申报表</span>
		</div>
		<!-- 图书选择-->
		<form id="objForm">
			<div class="sbxq_item1" id="tsxz">
				<div>
					<input type="hidden" id="select_nr" value="${bookSelects}"/>
					<input type="hidden" id="material_id" name="material_id" value="${materialMap.id}"/>
					<input type="hidden" id="declaration_id" name="declaration_id" value=""/>
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
				<div class="item" id="xz1">
					<span style="float: left;line-height: 30px;">图书：</span>
					<select id="edu1" name="textbook_id" class="st book" data-valid="isNonEmpty" data-error="书籍选择不能为空" style="float: left;height: 40px;">
						${bookSelects}
					</select>
					<div style="float: left;margin-left: 30px;" class="ts_radio">
						<table style="width: 260px;border:0" cellspacing="0" cellpadding="0">
							<tr>
								<c:if test="${materialMap.is_multi_position =='1'}">
									<td height="30px;"><input type="checkbox" name="zw_1" checked="checked" value="4"/>主编</td>
									<td><input type="checkbox" name="zw_1" value="2"/>副主编</td>
									<td><input type="checkbox" name="zw_1" value="1"/>编委</td>
									<c:if test="${materialMap.is_digital_editor_optional =='1'}">
										<td><input type="checkbox" name="zw_1" value="8"/>数字编委</td>
									</c:if>
								</c:if>
								<c:if test="${materialMap.is_multi_position !='1'}">
									<td><input type="radio" name="zw_1" checked="checked" value="4"/>主编</td>
									<td><input type="radio" name="zw_1" value="2"/>副主编</td>
									<td><input type="radio" name="zw_1" value="1"/>编委</td>
									<c:if test="${materialMap.is_digital_editor_optional =='1'}">
										<td><input type="radio" name="zw_1" value="8"/>数字编委</td>
									</c:if>
								</c:if>
							</tr>
						</table>
						<!-- 用于遍历radio中的值 -->
						<input type="hidden" name="preset_position" value="zw_1">
					</div>
					<div style="float: left;margin-left: 20px;height: 30px;">
						<span style="float: left;line-height: 30px;">上传教学大纲(只能上传一个文件或压缩包)：</span>
						<div id="fileNameDiv_1" class="fileNameDiv"></div>
						<input type="hidden" name="syllabus_id" id="syllabus_id_1"/>
						<input type="hidden" name="syllabus_name" id="syllabus_name_1"/>
						<div class="scys" id="scjxdg_1"><span>上传文件</span></div>
					</div>
				</div>
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
								<input class="cg_input" name="realname" id="realname" value="${userMap.realname}" maxlength="20"/>
								<input class="cg_input" name="user_id" type="hidden" value="${userMap.id}" />
							</td>
							<td><span class="btbs">*</span><span>性&emsp;&emsp;别：</span>
								<select class="select-input" id="sex" name="sex">
									<option value="1" ${userMap.sex=='1'?'selected':'' }>男</option>
									<option value="2" ${userMap.sex=='2'?'selected':'' }>女</option>
									<option value="0" ${userMap.sex=='0'?'selected':'' }>保密</option>
								</select></td>
							<td><span class="btbs">*</span><span>出生年月：</span>
								<input class="cg_input" calendar format="'yyyy-mm-dd'"  name="birthday" value="${userMap.birthday}"  id="birthday"  /></td>
							<td><span class="btbs">*</span><span>教&emsp;&emsp;龄：</span>
								<input class="cg_input" name="experience" value="${userMap.experience}" id="experience"
									   onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
									   maxlength="2"
								/></td>
						</tr>
						<tr>
							<td><span class="btbs">*</span><span>工作单位：</span>
								<input class="cg_input" name="org_name" value="${userMap.workplace}" id="org_name"  maxlength="60"/></td>
							<td><span class="btbs">*</span><span>职&emsp;&emsp;务：</span>
								<input class="cg_input" name="position" value="${userMap.position}" id="position"  maxlength="36"/></td>
							<td><span class="btbs">*</span><span>职&emsp;&emsp;称：</span>
                                <select id="zclx" name="title">
                                    <option value="院士" ${userMap.title=='院士'?'selected':'' }>院士</option>
                                    <option value="教授"  ${userMap.title=='教授'?'selected':'' }>教授</option>
                                    <option value="正高"  ${userMap.title=='正高'?'selected':'' }>正高</option>
                                    <option value="副教授" ${userMap.title=='副教授'?'selected':'' }>副教授</option>
                                    <option value="副高" ${userMap.title=='副高'?'selected':'' }>副高</option>
                                    <option value="高级讲师" ${userMap.title=='高级讲师'?'selected':'' }>高级讲师</option>
                                    <option value="讲师" ${userMap.title=='讲师'?'selected':'' }>讲师</option>
									<option value="其他" ${userMap.title=='其他'?'selected':'' }>其他</option>
								</select></td>
							<td><span class="btbs">*</span><span style="width: 70px">邮&emsp;&emsp;箱：</span>
								<input class="cg_input" name="email" value="${userMap.email}" id="email"  maxlength="40"/></td>
						</tr>
						<tr>
							<td><span>&ensp;邮&emsp;&emsp;编：</span>
								<input class="cg_input" name="postcode" value="${userMap.postcode}" id="postcode"
									   onblur="LengthLimit(this,20)"
									   maxlength="20"/>
							</td>
							<td><span>&ensp;联系电话：</span>
								<input class="cg_input" name="telephone" value="${userMap.telephone}" id="telephone"
									   onblur="LengthLimit(this,30)"
									   maxlength="30"/>
							</td>
							<td><span>&ensp;传&emsp;&emsp;真：</span>
								<input class="cg_input" name="fax" value="${userMap.fax}" id="fax" onblur="LengthLimit(this,50)" maxlength="50"/>
							</td>
							<td><span class="btbs">*</span><span>手&emsp;&emsp;机：</span>
								<input class="cg_input" name="handphone" value="${userMap.handphone}" id="handphone" maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td><span class="btbs">*</span><span>证件类型：</span>
								<select class="select-input" id="zjlx" name="idtype">
									<option value="0" selected="selected">身份证</option>
									<option value="1">护照</option>
									<option value="2">军官证</option>
								</select></td>
							<td><span class="btbs">*</span><span>证件号码：</span>
								<input class="cg_input" name="idcard" value="${userMap.idcard}" id="idcard"  maxlength="18"/></td>
							<td colspan="2"><span class="btbs">*</span><span>地&emsp;&emsp;址：</span>
								<input class="cg_input" style="width: 488px;" name="address" value="${userMap.address}" id="address"  maxlength="50"/></td>
						</tr>
						<tr>
							<td><span>&ensp;服从调剂：</span>
								<input type="radio" name="is_dispensed" value="1" checked="checked"/>是
								<input type="radio" name="is_dispensed" value="0"/>否
							</td>
							<td><span>&ensp;是否参与本科教学评估认证：</span>
								<input type="radio" name="is_utec" value="1" checked="checked"/>是
								<input type="radio" name="is_utec" value="0"/>否
							</td>
							<td><span>&ensp;学&emsp;&emsp;历：</span>
								<select id="degree" name="degree">
									<option value="0" selected="selected">无</option>
									<option value="1">大专</option>
									<option value="2">本科</option>
									<option value="3">硕士</option>
									<option value="4">博士</option>
								</select></td>
							<td><span>&ensp;专业特长：</span>
								<input class="cg_input" name="expertise" value="" id="expertise" maxlength="50"/>
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
						<tr>
							<td>
								<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'" z-index="100" max="'$#xx_jssj'" name="xx_kssj" id="xx_kssj" value="" style="width: 80px;" maxlength="20"/>
								-
								<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'" z-index="100" min="'$#xx_kssj'" name="xx_jssj" id="xx_jssj" value="" style="width: 80px;" maxlength="20"/>
							</td>
							<td><input class="cg_input" name="xx_school_name" id="xx_school_name" value="" placeholder="学校名称" maxlength="80"/></td>
							<td><input class="cg_input" name="xx_major" id="xx_major" value="" placeholder="所学专业" maxlength="50"/></td>
							<td><input class="cg_input" name="xx_degree" id="xx_degree" value="" style="width: 110px;" placeholder="学历" maxlength="30"/></td>
							<td><input class="cg_input" name="xx_note" id="xx_note" value="" style="width: 290px;" placeholder="备注" maxlength="100"/>
							</td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_xxjl()"/></td>
						</tr>
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
						<tr>
							<td>
								<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'" z-index="100" max="'$#gz_jssj'" name="gz_kssj" id="gz_kssj" value="" style="width: 80px;" maxlength="20"/>
								-
								<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"  z-index="100" min="'$#gz_kssj'" name="gz_jssj" id="gz_jssj" value="" style="width: 80px;" maxlength="20"/>
							</td>
							<td><input class="cg_input" name="gz_org_name" value="" id="gz_org_name" placeholder="工作单位"maxlength="100"/></td>
							<td><input class="cg_input" name="gz_position" value="" id="gz_position" placeholder="职位"maxlength="100" /></td>
							<td><input class="cg_input" name="gz_note" value="" style="width: 410px;" placeholder="备注" maxlength="100"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_gzjl()"/></td>
						</tr>
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
						<tr>
							<td>
								<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'" max="'$#jx_jssj'"  z-index="100"  name="jx_kssj"  id="jx_kssj" value="" style="width: 80px;" maxlength="20"/>
								-
								<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"  min="'$#jx_kssj'" id="jx_jssj" z-index="100" name="jx_jssj" value="" style="width: 80px;" maxlength="20"/>
							</td>
							<td><input class="cg_input" name="jx_school_name" id="jx_school_name" value="" placeholder="学校名称" maxlength="100"/></td>
							<td><input class="cg_input" name="jx_subject" id="jx_subject" value="" placeholder="教学科目" maxlength="150"/></td>
							<td><input class="cg_input" name="jx_note" value="" style="width: 410px;" placeholder="备注" maxlength="100"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jxjl()"/></td>
						</tr>
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
					<textarea class="text_cl" name="gr_content" id="gr_content" maxlength="1000"></textarea>
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
						<tr>
							<td><input class="cg_input" name="xs_org_name" id="xs_org_name" value="" placeholder="学术组织" maxlength="100"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 100%;"><tr>
									<td><input type="radio" name="xs_rank_1" value="0" checked="checked"/>无</td>
									<td><input type="radio" name="xs_rank_1" value="1" />国际</td>
									<td><input type="radio" name="xs_rank_1" value="2" />国家</td>
									<td><input type="radio" name="xs_rank_1" value="3" />省部</td>
									<td><input type="radio" name="xs_rank_1" value="4" />市级</td>
								</tr></table>
								<input type="hidden" name="xs_rank" value="xs_rank_1" />
							</td>
							<td><input class="cg_input" name="xs_position" id="xs_position" value="" placeholder="职务" maxlength="50"/></td>
							<td><input class="cg_input" name="xs_note" value="" style="width: 370px;" placeholder="备注" maxlength="100"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_xsjz()"/></td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!--作家本套上版教材参编情况-->
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
							<td width="280px">教材名称</td>
							<td width="260px">编写职务</td>
							<td width="100px">数字编委</td>
							<td width="120px">出版社</td>
							<td width="120px">出版时间</td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td><input class="cg_input" name="jc_material_name" id="jc_material_name" value="" style="width: 260px;" placeholder="教材名称" maxlength="100"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 100%;"><tr>
									<td><input type="radio" name="jc_position_1" value="0" checked="checked"/>无</td>
									<td><input type="radio" name="jc_position_1" value="1" />主编</td>
									<td><input type="radio" name="jc_position_1" value="2" />副主编</td>
									<td><input type="radio" name="jc_position_1" value="3" />编委</td>
								</tr></table>
								<input type="hidden" name="jc_position" value="jc_position_1" />
							</td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 80px;"><tr>
									<td><input type="radio" name="jc_is_digital_editor_1" value="1" />是</td>
									<td><input type="radio" name="jc_is_digital_editor_1" value="0" checked="checked"/>否</td>
								</tr></table>
								<input type="hidden" name="jc_is_digital_editor" value="jc_is_digital_editor_1" />
							</td>
                            <td><input class="cg_input" name="jc_publisher" value="人民卫生出版社" readonly="true" style="width: 100px;" maxlength="20"/></td>
							<td><input class="cg_input" name="jc_publish_date" id="jc_publish_date" value="" placeholder="出版时间" calendar format="'yyyy-mm-dd'"  z-index="100"  style="width: 100px;"/></td>
							<td><input class="cg_input" name="jc_note" value="" style="width: 190px;" placeholder="备注" maxlength="100"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jccb()"/></td>
						</tr>
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
							<td><input class="cg_input" name="hj_material_name" id="hj_material_name" value="" style="width: 300px;" placeholder="教材名称" maxlength="100"/></td>
							<td><input class="cg_input" name="hj_isbn" id="hj_isbn" value="" style="width: 110px;" placeholder="标准书号" maxlength="50"/></td>
							<td><input class="cg_input" name="hj_rank_text" id="hj_rank_text" value="" style="width: 300px;" placeholder="教材级别" maxlength="50"/></td>
							<td><input class="cg_input" name="hj_note" value="" style="width: 250px;" placeholder="备注" maxlength="100"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_gjghjc()"/></td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!--人卫社教材编写情况表-->
			<div class="sbxq_item" id="rwsjcbx">
				<div>
					<span id="tsxz_span7"></span>
					<span class="tsxz_title">人卫社教材编写情况</span>
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
						<tr>
							<td><input class="cg_input" id="pmph_material_name" name="pmph_material_name" value="" style="width: 200px;" placeholder="教材名称" maxlength="100"/></td>
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
									<td><input type="radio" name="pmph_is_digital_editor_1" value="1" />是</td>
									<td><input type="radio" name="pmph_is_digital_editor_1" value="0" checked="checked"/>否</td>
								</tr></table>
								<input type="hidden" name="pmph_is_digital_editor" value="pmph_is_digital_editor_1" />
							</td>
							<td><input class="cg_input" name="pmph_publish_date" id="pmph_publish_date" value="" placeholder="出版时间" calendar format="'yyyy-mm-dd'"  z-index="100"  style="width: 100px;"/></td>
							<td><input class="cg_input" name="pmph_isbn" id="pmph_isbn" value="" style="width: 100px;" placeholder="978-7-117-" maxlength="50"/></td>
							<td><input class="cg_input" name="pmph_note" id="pmph_note" value="" style="width: 260px;" placeholder="备注" maxlength="100"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_rwsjcbx()"/></td>
						</tr>
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
						<tr>
							<td><input class="cg_input" name="jcb_material_name" id="jcb_material_name" value="" style="width: 200px;" placeholder="教材名称" maxlength="100"/></td>
							<td>
								<select id="jcxz" name="jcb_rank">
									<option value="0">无</option>
									<option value="1">国家</option>
									<option value="2">省部</option>
									<option value="3">协编</option>
									<option value="4">校本</option>
									<option value="5">其他</option>
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
								<table class="radio_tb" style="width: 100%;"><tr>
									<td><input type="radio" name="jcb_is_digital_editor_1" value="1" />是</td>
									<td><input type="radio" name="jcb_is_digital_editor_1" value="0" checked="checked"/>否</td>
								</tr></table>
								<input type="hidden" name="jcb_is_digital_editor" value="jcb_is_digital_editor_1" />
							</td>
							<td><input class="cg_input" name="jcb_publisher" id="jcb_publisher" value="" style="width: 100px;" placeholder="出版社" maxlength="50"/></td>
							<td><input class="cg_input" placeholder="出版时间" id="jcb_publish_date" calendar format="'yyyy-mm-dd'"  z-index="100" name="jcb_publish_date" value="" style="width: 100px;"/></td>
							<td><input class="cg_input" name="jcb_isbn" id="jcb_isbn" value="" style="width: 100px;" placeholder="978-7-" maxlength="50"/></td>
							<td><input class="cg_input" name="jcb_note" value="" style="width: 130px;" placeholder="备注" maxlength="100"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jcbx()"/></td>
						</tr>
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
					<textarea class="text_cl" name="mooc_content" id="mooc_content" maxlength="1000"></textarea>
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
							<td><input class="cg_input" name="gj_course_name" id="gj_course_name" value="" style="width: 300px;" placeholder="课程名称" maxlength="50"/></td>
							<td><input class="cg_input" name="gj_class_hour" id="gj_class_hour" value="" style="width: 130px;" placeholder="课时数" maxlength="50"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 100%;"><tr>
									<td><input type="radio" name="gj_type_1" value="0" checked="checked"/>无</td>
									<td><input type="radio" name="gj_type_1" value="1" />国际</td>
									<td><input type="radio" name="gj_type_1" value="2" />国家</td>
									<td><input type="radio" name="gj_type_1" value="3" />省部</td>
								</tr></table>
								<input type="hidden" name="gj_type" value="gj_type_1" />
							</td>
							<td><input class="cg_input" name="gj_note" value="" style="width: 330px;" placeholder="备注" maxlength="100"/>
							</td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jpkcjs('tab_jpkcjs',1)"/></td>
						</tr>
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
						<tr>
							<td><input class="cg_input" name="zjk_research_name" id="zjk_research_name" value="" style="width: 200px;" placeholder="课题名称" maxlength="150"/></td>
							<td><input class="cg_input" name="zjk_approval_unit" id="zjk_approval_unit" value="" style="width: 200px;" placeholder="审批单位" maxlength="100"/></td>
							<td><input class="cg_input" name="zjk_award" value="" id="zjk_award" style="width: 300px;" placeholder="获奖情况" maxlength="100"/></td>
							<td><input class="cg_input" name="zjk_note" value="" style="width: 300px;" placeholder="备注" maxlength="100"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="add_zjky()"></td>
						</tr>
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
						<tr>
							<td><input class="cg_input" name="zb_monograph_name" id="zb_monograph_name" value="" style="width: 200px;" placeholder="专著名称" maxlength="50"/></td>
							<td><input class="cg_input" name="zb_monograph_date" id="zb_monograph_date" value="" style="width: 120px;" calendar format="'yyyy-mm-dd'" placeholder="发表日期"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 140px;"><tr>
									<td><input type="radio" name="is_self_paid_1" value="0" checked="checked"/>公费</td>
									<td><input type="radio" name="is_self_paid_1" value="1" />自费</td>
								</tr></table>
								<input type="hidden" name="is_self_paid" value="is_self_paid_1" />
							</td>
							<td><input class="cg_input" name="zb_publisher" id="zb_publisher" value="" style="width: 180px;" placeholder="出版单位"  maxlength="50"/></td>
							<td><input class="cg_input" name="zb_publish_date" id="zb_publish_date" value="" style="width: 120px;" calendar format="'yyyy-mm-dd'" placeholder="出版时间"/></td>
							<td><input class="cg_input" name="zb_note" value="" style="width: 200px;" placeholder="备注"  maxlength="100"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_zbxszz()"/></td>
						</tr>
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
						<tr>
							<td><input class="cg_input" name="pu_reward_name" id="pu_reward_name" value="" style="width: 300px;" placeholder="奖项名称" maxlength="50"/></td>
							<td><input class="cg_input" name="pu_award_unit" id="pu_award_unit" value="" style="width: 300px;" placeholder="评奖单位" maxlength="50"/></td>
							<td>
								<input class="cg_input" name="pu_reward_date" id="pu_reward_date" value="" style="width: 120px;" calendar format="'yyyy-mm-dd'"  placeholder="获奖时间"/>
							</td>
							<td><input class="cg_input" name="pu_note" value="" style="width: 250px;" placeholder="备注" maxlength="100"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_publish()"/></td>
						</tr>
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
						<tr>
							<td><input class="cg_input" name="sci_paper_name" id="sci_paper_name" value="" style="width: 300px;" placeholder="论文名称" maxlength="100"/></td>
							<td><input class="cg_input" name="sci_journal_name" id="sci_journal_name"  value="" style="width: 130px;" placeholder="期刊名称" maxlength="50"/></td>
							<td><input class="cg_input" name="sci_factor" id="sci_factor" value="" style="width: 170px;" placeholder="期刊SCI影响因子" maxlength="20"/></td>
							<td><input class="cg_input" name="sci_publish_date" id="sci_publish_date" value="" style="width: 110px;" calendar format="'yyyy-mm-dd'" placeholder="发表时间"/></td>
							<td><input class="cg_input" name="sci_note" value="" style="width: 250px;" placeholder="备注" maxlength="100"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_sci()"/></td>
						</tr>
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
						<tr>
							<td><input class="cg_input" name="cl_reward_name" maxlength="50" id="cl_reward_name" value="" style="width: 300px;" placeholder="奖项名称"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width: 180px;"><tr>
									<td><input type="radio" name="cl_award_unit_1" value="0" checked="checked"/>无</td>
									<td><input type="radio" name="cl_award_unit_1" value="1"/>国际</td>
									<td><input type="radio" name="cl_award_unit_1" value="2" />国家</td>
								</tr></table>
								<input type="hidden" name="cl_award_unit" value="cl_award_unit_1" />
							</td>
							<td><input class="cg_input" name="cl_reward_date" id="cl_reward_date" value="" style="width: 180px;" calendar format="'yyyy-mm-dd'" placeholder="获奖时间"/></td>
							<td><input class="cg_input" name="cl_note" value="" style="width: 330px;" placeholder="备注" maxlength="100"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_clinical()"/></td>
						</tr>
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
						<tr>
							<td><input class="cg_input" name="ac_reward_name" maxlength="50" id="ac_reward_name" value="" style="width: 300px;" placeholder="荣誉名称"/></td>
							<td style="color: #333333;">
								<table class="radio_tb" style="width:280px;"><tr>
									<td><input type="radio" name="ac_award_unit_1" value="0" checked="checked"/>无</td>
									<td><input type="radio" name="ac_award_unit_1" value="1"/>国际</td>
									<td><input type="radio" name="ac_award_unit_1" value="2"/>国家</td>
									<td><input type="radio" name="ac_award_unit_1" value="3"/>省部</td>
									<td><input type="radio" name="ac_award_unit_1" value="4" />市级</td>
								</tr></table>
								<input type="hidden" name="ac_award_unit" value="ac_award_unit_1" />
							</td>
							<td><input class="cg_input" id="ac_reward_date" name="ac_reward_date" value="" style="width: 150px;" calendar format="'yyyy-mm-dd'" placeholder="授予时间"/></td>
							<td><input class="cg_input" name="ac_note" value="" style="width: 280px;" placeholder="备注" maxlength="100"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_acade()"/></td>
						</tr>
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
					<textarea class="text_cl" name="intention_content" id="intention_content" maxlength="1000"></textarea>
				</div>
			</div>
			<!--扩展信息-->
			<c:forEach var="zjkzxx" items="${zjkzxxList}" varStatus="status">
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
						<input type="hidden" name="extension_id" value="${zjkzxx.id}"/>
					</div>
					<div class="content">
						<textarea class="text_cl" id="${zjkzxx.is_required}_${status.count}" name="kz_content" maxlength="1000"></textarea>
						<input type="hidden" name="zjkzxx" value="${zjkzxx.is_required}_${status.count}"/>
					</div>
				</div>
			</c:forEach>
			<!-- 申报单位-->
			<div class="sbxq_item1">
				<div>
					<span id="tsxz_span8"></span>
					<span class="tsxz_title">请选择您的申报单位</span>
				</div>
				<div class="sbdw">
					<span class="btbs" style="color: red">*</span><span class="btmc">申报单位：</span>
					<%-- <select id="apply-org" name="edu" class="st_2" >
                        ${orgSelects}
                    </select>  --%>
					<input class="cg_input" id="sbdw_name" name="sbdw_name" value="" style="width: 300px;" onclick="javascript:orgAdd('${materialMap.id}')" readonly="readonly"/>
					<input type="hidden" id="sbdw_id" name="sbdw_id" value="" style="width: 300px;"/>
				</div>
			</div>
			<hr style=" height:1px;border:none;border-top:1px #c1c1c1 dashed;margin-top: 30px;">
			<div class="button">
				<div class="div_butt">
					<div class="bt_tj" id="butj" onclick="javascript:buttAdd('1')">提交</div>
					<div class="bt_tj" id="buzc" onclick="javascript:buttAdd('2')">暂存</div>
					<%--<div class="bt_tj" onclick="javascript:buttGive()">放弃</div>--%>
				</div>
				<div style="color: red;font-size: 16px;">（提示：如暂存或提交不成功请使用360浏览器极速模式或谷歌浏览器）</div>
			</div>
		</form>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
