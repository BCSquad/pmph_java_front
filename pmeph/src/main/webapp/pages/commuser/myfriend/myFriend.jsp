<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/21
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>我的好友</title>
    <script src="<%=path %>/resources/comm/jquery/jquery.js"></script>
    <link rel="stylesheet" href="${ctx}/statics/commuser/myfriend/myFriend.css" type="text/css">
    <script src="${ctx}/resources/comm/base.js"></script>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="content-body">
    <div class="div_top">
        <div class="float_left">我的好友</div>
        <div class="float_right" style="color: #1abd44;">
            <img src="${ctx}/statics/image/tjhy.png" class="img1"/>
            <text class="v_a" onclick = "window.location.href='${ctx}/addFriend/toPage.action'">添加好友</text>
        </div>
    </div>
    <div class="items">
    	<c:forEach var="friend" items="${listFriends}" varStatus="st" >
			<c:choose>  
  			   <c:when test="${(st.index+1)%5 == 1}"><div class="item1"> 
			   </c:when>  
			   <c:otherwise>                         <div class="item1 item11"> 
			   </c:otherwise>  
			</c:choose>
			
	            <div><img src="${ctx}/${friend.avatar}" class="img2"></div>
	            <div class="div_txt1">${friend.username}</div>
	            <div class="div_txt2">${friend.position}</div>
	            <div class="div_txt3">
	                <div>私信</div>
	            </div>
            </div>
			<c:if test="${(st.index+1)%5==0}">
				</div><div class="items"><!-- 拼装换行 -->
			</c:if>
	    </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
