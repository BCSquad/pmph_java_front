<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/21
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath();%>

<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>我的好友</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/myfriend/myFriend.css" type="text/css">
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/base.js"></script>
    <script type="text/javascript" src="${ctx}/resources/commuser/myfriend/inviteMyFriend.js"></script>
    <style>
    	#rightContent .select-button {
            background: #f6f6f6;
        }

        #rightContent .select-wrapper {
            border: none;
        }
        button{
    outline: 0;
}
.messageList {
    background-color:#F8F8F8;
    color:#999999;
    margin-top: 30px;
    margin-right: auto;
    margin-left: auto;
    padding-top: 15px;
    padding-bottom: 15px;
    padding-left: 20px;
    padding-right: 50px;
    width: 1130px;
    border-bottom:1px solid #E6E6E6;
}
.message{
    background-color:#F8F8F8;
    margin-top: 15px;
    margin-right: auto;
    margin-left: auto;
    padding: 25px 20px;
    width: 1160px;
}

.headPortrait{
    align:left;
    width:90px;
    height:80px;
}
.type{
    align:left;
    width:auto;
    color:#999999;
    height:30px;
    font-family: MicrosoftYaHei;
    font-size: 14px;
    padding-left: 12px;
}
.time{
    align:left;
    width:auto;
    color:#999999;
    font-size: 12px;
    height:80px;
    margin-left: 30px;

}
.time1{
    align:left;
    width:auto;
    color: #bbbbbb;
    height:30px;
    font-size: 12px;
    margin-left: 28px;
}
.apply{
    font-family: MicrosoftYaHei;
    font-size: 16px;
    font-weight: normal;
    font-stretch: normal;
    line-height: 26px;
    letter-spacing: 0px;
    color: #666666;
    align:left;
    width:auto;
}
.buttonSpan{
    width:auto;
    text-align: right;
    height:80px;
}
.table{
    width: 100%;
}

.detail{
    background-color:#6FBCC2;
    color:white;
    padding: 3px;
    padding-right:18px ;
    padding-left:18px ;
    margin-right: 20px;
    width:65px;
}
.line{
    width: 1160px;
    height: 0;
    margin-right: 20px;
    border-top:1px solid #ebebeb;
}

#selected{
    font-family: MicrosoftYaHei;
    font-size: 16px;
    font-weight: normal;
    font-stretch: normal;
    line-height: 0px;
    letter-spacing: 0px;
    color: #333333;
    border-bottom: 2px solid black;
    padding-bottom: 14px;
}
#otherSelected{
    color: #333333;
    width: 32px;
    height: 16px;
    font-family: MicrosoftYaHei;
    font-size: 16px;
    border-bottom: 2px solid black;
    padding-bottom: 14px;
    margin-left: 40px;
}
.unselected{
    width: 32px;
    height: 16px;
    font-family: MicrosoftYaHei;
    font-size: 16px;
    font-weight: normal;
    font-stretch: normal;
    line-height: 0px;
    letter-spacing: 0px;
    color: #999999;
    margin-left: 40px;
}
.otherOptions{
    width: 32px;
    height: 16px;
    font-family: MicrosoftYaHei;
    font-size: 16px;
    font-weight: normal;
    font-stretch: normal;
    line-height: 0px;
    letter-spacing: 0px;
    color: #999999;
}
#rightContent{
    float:right;
    font-size: 16px;
    font-weight: normal;
    font-stretch: normal;
    line-height: 18px;
    letter-spacing: 0px;
    color: #999999;
}
/*
accept{
    width: 70px;
    height: 24px;
    background-color: #70bcc3;
    border-radius: 2px;
}
#ignore{
    width: 70px;
    height: 24px;
    border-radius: 2px;
    border: solid 1px #70bcc3;
}*/
.buttonAccept {
    background-color: #70bcc3; /* Green */
    border: none;
    color: white;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 14px;
    border-radius: 2px;
    margin-right: 20px;
   padding:5px 17px;
}
.buttonIgnore {
    background-color: white;
    border: none;
    color: #70bcc3;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 14px;
    border-radius: 2px;
    margin-right: 20px;
    width: 68px;
    height: 22px;
    border: solid 1px  #70bcc3;
}
.accept-text{
    margin-right: 20px;
    color:#70bcc3;
}
.ignore-text{
    margin-right: 20px;
    color:#999;
}

