<%--
  Created by IntelliJ IDEA.
  User: SuiXinYang
  Date: 2017/11/21
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <title>文章首页</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/articlepage/articlepage.css?t=${_timestamp}" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/articlepage/articlepage.js?t=${_timestamp}"></script>
</head>
<body>
    <jsp:include page="/pages/comm/head.jsp">
    	<jsp:param value="articlepage" name="pageTitle" />
    </jsp:include>
    <div class="body">
        <!-- <div class="apply-book"><div class="text">教材<br>申报</div></div> -->
        <div class="content-wrapper">
            <div class="notes" style="margin-top: 50px">
                <div class="title">
                    <div class="line"></div>
                    <div class="name">医学随笔  <a  style="cursor: pointer;"  onclick="window.location.href='${ctx}/cms/list.action'">全部&gt;&gt;</a></div>
                </div>
                <c:forEach items="${listArt}" var="list" varStatus="status">
	                <div class="${status.index==0 or status.index==4 ?'item' :'item behind'}" onclick="window.location.href='${ctx}/articledetail/toPage.action?wid=${list.id}'">
                    <div class="command">
                        <span style="margin-left: 5px">推荐</span>
                    </div>
                    <div  class="content" >
                        <div class="content-image">
                            <img src="${ctx}/${list.cover}" />
                        </div>
                        <p   class="content-title" style="cursor: pointer;">${list.title}</p>
                        <p  class="content-text">${list.summary}</p>
                        <div  class="foot">
                            <div style="float:left">
                                <%-- <c:if test="${list.avatar == '' || list.avatar == 'DEFAULT' || list.avatar == null}">  
                                	<img src="${ctx}/statics/image/default_image.png" class="personicon">
                                </c:if>
                				<c:if test="${!(list.avatar == '' || list.avatar == 'DEFAULT' || list.avatar == null)}">
                					<img src="${ctx}/image/${list.avatar}.action" class="personicon" />
                				</c:if> --%>
                				
                            </div>
                            <div  class="msg">
                                <span  class="name" style="cursor:pointer;" onclick="window.location.href='${ctx}/personalhomepage/tohomepage.action?userId=${list.userId }'">文章来源：${list.author_name==null?list.realname:list.author_name}</span>
                                <span  class="name" style="float:right;">${list.auth_date}</span>
                            </div>
                        </div>
                    </div>
                    </div>
                </c:forEach>
            </div>
            <div class="writer">
                <div class="title">推荐作者</div>
                <div style="margin-top: 11px">

                <c:forEach items="${listAut}" var="list" varStatus="status">
                    <div class="item">
                        <div class="content">
                            <div class="content-img" >
                                <c:choose>
	                            	<c:when test="${list.avatar == '' || list.avatar == 'DEFAULT' || list.avatar == null}">
	                            		<img src="${ctx}/statics/image/default_image.png" class="a6_head">
	                            	</c:when>
	                            	<c:otherwise>
	                            		<img src="${ctx}/image/${list.avatar}.action" class="a6_head">
	                            	</c:otherwise>
	                            </c:choose>
                            </div>
                            <div class="msg">
                                <div class="name">${list.realname}</div>
                                <div class="text">${list.title}</div>
                            </div>
                            <div class="add">
                                <div>
                                	<c:choose>
					                	<c:when test="${list.status == null }">
					                		<span class="friend add_btn" title="申请加为好友！" onclick="addFriendfun(${list.id},'${list.realname}',0)" id="friend${list.id}"><B>+</B>好友</span>
					                	</c:when>
					                	<c:when test="${list.status  == 2 }">
					                		<span class="friend isFriend" title="已是您的好友！" id="friend${list.id}"><B>好友</B></span>
					                	</c:when>
					                	<c:when test="${list.status == 0 && list.isBeenRequest==1}">
					                		<span class="friend isBeenRequest" title="对方也想加您为好友，点击马上成为好友！" onclick="addFriendfun(${list.id},'${list.realname}',2)" id="friend${list.id}"><B>+</B>好友</span>
					                	</c:when>
					                	<c:when test="${list.status == 0 && list.hasRequest==1}">
					                		<span class="friend hasRequest" title="已申请加为好友，请等待对方同意。" id="friend${list.id}"><B>+</B>好友</span>
					                	</c:when>
					                </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </div>
            </div>

            <div style="clear: both; height: 10px;"></div>
        </div>
    </div>
    <jsp:include page="/pages/comm/tail.jsp">
        <jsp:param name="linked" value="linked"></jsp:param>
    </jsp:include>
    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?c8927680e561788c668c5e891dfe322c";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
</body>
</html>
