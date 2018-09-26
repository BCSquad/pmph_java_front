<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
 <script type="text/javascript">
           var contextpath = '${pageContext.request.contextPath}/';
  </script>
  <style>
  	 .node_name {
	        /* display: inline-block;
		    background-color: #33caa9;
		    color: #ffffff; 
		    padding: 0px 8px !important;
		    border-radius: 2px;
		    line-height: 20px !important;
		    height: 20px; */
		    font-size: 14px !important;
	} 
	.ztree li {
	    /* margin: 5px !important;	 */
    }
    .ztree li span.button.chk.checkbox_false_part {
	    background-position: 0 0px !important;
	}
	.ztree li span.button.chk.checkbox_false_part_focus{
		background-position: 0 -14px !important;
	}
  </style>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/materialdec/tab.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/ztree/zTreeStyle.css?t=${_timestamp}"/>
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.ztree.all.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.ztree.exhide.min.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/fuzzysearch.js?t=${_timestamp}"></script>
    <%-- <script src="${ctx}/resources/comm/jquery/jquery.ztree.excheck.min.js?t=${_timestamp}"></script> --%>
    <script src="${ctx}/resources/commuser/materialdec/toContentTypeZTree.js?t=${_timestamp}"></script>
    
</head>
<body>
	<!-- 查询栏 -->
	<div class="qy_div">
		<div style="width: 100%;margin-top: 20px;">
			<span class="mc1">内容分类关键字：</span>
			<input type="hidden" id="product_id" name="product_id" value="${paraMap.product_id}">
			<input type="hidden" id="product_type" name="product_type" value="${paraMap.product_type}">
			<input type="hidden" id="chooseId" name="chooseId" value="${paraMap.chooseId}">
			<input class="cg_input" style="width: 400px;height: 36px;" id="namepath" name="namepath" value="${paraMap.namepath}" />
			<!-- <div class="cxbutn" onclick="query()">
				<span>查询</span>
			</div> -->
		</div>
		<div style="height: 10px;"></div>
		<div style="min-height: 350px;">
			
			<%-- <table id="org_tab" class="tab_2" cellpadding="0" cellspacing="0" >
				<thead>
					<tr>
						<td style="border: 1px solid #dedede;"><span class="bt">内容分类</span></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${pageResult.rows}">
						<tr>
							<td style="height: 30px;border: 1px solid #dedede;"><input type="checkbox" name="radio_id" value="${list.product_type_id}_${list.name_path}"/>
								${list.name_path}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table> --%>
			
			<div id="typeZtree" class="ztree"></div>
			
		</div>
		<%-- <div class="pageDiv" style="margin-top: 20px;">
             <ul class="pagination" id="page1">
             </ul>
             <div style="display: none;vertical-align: top">
                 <select id="page-size-select" name="pageSize">
                 	<option value="5" <c:if test ="${pageResult.pageSize=='5'}" >selected</c:if> >每页5条</option>
                    <option value="10"<c:if test ="${pageResult.pageSize=='10'}" >selected</c:if> >每页10条</option>
                    <option value="15"<c:if test ="${pageResult.pageSize=='15'}" >selected</c:if> >每页15条</option>
                    <option value="20"<c:if test ="${pageResult.pageSize=='20'}" >selected</c:if> >每页20条</option>
                 </select>
             </div>
             <div class="pageJump">
                 <span>共<span id="totoal_count" >${pageResult.pageTotal}</span>页，跳转到</span>
                 <input type="text" id="toPage" name="toPage" value="${paraMap.currentPage}"/>
                 <span class="pp">页</span>
                 <button type="button" class="button" onclick="javascript:tojump()">确定</button>
             </div>
         </div>  --%>
         <div class="button2">
				<div class="bt_tj" onclick="javascript:selectAdd()"><span>确认</span></div>
		</div>
	</div>
</body>
<script>

</script>
</html>