.more{
    font-family: MicrosoftYaHei;
	font-size: 16px;
	line-height: 18px;
	color: #7d7d7d;
	text-align:center;
	
}

.picture{
    width: 50px;
    height:50px;
    border-radius:50%;
}

.pictureNotice{
    width: 70px;
    height:70px;
    border-radius:50%;
}

.buttonDetail{
    width:auto;
    text-align: right;
    height:20px;
    vertical-align:top;
}
.title{
    width: auto;
    height: 20px;
    vertical-align:top;
    padding-left: 12px;
}

a{
    text-decoration:none;
    color: white;
    font-family: MicrosoftYaHei;

}

.name{
    align:left;
    width:auto;
    color:#333333;
    height:30px;
    font-family: MicrosoftYaHei;
    font-size: 14px;
    padding-left: 12px;
}

.personMessageContent{
    font-family: MicrosoftYaHei;
    font-size: 14px;
    font-weight: normal;
    font-stretch: normal;
    line-height: 26px;
    letter-spacing: 0px;
    padding-left: 12px;
    color: #666666;
    align:left;
    width:auto;
}

.delete{
    font-size: 14px;
    border-radius: 4px;
    margin-right: 20px;
    width: 70px;
    height: 24px;
    color:#999999;
    cursor: pointer;
}

.last_div{
	margin-bottom: 60px;
}

.b{
    width: 690px;
    height: 495px;
    background-color: #ffffff;
    box-shadow: 0px 4px 10px 0px
    rgba(0, 0, 0, 0.2);
    position:absolute;
    top:100px;
    left:330px;
    z-index: 99;
}
.hiddenX{
    width: 50px;
    height: 50px;
    position:absolute;
    top:-21px;
    right:-50px;
    border-radius: 50%;
    -webkit-transition: all 0.3s ;
    -moz-transition: all 0.3s;
    transition: all 0.3s;
    cursor: pointer;
}
.show{
    display:block;
}
.hidden{

    display: none;
}
.personMessageTitle{
    font-family: MicrosoftYaHei;
    font-size: 16px;
    font-weight: normal;
    font-stretch: normal;
    line-height: 45px;
    letter-spacing: 1px;
    color: #333333;
    margin-left: 20px;
}

.div_btn11{

    padding:5px 20px;
    background-color: #70bcc3;
    border-radius: 2px;
    text-align: center;
    position: absolute;
    right:28px;
    bottom:20px;
}

.button11{
    color: #ffffff;
    font-size: 14px;
}
.contentBox{
    width: 690px;
    height: 321px;
    overflow-y:auto;
    overflow-x: hidden;
    padding: 0;
    margin: 0;
    border-left: 0;
    border-top: 0;
    border-right: 0;
    border-bottom: 1px solid #dedede;
    border-top:1px solid #dedede;
    clear: both;
}
.inputBox{
    width: 690px;
    height:120px;
    border:none;
    margin-bottom: 5px;
    position: relative;
}

.X{
    font-size: 16px;
    color: white;
    text-align: center;
}
.talkTime{
    clear: both;
    font-family: MicrosoftYaHei;
    font-size: 14px;
    font-weight: normal;
    font-stretch: normal;
    letter-spacing: 0px;
    color: #d5d5d5;
}
.talkTimeAlignLeft{
    text-align: left;
}
.talkTimeAlignRight{
    text-align: right;
}
.oneTalk{
    clear:both;
    margin:0 auto;
    padding-left: 10px;
    padding-top: 20px;
    width: 680px;
    height: auto;
}

.headAndNameLeft{
    margin:0 auto;
    text-align:center;
}

.headAndNameRight{
    margin:0 auto;
    text-align:center;
    margin-right: 10px;
}

.headDiv{
    min-width: 44px;
    height:50px;
}

