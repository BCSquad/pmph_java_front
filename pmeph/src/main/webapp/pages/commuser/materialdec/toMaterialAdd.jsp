<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>申报表添加</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/materialdec/materialadd.css" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/css/jquery.calendar.css" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css" type="text/css">
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/commuser/materialdec/material.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.calendar.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
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
		<div class="sbxq_item" id="tsxz">
			<div>
				<input type="hidden" id="select_nr" value="${bookSelects}"/>
				<input type="hidden" name="material_id" value="${materialMap.id}"/>
				<span id="tsxz_span1"></span>
				<span class="tsxz_title">图书选择(只能选择一本书籍，一本书籍只能选择一个职位。) </span>
				<span class="tsxz_ts"><img src="${ctx}/statics/image/btxx.png" /></span>
				<div class="addBtn pull-right" onclick="javascript:addTsxz()"><span>增加</span></div>
			</div>
			<div class="item" id="xz1">
				<span style="float: left;">图书：</span>
				<select id="edu1" name="textbook_id" class="st book" style="float: left;">
					<option value="">请选择书籍</option>
				    	${bookSelects}
				</select>
				<div style="float: left;margin-left: 30px;" class="ts_radio">
					<input type="radio" name="preset_position" checked="checked" value="1"/>主编
					<input type="radio" name="preset_position" value="2"/>副编委
					<input type="radio" name="preset_position" value="3"/>编委
				</div>
				<div style="float: left;margin-left: 30px;">
					<span style="float: left;">上传教学大纲：</span>
					<div class="scys"><span>上传文件</span></div>
				</div>
			</div>
		</div>
		<!-- 专家信息-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span2"></span>
				<span class="tsxz_title">专家信息</span>
				<span class="tsxz_ts"><img src="${ctx}/statics/image/btxx.png" /></span>
			</div>
			<div class="content">
				<table class="tab_1">
					<tr>
						<td><span class="btbs">*</span><span>姓&emsp;&emsp;名：</span>
							<input class="cg_input" name="realname" value="" />
							<input class="cg_input" name="user_id" type="hidden" value="${userMap.id}" />
							</td>
						<td><span class="btbs">*</span><span>性&emsp;&emsp;别：</span>
                            <select class=" select-input" id="sex" name="sex">
                                <option value="0">保密</option>
                                <option value="1">男</option>
                                <option value="2">女</option>
                            </select></td>
						<td><span class="btbs">*</span><span>出生年月：</span>
							<input class="cg_input" calendar format="'yyyy-mm-dd'" offset="[3,9]" z-index="100"  name="birthday" value="" /></td>
						<td><span class="btbs">*</span><span>教&emsp;&emsp;龄：</span>
							<input class="cg_input" name="experience" value="" /></td>
					</tr>
					<tr>
						<td><span class="btbs">*</span><span>工作单位：</span>
							<input class="cg_input" name="org_name" value="" /></td>
						<td><span class="btbs">*</span><span>职&emsp;&emsp;务：</span>
							<input class="cg_input" name="position" value="" /></td>
						<td><span class="btbs">*</span><span>职&emsp;&emsp;称：</span>
                            <select class="select-input" id="zc" name="title">
                                <option value="0">教授</option>
                                <option value="1">主任</option>
                                <option value="3">一级教师</option>
                            </select></td>
						<td><span class="btbs">*</span><span>地&emsp;&emsp;址：</span>
							<input class="cg_input" name="address" value="" /></td>
					</tr>
					<tr>
						<td><span class="btbs">*</span><span>邮&emsp;&emsp;编：</span>
							<input class="cg_input" name="postcode" value="" />
						</td>
						<td><span>&ensp;联系电话：</span>
							<input class="cg_input" name="telephone" value="" />
						</td>
						<td><span>&ensp;传&emsp;&emsp;真：</span>
							<input class="cg_input" name="fax" value="" />
						</td>
						<td><span class="btbs">*</span><span>手&emsp;&emsp;机：</span>
							<input class="cg_input" name="handphone" value="" />
						</td>
					</tr>
					<tr>
						<td><span class="btbs">*</span><span style="width: 70px">E-mail：</span>
							<input class="cg_input" name="email" value="" /></td>
						<td><span class="btbs">*</span><span>证件类型：</span>
                            <select class="select-input" id="zjlx" name="idtype">
                                <option value="0">身份证</option>
                                <option value="1">护照</option>
                                <option value="2">军官证</option>
                            </select></td>
						<td><span class="btbs">*</span><span>证件号码：</span>
							<input class="cg_input" name="idcard" value="" /></td>
						<td></td>
					</tr>
				</table>
			</div>
		</div>
		<!--主要学习经历-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span3"></span>
				<span class="tsxz_title">主要学习经历</span>
				<span class="tsxz_ts"><img src="${ctx}/statics/image/btxx.png" /></span>
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
								<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'" z-index="100"  name="xx_kssj" value="" style="width: 80px;"/>
								-
								<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'" z-index="100" name="xx_jssj" value="" style="width: 80px;"/>
							</td>
							<td><input class="cg_input" name="xx_school_name" value="" /></td>
							<td><input class="cg_input" name="xx_major" value="" /></td>
							<td><input class="cg_input" name="xx_degree" value="" style="width: 120px;"/></td>
							<td><input class="cg_input" name="xx_note" value="" style="width: 310px;"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_xxjl()"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
			<!--主要工作经历-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span4"></span>
				<span class="tsxz_title">主要工作经历</span>
				<span class="tsxz_ts"><img src="${ctx}/statics/image/btxx.png" /></span>
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
								<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'" z-index="100"  name="gz_kssj" value="" style="width: 80px;"/>
								-
								<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="gz_jssj" value="" style="width: 80px;"/>
							</td>
							<td><input class="cg_input" name="gz_org_name" value="" /></td>
							<td><input class="cg_input" name="gz_position" value="" /></td>
							<td><input class="cg_input" name="gz_note" value="" style="width: 370px;"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_gzjl()"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
			<!--主要教学经历-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span5"></span>
				<span class="tsxz_title">主要教学经历</span>
				<span class="tsxz_ts"><img src="${ctx}/statics/image/btxx.png" /></span>
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
								<input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'"  z-index="100"  name="jx_kssj" value="" style="width: 80px;"/>
								-
								<input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="jx_jssj" value="" style="width: 80px;"/>
							</td>
							<td><input class="cg_input" name="jx_school_name" value="" /></td>
							<td><input class="cg_input" name="jx_subject" value="" /></td>
							<td><input class="cg_input" name="jx_note" value="" style="width: 370px;"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jxjl()"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!--主要学术兼职-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span10"></span>
				<span class="tsxz_title">主要学术兼职（选填）</span>
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
							<td><input class="cg_input" name="org_name" value="" /></td>
							<td style="color: #333333;">
								<input type="radio" name="xs_rank" value="1" checked="checked"/>国际
								<input type="radio" name="xs_rank" value="2" />国家
								<input type="radio" name="xs_rank" value="3" />省部
								<input type="radio" name="xs_rank" value="4" />其他
							</td>
							<td><input class="cg_input" name="xs_position" value="" /></td>
							<td><input class="cg_input" name="xs_note" value="" style="width: 370px;"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_xsjz()"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
			<!--上版教材参编情况-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span6"></span>
				<span class="tsxz_title">上版教材参编情况</span>
				<span class="tsxz_ts"><img src="${ctx}/statics/image/btxx.png" /></span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_jccb">
					<thead>
						<tr>
							<td width="420px">教材名称</td>
							<td width="320px">编写职务</td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="cg_input" name="jc_material_name" value="" style="width: 360px;"/></td>
							<td style="color: #333333;">
								<input type="radio" name="jc_position" value="0" checked="checked"/>无
								<input type="radio" name="jc_position" value="1" />主编
								<input type="radio" name="jc_position" value="2" />编委
								<input type="radio" name="jc_position" value="3" />副编委</td>
							<td><input class="cg_input" name="jc_note" value="" style="width: 330px;"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jccb()"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
			<!--国家级精品课程建设-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">国家级精品课程建设情况（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_jpkcjs">
					<thead>
						<tr>
							<td width="420px">课程名称</td>
							<td width="200px">该课程全年课时数</td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="cg_input" name="gj_course_name" value="" style="width: 370px;"/></td>
							<td><input class="cg_input" name="gj_class_hour" value="" style="width: 170px;"/></td>
							<td><input class="cg_input" name="gj_note" value="" style="width: 450px;"/>
								<input type="hidden" name="gj_type" value="1"/>
							</td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jpkcjs('tab_jpkcjs',1)"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!--省部级课程建设-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">省部级课程建设情况（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_sjkcjs">
					<thead>
						<tr>
							<td width="420px">课程名称</td>
							<td width="200px">该课程全年课时数</td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="cg_input" name="gj_course_name" value="" style="width: 370px;"/></td>
							<td><input class="cg_input" name="gj_class_hour" value="" style="width: 170px;"/></td>
							<td><input class="cg_input" name="gj_note" value="" style="width: 450px;"/>
								<input type="hidden" name="gj_type" value="2"/>
							</td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jpkcjs('tab_sjkcjs',2)"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!--作家国家级精品课程建设-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">学校课程建设情况（选填）</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_xskcjs">
					<thead>
						<tr>
							<td width="420px">课程名称</td>
							<td width="200px">该课程全年课时数</td>
							<td>备注</td>
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="cg_input" name="gj_course_name" value="" style="width: 370px;"/></td>
							<td><input class="cg_input" name="gj_class_hour" value="" style="width: 170px;"/></td>
							<td><input class="cg_input" name="gj_note" value="" style="width: 450px;"/>
								<input type="hidden" name="gj_type" value="3"/>
							</td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jpkcjs('tab_xskcjs',3)"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!--主编国家级规划教材情况-->
		<div class="sbxq_item">
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
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="cg_input" name="hj_material_name" value="" style="width: 300px;"/></td>
							<td><input class="cg_input" name="hj_isbn" value="" style="width: 110px;"/></td>
							<td style="color: #333333;">
								<input type="radio" name="hj_rank" value="1" checked="checked" />教育部十二五
								<input type="radio" name="hj_rank" value="2" />国家卫计委十二五
								<input type="radio" name="hj_rank" value="3" />其他
							</td>
							<td><input class="cg_input" name="hj_note" value="" style="width: 250px;"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_gjghjc()"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
			<!--教材编写情况-->
		<div class="sbxq_item">
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
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="cg_input" name="jcb_material_name" value="" style="width: 210px;"/></td>
							<td>
								<select id="jcxz" name="jcb_rank">
	                                <option value="0">其他教材</option>
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
							<td><input class="cg_input" name="jcb_publisher" value="" style="width: 120px;"/></td>
							<td><input class="cg_input" placeholder="出版时间" calendar format="'yyyy-mm-dd'"  z-index="100" name="jcb_publish_date" value="" style="width: 110px;"/></td>
							<td><input class="cg_input" name="jcb_isbn" value="" style="width: 110px;"/></td>
							<td><input class="cg_input" name="jcb_note" value=""/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_jcbx()"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		<div>
		<!--作家科研情况-->
		<div class="sbxq_item">
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
							<td width="78px">添加</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="cg_input" name="zjk_research_name" value="" style="width: 200px;"/></td>
							<td><input class="cg_input" name="zjk_approval_unit" value="" style="width: 200px;"/></td>
							<td><input class="cg_input" name="zjk_award" value="" style="width: 300px;"/></td>
							<td><input class="cg_input" name="zjk_note" value="" style="width: 300px;"/></td>
							<td><img class="add_img" src="${ctx}/statics/image/add.png"/ onclick="add_zjky()"></td>
						</tr>
					</tbody>
				</table>
			</div>
		<div>
			<!--个人成就-->
	<!-- 	<div class="sbxq_item">
			<div>
				<span id="tsxz_span9"></span>
				<span class="tsxz_title">个人成就（选填）</span>
			</div>
			<div class="content">
				<textarea class="text_cl">填写我的成就...</textarea>
			</div>
			<hr style=" height:1px;border:none;border-top:1px #c1c1c1 dashed;margin-top: 30px;">
		</div> -->
		<!-- 申报单位-->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span8"></span>
				<span class="tsxz_title">请选择你的申报单位</span>
			</div>
			<div class="sbdw">
				<span class="btbs">*</span><span>申报单位：</span>
				<select id="apply-org" name="edu" class="st_2" >
				    <option value="0">武汉大学</option>
				    <option value="1">北京大学1</option>
                    <option value="2">北京大学2</option>
                    <option value="3">家里蹲大学</option>
                    <option value="4">草根大学</option>
				</select>
			</div>
		</div>
		<hr style=" height:1px;border:none;border-top:1px #c1c1c1 dashed;margin-top: 30px;">
		<div class="button">
			<div class="div_butt">
				<div class="bt_tj" onclick="javascript:buttAdd()">提交</div>
				<div class="bt_tj">暂存</div>
				<div class="bt_tj">放弃</div>
			</div>

		</div>
		</form>
	</div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
