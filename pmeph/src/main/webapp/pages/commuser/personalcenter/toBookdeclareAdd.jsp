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
	<link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
	<link rel="stylesheet" href="${ctx 	}/statics/commuser/bookdeclare/bookdeclareadd.css?t=${_timestamp}" type="text/css">
	<link rel="stylesheet" href="${ctx}/statics/css/jquery.calendar.css?t=${_timestamp}" type="text/css">
	<link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}" type="text/css">
	<link rel="stylesheet" href="${ctx}/statics/css/jquery.tipso.css?t=${_timestamp}" type="text/css">
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js?t=${_timestamp}"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.calendar.js?t=${_timestamp}"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/layer/layer.js?t=${_timestamp}"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.tipso.js?t=${_timestamp}"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
	<script src="${ctx}/resources/comm/jquery/jquery.fileupload.js?t=${_timestamp}" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/resources/commuser/bookdeclare/bookdeclareadd.js?t=${_timestamp}"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
	<div class="content-wrapper">
		<div class="sbxq_title">
			<span><a style="text-decoration: none;color: #999999;" href="${contextpath}/medu/personalhomepage/tohomepage.action?pagetag=dt">个人中心</a> > <a style="text-decoration: none;color: #999999;" href="${contextpath}/medu/personalhomepage/tohomepage.action?pagetag=wycs&pageNum=1&pageSize=10">我要出书</a> > 填写选题申报表</span>
		</div>
		<form id="objForm">
			<!-- 图书书稿情况-->
			<div class="sbxq_item">

				<div class="content">
					<table class="tab_1 tab_4">
						<tr>
							<td colspan="3">
								<div style="text-align: left">
									<span id="tsxz_span1"></span>
									<span class="tsxz_title">图书书稿情况</span>
									<%--<span class="tsxz_ts1"><img src="${ctx}/statics/image/btxx.png" /></span>--%>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><span><span class="btbs">*</span>选题名称：&emsp;&emsp;</span>
								<input class="jc_input" name="bookname" id="bookname" placeholder="" value="" maxlength="100"/>
								<input type="hidden" name="user_id" value="${userMap.id}"/>
								<input type="hidden" name="topic_id" value="${topicMap.id}"/>
							</td>
							<td><span ><span class="btbs">*</span>读者对象：&emsp;&emsp;</span>
								<select name="reader" id="dzdx" class="dzdx">
									<option value="">-请选择-</option>
									<option value="0">医务工作者</option>
									<option value="1">医学院校师生</option>
									<option value="2">大众</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><span><span class="btbs1">*</span>预计交稿时间：</span>
								<input class="cg_input" name="deadline" id="deadline" calendar format="'yyyy-mm-dd'" value="" placeholder=""/>
								<%--<span class="dateclear"onclick="cleadate()"></span>--%>
							</td>
							<td><span ><span class="btbs">*</span>选题来源：&emsp;&emsp;</span>
								<select name="source" id="xzly" class="xzly">
									<option value="">-请选择-</option>
									<option value="0">社策划</option>
									<option value="1">编辑策划</option>
									<option value="2">修订</option>
									<option value="3">离退休编审策划</option>
									<option value="4">专家策划</option>
									<option value="5">上级交办</option>
									<option value="6">作者投稿</option>
								</select>
							</td>
							<td><span ><span class="btbs1">*</span>预估字数：&emsp;&emsp;</span>
								<input class="cg_input" name="word_number" id="word_number" value="" placeholder="单位千字"
									   onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
									   maxlength="9"/>
							</td>
						</tr>
						<tr>
							<td><span ><span class="btbs1">*</span>预估图数：&emsp;&emsp;</span>
								<input class="cg_input" name="picture_number" id="picture_number" value="" placeholder=""
									   onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
									   maxlength="9"/>
							</td>
							<td><span><span class="btbs1">*</span>学科及专业：&emsp;</span>
								<input class="cg_input" name="subject" id="subject" placeholder="" maxlength="50" value="" />
							</td>
							<td style="text-align: -webkit-center"><span><span class="btbs">*</span>级&emsp;&emsp;别：&emsp;&emsp;</span>
								<%--<div class="tsjb">&emsp;--%>
									<input type="radio" name="rank" value="0" checked="checked"/>低
									<input type="radio" name="rank" value="1" />中
									<input type="radio" name="rank" value="2" />高
								<%--</div>--%>
							</td>
						</tr>
						<tr>
							<td colspan="3" style="text-align: left"><span><span class="btbs">*</span>图书类别：&emsp;&emsp;</span>
								<%--<div class="tslb">&emsp;--%>
									<input type="radio" name="type" value="0" checked="checked"/>专著
									<input type="radio" name="type" value="1" />基础理论
									<input type="radio" name="type" value="2" />教材
									<input type="radio" name="type" value="3" />论文集
									<input type="radio" name="type" value="4" />图普
									<input type="radio" name="type" value="5" />科普
									<input type="radio" name="type" value="6" />应用技术
									<input type="radio" name="type" value="7" />教辅
									<input type="radio" name="type" value="8" />工具书
									<input type="radio" name="type" value="9" />其他
								<%--</div>--%>
							</td>
						</tr>
						<!--修订书稿-->
						<tr>
							<td colspan="3">
								<div style="text-align: left">
									<span id="tsxz_span2"></span>
									<span class="tsxz_title">修订书稿</span>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><span><span class="btbs1">*</span>原书名：&emsp;&emsp;&emsp;</span>
								<input class="jc_input" name="revision_bookname" id="revision_bookname" placeholder="" value="" maxlength="100"/>
							</td>
							<td><span><span class="btbs1">*</span>原编著者：&emsp;&emsp;</span>
								<input class="cg_input" name="revision_author" id="revision_author" placeholder="" value="" maxlength="100"/>
							</td>
						</tr>
						<tr>
							<td><span><span class="btbs1">*</span>上版出版时间：</span>
								<input class="cg_input" name="revision_publish_date" id="revision_publish_date" calendar format="'yyyy-mm-dd'"  value="" />
							</td>
							<td><span><span class="btbs1">*</span>累计印数：&emsp;&emsp;</span>
								<input class="cg_input" name="revision_print" id="revision_print" placeholder="" value=""
									   onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="9"/>
							</td>
							<td><span><span class="btbs1">*</span>库存数：&emsp;&emsp;&emsp;</span>
								<input class="cg_input" name="revision_stock" id="revision_stock" placeholder="" value=""
									   onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="9"/>
							</td>
						</tr>
						<!-- 翻译书稿 -->
						<tr>
							<td colspan="3">
								<div style="text-align: left">
									<span id="tsxz_span2"></span>
									<span class="tsxz_title">翻译书稿</span>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><span><span class="btbs1">*</span>译稿原书名：&emsp;</span>
								<input class="jc_input" name="original_bookname" id="original_bookname" placeholder="" value="" maxlength="100"/>
							</td>
							<td><span><span class="btbs1">*</span>原编著者：&emsp;&emsp;</span>
								<input class="cg_input" name="original_author" id="original_author" placeholder="" value="" maxlength="100"/>
							</td>
						</tr>
						<tr>
							<td><span><span class="btbs1">*</span>国&emsp;&emsp;籍：&emsp;&emsp;</span>
								<input class="cg_input" name="nation" id="nation" placeholder="" value="" maxlength="20"/>
							</td>
							<td><span><span class="btbs1">*</span>原出版者：&emsp;&emsp;</span>
								<input class="cg_input" name="original_publisher" id="original_publisher" placeholder="" value="" maxlength="100"/>
							</td>
							<td><span><span class="btbs1">*</span>出版年代版次：</span>
								<input class="cg_input" name="edition" id="edition" placeholder="" value="" maxlength="20"/>
							</td>
						</tr>
						<!--作（译）者简况 -->
						<tr>
							<td colspan="3">
								<div style="text-align: left">
									<span id="tsxz_span5"></span>
									<span class="tsxz_title">作（译）者简况</span>
								</div>
							</td>
						</tr>
						<tr>
							<td><span><span class="btbs">*</span>主编姓名：&emsp;&emsp;</span>
								<input class="cg_input" name="realname" maxlength="40" id="realname" placeholder="" value=""/>
							</td>
							<td><span><span class="btbs">*</span>性&emsp;&emsp;别：&emsp;&emsp;</span>
								<select name="sex" id="sex">
									<option value="">-请选择-</option>
									<option value="0">男</option>
									<option value="1">女</option>
								</select>
							</td>
							<td><span><span class="btbs">*</span>年&emsp;&emsp;龄：&emsp;&emsp;</span>
								<input class="cg_input" name="price" id="price" placeholder="" value=""
									   onkeyup="this.value=this.value.replace(/(\D|^0+)/g,'');this.value=this.value.replace(/^[^0-1]\d{2}$/g,'199');" onafterpaste="this.value=this.value.replace(/(\D|^0+)/g,'');this.value=this.value.replace(/^[^0-1]\d{2}$/g,'199');" maxlength="3"
								/>
							</td>
						</tr>
						<tr>
							<td><span><span class="btbs">*</span>行政职务：&emsp;&emsp;</span>
								<input class="cg_input" name="position" maxlength="36" id="position" placeholder="" value=""/>
							</td>
							<td><span><span class="btbs">*</span>专业职务：&emsp;&emsp;</span>
								<select name="position_profession" id="position_profession">
									<option value="">-请选择-</option>
									<option value="0">中科院院士</option>
									<option value="1">工程院院士</option>
									<option value="2">博导</option>
									<option value="3">硕导</option>
									<option value="4">正高</option>
									<option value="5">副高</option>
									<option value="6">中级</option>
									<option value="7">其他</option>
								</select>
							</td>
							<td><span><span class="btbs">*</span>学&emsp;&emsp;历：&emsp;&emsp;</span>
								<select name="degree" id="degree">
									<option value="">-请选择-</option>
									<option value="0">博士</option>
									<option value="1">硕士</option>
									<option value="2">学士</option>
									<option value="3">其他</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><span><span class="btbs">*</span>工作单位：&emsp;&emsp;</span>
								<input class="cg_input" name="workplace" maxlength="50" id="workplace" placeholder="" value=""/>
							</td>
							<td><span><span class="btbs">*</span>电&emsp;&emsp;话：&emsp;&emsp;</span>
								<input class="cg_input" name="phone" maxlength="50" id="phone" placeholder="" value=""/>
							</td>
							<td><span><span class="btbs">*</span>邮&emsp;&emsp;箱：&emsp;&emsp;</span>
								<input class="cg_input" name="email" maxlength="50" id="email" placeholder="" value=""/>
							</td>
						</tr>
						<tr>
							<td><span><span class="btbs">*</span>邮&emsp;&emsp;编：&emsp;&emsp;</span>
								<input class="cg_input" name="postcode" maxlength="36" id="postcode" placeholder="" value=""/>
							</td>
							<td colspan="2"><span><span class="btbs">*</span>通讯地址：&emsp;&emsp;</span>
								<input class="tx_input" name="address" maxlength="100" id="address" placeholder="" value="" style="width: 680px"/>
							</td>
						</tr>
					</table>
					<table class="tab_1">
						<!-- 选题情况 -->
						<tr>
							<td><span class="btbs">*</span><span>主要专业成就及学术地位：</span></td>
							<td colspan="3">
								<div class="content">
									<textarea class="text_cl" id="extra_achievement" name="extra_achievement" maxlength="1000"></textarea>
								</div>
							</td>
						</tr><tr>
						<td><span class="btbs1">*</span><span>写作、外语水平：</span></td>
						<td colspan="3">
							<div class="content">
								<textarea class="text_cl" id="extra_ability" name="extra_ability" maxlength="1000"></textarea>
							</div>
						</td>
					</tr>
						<tr>
							<td colspan="4">
								<div style="text-align: left">
									<span id="tsxz_span3"></span>
									<span class="tsxz_title">选题情况</span>
									<%--<span class="tsxz_ts1"><img src="${ctx}/statics/image/btxx.png" /></span>--%>
								</div>
							</td>
						</tr>
						<tr>
							<td><span class="btbs">*</span><span>选题理由及出版价值：</span></td>
							<td colspan="3">
								<div class="content">
									<textarea class="text_cl" id="extra_reason" name="extra_reason" maxlength="1000"></textarea>
								</div>
							</td>
						</tr>
						<tr>
							<td><span class="btbs">*</span><span>主要内容与特色：</span></td>
							<td colspan="3">
								<div class="content">
									<textarea class="text_cl" id="extra_score" name="extra_score" maxlength="1000"></textarea>
								</div>
							</td>
						</tr>
					</table>
					<table class="tab_1 tab_3">
						<!-- 读者情况及印制预测  -->
						<tr>
							<td colspan="3">
								<div style="text-align: left">
									<span id="tsxz_span4"></span>
									<span class="tsxz_title">读者情况及印制预测 </span>
								</div>
							</td>
						</tr>
						<tr>
							<td><span>预计读者数及购买力：</span>
								<input class="cg_input" name="reader_quantity" id="reader_quantity" placeholder="" value=""
									   maxlength="100"/>
							</td>
							<td><span>作者购书：&emsp;&emsp;</span>
								<input class="cg_input" name="purchase" id="purchase" placeholder="" value=""
									   <%--onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"--%>
									   maxlength="9"/>
							</td>
							<td><span>作者赞助：</span>
								<input class="cg_input" name="sponsorship" id="sponsorship" placeholder="" value=""
									   onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
									   maxlength="9"/>
							</td>
						</tr>
						<tr>
							<td><span>可能的销售渠道：&emsp;&emsp;</span>
								<input class="cg_input" name="sales_channel" id="sales_channel" placeholder="" value="" maxlength="50"/>
							</td>
							<td><span>图书生命周期：</span>
								<input class="cg_input" name="lifecycle" id="lifecycle" placeholder="" value="" maxlength="50"/>
							</td>
							<td><span>成本估算：</span>
								<input class="cg_input" name="cost" id="cost" placeholder="" value="" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<td><span>可能的宣传方式：&emsp;&emsp;</span>
								<input class="cg_input" name="campaign" id="campaign" placeholder="" value="" maxlength="50"/>
							</td>
							<td><span>定价建议：&emsp;&emsp;</span>
								<input class="cg_input" name="price_advise" id="price_advise" placeholder="" value="" maxlength="50"/>
							</td>
							<td><span>预计印数：</span>
								<input class="cg_input" name="print_number" id="print_number" placeholder="" value="" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<td><span>印刷、用纸建议：&emsp;&emsp;</span>
								<input class="cg_input" name="print_advise" id="print_advise" placeholder="" value="" maxlength="50"/>
							</td>
							<td><span>保底印数：&emsp;&emsp;</span>
								<input class="cg_input" name="min_print_number" id="min_print_number" placeholder="" value="" maxlength="50"/>
							</td>
							<td><span>效益估算：</span>
								<input class="cg_input" name="benefit" id="benefit" placeholder="" value="" maxlength="50"/>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 主要参编者 -->
			<div class="sbxq_item">
				<div>
					<span id="tsxz_span6"></span>
					<span class="tsxz_title">主要参编者 </span>
					<div class="sb_tj">
						<div class="tj_div" onclick="add_zjky()">
							<span id="img_1"></span>
							<span id="wz_1">添加</span>
						</div>
					</div>
				</div>
				<div class="content">
					<table class="tab_2" id="sbbzqk">
						<thead>
						<tr>
							<td width="140px">姓名</td>
							<td width="100px">性别</td>
							<td width="90px">年龄 </td>
							<td width="140px">电话</td>
							<td width="100px">学历</td>
							<td width="200px">职务职称</td>
							<td width="300px">工作单位</td>
							<td width="80px">操作</td>
						</tr>
						</thead>
						<tbody>
						<tr id="sbbz_1">
							<td><input class="sb_input" style="width: 120px;" id="write_realname" name="write_realname" placeholder="姓名" maxlength="40" value=""/></td>
							<td>
								<select id="write_sex" name="write_sex">
									<option value="" >-请选择-</option>
									<option value="0" >男</option>
									<option value="1">女</option>
								</select>
							</td>
							<td><input class="sb_input" style="width: 70px;" name="write_price" placeholder="年龄" value="" id="write_price"
									   onkeyup="this.value=this.value.replace(/(\D|^0+)/g,'');this.value=this.value.replace(/^[^0-1]\d{2}$/g,'199');" onafterpaste="this.value=this.value.replace(/(\D|^0+)/g,'');this.value=this.value.replace(/^[^0-1]\d{2}$/g,'199');"
									   onBlur="checkAge(this)"
									   maxlength="3"/></td>
							<td><input class="sb_input" style="width: 120px;" name="write_phone" placeholder="电话" value="" id="write_phone" maxlength="36"/></td>
							<td>
								<select id="write_degree" name="write_degree">
									<option value="">-请选择-</option>
									<option value="0">博士</option>
									<option value="1">硕士</option>
									<option value="2">学士</option>
									<option value="3">其他</option>
								</select>
							</td>
							<td><input class="sb_input" style="width: 180px;" id="write_position"  name="write_position" placeholder="职务职称" value="" maxlength="36"/></td>
							<td><input class="sb_input" style="width: 280px;" id="write_workplace" name="write_workplace" placeholder="工作单位" value="" maxlength="36"/>
								<input type="hidden" name="checkbzqk" value="write_realname,write_sex,write_price,write_phone,write_degree,write_position,write_workplace"/>
							</td>
							<td><div class="add_div"><img class="add_img" src="${ctx}/statics/image/del.png" onclick="del_tr('sbbz_1')"></div></td>
						</tr>
						</tbody>
					</table>
				</div>
	</div>
			<!-- 外社同类书情况 -->
			<div class="sbxq_item">
				<div>
					<span id="tsxz_span3"></span>
					<span class="tsxz_title">外社同类书情况 </span>
					<div class="sb_tj">
						<div class="tj_div" onclick="add_similar()">
							<span id="img_1"></span>
							<span id="wz_2">添加</span>
						</div>
					</div>
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
							<td width="80px">操作</td>
						</tr>
						</thead>
						<tbody>
						<tr id="sbbz_2">
							<td><input class="sb_input" style="width: 230px;" id="similar_bookname" placeholder="书名" name="similar_bookname"  maxlength="40" value=""/></td>
							<td><input class="sb_input" style="width: 80px;" id="similar_edition" placeholder="版次" name="similar_edition"  maxlength="2" value="" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
							<td><input class="sb_input" style="width: 80px;" id="similar_author" placeholder="作者" name="similar_author"  maxlength="100" value=""/></td>
							<td><input class="sb_input" style="width: 80px;" id="similar_booksize" placeholder="开本" name="similar_booksize"  maxlength="20" value=""/></td>
							<td><input class="sb_input" style="width: 160px;" id="similar_publisher" placeholder="出版单位" name="similar_publisher"  maxlength="100" value=""/></td>
							<td><input class="sb_input" style="width: 80px;" id="similar_print_number" placeholder="印数" name="similar_print_number"  maxlength="20" value=""/></td>
							<td><input class="sb_input" style="width: 80px;" id="similar_price" name="similar_price" placeholder="定价"  maxlength="20" value=""/></td>
							<td><input class="sb_input" style="width: 130px;" id="similar_publish_date" placeholder="出版时间" name="similar_publish_date"  calendar format="'yyyy-mm-dd'" value=""/></td>
							<input type="hidden" name="checkbzqk" value="similar_bookname,similar_edition,similar_author,similar_publisher,similar_print_number,similar_price,similar_publish_date"/>
							<td><div class="add_div"><img class="add_img" src="${ctx}/statics/image/del.png" onclick="del_tr('sbbz_2')"></div></td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div style=" height:1px;border:none;border-top:1px #c1c1c1 dashed;margin-top: 30px;width: 1200px;"></div>
			<div class="button">
				<div class="div_butt">
					<div class="bt_tj" id="butj" onclick="javascript:buttAdd('1')">提交</div>
					<div class="bt_tj" id="buzc" onclick="javascript:buttAdd('2')">暂存</div>
				</div>

			</div>
		</form>
	</div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
