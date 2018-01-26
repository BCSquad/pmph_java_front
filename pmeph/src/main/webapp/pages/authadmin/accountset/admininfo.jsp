<%--
  Created by IntelliJ IDEA.
  User: SuiXinYang
  Date: 2017/11/29
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	var contextpath = '${pageContext.request.contextPath}/';
</script>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>个人资料修改(机构用户)</title>
<script src="${ctx}/resources/comm/jquery/jquery.js"
	type="text/javascript"></script>
<script src="${ctx}/resources/comm/jquery/jquery-validate.js"
	type="text/javascript"></script>
<script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"
	type="text/javascript"></script>
<script src="${ctx}/resources/comm/base.js" type="text/javascript"></script>
<script src="${ctx}/resources/comm/json2.js" type="text/javascript"></script>
<script src="${ctx}/resources/authadmin/accountset/admininfo.js"
	type="text/javascript"></script>
 <script src="${ctx}/resources/comm/jquery/jquery.calendar.js"></script>	
     <script src="${ctx}/resources/comm/jquery/jquery.fileupload.js" type="text/javascript"></script>
<link href="${ctx}/statics/css/base.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/statics/authadmin/accountset/publicStyle.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/statics/authadmin/accountset/admininfo.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/statics/css/jquery.selectlist.css" rel="stylesheet"
	type="text/css" />
 <link rel="stylesheet" href="${ctx}/statics/css/jquery.calendar.css" type="text/css">	
<script type="text/javascript">
	
</script>
<style type="text/css">
.select-wrapper {
	background: #f1f1f1;
}

.select-button {
	background: #f1f1f1;
	padding-left: 20px;
}

.select-wrapper {
	border-color: #f1f1f1;
}

.select-list ul li {
	padding-left: 20px;
}

#address {
	width: 1045px;
}
/*input {
            color: #333333;
            height: 40px;
            line-height: 40px;
        }
        #address {
            width: 1070px;
        }

        .line {
            height: 10px;
        }

        .line td {
            height: 10px;
        }

        .font-1 {
            display: inline-block;
            height: 42px;
            line-height: 42px;
            vertical-align: top;
        }*/
