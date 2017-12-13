<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/26
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath();%>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title></title>
    <script src="<%=path %>/resources/comm/jquery/jquery.js"></script>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/mygroup/communication.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/mygroup/chat.css" type="text/css">
    <script src="<%=path %>/resources/comm/jquery/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/base.js"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/layer/layer.js"></script>
    <script src="<%=path %>/resources/commuser/mygroup/group.js" type="text/javascript"></script>
</head>
<body>
	<script type="text/javascript">
		var contxtpath = '${pageContext.request.contextPath}';
	</script>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>

<div class="content-body">
    <div class="content">
        <div class="left">
            <div class="top">
                <div class="div_img70"><img src="${pageContext.request.contextPath}/${thisGroup.groupImage}" class="img1" alt="小组图像"/></div>
                <div class="top_content">
                    <span class="span1">${thisGroup.groupName}</span>
                    <input id="groupId" type="hidden" value="${thisGroup.id}"/>
                    <input id="userId" type="hidden" value="${userId}"/>
                    <div class="top_content2">
                        <div class="top_content22">
                            <img src="${ctx}/statics/image/zjyh.png">
                        </div>
                        <text>${role}</text>
                        <span id='quitGroup'>退出小组</span>
                    </div>
                    <div class="top_content3">
                        <div class="top_content33">
                            <img src="${ctx}/statics/image/scz.png"/>
                        </div>
                        <div class="file_upload">
                        	<form id="uploadForm" action="${ctx}/group/fileup.action" method="post" enctype="multipart/form-data" >
                            	<input id="file_upload" type="file" name='file' />
                            	<input  type="hidden" name='groupId' value="${thisGroup.id}" />
                            </form>
                        </div>
                    </div>
                </div>
                <div class="top_tj">
                    <div class="top_tj1 ">
                        <text class="top_tj1_text1">${gropuMemebersNum}</text>
                        <br/>
                        <text class="top_tj1_text2">总人数</text>
                    </div>
                    <label></label>
                    <div class="top_tj2">
                        <text class="top_tj1_text1">${fileTotal}</text>
                        <br/>
                        <text class="top_tj1_text2">文件分享</text>
                    </div>
                </div>
            </div>

            <div class="left_communions_files">
                <span id="commnions_top" class="clicked" onclick="ChangeDiv('commnions')">互动交流</span>
                <span id="filesgx_top" class="clickbefore" onclick="ChangeDiv('filesgx')">文件共享</span>
            </div>
            <div class="left-content">
                <div class="_show" id="commnions">
                    <div class="iframe1">
						<a id ="history">历史消息。。。</a>
                     </div>
                    <div class="iframe2">
                        <textarea  id="msgContent" type="text" placeholder="请输入消息内容,按回车键发送" ></textarea>
                        <div class="div_btn"><span id="sendMsg" class="button">发送</span></div>
                    </div>
                </div>
                <div class="hidden" id="filesgx">
                    <input type="text" placeholder="请输入文件名" id= "fileName" class="file_input"/><img class="search" src="${ctx}/statics/image/sx1.png"/>
                    <span id ="fileContent"></span>
                    <div id ="fileMore">
                    	<center>更多</center>
                    </div>
				</div>

            </div>
        </div>
    </div>
    <div class="right">
        <div class="groupcomposition">
            <div class="ul_top">
                <div class="float_left"><span class="li_span1"></span>
                    <text class="font_size16">&ensp;小组成员</text>
                </div>
                <div class="float_right font_size14">邀请好友>></div>
            </div>
            <ul>
                <c:if test="${gropuMemebers !=null }">
                	<c:forEach var="gropuMemeber" items="${gropuMemebers}" varStatus="st">
                	   <c:if test="${st.index+1 <= 12 }"><!-- 展示12位 -->
	                		<li>
			                    <div class="init_center w65_h50"><img src="${pageContext.request.contextPath}/${gropuMemeber.avatar}" class="groupc_li"/></div>
			                    <div class="init_center w65_h20_line20">
			                        <span class="li_span2"></span>
			                        <text>${gropuMemeber.displayName}</text>
			                    </div>
							</li>
						</c:if>
                	</c:forEach>
                </c:if>
            </ul>
            <div class="show_all"><a href="#">>查看所有成员(${gropuMemebersNum}) </a></div>
        </div>
        <div class="alwaysgroup">
            <div class="ul_top">
                <div class="float_left"><span id="li_span3"></span>
                    <text class="font_size16 color33">&ensp;我常去的其他小组</text>
                </div>
                <div class="float_right">
                    <img src="${ctx}/statics/image/sx.png" alt="刷新" class="sx">
                    <text class="font_size12">换一批</text>
                </div>
            </div>

            <ul>
            	<c:if test="${otherGroup !=null }">
                	<c:forEach var="item" items="${otherGroup}" varStatus="st">
                	   <c:if test="${item.id != thisGroup.id }">
	                		 <li>
			                    <div class="init_center w85_h50"><img src="${pageContext.request.contextPath}/${item.groupImage}"/></div>
			                    <div class="init_center w85_h36_line18">
			                        <text class="color03">${item.groupName}</text>
			                        <br/>
			                        <text class="color99">(${item.members}人)</text>
			                    </div>
			
			                </li>
						</c:if>
                	</c:forEach>
                </c:if>
            </ul>
        </div>
    </div>

</div>
</div>
<div style="background-color: white;width: 100%;padding: 0;margin: 0;height: 220px;border: none;overflow: hidden;">
		<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</div>
</body>
</html>
