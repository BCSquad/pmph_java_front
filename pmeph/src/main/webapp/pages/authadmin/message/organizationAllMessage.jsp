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
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/layer/layer.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/authadmin/message/organizationMessage.js?t=${_timestamp}" ></script>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}?t=${_timestamp}" type="text/css">
    <link rel="stylesheet"
          href="${ctx}/statics/authadmin/message/organizationMessage.css?t=${_timestamp}"
          type="text/css">
</head>
<body>
<jsp:include page="/pages/comm/headGreenBackGround.jsp">
    <jsp:param name="pageTitle" value="message"></jsp:param>
</jsp:include>
 <div class="message-body" >
        <div class="message">
           <a href="${pageContext.request.contextPath}/AllMessage/init.action?tag=receive">
                <div class="item">
                    <div class="on-text">全部消息</div>
                    <div class="on-line" ></div>
                </div>
            </a>
            <a href="${pageContext.request.contextPath}/AllMessage/init.action?tag=send">
                <div class="item">
                    <div class="off-text">发送历史</div>
                    <div class="off-line" ></div>
                </div>
            </a>
            <a href="${pageContext.request.contextPath}/authSendMessage/init.action">
                <div class="item" style="float: right;margin-right: 40px;width: 100px">
                    <div class="off-text"><img style="float: left;margin-top: 2px" src="<c:url value="/statics/image/aaa.png"/>"/><div>发送消息</div></div>
                    <div class="off-line"></div>
                </div>
            </a>
        </div>

     <!-- 系统消息标题悬浮框 -->
     <div class="bookmistake" id="bookmistake">
         <form id="bookmistakeform">
             <%--  <div class="apache">
                   <div class="mistitle">消息详情</div>
                  &lt;%&ndash; <div class="x" onclick="hideup()"></div>&ndash;%&gt;
               </div>--%>
             <div class="info">
                 <label style="margin-left: 20px" class="labell">标题:<span id="titlec"></span></label>

             </div>
             <div class="info">
                 <label style="margin-left: 20px" class="labell">发送人:<span id="sendc"></span></label>
             </div>
             <div class="info">
                 <label style="margin-left: 20px" class="labell">发送时间:<span id="timec"></span></label>
             </div>

             <div class="info">
                 <label style="margin-left: 20px" class="labell">内容:</label>
                 <div class="misarea" id="tcontent" ></div>
             </div>
             <div class="info">
                 <label style="margin-left: 20px" class="labell">附件:<span id="tattachment"  class="listContent"></span></label>
             </div>
             <div class="clear"></div>
         </form>
     </div>

        <div class="message-list"  >
            <div style="height: 20px;"></div>
            <div id="message-list">
            <c:forEach items="${list}" var="item" >
            <div class="item" id="item${item.id}">
                <div class="item-img">
                    <img src="${ctx}/${item.avatar}" />
                </div>
                <div class="content" onclick="system('${item.id}','${item.NAME}','${item.TIME}')" style="width:1000px">
                     <p class="title" >
                         <span class="msg">${item.title}</span>
                         <span class="time">${item.TIME}</span>
                     </p>
                    <p class="text" id="txt${item.id }">
                        <c:if test="${item.isread==true }"><img src="${ctx}/statics/image/readyes.png"  id="readyes${item.id}"/></c:if>
                        <c:if test="${item.isread==false }"><img src="${ctx}/statics/image/readno.png"  id="readno${item.id}"/></c:if>
                        ${item.msg_content}
                    </p>
                </div>
	                    <div style="float:left;color: #999999;font-size: 14px;height:20px;margin-top: 45px;" onclick="delmsg('${item.id}',1)">
	                        <span style="width:20px;height:20px;float:left;" class="deltag"></span>
	                        <span style="line-height: 20px;">删除</span>
	                    </div>
            </div>
            </c:forEach>
            </div>
            <c:if test="${listSize>=8 }">
             <div class="load-more" id="load-more">
                <a href="javascript:void(0);" onclick="loadMore('receive')"><p class="load-text">加载更多...</p></a>
            </div>
            </c:if>
             <input id="startPara" name="startPara"  type="hidden">
        
        </div>
 </div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>

