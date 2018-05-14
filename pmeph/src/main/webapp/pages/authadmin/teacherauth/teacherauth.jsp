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
    <title>教师认证</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/authadmin/teacherauth/teacherauth.css?t=${_timestamp}" type="text/css">
    <%-- <script type="text/javascript" src="<%=path%>/resources/comm/jquery/jquery.min.js?t=${_timestamp}"></script> --%>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <script src="<%=path%>/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/authadmin/teacherauth/teacherauth.js?t=${_timestamp}"></script>
</head>
<body>

<!-- 隐藏域 -->

<input type="hidden" id="search-name-temp" value="">
<input type="hidden" id="page-num-temp" value="1">


<jsp:include page="/pages/comm/headGreenBackGround.jsp">
    <jsp:param name="pageTitle" value="teachercert"></jsp:param>
</jsp:include>

    <div class="body">
        <div class="content-wrapper">
            <div class="content-teacher-wrapper">
                <div class="white-out-div">
                    <div>
                    	<div id="select-search-condition-wrapper" >
	                        <select class="search-condition" id="select-search-condition" onchange="selectSearchCondition('false')">
	                            <option value="1">姓名</option>
	                            <option value="2">审核状态</option>
	                        </select>
                        </div>
                        <input class="search-condition " id="search-name">
                        <div id="select-search-status-wrapper">
	                        <select class="search-condition" id="select-search-status">
	                            <option value="">全部</option>
	                            <option value="1">待验证</option>
	                            <option value="2">已退回</option>
	                            <option value="3">已通过</option>
	                        </select>
                        </div>
                        <button id="btn-search" onclick="queryBtnClick()">查询</button>
                        <!-- <a id="mutiConditionSwitch">高级查询</a> -->
                        <!--<button id="view-switch">头像视图</button>-->
                    </div>
                    <div class="table-area">
	                    <table >
	                        <tr>
	                            <th>序号</th>
	                            <th>姓名</th>
	                            <th>身份证</th>
	                            <th>手机</th>
	                            <th>邮箱</th>
	                            <th>职务</th>
	                            <th>职称</th>
	                            <th>审核标志</th>
	                            <th>教师资格证</th>
	                            <th>审核操作</th>
	                        </tr>
	                        <tbody id="zebra-table">
	                        
	                        </tbody>
	                    </table>
	                    <div class="no-more" style="display: none;">
		                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
		                    <span>木有内容呀~~</span>
		               	</div>
                    </div>
                   
                    <div class="pagination-wrapper">
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
    
    <%-- <!-- 退回原因填写悬浮框 -->
        <div class="bookmistake" id="bookmistake" style="display: none;">
            <form id="bookmistakeform">
            <input type="hidden"  id="return_id" value="">
            <input type="hidden"  id="return_realname" value="">
                <div class="apache">
                    <div class="mistitle" id="return_cause_title">退回原因:</div>
                    <div class="xx" onclick="hideup()"></div>
                </div>
                
                <div class="info">
                    <textarea class="misarea" id="return_cause" maxLength="40" ></textarea>
                </div>
          
                <div class="">
                	<button class="btn" type="button" onclick="hideup()">取消</button>
                    <button class="btn" type="button" onclick="rejectCertification()">确认</button>
                </div>
            </form>
        </div>--%>
	
		<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
	
</body>
</html>