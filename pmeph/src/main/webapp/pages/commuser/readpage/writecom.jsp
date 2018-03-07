<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';


    </script>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>写书评</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/readpage/writecom.css" type="text/css">
    <script type="text/javascript" src="${ctx}/resources/comm/ueditor1.4.3.3/ueditor.config.js"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/ueditor1.4.3.3/ueditor.all.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/ueditor1.4.3.3/lang/zh-cn/zh-cn.js"></script>
	<script src="${ctx}/resources/comm/jquery/jquery.js"></script>
	<script src="${ctx}/resources/comm/jquery/jquery.form.js"></script>
	<script src="${ctx}/resources/comm/base.js"></script>
	<script src="${ctx}/resources/commuser/readpage/writecom.js"></script>
</head>
<body class="body">
	<jsp:include page="/pages/comm/head.jsp"></jsp:include>
	<div class="content-wrapper">
	    <div class="leftarea">
	            <input type="hidden" value="${map.type}" id="type_id">
	            <input type="hidden" id="book_id" value="${id}">
	            <input type="hidden" id="marks" value="${map.bookmarks}">
				<div class="writearticle"><span>文章 > 写书评</span></div>
				<div class="title" style="margin-top: 40px;float: left;">
			       <div class="line"></div>
			       <div class="rd_name">写书评</div>
			    </div>
			    <div class="topbook">
			       <div class="bao">
			         <img src="${map.image_url }" style="display: block;margin-bottom: 25px;height: 104px;margin: auto"/>
			       </div>
			       <div class="detail">
			         <ul>
		                <li class="bookname" style="margin-top: -8px">${map.bookname}</li>
			  			<li class="author">出版社： <font class="publisher">${map.publisher}</font></li>
			  			<li class="author">出版日期：${map.publish_date}</li>
			  			<li class="author">ISBN：${map.isbn}</li>
		  		   </ul>	
			       </div>
			    </div>
			    <div class="aaaaaa">
				    <span class="starfont">给个好评吧</span>
				    <div class="scorestar" id="star">
                             <div class="scorestar1" id="score1"></div>
                             <div class="scorestar1" id="score2"></div>
                             <div class="scorestar1" id="score3"></div>
                             <div class="scorestar1" id="score4"></div>
                             <div class="scorestar1" id="score5"></div>
	                    </div>
	                    <input type="hidden" id="last_score">
                </div>
			    <div class="">
			        <div class="longcomtitle">长评标题：</div>
			        <input type="text" class="sxy-txt2" id="TitleValue" name="titleValue" placeholder="输入长评标题请限100字以内.."  oninput="if(value.length>100){value=value.slice(0,100)}" />
			    </div>
			    <div class="sxy-div-content4">
			        <div style="height:10px;"></div>
			        <font class="sxy-font1">长评内容：</font>
			    </div>
				<form id="form1" >
			    <div class="sxy-div-content">
			        <div style="height:auto !important;min-height:600px;height:600px;width: 880px">
			           <script type="text/plian" id="mText" style="width: 880px; height:478px;scorll-y:true;position:absolute;margin-left:0px;"></script>
			           <input type="hidden" id="UEContent" name="UEContent"  value="" />
			        </div>
			    </div>
			    <div class="sxy-div-content3">
			        <img style="width: 880px;" alt="" src="${ctx}/statics/image/_cupline.jpg"/>
			    </div>
			    <div id="sxy-return">
			    	<input type="hidden" id="btn_type" name="btnType"  />
			        <input class="sxy-btn" style="display: none;" type="button" value="发表" onclick="insertlong()" />
			    </div>
			    </form>
			    <div id="content" hidden="true"></div>
			</div>
	    <!-- 右边区域 -->
        <div class="rightarea">
           <div class="right_1">
        		<div>
        			<span id="ptts"></span>
        			<span style="font-family: MicrosoftYaHei;font-size: 16px;color: #000000;margin-left: 5px;"><B>配套图书</B></span>
        		</div>
        		<div style="margin-top: 20px;">
        			<div  style="float: left;width: 90px;height: 116px">
        			    <img src="<%-- ${supMap.image_url} --%>${ctx}/statics/image/ts_06.png" class="righttopbook"/>
        			</div>
        			<div style="float: left;width: 170px;margin-left: 10px;">
        				<div class="ptts_sp1">${supMap.book_name}麻醉解剖学学习指导与习题集（第3版）</div><div class="watch" onclick="todetail('<%-- ${supMap.id} --%>168')"><div class="lookbook">去看看</div></div>
        				<div class="ptts_sp2">${supMap.realname}张励才</div>
        				<div class="ptts_sp3">${supMap.detail}全国高等学校麻醉学专业第四轮规划教材配套教材，根据麻醉...</div>
        			</div>
        		</div>
        	</div>
        	<div class="right_3">
                <div class="right_4">
                    <div class="right_5">
                        <div class="right_6"></div>
                        <div class="right_7">
                            <span id="span_3">相关推荐</span>
                        </div>
                    </div>
                    <div class="right_8">
                        <img src="../statics/image/refresh.png" style="float:left;margin-left:80px">
                        <div class="refresh" onclick='fresh("9")'>换一批</div>
                    </div>
                </div>
                <div id="change">
	                <c:forEach items="${auList}" var="list">
		                <div class="right_9" onclick="todetail('${list.id}')">
		                    <div class="right_10">
		                        <img src="${list.image_url}" class="right_12">
		                    </div>
		                    <div class="right_11">${list.bookname}</div>
		                </div>
	                </c:forEach>
	                <c:forEach items="${tMaps}" var="list">
		                <div class="right_9" onclick="todetail('${list.id}')">
		                    <div class="right_10">
		                        <img src="${list.image_url}" class="right_12">
		                    </div>
		                    <div class="right_11">${list.bookname}</div>
		                </div>
	                </c:forEach>
	             </div> 
            </div>
            <div class="right_13"></div>
            <div class="right_14">
                <div class="right_15">
                    <div class="right_16">
                        <div class="right_17"></div>
                        <div class="right_18">人卫推荐</div>
                    </div>
                </div>
                <div class="right_19">
                    <div class="picture1"></div>
                    <div class="refresh1" onclick="change()">换一批</div>
                </div>
                <div  id="comment">
	                <c:forEach items="${eMap}" var="list">
		                <div class="right_20">
		                    <div class="right_21" onclick="todetail('${list.id}')">${list.bookname}</div>
		                    <div class="right_22">（${list.author}）</div>
		                </div>
	                </c:forEach>
	            </div>
            </div>
        </div>	
    </div>
    <div style="clear: both;"></div>
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
<script type="text/javascript">
$(document).ready(function(){
	 //为所有的class为scorestar1绑定mouseout和mouseover事件。bind({事件名：function(){},事件名：function(){}})的方法绑定多个事件
	 $(".scorestar1").bind({
	  mouseover: function () {
		  $(this).css("background-position", "-183px -174px").prevAll().css("background-position", "-183px -174px");
		  $(this).nextAll().css({"background-position": "-183px -153px"});
		  var score=parseInt($(this).attr("id").substring(5))*2+'.0';
		  $("#last_score").val(score);
	  }
	  });
});
</script> 
</html>
</script>
