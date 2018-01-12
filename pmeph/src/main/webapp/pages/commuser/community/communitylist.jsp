<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = path+"/";
String contextpath=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     <script>
		var contextpath = "${pageContext.request.contextPath}/";
	</script>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css"/>
     <link rel="stylesheet" href="<%=path%>/statics/commuser/community/communitylist.css" type="text/css">
    <script src="<%=path%>/resources/comm/jquery/jquery.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js"></script>
    <script src="<%=path%>/resources/comm/base.js"></script>
    <script src="<%=path%>/resources/commuser/community/communitylist.js"></script>
</head>
<body>
               <jsp:include page="/pages/comm/head.jsp"></jsp:include> 
				
				<div style="width:1200px;margin:0px auto 0px">
				<div style="height:40px;width:100%;line-height: 40px;color:#A4A4A4;margin-bottom:10px">
				    <a href="homepage/tohomepage.action" style="color:#A4A4A4">首页</a>&gt;教材社区  
				</div>
				<div class="searchDiv" >
	                <div class="searchInputLeft" onclick="load('search')">
	                    <img src="<%=path%>/statics/image/sousuo-helpCenter.png">
	                </div>
	                <div class="nname">公告名称：</div>
	                <div class="searchInputRight">
	                    <input class="input" id="search-name" >
	                </div>
                </div>
                <div style="clear:both"></div>
                <div style="float:left" id="changediv">
               
	            </div>
	            
	            <div id="more" style="float:left;height:34px;width:100%;background-color:#F1F6FA;text-align: center;line-height: 34px;color:#6F7070">
	                <a href="javascript:loadMore();" style="color:#6F7070">点击获取更多精彩内容</a>
	            </div>
	            
	            <div style="float:left;text-align: center;width:100%;display:none" id="nomore">
	             <div class="no-more"  >
                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>" style="display: block;margin: 0px auto 0px;">
                    <span style="display: block;width: 100px;margin: 0px auto 0px;">木有内容呀~~</span>
                 </div>
	            </div>
	          </div> 
	          
	             <div style="clear:both"></div>
                <jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
</body>
</html>