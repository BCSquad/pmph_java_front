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
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/mygroup/groupList.css" type="text/css">
    
    <script src="<%=path %>/resources/comm/jquery/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/base.js"></script>
    
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>

<div class="content-body">
    <div class="div_top">我加入的小组</div>
    <div class="items">
        <c:forEach var = 'group' items="${listgroup}" > 
	        <div class="item">
            <img src="${pageContext.request.contextPath}/${group.groupImage}" alt="头像" class="img1"/>
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
                			<img src="${pageContext.request.contextPath}/${avatar}"/>
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
                <div>
                	<c:forEach var = 'groupMassage' items="${group.groupMassages}" varStatus="o">
                		<c:if test="${o.index <= 2 }"><!-- 展示2条 -->
                			<p>
                        		<text>${groupMassage.msgContent}</text>
                   			 </p>
                		</c:if>
                	 </c:forEach>
                </div>
			</div>
        </div>
        </c:forEach>
    </div>
</div>
<div style="background-color: white;width: 100%;padding: 0;margin: 0;height: 220px;border: none;overflow: hidden;">
		<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</div>
</body>
</html>
