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
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>精彩书评</title>
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/commuser/community/wanderfaulbookcomments.css?t=${_timestamp}" type="text/css">
    <script src="<%=path%>/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/commuser/community/wanderfaulbookcomments.js?t=${_timestamp}"></script>
</head>
<style type="text/css">
  
</style>
<body>
            <jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div style="background-color: #f6f6f6;padding-top:28px;padding-bottom:130px"> 
<div class="content-wrapper">
         <input type="hidden" id="materialId" value="${materialId }"/>
         <input type="hidden" id="pagenum" value="${pagenum }"/>
         <input type="hidden" id="pagesize" value="${pagesize }"/>
         <input type="hidden" id="total" value="${total }"/>
         <input type="hidden" id="pagetotal" value="${pagetotal }"/>
    	<div class="area2">
        		<div class="namehead">
        		<span class="name" >精选书评</span>
        		</div>
   		</div>
     <div style="margin-top:25px">
     
     <c:forEach items="${comments }" var="comment" >
     <div class="collection">
        <div class="content">
            <div  class="content-img">
                <img src="${comment.imagepath=='defualt'? 'statics/image/564f34b00cf2b738819e9c35_122x122!.jpg':comment.imagepath}"/>
            </div>
            <div  class="content-text">
                <div class="text">
                        ${comment.title }
                </div>
                <div class="message">
                   <div class="personicon"><img src="${comment.avatar}" style="height:33px;width:33px"/></div>
                   <div class="username">${comment.username }</div>
                   <div style="float: left;height: 33px;width: 75px;">
                   <div class="staricon ${comment.score>0.0 ? 'yellowstar':'graystar' }" >
                   </div>
    		        <div class="staricon ${comment.score>2.0 ? 'yellowstar':'graystar' }">
    		       </div>
    		        <div class="staricon ${comment.score>4.0? 'yellowstar':'graystar' }">
    		       </div>
    		        <div class="staricon ${comment.score>6.0? 'yellowstar':'graystar' }">
    		       </div>
    		        <div class="staricon ${comment.score>8.0 ? 'yellowstar':'graystar' }">
    		       </div>
    		       </div>
                </div>
                <div class="end" id="end">
                    <div class="textp">${comment.contentxt }</div>
                </div>
            </div>
        </div>
    </div>
    </c:forEach>
  
    </div>
    <div style="height:70px;padding-top:30px;background-color: #ffffff;">
    <div style="text-align: right;">
	        <ul class="pagination" id="page1">
	        </ul>
	        <div style="display: inline-block;vertical-align: top;text-align:left;">
	            <select id="edu" name="edu">
	                <option value="5"  ${pagesize=='5'? 'selected':'' }>每页5条</option>
	                <option value="10" ${pagesize=='10'? 'selected':'' }>每页10条</option>
	                <option value="15" ${pagesize=='15'? 'selected':'' } >每页15条</option>
	                <option value="20" ${pagesize=='20'? 'selected':'' }>每页20条</option>
	            </select>
	        </div>
	        <div class="pageJump">
	            <span>共${pagetotal }页，共${total }条数据，跳转到</span>
	            <input type="text"/>
	            <span class="pp">页</span>
	            <button type="button" class="button">确定</button>
	        </div>
	    </div>
   </div>
   </div>
   
   </div>          
            <jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
</body>

</html>