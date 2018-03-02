<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>消息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/layer/layer.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/authadmin/message/organizationMessage.js" ></script>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet"
          href="${ctx}/statics/authadmin/message/organizationMessage.css"
          type="text/css">
</head>
<body>
<jsp:include page="/pages/comm/headGreenBackGround.jsp">
    <jsp:param name="pageTitle" value="message"></jsp:param>
</jsp:include>
 <div class="message-body" >
        <div class="message">
           <a href="${pageContext.request.contextPath}/authSendMessage/initAllMessage.action">
                <div class="item">
                    <div class="on-text">全部消息</div>
                    <div class="on-line" ></div>
                </div>
            </a>
            <a href="${pageContext.request.contextPath}/authSendMessage/init.action">
                <div class="item">
                    <div class="off-text" >发送消息</div>
                    <div class="off-line" ></div>
                </div >
            </a>
        </div>

     <!-- 系统消息标题悬浮框 -->
     <div class="bookmistake" id="bookmistake">
         <form id="bookmistakeform">
             <div class="apache">
                 <div class="mistitle">系统消息详情</div>
                 <div class="x" onclick="hideup()"></div>
             </div>
             <div class="info">
                 <label style="margin-left: 20px" class="labell">标题:<span id="titlec"></span></label>

             </div>
             <div class="info">
                 <label style="margin-left: 20px" class="labell">发送人:系统</label>
             </div>
             <div class="info">
                 <label style="margin-left: 20px" class="labell">发送时间:<span id="timec"></span></label>
             </div>

             <div class="info">
                 <label style="margin-left: 20px" class="labell">内容:</label>
                 <textarea class="misarea" id="tcontent" disabled="disabled"></textarea>
             </div>
         </form>
     </div>

        <div class="message-list"  >
            <div style="height: 20px;"></div>
            <div id="message-list">
            <c:forEach items="${list}" var="item" >
            <div class="item">
                <div class="item-img">
                    <img src="${ctx}/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                     <p class="title" >
                         <span class="msg">${item.NAME}</span>
                         <span class="time">${item.TIME}</span>
                     </p>
                    <p class="text" onclick="system('${item.msg_id}','${item.NAME}','${item.TIME}')">
                        ${item.TYPE}
                    </p>
                </div>
            </div>
            </c:forEach>
            </div>
            <c:if test="${listSize>=8 }">
             <div class="load-more" id="load-more">
                <a href="javascript:void(0);" onclick="loadMore()"><p class="load-text">加载更多...</p></a>
            </div>
            </c:if>
             <input id="startPara" name="startPara"  type="hidden">
        
        </div>
 </div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>

