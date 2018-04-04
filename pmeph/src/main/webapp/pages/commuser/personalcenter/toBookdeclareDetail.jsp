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
	<title>我要出书-申报</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/statics/commuser/bookdeclare/bookdeclareadd.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/statics/css/jquery.calendar.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css" type="text/css">
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.calendar.js"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/base.js"></script>
	<script src="${ctx}/resources/comm/jquery/jquery.fileupload.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/resources/commuser/bookdeclare/bookdeclarezc.js"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
	<div class="content-wrapper">
		<div class="sbxq_title">
			<span><a style="text-decoration: none;color: #999999;" href="${contextpath}personalhomepage/tohomepage.action?pagetag=dt">个人中心</a> > <a style="text-decoration: none;color: #999999;" href="${contextpath}personalhomepage/tohomepage.action?pagetag=wycs&pageNum=1&pageSize=10"></a> > 查看选题申报表</span>
		</div>
		<form id="objForm">
			<!-- 图书书稿情况-->
			<div class="sbxq_item">

				<div class="content">
					<table class="tab_1">
						<tr>
							<td colspan="3">
								<div>
									<span id="tsxz_span1"></span>
									<span class="tsxz_title">图书书稿情况</span>
									<%--<span class="tsxz_ts1"><img src="${ctx}/statics/image/btxx.png" /></span>--%>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><span>选题名称：&emsp;&emsp;</span>
								${topicMap.bookname}
							</td>
							<td><span >读者对象：&emsp;&emsp;</span>
								<c:if test="${topicMap.reader=='0'}">医务工作者</c:if>
								<c:if test="${topicMap.reader=='1'}">医学院校师生</c:if>
								<c:if test="${topicMap.reader=='2'}">大众</c:if>
							</td>
						</tr>
						<tr>
							<td><span>预计交稿时间：</span>
								${topicMap.deadline}
								<%--<span class="dateclear"onclick="cleadate()"></span>--%>
							</td>
							<td><span >选题来源：&emsp;&emsp;</span>
								<c:if test="${topicMap.source=='0'}">社策划</c:if>
								<c:if test="${topicMap.source=='1'}">编辑策划</c:if>
								<c:if test="${topicMap.source=='2'}">修订</c:if>
								<c:if test="${topicMap.source=='3'}">离退休编审策划</c:if>
								<c:if test="${topicMap.source=='4'}">专家策划</c:if>
								<c:if test="${topicMap.source=='5'}">上级交办</c:if>
								<c:if test="${topicMap.source=='6'}">作者投稿</c:if>
							</td>
							<td><span >预估字数：&emsp;&emsp;</span>
								${topicMap.word_number}
							</td>
						</tr>
						<tr>
							<td><span >预估图数：&emsp;&emsp;</span>
								${topicMap.picture_number}
							</td>
							<td><span>学科及专业：&emsp;</span>
								${topicMap.subject}
							</td>
							<td><span>级&emsp;&emsp;别：&emsp;&emsp;</span>
								<%--<div class="tsjb">&emsp;--%>
								<c:if test="${topicMap.rank=='0'}">低</c:if>
								<c:if test="${topicMap.rank=='1'}">中</c:if>
								<c:if test="${topicMap.rank=='2'}">高</c:if>
								<%--</div>--%>
							</td>
						</tr>
						<tr>
							<td colspan="3"><span>图书类别：&emsp;&emsp;</span>
								<%--<div class="tslb">&emsp;--%>
								<c:if test="${topicMap.type=='0'}">专著</c:if>
								<c:if test="${topicMap.type=='1'}">基础理论</c:if>
								<c:if test="${topicMap.type=='2'}">教材</c:if>
								<c:if test="${topicMap.type=='3'}">论文集</c:if>
								<c:if test="${topicMap.type=='4'}">图谱</c:if>
								<c:if test="${topicMap.type=='5'}">科普</c:if>
								<c:if test="${topicMap.type=='6'}">应用技术</c:if>
								<c:if test="${topicMap.type=='7'}">教辅</c:if>
								<c:if test="${topicMap.type=='8'}">工具书</c:if>
								<c:if test="${topicMap.type=='9'}">其他</c:if>
								<%--</div>--%>
							</td>
						</tr>
						<!--修订书稿-->
						<tr>
							<td colspan="3">
								<div>
									<span id="tsxz_span2"></span>
									<span class="tsxz_title">修订书稿</span>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><span>原书名：&emsp;&emsp;&emsp;</span>
								${topicMap.revision_bookname}
							</td>
							<td><span>原编著者：&emsp;&emsp;</span>
								${topicMap.revision_author}
							</td>
						</tr>
						<tr>
							<td><span>上版出版时间：</span>
								${topicMap.revision_publish_date}
							</td>
							<td><span>累计印数：&emsp;&emsp;</span>
								${topicMap.revision_print}</td>
							<td><span>库存数：&emsp;&emsp;&emsp;</span>
								${topicMap.revision_stock}</td>
						</tr>
						<!-- 翻译书稿 -->
						<tr>
							<td colspan="3">
								<div>
									<span id="tsxz_span2"></span>
									<span class="tsxz_title">翻译书稿</span>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><span>译稿原书名：&emsp;</span>
								${topicMap.original_bookname}
							</td>
							<td><span>原编著者：&emsp;&emsp;</span>
								${topicMap.original_author}
							</td>
						</tr>
						<tr>
							<td><span>国&emsp;&emsp;籍：&emsp;&emsp;</span>
								${topicMap.nation}
							</td>
							<td><span>原出版者：&emsp;&emsp;</span>
								${topicMap.original_publisher}
							</td>
							<td><span>出版年代/版次：</span>
								${topicMap.edition}
							</td>
						</tr>
						<!--作（译）者简况 -->
						<tr>
							<td colspan="3">
								<div>
									<span id="tsxz_span5"></span>
									<span class="tsxz_title">作（译）者简况</span>
								</div>
							</td>
						</tr>
						<tr>
							<td><span>主编姓名：&emsp;&emsp;</span>
								${topicMap.realname}
							</td>
							<td><span>性&emsp;&emsp;别：&emsp;&emsp;</span>
								<c:if test="${topicMap.sex=='0'}">男</c:if>
								<c:if test="${topicMap.sex=='1'}">女</c:if>
							</td>
							<td><span>年&emsp;&emsp;龄：&emsp;&emsp;</span>
								${topicMap.price}
							</td>
						</tr>
						<tr>
							<td><span>行政职务：&emsp;&emsp;</span>
								${topicMap.position}
							</td>
							<td><span>专业职务：&emsp;&emsp;</span>
								<c:if test="${topicMap.position_profession=='0'}">中科院院士</c:if>
								<c:if test="${topicMap.position_profession=='1'}">工程院院士</c:if>
								<c:if test="${topicMap.position_profession=='2'}">博导</c:if>
								<c:if test="${topicMap.position_profession=='3'}">硕导</c:if>
								<c:if test="${topicMap.position_profession=='4'}">正高</c:if>
								<c:if test="${topicMap.position_profession=='5'}">副高</c:if>
								<c:if test="${topicMap.position_profession=='6'}">中级</c:if>
								<c:if test="${topicMap.position_profession=='7'}">其他</c:if>
							</td>
							<td><span>学&emsp;&emsp;历：&emsp;&emsp;</span>
								<c:if test="${topicMap.degree=='0'}">博士</c:if>
								<c:if test="${topicMap.degree=='1'}">硕士</c:if>
								<c:if test="${topicMap.degree=='2'}">学士</c:if>
								<c:if test="${topicMap.degree=='3'}">其他</c:if>
							</td>
						</tr>
						<tr>
							<td><span>工作单位：&emsp;&emsp;</span>
								${topicMap.workplace}
							</td>
							<td><span>电&emsp;&emsp;话：&emsp;&emsp;</span>
								${topicMap.phone}
							</td>
							<td><span>邮&emsp;&emsp;箱：&emsp;&emsp;</span>
								${topicMap.email}
							</td>
						</tr>
						<tr>
							<td><span>邮&emsp;&emsp;编：&emsp;&emsp;</span>
								${topicMap.postcode}
							</td>
							<td colspan="2"><span>通讯地址：&emsp;&emsp;</span>
								${topicMap.address}
							</td>
						</tr>
					</table>
					<table class="tab_1">
						<!-- 选题情况 -->
						<tr>
							<td><span>主要专业成就及学术地位：</span></td>
							<td colspan="3">
								<div class="content">
									<textarea class="text_cl" disabled maxlength="1000">${textraMap.achievement}</textarea>
								</div>
							</td>
						</tr><tr>
						<td><span>写作、外语水平：</span></td>
						<td colspan="3">
							<div class="content">
								<textarea class="text_cl" disabled maxlength="1000">${textraMap.ability}</textarea>
							</div>
						</td>
					</tr>
						<tr>
							<td colspan="4">
								<div>
									<span id="tsxz_span3"></span>
									<span class="tsxz_title">选题情况</span>
									<%--<span class="tsxz_ts1"><img src="${ctx}/statics/image/btxx.png" /></span>--%>
								</div>
							</td>
						</tr>
						<tr>
							<td><span>选题理由及出版价值：</span></td>
							<td colspan="3">
								<div class="content">
									<textarea class="text_cl" disabled maxlength="1000">${textraMap.reason}</textarea>
								</div>
							</td>
						</tr>
						<tr>
							<td><span>主要内容特色及特色：</span></td>
							<td colspan="3">
								<div class="content">
									<textarea class="text_cl" disabled maxlength="1000">${textraMap.score}</textarea>
								</div>
							</td>
						</tr>
					</table>
					<table class="tab_1">
						<!-- 读者情况及印制预测  -->
						<tr>
							<td colspan="3">
								<div>
									<span id="tsxz_span4"></span>
									<span class="tsxz_title">读者情况及印制预测 </span>
								</div>
							</td>
						</tr>
						<tr>
							<td><span>预计读者数及购买力：</span>
								${topicMap.reader_quantity}
							</td>
							<td><span>作者购书：</span>
								${topicMap.purchase}
							</td>
							<td><span>作者赞助：</span>
								${topicMap.sponsorship}
							</td>
						</tr>
						<tr>
							<td><span>可能的销售渠道：</span>
								${topicMap.sales_channel}
							</td>
							<td><span>图书生命周期：</span>
								${topicMap.lifecycle}
							</td>
							<td><span>成本估算：</span>
								${topicMap.cost}
							</td>
						</tr>
						<tr>
							<td><span>可能的宣传方式：</span>
								${topicMap.campaign}
							</td>
							<td><span>定价建议：</span>
								${topicMap.price_advise}
							</td>
							<td><span>预计印数：</span>
								${topicMap.print_number}
							</td>
						</tr>
						<tr>
							<td><span>印刷、用纸建议：</span>
								${topicMap.print_advise}
							</td>
							<td><span>保底印数：</span>
								${topicMap.min_print_number}
							</td>
							<td><span>效益估算：</span>
								${topicMap.benefit}
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 申报编者情况 -->
			<div class="sbxq_item">
				<div>
					<span id="tsxz_span6"></span>
					<span class="tsxz_title">申报编者情况 </span>
				</div>
				<div class="content">
					<table class="tab_2" id="sbbzqk">
						<thead>
						<tr>
							<td width="150px">姓名</td>
							<td width="100px">性别</td>
							<td width="100px">年龄 </td>
							<td width="100px">电话</td>
							<td width="100px">学历</td>
							<td width="200px">职务职称</td>
							<td width="300px">工作单位</td>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="list" items="${twriteList}" varStatus="status">
							<tr id="sbbz_${status.count}">
								<td>${list.realname}</td>
								<td>
									<c:if test="${list.sex == '0'}">男</c:if>
									<c:if test="${list.sex == '1'}">女</c:if>
								</td>
								<td>${list.price}</td>
								<td>${list.phone}</td>
								<td>
									<c:if test="${list.degree=='0'}">博士</c:if>
									<c:if test="${list.degree=='1'}">硕士</c:if>
									<c:if test="${list.degree=='2'}">学士</c:if>
									<c:if test="${list.degree=='3'}">其他</c:if>
								</td>
								<td>${list.position}</td>
								<td>${list.workplace}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!-- 外社同类书情况 -->
			<div class="sbxq_item">
				<div>
					<span id="tsxz_span3"></span>
					<span class="tsxz_title">外社同类书情况 </span>
				</div>
				<div class="content">
					<table class="tab_2" id="similar">
						<thead>
						<tr>
							<td width="250px">书名</td>
							<td width="100px">版次</td>
							<td width="100px">作者 </td>
							<td width="100px">开本 </td>
							<td>出版单位</td>
							<td width="100px">印数 </td>
							<td width="100px">定价 </td>
							<td width="150px">出版时间</td>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="list" items="${similarList}" varStatus="status">
							<tr id="similar_${status.count}">
								<td>${list.bookname}</td>
								<td>${list.edition}</td>
								<td>${list.author}</td>
								<td>${list.booksize}</td>
								<td>${list.publisher}</td>
								<td>${list.print_number}</td>
								<td>${list.price}</td>
								<td>${list.publish_date}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</form>
	</div>
	
	<!-- 退回原因及审批意见 显示悬浮框 -->
	<div class="bookmistake" id="return_cause_div">
	    <div class="apache">
	        <div class="mistitle">${topicMap.auth_progress==3?'审核意见':'' }${topicMap.auth_progress==2?'退回原因':'' }:</div>
	        <div class="xx" onclick="$('#return_cause_div').fadeOut(500);"></div>
	    </div>
	
	    <div class="info">
	        <input id="return_cause_hidden" type="hidden" value="${topicMap.auth_feedback }">
	        <textarea class="misarea" disabled="disabled">${topicMap.auth_feedback }</textarea>
	    </div>
	
	    <div class="">
	        <button class="btn" type="button" onclick="$('#return_cause_div').fadeOut(500);">确认</button>
	    </div>
	</div>
	<script type="text/javascript">
		if ("${(topicMap.auth_progress==3||topicMap.auth_progress==2)?'on':'off' }"=="on" && $("#return_cause_hidden").val().length>0) {
	
	        $("#return_cause_div").fadeIn(800);
	
	    }
	</script>
	
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
