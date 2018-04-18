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
    <title>我的小组</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/mygroup/communication.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/mygroup/chat.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path %>/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="${ctx}/resources/comm/layui/css/layui.css"  media="all">
    <script src="<%=path %>/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="<%=path %>/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>

    <script type="text/javascript">
		var contxtpath  = '${pageContext.request.contextPath}';
		var contextpath = '${pageContext.request.contextPath}/';
	</script>
    <script type="text/javascript" src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/layer/layer.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.fileupload.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="<%=path %>/resources/commuser/mygroup/group.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/layui/layui.js?t=${_timestamp}" charset="utf-8"></script>
    
</head>
<body>
<%--<script>--%>
    <%--layui.use('element', function(){--%>
        <%--var $ = layui.jquery--%>
            <%--,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块--%>

        <%--//触发事件--%>
        <%--var active = {--%>
                <%--setPercent: function(process){--%>
                    <%--//设置50%进度--%>
                    <%--element.progress('demo', process);--%>
                <%--}--%>
        <%--}--%>
<%--</script>--%>
	<%----%>
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
                    <input id="userName" type="hidden" value="${userName}"/>
                    <div class="top_content2">
                        <div class="top_content22">
                            <img src="${ctx}/statics/image/zjyh.png">
                        </div>
                        <text>${role}</text>
                        <span id='quitGroup' style="cursor:pointer" >退出小组</span>
                    </div>
                    <div style="clear:both;">
                        <div class="top_content3">
                            <div class="top_content33">
                                <img src="${ctx}/statics/image/scz.png"/>
                            </div>
                            <span id="scwj1" class="scmsg">上传文件</span>

                        </div>
                        <%--<div  id="process_div" style="display:none;float:left;margin-left:10px;width: 100px;height: 10px;border-radius: 4px;border: 2px solid #f2f2f2;"><span id="uploadFileTips" style="color: #999;height: 100%;border-radius: 4px;background-color: #0bc700;display:block;"></span></div>--%>

                    </div>

                    <%--<div class="flex-center" id="process" style="display: inline;margin-left: 30px;">--%>
                            <%--<h3>文件上传中，请稍等...</h3>--%>
                            <%--&lt;%&ndash;<span class="container">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<span id="progressBar" class="h-100P bar"></span>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                   <%--</div>--%>
                    
                </div>
                <div class="top_tj">
                    <div class="top_tj1 ">
                        <text class="top_tj1_text1">${gropuMemebersNum}</text>
                        <br/>
                        <text class="top_tj1_text2">总人数</text>
                    </div>
                    <label></label>
                    <div class="top_tj2">
                    <input type="hidden" value="${fileTotal}" id="file_count" /> <!-- 解决ie8获取不到值的问题 -->
                        <text class="top_tj1_text1" id="fileTotal"  >${fileTotal}</text>
                        <br/>
                        <text class="top_tj1_text2">文件分享</text>
                    </div>
                </div>
            </div>

            <div class="left_communions_files">
                <span id="commnions_top" class="clicked" onclick="ChangeDiv('commnions')">互动交流</span>
                <span id="filesgx_top" class="clickbefore" style="position: relative" onclick="ChangeDiv('filesgx')">文件共享</span>
            	<!-- <span id="memberManager_top" class="clickbefore" style="position: relative" onclick="ChangeDiv('memberManager')">成员管理</span> -->
            </div>
            <div class="left-content">
                <div class="_show" id="commnions">
                    <div class="iframe1" id="wechat">
						<a id ="history" style="cursor:pointer;color:#999;font-size:14px;" >历史消息...</a>
                     </div>
                    <div class="iframe2">
                        <textarea  id="msgContent" type="text" placeholder="请输入消息内容,按回车键发送" ></textarea>
                        <div class="div_btn"><span id="sendMsg" class="button">发送</span></div>
                    </div>
                </div>
                <div class="hidden" id="filesgx">
                	排序:
                	<div style="display: inline-block;vertical-align: top;text-align:center;margin-top:20px">
               <select id="edu" name="edu">
                   	    <option value="gmt_create:asc" >上传时间升序</option>
                		<option value="gmt_create:desc" selected="selected">上传时间降序</option>
                		<option value="file_size:asc" >文件大小升序</option>
                		<option value="file_size:desc">文件大小降序</option>
                		
                		<option value="file_name:asc" >文件名称升序</option>
                		<option value="file_name:desc">文件名称降序</option>
               </select>
           </div>
                	
                	
                	
                	<!-- 
                	<select id="order">
                		
                		<option value="gmt_create:asc"  >上传时间升序</option>
                		<option value="gmt_create:desc" selected="selected">上传时间降序</option>
                		<option value="file_size:asc" >文件大小升序</option>
                		<option value="file_size:desc">文件大小降序</option>
                		
                		<option value="file_name:asc" >文件名称升序</option>
                		<option value="file_name:desc">文件名称降序</option>
                	</select> -->
                    <div class='search-wrapper'>
                    	<input type="text" placeholder="请输入文件名" id= "fileName" class="file_input"/>
                    	<img class="search" src="${ctx}/statics/image/sx1.png"/>
                    </div>	
                    <span id ="fileContent"></span>
                    <div id ="fileMore">
                    	<center style="cursor:pointer" >更多...</center>
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
                <div class="float_right font_size14" > <a href="${ctx}/myFriend/listMyFriend.action?pageType=invite&groupId=${thisGroup.id}">邀请好友>></a></div>
            </div>
            <ul>
                <c:if test="${gropuMemebers !=null }">
                	<c:forEach var="gropuMemeber" items="${gropuMemebers}" varStatus="st">

                		<c:if test="${st.index <= 11 }"><!-- 0-11  展示12位 -->
	                		<li   onmouseenter="getImageTips('${gropuMemeber.userId}',this)" onmouseleave="removeImageTips()">
                                <a target="_blank" href="${ctx}/personalhomepage/tohomepage.action?userId=${gropuMemeber.userId}">
			                    <div class="init_center w65_h50"><img src="${pageContext.request.contextPath}/${gropuMemeber.avatar}" class="groupc_li"/></div>
			                    <div class="init_center w65_h20_line20">
			                        <text>${gropuMemeber.displayName}</text>
			                    </div>
                                </a>
							</li>
						 </c:if>
						 <c:if test="${st.index > 11 }"><!-- 0-11  展示12位 -->

	                		<li  class="show_Allgroupmember"  onmouseenter="getImageTips('${gropuMemeber.userId}',this)" onmouseleave="removeImageTips()">
                                <a target="_blank" href="${ctx}/personalhomepage/tohomepage.action?userId=${gropuMemeber.userId}">
			                    <div class="init_center w65_h50"><img src="${pageContext.request.contextPath}/${gropuMemeber.avatar}" class="groupc_li"/></div>
			                    <div class="init_center w65_h20_line20">
			                        <text>${gropuMemeber.displayName}</text>
			                    </div>
                                </a>
							</li>

						 </c:if>
						 <input id="${gropuMemeber.userId}_${gropuMemeber.userType}" type="hidden" value="${gropuMemeber.displayName}"/>
					</c:forEach>
                </c:if>
            </ul>
            <div class="show_all"><a href="#" id="show_All_Memeber" onclick="showAllGroupMember()">>查看所有成员(${gropuMemebersNum}) </a></div>
        </div>
        <div class="alwaysgroup">
            <div class="ul_top">
                <div class="float_left"><span id="li_span3"></span>
                    <text class="font_size16 color33">&ensp;我常去的其他小组</text>
                </div>
                <div  id ="anotherBatcHButton" class="float_right" style="cursor:pointer">
                    <img src="${ctx}/statics/image/sx.png"   alt="刷新" class="sx">
                    <text  class="font_size12">换一批</text>
                </div>
            </div>

            <ul id ="anotherBatcH" >
            	<c:if test="${otherGroup !=null }">
                	<c:forEach var="item" items="${otherGroup}" varStatus="st">
                	   <c:if test="${st.index <= 11 }"><!-- 0-11  展示12位 -->
                	         <a href="${pageContext.request.contextPath}/group/toMyGroup.action?groupId=${item.id}" style="display:block;" >
		                		 <li style="margin-bottom: 25px;">
				                    <div class="init_center w85_h50"><img src="${pageContext.request.contextPath}/${item.groupImage}"/></div>
				                    <div class="init_center w85_h36_line18">
				                        <text class="color03">${item.groupName}</text>
				                        <br/>
				                        <text class="color99">(${item.members}人)</text>
				                    </div>
				
				                </li>
			                </a>
						</c:if>
					</c:forEach>
                </c:if>
            </ul>
        </div>
    </div>
</div>
</div>
</div>
<div style="background-color: white;width: 100%;padding: 0;margin: 0;height: 220px;border: none;overflow: hidden;">
		<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</div>
</body>
</html>
<script type="text/javascript">
</script>
