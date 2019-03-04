<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/20
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <title>课程列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/commuser/course/teacher/courseList.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <script src="<%=path%>/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.qrcode.min.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/commuser/course/teacher/courseList.js?t=${_timestamp}"></script>

</head>
<body>
    <!-- 隐藏域 -->
    <input type="hidden" id="search-name-temp" value="">
    <input type="hidden" id="page-num-temp" value="1">

    <jsp:include page="/pages/comm/head.jsp"></jsp:include>
    <div class="content-wrapper" >

        <div class="wrapper">

            <div class="list" style="background-color: white" >


                <div>

                    <div id="select-search-status-wrapper">
                        <span>课程名称：</span>
                        <input class="search-condition " id="search-name">
                        <span>状态：</span>
                        <select class="search-condition" id="select-search-status">
                            <option value="">全部</option>
                            <option value="未发布">未发布</option>
                            <option value="已发布">已发布</option>
                            <option value="已截止">已截止</option>
                            <option value="已下单">已下单</option>
                            <option value="已付款">已付款</option>
                        </select>
                        <button id="btn-search" onclick="queryBtnClick()">查询</button>
                        <button id="btn-add" onclick="course_detail()">新增</button>
                    </div>
                </div>
                <div class="table-area">
                    <table >
                        <tr>
                            <th>序号</th>
                            <th>课程名称</th>
                            <th>说明</th>
                            <th>发布日期</th>
                            <th>开始日期</th>
                            <th>截止日期</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        <tbody id="zebra-table">

                        </tbody>
                    </table>
                    <div class="no-more" style="display: none;">
                        <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
                        <span>木有内容呀~~</span>
                    </div>
                </div>

                <div class="pagination-wrapper" >
                    <ul class="pagination" id="page1">
                    </ul>
                    <div style="display: inline-block;    vertical-align: top">
                        <select id="page-size-select" name="page-size-select">
                            <option value="5">每页5条</option>
                            <option value="10">每页10条</option>
                            <option value="15">每页15条</option>
                            <option value="20">每页20条</option>

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


    <!-- 二维码悬浮框 -->
    <div class="dialog" id="qrcode-dialog">
        <div class="apache">
            <div class="mistitle"></div>
            <div class="xx" onclick="$('#qrcode-dialog').fadeOut(500);"></div>
        </div>
        <div class="info">
            <div id = "qrcode">

            </div>
            <div class="qrcode-text">
                此码为课程选书入口
            </div>
            <div class="qrcode-text">
                可发送给需要选购教材的学生
            </div>
            <div class="btn-wrapper">
                <button class="btn btn-cancle"  onclick="$('#qrcode-dialog').fadeOut(500);">关闭</button>
            </div>

        </div>
    </div>

</body>
</html>