.headPicture{
    width:44px;
    height:44px;
    border-radius: 50%;
    margin-right:7px;
}

.talkName{
    height:15px;
    line-height: 15px;
    min-width: 44px;
    font-size: 12px;
    font-weight: normal;
    font-stretch: normal;
    letter-spacing: 1px;
    color: #999999;
    margin-right:8px;
}

.talkDiv{
    padding-left: 20px;
}
.talkDivRight{
    padding-right: 20px;
}
.sendMessage{
    clear: both;
    max-width: 510px;
    height: auto;
    min-height:34px;
    min-width: 20px;
    font-size: 12px;
    position:  relative;
}
.triangle{
    height: 0px;
    width: 0px;
    border-width: 8px;
    border-style: solid;
    position: absolute;
    left: -15px;
    top: 10px;
}
.leftTriangle{
    border-color: white #f8f8f8 white white;
}
.rightTriangle{
    border-color: #f8f8f8 #f8f8f8 #f8f8f8 #dee6ed;
}

.textDiv{
    font-size: 13px;
    height: auto;
    padding: 10px;
    margin:0 auto;
    word-wrap:break-word;
    word-break:break-all;
    text-align: left;
    border-radius: 7px;
    color: #666666;
    background-color: #f8f8f8;
    margin-bottom: 10px;
    
}

.contentBox::-webkit-scrollbar {/*滚动条整体样式*/
    width: 6px;     /*高宽分别对应横竖滚动条的尺寸*/
    height: 1px;
}

scrollbar{
-moz-appearance: none !important;
background-color: transparent !important;/* 滚动条背景透明 */
background-image: none !important; /* 滚动条背景图案不显示 */
position: relative !important; /* 更改滚动条的定位方式为相对 */
overflow: hidden !important;
z-index: 999999999 !important; /* 把滚动条提到Z轴最上层 */
}

/* 滚动条按钮基本样式 */
scrollbar thumb{
-moz-appearance: none !important;
background-color: rgba(0,100,255,.25) !important;
border-radius: 0px !important;
border: 1px !important; /* 滚动条按钮边框 */
border-color: rgba(0,100,255,.1) !important;  /* 滚动条按钮边框颜色和透明度 */
}

/* 滚动条按钮:鼠标悬停与点击拖动时基本样式 */
/* scrollbar:hover thumb,
scrollbar thumb:hover,
scrollbar thumb:active {
background-color: rgba(0,100,255,.75) !important;
border: 0px !important;
} */

/* 垂直滚动条 */
/* 把滚动条位置移到屏幕外，这里的像素应该等于垂直滚动条宽度的负值 */
/* scrollbar[orient="vertical"]{ margin-left: -5px !important; 
min-width: 5px !important; max-width: 5px !important;
}
 */
/* 垂直滚动条按钮的左边框样式 */
scrollbar thumb[orient="vertical"]{
border-style: none none none solid !important; 
}

/* 水平滚动条 */
/* 把滚动条位置移到屏幕外，这里的像素应该等于垂直滚动条宽度的负值 */
/* scrollbar[orient="horizontal"]{ margin-top: -5px !important; 
min-height: 5px !important; max-height: 5px !important;
} */

/* 水平滚动条按钮的上边框样式 */
/* scrollbar thumb[orient="horizontal"]{
border-style: solid none none none !important; 
}
 */
/* 去除垂直与水平滚动条相交汇的角落 */
/* scrollbar scrollcorner{display: none ! important; } */

/* 滚动条两端按钮不显示 */
/* scrollbar scrollbarbutton { display: none ! important; }
 */
scrollbarbutton{ -moz-appearance: none !important;
position: relative !important;
overflow: hidden !important;
background-color: rgba(0,100,255,.25) !important;
border: none !important; 
}
scrollbar:hover scrollbarbutton, scrollbar scrollbarbutton:hover{
background-color: rgba(0,100,255,.75) !important;
}

scrollbar[orient="vertical"] scrollbarbutton {
max-height:10px !important; min-height:10px !important;
}

scrollbar[orient="horizontal"] scrollbarbutton {
max-width: 10px !important; min-width: 10px !important;
}

