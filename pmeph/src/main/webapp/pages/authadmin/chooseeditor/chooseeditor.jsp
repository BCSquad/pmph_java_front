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
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/authadmin/chooseeditor/chooseeditor.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <script src="<%=path%>/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/authadmin/chooseeditor/chooseeditor.js?t=${_timestamp}"></script>
</head>
<body class="body">
<jsp:include page="/pages/comm/head.jsp"></jsp:include> 

<!-- 隐藏域 -->

<input type="hidden" id="search-name-temp" value="">
<input type="hidden" id="page-num-temp" value="1">
<input type="hidden" id="textBookId" value="${textBookId }">
<input type="hidden" id="selectedIds" value="${selectedIds }">
<input type="hidden" id="selectedNumIds" value="${selectedNumIds}">
<input type="hidden" id="is_locked" value="${is_locked }">
<input type="hidden" id="is_force_end" value="${is_force_end }">
<input type="hidden" id="is_published" value="${is_published }">
<input type="hidden" id="logUserId" value="${logUserId }">
<input type="hidden" id="logUserName" value="${logUserName }">

<input type="hidden" id="isFirstEditorLogIn" value="${isFirstEditorLogIn }">
<input type="hidden" id="is_digital_editor_optional" value="${is_digital_editor_optional }">



<div class="content-wrapper">
    <div class="top">
        <div class="title">
            <div class="top1">
            	<a  onclick="bfRedirect('personalhomepage/tohomepage.action')">个人中心 </a>
            	> 
            	<a onclick="bfRedirect('personalhomepage/tohomepage.action?pagetag=jcsb')">教材申报 </a>
            	> 
            	<a >申报进度</a>
            	>选择编委
            </div>
        </div>
        <div class="bt">
            <div class="top2"><!-- <a href="/books/list.action" target="_blank"> --><B>${textBookName }</B><!-- </a> --></div>
            <div class="top3">策划编辑：${planning_editor }</div>
        </div>
    </div>
    <div id="table">
        <div id="tb1">
            <div><input id="search-name" type="text" class="iu1" placeholder="姓名"></div>
            <div>
                <select id="select-search-org" name="select-search-org" class="iu2">
                	<option value="all">全部</option>
                	<option value="0">人民卫生出版社</option>
                	<c:forEach items="${OrgList }" var="org">
                		<option value="${org.id }">${org.org_name }</option>
                	</c:forEach>
                    
                </select>
            </div>
            
            <button class="div1" onclick="queryBtnClick();" style="cursor: pointer;">查询</button>
            <button class="div1" onclick="getExcel(1);" style="cursor: pointer;">导出已选名单</button>
            <button class="div1" onclick="getExcel(2);" style="cursor: pointer;">导出所有名单</button>
            <div id="handleBtn">
	            <button class="div2" onclick="selectReset()" style="cursor: pointer;">重置</button>
	            <button class="div3" onclick="selectRubmit()" style="cursor: pointer;">提交</button>
	            <button class="div3" onclick="tempSave()" style="cursor: pointer;">暂存</button>
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
                        <c:if test="${is_digital_editor_optional == true }">
                        	<td class="td9">选择数字编委</td>
                        </c:if>
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
	
	<div style="width: 100%;height: 60px;clear: both;"></div>
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 

</body>
</html>