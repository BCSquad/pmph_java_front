<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contxtpath='${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>用户管理</title>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/base.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/user/writerLists.css" type="text/css">
	<script src="${ctx}/resources/commuser/user/writerLists.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
        $(function () {
            Page({
                num: 16,					//页码数
                startnum: 3,				//指定页码
                elem: $('#page1'),		//指定的元素
                callback: function (n) {	//回调函数
                    console.log(n);
                }
            });
            $('#page1select').selectlist({
                zIndex: 10,
                width: 110,
                height: 30,
                optionHeight: 30
            });
        })
    </script>
<body>
<iframe style="width: 100%;padding: 0;margin: 0;height: 112px;border: none"
        src="${ctx}/pages/authadmin/accountset/head_1.jsp"></iframe>
<div class="body">
<input type="hidden" id="page-num-temp" value="1">
    <div class="content-wrapper">
        <div class="message">
            <div class="sousuokuang">
                <input type="text" value="" placeholder='请输入姓名或用户代码' id="ssk">
                <input type="button" value="查询" id="cxan">
                <div id="gjss"><a href="">高级搜素</a></div>
            <div class="friend-box-wrapper" id="table-15">
            </div>
            <table class="table">
                <thead>
                <tr>
                    <td class="" width="50">序号</td>
                    <td class="" width="100">用户代码</td>
                    <td class="" width="128">姓名</td>
                    <td class="" width="124">手机</td>
                    <td class="" width="185">邮箱</td>
                    <td class="" width="135">职务</td>
                    <td class="" width="90">职称</td>
                    <td class="">操作</td>
                </tr>
                </thead>
                <tbody>
	              		<c:forEach items="${page.rows }" var="item">
	              			<tr>
	              				<td>${item.id } </td>
	              				<td>${item.username } </td>
	              				<td>${item.realname } </td>
	              				<td>${item.handphone } </td>
	              				<td>${item.email } </td>
	              				<td>${item.position } </td>
	              				<td>${item.title } </td>
	              				<td><a href="">发消息</a></td>
	              			</tr>
	              		</c:forEach>
                </tbody>
            </table>
              <div class="fenyelan">
                <div class="utf">
                    <div>
                        <ul class="pagination" id="page1">
                        </ul>
                        <div style="display: inline-block;    vertical-align: top">
                            <select id="page1select" name="edu">
                                <option value="10">每页10条</option>
                                <option value="20">每页20条</option>
                                <option value="50">每页50条</option>
                            </select>
                        </div>
                		<div class="pageJump">
                    		<span>共<span id="totoal_count" >${page.total }</span>页，跳转到</span>
                    		<input type="text"/>
                    		<span class="pp">页</span>
                    		<button type="button" class="button">确定</button>
               			</div>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
    		</div>
		</div>
	</div>
</div>
<!--  <iframe style="width: 100%;clear:both;padding: 0;margin: 0;height: 200px;border: none" src="${ctx }/pages/comm/tail.jsp"></iframe>-->
<div style="background-color: #f4f4f4;width: 100%;padding: 0;margin: 0;height: 220px;border: none;overflow: hidden;">
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
</div>
</body>
</html>