.contentBox::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    background: #999;
}
.contentBox::-webkit-scrollbar-track {/*滚动条里面轨道*/
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    border-radius: 10px;
    background: #EDEDED;
}



.filtrate-wrapper{
    display: block;
}
.load-more{
    width: 140px;
    height: 36px;
    line-height: 36px;
    margin: 10px auto 0;
    color: #8f8e8e;
    cursor: pointer;
    text-align: center;
}
.load-more:hover{
    color: #333333;
    text-decoration: underline;
}

    </style>
</head>

<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div class="content-body" id="more">
    <div class="div_top">
        <div class="float_left"><a href="${pageContext.request.contextPath}/group/toMyGroup.action?groupId=${groupId}" style="color:#1ab194;margin-right:10px;">返回</a>邀请我的好友</div>
        <div class="float_right" style="color: #1abd44;">
           <%--  <img src="${ctx}/statics/image/tjhy.png" class="img1"/>
            <label class="v_a" onclick = "window.location.href='${ctx}/addFriend/toPage.action'" style="cursor: pointer;">添加好友</label> --%>
            <input type="hidden" value="" id="frendId" />
        </div>
    </div>
<%--     <div class="b hidden" id="box">
            <div class="hiddenX hidden" id="close">
                <img onclick="hide()" style="width:100%;height:100%;" src="${ctx}/statics/image/closediv.png">
            </div>
            <span class="personMessageTitle">私信窗口</span>
            <div class="contentBox" id="dialogue">
            </div>
			<div class="inputBox">
                <div style="float: left;width: 80%;height: 100%">
                <textarea id="content" style="width: 100%;height: 98%;border: none;outline:0;font-size:15px;" placeholder="请输入消息内容,按回车键发送" ></textarea>
                </div>
                <div style="float: left;width: 20%;height: 100%">
                <div class="div_btn11" style="cursor: pointer;">
                    <span class="button11" id ="sendNewMsg">发送</span>
                </div>
                </div>
            </div>
    </div> --%>
    <!-- 隐藏域 -->
    <input type="hidden" value="${row}" id="row">
    <input type="hidden" value="${id}" id="id">
    <input type="hidden" value="${realname}" id="realname">
    <input type="hidden" value="${more}" id="moreeee">
    <input type="hidden" value="${groupId}" id="groupId">
    <div class="items">
    	<c:choose>
    		<c:when test="${more<1}">
    			<div class="no-more">
                   <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
                   <span>木有内容呀~~</span>
               	</div>
    		</c:when>
    		<c:otherwise>
    			<c:forEach var="friend" items="${listFriends}" varStatus="st" >
		    		<c:choose>
		    		<c:when test="${st.last==false}">
		  				<div class="${(st.index+1)%5 == 1? 'item1':'item1 item11'}" >
				            <div><img src="${ctx}/${friend.avatar}" class="img2"></div>
				            <div class="div_txt1">${friend.realname}</div>
				            <div class="div_txt2">${friend.position}</div>
				            <div class="div_txt3">
				                <div  class ="showTalk" id="${friend.id}" onclick="invite('${friend.id}','${friend.realname}')">邀请</div>
				                <input type="hidden" id="t_${friend.id}" value= "${friend.realname}" />
				            </div>
		            	</div>
		    		</c:when>
		    		<c:otherwise>
			  			<div class="${(st.index+1)%5 == 1? 'item1':'item1 item11'}" >
				            <div><img src="${ctx}/${friend.avatar}" class="img2"></div>
				           <%--  <div><img src="${ctx}/statics/pictures/head.png" class="img2"></div> --%>
				            <div class="div_txt1">${friend.realname}</div>
				            <div class="div_txt2">${friend.position}</div>
				            <div class="div_txt3">
				                <div  class ="showTalk" id="${friend.id}" onclick="invite('${friend.id}','${friend.realname}')">邀请</div>
				                <input type="hidden" id="t_${friend.id}" value= "${friend.realname}" />
				            </div>
			            </div>
		            </c:otherwise>
		  			</c:choose>
			    </c:forEach>
    		</c:otherwise>
    	</c:choose>
     </div>
