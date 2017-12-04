<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
		                人民卫生出版社建社50年来，累计出版图书2万余种，总印数约67000万册，每年出书1000余种，年发行量1000多万册，
		                年产值超过5亿元。出书品种主要包括： 医学教材、参考书和医学科普读物等，涉及现代医药学和中国传统医药学的所有领域，
		                体系完整，品种齐全。人卫社不断加强管理，优化选题，提高质量，多出精品，加强服务，已成为国内唯一涵盖医学各领域,
		                各层次的出版机构,能满足不同读者的需求。使读者享受到一流的作者、一流的质量、一流的服务。人卫社的品牌已成为优质图书的代名词。
		                人民卫生出版社出版医学教材有着优良的传统。 从建社伊始的20世纪50年代， 翻译前苏联的医学教材以满足国内教学需要，
		                到组织国内一流作者自编教材至今已有50年的历史。一代代的医学生都是伴随着人卫社出版的教材成长起来的。
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

        <div class="registerDiv" >
            <span class="button">报名参加</span>
        </div>
	</div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>