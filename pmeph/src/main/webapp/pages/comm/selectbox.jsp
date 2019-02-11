<%--
  Created by IntelliJ IDEA.
  User: SuiXinYang
  Date: 2017/11/21
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/statics/css/jquery.selectlist.css?t=${_timestamp}"/>">
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
</head>
<body>
<select id="edu" name="edu">
    <option value="0">初中</option>
    <option value="1">高中</option>
    <option value="2">中技</option>
    <option value="3">中专</option>
    <option value="4">大专</option>
    <option value="5">本科</option>
    <option value="6">硕士</option>
    <option value="7">博士</option>
    <option value="8">博士后</option>
    <option value="9">教授</option>
</select>
<select id="salary" name="salary">
    <option value="0">面议</option>
    <option value="1">1000以下</option>
    <option value="2">1000-2000</option>
    <option value="3">2000-3000</option>
    <option value="4">3000-5000</option>
    <option value="5">5000-6000</option>
    <option value="6">6000-8000</option>
    <option value="7">8000-10000</option>
    <option value="8">10000-20000</option>
    <option value="9">20000以上</option>
</select>
<script type="text/javascript">
    $(function () {
        $('select').selectlist({
            zIndex: 100,
            width: 200,
            height: 26,
            optionHeight: 26,
            initValue:8,
            onChange:function (value,text) {
                console.log(value+","+text);
            }
        });
    })
</script>
</body>
</html>
