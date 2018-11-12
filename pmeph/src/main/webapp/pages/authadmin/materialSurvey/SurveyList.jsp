<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<jsp:include page="/pages/comm/headGreenBackGround.jsp">
    <jsp:param name="pageTitle" value="survey"></jsp:param>
</jsp:include>
<div class="body">
    <div class="content-wrapper">
        <div class="st">资料审核&nbsp;>&nbsp;调研表</div>
        <div class="content-teacher-wrapper">
            <c:if test="${material != null}">
                <div class="surveyTitle" style="margin-bottom: 15px;margin-top: 10px">
                    <span style="font-size: 18px">教材名称：${material.materialName}</span>
                </div>
            </c:if>


            <div class="white-out-div">
                <div>
                    <div id="select-search-status-wrapper">
                        <select class="search-condition" id="select-search-status">
                            <option value=""  ${state==''?'selected':''} >全部</option>
                            <option value="1" ${state=='1'?'selected':''}>已填</option>
                            <option value="2" ${state=='2'?'selected':''}>未填</option>
                        </select>
                    </div>
                    <c:if test="${material != null}">
                        <button id="btn-search" onclick="query()">查询</button>
                    </c:if>
                    <c:if test="${material == null}">
                        <button id="btn-search" onclick="queryList()">查询</button>
                    </c:if>
                    <button class="tohis" onclick="window.history.back()">返回</button>
                </div>
                <div class="table-area">
                    <table>
                        <tr>
                            <th>序号</th>
                            <th>调研表名称</th>
                            <th>问卷概述</th>
                            <th>结束时间</th>
                            <th>是否必填</th>
                            <th>填写时间</th>
                            <th>操作</th>
                        </tr>
                        <tbody id="zebra-table">
                        <c:forEach items="${pageResult.rows}" var="list" varStatus="status">
                            <tr>
                                <td>${status.index+1}</td>
                                <td>${list.title}</td>
                                <td>${list.intro}</td>
                                <td>${list.end_date == null?'永久有效':list.end_date}</td>
                                <c:if test="${list.required_for_material == true}">
                                    <td>是</td>
                                </c:if>
                                <c:if test="${list.required_for_material == false}">
                                    <td>否</td>
                                </c:if>


                                <c:if test="${list.gmt_create == null}">
                                    <td>暂未填写</td>
                                </c:if>
                                <c:if test="${list.gmt_create != null}">
                                    <td>${list.gmt_create}</td>
                                </c:if>

                                <c:if test="${list.gmt_create == null}">
                                    <td onclick='fillMaterialSurvey("${list.id}")'>填写</td>
                                </c:if>
                                <c:if test="${list.gmt_create != null}">
                                    <td onclick='surveyDetails("${list.id}")'>查看</td>
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
                        <span>共<span id="totoal_count">${pageResult.pageTotal}</span>页，跳转到</span>
                        <input type="text"/>
                        <span class="pp">页</span>
                        <button type="button" class="button">确定</button>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<div style="clear: both; background-color: white;">
    <jsp:include page="/pages/comm/tail.jsp"/>
</div>
<input type="hidden" id="pageTotal" value="${pageResult.pageTotal}">
<input type="hidden" id="pageNum" value="${pageResult.pageNumber}">
<input type="hidden" id="pageSize" value="${pageResult.pageSize}">
<script>
    $('#pages').selectlist({
        zIndex: 10,
        width: 110,
        height: 30,
        optionHeight: 30,
        onChange: function (n) {
            //指定一页多少条数据
            queryList($("#pageNum").val(), n);
        }
    });
    Page({
        num: $("#pageTotal").val(),		         //页码数
        startnum: $("#pageNum").val(),			//指定页码
        elem: $('#page1'),		//指定的元素
        callback: function (n) {	//点击页码后触发的回调函数
            //选定哪一页
            queryList(n, $("#pageSize").val());
        }
    });

    function queryList(pageNum, pageSize) {
        window.location.href = "${ctx}/orgSurvey/orgSurvey/tolist.action?pageNum=" + pageNum + "&pageSize=" + pageSize;
    }

    function query() {
        var state = $("#select-search-status").val();
        window.location.href = "${ctx}/orgSurvey/tolist.action?state="+ state + "&materialId=" +${material.id};
    }
    function fillMaterialSurvey(str) {
        window.location.href = "${ctx}/orgSurvey/fillSurveyById.action?surveyId=" + str;
    }
    function surveyDetails(str) {
        window.location.href = "${ctx}/orgSurvey/surveyDetailsById.action?surveyId=" + str;
    }
</script>
</body>
</html>

