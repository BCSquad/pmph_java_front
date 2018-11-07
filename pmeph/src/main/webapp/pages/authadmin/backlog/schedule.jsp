<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<script>
        var contextpath='${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>待办事项</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/authadmin/backlog/schedule.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    
</head>
<style type="text/css">

        #right .select-wrapper {
            border: none;
        }
         #right .select-button {
            background: #f6f6f6;
        }
    </style>
<body>
<jsp:include page="/pages/comm/headGreenBackGround.jsp">
    <jsp:param name="pageTitle" value="backlog"></jsp:param>
</jsp:include>
<div class="body" style="background-color:#f6f6f6;float: left">
	<input id="license" value="${map.progress}" type="hidden"/>
    <div class="content-wrapper">
        <div class="big">
                <div class="left">待办事项</div>
                <div class="mid"><a class="midButton" href="${ctx}/schedule/eventRecord.action">办事记录</a></div>
                <div class="right" id="right">
                   <div style="float: right;">
                      		 筛选：
                       <select id="filtrate-select" name="filtrate-select" title="请选择">
                           <option value="all" ${map.time=='all' ?'selected':''}>全部</option>
                           <option value="week" ${map.time=='week' ?'selected':''}>一周内</option>
                           <option value="month" ${map.time=='month' ?'selected':''}>一月内</option>
                           <option value="year" ${map.time=='year' ?'selected':''}>一年内</option>
                       </select>
                   </div>
                </div>
            <div class="bigList">
            <c:if test="${map.pageResult.total>=1 }">
	            	
	            <c:forEach items="${map.pageResult.rows}" var="one" varStatus="status">
	            	<div class="list">
	                    <div class="leftContent">
	                        <div class="leftContentSmall">
	                            <div class="pictureDiv">
	                            	<c:if test="${one.TYPE=='A' || one.TYPE == 'D' }">
	                            		<img  class="pictureB" src="${ctx}/statics/image/pic3555.png">
	                            	</c:if>
	                                <c:if test="${one.TYPE=='B'}">
	                               		<img  class="picture">	
	                                </c:if>
	                            </div>
	                        </div>
	                        <c:if test="${status.last==false}" >
		                        <div class="straightLine">
		                            <div class="whiteDiv">
		                            </div>
		                        </div>
	                        </c:if> 
	                    </div>
	                    <div class="rightContent">
	                            <div class="leftPictureAndName">
	                                <div class="upContent">
	                                        <div class="headPicture">
	                                            <img class="picture1"  src="${ctx}/${one.avatar}">
	                                            <%-- <img class="picture1"  src="${ctx}${one.avatar}"> --%>
	                                        </div>
	                                        <div class="username">
	                                             <span>${one.NAME}</span>
                                                <c:if test="${one.online_progress==4}">
                                                <span style="color: #b0120e">被出版社退回</span>
                                                </c:if>
	                                        </div>
	                                </div>
	                                <div class="downContent">
	                                	<c:choose>
	                                		<c:when test="${one.TYPE=='C'}">
	                                			<div class="timeEvent C">
			                                        <span class="time">于<fmt:formatDate pattern="yyyy年MM月dd日" value="${one.TIME}" /></span>&nbsp;<span class="event">${one.CONTENT}</span>
			                                        <input type="hidden" class="msg_id" value="${one.CONTENT}">
			                                    </div>
	                                		</c:when>
	                                		<c:otherwise>
	                                			<div class="timeEvent">
			                                        <span class="time">于<fmt:formatDate pattern="yyyy年MM月dd日" value="${one.TIME}" /></span>&nbsp;<span class="event">提交${one.CONTENT}</span><span class="event">，请审批</span>
			                                    </div>
	                                		</c:otherwise>
	                                	</c:choose>
	                                </div>
	                            </div>
	                            
	                            <div class="rightButton">
	                            <c:choose>
                               		<c:when test="${one.TYPE=='C'}">
                               			<div onclick="system('${one.ID}')"  class="buttonDiv">
	                                        		查看
	                                	</div>
                                        <div onclick="fillMaterialSurvey('${one.materialId}')"  class="buttonDiv">
                                            调查问卷
                                        </div>
                               		</c:when>
                               		<c:otherwise>
                               			<div onclick="checkAuthen('block','${one.TYPE}','${one.auditId}','${one.ID}')" class="buttonDiv">
	                                        		办理
	                                	</div>
                               		</c:otherwise>
	                            </c:choose>
	                                
	                                
	                                

	                            </div>
	                    </div>
	            </div>
	            </c:forEach>
              </c:if>
               <c:if test="${map.pageResult.total<1 }">
	            	<div class="no-more">
                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
                    <span>木有内容呀~~</span>
                	</div>
	           </c:if>
            </div>
            <div class="tip" id="tip">
                <div class="upDiv">
                    <div class="tipPicture" >
                        <img src="${ctx}/statics/pictures/sad.png">
                    </div>
                    <div class="tipWords">
                        <span>您目前还不是机构管理员，快去认证吧</span>
                    </div>
                </div>
                <div class="downDiv">
                    <div class="leftButton" onclick="toogleTip('none')">知道了</div>
                    <div class="checkButton" onclick="toAuthAudit(${map.userId})">马上认证</div>
                </div>
                <div class="close" id="close" onclick="toogleTip('none')">
                    <span>×</span>
                </div>
            </div>
            <div class="gray ie" onclick="toogleTip('none')"></div>
            	<c:if test="${map.pageResult.total>5 }">
            	<div class="pageDiv">
                <div style="float: right;">
                    <ul class="pagination" id="page1">
                    </ul>
                    <div style="display: inline-block;    vertical-align: top">
                        <select id="edu" name="edu" >
                            <option value="5" ${map.pageResult.pageSize=='5' ?'selected':''}>每页5条</option>
                            <option value="10" ${map.pageResult.pageSize=='10' ?'selected':''}>每页10条</option>
                            <option value="15" ${map.pageResult.pageSize=='15' ?'selected':''}>每页15条</option>
                        </select>
                    </div>
                    <div class="pageJump">
                        <span>共${map.pageResult.pageTotal}页，跳转到</span>
                        <input type="text" id="toPage"/>
                        <span class="pp">页</span>
                        <button type="button" class="button">确定</button>
                    </div>
                </div>
            </div>
            </c:if>
        </div>
		<c:choose>
			<c:when test="${map.license=='no'}">
			</c:when>
			<c:otherwise>
				<div class="info">
            <div class="topPictureDiv">
                <img class="topPicture"src="${ctx}/${map.avatar}">
            </div>
            <div class="firstRow">
                <span>${map.org_name }</span>
            </div>
            <div class="secondRow">
                <div class="littleV">
                <img >
                </div>
                <div>
                    <span class="littleTitle">${map.org_name},欢迎您!</span>
                    <!-- <span class="littleTitle">阿打算大所大所大所大所大</span> -->
                </div>
                <c:if test="${(map.progress==0||map.progress==2)}">
                	<div>
	                	<span class="littleTitle2" onclick="toAuthAudit(${map.userId})">未认证</span>
	                </div>
                </c:if>
                <c:if test="${map.progress==1}">
                	<div>
	                	<span class="littleTitle3">已认证</span>
	                </div>
                </c:if>
               
            </div>
            <div class="thirdRow">
                <span>最近登录:<fmt:formatDate value="${map.latest_login_time}" type="both" dateStyle="default" timeStyle="default"></fmt:formatDate></span>
            </div>
        </div>
			</c:otherwise>
		</c:choose>
        </div>
   </div>
   
   <!-- 系统消息标题悬浮框 -->
     <div class="bookmistake" id="bookmistake">
         <form id="bookmistakeform">
             <%--  <div class="apache">
                   <div class="mistitle">消息详情</div>
                  &lt;%&ndash; <div class="x" onclick="hideup()"></div>&ndash;%&gt;
               </div>--%>
             <div class="msg-info">
                 <label style="margin-left: 20px" class="labell">标题:<span id="titlec"></span></label>

             </div>
             <div class="msg-info">
                 <label style="margin-left: 20px" class="labell">发送人:<span id="sendc"></span></label>
             </div>
             <div class="msg-info">
                 <label style="margin-left: 20px" class="labell">发送时间:<span id="timec"></span></label>
             </div>

             <div class="msg-info">
                 <label style="margin-left: 20px" class="labell">内容:</label>
                 <div class="misarea" id="tcontent" ></div>
             </div>
             <div class="msg-info">
                 <label style="margin-left: 20px" class="labell">附件:<span id="tattachment"  class="listContent"></span></label>
             </div>
             <div class="clear"></div>
         </form>
     </div>
   
   
