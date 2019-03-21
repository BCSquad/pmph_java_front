<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
 <c:set var="ctx" value="${pageContext.request.contextPath}"/>
 <script>
    var contextpath='${pageContext.request.contextPath}/';
</script>
    <title>申请列表</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="${ctx}/statics/commuser/message/message.css?t=${_timestamp}" type="text/css">
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/commuser/message/message.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/reload.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
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
                zIndex: 100,
                width: 70,
                height: 20,
                optionHeight: 20,
				triangleColor:'#333333',
				onChange:function (){
					window.location.href="${ctx}/message/applyMessageList.action?condition="+$("input[name='condition']") .val();
				}	
			});
            $("#sqid").html('申请<span style="display: inline-block;position:absolute;background: #ff0000 !important;color: #fff;font-size: 10px;font-weight: 400;' +
                'line-height: 10px;padding: 3px 0px;border-radius: 50%;right: 10;top: 0;width: 20px;height: 10px;text-align: center;">' + (${nodealcount}?${nodealcount}:0)  + '</span>');

            $.ajax({
                type:'post',
                url:"getMessageCount.action",
                async:false,
                dataType:'json',
                success:function(json){
                    $("#tzid").html('通知<span style="display: inline-block;position:absolute;background: #ff0000 !important;color: #fff;font-size: 10px;font-weight: 400;' +
                        'line-height: 10px;border-radius: 50%;padding: 3px 0px;right: 10;top: 0;width: 20px;height: 10px;text-align: center;">' + json.nodealcount + '</span>');
                }
            });
            $.ajax({
                type:'post',
                url:"${ctx}/mymessage/getMyMessageCount.action",
                async:false,
                dataType:'json',
                success:function(json){
                    $("#sxid").html('私信<span style="display: inline-block;position:absolute;background: #ff0000 !important;color: #fff;font-size: 10px;font-weight: 400;' +
                        'line-height: 10px;border-radius: 50%;padding: 3px 0px;right: 10;top: 0;width: 20px;height: 10px;text-align: center;">' + json.no_read_count + '</span>');
                }
            });

        });
</script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div style="align-self: center">
    <div class="messageList">
        <span><a id="tzid" href="${ctx}/message/noticeMessageList.action" class="otherOptions" style="position: relative">通知</a></span>
        <span id="otherSelected"><span id="sqid" style="position: relative">申请</span></span>
        <span><a id='sxid' href="${ctx}/mymessage/listMyMessage.action" class="unselected" style="position: relative">私信</a></span>
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
    	<c:choose>
    		<c:when test="${listSize>0}">
    			<c:forEach items="${list}" var="message">
        <tr>
            <th rowspan="2" class="headPortrait"><img src="${ctx}/${message.avatar}" class="picture" ></th>
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
    		</c:when>
    		<c:otherwise>
    			<div class="no-more">
                    <%--<img src="<c:url value="/statics/image/aaa4.png"></c:url>">
                    <span>木有内容呀~~</span>--%>
                </div>
    		</c:otherwise>
    	</c:choose>
    	
    </table>
    <c:if test="${count>0}">
	    <div id="loadMoreDiv" class="load-more clearfix" onclick="loadMoreApply()">加载更多...</div>
	    <input id="applyPara" name="applyPara" type="hidden">
	    <input id="jsptype" name="jsptype" type="hidden" value="1">
    </c:if>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>

</body>
<script type="text/javascript">
    $(function(){
        $('#select').selectlist({
            zIndex: 100,
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

</script>
</html>