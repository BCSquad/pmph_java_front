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
<title>${title}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
 <link rel="stylesheet" href="${ctx}/statics/materialdec/material.css?t=${_timestamp}" type="text/css">
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js?t=${_timestamp}"></script>
	<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.jqprint-0.3.js?t=${_timestamp}"></script>
	<script src="http://www.jq22.com/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
	<script src="${ctx}/resources/comm/jquery/jquery.fileupload.js?t=${_timestamp}" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/commuser/materialdec/showExpertation.js?t=${_timestamp}"></script>
</head>
<body>
<c:if test="${userType == 'org' }">
	<jsp:include page="/pages/comm/headGreenBackGround.jsp">
    <jsp:param name="pageTitle" value="backlog"></jsp:param>
</jsp:include>
</c:if>
<c:if test="${userType != 'org' }">
	<jsp:include page="/pages/comm/head.jsp"></jsp:include>
</c:if>


<div class="body">
	<input type="hidden" id="user_id" value="${gezlList.user_id }"> 
	<input type="hidden" id="expertation_id" value="${gezlList.id}"> 
	
	
	<div class="content-wrapper">
		<input type="hidden" name="expert_type" id="expert_type" value="${queryMap.expert_type}">
		<input type="hidden" name="product_id" id="product_id" value="${queryMap.product_id}">
		<input type="hidden" id="printout" value="${state}">
		<div class="sbxq_title">
		<c:choose>
			<c:when test="${userType == 'org'}">
				
			</c:when>
			<c:otherwise>
				<span><a style="text-decoration: none;color: #999999;" href="${contextpath}/personalhomepage/tohomepage.action?pagetag=dt">个人中心</a> > <a style="text-decoration: none;color: #999999;" href="${contextpath}/personalhomepage/tohomepage.action?pagetag=lcjc"> 临床决策专家申报 </a> > <span id="product_name"></span></span>
			</c:otherwise>
		</c:choose>
		</div>
		<div id="ddd">
			<div class="tsxz_title" id="tnone" style="display: none;text-align: center;font-size: 20px;margin-top: 6px;">${material.material_name}</div>
		<!-- 专家信息-->
		<div class="sbxq_item1" style="display: block;">
			<div>
				<span id="tsxz_span2"></span>
				<span class="tsxz_title">专家信息</span>
			</div>
			<div class="content">
				<table class="tab_1">
					<tr>
						<td><span title="${gezlList.realname}">姓&emsp;&emsp;名：${gezlList.realname}</span></td>
						<td><span>性&emsp;&emsp;别：
								<c:if test="${gezlList.sex == '0'}">保密</c:if>
								<c:if test="${gezlList.sex == '1'}">男</c:if>
								<c:if test="${gezlList.sex == '2'}">女</c:if>
							</span></td>
						<td><span title="${gezlList.birthday1}">出生年月：${gezlList.birthday1}</span></td>
						<td><span title="${gezlList.org_name}">工作单位：${gezlList.org_name}</span></td>
					</tr>
					<tr>

						<td><span title="${gezlList.position}">职&emsp;&emsp;务：${gezlList.position}</span></td>
						<td><span title="${gezlList.title}">职&emsp;&emsp;称：${gezlList.title}</span></td>
						<td><span title="${gezlList.telephone}">联系电话：${gezlList.telephone}</span></td>
						<td><span title="${gezlList.handphone}">手&emsp;&emsp;机：${gezlList.handphone}</span></td>
					</tr>
					<tr>
						<td><span title="${gezlList.email}">邮&emsp;&emsp;箱：${gezlList.email}</span></td>
						<td><span>证件类型：
								<c:if test="${gezlList.idtype == '0'}">身份证</c:if>
								<c:if test="${gezlList.idtype == '1'}">护照</c:if>
								<c:if test="${gezlList.idtype == '2'}">军官证</c:if>
							</span>
						</td>
						<td><span title="${gezlList.idcard}">证件号码：${gezlList.idcard}</span></td>
						<td><span>学&emsp;&emsp;历：
                                <c:if test="${gezlList.education == '0'}">无</c:if>
						        <c:if test="${gezlList.education == '1'}">专科</c:if>
								<c:if test="${gezlList.education == '2'}">本科</c:if>
								<c:if test="${gezlList.education == '3'}">硕士</c:if>
							    <c:if test="${gezlList.education == '4'}">博士</c:if>
						    </span>
						</td>
					</tr>
					<tr>
						<td><span title="${gezlList.postcode}">邮&emsp;编：${gezlList.postcode}</span></td>
						<td><span title="${gezlList.banknumber}">卡号：${gezlList.banknumber}</span></td>
						<td colspan="2"><span title="${gezlList.bankaddress}">开户行：${gezlList.bankaddress}</span></td>
					</tr>
					<tr>
                        <td colspan="4"><span title="${gezlList.expertise}">专业特长(疾病诊治及研究方向)：${gezlList.expertise}</span></td>
						<%--<td><span>传&emsp;&emsp;真：${gezlList.fax}</span></td>--%>
					</tr>
                    <tr>
                        <td colspan="4"><div class="adress" title="${gezlList.address}">地&emsp;&emsp;址：${gezlList.address}</div></td>
                    </tr>

				</table>
			</div>
		</div>
		<!--主要学习经历-->
		<div class="sbxq_item" id="zyxxjl">
			<div>
				<span id="tsxz_span3"></span>
				<span class="tsxz_title">学习经历</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_xxjl">
					<thead>
						<tr>
							<td width="220px">起止时间</td>
							<td width="210px">学校名称</td>
							<td width="210px">所学专业</td>
							<td width="138px">学历</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="stulist" items="${stuList}">
							<tr>
								<td>${stulist.dbegin}-${stulist.dend}</td>
								<td>${stulist.school_name}</td>
								<td>${stulist.major}</td>
								<td>${stulist.degree}</td>
								<td>${stulist.note}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--主要工作经历-->
		<div class="sbxq_item" id="gzjl">
			<div>
				<span id="tsxz_span4"></span>
				<span class="tsxz_title">工作经历</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_gzjl">
					<thead>
						<tr>
							<td width="220px">起止时间</td>
							<td width="220px">工作单位</td>
							<td width="220px">职位</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${workList}">
							<tr>
								<td>${list.dbegin}-${list.dend}</td>
								<td>${list.org_name}</td>
								<td>${list.position}</td>
								<td>${list.note}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--主要学术兼职-->
		<div class="sbxq_item" id="xsjz">
			<div>
				<span id="tsxz_span10"></span>
				<span class="tsxz_title">主要学术兼职</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_xsjz">
					<thead>
						<tr>
							<td width="220px">兼职学术组织</td>
							<td width="220px">级别</td>
							<td width="220px">职务 </td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${zjxsList}">
							<tr>
								<td>${list.org_name}</td>
								<td>
									<c:if test="${list.rank == '0'}">无</c:if>
									<c:if test="${list.rank == '1'}">国际</c:if>
									<c:if test="${list.rank == '2'}">国家</c:if>
									<c:if test="${list.rank == '3'}">省部</c:if>
									<c:if test="${list.rank == '4'}">市级</c:if>
								</td>
								<td>${list.position}</td>
								<td>${list.note}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<!--人卫社教材编写情况-->
		<div class="sbxq_item" id="rwsjcbx">
			<div>
				<span id="tsxz_span5"></span>
				<span class="tsxz_title">人卫社教材编写情况</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_rwsjcbx">
					<thead>
						<tr>
							<td width="230px">教材名称</td>
							<td width="120px">级别</td>
							<td width="120px">编写职务</td>
							<td width="100px">数字编委</td>
							<td width="120px">出版时间</td>
							<td width="120px">标准书号</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${rwsjcList}" varStatus="status">
						<tr>
							<td>${list.material_name}</td>
							<td>
								<c:if test="${list.rank == '0'}">无</c:if>
								<c:if test="${list.rank == '1'}">国家</c:if>
								<c:if test="${list.rank == '2'}">省部</c:if>
								<c:if test="${list.rank == '3'}">协编</c:if>
								<c:if test="${list.rank == '4'}">校本</c:if>
								<c:if test="${list.rank == '5'}">其他</c:if>
							</td>
							<td>
								<c:if test="${list.position == '0'}">无</c:if>
								<c:if test="${list.position == '1'}">主编</c:if>
								<c:if test="${list.position == '2'}">副主编</c:if>
								<c:if test="${list.position == '3'}">编委</c:if>
							</td>
							<td style="color: #333333;">
								<c:if test="${list.is_digital_editor == '1'}">是</c:if>
								<c:if test="${list.is_digital_editor == '0'}">否</c:if>
							</td>
							<td>${list.publishdate}</td>
							<td>${list.isbn}</td>
							<td>${list.note}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--图书出版情况-->
		<div class="sbxq_item" id="zbxszz">
			<div>
				<span id="tsxz_span7"></span>
				<span class="tsxz_title">主编学术专著情况</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_zbxszz">
					<thead>
						<tr>
							<td width="240px">专著名称</td>
							<td width="140px">专著发表日期</td>
							<td width="120px">出版方式</td>
							<td width="220px">出版单位</td>
							<td width="150px">出版时间</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${monographList}">
						<tr>
							<td>${list.monograph_name}</td>
							<td>${list.monographdate}</td>
							<td>
								<c:if test="${list.is_self_paid == '0'}">公费</c:if>
								<c:if test="${list.is_self_paid == '1'}">自费</c:if>
							</td>
							<td>${list.publisher}</td>
							<td>${list.publishdate}</td>
							<td>${list.note}</td>
							</tr></c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<!--主编或参编图书情况-->
		<div class="sbxq_item" id="zbcbtsqk">
			<div>
				<span id="tsxz_span6"></span>
				<span class="tsxz_title">主编或参编图书情况</span>
			</div>
			<div class="content">
				<table class="tab_2" id="tab_zbtsqk">
					<thead>
					<tr>
						<td width="350px">教材名称</td>
						<td width="330px">出版社</td>
						<td width="160px">出版时间</td>
						<td>备注</td>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${editorList}" varStatus="status">
						<tr>
							<td>${list.material_name}</td>
							<td>${list.publisher}</td>
							<td>${list.publish_date}</td>
							<td>${list.note}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<!--文章发表情况（须第一作者，与本专业相关）-->
		<div class="sbxq_item" id="wzfbqk" >
				<div>
					<span id="tsxz_span6"></span>
					<span class="tsxz_title">文章发表情况（须第一作者，与本专业相关）</span>
				</div>
				<div class="content">
					<table class="tab_2" id="tab_wzfbqk">
						<thead>
						<tr>
							<td width="300px">题目</td>
							<td width="150px">期刊名称</td>
							<td width="100px">年、卷、期</td>
							<td width="250px">期刊级别（SCI或国内核心期刊）</td>
							<td>备注</td>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="list" items="${wzfbqkList}">
							<tr>
								<td>${list.title}</td>
								<td>${list.periodical_title}</td>
								<td>${list.year_volume_period}</td>
								<td>${list.periodical_level}</td>
								<td>${list.note}</td>
							</tr></c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		<!--本专业获奖情况-->
		<div class="sbxq_item" id="bzyhjqk" >
				<div>
					<span id="bzyhjqk_img"></span>
					<span class="tsxz_title">本专业获奖情况</span>
				</div>
				<div class="content">
					<table class="tab_2" id="tab_bzyhjqk">
						<thead>
						<tr>
							<td width="440px">名称</td>
							<td width="340px">级别（国家、省、市、单位）</td>
							<td>备注</td>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="list" items="${bzyhjqkList}">
							<tr>
								<td>${list.title}</td>
								<%--<td>
									<c:if test="${list.rank == '0'}">无</c:if>
									<c:if test="${list.rank == '1'}">国家</c:if>
									<c:if test="${list.rank == '2'}">省</c:if>
									<c:if test="${list.rank == '3'}">市</c:if>
									<c:if test="${list.rank == '4'}">单位</c:if>
								</td>--%>
								<td>${list.rank}></td>
								<td>${list.note}</td>
							</tr></c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		<!--扩展信息-->
		<c:forEach var="zjkzxx" items="${zjkzqkList}">
			<div class="sbxq_item1" style="display: block">
				<div style="margin-bottom: 3px">
					<span id="tsxz_span9"></span>
					<span class="tsxz_title">${zjkzxx.extension_name}</span>
				</div>
				<div class="content">
                    <div class="text_dy">
                            ${zjkzxx.content}
                    </div>
                </div>
			</div>
		</c:forEach>

        <div class="sbxq_item1" style="display: block">
            <div style="margin-bottom: 3px">
                <span id="beizhu"></span>
                <span class="tsxz_title">备注</span>
            </div>
            <div class="content">
                <div class="text_dy">
                    ${gezlList.remark}
                </div>
            </div>
        </div>


		<!-- 学科分类-->
		<div class="sbxq_item1" id="xkflxs">
			<div>
				<span id="tsxz_span8"></span>
				<span class="tsxz_title">学科分类</span>
				<%--<span class="el-button" onclick="javascript:SubjectdAdd('${materialMap.product_id}')">添加学科分类</span>--%>
			</div>
			<div class="sbdw" >
				<span class="btmc">学科分类：</span>
				<c:forEach var="subject" items="${subjectList}" varStatus="status">
				<span class="el-tag" id="xkfl_${status.count}">${subject.type_name}<input name="subjectId" type="hidden" value="${subject.product_subject_type_id}"/>
					</span>
				</c:forEach>
			</div>
		</div>

		<!-- 内容分类-->
		<div class="sbxq_item1" id="nrflxs">
			<div>
				<span id="tsxz_span12" style="margin-top: 6px"></span>
				<span class="tsxz_title">内容分类(可多选)</span>
				<%--<span class="el-button" onclick="javascript:ContentAdd('${materialMap.product_id}')">添加内容分类</span>--%>
			</div>
			<div class="sbdw" id="nrfladd">
				<span class="btmc">内容分类：</span>
				<c:forEach var="content" items="${contentList}" varStatus="status">
				<span class="el-tag" id="nrfl_${status.count}">${content.name_path}<input name="contentId" type="hidden" value="${content.product_content_type_id}"/>
					</span>
				</c:forEach>
			</div>
		</div>

		<!-- 申报专业-->
		<div class="sbxq_item1" id="sbzyxs">
			<div>
				<span id="sbzytb"></span>
				<span class="tsxz_title">申报专业</span>
				<%--<span class="el-button" onclick="javascript:SubjectdAdd('${materialMap.product_id}')">添加学科分类</span>--%>
			</div>
			<div class="sbdw">
				<span class="btmc">申报专业：</span>
				<c:forEach var="sbzy" items="${sbzyList}" varStatus="status">
		               <span class="el-tag" id="xkfl_${status.count}">${sbzy.type_name}</span>
				</c:forEach>
			</div>
		</div>

		<!-- 申报单位 -->
		<div class="sbxq_item1" style="display: block">
			<div>
				<span id="tsxz_span8"></span>
			</div>
			<div class="sbdw" id="xkfladd">
				<span class="btmc">申报单位：${org.org_name }</span>
			</div>
		</div>

		
		<c:if test="${userType == 'org'}">
		
			<c:if test="${state == 'audit' }">
				<div id="unit_advise_online_wrapper">
					<div>
						<span id="unit_advise_online_title">所在单位意见：</span>
						<%--<span class="el-button" onclick="javascript:SubjectdAdd('${materialMap.product_id}')">添加学科分类</span>--%>
					</div>
					<div>
						<textarea maxlength="500" id = "unit_advise_online" name = "unit_advise_online">${gezlList.unit_advise_online }</textarea>
					</div>
				</div>
			</c:if>
			
			
			<!-- 院校推荐意见(仅打印显示)-->
			<div class="yijian"  id="yijian">
				<div class="tujian01">院校推荐意见:</div>
				<div id="tujian00">${gezlList.unit_advise_online }</div>
				<div class="tujian02">
					<div class="qianzi">负责人签字:</div>
					<div class="gaizhang">(院校盖章)</div>
				</div>
				<div class="tujian03">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</div>
			</div>
			<!-- 院校推荐意见(仅打印显示) end -->
		</c:if>

        </div>
		
		
		<!-- 上传单位意见 -->
		<c:if test="${userType == 'org' && online_progress != 2}">
                <div class="sbxq_item" id="szdwyj" style="display: block">
                    <div>
                        <span id="tsxz_span13"></span>
                        <span class="tsxz_title"><%--<img src="${ctx}/statics/image/btxx.png" />--%>上传申报表扫描件<span style="color: red">(上传单位盖章的申报表)</span></span>
                    </div>
                    <div style="height: 30px;margin-top: 10px;">
                        <div class="scys" id="dwyjsc"><span>上传文件</span></div>
                        <div id="fileNameDiv" class="fileNameDiv"><a  onclick="downLoadProxy('${gezlList.unit_advise}')"
                                                 title="${gezlList.syllabus_name}">${gezlList.syllabus_name}</a></div>
                        <input type="hidden" name="syllabus_id" id="syllabus_id" value="${gezlList.unit_advise}"/>
                        <input type="hidden" name="syllabus_name" id="syllabus_name" value="${gezlList.syllabus_name}"/>
                        
                        <%--<div class="filename"><a href="javascript:" onclick="downLoadProxy('${gezlList.unit_advise}')"
                                                 title="${gezlList.syllabus_name}">${gezlList.syllabus_name}</a></div>--%>
                    </div>
                </div>
		</c:if>
		<!-- 上传单位意见 end -->


        <input type="hidden" id="declaration_id" value="${declaration_id}">
		<!-- 打印按钮(查看界面的打印按钮，与下面的确认打印不是同一个按钮)-->
		<c:if test="${state==out}">
			<div class="out" id="print_look" onclick="toprint()">打印</div>
		</c:if>

		
		<!-- 打印按钮(仅打印显示)-->
		<div class="out" id="print" onclick="toprint()" style="display: none">确认打印</div>
			
		<hr style=" height:1px;border:none;border-top:1px #999999 dashed;margin-top: 30px;">
		
		
		<!-- 机构用户审核显示  -->
		<c:if test="${state == 'audit'}">
			<div lass = "audit_wrapper">
				<div class="audit_middle">
				<c:if test="${online_progress != 3}">
					<div class="audit"  onclick="showup('${gezlList.id}','2')" >退回给个人</div>
                    <div class="audit pass"  id="shtg" onclick="toAuditPass('${gezlList.id}','3');changeColor()" >审核通过</div>
				</c:if>
					<div class="audit" id="hisprint" onclick="toprint('${gezlList.id}')" >打印</div>
					<div class="audit"  onclick="javascript:history.go(-1)" >返回</div>
				</div>
			</div>
		</c:if>
		
		
			<!-- 退回原因填写悬浮框 -->
	        <div class="bookmistake" id="bookmistake">
	            <form id="bookmistakeform">
	            <input type="hidden"  id="return_id" value="">
	            <input type="hidden"  id="return_type" value="">
	                <div class="apache">
	                    <div class="mistitle">退回原因:</div>
	                    <div class="xx" onclick="hideup()"></div>
	                </div>
	                
	                <div class="info">
	                    <textarea class="misarea" id="return_cause" onkeyup="javascript:LengthLimit(this,90);"
	                              onblur="javascript:LengthLimit(this,100);"></textarea>
	                    <c:if test="${expertChoosen }">
			                <div class="choosenWarning">
					          	提示:该作者已被遴选,退回将会同时 <font color="red" >撤销遴选</font> !
					          	</br>
					          	是否确认退回？
					        </div>
				        </c:if>
	                </div>
	                <div class="return_cause_btn_wrapper">
	                	<button class="btn" type="button" onclick="hideup()">取消</button>
	                    <button class="btn" type="button" onclick="correction()">确认</button>
	                </div>
	            </form>
	        </div>

	        <!-- 退回原因显示悬浮框 -->
	        <div class="bookmistake" id="return_cause_div">
	                <div class="apache">
	                    <div class="mistitle">退回原因:</div>
	                    <div class="xx" onclick="$('#return_cause_div').fadeOut(500);"></div>
	                </div>
	                
	                <div class="info">
	                	<input id="online_progress" type="hidden" value="${gezlList.online_progress }">
	                	<input id="return_cause_hidden" type="hidden" value="${gezlList.return_cause }">
	                    <textarea class="misarea" disabled="disabled">${gezlList.return_cause }</textarea>
	                </div>
	          
	                <div class="">
	                    <button class="btn" type="button" onclick="$('#return_cause_div').fadeOut(500);">确认</button>
	                </div>
	        </div>
		
		
		<!-- 机构用户审核显示 end  -->

		<%--<c:if test="${isSelfLog=='true' }">
			<div class="button">
				<div class="bt_tj" onclick="javascript:buttGive()">返回申报列表</div>
			</div>
			<span style="color: #E31028;font-size: 14px;text-align: center;float: left;margin-left: 350px;">打印推荐使用浏览器：chrome、360浏览器极速模式、IE浏览器支持IE10及以上版本</span>
		</c:if>--%>

	</div>

