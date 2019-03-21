<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>编写进度</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script>
        var contextpath='${pageContext.request.contextPath}/';
    </script>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="${ctx}/statics/commuser/report_progress/progress.css?t=${_timestamp}" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/layer/layer.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
	
</head>
<body>

<c:if test="${userType == '2' }">
	<jsp:include page="/pages/comm/headGreenBackGround.jsp">
		<jsp:param name="pageTitle" value="backlog"></jsp:param>
	</jsp:include>
</c:if>
<c:if test="${userType == '1' }">
	<jsp:include page="/pages/comm/head.jsp"></jsp:include>
</c:if>

<div class="body">

	<input type="hidden" id="erpParamJson" value="${erpParamJson}">
	<input type="hidden" id="materialId" value="${materialId}">


    <div class="content-wrapper">
        <div class="title">
            <%--<span><a class="alink" href="${ctx}/personalhomepage/tohomepage.action?">个人中心 ></a><a class="alink" href="${ctx}/personalhomepage/tohomepage.action?pagetag=jcsb"> 教材申报</a> > 编写进度</span>--%>
			<h2>${materialName}</h2>
        </div>

		<div style="margin: 15px;">
			<input id = "textbookName" style="border-radius: 3px;padding: 5px;outline: none;border: 1px solid #b4b4b4;">
			<button onclick="search()" style="    background-color: #29ab8f;color: white;border-radius: 5px;height: 31px;outline: none;padding: 3px 15px;">搜索</button>
		</div>


		<table id = "textbookTable">
			<tr>
				<th>书名</th>
				<th>当前编写进度</th>
				<th>更新时间</th>
				<th>负责人</th>
			</tr>
			<tbody id="listDataWrapper">
				<c:forEach items="${bookList}" var="b" varStatus="status" >
					<tr topicNumber = "${b.topicNumber}">
						<td>${b.textbookName}</td>
						<td name="currentStatus"></td>
						<td name="currentStatusDate"></td>
						<td name="currentHandler"></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

    </div>
</div>
<div style="clear: both; background-color: #f6f6f6;">   
<jsp:include page="/pages/comm/tail.jsp"/>
</div>
</body>

  <style>
	  table#textbookTable {
		  width: 1170px;
		  margin: auto;
		  margin-top: 15px;
		  border-collapse: collapse;
	  }
	  th {
		  background-color: #b4b4b4;
		  color: #636363;
		  padding: 5px 8px;
		  border-right: 2px solid #ffffff;
	  }
	  td {
		  padding: 3px 8px;
	  }
	  table tbody tr:nth-child(odd){
		  background-color:#f0f0f0;
		  text-align: center;
	  }

	  table tbody tr:nth-child(even){
		  text-align: center;

	  }

  </style>

<script type="text/javascript">
	var erpData = [];
	$(function () {
		var erpParamJson = $("#erpParamJson").val();

		/*$.ajax({
			type:'post',
			url:'erpUrl',//TODO erp	编写进度请求地址
			dataType:'json',
			async:false,
			data:$.parseJSON(erpParamJson),
			success:function (res) {
				if(res.code == 1){
					erpData = res.data;
					fixErpData();
                }
            }
		});*/

		//解开可测试 注意现textbook表中的topicnumber基本没有数据 需加上相应数据
        var data = [
            {
                "topicNumber":"10015-1",
                "currentStatus":"图书定稿中",
                "currentStatusDate":"2019-03-18 17:58:21",
                "currentHandler":"tony"
            },
            {
                "topicNumber":"10015-2",
                "currentStatus":"图书编写中",
                "currentStatusDate":"2019-03-1",
                "currentHandler":"tom"
            },
            {
                "topicNumber":"10015-3",
                "currentStatus":"图书审核中",
                "currentStatusDate":"2019-03-1",
                "currentHandler":"tom"
            }
        ];
        for(i in data){
            var item = data[i];
            var $tr =$("tr[topicnumber='"+item.topicNumber+"']");
            $tr.find("td[name='currentStatus']").html(item.currentStatus);
            $tr.find("td[name='currentStatusDate']").html(item.currentStatusDate);
            $tr.find("td[name='currentHandler']").html(item.currentHandler);
        }

    })

	function search() {
        $.ajax({
            type:'get',
            url:contextpath + "progress/refreshEditProgress.action?t=" + new Date().getTime(),
            dataType:'json',
            async:false,
            data:{materialId:$("#materialId").val(),
                textbookName:$("#textbookName").val()
			},
            success:function (res) {
                var list =res.bookList
                $("#textbookTable #listDataWrapper").html("");
				for(i =0 ;i<list.length ;i++){
                    var da = list[i];
                    var $tr = $("<tr topicNumber = \""+da.topicNumber+"\">" +
                        "<td>"+da.textbookName+"</td>" +
                        "<td name=\"currentStatus\"></td>" +
                        "<td name=\"currentStatusDate\"></td>" +
                        "<td name=\"currentHandler\"></td>" +
                        "</tr>");
                    console.log($tr);
                    $("#textbookTable #listDataWrapper").append($tr);
				}
                fixErpData();
            }
        })

    }
    function fixErpData() {
        for(i in erpData){
            var item = data[i];
            var $tr =$("tr[topicnumber='"+item.topicNumber+"']");
            $tr.find("td[name='currentStatus']").html(item.currentStatus);
            $tr.find("td[name='currentStatusDate']").html(item.currentStatusDate);
            $tr.find("td[name='currentHandler']").html(item.currentHandler);
        }
    }

</script>
</html>
