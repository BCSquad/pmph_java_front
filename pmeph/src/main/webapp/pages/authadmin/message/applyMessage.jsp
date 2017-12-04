<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
 <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>申请列表</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <link rel="stylesheet" href="${ctx}/statics/authadmin/message/message.css" type="text/css">
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js"></script>
    <script src="${ctx}/resources/authadmin/message/message.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
    <style type="text/css">
        #rightContent .select-button {
            background: #f6f6f6;
        }

        #rightContent .select-wrapper {
            border: none;
        }
    </style>
<script type="text/javascript">
		$(function () {
			$('#condition').selectlist({
                zIndex: 10,
                width: 70,
                height: 20,
                optionHeight: 20,
				triangleColor:'#333333',
				onChange:function (){
					window.location.href="${ctx}/message/applyMessageList.action?condition="+$("input[name='condition']") .val();
				}	
			});
		})
		
		
		
</script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div style="align-self: center">
    <div class="messageList">
        <span><a href="${ctx}/message/noticeMessageList.action" class="otherOptions">通知</a></span>
        <span id="otherSelected"><b>申请</b></span>
        <span><a href="../mymessage/personnelMessage.html" class="unselected">私信</a></span>
            <span id="rightContent">
                <span class="filtrate-wrapper">
                  		  筛选:
	                <select id="condition" name="condition" title="请选择">
	                    <option value="3" ${condition=='3' ?'selected':''}>全部</option>
	                    <option value="2" ${condition=='2' ?'selected':''}>未读</option>
					    <option value="1" ${condition=='1' ?'selected':''}>已读</option>
	                </select>
                </span>
            </span>
    </div>
</div>
<div class="message">
    <table class="table" id="applyTable">
    	<c:forEach items="${list}" var="message">
        <tr>
            <th rowspan="2" class="headPortrait"><img src="${ctx}/statics/pictures/head.png" class="picture" ></th>
        </tr>
        <tr>
            <td ><span class="apply">${message.realname}申请加我为好友</span><span class="time"><fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${message.gmt_create}" /></span></td>
            <td class="buttonSpan">
            	<c:if test="${message.status==0}">
                <button class="buttonIgnore" onclick="ignore(${message.id})">忽略</button><button class="buttonAccept" onclick="accept(${message.id})">接受</button>
                </c:if>
                <c:if test="${message.status==2}">
                <span class="accept-text">已接受</span>
                </c:if>
                <c:if test="${message.status==1}">
                <span class="ignore-text">已忽略</span>
                </c:if>
            </td>
        </tr>
        <tr>
            <td colspan="4"  ><hr class="line"></td>
        </tr>
		</c:forEach>
    </table>
    <c:if test="${listSize>=8}">
    <div class="load-more clearfix" onclick="loadMoreApply()">加载更多...</div>
    <input id="applyPara" name="applyPara" type="hidden">
    </c:if>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>

</body>
<script type="text/javascript">
    $(function(){
        $('#select').selectlist({
            zIndex: 10,
            width: 80,
            height: 30,
            optionHeight: 30
        });
    })
    function ignore(id){
    	var flag="1";
    	window.location.href="${ctx}/message/updateApplyMessage.action?id="+id+"&flag="+flag;
    }
    function accept(id){
    	var flag="2";
    	window.location.href="${ctx}/message/updateApplyMessage.action?id="+id+"&flag="+flag;
    }
    /* function loadMore(addPara){
    	
		addPara+=3;
		window.location.href="${ctx}/message/applyMessageList.action?addPara="+addPara+"&condition="+$("input[name='condition']") .val();
	} */
</script>
</html>