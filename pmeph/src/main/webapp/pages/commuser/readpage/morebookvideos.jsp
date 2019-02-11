<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = path+"/";
String contextpath=request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
     <script>
		var contextpath = "${pageContext.request.contextPath}/";
	</script>
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>图书微视频</title>
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
   
    <link rel="stylesheet" href="<%=path%>/statics/commuser/community/wanderfaulbookcomments.css?t=${_timestamp}" type="text/css">
    <script src="<%=path%>/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/base.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/ckplayer/ckplayer.js?t=${_timestamp}"></script>
    <%-- <script src="<%=path%>/resources/commuser/community/communitylist.js?t=${_timestamp}"></script> --%>
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
   .video-d{width:210px;
	   border-bottom:1px solid #EEEEEE;
	   height:44px;
	   margin:0px auto 0px;
	   line-height: 44px;
	   font-size: 16px;
	   color:#333333;
	   overflow: hidden;
	   white-space: nowrap;
	   text-overflow: ellipsis;
   }
   .video-e{height:30px;width:210px;margin:0px auto 0px;color:#999999;font-size: 14px}
   .video-f{float:left;line-height: 30px}
   .video-g{float:right;line-height:30px;margin-left:5px}
   .video-h{float:right;width:20px;height:30px;
            background-image: url(${ctx}/statics/image/css_sprites.png);
            background-position:-418px -144px;}
</style>
<body>
            <jsp:include page="/pages/comm/head.jsp"></jsp:include>
            <input type="hidden" id="pagenum" value="${pagenum }"/>
            <input type="hidden" id="pagesize" value="${pagesize}"/>
            <input type="hidden" id="total" value="${total }"/>
             <input type="hidden" id="pagetotal" value="${pagetotal }"/>
            <input type="hidden" id="bbid" value="${bbid }"/>
			<div style="background-color: #f6f6f6;padding-top:28px;padding-bottom:130px">
			<div class="content-wrapper">
    		<div class="area2">
        		<div class="namehead" style="width:54px">
        		<span class="name" >微视频</span>
        		</div>
   			</div>
     		<div style="margin-top:25px;background-color: #ffffff;padding-top:10px">

     		<%-- <div style="width:1045px;margin:0px auto 0px">
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
		        </div> --%>
		      <c:forEach items="${videolist }" var="video">
		           <div style="width:1045px;margin:0px auto 0px">
		     		<div style="width:347px;float:left;margin-top: 40px">
			          <div class="video-a" style="width:317px;height:275px;margin: 0px auto 0px;">
		              <div class="video-c" style="width:317px;height:178px;text-align: center;">
		                   <%--  <img src="${ctx }/statics/testfile/testvideoimage.png" style="height:178px;"/> --%>
		                    <div class="videou" id="videou${video.id }" src="http://${_remoteVideoUrl}/v/play/${video.file_name }" style="width:317px;height:178px" controls type="mp4"
	                          poster="image/${video.cover }.action"></div>
		              </div>
		             <div class="video-d" style="width:297px">
		                  ${video.title }
		             </div>
		            <div  class="video-e" style="width:297px">
		                 <span class="video-f" >${video.gmt_create }</span>
		                 <span class="video-g" style="float:right;line-height:30px;margin-left:5px" id="c${video.id }">${video.clicks }</span>
		                <span class="video-h"></span>
		              </div>
		          </div>
		          <div style="clear:both"></div>
		        </div>

		      </c:forEach>



		       <%--  <div style="width:1045px;margin:0px auto 0px">
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
		        </div> --%>


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
	                <option value="6" ${pagesize=='6'?'selected':'' }>每页6条</option>
	                <option value="12" ${pagesize=='12'?'selected':'' } >每页12条</option>
	                <option value="18" ${pagesize=='18'?'selected':'' } >每页18条</option>
	                <option value="24" ${pagesize=='24'?'selected':'' }>每页24条</option>
	            </select>
	        </div>
	        <div class="pageJump">
	            <span>共${pagetotal }页，共${total }条数据，跳转到</span>
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
	$(".videou").each(function () {
        var $this = $(this);
        var videoObject = {
            container: "#" + $this.attr("id"),
            variable: 'player',
            autoplay: false,
            /*flashplayer: true,*/
            video:$this.attr("src"),
            poster: $this.attr("poster")

        };

        var player = new ckplayer(videoObject);
        player.addListener("play",function () {
            var vid=$this.attr("id").substring(6);
            $.ajax({
                type:"post",
                url:contextpath+'community/videoCount.action',
                data:{vid:vid},
                success:function(data){
                    $("#c"+vid).text(data.clicks);
                },
                error:function(){
                    alert('服务器错误');
                }
            });
        });

    });
	var pagetotal=parseInt($("#pagetotal").val());
	var pagenum=parseInt($("#pagenum").val());
	Page({
        num:pagetotal,					
        startnum: pagenum,
        elem:$("#page1"),
        callback: function (n) {
        	var pagesize=$("input[name=edu]").val();
            // var bbid=$("#bbid").val();
            window.location.href=contextpath+'readdetail/morebookvideo.action?pagenum='+n+'&pagesize='+pagesize+'&id='+${bbid };
        }
 });
	
$('select').selectlist({
    zIndex: 100,
    width: 110,
    height: 30,
    optionHeight: 30,
    onChange: function () {
    	var pagesize=$("input[name=edu]").val();
        // var bbid=$("#bbid").val();
    	window.location.href=contextpath+'readdetail/morebookvideo.action?pagenum=1&pagesize='+pagesize+'&id='+${bbid };
    }  //自定义模拟选择列表项chang
});
	
});
</script>
</html>