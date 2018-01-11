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
	<script type="text/javascript">
           var contextpath = '${pageContext.request.contextPath}/';
  </script>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title></title>
    <script src="<%=path %>/resources/comm/jquery/jquery.js"></script>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/mygroup/communication.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/mygroup/chat.css" type="text/css">
    <script src="<%=path %>/resources/comm/jquery/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/base.js"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/layer/layer.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.fileupload.js" type="text/javascript"></script>
    <script src="<%=path %>/resources/commuser/mygroup/group.js" type="text/javascript"></script> 
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>

<div class="content-body">
    <div class="content">
        <div class="left">
            <div class="top">
                <div class="div_img70"><img src="" class="img1" alt="小组图像"/></div>
                <div class="top_content">
                    <span class="span1">${groupMap.textbook_name}</span>
                    <input id="groupId" type="hidden" value="${groupMap.id}"/>
                    <input id="type" name="type" type="hidden" value="${type}"/>
                    <div class="top_content2">
                        <div class="top_content22">
                            <img src="${ctx}/statics/image/zjyh.png">
                        </div>
                        <text>${role}</text>
                       <span style="cursor: pointer;" onclick="javascript:quitGroup('${groupMap.id}')">退出小组</span>
                    </div>
                    <div class="top_content3">
                        <div class="top_content33">
                            <img src="${ctx}/statics/image/scz.png"/>
                        </div>
                        <span id="scwj1" class="scmsg">上传文件</span>
                    </div>
                </div>
                <div class="top_tj">
                    <div class="top_tj1 ">
                        <text class="top_tj1_text1">${memberCount}</text>
                        <br/>
                        <text class="top_tj1_text2">总人数</text>
                    </div>
                    <label></label>
                    <div class="top_tj2">
                        <text class="top_tj1_text1">${fileCount}</text>
                        <br/>
                        <text class="top_tj1_text2">文件分享</text>
                    </div>
                </div>
            </div>

            <div class="left_communions_files">
                <span id="commnions_top" class="clickbefore" onclick="ChangeDiv('commnions',${groupMap.id})">互动交流</span>
                <span id="filesgx_top" class="clicked" onclick="ChangeDiv('filesgx',${groupMap.id})">文件共享</span>
            </div>
            <div class="left-content">
                <div class="_show" id="filesgx">
                    <input type="text" placeholder="请输入文件名" id= "fileName" class="file_input"/>
                    <img class="search" src="${ctx}/statics/image/sx1.png"/>
                     <c:forEach var="list" items="${fileList}">
	                     <div class="items">
	                        <div class="item1" style="clear:both;">
	                            <span>
	                            <c:if test="${list.xtype == '.xls' || list.xtype == '.xlsx'}">
	                            <img src="${ctx}/statics/image/excl.png" alt="文件类型" class="item_img1"/>
	                            </c:if>
	                            <c:if test="${list.xtype == '.txt'}">
	                            <img src="${ctx}/statics/image/txt.png" alt="文件类型" class="item_img1"/>
	                            </c:if>
	                            <c:if test="${list.xtype == '.png' || list.xtype == '.jpg'}">
	                            <img src="${ctx}/statics/image/img.png" alt="文件类型" class="item_img1"/>
	                            </c:if>
	                            <c:if test="${list.xtype == '.doc' || list.xtype == '.docx'}">
	                            <img src="${ctx}/statics/image/word.png" alt="文件类型" class="item_img1"/>
	                            </c:if>
	                            <c:if test="${list.xtype == 'pdf'}">
	                            <img src="${ctx}/statics/image/pdf.png" alt="文件类型" class="item_img1"/>
	                            </c:if>
	                            <text>${list.file_name}</text></span>
	                            <span style="margin-left: 30px;">
	                            	<img src="${ctx}/statics/image/xztp.png" onclick="downLoadProxy('${list.file_id}')" class="item_img2"/>
	                            <text style="color: #70bcc3;">${list.download}</text></span>
	                        </div>
	                        <div class="item2">
	                            <div class="item2_div1">${list.realname}于${list.gmt_create}上传文件</div>
	                            <div style=" float: right;height: 50px;line-height: 50px;"><span class="del_span"></span>
	                                <text style="width: 20px;height: 20px;">删除</text>
	                            </div>
	                        </div>
	                    </div>
                     </c:forEach>
                    <div class="jzgd">
                    	<span onclick="javascript:doMore('${pageSize}')">加载更多...</span>
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
              	 <c:forEach var="gropuMemeber" items="${memberList}">
               		<li style="margin-right: 20px;">
	                    <div class="init_center w65_h50"><img src="<%=path %>${gropuMemeber.avatar}" class="groupc_li"/></div>
	                    <div class="init_center w65_h20_line20">
	                        <!-- <span class="li_span2"></span> -->
	                        <text>${gropuMemeber.display_name}</text>
	                    </div>
					</li>
              	 </c:forEach>
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

           <c:if test="${otherGroup !=null }">
				<ul>
					<c:forEach var="item" items="${otherGroup}" varStatus="st">
						<c:if test="${item.id != thisGroup.id }">
							<li style="margin-right: 20px;">
								<div class="init_center w85_h50">
									<img src="<%=path %>/${item.groupImage}" />
								</div>
								<div class="init_center w85_h36_line18">
									<text class="color03">${item.groupName}</text>
									<br />
									<text class="color99">(${item.members}人)</text>
								</div>
	
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</c:if> 
			<div class="no-more">
                <img src="<c:url value='/statics/image/aaa4.png'></c:url>">
                <span>木有内容呀~~</span>
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
