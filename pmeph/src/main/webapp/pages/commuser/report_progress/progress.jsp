<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>申报进度</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script>
        var contextpath='${pageContext.request.contextPath}/';
    </script>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <link rel="stylesheet" href="${ctx}/statics/commuser/report_progress/progress.css" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/comm/layer/layer.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
	
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
    <div class="content-wrapper">
        <div class="title">
            <span><a class="alink" href="${ctx}/personalhomepage/tohomepage.action?">个人中心 ></a><a class="alink" href="${ctx}/personalhomepage/tohomepage.action?pagetag=jcsb"> 教材申报</a> > 申报进度</span>
            <h2>${progress.materialName}</h2>
        </div>
        	<c:choose>
        		<c:when test="${flag=='no'}">
        			<div class="no-more">
	                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
	                    <span>木有内容呀~~</span>
                	</div>
        		</c:when>
        		<c:otherwise>
        		<div class="conten">
		            <div class="conten-left">
		                <div class="conten-left-top">
		                    <div class="img-bg">

		                    	<c:if test="${progress.onlineProgress=='1'||progress.onlineProgress=='3'||progress.onlineProgress=='4'}">
			                        <div class="img_left">
			                            <div class="picture_top"></div>
			                            <div class="picture_bom">
			                                <span class="span_bom_left">已提交</span>
			                                <img   class="img_bom_right"   src="${ctx}/statics/image/yiwancheng.png" >
			                            </div>
			                        </div>
		                        </c:if>
		                    	<c:if test="${progress.onlineProgress=='0'}">
			                        <div class="img_left">
			                            <div class="picture_top1"></div>
			                            <div class="picture_bom1">
			                                <span class="span_bom_left" style="color: #999999">未提交</span>
			                            </div>
			                        </div>
		                        </c:if>
								<c:if test="${progress.onlineProgress=='2'||progress.onlineProgress=='5'}">
									<div class="img_left">
										<div class="picture_top1"></div>
										<div class="picture_bom1">
											<span class="span_bom_left" style="color: #999999">已退回</span>
										</div>
									</div>
								</c:if>
								<c:if test="${progress.onlineProgress=='1'||progress.onlineProgress=='0'||progress.onlineProgress=='2'||progress.onlineProgress=='5'}">
									<div class="img_mid">
										<div class="picture_mid1"></div>
										<div class="picture_bom" style="  margin-left: 0;">
											<span class="span_bom_left" style="color: #999999;  ">申报单位未审核</span>
										</div>
									</div>
								</c:if>
								<c:if test="${progress.onlineProgress=='3'}">
									<div class="img_mid">
										<div class="picture_mid"></div>
										<div class="picture_bom">
											<span class="span_bom_left">申报单位已审核</span>
											<img   class="img_bom_right"  src="${ctx}/statics/image/yiwancheng.png" >
										</div>
									</div>
								</c:if>
								<c:if test="${progress.onlineProgress=='4'}">
									<div class="img_mid">
										<div class="picture_mid1"></div>
										<div class="picture_bom">
											<span class="span_bom_left" style="color: #999999">申报单位</span>
											<img   class="img_bom_right"  src="${ctx}/statics/image/shenhezhong.png" style="width:52px;">
										</div>
									</div>
								</c:if>
		                        <c:if test="${progress.offlineProgress=='2'}">
			                        <div class="img_right">
			                            <div class="picture_right"></div>
			                            <div class="picture_bom">
			                                <span class="span_bom_left">纸质表已收到</span>
			                                <img   class="img_bom_right"   src="${ctx}/statics/image/yiwancheng.png" >
			                            </div>
			                        </div>
		                        </c:if>
		                        <c:if test="${progress.offlineProgress=='0'}">
			                        <div class="img_right">
			                            <div class="picture_right1"></div>
			                            <div class="picture_bom">
			                                <span class="span_bom_left" style="color: #999999">纸质表未收到</span>
			                            </div>
			                        </div>
		                        </c:if>
		                        <c:if test="${progress.offlineProgress=='1'}">
			                        <div class="img_right">
			                            <div class="picture_right1"></div>
			                            <div class="picture_bom">
			                                <span class="span_bom_left" style="color: #999999">纸质表被退回</span>
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
				                            <c:choose>
				                            	<c:when test="${(books.rank==1 && books.chosenPosition==4)}">
					                            	<span class="left-bom-24">已被遴选为第一主编</span>
					                            	<button class="left-bom-23" style="cursor: pointer;" onclick="chooseEditor('${books.textbookId}')">${books.isLocked == false?'选择编委':'查看编委' }>></button>
					                            </c:when>
					                            <c:when test="${(books.rank==1 && books.chosenPosition==12)}">
					                            	<span class="left-bom-24">已被遴选为第一主编兼数字编委</span>
					                            	<button class="left-bom-23" style="cursor: pointer;" onclick="chooseEditor('${books.textbookId}')">${books.isLocked == false?'选择编委':'查看编委' }>></button>
					                            </c:when>
					                            <c:when test="${books.chosenPosition==4 }">
					                            	<span class="left-bom-24">已被遴选为主编</span>
					                            	<button class="left-bom-23" style="cursor: pointer;" onclick="chooseEditor('${books.textbookId}')">查看编委 >></button>
					                            </c:when>
					                            <c:when test="${books.chosenPosition==12 }">
					                            	<span class="left-bom-24">已被遴选为主编兼数字编委</span>
					                            	<button class="left-bom-23" style="cursor: pointer;" onclick="chooseEditor('${books.textbookId}')">查看编委 >></button>
					                            </c:when>
					                            <c:when test="${books.chosenPosition==2}">
					                            	<span class="left-bom-24">已被遴选为副主编</span>
					                            	<button class="left-bom-23" style="cursor: pointer;" onclick="chooseEditor('${books.textbookId}')">查看编委 >></button>
					                            </c:when>
					                            <c:when test="${books.chosenPosition==10}">
					                            	<span class="left-bom-24">已被遴选为副主编兼数字编委</span>
					                            	<button class="left-bom-23" style="cursor: pointer;" onclick="chooseEditor('${books.textbookId}')">查看编委 >></button>
					                            </c:when>
					                            <c:when test="${books.chosenPosition==1}">
					                            	<span class="left-bom-24">已被遴选为编委</span>
					                            </c:when>
					                            <c:when test="${books.chosenPosition==9}">
					                            	<span class="left-bom-24">已被遴选为编委兼数字编委</span>
					                            </c:when>
					                            <c:when test="${books.chosenPosition==8}">
					                            	<span class="left-bom-24">已被遴选为数字编委</span>
					                            </c:when>
					                            <c:when test="${(books.isPublished || books.isForceEnd) }">
					                            	<span class="left-bom-22">遴选结束，未选中</span>
					                            </c:when>
					                            <c:when test="${(progress.offlineProgress=='2'&&books.onlineProgress==3)}">
					                            	<span class="left-bom-22">遴选中</span>
					                            </c:when>
					                            <c:when test="${(books.onlineProgress==1||books.onlineProgress==4) }">
					                            	<span class="left-bom-22">${progress.orgName } 审核中</span>
					                            </c:when>
					                            <c:when test="${(books.onlineProgress==2||books.onlineProgress==5) }">
					                            	<span class="left-bom-22">被退回</span>
					                            </c:when>
					                            <c:otherwise>
													<span class="left-bom-22">${progress.orgName } 审核通过</span>
												</c:otherwise>
				                            </c:choose>
				                            <%-- <c:if test="${books.isPublished==true}">
				                            	<c:choose>
													<c:when test="${books.rank=='1' && (books.chosenPosition=='4'||books.chosenPosition=='12') && books.isLocked == false} ">
						                            	<span class="left-bom-24">已被遴选为第一主编</span>
						                            	<button class="left-bom-23" style="cursor: pointer;" onclick="chooseEditor('${books.textbookId}')">选择编委>></button>
						                            </c:when>
						                            <c:otherwise>
														<span class="left-bom-22">审核通过</span>
													</c:otherwise>
												</c:choose>
				                            </c:if>
				                            <c:if test="${books.isPublished==false || books.isChiefChosen==false}">
				                            	<span class="left-bom-22">审核中...</span>
				                            </c:if> --%>
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
		           
		            	 <c:forEach items="${userMessageList}" var="message" varStatus="vs" >
		            	
		            	 	<div class="list">
				                <div class="rightContent-top">
				                    <div class="board-line"></div>
				                    <fmt:parseDate value="${message.gmtCreate}" var="date1" type="both"></fmt:parseDate>
				                    <div class="time"><fmt:formatDate value="${date1}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
				                </div>
				                <c:if test="${vs.last==false}" >
				                <div class="rightContent-bom">
				                
				                <%-- <c:choose >
				                	<c:when test="${message.msgMap.img==0}">
				                	 <div class="wzContent"> 您的申报《${progress.materialName}》已提交，请耐心等待审核...</div>
				                	</c:when>
				                	<c:otherwise>  --%>
				                	   <div class="wzContent"> ${message.msgMap.content}</div>
				                	<%-- </c:otherwise>
				                </c:choose> --%>
				                   
				                    <!-- <div class="wzContent">爱仕达开始打卡刷的卡刷的卡刷的卡合适的看哈上看到哈克斯电话卡睡得好卡视角大卡司哈上看到哈克斯电话卡睡得好卡视角大卡司</div> -->
				                </div>
				                </c:if>
				                <c:if test="${vs.last==true}" >
				                <div class="rightContent-bom1">
<%-- 				                       <c:choose > --%>
<%-- 				                	<c:when test="${message.msgMap.img==0}"> --%>
<!-- 				                	 <div class="wzContent"> 您的申报已提交，请耐心等待审核...</div> -->
<%-- 				                	</c:when> --%>
<%-- 				                	<c:otherwise> --%>
				                	   <div class="wzContent"> ${message.msgMap.content}</div>
<%-- 				                	</c:otherwise> --%>
<%-- 				                </c:choose> --%>
				                    <!-- <div class="wzContent">爱仕达开始打卡刷的卡刷的卡刷的卡合适的看哈上看到哈克斯电话卡睡得好卡视角大卡司哈上看到哈克斯电话卡睡得好卡视角大卡司</div> -->
				                </div>
				                </c:if>
				            </div>
		            	 </c:forEach>
		        	</div>
		        </div>
        	</c:otherwise>
        </c:choose>
    </div>
</div>
<div style="clear: both; background-color: #f6f6f6;">   
<jsp:include page="/pages/comm/tail.jsp"/>
</div>
</body>

<script type="text/javascript">
		function chooseEditor(bookId){
			window.location.href="${ctx}/chooseEditor/toPage.action?textBookId="+bookId;
		}

</script>
</html>
