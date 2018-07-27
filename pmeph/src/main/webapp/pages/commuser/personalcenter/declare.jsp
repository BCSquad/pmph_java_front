<%--
  Created by IntelliJ IDEA.
  User: xieming
  Date: 2018/7/26
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>人卫E教平台</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div style="width: 100%;height: 100%;background-color:#F8F8F8">
     <div class="content-wrapper">
         <div class="title">临床决策专家申报</div>

         <div class="table-area">
             <table>
                 <tr>
                     <th>序号</th>
                     <th>申报名称</th>
                     <th>申报类别</th>
                     <th>提交日期</th>
                     <th>状态&审核意见</th>
                     <th>操作</th>
                 </tr>
                 <tbody id="zebra-table">
                     <tr>
                         <th>1</th>
                         <th>人卫临床助手专家申报</th>
                         <th>某些传染病和寄生虫病/立克次病体</th>
                         <th>2018/7/12</th>
                         <th>已提交</th>
                         <th style="color: #33CAA9">查看</th>
                     </tr>
                 </tbody>
             </table>
         </div>
     </div>

</div>
<%--<jsp:include page="/pages/comm/tail.jsp">--%>
    <%--<jsp:param name="linked" value="linked"></jsp:param>--%>
<%--</jsp:include>--%>
</body>
<style>
    #zebra-table tr th{
        background-color: white;
    }

    .title{
        width: 1185px;
        height: 25px;
        background-color: white;
        margin-top: 40px;
        float: left;
        padding: 15px 0 15px 15px;
        margin-bottom: 30px;
    }

    .table-area{
        min-height: 80px;
    }

    table{
        padding:0px;
        margin: 24px 0px 24px 0px;
        width:100%;

        border-spacing: 0px 0px;
        border-collapse: collapse;
        white-space: nowrap;

    }

    tr{
        height: 46px;

    }

    th{
        height: 40px;
        background-color: #f0f0f0;
        padding:0px;margin:0px;

        font-size: 14px;
        font-weight: normal;
        font-stretch: normal;
        line-height: 24px;
        letter-spacing: 0px;
        color: #4e4e4e;
        /*border-left:3px  #ffffff solid;*/
    }

    tbody {
        display: table-row-group;
        vertical-align: middle;
        border-color: inherit;
    }

</style>
</html>
