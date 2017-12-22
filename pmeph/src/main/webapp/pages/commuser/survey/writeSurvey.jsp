<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
    <title>填写问卷</title>
    <script>
        var contextpath='${pageContext.request.contextPath}/';
    </script>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <link rel="stylesheet" href="${ctx}/statics/commuser/research/writeResearch.css" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/comm/layer/layer.js"></script>
    <script src="${ctx}/resources/commuser/research/writeResearch.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
</head>
<body>
	<jsp:include page="/pages/comm/head.jsp"></jsp:include>
	<div class="body" style="background-image: url('../statics/image/ypzj_bg.png');height:auto">
	
		<div class="content-wrapper">
				<div style="height: 60px;width: 100%"></div>
				<div style="width:1200px;height:auto;background-color: white">
					<div style="padding-top: 60px;width: 100%;text-align: center">
						<span style="font-size: x-large;color: #757575;">图书问卷调查</span>
					</div>
					<div style="width: 1200px;height: auto;padding-top: 60px;padding-bottom: 60px">
						<div style="width: 1120px;height: auto;margin-left: 80px">
							<span style="color: #757575;font-size: 16px">此调查旨在了解用户的图书阅读习惯，希望参与者根据实际情况认真填写！</span>
						</div>
						<div style="width: 1120px;height:auto;margin-left: 80px;color: #757575;font-size: 16px">
							<div style="width: 100%;height: auto">
								<p>Q1 : 性别</p>
								<form>
									<div class="female">
									    <input type="radio" id="female" name="sex" />
									    <label for="female">女</label>
									</div>
									<div class="male">                
									    <input type="radio" id="male" name="sex" />
									    <label for="male">男</label>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div style="height: 60px;width: 100%"></div>
		</div>
	</div>

<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>