<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>申报进度</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="<%=basePath%>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=basePath%>/statics/commuser/report_progress/progress.css" type="text/css">

</head>
	<body>
<iframe style="width: 100%;padding: 0;margin: 0;height: 81px;border: none" src="<%=basePath%>/statics/comm/head.html"></iframe>
<div class="body">
    <div class="content-wrapper">
        <div class="title">
            <span>个人中心 > 教材申报 > 申报进度</span>
            <h2>${progress.materialName}</h2>
        </div>
        <div class="conten">
            <div class="conten-left">
                <div class="conten-left-top">
                    <div class="img-bg">
                    	<c:if test="${progress.onlineSubmit=='1'}">
	                        <div class="img_left">
	                            <div class="picture_top"></div>
	                            <div class="picture_bom">
	                                <span class="span_bom_left">已提交</span>
	                                <img   class="img_bom_right"   src="<%=basePath%>/statics/image/yiwancheng.png" >
	                            </div>
	                        </div>
                        </c:if>
                        <c:if test="${progress.onlineProgress=='1'}">
	                        <div class="img_mid">
	                            <div class="picture_mid"></div>
	                            <div class="picture_bom">
	                                <span class="span_bom_left">资料已审核</span>
	                                <img   class="img_bom_right"  src="<%=basePath%>/statics/image/yiwancheng.png">
	                            </div>
	                        </div>
	                    </c:if>
                        <c:if test="${progress.offlineProgress=='2'}">
	                        <div class="img_right">
	                            <div class="picture_right"></div>
	                            <div class="picture_bom">
	                                <span class="span_bom_left">纸质表已收到</span>
	                                <img   class="img_bom_right"   src="<%=basePath%>/statics/image/yiwancheng.png" >
	                            </div>
	                        </div>
                        </c:if>
                    </div>
                </div>
                <div class="conten-left-bom">
                    <div class="left-bom-1">审核结果：</div>
	                    <c:forEach items="${textBookCheck}" var="books" varStatus="vs">
		                    <div class="left-bom-2">
		                            <span class="left-bom-21">${books.textbookName}</span>
		                            <c:if test="${books.isPublished==true}">
		                            	<c:choose>
											<c:when test="${books.isChiefChosen==true && books.rank=='1'}">
				                            	<span class="left-bom-24">已被遴选为第一主编</span>
				                            	<button class="left-bom-23">选择编委>></button>
				                            </c:when>
				                            <c:otherwise>
												<span class="left-bom-22">审核通过</span>
											</c:otherwise>
										</c:choose>
		                            </c:if>
		                            <c:if test="${books.isPublished==false || books.isChiefChosen==false}">
		                            	<span class="left-bom-22">审核中...</span>
		                            </c:if>
		                    </div>
	                    </c:forEach>
                    <!-- <div class="left-bom-2">
                        <span class="left-bom-21">医学影像学</span>
                        <span class="left-bom-24">已被遴选为第一主编</span>
                        <button class="left-bom-23">选择编委>></button>
                    </div>
                    <div class="left-bom-2">
                        <span class="left-bom-21">皮肤性医学</span>
                        <span class="left-bom-22">审核中...</span>
                    </div> -->
                </div>
            </div>
            <div class="conten-right">
            	 <c:forEach items="${userMessageList}" var="message" varStatus="vs">
            	 	<div class="list">
		                <div class="rightContent-top">
		                    <div class="board-line"></div>
		                    <fmt:parseDate value="${message.gmtCreate}" var="date1" type="both"></fmt:parseDate>
		                    <div class="time"><fmt:formatDate value="${date1}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
		                </div>
		                <div class="rightContent-bom">
		                    <div class="wzContent">${message.msgContent}</div>
		                </div>
		            </div>
            	 </c:forEach>
        	</div>
        </div>
    </div>
</div>

<iframe style="width: 100%;clear:both;padding: 0;margin: 0;height: 200px;border: none" src="<%=basePath%>/statics/comm/tail.html"></iframe>
</body>
</html>
