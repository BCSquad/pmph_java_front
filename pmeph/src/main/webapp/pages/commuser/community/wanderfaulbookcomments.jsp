<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextpath=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     <script>
		var contextpath = "${pageContext.request.contextPath}/";
	</script>
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css"/>
    <%--  <link rel="stylesheet" href="<%=path%>/statics/commuser/community/communitylist.css" type="text/css"> --%>
    <script src="<%=path%>/resources/comm/jquery/jquery.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js"></script>
    <script src="<%=path%>/resources/comm/base.js"></script>
    <link rel="stylesheet" href="<%=path %>/statics/commuser/collection/articlelist.css"/>
    <%-- <script src="<%=path%>/resources/commuser/community/communitylist.js"></script> --%>
</head>
<body>
            <jsp:include page="/pages/comm/head.jsp"></jsp:include> 
<div class="content-wrapper">
        <div class="area1"><a href="personalhomepage/tohomepage.action">个人中心</a> &gt; <a href="javascript:;">我的收藏</a> &gt; <a href="articlecollection/toarticlecollection.action">文章收藏夹</a> &gt; ${fmap.favorite_name }</div>
    		<div class="area2">
        		<span class="name" >精选书评</span>
        		<span class="del" onclick="delFavorite('${fmap.id }')">删除收藏夹</span>
   		</div>
     <div class="collection" >
        <div class="title">
               <div class="title-text">
                   <a href="#"> 的电视剧电视剧接到街道上的</a>
               </div>
               <div class="tm">
                   <span class="author-icon" style="background-image: url(${ctx}/statics/image/deficon.png); ">
                   </span>
                   <span class="name">海慧寺</span>
                   <span class="time">2017-12-25</span>
               </div>
        </div>
        <div class="content">
            <div  class="content-img">
                <img src="${article.imgpath }"/>
            </div>
            <div  class="content-text" >
                <div class="text">
                                                                             鸡蛋减肥法冬季减肥就发电机风机电机东方反对开口问哦饿哦我id到时看看地方看看疯狂的疯狂开发贷款房价跌哦日欸惹库房库管库管库管看看哥哥
                </div>
                <div class="end">
                    <div class="foot">
                        <span class="span1" onclick="">取消收藏</span>
                        <span class="span2" >36</span>
                        <span class="smicon comment"></span>
                        <span class="span3" >11</span>
                        <span class="smicon good" ></span>
                        <span class="span2">55</span>
                        <span class="smicon look"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
   </div>          
            <jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
</body>
</html>