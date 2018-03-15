<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<script>
        var contextpath='${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>待办事项</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/authadmin/backlog/schedule.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js"></script>
    
</head>
<style type="text/css">

        #right .select-wrapper {
            border: none;
        }
         #right .select-button {
            background: #f6f6f6;
        }
    </style>
<body>
<jsp:include page="/pages/comm/headGreenBackGround.jsp">
    <jsp:param name="pageTitle" value="backlog"></jsp:param>
</jsp:include>
<div class="body" style="background-color:#f6f6f6;float: left">
	<input id="license" value="${map.progress}" type="hidden"/>
    <div class="content-wrapper">
        <div class="big">
                <div class="left">待办事项</div>
                <div class="mid"><a class="midButton" href="${ctx}/schedule/eventRecord.action">办事记录</a></div>
                <div class="right" id="right">
                   <div style="float: right;">
                      		 筛选：
                       <select id="filtrate-select" name="filtrate-select" title="请选择">
                           <option value="all" ${map.time=='all' ?'selected':''}>全部</option>
                           <option value="week" ${map.time=='week' ?'selected':''}>一周内</option>
                           <option value="month" ${map.time=='month' ?'selected':''}>一月内</option>
                           <option value="year" ${map.time=='year' ?'selected':''}>一年内</option>
                       </select>
                   </div>
                </div>
            <div class="bigList">
            <c:if test="${map.pageResult.total>=1 }">
	            	
	            <c:forEach items="${map.pageResult.rows}" var="one" varStatus="status">
	            	<div class="list">
	                    <div class="leftContent">
	                        <div class="leftContentSmall">
	                            <div class="pictureDiv">
	                            	<c:if test="${one.TYPE=='A'}">
	                            		<img  class="pictureB" src="${ctx}/statics/image/pic3555.png">
	                            	</c:if>
	                                <c:if test="${one.TYPE=='B'}">
	                               		<img  class="picture">	
	                                </c:if>
	                            </div>
	                        </div>
	                        <c:if test="${status.last==false}" >
		                        <div class="straightLine">
		                            <div class="whiteDiv">
		                            </div>
		                        </div>
	                        </c:if> 
	                    </div>
	                    <div class="rightContent">
	                            <div class="leftPictureAndName">
	                                <div class="upContent">
	                                        <div class="headPicture">
	                                            <img class="picture1"  src="${ctx}/${one.avatar}">
	                                            <%-- <img class="picture1"  src="${ctx}${one.avatar}"> --%>
	                                        </div>
	                                        <div class="username">
	                                             <span>${one.NAME}</span>
	                                        </div>
	                                </div>
	                                <div class="downContent">
	                                    <div class="timeEvent">
	                                        <span class="time">于<fmt:formatDate pattern="yyyy年MM月dd日" value="${one.TIME}" /></span>&nbsp;<span class="event">提交${one.CONTENT}</span><span class="event">，请审批</span>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="rightButton">
	                                <div onclick="toogleTip('block','${one.TYPE}','${one.auditId}','${one.ID}')" class="buttonDiv">
	                                        		办理
	                                </div>
	                            </div>
	                    </div>
	            </div>
	            </c:forEach>
              </c:if>
               <c:if test="${map.pageResult.total<1 }">
	            	<div class="no-more">
                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
                    <span>木有内容呀~~</span>
                	</div>
	           </c:if>
            </div>
            <div class="tip" id="tip">
                <div class="upDiv">
                    <div class="tipPicture" >
                        <img src="${ctx}/statics/pictures/sad.png">
                    </div>
                    <div class="tipWords">
                        <span>您目前还不是机构管理员，快去认证吧</span>
                    </div>
                </div>
                <div class="downDiv">
                    <div class="leftButton" onclick="toogleTip('none')">知道了</div>
                    <div class="checkButton" onclick="toAuthAudit(${map.userId})">马上认证</div>
                </div>
                <div class="close" id="close" onclick="toogleTip('none')">
                    <span>×</span>
                </div>
            </div>
            <div class="gray ie" onclick="toogleTip('none')"></div>
            	<c:if test="${map.pageResult.total>5 }">
            	<div class="pageDiv">
                <div style="float: right;">
                    <ul class="pagination" id="page1">
                    </ul>
                    <div style="display: inline-block;    vertical-align: top">
                        <select id="edu" name="edu" >
                            <option value="5" ${map.pageResult.pageSize=='5' ?'selected':''}>每页5条</option>
                            <option value="10" ${map.pageResult.pageSize=='10' ?'selected':''}>每页10条</option>
                            <option value="15" ${map.pageResult.pageSize=='15' ?'selected':''}>每页15条</option>
                        </select>
                    </div>
                    <div class="pageJump">
                        <span>共${map.pageResult.pageTotal}页，跳转到</span>
                        <input type="text" id="toPage"/>
                        <span class="pp">页</span>
                        <button type="button" class="button">确定</button>
                    </div>
                </div>
            </div>
            </c:if>
        </div>
		<c:choose>
			<c:when test="${map.license=='no'}">
			</c:when>
			<c:otherwise>
				<div class="info">
            <div class="topPictureDiv">
                <img class="topPicture"src="${ctx}/${map.avatar}">
            </div>
            <div class="firstRow">
                <span>${map.org_name }</span>
            </div>
            <div class="secondRow">
                <div class="littleV">
                <img >
                </div>
                <div>
                    <span class="littleTitle">${map.org_name},欢迎您!</span>
                    <!-- <span class="littleTitle">阿打算大所大所大所大所大</span> -->
                </div>
                <c:if test="${(map.progress==0||map.progress==2)}">
                	<div>
	                	<span class="littleTitle2" onclick="toAuthAudit(${map.userId})">未认证</span>
	                </div>
                </c:if>
                <c:if test="${map.progress==1}">
                	<div>
	                	<span class="littleTitle3">已认证</span>
	                </div>
                </c:if>
               
            </div>
            <div class="thirdRow">
                <span>最近登录:<fmt:formatDate value="${map.latest_login_time}" type="both" dateStyle="default" timeStyle="default"></fmt:formatDate></span>
            </div>
        </div>
			</c:otherwise>
		</c:choose>
        </div>
   </div>
