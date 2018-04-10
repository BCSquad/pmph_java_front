<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/26
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath();%>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>小组列表</title>
    <script>
        var contextpath='${pageContext.request.contextPath}/';
    </script>
   	<link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/mygroup/groupList.css?t=${_timestamp}" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/commuser/mygroup/groupList.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
<div class="content-wrapper">
    <div class="div_top">我加入的小组</div>
    <input type="hidden" id="pageNumber" value="${pageNumber}">
    <div class="items">
    	<c:choose>
    		<c:when test="${listSize>0 }">
    			<c:forEach var = 'group' items="${listgroup}" > 
			        <div class="oneitem">
		            <img src="${ctx}/${group.groupImage}" class="img1"/>
		           <%--  <img src="${pageContext.request.contextPath}${group.groupImage}" alt="暂无图片" class="img1"/> --%>
		            <div class="item_content">
		                <text class="txt1">${group.groupName}</text>
		                <br/>
		                <text class="txt2">
		                    <text class="color64">创建时间</text>
		                    :&nbsp;<text class="color99">${group.gmtCreate}</text>
		                </text>
		                <div class="imgs">
		                	<c:forEach var = 'avatar' items="${group.avatars}" varStatus="o">
		                		<c:if test="${o.index <=5 }"><!-- 展示6位 -->
		                			<%-- <img src="${pageContext.request.contextPath}/${avatar}" alt="暂无图片"/> --%>

		                			 <img src="${ctx}/${avatar}" />
		                		</c:if>
		                	 </c:forEach>
		                </div>
		                <text class="txt3">
		                  	  人数
		                    <text class="txt30">${group.members}</text>
		                    <label></label>
		                    	文件
		                    <text class="txt30">${group.files}</text>
		                </text>
		                <div style="margin-top:10px;"><a class="item_link" href="${pageContext.request.contextPath}/group/toMyGroup.action?groupId=${group.id}">
		                    <div class="item_content_button">小组主页></div>
		                </a></div>
		            </div>
		            <div class="item_bc">
		                <div class="item_bc_top">
		                    <text>最新动态</text>
		                    <div></div>
		                </div>
		                	<c:forEach var = 'groupMassage' items="${group.groupMassages}" varStatus="o">
		                		<c:if test="${o.index <= 2 }"><!-- 展示2条 -->
		                			<div class="pContent">
		                        		${groupMassage.msgContent}
		                   			</div>
		                		</c:if>
		                	 </c:forEach>
					</div>
		        </div>
		        </c:forEach>
		        
    		</c:when>
    		<c:otherwise>
    			<div class="no-more">
                    <img src="<c:url value="/statics/image/noGroup.png"></c:url>">
                </div>
    		</c:otherwise>
    	</c:choose>
		<div class="clear"></div>
    </div>
    <div style="clear: both"></div>
    <c:if  test="${isover=='no'}">
        <div class="jzgd"><span onclick="javascript:doMore('${pageNumber}')">加载更多...</span></div>
        <div style="height: 30px;"></div>
    </c:if>
	<%--
    <c:if  test="${isover=='yes'}">
        <div class="jzgd"><span>没有更多了...</span></div>
        <div style="height: 30px;"></div>
    </c:if>--%>
</div>
</div>
		<div style="clear: both; background-color: #f6f6f6;">   
			<jsp:include page="/pages/comm/tail.jsp"/>
		</div>
</body>
</html>
