<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
    <title>通知列表</title>
    <script>
        var contextpath='${pageContext.request.contextPath}/';
    </script>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <link rel="stylesheet" href="${ctx}/statics/commuser/message/message.css" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/comm/layer/layer.js"></script>
    <script src="${ctx}/resources/commuser/message/message.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
</head>
    <style type="text/css">
        #rightContent .select-button {
            background: #f6f6f6;
        }

        #rightContent .select-wrapper {
            border: none;
        }
    </style>
    <script type="text/javascript">
        $(function(){
        	
            $('#select').selectlist({
                zIndex: 10,
                width: 100,
                height: 20,
                optionHeight: 20,
                triangleColor:'#333333',
                onChange:function (){
					window.location.href="${ctx}/message/noticeMessageList.action?condition="+$("input[name='select']") .val();
				}
            });
        });
    
        
      //删除通知
       function deleteNotice(nid){
       	window.message.confirm(
       			'确认删除吗?',
       			{btn:['确定','取消']},
       			function(id){
       				$.ajax({
       					type:'post',
       					data:{"nid":nid},
       					url:"deleteNoticeMessage.action?",
       					async:false,
       					dataType:'json',
       					success:function(json){

       						window.message.success("删除成功");
       						layer.close(id);
       						$(".tr_"+nid).remove();
       						//window.location.href="${ctx}/message/noticeMessageList.action?condition="+$("input[name='select']") .val();
       					}
       					});
                    layer.close(id);
       			},
       			function(id){
                    layer.close(id);
       			}
       	);
        }
    </script>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
	
        <div class="messageList">
            <span id="selected"><b>通知</b></span>
            <span><a href="${ctx}/message/applyMessageList.action" class="unselected">申请</a></span>
            <span><a href="${ctx}/mymessage/listMyMessage.action"  class="unselected">私信</a></span>
            <span id="rightContent" >筛选：
                <select id="select" name="select" title="请选择" >
                    <option value="3" ${condition=='3' ?'selected':''}>全部</option>
                    <option value="4" ${condition=='4' ?'selected':''}>公告</option>
                    <option value="0" ${condition=='0' ?'selected':''}>系统消息</option>

                </select>
            </span>
        </div>

        <div style="height: 26px;background-color: #fff;line-height: 26px;padding:0;" class="message">
            <span class="jcsbsbzt" id="c_span1" onclick="getMessage('1','c_span1','c_span0')">已读</span>
            <span class="jcsbsbzt" id="c_span0" onclick="getMessage('0','c_span0','c_span1')">未读</span>
            <input type="hidden" value="" id="is_read_hidden"/>
        </div>
        <!-- 系统消息标题悬浮框 -->
        <div class="bookmistake" id="bookmistake">
            <form id="bookmistakeform">
              <%--  <div class="apache">
                    <div class="mistitle">消息详情</div>
                   &lt;%&ndash; <div class="x" onclick="hideup()"></div>&ndash;%&gt;
                </div>--%>
                <div class="info">
                    <label style="margin-left: 20px" class="labell">标题:<span id="titlec"></span></label>
                    
                </div>
                <div class="info">
                    <label style="margin-left: 20px" class="labell">发送人:<span id="sendc"></span></label>
                </div>
                <div class="info">
                    <label style="margin-left: 20px" class="labell">发送时间:<span id="timec"></span></label>
                </div>
                
                <div class="info">
                    	<label style="margin-left: 20px" class="labell">内容:</label>
                    <div class="misarea" id="tcontent" ></div>
                </div>
                <div class="info">
                    <label style="margin-left: 20px" class="labell">附件:<span id="tattachment"  class="listContent"></span></label>
                </div>
                <div class="clear"></div>
            </form>
        </div>

        <input type="hidden" id="messid" value="${message.id}"/>
        <div class="message" id="message_content">
            <table class="table" id="messageTable" >
            <c:choose>
            	<c:when test="${listSize>0}">
            		<c:forEach items="${list}" var="message">

		                <tr style="width: 70%" class="tr_${message.id }">
		                    <th rowspan="2" class="headPortrait"><img  class ="pictureNotice" src="${ctx}/${message.avatar}"></th>
		                    <td class="type1">
			                    <span>${message.title}<%--<c:if test="${message.msgType==4}">公告 </c:if>
			                          <c:if test="${message.msgType==0||message.msgType==1}">系统消息</c:if>--%>
			                    </span>
			                    <span class="time1" id="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${message.time}" /></span>
		                    </td>
		                </tr>
		                <tr style="width: 30%" class="tr_${message.id }">
		                	  <c:if test="${(message.msgType==0||message.msgType==1)}">
		                	    <td colspan="2" class="title" style="cursor: pointer;" onclick="showup('${message.id}')" >

                                    <c:choose>
                                        <c:when test="${message.is_read==1}">
                                            <img src="${ctx}/statics/image/readyes.png"  id="readyes${message.id}"  class="readyes"/>
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${ctx}/statics/image/readno.png"  id="readno${message.id}" class="readyes"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <span class="fixwidth">${message.messageContent}</span>

		                	    </td>
		                	  </c:if>
		                	  <%--<c:if test="${(message.msgType==0||message.msgType==4)&& message.material_id!=0}">
			                	    <td colspan="2" class="title"  >

	                                <span class="fixwidth">${message.messageContent}</span>
			                	    </td>
		                	  </c:if>--%>
		                	<%--  <c:if test="${message.msgType==4}">
		                	    <td colspan="2" class="title">《${message.title}》已开始申报,请您留意</td>
		                	  </c:if>--%>
		                  
		                    <td class="buttonDetail">
		                    	<c:if test="${message.msgType==0 && message.material_id!=0}">
		                      	<div class="buttonAccept" ><a href="${ctx}/message/noticeMessageDetail.action?cmsId=${message.cmsid}">查看详情</a></div>
		                        </c:if>
		                        <c:if test="${message.msgType==0||message.msgType==1}">
		   					    <span class="deleteButton" onclick="deleteNotice(${message.id })"><span style="font-size:18px;">×</span> 删除</span>
		                        </c:if>
		                    </td>
		                </tr>
		                <tr class="tr_${message.id }">
		                    <td colspan="4" align="center" ><hr class="line"></td>
		                </tr>

					</c:forEach>
            	</c:when>
            	<c:otherwise>
                    <div class="no-more">
	                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
	                    <span>木有内容呀~~</span>
               		</div>
            	</c:otherwise>
            </c:choose>
            </table>
           
            <c:if test="${count>0}">
            <div id="loadMoreDiv" class="load-more clearfix" onclick='loadMore()'>加载更多...</div>
            <input id="startPara" name="startPara" type="hidden">
            </c:if>
        </div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>