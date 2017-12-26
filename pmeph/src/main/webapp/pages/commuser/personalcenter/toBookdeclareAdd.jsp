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
<script type="text/javascript" src="${ctx}/resources/commuser/bookdeclare/bookdeclareadd.js"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
	<div class="content-wrapper">
		<div class="sbxq_title">
			<span>个人中心 > 我要出书 > 填写申报表</span>
		</div>
		<form id="objForm">
		<!-- 图书书稿情况-->
		 <div class="sbxq_item">
			
			<div class="content">
				<table class="tab_1">
					<tr>
						<td colspan="4">
							<div>
								<span id="tsxz_span1"></span>
								<span class="tsxz_title">图书书稿情况</span>
								<span class="tsxz_ts1"><img src="${ctx}/statics/image/btxx.png" /></span>
							</div>
						</td>
					</tr>
					<tr>
						<td width="140px"><span class="btbs">*</span><span>选题名称：</span></td>
						<td width="460px">
							<input class="cg_input" name="bookname" id="bookname" placeholder="选题名称" value="" maxlength="100"/>
						</td>
						<td width="100px"><span class="btbs">*</span><span>读者对象：</span></td>
						<td width="300px">
							<select name="reader" id="dzdx" class="dzdx">
								<option value="0">医务工作者</option>
								<option value="1">医学院校师生</option>
								<option value="2">大众</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><span class="btbs">*</span><span>预计交稿时间：</span></td>
						<td>
							<input class="cg_input" name="deadline" id="deadline" calendar format="'yyyy-mm-dd'" value="" pattern="预计交稿时间"/>
						</td>
						<td><span class="btbs">*</span><span>选题来源：</span></td>
						<td>
							<select name="" id="xzly" class="xzly">
								<option value="0">社策划</option>
								<option value="1">编辑策划</option>
								<option value="2">专家策划</option>
								<option value="3">离退休编审策划</option>
								<option value="4">上级交办</option>
								<option value="5">作者投稿</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><span class="btbs">*</span><span>预估字数：</span></td>
						<td>
							<input class="cg_input" name="word_number" id="word_number" value=""  maxlength="20"/>
						</td>
						<td><span class="btbs">*</span><span>预估图数：</span></td>
						<td>
							<input class="cg_input" name="picture_number" id="picture_number" value=""  maxlength="20"/>
						</td>
					</tr>
						<tr>
						<td><span class="btbs">*</span><span>学科及专业：</span></td>
						<td>
							<input class="cg_input" name="subject" id="subject" placeholder="学科及专业" value="" />
						</td>
						<td><span class="btbs">*</span><span>级别：</span></td>
						<td>
							<div class="tsjb">
								<input type="radio" name="rank" value="0" />低
								<input type="radio" name="rank" value="1" />中
								<input type="radio" name="rank" value="2" />高
							</div>
						</td>
					</tr>
					<tr>
						<td><span class="btbs">*</span><span>图书类别：</span></td>
						<td colspan="3">
							<div class="tslb">
								<input type="radio" name="type" value="0" />专著
								<input type="radio" name="type" value="1" />基础理论
								<input type="radio" name="type" value="2" />论文集
								<input type="radio" name="type" value="3" />科普
								<input type="radio" name="type" value="4" />应用技术
								<input type="radio" name="type" value="5" />工具书
								<input type="radio" name="type" value="6" />其他
							</div>
						</td>
					</tr>
					<!-- 结算信息 -->
					<tr>
						<td colspan="4">
							<div>
								<span id="tsxz_span1"></span>
								<span class="tsxz_title">结算信息</span>
							</div>
						</td>
					</tr>
					<tr>
						<td><span>银行账户：</span></td>
						<td>
							<input class="cg_input" name="bank_account_id" id="bank_account_id" placeholder="银行账户" value=""/>
						</td>
						<td><span>开户银行：</span></td>
						<td>
							<input class="cg_input" name="realname" id="realname" placeholder="开户银行" value=""/>
						</td>
					</tr>
					<!-- 选题情况 -->
					<tr>
						<td colspan="4">
							<div>
								<span id="tsxz_span1"></span>
								<span class="tsxz_title">选题情况</span>
								<span class="tsxz_ts1"><img src="${ctx}/statics/image/btxx.png" /></span>
							</div>
						</td>
					</tr>
					<tr>
						<td><span>一、选题理由：</span></td>
						<td colspan="3">
							<div class="content">
								<textarea class="text_cl" name="content"></textarea>
							</div>
						</td>
					</tr>
					<tr>
						<td><span>二、出版价值：</span></td>
						<td colspan="3">
							<div class="content">
								<textarea class="text_cl" name="content"></textarea>
							</div>
						</td>
					</tr>
					<tr>
						<td><span>三、主要内容：</span></td>
						<td colspan="3">
							<div class="content">
								<textarea class="text_cl" name="content"></textarea>
							</div>
						</td>
					</tr>
					<!-- 读者情况及印刷预测  -->
					<tr>
						<td colspan="4">
							<div>
								<span id="tsxz_span1"></span>
								<span class="tsxz_title">读者情况及印刷预测 </span>
							</div>
						</td>
					</tr>
					<tr>
						<td><span>作者购书：</span></td>
						<td>
							<input class="cg_input" name="purchase" id="purchase" value=""/>
						</td>
						<td><span>作者赞助：</span></td>
						<td>
							<input class="cg_input" name="sponsorship" id="sponsorship" value=""/>
						</td>
					</tr>
					<!-- 翻译书稿 -->
					<tr>
						<td colspan="4">
							<div>
								<span id="tsxz_span1"></span>
								<span class="tsxz_title">翻译书稿</span>
							</div>
						</td>
					</tr>
					<tr>
						<td><span>译稿原书名：</span></td>
						<td>
							<input class="cg_input" name="original_bookname" id="original_bookname" placeholder="" value=""/>
						</td>
						<td><span>原编著者：</span></td>
						<td>
							<input class="cg_input" name="original_author" id="original_author" placeholder="" value=""/>
						</td>
					</tr>
					<tr>
						<td><span>出版年代及版次：</span></td>
						<td>
							<input class="cg_input" name="edition" id="edition" placeholder="" value=""/>
						</td>
						<td><span>国籍：</span></td>
						<td>
							<input class="cg_input" name="nation" id="nation" placeholder="" value=""/>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!-- 申报编者情况 -->
		<div class="sbxq_item">
			<div>
				<span id="tsxz_span1"></span>
				<span class="tsxz_title">申报编者情况 </span>
				<div class="sb_tj">
					<span></span>
					<span>添加</span>
				</div>
			</div>
			<div class="content">
				<table class="tab_2">
					<thead>
						<tr>
							<td width="120px">姓名</td>
							<td width="100px">性别</td>
							<td width="100px">年龄 </td>
							<td>行政职务</td>
							<td width="220px">工作单位</td>
							<td width="100px">操作</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>	
		<div style=" height:1px;border:none;border-top:1px #c1c1c1 dashed;margin-top: 30px;width: 1000px;"></div>
		<div class="button">
			<div class="div_butt">
				<div class="bt_tj" onclick="javascript:buttAdd('1')">提交</div>
				<div class="bt_tj" onclick="javascript:buttAdd('2')">暂存</div>
				<div class="bt_tj" onclick="javascript:buttGive()">放弃</div>
			</div>

		</div>
		</form>
	</div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>