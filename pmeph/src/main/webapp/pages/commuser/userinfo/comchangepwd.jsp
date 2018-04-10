<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>修改密码(作家用户)</title>
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/json2.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/commuser/userinfo/comchangepwd.js?t=${_timestamp}" type="text/javascript"></script>
    <link href="${ctx}/statics/css/base.css?t=${_timestamp}" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/statics/authadmin/accountset/publicStyle.css?t=${_timestamp}" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/statics/authadmin/accountset/changepwd.css?t=${_timestamp}" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div class="body">
    <div class="content-wrapper">
        <div style="height:30px"></div>
        <div class="div-content">
            <div id="div-child">
                <span class="span1"></span>
                <span class="div-menu-main">修改密码</span>
                <a href="#" class="return-menu-main"
                    onclick="window.location='${ctx}/userinfo/touser.action'"><< 返回个人资料</a>
            </div>
        </div>
        <div style="height:14px"></div>
        <div class="div-content"  style="height:546px;">
            <table border="0">
                <tr>
                    <td class="td-lable-right" align="right"><font color="#ff3d38">*</font>新密码</td>
                    <td class="td-input"><input class="txt" type="password" id="newpassword"/></td>
                    <td class="td-lable-left">请输入您的新密码（6-16位字母、数字或符号的组合）</td>
                </tr>
                <tr>
                    <td class="td-lable-right" align="right"><font color="#ff3d38">*</font>确认新密码</td>
                    <td class="td-input"><input class="txt" type="password" id="confirmpassword"/></td>
                    <td class="td-lable-left">请再次输入您的密码</td>
                </tr>
                <tr><td colspan="3" align="center" ><img alt="" src="${ctx}/statics/image/_cupline.jpg" /></td></tr>
                <tr>
                    <td colspan="3" align="center" >
                        <input type="button" class="btn-4" value="确认修改" onclick="confirupd()"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