</div>
<div style="clear:both;height: 30px" ></div>
<c:if test="${listSize>0}">
	<div class="last_div" id="span_more"><div class="more" ><span style="cursor: pointer;" onclick="morefriend()" id="mooorew">加载更多...</span></div></div>
</c:if>
<div style="background-color: #f4f4f4">
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</div>
<script type="text/javascript">
var webSocket = undefined;
try {
    if (WebSocket) {
    	webSocket = new WebSocket("ws://119.254.226.115:11000/pmpheep/websocket?userType=2&userId=" + $("#id").val());
    }
} catch (e) {

}
if (webSocket) {
	webSocket.onopen = function(event){
	    console.log("连接成功");
	    console.log(event);
	};
	webSocket.onerror = function(event){
	    console.log("连接失败");
	    console.log(event);
	};
	webSocket.onclose = function(event){
	    console.log("Socket连接断开");
	    console.log(event);
	};
	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function(){
    	webSocket.close();
    };
}

</script>
<script type="text/javascript">
   /*  $(function() {
        $(window).scroll(function() {
            var top = $(window).scrollTop()+100;
            var left= $(window).scrollLeft()+330;
            $("#box").css({ left:left + "px", top: top + "px" });
        });
    }); */
    
    if(($("#more").val)<15){
		$("#span_more").hide();
	}
    //邀请好友
    function invite(id,realname){
    	
    	var json = {id:id,groupId:$("#groupId").val()};
    	$.ajax({
    		  type:'post',
              url :contextpath+'myFriend/invite.action',
              async:false,
              dataType:'json',
              data:json,
              success:function(json){
            	  if(json=="0"){
            		  window.message.error("邀请失败");
            	  }else{
            		 
            		  $("#"+id).css({"background-color":"#f2f2f2"});
            		  $("#"+id).attr("onclick","");
            		  //推送消息
            	   if (webSocket) {
            			  try {
            				 
            				  webSocket.send("{senderId:"+$("#id").val()+",senderType:"+0+",content:'\""+$("#realname").val()+"\"邀请了\""+realname+"\"进群"+"',groupId:"+$("#groupId").val()+",sendType:0}");
                              
						} catch (e) {
							// TODO: handle exception
						}finally{
							setTimeout("webSocket.close();",1000);
							
						}
            	   }
						 window.message.success("邀请成功");  
            		  
                     
				    	
            	  }
              }
    		
    	})
    }
    //加载更多
    function morefriend(){
    	var json={
    			 id:$("#id").val(),
    			 row:$("#row").val(),
    			 groupId:$("#groupId").val()
    	};
    	$.ajax({
            type:'post',
            url :contextpath+'myFriend/more.action',
            async:false,
            dataType:'json',
            data:json,
            success:function(json){
            	var str='';
            	str+='<div class="items">';
            	$.each(json,function(i,n){
            		$("#row").val(n.row);
                 	$("#id").val(n.queryid);
                 	$("#moreeee").val(n.remainCount);
            		
               			if((i+1)%5==1){
               				str+='<div class="item1">'
               			}else{
               				str+='<div class="item1 item11">'
               			}; 
               	            <%-- <div><img src="${ctx}${friend.avatar}" class="img2"></div> --%>
               	       str+='<div><img src="${ctx}/${friend.avatar}" class="img2"></div><div class="div_txt1">'
               	            +n.realname
               	            +'</div><div class="div_txt2">'
               	            +n.position
               	            +'</div><div class="div_txt3"><div  class ="showTalk" id='
               	            +n.id
               	            +'>邀请</div><input type="hidden" onclick="invite('+n.id+','+n.realname+')" id="t_'
               	            +n.id 
               	            +'value=\"' 
               	            +n.realname 
               	            +'\"></div></div>';
            	});
            	str+='</div></div>';
            	$("#more").append(str);
            	if($("#moreeee").val()==0){
            		$("#mooorew").html('没有更多了~~');
            		$("#mooorew").attr("onclick","");
            	}
            }
    	});
    }
</script>
</body>
</html>
