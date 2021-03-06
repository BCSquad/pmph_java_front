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
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%-- <base href="<%=basePath%>"> --%>
<title>个人资料</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=path%>/statics/css/base.css?t=${_timestamp}" rel="stylesheet"
	type="text/css" />
<%-- <link href="<%=path%>/statics/home/read.css?t=${_timestamp}" rel="stylesheet"
	type="text/css" /> --%>
<link href="<%=path%>/statics/authadmin/accountset/publicStyle.css?t=${_timestamp}"
	rel="stylesheet" type="text/css" />
<script src="<%=path%>/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
<script src="<%=path%>/resources/comm/jquery/jquery.fileupload.js?t=${_timestamp}"
	type="text/javascript"></script>
<script src="<%=path%>/resources/comm/jquery/jquery-validate.js?t=${_timestamp}"
	type="text/javascript"></script>
<script src="<%=path%>/resources/comm/jquery/jquery.form.js?t=${_timestamp}"
	type="text/javascript"></script>
<script src="<%=path%>/statics/js/main/read/read.js"
	type="text/javascript"></script>
<script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"
	type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.tipso.js?t=${_timestamp}"></script>
<link href="<%=path%>/statics/css/jquery.selectlist.css?t=${_timestamp}"
	rel="stylesheet" type="text/css" />
<script src="<%=path%>/resources/comm/base.js?t=${_timestamp}"></script>
<link
	href="<%=path%>/statics/commuser/teachercertification/teacherAttest.css?t=${_timestamp}"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/statics/css/jquery.tipso.css?t=${_timestamp}" type="text/css">

