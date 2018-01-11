<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String contextpath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<script>
	var contextpath = "${pageContext.request.contextPath}/";
</script>
<%-- <base href="<%=basePath%>"> --%>
<title>个人资料</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=path%>/statics/css/base.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/statics/home/read.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/statics/authadmin/accountset/publicStyle.css"
	rel="stylesheet" type="text/css" />
<script src="<%=path%>/resources/comm/jquery/jquery.js"></script>
<script src="<%=path%>/resources/comm/jquery/jquery.fileupload.js"
	type="text/javascript"></script>
<script src="<%=path%>/resources/comm/jquery/jquery-validate.js"
	type="text/javascript"></script>
<script src="<%=path%>/resources/comm/jquery/jquery.form.js"
	type="text/javascript"></script>
<script src="<%=path%>/statics/js/main/read/read.js"
	type="text/javascript"></script>
<script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js"
	type="text/javascript"></script>
<link href="<%=path%>/statics/css/jquery.selectlist.css"
	rel="stylesheet" type="text/css" />
<script src="<%=path%>/resources/comm/base.js"></script>
<link
	href="<%=path%>/statics/commuser/teachercertification/teacherAttest.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript">
	function submitValidate() {
		if ($("form").validate('submitValidate')) {
			$("#certForm")
					.ajaxSubmit(
							{
								url : contextpath
										+ "teacherCertification/updateTeacherCertification.action",
								type : "post",
								success : function(json) {
									if (json.operatCount > 0) {
										window.message.success("提交成功");
										setTimeout(
												function() {
													window.location.href = contextpath
															+ "userinfo/touser.action?id="
															+ $("#userId")
																	.val();
												}, 1500);
									} else {
										window.message.error("提交失败");
									}
								}, //提交成功后执行的回调函数
								error : function() {
									window.message.error("提交失败");
								}, //提交失败执行的函数
								dataType : "json", //服务器返回数据类型
								clearForm : false, //提交成功后是否清空表单中的字段值
								restForm : false, //提交成功后是否重置表单中的字段值，即恢复到页面加载时的状态
								timeout : 6000
							//设置请求时间，超过该时间后，自动退出请求，单位(毫秒)。　　
							});

			return false;
			//document.getElementById("certForm").submit();
		}
	}

	$(function() {
		$('select').selectlist({
			zIndex : 10,
			width : 264,
			height : 40,
			optionHeight : 40,
			fiter : true
		});

		//$("input[name='orgId']").addClass("required");

		$('#certForm')
				.validate(
						{
							debug : true,
							onFocus : function() {
								this.removeClass("input-error");
								return false;
							},
							onBlur : function() {
								var $parent = this.parent();
								var _status = parseInt(this.attr('data-status'));
								if (!_status) {
									this.addClass("input-error");
									return false;
								}
								return true;
							},
							type : {
								idcard : function(value, errorMsg, el) {
									var idcardReg = /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/;
									if (!idcardReg.test(value)) {
										return errorMsg;
									}
								},
								isSelected : function(value, errorMsg, el) {
									var i = 0;
									var $collection = $("#Select1").find(
											"li[class='selected']");
									if (!$collection.length) {
										return errorMsg;
									}
								},
								isHandphone : function(value, errorMsg, el) {
									var handphoneReg = /^1[3|4|5|8|9][0-9]\d{4,8}$/;
									if (!(handphoneReg.test(value))) {
										return errorMsg;
									}
								}
							}
						/* ,
						submitHandler:function(){
							getElementById("certForm").submit();
						    
						} */
						});
		$("#downLoad_a").html("下载: ${showWriterUserCertification.certName}");
		$("#downLoad_a").unbind().bind("click", function() {
			downLoadProxy("${showWriterUserCertification.cert}");
		});

		$("#sxy-btn-upload").uploadFile({
			accept : "image/*",
			start : function() {
				console.log("开始上传。。。")
			},
			done : function(filename, fileid) {
				console.log("上传完成：name " + filename + " fileid " + fileid);
				$("#cert").val(fileid);
				$("#certName").val(filename);
				$("#downLoad_a").html("下载: " + filename);
				$("#downLoad_a").unbind().bind("click", function() {
					downLoadProxy(fileid);
				});

				window.message.success(filename + "上传成功！");
			},
			progressall : function(loaded, total, bitrate) {
				console.log("正在上传。。。" + loaded / total);
				window.message.info("正在上传。。。" + loaded / total);
			}
		});

	});

	//下载
	function downLoadProxy(fileId) {
		window.location.href = contextpath + 'file/download/' + fileId
				+ '.action';
	}

	$(function() {
		$(".sxy-div-menu").bind("click", function() {
			$(".sxy-span-menu").removeClass("sxy-span-menu");
			$(this).addClass("sxy-span-menu");
		});
	});

	$(function() {
		$("#span-menu1").click(function() {

		});
	});
	$(function() {
		$("#span-menu2").click(function() {

		});
	});
	$(function() {
		$("#span-menu3").click(function() {

		});
	});
