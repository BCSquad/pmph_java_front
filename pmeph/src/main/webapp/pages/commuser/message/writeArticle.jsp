<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}';


    </script>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${ctx}/statics/commuser/message/writeArticle.css"
	type="text/css">
<script type="text/javascript"
	src="${ctx}/resources/comm/ueditor1.4.3.3/ueditor.config.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/comm/ueditor1.4.3.3/ueditor.all.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/comm/ueditor1.4.3.3/lang/zh-cn/zh-cn.js"></script>
	<script src="${ctx}/resources/comm/jquery/jquery.js"></script>
	<script src="${ctx}/resources/comm/jquery/jquery.form.js"></script>
	<script src="${ctx}/resources/commuser/message/writeArticle.js" type="text/javascript"></script>
	<script src="${ctx}/resources/comm/base.js"></script>
	
	<link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
</head>

<body>
	<jsp:include page="/pages/comm/head.jsp"></jsp:include>
	<!--action="http://localhost:8080/pmeph/" method="post" enctype="multipart/form-data" onsubmit="return writeArticleValidate();"  target="targetIfr"  -->
	<form id="form1" >
    <div class="sxy-div-content">
        <div class="sxy-div-content">
            <img src="${ctx}/statics/testfile/5写文章.png" alt="" />
        </div>
    </div>
    <div class="sxy-div-content2">
        <div style="height:25px;"></div>
        <font class="sxy-font1">文章标题：</font>
    </div>
    <div class="sxy-div-content2">
        <input type="text" class="sxy-txt2" id="TitleValue" name="titleValue" placeholder="输入文章标题请限30字以内.."  oninput="if(value.length>30){value=value.slice(0,30)}" value="${title}" />
    </div>
    <div class="sxy-div-content4">
        <div style="height:10px;"></div>
        <font class="sxy-font1">添加文章内容：</font>
    </div>
    <div class="sxy-div-content">
        <div style="height:auto !important;min-height:600px;height:600px;">
            <!--<script type="text/plian" id="mText" style="width: 1060px; height:478px;position:absolute;left:126px;top:0px"></script>-->
            <script type="text/plian" id="mText" style="width: 1200px; height:478px;scorll-y:true;position:absolute;margin-left:0px;"></script>
           <input type="hidden" id="UEContent" name="UEContent"  value="" />
        </div>
    </div>
    <div class="sxy-div-content3">
        <img style="width: 1200px;" alt="" src="${ctx}/statics/image/_cupline.jpg"/>
    </div>
    <div id="sxy-return">
    <input type="hidden" id="msg_id" name="msg_id" value="${mid}" />
    	<input type="hidden" id="btn_type" name="btnType"  />
    	<input type="hidden" id="submitTypeCode" name="submitTypeCode" value="0" />
        <input class="sxy-btn" type="button" value="保存" onclick="btntype(0)" />
        <input class="sxy-btn" type="button" value="提交" onclick="btntype(1)"  />
    </div>
    </form>
    <div id="content" hidden="true"></div>
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
<iframe name="targetIfr" style="display:none"></iframe>
<script type="text/javascript">
	UE.getEditor('mText');
	if(('${UEContent}').length!=0){
		var ue = UE.getEditor('mText');
		ue.ready(function(){
			ue.setContent('${UEContent}');
		});
		$("#submitTypeCode").val("1");
	}
</script>