<script type="text/javascript">
	function submitValidate() {
		if ($.fireValidator()) {
			$("#certForm").ajaxSubmit(
							{
								url : contextpath
										+ "teacherCertification/updateTeacherCertification.action",
								type : "post",
								success : function(json) {
									if (json.operatCount > 0) {
										window.message.success("提交成功");
										setTimeout(
												function() {
													/* window.location.href = contextpath
															+ "userinfo/touser.action?id="
															+ $("#userId").val(); */
													location.reload(true);  
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
		}else{
			//window.message.error("请按要求填写必填信息！");
			return false;
		}
	}

	$(function() {

        setTimeout(function () {
            $('#realName').tipso({validator: "isNonEmpty", message: "姓名不能为空"});
            $('#idcard').tipso({validator: "isNonEmpty|isCard", message: "身份证号不能为空|请输入正确的身份证号码"});
            $('#Select1').tipso({validator: "isNonEmpty", message: "请选择学校"});
            $('#certName').tipso({validator: "isNonEmpty", message: "请上传教师资格证"});
            $('#handphone').tipso({validator: "isNonEmpty|isMobile", message: "手机号码不能为空|手机号码格式不正确"});
        },0)

		$('select').selectlist({
			zIndex : 10,
			width : 264,
			height : 40,
			optionHeight : 40,
			fiter : true
		});
		$('.select-list li[data-value=""]').remove();

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
									var select_id = $("#Select1 input[type='hidden']").val();
									if (!$collection.length || select_id == "") {
										return errorMsg;
									}
								},
								isHandphone : function(value, errorMsg, el) {
									var handphoneReg = /^1[3|4|5|8|9][0-9]\d{4,8}$/;
									if (!(handphoneReg.test(value))) {
										return errorMsg;
									}
								},
								maxLength : function(value, errorMsg, el){
									var v = $.trim(value);
							        var n = '';
								    var b = 0;
									var tooLong = false;
									var param = 9;
								    for (var i = 0; i < v.length; i++) {
								        var c = v.slice(i, i + 1);
								        if (b <= param) {
								        	 n += c;
								    	}else{
								    	tooLong = true;
								    	break;
								    	}
								     b++;
								    }
									$(el).val(n);
									if (tooLong) {
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
				var tail = filename.substring(filename.lastIndexOf("."));
				
				if ($.inArray(tail,['.bmp','.jpg','.gif','.jpeg','.png'])!=-1) {
					$("#cert").val(fileid);
					$("#certName").val(filename);
					$("#downLoad_a").html("下载: " + filename);
					$("#downLoad_a").unbind().bind("click", function() {
						downLoadProxy(fileid);
					});
					window.message.success(filename + "上传成功！");
				}else{
					window.message.warning("请上传bmp、jpg、gif、jpeg、png格式的照片或扫描件!");
				}
				
				
				
			}/* ,
			progressall : function(loaded, total, bitrate) {
				console.log("正在上传。。。" + loaded / total);
				window.message.info("正在上传。。。" + loaded / total); 
			} */
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
						onclick="window.location='<%=path%>/userinfo/touser.action'"><<返回个人资料&nbsp;&nbsp;</span>
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
					<div>如果您是学校教师，为了更好的保障您和广大平台用户的合法权益，请您认真填写以下认证信息。</div>
					<div>认证信息审核通过后，您可以在线进行规划教材申报</div>
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
												<c:when test="${showWriterUserCertification.progress==0}"> 
                    	未提交
					   	</c:when>
												<c:when test="${showWriterUserCertification.progress==1}">
                    	已提交
					   	</c:when>
												<c:when test="${showWriterUserCertification.progress==2}"> 
                    	被退回
					   	</c:when>
												<c:when test="${showWriterUserCertification.progress==3}"> 
                    	已通过
					   	</c:when>
												<c:otherwise>未提交</c:otherwise>
											</c:choose></font>）
								</font></td>
							</tr>
							<tr class="sxy-tr">
								<td style="width: 10px;"><input class="sxy-txt"
									type="hidden" value="1" name="progress" /> <input
									class="sxy-txt" type="hidden"
									value="${showWriterUserCertification.id}" name="id" /> <input
									class="sxy-txt" type="hidden"
									value="${showWriterUserCertification.userId}" name="userId"
									id="userId" />
									<div class="label-input">
										<label><font color="#ff3d38">*</font>姓名</label>
										<div class="input-wrapper">
											<input id="realName" class="sxy-txt" type="text"
												value="${showWriterUserCertification.realName}"
												name="realName"
												<%-- <c:if test="${showWriterUserCertification.progress != null&&(showWriterUserCertification.progress==1 || showWriterUserCertification.progress == 3)}">disabled="disabled"</c:if> --%>
												/>
												
										</div>
									</div></td>
								<td>
									<div style="margin-bottom: 25px; margin-left: 20px">
										<font class="td-font-3">请填写与教师资格证上一致的真实姓名</font>
									</div>
								</td>
							</tr>

							<tr class="sxy-tr">
								<td>
									<div class="label-input">
										<label><font color="#ff3d38">*</font>身份证号</label>
										<div class="input-wrapper">
											<input id="idcard" class="sxy-txt" type="text"
												value="${showWriterUserCertification.idcard}" name="idcard"
												<%-- <c:if test="${showWriterUserCertification.progress != null&&(showWriterUserCertification.progress==1 || showWriterUserCertification.progress == 3)}">disabled="disabled"</c:if> --%>
												/>
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
											<input type="hidden" class="">
											
											<%-- <c:choose>
												<c:when test="${showWriterUserCertification.progress==null||showWriterUserCertification.progress==0||showWriterUserCertification.progress==2}"> --%>
													<select class="sxy-select-td " id="Select1" name="orgId" 
														<%-- <c:if test="${showWriterUserCertification.progress != null&&(showWriterUserCertification.progress==1 || showWriterUserCertification.progress == 3)}">disabled="disabled"</c:if> --%> >
														<option value=""></option>
														<c:forEach var="org"
															items="${showWriterUserCertification.orgList}">
															<option value="${org.id}"
																<c:if test ="${org.id==showWriterUserCertification.orgId}" >selected</c:if>>${org.orgName}</option>
														</c:forEach>
													</select>
												<%-- </c:when>
												<c:when test="${showWriterUserCertification.progress != null&&(showWriterUserCertification.progress==1 || showWriterUserCertification.progress == 3)}">
													<input class="sxy-txt" type="text"
														value="${showWriterUserCertification.orgName}" 
														disabled="disabled"
														/>
												</c:when>
												
											</c:choose> --%>
											
											
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
											<input id="handphone" class="sxy-txt" type="text"
												value="${showWriterUserCertification.handphone}"
												name="handphone"
												<%-- <c:if test="${showWriterUserCertification.progress != null&&(showWriterUserCertification.progress==1 || showWriterUserCertification.progress == 3)}">disabled="disabled"</c:if> --%>
												/>
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
												name="cert"/>
											<input class="sxy-txt" type="text"
												value="${showWriterUserCertification.certName}"
												id="certName" name="certcertName" readonly/>
										</div>
									</div>
								</td>
								<td>
									<div style="margin-bottom: 65px; margin-left: 20px;">
										<%-- <c:if test="${showWriterUserCertification.progress==null||showWriterUserCertification.progress==0||showWriterUserCertification.progress==2}"> --%>
											<input id="sxy-btn-upload" type="button" value="上传文件" />
										<%-- </c:if> --%>
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
								<td colspan="2"><font class="td-font-last">请上传bmp、jpg、gif、jpeg、png格式的照片或扫描件</font></td>
							</tr>
						</table>
						<img class="cupline" alt=""
							src="<%=path%>/statics/image/_cupline.jpg" />
						<div class="submit_btn_wrapper">

								 <c:if test="${showWriterUserCertification.progress==0}">
									 <button class="btn-2" onclick="return submitValidate()" <%-- <c:if test="${(showWriterUserCertification.progress==1 || showWriterUserCertification.progress == 3)}">style = "display:none;"</c:if> --%>
									 >
									 提交
									 </button>
								 </c:if>
								<c:if test="${showWriterUserCertification.progress!=0 && showWriterUserCertification.progress!=1}">
							<button class="btn-2" onclick="return submitValidate()" <%-- <c:if test="${(showWriterUserCertification.progress==1 || showWriterUserCertification.progress == 3)}">style = "display:none;"</c:if> --%>
							>
									重新提交
							</button>
								</c:if>


						</div>
					</form>
				</div>
			</div>
		</div>
		<div style="height: 60px; width: 100%;"></div>
	</div>
	
	<c:if test="${showWriterUserCertification.progress==2 && showWriterUserCertification.backReason !=null && showWriterUserCertification.backReason != '' }">
		<!-- 退回原因及审批意见 显示悬浮框 -->
		<div class="bookmistake" id="return_cause_div">
		    <div class="apache">
		        <div class="mistitle">退回原因:</div>
		        <div class="xx" onclick="$('#return_cause_div').fadeOut(500);"></div>
		    </div>
		
		    <div class="info">
		        <input id="return_cause_hidden" type="hidden" value="${showWriterUserCertification.backReason }">
		        <textarea class="misarea" disabled="disabled">${showWriterUserCertification.backReason }</textarea>
		    </div>
		
		    <div class="">
		        <button class="btn" type="button" onclick="$('#return_cause_div').fadeOut(500);">确认</button>
		    </div>
		</div>
		<script type="text/javascript">
			if ("${(showWriterUserCertification.progress==2 && showWriterUserCertification.backReason != null && showWriterUserCertification.backReason !='' )?'on':'off' }"=="on" && $("#return_cause_hidden").val().length>0) {
		
		        $("#return_cause_div").fadeIn(800);
		
		    }
		</script>
	</c:if>
	
	
	<div style="clear: both"></div>
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>



</body>
</html>