<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
String path = request.getContextPath();
String basePath = path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
		var pathName=window.document.location.pathname;
		var contxtpath="${pageContext.request.contextPath}";
		var contextpath="${pageContext.request.contextPath}/";
</script>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <link rel="stylesheet" href="<%=path %>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=path %>/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="<%=path %>/statics/css/jquery.selectlist.css"/>
    <link rel="stylesheet" href="<%=path %>/statics/commuser/collection/booklist.css"/>
    <script src="<%=path %>/resources/comm/jquery/jquery.js"></script>
    <script src="<%=path %>/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="<%=path %>/resources/comm/jquery/jquery.pager.js"></script>
   <script src="<%=path %>/resources/comm/layer/layer.js"></script>
    <script src="<%=path %>/resources/comm/base.js"></script>
    <script src="${ctx }/resources/commuser/collection/booklist.js"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
   <div class="content-wrapper">
       <div class="area1"> <a  href="personalhomepage/tohomepage.action" >个人中心</a> &gt;<a href="javascript:;"> 我的收藏</a> &gt; <a href="bookcollection/tobookcollection.action">书籍收藏夹</a> &gt; <a>${fmap.favorite_name }</a></div>
       <div class="area2">
           <span class="name" >${fmap.favorite_name }</span>
            <input type="hidden" id="favoriteId" value="${fmap.id }"/>
           <span class="del" onclick="delFavorite('${fmap.id }')">删除收藏夹</span>
       </div>
       <c:forEach items="${booklist.rows}" var="book">
       <div class="collection">
           <div class="title" >
               <input type="hidden" id="mark${book.mid }" value="${book.mid }"/>
                <input type="hidden" id="book${book.id }" value="${book.id }"/>
               <div class="title-text" >
                   <a href="readdetail/todetail.action?id=${book.id }">${book.bookname }</a>
               </div>
               <div class="author" >
                                                                    作者： ${book.author }
               </div>
           </div>
           <div class="content" >
               <div class="content-img">
                   <img src="${book.image_url}" />
               </div>
               <div class="content-text">
                       <div class="text" >
                            ${book.detail }
                       </div>
                       <div class="end">
                           <div class="foot" style="height:24px;margin-top: 10px">
                               <span class="span1" onclick="cancelMark('${book.mid}','${book.bookmarks }','${book.id}')">取消收藏</span>
                               <span class="span2" >${book.comments }</span>
                               <span class="smicon comment"></span>
                               <span class="${book.like>0?'span3':'span2' }" id="like${book.id }">${book.likes }</span>
                               <span class="smicon ${book.like>0?'good':'nogood' }"  onclick="addlike('${book.id}')" id="good${book.id }"></span>
                               <span class="span2">${book.clicks }</span>
                               <span class="smicon look"></span>
                           </div>
                       </div>
               </div>
           </div>
       </div>
       </c:forEach>
       <input type="hidden" id="bookcount" value="${booklist.total }" />
       <input type="hidden" id="pagenum" value="${booklist.pageNumber }" />
       <input type="hidden" id="pagesize" value="${booklist.pageSize }" />
       <input type="hidden" id="pages" value="${booklist.pageTotal}" />
       <div style="margin-top: 30px;text-align:right;display:${booklist.pageTotal=='1' && booklist.rows.size()<5 ? 'none':'block' }">     
           <ul class="pagination" id="page1">
           </ul>
           <div style="display: inline-block;vertical-align: top;text-align:left;">
               <select id="edu" name="edu">
                   <option value="5" ${booklist.pageSize=="5"? "selected":"" }>每页5条</option>
                   <option value="10" ${booklist.pageSize=="10"? "selected":"" }>每页10条</option>
                   <option value="15" ${booklist.pageSize=="15"? "selected":"" }>每页15条</option>
                   <option value="20" ${booklist.pageSize=="20"? "selected":"" }>每页20条</option>
               </select>
           </div>
           <div class="pageJump">
               <span>共${booklist.pageTotal}页，${booklist.total }条数据，跳转到</span>
               <input type="text"/>
               <span class="pp">页</span>
               <button type="button" class="button">确定</button>
           </div>
       </div>
        <div class="no-more" style="display:${booklist.rows.size()>0 ?'none':'block' }" id="nomore">
                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>" style="display: block;margin: 0px auto 0px;">
                    <span style="display: block;width: 100px;margin: 0px auto 0px;">木有内容呀~~</span>
        </div>
   </div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
