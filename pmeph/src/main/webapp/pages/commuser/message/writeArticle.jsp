<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';


    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>写文章</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet"
          href="${ctx}/statics/commuser/message/writeArticle.css?t=${_timestamp}"
          type="text/css">
    <script type="text/javascript"
            src="${ctx}/resources/comm/ueditor1.4.3.3/ueditor.config.js?t=${_timestamp}"></script>
    <script type="text/javascript"
            src="${ctx}/resources/comm/ueditor1.4.3.3/ueditor.all.min.js?t=${_timestamp}"></script>
    <script type="text/javascript"
            src="${ctx}/resources/comm/ueditor1.4.3.3/lang/zh-cn/zh-cn.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.form.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/commuser/message/writeArticle.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.fileupload.js?t=${_timestamp}"
            type="text/javascript"></script>


    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
</head>

<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<!--action="http://localhost:8080/pmeph/" method="post" enctype="multipart/form-data" onsubmit="return writeArticleValidate();"  target="targetIfr"  -->
<form id="form1" style="margin-bottom: 15px;">
    <div class="sxy-div-content">
        <div class="sxy-div-content">
            <img src="${ctx}/statics/image/write_article1.png" alt=""/>
        </div>
    </div>
    <div class="sxy-div-content2">
        <div style="height:25px;"></div>
        <font class="sxy-font1">文章标题：</font>
    </div>
    <div class="sxy-div-content2">
        <input type="hidden" name="atrticle_id" id="atrticle_id" value="${atrticle_id }">
        <input type="text" class="sxy-txt2" id="TitleValue" name="titleValue" placeholder="输入文章标题请限100字以内.."
               onblur="if($('#TitleValue').val().length>100){$('#TitleValue').val($('#TitleValue').val().slice(0,100))};" value="${title}"/>
    </div>
    <div class="sxy-div-content4">
        <div style="height:10px;"></div>
        <font class="sxy-font1">添加文章内容：</font>
    </div>
    <div class="sxy-div-content">
        <div style="height:auto !important;min-height:600px;height:600px;">
            <!--<script type="text/plian" id="mText" style="width: 1060px; height:478px;position:absolute;left:126px;top:0px"></script>-->
            <script type="text/plian" id="mText"
                    style="width: 1200px; height:478px;scorll-y:true;position:absolute;margin-left:0px;"></script>
            <input type="hidden" id="UEContent" name="UEContent" value=""/>
        </div>
    </div>
    
    <div class="sxy-div-content2">
        <div style="height:25px;"></div>
        <font class="sxy-font1">文章来源:</font>
    </div>
    <div class="sxy-div-content2">
        <%-- <input type="hidden" name="author_name" id="author_name" value="${author_name }"> --%>
        <input type="text" class="sxy-txt2" id="author_name" name="author_name" placeholder="请输入文章作者姓名"
               onblur="if($('#author_name').val().length>40){$('#author_name').val($('#author_name').val().slice(0,39))};" value="${author_name}"/>
    </div>

    <div class="sxy-div-content upload-image">
        <div class="button" onclick="refresh()"> 上传封面图片</div>
        <div class="fileinfo"><span class="filename">${coverName }</span><span class="preview"> 预览</span></div>
    </div>

    <div class="sxy-div-content3">
        <img style="width: 1200px;" alt="" src="${ctx}/statics/image/_cupline.jpg"/>
    </div>
    <div id="sxy-return">
        <input type="hidden" id="msg_id" name="msg_id" value="${mid}"/>
        <input type="hidden" id="btn_type" name="btnType"/>
        <input type="hidden" id="image" name="image" value="${cover }"/>
         <input type="hidden" id="imageName" name="imageName" value="${coverName }"/>
        <input type="hidden" id="submitTypeCode" name="submitTypeCode" value="${submitTypeCode }"/>
        <input class="sxy-btn topub" type="button" value="发表" onclick="btntype(0)" id="topub0" />&nbsp;&nbsp;
        <input class="sxy-btn topub" type="button" value="暂存" onclick="btntype(1)" id="topub1" />
    </div>
</form>
<div id="content" hidden="true"></div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
<iframe name="targetIfr" style="display:none"></iframe>
<script type="text/javascript">
    UE.getEditor('mText');
    if (('${UEContent}').length != 0) {
        var ue = UE.getEditor('mText');
        ue.ready(function () {
            ue.setContent('${UEContent}');
        });
        $("#submitTypeCode").val("1");
    }
</script>
