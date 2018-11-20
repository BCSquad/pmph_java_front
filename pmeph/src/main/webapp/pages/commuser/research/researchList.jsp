<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>调研表列表</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="${ctx}/statics/commuser/research/researchList.css?t=${_timestamp}"/>
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div class="body">
   <div class="content-wrapper">
        <div class="st">首页&nbsp;>&nbsp;调研表</div>
        <div class="content-teacher-wrapper">
        <div class="white-out-div">
            <div>
                <div id="select-search-status-wrapper">
                    <select class="search-condition" id="select-search-status">
                        <option value=""  ${state==''?'selected':''} >全部</option>
                        <option value="1" ${state=='1'?'selected':''}>已填</option>
                        <option value="2" ${state=='2'?'selected':''}>未填</option>
                    </select>
                </div>
                <button id="btn-search" onclick="query()">查询</button>
                <button class="tohis" onclick="window.history.back()">返回</button>
            </div>
            <div class="table-area">
                <table style="table-layout: fixed;">
                    <tr>
                        <th style="width: 20px">序号</th>
                        <th style="width: 100px">调研表名称</th>
                        <th style="width: 100px">调研表概述</th>
                        <th style="width: 100px">书籍名称</th>
                        <th style="width: 50px">结束时间</th>
                        <th style="width: 50px">填写时间</th>
                        <th style="width: 30px">操作</th>
                    </tr>
                    <tbody id="zebra-table">
                    <c:forEach items="${pageResult.rows}" var="list" varStatus="status">
                        <tr>
                            <td><div class="font">${status.index+1}</div></td>
                            <td><div class="font">${list.title}</div></td>
                            <td><div class="font">${list.intro}</div></td>
                            <td><div class="font">${list.material_name}</div></td>
                            <td><div class="font">${list.end_date == null?'永久有效':list.end_date}</div></td>
                            <td><div class="font">${list.gmt_create == null?'暂未填写':list.gmt_create}</div></td>
                             <%--${list.gmt_create == null?'<td class="rt" onclick="add(${list.id})">填写</td>':'<td class="rt">查看</td>'}--%>
                            <c:if test="${list.gmt_create == null }">
                                <div><td class="rt" onclick="add(${list.id})"><font style="color: #337AB7;">填写</font></div></td>
                            </c:if>
                            <c:if test="${list.gmt_create != null}">
                                <div><td class="rt" onclick="look(${list.id})"><font style="color: #337AB7;">查看</font></div></td>
                            </c:if>

                        </tr>
                    </c:forEach>
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
                    <select id="pages" name="pages">
                        <option value="10" ${pageResult.pageSize=='10'?'selected':''}>每页10条</option>
                        <option value="20" ${pageResult.pageSize=='20'?'selected':''}>每页20条</option>
                        <option value="50" ${pageResult.pageSize=='50'?'selected':''}>每页50条</option>
                    </select>
                </div>
                <div class="pageJump">
                    <span>共<span id="totoal_count" >${pageResult.pageTotal}</span>页，跳转到</span>
                    <input type="text"/>
                    <span class="pp">页</span>
                    <button type="button" class="button">确定</button>
                </div>
            </div>

        </div>
    </div>
   </div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
<input type="hidden" id="pageTotal" value="${pageResult.pageTotal}">
<input type="hidden" id="pageNum" value="${pageResult.pageNumber}">
<input type="hidden" id="pageSize" value="${pageResult.pageSize}">
</body>
<script>
    $('#pages').selectlist({
        zIndex: 10,
        width: 110,
        height: 30,
        optionHeight: 30,
        onChange: function (n) {
            //指定一页多少条数据
            querylist($("#pageNum").val(),n);
        }
    });

    Page({
        num: $("#pageTotal").val(),		         //页码数
        startnum: $("#pageNum").val(),			//指定页码
        elem: $('#page1'),		//指定的元素
        callback: function (n) {	//点击页码后触发的回调函数
            //选定哪一页
            querylist(n,$("#pageSize").val());
        }
    });

    function querylist(pageNum,pageSize) {
        window.location.href=contextpath+'research/tolist.action?pageNum='+pageNum+'&pageSize='+pageSize;
    }

    function query() {
        var state=$("#select-search-status").val();
        window.location.href=contextpath+'research/tolist.action?state='+state;
    }

    function add(surveyId) {
        window.location.href=contextpath+'orgSurvey/fillSurveyById.action?surveyId='+surveyId+'&state=fromwrtlist';
    }

    function look(id) {
        window.location.href = contextpath+"/orgSurvey/surveyDetailsById.action?surveyId=" + id;
    }
</script>
</html>

