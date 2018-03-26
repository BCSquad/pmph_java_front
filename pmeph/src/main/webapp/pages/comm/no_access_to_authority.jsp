<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.bc.pmpheep.back.util.Const" %>
<%@ page import="org.apache.commons.collections.MapUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.bc.pmpheep.back.commuser.userinfo.service.UserInfoService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<head>
	<script>
        var contextpath='${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>待办事项</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/authadmin/backlog/schedule.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js"></script>
    
</head>
<style type="text/css">
.upDiv{
    width: auto;
    height: 70px;
    margin-top: 34px;
    padding-left: 72px;

}
.tip{
    position:absolute;
    top:35%;
    left:30%;
    z-index:20;
    width: 390px;
    height: 204px;
    display: block; 
    background-color: #ffffff;
    border-radius: 6px;
    box-shadow: 0px 4px 10px 0px
    rgba(0, 0, 0, 0.2);
}
.tipPicture{
    width: 70px;
    height: 70px;
    float: left;
}
.tipWords{
    width: 170px;
    height: 37px;
    font-family: MicrosoftYaHei;
    font-size: 14px;
    font-weight: normal;
    font-stretch: normal;
    line-height: 24px;
    letter-spacing: 0px;
    color: #333333;
    margin-top: 14px;
    float: left;
    margin-left: 16px;
}
.downDiv{
    width: auto;
    height: 60px;

    padding-left: 106px;
    padding-top: 40px;
}
.leftButton,.checkButton{
    height: 30px;
    border-radius: 4px;
    padding: 0 10px;
    cursor: pointer;
    border: solid 1px #03717b;
    font-family: MicrosoftYaHei;
    font-size: 14px;
    font-weight: normal;
    font-stretch: normal;
    line-height: 30px;
    letter-spacing: 0px;
    text-align: center;
    color: #03717b;
    /*padding-top: 3px;*/
    float: left;
}

.checkButton{
    margin-left: 20px;
    /*height: 27px;*/
    background-color: #03717b;
    /*border-radius: 4px;*/
    /*font-family: MicrosoftYaHei;*/
    /*font-size: 14px;*/
    /*font-weight: normal;*/
    /*font-stretch: normal;*/
    /*line-height: 24px;*/
    /*letter-spacing: 0px;*/
    color: #ffffff;
    /*float: left;*/
    /*text-align: center;*/
    /*padding-top: 3px;*/
}

        #right .select-wrapper {
            border: none;
        }
         #right .select-button {
            background: #f6f6f6;
        }
    </style>
<body>
<%--<%--%>
    <%--Map<String, Object> userInfo = null;--%>
    <%--if ("2".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {--%>
        <%--userInfo = (Map<String, Object>) session.getAttribute(Const.SESSION_USER_CONST_ORGUSER);--%>
    <%--}--%>

    <%--if (userInfo != null) {--%>
        <%--ApplicationContext applicationContext =--%>
                <%--WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());--%>
        <%--UserInfoService userInfoService = applicationContext.getBean("com.bc.pmpheep.back.commuser.userinfo.service.UserInfoServiceImpl", UserInfoService.class);--%>
        <%--Map<String, Object> map = userInfoService.queryValidate(MapUtils.getString(userInfo, "id"));--%>
        <%--request.setAttribute("progress", map.get("progress"));--%>
    <%--}--%>
<%--%>--%>
<jsp:include page="/pages/comm/headGreenBackGround.jsp">
    <jsp:param name="pageTitle" value="<%=request.getParameter(\"pageType\")  %>"></jsp:param>
</jsp:include>
        <div class="tip" id="tip">
                <div class="upDiv">
                    <div class="tipPicture" >
                        <img src="${ctx}/statics/pictures/sad.png">
                    </div>
                    <div class="tipWords">
                        <span>${SESSION_USER_CONST_ORGUSER.progress==2?'抱歉,您提交的管理员认证资料已被退回,请您修改后重试':'您目前还不是机构管理员,快去认证吧'}</span>
                    </div>
                </div>
                <div class="downDiv">
                    <!-- <div class="leftButton" onclick="toogleTip('none')">知道了</div> -->
                    <%--<div class="checkButton" onclick="toAuthAudit(${ SESSION_USER_CONST_ORGUSER.id})">${progress==2?'重新认证':'马上认证'}</div>--%>
                    <div class="checkButton" onclick="toAuthAudit(${ SESSION_USER_CONST_ORGUSER.id})">${ SESSION_USER_CONST_ORGUSER.progress==2?'重新认证':'马上认证'}</div>
                </div>
                <!-- <div class="close" id="close" onclick="toogleTip('none')">
                    <span>×</span>
                </div> -->
            </div>
</body>
<script>
function toAuthAudit(userId){
	window.location.href="${ctx}/admininfocontroller/toadminattest.action?userId="+userId;
}
</script>
</html>
