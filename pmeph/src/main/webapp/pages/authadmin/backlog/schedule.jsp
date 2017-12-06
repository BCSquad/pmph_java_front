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
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
</head>
<style type="text/css">

         #right .select-button {
            background: #f6f6f6;
        }

        #right .select-wrapper {
            border: none;
        }
    </style>
<body style="background-color: #f6f6f6;">
<jsp:include page="/pages/comm/headGreenBackGround.jsp"/>
<div class="body"  >
	<input id="license" value="${license}" type="hidden"/>
    <div class="content-wrapper">
        <div class="big">
                <div class="left">待办事项</div>
                <div class="mid"><a class="midButton" href="${ctx}/schedule/eventRecord.action">办事记录</a></div>
                <div class="right" id="right">
                   <div style="float: right;">
                      		 筛选：
                       <select id="filtrate-select" name="filtrate-select" title="请选择">
                           <option value="all" ${time=='all' ?'selected':''}>全部</option>
                           <option value="week" ${time=='week' ?'selected':''}>一周内</option>
                           <option value="month" ${time=='month' ?'selected':''}>一月内</option>
                           <option value="year" ${time=='year' ?'selected':''}>一年内</option>
                       </select>
                       
                   </div>
                </div>
            <div class="bigList">
            <c:forEach items="${map.pageResult.rows}" var="one" varStatus="status">
            	<div class="list">
                    <div class="leftContent">
                        <div class="leftContentSmall">
                            <div class="pictureDiv">
                                <img  class="picture" src="${ctx}/statics/pictures/head.png">
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
                                            <img class="picture1"  src="${ctx}/statics/pictures/head.png">
                                        </div>
                                        <div class="username">
                                             <span>${one.NAME}</span>
                                        </div>
                                </div>
                                <div class="downContent">
                                    <div class="timeEvent">
                                        <span class="time">于<fmt:formatDate pattern="yyyy年MM月dd日" value="${one.TIME}" /></span>&nbsp;<span class="event">提交${one.TYPE}</span><span class="event">，请审批</span>
                                    </div>
                                </div>
                            </div>
                            <div class="rightButton">
                                <div onclick="toogleTip('block')" class="buttonDiv">
                                        		办理
                                </div>
                            </div>
                    </div>
            </div>
            
            </c:forEach>
            
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
                    <div class="checkButton">马上认证</div>
                </div>
                <div class="close" id="close" onclick="toogleTip('none')">
                    <span>×</span>
                </div>
            </div>
            <div class="gray ie" onclick="toogleTip('none')"></div>
            	<c:if test="${map.pageResult.total>=1 }">
            	<div class="pageDiv">
                <div style="float: right;">
                    <ul class="pagination" id="page1">
                    </ul>
                    <div style="display: inline-block;    vertical-align: top">
                        <select id="edu" name="edu" >
                            <option value="2" ${map.pageResult.pageSize=='2' ?'selected':''}>每页2条</option>
                            <option value="3" ${map.pageResult.pageSize=='3' ?'selected':''}>每页3条</option>
                            <option value="4" ${map.pageResult.pageSize=='4' ?'selected':''}>每页4条</option>
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

        <div class="info">
            <div class="topPictureDiv">
                <img class="topPicture"src="${ctx}/statics/pictures/head.png">
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
                <c:if test="${map.license==false}">
                	<div>
	                	<span class="littleTitle2">未认证</span>
	                </div>
                </c:if>
                <c:if test="${map.license==true}">
                	<div>
	                	<span class="littleTitle3">已认证</span>
	                </div>
                </c:if>
               
            </div>
            <div class="thirdRow">
                <span>最近登录:  2017-09-27 16:12:07</span>
            </div>
        </div>
        </div>
   </div>
<div style="clear: both; background-color: white;">   
<jsp:include page="/pages/comm/tail.jsp"/>
</div>
<script>
    Page({
        num: "${totalPage}",					//页码数
        startnum: "${currentPage}",				//指定页码
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
    function toogleTip(val) {
    	/* var license = document.getElementById("license").val();
    	if(license==false){ */
    		 $('.tip').css('display',val);
    	     $('.gray').css('display',val);
    }
    
</script>
</body>
</html>