</style>
</head>
<body>
	<jsp:include page="/pages/comm/headGreenBackGround.jsp">
		<jsp:param name="pageTitle" value="account"></jsp:param>
	</jsp:include>
	<div class="body">
		<div class="content-wrapper">
			<div style="height: 30px"></div>
			<div class="div-content">
				<div id="div-child">
					<span class="span1"></span> <span class="div-menu-main">个人资料</span>
				</div>
			</div>
			<div style="height: 14px"></div>
			<%--        <div class="div-content" style="height:25px;"></div>--%>
			<div class="div-content">
			<input type="hidden" id="id" value="${admininfo.id}">
				<div class="he">
					<c:if test="${admininfo.avatar=='DEFAULT'}"><img id="sxy-img1" src="${ctx}/statics/image/putongyhtouxiang.png"/></c:if>
                	<c:if test="${admininfo.avatar!='DEFAULT'}"><img id="sxy-img1" src="${ctx}/image/${admininfo.avatar}.action" ></c:if>
                	<div class="modify" id="uploadFile">修改头像</div>
					
					<div class="links">
						<a href="javascript:;" class="manager"
							onclick="window.location='${ctx}/admininfocontroller/toadminattest.action'">学校管理员认证</a>
						<a href="javascript:;" class="password"
							onclick="window.location='${ctx}/admininfocontroller/tochangepwd.action'">修改密码</a>
					</div>
					<div style="clear: both"></div>
				</div>
				<div>
					<font class="td-title">基本信息</font>
				</div>
				<form id="orgForm">
					<table border="0" class="form-table" cellpadding="0"
						cellspacing="0">
						<tr>
							<td>
								<div class="label-input">
									<label class="require">真实姓名</label>
									<div class="input-wrapper">
										<input class="required" data-valid="isNonEmpty"
											data-error="真实姓名不能为空" placeholder="请填写真实姓名" type="text"
											id="realName" value="${admininfo.realname}">
									</div>
								</div>
							</td>
							<td>
								<div class="label-input">
									<label class="require">职务</label>
									<div class="input-wrapper">
										<input class="required" data-valid="isNonEmpty"
											data-error="职务不能为空" placeholder="请填写职务" type="text"
											id="position" value="${admininfo.position}">
									</div>
								</div>
							</td>
							<td>
								<div class="label-input">
									<label>联系电话</label>
									<div class="input-wrapper">
										<input type="text" id="telephone" placeholder="填写联系电话"
											value="${admininfo.telephone}">
									</div>
								</div>
								
								
							</td>
						</tr>
						<tr>
							<td>
								<div class="label-input">
									<label class="require">性别</label>
									<div class="input-wrapper">
										<span class="radio-area required" data-valid="isChecked"
											data-error="性别必选" data-type="radio"> <input
											type="radio" name="sex" value="1"
											${admininfo.sex=='1'?'checked="checked"':''} />男 <input
											type="radio" name="sex" value="2"
											${admininfo.sex=='2'?'checked="checked"':''} />女
										</span>
									</div>
								</div>
							</td>
							<td>
								<%-- <div style="width: 400px">
									<label class="require">教龄</label>
									<div class="input-wrapper">
										<input class="sxy-txt required"
											data-valid="isNonEmpty||minLength:3"
											data-error="教龄不能为空||教龄不能大于3位" placeholder="填写教龄" type="text"
											id="experience" name="experience" value="${admininfo.experience }" />
									</div>
								</div> --%>
								
								
								
								<div class="label-input">
				                    <label class="require" >职称</label>
				                    <div class="input-wrapper">
				                    <input type="hidden" id="title-hidden" value="${admininfo.title}"> 
				                    <select class="sxy-select-td" name="title" id="title" >
				                    	<option value="" >-请选择-</option>
				                        <option value="teacher1" >教授</option>
				                        <option value="teacher2" >讲师</option>
				                        <option value="teacher3" >副教授</option>
				                        <option value="teacher4" >院士</option>
				                    </select>
				                  </div>
								 </div>
								
							</td>
							<td>
								<div class="label-input">
									<label >传真</label>
									<div class="input-wrapper">
										<input type="text" 
											type="text" id="fax"
											value="${admininfo.fax}">
									</div>
								</div>
							</td>
						</tr>
						
						<tr>
							<td>
								<%-- <div style="width: 400px">
									<label class="require">工作单位</label>
									<div class="input-wrapper">
										<input class="sxy-txt required" data-valid="isNonEmpty"
											data-error="工作单位不能为空" placeholder="填写工作单位" type="text"
											id="workplace" name="workplace" value="${admininfo.workplace }" />
									</div>
								</div> --%>
								
								<div class="label-input">
									<label class="require">手机</label>
									<div class="input-wrapper">
										<input class="required" data-valid="isNonEmpty||isMobile"
											data-error="手机不能为空||手机格式不正确" type="text" id="handphone"
											value="${admininfo.handphone}" />
									</div>
								</div>
							</td>
							<td>
								<div class="label-input">
									<label class="require">邮编</label>
									<div class="input-wrapper">
										<input type="text" class="required" data-valid="isNonEmpty"
											data-error="邮编不能为空" type="text" id="postCode"
											value="${admininfo.postcode}">
									</div>
								</div>
							</td>
							<td>
								<div class="label-input">
									<label class="require">E-mail</label>
									<div class="input-wrapper">
										<input type="text" class="required"
											data-valid="isNonEmpty||isEmail"
											data-error="E-mail不能为空||E-mail格式不正确" type="text" id="email"
											value="${admininfo.email}">
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div class="label-input col-3">
									<label class="require">地址</label>
									<div class="input-wrapper">
										<input type="text" class="required" data-valid="isNonEmpty"
											data-error="地址不能为空" id="address" value="${admininfo.address}">
									</div>
								</div>
							</td>
						</tr>
						<tr class="line">
							<td colspan="3" align="center"><img alt="" width="100%"
								src="${ctx}/statics/image/_cupline.jpg" /></td>
						</tr>
						<tr>
							<td colspan="3" align="center"><input class="button"
								type="button" value="保存" onclick="save()" /> 
								<!-- <input class="button" type="button" value="提交" onclick="submit()" /> -->
								<!-- <input class="button" type="button" value="取消"  /> -->
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div style="height: 60px; width: 100%;"></div>
	</div>

	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
