<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script>
		var contextpath = "${pageContext.request.contextPath}/";
	</script>
    <%-- <base href="<%=basePath%>"> --%>
    <title>图书列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/commuser/booklist/bookList.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css"/>
    <script src="<%=path%>/resources/comm/jquery/jquery.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js"></script>
    <script src="<%=path%>/resources/comm/base.js"></script>
    <script src="<%=path%>/resources/commuser/booklist/booklist.js"></script>
  </head>
  
<body>

<!-- 隐藏域 -->
<input type="hidden" id="materialType" value="${materialType }">

<jsp:include page="/pages/comm/head.jsp"></jsp:include> 
<div class="body" style="background-color: #f6f6f6;padding-bottom:60px">
    <div class="content-wrapper">
        <div class="nav">
        	<span>书籍分类：
        	<c:forEach items="${parentTypeList }" var="type">
        		<a href="/books/list.action?pageSize=${pageSize }&pageNumber=1&order=${order }&type=${type.id}">${type.type_name }</a> >
        	</c:forEach>
        	${materiaName }
			</span>
        </div>
        <div class="select">
            <span>排序：</span>
            <select id="sort" name="sort">
                <option value="1" ${order=='1'?'selected':'' }>综合</option>
                <option value="2" ${order== 2 ?'selected':'' }>最新</option>
                <option value="3" ${order=='3'?'selected':'' }>最热</option>
            </select>
        </div>
        <div class="list" style="background-color: #ffffff">
        	<c:choose>
				<%-- <c:when test="${not empty page}"> --%>
				<c:when test="${listSize > 0}">
					<c:forEach items="${page.rows}" var="books" varStatus="vs">
						<div class="oneList">
			                <div class="leftPicture">
			                	<c:choose>
			                		<c:when test="${not empty books.imageUrl && books.imageUrl!='DEFAULT'}">
			                			<img class="pictureSize" src="${books.imageUrl }">
			                		</c:when>
			                		<c:otherwise>
			                			<img class="pictureSize" src="<%=path%>/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg">
			                		</c:otherwise>
			                	</c:choose>
			                </div>
			                <div class="rightContent">
			                    <div class="upDiv">
			                        <div class="upLeft">
			                            <div class="bookName">
			                            	<a class="not-like-an-a" target="_blank" href="<%=path%>/readdetail/todetail.action?id=${books.id}"><span class="book-name-span">${books.bookname}</span></a>
			                                
			                            </div>
			                            <div class="name">
			                                <span>${books.author} 丨 ${books.publisher}</span>
			                            </div>
			                        </div>
			                        <div class="upRight">
			                            <div class="pictureDiv">
			                            	<div class="number2">${books.comments}</div>
						                    <div class="comment" title="评论数"></div>
						                    <div class="number2">${books.likes}</div>
						                    <!-- <div class="handPicture active" title="取消赞" onclick="likeSwitch($!{c.bookId},this)"></div> -->
						                    <div class="handPicture ${books.likeId!=null?'active':'' }" title="点赞" onclick="likeSwitch(${books.id},this)" ></div>
						                    <div class="number">${books.clicks}</div>
						                    <div class="eyePicture div" title="点击数"></div>																						
			                            </div>
			                        </div>
			                    </div>
			                    <div class="downDiv">
			                        <span class="contentPage">${books.detail}</span>
			                    </div>
			                </div>
            			</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="no-more" style="">
	                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
	                    <span>木有内容呀~~</span>
	               	</div>
				</c:otherwise>
			</c:choose>
            <div class="pageDiv">
                <ul class="pagination" id="page1">
                </ul>
                <div style="display: inline-block; vertical-align: top">
                    <select id="pages" name="pages">
                        <option value="10 "  ${pageSize=='10'?'selected':'' }>每页10条</option>
                        <option value="20 "  ${pageSize=='20'?'selected':'' }>每页20条</option>
                        <option value="50 "  ${pageSize=='50'?'selected':'' }>每页50条</option>
                    </select>
                </div>
                <div class="pageJump">
                    <span>共<span id="totoal_count" >${page.pageTotal}</span>页</span>
                    <span>跳转到</span>
					<input type="text"/>
					<span>页</span>
                    <button type="button" class="button">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	var pageSize =$("#pages").val();
	var order =$("#sort").val();
    Page({
        num: parseInt('${page.pageTotal}'),					//页码数
        startnum: parseInt('${pageNumber}'),				//指定页码
        elem: $('#page1'),		//指定的元素
        callback: function (pageNumber) {	//回调函数
        	pageFun(pageSize,pageNumber,order,'${materialType}');
        }
    });
    $(function () {
        $('#pages').selectlist({
            zIndex: 10,
            width: 110,
            height: 30,
            optionHeight: 30,
            onChange: function () {
            	var pageSize =this.getSelectedOptionValue(pages);
           	 	pageFun(pageSize,'${pageNumber}',order,'${materialType}');
            }
        });
        $('#sort').selectlist({
            zIndex: 10,
            width: 70,
            height: 20,
            optionHeight: 20,
            onChange: function () {
            	var order =this.getSelectedOptionValue(sort);
           	 	pageFun(pageSize,'${pageNumber}',order,'${materialType}');
            }
        });
    });
    //分页
    function pageFun(pageSize,pageNumber,order,materialType){
    	window.location.href = '<%=path%>/books/list.action?pageSize='+pageSize+'&pageNumber='+pageNumber+'&order='+order+'&type='+materialType;
    }
</script>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
</body>
</html>
