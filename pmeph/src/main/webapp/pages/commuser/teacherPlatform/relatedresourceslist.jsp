<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>相关资源-更多</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="<c:url value="/statics/css/base.css?t=${_timestamp}"/>" type="text/css">
    <link href="<c:url value="/statics/commuser/userinfo/userinfo.css?t=${_timestamp}"/>" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="${ctx}/statics/commuser/teacherPlatform/relatedresourceslist.css" type="text/css">
    <script src="<%=path%>/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/base.js?t=${_timestamp}"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div class="body" style="background: #f6f6f6;">
    <div class="content-wrapper">
        <div style="height: 30px"></div>
        <div class="sxy-div-content">
            <div style="height:50px;">
                <span class="sxy-div-menu" style="color: #333333">相关资源</span>
                <span class="sxy-div-back">&lt;&lt;返回活动</span>
            </div>
        </div>
        <div style="height:14px"></div>
        <div class="sxy-div-content" style="min-height: 600px">
            <c:forEach items="${list}" var="list" varStatus="status">
                <div class="list">
                    <div class="item">
                        <a class="content">${status.index+1}.${list.source_name}</a>
                        <div class="time" >
                            <div class="downimg" onclick="window.location.href='${ctx}/file/download/${list.file_id}.action'"></div>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <%--分页--%>
            <div class="pageDiv">
                <ul class="pagination" id="page1">
                </ul>
                <div style="display: inline-block; vertical-align: top">
                    <select id="pages" name="pages">
                        <option value="10 "  ${endrow=='10'?'selected':'' }>每页10条</option>
                        <option value="20 "  ${endrow=='20'?'selected':'' }>每页20条</option>
                        <option value="50 "  ${endrow=='50'?'selected':'' }>每页50条</option>
                    </select>
                </div>
                <div class="pageJump">
                    <span>共<span id="totoal_count" >${pagesize}</span>页</span>
                    <span>跳转到</span>
                    <input type="text"/>
                    <span>页</span>
                    <button type="button" class="button">确定</button>
                </div>
            </div>
        </div>
    </div>


    <div style="height: 60px; width: 100%;"></div>

</div>
<div style="clear: both"></div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>

</html>
<input type="hidden" value="${id}" id="id">
<input type="hidden" value="${startrow}" id="startrow">
<input type="hidden" value="${endrow}" id="endrow">
<input type="hidden" value="${pagesize}" id="pagesize">
<script type="text/javascript">
    $(function () {
        var startrow=$("#startrow").val();
        var endrow=$("#endrow").val();
        var pagesize=$("#pagesize").val();
        var id=$("#id").val();

        $('#pages').selectlist({
            zIndex: 10,
            width: 110,
            height: 30,
            optionHeight: 30,
            onChange: function (n) {
                //指定一页多少条数据
                endrow=n;
                queryList(id,startrow,endrow);
            }
        });
        Page({
            num: pagesize,		         //页码数
            startnum: startrow,			//指定页码
            elem: $('#page1'),		//指定的元素
            callback: function (n) {	//点击页码后触发的回调函数
                //选定哪一页
                startrow=n;
                queryList(id,startrow,endrow);
            }
        });
    });

    function queryList(id,startrow,endrow) {
        window.location.href=contextpath+'teacherPlatform/videotolist.action?id='+id+'&startrow='+startrow+'&endrow='+endrow;
    }

</script>