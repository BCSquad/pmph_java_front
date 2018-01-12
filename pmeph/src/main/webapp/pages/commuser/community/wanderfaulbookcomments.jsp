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
	<title>Insert title here</title>
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css"/>
    <link rel="stylesheet" href="<%=path%>/statics/commuser/community/wanderfaulbookcomments.css" type="text/css">
    <script src="<%=path%>/resources/comm/jquery/jquery.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js"></script>
    <script src="<%=path%>/resources/comm/base.js"></script>
    <%-- <script src="<%=path%>/resources/commuser/community/communitylist.js"></script> --%>
</head>
<style type="text/css">
  
</style>
<body>
            <jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div style="background-color: #f6f6f6;padding-top:28px;padding-bottom:130px"> 
<div class="content-wrapper">
    	<div class="area2">
        		<div class="namehead">
        		<span class="name" >精选书评</span>
        		</div>
   		</div>
     <div style="margin-top:25px">
     <div class="collection">
        <div class="content">
            <div  class="content-img">
                <img src="${article.imgpath }"/>
            </div>
            <div  class="content-text">
                <div class="text">
                                                                             鸡蛋减肥法冬季减肥就发电机风机电机东方反对开口问哦饿哦我id到时看看地方看看疯狂的疯狂开发贷款房价跌哦日欸惹库房库管库管库管看看哥哥
                </div>
                <div class="message">
                   <div class="personicon"></div>
                   <div class="username">张三</div>
                   <div class="staricon" >
    		       </div>
    		        <div class="staricon">
    		       </div>
    		        <div class="staricon">
    		       </div>
    		        <div class="staricon">
    		       </div>
    		        <div class="staricon">
    		       </div>
                </div>
                <div class="end">
                                                                健康水健康水等级考试的角色等级考试的健康都换积分兑换积分兑换积分兑换话费的环境黑胡椒粉的机会发动机发动机回家的话就是觉得和计算机和环境和精神上的
                </div>
            </div>
        </div>
    </div>
    
   <div class="collection">
        <div class="content">
            <div  class="content-img">
                <img src="${article.imgpath }"/>
            </div>
            <div  class="content-text">
                <div class="text">
                                                                             鸡蛋减肥法冬季减肥就发电机风机电机东方反对开口问哦饿哦我id到时看看地方看看疯狂的疯狂开发贷款房价跌哦日欸惹库房库管库管库管看看哥哥
                </div>
                <div class="message">
                   <div class="personicon"></div>
                   <div class="username">张三</div>
                   <div class="staricon" >
    		       </div>
    		        <div class="staricon">
    		       </div>
    		        <div class="staricon">
    		       </div>
    		        <div class="staricon">
    		       </div>
    		        <div class="staricon">
    		       </div>
                </div>
                <div class="end">
                                                                健康水健康水等级考试的角色等级考试的健康都换积分兑换积分兑换积分兑换话费的环境黑胡椒粉的机会发动机发动机回家的话就是觉得和计算机和环境和精神上的
                </div>
            </div>
        </div>
    </div>
    </div>
    <div style="height:70px;padding-top:30px;background-color: #ffffff;">
    <div style="text-align: right;">
	        <ul class="pagination" id="page1">
	        </ul>
	        <div style="display: inline-block;vertical-align: top;text-align:left;">
	            <select id="edu" name="edu">
	                <option value="5"  >每页5条</option>
	                <option value="10" >每页10条</option>
	                <option value="15" >每页15条</option>
	                <option value="20">每页20条</option>
	            </select>
	        </div>
	        <div class="pageJump">
	            <span>共10页，共10条数据，跳转到</span>
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
<script type="text/javascript">
$(function(){
	Page({
        num:10,					
        startnum: 1,
        elem:$("#page1"),
        callback: function (n) {
        
        }
 });
$('select').selectlist({
    zIndex: 10,
    width: 110,
    height: 30,
    optionHeight: 30,
    onChange: function () {
    	
    }  //自定义模拟选择列表项chang
});
	
});

</script>
</html>