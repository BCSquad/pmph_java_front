<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<head>
    <script type="text/javascript">
        var pathName=window.document.location.pathname;
        var contextpath="${pageContext.request.contextPath }/";
    </script>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <link rel="stylesheet" href="<%=path %>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=path %>/statics/commuser/collection/articlecollection.css" type="text/css">
    <script src="<%=path %>/resources/comm/jquery/jquery.js"></script>
     <script src="<%=path %>/resources/comm/base.js"></script>
     <script src="${ctx }/resources/commuser/collection/articlecollection.js"></script>
</head>
<style type="text/css">
</style>
</script>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
    <div class="content-wrapper">
         <div class="message">
            <a href="bookcollection/tobookcollection.action">
                <div class="item">
                    <div class="off-text">书籍收藏夹</div>
                    <div class="off-line" ></div>
                </div>
            </a>
            <a href="javascript:;">
                <div class="item">
                    <div class="on-text" >文章收藏夹</div>
                    <div class="on-line" ></div>
                </div >
            </a>
        </div>
        <div class="message-line"></div>
        <c:forEach items="${articleCollection }" var="collection">
        <a href="articlecollection/toarticlecollectionlist.action?favoriteId=${collection.id }" target="_blank">
        <div class="collection">
            <p class="title" >${collection.favorite_name }</p>
            <p class="count">共${collection.mcount }条内容</p>
        </div></a>
        </c:forEach>
        <div class="no-more" style="display:${articleCollection.size()>0 ?'none':'block' }" id="nomore">
                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>" style="display: block;margin: 0px auto 0px;">
                    <span style="display: block;width: 100px;margin: 0px auto 0px;">木有内容呀~~</span>
        </div>
    </div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>