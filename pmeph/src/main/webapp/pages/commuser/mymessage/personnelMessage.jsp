<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>私信列表</title>
<script>
    var contextpath='${pageContext.request.contextPath}/';
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/commuser/mymessage/message.css?t=${_timestamp}" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/commuser/mygroup/chat.css?t=${_timestamp}" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}" />
<script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
<script type="text/javascript" src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
<script type="text/javascript" src="${ctx}/resources/comm/reload.js?t=${_timestamp}"></script>
<script src="${ctx}/statics/js/jquery/jquery.selectlist.js"></script>
<script src="${ctx}/resources/commuser/mymessage/personnelMessage.js?t=${_timestamp}"></script>

<style type="text/css">
#rightContent .select-button {
	background: #f6f6f6;
}

#rightContent .select-wrapper {
	border: none;
}
</style>
</head>
<script type="text/javascript">
    $(function(){
        //---------------统计未读数量----------------
        $("#sxid").html('私信<span style="display: inline-block;position:absolute;background: #ff0000 !important;color: #fff;font-size: 10px;font-weight: 400;' +
            'line-height: 10px;border-radius: 50%;padding: 3px 0px;right: 10;top: 0;width: 20px;height: 10px;text-align: center;">' + (${no_read_count}?${no_read_count}:0) + '</span>');
    });



</script>
<body>
	<jsp:include page="/pages/comm/head.jsp"></jsp:include>
	<div class="body">
	<div class="content-wrapper" style="line-height:normal">
	<div class="messageList">
		<span><a class="otherOptions" href="${ctx}/message/noticeMessageList.action">通知</a></span>
		<span><a href="${ctx}/message/applyMessageList.action" class="unselected">申请</a></span>
		<span id="otherSelected"><span id="sxid" style="position: relative">私信</span></span> <span id="rightContent">筛选：
			<select id="select" title="请选择">
				<option value="">全部</option>
				<option value="reade">已读</option>
				<option value="noreade">未读</option>
		</select>
		</span>
	</div>
	<div class="message">
		<%-- <c:choose>
			<c:when test="${list.size()> 0}"> --%>
				<table id="list" class='table'>
				</table>
				
			<%-- </c:when>
			<c:otherwise>
				<div class="no-more">
	                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
	                    <span>木有内容呀~~</span>
               	</div>
			</c:otherwise>
		</c:choose> --%>
		
		<!--  <a class="a" href="javascript:void(0)" onclick="show()" style=" background:red">弹出</a>-->

		<div class="b hidden" id="box">
			<div class="hiddenX hidden" id="close">
				<img id="hide" style="width: 100%; height: 100%;" src="${ctx}/statics/image/yellowclose.png">
			</div>
			<span class="personMessageTitle"></span>
			<input id='talk' type='hidden' value=''/>
            <div class="contentBox" id="dialogue">
            	<div id="talkList"></div>
	        </div>
			<div class="inputBox">
                <div style="float: left;width: 80%;height: 100%">
                <textarea id= "content" style="width: 100%;height: 98%;border: none;outline:0;font-size:15px;" type="text" placeholder="请输入消息内容,按回车键发送" ></textarea>
                </div>
                <div style="float: left;width: 20%;height: 100%">
                <div class="div_btn11" style="cursor: pointer;">
                    <span class="button11" id ='sendNewMsg'>发送</span>
                </div>
                </div>
            </div>
		</div>
		<div id="loadMore" class="load-more clearfix">加载更多...</div>
	</div>
	</div>
	</div>
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>

</html>