</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
<style>
    

    #yijian{
         display: none; 
         position: relative;
         min-height: 280px;
         height: unset;
    }
    #tujian00{
    	margin-bottom: 100px;
	    padding: 10px;
	    word-break:  break-all;
    }
    .tujian02 {
	    float: right;
	    position: absolute;
	    right: 0px;
	    clear: left;
	    bottom: 40px;
	}
	.tujian03 {
	    
	    bottom: 10px;
	    right: 10px;
	    float: right;
	    position: absolute;
	    text-align: right;
	    clear: right;
	}
	.out{
		width: 128px;
		height: 44px;
		background-color: #33CAA9;
		border-radius: 2px;
		margin: auto;
		cursor: pointer;
		color: #ffffff;
		margin-left: 500px;
		margin-right: 20px;
		margin-top: 20px;
		text-align: center;
		line-height: 42px;
	}
	.audit_wrapper{
		width: 100%;
	}
	.audit_middle{
		margin: auto;
    	width: 32em;
	}
	.audit{
	    line-height: 1em;
	    padding: 0.8em 0em;
    	width: 7em;
	    background-color: #33CAA9;
	    border-radius: 4px;
	    margin: 1.5em 0.5em;
	    cursor: pointer;
	    color: #ffffff;
	    text-align: center;
	    float: left;
	}
	.audit.pass{
		background-color:#33CAA9;
	}
	.footer {
    	clear: left;
	}
	
	.bookmistake {
    width: 426px;
    background-color: #FFFFFF;
    display: none;
    position: fixed;
    left: 35%;
    top: 30%;
    box-shadow: 0px 0px 5px #888888;
    border-radius: 5px;
    z-index: 999;
	}
	
	.apache {
	    width: 100%;
	    height: 27px;
	    float: left;
	    margin-top: 16px;
	}
	
	.mistitle {
	    width: 50%;
	    float: left;
	
	    line-height: 24px;
	    letter-spacing: 1px;
	    color: #333333;
	    font-size: 20px;
	    margin-left: 20px;
	}
	
	.xx {
	    width: 14px;
	    height: 14px;
	    background-image: url(../../image/close.png);
	    float: right;
	    margin-right: 10px;
	    cursor: pointer;
	}
	
	.info {
	    width: 100%;
	    /* height: 103px; */
	    box-sizing: border-box;
	    float: left;
	    margin-top: 25px;
	    padding: 0px 20px;
	}
	
	.misarea {
	    width: 374px;
	    height: 75px;
	    border: 1px solid #E0E0E0;
	    
	    padding: 5px;
	    resize: vertical;
	    max-height: 350px;
	}
	
	.btn {
	    float: right;
	    margin-right: 27px;
	    width: 84px;
	    height: 31px;
	    background-color: #1CB394;
	    border-radius: 3px;
	    border: none;
	    cursor: pointer;
	    color: #FFFFFF;
	    margin-bottom: 10px;
	}
	#nrflxs , #sbzyxs , #xkflxs{
		display:none;
	}
	#unit_advise_online{
	    width: 1030px;
	    border: 1px #000000 solid;
	    padding: 10px;
	    min-height: 100px;
    }
    
    
</style>
</html>
