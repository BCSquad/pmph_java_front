<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>图书列表</title>
	<link rel="stylesheet" href="<%=basePath%>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=basePath%>/statics/commuser/booklist/bookList.css" type="text/css">
    <link rel="stylesheet" href="<%=basePath%>/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="<%=basePath%>/statics/css/jquery.selectlist.css"/>
    <script src="<%=basePath%>/statics/js/jquery/jquery.js"></script>
    <script src="<%=basePath%>/statics/js/jquery/jquery.selectlist.js"></script>
    <script src="<%=basePath%>/statics/js/jquery/jquery.pager.js"></script>
  </head>
  
<body>
<iframe style="width: 100%;padding: 0;margin: 0;height: 81px;border: none" src="<%=basePath%>/statics/comm/head.html"></iframe>
<div class="body" style="background-color: #f6f6f6;padding-bottom:60px">
    <div class="content-wrapper">
        <div class="nav">
            <span>书籍分类：学校教育 > 长学制教材 > 临床医学</span>
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
				<c:when test="${not empty page}">
					<c:forEach items="${page.rows}" var="books" varStatus="vs">
						<div class="oneList">
			                <div class="leftPicture">
			                    <img class="pictureSize" src="<%=basePath%>/statics/image/ts04.png">
			                </div>
			                <div class="rightContent">
			                    <div class="upDiv">
			                        <div class="upLeft">
			                            <div class="bookName">
			                                <span>${books.bookname}</span>
			                            </div>
			                            <div class="name">
			                                <span>${books.author} 丨 ${books.publisher}</span>
			                            </div>
			                        </div>
			                        <div class="upRight">
			                            <div class="pictureDiv">
			                                <div class="number2">${books.clicks}</div>
			                                <div class="handPicture">${books.likes}</div>
			                                <div class="number">${books.comments}</div>
			                                <div class="eyePicture">${books.bookmarks}</div>
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
					<div class="oneList">
						没有相关数据
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
        	pageFun(pageSize,pageNumber,order);
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
           	 	pageFun(pageSize,'${pageNumber}',order);
            }
        });
        $('#sort').selectlist({
            zIndex: 10,
            width: 70,
            height: 20,
            optionHeight: 20,
            onChange: function () {
            	var order =this.getSelectedOptionValue(sort);
           	 	pageFun(pageSize,'${pageNumber}',order);
            }
        });
    });
    //分页
    function pageFun(pageSize,pageNumber,order){
    	window.location.href = '<%=basePath%>/books/list.action?pageSize='+pageSize+'&pageNumber='+pageNumber+'&order='+order;
    }
</script>
<iframe style="width: 100%;clear:both;padding: 0;margin: 0;height: 190px;border: none" src="<%=basePath%>/statics/comm/tail.html"></iframe>
</body>
</html>
