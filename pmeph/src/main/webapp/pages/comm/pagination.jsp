<%--
  Created by IntelliJ IDEA.
  User: SuiXinYang
  Date: 2017/11/21
  Time: 11:34
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
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <script src="${ctx}/statics/js/jquery/jquery.js"></script>
    <script src="${ctx}/statics/js/jquery/jquery.selectlist.js"></script>
    <script src="${ctx}/statics/js/jquery/jquery.pager.js"></script>
</head>
<body>
<div>
    <ul class="pagination" id="page1">
    </ul>
    <div style="display: inline-block;    vertical-align: top">
        <select id="edu" name="edu">
            <option value="10">每页10条</option>
            <option value="20">每页20条</option>
            <option value="50">每页50条</option>
        </select>
    </div>
    <div class="pageJump">
        <span>共10页，跳转到</span>
        <input type="text"/>
        <span class="pp">页</span>
        <button type="button" class="button">确定</button>
    </div>
</div>
<!--<div>
    <ul class="pagination" id="page2">
    </ul>
    <div class="pageJump">
        <span>共10页，跳转到</span>
        <input type="text"/>
        <span>页</span>
        <button type="button" class="button">确定</button>
    </div>
</div>-->
<script>
    Page({
        num: 16,					//页码数
        startnum: 3,				//指定页码
        elem: $('#page1'),		//指定的元素
        callback: function (n) {	//回调函数
            console.log(n);
        }
    });
    /*    Page({
            num: 17,					//页码数
            startnum: 6,				//指定页码
            elem: $('#page2'),		//指定的元素
            callback: function (n) {	//回调函数
                console.log(n);
            }
        });*/
    $(function () {
        $('select').selectlist({
            zIndex: 100,
            width: 110,
            height: 30,
            optionHeight: 30
        });
    })
</body>
</html>
