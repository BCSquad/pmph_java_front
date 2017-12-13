<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
    <title>申请列表</title>
    <script>
        var contextpath='${pageContext.request.contextPath}/';
    </script>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <link rel="stylesheet" href="${ctx}/statics/commuser/message/message.css" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/comm/layer/layer.js"></script>
    <script src="${ctx}/resources/commuser/message/message.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
</head>
    <style type="text/css">
        #rightContent .select-button {
            background: #f6f6f6;
        }

        #rightContent .select-wrapper {
            border: none;
        }
    </style>
    <script type="text/javascript">
        $(function(){
        	
            $('#select').selectlist({
                zIndex: 10,
                width: 100,
                height: 20,
                optionHeight: 20,
                triangleColor:'#333333',
                onChange:function (){
					window.location.href="${ctx}/message/noticeMessageList.action?condition="+$("input[name='select']") .val();
				}
            });
        });
    
        
      //删除通知
       function deleteNotice(nid){
       	window.message.confirm(
       			'确认删除吗?',
       			{btn:['确定','取消']},
       			function(id){
       				$.ajax({
       					type:'post',
       					data:{"nid":nid},
       					url:"deleteNoticeMessage.action?",
       					async:false,
       					dataType:'json',
       					success:function(json){
       						//window.message.success("删除成功");
       						window.location.href="${ctx}/message/noticeMessageList.action?condition="+$("input[name='select']") .val();
       					}
       					});
       			},
       			function(){
       				
       			}
       	);
        }
    </script>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>

        <div class="messageList">
            <span id="selected"><b>通知</b></span>
            <span><a href="${ctx}/message/applyMessageList.action" class="unselected">申请</a></span>
            <span><a href="${ctx}/mymessage/listMyMessage.action"  class="unselected">私信</a></span>
            <span id="rightContent" >筛选：
                <select id="select" name="select" title="请选择" >
                    <option value="3" ${condition=='3' ?'selected':''}>全部</option>
                    <option value="1" ${condition=='1' ?'selected':''}>公告</option>
                    <option value="0" ${condition=='0' ?'selected':''}>系统消息</option>
                </select>
            </span>
        </div>
        <div class="message">
            <table class="table" id="messageTable">
           
            <c:forEach items="${list}" var="message">
                <tr style="width: 70%">
                    <th rowspan="2" class="headPortrait"><img  class ="pictureNotice" src="${ctx}/statics/pictures/head.png"></th>
                    <td class="type1"><span><c:if test="${message.msg_type==1}">公告 </c:if><c:if test="${message.msg_type==0}">系统消息</c:if></span><span class="time1" id="gmt_create"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${message.gmt_create}" /></span></td>
                </tr>
                <tr style="width: 30%">
                    <td colspan="2" class="title">${message.title}</td>
                    <td class="buttonDetail">
                    	<c:if test="${message.msg_type==1}">
                        <div class="buttonAccept"><a href="${ctx}/message/noticeMessageDetail.action?id=${message.msg_id}">查看详情</a></div>
                        </c:if>
                        <c:if test="${message.msg_type==0}">
   					    <span class="deleteButton" onclick="deleteNotice(${message.id })"><span style="font-size:18px;">×</span> 删除</span>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center" ><hr class="line"></td>
                </tr>
			</c:forEach>
            </table>
            <c:if test="${listSize>=8}">
            <div class="load-more clearfix" onclick='loadMore()'>加载更多...</div>
            <input id="startPara" name="startPara" type="hidden">
            </c:if>
        </div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>