<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>社区主页</title>
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css"/>
     <link rel="stylesheet" href="<%=path%>/statics/commuser/community/community.css" type="text/css">
    <script src="<%=path%>/resources/comm/jquery/jquery.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js"></script>
    <script src="<%=path%>/resources/comm/base.js"></script>
    <script src="<%=path%>/resources/commuser/community/community.js"></script>
</head>

<body>
      <jsp:include page="/pages/comm/head.jsp"></jsp:include> 
       <div class="content-wrapper">
           <div class="navigation">
                <a href="homepage/tohomepage.action" >首页</a>&gt;${notice.title }
           </div>
           <input type="hidden" id="materialId" value="${notice.material_id }"/>
           <div  class="pagecontent">
           <div class="report">
                <div class="left">
                   <div style="background-image: url(${ctx}/statics/image/css_sprites.png);background-position:-10px -150px;width:25px;height:110px;
                   margin-left:10px;margin-top:34px"></div>
                </div>
                <div class="center">
                     <ul style="list-style:none;">
                        <c:forEach items="${reportlist}" var="report">
		                        <li>
		                                <div>
		                                <div class="tag">&gt;</div>
		                                <a href="inforeport/toinforeport.action?id=${report.id }">
		                                    <div class="inleft">${report.title}</div>
		                                    <div style="float: left;width: 29px;height: 15px;background-image: url(${ctx}/statics/image/css_sprites.png); background-position: -45px -212px;margin: 10px;back"></div>
		                                </a>
		                                <div class="inright" >
		         
		                                <fmt:formatDate value="${report.gmt_create }" pattern="yyyy-MM-dd"/>
		                                </div>
		                                </div>
		                        </li>
                        </c:forEach>  
                     </ul>
                </div>
                <div class="right" ><a href="cmsinfoletters/tolist.action?materialId=${notice.material_id }">全部&gt;&gt;</a></div>
           </div>
           <div class="book">
           <div class="bhead">
	           <div class="headicon"></div>
               <div class="headtext">本套教材图书</div>
           </div>
           <div class="booklist">
           <c:set var="count" value="${booklist.size() }" ></c:set>
               <c:forEach items="${booklist }" var="book" >
                    <c:if test="${book !=null }">
	                    <div class="item">
			                  <div class="itemimg" style="width:126px;height:126px;margin:20px auto 0px;text-align: center">
			                     <img alt="" src="${book.image_url=='DEFAULT'? 'statics/image/564f34b00cf2b738819e9c35_122x122!.jpg':book.image_url }" >
			                  </div>
	                 	<div class="bookname"><a href="readdetail/todetail.action?id=${book.id }">${book.bookname }</a> </div>
	                  </div>
	                  <c:set var="count" value="${count-1 }" ></c:set>
	               </c:if>
               </c:forEach>
               <c:if test="${booklist.size() ==count }">
                   <div style="float:left;text-align: center;width:100%;" id="nomore">
	                  <div class="no-more"  >
                       <img src="<c:url value="/statics/image/aaa4.png"></c:url>" style="display: block;margin: 0px auto 0px;">
                       <span style="display: block;width: 100px;margin: 0px auto 0px;">木有内容呀~~</span>
                     </div>
	               </div>
               </c:if>
              </div>    
           </div>
           </div>
           <div class="pageright">
             <div class="rhead">
                 <div class="item select"  id="comment" onclick="comments('${notice.material_id }')">
                                                                             精彩书评
                 </div>
                 <div class="item noselect"  id="smallvideo" onclick="smallvideos('${notice.material_id }')">
                                                                              微视频                                                       
                 </div>
             </div>
             <div class="list" style="margin-left:20px;width:230px;">
                 <ul id="ullist">
                     <c:forEach items="${someComments }" var="comment">
                        <li class="commentli">
                          <p class="title">${comment.bookname }</p>
                          <p  class="message">
	                           <span class="name" >${comment.username }  发表了评论</span>
	                           <span class="scoreimg ${comment.score >=2.0 ? 'yellowstar':'graystar'}"></span>
	                           <span class="scoreimg ${comment.score >=4.0 ? 'yellowstar':'graystar'}"></span>
	                           <span class="scoreimg ${comment.score >=6.0 ? 'yellowstar':'graystar'}"></span>
	                           <span class="scoreimg ${comment.score >=8.0 ? 'yellowstar':'graystar'}"></span>
	                           <span class="scoreimg ${comment.score >=10.0 ? 'yellowstar':'graystar'}"></span>
                          
                          </p>
                          <p  class="contentext">${comment.contentxt }</p>
                      </li>
                     
                     </c:forEach>
                 </ul>
             </div>
             <div class="more" id="more"><a href="community/morecomments.action?materialId=${notice.material_id}">查看更多精彩书评</a></div>
           </div> 
            <div style="clear:both;width:100%"></div>
       </div>
      
      <jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
</body>
</html>