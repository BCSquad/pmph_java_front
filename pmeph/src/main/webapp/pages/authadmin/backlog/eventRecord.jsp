<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<script type="text/javascript">
       var contextpath = "${pageContext.request.contextPath}/";
    </script>
    <title>事项记录</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>待办事项</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/authadmin/backlog/schedule.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.calendar.css" type="text/css">
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.calendar.js"></script>
    <%-- <script src="${ctx}/resources/authadmin/backlog/eventRecord.js"></script> --%>
</head>
<body>
<jsp:include page="/pages/comm/headGreenBackGround.jsp">
    <jsp:param name="pageTitle" value="backlog"></jsp:param>
</jsp:include>
<div class="body" style="background-color:#f6f6f6;float: left">
<div class="content-wrapper">
<div class="contentEvent">

    <div class="big">
        <div class="nav">
            <div ><a class="eventLeft" href="${ctx}/schedule/scheduleList.action">待办事项</a></div>
            <div class="eventMid">办事记录</div>
            <div>	
            <div class="eventRight"><%-- 选择日期:
                <input type="text" id="time" name="time" style="width:90px;padding-left: 5px" calendar format="'yyyy-mm-dd'" z-index="100" onselected="(function(view, date, value){timeChange(date.toLocaleDateString())})">
                <!-- <div style="position:absolute;left: 948px;top:159px;width: 15px;height: 10px;background-color: #70bcc3;border-radius: 50%;text-align: center;padding-bottom: 5px"><span style="color: white">×</span></div> -->
                <img onclick="clearTime()" src="${ctx}/statics/image/close.png" style="position:absolute;left: 949px;top:161px;width: 10px;height: 10px;"> --%>
            </div>
            </div>
        </div>
        <div class="bigList">
        <c:if test="${map.pageResult.total>=1 }">
        <c:forEach items="${map.pageResult.rows}" var="message" varStatus="status">
            <div class="listEvent">
                <div class="leftContent">
                    <div class="leftContentSmall">
                        <div class="pictureDiv">
                            <c:if test="${message.TYPE=='A'}">
                        		<img  class="picture">
                        	</c:if>
                            <c:if test="${message.TYPE=='B'}">
                            	<img  class="pictureB" src="${ctx}/statics/image/pic3555.png">
                            </c:if>
                        </div>
                    </div>
                    <c:if test="${status.last==false}" >
                    <div class="straightLineEvent">
                        <div class="whiteDiv">

                        </div>
                    </div>
                    </c:if>
                </div>
                <div class="rightContentEvent">
                    <div class="leftEvent">
                        <div class="upContentEvent">
                            <div class="eventTypeAndTime">
                                <span class="eventType"><c:if test="${message.TYPE=='A'}">教材申报审核</c:if><c:if test="${message.TYPE=='B'}">教师资格认证</c:if></span>&nbsp;&nbsp;<span class="eventTime"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${message.UPDATETIME}" /></span>
                            </div>
                        </div>
                        <div class="downContentEvent">
                                <span class="timeEventFont">审核了${message.NAME}于<fmt:formatDate pattern="yyyy年MM月dd日" value="${message.TIME}" />提交的${message.CONTENT}</span>
                        </div>
                    </div>
                    <div class="rightButtonEvent">
                        <div class="buttonDiv" onclick="detail('${message.TYPE}',${message.AUDITID})">
                            	查看
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
    <c:choose >
    	<c:when test="${map.license=='no'}">
    	</c:when>
    	<c:otherwise >
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
                </div>
                	<div>
	                	<span class="littleTitle3">已认证</span>
	                </div>
            </div>
            <div class="thirdRow">
                <span>最近登录:  2017-09-27 16:12:07</span>
            </div>
        </div>
    	</c:otherwise>
    </c:choose>
    	
    
</div>
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
            window.location.href="${ctx}/schedule/eventRecord.action?currentPage="+n+"&pageSize="+$("input[name='edu']").val(); 
        }
    });
    $(function () {
    	
        $('#filtrate-select').selectlist({
            width: 100,
            height: 20,
            optionHeight: 20
        });
        $('#edu').selectlist({
            width: 110,
            height: 30,
            optionHeight: 30,
            onChange:function (){
            	window.location.href="${ctx}/schedule/eventRecord.action?pageSize="+$("input[name='edu']").val();
            }
            
        });
    })
    function timeChange(time){
    	window.location.href="${ctx}/schedule/eventRecord.action?time="+time;
    }
    
	function clearTime(){
		$("#time").val(null);
		window.location.href="${ctx}/schedule/eventRecord.action";
	}
	
	function detail(type,auditId){
		 if(type=='A'){
			window.location.href="${ctx}/dataaudit/toPage.action?material_id="+auditId;
		}else if(type=B){
    		//跳转教师资格认证页面
			window.location.href="${ctx}/teacherauth/toPage.action?";	
		} 
	}
</script>
</body>
</html>