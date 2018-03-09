<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
    <title>问卷列表</title>
    <script>
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <link rel="stylesheet" href="${ctx}/statics/commuser/survey/surveyList.css" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/comm/layer/layer.js"></script>
    <script src="${ctx}/resources/commuser/survey/surveyList.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body" style="background-image: url('../statics/image/ypzj_bg.png');height:auto">

    <div class="content-wrapper">
        <div class="emptydiv"></div>
        <div class="bigContent">
            <div class="title">
                <span class="titleFont">所有问卷</span>
            </div>
            <c:choose>
                <c:when test="${listSize>0}">

                    <div class="researches">
                        <c:forEach var="survey" items="${list}">
                            <div class="oneResearch">
                                <div class="researchPic">
                                    <img class="newPicSize" src="../statics/image/research1.png">
                                </div>
                                <div class="researchContent">
                                    <span class="newResearchContentFont"
                                          <c:if test="${survey.isvalid=='1'}">style="cursor: pointer;color:#333; " onclick="detail(${survey.id})"</c:if> >
                                          ${survey.title}<c:if test="${survey.isvalid!='1'}">(已失效)</c:if>
                                    </span>
                                </div>
                                <div class="resaerchTime">
                                    <span class="newTimeFont"><fmt:formatDate pattern="yyyy年MM月dd日"
                                                                              value="${survey.begin_date}"/></span>
                                </div>
                            </div>
                        </c:forEach>
                        <!-- <div class="oneResearch">
                            <div class="researchPic">
                                <img class="oldPicSize" src="../statics/image/research2.png">
                            </div>
                            <div class="researchContent">
                                <span class="oldResearchContentFont" onclick="detail()">交通方式问卷调查</span>
                            </div>
                            <div class="resaerchTime">
                                <span class="oldTimeFont">2017-12-20</span>
                            </div>
                        </div> -->
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="no-more">
                        <img src="<c:url value='/statics/image/aaa4.png'></c:url>">
                        <span>木有内容呀~~</span>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="emptydiv"></div>
    </div>
</div>

<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>