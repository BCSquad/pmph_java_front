<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script type="text/javascript">
   var contextpath="${pageContext.request.contextPath}/"
</script>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>申请列表</title>
      <script>
        var contextpath='${pageContext.request.contextPath}/';
    </script>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <link rel="stylesheet" href="${ctx}/statics/commuser/message/noticeMessageDetail.css" type="text/css">
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
</head>
<body>

<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
	<div class="content-wrapper">
        <div class="navigator">
           	 <a href="${ctx }/${firstpath}">${firsttag } </a>&gt; <a href="${ctx }/${secondpath}">${secondtag }</a> &gt; 公告详情
        </div>
        
        <c:choose>
        	<c:when test="${materialId==null}">
        		<div class="no-more">
                    <img src="<c:url value='/statics/image/aaa4.png'></c:url>">
                    <span>木有内容呀~~</span>
                </div>
        	</c:when>
        	<c:otherwise>
        		<div id="section">

		            <span class="title">${map.title}</span>
					<c:if test="${map.deadline !=null && map.deadline !=''}">
		            <div class="time">
		            <span >截止日期：${map.deadline}</span>
		            </div>
		            </c:if>
		        </div>
		        <%-- <div class="content">
		            <div class="pSize">
				               ${map.notice}
		            </div>
		        </div> --%>
		        <div  class="con_css" >
		            <p>${content}</p>
		        </div>
		        <c:if test="${map.attachment !=null}">
				        <div class="list">
				            <div class="title2">
				                	附件：
				            </div>
				            <div class="listContent">
				            	<span ><a   href="${ctx}/file/download/${map.attachment}.action" ><img class="pictureSize" src="${ctx}/statics/pictures/attachment.png">&nbsp;&nbsp;${map.attachment_name}</a></span><br>
				            </div>
				        </div>
		        	</c:if>
		        		     
		        <div class="liseDiv">
		        <c:if test="${map.mail_address !=null && map.mail_address !=''}">
		        <div class="list">
		            <div class="title2">
		               	 邮寄地址：
		            </div>
		            <div class="listContent">
		            	${map.mail_address}
		            </div>
		        </div>
		        </c:if>
		        <c:if test="${listContact.size()>0}">
		        <div class="list">
		        <div class="title2">
		           	 联系人：
		        </div>
		            <div class="listContent">
		            	<c:forEach items="${listContact}" var="contact">
		                	<span>${contact.contact_user_name }（电话：${contact.contact_phone } 邮箱：${contact.contact_email}）</span><br>
		                </c:forEach>
		            </div>
		        </div>
		        </c:if>
		        <c:if test="${map.note !=null && map.note !=''}">
		        <div class="list">
		            <div class="title2">
		                	备注：
		            </div>
		            <div class="listContent">
				               ${map.note}
		            </div>
		        </div>
		        </c:if>
		        <c:if test="${listAttachment.size()>0}">
		        <div class="list">
		            <div class="title2">
		                	附件：
		            </div>
		            <div class="listContent">
		            	<c:forEach items="${listAttachment}" var="attachment">
		            	<span><a href="${ctx}/${attachment.attachmentId}"><img class="pictureSize" src="${ctx}/statics/pictures/attachment.png">&nbsp;&nbsp;${attachment.attachment_name}</a></span><br>
		            	</c:forEach>
		            </div>
		        </div>
		        </c:if>
		        </div>
		        <div class="registerDiv"  >
			        <c:if test="${is_material_entry==false}">
			        	
			        </c:if>
		         	<c:if test="${notEnd ==1 and is_material_entry==true}">
	                      <span class="button " onclick="register(${materialId})" >报名参加</span>
                  	  </c:if>
                  	  <c:if test="${notEnd ==0 and is_material_entry==true}">
	                     <span class="button " >已结束</span> 
                  	  </c:if>
		           
		        </div>
        	</c:otherwise>
        </c:choose>
        
        
	</div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>

<script type="text/javascript">
		function register(materialId){
			window.location.href="${ctx}/material/toMaterialAdd.action?material_id="+materialId;
		}

</script>
</html>