<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title></title>
<script type="text/javascript">
	var contextpath="${pageContext.request.contextPath}/"
</script>
	<link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
	<script src="${ctx}/resources/comm/base.js"></script>	
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js"></script>
<link rel="stylesheet"
	href="${ctx}/statics/authadmin/applydocaudit/dataaudit.css"
	type="text/css">
<script src="${ctx}/resources/authadmin/applydocaudit/dataaudit.js"
	type="text/javascript"></script>

</head>
<body>
	<!-- 隐藏域 -->
	<input type="hidden" id="page-num-temp" value="1">
	<input type="hidden" id="material_id" value="${material_id }">
	
	
	<div
		style="width: 100%; padding: 0; margin: 0; height: 100px; border: none; overflow: hidden;">
		<jsp:include page="/pages/comm/headGreenBackGround.jsp"></jsp:include>
	</div>
	<div class="body" >
		<div class="content-wrapper" >
			<div class="title">
				<span>资料审核>${material_name }</span>
			</div>
			<div class="box" >
				<div class="query_btn">
					<div class="query">
						<input class="query_input" id="search-name-temp"  />
					</div>
					<div class="btn">
						<button class="btn_1"  id="btn-search" onclick="queryBtnClick()">查 询</button>
						<button class="btn_2" onclick="exportExcel();">导出Excel</button>
						<button class="btn_3" onclick="history.go(-1);">返 回</button>
					</div>
				</div>

				<div class="message">
					<table class="table" >
						<thead>
							<tr>
								<td>序号</td>
								<td>姓名</td>
								<td>职务</td>
								<td>职称</td>
								<td>所选书籍与职务</td>
								<td>学校审核</td>
								<td>出版社审核</td>
								<td>遴选结果</td>
							</tr>
						</thead>
						<tbody id="zebra-table">
							
						</tbody>
					</table>
					<div class="no-more" >
						<img src="<c:url value="/statics/image/aaa4.png"></c:url>">
						<span>木有内容呀~~</span>
					</div>
				</div>
				<div style="clear: both;"></div>	
           		<div style="float:right;"   id="fenye">
                        <ul class="pagination" id="page1">
                        </ul>
                        <div style="display: inline-block;    vertical-align: top">
                            <select id="page-size-select" name="page-size-select" onchange="selectPageSize();">
                                <option value="10">每页10条</option>
                                <option value="20">每页20条</option>
                                <option value="50">每页50条</option>
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
	<div style="clear: both;"></div>
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
