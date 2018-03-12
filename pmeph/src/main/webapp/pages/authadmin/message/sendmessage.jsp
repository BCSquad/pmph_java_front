<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>发送消息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
  <link rel="stylesheet" href="${ctx}/statics/authadmin/message/inforelease.css" type="text/css">
     <link rel="stylesheet"
          href="${ctx}/statics/authadmin/message/sendmessage.css"
          type="text/css">
    
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
     <script type="text/javascript"
            src="${ctx}/resources/comm/ueditor1.4.3.3/ueditor.config.js"></script>
    <script type="text/javascript"
            src="${ctx}/resources/comm/ueditor1.4.3.3/ueditor.all.min.js"></script>
    <script type="text/javascript"
            src="${ctx}/resources/comm/ueditor1.4.3.3/lang/zh-cn/zh-cn.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="${ctx}/resources/comm/layer/layer.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/authadmin/message/sendMessage.js" type="text/javascript"></script>

  <%--   <script src="${ctx}/resources/authadmin/message/inforelease.js"></script> --%>
   <!--  <style type="text/css">
		input::-ms-clear{display: none;}
    </style> -->
</head>

<body>
<!-- 隐藏域 -->
	<%--<input type="hidden" id="validate" value="${resultFlag}">--%>
	<input type="hidden" id="search-name-temp" value="">
	<input type="hidden" id="page-num-temp" value="1">
<jsp:include page="/pages/comm/headGreenBackGround.jsp">
    <jsp:param name="pageTitle" value="message"></jsp:param>
</jsp:include>
<div class="message-body" id="message-body">
    <div class="message">
        <a href="${pageContext.request.contextPath}/AllMessage/init.action?tag=receive">
            <div class="item">
                <div class="off-text">全部消息</div>
                <div class="off-line"></div>
            </div>
        </a> 
        <a href="${pageContext.request.contextPath}/AllMessage/init.action?tag=send">
             <div class="item">
                 <div class="off-text">发送历史</div>
                 <div class="off-line" ></div>
             </div>
         </a>
        
        <a href="${pageContext.request.contextPath}/authSendMessage/init.action">
        <div class="item">
            <div class="on-text">发送消息</div>
            <div class="on-line"></div>
        </div>
    </a>
    </div><!-- onsubmit="return getValue();"  -->
    <%--<form class="message-list" id="theForm"--%>
          <%--action="${pageContext.request.contextPath}/authSendMessage/sendMessage.action" method="post"--%>
          <%--enctype="multipart/form-data">--%>
        <form class="message-list" id="theForm">
        <div class="title">
            <span class="span1" style="margin-right: 25px;"><span class="span2">*</span>标题</span>
            <input type="text" name="titleValue" id="TitleValue" placeholder="请输入消息标题 30字以内"
                   oninput="if(value.length>30){value=value.slice(0,30)}" value="${titleValue}" required/>

        </div>
        <div class="sel-object">
            <span><span class="span2">*</span>发送对象</span>
            <input id="one" type="radio" name="sendObject" value="0" checked="true"/>所有人
            <input id="two" type="radio" name="sendObject"/>教材报名者
            <input type="hidden" name="radioValue" id="radioValue"/>
        </div>
        <div class="message-input">
            <div class="tip" style="float:left;"><span class="span2">*</span>消息内容</div>
            <div style="height:auto !important;min-height:600px;height:600px;">
                <script type="text/plian" id="mText"
                        style="width: 1060px; height:478px;scorll-y:true;position:absolute;margin-left:0px;left:126px;"></script>
                <input type="hidden" id="UEContent" name="UEContent" value=""/>
                    </div>
           <%--<div style="width: 1060px; height:500px;position:absolute;left:126px;top:0px">  <textarea rows="8" cols="50" placeholder="请输入内容" id="UEContent" name ="UEContent" value="${UEContent}" required></textarea></div>--%>
        </div>
       <!--  <div class="appendix">
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
        </p> -->

        <div class="send">
            <div style="height: 84px;">
                <input type="button"  value="发送" id="send" onclick="sendf()"/>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
UE.getEditor('mText');
var ue = UE.getEditor('mText');
ue.ready(function () {

})
</script>
















<div class="body" id="selectBook">
    <div class="content-wrapper" >
    		 <div class="box">
            <div class="boxsm">
                <div class="item1">
                    <span  class="title2" >选择教材</span>
                    <select   class="st_2"  id="select-search-condition" >
                    	<option value="">请选择</option>
                    	<c:forEach items="${listMenu}" var="item">
								<option value="${item.material_name}">${item.material_name}</option>
							</c:forEach>
                    </select>
                </div>

                <div class="messages">
                    <span class="title1" >书籍列表</span>
                    <table class="table">
                        <thead>
                        <tr>
                            <td style="text-align: center;"><input  type="checkbox" class="check_box" name="allChecked" id="allChecked" onclick="DoCheck()" value="0"/></td>
                            <td>书序</td>
                            <td>书籍名称</td>
                            <td>版次</td>
                        </tr>
                        </thead>
                        <tbody id="tbody1">
                        
                        </tbody>
                    </table>
                </div>
					 
					 <div class="pagination-wrapper">
						<ul class="pagination" id="page1">
                        </ul>
                        <div style="display: inline-block;    vertical-align: top">
                            <select id="page-size-select" name="page-size-select" onchange="selectPageSize();">
                                <option value="10">每页10条</option>
                                <option value="20">每页20条</option>
                                <option value="50">每页50条</option>
                            </select>
                        </div>
                        <div class="pageJump">
                            <span>共<span ><input type="text" id="totoal_count" value="${totoal_count }"></span>页，跳转到</span>
                            <input type="text"/>
                            <span class="pp">页</span>
                            <button type="button" class="button">确定</button>
                        </div>
						</div>
						
					<div class="bom_btn">
                    <button class="bom_btn_01" id="backupdate">返回编辑</button>
                    <button class="bom_btn_02" id="send2">发送</button>
                </div>
              </div>
            </div>
        </div>
    </div>

<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>

