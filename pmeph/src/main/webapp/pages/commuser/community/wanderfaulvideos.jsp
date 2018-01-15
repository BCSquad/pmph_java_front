<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = path+"/";
String contextpath=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     <script>
		var contextpath = "${pageContext.request.contextPath}/";
	</script>
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>微视频</title>
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css"/>
   
    <link rel="stylesheet" href="<%=path%>/statics/commuser/community/wanderfaulbookcomments.css" type="text/css">
    <script src="<%=path%>/resources/comm/jquery/jquery.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js"></script>
    <script src="<%=path%>/resources/comm/base.js"></script>
    <%-- <script src="<%=path%>/resources/commuser/community/communitylist.js"></script> --%>
</head>
<style type="text/css">
     .play{
       z-index: 110;position: absolute;width: 80px; height: 80px;
       background-image: url(${ctx}/statics/image/css_sprites.png);
       background-position: -216px -265px;margin-top: 52px;margin-left: 75px;"
   }
   .video-a{width:230px;border:1px solid #CCCCCC;height:260px;margin-top:15px}
   .video-b{z-index:99;position:absolute;background-color: #000;height:184px;width:230px;opacity:0.5;}
   .video-c{width:230px;background-color: #EEEEEE;height:184px}
   .video-d{width:210px;border-bottom:1px solid #EEEEEE;height:44px;margin:0px auto 0px;line-height: 44px;font-size: 16px;color:#333333}
   .video-e{height:30px;width:210px;margin:0px auto 0px;color:#999999;font-size: 14px}
   .video-f{float:left;line-height: 30px};
   .video-g{float:right;line-height:30px;margin-left:5px}
   .video-h{float:right;width:20px;height:30px;
            background-image: url(${ctx}/statics/image/css_sprites.png);
            background-position:-418px -144px;}
</style>
<body>
            <jsp:include page="/pages/comm/head.jsp"></jsp:include>
			<div style="background-color: #f6f6f6;padding-top:28px;padding-bottom:130px"> 
			<div class="content-wrapper">
    		<div class="area2">
        		<div class="namehead" style="width:54px">
        		<span class="name" >微视频</span>
        		</div>
   			</div>
     		<div style="margin-top:25px;background-color: #ffffff;padding-top:10px">
     		
     		<div style="width:1045px;margin:0px auto 0px">
		     		<div style="width:347px;float:left;margin-top: 40px">
			          <div class="video-a" style="width:317px;height:275px;margin: 0px auto 0px;">
			          <div class="play" style="margin:49px 0px 0px 118px"></div>
					  <div id="popDiv" class="video-b" style="width:317px;height:178px"> </div>
		              <div class="video-c" style="width:317px;height:178px;text-align: center;">
		                    <img src="${ctx }/statics/testfile/testvideoimage.png" style="height:178px;"/>
		              </div>
		             <div class="video-d" style="width:297px">
		                                               解剖学讲解
		             </div>
		            <div  class="video-e" style="width:297px">
		                 <span class="video-f" >2017-12-22</span>
		                 <span class="video-g" style="float:right;line-height:30px;margin-left:5px">98</span>
		                <span class="video-h"></span>
		              </div>
		          </div>
		        </div>
		      
		      <div style="width:1045px;margin:0px auto 0px">
		     		<div style="width:347px;float:left;margin-top: 40px">
			          <div class="video-a" style="width:317px;height:275px;margin: 0px auto 0px;">
			          <div class="play" style="margin:49px 0px 0px 118px"></div>
					  <div id="popDiv" class="video-b" style="width:317px;height:178px"> </div>
		              <div class="video-c" style="width:317px;height:178px;text-align: center;">
		                    <img src="${ctx }/statics/testfile/testvideoimage.png" style="height:178px;"/>
		              </div>
		             <div class="video-d" style="width:297px">
		                                               解剖学讲解
		             </div>
		            <div  class="video-e" style="width:297px">
		                 <span class="video-f" >2017-12-22</span>
		                 <span class="video-g" style="float:right;line-height:30px;margin-left:5px">98</span>
		                <span class="video-h"></span>
		              </div>
		          </div>
		        </div>
		        
		        
		        <div style="width:1045px;margin:0px auto 0px">
		     		<div style="width:347px;float:left;margin-top: 40px">
			          <div class="video-a" style="width:317px;height:275px;margin: 0px auto 0px;">
			          <div class="play" style="margin:49px 0px 0px 118px"></div>
					  <div id="popDiv" class="video-b" style="width:317px;height:178px"> </div>
		              <div class="video-c" style="width:317px;height:178px;text-align: center;">
		                    <img src="${ctx }/statics/testfile/testvideoimage.png" style="height:178px;"/>
		              </div>
		             <div class="video-d" style="width:297px">
		                                               解剖学讲解
		             </div>
		            <div  class="video-e" style="width:297px">
		                 <span class="video-f" >2017-12-22</span>
		                 <span class="video-g" style="float:right;line-height:30px;margin-left:5px">98</span>
		                <span class="video-h"></span>
		              </div>
		          </div>
		        </div>
		        
		        
		        <div style="width:1045px;margin:0px auto 0px">
		     		<div style="width:347px;float:left;margin-top: 40px">
			          <div class="video-a" style="width:317px;height:275px;margin: 0px auto 0px;">
			          <div class="play" style="margin:49px 0px 0px 118px"></div>
					  <div id="popDiv" class="video-b" style="width:317px;height:178px"> </div>
		              <div class="video-c" style="width:317px;height:178px;text-align: center;">
		                    <img src="${ctx }/statics/testfile/testvideoimage.png" style="height:178px;"/>
		              </div>
		             <div class="video-d" style="width:297px;">
		                                               解剖学讲解
		             </div>
		            <div  class="video-e" style="width:297px">
		                 <span class="video-f" >2017-12-22</span>
		                 <span class="video-g" style="float:right;line-height:30px;margin-left:5px">98</span>
		                <span class="video-h"></span>
		              </div>
		          </div>
		        </div>
		        
		        
		        <div style="width:1045px;margin:0px auto 0px">
		     		<div style="width:347px;float:left;margin-top: 40px">
			          <div class="video-a" style="width:317px;height:275px;margin: 0px auto 0px;">
			          <div class="play" style="margin:49px 0px 0px 118px"></div>
					  <div id="popDiv" class="video-b" style="width:317px;height:178px"> </div>
		              <div class="video-c" style="width:317px;height:178px;text-align: center;">
		                    <img src="${ctx }/statics/testfile/testvideoimage.png" style="height:178px;"/>
		              </div>
		             <div class="video-d" style="width:297px">
		                                               解剖学讲解
		             </div>
		            <div  class="video-e" style="width:297px">
		                 <span class="video-f" >2017-12-22</span>
		                 <span class="video-g" style="float:right;line-height:30px;margin-left:5px">98</span>
		                <span class="video-h"></span>
		              </div>
		          </div>
		        </div>
		        
		        
		        <div style="width:1045px;margin:0px auto 0px">
		     		<div style="width:347px;float:left;margin-top: 40px">
			          <div class="video-a" style="width:317px;height:275px;margin: 0px auto 0px;">
			          <div class="play" style="margin:49px 0px 0px 118px"></div>
					  <div id="popDiv" class="video-b" style="width:317px;height:178px"> </div>
		              <div class="video-c" style="width:317px;height:178px;text-align: center;">
		                    <img src="${ctx }/statics/testfile/testvideoimage.png" style="height:178px;"/>
		              </div>
		             <div class="video-d" style="width:297px">
		                                               解剖学讲解
		             </div>
		            <div  class="video-e" style="width:297px">
		                 <span class="video-f" >2017-12-22</span>
		                 <span class="video-g" style="float:right;line-height:30px;margin-left:5px">98</span>
		                <span class="video-h"></span>
		              </div>
		          </div>
		        </div>
		      
		        
        </div>
        <div style="clear:both"></div>
           </div>
    <div style="height:70px;padding-top:30px;background-color: #ffffff;">
    <div style="width:1115px">
    <div style="text-align: right;">
	        <ul class="pagination" id="page1">
	        </ul>
	        <div style="display: inline-block;vertical-align: top;text-align:left;">
	            <select id="edu" name="edu">
	                <option value="5"  >每页5条</option>
	                <option value="10" >每页10条</option>
	                <option value="15" >每页15条</option>
	                <option value="20">每页20条</option>
	            </select>
	        </div>
	        <div class="pageJump">
	            <span>共10页，共10条数据，跳转到</span>
	            <input type="text"/>
	            <span class="pp">页</span>
	            <button type="button" class="button">确定</button>
	        </div>
	    </div>
	    </div>
   </div>
   </div>
   
   </div>          
            <jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
</body>
<script type="text/javascript">
$(function(){
	Page({
        num:10,					
        startnum: 1,
        elem:$("#page1"),
        callback: function (n) {
        
        }
 });
$('select').selectlist({
    zIndex: 10,
    width: 110,
    height: 30,
    optionHeight: 30,
    onChange: function () {
    	
    }  //自定义模拟选择列表项chang
});
	
});

</script>
</html>