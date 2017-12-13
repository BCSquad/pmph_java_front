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
                    <div class="top_content2">
                        <div class="top_content22">
                            <img src="${ctx}/statics/image/zjyh.png">
                        </div>
                        <text>${role}</text>
                        <a href="${thisGroup.id}">退出小组</a>
                    </div>
                    <div class="top_content3">
                        <div class="top_content33">
                            <img src="${ctx}/statics/image/scz.png"/>
                        </div>
                        <div class="file_upload">
                            <text>上传文件</text>
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
                        <text class="top_tj1_text1">22</text>
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
                        <div class="chat_items other">
                            <div class="chat_item1">
                                <div class="div_item1_img">
                                    <img src="${ctx}/statics/testfile/tx.png"/>
                                    <text>曾若男</text>
                                </div>
                                <div class="arrows"></div>
                            </div>

                            <div class="chat_item2">
                                <div class="sender">
                                    值此首届丝绸之路沿线民间组织合作网络论坛开幕之际，我谨代表中国政府和中国人民，并以我个人的名义，向论坛召开表示热烈的祝贺！向出席论坛的各国民间组织代表和各界人士表示诚挚的欢迎！
                                </div>
                                <div class="chat_item2_time">2017.04.01 10:32</div>
                            </div>
                            <div class="clear"></div>
                        </div>
						<div>这里是系统消息</div>
                        <div class="chat_items mine">
                            <div class="chat_item1">
                                <div class="div_item1_img">
                                    <img src="${ctx}/statics/testfile/tx.png"/>
                                    <text>曾若男</text>
                                </div>
                                <div class="arrows"></div>
                            </div>

                            <div class="chat_item2">
                                <div class="sender">
                                    促进共同发展提供了新平台、注入新动力。
                                </div>
                                <div class="chat_item2_time">2017.04.01 10:38</div>
                            </div>
                            <div class="clear"></div>
                        </div>


                        <div class="chat_items mine">
                            <div class="chat_item1">
                                <div class="div_item1_img">
                                    <img src="${ctx}/statics/testfile/tx.png"/>
                                    <text>曾若男</text>
                                </div>
                                <div class="arrows"></div>
                            </div>

                            <div class="chat_item2">
                                <div class="sender">
                                    好
                                </div>
                                <div class="chat_item2_time">2017.04.01 10:38</div>
                            </div>
                            <div class="clear"></div>
                        </div>

                        <div class="chat_items mine">
                            <div class="chat_item1">
                                <div class="div_item1_img">
                                    <img src="${ctx}/statics/testfile/tx.png"/>
                                    <text>曾若男</text>
                                </div>
                                <div class="arrows"></div>
                            </div>

                            <div class="chat_item2">
                                <div class="sender">
                                    值此首届丝绸之路沿线民间组织合作网络论坛开幕之际，我谨代表中国政府和中国人民，并以我个人的名义，向论坛召开表示热烈的祝贺！向出席论坛的各国民间组织代表和各界人士表示诚挚的欢迎！
                                </div>
                                <div class="chat_item2_time">2017.04.01 10:32</div>
                            </div>
                            <div class="clear"></div>
                        </div>

                        <div class="chat_items other">
                            <div class="chat_item1">
                                <div class="div_item1_img">
                                    <img src="${ctx}/statics/testfile/tx.png"/>
                                    <text>曾若男</text>
                                </div>
                                <div class="arrows"></div>
                            </div>

                            <div class="chat_item2">
                                <div class="sender">
                                    值此首届丝绸之路沿线民间组织合作网络论坛开幕之际，我谨代表中国政府和中国人民，并以我个人的名义，向论坛召开表示热烈的祝贺！向出席论坛的各国民间组织代表和各界人士表示诚挚的欢迎！
                                </div>
                                <div class="chat_item2_time">2017.04.01 10:32</div>
                            </div>
                            <div class="clear"></div>
                        </div>


                        <div class="chat_items other">
                            <div class="chat_item1">
                                <div class="div_item1_img">
                                    <img src="${ctx}/statics/testfile/tx.png"/>
                                    <text>曾若男</text>
                                </div>
                                <div class="arrows"></div>
                            </div>

                            <div class="chat_item2">
                                <div class="sender">
                                    值此首届丝绸之路沿线民间组织合作网络论坛开幕之际，我谨代表中国政府和中国人民，并以我个人的名义，向论坛召开表示热烈的祝贺！向出席论坛的各国民间组织代表和各界人士表示诚挚的欢迎！
                                </div>
                                <div class="chat_item2_time">2017.04.01 10:32</div>
                            </div>
                            <div class="clear"></div>
                        </div>

                    </div>
                    <div class="iframe2">
                        <textarea  type="text" placeholder="请输入消息内容,按回车键发送" ></textarea>
                        <div class="div_btn"><span class="button">发送</span></div>
                    </div>
                </div>
                <div class="hidden" id="filesgx">
                    <input type="text" placeholder="请输入文件名" class="file_input"/><img class="search"
                                                                                     src="${ctx}/statics/image/sx1.png"/>
                    <div class="items">
                        <div class="item1" style="clear:both;">
                            <span><img src="${ctx}/statics/image/word-(1).png" alt="文件类型" class="item_img1"/><text>全国高等学校五年制本科临床医学专业第九轮规划教材主编人会会议纪要.xls</text></span>
                            <span><img src="${ctx}/statics/image/xztp.png" class="item_img2"/><text style="color: #70bcc3;">5</text></span>
                        </div>
                        <div class="item2">
                            <div class="item2_div1">诺男于2017.8.28 13.16上传文件</div>
                            <div style=" float: right;height: 50px;line-height: 50px;"><span class="del_span"></span>
                                <text style="width: 20px;height: 20px;">删除</text>
                            </div>

                        </div>
                    </div>

                    <div class="items">
                        <div class="item1" style="clear:both;">
                            <span><img src="${ctx}/statics/image/word-(1).png" alt="文件类型" class="item_img1"/><text>全国高等学校五年制本科临床医学专业第九轮规划教材主编人会会议纪要.xls</text></span>
                            <span><img src="${ctx}/statics/image/xztp.png" class="item_img2"/><text style="color: #70bcc3;">5</text></span>
                        </div>
                        <div class="item2" style="clear:both;">
                            <div class="item2_div1">诺男于2017.8.28 13.16上传文件</div>
                            <div style=" color: #b0b0b0;float: right;"><span class="del_span"></span>
                                <text>删除</text>
                            </div>
                        </div>
                    </div>

                    <div class="items">
                        <div class="item1" style="clear:both;">
                            <span><img src="${ctx}/statics/image/word-(1).png" alt="文件类型" class="item_img1"/><text>全国高等学校五年制本科临床医学专业第九轮规划教材主编人会会议纪要.xls</text></span>
                            <span><img src="${ctx}/statics/image/xztp.png" class="item_img2"/><text style="color: #70bcc3;">5</text></span>
                        </div>
                        <div class="item2" style="clear:both;">
                            <div class="item2_div1">诺男于2017.8.28 13.16上传文件</div>
                            <div style=" color: #b0b0b0;float: right;"><span class="del_span"></span>
                                <text>删除</text>
                            </div>

                        </div>
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
