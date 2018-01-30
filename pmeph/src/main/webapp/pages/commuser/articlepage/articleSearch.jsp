<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
   <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>文章搜索</title>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/articlepage/articleSearch.css" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/commuser/articlepage/articleSearch.js"></script> 
</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div class="body" style="background-color: #f6f6f6;padding-bottom:60px">

<input type="hidden" id="allpage" value="${allpage}">
<input type="hidden" id="n" value="${pageNum}">

      <div class="nav" style="background-color: white">
         	<div style="width:1200px;height: 85px;margin: 0 auto;">
            <div class="searchDiv">
                <div class="searchInputLeft" onclick="queryall(1)">
                    <img src="../statics/image/sousuo-helpCenter.png">
                </div>
                <div class="searchInputRight">
                    <input class="input" id="selectall"  value="${title}">
                </div>
            </div>
            <div class="selectDiv">
                <div class="tab active">文章</div>
                <div class="tab " onclick="tobookpage()">书籍
                </div>
            </div>
            </div>
        </div>
    <div class="content-wrapper">
        <div class="bigContent">
            <div style="background-color: #f6f6f6;width: 100%;height: 15px;"></div>
            <div class="list" style="background-color: white">
               <c:forEach items="${artlist }" var="list">
                <div class="articleOneList">
                    <div class="articleLeftPicture">
                        <img class="articlePictureSize" src="${list.imgpath}">
                    </div>
                    <div class="articleRightContent">
                        <div class="articleUpDiv">
                            <div class="upLeft">
                                <div class="bookName">
                                	<a href="${ctx}/articledetail/toPage.action?wid=${list.id }" target="_blank" >
                                    	<div class="book-name-span" >${list.title}</div>
                                    </a>
                                </div>
                                <div class="nameDiv">
                                	<c:if test="${list.avatar=='DEFAULT'}">
                                	<img class="headPicture" src="${ctx}/statics/image/default_image.png">
                                    </c:if>
                					<c:if test="${list.avatar!='DEFAULT'}">
                					<img class="headPicture" src="${ctx}/image/${list.avatar}.action" >
                                    </c:if>
                                    <!-- <img class="headPicture" src="../statics/pictures/avatar.png"/> -->
                                    <span class="name book-name-span">${list.realname}</span><span class="time">${list.gmt_create }</span>
                                </div>
                            </div>
                            <div class="upRight">
	                                <div class="pictureDiv">
	                                <div class="number2">${list.comments}</div>
                                    <div class="comment"></div>
                                    <div class="number2" id="likenum${list.id}">${list.likes}</div>
                                    <input type="hidden" id="likes${list.id}" value="${list.cms_user_like==null?'handPicture':'nohandPicture'}">
                                    <input type="hidden" id="mainid${list.id}" value="${list.id}">
                                    <div class="${list.cms_user_like !=null ?'nohandPicture':'handPicture'}" onclick="changelikes('${list.id}')" id="praise${list.id}"></div>
                                    <div class="number">${list.clicks}</div>
                                    <div class="eyePicture"></div>
                                </div>
                            </div>
                        </div>
                        <div class="downDiv">
                        <span class="contentPage book-name-span">${list.summary }</span>
                        </div>
                    </div>
                 </div>
              </c:forEach>
              <c:if test="${listCount == 0 }">
	           		<div class="no-more">
	                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
	                    <span>木有内容呀~~</span>
	                </div>
	           	</c:if>
              <div class="pageDiv">
	                  <ul class="pagination" id="page1"></ul>
	                  <div style="display: inline-block;    vertical-align: top;text-align: left">
	                      <select id="edu" name="edu">
	                          <option value="5" ${m=='5'?'selected':''}>每页5条</option>
	                          <option value="10" ${m=='10'?'selected':''}>每页10条</option>
	                          <option value="15" ${m=='15'?'selected':''}>每页15条</option>
	                          <option value="20" ${m=='20'?'selected':''}>每页20条</option>
	                      </select>
	                  </div>
	                  <div class="pageJump">
	                      <span>共<span id="allppage">${allpage}</span>页，跳转到</span>
	                      <input type="text" id="jumpId"/>
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
</html>