<div style="clear: both; background-color: white;">   
<jsp:include page="/pages/comm/tail.jsp"/>
</div>
<script>
    Page({
        num: parseInt("${map.pageResult.pageTotal}"),					//页码数
        startnum: parseInt("${map.pageResult.pageNumber}"),				//指定页码
        elem: $('#page1'),		//指定的元素
        callback: function (n) {	//回调函数
            console.log(n);
            window.location.href="${ctx}/schedule/scheduleList.action?currentPage="+n+"&pageSize="+$("input[name='edu']").val(); 
        }
    });
    $(function () {
        $('#filtrate-select').selectlist({
            width: 100,
            height: 20,
            optionHeight: 20,
            onChange:function (){
            	window.location.href="${ctx}/schedule/scheduleList.action?time="+$("input[name='filtrate-select']").val();
            }
        });
        $('#edu').selectlist({
            width: 110,
            height: 30,
            optionHeight: 30,
            onChange:function (){
            	window.location.href="${ctx}/schedule/scheduleList.action?pageSize="+$("input[name='edu']").val();
            }
            
        });
    })
    function toogleTip(val,type,auditId,decId) {
    	var license = $("#license").val();
    	if(license==0||license==2){
    		 $('.tip').css('display',val);
    	     $('.gray').css('display',val);
    	} else{ 
    		if(type=="B"){
    			//跳转教师资格认证页面
    			window.location.href="${ctx}/teacherauth/toPage.action?";
    		}else{
    			//dataaudit/toMaterialAudit.action?material_id=10&declaration_id=123&view_audit=audit
    			window.location.href="${ctx}/dataaudit/toMaterialAudit.action?material_id="+auditId+"&declaration_id="+decId+"&view_audit=audit";
    		}
    	}	
    		
    }
    function toAuthAudit(userId){
    	window.location.href="${ctx}/admininfocontroller/toadminattest.action?userId="+userId;
    }
    
</script>
</body>
</html>