</script>
<style type="text/css">
.select-wrapper {
	background: #f1f1f1;
}

.select-button {
	background: #f1f1f1;
	padding-left: 20px;
}
</style>
</head>
<body>

	<jsp:include page="/pages/comm/head.jsp">
		<jsp:param value="homepage" name="pageTitle" />
	</jsp:include>
	<div class="body" style="background: #f6f6f6;">
		<div class="content-wrapper">
			<div style="height: 30px"></div>
			<div class="sxy-div-content">
				<div style="height: 50px;">
					<span style="width: 20px;"></span> <span class="sxy-div-menu">学校教师认证</span>
					<a> <span id="sxy-spantopright" style="cursor: pointer;"
						onclick="window.location='<%=path%>/userinfo/touser.action'"><<返回个人资料
								&nbsp&nbsp</span>
					</a>
				</div>
			</div>
			<div style="height: 14px"></div>
			<div class="sxy-div-content">
				<div style="height: 15px"></div>
				<div>
					<font id="sxy-td-title">学校教师认证说明</font>
				</div>
				<div id="sxy-divtop">
					<div>如果你是学校教师，为了更好的保障你和广大平台用户的合法权益，请你认真填写以下认证信息。</div>
					<div>认证信息审核通过后，你可以在线进行规划教材申报</div>
				</div>

				<div>

					<img class="cupline" alt=""
						src="<%=path%>/statics/image/_cupline.jpg" />

					<form id="certForm"
						action="${pageContext.request.contextPath}/teacherCertification/updateTeacherCertification.action"
						method="post">
						<table border="0" class="form-table" style="margin-left: 20px;">

							<tr class="sxy-tr">
								<td colspan="3"><font class="td-title">学校教师信息登记（<font
										color="#fd9a2e"> <c:choose>
												<c:when test="${showWriterUserCertification.progress==1}"> 
                    	未提交
					   	</c:when>
												<c:when test="${showWriterUserCertification.progress==2}">
                    	已提交
					   	</c:when>
												<c:when test="${showWriterUserCertification.progress==3}"> 
                    	被退回
					   	</c:when>
												<c:when test="${showWriterUserCertification.progress==4}"> 
                    	通过
					   	</c:when>
												<c:otherwise>未提交</c:otherwise>
											</c:choose></font>）
								</font></td>
							</tr>
							<tr class="sxy-tr">
								<td style="width: 10px;"><input class="sxy-txt"
									type="hidden" value="2" name="progress" /> <input
									class="sxy-txt" type="hidden"
									value="${showWriterUserCertification.id}" name="id" /> <input
									class="sxy-txt" type="hidden"
									value="${showWriterUserCertification.userId}" name="userId"
									id="userId" />
									<div class="label-input">
										<label><font color="#ff3d38">*</font>姓名</label>
										<div class="input-wrapper">
											<input id="realName" class="sxy-txt required" type="text"
												value="${showWriterUserCertification.realName}"
												name="realName" data-valid="isNonEmpty"
												data-error="真实姓名不能为空!" />
										</div>
									</div></td>
								<td>
									<div style="margin-bottom: 25px; margin-left: 20px">
										<font class="td-font-3">请填写与与教师资格证上一致的真实姓名</font>
									</div>
								</td>
							</tr>

							<tr class="sxy-tr">
								<td>
									<div class="label-input">
										<label><font color="#ff3d38">*</font>身份证号</label>
										<div class="input-wrapper">
											<input id="idcard" class="sxy-txt required" type="text"
												value="${showWriterUserCertification.idcard}" name="idcard"
												data-valid="idcard" data-error="请填正确的身份证信息!" />
										</div>
									</div>
								</td>
								<td>
									<div style="margin-bottom: 25px; margin-left: 20px">
										<font class="td-font-3">请填正确的身份证信息</font>
									</div>
								</td>
							</tr>

							<tr class="sxy-tr">
								<td>
									<div class="label-input">
										<label><font color="#ff3d38">*</font>选择学校</label>
										<div class="input-wrapper">
											<input type="hidden" class="required" data-valid="isSelected"
												data-error="请选择您所在的学校!"> <select
												class="sxy-select-td" id="Select1" name="orgId">
												<c:forEach var="org"
													items="${showWriterUserCertification.orgList}">
													<option value="${org.id}"
														<c:if test ="${org.id==showWriterUserCertification.orgId}" >selected</c:if>>${org.orgName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</td>
								<td>
									<div style="margin-bottom: 25px; margin-left: 20px">
										<font class="td-font-3">请选择您所在的学校</font>
									</div>
								</td>
							</tr>


							<tr class="sxy-tr">
								<td>
									<div class="label-input">
										<label><font color="#ff3d38">*</font>手机</label>
										<div class="input-wrapper">
											<input id="handphone" class="sxy-txt required" type="text"
												value="${showWriterUserCertification.handphone}"
												name="handphone" data-valid="isHandphone"
												data-error="请填写正确的手机号码!" />
										</div>
									</div>
								</td>
								<td>
									<div style="margin-bottom: 25px; margin-left: 20px">
										<font class="td-font-3">请填写正确的手机号码，以使我们能及时联系到您</font>
									</div>
								</td>
							</tr>

							<tr class="sxy-tr">
								<td>
									<div class="label-input">
										<label><font color="#ff3d38">*</font>教师资格证</label>
										<div class="input-wrapper">
											<input class="sxy-txt" type="hidden"
												value="${showWriterUserCertification.cert}" id="cert"
												name="cert" /> <input class="sxy-txt" type="text"
												value="${showWriterUserCertification.certName}"
												id="certName" name="certcertName" />
										</div>
									</div>
								</td>
								<td>
									<div style="margin-bottom: 65px; margin-left: 20px;">
										<input id="sxy-btn-upload" type="button" value="上传文件" />
										<div id="proxyDiv">
											<c:if test="${showWriterUserCertification.cert !=null}">
												<div class="td-font-2" style="float: left;" id="proxyName">
													<div style="float: left">
														<img alt="" src="<%=path%>/statics/testfile/_al.jpg" />
													</div>
													<div style="float: left; margin-left: 5px">
														<a href="javascript:" class="filename" id="downLoad_a"></a>
													</div>
												</div>
											</c:if>
										</div>
									</div> <%-- <div  id="fileNameDiv" style="float: left;cursor: pointer;padding-left:10px;">
                     	<div style="float:left">
                     		<img alt="" src="<%=path%>/statics/testfile/_al.jpg"/>
                     	</div>
                     	<div style="float:left;margin-left: 5px">
                     		<a href="#javascript:" class="filename" onclick="downLoad()" id="fileName"></a>
                     	</div>
                     </div>  --%>

								</td>
							</tr>

							<tr class="sxy-tr">
								<td colspan="2"><font class="td-font-last">请上传bmp、jpg、gif、jpge、png格式的照片或扫描件</font></td>
							</tr>
						</table>
						<img class="cupline" alt=""
							src="<%=path%>/statics/image/_cupline.jpg" />
						<div class="submit_btn_wrapper">
							<button class="btn-2" onclick="return submitValidate()">提交</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div style="height: 60px; width: 100%;"></div>
	</div>
	<div style="clear: both"></div>
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>



</body>
</html>