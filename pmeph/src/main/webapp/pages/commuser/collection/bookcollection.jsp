<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
String path = request.getContextPath();
String basePath = path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
		var pathName=window.document.location.pathname;
		var contextpath="${pageContext.request.contextPath }/";
</script>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>书籍收藏</title>
 <link rel="stylesheet" href="<%=path %>/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path %>/statics/commuser/collection/bookcollection.css?t=${_timestamp}" type="text/css">
    <script src="<%=path %>/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="<%=path %>/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx }/resources/commuser/collection/bookcollection.js?t=${_timestamp}"></script>
</head>
<style type="text/css">
</style>
<script type="text/javascript" >
/*    function toBookList(id,nameid){
	   var name=$("#title"+nameid).text();
	   window.location.href=contxtpath+"/bookcollection/tobookcollectionlist.action?favoriteId="+id+"&&favoriteName="+name;
   } */
</script>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
    <div class="content-wrapper">
        <div class="message">
            <a href="javascript:;">
                <div class="item">
                    <div class="on-text">书籍收藏夹</div>
                    <div class="on-line" ></div>
                </div>
            </a>
            <a href="articlecollection/toarticlecollection.action">
                <div class="item">
                    <div class="off-text" >文章收藏夹</div>
                    <div class="off-line" ></div>
                </div >
            </a>
        </div>
        <div class="message-line"></div>
        <c:forEach items="${bookCollection }" var="collection" varStatus="status">
        <a href="bookcollection/tobookcollectionlist.action?favoriteId=${collection.id}" target="_blank">
            <div class="collection">
                <p class="title" id="title${status.index }">${collection.favorite_name }</p>
                <p class="count">共${collection.mcount }条内容</p>
            </div>
        </a>
    </c:forEach>
        <div class="no-more" style="display:${bookCollection.size()>0 ?'none':'block' }" id="nomore">
                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>" style="display: block;margin: 0px auto 0px;">
                    <span style="display: block;width: 100px;margin: 0px auto 0px;">木有内容呀~~</span>
        </div>
    </div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>