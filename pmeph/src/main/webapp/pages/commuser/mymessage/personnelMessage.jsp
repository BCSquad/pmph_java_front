<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>私信列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${ctx}/statics/css/base.css"
	type="text/css">
<link rel="stylesheet"
	href="${ctx}/statics/commuser/mymessage/message.css" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/commuser/mygroup/chat.css"
	type="text/css">
<link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css" />
<script src="${ctx}/statics/js/jquery/jquery.js"></script>
<script src="${ctx}/statics/js/jquery/jquery.selectlist.js"></script>
<script src="${ctx}/resources/commuser/mymessage/personnelMessage.js"></script>
<style type="text/css">
#rightContent .select-button {
	background: #f6f6f6;
}

#rightContent .select-wrapper {
	border: none;
}
</style>
<script type="text/javascript">
	var contxtpath = '${pageContext.request.contextPath}';
	$(function() {
		$('#select').selectlist({
			zIndex : 10,
			width : 70,
			height : 20,
			optionHeight : 20,
			triangleColor : '#333333'
		});
	})
</script>
</head>
<body>
	<jsp:include page="/pages/comm/head.jsp"></jsp:include>
	<div class="messageList">
		<span><a class="otherOptions" href="../mymessage/notice.html">通知</a></span>
		<span><a href="../mymessage/apply.html" class="unselected">申请</a></span>
		<span id="otherSelected"><b>私信</b></span> <span id="rightContent">筛选：
			<select id="select" title="请选择">
				<option value="">全部</option>
				<option value="true">已读</option>
				<option value="false">未读</option>
		</select>
		</span>
	</div>
	</div>
	<div id="list" class="message">

		<!--  <a class="a" href="javascript:void(0)" onclick="show()" style=" background:red">弹出</a>-->

		<div class="b hidden" id="box">
			<div class="hiddenX hidden" id="close">
				<img onclick="hide()" style="width: 100%; height: 100%;"
					src="../image/关闭.png">
			</div>
			<span class="personMessageTitle">你与特朗普的私信窗口</span>
			<div class="contentBox">
				<div class="oneTalk">
					<div class="headAndNameLeft float_left">
						<div class="headDiv">
							<img class="headPicture" src="../测试文件/tx.png" />
						</div>
						<div class="talkName">
							<text>曾若男</text>
						</div>
					</div>

					<div class="talkDiv float_left">
						<div class="sendMessage">
							<!-- <div class="triangle leftTriangle float_left"></div>-->
							<div class="textDiv float_left">简单地说 简单地说 简单地说 简单地说 简单地说</div>
						</div>
						<div class="talkTime talkTimeAlignLeft">2017.04.01</div>
					</div>
				</div>
			</div>
		</div>
		<div class="inputBox">
			<div style="float: left; width: 80%; height: 100%">
				<textarea
					style="width: 100%; height: 98%; border: none; outline: 0; font-size: 15px;"
					type="text" placeholder="请输入消息内容,按回车键发送"></textarea>
			</div>
			<div style="float: left; width: 20%; height: 100%">
				<div class="div_btn11" style="cursor: pointer;">
					<span class="button11">发送</span>
				</div>
			</div>
		</div>

	</div>
	<div id="loadMore" class="load-more clearfix">加载更多...</div>
	</div>
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
<script>
	function show() {

		document.getElementById("box").setAttribute("class", "b show");
		document.getElementById("close").setAttribute("class", "hiddenX show");
	}
	function hide() {
		document.getElementById("box").setAttribute("class", "b hidden");
		document.getElementById("close")
				.setAttribute("class", "hiddenX hidden");
	}
</script>
</html>