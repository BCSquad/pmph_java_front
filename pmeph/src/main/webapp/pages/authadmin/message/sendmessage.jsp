<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>Insert title here</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet"
          href="${ctx}/statics/authadmin/message/sendmessage.css"
          type="text/css">
    <script type="text/javascript"
            src="${ctx}/resources/comm/ueditor1.4.3.3/ueditor.config.js"></script>
    <script type="text/javascript"
            src="${ctx}/resources/comm/ueditor1.4.3.3/ueditor.all.min.js"></script>
    <script type="text/javascript"
            src="${ctx}/resources/comm/ueditor1.4.3.3/lang/zh-cn/zh-cn.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/authadmin/message/sendMessage.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <script src="${ctx}/resources/commuser/homepage/homepage.js"></script>
    <script src="${ctx}/resources/comm/layer/layer.js"></script>
</head>
<body>
<jsp:include page="/pages/comm/headGreenBackGround.jsp"></jsp:include>
<div class="message-body">
    <div class="message">
        <a href="${pageContext.request.contextPath}/authSendMessage/initAllMessage.action">
            <div class="item">
                <div class="off-text">全部消息</div>
                <div class="off-line"></div>
            </div>
        </a> <a href="${pageContext.request.contextPath}/authSendMessage/init.action">
        <div class="item">
            <div class="on-text">发送消息</div>
            <div class="on-line"></div>
        </div>
    </a>
    </div>
    <form class="message-list" id="theForm"
          action="${pageContext.request.contextPath}/authSendMessage/sendMessage.action" method="post"
          onsubmit="return getValue();" enctype="multipart/form-data">
        <div class="title">
            <span class="span1" style="margin-right: 25px;"><span class="span2">*</span>标题</span>
            <input type="text" name="titleValue" id="TitleValue" placeholder="请输入消息标题 30字以内"
                   oninput="if(value.length>30){value=value.slice(0,30)}"/>

        </div>
        <div class="sel-object">
            <span><span class="span2">*</span>发送对象</span>
            <input id="one" type="radio" name="sendObject" value="0" checked="true"/>所有人
            <input id="two" type="radio" name="sendObject"/>教材报名者
            <input type="hidden" name="radioValue" id="radioValue"/>
        </div>
        <div class="message-input">
            <span class="tip"><span class="span2">*</span>消息内容</span>
            <script type="text/plian" id="mText"
                    style="width: 1060px; height:478px;position:absolute;left:126px;top:0px"></script>
            <input type="hidden" name="UEContent" id="UEContent"/>
        </div>
        <div class="appendix">
                         <span class="span1">
                              <span class="span2">*</span>添加附件
                         </span>

            <div style="display: inline;">
                <span class="icon"> </span> <span class="name" id="file_name"></span> <a href="javascript:void(0);"
                                                                                         class="operate input">
                <span id="up_txt">上传</span> <input type="file" class="file" id="file_id" name="file"/></a>
            </div>

        </div>
        <p class="file-tip">
            文件大小不超过100M
        </p>

        <div class="send">
            <div style="height: 84px;">
                <input type="submit" value="发送" id="send"/>
            </div>
        </div>
    </form>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
<script type="text/javascript">
    UE.getEditor('mText');
</script>
