<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<script type="text/javascript">
	var contxtpath='${pageContext.request.contextPath}/';
</script>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>医学随笔</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/resources/commuser/cms/cmscontents.css" type="text/css">
</head>
<body>
<div style="width: 100%;padding: 0;margin: 0;height: 81px;border: none;overflow: hidden;">
	<jsp:include page="/pages/comm/head.jsp"></jsp:include> 
</div>
<!--  <iframe style="width: 100%;padding: 0;margin: 0;height: 81px;border: none" src="${ctx }/pages/comm/head.jsp"></iframe>-->
<div class="body">
    <div class="content-wrapper">
    	<div class="sbxq_title">
			<span>文章 > 医学随笔</span>
		</div>
        <div class="notes">
            <div class="item" onclick="">
                <!--<div class="command">
                  <span style="margin-left: 5px">推荐</span>
                </div>-->
                <div  class="content" >
                   <div class="content-image">
                       <img src="../测试文件/p2.png" />
                   </div>

                    <p  class="content-title">尊敬的白衣人，你准备好了吗？</p>
                    <p  class="content-text">
                            技术等级好地方海景房独活独活肌肤的家分店绝代风华军火贩海景房禾
                    <div  class="foot">
                        <div  class="msg">
                        	<span id="span_1"></span><span id="span_4">453</span>
                        	<span id="span_2"></span><span id="span_4">53</span>
                        	<span id="span_3"></span><span id="span_4">45</span>
                        </div>
                    </div>
                    <div class="ryxx">
                    	<div class="ryxx_foot">
                    		<img class="ryxx_tx" src="../image/tx_1.png" />
                    		<span class="ryxx_xm">黄钰</span>
                    		<span class="ryxx_sj">2017.11.21</span>
                    	</div>
                    </div>
                </div>
            </div>
        </div>
        <div style="clear: both"></div>
        <div class="_load">
        	<span>加载更多...</span>
        </div>
    </div>
</div>
	<!-- <iframe style="width: 100%;clear:both;padding: 0;margin: 0;height: 200px;border: none" src="../comm/tail.html"></iframe>-->
	<div style="background-color: white;width: 100%;padding: 0;margin: 0;height: 220px;border: none;overflow: hidden;">
		<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
	</div> 
</body>
</html>