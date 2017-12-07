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

<div class="org-head">
    <div >
        
        <div class="div-content">
            <div id="div-titletop">
                <span class="top-lable1">欢迎访问人教e卫平台！</span>
                <span class="top-lable2">∨</span>
                <span class="top-lable2">&nbsp</span>
                <span class="top-lable2">哈尔滨医科大学的账号</span>
                <span class="top-lable2">&nbsp&nbsp&nbsp&nbsp&nbsp</span>
                <span class="top-lable2">下载手机客户端！</span>
            </div>
        </div>
    </div>
    <div class="div-menu">
        <div class="div-content">
            <div style="width:176px;float:left;"><img alt="" src="/pmeph/statics/image/_logo.jpg"/></div>
            <div style="width:90px;float:left;">&nbsp</div>
            <div class="div-menu-child "     >待办事项</div>
            <div class="div-menu-child "       >申报资料审核</div>
            <div class="div-menu-child "	 onclick="window.location.href='/pmeph/teacherauth/toPage.action'">教师认证</div>
            <div class="div-menu-child  div-menu-child-click" onclick="window.location.href='/pmeph/user/writerLists.action'">用户管理</div>
            <div class="div-menu-child "     onclick="window.location.href='/pmeph/user/writerLists.action'">账户设置</div>
            <div class="div-menu-child "     onclick="window.location.href='/pmeph/teacherauth/toPage.action'">消息</div>
        </div>
    </div>
</div>

<div class="body">
    <div class="content-wrapper">
        <div class="message">
             <div class="sousuokuang">
                <select id="sstj" name="sstj">
                    <option value="0" >姓名</option>
                    <option value="1" >用户代码</option>
                </select>
                <input type="text" value="" placeholder='请输入' id="ssk" name="username">
                <input type="button" value="查询" id="cxan" onclick="query()">
               	<!--  <div id="gjss"><a href="">高级搜素</a></div>-->
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
                <tbody id='tb'>
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
                    <select id="pages" name="pages">
                       	<option value="10 "  ${pageSize=='10'?'selected':'' }>每页10条</option>
                        <option value="20 "  ${pageSize=='20'?'selected':'' }>每页20条</option>
                        <option value="50 "  ${pageSize=='50'?'selected':'' }>每页50条</option>
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
<script type="text/javascript">
	var pageSize =$("#pages").val();
            Page({
                num: parseInt('${page.pageTotal}'),					//页码数
                startnum: parseInt('${pageNumber}'),				//指定页码
                elem: $('#page1'),		//指定的元素
                callback: function (pageNumber) {	//回调函数
                	pageFun(pageSize,pageNumber);
                }
            });
            $(function () {
                $('#pages').selectlist({
                    zIndex: 10,
                    width: 110,
                    height: 30,
                    optionHeight: 30,
                    onChange: function () {
                    	var pageSize =this.getSelectedOptionValue(pages);
                    	var p=$('#pages').val();
                    	alert(pageSize);
                    	alert(p);
                   	 	pageFun(pageSize,'${pageNumber}');
                    }
                });
                $('#ssk').keyup(function(event){
            		if(event.keyCode ==13){ //回车键弹起事件
            			this.query();
            		  }
                });
           });
        function query(){
        	var username=$("#ssk").val();
        	window.location.href = '<%=basePath%>/user/writerLists.action?username='+username;
        }
        function pageFun(pageSize,pageNumber){
        	window.location.href = '<%=basePath%>/user/writerLists.action?pageSize='+pageSize+'&pageNumber='+pageNumber;
        }
    </script>
	<div style="background-color: white;width: 100%;padding: 0;margin: 0;height: 220px;border: none;overflow: hidden;">
		<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
	</div>       
</body>
</html>