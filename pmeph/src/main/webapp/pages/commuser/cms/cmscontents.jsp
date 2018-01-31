<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<script type="text/javascript">
	var contxtpath='${pageContext.request.contextPath}/';
</script>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>医学随笔</title>
    <script type="text/javascript">
	var contextpath = '${pageContext.request.contextPath}/';
</script>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/cms/cmscontents.css" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js"
	type="text/javascript"></script>
	<script src="${ctx}/resources/comm/base.js" type="text/javascript"></script>
	
</head>
<body>
<div style="width: 100%;padding: 0;margin: 0;height: 81px;border: none;overflow: hidden;">
	<jsp:include page="/pages/comm/head.jsp">
		<jsp:param value="articlepage" name="pageTitle" />
	</jsp:include> 
</div>

<div class="body">
    <div class="content-wrapper">
    	<div class="sbxq_title">
			<span>文章 > 医学随笔</span>
        <div class="notes">
		   	<c:choose>
        		<c:when test="${not empty page}">
        			<c:forEach items="${page.rows}" var="cms" varStatus="vs">
           			<div class="item behind" onclick="window.location.href='${ctx}/articledetail/toPage.action?wid=${cms.id }'">
                			<div class="command">
			                   	<span style="margin-left: 5px">推荐</span>
			               	</div>
                			<div  class="content" >
	                   			<div class="content-image">
	                       			<img src="${ctx}/statics/testfile/p2.png" />
	                   			</div>

	                    		<p  class="content-title"  >${cms.title }</p>
	                    		<p  class="content-text">
	                            		${cms.summary }
	                    		<div  class="foot">
			                        <div  class="msg">
			                        	<span id="span_1"></span><span id="span_4">${cms.clicks }</span>
			                        	<span id="span_2"></span><span id="span_4">${cms.likes }</span>
			                        	<span id="span_3"></span><span id="span_4">${cms.comments }</span>
			                        </div>
	                    		</div>
			                    <div class="ryxx">
			                    	<div class="ryxx_foot">
			                    		<c:if test="${cms.avatar=='DEFAULT'}">
		                                <img src="${ctx}/statics/testfile/mi.png" class="ryxx_tx"></c:if>
		                				<c:if test="${cms.avatar!='DEFAULT'}">
		                				<img src="${ctx}/image/${cms.avatar}.action" class="ryxx_tx"></c:if>
					                    <span class="ryxx_xm" style="cursor:pointer;" onclick="window.location.href='${ctx}/personalhomepage/tohomepage.action?userId=${cms.userId }'">${cms.realname }</span>
			                    		<span class="ryxx_sj">${cms.authdate }</span>
			                    	</div>
			                    </div>
                			</div>
            			</div>
            	 		</c:forEach>
           			</c:when>
           		<c:otherwise>
					<div class="oneList">
						没有相关数据
					</div>
				</c:otherwise>
            </c:choose>
        </div>
        <div style="clear: both"></div>
        <!-- <div class="_load">
        	<span>加载更多...</span>
        </div> -->
        <div class="js-load-more" style="cursor: pointer;">加载更多...</div>
    </div>
</div>
	<div style="background-color: white;width: 100%;padding: 0;margin: 0;height: 220px;border: none;overflow: hidden;">
		<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
	</div> 
</body>
</html>