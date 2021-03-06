<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
 <script type="text/javascript">
           var contextpath = '${pageContext.request.contextPath}/';
  </script>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/materialdec/tab.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
</head>
<body>
	<!-- 教材申报 / 临床申报 product -->
	<input id = "productOrMaterial" value="${productOrMaterial }" type="hidden">

	<!-- 查询栏 -->
	<div class="qy_div">
		<div style="width: 100%;margin-top: 20px;">
			<span class="mc1">单位名称：</span>
			<input type="hidden" id="material_id" name="material_id" value="${paraMap.material_id}">
			<input type="hidden" id="product_id" name="product_id" value="${paraMap.product_id}">
			<input class="cg_input" style="width: 457px;height: 36px;" id="orgname" name="orgname" value="${paraMap.orgname}" />
			<div class="cxbutn" onclick="javascript:query()">
				<span>查询</span>
			</div>
		</div>
		<div style="height: 10px;"></div>
		<div style="height: 350px;">
			<table id="org_tab" class="tab_2" cellpadding="0" cellspacing="0" >
				<thead>
					<tr>
						<td style="border: 1px solid #dedede;"><span class="bt">机构名称</span></td> 
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${pageResult.rows}">
						<tr>
							<td style="height: 30px;border: 1px solid #dedede;"><input type="radio" name="radio_id" value="${list.org_id}_${list.org_name}"/>
								${list.org_name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pageDiv" style="margin-top: 20px;">
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
         </div> 
         <div class="button2">
				<div class="bt_tj" onclick="javascript:selectAdd()"><span>确认</span></div>
		</div>
	</div>
</body>
<script>
var material_id = $("#material_id").val();
Page({
    num: parseInt("${pageResult.pageTotal}"),					//页码数
    startnum: parseInt("${pageResult.pageNumber}"),				//指定页码
    elem: $('#page1'),		//指定的元素
    callback: function (n) {	//回调函数
    	var orgname =$("#orgname").val();
        window.location.href="${ctx}/material/toSearchOrg.action?currentPage="+n+"&pageSize="+$("input[name='pageSize']").val()+"&material_id="+material_id+"&orgname="+encodeURI(encodeURI(orgname)); 
    }
});
$(function () {
    $('#page-size-select').selectlist({
        width: 100,
        height: 30,
        optionHeight: 30,
        onChange:function (){
        	var orgname =$("#orgname").val();
        	window.location.href="${ctx}/material/toSearchOrg.action?currentPage="+n+"&pageSize="+$("input[name='pageSize']").val()+"&material_id="+material_id+"&orgname="+encodeURI(encodeURI(orgname));
        }
    });
    $('#org_tab tr:last').find('td').addClass('end'); 
});

function tojump(){
	var toPage = $("#toPage").val();
	window.location.href="${ctx}/material/toSearchOrg.action?currentPage="+toPage+"&pageSize="+$("input[name='pageSize']").val()+"&material_id="+material_id;
}
//查询
function query(){
	var orgname =$("#orgname").val();
	if("product"==$("#productOrMaterial").val()){
		window.location.href="${ctx}/expertation/toSearchOrg.action?product_id="+$("#product_id").val()+"&orgname="+encodeURI(encodeURI(orgname)); 
	}else{
		window.location.href="${ctx}/material/toSearchOrg.action?material_id="+material_id+"&orgname="+encodeURI(encodeURI(orgname)); 

	}
}

//确认选择
function selectAdd(){
	//获取radio值
	var strs= new Array(); //定义一数组 
	var chkObjs =document.getElementsByName("radio_id"); 
	 for(var i=0;i<chkObjs.length;i++){
         if(chkObjs[i].checked){
             strs = chkObjs[i].value.split("_");
             parent.$("#sbdw_name").val(strs[1]);
             parent.$("#sbdw_id").val(strs[0]);
             break;
         }
     }
	 //--关闭 当前页面 开始--
     var index = parent.layer.getFrameIndex(window.name);
     parent.layer.close(index);
     //--关闭 当前页面 结束--
}

//键盘监听
$(document).keydown(function(event){ 
	if(event.keyCode == 13){//回车键
		query();
	}
}); 
</script>
</html>
