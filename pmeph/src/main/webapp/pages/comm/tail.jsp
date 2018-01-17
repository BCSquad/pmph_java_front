<%--
  Created by IntelliJ IDEA.
  User: SuiXinYang
  Date: 2017/11/21
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="tail">
	<div style="height: 60px; width: 100%;"></div>
	<div class="about">
		<div class="item" style="border-left: 0px">
			<a style="text-decoration: none; color: #000;"
				href="${ctx}/pages/comm/about.jsp">关于平台</a>
		</div>
		<div class="item">
			<a style="text-decoration: none; color: #000;"
				href="${ctx}/pages/comm/contact.jsp">联系我们</a>
		</div>
		<div class="item">
			<a style="text-decoration: none; color: #000;"
				href="${ctx}/pages/comm/proposal.jsp">投诉建议</a>
		</div>
		<div class="item">
			<a style="text-decoration: none; color: #000;"
				href="${ctx}/pages/comm/help.jsp">帮助中心</a>
		</div>
	</div>
	<div class="copyright">北京创新乐知信息技术有限公司
		版权所有|江苏知之为计算机有限公司|江苏乐知网络技术有限公司 京 ICP 证 09002463 号</div>
	<div class="copyright">基建工程监督电话：59787010 基建工程监督信箱：jjjd@pmph.com</div>
</div>

