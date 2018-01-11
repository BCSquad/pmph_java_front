<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextpath=request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script>
		var contextpath = "${pageContext.request.contextPath}/";
	</script>
	<%-- <base href="<%=basePath%>"> --%>
    <title>第一主编选择编委</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/authadmin/chooseeditor/chooseeditor.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css"/>
    <script src="<%=path%>/resources/comm/jquery/jquery.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js"></script>
    <script src="<%=path%>/resources/comm/base.js"></script>
    <script src="<%=path%>/resources/authadmin/chooseeditor/chooseeditor.js"></script>
</head>
<body class="body">
<jsp:include page="/pages/comm/head.jsp"></jsp:include> 

<!-- 隐藏域 -->

<input type="hidden" id="search-name-temp" value="">
<input type="hidden" id="page-num-temp" value="1">
<input type="hidden" id="textBookId" value="${textBookId }">
<input type="hidden" id="selectedIds" value="${selectedIds }">
<input type="hidden" id="selectedNumIds" value="${selectedNumIds}">
<input type="hidden" id="is_list_selected" value="${is_list_selected }">



<div class="content-wrapper">
    <div class="top">
        <div class="title">
            <div class="top1">
            	<a  onclick="bfRedirect('personalhomepage/tohomepage.action')">个人中心 </a>
            	> 
            	<a onclick="bfRedirect('personalhomepage/tohomepageone.action')">教材申报 </a>
            	> 
            	<a >申报进度</a>
            	>选择编委
            </div>
        </div>
        <div class="bt">
            <div class="top2"><a href="/books/list.action" target="_blank"><B>${textBookName }</B></a></div>
            <div class="top3">编辑策划：${logUserName }</div>
        </div>
    </div>
    <div id="table">
        <div id="tb1">
            <div><input id="search-name" type="text" class="iu1" placeholder="姓名"></div>
            <div>
                <select id="select-search-org" name="select-search-org" class="iu2">
                	<option value="all">全部</option>
                	<c:forEach items="${OrgList }" var="org">
                		<option value="${org.id }">${org.org_name }</option>
                	</c:forEach>
                    
                </select>
            </div>
            
            <button class="div1" onclick="queryBtnClick();">查询</button>
            <div id="handleBtn">
	            <button class="div2" onclick="selectReset()">重置</button>
	            <button class="div3" onclick="selectRubmit()">提交</button>
	            <button class="div3" onclick="tempSave()">暂存</button>
            </div>
        </div>
        <div class="tb2">
            <div class="message">
                <table class="table">
                    <thead>
                    <tr>
                        <td class="td1">序号</td>
                        <td class="td2">姓名</td>
                        <td class="td3">性别</td>
                        <td class="td4">工作单位</td>
                        <td class="td5">申报单位</td>
                        <td class="td6">职位</td>
                        <td class="td7">职称</td>
                        <td class="td8">选择编委</td>
                        <td class="td9">选择数字编委</td>
                    </tr>
                    </thead>
                    <tbody id="userTbody">
                    	
                    </tbody>
                    
                    
                </table>
                <div class="no-more" style="display: none;">
                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
                    <span>木有内容呀~~</span>
               	</div>
            </div>
            <div class="utf">
                <div>
                    <ul class="pagination" id="page1">
                    </ul>
                    <div style="display: inline-block;    vertical-align: top">
                        <select id="page-size-select" name="page-size-select" onchange="selectPageSize();">
                            <option value="10">每页10条</option>
                            <option value="20">每页20条</option>
                            <option value="50">每页50条</option>
                        </select>
                    </div>
                    <div class="pageJump">
                        <span>共<span id="totoal_count" >${totoal_count }</span>页，跳转到</span>
                        <input type="text"/>
                        <span class="pp">页</span>
                        <button type="button" class="button">确定</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
	
	<div style="width: 100%;height: 60px"></div>
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 

</body>
</html>