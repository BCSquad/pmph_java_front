<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
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
<body>
<iframe style="width: 100%;padding: 0;margin: 0;height: 112px;border: none"
        src="${ctx}/pages/authadmin/accountset/head_1.jsp"></iframe>
<div class="body">
    <div class="content-wrapper">
        <div class="message">
            <div class="sousuokuang">
                <input type="text" value="" placeholder='请输入姓名或用户代码' id="ssk">
                <input type="button" value="查询" id="cxan">
                <!--  <div id="gjss"><a href="">高级搜素</a></div>-->
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
                <ul class="pagination" id="page1">
                </ul>
                <div style="display: inline-block; vertical-align: top">
                    <select id="page1select" name="edu">
                        <option value="5 "   ${pageSize=='5'?'selected':'' }>每页5条</option>
                        <option value="10 "  ${pageSize=='10'?'selected':'' }>每页10条</option>
                        <option value="15 "  ${pageSize=='15'?'selected':'' }>每页150条</option>
                    </select>
                </div>
                <div class="pageJump">
                    <span>共<span id="totoal_count" >${page.pageTotal}</span>页</span>
                    <span>跳转到</span>
					<input type="text"/>
					<span>页</span>
                    <button type="button" class="button">确定</button>
                </div>
            </div>
             </div>
            <div class="clear"></div>
    		</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var pageSize =$("#page1select").val();
            Page({
                num: parseInt('${page.pageTotal}'),					//页码数
                startnum: parseInt('${pageNumber}'),				//指定页码
                elem: $('#page1'),		//指定的元素
                callback: function (pageNumber) {	//回调函数
                	pageFun(pageSize,pageNumber);
                }
            });
            $(function () {
                $('#page1select').selectlist({
                    zIndex: 10,
                    width: 110,
                    height: 30,
                    optionHeight: 30,
                    onChange: function () {
                    	var pageSize =this.getSelectedOptionValue(page1select);
                   	 	pageFun(pageSize,'${pageNumber}');
                    }
                });
           });
        function pageFun(pageSize,pageNumber){
        	window.location.href = '<%=basePath%>/user/writerLists.action?pageSize='+pageSize+'&pageNumber='+pageNumber;
        }
    </script>
    <script type="text/javascript">
    </script>
   <div style="background-color: #f4f4f4;width: 100%;padding: 0;margin-top: 0;height: 220px;border: none;overflow: hidden; t">
		<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
	</div>
          <!--  <iframe style="width: 100%;clear:both;padding: 0;margin: 0;height: 200px;border: none" src="${ctx }/pages/comm/tail.jsp"></iframe>-->
</body>
</html>