<div style="clear: both; background-color: white;">   
<jsp:include page="/pages/comm/tail.jsp"/>
</div>
<script>
    Page({
        num: parseInt("${map.pageResult.pageTotal}"),					//页码数
        startnum: parseInt("${map.pageResult.pageNumber}"),				//指定页码
        elem: $('#page1'),		//指定的元素
        callback: function (n) {	//回调函数
            console.log(n);
            window.location.href="${ctx}/schedule/scheduleList.action?currentPage="+n+"&pageSize="+$("input[name='edu']").val(); 
        }
    });
    $(function () {

        console.log("${map.pageResult.rows}");
        $('#filtrate-select').selectlist({
            width: 100,
            height: 20,
            optionHeight: 20,
            onChange:function (){
            	window.location.href="${ctx}/schedule/scheduleList.action?time="+$("input[name='filtrate-select']").val();
            }
        });
        $('#edu').selectlist({
            width: 110,
            height: 30,
            optionHeight: 30,
            onChange:function (){
            	window.location.href="${ctx}/schedule/scheduleList.action?pageSize="+$("input[name='edu']").val();
            }
            
        });
    })
    function toogleTip(val,type,auditId,decId) {
    	var license = $("#license").val();
    	if(license==0||license==2){
    		 $('.tip').css('display',val);
    	     $('.gray').css('display',val);
    	} else{ 
    		if(type=="B"){
    			//跳转教师资格认证页面
    			window.location.href="${ctx}/teacherauth/toPage.action?";
    		}else if(type=="A"){
    			//教材申报审核
    			//dataaudit/toMaterialAudit.action?material_id=10&declaration_id=123&view_audit=audit
    			window.location.href="${ctx}/dataaudit/toMaterialAudit.action?material_id="+auditId+"&declaration_id="+decId+"&view_audit=audit";
    		}else if(type=="D"){
    			//临床申报审核
    			window.location.href="${ctx}/expertation/showExpertation.action?material_id="+auditId+"&declaration_id="+decId+"&state=audit&userType=org";   			
    		}
    	}	
    		
    }
    function toAuthAudit(userId){
    	window.location.href="${ctx}/admininfocontroller/toadminattest.action?userId="+userId;
    }


    function checkAuthen(val,type,auditId,decId){
        $.ajax({
            type: "POST",
            url:contextpath+'dataaudit/checkAuthen.action',
            dataType:"json",
            success: function(json) {
                if(json=="OK"){
                    toogleTip(val,type,auditId,decId);
                }
            }
        });
    }

    function fillMaterialSurvey(str){
        window.location.href="${ctx}/orgSurvey/fillSurveyById.action?materialId="+str;
    }
    
  //点击显示系统消息
    function system(str) {
    	var loaded = false;
    	var imgTimeOut = false;
    	var clickToEndLoad = false;
    	
    	//10秒不论是否加载完成，都跳出弹窗；
    	setTimeout(function(){
    		imgTimeOut = true;
    	}, 10000);
    	
    	//加载过程中点击window 阻止弹窗弹出（然而点不到window，只有加载图案的一小块有点击效果，问题待解决）
    	$(window).one("click",function(){
    		$(window).one("click",function(){
    			clickToEndLoad = true;
    		});
    	});
    	
        if (str != '' && str != '') {
            $.ajax({
                type: "post",
                url: contextpath + "message/queryTitleMessage.action?uid=" + str,
                async: false,
                dataType: 'json',
                success: function (json) {
                    $("#titlec").html(json.title);
                    $("#sendc").html(json.realname);
                    $("#timec").html(formatDate(json.gmt_create));
                    $("#tcontent").html(json.tContent);
                    /*$("#bookmistake").show();*/
                    var ste = '';
                    if(json.attaList){
                    	$.each(json.attaList, function (i, x) {
                            ste += '<a  href="' + contextpath + '' + x.attachment + '">' + x.attachment_name + '</a><br/>';
                        });
                    }
                    
                    if(ste==''){
                        ste +="<span>无</span>"
                    }
                    $("#tattachment").html(ste);
                    var imgTick;
                    /**
                     * 轮询$content下的图片是否加载完成，直到完成后回调
                     * @param loaded 是否加载完成
                     * @param id 所需判断的块的id
                     * @param callback  加载完成的回调函数 
                     */
                    function isImgLoad(loaded,id,callback) {
                    	//清除延时
                    	clearTimeout(imgTick);
                    	if ($("#"+id).find("img").length>0) { //有图片需要加载
                    		//是否所有图片加载完成
                        	$("#"+id).find("img").each(function(){
                        		var $t = $(this);
                        		if(imgTimeOut||$t[0].complete){
                        			loaded = true;
                        		}else{
                        			loaded = false;
                        			return false; 
                        		}
                        	});
        				}else{ //无图片需要加载
        					loaded = true;
        				}
                    	
                    	if (clickToEndLoad) {
                    		layer.closeAll("loading"); 
        				}else{
        					if (loaded) { // 若已全部加载 执行回调
        	            		callback();
        					}else{  //若还未全部加载完成 间隔每10毫秒再次轮询 直到加载完成执行回调 不再轮询0
        						imgTick = setTimeout(function(){
        							 layer.load(1); //加载图样载
        							 isImgLoad(loaded,id,callback); //轮询
        						}, 10);
        					}
        				}
                    	
                    }
                    
                    //开始轮询
                    isImgLoad(loaded,"bookmistake", function() {
                                layer.closeAll("loading"); //关闭所有layer.load(2);加载图样载
                                layer.open({
                                    type: 1,
                                    title: false,
                                    closeBtn: 1,
                                    area: '967px',
                                    skin: 'layui-layer-nobg', //没有背景色
                                    shadeClose: true,
                                    content: $("#bookmistake")
                                });
                            });
                    
                    var obj= document.getElementById('readyes'+str);
                    var readno=document.getElementById('readno'+str);
                    if(!obj&&json.isread=="yes"){
                    	if(readno){
                    		$("#readno"+str).remove();
                    	}
                      	$("#txt"+json.id).html('<img src="'+contextpath+'statics/image/readyes.png"  id="readyes'+str+'"/>'+$("#txt"+json.id).html());
                    }
                }
            });
        }
    }

    //点击关闭消息详情窗口
    function hideup() {
        $("#bookmistake").hide();
    }
  //时间格式化
    function formatDate(value) {
        if (value) {
            Number.prototype.padLeft = function (base, chr) {
                var len = (String(base || 10).length - String(this).length) + 1;// 获取value值的长度，如果长度大于0，就创建一个同等长度的数组
                return len > 0 ? new Array(len).join(chr || '0') + this : this;
            }
            var d = new Date(value), // 创建一个当前日期对象d
                dformat = [d.getFullYear(),// 把年格式化填充
                        (d.getMonth() + 1).padLeft(),// 把月格式化填充
                        d.getDate().padLeft()].join('-') + // 把日格式化填充
                    ' ' + [d.getHours().padLeft(),// 把小时格式化填充
                        d.getMinutes().padLeft(),// 把分钟格式化填充
                        d.getSeconds().padLeft()].join(':');// 把秒格式化填充
            return dformat;// 最后返回格式化好的日期和时间
        }
      }
</script>
</body>
</html>