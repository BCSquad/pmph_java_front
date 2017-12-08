<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>申请列表</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <link rel="stylesheet" href="${ctx}/statics/authadmin/message/noticeMessageDetail.css" type="text/css">
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
	<div class="content-wrapper">
        <div class="navigator">
           	 个人中心 > 消息通知 > 公告详情
        </div>
        <div id="section">

            <span class="title">${map.material_name}</span>

            <div class="time">
            <span >截止日期：${map.deadline}</span>
            </div>
        </div>
        <div class="content">
            <p class="pSize">
		               ${message.content}
            </p>
        </div>
        <div align="center">
            <img  class="pictureSizeBig" src="${ctx}/statics/pictures/1395ea09518bf0f9b1787e0ec8c7452c.jpg" />
        </div>
        <div class="liseDiv">
        <div class="list">
            <div class="title2">
               	 邮寄地址：
            </div>
            <div class="listContent">
            	${map.mail_address}
            </div>
        </div>
        <div class="list">
        <div class="title2">
           	 联系人：
        </div>
            <div class="listContent">
            	<c:forEach items="${listContact}" var="contact">
                	<span>${contact.contact_user_name }（电话：${contact.contact_phone } 邮箱：${contact.contact_email}）</span><br>
                </c:forEach>
            </div>
        </div>
        <div class="list">
            <div class="title2">
                	备注：
            </div>
            <div class="listContent">
		               ${map.note}
            </div>
        </div>
        <div class="list">
            <div class="title2">
                	附件：
            </div>
            <div class="listContent">
            	<c:forEach items="${listAttachment}" var="attachment">
            	<span><a href="#"><img class="pictureSize" src="${ctx}/statics/pictures/attachment.png">&nbsp;&nbsp;${attachment.attachment_name}</a></span><br>
            	</c:forEach>
                
            </div>
        </div>
        </div>

        <div class="registerDiv"  >
            <span class="button" onclick="register(${map.materialId})">报名参加</span>
        </div>
	</div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>

<script type="text/javascript">
		function register(materialId){
			window.location.href="${ctx}/material/toMaterialAdd.action?material_id="+materialId;
		}


</script>
</html>